DELETE
FROM usr;

INSERT INTO usr (login, password)
VALUES ('Ulxanxv', '$2a$12$Tcb2gJaAYXak6ZUEx8EfjOm9VC9KFppV2em5Yp3H1cmsv760cnDMy'),
       ('Ulxanxv2', '$2a$12$Tcb2gJaAYXak6ZUEx8EfjOm9VC9KFppV2em5Yp3H1cmsv760cnDMy'),
       ('Ulxanxv3', '$2a$12$Tcb2gJaAYXak6ZUEx8EfjOm9VC9KFppV2em5Yp3H1cmsv760cnDMy'),
       ('Ulxanxv4', '$2a$12$Tcb2gJaAYXak6ZUEx8EfjOm9VC9KFppV2em5Yp3H1cmsv760cnDMy');

DELETE
FROM disk;

-- For Docker
SET datestyle = dmy;

INSERT INTO disk (description, release_date, owner_id, holder_id)
VALUES ('S.T.A.L.K.E.R.: Тень Чернобыля', '27-03-2007', 1, 2),
       ('S.T.A.L.K.E.R.: Чистое небо', '11-07-2008', 1, 3),
       ('S.T.A.L.K.E.R.: Зов Припяти', '30-04-2009', 1, 1),
--
       ('Властелин колец: Братство Кольца', '27-03-2007', 2, 2),
       ('Властелин колец: Две крепости', '11-07-2008', 2, 2),
       ('Властелин колец: Возвращение короля', '30-04-2009', 2, 2),
--
       ('Хоббит: Нежданное путешествие', '27-03-2007', 3, 1),
       ('Хоббит: Пустошь Смауга', '11-07-2008', 3, 1),
       ('Хоббит: Битва пяти воинств', '30-04-2009', 3, 3),
--
       ('Гарри Поттер и философский камень', '27-03-2007', 4, 4),
       ('Гарри Поттер и Тайная комната', '11-07-2008', 4, 4),
       ('Гарри Поттер и узник Азкабана', '30-04-2009', 4, 4),
       ('Гарри Поттер и Кубок огня', '27-03-2007', 4, 4),
       ('Гарри Поттер и Орден Феникса', '11-07-2008', 4, 4),
       ('Гарри Поттер и Принц-полукровка', '30-04-2009', 4, 4),
       ('Гарри Поттер и Дары Смерти. Часть 1', '11-07-2008', 4, 4),
       ('Гарри Поттер и Дары Смерти. Часть 2', '30-04-2009', 4, 4);