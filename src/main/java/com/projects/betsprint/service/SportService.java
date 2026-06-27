package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateSportRequest;
import com.projects.betsprint.dto.SportResponse;
import com.projects.betsprint.dto.TournamentResponse;
import com.projects.betsprint.model.Sport;
import com.projects.betsprint.model.Tournament;
import com.projects.betsprint.repository.SportRepository;
import com.projects.betsprint.repository.TournamentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SportService {
    private final SportRepository sportRepository;
    private final TournamentRepository tournamentRepository;

    public SportService(SportRepository sportRepository, TournamentRepository tournamentRepository) {
        this.sportRepository = sportRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public SportResponse create(CreateSportRequest request) {
        Sport sport = new Sport();
        sport.setName(request.name());
        return toResponse(sportRepository.save(sport));
    }

    public List<SportResponse> getAllSports() {
        return sportRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public List<TournamentResponse> getTournamentsBySportId(Long sportId) {
        if (!sportRepository.existsById(sportId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport not found");
        }

        return tournamentRepository.findAllBySportId(sportId).stream()
                .map(this::toTournamentResponse)
                .toList();
    }

    private SportResponse toResponse(Sport sport) {
        return new SportResponse(sport.getSportId(), sport.getName());
    }

    private TournamentResponse toTournamentResponse(Tournament tournament) {
        return new TournamentResponse(
                tournament.getTournamentId(),
                tournament.getTitle(),
                tournament.getStartDateTime(),
                tournament.getSport().getSportId()
        );
    }
}
