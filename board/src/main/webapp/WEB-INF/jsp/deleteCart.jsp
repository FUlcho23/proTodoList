<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.big.dto.*, org.big.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카트 내용물 삭제하는곳인가</title>
</head>
<body>
	<%
	String id = request.getParameter("cartId");
	if (id == null || id.trim().equals("")) {
		response.sendRedirect("/bookmarket/cart.do");
		return;
	}

	session.invalidate();
	response.sendRedirect("/bookmarket/cart.do");
	%>
</body>
</html>