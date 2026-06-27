package com.projects.betsprint.controller;

import com.projects.betsprint.dto.CreateTournamentRequest;
import com.projects.betsprint.dto.MatchResponse;
import com.projects.betsprint.dto.TournamentResponse;
import com.projects.betsprint.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {
    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TournamentResponse create(@RequestBody CreateTournamentRequest request) {
        return tournamentService.create(request);
    }

    @GetMapping("/{tournamentId}")
    public TournamentResponse getTournament(@PathVariable UUID tournamentId) {
        return tournamentService.getTournament(tournamentId);
    }

    @GetMapping("/{tournamentId}/matches")
    public List<MatchResponse> getMatchesByTournamentId(@PathVariable UUID tournamentId) {
        return tournamentService.getMatchesByTournamentId(tournamentId);
    }
}
