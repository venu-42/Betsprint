package com.projects.betsprint.controller;

import com.projects.betsprint.dto.CreateUserRequest;
import com.projects.betsprint.dto.ContestEntryResponse;
import com.projects.betsprint.dto.FantasyTeamResponse;
import com.projects.betsprint.dto.TransactionResponse;
import com.projects.betsprint.dto.UserResponse;
import com.projects.betsprint.dto.WalletRequest;
import com.projects.betsprint.dto.WalletResponse;
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
    public List<FantasyTeamResponse> getFantasyTeamsByMatchId(
            @PathVariable("userId") UUID userId,
            @PathVariable("matchId") Long matchId) {
        return userService.getFantasyTeamsByMatchId(userId,matchId);
    }

    @GetMapping("/{userId}/contest-entries")
    public List<ContestEntryResponse> getContestEntries(@PathVariable UUID userId) {
        return userService.getContestEntries(userId);
    }

    @GetMapping("/{userId}/wallet")
    public WalletResponse getWallet(@PathVariable UUID userId) {
        return userService.getWallet(userId);
    }

    @PostMapping("/{userId}/wallet/deposit")
    public WalletResponse deposit(@PathVariable UUID userId, @RequestBody WalletRequest request) {
        return userService.deposit(userId, request);
    }

    @PostMapping("/{userId}/wallet/withdraw")
    public WalletResponse withdraw(@PathVariable UUID userId, @RequestBody WalletRequest request) {
        return userService.withdraw(userId, request);
    }

    @GetMapping("/{userId}/transactions")
    public List<TransactionResponse> getTransactions(@PathVariable UUID userId) {
        return userService.getTransactions(userId);
    }
}
