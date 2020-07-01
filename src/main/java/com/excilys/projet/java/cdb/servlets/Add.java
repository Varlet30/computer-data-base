package com.excilys.projet.java.cdb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.dto.ComputerDTO;
import com.excilys.projet.java.cdb.mapper.ComputerMapper;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceCompany;
import com.excilys.projet.java.cdb.service.ServiceComputer;

/**
 * Servlet implementation class Add
 */
@WebServlet(urlPatterns = "/Add")
public class Add extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public int maxPage;
	public int lenPage;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Company> listCompany;
		try 
		{
			listCompany = ServiceCompany.getInstance().getCompanyList();
			request.setAttribute("listCompany",listCompany);
			request.getRequestDispatcher("views/addComputer.jsp").forward(request,response);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String name = request.getParameter("computerName");
			String computerIntroduced = request.getParameter("computerIntroduced");
			String computerDiscontinued = request.getParameter("computerDiscontinued");
			String company = request.getParameter("company");
			CompanyDTO compaDTO =new CompanyDTO(company);
			ComputerDTO compuDTO =new ComputerDTO(name,computerIntroduced,computerDiscontinued,compaDTO); 
			Computer comp = ComputerMapper.convertComputerDTOtoComputer(compuDTO);
			StringBuilder erreur = new StringBuilder();
			try 
			{
				Optional<Computer> comput= ServiceComputer.getInstance().addComputer(comp);
				lenPage=10;
				maxPage=ServiceComputer.getInstance().getCount()/lenPage;
				request.getRequestDispatcher("List?page="+ maxPage).forward(request,response);
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
				erreur.append("Not Name");
			request.setAttribute("error", erreur);
			request.setAttribute("addComputer", comp);
			doGet(request,response);
	}

}
