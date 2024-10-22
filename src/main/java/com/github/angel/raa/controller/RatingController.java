package com.github.angel.raa.controller;

import com.github.angel.raa.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService service;

}
