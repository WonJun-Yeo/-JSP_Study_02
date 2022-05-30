-- 테이블 생성
create table member01 (
id varchar2(50) primary key,				-- 메일주소
passwd varchar2(50) not null,				-- 암호화해서 DB 저장
name varchar2(20) not null,					-- 이름
reg_date date default sysdate not null,		-- 가입일
address varchar2(100) not null,				-- 주소
tel varchar2(20) not null					-- 전화번호
);

-- 더미데이터 입력
insert into member01 (id, passwd, name, reg_date, address, tel)
values ('admin@kosomo.com', '1234', '관리자', default, '서울시', '010-1111-1111');

insert into member01 (id, passwd, name, reg_date, address, tel)
values ('hogngkd@kosomo.com', '1234', '홍길동', default, '경기도', '010-2222-2222');

commit;

-- 확인
desc member01;
select * from member01;