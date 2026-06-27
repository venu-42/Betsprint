package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateUserRequest;
import com.projects.betsprint.dto.ContestEntryResponse;
import com.projects.betsprint.dto.FantasyTeamResponse;
import com.projects.betsprint.dto.TransactionResponse;
import com.projects.betsprint.dto.UserResponse;
import com.projects.betsprint.dto.WalletRequest;
import com.projects.betsprint.dto.WalletResponse;
import com.projects.betsprint.model.ContestEntry;
import com.projects.betsprint.model.FantasyTeam;
import com.projects.betsprint.model.Transaction;
import com.projects.betsprint.model.TransactionType;
import com.projects.betsprint.model.User;
import com.projects.betsprint.repository.ContestEntryRepository;
import com.projects.betsprint.repository.FantasyTeamRepository;
import com.projects.betsprint.repository.TransactionRepository;
import com.projects.betsprint.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FantasyTeamRepository fantasyTeamRepository;
    private final ContestEntryRepository contestEntryRepository;
    private final TransactionRepository transactionRepository;

    public UserService(
            UserRepository userRepository,
            FantasyTeamRepository fantasyTeamRepository,
            ContestEntryRepository contestEntryRepository,
            TransactionRepository transactionRepository
    ) {
        this.userRepository = userRepository;
        this.fantasyTeamRepository = fantasyTeamRepository;
        this.contestEntryRepository = contestEntryRepository;
        this.transactionRepository = transactionRepository;
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

    public List<FantasyTeamResponse> getFantasyTeamsByMatchId(UUID userId, Long matchId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return fantasyTeamRepository.findAllByUserIdAndMatchId(userId, matchId).stream()
                .map(this::toFantasyTeamResponse)
                .toList();
    }

    public List<ContestEntryResponse> getContestEntries(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return contestEntryRepository.findAllByUserId(userId).stream()
                .map(this::toContestEntryResponse)
                .toList();
    }

    public WalletResponse getWallet(UUID userId) {
        User user = getUserOrThrow(userId);
        return toWalletResponse(user);
    }

    public WalletResponse deposit(UUID userId, WalletRequest request) {
        User user = getUserOrThrow(userId);
        BigDecimal amount = positiveAmount(request.amount());
        user.setWalletBalance(currentBalance(user).add(amount));

        User savedUser = userRepository.save(user);
        saveTransaction(savedUser, TransactionType.DEPOSIT, amount, "SUCCESS");
        return toWalletResponse(savedUser);
    }

    public WalletResponse withdraw(UUID userId, WalletRequest request) {
        User user = getUserOrThrow(userId);
        BigDecimal amount = positiveAmount(request.amount());
        BigDecimal currentBalance = currentBalance(user);

        if (currentBalance.compareTo(amount) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient wallet balance");
        }

        user.setWalletBalance(currentBalance.subtract(amount));
        User savedUser = userRepository.save(user);
        saveTransaction(savedUser, TransactionType.WITHDRAWAL, amount, "SUCCESS");
        return toWalletResponse(savedUser);
    }

    public List<TransactionResponse> getTransactions(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return transactionRepository.findAllByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(this::toTransactionResponse)
                .toList();
    }

    private User getUserOrThrow(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private BigDecimal positiveAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be positive");
        }
        return amount;
    }

    private BigDecimal currentBalance(User user) {
        return user.getWalletBalance() == null ? BigDecimal.ZERO : user.getWalletBalance();
    }

    private void saveTransaction(User user, TransactionType transactionType, BigDecimal amount, String status) {
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setStatus(status);
        transactionRepository.save(transaction);
    }

    private WalletResponse toWalletResponse(User user) {
        return new WalletResponse(user.getUserId(), currentBalance(user));
    }

    private FantasyTeamResponse toFantasyTeamResponse(FantasyTeam fantasyTeam) {
        return new FantasyTeamResponse(
                fantasyTeam.getFantasyTeamId(),
                fantasyTeam.getUser().getUserId(),
                fantasyTeam.getMatch().getMatchId(),
                fantasyTeam.getCaptain().getPlayerId(),
                fantasyTeam.getViceCaptain().getPlayerId()
        );
    }

    private ContestEntryResponse toContestEntryResponse(ContestEntry contestEntry) {
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

    private TransactionResponse toTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getUser().getUserId(),
                transaction.getCreatedAt(),
                transaction.getStatus()
        );
    }
}
