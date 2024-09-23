-- movie 테이블 내의 전체 data 조회
select *
from movie;

-- movie 테이블 내의 모든 title을 조회
select title
from movie;

-- 제목이 '이터널스'인 영화 정보를 조회하시오.
select *
from movie
where title='이터널스';

-- 제목이 '해리'로 시작하는 영화 정보 조회
select *
from movie
where title like '해리%';

-- 제목이 '포터' 단어가 포함되는 영화 정보 조회 
select *
from movie
where title like '%포터%';

-- ID가 1000 또는 1004인 영화의 제목을 조회
select title
from movie
where ID = 1000
or ID = 1004;

-- 'touppercase'를 모두 대문자로 바꿔서 출력
select upper('touppercase') as '대문자';

-- '해리포터와' '마법사의 돌' 두 단어를 연결해서 출력하시오.
select concat('해리포터와','마법사의 돌') as '연결';

-- 개봉일이 2018년 1월 1일 이후인 영화들의 제목의 맨 앞 두글자만 조회
select mid(title, 1, 2) as '두글자'
from movie
where ReleaseDate >= '2018-01-01';

-- 제목에 '해리'가 포함된 영화들의 제목의 '해리포터'부분을 '말포이'로 바꾼 후 조회
select replace(title, '해리포터', '말포이') as '영화 제목'
from movie
where title like '%해리%';

-- 원주율을 소수점 둘째 자리까지 반올림
select round(PI(),2);
