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

import com.prodapt.app.onboardingwebserver.entities.EPFKYCDetails;

@Component
public class EPFKYCExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<EPFKYCDetails> epfkycList;
	private int rowCount = 0;

	public EPFKYCExcelExporter(List<EPFKYCDetails> epfkycList) {
		this.epfkycList = epfkycList;
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
		sheet = workbook.createSheet("EPF-KYC (Form 11)");
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
		createCell(row, colCount++, "UAN (If already a member of EPF)", style);
		createCell(row, colCount++, "Employee Name", style);
		createCell(row, colCount++, "Date of Birth", style);
		createCell(row, colCount++, "Date of Joining", style);
		createCell(row, colCount++, "Gender", style);
		createCell(row, colCount++, "Father/Spouse Name", style);
		createCell(row, colCount++, "Relationship", style);
		createCell(row, colCount++, "Mobile", style);
		createCell(row, colCount++, "Email", style);
		createCell(row, colCount++, "Qualification", style);
		createCell(row, colCount++, "Maritial Status", style);
		createCell(row, colCount++, "Is International Worker", style);
		createCell(row, colCount++, "Country of Origin", style);
		createCell(row, colCount++, "Passport Number", style);
		createCell(row, colCount++, "Passport Valid From", style);
		createCell(row, colCount++, "Passport Valid To", style);
		createCell(row, colCount++, "Is Physically Handicapped", style);
		createCell(row, colCount++, "Disabled Category", style);
		createCell(row, colCount++, "Memeber of EPF", style);
		createCell(row, colCount++, "Memeber of EPS", style);
		createCell(row, colCount++, "Region Code", style);
		createCell(row, colCount++, "Office Code", style);
		createCell(row, colCount++, "Establishment ID", style);
		createCell(row, colCount++, "Extension", style);
		createCell(row, colCount++, "Account Number", style);
		createCell(row, colCount++, "Date of Exit", style);
		createCell(row, colCount++, "Date of Joining", style);
		createCell(row, colCount++, "Scheme Certificate Number", style);
		createCell(row, colCount++, "PPO Number ", style);

		createCell(row, colCount++, "Doc Name", style);
		createCell(row, colCount++, "Name as per Doc ", style);
		createCell(row, colCount++, "Doc ID Number", style);
		createCell(row, colCount++, "Remarks", style);

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

		for (EPFKYCDetails epckyc : epfkycList) {
			Row row = sheet.createRow(rowCount++);
			int colCount = 0;

			createCell(row, colCount++, epckyc.getEmpCode(), style);
			createCell(row, colCount++, epckyc.getuan(), style);
			createCell(row, colCount++, epckyc.getName(), style);

			calendar.setTimeInMillis(epckyc.getDateOfBirth());
			createCell(row, colCount++, formatter.format(calendar.getTime()), style);
			calendar.setTimeInMillis(epckyc.getDateOfJoining());
			createCell(row, colCount++, formatter.format(calendar.getTime()), style);

			createCell(row, colCount++, epckyc.getGender(), style);
			createCell(row, colCount++, epckyc.getFatherOrSpouseName(), style);
			createCell(row, colCount++, epckyc.getRelationship(), style);
			createCell(row, colCount++, epckyc.getMobile(), style);
			createCell(row, colCount++, epckyc.getEmail(), style);

			createCell(row, colCount++, epckyc.getEduQualifications(), style);
			createCell(row, colCount++, epckyc.getMaritialStatus(), style);
			createCell(row, colCount++, epckyc.getIsInternationWorker(), style);
			createCell(row, colCount++, epckyc.getCountryOforigin(), style);
			createCell(row, colCount++, epckyc.getPassportNumber(), style);

			calendar.setTimeInMillis(epckyc.getValidFrom());
			createCell(row, colCount++, formatter.format(calendar.getTime()), style);
			calendar.setTimeInMillis(epckyc.getValidTo());
			createCell(row, colCount++, formatter.format(calendar.getTime()), style);

			createCell(row, colCount++, epckyc.getSpeciallyabled(), style);
			createCell(row, colCount++, epckyc.getDisabledCategory(), style);
			createCell(row, colCount++, epckyc.getMemberOfEPF(), style);
			createCell(row, colCount++, epckyc.getMemberOfEPS(), style);
			createCell(row, colCount++, epckyc.getRegionCode(), style);
			createCell(row, colCount++, epckyc.getOfficeCode(), style);
			createCell(row, colCount++, epckyc.getEstablishmentId(), style);
			createCell(row, colCount++, epckyc.getExtension(), style);
			createCell(row, colCount++, epckyc.getAccountNumber(), style);

			calendar.setTimeInMillis(epckyc.getDateOfExit());
			createCell(row, colCount++, formatter.format(calendar.getTime()), style);
			calendar.setTimeInMillis(epckyc.getDateOfJoiningPrev());
			createCell(row, colCount++, formatter.format(calendar.getTime()), style);

			createCell(row, colCount++, epckyc.getSchemeCertificateNumber(), style);
			createCell(row, colCount++, epckyc.getPpoNumber(), style);

			if (epckyc.getKycDetailList() != null)
				for (int i = 0; i < epckyc.getKycDetailList().size(); i++) {

					if (i < 1) {
						createCell(row, colCount++, epckyc.getKycDetailList().get(i).getSelectKYC(), style);
						createCell(row, colCount++, epckyc.getKycDetailList().get(i).getNameAsPerDoc(), style);
						createCell(row, colCount++, epckyc.getKycDetailList().get(i).getDocNumber(), style);
						createCell(row, colCount++, epckyc.getKycDetailList().get(i).getRemarks(), style);
					} else {
						Row nestedRow = sheet.createRow(rowCount++);
						colCount = 0;
						createCell(nestedRow, colCount++, epckyc.getEmpCode(), style);
						colCount = 2;
						createCell(nestedRow, colCount++, epckyc.getName(), style);
						colCount = 30;
						createCell(nestedRow, colCount++, epckyc.getKycDetailList().get(i).getSelectKYC(), style);
						createCell(nestedRow, colCount++, epckyc.getKycDetailList().get(i).getNameAsPerDoc(), style);
						createCell(nestedRow, colCount++, epckyc.getKycDetailList().get(i).getDocNumber(), style);
						createCell(nestedRow, colCount++, epckyc.getKycDetailList().get(i).getRemarks(), style);
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
