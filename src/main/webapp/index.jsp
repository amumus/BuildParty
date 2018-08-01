<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仲恺党建app后台管理系统</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.5.5.4/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.5.5.4/themes/icon.css" />
<script type="text/javascript"
	src="js/jquery-easyui-1.5.5.4/jquery.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.5.5.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.5.5.4/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
	<!-- 头部标题 -->
	<div data-options="region:'north'" style="height:100px; padding:5px; background:#F3F3F3">
		<div>
			<h1 style="float: left">仲恺党建app后台管理系统</h1>
		</div>
		<div style="padding-top: 50px; padding-right: 20px;">
			<h4 class="loginInfo" style="float: right">登录用户：admin&nbsp;&nbsp;姓名：管理员&nbsp;&nbsp;角色：系统管理员</h4>
		</div>
	</div>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width: 180px;">
		<ul id="menu" class="easyui-tree"
			style="margin-top: 10px; margin-left: 5px;">
			<li><span>学生管理</span>
				<ul>
					<li data-options="attributes:{'url':'BackstageStudentInfoManage'}">查询学生</li>
				</ul></li>
			<li><span>内容管理</span>
				<ul>
					<li data-options="attributes:{'url':'BackstageNewsAdd'}">新增新闻</li>
					<li data-options="attributes:{'url':'BackstageNewsManage'}">新闻管理</li>
					<li data-options="attributes:{'url':'BackstageContentManage'}">轮播图管理</li>
				</ul></li>

		</ul>
	</div>
	<div data-options="region:'center',title:''">
		<div id="tabs" class="easyui-tabs">
			<div title="首页" style="padding: 20px;">欢迎登录仲恺党建app后台管理系统</div>
		</div>
	</div>
	<script>
		$(function() {
			$('#menu').tree({
				onClick : function(node) {
					if ($('#menu').tree("isLeaf", node.target)) {
						var tabs = $("#tabs");
						var tab = tabs.tabs("getTab", node.text);
						if (tab) {
							tabs.tabs("select", node.text);
						} else {
							tabs.tabs('add', {
								title : node.text,
								href : node.attributes.url,
								closable : true,
								bodyCls : "content"
							});
						}
					}
				}
			});
		});
	</script>
</body>
</html>