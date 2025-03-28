<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.oreilly.servlet.*, com.oreilly.servlet.multipart.*, java.sql.*"%>
<%@ include file="dbconn.jsp"%>

<%
    request.setCharacterEncoding("UTF-8");

    String filename = "";
    String realFolder = application.getRealPath("/resources/images");

    int maxSize = 5 * 1024 * 1024;
    String encType = "utf-8";

    MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());

    String bookId = multi.getParameter("bookId");  // 기존 오타 수정
    String name = multi.getParameter("name");
    String unitPrice = multi.getParameter("unitPrice");
    String author = multi.getParameter("author");
    String publisher = multi.getParameter("publisher");
    String releaseDate = multi.getParameter("releaseDate");
    String description = multi.getParameter("description");
    String category = multi.getParameter("category");
    String unitsInStock = multi.getParameter("unitsInStock");
    String condition = multi.getParameter("condition");

    Enumeration files = multi.getFileNames();
    String fname = (String) files.nextElement();
    String fileName = multi.getFilesystemName(fname);

    int price = 0;
    long stock = 0;

    try {
        if (unitPrice != null && !unitPrice.isEmpty()) {
            price = Integer.parseInt(unitPrice);
        }
    } catch (NumberFormatException e) {
        price = 0;
    }

    try {
        if (unitsInStock != null && !unitsInStock.isEmpty()) {
            stock = Long.parseLong(unitsInStock);
        }
    } catch (NumberFormatException e) {
        stock = 0;
    }

    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String sql = "SELECT * FROM book WHERE b_id=?";
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, bookId);
    rs = pstmt.executeQuery();

    if (rs.next()) {
        rs.close();
        pstmt.close(); // 기존 pstmt 닫기

        if (fileName != null && !fileName.isEmpty()) {  // 빈 문자열도 체크
            sql = "UPDATE book SET b_name=?, b_unitPrice=?, b_author=?, b_description=?, b_publisher=?, b_category=?, b_unitsInStock=?, b_releaseDate=?, b_condition=?, b_fileName=? WHERE b_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setString(3, author);
            pstmt.setString(4, description);
            pstmt.setString(5, publisher);
            pstmt.setString(6, category);
            pstmt.setLong(7, stock);
            pstmt.setString(8, releaseDate);
            pstmt.setString(9, condition);
            pstmt.setString(10, fileName);
            pstmt.setString(11, bookId);
        } else {
            sql = "UPDATE book SET b_name=?, b_unitPrice=?, b_author=?, b_description=?, b_publisher=?, b_category=?, b_unitsInStock=?, b_releaseDate=?, b_condition=? WHERE b_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setString(3, author);
            pstmt.setString(4, description);
            pstmt.setString(5, publisher);
            pstmt.setString(6, category);
            pstmt.setLong(7, stock);
            pstmt.setString(8, releaseDate);
            pstmt.setString(9, condition);
            pstmt.setString(10, bookId);
        }
        pstmt.executeUpdate();
    }

    if (pstmt != null) pstmt.close();
    if (conn != null) conn.close();

    response.sendRedirect("editBook.jsp?edit=update");
%>
