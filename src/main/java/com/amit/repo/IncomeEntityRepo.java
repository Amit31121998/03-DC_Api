package com.amit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.entity.IncomeEntity;

public interface IncomeEntityRepo extends JpaRepository<IncomeEntity, Integer> {

	public IncomeEntity findByCaseNumber(Integer caseNumb);

}
