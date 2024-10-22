package com.github.angel.raa.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "ratings")
@Entity(name = "Rating")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Rating implements Serializable {
    @Serial
    private static final long serialVersionUID = -1038162351624152615L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;
    @Column(name = "fk_movie_id", insertable = true, updatable = true)
    private Long movieId;
    @Column(name = "fk_user_id", insertable = true, updatable = true)
    private Long userId;
    @Check(constraints = "clasificacion BETWEEN 1 AND 5")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private int clasificacion;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "fk_movie_id", referencedColumnName = "movie_id", insertable = false, updatable = false)
    private Movie movie;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User users;
}
