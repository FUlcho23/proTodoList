drop table t_file;

create table t_file(
   idx NUMBER(10) NOT NULL PRIMARY KEY,
   board_idx NUMBER(11) NOT NULL,
   original_file_name  VARCHAR2(300) NOT NULL,
   stored_file_path VARCHAR2(500) NOT NULL,
   file_size NUMBER(15) NOT NULL,
   creator_id VARCHAR2(50) NOT NULL,
   created_datetime DATE  DEFAULT SYSDATE,
   updator_id VARCHAR2(50) DEFAULT NULL,
   updated_datetime DATE  DEFAULT NULL,
   deleted_yn CHAR(1) DEFAULT 'N' NOT NULL
 );
 
drop sequence tfile_seq;
CREATE SEQUENCE tfile_seq
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 9999
       NOCYCLE
       NOCACHE
       NOORDER;

