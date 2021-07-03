package Lin.NoteBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DelMember
 */
@WebServlet("/DelMember")
public class DelMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement  stmt=null;
    ResultSet  rs;
    
    String username, sql;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//request.setCharacterEncoding("big5");
		//response.setCharacterEncoding("big5");
		//response.setContentType("big5");
		//PrintWriter  out=response.getWriter();		
		//out.println("<html><head></head><body>");
		
		//String username=request.getParameter("username");
		
		//sql="Delete from member where account = '"+username+"' ";
		//System.out.println(sql);
		
		//try {
			  // Class.forName("com.mysql.jdbc.Driver");
	          // con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
	          // stmt=con.createStatement();
	          // stmt.executeUpdate(sql);   
	           //rs.last();
	           //rs.deleteRow();
	    	  // RequestDispatcher  rd=request.getRequestDispatcher("/read_Member");
	          // rd.forward(request, response);
	           
	           //out.print("</table>");
			   //out.print("</body></html>");
			   //out.close();
	      // }catch(Exception e)
	      // {
	    	  // request.setAttribute("message",e.toString());
	    	  // RequestDispatcher  rd=request.getRequestDispatcher("errorMessage.jsp");
	          // rd.forward(request, response);
	      // }
		  // finally {
			  // try {
			     // stmt.close();
	             // con.close();
			   //}catch(Exception e)
			  // {
				  // out.print(e);
			   //}
		   //}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("big5");
		response.setCharacterEncoding("big5");
		response.setContentType("big5");
		PrintWriter  out=response.getWriter();		
		out.println("<html><head></head><body>");
		
		String username=request.getParameter("username");
		
		sql="Delete from member where account = '"+username+"' ";
		System.out.println(sql);
		
		try {
			   Class.forName("com.mysql.jdbc.Driver");
	           con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
	           stmt=con.createStatement();
	           stmt.executeUpdate(sql);   
	           //rs.last();
	           //rs.deleteRow();
	    	   RequestDispatcher  rd=request.getRequestDispatcher("/read_Member");
	           rd.forward(request, response);
	           
	           //out.print("</table>");
			   out.print("</body></html>");
			   out.close();
	       }catch(Exception e)
	       {
	    	   request.setAttribute("message",e.toString());
	    	   RequestDispatcher  rd=request.getRequestDispatcher("errorMessage.jsp");
	           rd.forward(request, response);
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

}
