package ManejoArchivos;

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
import ConexionBD.Querys;
import ModeloGasto.Gasto;
import ModelosClientes.Cliente;
import Productos.Producto;
import Ventas.Item;
import Ventas.Venta;

public class ExportarExcel {
	static ObtenerDatos obtenerDatos;
	
	public static void main(String[]args) throws SQLException, IOException {
		exportar();
	}
	
	public static void exportar() throws SQLException, IOException {
		obtenerDatos = new ObtenerDatos();
		
		
		List<Cliente> clientes = obtenerDatos.obtenerClientes(new Querys().queryClientes());
		List<Gasto> gastos=obtenerDatos.obtenerGastos();
		List<Producto> productos = obtenerDatos.obtenerProductos();
		List<Venta> ventas = obtenerDatos.obtenerVentas();
		List<Item> itemsVenta= obtenerDatos.obtenerItemsVenta();
	
		// Create a Workbook
	    Workbook workbook = new XSSFWorkbook();
	    
	    /* CreationHelper helps us create instances of various things like DataFormat, 
	    Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	    CreationHelper createHelper = workbook.getCreationHelper();
	    
	    // Create a Sheet
        Sheet sheet = workbook.createSheet("Clientes");
        Sheet sheet1 = workbook.createSheet("Ventas");
        Sheet sheet2 = workbook.createSheet("Item_Venta");
        Sheet sheet3 = workbook.createSheet("Productos");
        Sheet sheet4 = workbook.createSheet("Gastos");
        
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        
        
        insertarDatosEnSheetVentas(sheet1, ventas, headerCellStyle);
        insertarDatosEnSheetClientes(sheet, clientes, headerCellStyle);
        insertarDatosEnSheetGastos(sheet4, gastos, headerCellStyle);
        insertarDatosEnSheetProductos(sheet3, productos, headerCellStyle);
        insertarDatosEnSheetItemsVenta(sheet2, itemsVenta, headerCellStyle);
      
        
        // Create Other rows and cells with  data
        
        
        
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("clientes.xlsx");
        
        workbook.write(fileOut);
        System.out.println("Excel exportado");
        fileOut.close();

        // Closing the workbook
        workbook.close();


	}
	
	
	private static void insertarDatosEnSheetItemsVenta(Sheet sheet2, List<Item> itemsVenta, CellStyle headerCellStyle) {
		String[] columnas = {"numero_venta", "cantidad_item", "producto","precio_item"};
		// Create a Row
		Row headerRow = sheet2.createRow(0);
		
		int rowNum = 1;
        for(Item unItem: itemsVenta) {
            Row row = sheet2.createRow(rowNum++);

            row.createCell(0)
                .setCellValue(unItem.getItem_venta()+1);

            row.createCell(1)
                .setCellValue(unItem.getCantidad());

            row.createCell(2)
                .setCellValue(unItem.getProducto().getNombreProducto());
            
            row.createCell(3)
            .setCellValue(unItem.getItemPrecio());
       
        }
        
        // Resize all columns to fit the content size
        for(int i = 0; i < columnas.length; i++) {
            sheet2.autoSizeColumn(i);
        }
        
        // Create cells
        for(int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }
		
	}

	private static void insertarDatosEnSheetProductos(Sheet sheet3, List<Producto> productos,
			CellStyle headerCellStyle) {
		String[] columnas = {"nombre_producto", "kilos_producto", "stock_producto","precio_producto","precio_mayor_producto","costo_producto","cantidad_mayor_producto"};
		// Create a Row
		Row headerRow = sheet3.createRow(0);
		
		int rowNum = 1;
        for(Producto unProd: productos) {
            Row row = sheet3.createRow(rowNum++);

            row.createCell(0)
                .setCellValue(unProd.getNombre());

            row.createCell(1)
                .setCellValue(unProd.getKilos());

            row.createCell(2)
                .setCellValue(unProd.getStock());
            
            row.createCell(3)
            .setCellValue(unProd.getPrecioUnitario());
            
            row.createCell(4)
            .setCellValue(unProd.getPrecioMayor());
            
            row.createCell(5)
            .setCellValue(unProd.getCosto());
            
            row.createCell(6)
            .setCellValue(unProd.getCantidadPorMayor());
        }
        
        // Resize all columns to fit the content size
        for(int i = 0; i < columnas.length; i++) {
            sheet3.autoSizeColumn(i);
        }
        
        // Create cells
        for(int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }
		
	}

	private static void insertarDatosEnSheetVentas(Sheet sheet1, List<Venta> ventas, CellStyle headerCellStyle) {
		String[] columnas = {"comprador", "fecha_venta", "precio_total_venta","ganancia_venta","costo_envio_venta"};
		// Create a Row
		Row headerRow = sheet1.createRow(0);
		
		int rowNum = 1;
        for(Venta unaVenta: ventas) {
            Row row = sheet1.createRow(rowNum++);

            row.createCell(0)
                .setCellValue(unaVenta.getCliente().getNombre() +", "+ unaVenta.getCliente().getApellido());

            row.createCell(1)
                .setCellValue(unaVenta.getFecha().toString());

            row.createCell(2)
                .setCellValue(unaVenta.getVenta_precioTotal());
            
            row.createCell(3)
            .setCellValue(unaVenta.getVenta_ganancia());
            
            row.createCell(4)
            .setCellValue(unaVenta.getEnvio());
        }
        
        // Resize all columns to fit the content size
        for(int i = 0; i < columnas.length; i++) {
            sheet1.autoSizeColumn(i);
        }
        
        // Create cells
        for(int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }
		
	}

	private static void insertarDatosEnSheetGastos(Sheet sheet4, List<Gasto> gastos, CellStyle headerCellStyle) {
		String[] columnas = {"fecha_gasto", "monto_gasto", "detalle_gasto"};
		// Create a Row
		Row headerRow = sheet4.createRow(0);
		
		int rowNum = 1;
        for(Gasto unGasto: gastos) {
            Row row = sheet4.createRow(rowNum++);

            row.createCell(0)
                .setCellValue(unGasto.getFecha().toString());

            row.createCell(1)
                .setCellValue(unGasto.getMonto());

            row.createCell(2)
                .setCellValue(unGasto.getDetalle());
        }
        
        // Resize all columns to fit the content size
        for(int i = 0; i < columnas.length; i++) {
            sheet4.autoSizeColumn(i);
        }
        
        // Create cells
        for(int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }
		
	}

	public static void insertarDatosEnSheetClientes(Sheet sheet, List<Cliente> clientes,CellStyle headerCellStyle) {
		String[] columnas = {"clie_nombre", "clie_apellido", "clie_dni", "clie_telefono","clie_email","dire_calle","dire_numero","dire_codPostal","clie_como_llego","clie_tipo"};
		Row headerRow = sheet.createRow(0);
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
        
        // Create cells
        for(int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
	}
	
}
