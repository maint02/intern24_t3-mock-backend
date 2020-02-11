with search_query as (
   _sql_
), count_query as (
    select count(1) totalRecord from search_query
), paging_query as (
select * from (
    select rownum noNum, a.*
    from search_query a
    where rownum < ((:p_page_number * :p_page_size) + 1 )
)
where noNum >= (((:p_page_number - 1) * :p_page_size) + 1)
)
select c.totalRecord, p.*
from paging_query p, count_query c