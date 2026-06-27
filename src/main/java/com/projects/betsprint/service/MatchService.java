package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateMatchRequest;
import com.projects.betsprint.dto.MatchResponse;
import com.projects.betsprint.model.*;
import com.projects.betsprint.repository.MatchRepository;
import com.projects.betsprint.repository.TeamRepository;
import com.projects.betsprint.repository.TournamentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {
    private static final Logger log = LoggerFactory.getLogger(MatchService.class);
    private final MatchRepository matchRepository;
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;

    public MatchService(
            MatchRepository matchRepository,
            TournamentRepository tournamentRepository,
            TeamRepository teamRepository
    ) {
        this.matchRepository = matchRepository;
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
    }

    public MatchResponse create(CreateMatchRequest request) {
        Tournament tournament = tournamentRepository.findById(request.tournamentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found"));
        Team homeTeam = teamRepository.findById(request.homeTeamId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Home team not found"));
        Team awayTeam = teamRepository.findById(request.awayTeamId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Away team not found"));

        Match match = new Match();
        match.setTournament(tournament);
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        return toResponse(matchRepository.save(match));
    }

    private MatchResponse toResponse(Match match) {
        return new MatchResponse(
                match.getMatchId(),
                match.getTournament().getTournamentId(),
                match.getHomeTeam().getTeamId(),
                match.getAwayTeam().getTeamId()
        );
    }

    public List<MatchResponse> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public MatchResponse getMatch(Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));
        return toResponse(match);
    }

    public List<Contest> getContestsByMatchId(Long matchId) {
        return matchRepository.findById(matchId)
                .map(Match::getContestList)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Match not found"));
    }

    public List<Player> getPlayersByMatchId(Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Match not found"));
        List<Player> list = new ArrayList<>(match.getAwayTeam().getPlayerList());
        list.addAll(match.getHomeTeam().getPlayerList());
        return list;
    }
}
