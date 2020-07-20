package com.excilys.projet.java.cdb.selenium;

import java.util.concurrent.TimeUnit;

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

	@Test
	public void testSelenium() throws InterruptedException {
		driver = new FirefoxDriver();
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/webModule/dashboard");
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
	}
}