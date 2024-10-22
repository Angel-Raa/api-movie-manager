package com.github.angel.raa.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.angel.raa.utils.Genero;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Builder
@Table(name = "movies", uniqueConstraints = @UniqueConstraint(columnNames = {"titulo"}))
@Entity(name = "Movie")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie implements Serializable {
    @Serial
    private static final long serialVersionUID = 1038162351624152615L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;
    @Column(nullable = false)
    @Size(max = 50, min = 5, message = "El título debe tener entre 5 y 50 caracteres.")
    private String titulo;
    @Size(max = 50, min = 5, message = "El director debe tener entre 5 y 50 caracteres.")
    @Column(nullable = false)
    private String director;
    @Lob
    @Column(columnDefinition =  "TEXT")
    @JdbcTypeCode(SqlTypes.LONGNVARCHAR)
    private String descripcion;
    @Column(name = "release_year")
    @NotNull(message = "El año de lanzamiento no puede ser nulo.")
    @PastOrPresent(message = "El año de lanzamiento debe ser en el pasado o presente.")
    private Year releaseYear;
    @Column(name = "genero", nullable = false)
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Genero genero;
    @Type(value = ListArrayType.class, parameters = {
            @org.hibernate.annotations.Parameter(name = ListArrayType.SQL_ARRAY_TYPE, value = "ratings")
    })
    @JsonManagedReference
    @Column(name ="ratings", columnDefinition = "ratings[]")
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Rating.class, fetch = FetchType.EAGER)
    private List<Rating> ratings = new ArrayList<>();

    @Column(name = "create_at", updatable = false)
    @JsonProperty(value = "create_at")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    public Movie(Long movieId, String director, String titulo, String descripcion, Year releaseYear, Genero genero) {
        this.movieId = movieId;
        this.director = director;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.releaseYear = releaseYear;
        this.genero = genero;
    }
}
