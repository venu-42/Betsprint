package com.projects.betsprint.repository;

import com.projects.betsprint.model.PlayerScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerScoreRepository extends JpaRepository<PlayerScore, Long> {
}
