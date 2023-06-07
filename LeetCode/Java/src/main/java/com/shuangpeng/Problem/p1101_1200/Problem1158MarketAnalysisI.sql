# Write your MySQL query statement below（市场分析I）

SELECT u.user_id AS buyer_id,
    u.join_date,
    COUNT(o.order_id) AS orders_in_2019
FROM Users u
LEFT JOIN Orders o ON o.buyer_id = u.user_id AND YEAR(o.order_date) = 2019
GROUP BY u.user_id, u.join_date
;

SELECT u.user_id AS buyer_id, u.join_date, IFNULL(o.orders_in_2019, 0) AS orders_in_2019
FROM Users u
LEFT JOIN (
    SELECT buyer_id, COUNT(order_id) AS orders_in_2019
    FROM Orders
    WHERE order_date BETWEEN '2019-01-01' AND '2019-12-31'
    GROUP BY buyer_id
) o ON u.user_id = o.buyer_id
;
