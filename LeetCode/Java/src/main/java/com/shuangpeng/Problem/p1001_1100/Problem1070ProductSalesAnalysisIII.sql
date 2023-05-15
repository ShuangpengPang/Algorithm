# 产品销售分析III
# Write your MySQL query statement below

SELECT DISTINCT product_id,
       year AS first_year,
       quantity, price
FROM Sales
WHERE (product_id, year) IN (
    SELECT product_id, MIN(year)
    FROM Sales
    GROUP BY product_id )
;

WITH Temp AS (
    SELECT product_id, MIN(year) AS year
    FROM Sales
    GROUP BY product_id
)
SELECT product_id, year AS first_year, quantity, price
FROM Sales s
WHERE EXISTS (SELECT 1 FROM Temp t WHERE t.product_id = s.product_id AND t.year = s.year)
;

SELECT product_id, year AS first_year, quantity, price
FROM (SELECT *, RANK() OVER (PARTITION BY product_id ORDER BY year ASC) AS row_id FROM Sales) t
WHERE t.row_id = 1
;
