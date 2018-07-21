<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form  method="post">
	<lable>用户名：</lable><input id="id" name="id" type="text"/>
	<lable>动态验证码</lable><input name="code" type="text"/><button id="bt_sendVerificationCode">获取验证码</button><br>
	<button id="bt_checkVerificationCode">提交</button>
	</form>
</body>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		//发送验证码
		$("#bt_sendVerificationCode").click(function(){
			console.log("点击了发送");
			$.ajax({
				contentType: "text/html; charset=utf-8",
				url:"sendVerificationCode",
				type:'POST',
				data:{
					id:$("#id")
				},
				success:function(result){
					console.log(result.code);
				},
				error:function (XMLHttpRequest, textStatus, errorThrown) {
	                // 状态码
	                console.log(XMLHttpRequest.status);
	                // 状态
	                console.log(XMLHttpRequest.readyState);
	                // 错误信息
	                console.log(textStatus);
	            }
			});
		});
		$('#bt_checkVerificationCode').click(function(){
			$.ajax({
				contentType: "text/html; charset=utf-8",
				url:"checkVerificationCode",
				type:'POST',
				data:$('form').serialize(),
				success:function(result){
					console.log(result.code);
				},
				error:function (XMLHttpRequest, textStatus, errorThrown) {
	                // 状态码
	                console.log(XMLHttpRequest.status);
	                // 状态
	                console.log(XMLHttpRequest.readyState);
	                // 错误信息
	                console.log(textStatus);
	            }
			});
		});
		
	});
	
	
	
</script>
</html>