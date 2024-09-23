use ssafydb;

create table board
(
	article_no INT NOT NULL AUTO_INCREMENT primary key,
	user_id VARCHAR(16) NULL DEFAULT NULL,
    subject VARCHAR(100) NULL DEFAULT NULL,
    content VARCHAR(2000) NULL DEFAULT NULL,
    register_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);