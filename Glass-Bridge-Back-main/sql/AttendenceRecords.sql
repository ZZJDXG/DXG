DROP TABLE IF EXISTS `AttendanceRecords`;
CREATE TABLE `AttendanceRecords` (
  `StaffID` VARCHAR(50) NOT NULL COMMENT '员工ID',
  `WorkStartTime` DATETIME NULL DEFAULT NULL COMMENT '上班时间',
  `WorkEndTime` DATETIME NULL DEFAULT NULL COMMENT '下班时间',
  `AttendanceStatus` INT NULL DEFAULT NULL COMMENT '出勤状态：0出勤，1下班，2请假，3迟到',
  `LocatedSpot` INT NULL DEFAULT NULL COMMENT '打卡点：0123456代表点位',
  CONSTRAINT `fk_attendance_staff`
    FOREIGN KEY (`StaffID`) REFERENCES `user`(`StaffID`)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;