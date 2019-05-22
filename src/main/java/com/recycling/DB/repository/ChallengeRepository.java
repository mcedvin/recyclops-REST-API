package com.recycling.DB.repository;

import com.recycling.production.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository  extends JpaRepository<Challenge, Integer> {
}
