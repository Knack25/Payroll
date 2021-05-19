package Gradle_Payroll.fileIO;



import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;


public class Excel_Out{
	
	static Workbook book;
	static Sheet sheet;
	static Row row;
	static Font normalF,underlineF;
	static CellStyle normalS,underlinedS,bottomB;
	
	//TODO: figure out how to set up custom margins, scaling, and print area
	public static void create(String sheetName,int numCol,int numRow) throws Exception {
		createBook();
		createSheet(sheetName);
		createCells(numCol, numRow);
		normalF = book.createFont();
		underlineF = book.createFont();
		normalS = book.createCellStyle();
		underlinedS = book.createCellStyle();
		book.setPrintArea(0, "$A$1:$M$53");
//		book.setPrintArea(
//		        1, //sheet index
//		        0, //start column
//		        12, //end column
//		        0, //start row
//		        53  //end row
//		);
		normalF.setFontHeightInPoints((short) 15);
		normalF.setFontName("ARIAL NARROW");
		underlineF.setFontHeightInPoints((short) 15);
		underlineF.setUnderline(Font.U_SINGLE);
		underlineF.setFontName("ARIAL NARROW");
		
	}
	
	private static void createBook() throws Exception{
		book = new HSSFWorkbook();
	}
	
	public static void createSheet(String sheetName) {
		sheet = book.createSheet(sheetName);
	}
	
	public static void createPrintArea(int numCol,int numRow) throws Exception{
		book.setPrintArea(0, 0, numCol, 0, numRow);
		sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
	}
	
	private static int createCells(int col,int row) {
		for(int i = 0; i < row; i++) {
			sheet.createRow(i);
			for(int j = 0;j<col;j++) {
				sheet.getRow(i).createCell(j);
			}
		}
		return 0;
	}
	
	//Overloads for managing cell inputs
	public static int writeToCell(int col,int row, String input) {
		sheet.getRow(row).getCell(col).setCellValue(input);
		return 0;
	}
	public static int writeToCell(int col,int row, LocalDate date) {
		sheet.getRow(row).getCell(col).setCellValue(date);
		return 0;
	}
	public static int writeToCell(int col,int row, int input) {
		sheet.getRow(row).getCell(col).setCellValue(input);
		return 0;
	}
	public static int writeToCell(int col,int row, boolean input) {
		sheet.getRow(row).getCell(col).setCellValue(input);
		return 0;
	}
	public static int writeToCell(int col,int row,double input) {
		sheet.getRow(row).getCell(col).setCellValue(input);
		return 0;
	}
	public static int changeNumberFormat(int col,int row) {
		CellStyle dollarStyle=book.createCellStyle();
		dollarStyle.setDataFormat((short) 0x2c);
		dollarStyle.setFont(normalF);
		dollarStyle.setLocked(true);
		sheet.getRow(row).getCell(col).setCellStyle(dollarStyle);
		return 0;
	}
	public static int formatRowHeight(int row,float height) {
		sheet.getRow(row).setHeightInPoints(height);
		return 0;
	}
	
	public static int formatColwidth(int col,int width) {
		sheet.setColumnWidth(col, width);
		return 0;
	}
	
	
	public static int clearCellFormat(int col,int row) {
		normalS.setFont(normalF);
		sheet.getRow(row).getCell(col).setCellStyle(normalS);
		return 0;
	}
	
	public static int underlineCell(int col,int row) {
		underlinedS.setFont(underlineF);
		sheet.getRow(row).getCell(col).setCellStyle(underlinedS);
		return 0;
	}
	
	public static int clearCellBorder(int col,int row) {
		sheet.getRow(row).getCell(col).getCellStyle().setBorderBottom(BorderStyle.NONE);
		return 0;
	}
	public static int setCellBorder(int col,int row) {
		CellStyle bottomB=book.createCellStyle();
		bottomB.setBorderBottom(BorderStyle.THIN);
		bottomB.setFont(normalF);
		bottomB.setLocked(true);
		sheet.getRow(row).getCell(col).setCellStyle(bottomB);
		
		return 0;
	}
	public static int setCellDataBorder(int col,int row,int i) {
		CellStyle bottomB=book.createCellStyle();
		bottomB.setBorderBottom(BorderStyle.THIN);
		bottomB.setFont(normalF);
		bottomB.setDataFormat((short) 0x2c);
		bottomB.setLocked(true);
		sheet.getRow(row).getCell(col).setCellStyle(bottomB);
		
		return 0;
	}
	
	
	public static int writeOut() throws Exception{
		try(OutputStream fileOut = new FileOutputStream("Output.xls")){
			book.write(fileOut);
			return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 1;
		}
	}
	//TODO: Add Method to lock excel sheet so you cant tamper with already saved data
		//in excel out, this is the line of code for it: CellStyle.setLocked(true);
}
