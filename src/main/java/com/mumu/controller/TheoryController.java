package com.mumu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mumu.service.TheoryService;
import com.mumu.utils.Result;

@Controller
public class TheoryController {

	@Autowired
	private TheoryService theoryService;
	
	@RequestMapping("/getTheory/{theoryId}")
	@ResponseBody
	public Result getTheoryById(@PathVariable Integer theoryId) {
		return theoryService.getTheoryById(theoryId);
	}
	
	@RequestMapping("/getTheories")
	@ResponseBody
	public Result getTheories(@RequestParam(value="page",required=false,defaultValue="0") 
	Integer page,  @RequestParam(value="rows",required=false,defaultValue="5") Integer rows) {
		return theoryService.getTheories(page, rows);
	}
	
	
	
	
}
