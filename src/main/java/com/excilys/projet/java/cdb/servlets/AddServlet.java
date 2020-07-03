package com.excilys.projet.java.cdb.servlets;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.projet.java.cdb.mapper.ComputerMapper;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceCompany;
import com.excilys.projet.java.cdb.service.ServiceComputer;

/**
 * Servlet implementation class Add
 */
@WebServlet(urlPatterns = "/AddServlet")
public class AddServlet extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(AddServlet.class);
	private static final long serialVersionUID = 1L;
	
	public int maxPage;
	public int lenPage;
       
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
		listCompany = ServiceCompany.getInstance().getCompanyList();
		request.setAttribute("listCompany",listCompany);
		request.getRequestDispatcher("views/addComputer.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		Computer comp = ComputerMapper.convertResult(request);
		StringBuilder erreur = new StringBuilder();
		ServiceComputer.getInstance().addComputer(comp);
		lenPage=10;
		maxPage=ServiceComputer.getInstance().getCount()/lenPage;
		request.getRequestDispatcher("ListServlet?page="+ maxPage).forward(request,response);
		erreur.append("Not Name");
		request.setAttribute("error", erreur);
		request.setAttribute("addComputer", comp);
		doGet(request,response);
	}

}
