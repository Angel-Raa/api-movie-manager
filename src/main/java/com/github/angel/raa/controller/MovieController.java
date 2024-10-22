package com.github.angel.raa.controller;

import com.github.angel.raa.dto.MovieDTO;
import com.github.angel.raa.service.MovieService;
import com.github.angel.raa.utils.Genero;
import com.github.angel.raa.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService service;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        return ResponseEntity.ok(service.getAllMovies());
    }


    @GetMapping("/genre")
    public ResponseEntity<List<MovieDTO>> getMoviesByGenre(@RequestParam(name = "genero") Genero genero) {
        return ResponseEntity.ok(service.getMoviesByGenre(genero));
    }
    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> getMoviesByTitleAndGenre(@RequestParam(name = "titulo") String titulo, @RequestParam(name = "genero") Genero genero) {
        return ResponseEntity.ok(service.findAll(titulo, genero));
    }

    @PostMapping
    public ResponseEntity<Response<MovieDTO>> createMovie(@RequestBody MovieDTO movie) {
        var dto = service.createMovie(movie);
        URI uri = URI.create("/movies/" );
        return ResponseEntity.created(uri)
                .body(dto);
    }

    @PutMapping("{movieId}")
    public ResponseEntity<Response<MovieDTO>> updateMovie(@PathVariable Long movieId, @RequestBody MovieDTO movie) {
        var dto = service.updateMovie(movieId, movie);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long movieId) {
        service.deleteMovie(movieId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
