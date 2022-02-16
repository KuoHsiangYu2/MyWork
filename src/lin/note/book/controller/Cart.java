package lin.note.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lin.note.book.model.ProductBean;

@WebServlet("/Cart")
public class Cart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Cart() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        List<ProductBean> list = new ArrayList<ProductBean>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        PrintWriter out = response.getWriter();
        boolean isFindData = false;
        String jdbcURL = "jdbc:mysql://localhost:3306/book?useUnicode=yes&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Taipei";
        String user = "root";
        String password = "";
        try {
            // 修正成 MySQL 10版的 class name
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, user, password);
            statement = connection.createStatement();

            String booknum = request.getParameter("booknum");

            resultSet = statement.executeQuery("select * from product WHERE booknum = '" + booknum + "' ");

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

            statement.close();
            connection.close();

        } catch (Exception e) {
            out.print(e);
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
