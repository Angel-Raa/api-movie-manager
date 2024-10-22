package com.github.angel.raa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
@Builder
public record UserDTO(
        Long userId,

        @NotBlank(message = "El nombre de usuario no debe estar vacío.")
        @Pattern(regexp = "^[\\S]+$", message = "El nombre de usuario no debe contener espacios en blanco.")
        @Size(max = 20, min = 5, message = "El nombre de usuario debe tener entre 5 y 20 caracteres.")
        String username,

        @NotBlank(message = "El nombre no debe estar vacío.")
        @Size(max = 20, min = 5, message = "El nombre debe tener entre 5 y 20 caracteres.")
        String name
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -1038162351624152615L;

}
