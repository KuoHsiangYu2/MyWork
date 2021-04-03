<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Math.Add" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
  String num1, num2, num3;
  num1=request.getParameter("num1");
  num2=request.getParameter("num2");
  num3=request.getParameter("num3");
  
  int target1 = Integer.parseInt(num1);
  int target2 = Integer.parseInt(num2);
  int target3 = Integer.parseInt(num3);
  
  String good1, good2, good3;
  good1=request.getParameter("good1");
  good2=request.getParameter("good2");
  good3=request.getParameter("good3");
  
  int sum1, sum2, sum3;
  
  if(!(good1==null)) {
	  out.println("你已成功購買JAVA"+"<br>");
	  sum1=100*target1;
	  out.println("數量:"+target1+"金額:"+sum1+"<br>");
  }else{sum1=0;}
 
  
  if(!(good2==null)) {
	  out.println("你已經成功購買C++"+"<br>");
	  sum2=150*target2;
	  out.println("數量:"+target2+"金額:"+sum2+"<br>");
  }else{sum2=0;}
  
  
  if(!(good3==null)) {
	  out.println("你已經成功購買English"+"<br>");
	  sum3=80*target3;
	  out.println("數量:"+target3+"金額:"+sum3+"<br>");
  }else{sum3=0;}
  
  
  Add total = new Add(sum1, sum2, sum3);
  out.println(total+"<br>");
%>
<a href="Box.html">重新輸入</a>
</body>
</html>