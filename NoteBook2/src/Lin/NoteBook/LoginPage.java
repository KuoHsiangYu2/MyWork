package Lin.NoteBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPage() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
    //public void readExcel(String username, String password) {
    	FileInputStream fis;
    	//POIFSFileSystem fs;
    	XSSFWorkbook wb;
    	
    	String filePath = "C:\\Users\\user\\eclipse-workspace\\NoteBook2\\test.xlsx";
    	
    	try {
    		fis = new FileInputStream(new File(filePath));
    		//fs = new POIFSFileSystem(new File(filePath));
    		wb = new XSSFWorkbook(fis);
    		
    		XSSFSheet sheet = wb.getSheetAt(0);
    		XSSFCell cell1, cell2;
    		
    		for(int i=0 ; i < sheet.getPhysicalNumberOfRows() ; i++) {
    			
    			XSSFRow row = sheet.getRow(i);
    			if(row != null) {
    				
    			  for(int j=0 ; j<2 ; j++ ) {
    				  cell1 = row.getCell(0);
    				  cell2 = row.getCell(1);
    				  String usn = cell1.toString();
    				  String psw = cell2.toString().replace(".0", "");
    				if(username.equals(usn) && password.equals(psw)) {
    				 if(request.getSession(false) != null) {
    					 request.changeSessionId();
    				 }
    				 request.getSession().setAttribute("login", username);
    					wb.close();
    					fis.close();
    					RequestDispatcher view = 
    							request.getRequestDispatcher("Member");
    					view.forward(request, response);
    					break;
    				}
    			  }
    			}
    			
    		}
            for(int i=0 ; i < sheet.getPhysicalNumberOfRows() ; i++) {
    			
    			XSSFRow row = sheet.getRow(i);
    			if(row != null) {
    				
    			  for(int j=0 ; j<2 ; j++ ) {
    				  cell1 = row.getCell(0);
    				  cell2 = row.getCell(1);
    				  String usn = cell1.toString();
    				  String psw = cell2.toString().replace(".0", "");
    				if(!(username.contains(usn))) {
    					wb.close();
    					fis.close();
    					RequestDispatcher view = 
    							request.getRequestDispatcher("Register_Fail.jsp");
    					view.forward(request, response);
    					break;
    				}
    			  }
    			}
            }
    	    fis.close();
    	}catch (java.io.IOException e) 
    	{
    	  e.printStackTrace();
    	}  
    }
}
