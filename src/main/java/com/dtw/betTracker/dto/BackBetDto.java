package com.dtw.betTracker.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.dtw.betTracker.annotation.ValueOfEnum;
import com.dtw.betTracker.enums.GameResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class BackBetDto {
	
	@NotNull
	private String bookMaker;
	
	@NotNull
	@ValueOfEnum(enumClass = GameResult.class)
	private String bettedResult;
	
	@NotNull
	@Positive
	private Double bettedAmmount;
	
	@NotNull
	@Positive
	private Double odds;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Double backReturn;
}