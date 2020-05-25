package ImportarArchivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ImportarExcel {
	
	public void leerExcel(String path) throws IOException {
		File excel = new File(path);
		FileInputStream fileInputStream = new FileInputStream(excel);
		
		//no se que es lo que hace esto
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		
		//obtengo la primer sheet
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//itero filas
		Iterator<Row> rowIterator = sheet.iterator();
		
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			//itero las celdas para la fila actual
			Iterator<Cell> cellIterator = row.cellIterator();
			
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				System.out.print(cell.toString() + ";");
				
			}
			System.out.println();
		}
		workbook.close();
		fileInputStream.close();
	}

}
