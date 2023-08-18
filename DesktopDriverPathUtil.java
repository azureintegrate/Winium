package com.scripted.desktop;

import org.apache.log4j.Logger;

import com.scripted.generic.FileUtils;


public class DesktopDriverPathUtil {
	private static final Logger log = Logger.getLogger(DesktopDriverPathUtil.class);

	public static String getWiniumDriverPath() {
		log.info("Inside DesktopDriverUtil.getWiniumDriverPath method");
		return FileUtils.getFilePath("src/main/resources/Web/Drivers/Winium.Desktop.Driver.exe");
	}
}
