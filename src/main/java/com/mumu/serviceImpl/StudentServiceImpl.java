package com.mumu.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mumu.bean.Student;
import com.mumu.bean.StudentExample;
import com.mumu.bean.StudentExample.Criteria;
import com.mumu.dao.StudentMapper;
import com.mumu.service.StudentService;
import com.mumu.utils.EasyUIDataGridResult;
import com.mumu.utils.Result;



@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper ;
	
	
	@Override
	public EasyUIDataGridResult getStudentList(Integer page, Integer rows,
			String id,String name,String politicalstatus) {
		// 设置分页信息
		PageHelper.startPage(page, rows);
		StudentExample studentExample = new StudentExample();
		List<Student> list = null;
		Criteria criteria = null;
		if(id !=null && !id.trim().equals("")) {
			if(criteria == null) {
				criteria = studentExample.createCriteria();
			}
			 String s = "%"+id+"%";
			 criteria.andIdLike(s);
		}
		if(name !=null && !name.trim().equals("")) {
			if(criteria == null) {
				criteria = studentExample.createCriteria();
			}
			String s = "%"+name+"%";
			criteria.andNameLike(s);
		}
		if(politicalstatus!=null && !politicalstatus.trim().equals("")) {
			if(criteria == null) {
				criteria = studentExample.createCriteria();
			}
			criteria.andPoliticalstatusEqualTo(politicalstatus);
		}
		
		list = studentMapper.selectByExample(studentExample);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		PageInfo<Student> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}


	@Override
	public Result deleteStudents(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			studentMapper.deleteByPrimaryKey(ids[i]);
		}
		return Result.success();
	}


	@Override
	public Result editStudent(Student student) {
		studentMapper.updateByPrimaryKeySelective(student);
		return Result.success();
	}


	@Override
	public Result addStudent(Student student) {
		studentMapper.insert(student);
		return Result.success();
	}

}
