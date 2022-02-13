package lin.note.book;

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

@WebServlet("/Cart")
public class Cart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    List<Cart> list = new ArrayList<Cart>();

    private String booknum;
    private String bookname;
    private String price;
    private Date createTime;

    public String getBooknum() {
        return booknum;
    }

    public void setBooknum(String booknum) {
        this.booknum = booknum;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Cart() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=big5");
        request.setCharacterEncoding("big5");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        PrintWriter out = response.getWriter();
        boolean isFindData = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/book?characterEncoding=utf-8", "root", "");
            statement = connection.createStatement();

            String booknum = request.getParameter("booknum");

            resultSet = statement.executeQuery("select * from product WHERE booknum = '" + booknum + "' ");

            while (true == resultSet.next()) {
                isFindData = true;
                String bookname = resultSet.getString("bookname");
                String price = resultSet.getString("price");

                Date createTime = new Date();
                Cart message = new Cart();

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
