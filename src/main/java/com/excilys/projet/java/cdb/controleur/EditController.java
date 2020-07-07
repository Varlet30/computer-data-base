package com.excilys.projet.java.cdb.controleur;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
public class EditController {
	
	@Autowired
	public ServiceCompany serviceCompany;
	@Autowired
	public ServiceComputer serviceComputer;
	
	private String findComputUpdate(String idComputer, ModelMap dataMap){
		List<CompanyDTO>companyDTOList=new ArrayList<CompanyDTO>();
		List<Company>companyList=new ArrayList<Company>();
		companyList=serviceCompany.getCompanyList();
		companyList.stream().forEach(compa->companyDTOList.add(CompanyMapper.convertCompanytoCompanyDTO(compa)));
		dataMap.put("listCompany", companyDTOList);
		long id = Long.parseLong(idComputer);
		Computer comput = serviceComputer.findComputerById(id);
		ComputerDTO compDTO = ComputerMapper.convertComputertoComputerDTO(comput);
		dataMap.put("computerToUpdate",compDTO);
		return "editComputer";
	}
	private void updateComput(ComputerDTO compDTO, ModelMap dataMap){
		Computer comp = ComputerMapper.convertComputerDTOtoComputer(compDTO);
		serviceComputer.editComputer(comp);
	}
	
	@GetMapping("editComputer")
	public String getEditComputer(@RequestParam(value="id", defaultValue = "1") String idComputer,
			ModelMap dataMap){
		return findComputUpdate(idComputer, dataMap);
	}
	
	@PostMapping("editComputer")
	public String postEditComputer (@ModelAttribute("computerToUpdate")ComputerDTO compDTO,
			@RequestParam(value="maxPage", defaultValue = "1")int maxPage,
			ModelMap dataMap){
		System.out.println(compDTO);
		updateComput(compDTO, dataMap);
		dataMap.put("computerToUpdate", compDTO);
		return "redirect:dashboard?lengthPage=10&column=&tri=0&page="+maxPage;
	}
}
