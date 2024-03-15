# Write your MySQL query statement below（可回收且低脂的产品）

select product_id
from Products
where recyclable = 'Y' and low_fats = 'y'
;
