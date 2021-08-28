package com.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonFunctions extends Staticvariables {
	public CommonFunctions() {
		ProjectDir = System.getProperty("user.dir");
		File file = new File(ProjectDir +"\\Screen_shots");
		boolean a= false;
		if (!file.exists()) {
			a=file.exists();
		} 
		if(a){
			System.out.println("folder created.");
		}else {
			System.out.println("foledr alredy exists.");
			
			Screenshot_path=ProjectDir +"\\Screen_shots";
			System.out.println("current project loaction");
		
        }
	}
	
	//========================== BROWSER LAUNCH =============================
	
	public void chromeBrowserLaunch() {
		
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		driver.navigate().refresh();
		driver.manage().window().maximize();
		System.out.println("windows maximized");
	}

	public void firefoxBrowserLaunch() {
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.navigate().refresh();
		driver.manage().window().maximize();
		System.out.println("windows maximized");
	}

	public void edgeBrowserLaunch() {
		
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.navigate().refresh();
	}

	public void ieBrowserLaunch() {
		
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
		driver.navigate().refresh();
	}

	public void operaBrowserLaunch() {
		
		WebDriverManager.operadriver().setup();
		driver = new OperaDriver();
		driver.navigate().refresh();
		driver.manage().window().maximize();
		System.out.println("windows maximized");
	}
	
	//==========================OPEN_URL====================================
	
	public void Open_Url(String URL) throws Exception {
		
		driver.navigate().to(URL);
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	
	//==========================WAIT'S======================================
	
	public void td_wait(int k) throws Exception {
		Thread.sleep(k);
	}
	
	public void imp_wait(int k) {
		driver.manage().timeouts().implicitlyWait(k, TimeUnit.SECONDS);
	}
	
	public void exp_wait(int k ,By locator) {
		WebDriverWait wait = new WebDriverWait(driver,k);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	//==========================LOCATOR'S ACTION===========================
	
	//sendkeys
	public void sendkeys(By locator, String inputData) throws Exception {
		
		WebElement element = driver.findElement(locator);
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				element.clear();
				element.sendKeys(inputData);
			} else {
				System.out.println("Element is not enabled/dispalyed, please check the Locator");
			}
		}
	}
	
	//normal_click
	public void normal_click(By locator) throws Exception {
		
		WebElement element = driver.findElement(locator);
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				element.click();
			} else {
				System.out.println("Element is not enabled state, please check the locator");
			}
		}
	}
	
	//js_click
	public void js_click(By locator) throws Exception {
		
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	//highlightElement
	public void highlightElement(WebElement element) throws InterruptedException {
		
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
	}
	
	//==========================SCREEN_SHOT================================
	
	public void screen_shot(String FileName) throws Exception {
		
		File k = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(k, new File("./src/screen_shots/" + FileName + TimeStamp() + ".PNG"));
	}
	
	//==========================TIME_STAMP=================================
	
	public String TimeStamp() {
		
		Date l = new Date();
		DateFormat df = new SimpleDateFormat("HH_mm_ss");
		String TS = df.format(l);
		return TS;
	}
	
	//==========================ALERT_HANDLING============================
	
	public void alert_Accept() {
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void alert_Dismiss() {
		
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	public void alert_sendkeys(String k) {
		
		driver.switchTo().alert().sendKeys(k);
	}
	
	//==========================FRAMES_HANDLING==========================
	
	public int iframeCount() {
		
		driver.switchTo().defaultContent();
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		int numberOfFrames = 0;
		numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("Number of iframes on the page are: " + numberOfFrames);
		return numberOfFrames;
	}
	
	public void switchToFrameByInt(int i) {
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(i);
	}
	
	public void switchToFrameByString(String i) {

		driver.switchTo().defaultContent();
		driver.switchTo().frame(i);
	}

	public int loopAllFramesForElement(By locator) {

		int elementpresenceCount = 0;
		int loop = 0;
		int maxFramaecount = iframeCount();
		elementpresenceCount = driver.findElements(locator).size();
		while (elementpresenceCount == 0 && loop < maxFramaecount) {
			try {
				switchToFrameByInt(loop);
				elementpresenceCount = driver.findElements(locator).size();
				System.out.println("Try LoopAllframesAndReturnWebEL : " + loop + "; ElementpresenceCount: "
						+ elementpresenceCount);
				if (elementpresenceCount > 0 || loop > maxFramaecount) {
					break;
				}
			} catch (Exception ex) {
				System.out.println("Catch LoopAllframesAndReturnWebEL Old: " + loop + "; " + ex);
			}
			loop++;
		}
		return elementpresenceCount;
	}
	
	//==========================POUP'S_HANDLING===============================
	
	public void Close_ChildWindow() throws InterruptedException {
		// get the main windown name
		String mainWindowName = driver.getWindowHandle();
		System.out.println("mainWindowName:" + mainWindowName);

		Set<String> allWindowNames = driver.getWindowHandles();
		System.out.println("allWindowNames:" + allWindowNames);

		// Close the child window (popups)
		for (String abc : allWindowNames) {
			// validate the window name is parent window /Child window?
			if (!mainWindowName.equals(abc)) {
				// switch to child window
				driver.switchTo().window(abc);
				Thread.sleep(3000);
				// Close my child window
				driver.close();
			}
		}
		driver.switchTo().window(mainWindowName);
	}
	
	public void navigate_TO_childWindow() throws InterruptedException {
		// get the main windown name
		String mainWindowName = driver.getWindowHandle();
		System.out.println("mainWindowName:" + mainWindowName);

		Set<String> allWindowNames = driver.getWindowHandles();// 4
		System.out.println("allWindowNames:" + allWindowNames);

		for (String string : allWindowNames) {
			// validate the window name is parent window /Child window?
			if (!mainWindowName.equals(string)) {
				// switch to child window
				driver.switchTo().window(string);
				Thread.sleep(3000);
			}
		}
	}
	
	//==========================SWITCHING_TABS==============================
	
	public void switchToNewTab() {
		// Get the current window handle
		String windowHandle = driver.getWindowHandle();// abc

		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));

		// Switch back to original window
		driver.switchTo().window(windowHandle);
	}
	
	public void switch_CloseNewTab() {
		// Get the current window handle
		String windowHandle = driver.getWindowHandle();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		// Close the newly Opened Window
		driver.close();
		// Switch back to original window
		driver.switchTo().window(windowHandle);
	}
	
	//================= ACTION'S (cursor & keyboard event's) ================
	
	public void moveTo_Element(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).build().perform();
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
	}

	public void mouseHover_ClickAndHold(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.clickAndHold(element).build().perform();
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
	}
	
	public void mouseHover_ContextClick(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.contextClick(element).build().perform();

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
	}
	
	public void doubleClick(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.doubleClick(element).build().perform();

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
	}
	
	//  Drag and drop
	
	public void dragNdrop(By sourceelementLocator, By destinationelementLocator) {
		try {
			WebElement sourceElement = driver.findElement(sourceelementLocator);
			WebElement destinationElement = driver.findElement(destinationelementLocator);

			if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
				Actions action = new Actions(driver);
				action.dragAndDrop(sourceElement, destinationElement).build().perform();
			} else {
				System.out.println("Element(s) was not displayed to drag / drop");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + sourceelementLocator + "or" + destinationelementLocator
					+ "is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sourceelementLocator + "or" + destinationelementLocator
					+ " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error occurred while performing drag and drop operation " + e.getStackTrace());
		}
	}
	
	
	
	 
	
	
	
	
	
	
	
	
	


}
