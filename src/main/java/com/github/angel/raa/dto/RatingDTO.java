package com.github.angel.raa.dto;

import java.io.Serial;
import java.io.Serializable;

public record RatingDTO(
        Long ratingId,
        Long movieId,
        Long userId,
        int clasificacion

) implements Serializable {
    @Serial
    private static final long serialVersionUID = -1038162351624152615L;
}
