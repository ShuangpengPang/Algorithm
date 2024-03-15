# Write your MySQL query statement below（无效的推文）

select tweet_id
from Tweets
where length(content) > 15
;

select tweet_id
from Tweets
where char_length(content) > 15
;
