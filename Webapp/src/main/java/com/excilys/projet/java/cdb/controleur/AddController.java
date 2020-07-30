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
public class AddController 
{
	@Autowired
	public ServiceCompany serviceCompany;
	
	@Autowired
	public ServiceComputer serviceComputer;
	
	private String listCompany(ModelMap map){
		
		List<CompanyDTO>companyDTOList = new ArrayList<>();
		List<Company>companyList = serviceCompany.getCompanyList();
		companyList.stream().forEach(compa->companyDTOList.add(CompanyMapper.convertCompanytoCompanyDTO(compa)));
		
		map.put("listCompany", companyDTOList);
		
		return "addComputer";
	}
	private void addComput(ComputerDTO compDTO)
	{
		Computer comp = ComputerMapper.convertComputerDTOtoComputer(compDTO);
		serviceComputer.addComputer(comp);
	}
	
	@GetMapping("addComputer")
	public String getAddComputer(ModelMap map)
	{
		return listCompany(map);
	}
	
	@PostMapping("addComputer")
	public String postAddComputer (@ModelAttribute("computerToAdd")ComputerDTO compDTO,
			@RequestParam(value = "maxPage", defaultValue = "1")int maxPage,
			ModelMap map)
	{
		addComput(compDTO);
		map.put("computerToAdd", compDTO);
		
		return "redirect:dashboard?lengthPage=10&column=&tri=0&page="+ maxPage;
	}	
}
