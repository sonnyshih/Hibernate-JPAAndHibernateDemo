-- 動物
CREATE TABLE Animal
(
    ID        bigint NOT NULL AUTO_INCREMENT,
    Name      VARCHAR(50) COMMENT '動物名稱',
    PRIMARY KEY (ID)
) COMMENT '動物'
ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 DEFAULT COLLATE=utf8mb3_general_ci;
