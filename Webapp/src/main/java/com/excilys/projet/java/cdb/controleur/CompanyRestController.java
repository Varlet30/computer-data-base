package com.excilys.projet.java.cdb.controleur;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.mapper.CompanyMapper;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.service.ServiceCompany;

@RestController
@CrossOrigin("*")
@RequestMapping("companies")
public class CompanyRestController {
    
    @Autowired
    private ServiceCompany companyService;

    @GetMapping(value = {"", "/"})
    public List<CompanyDTO> listCompanies() {
        List<Company> allCompanies = companyService.getCompanyList();
        return allCompanies.stream().map(c -> CompanyMapper.convertCompanytoCompanyDTO(c)).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public CompanyDTO getCompany (@PathVariable Long id) {
        return CompanyMapper.convertCompanytoCompanyDTO(companyService.getCompany(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    	companyService.getDeleteCompany(id);
    }
   
}
