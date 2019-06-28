create database company;

use company;

create table Dept(
    DEPTNO int primary key,
    DEPTNAME varchar(20) not null unique
);

create table Employee(
    EMPNO varchar(20) primary key,
    DEPTNO int not null,
    EMPNAME varchar(30) not null,
    EMPSEX char(1) not null comment '0女 1男',
    ENTRYDATE date not null,
    EMPPHONE varchar(30) not null,
    EMPADDR varchar(50),
    SALARY int not null,
    LEAVEDATE date,
    STATE int not null default '1' comment '1在职, 2离职, 3减员'
);

create table history(
    CHANGENO int auto_increment primary key,
    EMPNO varchar(20) not null,
    DEPTNO int not null,
    SALARY int not null,
    CHANGEDATE date,
    CHANGEREASON  varchar(100),
    DIMISSIONDATE date,
    DIMISSIONREASON varchar(100),
    foreign key(EMPNO) references Employee(EMPNO),
    foreign key(DEPTNO) references Dept(DEPTNO)
);

insert Dept values(1,"技术部");
insert Dept values(2,"宣传部");
insert Dept values(3,"人力资源部");

select * from Dept;

alter table Dept
add unique key(DEPTNAME);

alter table employee add constraint chk_employee_001 check(empsex in(0,1));