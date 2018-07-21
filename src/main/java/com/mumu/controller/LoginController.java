package com.mumu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mumu.bean.Student;
import com.mumu.service.LoginService;
import com.mumu.utils.JsonUtils;
import com.mumu.utils.Result;
/**
 * 包含登录界面所有功能
 * @author mumu
 *
 */
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	/**
	 * 登录功能，id是学号，password密码
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result login(long id,String password) {
		Student student = new Student();
		student.setId(id);
		student.setPassword(password);
		return loginService.login(student);
	}
	
	/**
	 * 发送动态验证码到邮箱，id是所要修改密码的学号
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/login/sendVerificationCode",method=RequestMethod.POST)
	@ResponseBody
	public Result sendVerificationCode(long id) {
		return loginService.sendVerificationCode(id);
	}
	/**
	 * 校验验证码，id是所需要修改密码的学号，code是邮箱接收的验证码
	 * @param id
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/login/checkVerificationCode",method=RequestMethod.POST)
	@ResponseBody
	public Result checkVerificationCode(long id ,String code) {
		return  loginService.checkVerificationCode(id, code);
	}
	
}
