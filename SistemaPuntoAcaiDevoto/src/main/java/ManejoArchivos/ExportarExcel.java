package ManejoArchivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ConexionBD.ObtenerDatos;
import ModelosClientes.Cliente;

public class ExportarExcel {
	static ObtenerDatos obtenerDatos;
	
	public static void main(String[]args) throws SQLException, IOException {
		exportarClientes();
	}
	
	public static void exportarClientes() throws SQLException, IOException {
		obtenerDatos = new ObtenerDatos();
		
		String[] columnas = {"clie_nombre", "clie_apellido", "clie_dni", "clie_telefono","clie_email","dire_calle","dire_numero","dire_codPostal","clie_como_llego","clie_tipo"};
		List<Cliente> clientes = obtenerDatos.obtenerClientes();
	
		// Create a Workbook
	    Workbook workbook = new XSSFWorkbook();
	    
	    /* CreationHelper helps us create instances of various things like DataFormat, 
	    Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	    CreationHelper createHelper = workbook.getCreationHelper();
	    
	    // Create a Sheet
        Sheet sheet = workbook.createSheet("Clientes");
        
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        
        // Create a Row
        Row headerRow = sheet.createRow(0);
        
        // Create cells
        for(int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        
        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(Cliente unCliente: clientes) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                .setCellValue(unCliente.getNombre());

            row.createCell(1)
                .setCellValue(unCliente.getApellido());

            row.createCell(2)
                .setCellValue(unCliente.getDni());
            
            row.createCell(3)
            	.setCellValue(unCliente.getTelefono());
            
            row.createCell(4)
            	.setCellValue(unCliente.getEmail());
            
            row.createCell(5)
            	.setCellValue(unCliente.getCalle());
            
            row.createCell(6)
            	.setCellValue(unCliente.getNumero());
            
            row.createCell(7)
            	.setCellValue(unCliente.getCodPostal());
            
            row.createCell(8)
            	.setCellValue(unCliente.getComoLlego());
            
            row.createCell(9)
            	.setCellValue(unCliente.getTipo());
        }
        
        // Resize all columns to fit the content size
        for(int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("clientes.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();


	}
	
}
