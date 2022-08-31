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

import com.prodapt.app.onboardingwebserver.entities.Mediclaim;

@Component
public class MediclaimExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Mediclaim> mediclaims;
	private int rowCount = 0;

	public MediclaimExcelExporter(List<Mediclaim> mediclaims) {
		this.mediclaims = mediclaims;
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
		sheet = workbook.createSheet("Mediclaim");
		Row row = sheet.createRow(rowCount++);
		int colCount = 0;

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.TOP);
		style.setAlignment(HorizontalAlignment.LEFT);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setBold(true);

		style.setFont(font);

		createCell(row, colCount++, "Emp Code", style);
		createCell(row, colCount++, "Employee Name", style);
		createCell(row, colCount++, "Gender", style);
		createCell(row, colCount++, "Date of Birth", style);
		createCell(row, colCount++, "Date of Joining", style);
		createCell(row, colCount++, "Prodapt Email", style);
		createCell(row, colCount++, "Covid Vaccination Detail", style);

		createCell(row, colCount++, "Relation Name", style);
		createCell(row, colCount++, "Relation Type", style);
		createCell(row, colCount++, "Relation Gender", style);
		createCell(row, colCount++, "Relation DOB", style);
		createCell(row, colCount++, "Dependency", style);
	}

	private void writeDataLines() {

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		Calendar calendar = Calendar.getInstance();

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontName("Arial");
		style.setFont(font);

		for (Mediclaim mediclaim : mediclaims) {
			Row row = sheet.createRow(rowCount++);
			int colCount = 0;

			createCell(row, colCount++, mediclaim.getEmpCode(), style);
			createCell(row, colCount++, mediclaim.getName(), style);

			createCell(row, colCount++, mediclaim.getGender(), style);
			calendar.setTimeInMillis(mediclaim.getDateOfBirth());
			createCell(row, colCount++, formatter.format(calendar.getTime()), style);
			calendar.setTimeInMillis(mediclaim.getDateOfJoining());
			createCell(row, colCount++, formatter.format(calendar.getTime()), style);
			createCell(row, colCount++, mediclaim.getProdaptEmail(), style);
			createCell(row, colCount++, mediclaim.getCovidVaccination(), style);

			if (mediclaim.getRelationList() != null) {
				for (int i = 0; i < mediclaim.getRelationList().size(); i++) {
					if (i < 1) {
						createCell(row, colCount++, mediclaim.getRelationList().get(i).getName(), style);
						createCell(row, colCount++, mediclaim.getRelationList().get(i).getType(), style);
						createCell(row, colCount++, mediclaim.getRelationList().get(i).getGender(), style);
						calendar.setTimeInMillis(mediclaim.getRelationList().get(i).getDateOfBirth());
						createCell(row, colCount++, formatter.format(calendar.getTime()), style);
						createCell(row, colCount++, mediclaim.getRelationList().get(i).getIsDependant(), style);
					} else {
						Row nestedRow = sheet.createRow(rowCount++);
						colCount = 0;
						createCell(nestedRow, colCount++, mediclaim.getEmpCode(), style);
						createCell(nestedRow, colCount++, mediclaim.getName(), style);
						colCount = 7;
						createCell(nestedRow, colCount++, mediclaim.getRelationList().get(i).getName(), style);
						createCell(nestedRow, colCount++, mediclaim.getRelationList().get(i).getType(), style);
						createCell(nestedRow, colCount++, mediclaim.getRelationList().get(i).getGender(), style);
						calendar.setTimeInMillis(mediclaim.getRelationList().get(i).getDateOfBirth());
						createCell(nestedRow, colCount++, formatter.format(calendar.getTime()), style);
						createCell(nestedRow, colCount++, mediclaim.getRelationList().get(i).getIsDependant(), style);
					}
				}
			}

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
