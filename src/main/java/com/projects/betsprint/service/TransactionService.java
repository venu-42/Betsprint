package com.projects.betsprint.service;

import com.projects.betsprint.dto.CreateTransactionRequest;
import com.projects.betsprint.dto.TransactionResponse;
import com.projects.betsprint.model.Transaction;
import com.projects.betsprint.model.User;
import com.projects.betsprint.repository.TransactionRepository;
import com.projects.betsprint.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public TransactionResponse create(CreateTransactionRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Transaction transaction = new Transaction();
        transaction.setTransactionType(request.transactionType());
        transaction.setAmount(request.amount());
        transaction.setUser(user);
        transaction.setCreatedAt(request.createdAt());
        transaction.setStatus(request.status());
        return toResponse(transactionRepository.save(transaction));
    }

    private TransactionResponse toResponse(Transaction transaction) {
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
