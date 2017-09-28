package Ituple.automationBed_Beta.pom.lccSe;

import java.awt.datatransfer.StringSelection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ituple.automationBed_Beta.utility.base.Utils;

public class CaseWizard {

	@FindBy(xpath = ".//a[@ng-click='exitCaseWiz()']")
	WebElement backButton; 
	
	
	@FindBy(xpath = ".//button[text()[contains(.,'Save')]]")
	WebElement saveAndContBtn;
	
	@FindBy(xpath = ".//a[text()[contains(.,'Save')]]")
	WebElement docSaveAndContBtn;

	@FindBy(xpath = ".//a[@ng-click='skipForNow()']")
	WebElement skipForNowBtn;

	@FindBy(xpath = ".//a[@ng-click='goBack()']")
	WebElement goBackBtn;

	/* elements for option form */
	@FindBy(xpath = "//input[@name='Medicare']")
	WebElement option_1_medicareCheckbox;

	@FindBy(xpath = ".//input[@name='RECEIVED_ANY_CORRESPONDENCE' and @value='true']")
	WebElement option_2_RECEIVED_ANY_CORRESPONDENCE_Y;

	@FindBy(xpath = ".//input[@name='RECEIVED_ANY_CORRESPONDENCE' and @value='false']")
	WebElement option_2_RECEIVED_ANY_CORRESPONDENCE_N;

	@FindBy(xpath = ".//input[@name='PREVIOUSLY_NOTIFIED' and @value='true']")
	WebElement option_2_PREVIOUSLY_NOTIFIED_Y;

	@FindBy(xpath = ".//input[@name='PREVIOUSLY_NOTIFIED' and @value='false']")
	WebElement option_2_PREVIOUSLY_NOTIFIED_N;

	@FindBy(xpath = ".//input[@name='ELIGIBLE_FOR_MEDICARE' and @value='true']")
	WebElement option_2_ELIGIBLE_FOR_MEDICARE_Y;

	@FindBy(xpath = ".//input[@name='ELIGIBLE_FOR_MEDICARE' and @value='false']")
	WebElement option_2_ELIGIBLE_FOR_MEDICARE_N;

	@FindBy(xpath = ".//input[@name='ELIGIBLE_FOR_MEDICARE' and @value='true']")
	WebElement option_2_RECEIVED_FINAL_DEMAND_LETTER_Y;

	@FindBy(xpath = ".//input[@name='ELIGIBLE_FOR_MEDICARE' and @value='false']")
	WebElement option_2_RECEIVED_FINAL_DEMAND_LETTER_N;

	/* elements for client details form */
	@FindBy(xpath = ".//div[@ng-repeat[contains(.,'CLIENT_DETAILS_ADDRESS')]]//input[@id='addressLine1']")
	WebElement clientDetails_1_CDA_addLine1;

	@FindBy(xpath = ".//div[@ng-repeat[contains(.,'CLIENT_DETAILS_ADDRESS')]]//input[@id='addressLine2']")
	WebElement clientDetails_1_CDA_addLine2;

	@FindBy(xpath = ".//div[@ng-repeat[contains(.,'CLIENT_DETAILS_ADDRESS')]]//select[@ng-model='entry.COUNTRY']")
	WebElement clientDetails_1_CDA_country;

	@FindBy(xpath = ".//div[@ng-repeat[contains(.,'CLIENT_DETAILS_ADDRESS')]]//select[@ng-model='entry.STATE']")
	WebElement clientDetails_1_CDA_state;

	@FindBy(xpath = ".//div[@ng-repeat[contains(.,'CLIENT_DETAILS_ADDRESS')]]//input[@id='city']")
	WebElement clientDetails_1_CDA_city;

	@FindBy(xpath = ".//div[@ng-repeat[contains(.,'CLIENT_DETAILS_ADDRESS')]]//input[@id='zip']")
	WebElement clientDetails_1_CDA_zip;

	@FindBy(xpath = ".//input[@data-ng-model[contains(.,'OTHER_ADDRESS')] and @value='true']")
	WebElement clientDetails_1_OTHER_ADDRESS_Y;

	@FindBy(xpath = ".//input[@data-ng-model[contains(.,'OTHER_ADDRESS')] and @value='false']")
	WebElement clientDetails_1_OTHER_ADDRESS_N;

	@FindBy(xpath = ".//input[@name='IS_DECEASED' and @value='true' ]")
	WebElement clientDetails_2_IS_DECEASED_Y;

	@FindBy(xpath = ".//input[@name='IS_DECEASED' and @value='false' ]")
	WebElement clientDetails_2_IS_DECEASED_N;

	@FindBy(xpath = ".//input[@name='IS_INCAPACITATED' and @value='true' ]")
	WebElement clientDetails_2_IS_INCAPACITATED_Y;

	@FindBy(xpath = ".//input[@name='IS_INCAPACITATED' and @value='false' ]")
	WebElement clientDetails_2_IS_INCAPACITATED_N;

	@FindBy(xpath = ".//input[@name='is_MINOR' and @value='true' ]")
	WebElement clientDetails_2_is_MINOR_Y;

	@FindBy(xpath = ".//input[@name='is_MINOR' and @value='false' ]")
	WebElement clientDetails_2_is_MINOR_N;

	@FindBy(xpath = ".//input[@name='IS_IN_BANKRUPTCY' and @value='true' ]")
	WebElement clientDetails_3_IS_IN_BANKRUPTCY_Y;

	@FindBy(xpath = ".//input[@name='IS_IN_BANKRUPTCY' and @value='false' ]")
	WebElement clientDetails_3_IS_IN_BANKRUPTCY_N;

	@FindBy(xpath = ".//input[@name='IS_WRONGFUL_DEATH' and @value='true' ]")
	WebElement clientDetails_4_IS_WRONGFUL_DEATH_Y;

	@FindBy(xpath = ".//input[@name='IS_WRONGFUL_DEATH' and @value='false' ]")
	WebElement clientDetails_4_IS_WRONGFUL_DEATH_N;

	/* elements for injury form */
	@FindBy(name = "LAWSUIT_TYPE")
	WebElement injury_1_lawsuitType;

	@FindBy(name = "DEFENDANT")
	WebElement injury_1_DEFENDANT;

	@FindBy(name = "IS_SELF_INSURED")
	WebElement injury_1_IS_SELF_INSUREDchkbox;

	@FindBy(xpath = ".//input[@placeholder = 'Select Injury Type']")
	WebElement injury_3_CLIENT_INJURY_CAUSED;

	@FindBy(xpath = ".//button[@ng-click[contains(.,'addInjuryObj')]]")
	WebElement injury_3_addInjuryObjBtn;

	@FindBy(xpath = ".//a[@ng-click[contains(.,'selectInjuryMainTopicListType')]]")
	WebElement injury_3_selectInjuryBtn;

	@FindBy(xpath = ".//*[@class[contains(.,'date')]]/span")
	List<WebElement> datePikerBtn;

	@FindBy(xpath = ".//input[@name='CMS_ON_NOTICE' and @value='true']")
	WebElement injury_3_CMS_ON_NOTICE_Y;

	@FindBy(xpath = ".//input[@name='CMS_ON_NOTICE' and @value='false']")
	WebElement injury_3_CMS_ON_NOTICE_N;

	@FindBy(id = "submission")
	WebElement injuryDoneBtn;

	// Settlement Screen
	@FindBy(xpath = ".//input[@name='IS_PROJECTED' and @value='false']")
	WebElement settlement_IS_PROJECTED_Y;

	@FindBy(xpath = ".//input[@name='IS_PROJECTED' and @value='true']")
	WebElement settlement_IS_PROJECTED_N;

	@FindBy(xpath = ".//input[@name='IS_PROJECTED_SETTLEMENT_DATE' and @value='true']")
	WebElement settlement_IS_PROJECTED_SETTLEMENT_DATE_Y;

	@FindBy(xpath = ".//input[@name='IS_PROJECTED_SETTLEMENT_DATE' and @value='false']")
	WebElement settlement_IS_PROJECTED_SETTLEMENT_DATE_N;

	// Document Screen
	@FindBy(xpath = ".//input[@id[contains(.,'COMPLAINT')]]/preceding-sibling::a")
	WebElement document_COMPLAINT_uploadBtn;

	@FindBy(xpath = ".//input[@id[contains(.,'CMS_PROOF_OF_REPRESENTATION')]]/preceding-sibling::a")
	WebElement document_CMS_PROOF_OF_REPRESENTATION_uploadBtn;

	@FindBy(xpath = ".//input[@id[contains(.,'LETTER_OF_AUTHORITY')]]/preceding-sibling::a")
	WebElement document_LETTER_OF_AUTHORITY_uploadBtn;

	// Payment Screen
	@FindBy(xpath = ".//*[@ng-click[contains(.,'PAYMENT_OPTIONS')]]")
	WebElement payment_ProceedToChkoutBtn;

	@FindBy(xpath = ".//*[@ng-model='formInstance.PAYMENT_OPTIONS.agree']")
	WebElement payment_PAYMENT_OPTIONS_agreement_chkbox;

	@FindBy(xpath = ".//*[@ng-click[contains(.,'proceedForPayment')]]")
	WebElement payment_paymentBtn;

	// payment pop up
	@FindBy(id = "cardNum")
	WebElement payment_cardNum;

	@FindBy(id = "expiryDate")
	WebElement payment_expiryDate;

	@FindBy(id = "cvv")
	WebElement payment_cvv;

	@FindBy(id = "firstNameID")
	WebElement payment_firstNameID;

	@FindBy(id = "lastNameID")
	WebElement payment_lastNameID;

	@FindBy(xpath = ".//div[@class='dropdown']/button")
	WebElement payment_countryDropdown;

	@FindBy(xpath = "(.//ul/li/a/div[text()[contains(.,'USA')]])[1]")
	WebElement payment_countryChoice;

	@FindBy(id = "zipID")
	WebElement payment_zipID;

	@FindBy(id = "billingAddressID")
	WebElement payment_billingAddressID;

	@FindBy(id = "cityID")
	WebElement payment_cityID;

	@FindBy(id = "stateID")
	WebElement payment_stateID;

	@FindBy(id = "phonenumberID")
	WebElement payment_phonenumberID;

	@FindBy(id = "payBtn")
	WebElement payment_payBtn;

	public CaseWizard saveAndContinue() throws Exception {
		Utils.uBase.checkPendingRequests();
		Utils.uBase.clickWebelement(saveAndContBtn);
		// Utils.uBase.clickWebelement(saveAndContBtn);
		Utils.uBase.checkPendingRequests();
		return this;
	}

	public CaseWizard skipForNow() throws Exception {
		Utils.uBase.clickWebelement(skipForNowBtn);
		return this;
	}

	public CaseWizard fillOption_1() throws Exception {
		Utils.uBase.checkPendingRequests();
		Utils.uBase.checkbox_Checking(option_1_medicareCheckbox);
		// Utils.uBase.clickWebelement(option_1_medicareCheckbox);

		return this;
	}

	public CaseWizard fillOption_2() throws Exception {
		Utils.uBase.clickWebelement(option_2_RECEIVED_ANY_CORRESPONDENCE_N);
		Utils.uBase.clickWebelement(option_2_PREVIOUSLY_NOTIFIED_N);
		return this;
	}

	public CaseWizard fillClientDetails_1(String addressLine1, String addressLine2, String country, String state,
			String city, String zip) throws Exception {
		Utils.uBase.setText(clientDetails_1_CDA_addLine1, addressLine1);
		Utils.uBase.setText(clientDetails_1_CDA_addLine2, addressLine2);
		Utils.uBase.selectElementByName(clientDetails_1_CDA_country, country);
		Thread.sleep(1000);
		Utils.uBase.selectElementByName(clientDetails_1_CDA_state, state);
		Utils.uBase.setText(clientDetails_1_CDA_city, city);
		Utils.uBase.setText(clientDetails_1_CDA_zip, zip);
		Utils.uBase.clickWebelement(clientDetails_1_OTHER_ADDRESS_N);
		return this;
	}

	public CaseWizard fillClientDetails_2() throws Exception {
		Utils.uBase.clickWebelement(clientDetails_2_IS_DECEASED_N);
		Utils.uBase.clickWebelement(clientDetails_2_IS_INCAPACITATED_N);
		Utils.uBase.clickWebelement(clientDetails_2_is_MINOR_N);
		return this;
	}

	public CaseWizard fillClientDetails_3() throws Exception {
		Utils.uBase.clickWebelement(clientDetails_3_IS_IN_BANKRUPTCY_N);
		return this;
	}

	public CaseWizard fillClientDetails_4() throws Exception {
		Utils.uBase.clickWebelement(clientDetails_4_IS_WRONGFUL_DEATH_N);
		return this;
	}

	public CaseWizard fillInjury_1() throws Exception {
		Utils.uBase.clickWebelement(injury_1_IS_SELF_INSUREDchkbox);
		return this;
	}

	public CaseWizard fillInjury_3(String injuryCausedCat, String injuryCausedSubCat1, String injuryCat,
			String injurySubCat, String injurySubCat1, String injuryDate) throws Exception {
		Utils.uBase.checkPendingRequests();
		Utils.uBase.clickWebelement(injury_3_CLIENT_INJURY_CAUSED);
		Thread.sleep(1000);
		Utils.uBase.checkPendingRequests();
		Utils.uBase.driver.findElements(By.xpath("//table[contains(@id,'DataTables_Table_')]//td")).get(1).click();
		// WebElement ele1 =
		// Utils.uBase.driver.findElement(By.xpath("//table[contains(@id,'DataTables_Table_')]//td[text()[contains(.,'Injury
		// Of Unspecified Body Region')]]"));
		// Utils.uBase.doubleClickWebelement(ele1);
		Thread.sleep(1000);
		Utils.uBase.checkPendingRequests();
		Utils.uBase.driver.findElements(By.xpath("//table[contains(@id,'DataTables_Table_')]//td")).get(1).click();
		/*
		 * WebElement ele2 =Utils.uBase.driver.findElement(By.
		 * xpath("//table[contains(@id,'DataTables_Table_')]//td[text()[contains(.,'Other injury of unspecified body region')]]"
		 * )); Utils.uBase.doubleClickWebelement(ele2);
		 */

		/*
		 * //Utils.uBase.clickWebelement(Utils.uBase.driver.findElement(
		 * By.xpath(
		 * ".//*[starts-with(@id='DataTables_Table_')]//td[text()[contains(.,'"
		 * + injuryCausedCat + "')]]")));
		 */

		/*
		 * Utils.uBase.clickWebelement(Utils.uBase.driver.findElement( By.xpath(
		 * ".//*[starts-with(@id='DataTables_Table_')]//td[text()[contains(.,'"
		 * + injuryCausedCat + "')]]")));
		 */
		
		/*
		 * Utils.uBase.clickWebelement(Utils.uBase.driver.findElement( By.xpath(
		 * ".//*[starts-with(@id='DataTables_Table_')]//td[text()[contains(.,'"
		 * + injuryCausedSubCat1 + "')]]")));
		 */
		Thread.sleep(1000);
		Utils.uBase.checkPendingRequests();
		Utils.uBase.clickWebelement(injury_3_addInjuryObjBtn);
		Thread.sleep(1000);
		Utils.uBase.checkPendingRequests();
		Utils.uBase.clickWebelement(injury_3_selectInjuryBtn);
		Thread.sleep(1000);
		Utils.uBase.checkPendingRequests();

		Utils.uBase.clickWebelement(Utils.uBase.driver.findElement(
				By.xpath(".//*[contains(@id,'DataTables_Table_')]//td[text()[contains(.,'" + injuryCat + "')]]")));
		Thread.sleep(1000);
		Utils.uBase.checkPendingRequests();
		Utils.uBase.clickWebelement(Utils.uBase.driver.findElement(
				By.xpath(".//*[contains(@id,'DataTables_Table_')]//td[text()[contains(.,'" + injurySubCat + "')]]")));
		Thread.sleep(1000);
		Utils.uBase.checkPendingRequests();

		Utils.uBase.driver.findElements(By.xpath("//input[@value = 'injury.MAIN_CODE_NAME']")).get(1).click();

		Thread.sleep(1000);
		Utils.uBase.checkPendingRequests();

		Utils.uBase.doubleClickWebelement(injuryDoneBtn);

		Thread.sleep(1000);
		Utils.uBase.checkPendingRequests();

		// Utils.uBase.driver.findElement(By.xpath("//div[@class[contains(.,'date')]]//span[@class=
		// 'glyphicon glyphicon-calendar']")).click();

		Utils.uBase.clickWebelement(datePikerBtn.get(0));
		Utils.uBase.checkPendingRequests();
		Utils.uBase.clickWebelement(Utils.uBase.driver.findElement(By.xpath(".//*[@data-day='" + injuryDate + "']")));
		Thread.sleep(1000);
		Utils.uBase.checkPendingRequests();
		Utils.uBase.clickWebelement(injury_3_CMS_ON_NOTICE_N);
		return this;
	}

	public CaseWizard fillSettlement(String projectedSettlementDate) throws Exception {
		Utils.uBase.checkPendingRequests();
		Utils.uBase.clickWebelement(settlement_IS_PROJECTED_N);
		Utils.uBase.clickWebelement(settlement_IS_PROJECTED_SETTLEMENT_DATE_Y);
		Utils.uBase.clickWebelement(datePikerBtn.get(0));
		Utils.uBase.clickWebelement(
				Utils.uBase.driver.findElement(By.xpath(".//*[@data-day='" + projectedSettlementDate + "']")));

		return this;
	}

	public CaseWizard fillDocument(String excelPath) throws Exception {
		Utils.uBase.untilAngularFinishHttpCalls();
		Thread.sleep(3000);
		uploadFile(document_COMPLAINT_uploadBtn, excelPath);
		Utils.uBase.checkPendingRequests();
		uploadFile(document_LETTER_OF_AUTHORITY_uploadBtn, excelPath);
		Utils.uBase.checkPendingRequests();
		uploadFile(document_CMS_PROOF_OF_REPRESENTATION_uploadBtn, excelPath);
		Utils.uBase.checkPendingRequests();
		Utils.uBase.clickWebelement(docSaveAndContBtn);
		return this;
	}

	public CaseWizard payment() throws Exception {
		Utils.uBase.clickWebelement(payment_ProceedToChkoutBtn);
		Utils.uBase.clickWebelement(payment_PAYMENT_OPTIONS_agreement_chkbox);
		Utils.uBase.clickWebelement(payment_paymentBtn);
		
		
		Utils.uBase.checkPendingRequests();
		Thread.sleep(1000);
		Utils.uBase.switchToFrame_byName("iframeAuthorizeNet");
		Utils.uBase.setText(payment_cardNum, "5241646463737468");
		Utils.uBase.setText(payment_expiryDate, "0919");
		Utils.uBase.setText(payment_cvv, "123");
		Utils.uBase.setText(payment_firstNameID, "sahil");
		Utils.uBase.setText(payment_lastNameID, "taneja");
		Utils.uBase.clickWebelement(payment_countryDropdown);
		Utils.uBase.clickWebelement(payment_countryChoice);
		Utils.uBase.setText(payment_zipID, "123456");
		Utils.uBase.setText(payment_billingAddressID, "1508,abd");
		Utils.uBase.setText(payment_cityID, "Gurgaon");
		Utils.uBase.setText(payment_stateID, "Harayana");
		Utils.uBase.setText(payment_phonenumberID, "1234567890");

		Utils.uBase.clickWebelement(payment_payBtn);
		
		Utils.uBase.checkPendingRequests();
		Utils.uBase.switchToDefaultContent();
		Utils.uBase.checkPendingRequests();
		return this;
	}

	public CaseDetailsPage exitCaseWizard() throws Exception{
		Thread.sleep(1000);
		Utils.uBase.clickWebelement(backButton);
		Utils.uBase.checkPendingRequests();
		return PageFactory.initElements(Utils.uBase.driver, CaseDetailsPage.class);
	}
	
	public boolean uploadFile(WebElement ele, String excelPath) throws Exception {
		String tempDirPath = System.getProperty("user.dir") + "\\src\\main\\resources\\datafiles\\LccDoc\\";

		StringSelection templatePath = new StringSelection(tempDirPath + excelPath);

		Utils.uBase.uploadFileWithRobot(ele, templatePath);
		Utils.uBase.checkPendingRequests();
		return true;
	}
}
