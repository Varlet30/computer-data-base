package com.excilys.projet.java.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.projet.java.cdb.persistence.dao.ComputerDAO;
import com.excilys.projet.java.cdb.model.Computer;

@Service
public class ServiceComputer 
{
	@Autowired
	private ComputerDAO computerDao;
	
	public List<Computer> getComputerList() 
	{
		return computerDao.allComputer();
	}
	
	public List<Computer> getComputerListPaginer(int tri, String column, int limit, int offset) 
	{
		return computerDao.pageComputer(tri, column, limit, offset);
	}
	
	public int getCount() 
	{
		return computerDao.countComputer();
	}
	
	public Computer addComputer(Computer comput) 
	{
			return comput;
	}
	
	public void updateComputer(Computer comp) 
	{
		computerDao.updateComputer(comp);
	}
	
	public Computer findComputerById(Long id) 
	{
		return computerDao.findComputerId(id);
	}
	
	public Computer findComputerById(Integer id) 
	{
		return computerDao.findComputerId(id);
	}
	
	public void deleteComputer(long id) 
	{
		computerDao.deleteComputer(id);
	}
	
	public List<Computer> findComputerByName(String name) 
	{
		return computerDao.findComputerName(name);
	}
}
