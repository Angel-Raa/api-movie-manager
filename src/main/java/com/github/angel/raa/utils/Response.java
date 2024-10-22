package com.github.angel.raa.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A generic container class for HTTP responses, providing details such as the status code, message,
 * data payload, and whether the response represents an error.
 *
 * @param <T> the type of the data payload in the response.
 */

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -2281610816261719181L;
    /**
     * Message providing information about the response.
     */
    private String message;
    /**
     * HTTP status code associated with the response.
     */
    private int code;
    /**
     * HTTP status of the response.
     */
    private HttpStatus status;
    /**
     * Indicates if the response represents an error.
     */
    private boolean error;

    /**
     * Timestamp of when the response was created.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    /**
     * Data payload of the response.
     */
    private T data;

    public boolean isSuccess() {
        return !error && HttpStatus.valueOf(code).is2xxSuccessful();
    }

    public boolean hasData() {
        return data != null;
    }


}
