package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateFantasyTeamRequest;
import com.projects.betsprint.dto.FantasyTeamResponse;
import com.projects.betsprint.model.FantasyTeam;
import com.projects.betsprint.model.Match;
import com.projects.betsprint.model.Player;
import com.projects.betsprint.model.User;
import com.projects.betsprint.repository.FantasyTeamRepository;
import com.projects.betsprint.repository.MatchRepository;
import com.projects.betsprint.repository.PlayerRepository;
import com.projects.betsprint.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FantasyTeamService {
    private final FantasyTeamRepository fantasyTeamRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    public FantasyTeamService(
            FantasyTeamRepository fantasyTeamRepository,
            UserRepository userRepository,
            MatchRepository matchRepository,
            PlayerRepository playerRepository
    ) {
        this.fantasyTeamRepository = fantasyTeamRepository;
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }

    public FantasyTeamResponse create(CreateFantasyTeamRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Match match = matchRepository.findById(request.matchId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));
        Player captain = playerRepository.findById(request.captainId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Captain not found"));
        Player viceCaptain = playerRepository.findById(request.viceCaptainId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vice captain not found"));

        FantasyTeam fantasyTeam = new FantasyTeam();
        fantasyTeam.setUser(user);
        fantasyTeam.setMatch(match);
        fantasyTeam.setCaptain(captain);
        fantasyTeam.setViceCaptain(viceCaptain);
        return toResponse(fantasyTeamRepository.save(fantasyTeam));
    }

    private FantasyTeamResponse toResponse(FantasyTeam fantasyTeam) {
        return new FantasyTeamResponse(
                fantasyTeam.getFantasyTeamId(),
                fantasyTeam.getUser().getUserId(),
                fantasyTeam.getMatch().getMatchId(),
                fantasyTeam.getCaptain().getPlayerId(),
                fantasyTeam.getViceCaptain().getPlayerId()
        );
    }
}
