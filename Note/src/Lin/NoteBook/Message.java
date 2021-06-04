package Lin.NoteBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Message
 */
@WebServlet("/Message")
public class Message extends HttpServlet {
	List<Message> list = new ArrayList();
	private String name;
	private String title;
	private Date createTime;
	private String content;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message() {
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
		//List<Message> list = new ArrayList();
		
		 response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
	        String name = request.getParameter("name");
	        String title = request.getParameter("title");
	        String content = request.getParameter("content");
	        Date createTime = new Date();
	        Message message = new Message();
	        message.setName(name);
	        message.setTitle(title);
	        message.setContent(content);
	        message.setCreateTime(createTime);
	        	        
	        list.add(message);
	        
	        HttpSession session = request.getSession();
	        session.setAttribute("messageList",list);
	        response.sendRedirect("InputMessage.jsp");
	        }
	}


