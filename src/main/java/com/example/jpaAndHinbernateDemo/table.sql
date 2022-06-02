-- 員工
CREATE TABLE Employee
(
    ID        bigint NOT NULL AUTO_INCREMENT,
    FirstName      VARCHAR(50) COMMENT '姓名',
    LastName   VARCHAR(50) COMMENT '帳號',
    DeptID bigint not null default '0',
    PRIMARY KEY (ID)
) COMMENT '員工'
ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 DEFAULT COLLATE=utf8mb3_general_ci;

-- 部門
CREATE TABLE Department
(
    ID        bigint NOT NULL AUTO_INCREMENT,
    Name      VARCHAR(50) COMMENT '部門名稱',
    PRIMARY KEY (ID)
) COMMENT '部門'
ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 DEFAULT COLLATE=utf8mb3_general_ci;

-- ALTER TABLE
--     `hibernate_demo`.`Employee` ADD CONSTRAINT Employee_fk1 FOREIGN KEY (`DeptID`) REFERENCES
--     `hibernate_demo`.`Department` (`ID`)