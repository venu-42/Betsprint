package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateMatchRequest;
import com.projects.betsprint.dto.ContestResponse;
import com.projects.betsprint.dto.MatchResponse;
import com.projects.betsprint.dto.PlayerResponse;
import com.projects.betsprint.model.*;
import com.projects.betsprint.repository.ContestRepository;
import com.projects.betsprint.repository.MatchRepository;
import com.projects.betsprint.repository.PlayerRepository;
import com.projects.betsprint.repository.TeamRepository;
import com.projects.betsprint.repository.TournamentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MatchService {
    private static final Logger log = LoggerFactory.getLogger(MatchService.class);
    private final MatchRepository matchRepository;
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;
    private final ContestRepository contestRepository;
    private final PlayerRepository playerRepository;

    public MatchService(
            MatchRepository matchRepository,
            TournamentRepository tournamentRepository,
            TeamRepository teamRepository,
            ContestRepository contestRepository,
            PlayerRepository playerRepository
    ) {
        this.matchRepository = matchRepository;
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
        this.contestRepository = contestRepository;
        this.playerRepository = playerRepository;
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

    public MatchResponse toResponse(Match match) {
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

    public List<ContestResponse> getContestsByMatchId(Long matchId) {
        if (!matchRepository.existsById(matchId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        }

        return contestRepository.findAllContestsByMatchId(matchId).stream()
                .map(this::toContestResponse)
                .toList();
    }

    public List<PlayerResponse> getPlayersByMatchId(Long matchId) {
        if (!matchRepository.existsById(matchId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        }

        return playerRepository.findAllByMatchId(matchId).stream()
                .map(this::toPlayerResponse)
                .toList();
    }

    private ContestResponse toContestResponse(Contest contest) {
        return new ContestResponse(
                contest.getContestId(),
                contest.getMatch().getMatchId(),
                contest.getName(),
                contest.getPrizePool(),
                contest.getMaxEntries(),
                contest.getEntryFees()
        );
    }

    private PlayerResponse toPlayerResponse(Player player) {
        return new PlayerResponse(
                player.getPlayerId(),
                player.getName(),
                player.getTeam().getTeamId()
        );
    }
}
