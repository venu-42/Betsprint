package com.projects.betsprint.service;

import com.projects.betsprint.dto.ContestResponse;
import com.projects.betsprint.dto.CreateContestRequest;
import com.projects.betsprint.model.Contest;
import com.projects.betsprint.model.Match;
import com.projects.betsprint.repository.ContestRepository;
import com.projects.betsprint.repository.MatchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContestService {
    private final ContestRepository contestRepository;
    private final MatchRepository matchRepository;

    public ContestService(ContestRepository contestRepository, MatchRepository matchRepository) {
        this.contestRepository = contestRepository;
        this.matchRepository = matchRepository;
    }

    public ContestResponse create(CreateContestRequest request) {
        Match match = matchRepository.findById(request.matchId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));

        Contest contest = new Contest();
        contest.setMatch(match);
        contest.setName(request.name());
        contest.setPrizePool(request.prizePool());
        contest.setMaxEntries(request.maxEntries());
        contest.setEntryFees(request.entryFees());
        return toResponse(contestRepository.save(contest));
    }

    private ContestResponse toResponse(Contest contest) {
        return new ContestResponse(
                contest.getContestId(),
                contest.getMatch().getMatchId(),
                contest.getName(),
                contest.getPrizePool(),
                contest.getMaxEntries(),
                contest.getEntryFees()
        );
    }
}
