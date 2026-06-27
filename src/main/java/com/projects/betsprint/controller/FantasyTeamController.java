package com.projects.betsprint.controller;

import com.projects.betsprint.dto.CreateFantasyTeamRequest;
import com.projects.betsprint.dto.FantasyTeamResponse;
import com.projects.betsprint.service.FantasyTeamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fantasy-teams")
public class FantasyTeamController {
    private final FantasyTeamService fantasyTeamService;

    public FantasyTeamController(FantasyTeamService fantasyTeamService) {
        this.fantasyTeamService = fantasyTeamService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FantasyTeamResponse create(@RequestBody CreateFantasyTeamRequest request) {
        return fantasyTeamService.create(request);
    }
}
