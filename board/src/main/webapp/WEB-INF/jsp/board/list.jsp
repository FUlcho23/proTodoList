<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="org.big.dto.BbsDto"%>

<html>
<head>
<link rel="stylesheet" href="/css/bootstrap.min.css" />
<title>Board</title>
<%
   String sessionId = (String) session.getAttribute("sessionId");
   List boardList = (List) request.getAttribute("boardlist");
   if (boardList == null) {
       boardList = new ArrayList();
   }

   Integer totalRecordObj = (Integer) request.getAttribute("total_record");
   int total_record = (totalRecordObj != null) ? totalRecordObj.intValue() : 0;

   Integer pageNumObj = (Integer) request.getAttribute("pageNum");
   int pageNum = (pageNumObj != null) ? pageNumObj.intValue() : 1;

   Integer totalPageObj = (Integer) request.getAttribute("total_page");
   int total_page = (totalPageObj != null) ? totalPageObj.intValue() : 1;
%>

<script type="text/javascript">
   var sessionId = "<%=sessionId%>";

   function checkForm() {
      if (!sessionId || sessionId === "null") {
         alert("로그인 해주세요.");
         location.href = "/bookmarket/member/loginMember.do";
         return false;
      }

      location.href = "/bookmarket/BoardWriteForm.do?id=" + sessionId;
   }
</script>



</head>
<body>
<div class="container py-4">
   <jsp:include page="/bookmarket/menu.do" />
   
    <div class="p-5 mb-4 bg-body-tertiary rounded-3">
      <div class="container-fluid py-5">
        <h1 class="display-5 fw-bold">게시판</h1>
        <p class="col-md-8 fs-4">Board</p>      
      </div>
    </div>
   
   <div class="row align-items-md-stretch   text-center">       
      <form action="<c:url value="/bookmarket/BoardListAction.do"/>" method="post">
   
         
            <div class="text-end"> 
               <span class="badge text-bg-success">전체 <%=total_record%>건   </span>
            </div>
      
         <div style="padding-top: 20px">
            <table class="table table-hover text-center">
               <tr>
                  <th>번호</th>
                  <th>제목</th>
                  <th>작성일</th>
                  <th>조회</th>
                  <th>글쓴이</th>
               </tr>
               <%
               
                  for (int j = 0; j < boardList.size() ; j++){
                     
                     BbsDto notice = (BbsDto) boardList.get(j);
               %>
               <tr>
                  <td><%=notice.getNum()%></td>
                  <td><a href="/bookmarket/BoardViewAction.do?num=<%=notice.getNum()%>&pageNum=<%=pageNum%>"><%=notice.getSubject()%></a></td>
                  <td><%=notice.getRegist_day()%></td>
                  <td><%=notice.getHit()%></td>
                  <td><%=notice.getName()%></td>
               </tr>
               <%
                  }
               %>
            </table>
         </div>
         <div align="center">
            <c:set var="pageNum" value="<%=pageNum%>" />
            <c:forEach var="i" begin="1" end="<%=total_page%>">
               <a href="<c:url value="/bookmarket/BoardListAction.do?pageNum=${i}" /> ">
                  <c:choose>
                     <c:when test="${pageNum==i}">
                        <font color='4C5317'><b> [${i}]</b></font>
                     </c:when>
                     <c:otherwise>
                        <font color='4C5317'> [${i}]</font>

                     </c:otherwise>
                  </c:choose>
               </a>
            </c:forEach>
         </div>
         
         <div class="py-3" align="right">                     
            <a href="#" onclick="checkForm(); return false;" class="btn btn-primary">&laquo;글쓰기</a>            
         </div>         
         <div align="left">            
            <select name="items" class="txt">
               <option value="subject">제목에서</option>
               <option value="content">본문에서</option>
               <option value="name">글쓴이에서</option>
            </select> <input name="text" type="text" /> <input type="submit" id="btnAdd" class="btn btn-primary " value="검색 " />            
         </div>
         
      </form>         
   </div>
   <jsp:include page="/bookmarket/footer.do" />
</div>
</body>
</html>
