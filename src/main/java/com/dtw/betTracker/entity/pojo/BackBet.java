package com.dtw.betTracker.entity.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.dtw.betTracker.entity.BookMaker;
import com.dtw.betTracker.enums.GameResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class BackBet {

	@ManyToOne
	@JoinColumn(name = "back_bookmaker_id", referencedColumnName = "id")
	private BookMaker bookMaker;
	
	@Column(nullable = false, name = "back_betted_result")
	@Enumerated(EnumType.STRING)
	private GameResult bettedResult;
	
	@Column(nullable = false, name = "back_betted_ammount")
	private Double bettedAmmount;
	
	@Column(nullable = false, name = "back_odds")
	private Double odds;

	private Double backReturn;
}