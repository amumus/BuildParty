package com.mumu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mumu.service.TheoryService;
import com.mumu.utils.Result;

@Controller
public class TheoryController {
	/**
	 * 格式化日期，加此注解即可将接收过来的日期字符串转化为Date类型
	 * @param binder
	 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}
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
