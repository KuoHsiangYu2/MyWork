<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="lin.note.book.model.ProductBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Input.jsp</title>
</head>
<body>
	<form method="post" action="Cart" enctype="application/x-www-form-urlencoded">
		編號:
		<input type="text" name="booknum" />
		<br />

		<input type="submit" value="輸入" />
	</form>
	<%
	    SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
	    List list = (List) session.getAttribute("messageList");
	    if (list != null) {
	        for (int i = 0; i < list.size(); i++) {

	            ProductBean message = (ProductBean) list.get(i);
	            //list.remove(i);
	%>

	編號:
	<%=message.getBooknum()%>
	<br>
	書名:
	<%=message.getBookname()%>
	<br>
	價格:
	<%=message.getPrice()%>
	<br>
	時間:
	<%=format.format(message.getCreateTime())%>
	<br>
	<br>
	<br>

	<%
	    }
	    }
	%>

	<%
	    out.print("<a href='Input.jsp'>重回購物車</a> <br/>");
	%>
	<%
	    out.print("<a href='QueryProductData'>購物目錄</a> <br/>");
	%>
</body>
</html>