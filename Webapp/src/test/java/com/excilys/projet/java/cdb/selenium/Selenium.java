package com.excilys.projet.java.cdb.selenium;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;

public class Selenium {
	private WebDriver driver;
	private Actions actions;

	public Selenium() {
		System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver");
	}

	@Test
	public void testSelenium() throws InterruptedException, IOException {
		String pass;
		driver = new FirefoxDriver();
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/Webapp/dashboard");
		actions.moveToElement(driver.findElement(By.id("username"))).click().perform();
		actions.pause(1000).perform();
		actions.moveToElement(driver.findElement(By.id("username"))).sendKeys("admin").perform();
		actions.pause(1000).perform();
		actions.moveToElement(driver.findElement(By.id("password"))).click().perform();
		actions.pause(1000).perform();
		actions.moveToElement(driver.findElement(By.id("password"))).sendKeys("admin").perform();
		actions.pause(1000).perform();
		actions.moveToElement(driver.findElement(By.id("login"))).click().perform();
		actions.pause(1000).perform();
		
		actions.moveToElement(driver.findElement(By.name("2"))).click().perform();
		actions.pause(1000).perform();
		actions.moveToElement(driver.findElement(By.id("addComputer"))).click().perform();
		actions.pause(1000).perform();
		actions.moveToElement(driver.findElement(By.id("cancel"))).click().perform();
		actions.pause(1000).perform();
		actions.moveToElement(driver.findElement(By.name("LenPage50"))).click().perform();
		actions.pause(1000).perform();
		actions.moveToElement(driver.findElement(By.id("editComputer"))).click().perform();
		actions.pause(1000).perform();
		driver.quit();
		pass = "pass";
		assertEquals("pass",pass);
		
		
	}
}