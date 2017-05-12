package edu.neu.webtool.view;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

public class MyView extends AbstractPdfView
{
	
	private String paragraph;
	
	
	
	public MyView(String paragraph) {
		super();
		this.paragraph = paragraph;
	}



	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter pdfwriter, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Font  helvetica_18_blue = new Font(Font.HELVETICA, 18, Font.BOLDITALIC, Color.BLUE);
		Paragraph title = new Paragraph("Thank you for buying ticket at our website!", helvetica_18_blue);		
		Phrase firstPhrase = new Phrase("Below is your information. \n");
		Phrase secondPhrase = new Phrase(paragraph);
		pdfdoc.add(title);
		pdfdoc.add(firstPhrase);
		pdfdoc.add(secondPhrase);
	}
}
