<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<%
	Connection conn = null;
	
	try{
		String url = "jdbc:mysql://localhost:3306/bookmarket";
		String user = "root";
		String password = "1111";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, password);
	}catch(SQLException e){
		out.println("DB연결 실패");
		out.println("SQLException: "+e.getMessage());
	}

	
%>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DB연결 관리하는 파일</title>
</head>
<body>

</body>
</html>