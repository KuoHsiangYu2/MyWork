package lin.note.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lin.note.book.model.ProductBean;
import lin.note.book.util.PropertiesTool;

@WebServlet("/Cart")
public class Cart extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        List<ProductBean> list = new ArrayList<ProductBean>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        PrintWriter out = response.getWriter();
        boolean isFindData = false;
        Properties properties = PropertiesTool.getDatabaseProperties();
        String jdbcURL = properties.getProperty("jdbcURL");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        try {
            // 修正成 MySQL 10版的 class name
            String booknum = request.getParameter("booknum");

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, username, password);
            preparedStatement = connection.prepareStatement("SELECT bookname, price FROM product WHERE booknum = ?");
            preparedStatement.setString(1, booknum);
            resultSet = preparedStatement.executeQuery();

            while (true == resultSet.next()) {
                isFindData = true;
                String bookname = resultSet.getString("bookname");
                int price = resultSet.getInt("price");

                Date createTime = new Date();
                ProductBean message = new ProductBean();

                message.setBooknum(booknum);
                message.setBookname(bookname);
                message.setPrice(price);
                message.setCreateTime(createTime);

                list.add(message);
                Scanner sc = new Scanner(System.in);
                System.out.println("是否要此編號(Y/N):");
                String x = sc.next();
                if (!("Y".contentEquals(x))) {
                    System.out.println("請返回輸入編號網頁");
                    list.remove(message);
                    out.println("<a href='Input.jsp'>重回購物車</a> <br />");
                    return;
                }

                Scanner sc1 = new Scanner(System.in);
                System.out.println("是否要清空(Y/N):");
                String x1 = sc1.next();
                if ("Y".contentEquals(x1)) {
                    list.clear();
                    System.out.println("購物車已經被清空");
                }

                HttpSession session = request.getSession();
                session.setAttribute("messageList", list);
            }

            if (false == isFindData) {
                out.println("編號輸入錯誤，資料庫裡沒有這個書籍。 <br />");
                out.println("<a href='Input.jsp'>重回購物車</a> <br />");
                return;
            }

            response.sendRedirect("Input.jsp");
        } catch (Exception e) {
            out.print(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
