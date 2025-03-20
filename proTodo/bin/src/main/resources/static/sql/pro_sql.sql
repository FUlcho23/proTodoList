CREATE TABLE pro_board (
    b_num NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,        -- 게시글 번호 (자동 증가)
    member_id VARCHAR(20) NOT NULL,                -- 작성자 아이디 (회원 테이블과 연결)
    b_subject VARCHAR(100) NOT NULL,          -- 게시글 제목
    b_content VARCHAR(255) NOT NULL,                  -- 게시글 내용
    b_regist_day TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 게시글 작성 일시 (현재 시간 자동 입력)
    b_hit INT,                                -- 조회수
    FOREIGN KEY (member_id) REFERENCES pro_member(member_id)
);
drop table pro_board;
-- 회원 테이블
CREATE TABLE pro_member (
    member_id VARCHAR2(20) PRIMARY KEY,       -- 아이디
    m_password VARCHAR2(255) NOT NULL,   -- 비밀번호
    m_name VARCHAR2(20) NOT NULL,        -- 이름
    m_phone VARCHAR2(20) UNIQUE,         -- 전화번호
    m_email VARCHAR2(100) UNIQUE,        -- 이메일
    m_regist_day TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 가입일자
);

-- 팀 테이블
CREATE TABLE pro_team (
    t_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- 팀 번호 (자동 증가)
    t_name VARCHAR2(100) NOT NULL,  -- 팀명
    member_id VARCHAR2(20),            -- 팀원 아이디 (member 테이블과 연결)
    t_role VARCHAR2(10) NOT NULL CHECK (t_role IN ('팀장', '팀원')),  -- 직책 (ENUM 대신 CHECK 사용)
    FOREIGN KEY (member_id) REFERENCES pro_member(member_id)  -- member 테이블과 연결
);

-- 할 일 테이블
CREATE TABLE todo_list (
    todo_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,  -- 할 일 고유 ID (자동 증가)
    member_id VARCHAR2(20) NOT NULL,         -- 할 일을 생성한 회원 (팀원/팀장 모두 가능)
    assigned_to VARCHAR2(20),       -- 할 일이 배분된 팀원 (배분된 팀원 아이디)
    team_id NUMBER,                 -- 팀 정보
    todo_task_content VARCHAR2(255) NOT NULL,              -- 할 일 내용 
    todo_status VARCHAR2(10) DEFAULT 'N' CHECK (todo_status IN ('Y', 'N', 'P')),  -- 진행여부
    todo_file_path VARCHAR2(255),        -- 첨부파일 (선택 사항)
    todo_visible NUMBER(1) DEFAULT 0 CHECK (todo_visible IN (0, 1)), -- 공개여부(기본 비공개0)
    todo_due_date TIMESTAMP NOT NULL,             -- 마감일
    CREATED_DAY TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 일정 생성일자
    FOREIGN KEY (member_id) REFERENCES pro_member(member_id),
    FOREIGN KEY (assigned_to) REFERENCES pro_member(member_id),
    FOREIGN KEY (team_id) REFERENCES pro_team(t_id)
);


