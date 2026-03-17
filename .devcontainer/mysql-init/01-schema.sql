USE oa;

DROP TABLE IF EXISTS `AttendanceRecords`;
DROP TABLE IF EXISTS `positions`;
DROP TABLE IF EXISTS `Departments`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `StaffID` varchar(50) NOT NULL,
  `StaffName` varchar(50) NOT NULL DEFAULT 'None',
  `StaffGender` tinyint DEFAULT NULL,
  `StaffBirthday` date DEFAULT NULL,
  `StaffIDCard` varchar(20) DEFAULT NULL,
  `AttendanceStatus` int DEFAULT 0,
  `StaffDept` int DEFAULT NULL,
  `StaffTel` varchar(20) NOT NULL,
  `StaffPwd` varchar(255) NOT NULL,
  `StaffPosition` int DEFAULT 0,
  `HireDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`StaffID`),
  UNIQUE KEY `uk_staff_tel` (`StaffTel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `Departments` (
  `DeptID` int NOT NULL AUTO_INCREMENT,
  `DeptName` varchar(60) NOT NULL,
  `SupportChannels` char(16) NOT NULL,
  `LateStartTime` time NOT NULL DEFAULT '09:00:00',
  `GraceMinutes` int NOT NULL DEFAULT 0,
  `LateEnabled` tinyint NOT NULL DEFAULT 1,
  PRIMARY KEY (`DeptID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `positions` (
  `PosID` int NOT NULL AUTO_INCREMENT,
  `PosName` varchar(50) NOT NULL,
  `UpdatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PosID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `AttendanceRecords` (
  `RecordID` bigint NOT NULL AUTO_INCREMENT,
  `StaffID` varchar(50) NOT NULL,
  `WorkStartTime` datetime DEFAULT NULL,
  `WorkEndTime` datetime DEFAULT NULL,
  `AttendanceStatus` int DEFAULT 0,
  `LocatedSpot` int DEFAULT NULL,
  PRIMARY KEY (`RecordID`),
  KEY `idx_attendance_staff_day` (`StaffID`, `WorkStartTime`),
  CONSTRAINT `fk_attendance_staff` FOREIGN KEY (`StaffID`) REFERENCES `user` (`StaffID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `Departments` (`DeptID`, `DeptName`, `SupportChannels`, `LateStartTime`, `GraceMinutes`, `LateEnabled`) VALUES
  (1, 'Default Department', '1111111111111111', '09:00:00', 5, 1),
  (2, 'Operations', '1111000011110000', '08:30:00', 0, 1);

INSERT INTO `positions` (`PosID`, `PosName`, `UpdatedTime`) VALUES
  (1, 'Staff', CURRENT_TIMESTAMP),
  (2, 'Manager', CURRENT_TIMESTAMP);

INSERT INTO `user` (`StaffID`, `StaffName`, `StaffGender`, `StaffBirthday`, `StaffIDCard`, `AttendanceStatus`, `StaffDept`, `StaffTel`, `StaffPwd`, `StaffPosition`, `HireDate`) VALUES
  ('CS001', 'Codespaces Demo', 1, '1990-01-01', '430000199001011234', 0, 1, '13800138000', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 2, CURRENT_TIMESTAMP),
  ('CS002', 'Team Member', 0, '1992-06-18', '430000199206188888', 0, 2, '13900139000', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, CURRENT_TIMESTAMP);
