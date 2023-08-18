package com.scripted.desktop;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class DesktopWaitHelper {
	public static final Logger LOGGER = Logger.getLogger(DesktopDriver.class);
	public static Actions action = new Actions(DesktopDriver.dDriver);

	protected static int getElementTimeout() {
		return 10;
	}

	public static void waitForElement(WebElement webEle) {
		try
		{
		WebDriverWait wait = new WebDriverWait(DesktopDriver.dDriver, 30);
		By byEle = DesktopHandlers.DesktopElementToBy(webEle);
		wait.until(ExpectedConditions.elementToBeClickable(byEle));
		}
		catch(Exception e)
		{
			LOGGER.error("Error while waiting for element "+" Exception :"+e);
			Assert.fail("Error while waiting for element "+" Exception :"+e);
		}
	}
	
	public static void scrollToElement(By locator) {
		try
		{
		WebElement element = DesktopDriver.dDriver.findElement(locator);
		Coordinates coordinate = ((Locatable) element).getCoordinates();
		coordinate.onPage();
		coordinate.inViewPort();
		}
		catch(Exception e)
		{
			LOGGER.error("Error while trying to scroll to the element "+" Exception :"+e);
			Assert.fail("Error while  trying to scroll to the element "+" Exception :"+e);
		}
	}

	public static Actions getAction() {
		return new Actions(DesktopDriver.dDriver);
	}
}
