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
	
	private ServiceComputer()
	{
		
	}
	
	public List<Computer> getComputerList()
	{
		List<Computer> listComput = computerDao.allComputer();
		return listComput;	
	}
	
	public List<Computer> getComputerListPaginer(int tri, String colonne, int limit, int offset)
	{
	List<Computer> listComput = computerDao.pageComputer(tri, colonne, limit, offset);
		return listComput;
	}
	
	public int getCount()
	{
		int nombreComputer = computerDao.count();
		return nombreComputer;	
	}
	
	public void addComputer(Computer comput)
	{
		computerDao.create(comput);
	}
	
	public void editComputer(Computer comp)
	{
		computerDao.update(comp);
	}
	
	public Computer findComputerById(Long id)
	{
		Computer comp = computerDao.findId(id);
		return comp;
	}
	
	public void deleteComputer(long id)
	{
		computerDao.delete(id);
	}
	
	public List<Computer> findComputerByName(String name)
	{
		List<Computer> computerList = computerDao.findName(name);
		return computerList;
	}
}
