package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class indexController
 */
@WebServlet(name = "indexController", urlPatterns = "*.index")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) {

		String method = request.getServletPath();
		method=method.split(".")[0];
		
		if (method == null) {
			method = "index";
		}
		System.out.println(method);
		try{
			if ("index".equals(method)) {
				 response.sendRedirect("index.jsp");  
			}
			else if("login".equals(method)){
				response.sendRedirect("login.jsp");  
			}
			
			
			
		}catch(Exception ex){
			System.err.println(ex);
		}

	}

}
