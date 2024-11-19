package com.github.angel.raa.persistence.repository;

import com.github.angel.raa.dto.RatingDTO;
import com.github.angel.raa.persistence.entity.Rating;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends  BaseJpaRepository<Rating, Long>, JpaRepository<Rating, Long> {
    Optional<Rating> findByUserIdAndMovieId(Long userId, Long movieId);
    @Query(value = "SELECT new com.github.angel.raa.dto.RatingDTO(r.ratingId, r.movieId, r.userId, r.clasificacion) FROM Rating r WHERE r.movieId = :movieId ",
    countQuery = "SELECT COUNT(r) FROM Rating r WHERE r.movieId = :movieId"
    )
    Page<RatingDTO> findByMovieId(@Param("movieId") Long movieId, Pageable pageable);
    @Query(value = "SELECT new com.github.angel.raa.dto.RatingDTO(r.ratingId, r.movieId, r.userId, r.clasificacion) FROM Rating r WHERE r.users.userId = :userId",
     countQuery = "SELECT COUNT(r) FROM Rating r WHERE r.users.userId = :userId"
    )
    Page<RatingDTO> findByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query(value = "SELECT r FROM Rating r WHERE r.users.username = :username",
    countQuery = "SELECT COUNT(r) FROM Rating r WHERE r.users.username = :username"
    )
    Page<Rating> findByUserUsername(@Param("username") String username, Pageable pageable);
    @Query(value = "SELECT new com.github.angel.raa.dto.RatingDTO(r.ratingId, r.movieId, r.userId, r.clasificacion) FROM Rating r",

    countQuery = "SELECT COUNT(r) FROM Rating r"
    )
    Page<RatingDTO> findAllDto(Pageable pageable);

}
