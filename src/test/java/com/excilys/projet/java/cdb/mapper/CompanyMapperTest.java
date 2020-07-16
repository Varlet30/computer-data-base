package com.excilys.projet.java.cdb.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.dto.CompanyDTO;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class CompanyMapperTest {
	
	private static final String IDCOMPANY = "id";
    private ResultSet resultSet = mock(ResultSet.class);

    private final Long id = 10L;

	@Test
	public void testDtoCompanyToCompany() throws Exception {
			try {
				when(resultSet.getLong(IDCOMPANY)).thenReturn(id);
			} catch (SQLException e) {
				fail("sql exception :" + e.getMessage());
			}
			CompanyDTO compaDTO = new CompanyDTO();
			compaDTO.setId(id);
			Company compa = CompanyMapper.convertCompanyDTOtoCompany(compaDTO);
	        Company expCompany = new Company.CompanyBuilder().setId(id).build();
	        
	        assertEquals(expCompany.getId(), compa.getId());
	}
	
	@Test
	public void testCompanyToDtoCompany() throws Exception {
			try {
				when(resultSet.getLong(IDCOMPANY)).thenReturn(id);
			} catch (SQLException e) {
				fail("sql exception :" + e.getMessage());
			}
			Company compa = new Company.CompanyBuilder().setId(id).build();
			CompanyDTO compaDTO = CompanyMapper.convertCompanytoCompanyDTO(compa);
	        CompanyDTO expCompany = new CompanyDTO(id.toString());
	        
	        assertEquals(expCompany.getId(), compaDTO.getId());
	}
}
