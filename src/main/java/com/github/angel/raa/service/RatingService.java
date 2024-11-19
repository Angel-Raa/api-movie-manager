package com.github.angel.raa.service;


import com.github.angel.raa.dto.RatingDTO;
import com.github.angel.raa.dto.SaveRating;
import com.github.angel.raa.persistence.entity.Rating;
import com.github.angel.raa.utils.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RatingService {
    Page<RatingDTO > findAll(Pageable pageable);

    Page<RatingDTO> findByMovieId(Long movieId, Pageable pageable);
    Page<RatingDTO> findByUserId(Long userId, Pageable pageable);
    Page<Rating> findByUserUsername (String username, Pageable pageable);
    void deleteById(Long id);
    Response<String> create(SaveRating rating);

}
