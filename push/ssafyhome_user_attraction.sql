use ssafyhome;

-- user 테이블 생성
CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(16),
    id VARCHAR(45),
    pw VARCHAR(45),
    address VARCHAR(45),
    phonenumber VARCHAR(11)
);

-- attraction 테이블 생성
CREATE TABLE attraction (
    dong_id INT AUTO_INCREMENT PRIMARY KEY,
    dong_name VARCHAR(45),
    user_name VARCHAR(45),
    user_user_id INT,
    FOREIGN KEY (user_user_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);