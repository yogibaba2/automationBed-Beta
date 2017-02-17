package Ituple.automationBed_Beta.pom.nfl.scheduling;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;

public class NflMemberTab {

	// filter component
	@FindBy(id = "cmDisplay_searchClassMember_txtSearchMember_DWHID")
	WebElement filterGRGID;
	@FindBy(id = "cmDisplay_searchClassMember_txtSearchMember_LastName")
	WebElement filterLastName;
	@FindBy(id = "cmDisplay_searchClassMember_txtSearchMember_FirstName")
	WebElement filterFirstName;
	@FindBy(id = "cmDisplay_searchClassMember_cmbState_Arrow")
	WebElement filterState;
	@FindBy(id = "cmDisplay_searchClassMember_cmbState_DropDown")
	WebElement filterStateDropDown;
	@FindBy(id = "cmDisplay_searchClassMember_btnSearchMember_input")
	WebElement filterSearchBtn;
	@FindBy(id = "cmDisplay_searchClassMember_btnClear_input")
	WebElement filterClearBtn;

	//MemberList component
	@FindBy(xpath="//table[@id='cmDisplay_searchClassMember_grdMemberGrid_ctl00']")
	WebElement searchClassMemberGrid;
	
	
	// member detail component
	//@FindBy(id = "cmDisplay_cmDisplay_ClassMemberInfo_cmEligibility_btnScheduleInitialVisit")
	WebElement scheduleAppointmentLink;

	
	
	// schedulingWizardPopup
	String schedulingWizardPopup = "schedulingWizardPopup";

	@FindBy(xpath=".//tr[contains(@class,'SelectedAddress')]/td[1]/table")
	WebElement claimantAdressTbl;
	
	@FindBy(id = "btnProviderSearch_Next_input")
	WebElement btnProviderSearch_Next;

	@FindBy(xpath = ".//a[@class='rwPopupButton'][1]")
	WebElement outOfStateWarnignYes;

	@FindBy(xpath = ".//a[@class='rwPopupButton'][2]")
	WebElement outOfStateWarnignNo;

	@FindBy(id = "Scheduling1_DatePicker_dateInput")
	WebElement schedullingTimeCardDate;

	@FindBy(id = "Scheduling1_TimePicker_dateInput")
	WebElement schedullingTimeCardTime;

	@FindBy(id = "Scheduling1_RadComboBoxPhysicians_Input")
	WebElement schedullingTimeCardPractitioner;

	@FindBy(xpath = "//*[@id='Scheduling1_RadComboBoxPhysicians_DropDown']/div/ul/li[1]")
	WebElement schedullingTimeCardPractitionerSelection;
	
	@FindBy(id = "lblConfirmation_MemberAddress")
	WebElement confirmedMemberAdd;
	
	@FindBy(id = "lblConfirmation_ProviderAddress1")
	WebElement confirmedProviderAdd1;
	
	@FindBy(id = "lblConfirmation_ProviderAddress2")
	WebElement confirmedProviderAdd2;
	
	@FindBy(id = "btnMemberScheduling_Next_input")
	WebElement btnMemberScheduling_Next;
	
	@FindBy(id = "btnConfirmation_Schedule_input")
	WebElement btnConfirmation_Schedule;
	
	
	//Appointment tab
	
	
	@FindBy(xpath = "//*[@id='cmDisplay_cmDisplay_ctl01']/ul/li/a")
	WebElement memberTab_AppointmentTab;
	
	@FindBy(id = "cmDisplay_cmDisplay_ctl01_i0_ActiveAppointments_PastAppointmentsAddress1")
	WebElement scheduledProviderAddress1;
	
	@FindBy(id = "cmDisplay_cmDisplay_ctl01_i0_ActiveAppointments_PastAppointmentsAddress2")
	WebElement scheduledProviderAddress2;
	
	@FindBy(id = "cmDisplay_cmDisplay_ctl01_i0_ActiveAppointments_PastAppointmentScheduledAddress1")
	WebElement scheduledClaimantAddress1;
	
	@FindBy(id = "cmDisplay_cmDisplay_ctl01_i0_ActiveAppointments_PastAppointmentScheduledAddress2")
	WebElement scheduledClaimantAddress2;
	
	@FindBy(id = "cmDisplay_cmDisplay_ctl01_i0_ActiveAppointments_PastAppointmentsDate")
	WebElement scheduledDate;
	
	@FindBy(id = "cmDisplay_cmDisplay_ctl01_i0_ActiveAppointments_PastAppointmentsTime")
	WebElement scheduledTime;
	
	
	
	
	public String GRGID=null;
	public String programe= null;
	public String claimantAddress = null;
	public String providerAddress=null;
	public String date=null;
	public String time=null;
	
	public String confirmedClaimantAdd=null;
	public String confirmedProviderAdd=null;
	/**
	 * search the member by filter type
	 * 
	 * <h3>Param</h3>
	 * <h4>String searchBy :</h4> GRGID/FirstName/LastName
	 * 
	 * <h4>String str :</h4> String to be searched
	 * 
	 * @throws Exception
	 */
	public NflMemberTab searchBy(String searchBy, String str) throws Exception {
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.clickWebelement(filterClearBtn);
		switch (searchBy.toUpperCase()) {
		case "GRGID":
			Utils.uBase.setText(filterGRGID, str);
			break;
		case "FIRSTNAME":
			Utils.uBase.setText(filterFirstName, str);
			break;
		case "LASTNAME":
			Utils.uBase.setText(filterLastName, str);
			break;
		case "STATE":
			Utils.uBase.clickWebelement(filterState);
			Utils.uBase.clickWebelement(filterStateDropDown.findElement(By.xpath("./div/ul/li[text()[contains(.,'GA')]]")));
			break;
		default:
			Utils.addLog.error("invalid searchBy value");
		}

		Utils.uBase.clickWebelement(filterSearchBtn);
		return this;
	}

	
	public NflMemberTab selectRandomClassMember() throws Exception{
		Utils.uBase.waitForPageLoadByLoader("MainLoadingPanelAsp");
		WebElement searchMemberPager=searchClassMemberGrid.findElement(By.xpath("./tfoot//span[text()='"+Utils.uBase.getRandomNumberBetween(4, 9)+"']/.."));
		
		Utils.uBase.highlightelement(searchMemberPager);
		Utils.uBase.clickWebelement(searchMemberPager);
		Utils.uBase.waitForPageLoadByLoader("MainLoadingPanelAsp");
		
		List<WebElement> memberList=searchClassMemberGrid.findElements(By.xpath("./tbody/tr"));
		if(memberList==null){
			throw new Exception("no member found");
		}
		int index=Utils.uBase.getRandomNumberBetween(0, (memberList.size()-1));
		WebElement member = searchClassMemberGrid.findElement(By.xpath("./tbody/tr["+index+"]"));
		Utils.uBase.highlightelement(member);
		GRGID= member.findElement(By.xpath("./td[2]")).getText();
		programe=member.findElement(By.xpath("./td[1]/a")).getText();
		System.out.println(GRGID+"  :  "+programe);
		Utils.uBase.highlightelement(member.findElement(By.xpath("./td[3]/a")));
		Utils.uBase.clickWebelement(member.findElement(By.xpath("./td[3]/a")));
		return this;
	}
	
	
	/**
	 * this method will open the Schedule Visit window
	 */
	public NflMemberTab openScheduleAppointment() throws Exception {
		Utils.uBase.waitForPageLoadByLoader("MainLoadingPanelAsp");
		
		if(programe.equalsIgnoreCase("NFL")){
			scheduleAppointmentLink=Utils.uBase.driver.findElement(By.id("cmDisplay_cmDisplay_ClassMemberInfo_cmEligibilityNFL_btnNeurology"));
		}else if(programe.equalsIgnoreCase("PMCP")){
			scheduleAppointmentLink=Utils.uBase.driver.findElement(By.id("cmDisplay_cmDisplay_ClassMemberInfo_cmEligibility_btnScheduleInitialVisit"));
		}
		
		if((scheduleAppointmentLink.getAttribute("class")).contains("aspNetDisabled")){
			
			selectRandomClassMember().openScheduleAppointment();
			return this;
		}
		else{
			AdvanceReporting.addLogs("info", "Memeber select", "memberSelect");
			Utils.uBase.clickWebelement(scheduleAppointmentLink);
			Utils.uBase.waitForPageLoadByLoader("MainLoadingPanelAsp");
			Utils.uBase.switchToFrame_byName(schedulingWizardPopup);
			Utils.uBase.untilAngularFinishHttpCalls();
			return this;
		}
		
		
	}

	/**
	 * this method let you select the Health Organization for visit
	 * 
	 * @throws Exception
	 */
	public NflMemberTab selectHlthOrg() throws Exception {
		// Utils.uBase.untilAngularFinishHttpCalls();
		int provider_index=Utils.uBase.getRandomNumberBetween(0, 2);
		WebElement provider_ele = Utils.uBase.driver.findElement(By.xpath("//a[@id='Provider_"+provider_index+"']"));
		AdvanceReporting.addLogs("info", "Scheduling Wizard Popup top", "schedulingWizardPopupTop");
		Utils.uBase.scrolltoElement(provider_ele);
		AdvanceReporting.addLogs("info", "Scheduling Wizard Popup bottom", "schedulingWizardPopupBottom");
		Utils.uBase.switchToDefaultContent();
		String script = "var iframe = document.getElementsByName('schedulingWizardPopup');"
				+ "var ineerDoc = iframe[0].contentDocument || iframe[0].contentWindow.document;"
				+ "function resolver(prefix){return prefix === 'xhtml' ? 'http://www.w3.org/1999/xhtml' : null;}"
				+ "ineerDoc.evaluate(\"//xhtml:a[@id='Provider_"+provider_index+"']\","
				+ " ineerDoc, resolver, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		Utils.uBase.executeJScript(script);
		Utils.uBase.scrollDown(3);
		Utils.uBase.switchToFrame_byName(schedulingWizardPopup);
		AdvanceReporting.addLogs("info", "Scheduling Wizard Popup bottom", "schedulingWizardPopupBottom");
		claimantAddress = getMemberAddress();
		providerAddress= getProviderAddress(provider_ele);
		double providerDist = Double
				.parseDouble(((provider_ele.findElement(By.xpath("../span")).getText()).split("\\("))[1].split("\\s")[0]);
		if (providerDist >= 50) {
			Utils.uBase.waitForElement(outOfStateWarnignYes);
			Thread.sleep(5000);
			Utils.uBase.clickWebelement(outOfStateWarnignYes);
		}
		Utils.uBase.clickWebelement(btnProviderSearch_Next);
		return this;
	}
	
	/**
	 * This method will return the address of claimant or member
	 * */
	private String getMemberAddress(){
		try {
			String address = "";
			address+=claimantAdressTbl.findElement(By.xpath("//tr[2]/td[2]")).getText().trim()+" ";
			address+=claimantAdressTbl.findElement(By.xpath("//tr[3]/td[2]")).getText().trim()+" ";
			address+=claimantAdressTbl.findElement(By.xpath("//tr[4]/td[2]")).getText().trim();
			address=address.replaceAll(" +", " ");
			return address;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * This method will return the address of provider or doc
	 * */
	private String getProviderAddress(WebElement provider){
		try {
			String address = "";
			address+=provider.findElement(By.xpath("../following-sibling::div[position()=1]/div[1]/span")).getText().trim()+" ";
			address+=provider.findElement(By.xpath("../following-sibling::div[position()=1]/div[2]/span")).getText().trim();
			address=address.replaceAll(" +", " ");
			
			return address;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method will schedule visit on a given time
	 * 
	 * @throws Exception
	 * 
	 */
	public NflMemberTab scheduleVisit(String args) throws Exception {
		String[] timeCard = args.split("\\|");
		
		Utils.uBase.waitForElement(schedullingTimeCardDate);
		Utils.uBase.setText(schedullingTimeCardDate, timeCard[0]);
		Utils.uBase.pressTab(schedullingTimeCardDate);
		date=timeCard[0];
		
		Utils.uBase.waitForElement(schedullingTimeCardTime);
		Utils.uBase.setText(schedullingTimeCardTime, timeCard[1]);
		Utils.uBase.pressTab(schedullingTimeCardTime);
		time= timeCard[1];
		
		Utils.uBase.waitForElement(schedullingTimeCardPractitioner);
		Utils.uBase.clickWebelement(schedullingTimeCardPractitioner);
		Utils.uBase.clickWebelement(schedullingTimeCardPractitionerSelection);
		
		AdvanceReporting.addLogs("info", "Scheduling Wizard Date Time", "schedulingWizardDateTime");
		Utils.uBase.waitForElement(btnMemberScheduling_Next);
		Utils.uBase.clickWebelement(btnMemberScheduling_Next);
		
		Utils.uBase.waitForElement(btnConfirmation_Schedule);
		Utils.uBase.clickWebelement(btnConfirmation_Schedule);
		Utils.uBase.waitForPageLoadByLoader("MainLoadingPanelAsp");
		
		return this;
	}
	
	public NflMemberTab verifyAppointmentDetails() throws Exception{
		
		Utils.uBase.switchToDefaultContent();
		Utils.uBase.waitForElement(memberTab_AppointmentTab);
		Utils.uBase.clickWebelement(memberTab_AppointmentTab);
		Utils.uBase.scrollDown(2);
		
		AdvanceReporting.addLogs("info", "Scheduling Wizard Popup bottom", "schedulingWizardPopupBottom");
		
		confirmedClaimantAdd=(scheduledClaimantAddress1.getText()+" "+scheduledClaimantAddress2.getText()).trim().replaceAll(" +", " ");
		confirmedProviderAdd =(scheduledProviderAddress1.getText()+" "+scheduledProviderAddress2.getText()).trim().replaceAll(" +", " ");
		
		System.out.println(confirmedClaimantAdd+" : "+claimantAddress);
		System.out.println(confirmedProviderAdd+" : "+providerAddress);
		
		if (claimantAddress.equalsIgnoreCase(confirmedClaimantAdd)){
			AdvanceReporting.addLogs("pass", "Claimant address updated correctly");
			AdvanceReporting.addLogs("info", claimantAddress+" : "+confirmedClaimantAdd);	
		}else{
			AdvanceReporting.addLogs("fail","Claimant address failed to updated correctly");
			AdvanceReporting.addLogs("error", claimantAddress+" is expected to : "+confirmedClaimantAdd);
		}
		if (providerAddress.equalsIgnoreCase(confirmedProviderAdd)) {
			AdvanceReporting.addLogs("pass", "Provider address updated correctly");
			AdvanceReporting.addLogs("info", providerAddress+" : "+confirmedProviderAdd);
		} else {
			AdvanceReporting.addLogs("fail","Provider address failed to updated correctly");
			AdvanceReporting.addLogs("error", providerAddress+" is expected to : "+confirmedProviderAdd);
		}
		System.out.println(scheduledDate.getText().trim()+" : "+scheduledTime.getText().trim());
		if (date.equalsIgnoreCase(scheduledDate.getText().trim())) {
			AdvanceReporting.addLogs("pass", "Scheduled date is correct");
			AdvanceReporting.addLogs("info", date+" : "+scheduledDate.getText().trim());
		} else {
			AdvanceReporting.addLogs("fail","Scheduled date is not correct");
			AdvanceReporting.addLogs("error", date+" is expected to : "+scheduledDate.getText().trim());
		}
		if (time.equalsIgnoreCase(scheduledTime.getText().trim())) {
			AdvanceReporting.addLogs("pass", "Scheduled time is correct");
			AdvanceReporting.addLogs("info", time+" : "+scheduledTime.getText().trim());
		} else {
			AdvanceReporting.addLogs("fail","Scheduled time is not correct");
			AdvanceReporting.addLogs("error", time+" is expected to : "+scheduledTime.getText().trim());
		}
		return this;
	}

}
