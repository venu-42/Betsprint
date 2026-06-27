package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateTeamRequest;
import com.projects.betsprint.dto.TeamResponse;
import com.projects.betsprint.model.Team;
import com.projects.betsprint.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamResponse create(CreateTeamRequest request) {
        Team team = new Team();
        team.setName(request.name());
        return toResponse(teamRepository.save(team));
    }

    private TeamResponse toResponse(Team team) {
        return new TeamResponse(team.getTeamId(), team.getName());
    }
}
