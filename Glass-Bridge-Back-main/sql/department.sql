DROP TABLE IF EXISTS Departments;

CREATE TABLE Departments (
    DeptID INT AUTO_INCREMENT PRIMARY KEY COMMENT '部门ID，主键自增',
    DeptName VARCHAR(60) NOT NULL COMMENT '部门名，最长60字符',
    SupportChannels CHAR(16) NOT NULL COMMENT '菜单可视范围，定长16',

    SuperiorDept TINYINT NULL DEFAULT -1 COMMENT '-1为一级部门，其余为上级部门ID',
    ShiftType TINYINT NULL DEFAULT -1 COMMENT '0固定班制 1自由工时 2排班制',
    AttendanceDay VARCHAR(7) NULL DEFAULT '1234567' COMMENT '考勤日：对应周一二三四五六七',

    AttendanceRule1Start CHAR(4) NULL DEFAULT '0800' COMMENT '考勤规则1上班时间(HHMM)',
    AttendanceRule1End   CHAR(4) NULL DEFAULT '1200' COMMENT '考勤规则1下班时间(HHMM)',
    AttendanceRule2Start CHAR(4) NULL DEFAULT '1300' COMMENT '考勤规则2上班时间(HHMM)',
    AttendanceRule2End   CHAR(4) NULL DEFAULT '1600' COMMENT '考勤规则2下班时间(HHMM)',

    ClearTime CHAR(4) NULL DEFAULT '0000' COMMENT '强制下班时间(HHMM)',

    LateStartTime TIME NOT NULL DEFAULT '09:00:00' COMMENT '迟到判定时间，例如 09:00:00',
    GraceMinutes INT NOT NULL DEFAULT 0 COMMENT '宽限分钟，例如 5',
    LateEnabled TINYINT NOT NULL DEFAULT 1 COMMENT '是否启用迟到规则：0否1是',

    LocationBoundary VARCHAR(255) NULL DEFAULT NULL COMMENT '考勤定位边界'
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
ROW_FORMAT=Dynamic;