# Write your MySQL query statement below（即时食物配送II）

WITH first_order AS (
    SELECT customer_id, MIN(order_date) AS order_date
    FROM Delivery
    GROUP BY customer_id
)
SELECT ROUND(IF(SUM(1) = 0, 0, SUM(IF(d.order_date = d.customer_pref_delivery_date, 1, 0)) / SUM(1) * 100), 2) AS immediate_percentage
FROM first_order f
LEFT JOIN Delivery d ON f.customer_id = d.customer_id AND f.order_date = d.order_date
;

SELECT ROUND(SUM(order_date = customer_pref_delivery_date) / COUNT(*) * 100, 2) AS immediate_percentage
FROM Delivery
WHERE (customer_id, order_date) IN (
    SELECT customer_id, MIN(order_date)
    FROM Delivery
    GROUP BY customer_id
)
;

WITH immediate_count AS (
    SELECT customer_id
    FROM Delivery
    GROUP BY customer_id
    HAVING MIN(order_date) = MIN(customer_pref_delivery_date)
)
SELECT ROUND(COUNT(customer_id) / (SELECT COUNT(DISTINCT customer_id) FROM Delivery) * 100, 2) AS immediate_percentage
FROM immediate_count
;
