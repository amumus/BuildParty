<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<lable>用户名：</lable><input id="id" name="id" type="text"/>
<lable>密码：</lable><input id="password" name="password" type="text"/>
<lable>动态验证码</lable><input id="code" name="code" type="text"/>
<button id="bt_changePassword">提交</button>
</body>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//发送验证码
	$("#bt_changePassword").click(function(){
		console.log("点击了修改");
		$.ajax({
			url:"changePassword",
			type:'post',
			data:{
				id:$("#id").val(),
				password:$("#password").val(),
				code:$("#code").val()
			},
			success:function(result){
				console.log(result.code);
				console.log(result.message);
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