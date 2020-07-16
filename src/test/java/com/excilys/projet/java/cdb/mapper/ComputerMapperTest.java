package com.excilys.projet.java.cdb.mapper;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.dto.ComputerDTO;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;

public class ComputerMapperTest {

    private static final String IDCOMPUTER = "computer.id";
    private static final String NAMECOMPUTER = "computer.name";
    private static final String INTRODUCEDCOMPUTER = "computer.introduced";
    private static final String DISCONTINUEDCOMPUTER = "computer.discontinued";
    private static final String IDCOMPANY = "id";

    private final Long idComputer = 10L;
    private final String computerName = "computer name";
    private final Date introduced = new Date(100L);
    private final Date discontinued = new Date(100L);
    private final Long idCompany = 11L;

    private ResultSet resultSet = mock(ResultSet.class);

	@Test
    public void testConvert() throws SQLException {
        try {
            when(resultSet.getLong(IDCOMPUTER)).thenReturn(idComputer);
            when(resultSet.getString(NAMECOMPUTER)).thenReturn(computerName);
            when(resultSet.getDate(INTRODUCEDCOMPUTER)).thenReturn(introduced);
            when(resultSet.getDate(DISCONTINUEDCOMPUTER)).thenReturn(discontinued);
            when(resultSet.getLong(IDCOMPANY)).thenReturn(idCompany);
        } catch (SQLException e) {
            fail("sql exception :" + e.getMessage());
        }
        CompanyDTO compaDTO = new CompanyDTO();
		compaDTO.setId(idCompany);
        ComputerDTO compuDTO =new ComputerDTO(computerName, introduced.toString(), discontinued.toString(), compaDTO); 
		Computer computer = ComputerMapper.convert(compuDTO);
        
        Company compa = new Company.CompanyBuilder().setId(idCompany).build();
        
        Computer expComputer = new Computer.ComputerBuilder(computerName).setDiscontinued(discontinued.toLocalDate()).setIntroduced(introduced.toLocalDate()).setCompany(compa).build();
        
        assertEquals(expComputer.getId(), computer.getId());
        assertEquals(expComputer.getName(), computer.getName());
        assertEquals(expComputer.getIntroduced(), computer.getIntroduced());
        assertEquals(expComputer.getDiscontinued(), computer.getDiscontinued());
        assertEquals(expComputer.getCompany().getId(), computer.getCompany().getId());
        
    }
}    
    