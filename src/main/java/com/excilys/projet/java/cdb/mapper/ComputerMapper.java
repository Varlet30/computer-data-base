package com.excilys.projet.java.cdb.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.dto.ComputerDTO;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;

@Repository
public class ComputerMapper 
{
	
	public static Computer convert(ComputerDTO compDTO)
	{
		Long id = compDTO.getIdComputer();
		String name = compDTO.getName();
		String intro = compDTO.getIntroduced();
		String disco = compDTO.getDiscontinued();
		CompanyDTO compaDTO = compDTO.getCompa();
		LocalDate introduced = LocalDate.parse(intro);
		LocalDate discontinued = LocalDate.parse(disco);
		Company compa = CompanyMapper.convert(compaDTO);
		Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build(); 
		comp.setId(id);
		return comp;
	}
	
	public static ComputerDTO convertInverse(Computer comp)
	{
		String name = comp.getName();
		LocalDate intro = comp.getIntroduced();
		LocalDate disco = comp.getDiscontinued();
		Company company = comp.getCompany();
		CompanyDTO compaDTO = CompanyMapper.convertInverse(company);
		ComputerDTO compDTO = new ComputerDTO(name, intro.toString(), disco.toString(), compaDTO); 
		return compDTO;
	}
	
	public static Computer convertResult(HttpServletRequest request)
	{
		String name = request.getParameter("computerName");
		String computerIntroduced = request.getParameter("computerIntroduced");
		String computerDiscontinued = request.getParameter("computerDiscontinued");
		String company = request.getParameter("company");
		CompanyDTO compaDTO = new CompanyDTO(company);
		ComputerDTO compuDTO = new ComputerDTO(name,computerIntroduced,computerDiscontinued,compaDTO); 
		Computer comp = ComputerMapper.convert(compuDTO);
		return comp;
	}
	
	public static Computer convertResultId(HttpServletRequest request)
	{
		Long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String company = request.getParameter("company");
		CompanyDTO compaDTO = new CompanyDTO(company);
		ComputerDTO compuDTO = new ComputerDTO(name,introduced,discontinued,compaDTO);
		compuDTO.setIdComputer(id);
		Computer comp = ComputerMapper.convert(compuDTO);
		return comp;
	}
	public static Computer convertRequestByName(ResultSet resultat) throws SQLException
	{
		String name = resultat.getString("computerName");
		Date intro = resultat.getDate("computerIntroduced");
		Date disco = resultat.getDate("computerDiscontinued");
		LocalDate introduced = null;
		if (intro != null)
		{
			introduced = intro.toLocalDate();
		}
		LocalDate discontinued = null;
		if (disco != null)
		{
			discontinued = disco.toLocalDate();
		}
		String companyName = resultat.getString("companyName");
		Company compa = new Company.CompanyBuilder().setName(companyName).build();
		long id = resultat.getLong("computerId");
		Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			
		comp.setId(id);
		return comp;
	}
	public static Computer convertRequestById(ResultSet resultat) throws SQLException
	{
		String name = resultat.getString("computerName");
		Date intro = resultat.getDate("computerIntroduced");
		Date disco = resultat.getDate("computerDiscontinued");
		LocalDate introduced = null;
		if (intro != null)
		{
			introduced = intro.toLocalDate();
		}
		LocalDate discontinued = null;
		if (disco != null)
		{
			discontinued = disco.toLocalDate();
		}
		Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).build();
		return comp;
	}
}
