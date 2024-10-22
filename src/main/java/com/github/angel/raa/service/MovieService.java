package com.github.angel.raa.service;

import com.github.angel.raa.dto.MovieDTO;
import com.github.angel.raa.utils.Genero;
import com.github.angel.raa.utils.Response;

import java.util.List;

/**
 * Interface for movie service
 */
public interface MovieService {
    /**
     * Get all movies
     * @return List<MovieDTO>
     */
    List<MovieDTO> getAllMovies();

    /**
     * Get movies by genre
     * @param genero
     * @return List<MovieDTO>
     */
    List<MovieDTO> getMoviesByGenre(Genero genero);

    /**
     * Get movies by title and genre
     * @param titulo
     * @param genero
     * @return List<MovieDTO>
     */
    List<MovieDTO> getMoviesByTitleAndGenre(String titulo, Genero genero);

    /**
     * Get movie by id
     * @param id
     * @return MovieDTO
     */
    MovieDTO getMovieById(Long id);

    /**
     * Create a movie
     * @param movie
     * @return Response<MovieDTO>
     */
    Response<MovieDTO> createMovie(MovieDTO movie);
    /**
     * Update a movie
     * @param id
     * @param movie
     * @return Response<MovieDTO>
     */
    Response<MovieDTO> updateMovie(Long id, MovieDTO movie);

    /**
     * Delete a movie by id
     * @param id
     */
    void deleteMovie(Long id);
    /**
     * Find all movies
     * @param title
     * @param genero
     * @return List<MovieDTO>
     */
    List<MovieDTO> findAll(String title, Genero genero);

   

}
