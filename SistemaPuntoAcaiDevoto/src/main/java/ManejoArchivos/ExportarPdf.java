package ManejoArchivos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
		//exportar();
	}
	
	public static void exportar(Venta unaVenta) throws FileNotFoundException, DocumentException, SQLException {
	
	Document document = new Document();
	ObtenerDatos obtenerDatosBd = new ObtenerDatos();
	
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(""+unaVenta.getDatosCliente().toUpperCase()+""+obtenerDatosBd.obtenerIdUltimaVentaIngresada()+".pdf"));
		document.open();
		//--------TABLA DE PRODUCTOS
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(105);
		table.setSpacingBefore(11f);
		table.setSpacingAfter(11f);
		
		float[] colWidth= {2f,1f,1f,1f,1f};
		table.setWidths(colWidth);
		//COLUMNA NOMBRE
		PdfPCell c1 = new PdfPCell(new Paragraph("DESCRIPCION"));
		PdfPCell c2 = new PdfPCell(new Paragraph("CANT.TOTAL"));
		PdfPCell c3 = new PdfPCell(new Paragraph("PRECIO UNITARIO"));
		PdfPCell c4 = new PdfPCell(new Paragraph("PRECIO MAYORISTA"));
		PdfPCell c5 = new PdfPCell(new Paragraph("PRECIO TOTAL"));
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		table.addCell(c4);
		table.addCell(c5);
		
		
		
		//FILA VALORES
		agregarProductoATabla(c1,c2,c3,c4,c5,table, unaVenta.getItems());

		
		
		c1=new PdfPCell(new Paragraph("")); 
		c2=new PdfPCell(new Paragraph("")); 
		c3=new PdfPCell(new Paragraph("")); 
		c4=new PdfPCell(new Paragraph("")); 
		c5=new PdfPCell(new Paragraph("")); 
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		table.addCell(c4);
		table.addCell(c5);
		//------------------------------
		

		//DETELLA PRODUCTOS
		PdfPTable precioTotal = new PdfPTable(1);
		precioTotal.setWidthPercentage(100);
		precioTotal.addCell(getCell("Costo Envio: "+unaVenta.getPrecioEnvio(), PdfPCell.ALIGN_RIGHT));
		precioTotal.addCell(getCell("Precio Total: "+unaVenta.getPrecioTotal(), PdfPCell.ALIGN_RIGHT));
		
		//------------------
		
		//DETALLE VENDEDOR
		FontSelector selector1 = new FontSelector();
		Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18);
		f1.setColor(BaseColor.BLACK);
		selector1.addFont(f1);
		
		Paragraph titulo = new Paragraph(selector1.process("AÇAI MARACAIBO CABA \n"));
		titulo.setAlignment(Element.ALIGN_CENTER);
		document.add(titulo);
		
		
		
		PdfPTable table2 = new PdfPTable(3);
		table2.setWidthPercentage(100);
		table2.addCell(getCell("Fecha: "+unaVenta.getFechaEntrega().toString(), PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell("Remito Nro: "+obtenerDatosBd.obtenerIdUltimaVentaIngresada(), PdfPCell.ALIGN_RIGHT));

		table2.addCell(getCell(" ", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
		
		table2.addCell(getCell("", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell("", PdfPCell.ALIGN_RIGHT));
		
		table2.addCell(getCell("", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell("", PdfPCell.ALIGN_RIGHT));
		
		table2.addCell(getCell(" ", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
		
		table2.addCell(getCell(" ", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
		
		table2.addCell(getCell(" ", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
		
		
		table2.addCell(getCell("Cliente: " + unaVenta.getDatosCliente().toUpperCase(), PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell("Contacto: " +unaVenta.getClienteContacto(), PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell("Direccion: "+unaVenta.getDireccionCliente(), PdfPCell.ALIGN_RIGHT));


		
		//------------------------

		//CUADRO PRINCIPAL------------
		
		PdfPTable framePrincipal = new PdfPTable(1);
		framePrincipal.setWidthPercentage(105);
		framePrincipal.setSpacingBefore(11f);
		framePrincipal.setSpacingAfter(11f);
		
		
		PdfPCell primerCuadro =new PdfPCell(table2);
		primerCuadro.setFixedHeight(100);
		PdfPCell segundoCuadro = new PdfPCell(table);
		segundoCuadro.setFixedHeight(300);
		PdfPCell tercerCuadro = new PdfPCell(precioTotal);
		tercerCuadro.setFixedHeight(80);
		
		

		
		
		framePrincipal.addCell(primerCuadro);
		framePrincipal.addCell(segundoCuadro);
		framePrincipal.addCell(tercerCuadro);
		
		
		
		
		//-----------------
		
	
		document.add(framePrincipal);
		document.close();
		writer.close();
	}
	
	private static void agregarProductoATabla(PdfPCell c1, PdfPCell c2, PdfPCell c3, PdfPCell c4, PdfPCell c5, PdfPTable table, java.util.List<Item> items) {
		
		for (Item item : items) {
			c1=new PdfPCell(new Paragraph(""+item.getNombreProducto())); 
			c2=new PdfPCell(new Paragraph(""+item.getCantidad())); 
			if(!item.esCompraMayorista()) {
				c3=new PdfPCell(new Paragraph(""+item.getPrecioUnitarioProducto())); 
				c4=new PdfPCell(new Paragraph("-")); 
				
			}else {
				c3=new PdfPCell(new Paragraph("-")); 
				c4=new PdfPCell(new Paragraph(""+item.getPrecioMayoristaProducto())); 
			}
			c5=new PdfPCell(new Paragraph(""+item.getPrecioFinal()));
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			table.addCell(c4);
			table.addCell(c5);
		}

	}

	public static PdfPCell getCell(String text, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(text));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    cell.setBorder(PdfPCell.NO_BORDER);
	    return cell;
	}
}
