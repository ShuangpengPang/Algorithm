# Write your MySQL query statement below（2016年的投资）

with valid_tiv_2015 as (
    select tiv_2015
    from Insurance
    group by tiv_2015
    having count(*) > 1
), valid_location as (
    select lat, lon
    from Insurance
    group by lat, lon
    having count(*) = 1
)
select round(sum(ifnull(tiv_2016, 0)), 2) as tiv_2016
from valid_tiv_2015 t
    left join valid_location l on true
    left join Insurance i on i.tiv_2015 = t.tiv_2015 and i.lat = l.lat and i.lon = l.lon;


with valid_info as (
    select tiv_2016,
        count(*) over (partition by tiv_2015) as cnt_2015,
        count(*) over (partition by lat, lon) as cnt_location
    from Insurance
)
select round(sum(ifnull(tiv_2016, 0)), 2) as tiv_2016
from valid_info
where cnt_2015 > 1
    and cnt_location = 1;
