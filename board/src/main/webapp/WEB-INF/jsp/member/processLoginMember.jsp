<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<sql:setDataSource var="dataSource"
   url="jdbc:mysql://localhost:3306/BookMarket"
   driver="com.mysql.cj.jdbc.Driver" user="root" password="1111" />

<sql:query dataSource="${dataSource}" var="resultSet">
    SELECT * FROM MEMBER WHERE ID=? AND PASSWORD=?  
    <sql:param value="${param.id}" />
    <sql:param value="${param.password}" />
</sql:query>

<c:choose>
    <c:when test="${not empty resultSet.rows}">
        <c:set var="sessionId" value="${param.id}" scope="session" />
        <c:redirect url="/bookmarket/member/resultMember.do?msg=2" />
    </c:when>
    <c:otherwise>
        <c:redirect url="/bookmarket/member/loginMember.do?error=1" />
    </c:otherwise>
</c:choose>
