-- 코드를 입력하세요
select f.flavor
from first_half f inner join july j on f.flavor = j.flavor
group by f.flavor
order by sum(f.total_order) + j.total_order desc
limit 3;