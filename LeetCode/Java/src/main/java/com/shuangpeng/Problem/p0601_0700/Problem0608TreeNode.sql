# 树节点

# Write your MySQL query statement below

SELECT
    id, 'Root' AS type
FROM
    Tree
WHERE
    p_id IS NULL
UNION
SELECT
    id, 'Leaf' AS type
FROM
    Tree
WHERE
    p_id IS NOT NULL
    AND id NOT IN (
        SELECT
            p_id
        FROM
            Tree
        WHERE
            p_id IS NOT NULL
    )
UNION
SELECT
    id, 'Inner' AS type
FROM
    Tree
WHERE
    p_id IS NOT NULL
    AND id IN (
        SELECT
            p_id
        FROM
            Tree
        WHERE
            p_id IS NOT NULL
    )
;

SELECT
    id,
    CASE
        WHEN p_id IS NULL
            THEN 'Root'
        WHEN id IN (SELECT p_id FROM Tree WHERE p_id IS NOT NULL)
            THEN 'Inner'
        ELSE 'Leaf'
    END AS type
FROM
    Tree
;
