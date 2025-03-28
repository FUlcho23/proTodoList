<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>	
	 <div class = "container py-4">
		<jsp:include page="/bookmarket/menu.do" />  
	
		<%!
			String greeting = "Welcome to Book Shopping Mall";
			String tagline = "Welcome to Web Market!";
		%>
		
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold"><%=greeting %></h1>
				<p class="col-md-8 fs-4">BookMarket</p>
			</div>
		</div>
		
		<div class="row align-items-md-stretch text-center">
			<div class="col-md-12">
				<div class="h-100 p-5">
					<h3><%=tagline %></h3>
					<%
						response.setIntHeader("Refresh", 5);
						Date day = new Date();
						String am_pm;
						int hour = day.getHours();
						int minute = day.getMinutes();
						int second = day.getSeconds();
						
						if(hour / 12 == 0){
							am_pm = "AM";
						}else {
							am_pm = "PM";
							hour = hour - 12;
						}
						String CT = hour + ":" + minute + ":" + second + ":" + am_pm;
						out.println("현재 접속 시간: " + CT + "\n");
					%>
				</div>
			</div>
		</div>
		
		<jsp:include page="/bookmarket/footer.do" />
	</div>

</body>
</html>





