package com.excilys.projet.java.cdb.service;

import java.util.List;
import com.excilys.projet.java.cdb.models.Company;
import com.excilys.projet.java.cdb.models.Page;
import com.excilys.projet.java.cdb.persistence.DAOs.CompanyDAO;

public class CompanyService {

    private static CompanyService companyService;

    private CompanyService() {
    }

    public static synchronized CompanyService getInstance() {
        if (companyService == null) {
            companyService = new CompanyService();
        }
        return companyService;
    }

    private CompanyDAO companyDAO = CompanyDAO.getInstance();

    public List<Company> getAll() {
        return companyDAO.getAll();
    }

    public List<Company> getAllByPage(Page page) {
        return companyDAO.getAllByPage(page);
    }

    public Company findById(Long id) {
        return companyDAO.findById(id).get();
    }


    public boolean exist(Long id) {
        return companyDAO.findById(id).isPresent();
    }

}