package DDT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

     public class ToReadDataFromExcel {
      public static void main(String[] args) throws Throwable {
	
	 
        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestScrpitData.xls.xlsx");
           Workbook wb = WorkbookFactory.create(fis);
           Sheet sh = wb.getSheet("campaign");
           Row r = sh.getRow(1);
           String campname = r.getCell(2).getStringCellValue();
           String target = r.getCell(3).getStringCellValue();
           System.out.println(campname);
           System.out.println(target);
}
}
