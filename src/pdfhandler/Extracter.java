package pdfhandler;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

public class Extracter {

	private List<String> pdfFiles = new ArrayList<String>();
	
	public Extracter(List<String> pdfFiles){
		this.pdfFiles = pdfFiles;
	}
	
	/**
	 * Page with index 'extractPageNr' will be extracted from all pdf files.
	 * New pdf files containing 1 page will be written to the given output folder.
	 * 
	 * @param extractPageNr
	 */
	public void extractPageFromPdfFiles(int extractPageNr, String outputFolder){	
		try{
			for(String pdf : pdfFiles){
				int index = pdf.lastIndexOf("\\");
				String pdfName = pdf.substring(index+1, pdf.indexOf(".pdf"));
				System.out.println("Reading " + pdfName);
				PdfReader reader = new PdfReader(pdf);
				if(reader.getNumberOfPages() < extractPageNr){
					System.err.println("'" + pdfName + "' has not enough pages to extract. Skipped to next pdf!");
					continue;
				}
				String outFile = Statics.extractOutputFolder + pdfName + "-" + String.format("%03d", extractPageNr) + ".pdf";
	            System.out.println ("Writing page " + extractPageNr + " from '" + pdfName + "'");
	            Document document = new Document(reader.getPageSizeWithRotation(1));
	            PdfCopy writer = new PdfCopy(document, new FileOutputStream(outFile));
	            document.open();
	            PdfImportedPage page = writer.getImportedPage(reader, extractPageNr);
	            writer.addPage(page);
	            document.close();
	            writer.close();
	            System.out.println("");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}