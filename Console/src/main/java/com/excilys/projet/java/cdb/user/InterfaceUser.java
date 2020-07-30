package com.excilys.projet.java.cdb.user;

import java.util.Date;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.time.LocalDate;

import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;

import com.excilys.projet.java.cdb.service.ServiceCompany;
import com.excilys.projet.java.cdb.service.ServiceComputer;

@Controller
public class InterfaceUser
{
	@Autowired
	private static ServiceComputer serviceComputer;
	
	@Autowired
	private static ServiceCompany serviceCompany;
	
	
	
	private static Logger logger = LoggerFactory.getLogger(InterfaceUser.class);
	
	public static void displayInterface() throws ParseException
	{
		logger.info("Choices: "+
							"\n 1/ Add computer"+
							"\n 2/ Update computer by id"+
							"\n 3/ Delete a company by id"+
							"\n 4/ Find a computer by name"+
							"\n 5/ Display all computers"+
							"\n 6/ Display all companies"+
							"\n 7/ Find a company by id"+
							"\n 8/ Dispaly a page of computers"+
							"\n 9/ Delete a computer"+
							"\n 10/ Exit\n");

		Scanner clavier = new Scanner(System.in);
		int i = clavier.nextInt();
		switch(i)
		{
		case 1:
			logger.info("Add computer..");
			addComputer(clavier);
			break;
			
		case 2:
			logger.info("Update computer..");
			updateComputerById(clavier);
			break;
			
		case 3:
			logger.info("Delete company by id..");
			deleteCompanyById(clavier);
			break;
			
		case 4:
			logger.info("Find computer by name..");
			findComputerByName(clavier);
			break;
			
		case 5:
			logger.info("All computers..");
			displayAllComputers();
			break;
			
		case 6:
			logger.info("All company..");
			displayAllCompany();
			break;
			
		case 7:
			logger.info("Find company by id..");
			findCompanyById(clavier);
			break;
			
		case 8:
			logger.info("Allcomputers by page..");
			dispalyPageComputers(clavier);
			break;
			
		case 9:
			logger.info("Delete computer..");
			deleteComputer(clavier);
			break;
			
		default:
			logger.error("error");
		    break;
		}
		clavier.close();
		
	}
	
	public static void deleteComputer(Scanner clavier) 
	{
		logger.info("Id computer to delete:");
		Long computerId = clavier.nextLong();
		serviceComputer.deleteComputer(computerId);
	}
	
	public static void dispalyPageComputers(Scanner clavier) 
	{
		logger.info("Nb computer to view: ");
		int limit = clavier.nextInt();
		logger.info("Nb computer to view max: ");
		int offset = clavier.nextInt();
		serviceComputer.getComputerListPaginer(0, null, limit, offset);
	}
	
	public static void findCompanyById(Scanner clavier) 
	{
		logger.info("Id company to find : ");
		Long companyId = clavier.nextLong();
		serviceCompany.getCompany(companyId);
	}
	
	public static void displayAllComputers() 
	{
		serviceComputer.getComputerList();
	}
	
	public static void displayAllCompany() 
	{
		serviceCompany.getCompanyList();
	}
	
	public static void findComputerByName(Scanner clavier) 
	{
		logger.info("Name computer to find : ");
		String nameComputer = clavier.next();
		serviceComputer.findComputerByName(nameComputer);
	}
	
	public static void deleteCompanyById(Scanner clavier)
	{	
		logger.info("Id company to delete: ");
		Long companyId = clavier.nextLong();
		serviceCompany.getDeleteCompany(companyId);
	}
	
	public static void updateComputerById(Scanner clavier)
	{	
		logger.info("Name computer: ");
		String name = clavier.next();
		
		logger.info("Introduced Date: jj-mm-yyyy");
		String introducedString = clavier.next();
		LocalDate introduced = newDateUpdate(introducedString);
		
		logger.info("Discontinued Date: jj-mm-yyyy");
		String discontinuedString = clavier.next();
		LocalDate discontinued = newDateUpdate(discontinuedString);
		
		logger.info("Id company : ");
		String idCompanyString = clavier.next();
		Long companyId = newCompanyIdUpdate(idCompanyString);
		
		Company compa = new Company();
		compa.setId(companyId);
		
		Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			 
		
		serviceComputer.updateComputer(comp);
	}
	
	public static LocalDate newDateUpdate(String dateString){
		
		LocalDate date;
		
		if (dateString.equals("null"))
		{
		date = null;
		}
		else
		{
		date = LocalDate.parse(dateString);
		}
		return date;
	}
	
	public static Long newCompanyIdUpdate(String idString){
		
		Long companyId;
		
		if (idString.compareTo("null") == 0)
		{
		companyId = 0L;
		}
		else
		{
		companyId = new Long(idString);
		}
		
		return companyId;
	}
	
	
	public static void addComputer(Scanner clavier) throws ParseException {
		
		logger.info("Name : ");
		String name = clavier.next();
		logger.info("Introduced Date : jj-mm-yyyy");
		String introducedString = clavier.next();
		LocalDate introduced = createDateAdd(introducedString);
		
		logger.info("Discontinued Date : jj-mm-yyyy");
		String discontinuedString = clavier.next();
		LocalDate discontinued = createDateAdd(discontinuedString);
		
		logger.info("Company Id : ");
		String idCompany = clavier.next();
		Company compa = createCompanyAdd(idCompany);
		
		Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			 
		
		serviceComputer.addComputer(comp);
	}
	
	public static LocalDate createDateAdd(String dateString) throws ParseException {
		
		LocalDate date;
		if (dateString.compareTo("null") == 0)
		{
			date = null;
		}
		else
		{
			Date dateDisco = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
			date =  dateDisco.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
		return date;
	}
	
	public static Company createCompanyAdd(String idCompany){
		
		Long companyId;
		Company compa = null;
	
		if (idCompany.compareTo("null") != 0)
		{
			companyId = Long.parseLong(idCompany);
			compa = serviceCompany.getCompany(companyId);
		}
		return compa;
	}
}