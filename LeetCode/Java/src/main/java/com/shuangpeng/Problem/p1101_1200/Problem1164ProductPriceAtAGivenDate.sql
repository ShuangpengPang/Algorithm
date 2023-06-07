# Write your MySQL query statement below（指定日期的产品价格）

WITH ids AS (
    SELECT DISTINCT product_id
    FROM Products
), prices AS (
    SELECT product_id, new_price, MAX(change_date) AS change_date
    FROM Products
    WHERE change_date <= '2019-08-16'
    GROUP BY product_id
), tmp AS (
    SELECT t.product_id, t.new_price, t.change_date
    FROM prices p
    LEFT JOIN Products t ON t.product_id = p.product_id AND t.change_date = p.change_date
)
SELECT p.product_id, IFNULL(t.new_price, 10) AS price
FROM ids p
LEFT JOIN tmp t ON t.product_id = p.product_id
;

WITH p1 AS (
    SELECT DISTINCT product_id
    FROM Products
), p2 AS (
    SELECT product_id, new_price, change_date
    FROM Products
    WHERE (product_id, change_date) IN (
        SELECT product_id, MAX(change_date) AS change_date
        FROM Products
        WHERE change_date <= '2019-08-16'
        GROUP BY product_id
    )
)
SELECT p1.product_id, IFNULL(p2.new_price, 10) AS price
FROM p1
LEFT JOIN p2 ON p1.product_id = p2.product_id
;

SELECT product_id, price
FROM (
    SELECT product_id, IF(change_date IS NULL, 10, price) AS price,
        ROW_NUMBER() OVER(PARTITION BY product_id ORDER BY change_date DESC) row_index
    FROM (
        SELECT product_id, new_price AS price, IF(change_date <= '2019-08-16', change_date, NULL) AS change_date
        FROM Products
    ) t1
) t2
WHERE row_index = 1
;
