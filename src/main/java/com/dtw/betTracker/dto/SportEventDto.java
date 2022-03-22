package com.dtw.betTracker.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.dtw.betTracker.annotation.ValueOfEnum;
import com.dtw.betTracker.enums.GameResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SportEventDto {

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
}