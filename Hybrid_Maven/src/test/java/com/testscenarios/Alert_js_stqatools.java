package com.testscenarios;

import com.objectrepository.Locators;
import com.utilities.CommonFunctions;
import com.utilities.Staticvariables;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class Alert_js_stqatools extends Staticvariables {
	CommonFunctions cfn = new CommonFunctions();
	Locators l = new Locators();
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeTest
	public void beforeTest() throws Exception {
		Properties p = new Properties();
		FileInputStream fi = new FileInputStream("./src/test/resources/testdata/Td.properties");
		p.load(fi);

		cfn.chromeBrowserLaunch();
		cfn.Open_Url(p.getProperty("stqa_url"));
	}

	@Test
	public void f() throws Exception {

		WebElement e = driver.findElement(l.stqa_frame);
		driver.switchTo().frame(e);
		cfn.normal_click(l.stqa_btn1);
		WebElement n = driver.findElement(l.stqa_input);
		cfn.td_wait(2000);
		n.sendKeys("hari");
		// WebElement el = driver.findElement(l.stqa_text);
		cfn.js_click(l.stqa_btn2);
		System.out.println(driver.findElement(l.stqa_text).getText());
		cfn.td_wait(5000);

	}

	@AfterTest
	public void afterTest() throws Exception {
		cfn.td_wait(10000);
		driver.quit();
	}

}
