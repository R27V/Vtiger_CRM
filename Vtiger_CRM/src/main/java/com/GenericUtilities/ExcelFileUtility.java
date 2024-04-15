package com.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelFileUtility {

	/**
	 * This method will read the data from excel sheet and return the value to the caller function
	 * @author RENU
	 * @param sheetName
	 * @param rowNo
	 * @param colNo
	 * @return
	 * @throws Throwable
	 */
	public String readDataFromExcel(String sheetName,int rowNo, int cellNo) throws Throwable 
	{
		FileInputStream fis = new FileInputStream(IPathConstant.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		String value = sh.getRow(rowNo).getCell(cellNo).getStringCellValue();
		wb.close();
		return value;
	}
	
	/**
	 * This method will give the number of row present in excel sheet
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public int getTotalRowCount(String sheetName) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstant.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		return rowCount;
	}
	
	/**
	 * This method will write data into Excel Sheet
	 * @param sheetName
	 * @param rowno
	 * @param colNo
	 * @throws Throwable
	 */
	public void writeDataIntoExcel(String sheetName, int rowNo, int cellNo, String value) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstant.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		wb.createSheet(sheetName).createRow(rowNo).createCell(cellNo).setCellValue(value);
		
		FileOutputStream fos = new FileOutputStream(IPathConstant.ExcelPath);
		wb.write(fos);
		wb.close();
		
	}
	
	/**
	 * this method will read multiple data from excel sheet but element must be find by same locator only otherwise hashmap will not work
	 * @param sheetName
	 * @param cellNo
	 * @return
	 * @throws Throwable
	 */
	public HashMap<String,String> readMultipleData(String sheetName, int cellNo) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstant.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.createSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		for(int i=0; i<=rowCount; i++)
		{
			String key = sh.getRow(i).getCell(cellNo).getStringCellValue();
			String value = sh.getRow(i).getCell(cellNo+1).getStringCellValue();
			map.put(key, value);
		}
		return map;
	}
	
	
}
