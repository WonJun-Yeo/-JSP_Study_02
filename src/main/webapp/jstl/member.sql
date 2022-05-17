create table member (
    id varchar2(100) not null primary key,
    pass varchar2(100),
    name varchar2(100)
);

insert into member values('1', '1234','È«±æµ¿');
insert into member values('2', '1234','±è¶Ê¶Ê');

commit;