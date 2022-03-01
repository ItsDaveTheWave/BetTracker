package com.dtw.betTracker.dto.collection;

import java.util.Collection;

import com.dtw.betTracker.dto.CompetitionDto;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@JsonRootName(value = "competition")
public class CompetitionCollectionDto {

	private Collection<CompetitionDto> competitions;
	private Object links;
}