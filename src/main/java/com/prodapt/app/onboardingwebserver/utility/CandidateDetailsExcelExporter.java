package com.prodapt.app.onboardingwebserver.utility;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
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

import com.prodapt.app.onboardingwebserver.entities.CandidateDetails;

@Component
public class CandidateDetailsExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<CandidateDetails> candidateDetailsList;
	private int rowCount = 0;

	public CandidateDetailsExcelExporter(List<CandidateDetails> candidateDetailsList) {
		this.candidateDetailsList = candidateDetailsList;
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
		sheet = workbook.createSheet("Candidate Details");
		Row row = sheet.createRow(rowCount);
		int colCount = 0;

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setVerticalAlignment(VerticalAlignment.TOP);
		style.setAlignment(HorizontalAlignment.LEFT);
//		style.setWrapText(true);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setBold(true);

		style.setFont(font);

		createCell(row, colCount++, "Emp Code", style);
		createCell(row, colCount++, "First Name", style);
		createCell(row, colCount++, "Last Name", style);
		createCell(row, colCount++, "Date of Birth", style);
		createCell(row, colCount++, "Date of Joining", style);
		createCell(row, colCount++, "Place of Birth", style);
		createCell(row, colCount++, "Mobile Number", style);
		createCell(row, colCount++, "Gender", style);
		createCell(row, colCount++, "Personal email", style);
		createCell(row, colCount++, "Nationality", style);
		createCell(row, colCount++, "State of Domicile", style);
		// createCell(row, colCount++, "Religion", style);
		createCell(row, colCount++, "Maritial Status", style);
		createCell(row, colCount++, "Father's Name", style);

		// Emergency Contact
		createCell(row, colCount++, "Emg Contact Name", style);
		createCell(row, colCount++, "Emg Contact Number", style);
		createCell(row, colCount++, "Emg Contact Address", style);
		createCell(row, colCount++, "Emg Contact Email", style);
		createCell(row, colCount++, "Relationship", style);

		// Social Media Link
		createCell(row, colCount++, "Social Media Link", style);

		// Identification Marks & Hobbies
//		createCell(row, colCount++, "Identification Marks", style);
//		createCell(row, colCount++, "Hobbies", style);

		// Address
		createCell(row, colCount++, "Address Type", style);
		createCell(row, colCount++, "Address Line 1", style);
		createCell(row, colCount++, "Address Line 2", style);
		createCell(row, colCount++, "City", style);
		createCell(row, colCount++, "State", style);
		createCell(row, colCount++, "Country", style);
		createCell(row, colCount++, "Zip/Postal Code", style);

		// Education Details
		createCell(row, colCount++, "Education Name", style);
		createCell(row, colCount++, "College/Institute", style);
		createCell(row, colCount++, "Board/University", style);
		createCell(row, colCount++, "Year of Completion", style);
		createCell(row, colCount++, "Class", style);

		// Work Details
		createCell(row, colCount++, "Employer Name", style);
		createCell(row, colCount++, "Worked From", style);
		createCell(row, colCount++, "Worked To", style);
		createCell(row, colCount++, "Industry Type", style);
		createCell(row, colCount++, "Organisation Type", style);

		// Identity Details
		createCell(row, colCount++, "Id Doc Name", style);
		createCell(row, colCount++, "Id Number", style);
		createCell(row, colCount++, "Valid From", style);
		createCell(row, colCount++, "Valid To", style);
		createCell(row, colCount++, "Name as in Doc", style);
		createCell(row, colCount++, "Doc issued Country", style);

//		// Family Details
//		createCell(row, colCount++, "Family Member", style);
//		createCell(row, colCount++, "Relationship", style);
//		createCell(row, colCount++, "Maritial Status", style);
//		createCell(row, colCount++, "Occupation", style);

		// Travel Details
		createCell(row, colCount++, "Travelled Country", style);
		createCell(row, colCount++, "Stayed From", style);
		createCell(row, colCount++, "Stayed To", style);
		createCell(row, colCount++, "Travel Type", style);
		createCell(row, colCount++, "Visa Type", style);

//		// Certifications Received
//		createCell(row, colCount++, "Certification Name", style);
//		createCell(row, colCount++, "Issued By", style);
//		createCell(row, colCount++, "Valid From", style);
//		createCell(row, colCount++, "Valid To", style);

		// Specialized Training
		createCell(row, colCount++, "Specialized Course", style);
		createCell(row, colCount++, "Details", style);
		createCell(row, colCount++, "Date of Completion", style);

		// Languages Known
		createCell(row, colCount++, "Language Name", style);
		createCell(row, colCount++, "Read", style);
		createCell(row, colCount++, "Write", style);
		createCell(row, colCount++, "Speak", style);

		// Reference Details
		createCell(row, colCount++, "Reference Name", style);
		createCell(row, colCount++, "Designation", style);
		createCell(row, colCount++, "Address", style);
		createCell(row, colCount++, "Mobile", style);

		rowCount++;
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

		for (CandidateDetails candidate : candidateDetailsList) {

			int colCount = 0;

			int maxRows = Collections
					.max(Arrays.asList(1, candidate.getAddressList() != null ? candidate.getAddressList().size() : 0,
							candidate.getEducationList() != null ? candidate.getEducationList().size() : 0,
							candidate.getWorkList() != null ? candidate.getWorkList().size() : 0,
							candidate.getIdentityList() != null ? candidate.getIdentityList().size() : 0,
							// candidate.getFamilyList() != null ? candidate.getFamilyList().size() : 0,
							candidate.getTravelList() != null ? candidate.getTravelList().size() : 0,
							// candidate.getCertificationsList() != null ?
							// candidate.getCertificationsList().size() : 0,
							candidate.getSpecializationList() != null ? candidate.getSpecializationList().size() : 0,
							candidate.getLanguageList() != null ? candidate.getLanguageList().size() : 0,
							candidate.getRefPersonList() != null ? candidate.getRefPersonList().size() : 0));

			Row row = sheet.createRow(rowCount++);

			createCell(row, colCount++, candidate.getEmpCode(), style);
			createCell(row, colCount++, candidate.getFirstName(), style);
			createCell(row, colCount++, candidate.getLastName(), style);
			calendar.setTimeInMillis(candidate.getDateOfBirth());
			createCell(row, colCount++, formatter.format(calendar.getTime()), style);
			calendar.setTimeInMillis(candidate.getDateOfJoining());
			createCell(row, colCount++, formatter.format(calendar.getTime()), style);
			colCount++; // Place of Birth Dummy Cell
			createCell(row, colCount++, candidate.getMobileNumber(), style);
			createCell(row, colCount++, candidate.getGender(), style);
			createCell(row, colCount++, candidate.getEmail(), style);
			createCell(row, colCount++, candidate.getNationality(), style);
			colCount++; // State of Domicile Dummy Cell
			// createCell(row, colCount++, candidate.getReligion(), style);
			createCell(row, colCount++, candidate.getMaritalStatus(), style);
			createCell(row, colCount++, candidate.getFatherOrSpouseName(), style);

			// Emergency Contact
			createCell(row, colCount++, candidate.getEmgContactName(), style);
			createCell(row, colCount++, candidate.getEmgContactMobile(), style);
			colCount++; // Emg Contact Address
			createCell(row, colCount++, candidate.getEmgContactEmail(), style);
			createCell(row, colCount++, candidate.getEmgContactRelation(), style);

			// Social Media Link
			createCell(row, colCount++, candidate.getSocialMediLink(), style);

			// Identification Marks & Hobbies
//			createCell(row, colCount++, candidate.getIdentificationMarks(), style);
//			createCell(row, colCount++, candidate.getHobbies(), style);

			// Address
			for (int i = 0; i < maxRows; i++) {
				colCount = 0;

				if (i < 1) {

					if (candidate.getAddressList() != null && i < candidate.getAddressList().size()) {
						colCount = 19;
						createCell(row, colCount++, candidate.getAddressList().get(i).getType(), style);
						createCell(row, colCount++, candidate.getAddressList().get(i).getAddressLine1(), style);
						createCell(row, colCount++, candidate.getAddressList().get(i).getAddressLine2(), style);
						createCell(row, colCount++, candidate.getAddressList().get(i).getCity(), style);
						createCell(row, colCount++, candidate.getAddressList().get(i).getState(), style);
						createCell(row, colCount++, candidate.getAddressList().get(i).getCountry(), style);
						createCell(row, colCount++, candidate.getAddressList().get(i).getZipOrPostalCode(), style);
					}

					if (candidate.getEducationList() != null && i < candidate.getEducationList().size()) {
						colCount = 26;
						createCell(row, colCount++, candidate.getEducationList().get(i).getEducationName(), style);
						createCell(row, colCount++, candidate.getEducationList().get(i).getInstitution(), style);
						createCell(row, colCount++, candidate.getEducationList().get(i).getBoardOrUniversity(), style);
						calendar.setTimeInMillis(candidate.getEducationList().get(i).getStudiedTo());
						createCell(row, colCount++, formatter.format(calendar.getTime()), style);
						colCount++; // Class Dummy Field
					}

					if (candidate.getWorkList() != null && i < candidate.getWorkList().size()) {
						colCount = 31;
						createCell(row, colCount++, candidate.getWorkList().get(i).getCompanyName(), style);
						calendar.setTimeInMillis(candidate.getWorkList().get(i).getWorkedFrom());
						createCell(row, colCount++, formatter.format(calendar.getTime()), style);
						calendar.setTimeInMillis(candidate.getWorkList().get(i).getWorkedTo());
						createCell(row, colCount++, formatter.format(calendar.getTime()), style);
						createCell(row, colCount++, candidate.getWorkList().get(i).getIndustryType(), style);
						createCell(row, colCount++, candidate.getWorkList().get(i).getOrganizationType(), style);
					}

					if (candidate.getIdentityList() != null && i < candidate.getIdentityList().size()) {
						colCount = 36;
						createCell(row, colCount++, candidate.getIdentityList().get(i).getDocName(), style);
						createCell(row, colCount++, candidate.getIdentityList().get(i).getDocNumber(), style);
						calendar.setTimeInMillis(candidate.getIdentityList().get(i).getDocValidFrom());
						createCell(row, colCount++, formatter.format(calendar.getTime()), style);
						calendar.setTimeInMillis(candidate.getIdentityList().get(i).getDocValidTo());
						createCell(row, colCount++, formatter.format(calendar.getTime()), style);
						createCell(row, colCount++, candidate.getIdentityList().get(i).getNameInDoc(), style);
						createCell(row, colCount++, candidate.getIdentityList().get(i).getDocIssuedCountry(), style);
					}

//					if (candidate.getFamilyList() != null && i < candidate.getFamilyList().size()) {
//						colCount = 42;
//						createCell(row, colCount++, candidate.getFamilyList().get(i).getName(), style);
//						createCell(row, colCount++, candidate.getFamilyList().get(i).getRelation(), style);
//						createCell(row, colCount++, candidate.getFamilyList().get(i).getMaritialStatus(), style);
//						createCell(row, colCount++, candidate.getFamilyList().get(i).getOccupation(), style);
//					}

					if (candidate.getTravelList() != null && i < candidate.getTravelList().size()) {
						colCount = 42;
						createCell(row, colCount++, candidate.getTravelList().get(i).getCountryVisited(), style);

						calendar.setTimeInMillis(candidate.getTravelList().get(i).getStayedFrom());
						createCell(row, colCount++, formatter.format(calendar.getTime()), style);
						calendar.setTimeInMillis(candidate.getTravelList().get(i).getStayedTo());
						createCell(row, colCount++, formatter.format(calendar.getTime()), style);

						createCell(row, colCount++, candidate.getTravelList().get(i).getTravelType(), style);
						createCell(row, colCount++, candidate.getTravelList().get(i).getVisaType(), style);
					}

//					if (candidate.getCertificationsList() != null && i < candidate.getCertificationsList().size()) {
//						colCount = 51;
//						createCell(row, colCount++, candidate.getCertificationsList().get(i).getName(), style);
//						createCell(row, colCount++, candidate.getCertificationsList().get(i).getIssuedBy(), style);
//
//						calendar.setTimeInMillis(candidate.getCertificationsList().get(i).getValidFrom());
//						createCell(row, colCount++, formatter.format(calendar.getTime()), style);
//						calendar.setTimeInMillis(candidate.getCertificationsList().get(i).getValidTo());
//						createCell(row, colCount++, formatter.format(calendar.getTime()), style);
//					}

					if (candidate.getSpecializationList() != null && i < candidate.getSpecializationList().size()) {
						colCount = 47;
						createCell(row, colCount++, candidate.getSpecializationList().get(i).getName(), style);
						createCell(row, colCount++, candidate.getSpecializationList().get(i).getDetails(), style);
						calendar.setTimeInMillis(candidate.getSpecializationList().get(i).getDateOfCompletion());
						createCell(row, colCount++, formatter.format(calendar.getTime()), style);

					}

					if (candidate.getLanguageList() != null && i < candidate.getLanguageList().size()) {
						colCount = 50;
						createCell(row, colCount++, candidate.getLanguageList().get(i).getName(), style);
						createCell(row, colCount++, candidate.getLanguageList().get(i).isRead(), style);
						createCell(row, colCount++, candidate.getLanguageList().get(i).isWrite(), style);
						createCell(row, colCount++, candidate.getLanguageList().get(i).isSpeak(), style);
					}

					if (candidate.getRefPersonList() != null && i < candidate.getRefPersonList().size()) {
						colCount = 54;
						createCell(row, colCount++, candidate.getRefPersonList().get(i).getName(), style);
						createCell(row, colCount++, candidate.getRefPersonList().get(i).getDesignation(), style);
						createCell(row, colCount++, candidate.getRefPersonList().get(i).getAddress(), style);
						createCell(row, colCount++, candidate.getRefPersonList().get(i).getMobileNumber(), style);
					}

				} else {

					Row nestedRow = sheet.createRow(rowCount++);
					colCount = 0;
					createCell(nestedRow, colCount++, candidate.getEmpCode(), style);
					createCell(nestedRow, colCount++, candidate.getFirstName(), style);
					createCell(nestedRow, colCount++, candidate.getLastName(), style);

					if (candidate.getAddressList() != null && i < candidate.getAddressList().size()) {
						colCount = 19;
						createCell(nestedRow, colCount++, candidate.getAddressList().get(i).getType(), style);
						createCell(nestedRow, colCount++, candidate.getAddressList().get(i).getAddressLine1(), style);
						createCell(nestedRow, colCount++, candidate.getAddressList().get(i).getAddressLine2(), style);
						createCell(nestedRow, colCount++, candidate.getAddressList().get(i).getCity(), style);
						createCell(nestedRow, colCount++, candidate.getAddressList().get(i).getState(), style);
						createCell(nestedRow, colCount++, candidate.getAddressList().get(i).getCountry(), style);
						createCell(nestedRow, colCount++, candidate.getAddressList().get(i).getZipOrPostalCode(),
								style);
					}

					if (candidate.getEducationList() != null && i < candidate.getEducationList().size()) {
						colCount = 26;
						createCell(nestedRow, colCount++, candidate.getEducationList().get(i).getEducationName(),
								style);
						createCell(nestedRow, colCount++, candidate.getEducationList().get(i).getInstitution(), style);
						createCell(nestedRow, colCount++, candidate.getEducationList().get(i).getBoardOrUniversity(),
								style);
						calendar.setTimeInMillis(candidate.getEducationList().get(i).getStudiedTo());
						createCell(nestedRow, colCount++, formatter.format(calendar.getTime()), style);
						colCount++; // Class Dummy Field
					}

					if (candidate.getWorkList() != null && i < candidate.getWorkList().size()) {
						colCount = 31;
						createCell(nestedRow, colCount++, candidate.getWorkList().get(i).getCompanyName(), style);
						calendar.setTimeInMillis(candidate.getWorkList().get(i).getWorkedFrom());
						createCell(nestedRow, colCount++, formatter.format(calendar.getTime()), style);
						calendar.setTimeInMillis(candidate.getWorkList().get(i).getWorkedTo());
						createCell(nestedRow, colCount++, formatter.format(calendar.getTime()), style);
						createCell(nestedRow, colCount++, candidate.getWorkList().get(i).getIndustryType(), style);
						createCell(nestedRow, colCount++, candidate.getWorkList().get(i).getOrganizationType(), style);
					}

					if (candidate.getIdentityList() != null && i < candidate.getIdentityList().size()) {
						colCount = 36;
						createCell(nestedRow, colCount++, candidate.getIdentityList().get(i).getDocName(), style);
						createCell(nestedRow, colCount++, candidate.getIdentityList().get(i).getDocNumber(), style);
						calendar.setTimeInMillis(candidate.getIdentityList().get(i).getDocValidFrom());
						createCell(nestedRow, colCount++, formatter.format(calendar.getTime()), style);
						calendar.setTimeInMillis(candidate.getIdentityList().get(i).getDocValidTo());
						createCell(nestedRow, colCount++, formatter.format(calendar.getTime()), style);
						createCell(nestedRow, colCount++, candidate.getIdentityList().get(i).getNameInDoc(), style);
						createCell(nestedRow, colCount++, candidate.getIdentityList().get(i).getDocIssuedCountry(),
								style);
					}
//					if (candidate.getFamilyList() != null && i < candidate.getFamilyList().size()) {
//						colCount = 44;
//						createCell(nestedRow, colCount++, candidate.getFamilyList().get(i).getName(), style);
//						createCell(nestedRow, colCount++, candidate.getFamilyList().get(i).getRelation(), style);
//						createCell(nestedRow, colCount++, candidate.getFamilyList().get(i).getMaritialStatus(), style);
//						createCell(nestedRow, colCount++, candidate.getFamilyList().get(i).getOccupation(), style);
//					}

					if (candidate.getTravelList() != null && i < candidate.getTravelList().size()) {
						colCount = 42;
						createCell(nestedRow, colCount++, candidate.getTravelList().get(i).getCountryVisited(), style);

						calendar.setTimeInMillis(candidate.getTravelList().get(i).getStayedFrom());
						createCell(nestedRow, colCount++, formatter.format(calendar.getTime()), style);
						calendar.setTimeInMillis(candidate.getTravelList().get(i).getStayedTo());
						createCell(nestedRow, colCount++, formatter.format(calendar.getTime()), style);

						createCell(nestedRow, colCount++, candidate.getTravelList().get(i).getTravelType(), style);
						createCell(nestedRow, colCount++, candidate.getTravelList().get(i).getVisaType(), style);
					}

//					if (candidate.getCertificationsList() != null && i < candidate.getCertificationsList().size()) {
//						colCount = 53;
//						createCell(nestedRow, colCount++, candidate.getCertificationsList().get(i).getName(), style);
//						createCell(nestedRow, colCount++, candidate.getCertificationsList().get(i).getIssuedBy(),
//								style);
//
//						calendar.setTimeInMillis(candidate.getCertificationsList().get(i).getValidFrom());
//						createCell(nestedRow, colCount++, formatter.format(calendar.getTime()), style);
//						calendar.setTimeInMillis(candidate.getCertificationsList().get(i).getValidTo());
//						createCell(nestedRow, colCount++, formatter.format(calendar.getTime()), style);
//					}

					if (candidate.getSpecializationList() != null && i < candidate.getSpecializationList().size()) {
						colCount = 47;
						createCell(nestedRow, colCount++, candidate.getSpecializationList().get(i).getName(), style);
						createCell(nestedRow, colCount++, candidate.getSpecializationList().get(i).getDetails(), style);
						calendar.setTimeInMillis(candidate.getSpecializationList().get(i).getDateOfCompletion());
						createCell(nestedRow, colCount++, formatter.format(calendar.getTime()), style);

					}

					if (candidate.getLanguageList() != null && i < candidate.getLanguageList().size()) {
						colCount = 50;
						createCell(nestedRow, colCount++, candidate.getLanguageList().get(i).getName(), style);
						createCell(nestedRow, colCount++, candidate.getLanguageList().get(i).isRead(), style);
						createCell(nestedRow, colCount++, candidate.getLanguageList().get(i).isWrite(), style);
						createCell(nestedRow, colCount++, candidate.getLanguageList().get(i).isSpeak(), style);
					}

					if (candidate.getRefPersonList() != null && i < candidate.getRefPersonList().size()) {
						colCount = 54;
						createCell(nestedRow, colCount++, candidate.getRefPersonList().get(i).getName(), style);
						createCell(nestedRow, colCount++, candidate.getRefPersonList().get(i).getDesignation(), style);
						createCell(nestedRow, colCount++, candidate.getRefPersonList().get(i).getAddress(), style);
						createCell(nestedRow, colCount++, candidate.getRefPersonList().get(i).getMobileNumber(), style);
					}
				}
			}

//			rowCount = ++maxRows;

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
