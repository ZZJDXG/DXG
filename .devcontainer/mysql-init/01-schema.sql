SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE oa;

DROP TABLE IF EXISTS `AttendanceRecords`;
DROP TABLE IF EXISTS `positions`;
DROP TABLE IF EXISTS `Departments`;
DROP TABLE IF EXISTS `user`;
DROP TRIGGER IF EXISTS `trg_user_before_insert`;

CREATE TABLE `user` (
  `StaffID` varchar(50) NOT NULL DEFAULT '',
  `StaffName` varchar(50) NOT NULL DEFAULT 'None',
  `StaffGender` tinyint DEFAULT NULL,
  `StaffBirthday` date DEFAULT NULL,
  `StaffIDCard` varchar(20) DEFAULT NULL,
  `StaffDept` int DEFAULT NULL,
  `StaffTel` varchar(20) NOT NULL,
  `StaffPwd` varchar(255) NOT NULL,
  `StaffPosition` tinyint DEFAULT 0,
  `AttendanceStatus` int DEFAULT 0,
  `HireDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`StaffID`),
  UNIQUE KEY `uk_tel` (`StaffTel`),
  KEY `idx_staff_dept` (`StaffDept`),
  KEY `idx_staff_position` (`StaffPosition`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DELIMITER $$
CREATE TRIGGER `trg_user_before_insert`
BEFORE INSERT ON `user`
FOR EACH ROW
BEGIN
  IF NEW.`StaffID` IS NULL OR NEW.`StaffID` = '' THEN
    SET NEW.`StaffID` = REPLACE(UUID(), '-', '');
  END IF;
END$$
DELIMITER ;

CREATE TABLE `Departments` (
  `DeptID` int NOT NULL AUTO_INCREMENT,
  `DeptName` varchar(60) NOT NULL,
  `SupportChannels` char(16) NOT NULL,
  `SuperiorDept` tinyint DEFAULT -1,
  `ShiftType` tinyint DEFAULT -1,
  `AttendanceDay` varchar(7) DEFAULT '1234567',
  `AttendanceRule1Start` char(4) DEFAULT '0800',
  `AttendanceRule1End` char(4) DEFAULT '1200',
  `AttendanceRule2Start` char(4) DEFAULT '1300',
  `AttendanceRule2End` char(4) DEFAULT '1600',
  `ClearTime` char(4) DEFAULT '0000',
  `LateStartTime` time NOT NULL DEFAULT '09:00:00',
  `GraceMinutes` int NOT NULL DEFAULT 0,
  `LateEnabled` tinyint NOT NULL DEFAULT 1,
  `LocationBoundary` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DeptID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `positions` (
  `PosID` int NOT NULL AUTO_INCREMENT,
  `PosName` varchar(50) NOT NULL,
  `UpdatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PosID`),
  UNIQUE KEY `uk_positions_name` (`PosName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `AttendanceRecords` (
  `RecordID` bigint NOT NULL AUTO_INCREMENT,
  `StaffID` varchar(50) NOT NULL,
  `WorkStartTime` datetime DEFAULT NULL,
  `WorkEndTime` datetime DEFAULT NULL,
  `AttendanceStatus` int DEFAULT NULL,
  `LocatedSpot` int DEFAULT NULL,
  PRIMARY KEY (`RecordID`),
  KEY `idx_attendance_staff_day` (`StaffID`, `WorkStartTime`),
  CONSTRAINT `fk_attendance_staff`
    FOREIGN KEY (`StaffID`) REFERENCES `user` (`StaffID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `Departments` (
  `DeptID`, `DeptName`, `SupportChannels`, `SuperiorDept`, `ShiftType`,
  `AttendanceDay`, `AttendanceRule1Start`, `AttendanceRule1End`,
  `AttendanceRule2Start`, `AttendanceRule2End`
) VALUES
  (1, 'Second-Line Attendance Team', '1111111111111111', -1, 0, '1234567', '0830', '1200', '1300', '1630'),
  (2, 'Logistics Support Team', '1111111111111100', -1, 1, NULL, '0400', '0359', NULL, NULL),
  (3, 'Frontline Attendance Zone A', '1111111111110000', -1, 2, NULL, '0830', '1530', NULL, NULL),
  (4, 'Frontline Attendance Zone B', '1111111111110000', -1, 2, NULL, '0930', '1630', NULL, NULL),
  (5, 'Power Maintenance Command Electrician A', '1111111111000000', -1, 2, NULL, '0830', '1530', NULL, NULL),
  (6, 'Power Maintenance Command Electrician B', '1111111111000000', -1, 2, NULL, '0930', '1630', NULL, NULL),
  (7, 'Canyon Cruise Night Shift A', '1111111100000000', -1, 2, NULL, '1800', '0600', NULL, NULL),
  (8, 'Canyon Cruise Night Shift B', '1111111100000000', -1, 2, NULL, '1800', '0830', NULL, NULL),
  (9, 'Canyon Cruise Zone A', '1111111100000000', -1, 2, NULL, '0830', '1530', NULL, NULL),
  (10, 'Canyon Cruise Zone B', '1111111100000000', -1, 2, NULL, '0930', '1630', NULL, NULL),
  (11, 'ZJJ Canyon Ops Zone A', '1111110000000000', -1, 2, NULL, '0830', '1530', NULL, NULL),
  (12, 'ZJJ Canyon Ops Zone B', '1111110000000000', -1, 2, NULL, '0930', '1630', NULL, NULL),
  (13, 'Central Zone Night Shift', '1111000000000000', -1, 0, '1234567', '1600', '0830', NULL, NULL),
  (14, 'Power Maintenance Command 2 Office', '1111000000000000', -1, 2, NULL, '0830', '1200', '1300', '1630'),
  (15, 'Power Maintenance Command 2 Shift A', '1111000000000000', -1, 2, NULL, '0830', '1530', NULL, NULL),
  (16, 'Power Maintenance Command 2 Shift B', '1111000000000000', -1, 2, NULL, '0930', '1630', NULL, NULL),
  (17, 'Admin Attendance Office', '1100000000000000', -1, 2, NULL, '0830', '1200', '1300', '1630'),
  (18, 'Admin Department', '1100000000000000', -1, 2, NULL, '0830', '1600', NULL, NULL);

INSERT INTO `positions` (`PosID`, `PosName`, `UpdatedTime`) VALUES
  (1, 'Staff', '2023-06-01 10:00:00'),
  (2, 'Department Manager', '2023-06-02 15:00:00'),
  (3, 'General Manager', '2023-06-03 11:00:00'),
  (4, 'Director', '2023-06-04 14:30:00');

INSERT INTO `user` (
  `StaffID`, `StaffName`, `StaffGender`, `StaffBirthday`, `StaffIDCard`,
  `StaffDept`, `StaffTel`, `StaffPwd`, `StaffPosition`, `AttendanceStatus`, `HireDate`
) VALUES
  ('CS001', 'Codespaces Demo', 1, '1990-01-01', '430000199001011234', 1, '13800138000', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 2, 0, CURRENT_TIMESTAMP),
  ('CS002', 'Team Member', 0, '1992-06-18', '430000199206188888', 2, '13900139000', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, 0, CURRENT_TIMESTAMP);

SET FOREIGN_KEY_CHECKS = 1;