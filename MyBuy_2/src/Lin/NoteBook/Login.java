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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement  stmt=null;
    ResultSet  rs; 
    //String sql;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("big5");
		response.setCharacterEncoding("big5");
		response.setContentType("big5");
		PrintWriter  out=response.getWriter();
		
		
		boolean isFindData = false;
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		
		
		try {
			   Class.forName("com.mysql.jdbc.Driver");
	           con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
	           stmt=con.createStatement();
	           //account是唯一的
	           rs=stmt.executeQuery("SELECT * FROM member WHERE(account='"+account+"' and password='"+password+"')");
	           
	           while(true==rs.next()) {
	        	   isFindData = true;
	        	   
	        	   if(request.getSession(false) != null) {
	        		   request.changeSessionId();
	        	   }
	        	   request.getSession().setAttribute("login", account);
	        	   RequestDispatcher  rd=request.getRequestDispatcher("/Member");
		           rd.forward(request, response);
	           }
	    	   //RequestDispatcher  rd=request.getRequestDispatcher("/read_Member");
	           //rd.forward(request, response);
	           
	           //out.print("</table>");
	           if (false == isFindData) {	                
	                out.println("<a href='Login.html'>重回登入頁面</a> <br />");
	                return;
	            }
			   
			   out.close();
			   stmt.close();
		       con.close();
	       }catch(Exception e)
	       {
	    	   out.print(e);
	       }
		   
		   }
	      
		}
	


