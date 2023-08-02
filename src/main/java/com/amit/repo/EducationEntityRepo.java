package com.amit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.entity.EducationEntity;

public interface EducationEntityRepo extends JpaRepository<EducationEntity, Integer>{

	public EducationEntity findByCaseNumber(Integer caseNumb);
}
