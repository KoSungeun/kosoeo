create table dept(
dno number(2),
dname varchar2(14),
loc varchar2(13));

create table dept2
as select * from department;

create table dept4 (num number(4), name varchar2(10))
as select eno, ename from employee;
desc dept3;
select * from dept3;

desc dept;

alter table dept
    modify ( loc varchar2(20));
    
alter table dept
drop column birth;

select * from dept3;

alter table dept3
set unused (ename);

alter table dept3
drop unused columns;

rename dept3 to dept33;
drop table dept33;


drop table dept3;

select owner, table_name from all_tables
where owner = 'SCOTT';

create table dept
as 
select * from department where 0 = 1;

insert into dept 
values (10, 'ACCOUNTING', 'NEW YORK');

insert into dept (dno, dname)
values (30, 'SALES');

create table emp2 
as select * from employee where 1=2;

desc emp2;
insert into emp2 (eno, hiredate)
values (1, '2019/07/29');

insert into emp2 (eno, hiredate)
values (2, to_date('2019, 07, 01', 'YYYY,MM,DD'));
select *
from emp2;


desc emp2;
insert into emp2 (eno, hiredate)
values (4, '2019.07/30');

insert into dept
select * from department;

update dept
set dno = 21
where dname = 'RESEARCH';

select * from dept;

update dept
set dname = 'PROGRAMMING', loc = 'SEOUL'
where dno = 10;

update dept
set (dname, loc) = (select dname, loc from department where dno =30)
where dno = 10;

delete dept
where dno = 10;

delete dept
where dno = (select dno from department where dname = 'SALES');

commit;
rollback;

delete dept
where dno = 21;

rollback;

drop table emp2;

create table emp2
as select * from employee;

create table dept2
as select * from department where 1=2;

insert into dept2
 select * from department;
 
select * from dept2;

select * from emp2;
delete from emp2
where eno = 7934;

rollback;

delete from emp2
where dno =20;
delete from emp2
where dno =30;

commit;

update emp2
set salary = 0
where dno = 20;

create table customer( id varchar(20) unique,
                      pwd varchar2(20) not null,
                      name varchar2(20) not null);
                      
create table customer2(
    id varchar2(20) constraint coustome2_id_uk UNIQUE,
    pwd varchar2(20) constraint coustomer2_pwd_nn NOT NULL,
    name varchar(30) constraint coustomer2_name_nn not null,
    phone varchar2(30),
    address varchar2(100));
    
    create table coustomer3(
    id varchar2(20), 
    phone varchar(30),
    constraint coustomer_id_pk primary key(id));
    
    create table coustomer4(
    id varchar2(20) primary key, 
    phone varchar(30));

create table coustomer5(
    id varchar2(20) constraint coustomer5_id_pk primary key, 
    phone varchar(30));

create table dept9 (
 dno number(2) constraint dept_dno_pk primary key,
 dname varchar2(14),
 loc varchar2(13));

insert into dept9
select * from department order by dno;

select * from dept9;

insert into dept9 values (null, 'TEST','TEST');

create table emp9 (
 eno number(4) constraint emp_eno_pk primary key,
 ename varchar2(10),
 job varchar2(9),
 dno number(2) constraint emp_dno_fk references dept9);
 
 insert into emp9
 select eno, ename, job, dno from employee order by eno;
 select * from emp9;
 insert into emp9
 values (8001, 'È«±æµ¿1', 'Á¡¿ø', 50);
 
create table emp (
    eno number(4) constraint emp_eno_pk primary key,
    ename varchar2(10),
    salary number(7,2) default 1000
    );
insert into emp (eno, ename, salary)
values(1001, 'È«±æµ¿', 2000);
insert into emp
values (1002, 'È«±æµ¿2', 1000);
insert into emp (eno, salary)
values (1003, 1500);
insert into emp
values (1004, null, 1600);
insert into emp (eno, ename)
values (2002, 'Àü¿ìÄ¡');

drop table emp;

select  * from emp;

alter table emp
add constraint emp_salary_check check(0 < salary);

alter table emp
enable constraint emp_salary_check;

create view v_emp
as
select eno, ename, job, manager, dno
from employee;
desc employee;
desc v_emp;

select * from v_emp2;
create view v_emp2
as
select a.eno, a.ename, a.salary, b.dno, b.dname, b.loc
from employee a, department b
where a.dno = b.dno;

desc v_emp2;


create or replace view v_emp
as 
select eno, ename, dno, job
from employee
where job like 'MANAGER' with check option;

select * from v_emp;
select * from employee;
insert into v_emp
values(9004, 'È«±æµ¿2', 30, 'MANAGER');

create sequence sample_seq
 increment by 1
 start with 1;
 
select sequence_name , min_value, max_value,
increment_by, cycle_flag
from user_sequences;

select dno_seq.currval from dual;

create sequence dno_seq
increment by 10
start with 50;

insert into department
values (dno_seq.nextval, '111', '111');

create index idx_employee_ename
on employee (ename);

select * from employee
 where ename = 'SCOTT';
 
select * from zipcode
 where rownum < 10;