package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateSportRequest;
import com.projects.betsprint.dto.SportResponse;
import com.projects.betsprint.model.Sport;
import com.projects.betsprint.repository.SportRepository;
import org.springframework.stereotype.Service;

@Service
public class SportService {
    private final SportRepository sportRepository;

    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public SportResponse create(CreateSportRequest request) {
        Sport sport = new Sport();
        sport.setName(request.name());
        return toResponse(sportRepository.save(sport));
    }

    private SportResponse toResponse(Sport sport) {
        return new SportResponse(sport.getSportId(), sport.getName());
    }
}
