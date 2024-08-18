CREATE TABLE `Librarian` (
  `ID` varchar(255) PRIMARY KEY,
  `Email` varchar(255),
  `Name` varchar(255),
  `Password` varchar(255)
);

CREATE TABLE `Student` (
  `ID` varchar(255) PRIMARY KEY,
  `Email` varchar(255),
  `Name` varchar(255),
  `Password` varchar(255),
  `Fines` integer
);

CREATE TABLE `Books` (
  `ISBN` integer PRIMARY KEY,
  `title` varchar(255),
  `Ayther_Name` varchar(255),
  `Puplication_Date` varchar(255),
  `Copies` integer,
  `Delay_Fine` integer,
  `Lost_Fine` integer
);

CREATE TABLE `Borrowed_Books` (
  `ISBN` integer,
  `Due_Date` varchar(255),
  `Student_ID` varchar(255)
);

CREATE TABLE `Lost_Books` (
  `ISBN` integer,
  `Student_ID` varchar(255)
);

CREATE TABLE `Fines` (
  `ISBN` integer,
  `Student_ID` varchar(255),
  `Fine_Type` varchar(255)
);

ALTER TABLE `Borrowed_Books` ADD FOREIGN KEY (`ISBN`) REFERENCES `Books` (`ISBN`);

ALTER TABLE `Borrowed_Books` ADD FOREIGN KEY (`Student_ID`) REFERENCES `Student` (`ID`);

ALTER TABLE `Books` ADD FOREIGN KEY (`ISBN`) REFERENCES `Lost_Books` (`ISBN`);

ALTER TABLE `Lost_Books` ADD FOREIGN KEY (`Student_ID`) REFERENCES `Student` (`ID`);

ALTER TABLE `Fines` ADD FOREIGN KEY (`ISBN`) REFERENCES `Books` (`ISBN`);

ALTER TABLE `Student` ADD FOREIGN KEY (`ID`) REFERENCES `Fines` (`Student_ID`);

ALTER TABLE `Fines` ADD FOREIGN KEY (`Fine_Type`) REFERENCES `Books` (`Delay_Fine`);

ALTER TABLE `Fines` ADD FOREIGN KEY (`Fine_Type`) REFERENCES `Books` (`Lost_Fine`);
