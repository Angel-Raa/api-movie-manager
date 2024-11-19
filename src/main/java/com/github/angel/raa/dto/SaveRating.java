package com.github.angel.raa.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveRating(
        @Positive(message = "El ID de la película debe ser un número positivo.")
        Long movieId,
        @NotBlank(message = "El nombre no debe estar vacío.")
        @Size(max = 20, min = 5, message = "El nombre debe tener entre 5 y 20 caracteres.")
        String username,
        @Positive(message = "La calificación debe ser un número positivo.")
                @Min(value = 1, message = "La calificación debe ser al menos 1.")
                @Min(value = 5, message = "La calificación debe ser como máximo 5.")
        Integer rating
)implements Serializable {
}
