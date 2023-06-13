# Write your MySQL query statement below（最后一个能进入电梯的人）

SELECT person_name
FROM Queue q
WHERE (SELECT SUM(weight) FROM Queue t WHERE t.turn <= q.turn) <= 1000
ORDER BY turn DESC
LIMIT 1
;

SELECT person_name
FROM (
    SELECT turn, person_name, SUM(weight) OVER (ORDER BY turn ASC) AS total
    FROM Queue
) t
WHERE total <= 1000
ORDER BY turn DESC
LIMIT 1
;

SELECT person_name
FROM (
    SELECT turn, person_name, @total := @total + weight AS total
    FROM Queue, (SELECT @total := 0) t
    ORDER BY turn ASC
) a
WHERE a.total <= 1000
ORDER BY a.turn DESC
LIMIT 1
;

SELECT a.person_name
FROM Queue a, Queue b
WHERE a.turn >= b.turn
GROUP BY a.person_id HAVING SUM(b.weight) <= 1000
ORDER BY a.turn DESC
LIMIT 1
