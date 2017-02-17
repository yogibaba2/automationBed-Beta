package Ituple.automationBed_Beta.pom.wap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Ituple.automationBed_Beta.utility.base.Utils;

public class MyTaskPage {

	private WebElement taskPanel;

	//My Task Panel elements
	@FindBy(xpath = "//div[contains(@class,'mytask-module')]")
	private WebElement myTaskPanel;

	
	
	/**
	 * intialize task webelement with provided categories</br>
	 * <h3>param : </h3>new/today/due
	 * 
	 * @return MyTaskPage.class
	 */
	public MyTaskPage getTaskPanel(String panelName) {

		taskPanel = taskPanel.findElement(By.xpath("//div[contains(@class,'" + panelName + "-task-card')]"));
		return this;

	}

	public Integer getTaskPanelHeadCount(){
		return Integer.parseInt(taskPanel.findElement(By.xpath("//div[@class='panel-heading']//span")).getText());
	}
	
	public Integer getTaskPanelBodyCount(){
		return (taskPanel.findElements(By.xpath("//div[@class='panel-body']//ul/li"))).size();
	}
	
	public MyTaskPage openTask(String caseName) throws Exception{
		WebElement task =taskPanel.findElement(By.xpath("//div[@class='panel-body']//ul/li/a[contains(@text(),'"+caseName+"')]"));
		Utils.uBase.clickWebelement(task);
		return this;
	}
	
	public MyTaskPage searchTask(String task){
		
		return this;
	}
}
