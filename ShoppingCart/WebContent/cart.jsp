<%@page import="Math.Multiple" %>
<%@page import="Math.Add" %>
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
     String num1, num2, num3, num4, num5;
     num1=request.getParameter("num1");
     num2=request.getParameter("num2");
     num3=request.getParameter("num3");
     num4=request.getParameter("num4");
     num5=request.getParameter("num5");
          
     int target1 = Integer.parseInt(num1);
     int target2 = Integer.parseInt(num2);
     int target3 = Integer.parseInt(num3);
     int target4 = Integer.parseInt(num4);
     int target5 = Integer.parseInt(num5);
     Multiple a = new Multiple(target1, 100);
     Multiple b = new Multiple(target2, 150);
     Multiple c = new Multiple(target3, 80);
     Multiple d = new Multiple(target4, 120);
     Multiple e = new Multiple(target5, 70);
     
     out.println("書名:JAVA"+","+"價格:100"+","+"數量:"+target1+","+a+"<br>");
     out.println("書名:C++"+","+"價格:150"+","+"數量:"+target2+","+b+"<br>");
     out.println("書名:English"+","+"價格:80"+","+"數量:"+target3+","+c+"<br>");
     out.println("書名:Math"+","+"價格:120"+","+"數量:"+target4+","+d+"<br>");
     out.println("書名:Chinese"+","+"價格:70"+","+"數量:"+target5+","+e+"<br>");
     
     Add total = new Add(target1, target2, target3, target4, target5);
     out.println(total+"<br>");
     
%>
<a href="Buy.html">重新輸入</a>
</body>
</html>