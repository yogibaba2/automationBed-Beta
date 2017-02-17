package Ituple.automationBed_Beta.pom.mrr;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Constant;
import Ituple.automationBed_Beta.utility.base.Utils;


public class ProgramPage {

	/*element for dynamic binding*/
	WebElement programName;
	
	
	
	/**
	 * choose programe
	 * */
	public ProgramPage chooseProgram(String program) throws Exception{ 
		Utils.uBase.untilAngularFinishHttpCalls();

		//defining dynamic xpath for program
		programName=Utils.uBase.driver.findElement(By.xpath("//h2[@uib-tooltip='"+program+"']"));
		
		AdvanceReporting.addLogs("info", "selecting Programe : "+program, program);
		Utils.uBase.clickWebelement(programName);
		AdvanceReporting.addLogs("pass", program+" program selected ");
		return this;
	}

	
	
	public int getProgramsCompletedCaseCount(String program) throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		WebElement element=Utils.uBase.driver.findElement(By.xpath("//h2[@uib-tooltip='"+program+"']/../span/p"));
		
		
		int completedCaseCount= Integer.parseInt((element.getText().split(" "))[0]);
		AdvanceReporting.addLogs("info", "Completed case count for "+program+" is :"+completedCaseCount);
		
		return completedCaseCount;
	}
	
	
	
}
