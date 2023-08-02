package com.amit.requ;

import java.util.List;

import com.amit.binding.Child;

import lombok.Data;

@Data
public class ChildReq {
	
	private Integer caseNo;
	
	private List<Child> child;
}
