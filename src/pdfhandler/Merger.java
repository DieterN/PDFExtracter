package pdfhandler;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

public class Merger {

	private List<String> pdfFiles = new ArrayList<String>();
	
	public Merger(List<String> pdfFiles){
		this.pdfFiles = pdfFiles;
	}
	
	/**
	 * Page with index 'extractPageNr' will be extracted from all pdf files.
	 * New pdf files containing 1 page will be written to the given output folder.
	 * 
	 * @param extractPageNr
	 */
	public void mergesPagesFromPdfFiles(Integer[] mergePageNrs, String outputFolder){	
		try{
			Document document = new Document();
            PdfCopy writer = new PdfCopy(document, new FileOutputStream(outputFolder + "merged.pdf"));
            writer.setMergeFields();
            document.open();
			
			for(String pdf : pdfFiles){
				int index = pdf.lastIndexOf("\\");
				String pdfName = pdf.substring(index+1, pdf.indexOf(".pdf"));
				System.out.println("Reading " + pdfName);
				PdfReader reader = new PdfReader(pdf);
//				if(reader.getNumberOfPages() < mergePageNr){
//					System.err.println("'" + pdfName + "' has not enough pages to merge. Skipped to next pdf!");
//					continue;
//				}
	            writer.addDocument(reader, Arrays.asList(mergePageNrs));
			}
			System.out.println ("Writing merged pdf");
			System.out.println("");
            document.close();
            writer.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
