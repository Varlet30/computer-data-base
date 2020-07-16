package com.excilys.projet.java.cdb.servlets;

import java.io.IOException;

import java.util.List;

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
 * Servlet implementation class Edit
 */
@WebServlet(urlPatterns = "/EditServlet")
public class EditServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public String idComputer;
       
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
		listCompany = ServiceCompany.getInstance().getCompanyList();
		Computer comp = ServiceComputer.getInstance().findComputerById(Long.parseLong(idComputer));	
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
		ServiceComputer.getInstance().editComputer(comp);
		request.getRequestDispatcher("ListServlet").forward(request,response);
	}
}