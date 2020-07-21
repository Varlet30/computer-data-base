package com.excilys.projet.java.cdb.controleur;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.excilys.projet.java.cdb.spring.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class DashboardControllerTest {
	
	@Autowired
	public DashboardController dashboardController;
	
	@Test
	public void testPostDashboardController() {
		
		//GIVEN
		String request = "redirect:dashboard";
		ModelMap map = new ModelMap();
		String select = "5";
		
		//WHEN		
		String result =  dashboardController.postEditComputer(select, map);
		
		//THEN
		assertEquals(result, request);
	}
	
	@Test
	public void testGetDashboardControllerClassificationPageIsMaxPage() {
		
		//GIVEN
		ModelMap map = new ModelMap();
		String request = "dashboard";
		int maxPage = 569;
		int page = 1;
		int tri = 0;
		String research = null;
		String column = null;
		
		//WHEN		
		String result =  dashboardController.getDashboard(research, tri, column, maxPage, page, map);
		
		//THEN
		assertEquals(result, request);
	}
	
	@Test
	public void testGetDashboardControllerClassificationPage1() {
		
		//GIVEN
		ModelMap map = new ModelMap();
		String request = "dashboard";
		int maxPage = 1;
		int page = 1;
		int tri = 0;
		String research = null;
		String column = null;
		
		//WHEN		
		String result =  dashboardController.getDashboard(research, tri, column, maxPage, page, map);
		
		//THEN
		assertEquals(result, request);
	}
	
	@Test
	public void testGetDashboardControllerClassificationPage2() {
		
		//GIVEN
		ModelMap map = new ModelMap();
		String request = "dashboard";
		int maxPage = 2;
		int page = 1;
		int tri = 0;
		String research = null;
		String column = null;
		
		//WHEN		
		String result =  dashboardController.getDashboard(research, tri, column, maxPage, page, map);
		
		//THEN
		assertEquals(result, request);
	}
	
	@Test
	public void testGetDashboardControllerSearch() {
		
		//GIVEN
		ModelMap map = new ModelMap();
		String request = "dashboard";
		int maxPage = 0;
		int page = 0;
		int tri = 0;
		String research = "test";
		String column = null;
		
		//WHEN		
		String result =  dashboardController.getDashboard(research, tri, column, maxPage, page, map);
		
		//THEN
		assertEquals(result, request);
	}
}