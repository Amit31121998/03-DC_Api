package com.amit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amit.binding.DcSummary;
import com.amit.binding.Education;
import com.amit.binding.Income;
import com.amit.binding.PlanSelection;
import com.amit.requ.ChildReq;
import com.amit.service.DataCollectionService;

@RestController
public class DcRestController {

	@Autowired
	private DataCollectionService service;

	@GetMapping("/getPlanName")
	public ResponseEntity<List<String>> getPlanNames() {
		List<String> plansName = service.getPlansName();
		return new ResponseEntity<>(plansName, HttpStatus.OK);
	}

	@PostMapping("/planInsertion")
	public ResponseEntity<String> PlanSelection(@RequestBody PlanSelection selectionPlan) {
		String saveSelectedPlan = service.saveSelectedPlan(selectionPlan);
		return new ResponseEntity<>(saveSelectedPlan, HttpStatus.CREATED);
	}

	@PostMapping("/saveIncome")
	public String saveCitizenIncome(@RequestBody Income binding) {

		return service.saveIncomeData(binding);
	}

	@PostMapping("/saveEdu")
	public String saveEduDetails(@RequestBody Education edu) {
		return service.saveEducationData(edu);
	}

	@PostMapping("/saveChilds")
	public String saveKids(@RequestBody ChildReq child) {
		return service.saveKidsData(child);
	}

	@GetMapping("/getAllData/{caseNo}")
	public ResponseEntity<DcSummary> getCitizenDetails(@PathVariable Integer caseNo) {
		DcSummary summary = service.getSummary(caseNo);
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}
}
