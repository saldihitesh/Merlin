package com.functionalComponents;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestDataDriver {

	static Sheet wrksheet;
	static Workbook wrkbook = null;
	static Hashtable <String , Integer> dictCol = new Hashtable <String , Integer >();
	static Hashtable <String , Integer> dictRow = new Hashtable<String , Integer >();

	// fetches data based on the excelPath and sheetName
	public void customizedTestDataDriver(String excelSheetPath, String sheetName)
			throws BiffException, IOException {
		// initialize
		wrkbook = Workbook.getWorkbook(new File(excelSheetPath));
		// get sheet name from the excel file
		wrksheet = wrkbook.getSheet(sheetName);
	}

	// Returns the Number of Rows
	public static int RowCount() {
		return wrksheet.getRows();
	}

	// Returns the Cell value by taking row and Column values as argument
	public String ReadCell(int column, int row) {
		return wrksheet.getCell(column, row).getContents();
	}

	// Create Column Dictionary to hold all the Column Names
	public void ColumnDictionary() {
		// Iterate through all the columns in the Excel sheet and store the
		// value in Hashtable
		for (int col = 0; col < wrksheet.getColumns(); col++) {
			dictCol.put(ReadCell(col, 0), col);
		}
	}

	// Read Column Names
	public int GetCol(String colName) {
		try {
			int value;
			value = ((Integer) dictCol.get(colName)).intValue();
			return value;
		} catch (NullPointerException e) {
			return (0);

		}
	}

	// Create Row Dictionary to hold all the Row Names
	public void RowDictionary() {
		// Iterate through all the Rows in the Excel sheet and store the value
		// in Hashtable
		for (int row = 0; row < wrksheet.getRows(); row++) {
			dictRow.put(ReadCell(0, row), row);
		}
	}

	// Read Row Names
	public int GetRow(String rowName) {
		try {
			int value;
			value = ((Integer) dictRow.get(rowName)).intValue();
			return value;
		} catch (NullPointerException e) {
			return (0);
		}
	}

}
