package com.excilys.projet.java.cdb.servlets;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceComputer;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet(urlPatterns  =  "/ListServlet")
@Controller
public class ListServlet extends HttpServlet 
{
	private static final long serialVersionUID  =  1L;
	
	public int tri;
	public int page;
	public int maxPage;
	public int lenPage;
	public int totalComputer;
	public String colonne;
	@Autowired
	private ServiceComputer serviceComputer;

	/**
     * @throws ServletException 
	 * @see HttpServlet#HttpServlet()
     */
    public void init(ServletConfig config) throws ServletException
    {
    	super.init(config);
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    
    public ListServlet() 
    {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Computer> computerList = null;
		if (request.getParameter("tri") == null){
			tri = 0;
		}else{
			tri = Integer.parseInt(request.getParameter("tri")) % 3;
		}
		colonne = request.getParameter("colonne");
		if (request.getParameter("search") == null||request.getParameter("search") == ""){
			if (request.getParameter("lenPage") != null){
				lenPage = Integer.parseInt(request.getParameter("lenPage"));
			}else{
				lenPage = 10;
			}	totalComputer = serviceComputer.getCount();
			maxPage = totalComputer/lenPage;
			
			if (request.getParameter("page") != null)	{
				page = Integer.parseInt(request.getParameter("page"));
				if (page == maxPage){
					computerList = serviceComputer.getComputerListPaginer(tri,colonne,serviceComputer.getCount()%10,page*lenPage);
				}else{
					computerList = serviceComputer.getComputerListPaginer(tri,colonne,lenPage,page*lenPage);
				}
			}else{
				page = 1;
				computerList = serviceComputer.getComputerListPaginer(tri,colonne,lenPage,0);
			}
			request.setAttribute("page", page);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("lenPage", lenPage);
		}else{
			String search = request.getParameter("search");
			computerList = serviceComputer.findComputerByName(search);
			totalComputer = computerList.size();
		}
		request.setAttribute("totalComputer", totalComputer);
		request.setAttribute("listComputer", computerList);
		request.setAttribute("tri", tri);
		this.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAttacher  =  request.getParameter("selection");
		String[] idList  =  idAttacher.split(",");
		
		for (int i = 0; i < idList.length; i++){
			serviceComputer.deleteComputer(Long.parseLong(idList[i]));
		}
		doGet(request, response);
	}
}
