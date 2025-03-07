<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*, org.big.dto.*"%>
<%@ include file="dbconn.jsp"%>

<%
    String id = request.getParameter("id");

    // ID 값이 없거나 빈 문자열이면 books.jsp로 리디렉션
    if (id == null || id.trim().equals("")) {
        response.sendRedirect("/bookmarket/books.do");
        return;
    }

    // 데이터베이스에서 해당 책 조회
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Book book = null;

    try {
        String sql = "SELECT * FROM book WHERE b_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            book = new Book();
            book.setBookId(rs.getString("b_id"));
            book.setName(rs.getString("b_name"));
            book.setUnitPrice(rs.getInt("b_unitPrice"));
            book.setAuthor(rs.getString("b_author"));
            book.setPublisher(rs.getString("b_publisher"));
            book.setReleaseDate(rs.getString("b_releaseDate"));
            book.setDescription(rs.getString("b_description"));
            book.setCategory(rs.getString("b_category"));
            book.setUnitsInStock(rs.getInt("b_unitsInStock"));
            book.setCondition(rs.getString("b_condition"));
            book.setFilename(rs.getString("b_filename"));
            book.setQuantity(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        response.sendRedirect("/bookmarket/exceptionNoBookId.do");
        return;
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 책이 존재하지 않으면 예외 페이지로 이동
    if (book == null) {
        response.sendRedirect("/bookmarket/exceptionNoBookId.do");
        return;
    }

    // 세션에서 카트 리스트 가져오기
    ArrayList<Book> cartList = (ArrayList<Book>) session.getAttribute("cartlist");
    if (cartList == null) {
        cartList = new ArrayList<>();
        session.setAttribute("cartlist", cartList);
    }

    // 카트에서 해당 책 삭제
    cartList.removeIf(goodsQnt -> goodsQnt.getBookId().equals(id));

    // 카트 페이지로 이동
    response.sendRedirect("/bookmarket/cart.do");
%>
