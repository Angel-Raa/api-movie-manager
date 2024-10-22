package com.github.angel.raa.service.impl;

import com.github.angel.raa.dto.MovieDTO;
import com.github.angel.raa.excpetion.ResourceException;
import com.github.angel.raa.persistence.entity.Movie;
import com.github.angel.raa.persistence.repository.MovieRepository;
import com.github.angel.raa.utils.Genero;
import com.github.angel.raa.utils.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceImplTest {
    @Mock
    private MovieRepository repository;

    @InjectMocks
    private MovieServiceImpl movieService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @DisplayName("Test getAllMovies")
    @Test
    void getAllMovies() {
        // Given
        List<MovieDTO> movieDTOs = new ArrayList<>();
        movieDTOs.add(MovieDTO.builder().movieId(1L).title("Movie 1").genero(Genero.ACCION).build());
        movieDTOs.add(MovieDTO.builder().movieId(2L).title("Movie 2").genero(Genero.COMEDIA).build());
        when(repository.findAllDto()).thenReturn(movieDTOs);

        // When
        List<MovieDTO> result = movieService.getAllMovies();

        // Then
        assertEquals(2, result.size());
        verify(repository, times(1)).findAllDto();
    }
    @DisplayName("Test getMoviesByGenre")
    @Test
    void getMoviesByGenre() {
        // Given
        List<MovieDTO> movieDTOs = new ArrayList<>();
        movieDTOs.add(MovieDTO.builder().movieId(1L).title("Movie 1").genero(Genero.ACCION).build());
        when(repository.findByGenero(Genero.ACCION)).thenReturn(movieDTOs);

        // When
        List<MovieDTO> result = movieService.getMoviesByGenre(Genero.ACCION);

        // Then
        assertEquals(1, result.size());
        verify(repository, times(1)).findByGenero(Genero.ACCION);
    }
    @DisplayName( "Test getMoviesByTitleAndGenre")
    @Test
    void getMoviesByTitleAndGenre() {
        // Given
        List<MovieDTO> movieDTOs = new ArrayList<>();
        movieDTOs.add(MovieDTO.builder().movieId(1L).title("Movie 1").genero(Genero.ACCION).build());
        when(repository.findByTitleAndGenre("Movie 1", Genero.ACCION)).thenReturn(movieDTOs);

        // When
        List<MovieDTO> result = movieService.getMoviesByTitleAndGenre("Movie 1", Genero.ACCION);

        // Then
        assertEquals(1, result.size());
        verify(repository, times(1)).findByTitleAndGenre("Movie 1", Genero.ACCION);
    }
    @DisplayName( "Test getMovieById")
    @Test
    void getMovieById() {
        // Given
        MovieDTO movieDTO = MovieDTO.builder().movieId(1L).title("Movie 1").genero(Genero.ACCION).build();
        when(repository.findByIdDto(1L)).thenReturn(Optional.of(movieDTO));

        // When
        MovieDTO result = movieService.getMovieById(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getMovieId());
        verify(repository, times(1)).findByIdDto(1L);
    }

    @DisplayName( "Test createMovie")
    @Test
    void createMovie() {
        // Given
        MovieDTO movieDTO = MovieDTO.builder().title("Movie 1").genero(Genero.ACCION).build();
        Movie movieEntity = Movie.builder().movieId(1L).titulo("Movie 1").genero(Genero.ACCION).build();
        when(repository.existsByTitulo("Movie 1")).thenReturn(false);
        when(repository.persist(any(Movie.class))).thenReturn(movieEntity);

        // When
        Response<MovieDTO> response = movieService.createMovie(movieDTO);

        // Then
        assertNotNull(response);
        assertEquals("Movie created successfully.", response.getMessage());
        assertEquals(201, response.getCode());
        assertFalse(response.isError());
        assertEquals(HttpStatus.CREATED, response.getStatus());
        assertNotNull(response.getTimestamp());
        assertEquals(1L, response.getData().getMovieId());
        verify(repository, times(1)).existsByTitulo("Movie 1");
        verify(repository, times(1)).persist(any(Movie.class));
    }
    @DisplayName( "Test updateMovie")
    @Test
    void updateMovie() {
        // Given
        MovieDTO movieDTO = MovieDTO.builder().title("Movie 1").genero(Genero.ACCION).build();
        Movie movieEntity = Movie.builder().movieId(1L).titulo("Movie 1").genero(Genero.ACCION).build();
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.update(any(Movie.class))).thenReturn(movieEntity);

        // When
        Response<MovieDTO> response = movieService.updateMovie(1L, movieDTO);

        // Then
        assertNotNull(response);
        assertEquals("Movie updated successfully.", response.getMessage());
        assertFalse(response.isError());
        assertNotNull(response.getTimestamp());
        assertEquals(200, response.getCode());
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(1L, response.getData().getMovieId());
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).update(any(Movie.class));
    }
    @DisplayName( "Test deleteMovie")
    @Test
    void deleteMovie() {
        // Given
        when(repository.existsById(1L)).thenReturn(true);

        // When
        movieService.deleteMovie(1L);

        // Then
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @DisplayName( "Test getMovieByIdNotFound")
    @Test
    void getMovieByIdNotFound() {
        // Given
        when(repository.findByIdDto(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ResourceException.class, () -> movieService.getMovieById(1L));
        verify(repository, times(1)).findByIdDto(1L);
    }

    @DisplayName( "Test createMovieAlreadyExists")
    @Test
    void createMovieAlreadyExists() {
        // Given
        MovieDTO movieDTO = MovieDTO.builder().title("Movie 1").genero(Genero.ACCION).build();
        when(repository.existsByTitulo("Movie 1")).thenReturn(true);

        // When & Then
        assertThrows(ResourceException.class, () -> movieService.createMovie(movieDTO));
        verify(repository, times(1)).existsByTitulo("Movie 1");
        verify(repository, never()).persist(any(Movie.class));
    }

    @DisplayName( "Test updateMovieNotFound")
    @Test
    void updateMovieNotFound() {
        // Given
        MovieDTO movieDTO = MovieDTO.builder().title("Movie 1").genero(Genero.ACCION).build();
        when(repository.existsById(1L)).thenReturn(false);

        // When & Then
        assertThrows(ResourceException.class, () -> movieService.updateMovie(1L, movieDTO));
        verify(repository, times(1)).existsById(1L);
        verify(repository, never()).update(any(Movie.class));
    }

    @DisplayName("Test deleteMovieNotFound")
    @Test
    void deleteMovieNotFound() {
        // Given
        when(repository.existsById(1L)).thenReturn(false);

        // When & Then
        assertThrows(ResourceException.class, () -> movieService.deleteMovie(1L));
        verify(repository, times(1)).existsById(1L);
        verify(repository, never()).deleteById(anyLong());
    }

}