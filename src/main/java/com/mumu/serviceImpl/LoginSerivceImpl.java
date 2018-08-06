package com.mumu.serviceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mumu.bean.Student;
import com.mumu.bean.StudentExample;
import com.mumu.bean.StudentExample.Criteria;
import com.mumu.dao.StudentMapper;
import com.mumu.service.LoginService;
import com.mumu.utils.Result;

@Service
public class LoginSerivceImpl implements LoginService {

	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	HttpServletRequest request;
/*
 * 登录验证
 * (non-Javadoc)
 * @see com.mumu.service.LoginService#login(com.mumu.bean.Student)
 */
	public Result login(Student student) {
		StudentExample StudentExample = new StudentExample();
		Criteria criteria = StudentExample.createCriteria();
		criteria.andIdEqualTo(student.getId());
		criteria.andPasswordEqualTo(student.getPassword());

		int count = (int) studentMapper.countByExample(StudentExample);
		Result result = new Result();
		Map map = new HashMap();
		if (count == 1) {
			Student student2 = studentMapper.selectByPrimaryKey(student.getId());
			result.setCode(1);
			map.put("message", "success");
			map.put("id", student2.getId());
			map.put("name",student2.getName());
			map.put("politicalstatus",student2.getPoliticalstatus());
			map.put("email",student2.getEmail());
		} else {
			result.setCode(0);
			map.put("message", "用户名或密码错误.");
		}
		result.setData(map);
		return result;
	}

 /**
  * 发送验证码到邮箱
  */
	public Result sendVerificationCode(String id) {
		//验证改用户是否存在
		Student isExit = studentMapper.selectByPrimaryKey(id);
		Result result = new Result();
		Map map = new HashMap();
		if(isExit == null) {
			result.setCode(0);
			map.put("message", "该用户不存在");
			return result;
		}
		//生成验证码,flag为验证码
		
		String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了  
	    Random rand = new Random();  
	    StringBuffer flag = new StringBuffer();  
	    for (int j = 0; j < 6; j++)   
	    {  
	        flag.append(sources.charAt(rand.nextInt(9)) + "");  
	    }  
	    System.out.println(flag.toString());  
	    
	    
		//将验证码和有效时间保存到数据库,有效时间为5分钟
	    Student student = new Student();
	    student.setId(id);
	    student.setValidatacode(flag.toString());
	    
	    Calendar nowTime = Calendar.getInstance();
	    nowTime.add(Calendar.MINUTE, 5);
	    
	    student.setOutdate(nowTime.getTime());
	    studentMapper.updateByPrimaryKeySelective(student);
	       
		//发送验证码到邮箱
		
		
		
		
		try {
			// 创建Properties 类用于记录邮箱的一些属性
			final Properties props = new Properties();
			// 表示SMTP发送邮件，必须进行身份验证0
			props.put("mail.smtp.auth", "true");
			// 此处填写SMTP服务器
			props.put("mail.smtp.host", "smtp.qq.com");
			// 端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
			props.put("mail.smtp.port", "587");
			// 此处填写你的账号
			props.put("mail.user", "1013760411@qq.com");
			// 此处的密码就是前面说的16位STMP口令
			props.put("mail.password", "xomddmqqmtyibahe");

			// 构建授权信息，用于进行SMTP进行身份验证
			Authenticator authenticator = new Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication() {
					// 用户名、密码
					String userName = props.getProperty("mail.user");
					String password = props.getProperty("mail.password");
					return new PasswordAuthentication(userName, password);
				}
			};
			// 使用环境属性和授权信息，创建邮件会话
			Session mailSession = Session.getInstance(props, authenticator);
			// 创建邮件消息
			MimeMessage message = new MimeMessage(mailSession);
			// 设置发件人
			InternetAddress form;

			form = new InternetAddress(props.getProperty("mail.user"));

			message.setFrom(form);

			// 设置收件人的邮箱，当然要先根据id获取该用户的邮箱
			Student selectEmailStudent = studentMapper.selectByPrimaryKey(id);
			InternetAddress to = new InternetAddress(selectEmailStudent.getEmail());
			message.setRecipient(RecipientType.TO, to);

			// 设置邮件标题
			message.setSubject("仲恺党建app验证码");

			// 设置邮件的内容体
			message.setContent("你好，本次验证码为"+flag.toString()+" 5分钟内有效。", "text/html;charset=UTF-8");

			// 最后当然就是发送邮件啦
			Transport.send(message);
			
			//发送邮箱成功后
			
			result.setCode(200);
			map.put("id", id);
			map.put("message","发送验证码成功！");
			result.setData(map);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(0);
			map.put("message","服务器出错，请联系管理员！");
			result.setData(map);
			return result;
		}
	}

	/*
	 * 验证码校验
	 * (non-Javadoc)
	 * @see com.mumu.service.LoginService#checkVerificationCode(long, int)
	 */
	public Result checkVerificationCode(String id, String code) {
		Student student = studentMapper.selectByPrimaryKey(id);
		Result result = new Result();
		Map map = new HashMap();
		String message ;
		//校验验证码是否正确
		if(!student.getValidatacode().equals(code)) {
			message = "验证码错误，请重新输入！";
			result.setCode(0);
		}else if(student.getOutdate().before(new Date())) {
			//校验验证码是否过期
			message="验证码已失效，请重验证!";
			result.setCode(0);
		}else {
			message="验证成功！";
			result.setCode(1);
			map.put("id", id);
			//还要返回验证码的值，修改密码时再一次校验，防止有人得知修改密码的逻辑后恶意修改
			map.put("validatacode", code);
		}
		
		map.put("message", message);
		result.setData(map);
		return result;
	}
	/*
	 * 修改密码
	 * (non-Javadoc)
	 * @see com.mumu.service.LoginService#changePassword(long,String,Strings)
	 */
	public Result changePassword(String id,String password,String code) {
		Result result = new Result();
		Map map = new HashMap<>();
		Student student = studentMapper.selectByPrimaryKey(id);
		if(student==null) {
			result.setCode(500);
			map.put("message", "该用户不存在");
			result.setData(map);
			return result;
		}
		if(code == null || code.trim().equals("")) {
			result.setCode(500);
			map.put("message", "验证码为空");
			result.setData(map);
			return result;
		}
		if(student.getOutdate().before(new Date())) {
			result.setCode(500);
			map.put("message", "验证码已失效，请重验证!");
			result.setData(map);
			return result;
		}
		if(!student.getValidatacode().equals(code)) {
			result.setCode(500);
			map.put("message", "验证码错误");
			result.setData(map);
			return result;
		}
		Student s = new Student();
		s.setId(id);
		s.setPassword(password);
		studentMapper.updateByPrimaryKeySelective(s);
		result.setCode(200);
		map.put("message", "修改密码成功！");
		result.setData(map);
		return result;
	}

}
