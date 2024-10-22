package com.github.angel.raa.dto;

import com.github.angel.raa.utils.Genero;

import java.io.Serial;
import java.io.Serializable;
import java.time.Year;
import java.util.List;

public record MovieRatingDetailsDTO(
        Long movieId,
        String title,
        String director,
        String descripcion,
        Year releaseYear,
        Genero genero,
        List<Ratings> ratings

) implements Serializable {
    @Serial
    private static final long serialVersionUID = -1038162351624152615L;

    public record Ratings(
            Long ratingId,
            String username,
            int clasificacion
    ) {

    }
}

