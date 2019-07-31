set serveroutput on
declare
    v_eno number(4);
    v_ename employee.ename%type;
begin
    v_eno := 7788;
    v_ename := 'scott';
    dbms_output.put_line('사원번호  사원이름');
    dbms_output.put_line('----------------------');    
    dbms_output.put_line(v_eno || '      ' || v_ename);
end;
/

set serveroutput on
declare
    v_eno number(4);
    v_ename employee.ename%type;
begin
    dbms_output.put_line('사원번호  사원이름');
    dbms_output.put_line('----------------------');    
    
    select eno, ename into v_eno, v_ename
    from employee
    where ename = 'SCOTT';
    
    dbms_output.put_line(v_eno || '       ' || v_ename);
end;
/

set serveroutput on
declare
    v_employee employee%rowtype;
    temp number(4) := 1;
    annsal number(7, 2);
begin
    select * into v_employee
    from employee
    where ename = 'SCOTT';
    
    if(v_employee.commission is null ) then
        v_employee.commission := 0;
    end if;
    
    annsal := v_employee.salary * 12 + v_employee.commission;
    
    dbms_output.put_line('사원번호  사원이름    연봉');
    dbms_output.put_line('--------------------------------');
    dbms_output.put_line(v_employee.eno || '       ' ||
                          v_employee.ename || '       '||
                          annsal);
end;
/

set serveroutput on
declare
    v_eno employee.eno%type;
    v_ename employee.ename%type;
    v_dno employee.dno%type;
    v_dname varchar2(20) := null;
begin
    select eno, ename, dno
    into v_eno, v_ename, v_dno
    from employee
    where ename = 'SCOTT';
    if(v_dno = 10) then
        v_dname := 'ACCOUNTING';
    elsif(v_dno = 20) then
        v_dname := 'RESEARCH';
    elsif(v_dno = 30) then
        v_dname := 'SALES';
    elsif(v_dno = 40) then
        v_dname := 'OPERATIONS';
    end if;
    
    dbms_output.put_line('사원번호      사원이름    부서명');
    dbms_output.put_line('-------------------------------------');
    dbms_output.put_line(v_eno || '    ' ||
                         v_ename || '    ' ||
                         v_dname);
end;
/

set serveroutput on
declare 
    dan number := 2;
    i number := 1;
begin
    loop
        dbms_output.put_line(dan || ' * ' || i || ' = ' || (dan * i) );
        i := i + 1;
        if i > 9 then
            exit;
        end if;
    end loop;
end;
/

set serveroutput on
declare
    dan number := 2;
    i number := 1;
begin
    for i in reverse 1 .. 9 loop
        dbms_output.put_line(dan || ' * ' || i || ' = ' || (dan * i) );
    end loop;
end;
/

set serveroutput on
declare
    dan number := 2;
    i number := 1;
begin
    while i <= 9 loop
        dbms_output.put_line(dan || ' * ' || i || ' = ' || (dan * i) );
        i := i + 1;
    end loop;
end;
/

set serveroutput on
declare
    v_dept department%rowtype;
    cursor c1
        is
        select * from department;
begin
    dbms_output.put_line('사원번호   사원이름    지역명');
    dbms_output.put_line('-------------------------------');
    open c1;
    loop
        fetch c1 into v_dept.dno, v_dept.dname, v_dept.loc;
        exit when c1 %notfound;
        dbms_output.put_line(v_dept.dno || '       ' ||
                             v_dept.dname || '       ' ||
                             v_dept.loc);
    end loop;
    close c1;
end;
/

set serveroutput on
declare
    v_dept department%rowtype;
    cursor c1
        is
        select * from department;
begin
    dbms_output.put_line('사원번호   사원이름    지역명');
    dbms_output.put_line('-------------------------------');
    for v_dept in c1 loop
        exit when c1 %notfound;
        dbms_output.put_line(v_dept.dno || '       ' ||
                             v_dept.dname || '       ' ||
                             v_dept.loc);
    end loop;
end;
/


set serveroutput on
declare
    v_dno employee.dno%type;
    v_dname department.dname%type;
begin
    select dno into v_dno from employee
    where ename = 'KING';
    if (v_dno = 10) then
        v_dname := 'ACCOUNTING';
    elsif (v_dno = 20) then
        v_dname := 'RESEARCH';
    elsif (v_dno = 30) then
        v_dname := 'SALES';
    elsif (v_dno = 40) then
        v_dname := 'OPERATIONS';
    end if;
    dbms_output.put_line(v_dname);  
end;
/


declare
    i number := 1;
    isum number := 0;
begin
    loop
        isum := isum + i;
        i := i + 1;
        if i > 10 then
            exit;
        end if;
    end loop;
    dbms_output.put_line(isum);
end;
/


set serveroutput on
declare
    i number;
    isum number := 0;
begin
    for i in 1 .. 10 loop
        isum := isum + i;
    end loop;
    dbms_output.put_line(isum);
end;
/

set serveroutput on
declare
    i number := 1;
    isum number := 0;
begin
    while i <= 10 loop
        isum := isum + i;
        i := i + 1;
    end loop;
    dbms_output.put_line(isum);
end;
/

set serveroutput on
declare
    v_emp department%rowtype;
    cursor c1
    is
    select eno, ename, salary into v_emp from employee
    where commission is not null
    order by ename;
begin
    for v_emp in c1 loop
        exit when c1 %notfound;
        dbms_output.put_line(v_emp.eno || chr(9) || v_emp.ename || chr(9) || v_emp.salary);
    end loop;
end;
/
