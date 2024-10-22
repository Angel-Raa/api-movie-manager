package com.github.angel.raa.excpetion;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class InvalidPasswordException extends RuntimeException {
    private final String password;
    private final String passwordRepeated;
    private final String description;

    public InvalidPasswordException(String password, String passwordRepeated, String description) {
        this.password = password;
        this.passwordRepeated = passwordRepeated;
        this.description = description;
    }

    @Override
    public String getMessage() {
        return "Invalid Password" + this.description;
    }

}
