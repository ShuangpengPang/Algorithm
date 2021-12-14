SELECT *
FROM Stadium s
WHERE s.people >= 100
  AND ((SELECT COUNT(1)
        FROM Stadium r
        WHERE r.people >= 100
          AND (r.id = s.id OR r.id = s.id - 1 OR r.id = s.id + 1 OR
               (r.id = s.id + 2 AND EXISTS(SELECT 1 FROM Stadium t WHERE t.people >= 100 AND t.id = s.id + 1)) OR
               (r.id = s.id - 2 AND EXISTS(SELECT 1 FROM Stadium t WHERE t.people >= 100 AND t.id = s.id - 1))
            )
       ) >= 3)
ORDER BY s.visit_date ASC
;


SELECT tt.id, tt.visit_date, tt.people
FROM (SELECT CASE
                 WHEN @group_id = 0 THEN @group_id := 1
                 WHEN t.id - @pre_id > 1 THEN @group_id := @group_id + 1
                 ELSE @group_id
                 END AS group_id,
             CASE
                 WHEN @cnt = 0 THEN @cnt := 1
                 WHEN t.id - @pre_id <= 1 THEN @cnt := @cnt + 1
                 ELSE @cnt := 1
                 END AS cnt,
             @pre_id := t.id,
       t.id, t.visit_date, t.people
      FROM (SELECT @pre_id := -5, @group_id := 0, @cnt := 0, id, visit_date, people
          FROM Stadium
          WHERE people >= 100
          ORDER BY id ASC
          ) t
     ) tt
WHERE EXISTS(SELECT 1
             FROM (SELECT CASE
                              WHEN @group_id = 0 THEN @group_id := 1
                              WHEN t.id - @pre_id > 1 THEN @group_id := @group_id + 1
                              ELSE @group_id
                              END AS group_id,
                          CASE
                              WHEN @cnt = 0 THEN @cnt := 1
                              WHEN t.id - @pre_id <= 1 THEN @cnt := @cnt + 1
                              ELSE @cnt := 1
                              END AS cnt,
                          @pre_id := t.id, t.id, t.visit_date, t.people
                   FROM (SELECT @pre_id := -5, @group_id := 0, @cnt := 0, id, visit_date, people
                       FROM Stadium
                       WHERE people >= 100
                       ORDER BY id ASC
                       ) t
                  ) t1
             WHERE tt.group_id = t1.group_id
               AND t1.cnt >= 3
          )
ORDER BY tt.visit_date ASC
;


SELECT id, visit_date, people
FROM (SELECT id, visit_date, people, COUNT(1) OVER (PARTITION BY rank_1) AS rank_2
      FROM (SELECT id, visit_date, people, id - DENSE_RANK() OVER (ORDER BY id) AS rank_1
            FROM Stadium
            WHERE people >= 100
           ) t
     ) tt
WHERE tt.rank_2 >= 3
;

