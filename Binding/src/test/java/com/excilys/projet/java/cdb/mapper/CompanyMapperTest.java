package com.excilys.projet.java.cdb.mapper;

import org.junit.Test;

import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CompanyMapperTest {
	
	private static final String IDCOMPANY = "id";
	private static final String NAMECOMPANY = "company";
	private ResultSet resultSet = mock(ResultSet.class);
	private final Long id = 10L;
	private final String name = "company";
	
	@Test
	public void testCompanyDtoId() {
		
		//GIVEN
		try {
			when(resultSet.getLong(IDCOMPANY)).thenReturn(id);
		} catch (SQLException e) {
			fail("sql exception :" + e.getMessage());
		}
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
		try {
			when(resultSet.getString(NAMECOMPANY)).thenReturn(name);
		} catch (SQLException e) {
			fail("sql exception :" + e.getMessage());
		}
		Company company = new Company();
		company.setId(id);
		company.setName(name);
		
		//WHEN
		CompanyDTO companyDTO = CompanyMapper.convertCompanytoCompanyDTO(company);
		
        //THEN
        assertEquals(companyDTO.getName(), company.getName());
	}
}