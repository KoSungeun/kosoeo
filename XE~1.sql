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


select ename, job, salary , salary + decode(job, 'ANALYST', 200, 'SALESMAN', 180, 'MANAGER', 150, 'CLERK', 100, 0) as 인상급여 from employee;