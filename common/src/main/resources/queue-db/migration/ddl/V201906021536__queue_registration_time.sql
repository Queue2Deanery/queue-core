alter table queue
add column registration_start time without time zone not null default make_time(8, 0, 0),
add column registration_end time without time zone not null default make_time(16, 0, 0);
