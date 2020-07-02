package com.excilys.projet.java.cdb.service;

import java.util.List;
import java.util.Optional;

import com.excilys.projet.java.cdb.persistence.dao.ComputerDAO;
import com.excilys.projet.java.cdb.model.Computer;

public class ServiceComputer 
{
	private static ServiceComputer instance;
	private final ComputerDAO computerDao = ComputerDAO.getInstance();
	
	private ServiceComputer()
	{
		
	}
	
	public static ServiceComputer getInstance()
	{
		if (instance == null)
		{
			instance = new ServiceComputer();
			return instance;
		}
		else
		{
			return instance;
		}
	}
	
	public List<Computer> getComputerList()
	{
		List<Computer> listComput = computerDao.allComputer();
		return listComput;	
	}
	
	public List<Computer> getComputerListPaginer(int tri, String colonne, int limit, int offset) throws ClassNotFoundException
	{
		List<Computer> listComput = computerDao.pageComputer(tri, colonne, limit, offset);
		return listComput;
	}
	
	public int getCount()
	{
		int nombreComputer = computerDao.count();
		return nombreComputer;	
	}
	
	public Optional<Computer> addComputer(Computer comput)
	{
		int i = ComputerDAO.getInstance().create(comput);
		if (i == 1)
		{
			return Optional.of(comput);
		}
		else 
		{
			return Optional.empty();
		}
	}
	
	public void editComputer(Computer comp)
	{
		ComputerDAO.getInstance().update(comp);
	}
	
	public Computer findComputerById(Long id)
	{
		Computer comp = ComputerDAO.getInstance().findId(id);
		return comp;
	}
	
	public void deleteComputer(long id)
	{
		ComputerDAO.getInstance().delete(id);
	}
	
	public List<Computer> findComputerByName(String name)
	{
		List<Computer> computerList = ComputerDAO.getInstance().findName(name);
		return computerList;
	}
}
