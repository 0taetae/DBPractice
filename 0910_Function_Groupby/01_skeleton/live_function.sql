use ssafydb;

-- ------------------- 숫자 관련 함수 ----------------------
-- 절대값 : 5  0  5
select abs(-5), abs(0), abs(+5);


-- 올림(정수) : 13  13  -12  -12
select ceil(12.2), ceiling(12.2), ceil(-12.2), ceiling(-12.2);


-- 버림(정수) : 12  -13
select floor(12.2), floor(-12.2);


-- 반올림 : 1526  1526  1526.2  1526.16  1530  2000
select round(1526), round(1526.2), round(1526.16), round(1530);
-- 자릿수 까지 반올림


-- 버림 : 1526  1526.1  1526.15  1520  1000
select truncate(1526.159,0);


-- 제곱, 제곱근 : 8  8	4
select pow(2,3), power(2,3), sqrt(16);


-- 나머지 : 2  2
select 8%3, mod(8,3);


-- 최대, 최소값 : 9  3
select greatest(6,2,3,4,9,1,7), least(6,2,3,4,9,1,7);


-- 난수
select rand();


-- ------------------- 문자 관련 함수 ----------------------
-- 문자 :: > 아스키, 아스키 ::> 문자 : 48  65  97  '0'  'A'  'a'  'ABCD'
select ascii('0'), ascii('A'), ascii('a'), cast(char(48) as char);


-- 문자열 결합 : 100번 사원의 이름 Steven King
select concat(employee_id, '번 사원의 이름', first_name, ' ' , last_name)
from employees
where employee_id = 100;


-- 구분자를 이용한 문자열 결합 : 2024-09-10
select concat_ws('-', 2024, '09','10');


-- 문자열 대치(index) : hello ssafy !!!
select '!!!!! saffy', insert('!!!!! saffy', 1, 5, 'hello');
-- 1번째부터 5개를 바꾸기

-- 문자열 대치(문자열) : hello ssafy !!!
select '!!!!! saffy', replace('!!!!! saffy', '!!!!!', 'hello');


-- 찾을 문자열의 index 반환 : 7
select instr('hello ssafy !!!', 'ssafy');


-- 문자열 추출 : ssafy
select 'hello ssafy !!!', mid('hello ssafy !!!',7,5), substring('hello ssafy !!!', 7, 5);


-- hello ssafy !!!
select reverse('!!! yfass olleh');


-- hello ssafy !!!  hello ssafy !!!
select lower('HELLO SSAFY!!!'), lcase('HELLO SSAFY!!!');


-- HELLO SSAFY !!!  HELLO SSAFY !!!
select upper('hello ssafy!!!'), ucase('hello ssafy!!!');


-- hello  fy !!!



-- 3자리마다 콤마(,) & 소수점 2자리까지



-- 공백제거, 문자기준 trim



-- 길이(byte), 비트수, 문자의 개수



-- ------------------- 날짜 관련 함수 ----------------------
-- 현재 시간 2024-09-10 10:11:52  2024-09-10 10:11:52  2024-09-10 10:11:52



-- 현재시간 (실행시점) : 2024-09-10 10:11:52. 2024-09-10 10:11:57. 2024-09-10 10:11:52



-- 날짜 또는 시간만 반환 : 2024-09-10  2024-09-10  10:12:10  10:12:10



-- X일(or 시간) 전, 후


-- 날짜 세부 반환 함수 : 



-- 날짜 형식 지정 : 
-- 2024-09-03 14:04:44	
-- 2024 September 3 PM 2 04 44	
-- 24-09-03 14:04:44	
-- 24.09.03 Tuesday	
-- 14시04분44초



-- 시간, 날짜 차



-- 달의 마지막 날



-- ------------------- 논리 관련 함수 ----------------------
-- 논리함수 : 크다  작다  3  b  a


-- ------------------- 집계 함수 ----------------------
-- employees table에서 사원의 총수, 급여의 합, 급여의 평균, 최고급여, 최저급여


