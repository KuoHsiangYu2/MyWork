<%@page import="java.util.Date" %>
<%@page import="java.util.Timer" %>
<%@page import="java.io.*" %>
<%@page import="Lin.Warn.DateTask" %>
<%@page import="java.time.LocalDateTime" %>
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
    String userA = request.getParameter("userA");
	String timeA = request.getParameter("timeA");
	
	int numA = Integer.parseInt(timeA);
	
	Timer timer = new Timer();
	if(!(userA.isEmpty())) {
		out.println(userA+" "+"is ready"+"<br>");
		LocalDateTime localDateTime = LocalDateTime.now();
		//timer.schedule(new DateTask(), numberA);
		out.println("現在時間:"+localDateTime+"<br>");		
	}
	timer.cancel();
	DateTask DT = new DateTask(numA);
	out.println(DT+"<br>");
	
	//LocalDateTime localDateTime = LocalDateTime.now();
	//LocalDateTime nextTime = localDateTime.plusSeconds(numA);
	//for(int i=0; i<10000; i++) {
		//if(nextTime.isEqual(LocalDateTime.now())) {
			//out.println("Warning......");
		//}
	//}
	//LocalDateTime localDateTime = LocalDateTime.now();
	//LocalDateTime nextTime = localDateTime.plusSeconds(numA);
	//LocalDateTime localDateTimeA = LocalDateTime.now();
	//if(localDateTimeA.isAfter(nextTime)) {
		//out.println("Warning......");
	//}
 %>
 
 <a href="Info.html">重新輸入</a>
</body>
</html>