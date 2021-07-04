<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.io.*" %>
<%@page import="java.io.FileWriter" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>註冊首頁</title>
</head>
<body>

<%
   
   String username, password;
   username=request.getParameter("username");
   password=request.getParameter("password");
   HashMap users = new HashMap();
   users.put("ann", "aaa");				
   users.put("john", "jjj");
   users.put("mark", "mmm");
   
%>

<%!   
   //方法
   String Status(String username,  String password) 
   {
   if(!(username.isEmpty()))
	 if(!(password.isEmpty())) 
		 
	   return "success";	
	   
     else
       return "fail";
   else
	   return "fail";
   }
%>
   
<% if(Status(username,password)=="success" && password.equals(users.get(username))) { 
      request.setAttribute("username", username); %>
<%
   if(request.getSession(false) != null) {
	   request.changeSessionId();
   }
%>
<%
   request.getSession().setAttribute("login", username);
%>

  <jsp:forward page="/Member" />

<% } %>

 
<% if(Status(username,password)=="fail" || !(password.equals(users.get(username)))) { %>  
  
  <jsp:forward page="Register_Fail.jsp" />

<% } %>  

<%-- 
       使用資料庫   儲存帳號和密碼
   String USERS = "C:/Users/user/Desktop/Note.data";
   File file = new File(USERS);
   
   //Object users = request.getAttribute(username);
   //Object pass = request.getAttribute(password);
   
   if(Status(username,password)=="success" ) {
   //String USERS = "c:/Users/user/eclipse/users";
   FileWriter fw = new FileWriter(file, true);
   BufferedWriter br = new BufferedWriter(fw);
   
   //username=request.getParameter("username");
   fw.append(username);
   //password=request.getParameter("password");
   fw.append(password);
   
   //pageContext.setAttribute("username", USERS, PageContext.REQUEST_SCOPE);
   //pageContext.setAttribute("password", USERS, PageContext.REQUEST_SCOPE);
   //Path userhome = Paths.get(USERS, username);
   fw.flush();
   fw.close();
   }
   
   boolean bool = file.exists();
   while(!bool) {
	   file = new File(USERS);
	   bool=true;
   }
--%>

   
</body>
</html>