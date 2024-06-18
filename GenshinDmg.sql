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
    suit INT(16) UNSIGNED NOT NULL,
    part INT(4) UNSIGNED NOT NULL,
    primary_attribute INT(8) UNSIGNED NOT NULL,
    primary_value FLOAT NOT NULL,
    secondary_attribute1 INT(8) UNSIGNED NOT NULL,
    secondary_attribute2 INT(8) UNSIGNED NOT NULL,
    secondary_attribute3 INT(8) UNSIGNED NOT NULL,
    secondary_attribute4 INT(8) UNSIGNED NOT NULL,
    secondary_value1 FLOAT NOT NULL,
    secondary_value2 FLOAT NOT NULL,
    secondary_value3 FLOAT NOT NULL,
    secondary_value4 FLOAT NOT NULL,
    CONSTRAINT fk_artifact_user
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    CHECK (part >= 1 AND part <=5)
);

INSERT INTO user(user_name, user_password, mail) VALUES('arc', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ruicheng_ao@foxmail.com');  # password is '123'
INSERT INTO user(user_name, user_password, mail) VALUES('user1', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '2031449330@qq.com');  # password is '123'
INSERT INTO artifact(user_id, suit, part, primary_attribute, primary_value, secondary_attribute1, secondary_value1, secondary_attribute2, secondary_value2, secondary_attribute3, secondary_value3, secondary_attribute4, secondary_value4) VALUES (1, 30, 4, 11, 0.466, 1, 39, 4, 0.053, 7, 40, 9, 0.097);
