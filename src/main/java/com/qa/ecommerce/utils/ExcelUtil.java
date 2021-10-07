package com.qa.ecommerce.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;

public class ExcelUtil {

	private static String TEST_DATA_PATH = "./src/main/java/com/qa/ecommerce/testdata/Registration.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object [][] getTestData(String sheetName) {
		Object data[][] = null;
		try {
			FileInputStream testDataFileStream = new FileInputStream(TEST_DATA_PATH);
			book = WorkbookFactory.create(testDataFileStream);
			sheet = book.getSheet(sheetName);
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int rowNumber = 0; rowNumber < sheet.getLastRowNum(); rowNumber++) {

				for (int columnNumber = 0; columnNumber < sheet.getRow(0).getLastCellNum(); columnNumber++) {

					data[rowNumber][columnNumber] = sheet.getRow(rowNumber + 1).getCell(columnNumber).toString();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
