package lin.note.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lin.note.book.util.PropertiesTool;

@WebServlet("/QueryProductData")
public class QueryProductData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public QueryProductData() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Properties properties = PropertiesTool.getDatabaseProperties();
        String jdbcURL = properties.getProperty("jdbcURL");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        PrintWriter out = response.getWriter();
        out.println("<html><head></head><body>");

        try {
            // 修正成 MySQL 10版的 class name
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, username, password);
            preparedStatement = connection.prepareStatement("SELECT booknum, bookname, type, author, price, stock, memo FROM product ORDER BY price DESC");
            resultSet = preparedStatement.executeQuery();
            out.println("<table border='1'>");
            out.println("<tr><td>編號<td>書名<td>分類<td>作者<td>價格<td>庫存<td>說明");
            while (resultSet.next()) {
                out.print("<tr>");
                out.print("<td>" + resultSet.getString("booknum"));
                out.print("<td>" + resultSet.getString("bookname"));
                out.print("<td>" + resultSet.getString("type"));
                out.print("<td>" + resultSet.getString("author"));
                out.print("<td>" + resultSet.getInt("price"));
                out.print("<td>" + resultSet.getString("stock"));
                out.print("<td>" + resultSet.getString("memo"));
            }
            out.print("</table>");
            out.print("<a href='Buy.html'>加入購物車</a> <br/>");
            out.print("</body></html>");

        } catch (Exception e) {
            out.print(e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

}
