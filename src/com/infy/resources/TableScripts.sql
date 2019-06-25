DROP TABLE Feedback;
DROP TABLE Assets;
DROP TABLE Interests;
DROP TABLE Liabilities;
DROP TABLE Expenses;
DROP Table Corporate;
DROP TABLE Clients;
DROP SEQUENCE HIBERNATE_SEQUENCE;

CREATE SEQUENCE hibernate_sequence START WITH 2011 INCREMENT BY 1;

create table Clients(
userId varchar2(30) PRIMARY KEY,
name varchar2(30) not null,
typeOfClient varchar2(30) not null,
password varchar2(30) not null,
contactNo number(10)not null,
email varchar2(40) not null,
cdate date not null,
detail varchar2(1) not null,
CONSTRAINT chkdetail check (detail IN ('1','0'))
);

insert into Clients values ('karan123','Karan sharma','individual','karankaran',998745632,'karan@mail.com',TO_DATE('22/05/1986', 'DD/MM/YYYY'),'0');
insert into Clients values ('ashu123','Ashu','mediumScaleIndustries','ashuashu',998745632,'ashu@mail.com',TO_DATE('22/05/1996', 'DD/MM/YYYY'),'0');
insert into Clients values ('rajat123','Rajat kumar','corporates','rajatrajat',998745632,'rajat@mail.com',TO_DATE('22/05/1996', 'DD/MM/YYYY'),'0');

create table Feedback(
userId varchar2(30) references Clients(userId) not null,
feedbackId number(5) PRIMARY KEY,
star number, 
feedbackMessage varchar2(50)
);

insert into Feedback values ('karan123','2',5,'Very Helpfull');
insert into Feedback values ('ashu123','3',4,'Loved your site');
insert into Feedback values ('karan123','4',2,'Need some improvement');
insert into Feedback values ('rajat123','5',3,'Faced Some Problem');

select * from Clients;
select * from Feedback;

create table Assets(
userId varchar2(30) references Clients(UserId),
AssetsId number(5) PRIMARY KEY,
cash number,
checkingAccounts number,
saving number,
cashOfLifeInsurance number,
retirement number,
properties number,
noOfVehicles number,
medicalInsurance varchar2(10)
);

create table Interests(
userId varchar2(30) references Clients(UserId),
interestId number(5) PRIMARY KEY,
interest varchar2(30),
timeFrame number,
amount number
);

create table Liabilities(
userId varchar2(30) references Clients(UserId),
LiabilityId number(5) PRIMARY KEY,
mortageBalance number,
creditCardLimit number,
bankLoan number,
emis number
);

create table Expenses(
userId varchar2(30) references Clients(UserId) ,
ExpensesId number(5) PRIMARY KEY,
income number,
expenditure number
);

create table Corporate(
userId varchar2(30) references Clients(UserId) ,
corporateId number(5) PRIMARY KEY,
account number,
cash number,
debts number,
property number,
netProfit number
);

--UPDATE Liabilities
--SET bankLoan = 10000 WHERE LiabilityId = 4;

select * from Assets;
select * from Liabilities;
select * from Expenses;
select * from corporate;
--insert into corporate values ('ashu1234',1,10000,1000,5000,50000,50000000);

