use ssafydb;

show index from ssafy;

create index ssafy_address_area_idx
on ssafy (address_area);

show index from ssafy;

create unique index ssafy_birth_year_idx
on ssafy (birth_year);

create unique index ssafy_user_name_idx
on ssafy (user_name);

show index from ssafy;

select *
from ssafy
where user_name = '강수찬';

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('GSC', '강수찬', 1996, '부산');

create unique index ssafy_user_name_birth_year_idx
on ssafy (user_name, birth_year);

drop index ssafy_user_name_idx on ssafy;

show index from ssafy;

insert into ssafy (user_id, user_name, birth_year, address_area)
values ('GSC', '강수찬', 1996, '부산');

select *
from ssafy
where user_name = '강수찬';