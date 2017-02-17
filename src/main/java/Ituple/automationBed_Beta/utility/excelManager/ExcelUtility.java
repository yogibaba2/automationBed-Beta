package Ituple.automationBed_Beta.utility.excelManager;

import java.util.ArrayList;

import Ituple.automationBed_Beta.utility.base.Utils;

public class ExcelUtility {

	public Object[][] getData(Read_XLS workbook,String worksheet){
		Utils.addLog.debug("retrieving data from : {}",worksheet);
		return workbook.retrieveTestData(worksheet);
	}

	public boolean writeResult(Read_XLS workbook,String worksheet,String colName, String testCase, String result){
		Utils.addLog.debug("Updating result : {} for : {}",result,testCase);
		return workbook.writeResult(worksheet, colName, testCase, result);
	}

	public boolean getToRunFlag(Read_XLS workbook,String worksheet,String testCase) {
		if(workbook.retrieveToRunFlag(worksheet, "flag", testCase).equalsIgnoreCase("y")){
			Utils.addLog.info("flag Y retrieved  for {}",testCase);
			return true;
		}else{
			Utils.addLog.info("flag N retrieved  for {}",testCase);
			return false;
		}
			
	}
	public ArrayList<ArrayList<Object>> getDataAsArrayList(Read_XLS workbook,String worksheet){
		Utils.addLog.debug("retrieving data from : {} as arrayList",worksheet);
		return workbook.retrieveTestDataAsArrayList(worksheet);
	}
}
