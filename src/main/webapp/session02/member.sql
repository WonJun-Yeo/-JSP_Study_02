-- 테이블 생성
create table member (
id varchar2(10) primary key,
pass varchar2(10) not null,
name varchar2(30) not null,
regidate date default sysdate not null
);

-- 더미 데이터
insert into member (id, pass, name)
values('admin', '1234', '관리자');

insert into member (id, pass, name)
values('hong', '1234', '홍길동');

commit;

select * from member;
