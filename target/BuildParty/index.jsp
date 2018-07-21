<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="login" method="post">
	<label>用户名：</label><input name="id" type="text"/>
	<label>密码：</label><input name="password" type="password"> 
	<button id="bt_login">登录</button>
	<a href="/">忘记密码？</a>
</form>
</body>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
	$.(function (){
		$('#bt_login').click(function(){
			$.ajax({
				url:"login",
				type:POST,
				data:$('form').serialize(),
				success:function(result){
					alert(result.code);
				}
				,
				error:function(data){
					alert("出错");
				}
			});
		});
	});
</script>
</html>