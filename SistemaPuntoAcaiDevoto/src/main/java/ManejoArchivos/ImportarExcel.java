package ManejoArchivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ConexionBD.InsertarDatos;
import ModelosClientes.Cliente;
import ModelosClientes.Direccion;



public class ImportarExcel {
	InsertarDatos insertarDatos;
	public void leerExcel(String path) throws IOException, SQLException {
		
		File excel = new File(path);
		FileInputStream fileInputStream = new FileInputStream(excel);
		
		//no se que es lo que hace esto
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		
		//obtengo la primer sheet
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//itero filas
		Iterator<Row> rowIterator = sheet.iterator();
		
		Row row = rowIterator.next();
		//TODO CHEQUEAR QUE LAS COLUMNAS ESTEN EN EL ORDEN REQUERIDO
		List<String> listaValores = new ArrayList<String>();
		while(rowIterator.hasNext()) {
			row = rowIterator.next();
			
			//itero las celdas para la fila actual
			Iterator<Cell> cellIterator = row.cellIterator();
			
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				listaValores.add(cell.toString());
				//System.out.print(cell.toString() +row.getLastCellNum()+ ";");
			}
			
			//System.out.print(Float.parseFloat((listaValores.get(6))));
			
			insertarDatos=new InsertarDatos();
			Direccion unaDireccion = new Direccion(listaValores.get(5),(int) Float.parseFloat((listaValores.get(6))) ,listaValores.get(7),(int) Float.parseFloat((listaValores.get(8))));
			Cliente unCliente = new Cliente((int) Float.parseFloat((listaValores.get(0))),listaValores.get(1),listaValores.get(2),(int) Float.parseFloat((listaValores.get(3))), listaValores.get(4),unaDireccion,listaValores.get(9) , listaValores.get(10));
			insertarDatos.insertarCliente(unCliente);
			listaValores.clear();
			
			
		}
		workbook.close();
		fileInputStream.close();
	}

}
