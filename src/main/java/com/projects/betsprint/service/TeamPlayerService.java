package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateTeamPlayerRequest;
import com.projects.betsprint.dto.TeamPlayerResponse;
import com.projects.betsprint.model.Player;
import com.projects.betsprint.model.Team;
import com.projects.betsprint.model.TeamPlayer;
import com.projects.betsprint.model.Tournament;
import com.projects.betsprint.repository.PlayerRepository;
import com.projects.betsprint.repository.TeamPlayerRepository;
import com.projects.betsprint.repository.TeamRepository;
import com.projects.betsprint.repository.TournamentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TeamPlayerService {
    private final TeamPlayerRepository teamPlayerRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final TournamentRepository tournamentRepository;

    public TeamPlayerService(
            TeamPlayerRepository teamPlayerRepository,
            TeamRepository teamRepository,
            PlayerRepository playerRepository,
            TournamentRepository tournamentRepository
    ) {
        this.teamPlayerRepository = teamPlayerRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public TeamPlayerResponse create(CreateTeamPlayerRequest request) {
        Team team = teamRepository.findById(request.teamId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
        Player player = playerRepository.findById(request.playerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));
        Tournament tournament = tournamentRepository.findById(request.tournamentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found"));

        TeamPlayer teamPlayer = new TeamPlayer();
        teamPlayer.setTeam(team);
        teamPlayer.setPlayer(player);
        teamPlayer.setTournament(tournament);
        return toResponse(teamPlayerRepository.save(teamPlayer));
    }

    private TeamPlayerResponse toResponse(TeamPlayer teamPlayer) {
        return new TeamPlayerResponse(
                teamPlayer.getId(),
                teamPlayer.getTeam().getTeamId(),
                teamPlayer.getPlayer().getPlayerId(),
                teamPlayer.getTournament().getTournamentId()
        );
    }
}
