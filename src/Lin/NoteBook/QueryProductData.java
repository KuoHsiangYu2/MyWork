package Lin.NoteBook;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryProductData
 */
@WebServlet("/QueryProductData")
public class QueryProductData extends HttpServlet {
	private static final long serialVersionUID = 1L;
      Connection con=null;
      Statement  stmt=null;
      ResultSet   rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryProductData() {
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
		//response.setContentType("big5");
		PrintWriter  out=response.getWriter();
		out.println("<html><head></head><body>");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/book?characterEncoding=utf-8","root","");
			stmt=con.createStatement();   
			rs=stmt.executeQuery("select * from product order by price desc");
			out.println("<table border='1'>");
			out.println("<tr><td>編號<td>書名<td>分類<td>作者<td>價格<td>庫存<td>說明");
			while(rs.next())   
			{
				out.print("<tr>");
				out.print("<td>"+rs.getString("booknum"));
				out.print("<td>"+rs.getString("bookname"));
				out.print("<td>"+rs.getString("type"));
				out.print("<td>"+rs.getString("author"));
				out.print("<td>"+rs.getString("price"));
				out.print("<td>"+rs.getString("stock"));
				out.print("<td>"+rs.getString("memo"));
			}
			out.print("</table>");
			out.print("<a href='Buy.html'>加入購物車</a> <br/>");
			out.print("</body></html>");
			
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
