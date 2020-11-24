create table board(
	no number(3) primary key,
	title varchar2(100),
	content varchar2(500),
	author varchar2(100),
	nal varchar2(10),
	readcount number(3)
)

create sequence board_no

insert into board(no,title,content,author,nal,readcount) values(board_no.nextval,'力格1','郴侩1','kh','2020.11.12',0)

select * from board

drop table board

update board set readcount = 0 where title = '力格1'

