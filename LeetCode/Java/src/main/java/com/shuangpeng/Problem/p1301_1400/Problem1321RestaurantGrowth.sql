# Write your MySQL query statement below（餐馆营业额变化增长）

WITH summary AS (
    SELECT SUM(amount) AS amount, visited_on
    FROM Customer
    GROUP BY visited_on
), pre_sum AS (
    SELECT SUM(amount) OVER(ORDER BY visited_on ASC) AS total,
        visited_on,
        DATE_ADD(visited_on, INTERVAL -7 DAY) AS pre_date
    FROM summary
), first_date AS (
    SELECT MIN(visited_on) min_day
    FROM summary
)
SELECT s1.visited_on, IF(s2.total IS NULL, s1.total, s1.total - s2.total) AS amount, ROUND(IF(s2.total IS NULL, s1.total, s1.total - s2.total) / 7, 2) AS average_amount
FROM pre_sum s1
    LEFT JOIN pre_sum s2 ON s1.pre_date = s2.visited_on
    LEFT JOIN first_date f ON true
WHERE s2.visited_on IS NOT NULL OR s1.visited_on = DATE_ADD(f.min_day, INTERVAL 6 DAY)
;

WITH summary AS (
    SELECT SUM(amount) AS amount, visited_on
    FROM Customer
    GROUP BY visited_on
), pre_sum AS (
    SELECT (@sum := @sum + amount) AS total, visited_on, DATE_ADD(visited_on, INTERVAL -7 DAY) AS pre_date
    FROM summary, (SELECT @sum := 0) t
    ORDER BY visited_on ASC
), tmp AS (
    SELECT p1.visited_on, IF(p2.total IS NULL, p1.total, p1.total - p2.total) AS amount
    FROM (SELECT MIN(visited_on) min_date FROM summary) t
        LEFT JOIN pre_sum p1 ON p1.visited_on >= DATE_ADD(t.min_date, INTERVAL 6 DAY)
        LEFT JOIN pre_sum p2 ON p2.visited_on = p1.pre_date
)
SELECT visited_on, amount, ROUND(amount / 7, 2) AS average_amount
FROM tmp
;

WITH summary AS (
    SELECT SUM(amount) AS amount, visited_on
    FROM Customer
    GROUP BY visited_on
), tmp AS (
    SELECT (SELECT SUM(s2.amount) FROM summary s2 WHERE s2.visited_on BETWEEN DATE_ADD(s1.visited_on, INTERVAL -6 DAY) AND s1.visited_on) AS amount,
        s1.visited_on
    FROM summary s1
), first_date AS (
    SELECT DATE_ADD(MIN(visited_on), INTERVAL 6 DAY) AS min_day FROM summary
)
SELECT t.visited_on, t.amount, ROUND(t.amount / 7, 2) AS average_amount
FROM tmp t, first_date f
WHERE t.visited_on >= f.min_day
;

WITH group_tmp AS (
    SELECT visited_on, SUM(amount) AS amount
    FROM Customer
    GROUP BY visited_on
), tmp AS (
    SELECT
        visited_on,
        amount,
        LAG(amount, 1, 0) OVER (ORDER BY visited_on ASC) AS lag1,
        LAG(amount, 2, 0) OVER (ORDER BY visited_on ASC) AS lag2,
        LAG(amount, 3, 0) OVER (ORDER BY visited_on ASC) AS lag3,
        LAG(amount, 4, 0) OVER (ORDER BY visited_on ASC) AS lag4,
        LAG(amount, 5, 0) OVER (ORDER BY visited_on ASC) AS lag5,
        LAG(amount, 6, 0) OVER (ORDER BY visited_on ASC) AS lag6
    FROM group_tmp
), summary AS (
    SELECT t.visited_on, t.amount + t.lag1 + t.lag2 + t.lag3 + t.lag4 + t.lag5 + t.lag6 AS amount
    FROM (SELECT MIN(visited_on) min_date FROM Customer) m
    LEFT JOIN tmp t ON t.visited_on >= DATE_ADD(m.min_date, INTERVAL 6 DAY)
)
SELECT s.visited_on, s.amount, ROUND(s.amount / 7, 2) AS average_amount
FROM summary s
;

SELECT visited_on, amount, ROUND(amount / 7, 2) AS average_amount
FROM (
    SELECT visited_on, SUM(amount) OVER (ORDER BY visited_on ASC ROWS 6 PRECEDING) AS amount
    FROM (
        SELECT visited_on, SUM(amount) AS amount
        FROM Customer
        GROUP BY visited_on
    ) t1
) t2
WHERE visited_on >= DATE_ADD((SELECT MIN(visited_on) FROM Customer), INTERVAL 6 DAY)
;
