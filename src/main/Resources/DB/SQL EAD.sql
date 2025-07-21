CREATE DATABASE  IF NOT EXISTS ead; 
USE ead;

CREATE TABLE tbluser (
  email varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  role enum('Admin','Teacher') DEFAULT 'Admin',
  name varchar(50) NOT NULL,
  PRIMARY KEY (`email`)
);

INSERT INTO `tbluser` VALUES ('poorna@gmail.com','4321','Admin','Poorna');

CREATE TABLE students (
  student_id int NOT NULL AUTO_INCREMENT,
  first_name varchar(50) DEFAULT NULL,
  last_name varchar(50) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  date_of_birth date DEFAULT NULL,
  PRIMARY KEY (student_id)
);
INSERT INTO students VALUES (1,'Alice','Smith','alice.smith@example.com','2000-01-15'),(2,'Bob','Johnson','bob.johnson@example.com','1999-03-22'),(3,'Charlie','Brown','charlie.brown@example.com','2001-07-30'),(4,'Diana','Prince','diana.prince@example.com','2000-11-05'),(5,'Eve','Davis','eve.davis@example.com','2002-04-10'),(6,'Frank','White','frank.white@example.com','1998-09-18'),(7,'Grace','Taylor','grace.taylor@example.com','2001-02-25'),(8,'Harry','Moore','harry.moore@example.com','2000-06-12'),(9,'Ivy','Clark','ivy.clark@example.com','1999-12-01'),(10,'Jack','Hall','jack.hall@example.com','2002-08-07'),(11,'Poorna','Rajapaksha','poorna@gmail.com','2004-07-31'),(13,'Arohsa','Bandara','arosha@gmail.com','2008-04-14');

CREATE TABLE subjects (
  subject_id int NOT NULL AUTO_INCREMENT,
  subject_code varchar(20) NOT NULL,
  subject_name varchar(100) NOT NULL,
  description text,
  credit_hours int DEFAULT '3',
  PRIMARY KEY (subject_id),
  UNIQUE KEY subject_code (subject_code)
);

INSERT INTO subjects VALUES (1,'CS101','Introduction to Computer Science','A foundational course covering basic concepts of computer science, programming, and algorithms.',3),(2,'MA201','Calculus I','First course in a series on calculus, covering limits, derivatives, and integrals.',4),(3,'PH101','General Physics','An introductory course to the principles of physics, including mechanics, thermodynamics, and electricity.',3),(4,'CH101','General Chemistry','Fundamental principles of chemistry, including atomic structure, bonding, and chemical reactions.',3),(5,'BI101','General Biology','An overview of fundamental biological concepts, including cell structure, genetics, and evolution.',3),(6,'EN101','English Composition','Develops skills in academic writing, critical thinking, and research.',3),(7,'HS201','World History','A survey of major events and civilizations throughout world history.',3),(8,'EC301','Microeconomics','Introduction to microeconomic principles, including supply and demand, market structures, and consumer behavior.',3),(9,'PS101','Introduction to Psychology','Explores basic concepts and theories in psychology, including cognitive processes, human development, and social behavior.',3),(10,'SO101','Introduction to Sociology','Examines fundamental sociological concepts, theories, and research methods.',3),(12,'DSE24','Software Engineer','This is a description',2);

CREATE TABLE marks (
  mark_id int NOT NULL AUTO_INCREMENT,
  student_id int DEFAULT NULL,
  subject_id int DEFAULT NULL,
  exam_type enum('Mid','Final','Quiz') DEFAULT NULL,
  marks_obtained int DEFAULT NULL,
  max_marks int DEFAULT NULL,
  PRIMARY KEY (mark_id),
  KEY student_id (student_id),
  KEY subject_id (subject_id),
  CONSTRAINT marks_ibfk_1 FOREIGN KEY (student_id) REFERENCES students (student_id),
  CONSTRAINT marks_ibfk_2 FOREIGN KEY (subject_id) REFERENCES subjects (subject_id)
);

INSERT INTO marks VALUES (1,1,1,'Mid',85,100),(2,1,1,'Final',92,100),(3,1,2,'Quiz',18,20),(4,1,2,'Mid',78,100),(5,2,1,'Mid',75,100),(6,2,3,'Final',88,100),(7,2,4,'Quiz',15,20),(8,3,1,'Mid',60,100),(9,3,2,'Final',70,100),(10,3,5,'Mid',82,100),(11,4,3,'Mid',90,100),(12,4,4,'Final',85,100),(13,4,6,'Quiz',19,20),(14,5,1,'Mid',70,100),(15,5,5,'Final',75,100),(16,5,7,'Mid',68,100),(17,6,2,'Mid',65,100),(18,6,6,'Final',95,100),(19,6,8,'Quiz',17,20),(20,7,3,'Mid',80,100),(21,7,7,'Final',72,100),(22,7,9,'Mid',88,100),(23,8,4,'Mid',70,100),(24,8,8,'Final',79,100),(25,8,10,'Quiz',16,20),(26,9,5,'Mid',92,100),(27,9,9,'Final',85,100),(28,9,1,'Quiz',19,20),(29,10,6,'Mid',77,100),(30,10,10,'Final',83,100),(31,10,2,'Quiz',14,20),(36,1,4,'Final',57,100);