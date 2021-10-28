package Lin.NoteBook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Shopping
 */
@WebServlet("/Shopping")
public class Shopping extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement  stmt=null;
    ResultSet  rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shopping() {
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
		  
		  int sum1 = 0, sum2 = 0, sum3 = 0;
		  
		  try {
			   Class.forName("com.mysql.jdbc.Driver");
	           con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
	           stmt=con.createStatement();
	           
	           int Key=(int)(((Math.random())*10000)+1000);
	   		   System.out.println(Key);
	   		   
	   		   String USERS = "C:/Users/user/Desktop/Note.data";
			   File file = new File(USERS);
			   
			   FileWriter fw = new FileWriter(file, true);
			   BufferedWriter br = new BufferedWriter(fw);
			   
			   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			   Date date = new Date();
			   String dateToStr = dateFormat.format(date);
			   
			   fw.append("\n\n");
			   fw.append("Key:"+Key+"\n");
			   fw.append("時間:"+dateToStr+"\n\n");
	   		   System.out.println("Key已發送到Note.data");
	   		   
	   		   fw.flush();
			   fw.close();
			   
			   boolean bool = file.exists();
			   while(!bool) {
				   file = new File(USERS);
				   bool=true;
			   }
			   
			   Scanner sc = new Scanner(System.in);
			   System.out.print("請輸入Key:");
			   int a = sc.nextInt();
			   
			   if(a == Key) {
	           
	              if(!(good1==null)) {
	              try {
	            	  stmt.executeUpdate("insert into pretty(product,number)values('JAVA','"+num1+"')");
	        		  out.println("你已成功購買JAVA"+"<br>");
	        		  sum1=100*target1;
	        		  out.println("數量:"+target1+"金額:"+sum1+"<br>"+"<br>");
	        		  out.println();
	              }catch(SQLException se)
	    		  {    out.println("JAVA不能重複購物 <br/>");
	    		       out.println("請進入購物清單 <br/><br/>");
	    		       //out.println("<a href='Query'>購物清單</a> <br/>");
	    		  }
	        	  }else{sum1=0;}
	        	 
	        	  
	        	  if(!(good2==null)) {
	        	  try {
	                  stmt.executeUpdate("insert into pretty(product,number)values('C++','"+num2+"')");
	        		  out.println("你已經成功購買C++"+"<br>");
	        		  sum2=150*target2;
	        		  out.println("數量:"+target2+"金額:"+sum2+"<br>"+"<br>");
	        		  out.println();
	        	  }catch(SQLException se)
	    		  {    out.println("C++不能重複購物  <br/>");
	    		       out.println("請進入購物清單 <br/><br/>");
	    		       //out.println("<a href='Query'>購物清單</a> <br/>");
	    		  }	  
	        	  }else{sum2=0;}
	        	  
	        	  
	        	  if(!(good3==null)) {
	        	  try {
	        		  stmt.executeUpdate("insert into pretty(product,number)values('English','"+num3+"')");
	        		  out.println("你已經成功購買English"+"<br>");
	        		  sum3=80*target3;
	        		  out.println("數量:"+target3+"金額:"+sum3+"<br>"+"<br>");
	        		  out.println();
	        	  }catch(SQLException se)
	    		  {    out.println("English不能重複購物 <br/>");
	    		       out.println("請進入購物清單 <br/><br/>");
	    		       //out.println("<a href='Query'>購物清單</a> <br/>");
	    		  }	  
	        	  }else{sum3=0;}
	        	  
	        	  
	        	  Add total = new Add(sum1, sum2, sum3);
	        	  out.println(total+"<br>");
	        	  out.println("<a href='Query'>購物清單</a> <br/>");
	        	 
	           
	           out.print("</table>");
			   out.print("</body></html>");
			   out.close();
			   }else {
				   out.println("Key輸入錯誤  <br/>");
				   out.println("<a href='Box.html'>重回購物目錄</a> <br/>");
			   }
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
