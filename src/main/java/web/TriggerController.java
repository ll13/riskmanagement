package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.TriggerDao;
import vo.Trigger;



/**
 * Servlet implementation class TriggerController
 */
@WebServlet(name="TriggerController", urlPatterns="/trigger")
public class TriggerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TriggerController() {
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
		// TODO Auto-generated method stub
		String method=request.getParameter("method");
		if(method==null){
			method="list";
		}
		if(method.equals("list")){
			ArrayList<Trigger> list=this.list();
			HttpSession session = request.getSession();
			session.setAttribute("triggerList", list);
			response.sendRedirect("trigger.jsp"); 
		}else if(method.equals("delete")){
			String id=request.getParameter("id");
			this.delete(id);
			ArrayList<Trigger> list=this.list();
			HttpSession session = request.getSession();
			session.setAttribute("TriggerList", list);
			response.sendRedirect("trigger.jsp"); 
		}else if(method.equals("add")){
			Trigger trigger=new Trigger();
			trigger.setId(request.getParameter("id"));
			trigger.setType(request.getParameter("type"));
			trigger.setPossibility(request.getParameter("possibility"));
			trigger.setInfluence(request.getParameter("influence"));
			this.add(trigger);
			response.sendRedirect("triggeradd.jsp");
		}else if(method.equals("update")){
			Trigger trigger=new Trigger();
			trigger.setId(request.getParameter("id"));
			trigger.setType(request.getParameter("type"));
			trigger.setPossibility(request.getParameter("possibility"));
			trigger.setInfluence(request.getParameter("influence"));
			this.delete(trigger.getId());
			this.add(trigger);
			response.sendRedirect("triggerupdate.jsp");
		}
		
		
		
		
		
		
	}
	protected void add(Trigger trigger){
		TriggerDao triggerDao=new TriggerDao();
		triggerDao.add(trigger);
	}
	protected void delete(String id){
		TriggerDao triggerDao=new TriggerDao();
		triggerDao.delete(id);
	}
	protected ArrayList<Trigger> list(){
		ArrayList<Trigger> triggerList=new ArrayList<Trigger>();
		TriggerDao triggerDao=new TriggerDao();
		triggerList=triggerDao.list(triggerList);
		return triggerList;
		
	}

}
