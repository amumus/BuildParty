package com.mumu.service;

import com.mumu.bean.Student;
import com.mumu.utils.EasyUIDataGridResult;
import com.mumu.utils.Result;

public interface StudentService {
	
	public EasyUIDataGridResult getStudentList(Integer page, Integer rows ,String id,String name,String politicalstatus);
	
	public Result deleteStudents(String ids[]);
	
	public Result editStudent(Student student);
	
	public Result addStudent(Student student);
}
