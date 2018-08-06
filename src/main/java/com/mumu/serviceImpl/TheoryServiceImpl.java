package com.mumu.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mumu.bean.Theory;
import com.mumu.dao.TheoryMapper;
import com.mumu.service.TheoryService;
import com.mumu.utils.Result;
@Service
public class TheoryServiceImpl implements TheoryService{
	@Autowired
	private TheoryMapper theoryMapper;
	
	@Override
	public Result getTheoryById(Integer theoryId) {
		Result result = new Result();
		result.setCode(200);
		Map map = new HashMap<>();
		Theory theory = theoryMapper.selectByPrimaryKey(theoryId);
		map.put("theory", theory);
		result.setData(map);
		return result;
	}

	@Override
	public Result getTheories(Integer page, Integer rows) {
		Result result = new Result();
		result.setCode(200);
		Map map = new HashMap<>();
		Integer start = page*rows;
		List<Theory> list = theoryMapper.selectList(start, rows);
		map.put("list", list);
		result.setData(map);
		return result;
	}
	
}
