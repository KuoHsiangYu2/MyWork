package Lin.NoteBook;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddMember
 */
@WebServlet("/AddMember")
public class AddMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement  stmt=null;
    ResultSet  rs;
    
    String account, password, birthday, tel, email, sql;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
						
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		String Name=request.getParameter("Name");
		String birthday=request.getParameter("birthday");
		String tel=request.getParameter("tel");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		
		
		
		//out.print("<table>");		
		sql="insert into member(account,password,Name,birthday,tel,address,email)values('"+account+"','"+password+"','"+Name+"','"+birthday+"','"+tel+"','"+address+"','"+email+"')";
			System.out.println(sql);
			
			try {
				   Class.forName("com.mysql.jdbc.Driver");
		           con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
		           stmt=con.createStatement();
		           stmt.executeUpdate(sql);         
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
