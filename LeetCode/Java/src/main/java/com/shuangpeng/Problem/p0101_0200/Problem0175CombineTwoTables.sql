package com.shuangpeng.Problem.p0101_0200;

/**
 * @Description: Problem0175CombineTwoTables（组合两个表）
 * @Date 2022/6/22 2:58 PM
 * @Version 1.0
 */

# Write your MySQL query statement below
SELECT p.firstName, p.lastName, a.city, a.state
FROM Person p
    LEFT JOIN Address a ON a.personId = p.personId

