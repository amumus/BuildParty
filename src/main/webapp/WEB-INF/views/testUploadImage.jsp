<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单图片上传</title>
<link rel="stylesheet" href="js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="js/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="js/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="js/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="js/kindeditor/plugins/code/prettify.js"></script>
</head>
<body>
<!--  <form action="uploadFiles" method="post" enctype="multipart/form-data">
     选择文件:<input type="file" name="file">
     <input type="submit" value="上传"> 
 </form> -->
 
 <br/>
<!--  <input type="text" id="url" value="" /> -->
 <input type="button" id="uploadContentImage" value="选择图片" />
 <script type="text/javascript">
 KindEditor.ready(function(K) {

	 var editor = K.editor({
	 allowFileManager : true //允许图片管理 开启后再挑选图片的时候可以直接从图片空间内挑选
	 });
	 //给按钮添加click事件
	 K('#uploadContentImage').click(function() {
	 alert("你点我了!");
	 });
	 });
 </script>
 
</body>
</html>