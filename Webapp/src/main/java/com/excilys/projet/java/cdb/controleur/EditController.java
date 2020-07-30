package com.excilys.projet.java.cdb.controleur;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.dto.ComputerDTO;
import com.excilys.projet.java.cdb.mapper.CompanyMapper;
import com.excilys.projet.java.cdb.mapper.ComputerMapper;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceCompany;
import com.excilys.projet.java.cdb.service.ServiceComputer;

@Controller
public class EditController 
{
	@Autowired
	public ServiceCompany serviceCompany;
	@Autowired
	public ServiceComputer serviceComputer;
	
	private String findComputUpdate(String idComputer, ModelMap map)
	{
		List<CompanyDTO>companyDTOList = new ArrayList<>();
		List<Company>companyList = serviceCompany.getCompanyList();
		companyList.stream().forEach(compa->companyDTOList.add(CompanyMapper.convertCompanytoCompanyDTO(compa)));
		
		map.put("listCompany", companyDTOList);
		
		long id = Long.parseLong(idComputer);
		
		Computer comput = serviceComputer.findComputerById(id);
		ComputerDTO compDTO = ComputerMapper.convertComputertoComputerDTO(comput);
		
		map.put("computerToUpdate", compDTO);
		
		return "editComputer";
	}
	private void updateComput(ComputerDTO compDTO)
	{
		Computer comp = ComputerMapper.convertComputerDTOtoComputer(compDTO);
		serviceComputer.updateComputer(comp);
	}
	
	@GetMapping("editComputer")
	public String getEditComputer(@RequestParam(value = "id", defaultValue = "1") String idComputer,
			ModelMap map)
	{
		return findComputUpdate(idComputer, map);
	}
	
	@PostMapping("editComputer")
	public String postEditComputer (@ModelAttribute("computerToUpdate")ComputerDTO compDTO,
			@RequestParam(value = "maxPage", defaultValue = "1")int maxPage, ModelMap map)
	{
		updateComput(compDTO);
		map.put("computerToUpdate", compDTO);
		
		return "dashboard";
	}
}
