package com.github.angel.raa.dto;

import com.github.angel.raa.utils.Genero;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Year;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2038162351624152615L;
    @Positive(message = "El ID de la película debe ser un número positivo")
    private Long movieId;
    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 50, min = 5, message = "El título debe tener entre 5 y 50 caracteres.")
    private String title;
    @NotBlank(message = "El nombre del director no puede estar vacío")
    @Size(max = 30, min = 5, message = "El director debe tener entre 5 y 30 caracteres.")
    private String director;
    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, min = 20, message = "La descripción debe tener entre 20 y 400 caracteres.")
    private String descripcion;
    @NotNull(message = "El año de lanzamiento es obligatorio")
    @PastOrPresent(message = "El año de lanzamiento no puede ser en el futuro")
    @Positive(message = "El año de lanzamiento debe ser un número positivo")
    private Year releaseYear;
    private Genero genero;





}