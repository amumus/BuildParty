<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
</head>
<body>

		<label>用户名：</label><input id="id" name="id" type="text" /> <label>密码：</label>
		<input name="password" type="password" id="password" />
		<button id="bt_login">登录</button>
		<a href="fogetPassword">忘记密码？</a>

	<textarea rows="10" cols="10">
</textarea>
</body>
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#bt_login').on('click',function() {
			 $.ajax({
				url:"login",
				type : 'post',
				data : {
					id:$("#id").val(),
					password:$("#password").val()
				},
				success : function(result) {
					console.log(result);
					$('textarea').val(result);
				},
				error:function(err){
					console.log(err);
				}
				 /* error : function(XMLHttpRequest, textStatus) {
					// 状态码
					console.log(XMLHttpRequest.status);
					// 状态
					console.log(XMLHttpRequest.readyState);
					// 错误信息
					console.log(textStatus);
					$('textarea').val(textStatus);
				}  */
			}); 
		});
	});
</script>
</html>