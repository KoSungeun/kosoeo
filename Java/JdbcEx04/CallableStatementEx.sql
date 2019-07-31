create table salary (
    name varchar2(10),
    pay number(10));
insert into salary values ('홍길동', 1000000);
insert into salary values ('전우치', 2000000);
insert into salary values ('손오공', 3000000);
commit;

create or replace procedure adjust (
    n in varchar2,
    rate in float )
is
    newpay float;
begin 
    select pay into newpay from salary where name = n;
    
    newpay := newpay + newpay * rate;
    update salary set pay = newpay where name = n;
end;
/
