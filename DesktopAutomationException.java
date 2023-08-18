package com.scripted.desktop;

import org.openqa.selenium.WebElement;

public class DesktopAutomationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DesktopAutomationException(WebElement webEle, String reason) {
		super(String.format("Failed on locator %s due to error : %s", webEle, reason));
	}

	public DesktopAutomationException(WebElement webEle, String expected, String actual) {
		super(String.format("Failed on locator %s with expected :  %s  but the actual : %s", webEle, expected, actual));
	}

	public DesktopAutomationException(RuntimeException t) {
		super(t);
	}

	public DesktopAutomationException(String message) {
		super(message);
	}

	public DesktopAutomationException(String message, RuntimeException e) {
		super(message, e);
	}
}
