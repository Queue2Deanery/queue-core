comment on schema public is 'standard public schema';

alter schema public owner to postgres;

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table issue_category
(
	id bigserial not null
		constraint issue_category_pkey
			primary key,
	name varchar(255) not null
		constraint uk_issue_category_name
			unique
);

alter table issue_category owner to postgres;

create table queue
(
	id bigserial not null
		constraint queue_pkey
			primary key,
	name varchar(255) not null
		constraint uk_queue_name
			unique,
	short_name varchar(255)
		constraint uk_queue_short_name
			unique
);

alter table queue owner to postgres;

create table field_of_study
(
	id bigserial  not null
		constraint field_of_study_pkey
			primary key,
	faculty varchar(255) not null,
	name varchar(255) not null
		constraint uk_field_of_study_name
			unique,
	short_name varchar(255)
		constraint uk_field_of_study_short_name
			unique,
	queue_id bigint not null
		constraint fk_field_of_study_to_queue
			references queue,
	access_role varchar(255) not null
);

alter table field_of_study owner to postgres;

create table issue
(
	id bigserial not null
		constraint issue_pkey
			primary key,
	completed_at timestamp,
	created_at timestamp not null,
	started_at timestamp,
	student_comment varchar(255),
	student_index varchar(255) not null,
	issue_category_id bigint
		constraint fk_issue_to_issue_category
			references issue_category,
	queue_id bigint
		constraint fk_issue_to_queue
			references queue
);

alter table issue owner to postgres;
