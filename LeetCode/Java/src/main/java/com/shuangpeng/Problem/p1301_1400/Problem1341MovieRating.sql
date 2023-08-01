# Write your MySQL query statement below（电影评分）

SELECT results
FROM (
   SELECT u.name AS results
   FROM (
        SELECT user_id, COUNT(user_id) AS user_count
        FROM MovieRating
        GROUP BY user_id
    ) tu
    LEFT JOIN Users u ON tu.user_id = u.user_id
    ORDER BY tu.user_count DESC, u.name ASC
    LIMIT 1
) t1
UNION ALL
SELECT results
FROM (
   SELECT m.title AS results
    FROM (
        SELECT movie_id, SUM(rating) / COUNT(DISTINCT user_id) AS avg_rating
        FROM MovieRating
        WHERE YEAR(created_at) = 2020 AND MONTH(created_at) = 2
        GROUP BY movie_id
    ) tm
    LEFT JOIN Movies m ON tm.movie_id = m.movie_id
    ORDER BY tm.avg_rating DESC, m.title ASC
    LIMIT 1
) t2
;
