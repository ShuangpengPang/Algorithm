--换座位（626）

SELECT t1.id,
    CASE
        WHEN t2.student IS NULL THEN t1.student
        ELSE t2.student
    END AS student
FROM Seat t1
    LEFT JOIN Seat t2 ON (t2.id = t1.id + 1 && t1.id % 2 = 1) || (t2.id = t1.id - 1 && t1.id % 2 = 0)