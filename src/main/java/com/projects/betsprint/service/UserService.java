package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateUserRequest;
import com.projects.betsprint.dto.UserResponse;
import com.projects.betsprint.model.User;
import com.projects.betsprint.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
