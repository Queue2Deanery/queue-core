create or replace function generate_completed_issues(last_date timestamp, insert_size int, queue_name text) returns table (completed_at timestamp, created_at timestamp, started_at timestamp, student_comment varchar(255), student_index varchar(255), issue_category_id bigint, queue_id bigint)
as $$ select
       last_date - (i.ints::text || ' minutes')::interval ,
       last_date - ((i.ints + 10 * ints + 10)::text || ' minutes')::interval ,
       last_date - ((i.ints + 7 * ints + 1)::text || ' minutes')::interval ,
       'Sprzedam opla',
       '289111',
       (select id from issue_category order by random() * i.ints limit 1),
       (select id from queue where name = queue_name)
from ( select
              generate_series * random() as ints
        from generate_series(1, insert_size)) i;
$$ language sql;

create or replace function generate_not_completed_issues(last_date timestamp, insert_size int, queue_name text) returns table (created_at timestamp, student_comment varchar(255), student_index varchar(255), issue_category_id bigint, queue_id bigint)
as $$ select
              last_date - ((i.ints + 10 * ints + 10)::text || ' minutes')::interval ,
              'Sprzedam opla',
              '289111',
              (select id from issue_category order by random() * i.ints limit 1),
              (select id from queue where name = queue_name)
      from ( select
                     generate_series * random() as ints
             from generate_series(1, insert_size)) i;
$$ language sql;
