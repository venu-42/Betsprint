package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateTournamentRequest;
import com.projects.betsprint.dto.MatchResponse;
import com.projects.betsprint.dto.TournamentResponse;
import com.projects.betsprint.model.Match;
import com.projects.betsprint.model.Sport;
import com.projects.betsprint.model.Tournament;
import com.projects.betsprint.repository.MatchRepository;
import com.projects.betsprint.repository.SportRepository;
import com.projects.betsprint.repository.TournamentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final SportRepository sportRepository;
    private final MatchRepository matchRepository;

    public TournamentService(
            TournamentRepository tournamentRepository,
            SportRepository sportRepository,
            MatchRepository matchRepository
    ) {
        this.tournamentRepository = tournamentRepository;
        this.sportRepository = sportRepository;
        this.matchRepository = matchRepository;
    }

    public TournamentResponse create(CreateTournamentRequest request) {
        Sport sport = sportRepository.findById(request.sportId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport not found"));

        Tournament tournament = new Tournament();
        tournament.setTitle(request.title());
        tournament.setStartDateTime(request.startDateTime());
        tournament.setSport(sport);
        return toResponse(tournamentRepository.save(tournament));
    }

    public TournamentResponse getTournament(UUID tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .map(this::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found"));
    }

    public List<MatchResponse> getMatchesByTournamentId(UUID tournamentId) {
        if (!tournamentRepository.existsById(tournamentId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found");
        }

        return matchRepository.findAllByTournamentId(tournamentId).stream()
                .map(this::toMatchResponse)
                .toList();
    }

    private TournamentResponse toResponse(Tournament tournament) {
        return new TournamentResponse(
                tournament.getTournamentId(),
                tournament.getTitle(),
                tournament.getStartDateTime(),
                tournament.getSport().getSportId()
        );
    }

    private MatchResponse toMatchResponse(Match match) {
        return new MatchResponse(
                match.getMatchId(),
                match.getTournament().getTournamentId(),
                match.getHomeTeam().getTeamId(),
                match.getAwayTeam().getTeamId()
        );
    }
}
