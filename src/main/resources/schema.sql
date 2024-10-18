-- posts 테이블(MySQL의 경우 필요)
CREATE TABLE IF NOT EXISTS posts (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(255),
                                     content VARCHAR(255),
                                     author VARCHAR(255)
);
