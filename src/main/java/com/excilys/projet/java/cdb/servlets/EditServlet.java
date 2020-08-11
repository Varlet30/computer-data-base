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

import com.excilys.projet.java.cdb.mapper.ComputerMapper;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceCompany;
import com.excilys.projet.java.cdb.service.ServiceComputer;

/**
 * Servlet implementation class Edit
 */
@WebServlet(urlPatterns = "/EditServlet")
@Controller
public class EditServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public String idComputer;
	@Autowired
	private ServiceComputer serviceComputer;
	private ServiceCompany serviceCompany;  
    
	public void init(ServletConfig config) throws ServletException
    {
    	super.init(config);
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> listCompany;
		idComputer = request.getParameter("id");
		listCompany = serviceCompany.getCompanyList();
		Computer comp = serviceComputer.findComputerById(Long.parseLong(idComputer));	
		request.setAttribute("listCompany",listCompany);
		request.setAttribute("idComputer", idComputer);
		request.setAttribute("updateComputer", comp);
		request.getRequestDispatcher("views/editComputer.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Computer comp = ComputerMapper.convertResultId(request);
		serviceComputer.editComputer(comp);
		request.getRequestDispatcher("ListServlet").forward(request,response);
	}
}