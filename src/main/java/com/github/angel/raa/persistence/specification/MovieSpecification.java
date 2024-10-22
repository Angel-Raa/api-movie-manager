package com.github.angel.raa.persistence.specification;

import com.github.angel.raa.persistence.entity.Movie;
import com.github.angel.raa.utils.Genero;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MovieSpecification implements Specification<Movie> {
    private String title;
    private Genero genero;
    @Override
    public Predicate toPredicate(@NotNull Root<Movie> root, CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
        // root = From Movie
        // query = Select * From Movie
        // criteriaBuilder = Where
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.hasText(title)){
            Predicate titleLike = criteriaBuilder.like(root.get("titulo"),"%" + this.title + "%");
            predicates.add(titleLike);
        }
        if(genero != null){
            Predicate generoEqual = criteriaBuilder.equal(root.get("genero"), this.genero);
            predicates.add(generoEqual);
        }


        return predicates.isEmpty() ? null : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
