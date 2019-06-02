insert into queue (name, short_name) values
('Informatyka i AiR Stacjonarne', 'E. Inf i AiR St.'),
('Informatyka i AiR Niestacjonarne','E. Inf i AiR Nst.'),
('Elektrotechnika Stacjonarne','E. Elektro St.'),
('Elektrotechnika Niestacjonarne','E. Elektro Nst.'),
('Informatyka','EITI Inf');

insert into field_of_study (faculty, name, short_name, queue_id, access_role) values
('Wydział Elektryczny', 'Informatyka Stosowana Stacjonarne', 'E. Inf St.', (select id from queue where name = 'Informatyka i AiR Stacjonarne'), 'ROLE_WE_INF' ),
('Wydział Elektryczny', 'Informatyka Stosowana Niestacjonarne', 'E. Inf Nst.', (select id from queue where name = 'Informatyka i AiR Niestacjonarne'), 'ROLE_WE_INF_N'),
('Wydział Elektryczny', 'Automatyka i Robotyka Stacjonarne', 'E. AiR St.', (select id from queue where name = 'Informatyka i AiR Stacjonarne'), 'ROLE_WE_AIR'),
('Wydział Elektryczny', 'Automatyka i Robotyka Niestacjonarne', 'E. AiR Nst.', (select id from queue where name = 'Informatyka i AiR Niestacjonarne'), 'ROLE_WE_AIR_N'),
('Wydział Elektryczny', 'Elektrotechnika Stacjonarne', 'E. Elektro St.', (select id from queue where name = 'Elektrotechnika Stacjonarne'), 'ROLE_WE_ELEKTRO'),
('Wydział Elektryczny', 'Elektrotechnika Niestacjonarne', 'E. Elektro Nst.', (select id from queue where name = 'Elektrotechnika Niestacjonarne'), 'ROLE_WE_ELEKTRO_N'),
('Wydział Elektroniki i Technik Informacyjnych', 'Informatyka Stacjonarne', 'EITI Inf St.', (select id from queue where name = 'Informatyka'), 'ROLE_WEITI_INF'),
('Wydział Elektroniki i Technik Informacyjnych', 'Informatyka Niestacjonarne', 'EITI Inf Nst.', (select id from queue where name = 'Informatyka'), 'ROLE_WEITI_INF_N');

insert into issue_category (name) values
('Przedłużenie terminu złożenia pracy dyplomowej'),
('Sprawa ogólna'),
('Rozłożenie opłaty za studia na raty'),
('Zmniejszenie opłaty za powtarzanie'),
('Podanie o urlop'),
('Podanie o rejestrację'),
('Potwierdzenie opłaty');

