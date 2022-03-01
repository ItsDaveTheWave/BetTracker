package com.dtw.betTracker.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@JsonRootName(value = "sport")
public class SportDto extends RepresentationModel<SportDto> {

	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	@NotNull
	private String sportName;
	@JsonProperty(access = Access.READ_ONLY)
	private Set<CompetitionDto> competitions;
}
