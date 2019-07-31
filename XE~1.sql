desc employee;

select eno, ename from employee;

select eno, ename, salary from employee;

select salary + 300 from employee;
select salary - 300 from employee;
select salary * 12 from employee;
select salary / 4 from employee;
select salary, salary + 300, salary / 4 from employee;

select ename 직원이름, salary, job, dno, nvl(commission, 0), 
       salary*12, salary*12+ nvl(commission, 0) as "연 봉"
from employee;

select distinct dno, ename from employee;
select distinct dno from employee;

desc dual;
select * from dual;
select 1+1, 2+2, 3+3 from dual;

select eno, ename, salary from employee
where salary <> 1500;

select * from employee
where dno = 10;

select salary from employee
 where ename = 'SCOTT';
 
select * from employee
 where hiredate <= '1981/01/01';

select * from employee
 where dno = 10 and job = 'MANAGER';

select * from employee
 where dno = 10 or job = 'MANAGER';
 
 select * from employee
 where not dno = 10;
 
 select * from employee
  where salary between 1100 and 1300;
 select * from employee
  where salary >= 1100 and salary <= 1500;
  
select * from employee
 where commission in(300, 500, 1400);
select * from employee
 where commission = 300 or commission = 500 or commission = 1400;
 
select ename, salary, salary+300 from employee;

select ename, salary, salary*12+100 from employee
order by salary;

select ename, salary from employee
 where salary > 2000 
 order by salary desc;

select ename, salary from employee
 where eno = 7788;
 
select ename, salary from employee
 where salary not between 2000 and 3000;
 
select ename, job, hiredate from employee
 where hiredate between '81/02/20' and '81/05/01';
 
select ename, dno from employee
 where dno in(20,30) order by ename desc;
 
select ename, salary, dno from employee
where salary between 2000 and 3000 and dno in(20, 30)
order by ename;
  
select ename, hiredate from employee
 where hiredate like '81%';
 
select ename, job from employee
 where manager is null;
 
select ename, salary, commission from employee
 where commission is not null
 order by salary desc, commission desc;
 
select ename from employee
 where ename like '__R%';
 
select ename from employee
 where ename like '%A%' and ename like '%E%';
 
select ename, job, salary from employee
 where job in('CLERK', 'SALESMAN') and salary not in(1600, 950, 1300);
 
select ename, salary, commission from employee
where nvl(commission, 0) >= 500;

select 'Oracle Mania',
        Upper('Oracle Mania'),
        Lower('Oracle Mania'),
        InitCap('Oracle Mania')
from dual;

select length('OracleMania'), length('오라클매니아') from dual;
select lengthB('OracleMania'), lengthB('오라클매니아') from dual; 

select 'Oracle', 'manina', cancat('Oracle','mania') from dual;
select substr('Oracle maina', 4, 3) from dual;

select INSTR('Oracle mania', 'a', 1, 3) from dual;

select 'Oracle mania', trim('o' from 'oracle mania') from dual;
select '[' || trim('   aaa     ') || ']' from dual;

select sysdate, NEXT_DAY(sysdate, '월요일') from dual;

select ename, dno, decode(dno, 10, 'ACCOUNTING',
                                20, 'RESEARCH',
                                30, 'SALES',
                                40, 'OPERATINONS',
                                'DEFAULT') as DNAME
                                from employee;
                            

select substr(hiredate, 1, 2) 년도, substr(hiredate, 4, 2) 달 from employee;

select * from employee
where substr(hiredate, 4, 2) = 04;

select eno, ename from employee
where mod(eno, 2) = 0;

select to_char(hiredate, 'YY/MON/DD DY') from employee;

select trunc(sysdate - to_date('19/01/01')) from dual;

select ename, nvl(manager, 0) from employee;


select ename, job, salary , 
salary + decode(job, 'ANALYST', 200, 'SALESMAN', 180, 'MANAGER', 150, 'CLERK', 100, 0)
as 인상급여 from employee;


select dno, job, round(avg(salary)) as 평균 from employee
group by dno, job
order by 평균 desc;

select sum(commission) as "커미션 총액" from employee;

select commission from employee;
select count(*) as from employee;

select count(distinct manager) from employee;

select dno, job, count(*), sum(salary) from employee
group by dno, job
order by dno, job;

select dno, max(salary) 
from employee
group by dno;


select max(salary) Maximun, min(salary) Minimum, sum(salary) Sum, round(avg(salary)) Average from employee;

select job, max(salary) Maximun, min(salary) Minimum, sum(salary) Sum, round(avg(salary)) Average from employee
group by job;

select job, count(*) from employee
group by job;

select count(distinct manager) from employee
where manager is not null;

select max(salary) - min (salary) DIFFRENCE from employee;

select job, min(salary)  from employee
where manager is not null
group by job 
having min(salary) > 2000
order by min(salary) desc;

select decode(dno, 10, 'ACCOUNTING', 20, 'RESEARCH', 30, 'SALES', 40, 'OPERATIONS') as dno, count(*) as "Number of People", round(avg(salary),2) as Salary from employee
group by dno;

SELECT * from department;

select decode(dno, 10, 'ACCOUNTING', 20, 'RESEARCH', 30, 'SALES', 40, 'OPERATIONS') 
as dname, decode(dno, 10, 'NEW YORK', 20, 'DALLAS', 30, 'CHICAGO', 40, 'BOSTON') as Location, count(*) as "Number of People", round(avg(salary)) as Salary from employee
group by dno;


(select job, sum(salary) from employee where dno = 10 group by job, dno);

select job, dno, 
(select job, sum(salary) from employee where dno = 10 group by job, sum(salary)) as 부서1
,sum(salary) 총액 from employee
group by job,dno, sum(salary);

select job, dno, (select job, sum(salary) from employee where dno = 10 group by job, sum(salary)) as 부서1
,sum(salary) 총액 from employee
group by job, dno
order by job;




select job, dno, nvl(case when dno = 10 then sum(salary) end,0) as 부서10
, nvl(case when dno = 20 then sum(salary) end,0) 부서20
, nvl(case when dno = 30 then sum(salary) end,0) 부서30
,sum(salary) 총액 from employee
group by job, dno
order by job, dno;


(select sum(salary) from employee
where dno = 10);

select dname, d.dno, ename
from employee e join department d on e.dno = d.dno;

select ename, salary, grade
from employee, salgrade
where salary between losal and hisal;

select e.ename, d.dname, e.sala
from employee e, department d, salgrade s;

select a.eno, a.ename, b.ename manager
from employee a, (select eno, ename from employee) b
where b.eno(+) = a.manager;

select a.eno, a.ename, b.ename manager
from employee a right outer join (select eno, ename from employee) b
on b.eno = a.manager;

select e.ename, e.eno, d.dname
from employee e, department d
where 
--e.dno = d.dno and
ename = 'SCOTT';

select e.ename, d.dname, d.loc
from employee e join department d
on e.dno = d.dno;

select distinct dno, e.job, d.loc
from employee e join department d
using (dno)
where dno = 20;

select ename, dname, loc
from employee natural join department
where commission is not null;

select e.ename, d.dname, d.dno
from employee e, department d
where e.dno = d.dno 
and e.ename like '%A%';

select ename, job, dno, dname, loc
from employee natural join department
where loc = 'NEW YORK';

select a.ename as employee, a.eno as emp#, b.ename as Manager, a.manager as mgr# 
from employee a, employee b
where a.manager = b.eno;

select a.ename as employee, a.eno as emp#, b.ename as Manager, a.manager as mgr# 
from employee a, employee b
where a.manager = b.eno(+)
order by a.eno desc;

select a.ename as 이름, a.dno as 부서, b.ename as 동료
from employee a, employee b
where a.dno = b.dno
and a.ename = 'FORD'
and b.ename != 'FORD';

select a.ename, a.hiredate
from employee a, employee b
where b.ename = 'WARD'
and a.hiredate > b.hiredate;

select a.ename as ENAME, a.hiredate as HIREDATE, b.ename as ENAME, b.hiredate as HIREDATE  
from employee a, employee b
where a.manager = b.eno
and a.hiredate < b.hiredate;


drop table ZIPCODE;
create table ZIPCODE (
	ZIPCODE varchar2(5) not null,
	SIDOK varchar2(200),
	SIDOE varchar2(200),
	SIGUNGUK varchar2(200),
	SIGUNGUE varchar2(200),
	UPMYENK varchar2(200),
	UPMYENE varchar2(200),
	DORO_CODE varchar2(12) not null,
	DOROK varchar2(200),
	DOROE varchar2(200),
	JIHA_FLAG varchar2(1),
	BD_NUM1 numeric(5),
BD_NUM2 numeric(5),
	BD_MNG_NUM varchar2(200),
	MULTI_NAME varchar2(200),
	BD_NAME varchar2(300),
	LAW_CODE varchar2(200),
	LAW_NAME varchar2(200),
	RI_NAME varchar2(200),
	ADMIN_DONG varchar2(200),
	SAN_FLAG varchar2(1),
	JIBEON1 numeric(4),
	UMD_SEQ varchar2(2) not null,
	JIBEON2 numeric(4),
	OLD_ZIP varchar2(6),
	OLD_SEQ varchar2(3)
);

select distinct sigunguk from zipcode
where SIDOK = '서울특별시';

select bd_name from zipcode
where SIDOK = '서울특별시' and
bd_name like '%중학교%';

select DISTINCT sigunguk, law_name
from zipcode
where sidok = '서울특별시'
order by sigunguk;

select sigunguk, law_name ,count(*)
from zipcode
where 
bd_name like '%중학교%'
and sidok = '서울특별시'
group by law_name, sigunguk
order by sigunguk;

select eno, ename
from employee
where salary in
(select min(salary)
from employee
group by dno);

select eno, ename, job, salary
from employee
where salary < any 
(select salary 
from employee
where job = 'SALESMAN')
and job != 'SALESMAN';



select eno, ename, job, salary
from employee
where salary < all 
(select salary 
from employee
where job = 'SALESMAN')
and job != 'SALESMAN';


select 
(select ename 
from employee), ename
from employee;


select sigunguk
from zipcode
where sidok = '서울특별시'
group by sigunguk
order by sigunguk;


select ename, job
from employee
where job = (
select job 
from employee
where eno = 7788);

select ename, job
from employee
where salary > (
select salary 
from employee
where eno = 7499);

select ename, job, salary 
from employee
where salary = (
select min(salary)
from employee);

select job, avg(salary)
from employee
group by job
having avg(salary) =
(select min(avg(salary))
from employee
group by job);

select ename, salary, dno
from employee
where (dno, salary) in (
select dno, min(salary)
from employee
group by dno);

select eno, ename, job, salary
from employee
where salary < all (
select salary 
from employee 
where job = 'ANALYST');

select ename, eno
from employee
where eno in (
select DISTINCT manager 
from employee
where manager is not null);

select ename, hiredate
from employee
where dno = (
select dno 
from employee 
where ename = 'BLAKE')
and ename <> 'BLAKE';

select eno, ename
from employee
where salary > (
select avg(salary)
from employee)
order by salary;

select eno, ename 
from employee
where dno in (select DISTINCT dno 
from employee
where ename like '%K%');



select ename, eno, job
from employee
where dno = (
select dno
from department
where loc = 'DALLAS'
);


select ename, salary
from employee
where manager = (
select eno 
from employee
where ename = 'KING');

select dno, ename, job
from employee
where dno = (select dno
from department
where dname = 'RESEARCH');


select eno, ename, salary
from employee
where salary > (
select avg(salary)
from employee) 
and dno in (
select DISTINCT dno 
from employee
where ename like '%K%');


select job
from employee
group by job
having avg(salary) = (select min(avg(salary))
from employee
group by job);


select ename, dno
from employee
where dno in (
select DISTINCT dno
from employee
where job = 'ANALYST');

select DISTINCT law_name from zipcode
where law_name in
(select law_name 
from zipcode
where sidok = '인천광역시')
and sidok = '서울특별시';

select distinct a.law_name, b.sigunguk, a.sigunguk
from zipcode a join zipcode b
on a.law_name = b.law_name
where b.sidok = '인천광역시'
and a.sidok = '서울특별시';

select sidok, sigunguk, law_name
from zipcode
where sidok != '경기도' and law_name in (select law_name
from 
(select sigunguk, law_name
from zipcode
where sidok != '경기도'
group by sigunguk, law_name)
group by law_name
having count(*)>1)
group by sidok, sigunguk, law_name
order by law_name;



select distinct a.law_name
from zipcode a join zipcode b
on a.law_name = b.law_name
where 
b.sidok = '인천광역시'
and a.sidok = '서울특별시';


select sidok, sigunguk, law_name
from zipcode
where law_name in (select a.law_name
from zipcode a join zipcode b
on a.law_name = b.law_name
where 
b.sidok = '인천광역시'
and a.sidok = '서울특별시'
group by a.law_name) and sidok != '경기도'
group by sidok, sigunguk, law_name
order by law_name;

select distinct a.law_name
from zipcode a join zipcode b
on a.law_name = b.law_name
where b.sidok = '인천광역시'
and a.sidok = '서울특별시';

select law_name from zipcode
 where law_name = '논현동';
 
create index idx_zipcode_law_name
on zipcode(law_name);

create index idx_zipcode_sidok
on zipcode(sidok);

create index idx_zipcode_dogulwa
on zipcode (sidok, sigunguk, law_name);


