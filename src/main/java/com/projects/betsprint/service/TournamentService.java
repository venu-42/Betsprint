package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateTournamentRequest;
import com.projects.betsprint.dto.TournamentResponse;
import com.projects.betsprint.model.Sport;
import com.projects.betsprint.model.Tournament;
import com.projects.betsprint.repository.SportRepository;
import com.projects.betsprint.repository.TournamentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final SportRepository sportRepository;

    public TournamentService(TournamentRepository tournamentRepository, SportRepository sportRepository) {
        this.tournamentRepository = tournamentRepository;
        this.sportRepository = sportRepository;
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

    private TournamentResponse toResponse(Tournament tournament) {
        return new TournamentResponse(
                tournament.getTournamentId(),
                tournament.getTitle(),
                tournament.getStartDateTime(),
                tournament.getSport().getSportId()
        );
    }
}
