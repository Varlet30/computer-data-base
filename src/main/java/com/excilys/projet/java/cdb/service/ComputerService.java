package main.java.com.excilys.projet.java.cdb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.com.excilys.projet.java.cdb.models.Computer;
import main.java.com.excilys.projet.java.cdb.models.Page;
import main.java.com.excilys.projet.java.cdb.persistence.DAOs.CompanyDAO;
import main.java.com.excilys.projet.java.cdb.persistence.DAOs.ComputerDAO;

public class ComputerService {

    private static ComputerService computerService;

    private ComputerService() {
    }

    public static synchronized ComputerService getInstance() {
        if (computerService == null) {
            computerService = new ComputerService();
        }
        return computerService;
    }

    private ComputerDAO computerDAO = ComputerDAO.getInstance();
    private CompanyDAO companyDAO = CompanyDAO.getInstance();

    private static Logger logger = LoggerFactory.getLogger(ComputerService.class);


    public List<Computer> getAll() {
        return computerDAO.getAll();
    }

    public List<Computer> getAllByPage(Page page) {
        return computerDAO.getAllByPage(page);
    }

      public Computer findById(Long id) {
        return computerDAO.findById(id).get();
    }

    public void create(Computer computer) {
        computerDAO.create(computer);
    }

    public boolean allowedToCreateOrEdit(Computer computer) {
        boolean allowed = true;
        if (computer.getName() == null || computer.getName().isEmpty()) {
            logger.info("computer name is required");
            allowed = false;
        } else if (computer.getDiscontinuedDate() != null) {
            if (computer.getIntroducedDate() == null) {
                allowed = false;
                logger.info("introduced date is null");
            } else if (computer.getDiscontinuedDate().isBefore(computer.getIntroducedDate())) {
                allowed = false;
                logger.info("discontinued is before intro");
            }
            if (computer.getCompany() != null) {
                if (!companyDAO.findById(computer.getCompany().getIdCompany()).isPresent()) {
                    allowed = false;
                    logger.info("company does not exist");
                }
            }
        }
        return allowed;
    }

    public void update(Computer computer) {
        computerDAO.update(computer);
    }

    public void delete(Long id) {
        computerDAO.delete(id);
    }

    public boolean exist(Long id) {
        return computerDAO.findById(id).isPresent();
    }
}
