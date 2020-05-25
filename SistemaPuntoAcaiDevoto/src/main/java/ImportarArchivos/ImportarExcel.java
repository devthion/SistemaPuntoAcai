package ImportarArchivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ImportarExcel {
	
	public void leerExcel(String path) throws IOException {
		File excel = new File(path);
		FileInputStream fileInputStream = new FileInputStream(excel);
		
		
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
	}

}
