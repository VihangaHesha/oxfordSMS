create database oxford;

use oxford;

create table Parent(
parentId varchar(10)primary key,
Name varchar (60),
Contact varchar(10),
Address varchar(80));

create table User(
UserId varchar(10) primary key,
Name varchar(60),
Password varchar(16),
Contact varchar(10),
Email varchar(40));

create table Student(
StId varchar(10) primary key,
Name varchar(60),
Grade varchar(3),
Contact varchar(10),
Address varchar(80),
UserId varchar(10),
foreign key(UserId) references User(UserId) on update cascade on delete cascade);


create table Employee(
EmpId varchar(10) primary key,
Name varchar(60),
Contact varchar(10),
Address varchar(80),
Type varchar(20),
UserId varchar(10),
foreign key(UserId) references User(UserId) on update cascade on delete cascade);


create table Payment(
PayId varchar(10) primary key,
Amount double,
Date date,
PayType varchar(20),
Subject varchar(40),
StId varchar(10),
foreign key(StId) references Student(StId) on update cascade on delete cascade);


create table StudentTeacherDetails(
StId varchar(10),
EmpId varchar(10),
Subject varchar(40),
foreign key(StId) references Student(StId) on update cascade on delete cascade,
foreign key(EmpId) references Employee(EmpId) on update cascade on delete cascade);


create table StudentDetails(
StId varchar(10),
ParentId varchar(10),
foreign key(StId) references Student(StId) on update cascade on delete cascade,
foreign key(ParentId) references Parent(ParentId) on update cascade on delete cascade);


create table Attendance(
AttendId varchar(10) primary key,
Date date,
AttendMark varchar(1),
StId varchar(10),
foreign key(StId) references Student(StId) on update cascade on delete cascade);


create table Equipment(
EquipId varchar(10) primary key,
Description varchar(50),
QTY int(20));


create table SubjectEquipments(
EmpId varchar(10),
EquipId varchar(10),
foreign key(EmpId) references Employee(EmpId) on update cascade on delete cascade,
foreign key(EquipId) references Equipment(EquipId) on update cascade on delete cascade);


create table TimeTable(
TimeId varchar(10) primary key,
TimePeriod varchar(12),
Subject varchar(40));


create table SubjectSchedule(
TimeId varchar(10),
EmpId varchar(10),
foreign key(EmpId) references Employee(EmpId) on update cascade on delete cascade,
foreign key(TimeId) references TimeTable(TimeId) on update cascade on delete cascade);


create table Salary(
SalaryId varchar(10) primary key,
Amount double,
Date date,
EmpId varchar(10),
foreign key(EmpId) references Employee(EmpId) on update cascade on delete cascade);

create table Classroom(
ClassId varchar(10) primary key,
Description varchar(50),
Capacity int(6));

create table StudentClass(
StId varchar(10),
ClassId varchar(10),
foreign key(StId) references Student(StId) on update cascade on delete cascade,
foreign key(ClassId) references Classroom(ClassId) on update cascade on delete cascade);


create table ClassroomSchedule(
ClassId varchar(10),
TimeId varchar(10),
foreign key(ClassId) references Classroom(ClassId) on update cascade on delete cascade,
foreign key(TimeId) references TimeTable(TimeId) on update cascade on delete cascade);

create table ClassroomDetails(
ClassId varchar(10),
EmpId varchar(10),
foreign key(ClassId) references Classroom(ClassId) on update cascade on delete cascade,
foreign key(EmpId) references Employee(EmpId) on update cascade on delete cascade);
#################################################################################
INSERT INTO Parent (ParentId, Name, Contact, Address) 
VALUES ('P001', 'Jayantha Perera', '0112345678', '123 Galle Road, Hikkaduwa');

INSERT INTO User (UserId, Name, Password)
VALUES ('U001', 'Chaminda', 'Pass@4321');

INSERT INTO Student (StId, Name, Grade, Contact, Address,UserId)
VALUES ('S001', 'Amali Jayawardena', '10', '0777123456', 'N0.51, Kuruduwatta, Ginimallagaha','U001');

INSERT INTO Employee (EmpId, Name, Contact, Address, Type,UserId)
VALUES ('E001', 'Sachini Madushani', '0112987654', 'Kamal, Adurathvila, Poddala', 'Teacher','U001');

INSERT INTO Subject (SubId,Description,FeeAmount,AvailableSeats,EmpId) VALUES
('SUB001','Mathematics',2500.00,35,'E001');

INSERT INTO Payment (PayId, Amount, Date, PayType, Subject, StId)
VALUES ('PAY001', 25000.00, '2023-04-09', 'Cash', 'Mathematics', 'S001');

INSERT INTO StudentTeacherDetails (StId, EmpId, Subject)
VALUES ('S001', 'E001', 'Mathematics');

INSERT INTO StudentDetails (StId, ParentId)
VALUES ('S001', 'P001');

INSERT INTO Attendance (AttendId, Date, AttendMark, StId)
VALUES ('A001', '2024-04-09', 'I', 'S001');

INSERT INTO Equipment (EquipId, Description, QTY)
VALUES ('EQ001', 'Whiteboard Marker', 50);

INSERT INTO SubjectEquipments (EmpId, EquipId)
VALUES ('E001', 'EQ001');

INSERT INTO TimeTable (TimeId, TimePeriod, Subject)
VALUES ('T001', '08:00-09:00', 'Mathematics');

INSERT INTO SubjectSchedule (TimeId, EmpId)
VALUES ('T001', 'E001');

INSERT INTO Salary (SalaryId, Amount, Date, EmpId)
VALUES ('S001', 75000.00, '2023-04-01', 'E001');

INSERT INTO Classroom (ClassId, Description, Capacity,SubId)
VALUES ('C001', 'Mathematics Classroom', 35,'SUB001');

INSERT INTO StudentClass (StId, ClassId)
VALUES ('S001', 'C001');

INSERT INTO ClassroomSchedule (ClassId, TimeId)
VALUES ('C001', 'T001');

INSERT INTO ClassroomDetails (ClassId, EmpId)
VALUES ('C001', 'E001');
