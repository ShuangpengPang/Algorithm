SELECT t.request_at AS 'Day', ROUND((SUM(CASE WHEN t.status = 'completed' THEN 0 ELSE 1 END) / COUNT(t.id)), 2) AS 'Cancellation Rate'
FROM Trips t
         JOIN
     Users c ON t.client_id = c.users_id
         JOIN
     Users d ON t.driver_id = d.users_id
WHERE t.request_at BETWEEN '2013-10-01' AND '2013-10-03'
  AND c.banned = 'No'
  AND d.banned = 'No'
GROUP BY t.request_at
