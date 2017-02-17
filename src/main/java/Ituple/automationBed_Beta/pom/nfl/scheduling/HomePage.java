package Ituple.automationBed_Beta.pom.nfl.scheduling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ituple.automationBed_Beta.utility.base.Utils;

public class HomePage {

	@FindBy(xpath="//ul[@class='rtsUL']")
	private WebElement NflNav;
	
	public NflMemberTab selectMemebrTab(){
		NflNav.findElement(By.xpath("//li/a//span[text()='Members']")).click();
		return PageFactory.initElements(Utils.uBase.driver, NflMemberTab.class);
	}
	
	
	public HomePage selectHomeTab(){
		NflNav.findElement(By.xpath("//li/a//span[text()='Home']")).click();
		return this;
	}
	
}
