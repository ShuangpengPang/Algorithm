# Write your MySQL query statement below（游戏玩法分析IV）

with player_info as (
    select count(*) as player_count
    from Activity a
    where exists (
        select 1
        from Activity b
        where b.player_id = a.player_id
            and b.event_date = date_sub(a.event_date, interval 1 day)
       )
       and not exists (
        select 1
        from Activity b
        where b.player_id = a.player_id
                    and b.event_date < date_sub(a.event_date, interval 1 day)
       )
)
select round((select player_count from player_info) / (select count(distinct player_id) from Activity), 2) as fraction;


# Write your MySQL query statement below
select round((select count(distinct A2.player_id)
from Activity A1 join Activity A2 on
A1.player_id = A2.player_id and date_sub(A1.event_date, interval 1 day) = A2.event_date
where (A2.player_id, A2.event_date) in (
    select player_id, min(event_date)
    from Activity
    group by player_id
)) / (select count(distinct player_id) from Activity), 2) as fraction


select IFNULL(round(count(distinct(Result.player_id)) / count(distinct(Activity.player_id)), 2), 0) as fraction
from (
  select Activity.player_id as player_id
  from (
    select player_id, DATE_ADD(MIN(event_date), INTERVAL 1 DAY) as second_date
    from Activity
    group by player_id
  ) as Expected, Activity
  where Activity.event_date = Expected.second_date and Activity.player_id = Expected.player_id
) as Result, Activity
