INSERT INTO users(first_name, last_name, email, phone, dob, profile_pic, password)
VALUES ('Alice', 'Johnson', 'user@example.com', '9876543210', '1992-05-14', 'https://pics.example.com/alice.jpg',
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Brian', 'Miller', 'admin@example.com', '+12345678901', NULL, NULL,
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Catherine', 'Lopez', 'catherine.lopez@example.com', '8765432109', '1988-11-02',
        'https://pics.example.com/catherine.png', '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('David', 'Kim', 'david.kim@example.com', '7654321098', NULL, NULL,
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Emma', 'Brown', 'emma.brown@example.com', '+19876543210', '1995-07-21', 'https://pics.example.com/emma.jpg',
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Frank', 'Nguyen', 'frank.nguyen@example.com', '6543210987', NULL, NULL,
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Grace', 'Smith', 'grace.smith@example.com', '+44712345678', '1990-09-30', 'https://pics.example.com/grace.png',
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Henry', 'Davis', 'henry.davis@example.com', '5432109876', NULL, NULL,
        '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
       ('Isabella', 'Martinez', 'isabella.martinez@example.com', '+33123456789', '1993-04-10',
        'https://pics.example.com/isabella.jpg', '$2a$10$WvyoQlHQumGn8pVts2c23evkIdruk/TAhqsjc7mD6lbqlGi56fPLW'),
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
VALUES ('Pin Pioneers', 'https://example.com/images/pin-pioneers.png', '2021-03-15', 2),
       ('Strike Force', 'https://example.com/images/strike-force.png', '2020-07-22', 5),
       ('Alley Masters', 'https://example.com/images/alley-masters.png', '2022-01-09', 3),
       ('Rolling Thunder', 'https://example.com/images/rolling-thunder.png', '2019-11-05', 7),
       ('Spare Parts', 'https://example.com/images/spare-parts.png', '2023-05-18', 1),
       ('Kingpins', 'https://example.com/images/kingpins.png', '2024-02-27', 9),
       ('Ten Pin Commandos', 'https://example.com/images/ten-pin-commandos.png', '2022-09-10', 4),
       ('The Gutter Gurus', 'https://example.com/images/gutter-gurus.png', '2021-06-30', 10),
       ('Lucky Strikes', 'https://example.com/images/lucky-strikes.png', '2023-12-03', 6),
       ('Bowl Movement', 'https://example.com/images/bowl-movement.png', '2020-04-14', 8);