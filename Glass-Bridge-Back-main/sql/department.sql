-- 1. 先删除已存在的同名表（可选，避免建表冲突）
DROP TABLE IF EXISTS Departments;

-- 2. 创建部门表（修正语法错误+优化字段设计）
CREATE TABLE Departments (
    DeptID INT AUTO_INCREMENT PRIMARY KEY,        -- 部门ID，主键自增
    DeptName VARCHAR(60) NOT NULL,                 -- 部门名，最长60字符
    SupportChannels CHAR(16) NOT NULL,             -- 菜单可视范围，定长16

    -- 优化：用TINYINT替代INT(1)，更节省空间，语义更清晰
    SuperiorDept TINYINT NULL DEFAULT -1 COMMENT '-1为一级部门，其余为上级部门ID',
    ShiftType TINYINT NULL DEFAULT -1 COMMENT '0固定班制 1自由工时 2排班制',
    AttendanceDay VARCHAR(7) NULL DEFAULT '1234567' COMMENT '考勤日：对应周一二三四五六七',

    AttendanceRule1Start CHAR(4) NULL DEFAULT '0800' COMMENT '考勤规则1上班时间(HHMM)',
    AttendanceRule1End   CHAR(4) NULL DEFAULT '1200' COMMENT '考勤规则1下班时间(HHMM)',
    AttendanceRule2Start CHAR(4) NULL DEFAULT '1300' COMMENT '考勤规则2上班时间(HHMM)',
    AttendanceRule2End   CHAR(4) NULL DEFAULT '1600' COMMENT '考勤规则2下班时间(HHMM)',

    ClearTime CHAR(4) NULL DEFAULT '0000' COMMENT '强制下班时间(HHMM)'  -- 修正：删除末尾多余逗号
) ENGINE=InnoDB
CHARACTER SET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
ROW_FORMAT=Dynamic;
