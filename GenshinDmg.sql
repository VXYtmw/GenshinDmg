CREATE DATABASE genshin_dmg;
USE genshin_dmg;

CREATE TABLE user (
    user_id INT(32) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(32) NOT NULL UNIQUE,
    user_password CHAR(64) NOT NULL,
    mail VARCHAR(320) NOT NULL UNIQUE,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE artifact (
    artifact_id INT(64) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id INT(32) UNSIGNED,
    artifact_set INT(16) UNSIGNED NOT NULL,
    slot INT(4) UNSIGNED NOT NULL,
    main_stat INT(8) UNSIGNED NOT NULL,
    main_value FLOAT NOT NULL,
    sec_stat1 INT(8) UNSIGNED NOT NULL,
    sec_stat2 INT(8) UNSIGNED NOT NULL,
    sec_stat3 INT(8) UNSIGNED NOT NULL,
    sec_stat4 INT(8) UNSIGNED NOT NULL,
    sec_value1 FLOAT NOT NULL,
    sec_value2 FLOAT NOT NULL,
    sec_value3 FLOAT NOT NULL,
    sec_value4 FLOAT NOT NULL,
    CONSTRAINT fk_artifact_user
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    CHECK (slot >= 1 AND slot <=5)
);

INSERT INTO user(user_name, user_password, mail) VALUES('arc', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ruicheng_ao@foxmail.com');  # password is '123'
INSERT INTO user(user_name, user_password, mail) VALUES('user1', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '2031449330@qq.com');  # password is '123'
INSERT INTO artifact(user_id, artifact_set, slot, main_stat, main_value, sec_stat1, sec_value1, sec_stat2, sec_value2, sec_stat3, sec_value3, sec_stat4, sec_value4) VALUES (1, 30, 4, 11, 0.466, 1, 39, 4, 0.053, 7, 40, 9, 0.097);
