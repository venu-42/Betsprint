package com.projects.betsprint.controller;

import com.projects.betsprint.dto.ContestEntryResponse;
import com.projects.betsprint.dto.CreateContestEntryRequest;
import com.projects.betsprint.service.ContestEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contest-entries")
public class ContestEntryController {
    private final ContestEntryService contestEntryService;

    public ContestEntryController(ContestEntryService contestEntryService) {
        this.contestEntryService = contestEntryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContestEntryResponse create(@RequestBody CreateContestEntryRequest request) {
        return contestEntryService.create(request);
    }
}
