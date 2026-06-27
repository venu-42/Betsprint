package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateUserRequest;
import com.projects.betsprint.dto.UserResponse;
import com.projects.betsprint.model.FantasyTeam;
import com.projects.betsprint.model.User;
import com.projects.betsprint.repository.FantasyTeamRepository;
import com.projects.betsprint.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FantasyTeamRepository fantasyTeamRepository;

    public UserService(UserRepository userRepository, FantasyTeamRepository fantasyTeamRepository) {
        this.userRepository = userRepository;
        this.fantasyTeamRepository = fantasyTeamRepository;
    }

    public UserResponse create(CreateUserRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setWalletBalance(request.walletBalance());
        return toResponse(userRepository.save(user));
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getUserId(), user.getName(), user.getEmail(), user.getWalletBalance());
    }

    public List<FantasyTeam> getFantasyTeamsByMatchId(UUID userId, Long matchId) {
        return fantasyTeamRepository.findAllByUserIdAndMatchId(userId,matchId);
    }
}
