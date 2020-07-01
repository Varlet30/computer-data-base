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
		else
		{
			if (i == 2)
			{
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
			else
			{
				if (i == 3)
				{
					System.out.println("Id company to delete: ");
					Long companyId = clavier.nextLong();
					ServiceCompany.getInstance().getDeleteCompany(companyId);
				}
				else
				{
					if (i == 4)
					{
						System.out.println("Name computer to find : ");
					}
					else
					{
						if (i == 5)
						{
							ServiceComputer.getInstance().getComputerList();
						}
						else
						{
							if (i == 6)
							{
								ServiceCompany.getInstance().getCompanyList();
							}
							else
							{
								if (i == 7)
								{
									System.out.println("Id company to find : ");
									Long companyId = clavier.nextLong();
									ServiceCompany.getInstance().getCompany(companyId);
								}
								else
								{
									if (i == 8)
									{
										System.out.println("Nb computer to view: ");
										int limit = clavier.nextInt();
										System.out.println("Nb computer to view: ");
										int offset = clavier.nextInt();
										ServiceComputer.getInstance().getComputerListPaginer(0, null, limit, offset);
									}
									else
									{
										if (i == 9)
										{
											System.out.println("Id computer to delete:");
											Long computerId = clavier.nextLong();
											ServiceComputer.getInstance().deleteComputer(computerId);
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
}