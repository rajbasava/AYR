DROP TABLE phk_seat;
DROP TABLE phk_comments;
DROP TABLE phk_vollogin;
DROP TABLE phk_volunteer;
DROP TABLE phk_participant;

CREATE TABLE phk_participant (
	participantid INT PRIMARY KEY auto_increment,   
	name VARCHAR(300),
	mobile VARCHAR(15),   
	email VARCHAR(50),   
	foundation VARCHAR(100),
	level varchar(5),
	foodcoupon varchar(1),
	eventkit varchar(1),
    amount int,
	amountpaid int,
	dueamount int,
    receiptinfo varchar(50),   
	preparedby VARCHAR(50),   
	timecreated TIMESTAMP,
	timeupdated TIMESTAMP, 
	active VARCHAR(1)
);

CREATE TABLE phk_volunteer (
	volunteerid INT PRIMARY KEY auto_increment,
	name VARCHAR(300),	
	email VARCHAR(50),   
	password VARCHAR(15),
	mobile VARCHAR(15), 
	activity VARCHAR(50),  
	permission varchar(30),
	preparedby VARCHAR(50),   
	timecreated TIMESTAMP,
	timeupdated TIMESTAMP,
	CONSTRAINT email UNIQUE (email)
);


CREATE TABLE phk_comments
(
    id INT PRIMARY KEY auto_increment,
	participantid int,
	comments varchar(300),
	preparedby VARCHAR(50),   
	timecreated TIMESTAMP,
	FOREIGN KEY (participantid) REFERENCES phk_participant(participantid)
                      ON DELETE CASCADE
);

CREATE TABLE phk_seat
(
    id INT PRIMARY KEY auto_increment,
	participantid int,
	level varchar(5),
	seat int,
	FOREIGN KEY (participantid) REFERENCES phk_participant(participantid)
                      ON DELETE CASCADE
);

CREATE TABLE phk_vollogin (
	id INT PRIMARY KEY auto_increment,
	volunteerid INT,	
	counter VARCHAR(5),   
	loggedin TIMESTAMP null,
	loggedout TIMESTAMP null,
	FOREIGN KEY (volunteerid) REFERENCES phk_volunteer(volunteerid)
                      ON DELETE CASCADE
);

INSERT INTO phk_volunteer(name,   email,   password,   mobile,   activity,   permission,   preparedby,   timecreated,   timeupdated)
VALUES('Admin',   'admin@yvphk.com',   'admin',   '9999999999',   'admin',   'admin',   'system',   now(),   now());

