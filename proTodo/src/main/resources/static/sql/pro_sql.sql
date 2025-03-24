
 -- 회원 테이블
 CREATE TABLE pro_member (
     member_id VARCHAR2(20) PRIMARY KEY,       -- 아이디
     m_password VARCHAR2(255) NOT NULL,   -- 비밀번호
     m_name VARCHAR2(20) NOT NULL,        -- 이름
     m_phone VARCHAR2(20) UNIQUE,         -- 전화번호
     m_email VARCHAR2(100) UNIQUE,        -- 이메일
     m_regist_day TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 가입일자
 );
 
 --게시글 테이블
CREATE TABLE pro_board (
     b_num NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,        -- 게시글 번호 (자동 증가)
     member_id VARCHAR(20) NOT NULL,                -- 작성자 아이디 (회원 테이블과 연결)
     b_subject VARCHAR(100) NOT NULL,          -- 게시글 제목
     b_content VARCHAR(255) NOT NULL,                  -- 게시글 내용
     b_regist_day TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 게시글 작성 일시 (현재 시간 자동 입력)
     b_hit INT,                                -- 조회수
     FOREIGN KEY (member_id) REFERENCES pro_member(member_id)
 );
 
 -- 팀 테이블
 CREATE TABLE pro_team (
     t_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- 팀 번호 (자동 증가)
     t_name VARCHAR2(100) NOT NULL,  -- 팀명
     member_id VARCHAR2(20),            -- 팀원 아이디 (member 테이블과 연결)
     t_role VARCHAR2(10) NOT NULL CHECK (t_role IN ('팀장', '팀원')),  -- 직책 (ENUM 대신 CHECK 사용)
     FOREIGN KEY (member_id) REFERENCES pro_member(member_id)  -- member 테이블과 연결
 );
--할일 테이블
CREATE TABLE todo_list(
    td_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,  -- 할 일 고유 ID (자동 증가) == calendarNo
    member_id VARCHAR2(20) NOT NULL,         -- 할 일을 생성한 회원 (팀원/팀장 모두 가능)
    td_work_m VARCHAR2(20),       -- 할 일이 배분된 팀원 (배분된 팀원 아이디)
    t_name VARCHAR2(20),                 -- 팀 정보
    td_todo VARCHAR2(255),              -- 일정
    td_status VARCHAR2(10) DEFAULT 'N' CHECK (td_status IN ('Y', 'N', 'P')),  -- 진행여부
    td_file VARCHAR2(255),        -- 첨부파일 (선택 사항) (힝)
    td_hidden NUMBER(1) DEFAULT 0 CHECK (td_hidden IN (0, 1)), -- 공개여부(기본 비공개0)
    td_end TIMESTAMP NOT NULL,             -- 마감일(시작일도 만들어야 할지도) == end
    td_start TIMESTAMP NOT NULL,             -- 시작일 == start1
    td_allday NUMBER(1) DEFAULT 0 CHECK (td_allday IN (0, 1)), --하루종일인가 == allDay
    FOREIGN KEY (member_id) REFERENCES pro_member(member_id)
);
