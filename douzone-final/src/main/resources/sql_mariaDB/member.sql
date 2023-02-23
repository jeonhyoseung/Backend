#member 테이블 생성
create table tb_member (
	user_id varchar(50) not null,
	user_name varchar(50) not null,
	user_pwd varchar(50) not null,
	user_phone varchar(50) not null,
	user_email varchar(100) not null,
	user_birth varchar(20) not null,
	primary key (user_id)
)

#memberInsert 프로시저 (150개)
DROP PROCEDURE IF EXISTS memberInsert
 
CREATE PROCEDURE memberInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
        
    WHILE i <= 150 DO
        INSERT INTO tb_member(user_id, user_name, user_pwd, user_phone, user_email, user_birth)
          VALUES(concat('user',i), '최수진', concat('pwd',i), '01012341234', concat(concat('tnwls', i),'@naver.com'), '1998-07-29');
        set i = i + 1;
    END WHILE;
END

CALL memberInsert;