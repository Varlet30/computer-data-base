package com.excilys.projet.java.cdb.mapper;

import org.junit.Test;

import static org.junit.Assert.fail;

import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.model.Company;

import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;

import static org.junit.Assert.assertEquals;

public class CompanyMapperTest {
		
	@Test
	public void testCompanyDtoId() {
		
		//GIVEN
		Long id = 0L;
		CompanyDTO compaDTO = new CompanyDTO();
		compaDTO.setId(id);
		
		//WHEN
		Company compa = CompanyMapper.convertCompanyDTOtoCompany(compaDTO);
        Company expCompany = new Company.CompanyBuilder().setId(id).build();
        
        //THEN
        assertEquals(expCompany.getId(), compa.getId());
	}
	
	@Test
	public void testCompanyDtoInverseNameId() {
		
		//GIVEN
		Long id = 1L;
		String name = "company";
		Company company = new Company();
		company.setId(id);
		company.setName(name);
		
		//WHEN
		CompanyDTO companyDTO = CompanyMapper.convertCompanytoCompanyDTO(company);
		
        //THEN
        assertEquals(companyDTO.getName(), company.getName());
	}
}