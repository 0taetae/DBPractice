use ssafydb;

drop table if exists ssafy;

create table ssafy
(
	user_id char(4) not null, -- primary key,
    user_name varchar(10) not null,
    birth_year int not null,
    address_area char(2) not null
);

-- show index from ssafy;

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('KDW', '김동웅', 1997, '서울');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('KPR', '김파랑', 1994, '제주');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('YKB', '윤기봉', 1992, '경기');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('KSH', '김세형', 1993, '서울');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('JSB', '장서범', 1994, '충남');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('CMH', '최명화', 1992, '광주');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('SWJ', '신우존', 1991, '충북');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('HSY', '황서연', 1993, '서울');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('KSC', '강수찬', 1991, '강원');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('CYH', '최용현', 1994, '경남');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('JSH', '전상후', 1992, '부산');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('SHD', '송히도', 1991, '경북');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('JJW', '전재유', 1993, '충남');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('LCH', '이찬혁', 1993, '충북');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('HSW', '한성우', 1994, '서울');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('KKJ', '김기졍', 1992, '전남');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('HYR', '허예름', 1995, '경기');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('JSM', '김수민', 1993, '전북');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('JJY', '정지온', 1995, '제주');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('KJE', '김지원', 1994, '서울');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('CJY', '최주영', 1992, '경북');

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('OCY', '오채연', 1994, '서울');

commit;

select *
from ssafy;

-- alter table ssafy modify column user_id char(4) primary key;
