package com.projects.betsprint.repository;

import com.projects.betsprint.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT t FROM Transaction t WHERE t.user.userId = :userId ORDER BY t.createdAt DESC")
    List<Transaction> findAllByUserIdOrderByCreatedAtDesc(@Param("userId") UUID userId);
}
