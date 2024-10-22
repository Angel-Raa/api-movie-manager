package com.github.angel.raa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;


public record ChangeUsernameDTO(
        @NotBlank(message = "Username cannot be blank")

        @JsonProperty(value = "username")
        String username
)
        implements Serializable {
    @Serial
    private static final long serialVersionUID = -2281610816261719181L;
}
