use test_movie;

-- movie라는 테이블이 존재한다면 삭제
drop table if exists test_movie.movie;

-- 테이블 생성 
create table movie (
	ID 	int not null,
    Title varchar(40) not null,
    ReleaseDate date,
    RunningTime int not null
);

-- 칼럼 삽입
alter table movie add Director varchar(40) not null;
-- 칼럼 변경
alter table movie modify Director varchar(32);
-- 칼럼 삭제
alter table movie drop Director;
-- 데이터 삽입
insert into movie values(1000, '이터널스', '2021-11-05', 156);
insert into movie values(1001, '트랜스포터', '2002-10-02', 92);
insert into movie values(1002, '해리포터와 마법사의 돌', '2001-12-14', 152);
insert into movie values(1003, '해리포터와 비밀의 방', '2002-12-14', 162);
insert into movie values(1004, '기생충', '2019-05-30', 153);

-- ID가 1003인 영화의 Title을 '해리포터와 불의 잔'으로 변경
update movie set Title = '해리포터와 불의 잔' where ID=1003;

-- RunningTime이 100이하인 영화 삭제
delete from movie
where RunningTime <=100;

-- movie 테이블에 있는 모든 data 삭제
delete from movie;
