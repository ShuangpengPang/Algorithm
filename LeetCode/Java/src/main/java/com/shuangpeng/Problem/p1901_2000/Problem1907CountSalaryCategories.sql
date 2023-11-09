# Write your MySQL query statement below（按分类统计薪水）

WITH total_salary AS (
    SELECT account_id, SUM(income) AS salary
    FROM Accounts
    GROUP BY account_id
)
SELECT 'Low Salary' AS category, (SELECT COUNT(account_id) FROM total_salary WHERE salary < 20000) AS accounts_count
UNION ALL
SELECT 'Average Salary' AS category, (SELECT COUNT(account_id) FROM total_salary WHERE salary >= 20000 && salary <= 50000) AS accounts_count
UNION ALL
SELECT 'High Salary' AS category, (SELECT COUNT(account_id) FROM total_salary WHERE salary > 50000) AS accounts_count
;
