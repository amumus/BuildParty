package com.mumu.service;

import com.mumu.bean.Student;
import com.mumu.utils.Result;

public interface LoginService {
	/*
	 * 登录
	 */
	public Result login(Student student);
	/*
	 * 发送验证码
	 */
	public Result sendVerificationCode(long id);
	/*
	 * 匹配验证码
	 */
	public Result checkVerificationCode(long id,String code);
	/*
	 * 修改密码
	 */
	public Result changePassword();
	
}
