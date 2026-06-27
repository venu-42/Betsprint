package com.projects.betsprint.controller;

import com.projects.betsprint.dto.CreateTeamPlayerRequest;
import com.projects.betsprint.dto.TeamPlayerResponse;
import com.projects.betsprint.service.TeamPlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team-players")
public class TeamPlayerController {
    private final TeamPlayerService teamPlayerService;

    public TeamPlayerController(TeamPlayerService teamPlayerService) {
        this.teamPlayerService = teamPlayerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamPlayerResponse create(@RequestBody CreateTeamPlayerRequest request) {
        return teamPlayerService.create(request);
    }
}
