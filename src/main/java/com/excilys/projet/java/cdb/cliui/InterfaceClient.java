package com.excilys.projet.java.cdb.cliui;

import java.util.Date;
import java.util.InputMismatchException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.projet.java.cdb.models.Company;
import com.excilys.projet.java.cdb.models.Computer;
import com.excilys.projet.java.cdb.models.Page;
import com.excilys.projet.java.cdb.service.CompanyService;
import com.excilys.projet.java.cdb.service.ComputerService;

public class InterfaceClient {

    private static ComputerService computerService = ComputerService.getInstance();
    private static CompanyService companyService = CompanyService.getInstance();
    private static Scanner scanner = new Scanner(System.in);
    
    private static final Logger logger = LoggerFactory.getLogger(InterfaceClient.class);

    public void start() throws ParseException {

        boolean quit = false;

        while (!quit) {
            System.out.println("Computer Database \n\n"
            				 + "Available features : \n"
            				 + "1 -> List computers \n"
            				 + "2 -> List companies \n"
            				 + "3 -> Show computer details \n"
            				 + "4 -> Create a computer \n"
            				 + "5 -> Update a computer \n"
            				 + "6 -> Delete a computer \n"
            				 + "7 -> Exit \n\n"
        					 + "-- Enter your choice: --");
            try {
            	String featureChoice = scanner.next();
            	scanner.nextLine();

            	switch (featureChoice) {
            	case "1":
            		logger.info("Show All Computers");
            		showAllComputers();
            		break;

            	case "2":
            		logger.info("Show All Companies");
            		showAllCompanies();
            		break;

            	case "3":
            		logger.info("Computer Details");
            		System.out.println("Enter the id of a computer: ");
            		Long computerId = scanner.nextLong();

            		if (computerService.exist(computerId)) {
            			Computer computer = computerService.findById(computerId);
            			System.out.println(computer.toString());
            		} else {
            			System.out.println("Doesn't exit.");
            		}

            		backToMenu();

            		break;

            	case "4":
            		logger.info("Input Computer");
            		boolean askId = false;
            		Computer computer = inputComputer(askId);
            		computerService.create(computer);
                
            		break;

            	case "5":
            		logger.info("Update Computer");
            		boolean needId = true;
            		Computer computerUpd = inputComputer(needId);

            		if (computerService.exist(computerUpd.getIdComputer())) {
            			computerService.update(computerUpd);
            			System.out.println("Updated");
            		} else {
            			System.out.println("Error update");
            		}
            		break;

            	case "6":
            		logger.info("Delete Computer");
            		System.out.println("Id computer to delete: ");
            		Long id = scanner.nextLong();
            		boolean exist = computerService.exist(id);

            		while (!exist) {
            			System.out.println("Id doesn't exist.");
            			System.out.println("Enter the id of an existing  computer : ");
            			id = scanner.nextLong();
            		}
            		scanner.nextLine();
            		computerService.delete(id);
            		System.out.println("Delete OK.");
            		break;
            	case "7":
            		logger.info("Exit");
            		quit = true;
            		break;

            	default:
            		System.out.println("This feature doesn't exist.");
            	}
            	
            }catch(InputMismatchException e) {
        		System.out.println("just int");
        		scanner.close();
        	}	
        }

        scanner.close();
    }

    public Computer inputComputer(boolean needId) throws ParseException {
        Computer computer = new Computer();

        if (needId) {
            System.out.println("Enter the id of the computer: ");
            String id = scanner.nextLine();
            while (id.equals("")) {
                System.out.println("The id cannot be empty.");
                System.out.println("Enter the id of the computer : ");
                id = scanner.nextLine();
            }
            computer.setIdComputer(Long.valueOf(id));
        }

        System.out.println("Enter the name of the computer: ");
        String name = scanner.nextLine();
        while (name.equals("")) {
            System.out.println("The name cannot be empty.");
            System.out.println("Enter the name of the computer : ");
            name = scanner.nextLine();
        }
        computer.setName(name);

        System.out
                .println("Enter the introduced date of the computer in the format YYYY-MM-DD: (press <Enter> to skip)");
        String introduced = scanner.nextLine();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");    

        if (!introduced.equals("")) {
            Date dateIntroduced = format.parse(introduced);
            java.sql.Date sqlIntroducedDate = new java.sql.Date(dateIntroduced.getTime());
            computer.setIntroducedDate(sqlIntroducedDate);
            System.out.println("Enter the discontinued date of the computer in the format YYYY-MM-DD: (press <Enter> to skip)");
            String discontinued = scanner.nextLine();
            if (!discontinued.equals("")) {
            	Date dateDiscontinued = format.parse(discontinued); 
            	java.sql.Date sqlDiscontinuedDate = new java.sql.Date(dateDiscontinued.getTime());
                computer.setDiscontinuedDate(sqlDiscontinuedDate);
            }
        }
        System.out.println("Enter the manufacturer company id:(press <Enter> to skip)");
        String companyId = scanner.nextLine();
        if (!companyId.equals("")) {
            computer.setCompany(new Company(Long.valueOf(companyId), companyId));
        }

        return computer;
    }

    public void showAllCompanies() {
        Page currentPage = new Page();
        boolean quit = false;
        List<Company> allCompanies = companyService.getAll();
        int numberOfCompanies = allCompanies.size();

        do {
            List<Company> allCompaniesbyPage = companyService.getAllByPage(currentPage);
            allCompaniesbyPage.forEach(cp -> System.out.println(cp.toString()));
            System.out.println(
                    "Page " + currentPage.getCurrentPage() + "/" + currentPage.getTotalPages(numberOfCompanies));
            System.out.println("(Enter 'q' to quit, 'p' to go to the previous page, 'n' to go to the next page.)");

            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
            case "p":
                if (currentPage.hasPreviousPage()) {
                    currentPage.previousPage();
                }
                break;
            case "n":
                if (currentPage.hasNextPage(numberOfCompanies)) {
                    currentPage.nextPage();
                }
                break;
            case "q":
                quit = true;
                break;
            }
        } while (!quit);
    }

    public void showAllComputers() {
        Page currentPage = new Page();
        boolean quit = false;
        List<Computer> allComputers = computerService.getAll();
        int numberOfComputers = allComputers.size();

        do {
            List<Computer> allComputersbyPage = computerService.getAllByPage(currentPage);
            allComputersbyPage.forEach(cp -> System.out.println(cp.toString()));
            System.out.println(
                    "Page " + currentPage.getCurrentPage() + "/" + currentPage.getTotalPages(numberOfComputers));
            System.out.println("(Enter 'q' to quit, 'p' to go to the previous page, 'n' to go to the next page.)");

            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
            case "p":
                if (currentPage.hasPreviousPage()) {
                    currentPage.previousPage();
                }
                break;
            case "n":
                if (currentPage.hasNextPage(numberOfComputers)) {
                    currentPage.nextPage();
                }
                break;
            case "q":
                quit = true;
                break;
            }
        } while (!quit);
    }

    public void backToMenu() {
        System.out.println("(Enter 'q' to quit this page and go to main page.)");
        boolean quit = false;
        do {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("q")) {
                quit = true;
            }
        } while (!quit);
    }
}
