package ManejoArchivos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabStop;
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
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(105);
		table.setSpacingBefore(11f);
		table.setSpacingAfter(11f);
		
		float[] colWidth= {2f,2f,2f};
		table.setWidths(colWidth);
		//COLUMNA NOMBRE
		PdfPCell c1 = new PdfPCell(new Paragraph("VentaNro"));
		PdfPCell c2 = new PdfPCell(new Paragraph("Cliente"));
		PdfPCell c3 = new PdfPCell(new Paragraph("Precio"));
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		
		//COLUMNA VALORES
		c1=new PdfPCell(new Paragraph("194242")); 
		c2=new PdfPCell(new Paragraph("ElDiegui")); 
		c3=new PdfPCell(new Paragraph("$5000")); 
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		
		c1=new PdfPCell(new Paragraph("194242")); 
		c2=new PdfPCell(new Paragraph("ElDiegui")); 
		c3=new PdfPCell(new Paragraph("$5000")); 
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		
		c1=new PdfPCell(new Paragraph("")); 
		c2=new PdfPCell(new Paragraph("")); 
		c3=new PdfPCell(new Paragraph("")); 
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		//------------------------------
		

		//DETELLA PRODUCTOS
		Paragraph detalleProducto = new Paragraph("Total: $34");
		//------------------
		
		//DETALLE CLIENTE

		//------------------------

		//CUADRO PRINCIPAL------------
		PdfPTable framePrincipal = new PdfPTable(1);
		framePrincipal.setWidthPercentage(105);
		framePrincipal.setSpacingBefore(11f);
		framePrincipal.setSpacingAfter(11f);
		PdfPCell primerCuadro =new PdfPCell(new Paragraph("ACAI DEVOTO"));
		primerCuadro.setFixedHeight(150);
		PdfPCell segundoCuadro = new PdfPCell(table);
		segundoCuadro.setFixedHeight(300);
		
		framePrincipal.addCell(primerCuadro);
		framePrincipal.addCell(segundoCuadro);
		framePrincipal.addCell(detalleProducto);
		//-----------------
		
	
		document.add(framePrincipal);
		document.close();
		writer.close();
	}
}
