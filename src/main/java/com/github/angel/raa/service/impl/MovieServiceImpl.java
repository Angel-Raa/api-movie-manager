package com.github.angel.raa.service.impl;

import com.github.angel.raa.dto.MovieDTO;
import com.github.angel.raa.excpetion.ResourceException;
import com.github.angel.raa.persistence.entity.Movie;
import com.github.angel.raa.persistence.repository.MovieRepository;
import com.github.angel.raa.persistence.specification.MovieSpecification;
import com.github.angel.raa.service.MovieService;
import com.github.angel.raa.utils.Genero;
import com.github.angel.raa.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository repository;


    private static MovieDTO mapMovieToDTO(@NotNull Movie movie) {
        return MovieDTO.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitulo())
                .director(movie.getDirector())
                .descripcion(movie.getDescripcion())
                .genero(movie.getGenero())
                .releaseYear(movie.getReleaseYear())
                .build();
    }
    private static Movie mapDTOToMovie(@NotNull MovieDTO movieDTO) {
        return Movie.builder()
                .movieId(movieDTO.getMovieId())
                .titulo(movieDTO.getTitle())
                .director(movieDTO.getDirector())
                .descripcion(movieDTO.getDescripcion())
                .genero(movieDTO.getGenero())
                .releaseYear(movieDTO.getReleaseYear())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<MovieDTO> getAllMovies() {
        log.info("get all movies");
        return repository.findAllDto();
    }



    @Transactional(readOnly = true)
    @Override
    public Page<MovieDTO> getMoviesByGenre(Genero genero, Pageable pageable) {
        log.info("search movie by genre {}", genero);
        return repository.findByGenero(genero, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MovieDTO> getMoviesByTitleAndGenre(String titulo, Genero genero) {
        log.info("search movie by title {} and genre {}", titulo, genero);
        return repository.findByTitleAndGenre(titulo, genero);
    }

    @Transactional(readOnly = true)
    @Override
    public MovieDTO getMovieById(Long id) {
        log.info("search movie by ID {}", id);
        return repository.findByIdDto(id).orElseThrow(() -> new ResourceException("No movie found with that ID " + id));
    }

    @Transactional
    @Override
    public Response<MovieDTO> createMovie(@NonNls @NotNull MovieDTO movie) {
        if (repository.existsByTitulo(movie.getTitle())) {
            throw new ResourceException("Resource already exists");
        }
        log.info("create movie {}", movie);
        Movie movieEntity = mapDTOToMovie(movie);
        var o = repository.persist(movieEntity);
        MovieDTO dto = mapMovieToDTO(o);
        return Response.<MovieDTO>builder()
                .message("Movie created successfully.")
                .code(201)
                .error(false)
                .status(HttpStatus.CREATED)
                .timestamp(now())
                .data(dto)
                .build();
    }

    @Transactional
    @Override
    public Response<MovieDTO> updateMovie(Long id, @NonNls MovieDTO movie) {
        if (repository.existsById(id)) {
            movie.setMovieId(id);
            log.info("update movie {}", movie);
            Movie movieEntity = mapDTOToMovie(movie);
            var o = repository.update(movieEntity);
            MovieDTO dto = mapMovieToDTO(o);
            return Response.<MovieDTO>builder()
                    .message("Movie updated successfully.")
                    .error(false)
                    .timestamp(now())
                    .code(200)
                    .status(HttpStatus.OK)
                    .data(dto)
                    .build();
        }
        throw new ResourceException("No movie found with that ID " + id);
    }

    @Transactional
    @Override
    public void deleteMovie(Long id) {
        log.info("delete movie by ID {}", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceException("No movie found with that ID " + id);

        }

    }

    @Override
    public Page<MovieDTO> findAll(String title, Genero genero, Integer average, Pageable pageable) {
        MovieSpecification specification = new MovieSpecification(title, genero, average);
        Page<Movie>  movies =  repository.findAll(specification, pageable);
        return movies.map(MovieServiceImpl::mapMovieToDTO);

    }



    @Override
    public Page<MovieDTO> findAll(Pageable pageable) {
        return repository.findAllPage(pageable);
    }
}
