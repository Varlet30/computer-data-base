package com.excilys.projet.java.cdb.user;

import java.util.Date;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Timestamp;

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
	
	public static void DisplayInterface() throws ParseException, SQLException
	{
		System.out.println("Choices: "+
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
			Add_Computer(clavier);
			break;
			
		case 2:
			logger.info("Update computer..");
			Update_Computer_By_Id(clavier);
			break;
			
		case 3:
			logger.info("Delete company by id..");
			Delete_Company_By_Id(clavier);
			break;
			
		case 4:
			logger.info("Find computer by name..");
			Find_Computer_By_Name(clavier);
			break;
			
		case 5:
			logger.info("All computers..");
			Display_All_Computers(clavier);
			break;
			
		case 6:
			logger.info("All company..");
			Display_All_Company(clavier);
			break;
			
		case 7:
			logger.info("Find company by id..");
			Find_Company_By_Id(clavier);
			break;
			
		case 8:
			logger.info("Allcomputers by page..");
			Dispaly_Page_Computers(clavier);
			break;
			
		case 9:
			logger.info("Delete computer..");
			Delete_Computer(clavier);
			break;
		}
		clavier.close();
	}
	
	public static void Delete_Computer(Scanner clavier) 
	{
		System.out.println("Id computer to delete:");
		Long computerId = clavier.nextLong();
		serviceComputer.deleteComputer(computerId);
	}
	
	public static void Dispaly_Page_Computers(Scanner clavier) 
	{
		System.out.println("Nb computer to view: ");
		int limit = clavier.nextInt();
		System.out.println("Nb computer to view max: ");
		int offset = clavier.nextInt();
		serviceComputer.getComputerListPaginer(0, null, limit, offset);
	}
	
	public static void Find_Company_By_Id(Scanner clavier) 
	{
		System.out.println("Id company to find : ");
		Long companyId = clavier.nextLong();
		serviceCompany.getCompany(companyId);
	}
	
	public static void Display_All_Computers(Scanner clavier) 
	{
		serviceComputer.getComputerList();
	}
	
	public static void Display_All_Company(Scanner clavier) 
	{
		serviceCompany.getCompanyList();
	}
	
	public static void Find_Computer_By_Name(Scanner clavier) 
	{
		System.out.println("Name computer to find : ");
		String NameComputer = clavier.next();
		serviceComputer.findComputerByName(NameComputer);
	}
	
	public static void Delete_Company_By_Id(Scanner clavier) throws SQLException 
	{	
		System.out.println("Id company to delete: ");
		Long companyId = clavier.nextLong();
		serviceCompany.getDeleteCompany(companyId);
	}
	
	public static void Update_Computer_By_Id(Scanner clavier) throws ParseException
	{	
		System.out.println("Id computer : ");
		Long computer_id = clavier.nextLong();
		System.out.println("Name computer: ");
		String name = clavier.next();
		
		System.out.println("Introduced Date: jj-mm-yyyy");
		String introducedString = clavier.next();
		Timestamp introduced = newDateUpdate(introducedString);
		
		System.out.println("Discontinued Date: jj-mm-yyyy");
		String discontinuedString = clavier.next();
		Timestamp discontinued = newDateUpdate(discontinuedString);
		
		System.out.println("Id company : ");
		String id_company = clavier.next();
		Long company_id = newCompanyIdUpdate(id_company);
	}
	
	public static Timestamp newDateUpdate(String dateString) throws ParseException {
		
		Timestamp date;
		
		if (dateString.compareTo("null") == 0)
		{
		date = null;
		}
		else
		{
		Date dateDisco = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
		date = new Timestamp(dateDisco.getTime());
		}
		return date;
	}
	
	public static Long newCompanyIdUpdate(String idString) throws ParseException {
		
		Long company_id;
		
		if (idString.compareTo("null") == 0)
		{
		company_id = 0L;
		}
		else
		{
		company_id = new Long(idString);
		}
		
		return company_id;
	}
	
	
	public static void Add_Computer(Scanner clavier) throws ParseException {
		
		System.out.println("Name : ");
		String name = clavier.next();
		System.out.println("Introduced Date : jj-mm-yyyy");
		String introducedString = clavier.next();
		LocalDate introduced = createDateAdd(introducedString);
		
		System.out.println("Discontinued Date : jj-mm-yyyy");
		String discontinuedString = clavier.next();
		LocalDate discontinued = createDateAdd(discontinuedString);
		
		System.out.println("Company Id : ");
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
			date =  dateDisco.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
		}
		return date;
	}
	
	public static Company createCompanyAdd(String idCompany) throws ParseException {
		
		Long companyId;
		Company compa = null;
	
		if (idCompany.compareTo("null") == 0)
		{
			companyId = 0L;
		}
		else
		{
			companyId = Long.parseLong(idCompany);
			compa = serviceCompany.getCompany(companyId);
		}
		return compa;
	}
}