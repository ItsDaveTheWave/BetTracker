package com.dtw.betTracker.service.lifecycleEventListener;

import javax.persistence.PreRemove;

import com.dtw.betTracker.entity.Competition;
import com.dtw.betTracker.entity.MatchedBet;

public class CompetitionLifecycleEvenListener {
	
	@PreRemove
	private void setMatchedBetCompetitionIdToNull(Competition competition) {
		for(MatchedBet bet : competition.getMatchedBets()) {
			if(bet.getId() != null) {
				bet.getSportEvent().setCompetition(null);
			}
		}
	}
}