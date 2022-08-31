package com.prodapt.app.onboardingwebserver.utility;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.prodapt.app.onboardingwebserver.entities.WelfareFund;

@Component
public class WelfareFundExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<WelfareFund> welfareFundList;
	private int rowCount = 0;

	public WelfareFundExcelExporter(List<WelfareFund> welfareFundList) {
		this.welfareFundList = welfareFundList;
		System.out.println(welfareFundList);
		workbook = new XSSFWorkbook();
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("WelfareFundDetails");
		Row row = sheet.createRow(rowCount++);
		int colCount = 0;

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GOLD.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.TOP);
		style.setAlignment(HorizontalAlignment.LEFT);
//		style.setWrapText(true);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setBold(true);

		style.setFont(font);

		createCell(row, colCount++, "Emp Code", style);
		createCell(row, colCount++, "Emp Name", style);
		createCell(row, colCount++, "Date of Joining", style);
		createCell(row, colCount++, "Prodapt Email ID", style);
		createCell(row, colCount++, "Welfare Fund Amount", style);

	}

	private void writeDataLines() {

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		Calendar calendar = Calendar.getInstance();

		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);

		XSSFFont font = workbook.createFont();
		font.setFontName("Arial");

		style.setFont(font);

		for (WelfareFund welfareFund : welfareFundList) {
			Row row = sheet.createRow(rowCount++);
			int colCount = 0;

			createCell(row, colCount++, welfareFund.getEmpCode(), style);
			createCell(row, colCount++, welfareFund.getEmpName(), style);
			calendar.setTimeInMillis(welfareFund.getDateOfJoining());
			createCell(row, colCount++, formatter.format(calendar.getTime()), style);
			createCell(row, colCount++, welfareFund.getProdaptEmail(), style);
			createCell(row, colCount++, welfareFund.getWelfareFundAmount(), style);
		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}
