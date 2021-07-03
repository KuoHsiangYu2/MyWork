<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="big5">
<title>Insert title here</title>
</head>
<body>
<%=exception %>
<%=request.getAttribute("message") %>
</body>
</html>