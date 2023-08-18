package com.scripted.desktop;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

import junit.framework.Assert;

public class DesktopDriver {

	private static final Logger log = Logger.getLogger(DesktopDriver.class);
	public static WebDriver dDriver = null;

	public static WebDriver funGetDriver(String desktopAppPath) {
		try {
			String DriverPath = DesktopDriverPathUtil.getWiniumDriverPath();
			
			//To kill open drivers
			killOpenDrivers();
			
			if (desktopAppPath == null || desktopAppPath.equals(" ")) {
				log.info("Application path is null, please check the value of AppPath in WiniumOption.properties");
				System.exit(0);
			}
			DesktopOptions options = new DesktopOptions();
			options.setApplicationPath(desktopAppPath);
			WiniumDriverService service = new WiniumDriverService.Builder().usingDriverExecutable(new File(DriverPath))
					.usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
			service.start();
			dDriver = new WiniumDriver(service, options);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while trying to initalize Winiumdriver "+"Exception :"+e);
			Assert.fail("Error occurred while trying to initalize Winiumdriver "+"Exception :"+e);
			}
		return dDriver;
	}

	public static void killOpenDrivers() {
		Process process;
		try {
			process = Runtime.getRuntime().exec("taskkill /F /IM Winium.Desktop.Driver.exe");
			process.waitFor();
			process.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while executing  process,killOpenDrivers "+"Exception :"+e);
			Assert.fail("Error occurred while executing  process,killOpenDrivers "+"Exception :"+e);
		}

	}
}
