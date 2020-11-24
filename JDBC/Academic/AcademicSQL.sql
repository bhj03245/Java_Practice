CREATE TABLE manager
(
	no                    NUMBER(3)  NOT NULL ,
	name                  VARCHAR2(100)  NULL ,
	age                   NUMBER(3)  NULL ,
	part                  VARCHAR2(100)  NULL 
);

CREATE UNIQUE INDEX XPK관리자 ON manager
(no  ASC);

ALTER TABLE manager
	ADD CONSTRAINT  XPK관리자 PRIMARY KEY (no);

CREATE TABLE professor
(
	no                    NUMBER(3)  NOT NULL ,
	age                   NUMBER(3)  NULL ,
	name                  VARCHAR2(100)  NULL ,
	subject               VARCHAR2(100)  NULL 
);

CREATE UNIQUE INDEX XPK교수 ON professor
(no  ASC);

ALTER TABLE professor
	ADD CONSTRAINT  XPK교수 PRIMARY KEY (no);

CREATE TABLE student
(
	no                    NUMBER(3)  NOT NULL ,
	age                   NUMBER(3)  NULL ,
	name                  VARCHAR2(100)  NULL ,
	hakbun                NUMBER(4)  NULL 
);

CREATE UNIQUE INDEX XPK학생 ON student
(no  ASC);

ALTER TABLE student
	ADD CONSTRAINT  XPK학생 PRIMARY KEY (no);
	
CREATE SEQUENCE manager_no ;
CREATE SEQUENCE professor_no;
CREATE SEQUENCE student_no;

drop table student;
drop table manager;
drop table professor;

drop sequence student_no ;
drop sequence manager_no; 

select * from student;
select * from MANAGER
select * from professor;

