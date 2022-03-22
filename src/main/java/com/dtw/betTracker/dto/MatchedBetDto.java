package com.dtw.betTracker.dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import com.dtw.betTracker.annotation.ValueOfEnum;
import com.dtw.betTracker.enums.GameResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@JsonRootName(value = "matchedBet")
public class MatchedBetDto extends RepresentationModel<MatchedBetDto> {

	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
	private Date betDate;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
	private Date gameDate;
	
	@NotNull
	private String sport;
	private String competition;
	
	@NotNull
	private String team1;
	
	@NotNull
	private String team2;
	
	@ValueOfEnum(enumClass = GameResult.class)
	private String result;
	private Double totalReturn;
	private String bonusType;
	
	@NotNull
	@Valid
	private BackBetDto backBet;
}