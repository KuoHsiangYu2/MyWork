package Lin.NoteBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryProduct
 */
@WebServlet("/QueryProduct")
public class QueryProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement  stmt=null;
    ResultSet  rs;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("big5");
		response.setCharacterEncoding("big5");
		response.setContentType("text/html;big5");
		PrintWriter  out=response.getWriter();
		
		out.println("<html><head></head><body>");
		
		try {
			   Class.forName("com.mysql.jdbc.Driver");
	           con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
	           stmt=con.createStatement();
	           rs=stmt.executeQuery("select  *  from  mybuy");
	           
	           out.print("<table border='1'>");
	           out.println("<tr><td>編號<td>書名<td>價格<td>數量");
	           
	           while(rs.next())   
				{
					out.print("<tr>");
					//out.print("<td>"+rs.getString("ID"));
					out.print("<td>"+rs.getString("booknum"));
					out.print("<td>"+rs.getString("bookname"));					
					out.print("<td>"+rs.getString("price"));
					out.print("<td>"+rs.getString("num"));
				}
				out.print("</table>");
				out.print("<a href='QueryProductData'>購物網站</a> <br/>");
				out.print("<a href='Buy.html'>加入購物車</a> <br/>");
				out.print("</body></html>");
				out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			out.print(e);
		}finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
		}
	}

}
