package com.github.angel.raa.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Type;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
@Entity(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "ratings")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -1038162351624152615L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(nullable = false)
    @Pattern(regexp = "^[\\S]+$", message = "El nombre de usuario no debe contener espacios en blanco.")
    @Size(max = 40, min = 5, message = "El nombre de usuario debe tener entre 5 y 40 caracteres.")
    private String username;
    @Column(nullable = false)
    @Size(max = 40, min = 5, message = "El nombre debe tener entre 5 y 40 caracteres.")
    private String name;
    @Column(nullable = false)
    private String password;
    @Type(value = ListArrayType.class, parameters = {
            @org.hibernate.annotations.Parameter(name = ListArrayType.SQL_ARRAY_TYPE, value = "ratings")
    })
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "users", targetEntity = Rating.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @Column(name = "ratings", columnDefinition = "ratings[]")
    private List<Rating> ratings = new ArrayList<>();

}
