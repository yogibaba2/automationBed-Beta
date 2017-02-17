package Ituple.automationBed_Beta.utility.excelManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Read_XLS {	
	public  String filelocation;
	public  FileInputStream ipstr = null;
	public  FileOutputStream opstr =null;
	private XSSFWorkbook wb = null;
	private XSSFSheet ws = null;	
	
	public Read_XLS(String filelocation) {		
		this.filelocation=filelocation;
		try {
			ipstr = new FileInputStream(filelocation);
			wb = new XSSFWorkbook(ipstr);
			ws = wb.getSheetAt(0);
			ipstr.close();
		} catch (Exception e) {			
			e.printStackTrace();
		} 
		
	}
	
	public Read_XLS(String filelocation, String rights) {		
		this.filelocation=filelocation;
		try {
			ipstr = new FileInputStream(filelocation);
			wb = new XSSFWorkbook(ipstr);
			ws = wb.getSheetAt(0);
			ipstr.close();
		} catch (FileNotFoundException e) {			
			if (rights.equalsIgnoreCase("w")) {
				if(createWorkbook(filelocation)){
					try {
						ipstr = new FileInputStream(filelocation);
						wb = new XSSFWorkbook(ipstr);
						ws = wb.getSheetAt(0);
						ipstr.close();
					} catch (Exception e1) {			
						e.printStackTrace();
					} 
				}
				
			} else {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	//To retrieve No Of Rows from .xls file's sheets.
	public int retrieveNoOfRows(String wsName){		
		int sheetIndex=wb.getSheetIndex(wsName);
		if(sheetIndex==-1)
			return 0;
		else{
			ws = wb.getSheetAt(sheetIndex);
			int rowCount=ws.getLastRowNum()+1;		
			return rowCount;		
		}
	}
	
	//To retrieve No Of Columns from .cls file's sheets.
	public int retrieveNoOfCols(String wsName){
		int sheetIndex=wb.getSheetIndex(wsName);
		if(sheetIndex==-1)
			return 0;
		else{
			ws = wb.getSheetAt(sheetIndex);
			int colCount=ws.getRow(0).getLastCellNum();			
			return colCount;
		}
	}
	
	//To retrieve SuiteToRun and CaseToRun flag of test suite and test case.
	public String retrieveToRunFlag(String wsName, String colName, String rowName){
		
		int sheetIndex=wb.getSheetIndex(wsName);
		if(sheetIndex==-1)
			return null;
		else{
			int rowNum = retrieveNoOfRows(wsName);
			int colNum = retrieveNoOfCols(wsName);
			int colNumber=-1;
			int rowNumber=-1;			
			
			XSSFRow Suiterow = ws.getRow(0);				
			
			for(int i=0; i<colNum; i++){
				if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
					colNumber=i;					
				}					
			}
			
			if(colNumber==-1){
				return "";				
			}
			
			
			for(int j=0; j<rowNum; j++){
				XSSFRow Suitecol = ws.getRow(j);				
				if(Suitecol.getCell(0).getStringCellValue().equals(rowName.trim())){
					rowNumber=j;	
				}					
			}
			
			if(rowNumber==-1){
				return "";				
			}
			
			XSSFRow row = ws.getRow(rowNumber);
			XSSFCell cell = row.getCell(colNumber);
			if(cell==null){
				return "";
			}
			String value = cellToString(cell);
			return value;			
		}			
	}
	
	//To retrieve DataToRun flag of test data.
	public String[] retrieveToRunFlagTestData(String wsName, String colName){
		
		int sheetIndex=wb.getSheetIndex(wsName);
		if(sheetIndex==-1)
			return null;
		else{
			int rowNum = retrieveNoOfRows(wsName);
			int colNum = retrieveNoOfCols(wsName);
			int colNumber=-1;
					
			
			XSSFRow Suiterow = ws.getRow(0);				
			String data[] = new String[rowNum-1];
			for(int i=0; i<colNum; i++){
				if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
					colNumber=i;					
				}					
			}
			
			if(colNumber==-1){
				return null;				
			}
			
			for(int j=0; j<rowNum-1; j++){
				XSSFRow Row = ws.getRow(j+1);
				if(Row==null){
					data[j] = "";
				}
				else{
					XSSFCell cell = Row.getCell(colNumber);
					if(cell==null){
						data[j] = "";
					}
					else{
						String value = cellToString(cell);
						data[j] = value;	
					}	
				}
			}
			
			return data;			
		}			
	}
	
	
	public ArrayList<ArrayList<Object>> retrieveTestDataAsArrayList(String wsName){
		
		int sheetIndex=wb.getSheetIndex(wsName);
		if(sheetIndex==-1){
			return null;
		}
		else{
			int rowNum=retrieveNoOfRows(wsName);
			int colNum= retrieveNoOfCols(wsName);
			 
			ArrayList<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();
			
			
			for(int i=1;i<rowNum;i++){
				XSSFRow row= ws.getRow(i);
				ArrayList<Object> rowdata = new ArrayList<Object>(); 
				for(int j=0;j<colNum;j++){
					
					if(row==null){
						data.add(null);
						break;
					}
					else{
						XSSFCell cell = row.getCell(j);
						if(cell==null){
							rowdata.add("");
						}
						else{
							//cell.setCellType(Cell.CELL_TYPE_STRING);
							String value= cellToString(cell);
							rowdata.add(value);
						}
						
					}
				}
				data.add(rowdata);
			}
			return data;
		}
		
	}
	
	//To retrieve test data from test case data sheets.
	public Object[][] retrieveTestData(String wsName){
		int sheetIndex=wb.getSheetIndex(wsName);
		if(sheetIndex==-1)
			return null;
		else{
				int rowNum = retrieveNoOfRows(wsName);
				int colNum = retrieveNoOfCols(wsName);
		
				Object data[][] = new Object[rowNum-1][colNum-2];
		
				for (int i=0; i<rowNum-1; i++){
					XSSFRow row = ws.getRow(i+1);
					for(int j=0; j< colNum-2; j++){					
						if(row==null){
							data[i][j] = "";
							break;
						}
						else{
							XSSFCell cell = row.getCell(j);	
					
							if(cell==null){
								data[i][j] = "";							
							}
							else{
								//cell.setCellType(Cell.CELL_TYPE_STRING);
								String value = cellToString(cell);
								data[i][j] = value;						
							}
						}
					}				
				}			
				return data;		
		}
	
	}		
	
	
	public static String cellToString(XSSFCell cell){
		int type;
		Object result;
		DataFormatter formatter = new DataFormatter();
		switch (cell.getCellType()){
			case 0 :
				 if (DateUtil.isCellDateFormatted(cell)) {
                     result= formatter.formatCellValue(cell);
                 } else {
                	 result = formatter.formatCellValue(cell);
                 }
				
				break;
				
			case 1 : 
				result = cell.getStringCellValue();
				break;
				
			default :
				throw new RuntimeException("Unsupportd cell.");			
		}
		return result.toString();
	}
	
	//To write result In test data and test case list sheet.
	public boolean writeResult(String wsName, String colName, int rowNumber, String Result){
		try{
			int sheetIndex=wb.getSheetIndex(wsName);
			if(sheetIndex==-1)
				return false;
			
			ws=wb.getSheetAt(sheetIndex);
			int colNum = retrieveNoOfCols(wsName);
			int colNumber=-1;
					
			
			XSSFRow Suiterow = ws.getRow(0);			
			for(int i=0; i<colNum; i++){				
				if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
					colNumber=i;					
				}					
			}
			
			if(colNumber==-1){
				return false;				
			}
			
			XSSFRow Row = ws.getRow(rowNumber);
			XSSFCell cell = Row.getCell(colNumber);
			if (cell == null)
		        cell = Row.createCell(colNumber);			
			
			cell.setCellValue(Result);
			
			opstr = new FileOutputStream(filelocation);
			wb.write(opstr);
			opstr.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//To write result In test suite list sheet.
	public boolean writeResult(String wsName, String colName, String rowName, String Result){
		try{
			int rowNum = retrieveNoOfRows(wsName);
			int rowNumber=-1;
			int sheetIndex=wb.getSheetIndex(wsName);
			if(sheetIndex==-1)
				return false;
			
			ws=wb.getSheetAt(sheetIndex);
			int colNum = retrieveNoOfCols(wsName);
			int colNumber=-1;
					
			
			XSSFRow Suiterow = ws.getRow(0);			
			for(int i=0; i<colNum; i++){				
				if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim())){
					colNumber=i;					
				}					
			}
			
			if(colNumber==-1){
				return false;				
			}
			
			for (int i=0; i<rowNum-1; i++){
				XSSFRow row = ws.getRow(i+1);				
				XSSFCell cell = row.getCell(0);	
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String value = cellToString(cell);	
				if(value.equals(rowName)){
					rowNumber=i+1;
					break;
				}
			}		
			
			XSSFRow Row = ws.getRow(rowNumber);
			XSSFCell cell = Row.getCell(colNumber);
			if (cell == null)
		        cell = Row.createCell(colNumber);			
			
			cell.setCellValue(Result);
			
			opstr = new FileOutputStream(filelocation);
			wb.write(opstr);
			opstr.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public boolean createWorkbook(String workbook){
		
		try {
			if ((workbook.split("[.]")[1]).equalsIgnoreCase("xls")) {
				Workbook tempwb = new HSSFWorkbook();
				Sheet sheet1 = tempwb.createSheet("sheet1");
			    FileOutputStream fileOut = new FileOutputStream(workbook);
			    tempwb.write(fileOut);
			    fileOut.close();
			    tempwb.close();
			} else if((workbook.split("[.]")[1]).equalsIgnoreCase("xlsx")){
				 Workbook tempwb = new XSSFWorkbook();
				 Sheet sheet1 = tempwb.createSheet("sheet1");
			 	 FileOutputStream fileOut = new FileOutputStream(workbook);
			 	 tempwb.write(fileOut);
			 	 fileOut.close();
			 	tempwb.close();
			}
			else{
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	 	
	 	return true;
	}
	
	
	public boolean isWorksheetExist(String worksheet){
		
		int sheetIndex=wb.getSheetIndex(worksheet);
		
		if(sheetIndex==-1)
			return false;	
		
		return true;	
	}
	
	public boolean createWorksheet(String worksheet){
		
		 	try {
				String safeName = WorkbookUtil.createSafeSheetName(worksheet); // replace invalid characters with a space (' ')
				Sheet sheet3 = wb.createSheet(safeName);
				opstr = new FileOutputStream(filelocation);
				wb.write(opstr);
				opstr.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
		return true;
	}
	
	
	public boolean addRowAt(String worksheet, ArrayList<Object> rowData, int index){
		try {
			int sheetIndex=wb.getSheetIndex(worksheet);
			
			//create worksheet if not exist
			if(sheetIndex==-1){
				createWorksheet(worksheet);
				sheetIndex=wb.getSheetIndex(worksheet);
				System.out.println("sheet not exist new sheet craeted.");
	
			}
			
			ws=wb.getSheetAt(sheetIndex);
			
			Row row = ws.createRow(index);
			
			for (int i = 0; i < rowData.size(); i++) {
				row.createCell(i).setCellValue((String)rowData.get(i));
			}
			opstr = new FileOutputStream(filelocation);
			wb.write(opstr);
			opstr.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean addRow(String worksheet, ArrayList<Object> rowData){
		try {
			int sheetIndex=wb.getSheetIndex(worksheet);
			
			//create worksheet if not exist
			if(sheetIndex==-1){
				createWorksheet(worksheet);
				sheetIndex=wb.getSheetIndex(worksheet);
				System.out.println("sheet not exist new sheet craeted.");
	
			}
			
			ws=wb.getSheetAt(sheetIndex);
			int rowNum = retrieveNoOfRows(worksheet);
			
			Row row = ws.createRow(rowNum);
			
			for (int i = 0; i < rowData.size(); i++) {
				row.createCell(i).setCellValue((String)rowData.get(i));
			}
			opstr = new FileOutputStream(filelocation);
			wb.write(opstr);
			opstr.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public ArrayList<Object> getRowAt(String worksheet, int index){
		
		ArrayList<Object> rowData;
		try {	
			int sheetIndex=wb.getSheetIndex(worksheet);
			
			//create worksheet if not exist
			if(sheetIndex==-1){
				return null;
			}else{
				int colNum = retrieveNoOfCols(worksheet);
				if(colNum==-1){
					return null;
				}else{
					
					ws=wb.getSheetAt(sheetIndex);
					
					XSSFRow row= ws.getRow(index);
					
					if(row==null){
						return null;
					}else{
						rowData = new ArrayList<Object>(); 
						for(int j=0;j<colNum;j++){
							XSSFCell cell = row.getCell(j);
							if(cell==null){
								rowData.add("");
								}else{
								//cell.setCellType(Cell.CELL_TYPE_STRING);
									
								String value= cellToString(cell);
								//System.out.println(value);
								rowData.add(value);
							}
								
						}
					}
					
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return rowData;
	}
	
}
