package com.dtw.betTracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dtw.betTracker.entity.MatchedBet;

public interface MatchedBetRepo extends JpaRepository<MatchedBet, Long> {

}