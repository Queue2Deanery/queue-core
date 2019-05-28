create schema public;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table issue_category
(
	id bigint not null
		constraint issue_category_pkey
			primary key,
	name varchar(255) not null
		constraint uk_q4lbjyuppmojfj44frr7ra69s
			unique
);

alter table issue_category owner to postgres;

create table queue
(
	id bigint not null
		constraint queue_pkey
			primary key,
	name varchar(255) not null
		constraint uk_iyqalgwd607caww84l67ehw9
			unique,
	short_name varchar(255)
		constraint uk_h7m1qpvo9jbb1jvs0x0vt4t1v
			unique
);

alter table queue owner to postgres;

create table field_of_study
(
	id bigint not null
		constraint field_of_study_pkey
			primary key,
	name varchar(255) not null
		constraint uk_hgi2rosaa4gtrnjolj2yjncoh
			unique,
	short_name varchar(255)
		constraint uk_dbsykknrehngxdvk9etdwdd3d
			unique,
	queue_id bigint not null
		constraint fkaj88tymeh7p794u5hjspfao83
			references queue
);

alter table field_of_study owner to postgres;

create table issue
(
	id bigint not null
		constraint issue_pkey
			primary key,
	completed_at timestamp,
	created_at timestamp,
	started_at timestamp,
	student_comment varchar(255),
	student_index varchar(255) not null,
	issue_category_id bigint
		constraint fkaqkukfcwuxpf7g5nmrery2uyu
			references issue_category,
	queue_id bigint
		constraint fkngj35u0qu8tdie42icuw0w8co
			references queue
);

alter table issue owner to postgres;

