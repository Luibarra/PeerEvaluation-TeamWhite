drop database cs375v1;
create database cs375v1; 
\c cs375v1

CREATE TABLE DBTest(
id int, 
word varchar(255),
Charid char(2), 
val int
);

CREATE TABLE Response(
evalID int, 
Student1 int, 
Student2 int,
Category char(3), 
val int 
);

CREATE TABLE Teams(
evalID int, 
teamID int, 
student int
);

CREATE TABLE Students(
id int, 
name char(50)
);

CREATE TABLE course (
id int,
abbrev text,
title text,
primary key (id)
);

CREATE TABLE section (
id int,
course_id int,
title text,
start date,
primary key (id)
);

CREATE TABLE eval (
id int,
title text,
end_date date,
primary key( id )
);

CREATE TABLE eval_section (
eval_id int,
section_id int,
primary key (eval_id, section_id)
);

CREATE VIEW v_response AS 
SELECT evalID, Student1, Student2, Category, val
FROM Response; 

CREATE VIEW v_teams AS 
SELECT evalID, teamID, student 
FROM Teams; 

-- Grant Statements
GRANT ALL ON DBTest TO mrblee; 
GRANT ALL ON Response TO mrblee; 
GRANT ALL ON Teams TO mrblee; 
GRANT ALL ON Students TO mrblee; 
GRANT ALL ON course TO mrblee;
GRANT ALL ON section TO mrblee;
GRANT ALL ON eval TO mrblee;
GRANT ALL ON eval_section TO mrblee;
GRANT ALL ON v_response TO mrblee; 
GRANT ALL ON v_teams TO mrblee; 

-- Test Insert 
insert into dbtest (id,word,charid,val) values (1,'gamer','AM', 69);

-- Students Inserts
insert into students (id,name) values ( 1,'StudentA');
insert into students (id,name) values ( 2,'StudentB');
insert into students (id,name) values ( 3,'StudentC');
insert into students (id,name) values ( 4,'StudentD');
insert into students (id,name) values ( 5,'StudentE');
insert into students (id,name) values ( 6,'StudentF');
insert into students (id,name) values ( 7,'StudentG');
insert into students (id,name) values ( 8,'StudentH');
insert into students (id,name) values ( 9,'StudentI');
insert into students (id,name) values ( 10,'StudentJ');
insert into students (id,name) values ( 11,'StudentK');
insert into students (id,name) values ( 12,'StudentL');
insert into students (id,name) values ( 13,'StudentM');
insert into students (id,name) values ( 14,'StudentN');
insert into students (id,name) values ( 15,'StudentO');
insert into students (id,name) values ( 16,'StudentP');
insert into students (id,name) values ( 17,'StudentQ');
insert into students (id,name) values ( 18,'StudentR');
insert into students (id,name) values ( 19,'StudentS');

-- Teams Inserts
insert into teams (evalID,teamID,student) values (1,1,1);
insert into teams (evalID,teamID,student) values (1,1,2);
insert into teams (evalID,teamID,student) values (1,1,3);
insert into teams (evalID,teamID,student) values (1,2,4);
insert into teams (evalID,teamID,student) values (1,2,5);
insert into teams (evalID,teamID,student) values (1,2,6);
insert into teams (evalID,teamID,student) values (1,3,7);
insert into teams (evalID,teamID,student) values (1,3,8);
insert into teams (evalID,teamID,student) values (1,3,9);
insert into teams (evalID,teamID,student) values (1,4,10);
insert into teams (evalID,teamID,student) values (1,4,11);
insert into teams (evalID,teamID,student) values (1,4,12);
insert into teams (evalID,teamID,student) values (1,5,13);
insert into teams (evalID,teamID,student) values (1,5,14);
insert into teams (evalID,teamID,student) values (1,5,15);
insert into teams (evalID,teamID,student) values (1,6,16);
insert into teams (evalID,teamID,student) values (1,6,17);
insert into teams (evalID,teamID,student) values (1,7,18);
insert into teams (evalID,teamID,student) values (2,7,19);
insert into teams (evalID,teamID,student) values (2,1,2);
insert into teams (evalID,teamID,student) values (2,1,3);
insert into teams (evalID,teamID,student) values (2,2,1);
insert into teams (evalID,teamID,student) values (2,2,4);
insert into teams (evalID,teamID,student) values (2,3,5);
insert into teams (evalID,teamID,student) values (2,3,6);
insert into teams (evalID,teamID,student) values (2,4,7);
insert into teams (evalID,teamID,student) values (2,4,8);
insert into teams (evalID,teamID,student) values (2,5,9);
insert into teams (evalID,teamID,student) values (2,5,10);



-- Response Inserts 
-- category C eval 1 
insert into Response (evalID, Student1, Student2,Category,Val) values (1,1,1,'C',3);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,1,2,'C',2);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,1,3,'C',3);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,4,4,'C',3);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,4,5,'C',3);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,4,6,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,7,7,'C',3);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,7,8,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,7,9,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,10,10,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,10,11,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,10,12,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,13,13,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,13,14,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,13,15,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,16,16,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,16,17,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,18,18,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,18,19,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,2,1,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,2,2,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,2,3,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,5,4,'C',2);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,5,5,'C',3);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,5,6,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,8,7,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,8,8,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,8,9,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,11,10,'C',3);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,11,11,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,11,12,'C',2);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,14,13,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,14,14,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,14,15,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,17,16,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,17,17,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,19,18,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,19,19,'C',3);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,3,1,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,3,2,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,3,3,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,6,4,'C',2);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,6,5,'C',2);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,6,6,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,9,7,'C',2);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,9,8,'C',2);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,9,9,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,13,10,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,13,11,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,13,12,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,15,13,'C',4);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,15,14,'C',5);
insert into Response (evalID, Student1, Student2,Category,Val) values (1,15,15,'C',4);
-- category I eval 1
insert into Response (evalID,student1,student2,category,val) values (1,1,1,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,1,2,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,1,3,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,4,4,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,4,5,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,4,6,'I',2);
insert into Response (evalID,student1,student2,category,val) values (1,7,7,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,7,8,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,7,9,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,10,10,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,10,11,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,10,12,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,13,13,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,13,14,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,13,15,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,16,16,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,16,17,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,18,18,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,18,19,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,2,1,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,2,2,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,2,3,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,5,4,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,5,5,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,5,6,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,8,7,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,8,8,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,8,9,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,11,10,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,11,11,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,11,12,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,14,13,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,14,14,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,14,15,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,17,16,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,17,17,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,19,18,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,19,19,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,3,1,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,3,2,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,3,3,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,6,4,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,6,5,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,6,6,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,9,7,'I',2);
insert into Response (evalID,student1,student2,category,val) values (1,9,8,'I',2);
insert into Response (evalID,student1,student2,category,val) values (1,9,9,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,13,10,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,13,11,'I',5);
insert into Response (evalID,student1,student2,category,val) values (1,13,12,'I',3);
insert into Response (evalID,student1,student2,category,val) values (1,15,13,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,15,14,'I',4);
insert into Response (evalID,student1,student2,category,val) values (1,15,15,'I',4);
-- category K eval 1 
insert into Response (evalID,student1,student2,category,val) values (1,1,1,'K',3);
insert into Response (evalID,student1,student2,category,val) values (1,1,2,'K',2);
insert into Response (evalID,student1,student2,category,val) values (1,1,3,'K',3);
insert into Response (evalID,student1,student2,category,val) values (1,4,4,'K',3);
insert into Response (evalID,student1,student2,category,val) values (1,4,5,'K',3);
insert into Response (evalID,student1,student2,category,val) values (1,4,6,'K',3);
insert into Response (evalID,student1,student2,category,val) values (1,7,7,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,7,8,'K',3);
insert into Response (evalID,student1,student2,category,val) values (1,7,9,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,10,10,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,10,11,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,10,12,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,13,13,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,13,14,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,13,15,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,16,16,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,16,17,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,18,18,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,18,19,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,2,1,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,2,2,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,2,3,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,5,4,'K',2);
insert into Response (evalID,student1,student2,category,val) values (1,5,5,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,5,6,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,8,7,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,8,8,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,8,9,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,11,10,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,11,11,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,11,12,'K',3);
insert into Response (evalID,student1,student2,category,val) values (1,14,13,'K',3);
insert into Response (evalID,student1,student2,category,val) values (1,14,14,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,14,15,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,17,16,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,17,17,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,19,18,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,19,19,'K',3);
insert into Response (evalID,student1,student2,category,val) values (1,3,1,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,3,2,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,3,3,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,6,4,'K',3);
insert into Response (evalID,student1,student2,category,val) values (1,6,5,'K',2);
insert into Response (evalID,student1,student2,category,val) values (1,6,6,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,9,7,'K',2);
insert into Response (evalID,student1,student2,category,val) values (1,9,8,'K',2);
insert into Response (evalID,student1,student2,category,val) values (1,9,9,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,13,10,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,13,11,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,13,12,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,15,13,'K',4);
insert into Response (evalID,student1,student2,category,val) values (1,15,14,'K',5);
insert into Response (evalID,student1,student2,category,val) values (1,15,15,'K',4);
-- category E eval 1
insert into Response (evalID,student1,student2,category,val) values (1,1,1,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,1,2,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,1,3,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,4,4,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,4,5,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,4,6,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,7,7,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,7,8,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,7,9,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,10,10,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,10,11,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,10,12,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,13,13,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,13,14,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,13,15,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,16,16,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,16,17,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,18,18,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,18,19,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,2,1,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,2,2,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,2,3,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,5,4,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,5,5,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,5,6,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,8,7,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,8,8,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,8,9,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,11,10,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,11,11,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,11,12,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,14,13,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,14,14,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,14,15,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,17,16,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,17,17,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,19,18,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,19,19,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,3,1,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,3,2,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,3,3,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,6,4,'E',2);
insert into Response (evalID,student1,student2,category,val) values (1,6,5,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,6,6,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,9,7,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,9,8,'E',4);
insert into Response (evalID,student1,student2,category,val) values (1,9,9,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,13,10,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,13,11,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,13,12,'E',3);
insert into Response (evalID,student1,student2,category,val) values (1,15,13,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,15,14,'E',5);
insert into Response (evalID,student1,student2,category,val) values (1,15,15,'E',5);
-- category H eval 1
insert into Response (evalID,student1,student2,category,val) values (1,1,1,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,1,2,'H',2);
insert into Response (evalID,student1,student2,category,val) values (1,1,3,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,4,4,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,4,5,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,4,6,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,7,7,'H',3);
insert into Response (evalID,student1,student2,category,val) values (1,7,8,'H',3);
insert into Response (evalID,student1,student2,category,val) values (1,7,9,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,10,10,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,10,11,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,10,12,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,13,13,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,13,14,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,13,15,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,16,16,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,16,17,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,18,18,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,18,19,'H',3);
insert into Response (evalID,student1,student2,category,val) values (1,2,1,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,2,2,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,2,3,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,5,4,'H',3);
insert into Response (evalID,student1,student2,category,val) values (1,5,5,'H',3);
insert into Response (evalID,student1,student2,category,val) values (1,5,6,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,8,7,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,8,8,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,8,9,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,11,10,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,11,11,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,11,12,'H',3);
insert into Response (evalID,student1,student2,category,val) values (1,14,13,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,14,14,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,14,15,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,17,16,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,17,17,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,19,18,'H',3);
insert into Response (evalID,student1,student2,category,val) values (1,19,19,'H',3);
insert into Response (evalID,student1,student2,category,val) values (1,3,1,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,3,2,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,3,3,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,6,4,'H',2);
insert into Response (evalID,student1,student2,category,val) values (1,6,5,'H',3);
insert into Response (evalID,student1,student2,category,val) values (1,6,6,'H',3);
insert into Response (evalID,student1,student2,category,val) values (1,9,7,'H',2);
insert into Response (evalID,student1,student2,category,val) values (1,9,8,'H',2);
insert into Response (evalID,student1,student2,category,val) values (1,9,9,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,13,10,'H',3);
insert into Response (evalID,student1,student2,category,val) values (1,13,11,'H',5);
insert into Response (evalID,student1,student2,category,val) values (1,13,12,'H',3);
insert into Response (evalID,student1,student2,category,val) values (1,15,13,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,15,14,'H',4);
insert into Response (evalID,student1,student2,category,val) values (1,15,15,'H',4);

--Insertions into course table
insert into course (id, abbrev, title) values (123, BIBL, Bible);
insert into course (id, abbrev, title) values (234, ACCT, Accounting);
insert into course (id, abbrev, title) values (345, FIN, Finance);
insert into course (id, abbrev, title) values (456, BIBT, BibleTheory);
insert into course (id, abbrev, title) values (567, PHYS, Physiology);
insert into course (id, abbrev, title) values (678, DET, Gaming);
insert into course (id, abbrev, title) values (789, CS, CompSci);

--Insertions into section table
insert into section (id, course_id, title, start) values (11, 123, BibleClass, 2008-11-11);
insert into section (id, course_id, title, start) values (12, 234, AccountingClass, 2008-11-11);
insert into section (id, course_id, title, start) values (1, 345, FinanceClass, 2008-11-11);
insert into section (id, course_id, title, start) values (2, 456, BibleTheoryClass, 2008-11-11);
insert into section (id, course_id, title, start) values (3, 567, PhysiologyClass, 2008-11-11);
insert into section (id, course_id, title, start) values (4, 678, GamingClass, 2008-11-11);
insert into section (id, course_id, title, start) values (5, 789, CompSciClass, 2008-11-11);

--Insertions into eval table
insert into eval (id, title, end_date) values (1, first, 2008-11-20);
insert into eval (id, title, end_date) values (2, second, 2008-11-20);
insert into eval (id, title, end_date) values (3, third, 2008-11-20);
insert into eval (id, title, end_date) values (4, fourth, 2008-11-20);
insert into eval (id, title, end_date) values (5, fifth, 2008-11-20);
insert into eval (id, title, end_date) values (6, sixth, 2008-11-20);
insert into eval (id, title, end_date) values (7, seventh, 2008-11-20);

--Insertions into eval_section table
insert into eval (eval_id, section_id) values (1, 11);
insert into eval (eval_id, section_id) values (2, 12);
insert into eval (eval_id, section_id) values (3, 1);
insert into eval (eval_id, section_id) values (4, 2);
insert into eval (eval_id, section_id) values (5, 3);
insert into eval (eval_id, section_id) values (6, 4);
insert into eval (eval_id, section_id) values (7, 5);

-- INSERT INTO TeamContribution (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('1', 'Does more or higher-quality work than expected', ' Makes important contributions that improve the teams work.', 'Helps teammates who are having difficulty completing their work.', '0');
-- INSERT INTO TeamContribution (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('2', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
-- INSERT INTO TeamContribution (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('3', 'Completes a fair share of the teams work with acceptable quality', 'Keeps commitments and completes assignments on time.', 'Helps teammates who are having difficulty when it is easy or important.', '0');
-- INSERT INTO TeamContribution (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('4', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
-- INSERT INTO TeamContribution (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('5', 'Does not do a fair share of the teams work. Delivers sloppy or incomplete work.', 'Misses deadlines. Is late unprepared or absent for team meetings.', 'Does not assist teammates. Quits if the work becomes difficult.', '0');

-- INSERT INTO Interacting (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('1', 'Asks for and shows an interest in teammates ideas and contributions.', 'Makes sure teammates stay informed and understand each other.', 'Provides encouragement or enthusiasm to the team.', '0');
-- INSERT INTO Interacting (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('2', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
-- INSERT INTO Interacting (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('3', 'Listens to teammates and respects their contributions.', 'Communicates clearly. Shares information with teammates.', 'Participates fully in team activities.', '0');
-- INSERT INTO Interacting (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('4', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
-- INSERT INTO Interacting (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('5', ' Is defensive. Will not accept help or advice from teammates.', 'Complains makes excuses or does not interact with teammates.', ' Takes actions that affect teammates without their input. Does not share information.', '0');

-- INSERT INTO OnTrack (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('1', 'Gives teammates specific timely and constructive feedback.', 'Makes sure that teammates are making appropriate progress.', 'Watches conditions affecting the team and monitors the teams progress.', '0');
-- INSERT INTO OnTrack (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('2', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
-- INSERT INTO OnTrack (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('3', 'Notices changes that influence the teams success.', 'Knows what everyone on the team should be doing and notices problems.', ' Alerts teammates or suggests solutions when the teams success is threatened.', '0');
-- INSERT INTO OnTrack (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('4', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
-- INSERT INTO OnTrack (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('5', ' Avoids discussing team problems even when they are obvious.', ' Does not pay attention to teammates progress.', 'Is unaware of whether the team is meeting its goals.', '0');

-- INSERT INTO Quality (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('1', 'Motivates the team to do excellent work.',  'Cares that the team does outstanding work even if there is no additional reward.',' Believes that the team can do excellent work.', '0');
-- INSERT INTO Quality (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('2', 'Demonstrates behaviors described immediately above and below.','empty', 'empty', '0');
-- INSERT INTO Quality (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('3', 'Encourages the team to do good work that meets all requirements.',' Wants the team to perform well enough to earn all available rewards.', 'Believes that the team can fully meet its responsibilities.', '0');
-- INSERT INTO Quality (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('4', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
-- INSERT INTO Quality (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('5', 'Satisfied even if the team does not meet assigned standards.',' Wants the team to avoid work',' even if it hurts the team., Doubts that the team can meet its requirements.', '0');

-- INSERT INTO Knowledge (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('1', 'Demonstrates the knowledge skills and abilities to do excellent work.', 'Able to perform the role of any team member if necessary.', ' Acquires new knowledge or skills to improve the teams performance.', '0');
-- INSERT INTO Knowledge (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('2', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
-- INSERT INTO Knowledge (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('3', ' Able to perform some of the tasks normally done by other team members.', 'Acquires knowledge or skills as needed to meet requirements.', 'Demonstrates sufficient knowledge skills and abilities to contribute to the teams work.', '0');
-- INSERT INTO Knowledge (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('4', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
-- INSERT INTO Knowledge (levelID, Attribute1, Attribute2, Attribute3, Selected)
-- VALUES ('5', 'Unable to perform any of the duties of other team members.', 'Unable or unwilling to develop knowledge or skills to contribute to the team.', 'Missing basic qualifications needed to be a member of the team.', '0');