package extracter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

public class Extracter {

	private static String inputFolder = "pdfs/"; //input folder
	private static String outputFolder = "extracted_pdfs/"; //output folder
	
	private static int extractPageNr = 2; //nr Of the page you wish to extract
	
	public static void main(String[] args){
		
		List<String> pdfFiles = extractPDFsFromFolder();
		
		try{
			for(String pdf : pdfFiles){
				System.out.println("Reading " + pdf);
				PdfReader reader = new PdfReader(inputFolder + pdf);
				System.out.println(inputFolder + pdf);
				if(reader.getNumberOfPages() < extractPageNr){
					System.out.println(pdf + " has not enough pages to extract. Skipped to next pdf!");
					continue;
				}
				String outFile = outputFolder + pdf.substring(0, pdf.indexOf(".pdf"))
	                + "-" + String.format("%03d", extractPageNr) + ".pdf";
	            System.out.println ("Writing " + outFile);
	            Document document = new Document(reader.getPageSizeWithRotation(1));
	            PdfCopy writer = new PdfCopy(document, new FileOutputStream(outFile));
	            document.open();
	            PdfImportedPage page = writer.getImportedPage(reader, extractPageNr);
	            writer.addPage(page);
	            document.close();
	            writer.close();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Returns a list containing all names of the pdf files in the given directory.
	 * 
	 * @return
	 */
	private static List<String> extractPDFsFromFolder() {
		File folder = new File(inputFolder);
		List<String> pdfFiles = new ArrayList<String>();
		
		for(final File fileEntry : folder.listFiles()){
			pdfFiles.add(fileEntry.getName());
		}
		return pdfFiles;
	}
}