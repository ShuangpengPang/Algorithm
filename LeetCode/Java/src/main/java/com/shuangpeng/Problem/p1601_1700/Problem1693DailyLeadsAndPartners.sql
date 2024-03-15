# Write your MySQL query statement below（每天的领导和合伙人）

select date_id, make_name, count(distinct lead_id) as unique_leads, count(distinct partner_id) as unique_partners
from DailySales
group by date_id, make_name
;
