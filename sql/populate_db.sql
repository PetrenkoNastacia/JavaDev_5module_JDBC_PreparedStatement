INSERT INTO worker (id, name, birthday, level, salary) VALUES
(1, 'Anastasiia', '1994-02-23', 'Middle', 3500),
(2, 'Karen', '1991-01-10', 'Middle', 3000),
(3, 'Helen', '1992-01-30', 'Senior', 5200),
(4, 'Linda', '1990-05-25', 'Senior', 4100),
(5, 'Phobe', '1988-06-16', 'Middle', 3000),
(6, 'Piper', '1988-07-04', 'Middle', 3000),
(7, 'Prue', '1982-08-17', 'Middle', 3000),
(8, 'David', '1994-03-13', 'Junior', 2000),
(9, 'James', '1990-02-27', 'Junior', 2000),
(10, 'Roman', '1997-06-19', 'Trainee', 800);

INSERT INTO client (id, name) VALUES
(1, 'Hideo'),
(2, 'Hidetaka'),
(3, 'Shigeru'),
(4, 'John'),
(5, 'Will');

INSERT INTO project (id, client_id, start_date, finish_date) VALUES
(1, 1, '2026-06-06', '2030-12-12'),
(2, 2, '2024-04-14', '2024-05-15'),
(3, 3, '2023-11-11', '2024-11-12'),
(4, 4, '2025-05-05', '2026-06-16'),
(5, 5, '2026-06-06', '2027-07-27'),
(6, 1, '2024-01-19', '2028-08-18'),
(7, 2, '2023-08-08', '2027-07-17'),
(8, 3, '2026-06-06', '2028-03-13'),
(9, 4, '2027-07-07', '2030-03-30'),
(10, 5, '2024-04-04', '2024-05-05');

INSERT INTO project_worker (project_id, worker_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);