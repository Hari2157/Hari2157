package com.testscenarios;

import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.CommonFunctions;
import com.utilities.Staticvariables;

import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;

import java.util.Properties;

import org.testng.annotations.AfterTest;

public class Switchtab_stqa extends Staticvariables {

	CommonFunctions cfn = new CommonFunctions();
	Locators l = new Locators();

	@BeforeTest
	public void beforeTest() throws Exception {

		Properties p = new Properties();
		FileInputStream fi = new FileInputStream("./src/test/resources/testdata/Td.properties");
		p.load(fi);

		cfn.chromeBrowserLaunch();
		cfn.Open_Url(p.getProperty("stqa_popup_url"));
	}

	@Test
	public void f() throws Exception {
		cfn.normal_click(l.stqa_tab_button);
		cfn.td_wait(5000);
		// cfn.switchToNewTab();
		cfn.td_wait(10000);
		cfn.switch_CloseNewTab();
		cfn.td_wait(2000);
		cfn.normal_click(l.stqa_popup_button);
		cfn.Close_ChildWindow();
	}

	@AfterTest
	public void afterTest() throws Exception {
		cfn.td_wait(10000);
		driver.quit();
	}

}
