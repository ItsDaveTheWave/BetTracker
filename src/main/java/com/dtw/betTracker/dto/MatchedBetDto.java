package com.dtw.betTracker.dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

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
	
	@JsonProperty(access = Access.READ_ONLY)
	private Double totalReturn;
	
	private String bonusType;
	
	@NotNull
	@Valid
	private BackBetDto backBet;
	
	@NotNull
	@Valid
	private SportEventDto sportEvent;
}