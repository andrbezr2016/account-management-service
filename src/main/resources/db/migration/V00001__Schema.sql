CREATE TABLE accounts (
	id bigserial PRIMARY KEY,
	bank_id bigint,
	last_name varchar,
	first_name varchar,
	middle_name varchar,
	birth_date date,
	birth_place varchar,
	passport_number varchar,
	phone_number varchar,
	email varchar,
	registration_address varchar,
	residence_address varchar
);