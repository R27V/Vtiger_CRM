package testNGpractice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.GenericUtilities.IPathConstant;

public class DataProviderdata {

	@DataProvider
	public Object[][] loginData()
	{
		Object[][] obj = new Object[2][2];
		
		obj[0][0] = "admin";
		obj[0][1] = "admin";
		
		obj[1][0] = "admin";
		obj[1][1] = "admin";
		
		return obj;
	}
	
	@DataProvider(name = "readDatafromExcel")
	public Object[][] orgData() throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstant.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("DataProvider");
		int rowCount = sh.getPhysicalNumberOfRows();  // or sh.getLastRowNum()+1
		int cellCount = sh.getRow(0).getPhysicalNumberOfCells(); // or getLastCellNum();
		
		Object[][] obj = new Object[rowCount][cellCount];
		
		for(int i=0; i<rowCount; i++)
		{
			for(int j=0; j<cellCount; j++)
			{
				obj[i][j] = sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return obj;
	}
}
