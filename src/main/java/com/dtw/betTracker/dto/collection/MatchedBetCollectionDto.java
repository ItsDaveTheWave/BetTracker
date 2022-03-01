package com.dtw.betTracker.dto.collection;

import java.util.Collection;

import com.dtw.betTracker.dto.MatchedBetDto;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@JsonRootName(value = "matchedBet")
public class MatchedBetCollectionDto {

	private Collection<MatchedBetDto> matchedBets;
	private Object links;
}