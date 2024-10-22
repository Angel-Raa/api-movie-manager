package com.github.angel.raa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record UserRatingDetailsDTO(
        Long userid,
        String username,
        String name,
        @JsonProperty(value = "ratings")
        List<RatingDTO> ratings
)implements Serializable {
        @Serial
    private static final long serialVersionUID = -1028162351624152615L;
}
