package com.projects.betsprint.controller;

import com.projects.betsprint.dto.CreateMatchRequest;
import com.projects.betsprint.dto.ContestResponse;
import com.projects.betsprint.dto.MatchResponse;
import com.projects.betsprint.dto.PlayerResponse;
import com.projects.betsprint.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatchResponse create(@RequestBody CreateMatchRequest request) {
        return matchService.create(request);
    }

    @GetMapping
    public List<MatchResponse> getAllMatches(){
        return matchService.getAllMatches();
    }

    @GetMapping("/{matchId}")
    public MatchResponse getMatch(@PathVariable("matchId") Long matchId){
        return matchService.getMatch(matchId);
    }

    @GetMapping("/{matchId}/contests")
    public List<ContestResponse> getContestsByMatchId(@PathVariable("matchId") Long matchId){
        return matchService.getContestsByMatchId(matchId);
    }

    @GetMapping("/{matchId}/players")
    public List<PlayerResponse> getPlayersByMatchId(@PathVariable("matchId") Long matchId){
        return matchService.getPlayersByMatchId(matchId);
    }

}
