package com.projects.betsprint.controller;

import com.projects.betsprint.dto.ContestResponse;
import com.projects.betsprint.dto.CreateContestRequest;
import com.projects.betsprint.service.ContestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contests")
public class ContestController {
    private final ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContestResponse create(@RequestBody CreateContestRequest request) {
        return contestService.create(request);
    }
}
