package pdfParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfParser {
	
	private String Text;   //pdf���� �Ľ̵� ������ ���ڿ��� ����
	private String filePath;
	private File file;
	
	private PDFParser parser;
	private PDFTextStripper pdfStripper;
	private PDDocument pdDoc;
	private COSDocument cosDoc;
	
	public void PdfFileParser( String pdffilePath, String filename ) throws FileNotFoundException, IOException {
		
		file = new File(pdffilePath);
		parser = new PDFParser( new RandomAccessFile(file, "r") );
		parser.parse();
		cosDoc = parser.getDocument();
		pdfStripper = new PDFTextStripper();
		pdDoc = new PDDocument(cosDoc);
		pdDoc.getNumberOfPages();
		pdfStripper.setStartPage(1);
		
		pdfStripper.setEndPage(pdDoc.getNumberOfPages());
		
		Text = pdfStripper.getText(pdDoc);
		
		File file = new File("������ ������ ���¿� ���");
		FileWriter writer = null;
		
		try {
			writer = new FileWriter( file, true );
			writer.write(Text);
			writer.flush();
			
			System.out.println("Done");
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String filepath = "PDF������ �ִ� ���";
		File dir = new File(filepath);
		File[] fList = dir.listFiles();
		
		for ( File file : fList ) {
			new PdfParser().PdfFileParser(filepath, file.getName());
		}

	}

}
