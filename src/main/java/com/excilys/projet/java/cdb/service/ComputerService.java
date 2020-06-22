package com.excilys.projet.java.cdb.service;

import java.util.List;

import com.excilys.projet.java.cdb.models.Computer;
import com.excilys.projet.java.cdb.models.Page;
import com.excilys.projet.java.cdb.persistence.DAOs.ComputerDAO;

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
