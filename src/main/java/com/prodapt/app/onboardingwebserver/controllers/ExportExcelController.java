package com.prodapt.app.onboardingwebserver.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletResponse;

import com.prodapt.app.onboardingwebserver.entities.BankDetails;
import com.prodapt.app.onboardingwebserver.entities.CandidateDetails;
import com.prodapt.app.onboardingwebserver.entities.EPFKYCDetails;
import com.prodapt.app.onboardingwebserver.entities.Mediclaim;
import com.prodapt.app.onboardingwebserver.entities.WelfareFund;
import com.prodapt.app.onboardingwebserver.repository.BankDetailsRepository;
import com.prodapt.app.onboardingwebserver.repository.CandidateDetailsRepository;
import com.prodapt.app.onboardingwebserver.repository.EPFKYCRepository;
import com.prodapt.app.onboardingwebserver.repository.MediclaimRepository;
import com.prodapt.app.onboardingwebserver.repository.WelfareFundRespository;
import com.prodapt.app.onboardingwebserver.utility.BankDetailsExcelExporter;
import com.prodapt.app.onboardingwebserver.utility.CandidateDetailsExcelExporter;
import com.prodapt.app.onboardingwebserver.utility.EPFKYCExcelExporter;
import com.prodapt.app.onboardingwebserver.utility.MediclaimExcelExporter;
import com.prodapt.app.onboardingwebserver.utility.WelfareFundExcelExporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/export-excel")
public class ExportExcelController {

	@Autowired
	private MediclaimRepository mediclaimRepository;

	@Autowired
	private EPFKYCRepository epfkycRepository;

	@Autowired
	private CandidateDetailsRepository candidateDetailsRepository;

	@Autowired
	private BankDetailsRepository bankDetailsRepository;

	@Autowired
	private WelfareFundRespository welfareFundRespository;

	private MediclaimExcelExporter mediclaimExcelExporter;
	private EPFKYCExcelExporter epfkycExcelExporter;
	private CandidateDetailsExcelExporter candidateDetailsExcelExporter;
	private BankDetailsExcelExporter bankDetailsExcelExporter;
	private WelfareFundExcelExporter welfareFundExcelExporter;

	@GetMapping("/mediclaim/{fromDate},{toDate},{name}")
	public void exportMediclaimList(HttpServletResponse response, @PathVariable("fromDate") long fromDate,
			@PathVariable long toDate, @PathVariable("name") String name) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<Mediclaim> mediclaimsList = StreamSupport.stream(mediclaimRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());

		if (name == null || name.equals("null")) {
			mediclaimsList = mediclaimsList.stream()
					.filter(c -> (c.getDateOfJoining() >= fromDate && c.getDateOfJoining() <= toDate))
					.collect(Collectors.toList());
		} else {
			mediclaimsList = mediclaimsList.stream()
					.filter(c -> (c.getDateOfJoining() >= fromDate && c.getDateOfJoining() <= toDate)
							&& c.getName().toLowerCase().contains(name.toLowerCase()))
					.collect(Collectors.toList());
		}
		mediclaimExcelExporter = new MediclaimExcelExporter(mediclaimsList);
		mediclaimExcelExporter.export(response);
	}

	@GetMapping("/epf-kyc/{fromDate},{toDate},{name}")
	public void exportEPFKYCDetails(HttpServletResponse response, @PathVariable("fromDate") long fromDate,
			@PathVariable long toDate, @PathVariable("name") String name) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<EPFKYCDetails> epfkycList = StreamSupport.stream(epfkycRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());

		if (name == null || name.equals("null")) {
			epfkycList = epfkycList.stream()
					.filter(c -> (c.getDateOfJoining() >= fromDate && c.getDateOfJoining() <= toDate))
					.collect(Collectors.toList());
		} else {
			epfkycList = epfkycList.stream()
					.filter(c -> (c.getDateOfJoining() >= fromDate && c.getDateOfJoining() <= toDate)
							&& c.getName().toLowerCase().contains(name.toLowerCase()))
					.collect(Collectors.toList());
		}
		epfkycExcelExporter = new EPFKYCExcelExporter(epfkycList);
		epfkycExcelExporter.export(response);
	}

	@GetMapping("/candidate-details/{fromDate},{toDate},{name}")
	public void exportCandidateDetails(HttpServletResponse response, @PathVariable("fromDate") long fromDate,
			@PathVariable long toDate, @PathVariable("name") String name) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<CandidateDetails> candidateDetailsList = StreamSupport
				.stream(candidateDetailsRepository.findAll().spliterator(), false).collect(Collectors.toList());

		if (name == null || name.equals("null")) {
			candidateDetailsList = candidateDetailsList.stream()
					.filter(c -> (c.getDateOfJoining() >= fromDate && c.getDateOfJoining() <= toDate))
					.collect(Collectors.toList());
		} else {
			candidateDetailsList = candidateDetailsList.stream()
					.filter(c -> (c.getDateOfJoining() >= fromDate && c.getDateOfJoining() <= toDate)
							&& (c.getFirstName().toLowerCase().contains(name.toLowerCase())
									|| c.getLastName().toLowerCase().contains(name.toLowerCase())))
					.collect(Collectors.toList());
		}

		candidateDetailsExcelExporter = new CandidateDetailsExcelExporter(candidateDetailsList);
		candidateDetailsExcelExporter.export(response);
	}

	@GetMapping("/bank-details/{fromDate},{toDate},{name}")
	public void exportBankDetails(HttpServletResponse response, @PathVariable("fromDate") long fromDate,
			@PathVariable long toDate, @PathVariable("name") String name) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<BankDetails> bankDetailsList = StreamSupport.stream(bankDetailsRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	
		if (name == null || name.equals("null")) {
			bankDetailsList = bankDetailsList.stream()
					.filter(c -> (c.getDateOfJoining() >= fromDate && c.getDateOfJoining() <= toDate))
					.collect(Collectors.toList());
		} else {
			bankDetailsList = bankDetailsList.stream()
					.filter(c -> (c.getDateOfJoining() >= fromDate && c.getDateOfJoining() <= toDate)
							&& (c.getEmpName().toLowerCase().contains(name.toLowerCase())))
					.collect(Collectors.toList());
		}

		bankDetailsExcelExporter = new BankDetailsExcelExporter(bankDetailsList);
		bankDetailsExcelExporter.export(response);
	}

	@GetMapping("/welfare-fund/{fromDate},{toDate},{name}")
	public void exportWelfareFund(HttpServletResponse response, @PathVariable("fromDate") long fromDate,
			@PathVariable long toDate, @PathVariable("name") String name) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<WelfareFund> welfareFundList = StreamSupport.stream(welfareFundRespository.findAll().spliterator(), false)
				.collect(Collectors.toList());

		if (name == null || name.equals("null")) {
			welfareFundList = welfareFundList.stream()
					.filter(c -> (c.getDateOfJoining() >= fromDate && c.getDateOfJoining() <= toDate))
					.collect(Collectors.toList());
		} else {
			welfareFundList = welfareFundList.stream()
					.filter(c -> (c.getDateOfJoining() >= fromDate && c.getDateOfJoining() <= toDate)
							&& (c.getEmpName().toLowerCase().contains(name.toLowerCase())))
					.collect(Collectors.toList());
		}

		welfareFundExcelExporter = new WelfareFundExcelExporter(welfareFundList);
		welfareFundExcelExporter.export(response);
	}

}
