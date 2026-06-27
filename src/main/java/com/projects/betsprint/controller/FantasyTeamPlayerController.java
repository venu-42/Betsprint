package com.projects.betsprint.controller;

import com.projects.betsprint.dto.CreateFantasyTeamPlayerRequest;
import com.projects.betsprint.dto.FantasyTeamPlayerResponse;
import com.projects.betsprint.service.FantasyTeamPlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fantasy-team-players")
public class FantasyTeamPlayerController {
    private final FantasyTeamPlayerService fantasyTeamPlayerService;

    public FantasyTeamPlayerController(FantasyTeamPlayerService fantasyTeamPlayerService) {
        this.fantasyTeamPlayerService = fantasyTeamPlayerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FantasyTeamPlayerResponse create(@RequestBody CreateFantasyTeamPlayerRequest request) {
        return fantasyTeamPlayerService.create(request);
    }
}
