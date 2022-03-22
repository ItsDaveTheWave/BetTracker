insert into sport (id, sport_name) values 
	(1, 'FootBall'), 
	(2, 'BasketBall');
	
insert into competition (id, competition_name, sport_id) values 
	(1, 'UEFA World Cup', 1),
	(2, 'Fake competition', 2);

insert into book_maker (id, name) values
	(1, 'BetFair'),
	(2, 'BiWin');

insert into matched_bet (id, bet_date, game_date, sport_id, competition_id, team1, team2, result, total_return, bonus_type, 
		back_bookmaker_id, back_betted_result, back_odds, back_betted_ammount, back_return) values 
	(1, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null, 1, 'TEAM_1_WIN', 1.23, 10, 4.05),
	(2, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 2, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null, 1, 'TEAM_1_WIN', 1.23, 10, 4.05),
	(3, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null, 1, 'TEAM_1_WIN', 1.23, 10, 4.05),
	(4, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null, 1, 'TEAM_1_WIN', 1.23, 10, 4.05),
	(5, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null, 1, 'TEAM_1_WIN', 1.23, 10, 4.05),
	(6, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null, 1, 'TEAM_1_WIN', 1.23, 10, 4.05),
	(7, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null, 1, 'TEAM_1_WIN', 1.23, 10, 4.05),
	(8, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null, 1, 'TEAM_1_WIN', 1.23, 10, 4.05),
	(9, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null, 1, 'TEAM_1_WIN', 1.23, 10, 4.05),
	(10, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null, 1, 'TEAM_1_WIN', 1.23, 10, 4.05),
	(11, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null, 1, 'TEAM_1_WIN', 1.23, 10, 4.05),
	(12, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia', 'TEAM_1_WIN', 7.07, null, 1, 'TEAM_1_WIN', 1.23, 10, 4.05);