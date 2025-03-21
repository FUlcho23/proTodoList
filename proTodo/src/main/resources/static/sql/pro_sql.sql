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

drop table todo_list;