
# MySQL 핵심 정리

## 1. MySQL의 주요 목적
- 데이터를 **삽입, 조회, 수정, 삭제**하는 것이 기본 기능
- 실제 작업의 **90% 이상**은 CRUD 문법으로 처리 가능
- 하지만 진짜 중요한 건 **ERD 설계 + 제약 조건 + 무결성 유지**

---

## 2. 데이터 조작 (CRUD)

### 2-1. INSERT INTO – 데이터 삽입
```
sql
INSERT INTO 테이블명 (컬럼1, 컬럼2, ...)
VALUES (값1, 값2, ...);
예시:

sql

INSERT INTO USERS (user_id, email, name)
VALUES (1, 'minsu@example.com', '민수');
2-2. SELECT FROM – 데이터 조회
sql

SELECT 컬럼1, 컬럼2, ...
FROM 테이블명
WHERE 조건;
예시:

sql

SELECT name, email
FROM USERS
WHERE user_id = 1;
2-3. UPDATE SET – 데이터 수정
sql
UPDATE 테이블명
SET 컬럼1 = 값1, 컬럼2 = 값2, ...
WHERE 조건;
예시:

sql

UPDATE USERS
SET name = '민수정'
WHERE user_id = 1;
2-4. DELETE FROM – 데이터 삭제
sql

DELETE FROM 테이블명
WHERE 조건;
예시:

sql

DELETE FROM USERS
WHERE user_id = 1;
```
3. 테이블 구조 정의와 제약 조건
3-1. CREATE TABLE – 테이블 생성
sql
```
CREATE TABLE 테이블명 (
    컬럼명 데이터타입 제약조건,
    ...
    PRIMARY KEY (컬럼명),
    FOREIGN KEY (컬럼명) REFERENCES 다른테이블(컬럼명)
```

| 제약조건           | 설명                            |
| -------------- | ----------------------------- |
| NOT NULL       | NULL 값 입력 불가                  |
| UNIQUE         | 중복 값 불가                       |
| PRIMARY KEY    | 유일한 식별자, 자동 NOT NULL + UNIQUE |
| FOREIGN KEY    | 다른 테이블과 연결, 참조 무결성 유지         |
| CHECK          | 입력 값 조건 제한                    |
| DEFAULT        | 값이 없을 때 기본값 지정                |
| AUTO_INCREMENT | 숫자 컬럼 자동 증가                   |


4. 테이블 관계 설계

테이블 간 관계는 ERD로 표현

관계 유형:

1:1

1:N

N:M → 중간 테이블 생성으로 해결

예시: USERS ↔ COURSES ↔ ENROLLMENTS

5. MySQL 실습 흐름

ERD 설계 → 테이블, 컬럼, 관계 정의

CREATE TABLE로 테이블 생성

INSERT INTO로 데이터 삽입

SELECT, UPDATE, DELETE로 데이터 조회/수정/삭제

제약 조건과 관계를 통해 데이터 무결성 유지

6. 핵심 포인트

CRUD 문법 = MySQL 작업의 90% 이상

하지만 중요한 건:

효율적인 테이블 구조 설계

제약 조건 설정

데이터 무결성 유지

결국 DB 설계는 단순 문법이 아니라 창의적 설계 + 안정성 확보가 핵심
