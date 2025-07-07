<h2>1. 프로젝트명</h2>
<br>
<text>팀별 일정 관리 Todo List 웹 사이트</text>
<h2>2. 프로젝트 설명</h2>
<h3>- 프로젝트 개발목표</h3>
1) 팀원 간 소통 부족으로 일정 관리에 문제가 생기는걸 방지함으로 프로젝트 효율 증가<br>
2) 개인 일정과 팀원 일정을 함께 확인함으로써, 팀 내 일정과 개인 일정을 조율하는데 용이<br>
3) 일정 진행도를 공유함으로써 얼마나 일이 진행되고 있는지 파악하기 편리<br>
<h3>- 프로젝트 선정 배경</h3>
팀원들과 프로젝트를 진행할 일이 많아지면서 각자의 개인적인 일정이나 진도가 궁금한 경우가 종종 생기고는 했습니다.
그러나 이러한 정보를 직접 물어보는 건 어색하거나 불편한 사이인 경우 소통이 잘 되지 않기 때문에 일정을 잡을때 곤란한 경우가 있었습니다. 또한, 팀원들과 일정을 조율할 때 각자의 중요한 일정이 무엇인지 공유되지 않으면, 일정을 겹쳐서 잡는 일이 생기기도 했습니다. 그래서 팀원들 간의 일정과 진도를 효율적으로 공유하고 관리할 수 있는 Todo가 있으면 좋겠다는 생각에 이 웹페이지를 개발하게 되었습니다.<br>
<h3>- 페이지 설계 및 기능</h3>
<b>메뉴</b></br>
   - 메인, TODO, 팀, 게시판, 마이페이지, 로그인 페이지 진입</br>
   - 로그아웃 실행</br><br>
<b>푸터</b></br>
   - 페이지 정보</br><br>
<b>메인 페이지</b></br>
   - 페이지 소개 문구</br><br>
<b>로그인 페이지</b></br>
   - 아이디, 비밀번호를 이용해 로그인</br><br>
<b>회원가입 페이지</b></br>
   - 입력된 정보로 회원 가입</br><br>
<b>마이 페이지</b></br>
   - 회원정보 수정</br><br>
<b>게시판 페이지</b></br>
   - 게시글을 등록, 삭제, 수정</br>
   - 게시글 상세페이지</br>
   - 조회수 카운트</br><br>
<b>팀 페이지</b></br>
   - 팀 생성, 삭제, 탈퇴</br>
   - 팀 구성원 확인</br>
   - 팀 관리 페이지 진입</br><br>
<b>팀 관리 페이지</b></br>
   - 팀원추가, 삭제</br><br>
<b>일정 관리(TODO) 페이지</b></br>
   - 일정을 등록, 수정, 삭제</br>
   - 일정의 진행사항 변경</br>
   - 팀원의 일정 조회</br>
   - 팀원과 일정 공유, 공유취소</br>

<h3>- 사용된 기술</h3>
운영체제
: 윈도우11<br>
사용언어
: HTML5, CSS3, javaScript, jQuery, Ajax<br>
FrameWork / Library
: Spring<br>
DB
: Oracle<br>
Tool
: Eclipse<br>
WAS
: Tomcat<br>
Collaboration
: GitHub<br>
API
: FullCalendar JavaScript API (v6.1.15)<br>

<h3>📁코드 설계</h3>

```
proTodo
├── src/main/java
│   ├── org.big
│   ├── org.big.aop
│   ├── org.big.common
│   ├── org.big.configuration
│   ├── org.big.controller
│   ├── org.big.dto
│   ├── org.big.filter
│   ├── org.big.mapper
│   └── org.big.service
└── src/main/resources
    ├── mapper
    ├── static
    │  ├── css
    │  ├── fullcalendar-6.1.15
    │  └── sql
    └── templates
       └── board
```

<h3>- 흐름도</h3>

![1](https://github.com/user-attachments/assets/6692e68a-ada2-4559-9baa-9d47f25c75ca)

![2](https://github.com/user-attachments/assets/6f713e00-6036-4401-aac3-178ac5b3e37b)

<h2>3. 프로젝트 화면</h2>
 - 메인
 
 ![메인](https://github.com/user-attachments/assets/684b6a2e-b0fe-4983-8a20-5d695c0fbc83)

 - 로그인+회원가입

![로그인+회원가입](https://github.com/user-attachments/assets/d8a55221-4999-4cac-bde6-471ee2ffccdf)


 - 마이페이지

![마이페이지 (4)](https://github.com/user-attachments/assets/dabcc999-1309-44eb-988c-e8379612d2c3)


 - 게시판

![게시판 (1)](https://github.com/user-attachments/assets/42f0500b-fa8f-40cf-9b9a-c41f75f1de66)


 - 팀

![team](https://github.com/user-attachments/assets/8cb6d425-3f73-48ef-9205-27dfc6d6a296)


 - 일정관리

![일정관리창](https://github.com/user-attachments/assets/980359a4-fc30-4fc1-891b-7e4bdf82f453)


<h2>4. 팀원 및 참고자료</h2>
<h3>-팀원</h3>
개인 프로젝트
<h3>-참고자료</h3>
[FullCalendar JavaScript API (v6.1.15)](https://fullcalendar.io/docs/initialize-globals)</br>
[캘린터 API활용법](https://jungmin5600.tistory.com/42)</br>
<h2>5. 발표에 사용된 PPT</h2>

[[최종]팀별 일정 관리 웹 페이지(개인).pdf](https://github.com/user-attachments/files/21100415/default.pdf)
