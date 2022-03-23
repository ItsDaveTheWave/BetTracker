package com.dtw.betTracker.entity;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dtw.betTracker.entity.pojo.BackBet;
import com.dtw.betTracker.entity.pojo.LayBet;
import com.dtw.betTracker.entity.pojo.SportEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MatchedBet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date betDate;
	
	@Embedded
	private SportEvent sportEvent;
	
	@Embedded
	private BackBet backBet;
	
	@Embedded
	private LayBet layBet;
	
	private Double totalReturn;
	private String bonusType;
}