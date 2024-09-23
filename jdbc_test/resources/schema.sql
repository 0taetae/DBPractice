use ssafydb;

create table ssafy_members
(
	user_id	varchar(16)	primary key,
    user_name varchar(20) not null,
    user_pwd varchar(16),
	email_id varchar(16),
    email_domain varchar(50),
    join_date timestamp default current_timestamp
);