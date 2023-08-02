package com.amit.service;

import java.util.List;

import com.amit.binding.DcSummary;
import com.amit.binding.Education;
import com.amit.binding.Income;
import com.amit.binding.PlanSelection;
import com.amit.requ.ChildReq;

public interface DataCollectionService {
	
	public List<String> getPlansName();
	
	public String saveSelectedPlan(PlanSelection select);
	
	public String saveIncomeData(Income income);
	
	public String saveEducationData(Education education);
	
	public String saveKidsData(ChildReq kids);
	
	public DcSummary getSummary(Integer caseNo);
	
}
