package com.excilys.projet.java.cdb.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceComputer;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet(urlPatterns  =  "/List")
public class List extends HttpServlet 
{
	private static final long serialVersionUID  =  1L;
	
	public int tri;
	public int page;
	public int maxPage;
	public int lenPage;
	public int totalComputer;
	public String colonne;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public List() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ArrayList<Computer> computerList = null;
		if (request.getParameter("tri") == null)
		{
			tri = 0;
		}
		else
		{
			tri = Integer.parseInt(request.getParameter("tri"));
		}
		tri %=  3;
		colonne = request.getParameter("colonne");
		if (request.getParameter("search") == null||request.getParameter("search") == "")
		{
			if (request.getParameter("lenPage") != null)
			{
				lenPage = Integer.parseInt(request.getParameter("lenPage"));
			}
			else
			{
				lenPage = 10;
			}	
			try 
			{
				totalComputer = ServiceComputer.getInstance().getCount();
				maxPage = totalComputer/lenPage;
				
				if (request.getParameter("page") != null)	
				{
					page = Integer.parseInt(request.getParameter("page"));
					
					if (page == maxPage)
					{
						computerList = ServiceComputer.getInstance().getComputerListPaginer(tri,colonne,ServiceComputer.getInstance().getCount()%10,page*lenPage);
					}
					else
					{
						computerList = ServiceComputer.getInstance().getComputerListPaginer(tri,colonne,lenPage,page*lenPage);
					}
				}
				else
				{
					page = 1;
					computerList = ServiceComputer.getInstance().getComputerListPaginer(tri,colonne,lenPage,0);
				}
				
				request.setAttribute("page", page);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("lenPage", lenPage);
			}
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			String search = request.getParameter("search");
			try 
			{
				computerList = ServiceComputer.getInstance().findComputerByName(search);
				totalComputer = computerList.size();
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}	
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
		
		for (int i = 0; i < idList.length; i++)
		{
			try 
			{
				ServiceComputer.getInstance().deleteComputer(Long.parseLong(idList[i]));
			} 
			catch (NumberFormatException | ClassNotFoundException e) 
			{	
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}
}
