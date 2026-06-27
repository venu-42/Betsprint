package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreatePlayerRequest;
import com.projects.betsprint.dto.PlayerResponse;
import com.projects.betsprint.model.Player;
import com.projects.betsprint.model.Team;
import com.projects.betsprint.repository.PlayerRepository;
import com.projects.betsprint.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public PlayerResponse create(CreatePlayerRequest request) {
        Team team = teamRepository.findById(request.teamId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));

        Player player = new Player();
        player.setName(request.name());
        player.setTeam(team);
        return toResponse(playerRepository.save(player));
    }

    private PlayerResponse toResponse(Player player) {
        return new PlayerResponse(player.getPlayerId(), player.getName(), player.getTeam().getTeamId());
    }
}
