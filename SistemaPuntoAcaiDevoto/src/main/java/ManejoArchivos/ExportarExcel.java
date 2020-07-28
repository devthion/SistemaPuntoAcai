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
import Gastos.GastosDiarios;
import Gastos.GastosGenerales;
import Gastos.GastosProductos;
import ModeloInversion.Inversion;
import ModelosClientes.Cliente;
import Productos.Producto;
import Propina.Propina;
import Ventas.CajaCerrada;
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
		List<GastosProductos> gastosProductos=obtenerDatos.obtenerGastosProductos();
		List<GastosDiarios> gastosDiarios = obtenerDatos.obtenerGastosDiarios();
		List<GastosGenerales> gastosGenerales =obtenerDatos.obtenerGastosGenerales();
		List<CajaCerrada> cajasCerradas = obtenerDatos.obtenerCajasCerradas();
		List<Propina> propinas = obtenerDatos.obtenerPropinas();
		List<Inversion> inversiones = obtenerDatos.obtenerInversiones();
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
        Sheet sheet4 = workbook.createSheet("GastosGenerales");
        Sheet sheet5 = workbook.createSheet("GastosDiarios");
        Sheet sheet6 = workbook.createSheet("GastosProductos");
        Sheet sheet7 = workbook.createSheet("CajasCerradas");
        Sheet sheet8 = workbook.createSheet("Propinas");
        Sheet sheet9 = workbook.createSheet("Inversiones");
        
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
        insertarDatosEnSheetProductos(sheet3, productos, headerCellStyle);
        insertarDatosEnSheetItemsVenta(sheet2, itemsVenta, headerCellStyle);
        insertarDatosEnSheetGastosGenerales(sheet4, gastosGenerales, headerCellStyle);
        insertarDatosEnSheetGastosDiarios(sheet5,gastosDiarios,headerCellStyle);
        insertarDatosEnSheetGastosProductos(sheet6, gastosProductos, headerCellStyle);
        insertarDatosEnSheetCajasCerradas(sheet7, cajasCerradas, headerCellStyle);
        insertarDatosEnPropinas(sheet8, propinas, headerCellStyle);
        insertarDatosEnInversiones(sheet9, inversiones, headerCellStyle);
        // Create Other rows and cells with  data
        
        
        
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("data.xlsx");
        
        workbook.write(fileOut);
        System.out.println("Excel exportado");
        fileOut.close();

        // Closing the workbook
        workbook.close();


	}
	
	private static void insertarDatosEnInversiones(Sheet sheet9, List<Inversion> inversiones, CellStyle headerCellStyle) {
		String[] columnas = {"fecha", "detalle", "monto"};
		// Create a Row
		Row headerRow = sheet9.createRow(0);
		
		int rowNum = 1;
        for(Inversion unaInversion: inversiones) {
            Row row = sheet9.createRow(rowNum++);

            row.createCell(0)
                .setCellValue(unaInversion.getFecha().toString());

            row.createCell(1)
                .setCellValue(unaInversion.getDetalle());

            row.createCell(2)
            	.setCellValue(unaInversion.getMonto());

        }
        
        // Resize all columns to fit the content size
        for(int i = 0; i < columnas.length; i++) {
            sheet9.autoSizeColumn(i);
        }
        
        // Create cells
        for(int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }
		
	}
	
	private static void insertarDatosEnPropinas(Sheet sheet8, List<Propina> propinas, CellStyle headerCellStyle) {
		String[] columnas = {"fecha", "monto_real"};
		// Create a Row
		Row headerRow = sheet8.createRow(0);
		
		int rowNum = 1;
        for(Propina unaPropina: propinas) {
            Row row = sheet8.createRow(rowNum++);

            row.createCell(0)
                .setCellValue(unaPropina.getFecha().toString());

            row.createCell(1)
                .setCellValue(unaPropina.getMonto());


        }
        
        // Resize all columns to fit the content size
        for(int i = 0; i < columnas.length; i++) {
            sheet8.autoSizeColumn(i);
        }
        
        // Create cells
        for(int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }
		
	}
	
	
	
	private static void insertarDatosEnSheetCajasCerradas(Sheet sheet7, List<CajaCerrada> cajas, CellStyle headerCellStyle) {
		String[] columnas = {"fecha", "monto_real", "monto_ideal"};
		// Create a Row
		Row headerRow = sheet7.createRow(0);
		
		int rowNum = 1;
        for(CajaCerrada unaCajaCerrada: cajas) {
            Row row = sheet7.createRow(rowNum++);

            row.createCell(0)
                .setCellValue(unaCajaCerrada.getFecha().toString());

            row.createCell(1)
                .setCellValue(unaCajaCerrada.getMonto_real());
            
            row.createCell(2)
            	.setCellValue(unaCajaCerrada.getMonto_ideal());

        }
        
        // Resize all columns to fit the content size
        for(int i = 0; i < columnas.length; i++) {
            sheet7.autoSizeColumn(i);
        }
        
        // Create cells
        for(int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }
		
	}
	
	private static void insertarDatosEnSheetGastosProductos(Sheet sheet6, List<GastosProductos> gastos, CellStyle headerCellStyle) {
		String[] columnas = {"detalle", "monto"};
		// Create a Row
		Row headerRow = sheet6.createRow(0);
		
		int rowNum = 1;
        for(GastosProductos unGasto: gastos) {
            Row row = sheet6.createRow(rowNum++);

            row.createCell(0)
                .setCellValue(unGasto.getDetalle());

            row.createCell(1)
                .setCellValue(unGasto.getMonto());

        }
        
        // Resize all columns to fit the content size
        for(int i = 0; i < columnas.length; i++) {
            sheet6.autoSizeColumn(i);
        }
        
        // Create cells
        for(int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }
		
	}
	
	private static void insertarDatosEnSheetGastosDiarios(Sheet sheet5, List<GastosDiarios> gastos, CellStyle headerCellStyle) {
		String[] columnas = {"detalle", "monto"};
		// Create a Row
		Row headerRow = sheet5.createRow(0);
		
		int rowNum = 1;
        for(GastosDiarios unGasto: gastos) {
            Row row = sheet5.createRow(rowNum++);

            row.createCell(0)
                .setCellValue(unGasto.getDetalle());

            row.createCell(1)
                .setCellValue(unGasto.getMonto());

        }
        
        // Resize all columns to fit the content size
        for(int i = 0; i < columnas.length; i++) {
            sheet5.autoSizeColumn(i);
        }
        
        // Create cells
        for(int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }
		
	}
	
	private static void insertarDatosEnSheetGastosGenerales(Sheet sheet4, List<GastosGenerales> gastos, CellStyle headerCellStyle) {
		String[] columnas = {"detalle", "monto"};
		// Create a Row
		Row headerRow = sheet4.createRow(0);
		
		int rowNum = 1;
        for(GastosGenerales unGasto: gastos) {
            Row row = sheet4.createRow(rowNum++);

            row.createCell(0)
                .setCellValue(unGasto.getDetalle());

            row.createCell(1)
                .setCellValue(unGasto.getMonto());

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
		String[] columnas = {"comprador", "fecha_venta", "precio_total_venta","ganancia_venta","costo_envio_venta","estado_envio","horario_entrega_envio","fecha_entrega_envio"};
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
            .setCellValue(unaVenta.getEnvioPrecio());
            
            row.createCell(5)
            .setCellValue(""+unaVenta.getEstado());
            
            row.createCell(6)
            .setCellValue(unaVenta.getHorario());
            
            row.createCell(7)
            .setCellValue(unaVenta.getFechaEntrega().toString());
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


	public static void insertarDatosEnSheetClientes(Sheet sheet, List<Cliente> clientes,CellStyle headerCellStyle) {
		String[] columnas = {"clie_dni","clie_nombre","clie_apellido","clie_telefono","clie_email","dire_calle","dire_numero","dire_barrio","dire_codPostal","clie_tipo","clie_como_llego","clie_rubro"};
		Row headerRow = sheet.createRow(0);
		int rowNum = 1;
        for(Cliente unCliente: clientes) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                .setCellValue(unCliente.getDni());

            row.createCell(1)
                .setCellValue(unCliente.getNombre());

            row.createCell(2)
                .setCellValue(unCliente.getApellido());
            
            row.createCell(3)
            	.setCellValue(unCliente.getTelefono());
            
            row.createCell(4)
            	.setCellValue(unCliente.getEmail());
            
            row.createCell(5)
            	.setCellValue(unCliente.getCalle());
            
            row.createCell(6)
            	.setCellValue(unCliente.getNumero());
            
            row.createCell(7)
            	.setCellValue(unCliente.getBarrio());
            
            row.createCell(8)
            	.setCellValue(unCliente.getCodPostal());
            
            row.createCell(9)
            	.setCellValue(unCliente.getTipo());
            
            row.createCell(10)
        	.setCellValue(unCliente.getComoLlego());
            
            row.createCell(11)
        	.setCellValue(unCliente.getRubro());
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
