package com.excilys.projet.java.cdb.utilisateur;

import java.util.Date;
import java.util.Scanner;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.*;

import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceCompany;
import com.excilys.projet.java.cdb.service.ServiceComputer;

public class InterfaceUtilisateur 
{
	public static void AfficherInterface() throws ParseException, ClassNotFoundException, SQLException
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
		if (i == 1)
		{
			Add_Computer(clavier);
		}
		else
		{
			if (i == 2)
			{
				Update_Computer_By_Id(clavier);
			}
			else
			{
				if (i == 3)
				{	
					Delete_Company_By_Id(clavier);
				}
				else
				{
					if (i == 4)
					{
						Find_Computer_By_Name(clavier);
					}
					else
					{
						if (i == 5)
						{
							Display_All_Computers(clavier);
						}
						else
						{
							if (i == 6)
							{
								Display_All_Company(clavier);
							}
							else
							{
								if (i == 7)
								{
									Find_Company_By_Id(clavier);
								}
								else
								{
									if (i == 8)
									{
										Dispaly_Page_Computers(clavier);
									}
									else
									{
										if (i == 9)
										{
											Delete_Computer(clavier);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		clavier.close();
	}
	
	public static void Delete_Computer(Scanner clavier) throws ClassNotFoundException {
		System.out.println("Id computer to delete:");
		Long computerId = clavier.nextLong();
		ServiceComputer.getInstance().deleteComputer(computerId);
	}
	
	public static void Dispaly_Page_Computers(Scanner clavier) throws ClassNotFoundException {
		System.out.println("Nb computer to view: ");
		int limit = clavier.nextInt();
		System.out.println("Nb computer to view: ");
		int offset = clavier.nextInt();
		ServiceComputer.getInstance().getComputerListPaginer(0, null, limit, offset);
	}
	
	public static void Find_Company_By_Id(Scanner clavier) throws ClassNotFoundException {
		System.out.println("Id company to find : ");
		Long companyId = clavier.nextLong();
		ServiceCompany.getInstance().getCompany(companyId);
	}
	
	public static void Display_All_Computers(Scanner clavier) throws ClassNotFoundException {
		ServiceComputer.getInstance().getComputerList();
	}
	
	public static void Display_All_Company(Scanner clavier) throws ClassNotFoundException {
		ServiceCompany.getInstance().getCompanyList();
	}
	
	public static void Find_Computer_By_Name(Scanner clavier) throws ClassNotFoundException {
		System.out.println("Name computer to find : ");
		String NameComputer = clavier.next();
		ServiceComputer.getInstance().findComputerByName(NameComputer);
	}
	
	public static void Delete_Company_By_Id(Scanner clavier) throws SQLException, ClassNotFoundException {	
		System.out.println("Id company to delete: ");
		Long companyId = clavier.nextLong();
		ServiceCompany.getInstance().getDeleteCompany(companyId);
	}
	
	public static void Update_Computer_By_Id(Scanner clavier) throws ParseException{	
		System.out.println("Id computer : ");
		Long computer_id = clavier.nextLong();
		System.out.println("Name computer: ");
		String name = clavier.next();
		System.out.println("Introduced Date: jj-mm-yyyy");
		String introducedString = clavier.next();
		Timestamp introduced;
		if (introducedString.compareTo("null")==0)
		{
		introduced = null;
		}
		else
		{
		Date dateIntro = new SimpleDateFormat("dd-MM-yyyy").parse(introducedString);
		introduced = new Timestamp(dateIntro.getTime());
		}
		System.out.println("Discontinued Date: jj-mm-yyyy");
		String discontinuedString = clavier.next();
		Timestamp discontinued;
		if (discontinuedString.compareTo("null") == 0)
		{
		discontinued = null;
		}
		else
		{
		Date dateDisco = new SimpleDateFormat("dd-MM-yyyy").parse(discontinuedString);
		discontinued = new Timestamp(dateDisco.getTime());
		}
		System.out.println("Id company : ");
		String id_company = clavier.next();
		Long company_id;
		if (id_company.compareTo("null") == 0)
		{
		company_id = 0L;
		}
		else
		{
		company_id = new Long(id_company);
		}
	}
	
	public static void Add_Computer(Scanner clavier) throws ParseException, ClassNotFoundException {
		System.out.println("Name : ");
		String name = clavier.next();
		System.out.println("Introduced Date : jj-mm-yyyy");
		String introducedString = clavier.next();
		LocalDate introduced;
		if (introducedString.compareTo("null") == 0) {
			introduced = null;
		}
		else
		{
			Date dateIntro = new SimpleDateFormat("dd-MM-yyyy").parse(introducedString);
			introduced = dateIntro.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
		System.out.println("Discontinued Date : jj-mm-yyyy");
		String discontinuedString = clavier.next();
		LocalDate discontinued;
		if (discontinuedString.compareTo("null") == 0)
		{
			discontinued = null;
		}
		else
		{
			Date dateDisco = new SimpleDateFormat("dd-MM-yyyy").parse(discontinuedString);
			discontinued =  dateDisco.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
		}
		System.out.println("Company Id : ");
		String idCompany = clavier.next();
		Long companyId;
		Company compa = null;
		if (idCompany.compareTo("null") == 0)
		{
			companyId = 0L;
		}
		else
		{
			companyId = Long.parseLong(idCompany);
			compa = ServiceCompany.getInstance().getCompany(companyId);
		}
		Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			 
		ServiceComputer.getInstance().addComputer(comp);
	}
}