use ssafydb;

-- subquery
-- 사번이 100인 사원의 부서이름
-- join
-- subquery

-- 부서가 ‘seattle’(대소문자 구분X)에 있는 부서의 부서 번호, 부서 이름.
-- 단일행
-- 전체 사원의 평균 급여보다 많이 받는 사원의 사번, 이름, 급여.
-- 급여순 정렬
-- ‘adam’과 같은 부서에 근무하는 사원의 사번, 이름, 부서번호.
-- 근무 도시가 ‘seattle’(대소문자 구분X)인 사원의 사번, 이름.
-- 다중행 (in)
select employee_id, first_name
from employees
where department_id in (
						select department_id
                        from departments
						where location_id=(
											select location_id
                                            from locations
                                            where city='seatle'
											)
                        );
-- 모든 사원 중 적어도(최소급여자보다) 30번 부서에서 근무하는 사원의 급여보다 많이 받는 사원의 사번, 이름, 급여, 부서번호
-- 다중행 (any)
-- 30번 부서에서 근무하는 모든(최대급여자보다) 사원들보다 급여를 많이 받는 사원의 사번, 이름, 급여, 부서번호.
-- 다중행 (all)
-- 다중열
-- 커미션을 받는 사원중 매니저 사번이 148인 사원의 급여와 부서번호가 일치하는 사원의 사번, 이름
-- 인라인뷰(Inline View)
-- 모든 사원의 평균 급여보다 적게 받는 사원들과 같은 부서에서 근무하는 사원의 사번, 이름, 급여, 부서번호
-- TopN 질의
-- 모든 사원의 사번, 이름, 급여를 출력.(단 아래의 조건 참조)
--   1. 사원 정보를 급여순으로 정렬.
--   2. 한 페이지당 5명이 출력.
--   3. 현재페이지가 3페이지라고 가정. (급여 순 11등 ~ 15등까지 출력)
set
    @pageno = 3;

select b.rn, b.employee_id, b.first_name, b.salary
from (
        select @rownum := @rownum + 1 as rn, a.*
        from (
                select employee_id, first_name, salary
                from employees
                order by salary desc
            ) a, (
                select @rownum := 0
            ) tmp
    ) b
where
    b.rn > (@pageno * 5 - 5)
    and b.rn <= (@pageno * 5);

-- MySQL은 limit로 해결.

-- scalar subquery
-- 직급 아이디가 IT_PROG인 사원의 사번, 이름, 직급아이디, 부서이름

-- 60번 부서에 근무하는 사원의 사번, 이름, 급여, 부서번호, 60번부서의 평균급여

-- 부서번호가 50인 부서의 총급여, 60인 부서의 평균급여, 90인 부서의 최고급여, 90인 부서의 최저급여

-- 서브쿼리를 이용한 create.
-- employees table을 emp_copy라는 이름으로 복사(컬럼 이름 동일).

-- employees table의 구조만 emp_blank라는 이름으로 생성(컬럼 이름 동일).

-- 50번 부서의 사번(eid), 이름(name), 급여(sal), 부서번호(did)만 emp50이라는 이름으로 생성.

-- 서브쿼리를 이용한 insert.
-- employees table에서 부서번호가 80인 사원의 모든 정보를 emp_blank에 insert

-- 서브쿼리를 이용한 update.
-- employees table의 모든 사원의 평균 급여보다 적게 받는 emp50 table의 사원의 급여를 500 인상.

-- 서브쿼리를 이용한 delete.
-- employees table의 모든 사원의 평균 급여보다 적게 받는 emp50 table의 사원은 퇴사.