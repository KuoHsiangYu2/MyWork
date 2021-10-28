package Lin.NoteBook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("big5");
		response.setCharacterEncoding("big5");
		response.setContentType("text/html;big5");
		PrintWriter out=response.getWriter();
		
		out.println("<html><head></head><body>");
		
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		String Name=request.getParameter("Name");
		String birthday=request.getParameter("birthday");
		String tel=request.getParameter("tel");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		
		sql="insert into member(account,password,Name,birthday,tel,address,email)values('"+account+"','"+password+"','"+Name+"','"+birthday+"','"+tel+"','"+address+"','"+email+"')";
		System.out.println(sql);
		
		try {
			   Class.forName("com.mysql.jdbc.Driver");
	           con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
	           stmt=con.createStatement();
	           stmt.executeUpdate(sql); 
	           
	           int token=(int)(((Math.random())*10000)+1000);
	   		   System.out.println(token);
	   		   
	   		   String USERS = "C:/Users/user/Desktop/Note.data";
			   File file = new File(USERS);
			   
			   FileWriter fw = new FileWriter(file, true);
			   BufferedWriter br = new BufferedWriter(fw);
			   
			   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			   Date date = new Date();
			   String dateToStr = dateFormat.format(date);
			   
			   fw.append("\n\n");
			   fw.append("token:"+token+"\n");
			   fw.append("時間:"+dateToStr+"\n\n");
	   		   System.out.println("token已發送到Note.data");
	   		   
	   		   fw.flush();
			   fw.close();
			   
			   boolean bool = file.exists();
			   while(!bool) {
				   file = new File(USERS);
				   bool=true;
			   }
			   
	   		   Scanner sc = new Scanner(System.in);
			   System.out.print("請輸入token:");
			   int a = sc.nextInt();
			   
			   if(a == token) {
			   System.out.println("註冊成功");	   
	    	   RequestDispatcher  rd=request.getRequestDispatcher("Login.html");
	           rd.forward(request, response);
			   }
	           out.print("</table>");
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
