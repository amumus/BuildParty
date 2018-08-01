package com.mumu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mumu.bean.Student;
import com.mumu.service.StudentService;
import com.mumu.utils.EasyUIDataGridResult;
import com.mumu.utils.Result;

/**
 * 学生的controller
 * @author mumu
 *
 */
@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	/**
	 * 后台获取学生列表
	 * @param page 第几页，从零开始
	 * @param rows 每页几行
	 * @param id 学生的学号
	 * @param name 学生的姓名
	 * @param politicalstatus 学生的政治面貌
	 * @return
	 */
	@RequestMapping("/student/list")
	@ResponseBody
	public EasyUIDataGridResult getStudentsList(Integer page, Integer rows,
			@RequestParam(required=false)String id,
			@RequestParam(required=false)String name,
			@RequestParam(required=false)String politicalstatus) {
		EasyUIDataGridResult result = studentService.getStudentList(page, rows,id,name,politicalstatus);
		return result;
	}
	
	/**
	 * 后台学生添加
	 * @param student
	 * @return
	 */
	@RequestMapping("/student/add")
	@ResponseBody
	public Result addStudent(Student student) {
		return studentService.addStudent(student);
	}
	/**
	 * 后台学生修改
	 * @param student
	 * @return
	 */
	@RequestMapping("/student/edit")
	@ResponseBody
	public Result editStudent(Student student) {
		return studentService.editStudent(student);
	}
	/**
	 * 后台学生删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/student/delete")
	@ResponseBody
	public Result deleteStudents(@RequestParam("ids") String[] ids) {
		System.out.println(ids.length);
		return studentService.deleteStudents(ids);
	}
}
