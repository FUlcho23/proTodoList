<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String sessionId = (String) session.getAttribute("sessionId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* 헤더 디자인 */
header {
   padding: 20px 0;
}

/* 웰컴 버튼 디자인 */
.welcome-link {
   display: flex;
   align-items: center;
   text-decoration: none;
   color: #333;
   font-size: 24px;
   font-weight: bold;
}

.welcome-link svg {
   width: 32px;
   height: 32px;
   fill: currentColor;
   margin-right: 10px;
}

.welcome-link:hover {
   color: #007bff;
}

/* 도서목록 관련 버튼 디자인 */
.nav-link {
   font-size: 18px;
   font-weight: bold;
   color: #333;
   transition: background-color 0.3s, color 0.3s;
   text-decoration: none;
}

.nav-link:hover {
   background-color: #007bff;
   color: #fff;
}
</style>
</head>
<body>
   <header class="pb-3 mb-4 border-bottom">
      <div class="navbar">
         <a href="<%= request.getContextPath() %>/bookmarket/welcome.do" class="welcome-link"> <svg width="32"
               height="32" fill="currentColor" class="bi bi-house-fill"
               viewBox="0 0 16 16">
               <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5Z" />
               <path d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6Z" />
            </svg> <span class="fs-4">Home</span>
         </a>

         <ul class="nav nav-pills">
            <c:choose>
               <c:when test="${empty sessionScope.sessionId}">
                  <li class="nav-item"><a class="nav-link" href="<c:url value="/bookmarket/member/loginMember.do"/>">로그인</a></li>
                  <li class="nav-item"><a class="nav-link" href="<c:url value="/bookmarket/member/addMember.do"/>">회원 가입</a></li>
               </c:when>
               <c:otherwise>
                  <li style="padding-top : 7px; color:white">[<%=sessionId %>님]</li>
                  <li class="nav-item"><a class="nav-link" href="<c:url value="/bookmarket/member/logoutMember.do"/>">로그아웃</a></li>
                  <li class="nav-item"><a class="nav-link" href="<c:url value="/bookmarket/member/updateMember.do"/>">회원 수정</a></li>
               </c:otherwise>
            </c:choose>
            
            <!-- 도서 목록 관련 버튼들 -->
            <a href="<%= request.getContextPath() %>/bookmarket/books.do" class="nav-link">도서 목록</a>
            <a href="<%= request.getContextPath() %>/bookmarket/addBook.do" class="nav-link">도서 추가</a>
            <a href="<%= request.getContextPath() %>/bookmarket/editBook.do?edit=update" class="nav-link">도서 수정</a>
            <a href="<%= request.getContextPath() %>/bookmarket/editBook.do?edit=delete" class="nav-link">도서 삭제</a>
            <a href="<c:url value='/bookmarket/BoardListAction.do?pageNum=1' />" class="nav-link">게시판</a>
            
         </ul>
      </div>
   </header>
</body>
</html>
