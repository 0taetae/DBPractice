-- mysql 지원 character set 확인.
show character set;

-- 'ssafydb' schema의 character set 확인.
SELECT default_character_set_name, DEFAULT_COLLATION_NAME FROM information_schema.SCHEMATA 
WHERE schema_name = "ssafydb";

-- 'ssafydb' schema에 있는 employees table의 character set 확인.
SELECT CCSA.character_set_name FROM information_schema.`TABLES` T,
       information_schema.`COLLATION_CHARACTER_SET_APPLICABILITY` CCSA
WHERE CCSA.collation_name = T.table_collation
AND T.table_schema = "ssafydb"
AND T.table_name = "employees";

create database dbtest
default character set utf8mb3 collate utf8mb3_general_ci;

create database dbtest
default character set utf8mb4 collate utf8mb4_general_ci;

alter database dbtest
default character set utf8mb4 collate utf8mb4_general_ci;

drop database dbtest;

use ssafydb;

-- 회원 정보 table 생성.
-- table name : ssafy_member
-- column
-- idx			int			auto_increments		PK
-- userid		varchar(16)	not null unique
-- username		varchar(20)
-- userpwd		varchar(16)
-- emailid		varchar(20)
-- emaildomain	varchar(50) default 'ssafy.com'
-- joindate		timestamp	default	current_timestamp

use ssafydb;

create table ssafy_member (
	idx			int 		not null auto_increment,
	userid		varchar(16)	not null unique,
	username	varchar(20),
	userpwd		varchar(16),
	emailid		varchar(20),
	emaildomain	varchar(50) default 'ssafy.com',
	joindate	timestamp	not null default current_timestamp,
	constraint ssafy_member_idx_pk primary key (idx)
) ENGINE=InnoDB default CHARSET=utf8mb4;

-- 회원 정보 등록
-- 'kimssafy', '김싸피', '1234', 'kimssafy', 'ssafy.com', 등록시간
insert into ssafy_member (userid, username, userpwd, emailid, emaildomain, joindate)
values ('kimssafy', '김싸피', '1234', 'kimssafy', 'ssafy.com', now());

-- '최싸피', 'choissafy', 'abcd'
insert into ssafy_member (username, userid , userpwd)
values ('최싸피', 'choissafy', 'abcd');

-- '이싸피', 'leessafy', '1234'
-- '홍싸피', 'hongssafy', '9876'
insert into ssafy_member (username, userid , userpwd)
values 
	('이싸피', 'leessafy', '1234'), 
	('홍싸피', 'hongssafy', '9876');

-- userid가 kimssafy인 회원의 비번을 9876, 이메일 도메인을 samsung.com으로 변경.
update ssafy_member
set userpwd = 9876, emaildomain = 'samsung.com'
where userid = 'kimssafy';

-- userid가 kimssafy 회원 탈퇴
delete from ssafy_member
where userid = 'kimssafy';