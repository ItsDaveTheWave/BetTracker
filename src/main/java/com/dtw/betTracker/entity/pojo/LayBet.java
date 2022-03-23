package com.dtw.betTracker.entity.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class LayBet {

	@Column(nullable = false, name = "lay_betted_ammount")
	private Double bettedAmmount;
	
	@Column(nullable = false, name = "lay_odds")
	private Double odds;

	private Double layReturn;
	
	@Column(nullable = false)
	private Double liability;
}