#board 테이블 생성
drop table tb_board;
create table tb_board (
	bno int(100) not null AUTO_INCREMENT,
	btitle varchar(50) not null,
	bwriter varchar(50) not null,
	bdate varchar(50) not null DEFAULT sysdate(),
	bcontents varchar(100) not null,
	bcategory varchar(20) not null  DEFAULT '일반게시판',
	primary key (bno)
);

#boardInsert 프로시저 (150개)
DROP PROCEDURE IF EXISTS boardInsert
 
CREATE PROCEDURE boardInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
        
    WHILE i <= 150 DO
        INSERT INTO tb_board(btitle, bwriter, bdate, bcontents, bcategory)
          VALUES(concat('제 목',i), concat('user', i), sysdate(), concat('내 용 ', i), '일반게시판');
        set i = i + 1;
    END WHILE;
END

CALL boardInsert;

#boardInsert 프로시저 (150개)
DROP PROCEDURE IF EXISTS boardInsert2
 
CREATE PROCEDURE boardInsert2()
BEGIN
    DECLARE i INT DEFAULT 151;
        
    WHILE i <= 300 DO
        INSERT INTO tb_board(btitle, bwriter, bdate, bcontents, bcategory)
          VALUES(concat('제 목',i), 'user1', sysdate(), concat('내 용 ', i), '일반게시판');
        set i = i + 1;
    END WHILE;
END

CALL boardInsert2;