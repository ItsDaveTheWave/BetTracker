package com.dtw.betTracker.dto.collection;

import java.util.Collection;

import com.dtw.betTracker.dto.SportDto;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@JsonRootName(value = "sport")
public class SportCollectionDto {

	private Collection<SportDto> sports;
	private Object links;
}