-- 1) 删除旧表（避免重复建表报错）
DROP TABLE IF EXISTS Positions;

-- 2) 创建职级表
CREATE TABLE Positions (
  PosID INT PRIMARY KEY,                         -- 职级编号
  PosName VARCHAR(50) NOT NULL,                  -- 职级名称
  UpdatedTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 更新时间（默认当前时间）
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3) 插入数据
INSERT INTO Positions (PosID, PosName, UpdatedTime) VALUES
(1, '普通员工', '2023-06-01 10:00:00'),
(2, '部门主管', '2023-06-02 15:00:00'),
(3, '总经理',   '2023-06-03 11:00:00'),
(4, '董事',     '2023-06-04 14:30:00'),
(5, '',         '2023-06-04 14:30:00');
