package Ituple.automationBed_Beta.pom.lccSe;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;

public class UserManagementPage {

	// NgWebDriver ngdriver = new
	// NgWebDriver((JavascriptExecutor)Utils.uBase.driver);

	@FindBy(xpath = "//button[@ng-click='addUserPopUp()']")
	WebElement adduser;

	@FindBy(name = "email")
	WebElement UemailId;

	@FindBy(name = "fname")
	WebElement fname;

	@FindBy(name = "lname")
	WebElement lname;

	@FindBy(name = "mname")
	WebElement mname;

	@FindBy(id = "dob")
	WebElement dob;

	@FindBy(name = "lawyerId")
	WebElement lawyerId;

	@FindBy(className = "phonenumber")
	WebElement Cnumber;

	@FindBy(name = "role")
	WebElement role;

	@FindBy(xpath = "//select[@ng-model='editSelectedUser.role']")
	WebElement editRole;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnAddUser;

	@FindBy(xpath = "//select[@ng-model='user.selectedProgramID']")
	WebElement selectPrgm;

	@FindBy(xpath = "//select[@ng-model='user.selectedProjectID']")
	WebElement selectPrjct;

	@FindBy(xpath = "//tr[@ng-repeat='case in caseListForSelectedProject track by $index']")
	WebElement selectCase;

	@FindBy(xpath = "//input[@type='search']")
	WebElement caseSearch;

	@FindBy(xpath = "//table[@id[contains(.,'DataTables_Table')]]")
	WebElement caseTable;

	@FindBy(xpath = "//a[@ng-click='editUserCall(user)']")
	WebElement btnEditCase;

	@FindBy(xpath = "//button[@class = 'btn btn-primary btn-xs pull-right']")
	WebElement btnUpdateCase;

	@FindBy(xpath = ".//*[@class[contains(.,'date')]]/span")
	List<WebElement> datePikerBtn;

	// "//table[@id='DataTables_Table_0']/tbody/tr[1]/td[1]

	public UserManagementPage openadduser() throws Exception {
		Thread.sleep(5000);
		AdvanceReporting.addLogs("pass", "User Management opened successfuly.");
		AdvanceReporting.addLogs("pass", "User Management opened successfuly.", "Usermanagement_Page");
		Utils.uBase.clickWebelement(adduser);

		return this;
	}

	public String AddUser(String role, String prgData) throws Exception {

		if (Utils.uBase.isElementPresent_webelement(UemailId)) {
			AdvanceReporting.addLogs("pass", "Add User page opened successfuly.");
			AdvanceReporting.addLogs("pass", "Add User page opened successfuly.", "Add User_Page");

			// verifying validation messages
			verifyValidationMessage(UemailId, "Email");
			verifyValidationMessage(fname, "First Name");
			verifyValidationMessage(lname, "Last Name");
			mname.sendKeys("989");
			verifyValidationMessage(mname, "Middle Name");
			verifyValidationMessage(lawyerId, "Lawyer Id");
			verifyValidationMessage(this.role, "Role");

			// populating valid data
			String userEmailId = "davidk" + Utils.uBase.generateRandomNumber(5) + "@ituple.com";
			Utils.uBase.setText(UemailId, userEmailId);
			Utils.uBase.setText(fname, "David");
			Utils.uBase.setText(lname, "Clinton");
			Utils.uBase.setText(mname, "K");
			Utils.uBase.setText(lawyerId, "LawID001");
			Utils.uBase.setText(Cnumber, "8860094494");
			Utils.uBase.clickWebelement(datePikerBtn.get(0));
			Utils.uBase.clickWebelement(Utils.uBase.driver.findElement(By.xpath(".//*[@data-day='09/01/2017']")));
			// Utils.uBase.setText(dob, "08/30/1985");
			if (role.contentEquals("LF ADMIN")) {
				Utils.uBase.selectElementByName(this.role, "LF ADMIN");
				Utils.uBase.clickWebelement(btnAddUser);
			} else if (role.contentEquals("LF PROGRAM MANAGER")) {
				Utils.uBase.selectElementByName(this.role, "LF PROGRAM MANAGER");
				Thread.sleep(5000);
				Utils.uBase.checkPendingRequests();

				String[] arrPrgdata = prgData.split(";");
				for (String a : arrPrgdata) {
					Utils.uBase.clickWebelement(Utils.uBase.driver.findElement(By.name(a)));
				}
				Utils.uBase.clickWebelement(btnAddUser);
			} else if (role.contentEquals("LF CASE MANAGER")) {
				Utils.uBase.selectElementByName(this.role, "LF CASE MANAGER");
				Thread.sleep(1000);
				Utils.uBase.checkPendingRequests();
				Utils.uBase.selectElementByIndex(selectPrgm, 2);
				Utils.uBase.checkPendingRequests();
				Utils.uBase.selectElementByIndex(selectPrjct, 1);
				Utils.uBase.checkPendingRequests();
				List<WebElement> caselist = Utils.uBase.driver.findElements(
						By.xpath("//tr[@ng-repeat='case in caseListForSelectedProject track by $index']"));

				if (caselist.size() > 0) {
					List<WebElement> caseid = caselist.get(0).findElements(By.tagName("td"));
					String clmName = caseid.get(0).getText().toString();
					System.out.println(clmName);
					String casid = caseid.get(1).getText().toString();
					System.out.println(casid);
					caseid.get(2).click();
					Thread.sleep(5000);
					Utils.uBase.clickWebelement(btnAddUser);
				} else {
					AdvanceReporting.addLogs("fail", "Case not loaded for selected program and project");
					AdvanceReporting.addLogs("fail", "Case not loaded for selected program and project", "caseload");
					throw new Exception("No case found for selected program and project");
				}

			} else {
				throw new Exception("Invalid role provided");
			}
			Utils.uBase.checkPendingRequests();
			Thread.sleep(1000);
			return userEmailId;
		} else {
			AdvanceReporting.addLogs("fail", "Add User page does not opened successfuly.");
			return null;
		}
	}

	public void verifyAddedUser(String userEmailId) throws Exception {

		java.util.List<WebElement> caseRowCol = getUserRow(userEmailId);
		if (caseRowCol.get(1).getText().toString().equalsIgnoreCase(userEmailId)) {
			System.out.println("pass");
			AdvanceReporting.addLogs("pass", "User added Successfully");
			AdvanceReporting.addLogs("pass", "User added Successfully", "User_Added");

		} else {
			AdvanceReporting.addLogs("fail", "User not added");
		}

	}

	public void editUser(String userEmailId) throws Exception {

		java.util.List<WebElement> caseRowCol = getUserRow(userEmailId);
		caseRowCol.get(4).click();
		Utils.uBase.checkPendingRequests();
		if (Utils.uBase.isElementPresent_webelement(editRole)) {
			Utils.uBase.selectElementByName(editRole, "LF ADMIN");
			Utils.uBase.checkPendingRequests();
			Thread.sleep(1000);
			Utils.uBase.clickWebelement(btnUpdateCase);
			Utils.uBase.checkPendingRequests();
			Thread.sleep(1000);
			// VerifyeditUser(userEmailId);
			AdvanceReporting.addLogs("pass", "Open user to Edit");
			AdvanceReporting.addLogs("pass", "Open user to Edit", "User_EditPage");
		} else {
			AdvanceReporting.addLogs("fail", "Edit User page does not open");
		}

	}

	public void VerifyeditUser(String userEmailId) throws Exception {

		java.util.List<WebElement> caseRowCol = getUserRow(userEmailId);
		if (caseRowCol.get(2).getText().toString().contentEquals("LF ADMIN")) {
			AdvanceReporting.addLogs("pass", "User updated");
			AdvanceReporting.addLogs("pass", "User updated Successfully", "User_Page");
			System.out.println("Updated Successfully");
		} else {
			AdvanceReporting.addLogs("fail", "User not updated");
		}

	}

	public java.util.List<WebElement> getUserRow(String userEmailId) throws Exception {

		Utils.uBase.setText(caseSearch, userEmailId);
		java.util.List<WebElement> caseRowCol = null;
		java.util.List<WebElement> caseRows = null;
		if (Utils.uBase.isElementPresent_webelement(caseTable)) {
			caseRows = caseTable.findElements(By.tagName("tr"));
			System.out.println(caseRows);
			caseRowCol = caseRows.get(1).findElements(By.tagName("td"));
			System.out.println(caseRowCol);
		}
		return caseRowCol;
	}

	public void deActivateUser(String userEmailId) throws Exception {
		// Utils.uBase.setText(caseSearch, userEmailId);

		try {
			java.util.List<WebElement> caseRowCol = getUserRow(userEmailId);
			caseRowCol.get(3).findElement(By.xpath("//div[@class='slider round']")).click();
			AdvanceReporting.addLogs("pass", "User deactivated Successfully");
			AdvanceReporting.addLogs("pass", "User deactivated Successfully", "User_Page");
			System.out.println(caseRowCol.get(3));
		} catch (Exception e) {
			AdvanceReporting.addLogs("fail", "User not deactivated");
		}

	}

	public boolean verifyValidationMessage(WebElement ele, String fieldName) throws Exception {
		ele.sendKeys(Keys.TAB);
		if (getValidationMessage(fieldName) != null) {
			AdvanceReporting.addLogs("pass", "Validation message is displayed for incorrect " + fieldName);
			AdvanceReporting.addLogs("pass", "Validation message is displayed for incorrect " + fieldName, fieldName);
			return true;
		} else {
			AdvanceReporting.addLogs("fail", "Validation message is not displayed for incorrect " + fieldName);
			AdvanceReporting.addLogs("fail", "Validation message is not displayed for incorrect " + fieldName,
					fieldName);
			return false;
		}

	}

	public String getValidationMessage(String fieldName) throws Exception {
		String validationMessage = null;

		try {
			validationMessage = Utils.uBase.driver
					.findElement(By.xpath(".//em[text()[contains(.,'" + fieldName + "')]]")).getText();
		} catch (NoSuchElementException e) {
			Utils.addLog.info("No Validaton message displayed for : {}", fieldName);
		} catch (Exception e) {
			throw e;
		}
		return validationMessage;
	}
}
