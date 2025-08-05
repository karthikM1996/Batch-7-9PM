package DDT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadDataMultipleRow {

	public static void main(String[] args) throws Throwable {
		
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestScrpitData.xls.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("mobile");
		
		//to get lastrow number
		int rowcount = sh.getLastRowNum();
		
		for(int i=1;i<=rowcount;i++)
		{
			Row r = sh.getRow(i);
			String prodcat = r.getCell(0).getStringCellValue();
			String prodname = r.getCell(1).getStringCellValue();
			System.out.println(prodcat+"=="+prodname);
		}
		
	}
}
