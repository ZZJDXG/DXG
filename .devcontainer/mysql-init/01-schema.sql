USE oa;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `StaffID` varchar(50) NOT NULL,
  `StaffName` varchar(50) NOT NULL DEFAULT 'None',
  `StaffGender` tinyint DEFAULT NULL,
  `StaffBirthday` date DEFAULT NULL,
  `StaffIDCard` varchar(20) DEFAULT NULL,
  `StaffDept` int DEFAULT NULL,
  `StaffTel` varchar(20) NOT NULL,
  `StaffPwd` varchar(255) NOT NULL,
  `StaffPosition` tinyint DEFAULT 0,
  `HireDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`StaffID`),
  UNIQUE KEY `uk_tel` (`StaffTel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `DeptID` int NOT NULL AUTO_INCREMENT,
  `DeptName` varchar(60) NOT NULL,
  `SupportChannels` char(16) NOT NULL,
  `SuperiorDept` int DEFAULT -1,
  `ShiftType` tinyint DEFAULT -1,
  `AttendanceDay` varchar(7) DEFAULT '1234567',
  `AttendanceRule1Start` char(4) DEFAULT '0800',
  `AttendanceRule1End` char(4) DEFAULT '1200',
  `AttendanceRule2Start` char(4) DEFAULT '1300',
  `AttendanceRule2End` char(4) DEFAULT '1600',
  `ClearTime` char(4) DEFAULT '0000',
  PRIMARY KEY (`DeptID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `positions`;
CREATE TABLE `positions` (
  `PosID` int NOT NULL AUTO_INCREMENT,
  `PosName` varchar(50) NOT NULL,
  `UpdatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PosID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `departments`
  (`DeptID`, `DeptName`, `SupportChannels`, `SuperiorDept`, `ShiftType`, `AttendanceDay`, `AttendanceRule1Start`, `AttendanceRule1End`, `AttendanceRule2Start`, `AttendanceRule2End`, `ClearTime`)
VALUES
  (1, 'Default Dept', '1111111111111111', -1, 0, '1234567', '0830', '1200', '1300', '1730', '0000');

INSERT INTO `positions` (`PosID`, `PosName`, `UpdatedTime`)
VALUES
  (1, 'Staff', CURRENT_TIMESTAMP),
  (2, 'Manager', CURRENT_TIMESTAMP);

INSERT INTO `user`
  (`StaffID`, `StaffName`, `StaffGender`, `StaffBirthday`, `StaffIDCard`, `StaffDept`, `StaffTel`, `StaffPwd`, `StaffPosition`, `HireDate`)
VALUES
  ('CS001', 'Codespaces Demo', 1, '1990-01-01', '430000199001011234', 1, '13800138000', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, CURRENT_TIMESTAMP);
