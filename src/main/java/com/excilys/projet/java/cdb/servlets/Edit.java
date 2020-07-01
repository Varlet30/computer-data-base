package com.excilys.projet.java.cdb.servlets;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class Edit
 */
@WebServlet(urlPatterns = "/Edit")
public class Edit extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public String idComputer;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Company> listCompany;
		idComputer = request.getParameter("id");
		try 
		{
			listCompany = ServiceCompany.getInstance().getCompanyList();
			Computer comp = ServiceComputer.getInstance().findComputerById(Long.parseLong(idComputer));
			
			request.setAttribute("listCompany",listCompany);
			request.setAttribute("idComputer", idComputer);
			request.setAttribute("updateComputer", comp);
			request.getRequestDispatcher("views/editComputer.jsp").forward(request,response);
		} 
		catch (ClassNotFoundException | NumberFormatException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String company = request.getParameter("company");
		CompanyDTO compaDTO = new CompanyDTO(company);
		ComputerDTO compuDTO = new ComputerDTO(name,introduced,discontinued,compaDTO);
		compuDTO.setIdComputer(id);
		Computer comp = ComputerMapper.convertComputerDTOtoComputer(compuDTO);
		try 
		{
			ServiceComputer.getInstance().editComputer(comp);
			request.getRequestDispatcher("List").forward(request,response);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			doGet(request, response);
		}
		
	}

}
