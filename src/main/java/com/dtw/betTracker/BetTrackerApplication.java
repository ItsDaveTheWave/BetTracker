package com.dtw.betTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class BetTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetTrackerApplication.class, args);
	}
}