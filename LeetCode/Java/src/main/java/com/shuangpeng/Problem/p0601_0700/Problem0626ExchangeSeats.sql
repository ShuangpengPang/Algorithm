--换座位（626）

SELECT t1.id,
    CASE
        WHEN t2.student IS NULL THEN t1.student
        ELSE t2.student
    END AS student
FROM Seat t1
    LEFT JOIN Seat t2 ON (t2.id = t1.id + 1 && t1.id % 2 = 1) || (t2.id = t1.id - 1 && t1.id % 2 = 0)
;

SELECT
    s1.id, COALESCE(s2.student, s1.student) AS student
FROM
    Seat s1
        LEFT JOIN Seat s2 ON ((s1.id + 1) ^ 1) - 1 = s2.id
ORDER BY s1.id ASC
;

SELECT
    CASE
        WHEN MOD(id, 2) = 1 AND id = count
            THEN id
        WHEN MOD(id, 2) = 1
            THEN id + 1
        ELSE id - 1
    END AS id,
    student
FROM
    Seat, (SELECT count(*) AS count FROM Seat) AS c
ORDER BY id ASC
;

SELECT
    s1.id, COALESCE(s2.student, s1.student) AS student
FROM
    seat s1
        LEFT JOIN
    seat s2 ON ((s1.id + 1) ^ 1) - 1 = s2.id
ORDER BY s1.id;

SELECT
    id,
    CASE
        WHEN MOD(id, 2) = 1 THEN LEAD(student, 1, student) OVER (ORDER BY id)
        WHEN MOD(id, 2) = 0 THEN LAG(student, 1) OVER (ORDER BY id)
    END AS student
FROM
    Seat
ORDER BY id
;
