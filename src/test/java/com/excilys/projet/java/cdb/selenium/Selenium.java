package com.excilys.projet.java.cdb.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Selenium {
	private WebDriver driver;
	private Actions actions;

	public Selenium() {
		System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver");
	}

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/cdb/");
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testSelenium() throws InterruptedException {
		actions.moveToElement(driver.findElement(By.id("dashboard"))).perform();
		actions.pause(1000).click().perform();
		actions.moveToElement(driver.findElement(By.name("2"))).perform();
		actions.pause(1000).click().perform();
		actions.moveToElement(driver.findElement(By.name("3"))).perform();
		actions.pause(1000).click().perform();
		actions.moveToElement(driver.findElement(By.id("editComputer"))).perform();
		actions.pause(1000).click().perform();
		actions.moveToElement(driver.findElement(By.id("addComputer"))).perform();
		actions.pause(1000).click().perform();
		actions.moveToElement(driver.findElement(By.id("cancel"))).perform();
		actions.pause(1000).click().perform();
	}
}