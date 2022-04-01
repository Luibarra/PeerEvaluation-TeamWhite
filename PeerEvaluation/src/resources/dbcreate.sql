CREATE TABLE TeamContribution (
levelID int,
Attribute1 varchar(255),
Attribute2 varchar(255),
Attribute3 varchar(255),
Selected boolean
);
CREATE TABLE Interacting (
levelID int,
Attribute1 varchar(255),
Attribute2 varchar(255),
Attribute3 varchar(255),
Selected boolean
);
CREATE TABLE OnTrack (
levelID int,
Attribute1 varchar(255),
Attribute2 varchar(255),
Attribute3 varchar(255),
Selected boolean
);
CREATE TABLE Quality (
levelID int,
Attribute1 varchar(255),
Attribute2 varchar(255),
Attribute3 varchar(255),
Selected boolean
);
CREATE TABLE Knowledge (
levelID int,
Attribute1 varchar(255),
Attribute2 varchar(255),
Attribute3 varchar(255),
Selected boolean
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

CREATE VIEW v_response AS 
SELECT evalID, Student1, Student2, Category, val
FROM Response; 

CREATE VIEW v_teams AS 
SELECT evalID, teamID, student 
FROM Teams; 

INSERT INTO TeamContribution (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('1', 'Does more or higher-quality work than expected', ' Makes important contributions that improve the teams work.', 'Helps teammates who are having difficulty completing their work.', '0');
INSERT INTO TeamContribution (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('2', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
INSERT INTO TeamContribution (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('3', 'Completes a fair share of the teams work with acceptable quality', 'Keeps commitments and completes assignments on time.', 'Helps teammates who are having difficulty when it is easy or important.', '0');
INSERT INTO TeamContribution (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('4', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
INSERT INTO TeamContribution (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('5', 'Does not do a fair share of the teams work. Delivers sloppy or incomplete work.', 'Misses deadlines. Is late unprepared or absent for team meetings.', 'Does not assist teammates. Quits if the work becomes difficult.', '0');

INSERT INTO Interacting (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('1', 'Asks for and shows an interest in teammates ideas and contributions.', 'Makes sure teammates stay informed and understand each other.', 'Provides encouragement or enthusiasm to the team.', '0');
INSERT INTO Interacting (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('2', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
INSERT INTO Interacting (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('3', 'Listens to teammates and respects their contributions.', 'Communicates clearly. Shares information with teammates.', 'Participates fully in team activities.', '0');
INSERT INTO Interacting (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('4', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
INSERT INTO Interacting (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('5', ' Is defensive. Will not accept help or advice from teammates.', 'Complains makes excuses or does not interact with teammates.', ' Takes actions that affect teammates without their input. Does not share information.', '0');

INSERT INTO OnTrack (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('1', 'Gives teammates specific timely and constructive feedback.', 'Makes sure that teammates are making appropriate progress.', 'Watches conditions affecting the team and monitors the teams progress.', '0');
INSERT INTO OnTrack (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('2', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
INSERT INTO OnTrack (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('3', 'Notices changes that influence the teams success.', 'Knows what everyone on the team should be doing and notices problems.', ' Alerts teammates or suggests solutions when the teams success is threatened.', '0');
INSERT INTO OnTrack (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('4', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
INSERT INTO OnTrack (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('5', ' Avoids discussing team problems even when they are obvious.', ' Does not pay attention to teammates progress.', 'Is unaware of whether the team is meeting its goals.', '0');

INSERT INTO Quality (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('1', 'Motivates the team to do excellent work.',  'Cares that the team does outstanding work even if there is no additional reward.',' Believes that the team can do excellent work.', '0');
INSERT INTO Quality (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('2', 'Demonstrates behaviors described immediately above and below.','empty', 'empty', '0');
INSERT INTO Quality (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('3', 'Encourages the team to do good work that meets all requirements.',' Wants the team to perform well enough to earn all available rewards.', 'Believes that the team can fully meet its responsibilities.', '0');
INSERT INTO Quality (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('4', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
INSERT INTO Quality (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('5', 'Satisfied even if the team does not meet assigned standards.',' Wants the team to avoid work',' even if it hurts the team., Doubts that the team can meet its requirements.', '0');

INSERT INTO Knowledge (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('1', 'Demonstrates the knowledge skills and abilities to do excellent work.', 'Able to perform the role of any team member if necessary.', ' Acquires new knowledge or skills to improve the teams performance.', '0');
INSERT INTO Knowledge (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('2', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
INSERT INTO Knowledge (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('3', ' Able to perform some of the tasks normally done by other team members.', 'Acquires knowledge or skills as needed to meet requirements.', 'Demonstrates sufficient knowledge skills and abilities to contribute to the teams work.', '0');
INSERT INTO Knowledge (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('4', 'Demonstrates behaviors described immediately above and below.', 'empty', 'empty', '0');
INSERT INTO Knowledge (levelID, Attribute1, Attribute2, Attribute3, Selected)
VALUES ('5', 'Unable to perform any of the duties of other team members.', 'Unable or unwilling to develop knowledge or skills to contribute to the team.', 'Missing basic qualifications needed to be a member of the team.', '0');