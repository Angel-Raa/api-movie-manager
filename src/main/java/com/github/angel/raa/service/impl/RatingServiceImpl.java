package com.github.angel.raa.service.impl;

import com.github.angel.raa.dto.RatingDTO;
import com.github.angel.raa.dto.SaveRating;
import com.github.angel.raa.persistence.entity.Rating;
import com.github.angel.raa.persistence.repository.RatingRepository;
import com.github.angel.raa.service.RatingService;
import com.github.angel.raa.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository repository;

    @Transactional(readOnly = true)
    @Override
    public Page<RatingDTO> findAll(Pageable pageable) {
        return repository.findAllDto(pageable);
    }
    @Transactional(readOnly = true)
    @Override
    public Page<RatingDTO> findByMovieId(Long movieId, Pageable pageable) {
        return repository.findByMovieId(movieId, pageable);
    }
    @Transactional(readOnly = true)
    @Override
    public Page<RatingDTO> findByUserId(Long userId, Pageable pageable) {
        return repository.findByUserId(userId, pageable);
    }
    @Transactional(readOnly = true)
    @Override
    public Page<Rating> findByUserUsername(String username, Pageable pageable) {
        return repository.findByUserUsername(username, pageable);
    }
    @Transactional
    @Override
    public void deleteById(Long id) {

    }

    @Transactional
    @Override
    public Response<String> create(SaveRating rating) {
        return null;
    }
}
