package com.github.angel.raa.persistence.repository;

import com.github.angel.raa.dto.MovieDTO;
import com.github.angel.raa.persistence.entity.Movie;
import com.github.angel.raa.utils.Genero;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends ListPagingAndSortingRepository<Movie, Long>, BaseJpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
    @Query("SELECT NEW com.github.angel.raa.dto.MovieDTO(m.movieId, m.titulo, m.director, m.descripcion, m.releaseYear, m.genero) FROM Movie m WHERE m.genero = :genero")
    List<MovieDTO> findByGenero(@Param("genero") Genero genero);

    @Query("SELECT NEW com.github.angel.raa.dto.MovieDTO(m.movieId, m.titulo, m.director, m.descripcion, m.releaseYear, m.genero) FROM Movie m WHERE LOWER(m.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')) AND m.genero = :genero")
    List<MovieDTO> findByTitleAndGenre(@Param("titulo") String titulo, @Param("genero") Genero genero);

    @Query("SELECT NEW com.github.angel.raa.dto.MovieDTO(m.movieId, m.titulo, m.director, m.descripcion, m.releaseYear, m.genero) FROM Movie m")
    List<MovieDTO> findAllDto();

    @Query("SELECT NEW com.github.angel.raa.dto.MovieDTO(m.movieId, m.titulo, m.director, m.descripcion, m.releaseYear, m.genero) FROM Movie m WHERE m.movieId = :id")
    Optional<MovieDTO> findByIdDto(@Param("id") Long id);

    boolean existsByTitulo(String titulo);


}
