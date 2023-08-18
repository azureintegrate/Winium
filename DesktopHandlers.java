package com.scripted.desktop;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class DesktopHandlers {

	private static final Logger log = Logger.getLogger(DesktopDriver.class);
	public static Actions action = new Actions(DesktopDriver.dDriver);

	public static By DesktopElementToBy(WebElement webEle) {
		try {
		String flag = webEle.toString();
		String[] data = null;
		if (flag.contains("xpath")) {
			data = webEle.toString().split(" -> ")[1].split(": ");
		} else {
			data = webEle.toString().split(" -> ")[1].replace("]", "").split(": ");
		}
		String locator = data[0];
		String term = data[1];

		switch (locator) {
		case "xpath":
			if (term.contains("]]")) {
				term = term.replaceAll("]]", "]");
			}
			return By.xpath(term);
		case "css selector":
			return By.cssSelector(term);
		case "id":
			return By.id(term);
		case "tag name":
			return By.tagName(term);
		case "name":
			return By.name(term);
		case "link text":
			return By.linkText(term);
		case "class name":
			return By.className(term);
		}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error  while converting by type to webelement" + e);
			Assert.fail("Error  while converting by type to webelement" + e);
			}
		return (By) webEle;
	}

	public static void click(WebElement locator) {
		try {
			// Need to add the assertions when we decide the reporting
			DesktopWaitHelper.waitForElement(locator);
			locator.click();
			log.info("Click action completed successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error while performing click operation , Exception :"+e);
			Assert.fail("Error while performing click operation , Exception :"+e);
		}
	}

	public static void enterText(WebElement locator, String value) {
		try {
			DesktopWaitHelper.waitForElement(locator);
			locator.sendKeys(new String[] { value });
			log.info("Text entered successfully");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while trying to enter text , Exception :"+e);
			Assert.fail("Error occurred while trying to enter text , Exception :"+e);
		}
	}

	public static boolean compareText(String strActualText, String strCompText) {
		boolean compFlag = false;
		try {
			if (strActualText.equals(strCompText)) {
				compFlag = true;
				log.info(strActualText + " match with :" + strCompText);
			} else {
				compFlag = false;
				log.info(strActualText + "doesnot match with" + strCompText);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while comparing text , Exception :"+e);
			Assert.fail("Error occurred while comparing text , Exception :"+e);
		}
		return compFlag;
	}

	public static boolean existText(WebElement locator) {
		boolean flag = true;
		try {
			DesktopWaitHelper.waitForElement(locator);
			if (locator.getAttribute("value").isEmpty())
				flag = false;
			else
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while checking whether the text exists, Exception :"+e);
			Assert.fail("Error occurred while checking whether the text exists, Exception :"+e);
		}
		return flag;
	}

	public static void clearText(WebElement locator) {
		try {
			DesktopWaitHelper.waitForElement(locator);
			locator.clear();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while trying to clear text, Exception :"+e);
			Assert.fail("Error occurred while trying to clear text, Exception :"+e);

		}
	}

	public static boolean radioBtnIsSelected(WebElement locator) {
		DesktopWaitHelper.waitForElement(locator);

		boolean eFlag = false;
		try {
			if (locator.isSelected()) {
				eFlag = true;
			} else {
				eFlag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while checking whether radio button is selected "+"Exception :"+e);
			Assert.fail("Error occurred while checking whether radio button is selected "+"Exception :"+e);
		}
		return eFlag;

	}

	public static boolean radioBtnIsNotSelected(WebElement locator) {
		DesktopWaitHelper.waitForElement(locator);
		boolean eFlag = false;
		try {
			if (!locator.isSelected()) {
				eFlag = false;
			} else {
				eFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while checking whether radio button is not selected "+"Exception :"+e);
			Assert.fail("Error occurred while checking whether radio button is not selected "+"Exception :"+e);
		}
		return eFlag;

	}

	public static void doubleClick(WebElement locator) {
		try {
			DesktopWaitHelper.waitForElement(locator);
			action.doubleClick(locator).perform();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error while performing double click action "+"Exception :"+e);
			Assert.fail("Error while performing double click action "+"Exception :"+e);
		}

	}

	public static void selectValListBox(WebElement locator, String ListValue) {
		try {
			DesktopWaitHelper.waitForElement(locator);
			locator.findElement(By.name(ListValue)).click();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error while performing select value from listbox action "+"Exception :"+e);
			Assert.fail("Error while performing select value from listbox action "+"Exception :"+e);
		}
	}

	public static void multiSelectbyIndex(WebElement locator, String value) {
		try {
			DesktopWaitHelper.waitForElement(locator);
			doubleClick(locator.findElement(By.name(value)));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error while performing multi select by index action "+"Exception :"+e);
			Assert.fail("Error while performing multi select by index action "+"Exception :"+e);
		}
	}

	public static void scrollSelectValListBox(List<WebElement> locator, String value) {
		try {
			int i;
			boolean flag = false;
			do {
				int size = locator.size();
				for(i=0;i<size;i++){
					if(locator.get(i).isDisplayed())
					{
						if(locator.get(i).getAttribute("Name").equalsIgnoreCase(value))
						{
							flag=true;
						}
					}
				}
				if(!flag)
				{
					DesktopDriver.dDriver.findElement(By.id("Vertical ScrollBar")).click();
				}
			} while (flag);
			locator.get(i).findElement(By.name(value)).click();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error while performing scroll to Selected Value in ListBox action "+"Exception :"+e);
			Assert.fail("Error while performing scroll to Selected Value in ListBox action "+"Exception :"+e);
		}
	}
	
	public static void verifyText(WebElement locator, String Value)
	{
		try
		{
		String actVal = locator.getAttribute("Name");
		compareText(actVal, Value);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error("Error while performing scroll to Selected Value in ListBox action "+"Exception :"+e);
			Assert.fail("Error while performing scroll to Selected Value in ListBox action "+"Exception :"+e);
		}
	}
}
