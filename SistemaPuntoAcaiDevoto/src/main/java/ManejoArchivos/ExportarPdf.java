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

public class ExportarPdf {
	
	public static void main(String[]args) throws SQLException, IOException, DocumentException {
		exportar();
	}
	
	public static void exportar() throws FileNotFoundException, DocumentException {
	
	Document document = new Document();

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("prueba.pdf"));
		document.open();
		//--------TABLA DE PRODUCTOS
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(105);
		table.setSpacingBefore(11f);
		table.setSpacingAfter(11f);
		
		float[] colWidth= {1.5f,1.5f,1.5f,1.5f};
		table.setWidths(colWidth);
		//COLUMNA NOMBRE
		PdfPCell c1 = new PdfPCell(new Paragraph("DESCRIPCION"));
		PdfPCell c2 = new PdfPCell(new Paragraph("CANT.TOTAL"));
		PdfPCell c3 = new PdfPCell(new Paragraph("PRECIO UNITARIO"));
		PdfPCell c4 = new PdfPCell(new Paragraph("PRECIO TOTAL"));
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		table.addCell(c4);
		
		//COLUMNA VALORES
		c1=new PdfPCell(new Paragraph("Pote de Frutilla")); 
		c2=new PdfPCell(new Paragraph("1")); 
		c3=new PdfPCell(new Paragraph("$600")); 
		c4=new PdfPCell(new Paragraph("$600")); 
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		table.addCell(c4);
		
		c1=new PdfPCell(new Paragraph("Pote de Limon")); 
		c2=new PdfPCell(new Paragraph("2")); 
		c3=new PdfPCell(new Paragraph("$450")); 
		c4=new PdfPCell(new Paragraph("$900")); 
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		table.addCell(c4);
		
		
		c1=new PdfPCell(new Paragraph("")); 
		c2=new PdfPCell(new Paragraph("")); 
		c3=new PdfPCell(new Paragraph("")); 
		c4=new PdfPCell(new Paragraph("")); 
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		table.addCell(c4);
		//------------------------------
		

		//DETELLA PRODUCTOS
		PdfPTable precioTotal = new PdfPTable(1);
		precioTotal.setWidthPercentage(100);
		precioTotal.addCell(getCell("Total: ", PdfPCell.ALIGN_RIGHT));
		
		//------------------
		
		//DETALLE VENDEDOR
		FontSelector selector1 = new FontSelector();
		Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18);
		f1.setColor(BaseColor.BLACK);
		selector1.addFont(f1);
		
		Paragraph titulo = new Paragraph(selector1.process("ACAI DEVOTO \n"));
		titulo.setAlignment(Element.ALIGN_CENTER);
		document.add(titulo);
		
		
		
		PdfPTable table2 = new PdfPTable(3);
		table2.setWidthPercentage(100);
		table2.addCell(getCell("Fecha: ", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell("Remito Nro: ", PdfPCell.ALIGN_RIGHT));

		table2.addCell(getCell(" ", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
		
		table2.addCell(getCell("Direccion: ", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell("", PdfPCell.ALIGN_RIGHT));
		
		table2.addCell(getCell("Telefono:", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell("", PdfPCell.ALIGN_RIGHT));
		
		table2.addCell(getCell(" ", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
		
		table2.addCell(getCell(" ", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
		
		table2.addCell(getCell("Cliente: ", PdfPCell.ALIGN_LEFT));
		table2.addCell(getCell("Contacto: ", PdfPCell.ALIGN_CENTER));
		table2.addCell(getCell("", PdfPCell.ALIGN_RIGHT));


		
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
	
	public static PdfPCell getCell(String text, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(text));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    cell.setBorder(PdfPCell.NO_BORDER);
	    return cell;
	}
}
