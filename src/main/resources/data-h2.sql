insert into sport (id, sport_name) values 
	(1, 'FootBall'), 
	(2, 'BasketBall');
	
insert into competition (id, competition_name, sport_id) values 
	(1, 'UEFA World Cup', 1),
	(2, 'Fake competition', 2);

insert into matched_bet (id, bet_date, game_date, sport_id, competition_id, team1, team2, result, total_return, bonus_type) values 
	(1, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null),
	(2, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 2, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null),
	(3, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null),
	(4, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null),
	(5, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null),
	(6, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null),
	(7, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null),
	(8, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null),
	(9, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null),
	(10, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null),
	(11, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null),
	(12, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null);