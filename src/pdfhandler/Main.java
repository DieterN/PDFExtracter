package pdfhandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

public static void main(String[] args){
		
		List<String> pdfFiles = extractPDFsFromFolder(Statics.inputFolder);
		
		if(Statics.merge){
			Merger merger = new Merger(pdfFiles);
			merger.mergesPagesFromPdfFiles(Statics.mergePageNrs, Statics.mergeOutputFolder);
		}
		
		if(Statics.extract){
			Extracter extracter = new Extracter(pdfFiles);
			extracter.extractPageFromPdfFiles(Statics.extractPageNr, Statics.extractOutputFolder);
		}
	}

	/**
	 * Returns a list containing all names of the pdf files in the given directory.
	 * 
	 * @return
	 */
	private static List<String> extractPDFsFromFolder(String folderName) {
		File folder = new File(folderName);
		List<String> pdfFiles = new ArrayList<String>();
		
		for(final File fileEntry : folder.listFiles()){
			if(fileEntry.isDirectory()){
				pdfFiles.addAll(extractPDFsFromFolder(fileEntry.getAbsolutePath()));
			}
			else{
				pdfFiles.add(fileEntry.getAbsolutePath());
			}
		}
		return pdfFiles;
	}

}
