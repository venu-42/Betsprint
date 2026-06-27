package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateFantasyTeamPlayerRequest;
import com.projects.betsprint.dto.FantasyTeamPlayerResponse;
import com.projects.betsprint.model.FantasyTeam;
import com.projects.betsprint.model.FantasyTeamPlayer;
import com.projects.betsprint.model.Player;
import com.projects.betsprint.repository.FantasyTeamPlayerRepository;
import com.projects.betsprint.repository.FantasyTeamRepository;
import com.projects.betsprint.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FantasyTeamPlayerService {
    private final FantasyTeamPlayerRepository fantasyTeamPlayerRepository;
    private final FantasyTeamRepository fantasyTeamRepository;
    private final PlayerRepository playerRepository;

    public FantasyTeamPlayerService(
            FantasyTeamPlayerRepository fantasyTeamPlayerRepository,
            FantasyTeamRepository fantasyTeamRepository,
            PlayerRepository playerRepository
    ) {
        this.fantasyTeamPlayerRepository = fantasyTeamPlayerRepository;
        this.fantasyTeamRepository = fantasyTeamRepository;
        this.playerRepository = playerRepository;
    }

    public FantasyTeamPlayerResponse create(CreateFantasyTeamPlayerRequest request) {
        FantasyTeam fantasyTeam = fantasyTeamRepository.findById(request.fantasyTeamId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fantasy team not found"));
        Player player = playerRepository.findById(request.playerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));

        FantasyTeamPlayer fantasyTeamPlayer = new FantasyTeamPlayer();
        fantasyTeamPlayer.setFantasyTeam(fantasyTeam);
        fantasyTeamPlayer.setPlayer(player);
        return toResponse(fantasyTeamPlayerRepository.save(fantasyTeamPlayer));
    }

    private FantasyTeamPlayerResponse toResponse(FantasyTeamPlayer fantasyTeamPlayer) {
        return new FantasyTeamPlayerResponse(
                fantasyTeamPlayer.getFantasyTeamPlayerId(),
                fantasyTeamPlayer.getFantasyTeam().getFantasyTeamId(),
                fantasyTeamPlayer.getPlayer().getPlayerId()
        );
    }
}
