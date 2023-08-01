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

WITH user_rating AS (
    SELECT COUNT(user_id) AS user_count, user_id
    FROM MovieRating
    GROUP BY user_id
), max_user_rating AS (
    SELECT name
    FROM user_rating t
    LEFT JOIN Users u ON t.user_id = u.user_id
    WHERE t.user_count = (SELECT MAX(user_count) FROM user_rating)
), movie_rating AS (
    SELECT SUM(rating) / COUNT(DISTINCT user_id) AS avg_rating, movie_id
    FROM MovieRating
    WHERE DATE_FORMAT(created_at, "%Y-%m") = '2020-02'
    GROUP BY movie_id
), max_movie_rating AS (
    SELECT m.title
    FROM movie_rating mt
    LEFT JOIN Movies m ON mt.movie_id = m.movie_id
    WHERE mt.avg_rating = (SELECT MAX(avg_rating) FROM movie_rating)
)
SELECT MIN(name) AS results
FROM max_user_rating
UNION ALL
SELECT MIN(title) AS results
FROM max_movie_rating
;

SELECT results
FROM (
  SELECT name AS results, RANK() OVER(ORDER BY COUNT(*) DESC, name) AS RANKING
  FROM Users
  INNER JOIN MovieRating USING(user_id)
  GROUP BY user_id
  UNION ALL
  SELECT title AS results, RANK() OVER(ORDER BY AVG(rating) DESC, title) AS RANKING
  FROM MovieRating
  INNER JOIN Movies USING(movie_id)
  WHERE DATE_FORMAT(created_at, '%Y-%m') = '2020-02'
  GROUP BY movie_id
) T
WHERE T.RANKING = 1
;