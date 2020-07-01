package com.excilys.projet.java.cdb.service;

import java.util.ArrayList;
import java.util.Optional;

import com.excilys.projet.java.cdb.persistence.dao.ComputerDAO;
import com.excilys.projet.java.cdb.model.Computer;

public class ServiceComputer 
{
	private static ServiceComputer instance;
	private final ComputerDAO computerDao = ComputerDAO.getInstance();
	
	private ServiceComputer() throws ClassNotFoundException
	{
		
	}
	
	public static ServiceComputer getInstance() throws ClassNotFoundException
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
	
	public ArrayList<Computer> getComputerList() throws ClassNotFoundException
	{
		ArrayList<Computer> listComput = computerDao.allComputer();
		return listComput;	
	}
	
	public ArrayList<Computer> getComputerListPaginer(int tri, String colonne, int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComput = computerDao.pageComputer(tri, colonne, limit, offset);
		return listComput;
	}
	
	public int getCount() throws ClassNotFoundException
	{
		int nombreComputer = computerDao.count();
		return nombreComputer;	
	}
	
	public Optional<Computer> addComputer(Computer comput) throws ClassNotFoundException
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
	
	public void editComputer(Computer comp) throws ClassNotFoundException
	{
		ComputerDAO.getInstance().update(comp);
	}
	
	public Computer findComputerById(Long id) throws ClassNotFoundException
	{
		Computer comp = ComputerDAO.getInstance().findId(id);
		return comp;
	}
	
	public void deleteComputer(long id) throws ClassNotFoundException
	{
		ComputerDAO.getInstance().delete(id);
	}
	
	public ArrayList<Computer> findComputerByName(String name) throws ClassNotFoundException
	{
		ArrayList<Computer> computerList = ComputerDAO.getInstance().findName(name);
		return computerList;
	}
}
