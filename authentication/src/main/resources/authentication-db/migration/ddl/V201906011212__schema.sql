comment on schema public is 'standard public schema';

alter schema public owner to postgres;

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table revoked_jwt_token
(
	id bigserial not null
		constraint revoked_jwt_token_pkey
			primary key,
	created_at timestamp not null,
	ip_address varchar(255) not null,
	jwt_token varchar(255) not null
		constraint uk_155ol0mc050v1burefg2ft70e
			unique,
	user_index varchar(255) not null
);

alter table revoked_jwt_token owner to postgres;

create table token_generation_audit
(
    id bigserial not null constraint token_generation_audit_pkey primary key,
	ip_address varchar(255) not null,
	user_index varchar(255) not null,
	created_at timestamp not null
)

