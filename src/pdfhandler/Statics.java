package pdfhandler;

import java.util.List;

public class Statics {

	public static String inputFolder = "pdfs/"; //input folder
	public static String extractOutputFolder = "extracted_pdfs/"; //extract output folder
	public static String mergeOutputFolder = "merged_pdfs/"; //merged output folder
	
	public static int extractPageNr = 5; //nr of the page you wish to extract
	public static Integer[] mergePageNrs = {5}; //nrs of the pages you wish to merge
	
	public static boolean merge = true;
	public static boolean extract = true;
	
}
