package Ituple.automationBed_Beta.pom.wap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Constant;
import Ituple.automationBed_Beta.utility.base.Utils;

public class ProgramPage {

	private WebElement programName;
	private WebElement project;
	
	private String programName1;
	private String projectName1;
	/**
	 * choose programe
	 * */
	public ProgramPage chooseProgram(String program) throws Exception{ 
		Utils.uBase.untilAngularFinishHttpCalls();
		
		this.programName1=program;
		
		//defining dynamic xpath for program
		AdvanceReporting.addLogs("pass", "selecting Programe : "+program, Constant.TestCase+"//"+program);
		try {
			programName=Utils.uBase.driver.findElement(By.xpath("//h2[text()[contains(.,'"+program+"')]]"));
			Utils.uBase.clickWebelement(this.programName);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			programName=Utils.uBase.driver.findElement(By.xpath("//h2[text()[contains(.,'"+program.toUpperCase()+"')]]"));
			Utils.uBase.clickWebelement(this.programName);
		}
		return this;
	}
	
	/**
	 * choose lawfirm
	 * */
	public ProgramPage selectProject(String project) throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();

		this.programName1=project;
		//defining dynamic xpath for clientSelect
		AdvanceReporting.addLogs("pass", "selecting Law firm : "+project, project);
		try {
			this.project=Utils.uBase.driver.findElement(By.xpath("//div[contains(@uib-tooltip,'"+project+"')]"));
			Utils.uBase.clickWebelement(this.project);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			this.project=Utils.uBase.driver.findElement(By.xpath("//div[contains(@uib-tooltip,'"+project.toUpperCase()+"')]"));
			Utils.uBase.clickWebelement(this.project);
		}
		return this;
	}
}
