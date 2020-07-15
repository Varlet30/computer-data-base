package com.excilys.projet.java.cdb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.projet.java.cdb.persistence.dao.ComputerDAO;
import com.excilys.projet.java.cdb.model.Computer;

@Service
public class ServiceComputer 
{
	private ComputerDAO computerDao;
	
	private ServiceComputer(ComputerDAO computerDao)
	{
		this.computerDao = computerDao;
	}
	
	public List<Computer> getComputerList() 
	{
		List<Computer> listComput=computerDao.allComputer();
		
		return listComput;	
	}
	
	public List<Computer> getComputerListPaginer(int tri, String column, int limit, int offset) 
	{
		List<Computer> listComput = computerDao.pageComputer(tri, column, limit, offset);
		
		return listComput;
	}
	
	public int getCount() 
	{
		int nombreComputer=computerDao.countComputer();
		
		return nombreComputer;	
	}
	
	public Computer addComputer(Computer comput) 
	{
			return comput;
	}
	
	public void editComputer(Computer comp) 
	{
		computerDao.updateComputer(comp);
	}
	
	public Computer findComputerById(Long id) 
	{
		Computer comp = computerDao.findComputerId(id);
		
		return comp;
	}
	
	public void deleteComputer(long id) 
	{
		computerDao.deleteComputer(id);
	}
	
	public List<Computer> findComputerByName(String name) 
	{
		List<Computer> computerList = computerDao.findComputerName(name);
		return computerList;
	}
}
