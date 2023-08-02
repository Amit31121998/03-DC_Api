package com.amit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.entity.KidsEntity;

public interface KidsEntityRepo extends JpaRepository<KidsEntity, Integer> {
	
	public List<KidsEntity> findByCaseNumber(Integer caseNumb);
}
