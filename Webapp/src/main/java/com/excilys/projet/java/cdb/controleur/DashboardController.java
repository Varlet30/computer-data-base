package com.excilys.projet.java.cdb.controleur;

import java.util.List;
import java.util.ArrayList;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.projet.java.cdb.dto.ComputerDTO;
import com.excilys.projet.java.cdb.mapper.ComputerMapper;
import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceComputer;

@Controller
public class DashboardController 
{
	private int totalComputer;
	private int maxPage;
	
	@Autowired
	public ServiceComputer serviceComputer;
	
	private String search(String research,ModelMap map)
	{
		List<ComputerDTO>computerDTOList = new ArrayList<>();
		List<Computer>computerList = serviceComputer.findComputerByName(research);
		totalComputer = computerList.size();
		map.put("totalComputer", totalComputer);
		
		computerList.stream().forEach(comp->computerDTOList.add(ComputerMapper.convertComputertoComputerDTO(comp)));
		map.put("listComput", computerDTOList);
		
		return "dashboard";
	}
	private List<Computer> paginer(int tri, int lengthPage, int page, String column) 
	{
		List<Computer>computerList;
		
		tri %= 3;
		if (page != 1) {
			if (page == maxPage) {
				computerList = serviceComputer.getComputerListPaginer(tri,column,serviceComputer.getCount()%10,page*lengthPage);
			} else {
				computerList = serviceComputer.getComputerListPaginer(tri,column,lengthPage,page*lengthPage);
			}
		} else {
			computerList = serviceComputer.getComputerListPaginer(tri,column,lengthPage,0);
		}
		return computerList;
	}
	
	private String classification(int tri, String column, int lengthPage,int page, ModelMap map)
	{
		List<ComputerDTO>computerDTOList = new ArrayList<>();
		
		totalComputer = serviceComputer.getCount();
		maxPage = totalComputer/lengthPage;
		
		List<Computer>computerList = paginer(tri, lengthPage, page, column);
		computerList.stream().forEach(comp->computerDTOList.add(ComputerMapper.convertComputertoComputerDTO(comp)));
		
		map.put("listComput", computerDTOList);
		map.put("totalComputer", totalComputer);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("lengthPage", lengthPage);
		map.put("tri", tri);
		map.put("column", column);
		
		return "dashboard";
	}
	
	private void deleteComputers(String select)
	{
		String[] listId = select.split(",");
		
		for (int i = 0; i < listId.length; i++) {
			serviceComputer.deleteComputer(Long.parseLong(listId[i]));
		}
	}
	
	@GetMapping("dashboard")
	public String getDashboard(@RequestParam(value = "search", required = false) String research,
			@RequestParam(value = "tri", defaultValue = "0") int tri,
			@RequestParam(value = "column", defaultValue = "") String column,
			@RequestParam(value = "page", defaultValue = "1")int page,
			@RequestParam(value = "lengthPage",defaultValue = "10")int lengthPage,
			ModelMap map)
	{
		if (research == null||research.equals(""))
		{
			return classification(tri, column, lengthPage, page, map);
			
		} 
		else 
		{
			return search(research, map);
		}
	}
	
	@PostMapping("dashboard")
	public String postEditComputer (@RequestParam(value = "select", defaultValue = "")String select,
			ModelMap map)
	{
		deleteComputers(select);
		
		return "redirect:dashboard";
	}
}