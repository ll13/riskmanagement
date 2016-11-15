package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RiskItemDao;
import vo.RiskItem;


/**
 * Servlet implementation class SolutionController
 */
@WebServlet(name="SolutionController", urlPatterns="/solution")
public class SolutionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolutionController() {
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
		}if(method.equals("list")){
			ArrayList<RiskItem> list=this.list();
			HttpSession session = request.getSession();
			session.setAttribute("solutionList", list);
			response.sendRedirect("solution.jsp"); 
		}else if(method.equals("done")){
			ArrayList<RiskItem> list=this.listDone();
			HttpSession session = request.getSession();
			session.setAttribute("solutionList", list);
			response.sendRedirect("solution.jsp");
		}else if(method.equals("update")){
			RiskItem riskItem=new RiskItem();
			riskItem.setId(request.getParameter("id"));
			riskItem.setRiskname(request.getParameter("riskname"));
			riskItem.setType(request.getParameter("type"));
			riskItem.setPossibility(request.getParameter("possibility"));
			riskItem.setInfluence(request.getParameter("influence"));
			riskItem.setStatus(request.getParameter("status"));
			riskItem.setCommit(request.getParameter("commit"));
			riskItem.setFollow(request.getParameter("follow"));
			riskItem.setSolution(request.getParameter("solution"));
			riskItem.setNumber(1);
			this.update(riskItem);
			response.sendRedirect("solutionupdate.jsp");
		}
	}
	protected ArrayList<RiskItem> list(){
		ArrayList<RiskItem> riskItemList=new ArrayList<RiskItem>();
		RiskItemDao riskItemDao=new RiskItemDao();
		riskItemList=riskItemDao.listUndo(riskItemList);
		return riskItemList;
	}
	protected ArrayList<RiskItem> listDone(){
		ArrayList<RiskItem> riskItemList=new ArrayList<RiskItem>();
		RiskItemDao riskItemDao=new RiskItemDao();
		riskItemList=riskItemDao.listDone(riskItemList);
		return riskItemList;
	}
	protected void update(RiskItem riskItem){
		RiskItemDao riskItemDao=new RiskItemDao();
		riskItemDao.update(riskItem);
	}
}
