package com.dtw.betTracker.service.lifecycleEventListener;

import javax.persistence.PreRemove;

import com.dtw.betTracker.entity.MatchedBet;
import com.dtw.betTracker.entity.Sport;

public class SportLifecycleEvenListener {

	@PreRemove
	private void setMatchedBetSportIdToNull(Sport sport) {
		for(MatchedBet bet : sport.getMatchedBets()) {
			if(bet.getId() != null) {
				bet.getSportEvent().setSport(null);
			}
		}
	}
}