-- allows scheduling jobs in db - requires additional config from postgres.Dockerfile and postgresql-queue.conf
create extension pg_cron;

create materialized view issue_processing_estimation_materialized_view as
select cross_all.queue_id, cross_all.issue_category_id, cross_all.queue_name, cross_all.issue_category_name, extract(epoch from coalesce(aggregated.estimate, interval '0'))::bigint as estimated_time_in_sec from (select q.name as queue_name, ic.name as issue_category_name, q.id as queue_id, ic.id as issue_category_id from queue q cross join issue_category ic) as cross_all
left join (
    select avg(i.completed_at - i.started_at) as estimate, ic.name as issue_category_name, q.name as queue_name from issue i
                                                                        join issue_category ic on i.issue_category_id = ic.id
                                                                        join queue q on i.queue_id = q.id
    group by q.id, ic.id
    ) as aggregated on (cross_all.queue_name = aggregated.queue_name and cross_all.issue_category_name = aggregated.issue_category_name);

-- calculate processing time for each issue at 6 am
select cron.schedule('0 6 * * *', $$ refresh materialized view issue_processing_estimation_materialized_view $$);

-- fix for hibernate default lack of handling MATERIALIZED VIEW, alternative solutions: https://stackoverflow.com/questions/27952794/mapping-entity-to-a-materialized-view-using-hibernate/27952997#27952997
create or replace view issue_processing_estimation_view as select mv.* from issue_processing_estimation_materialized_view mv;

create or replace view active_issue_view as
select i.id, i.created_at, i.started_at, i.student_index, i.queue_id, ic.name as issue_category_name, ipev.estimated_time_in_sec
from issue i
    join queue q
        on i.queue_id = q.id
               and i.created_at::date = current_date
               and (q.registration_start < i.created_at::time and q.registration_end > i.created_at::time)
    join issue_category ic on i.issue_category_id = ic.id
    join issue_processing_estimation_materialized_view ipev on (i.queue_id = ipev.queue_id and i.issue_category_id = ipev.issue_category_id)
    where i.completed_at is null;
