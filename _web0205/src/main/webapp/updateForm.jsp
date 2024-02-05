<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String url="jdbc:oracle:thin:@localhost:1521:xe";
Connection conn = DriverManager.getConnection(url, "scott", "tiger");
String sql = "select * from score where num = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, request.getParameter("num"));
ResultSet rs = pstmt.executeQuery();
String num = rs.getString("num");
String name = rs.getString("name");
String kor = rs.getString("kor");
String eng = rs.getString("eng");
String math = rs.getString("math");
if (rs.next()) {  // 왜 if 일까? 무조건 1건(행, 레코드);
	
}
%>
<a href="index.jsp">처음으로</a>
<form action="insert.jsp" method="post">
번호<input type="text" name="num" value="<%=num %>"/><br/>
이름<input type="text" name="name" value="<%=name %>"/><br/>
국어<input type="text" name="kor" value="<%=kor %>"/><br/>
영어<input type="text" name="eng" value="<%=eng %>"/><br/>
수학<input type="text" name="math" value="<%=math %>" /><br/>
<input type="submit" value="확인"/>
<input type="reset" />
</form>
</body>
</html>