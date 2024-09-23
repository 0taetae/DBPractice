use ssafydb;

select *
from movie;

select *
from cinema;

-- movie 테이블과 cinema 테이블을 full outer 조인하면 몇개의 행이 조회되는지 출력
select count(*)
from movie join cinema;

-- 모든 영화가 어떤 cinema에서 상영중인지 알 수 있도록 영화 정보와 시네마 정보를 모두 출력
select *
from movie m inner join cinema c
on m.cinemacode = c.cinemacode;

-- 서울에 있는 시네마에서 상영하는 영화들을 조회
select *
from movie m inner join cinema c
on m.cinemacode = c.cinemacode
where c.location = '서울';

-- 광주에 있는 시네마에서 상영하는 영화가 몇개인지 조회
select count('cinemacode') as 상영중, c.location
from movie m inner join cinema c
on m.cinemacode = c.cinemacode
where c.location = '광주';

-- cinema 이름으로 그룹핑하여 각 시네마 마다 몇 개의 영화가 상영중인지 조회
select cinemaName, count(*) as 상영중
from movie m join cinema c
on m.cinemacode = c.cinemacode
group by c.cinemaName;

-- cinema 이름으로 그룹핑하여 각 시네마 마다 몇 개의 영화가 상영중인지 조회 
select cinemaName, count(m.title) as '상영중'
from movie m right outer join cinema c
using (cinemacode)
group by c.cinemaName;

-- cinema 지역으로 그룹핑 하는데 상영하는 영화의 개수가 1개인 시네마 지역들을 조회
select count('cinemacode') as 상영중, location as 지역
from movie m join cinema c
on m.cinemacode = c.cinemacode
group by c.location
having count('cinemacode')=1;

-- 영화 이름이 '이터널스'인 영화의 상영 시간 이상인 영화 이름과 상영 시간 출력
select title, runningTime
from movie m join cinema c
on m.cinemacode = c.cinemacode
where m.runningTime >=(
						select runningTime
						from movie m 
						where m.title ='이터널스'
						);

-- 상영 시간이 156분 이상인 영화들을 상영중인 cinema 이름을 출력
select cinemaName
from movie m join cinema c
on m.cinemacode = c.cinemacode
where m.runningTime >= 156;

-- 상영 시간이 156분 이상인 영화들 중 제목에 '해리포터'라는 단어를 포함하는 영화 제목과 상영 시간 출력
select m.title, m.runningTime
from movie m join cinema c
on m.cinemacode = c.cinemacode
where m.runningTime >= 156
and m.title like '%해리포터%';