package com.amit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.binding.Child;
import com.amit.binding.DcSummary;
import com.amit.binding.Education;
import com.amit.binding.Income;
import com.amit.binding.PlanSelection;
import com.amit.entity.EducationEntity;
import com.amit.entity.IncomeEntity;
import com.amit.entity.KidsEntity;
import com.amit.entity.User;
import com.amit.repo.EducationEntityRepo;
import com.amit.repo.IncomeEntityRepo;
import com.amit.repo.KidsEntityRepo;
import com.amit.repo.PlanRepo;
import com.amit.repo.PlanSelectionRepo;
import com.amit.repo.UserRepo;
import com.amit.requ.ChildReq;
import com.amit.service.DataCollectionService;

@Service
public class DataCollectionServiceImpl implements DataCollectionService {

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PlanSelectionRepo seleRepo;

	@Autowired
	private IncomeEntityRepo incomeRepo;

	@Autowired
	private EducationEntityRepo eduRepo;

	@Autowired
	private KidsEntityRepo kidsRepo;

	@Override
	public List<String> getPlansName() {
		return planRepo.getPlanNames();
	}

	@Override
	public String saveIncomeData(Income income) {

		IncomeEntity incomeEntity = new IncomeEntity();

		Optional<User> findById = userRepo.findById(income.getCaseNo());
		if (findById.isPresent()) {
			User userEntity = findById.get();
			BeanUtils.copyProperties(income, incomeEntity);
			incomeEntity.setCaseNumber(income.getCaseNo());
			incomeEntity.setUser(userEntity);
			incomeRepo.save(incomeEntity);
		}
		return "saves";
	}

	@Override
	public String saveEducationData(Education edu) {

		EducationEntity entity = new EducationEntity();
		Optional<User> findById = userRepo.findById(edu.getCaseNo());
		if (findById.isPresent()) {
			User userEntity = findById.get();
			BeanUtils.copyProperties(edu, entity);
			entity.setCaseNumber(edu.getCaseNo());
			entity.setUser(userEntity);
			eduRepo.save(entity);
		}

		return "success";
	}

	@Override
	public String saveKidsData(ChildReq req) {

		List<Child> childs = req.getChild();

		Optional<User> findById = userRepo.findById(req.getCaseNo());

		if (findById.isPresent()) {
			User user = findById.get();
			for (Child k : childs) {
				KidsEntity entity = new KidsEntity();
				BeanUtils.copyProperties(k, entity);
				entity.setCaseNumber(req.getCaseNo());
				entity.setUser(user);
				kidsRepo.save(entity);
			}

		}
		return "success";
	}

	@Override
	public String saveSelectedPlan(PlanSelection select) {

		Optional<User> findById = userRepo.findById(select.getCaseNo());
		if (findById.isPresent()) {
			User userEntity = findById.get();
			BeanUtils.copyProperties(select, userEntity);
			userRepo.save(userEntity);
		}
		return "success";
	}

	@Override
	public DcSummary getSummary(Integer caseNo) {

		IncomeEntity incomeEntity = incomeRepo.findByCaseNumber(caseNo);
		EducationEntity educationEntity = eduRepo.findByCaseNumber(caseNo);
		List<KidsEntity> kidsEntity = kidsRepo.findByCaseNumber(caseNo);

		DcSummary summary = new DcSummary();

		Income income = new Income();
		BeanUtils.copyProperties(incomeEntity, income);
		income.setCaseNo(incomeEntity.getCaseNumber());
		summary.setIncome(income);

		Education edu = new Education();
		BeanUtils.copyProperties(educationEntity, edu);
		edu.setCaseNo(educationEntity.getCaseNumber());
		summary.setEducation(edu);

		List<Child> childs = new ArrayList<>();

		for (KidsEntity entity : kidsEntity) {
			Child ch = new Child();
			BeanUtils.copyProperties(entity, ch);
			childs.add(ch);
		}
		summary.setChilds(childs);
		return summary;
	}

}
