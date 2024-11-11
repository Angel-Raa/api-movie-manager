package com.github.angel.raa.controller;

import com.github.angel.raa.dto.MovieDTO;
import com.github.angel.raa.service.MovieService;
import com.github.angel.raa.utils.Genero;
import com.github.angel.raa.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService service;

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> getAllMovies(@RequestParam(name = "page", defaultValue = "0", required = false)  int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        return ResponseEntity.ok(service.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createAt"))));
    }


    @GetMapping("/genre")
    public ResponseEntity<Page<MovieDTO>> getMoviesByGenre(@RequestParam(name = "genero") Genero genero, Pageable pageable) {

        return ResponseEntity.ok(service.getMoviesByGenre(genero, pageable));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<MovieDTO>> getMoviesByTitleAndGenre(@RequestParam(name = "titulo") String titulo, @RequestParam(name = "genero") Genero genero, @RequestParam(name = "average") Integer average,
                                                                   @RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.findAll(titulo, genero, average, pageable));
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
