package com.mumu.service;

import com.mumu.utils.Result;

public interface TheoryService {
	public Result getTheoryById(Integer theoryId);
	
	public Result getTheories(Integer page,Integer rows);
}
