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
