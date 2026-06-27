package com.projects.betsprint.service;

import com.projects.betsprint.dto.ContestEntryResponse;
import com.projects.betsprint.dto.CreateContestEntryRequest;
import com.projects.betsprint.model.Contest;
import com.projects.betsprint.model.ContestEntry;
import com.projects.betsprint.model.FantasyTeam;
import com.projects.betsprint.model.User;
import com.projects.betsprint.repository.ContestEntryRepository;
import com.projects.betsprint.repository.ContestRepository;
import com.projects.betsprint.repository.FantasyTeamRepository;
import com.projects.betsprint.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContestEntryService {
    private final ContestEntryRepository contestEntryRepository;
    private final ContestRepository contestRepository;
    private final FantasyTeamRepository fantasyTeamRepository;
    private final UserRepository userRepository;

    public ContestEntryService(
            ContestEntryRepository contestEntryRepository,
            ContestRepository contestRepository,
            FantasyTeamRepository fantasyTeamRepository,
            UserRepository userRepository
    ) {
        this.contestEntryRepository = contestEntryRepository;
        this.contestRepository = contestRepository;
        this.fantasyTeamRepository = fantasyTeamRepository;
        this.userRepository = userRepository;
    }

    public ContestEntryResponse create(CreateContestEntryRequest request) {
        Contest contest = contestRepository.findById(request.contestId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contest not found"));
        FantasyTeam fantasyTeam = fantasyTeamRepository.findById(request.fantasyTeamId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fantasy team not found"));
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        ContestEntry contestEntry = new ContestEntry();
        contestEntry.setContest(contest);
        contestEntry.setFantasyTeam(fantasyTeam);
        contestEntry.setUser(user);
        contestEntry.setFinalScore(request.finalScore());
        contestEntry.setRank(request.rank());
        contestEntry.setWinnings(request.winnings());
        contestEntry.setJoinedAt(request.joinedAt());
        return toResponse(contestEntryRepository.save(contestEntry));
    }

    private ContestEntryResponse toResponse(ContestEntry contestEntry) {
        return new ContestEntryResponse(
                contestEntry.getId(),
                contestEntry.getContest().getContestId(),
                contestEntry.getFantasyTeam().getFantasyTeamId(),
                contestEntry.getUser().getUserId(),
                contestEntry.getFinalScore(),
                contestEntry.getRank(),
                contestEntry.getWinnings(),
                contestEntry.getJoinedAt()
        );
    }
}
