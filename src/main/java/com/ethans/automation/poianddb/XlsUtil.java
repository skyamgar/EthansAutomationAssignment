package com.ethans.automation.poianddb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsUtil {
	Employee emp;
	static XSSFRow row;
	HashMap<String, Employee> map = new HashMap();

	public void readData() {

	}

	public void writeData() {

	}

	public HashMap<String, Employee> readDataSheetAsMap() {

		FileInputStream fis1, fis2;
		try {
			System.out.println("Reading Data from source...");
			fis1 = new FileInputStream(new File(".//Config.properties"));
			Properties prop = new Properties();
			prop.load(fis1);
			fis2 = new FileInputStream(prop.getProperty("sheet_to_read"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis2);
			XSSFSheet spreadsheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = spreadsheet.iterator();
			XSSFRow header = (XSSFRow) rowIterator.next();
			System.out.println("Storing Data in Map....");
			while (rowIterator.hasNext()) {
				row = (XSSFRow) rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				emp = new Employee();
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					
					switch (header.getCell(cell.getColumnIndex()).getStringCellValue().trim()) {
					case "EMP ID":
						emp.setId(cell.getStringCellValue().trim());
						break;
					case "EMP NAME":
						emp.setName(cell.getStringCellValue().trim());
						break;
					case "Role":
						emp.setRole(cell.getStringCellValue().trim());
						break;
					case "Age":
						emp.setAge(cell.getStringCellValue().trim());
						break;
					case "Gender":
						emp.setGender(cell.getStringCellValue().trim());
						break;
					default:
						break;

					}

				}
				
				map.put(emp.getId(), emp);

			}
			System.out.println("Reading Data from source and storing in map completed successfully");
			fis1.close();
			fis2.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return map;
	}
}
