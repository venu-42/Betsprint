package com.projects.betsprint.controller;

import com.projects.betsprint.dto.CreateSportRequest;
import com.projects.betsprint.dto.SportResponse;
import com.projects.betsprint.service.SportService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sports")
public class SportController {
    private final SportService sportService;

    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SportResponse create(@RequestBody CreateSportRequest request) {
        return sportService.create(request);
    }
}
