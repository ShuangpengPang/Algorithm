# 买下所有产品的客户
# Write your MySQL query statement below

WITH Info AS (
    SELECT customer_id, COUNT(DISTINCT product_key) AS count
    FROM Customer
    GROUP BY customer_id
), Count AS (
    SELECT COUNT(1) AS count
    FROM Product
)
SELECT t.customer_id
FROM Info t
WHERE t.count = (SELECT count FROM Count)
;

SELECT customer_id
FROM Customer
GROUP BY customer_id
HAVING COUNT(DISTINCT product_key) = (SELECT COUNT(product_key) FROM Product)
;
