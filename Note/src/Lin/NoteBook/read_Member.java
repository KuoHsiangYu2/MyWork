package Lin.NoteBook;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class read_Member
 */
@WebServlet("/read_Member")
public class read_Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement  stmt=null;
    ResultSet  rs;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public read_Member() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("big5");
		response.setCharacterEncoding("big5");
		response.setContentType("big5");
		PrintWriter  out=response.getWriter();
		
		if(request.getSession().getAttribute("login") == null) {
			response.sendRedirect("Login.html");
		  }
		
		out.println("<html><head></head><body>");
		try {
		   Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
           stmt=con.createStatement();
           rs=stmt.executeQuery("select  *  from  member");
           //rs.next();   
           out.print("<table border='1'>");
           out.print("<tr><td>帳號<td>密碼<td>姓名<td>生日<td>電話<td>地址<td>電子郵件");
           while(rs.next())
           {   out.print("<tr>");
               out.print("<td>"+rs.getString("account")); 
               out.print("<td>"+rs.getString("password"));
               out.print("<td>"+rs.getString("Name"));
               out.print("<td>"+rs.getString("birthday")); 
               out.print("<td>"+rs.getString("tel"));
               out.print("<td>"+rs.getString("address"));
               out.print("<td>"+rs.getString("email"));               
           }
           out.print("</table>");
           out.println("<a href='AddMem.html'>新增會員</a> <br/>");
           out.println("<a href='DelMem.html'>刪除帳號</a> <br/>");
           out.println("<a href='Logout'>登出</a> <br/>");
		   out.print("</body></html>");
		   out.close();
       }catch(Exception e)
       {
    	   out.print(e);
       }
	   finally {
		   try {
		      stmt.close();
              con.close();
		   }catch(Exception e)
		   {
			   out.print(e);
		   }
	   }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
