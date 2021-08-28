package com.objectrepository;

import org.openqa.selenium.By;

//import org.openqa.selenium.By;

import com.utilities.Staticvariables;

public class Locators extends Staticvariables{
	
	//stqatools
	public final By stqa_frame = By.xpath("//iframe[@src=\"Alerts.php\"]");
	public final By stqa_btn1 = By.id("btnPrompt");
	public final By stqa_input = By.id("prompt");
	public final By stqa_btn2 = By.xpath("//*[@id=\"ezAlerts-footer\"]/button");
	public final By stqa_text = By.id("ezAlerts-message");
	
	//stqa_popup's
	public final By stqa_popup_button= By.xpath("/html/body/div[1]/div/div[2]/a[2]/button");
	
	//switch_tabs stqa
	public final By stqa_tab_button = By.xpath("/html/body/div[1]/div/div[2]/a[1]/button");
	
	//random_class_womenstore
	public final By women_img = By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img");
	//public final By women_cart= By.xpath("(//*[@title='Add to cart'])["+i+"]");
	public final By women_continue = By.xpath("//i[@class=\"icon-chevron-left left\"]");

}
