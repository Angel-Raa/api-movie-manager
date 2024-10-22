package com.github.angel.raa.persistence.repository;

import com.github.angel.raa.dto.MovieDTO;
import com.github.angel.raa.persistence.entity.Movie;
import com.github.angel.raa.utils.Genero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    void findByGenero() {
        // Given
        Genero genero = Genero.ACCION;
        Movie movie = new Movie(1L, "titulo", "director", "descripcion", Year.of(2023), genero);
        movieRepository.persist(movie);

        // When
        List<MovieDTO> result = movieRepository.findByGenero(genero);

        // Then
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(genero, result.get(0).getGenero());
    }

    @Test
    void findByTitleAndGenre() {
    }

    @Test
    void findAllDto() {
    }

    @Test
    void findByIdDto() {
    }

    @Test
    void existsByTitulo() {
    }
}