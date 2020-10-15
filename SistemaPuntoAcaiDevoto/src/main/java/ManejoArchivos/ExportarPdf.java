package ManejoArchivos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;


import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ElementListener;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import ConexionBD.ObtenerDatos;
import Ventas.Item;
import Ventas.Venta;

public class ExportarPdf {
	
	public static void main(String[]args) throws SQLException, IOException, DocumentException {
		
	}
	
	public void exportar(Venta unaVenta) throws DocumentException, SQLException, MalformedURLException, IOException {
	
	Document document = new Document();
	ObtenerDatos obtenerDatosBd = new ObtenerDatos();
	
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Remitos/"+unaVenta.getFechaEntrega().toString()+unaVenta.getDatosCliente().toUpperCase()+""+obtenerDatosBd.obtenerIdUltimaVentaIngresada()+".pdf"));
		document.open();
		//--------TABLA DE PRODUCTOS
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(105);
		table.setSpacingBefore(10f);
	
		
		float[] colWidth= {2f,1f,1f,1f,1f};
		table.setWidths(colWidth);
		//COLUMNA NOMBRE
		table.addCell(getCellConBorde("DESCRIPCION", PdfPCell.ALIGN_CENTER));
		table.addCell(getCellConBorde("CANT.TOTAL", PdfPCell.ALIGN_CENTER));
		table.addCell(getCellConBorde("PRECIO UNITARIO", PdfPCell.ALIGN_CENTER));
		table.addCell(getCellConBorde("PRECIO MAYORISTA", PdfPCell.ALIGN_CENTER));
		table.addCell(getCellConBorde("PRECIO TOTAL", PdfPCell.ALIGN_CENTER));
		
		
		
		
		agregarProductoATabla(table, unaVenta.getItems());


	
		FontSelector selector1 = new FontSelector();
		Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18);
		f1.setColor(BaseColor.BLACK);
		selector1.addFont(f1);

		//DETELLE PRODUCTOS

		
		PdfPTable precioTotal = new PdfPTable(1);
		precioTotal.setWidthPercentage(100);
	
		precioTotal.addCell(getCell("Costo Envio:     "+unaVenta.getPrecioEnvio().toString(), PdfPCell.ALIGN_RIGHT));
		precioTotal.addCell(getCell("Precio Total:     "+unaVenta.getVenta_precioTotal(), PdfPCell.ALIGN_RIGHT));
		precioTotal.addCell(getCell("\nTipo de Pago:     "+unaVenta.getTipoDePago(), PdfPCell.ALIGN_RIGHT));
		
		
		precioTotal.addCell(getCell(("PRESUPUESTO"), PdfPCell.ALIGN_LEFT));
		precioTotal.addCell(getCell(("\nEstos valores no incluyen IVA*"), PdfPCell.ALIGN_LEFT));

		
		//------------------
		
		//DETALLE VENDEDOR
		
		
		Paragraph titulo = new Paragraph(selector1.process("AÇAÍ M CABA \n"));
		titulo.setAlignment(Element.ALIGN_CENTER);
		document.add(titulo);
		
		PdfPTable table2 = new PdfPTable(2);
		 
		// Set First row as header
		table2.setHeaderRows(1);
		// Add header details
		table2.addCell(getCellConBorde("Remito Nro: "+obtenerDatosBd.obtenerIdUltimaVentaIngresada(), PdfPCell.ALIGN_CENTER));
		table2.addCell(getCellConBorde("Fecha: "+unaVenta.getFechaEntrega(), PdfPCell.ALIGN_CENTER));

		// Add the data
		
		Image image1 = Image
				.getInstance("acai.jpeg");
		image1.scaleAbsolute(50,50);
		table2.addCell(image1);
		table2.addCell(getCell("\n"+"Arregui 6600, Versalles, CABA" + "\n" +
		"Tel: 11 3165-8780" + "\n"+ "maracaibocaba@gmail.com", PdfPCell.ALIGN_LEFT));
		
		//------------------------
		
		
		// DATOS CLIENTE
		
		PdfPTable tableCliente = new PdfPTable(3);
		tableCliente.setWidthPercentage(100);
		tableCliente.addCell(getCell("Cliente: " + unaVenta.getDatosCliente().toUpperCase(), PdfPCell.ALIGN_LEFT));
		tableCliente.addCell(getCell("Contacto: " +unaVenta.getClienteContacto(), PdfPCell.ALIGN_CENTER));
		tableCliente.addCell(getCell("Direccion: "+unaVenta.getDireccionMayorista(), PdfPCell.ALIGN_RIGHT));
		tableCliente.addCell(getCell("", PdfPCell.ALIGN_LEFT));
		tableCliente.addCell(getCell("Observacion: "+unaVenta.getObservacion(), PdfPCell.ALIGN_CENTER));
		tableCliente.addCell(getCell("", PdfPCell.ALIGN_RIGHT));

		
		//--------------

		//CUADRO PRINCIPAL------------
		
		PdfPTable framePrincipal = new PdfPTable(1);
		framePrincipal.setWidthPercentage(105);
		framePrincipal.setSpacingBefore(11f);
		framePrincipal.setSpacingAfter(11f);
		
		
		PdfPCell primerCuadro =new PdfPCell(table2);
		primerCuadro.setFixedHeight(180);
		PdfPCell segundoCuadro =new PdfPCell(tableCliente);
		segundoCuadro.setFixedHeight(50);
		PdfPCell tercerCuadro = new PdfPCell(table);
		tercerCuadro.setFixedHeight(300);
		PdfPCell cuartoCuadro = new PdfPCell(precioTotal);
		cuartoCuadro.setFixedHeight(120);
		
		

		
		
		framePrincipal.addCell(primerCuadro);
		framePrincipal.addCell(segundoCuadro);
		framePrincipal.addCell(tercerCuadro);
		framePrincipal.addCell(cuartoCuadro);
		
		
		
		
		//-----------------
		
	
		document.add(framePrincipal);
		document.close();
		writer.close();
	}
	


	private static void agregarProductoATabla(PdfPTable table, java.util.List<Item> items) {
		
		for (Item item : items) {

			table.addCell(getCellConBorde(item.getNombreProducto(), PdfPCell.ALIGN_CENTER));
			table.addCell(getCellConBorde(""+item.getCantidad(), PdfPCell.ALIGN_CENTER));
			
			if(!item.esCompraMayorista()) {
				table.addCell(getCellConBorde(""+item.getPrecioUnitarioProducto(), PdfPCell.ALIGN_CENTER));
				table.addCell(getCellConBorde("-", PdfPCell.ALIGN_CENTER));
			}else {
				table.addCell(getCellConBorde("-", PdfPCell.ALIGN_CENTER));
				table.addCell(getCellConBorde(""+item.getPrecioMayoristaProducto(), PdfPCell.ALIGN_CENTER));
			}
			
			table.addCell(getCellConBorde(""+item.getPrecioFinal(), PdfPCell.ALIGN_CENTER));
		}

	}

	public static PdfPCell getCellConBorde(String text, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(text));
	    cell.setPadding(2);
	    cell.setHorizontalAlignment(alignment);
	    return cell;
	}
	public static PdfPCell getCell(String text, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(text));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    cell.setBorder(PdfPCell.NO_BORDER);
	    return cell;
	}
}
