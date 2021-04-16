package com.Birst.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	XSSFWorkbook wb;

	public ExcelDataProvider() {

		File src = new File("./TestData/data.xlsx");

		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			System.out.println("Unable to read excel file" + e.getMessage());
		}
	}

	public String getData(String SheetName, int row, int column) {
		return wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();
	}

}
