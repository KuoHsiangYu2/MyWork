package lin.note.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lin.note.book.model.ProductBean;
import lin.note.book.service.IProduceService;
import lin.note.book.service.impl.ProduceService;

@WebServlet("/Cart")
public class Cart extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        List<ProductBean> list = new ArrayList<ProductBean>();

        PrintWriter printWriter = response.getWriter();

        boolean isFindData = false;

        String booknum = request.getParameter("booknum");

        IProduceService produceService = new ProduceService();
        ProductBean productBean = produceService.getProductByBooknum(booknum);
        if (productBean != null) {
            isFindData = true;
        }
        list.add(productBean);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("messageList", list);

        if (false == isFindData) {
            printWriter.println("編號輸入錯誤，資料庫裡沒有這個書籍。 <br />");
            printWriter.println("<a href='Input.jsp'>重回購物車</a> <br />");
            return;
        }

        response.sendRedirect("Input.jsp");

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
