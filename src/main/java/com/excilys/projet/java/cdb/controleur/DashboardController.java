package com.excilys.projet.java.cdb.controleur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.projet.java.cdb.dto.ComputerDTO;
import com.excilys.projet.java.cdb.mapper.ComputerMapper;
import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceComputer;

@Controller
public class DashboardController {
	
	private int totalComputer;
	private int maxPage;
	
	@Autowired
	public ServiceComputer serviceComputer;
	
	private String search(String recherche,ModelMap dataMap){
		List<ComputerDTO>computerDTOList=new ArrayList<ComputerDTO>();
		List<Computer>computerList=new ArrayList<Computer>();
		computerList=serviceComputer.findComputerByName(recherche);
		totalComputer=computerList.size();
		dataMap.put("totalComputer", totalComputer);
		computerList.stream().forEach(comp->computerDTOList.add(ComputerMapper.convertComputertoComputerDTO(comp)));
		dataMap.put("listComput", computerDTOList);
		return "dashboard";
	}
	private String classification(int tri, String column, int lengthPage,int page, ModelMap dataMap){
		List<ComputerDTO>computerDTOList=new ArrayList<ComputerDTO>();
		List<Computer>computerList=new ArrayList<Computer>();
		totalComputer=serviceComputer.getCount();
		maxPage=totalComputer/lengthPage;
		tri%=3;
		if (page!=1) {
			if (page==maxPage) {
				computerList=serviceComputer.getComputerListPaginer(tri,column,serviceComputer.getCount()%10,page*lengthPage);
			} else {
				computerList=serviceComputer.getComputerListPaginer(tri,column,lengthPage,page*lengthPage);
			}
		} else {
			computerList=serviceComputer.getComputerListPaginer(tri,column,lengthPage,0);
		}
		computerList.stream().forEach(comp->computerDTOList.add(ComputerMapper.convertComputertoComputerDTO(comp)));
		dataMap.put("listComput", computerDTOList);
		dataMap.put("totalComputer", totalComputer);
		dataMap.put("page", page);
		dataMap.put("maxPage", maxPage);
		dataMap.put("lengthPage", lengthPage);
		dataMap.put("tri", tri);
		dataMap.put("column", column);
		return "dashboard";
	}
	private void deleteComputers(String selection, ModelMap dataMap) throws NumberFormatException
	{
		String[] listId = selection.split(",");
		for (int i=0;i<listId.length;i++) {
			serviceComputer.deleteComputer(Long.parseLong(listId[i]));
		}
	}
	
	@GetMapping("dashboard")
	public String getDashboard(@RequestParam(value="search", required = false) String recherche,
			@RequestParam(value="tri", defaultValue = "0") int tri,
			@RequestParam(value="column", defaultValue = "") String column,
			@RequestParam(value="page", defaultValue = "1")int page,
			@RequestParam(value="lengthPage",defaultValue = "10")int lengthPage,
			ModelMap dataMap){
		if (recherche==null||recherche=="") {
			return classification(tri, column, lengthPage, page, dataMap);
		} else {
			return search(recherche, dataMap);
		}
		
	}
	
	@PostMapping("dashboard")
	public String postEditComputer (@RequestParam(value="selection", defaultValue = "")String selection,
			ModelMap dataMap) throws NumberFormatException{
		deleteComputers(selection,dataMap);
		return "redirect:dashboard";
	}
}
	