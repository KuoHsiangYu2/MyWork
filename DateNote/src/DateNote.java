

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DateNote
 */
@WebServlet("/DateNote")
public class DateNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateNote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username, password, word;
	
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//使用資料庫   儲存帳號和密碼
		   
		   String USERS = "C:/Users/user/Desktop/Note.data";
		   File file = new File(USERS);
		   
		   request.setCharacterEncoding("UTF-8");
		   response.setCharacterEncoding("UTF-8");
		   response.setContentType("text/html;UTF-8");
		   
		   PrintWriter out = response.getWriter();
		   username = request.getParameter("username");
		   password = request.getParameter("password");
		   word = request.getParameter("word");
		   if(!(username.isEmpty()) && !(password.isEmpty()) && !(word.isEmpty())) {
		   //String USERS = "c:/Users/user/eclipse/users";
		   FileWriter fw = new FileWriter(file, true);
		   BufferedWriter br = new BufferedWriter(fw);
		   
		   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   Date date = new Date();
		   String dateToStr = dateFormat.format(date);
		   fw.append("\n\n");
		   fw.append("使用者名稱:"+username+"\n");		   
		   fw.append("密碼:"+password+"\n");
		   fw.append("平台:"+word+"\n");
		   fw.append("時間:"+dateToStr+"\n\n");
		   
		   
		    out.println("<!Doctype html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title> welcome </title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>已經成功儲存 </h1>");
			out.println("<a href='Date.html'>再輸入一次</a> <br/>");
			
			out.println("</body>");
			
			out.println("</html>");
		   //pageContext.setAttribute("username", USERS, PageContext.REQUEST_SCOPE);
		   //pageContext.setAttribute("password", USERS, PageContext.REQUEST_SCOPE);
		   //Path userhome = Paths.get(USERS, username);
		   fw.flush();
		   fw.close();
		   }else {out.println("<a href='Date.html'>錯誤，再輸入一次</a> <br/>");}
		   
		   boolean bool = file.exists();
		   while(!bool) {
			   file = new File(USERS);
			   bool=true;
		   }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		//doGet(request, response);
	}	  

}
