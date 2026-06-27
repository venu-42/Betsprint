package com.projects.betsprint.controller;

import com.projects.betsprint.dto.CreateUserRequest;
import com.projects.betsprint.dto.UserResponse;
import com.projects.betsprint.model.FantasyTeam;
import com.projects.betsprint.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody CreateUserRequest request) {
        return userService.create(request);
    }

    @GetMapping("/{userId}/matches/{matchId}/fantasy-teams")
    public List<FantasyTeam> getFantasyTeamsByMatchId(
            @PathVariable("userId") UUID userId,
            @PathVariable("matchId") Long matchId) {
        return userService.getFantasyTeamsByMatchId(userId,matchId);
    }
}
