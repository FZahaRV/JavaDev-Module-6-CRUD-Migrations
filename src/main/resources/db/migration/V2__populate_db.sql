INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY)
VALUES
    ('Employee1', '1990-01-01', 'Trainee', 800),
    ('Employee2', '1985-02-15', 'Junior', 1200),
    ('Employee3', '1980-03-20', 'Middle', 2500),
    ('Employee4', '1975-04-10', 'Senior', 4500),
    ('Employee5', '1995-05-05', 'Trainee', 900),
    ('Employee6', '1993-06-30', 'Junior', 1300),
    ('Employee7', '1992-07-25', 'Middle', 2700),
    ('Employee8', '1991-08-18', 'Senior', 4700),
    ('Employee9', '1988-09-11', 'Trainee', 950),
    ('Employee10', '1986-10-06', 'Junior', 1400);

INSERT INTO client (NAME)
VALUES
    ('Client1'),
    ('Client2'),
    ('Client3'),
    ('Client4'),
    ('Client5');

INSERT INTO project (NAME, CLIENT_ID, START_DATE, FINISH_DATE)
VALUES
    ('ProjectGAF', 1, '2023-01-01', '2023-03-31'),
    ('ProjectMYU', 2, '2023-02-15', '2023-04-15'),
    ('ProjectMOO', 3, '2023-03-20', '2023-05-20'),
    ('ProjectGAGA', 4, '2023-04-10', '2023-06-10'),
    ('ProjectHRU', 5, '2023-05-05', '2023-07-05'),
    ('ProjectFUR', 1, '2023-06-01', '2023-08-31'),
    ('ProjectKOKO', 2, '2023-07-15', '2023-09-15'),
    ('ProjectTG', 3, '2023-08-20', '2023-10-20'),
    ('ProjectGTL', 4, '2023-09-10', '2023-11-10'),
    ('ProjectNYMJ', 5, '2023-10-05', '2023-12-05');

INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
SELECT
    project.id,
    worker.id
FROM
    project
JOIN
    worker
ORDER BY
    RAND()
LIMIT 5;
