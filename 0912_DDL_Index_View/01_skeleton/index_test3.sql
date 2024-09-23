alter table ssafy drop primary key;

select *
from ssafy;

alter table ssafy
add constraint ssafy_user_name_pk primary key (user_name);

show index from ssafy;

select *
from ssafy;

-- alter table ssafy drop primary key;

alter table ssafy modify column user_id char(4) primary key;

show index from ssafy;

select *
from ssafy;




