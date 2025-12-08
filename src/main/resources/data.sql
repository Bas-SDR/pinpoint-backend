INSERT INTO player_stats (games_played, highest_game, highest_series, total_pinfall, perfect_games)
VALUES (45, 300, 720, 8920, 2),
       (60, 300, 780, 10400, 5),
       (32, 300, 680, 7500, 1),
       (51, 265, 700, 9600, 0),
       (40, 289, 740, 8300, 0),
       (55, 275, 710, 10200, 0),
       (28, 240, 660, 6700, 0),
       (38, 260, 695, 7800, 0),
       (47, 280, 730, 9100, 0),
       (36, 258, 685, 7700, 0);

INSERT INTO players(stats_id)
SELECT id
FROM player_stats
ORDER BY id;

INSERT INTO users(first_name, last_name, email, phone, dob, profile_pic, password)
VALUES ('Alice', 'Johnson', 'user@example.com', '9876543210', '1992-05-14', '/images/profilepic/1765205777270_1.svg',
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Brian', 'Miller', 'admin@example.com', '+12345678901', NULL, '/images/profilepic/1765205786211_2.svg',
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Catherine', 'Lopez', 'catherine.lopez@example.com', '8765432109', '1988-11-02',
        '/images/profilepic/1765205791686_3.svg', '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('David', 'Kim', 'david.kim@example.com', '7654321098', NULL, '/images/profilepic/1765205795959_4.svg',
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Emma', 'Brown', 'emma.brown@example.com', '+19876543210', '1995-07-21', '/images/profilepic/1765205804418_5.svg',
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Frank', 'Nguyen', 'frank.nguyen@example.com', '6543210987', NULL, '/images/profilepic/1765205808859_6.svg',
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Grace', 'Smith', 'grace.smith@example.com', '+44712345678', '1990-09-30', '/images/profilepic/1765205812706_7.svg',
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Henry', 'Davis', 'henry.davis@example.com', '5432109876', NULL, '/images/profilepic/1765205816351_8.svg',
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Isabella', 'Martinez', 'isabella.martinez@example.com', '+33123456789', '1993-04-10',
        '/images/profilepic/1765205820715_9.svg', '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Jack', 'Wilson', 'jack.wilson@example.com', '4321098765', NULL, NULL,
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW');

INSERT INTO roles (rolename)
VALUES ('ROLE_USER'),
       ('ROLE_TEAMCAPTAIN'),
       ('ROLE_ADMIN');

INSERT INTO users_roles (users_id, roles_id)
VALUES (1, 1),
       (2, 1),
       (2, 3),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1);

INSERT INTO teams (team_name, team_pic, creation_date, captain_id)
VALUES ('Pin Pioneers', '/images/teampic/1765205533137_1.png', '2021-03-15', 2),
       ('Strike Force', '/images/teampic//1765205593848_2.png', '2020-07-22', 5),
       ('Alley Masters', null, '2022-01-09', 3),
       ('Rolling Thunder', null, '2019-11-05', 7),
       ('Spare Parts', null, '2023-05-18', 1),
       ('Kingpins', null, '2024-02-27', 9),
       ('Ten Pin Commandos', null, '2022-09-10', 4),
       ('The Gutter Gurus', null, '2021-06-30', 10),
       ('Lucky Strikes', null, '2023-12-03', 6),
       ('Bowl Movement', null, '2020-04-14', 8);

INSERT INTO players_teams (team_id, player_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (3, 3),
       (3, 7),
       (4, 7),
       (4, 8),
       (4, 2),
       (5, 1),
       (5, 5),
       (6, 9),
       (6, 10),
       (6, 2),
       (7, 4),
       (7, 8),
       (8, 10),
       (8, 1),
       (9, 6),
       (9, 7),
       (10, 8),
       (10, 9);

UPDATE users
SET player_id = 1
WHERE id = 1;
UPDATE users
SET player_id = 2
WHERE id = 2;
UPDATE users
SET player_id = 3
WHERE id = 3;
UPDATE users
SET player_id = 4
WHERE id = 4;
UPDATE users
SET player_id = 5
WHERE id = 5;
UPDATE users
SET player_id = 6
WHERE id = 6;
UPDATE users
SET player_id = 7
WHERE id = 7;
UPDATE users
SET player_id = 8
WHERE id = 8;
UPDATE users
SET player_id = 9
WHERE id = 9;
UPDATE users
SET player_id = 10
WHERE id = 10;

INSERT INTO leagues (league_name, league_division, league_day, creation_date)
VALUES ('Monday League', 'A', 'MONDAY', '2023-01-10'),
       ('Tuesday Night', 'B', 'TUESDAY', '2023-02-14'),
       ('Wednesday League', 'A', 'WEDNESDAY', '2023-03-22'),
       ('Thursday Masters', 'PRO', 'THURSDAY', '2023-04-28'),
       ('Weekend Open', 'C', 'SATURDAY', '2023-05-12'),
       ('Friday Night Lights', 'B', 'FRIDAY', '2023-06-09');

INSERT INTO league_teams (league_id, team_id)
VALUES (1, 1),
       (1, 3),
       (1, 5),
       (1, 6);

INSERT INTO league_teams (league_id, team_id)
VALUES (2, 2),
       (2, 4),
       (2, 6),
       (2, 1),
       (2, 5);

INSERT INTO league_teams (league_id, team_id)
VALUES (3, 3),
       (3, 2),
       (3, 6);

INSERT INTO league_teams (league_id, team_id)
VALUES (4, 4),
       (4, 5),
       (4, 1),
       (4, 2),
       (4, 3),
       (4, 6);

INSERT INTO league_teams (league_id, team_id)
VALUES (5, 2),
       (5, 5),
       (5, 6),
       (5, 3);

INSERT INTO league_teams (league_id, team_id)
VALUES (6, 1),
       (6, 4),
       (6, 3),
       (6, 2);

INSERT INTO games (date_played, created_date, pinfall, game_number, player_id, team_id, league_id)
VALUES
-- Player 1 / Team 1 / League 1
('2025-01-01', '2025-01-02', 198, 1, 1, 1, 1),
('2025-01-05', '2025-01-06', 212, 2, 1, 1, 1),
('2025-01-10', '2025-01-12', 205, 3, 1, 1, 1),

-- Player 2 / Team 1 / League 1
('2025-01-02', '2025-01-03', 185, 1, 2, 1, 1),
('2025-01-06', '2025-01-07', 200, 2, 2, 1, 1),
('2025-01-11', '2025-01-12', 215, 3, 2, 1, 1),

-- Player 3 / Team 1 / League 1
('2025-01-03', '2025-01-04', 220, 1, 3, 1, 1),
('2025-01-07', '2025-01-08', 210, 2, 3, 1, 1),
('2025-01-12', '2025-01-13', 225, 3, 3, 1, 1),

-- Player 3 / Team 3 / League 1
('2025-01-04', '2025-01-05', 205, 1, 3, 3, 1),
('2025-01-08', '2025-01-09', 215, 2, 3, 3, 1),
('2025-01-13', '2025-01-14', 210, 3, 3, 3, 1),

-- Player 5 / Team 5 / League 1
('2025-01-05', '2025-01-06', 198, 1, 5, 5, 1),
('2025-01-09', '2025-01-10', 205, 2, 5, 5, 1),
('2025-01-14', '2025-01-15', 212, 3, 5, 5, 1),

-- Player 2 / Team 6 / League 1
('2025-01-06', '2025-01-07', 195, 1, 2, 6, 1),
('2025-01-10', '2025-01-11', 205, 2, 2, 6, 1),
('2025-01-15', '2025-01-16', 198, 3, 2, 6, 1),

-- Player 9 / Team 6 / League 1
('2025-01-07', '2025-01-08', 208, 1, 9, 6, 1),
('2025-01-11', '2025-01-12', 222, 2, 9, 6, 1),
('2025-01-16', '2025-01-17', 230, 3, 9, 6, 1),

-- Player 10 / Team 6 / League 1
('2025-01-08', '2025-01-09', 192, 1, 10, 6, 1),
('2025-01-12', '2025-01-13', 210, 2, 10, 6, 1),
('2025-01-17', '2025-01-18', 218, 3, 10, 6, 1),

-- Player 7 / Team 3 / League 3
('2025-01-09', '2025-01-10', 207, 1, 7, 3, 3),
('2025-01-13', '2025-01-14', 220, 2, 7, 3, 3),
('2025-01-18', '2025-01-19', 195, 3, 7, 3, 3),

-- Player 4 / Team 7 / League 2
('2025-01-10', '2025-01-11', 210, 1, 4, 7, 2),
('2025-01-14', '2025-01-15', 218, 2, 4, 7, 2),
('2025-01-19', '2025-01-20', 195, 3, 4, 7, 2),

-- Player 7 / Team 4 / League 4
('2025-01-11', '2025-01-12', 203, 1, 7, 4, 4),
('2025-01-15', '2025-01-16', 216, 2, 7, 4, 4),
('2025-01-20', '2025-01-21', 198, 3, 7, 4, 4),

-- Player 8 / Team 4 / League 4
('2025-01-12', '2025-01-13', 190, 1, 8, 4, 4),
('2025-01-16', '2025-01-17', 212, 2, 8, 4, 4),
('2025-01-21', '2025-01-22', 225, 3, 8, 4, 4),

-- Player 8 / Team 10 / League 6
('2025-01-13', '2025-01-14', 203, 1, 8, 10, 6),
('2025-01-17', '2025-01-18', 221, 2, 8, 10, 6),
('2025-01-22', '2025-01-23', 210, 3, 8, 10, 6),

-- Player 9 / Team 9 / League 1
('2025-01-14', '2025-01-15', 200, 1, 9, 9, 1),
('2025-01-18', '2025-01-19', 215, 2, 9, 9, 1),
('2025-01-23', '2025-01-24', 225, 3, 9, 9, 1);
