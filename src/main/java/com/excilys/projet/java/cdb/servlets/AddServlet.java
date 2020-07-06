package com.excilys.projet.java.cdb.servlets;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.projet.java.cdb.mapper.ComputerMapper;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceCompany;
import com.excilys.projet.java.cdb.service.ServiceComputer;

/**
 * Servlet implementation class Add
 */
@WebServlet(urlPatterns = "/AddServlet")
@Controller
public class AddServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public int maxPage;
	public int lenPage;
	@Autowired
	private ServiceComputer serviceComputer;
	private ServiceCompany serviceCompany;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> listCompany;
		listCompany = serviceCompany.getCompanyList();
		request.setAttribute("listCompany",listCompany);
		request.getRequestDispatcher("WEB-INF/views/addComputer.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		Computer comp = ComputerMapper.convertResult(request);
		StringBuilder erreur = new StringBuilder();
		serviceComputer.addComputer(comp);
		lenPage=10;
		maxPage=serviceComputer.getCount()/lenPage;
		request.getRequestDispatcher("ListServlet?page="+ maxPage).forward(request,response);
		erreur.append("Not Name");
		request.setAttribute("error", erreur);
		request.setAttribute("addComputer", comp);
		doGet(request,response);
	}

}
