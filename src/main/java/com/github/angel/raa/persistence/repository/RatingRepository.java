package com.github.angel.raa.persistence.repository;

import com.github.angel.raa.dto.RatingDTO;
import com.github.angel.raa.persistence.entity.Rating;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends  BaseJpaRepository<Rating, Long>, JpaRepository<Rating, Long> {
    Optional<Rating> findByUserIdAndMovieId(Long userId, Long movieId);
    List<Rating> findByMovieId(Long movieId);
    List<Rating> findByUserId(Long userId);
    @Query("SELECT r FROM Rating r WHERE r.users.username = :username")
    List<Rating> findByUserUsername(@Param("username") String username);
    @Query(value = "SELECT new com.github.angel.raa.dto.RatingDTO(r.ratingId, r.movieId, r.userId, r.clasificacion) FROM Rating r")
    List<RatingDTO> findAllDto();
}
