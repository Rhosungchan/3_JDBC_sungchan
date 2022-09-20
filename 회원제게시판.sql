

-- SYS 관리자 계정
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- 사용자 계정 생성
CREATE USER member_rsc IDENTIFIED BY member1234;

-- 생성한 사용자 계정에 권한 부여
GRANT CONNECT, RESOURCE, CREATE VIEW TO member_rsc;

-- 테이블 스페이스 할당 
ALTER USER member_rsc DEFAULT TABLESPACE SYSTEM QUOTA UNLIMITED ON SYSTEM;

----------------------------------------------------------------------------

--member_rsc 계정 

-- 회원 테이블 
-- 회원 번호, 아이디, 비밀번호, 이름, 성별, 가입일, 탈퇴여부
CREATE TABLE "MEMBER"(
	MEMBER_NO NUMBER PRIMARY KEY,
	MEMBER_ID VARCHAR2(30) NOT NULL,
	MEMBER_PW VARCHAR2(30) NOT NULL,
	MEMBER_NM VARCHAR2(30) NOT NULL,
	MEMBER_GENDER CHAR(1) CHECK(MEMBER_GENDER IN('M','F')),
	ENROLL_DATE DATE DEFAULT SYSDATE,
	SECESSION_FL CHAR(1) DEFAULT 'N' CHECK(SECESSION_FL IN('Y','N'))
);

COMMENT ON COLUMN "MEMBER".MEMBER_NO IS '회원번호';
COMMENT ON COLUMN "MEMBER".MEMBER_ID IS '회원 아이디';
COMMENT ON COLUMN "MEMBER".MEMBER_PW IS '회원 비밀번호';
COMMENT ON COLUMN "MEMBER".MEMBER_NM IS '회원 이름';
COMMENT ON COLUMN "MEMBER".MEMBER_GENDER IS '회원 성별';
COMMENT ON COLUMN "MEMBER".ENROLL_DATE IS '회원 가입입';
COMMENT ON COLUMN "MEMBER".SECESSION_FL IS '탈퇴여부(Y/N)';

-- 회원 번호 시퀀스 생성
CREATE SEQUENCE SEQ_MEMBER_NO 
START WITH 1 
INCREMENT BY 1
NOCYCLE 
NOCACHE;

-- 회원 가입 INSERT
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user01','pass01','유저일','M',DEFAULT, DEFAULT);

INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user02','pass02','유저이','F',DEFAULT, DEFAULT);

INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user03','pass03','유저삼','F',DEFAULT, DEFAULT);

SELECT * FROM "MEMBER";

COMMIT;

------------------참고----------------------
DROP TABLE "MEMBER"; -- 테이블 삭제
DROP SEQUENCE SEQ_MEMBER_NO; -- 시퀀스 삭제 
--------------------------------------------

-- 아이디 중복 확인
-- (중복되는 아이디가 입력되어도 탈퇴한 계정이면 중복 X)
SELECT COUNT(*) FROM "MEMBER"
WHERE MEMBER_ID = 'user1'
AND SECESSION_FL = 'N'; 
--> ID가 user01이면서 탈퇴하지 않은 회원 조회

-- 중복이면 1, 아니면 0 조회


-- [로그인]======================================
SELECT MEMBER_NO, MEMBER_ID, MEMBER_NM, MEMBER_GENDER,
	TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일" HH24:MI:SS')ENROLL_DATE
FROM "MEMBER"
WHERE MEMBER_ID = 'user01'
AND MEMBER_PW ='pass01'
AND SECESSION_FL = 'N';

-- 회원 목록 조회(아이디, 이름, 성별)
-- 탈퇴 회원 미포함, 가입일 내림차순
                      --> MEMBER_NO 내림차순(나중에 가입한 회원의 번호가 더 큼) 
SELECT MEMBER_ID , MEMBER_NM , MEMBER_GENDER
FROM "MEMBER"
WHERE SECESSION_FL = 'N'
ORDER BY MEMBER_NO  DESC;


-- 회원 정보 수정(이름, 성별)
UPDATE "MEMBER" SET
MEMBER_NM = '김효동',   -- 입력
MEMBER_GENDER = 'F'     -- 입력
WHERE MEMBER_NO = 1;    -- loginMember.getMemberNo();

SELECT * FROM "MEMBER";

ROLLBACK;

-- 비밀번호 변경 
UPDATE "MEMBER" SET
MEMBER_PW = '새 비밀번호'
WHERE MEMBER_NO = 1 
AND MEMBER_PW = '현재 비밀번호';

ROLLBACK;

-- 회원 탈퇴 (SECESSiON_FL 컬럼의 값을 'Y'로 변경)
UPDATE "MEMBER" SET
SECESSION_FL = 'Y'
WHERE MEMBER_NO  = ?
AND MEMBER_PW = ?;

SELECT * FROM "MEMBER";
UPDATE "MEMBER"  SET
SECESSION_FL  = 'N';

COMMIT;

















-- 테이블