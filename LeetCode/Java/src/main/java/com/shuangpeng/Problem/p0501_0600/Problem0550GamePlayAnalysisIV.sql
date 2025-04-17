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
