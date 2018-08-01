<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="news_tb">
	<div>
		<a href="javascript:openNewsAdd()" class="easyui-linkbutton"
			iconCls="icon-add" plain="true">添加</a> <a
			href="javascript:openNewsEdit()" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true">修改</a> <a
			href="javascript:deleteNews()" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>
		&nbsp;新闻id：&nbsp;<input type="text" name="news_id" id="search_news_id" size="10" class="easyui-numberbox"/>
		&nbsp;标题：&nbsp;<input type="text" name="name" id="search_title" size="10" />
		&nbsp;创建日期：&nbsp;<input class="easyui-datebox" name="search_start_created" id="search_start_created" editable="false" size="15"/>-><input class="easyui-datebox" name="search_end_created" id="search_end_created" editable="false" size="15"/>
		 <a href="javascript:searchNews()" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>
	
<table id="news_dg" title="新闻信息" class="easyui-datagrid" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'news/list',method:'get',pageSize:10">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'newsId',width:100">新闻id</th>
			<th data-options="field:'title',width:300">标题</th>
			<th data-options="field:'image',width:200">图片</th>
			<th data-options="field:'created',width:150">创建日期</th>
			<th data-options="field:'reading',width:100">阅读量</th>
		</tr>
	</thead>
</table>

<script type="text/javascript">
	var url;

	function deleteNews() {
		var selectedRows = $("#news_dg").datagrid('getSelections');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var ids = [];
		for (var i = 0; i < selectedRows.length; i++) {
			ids.push(selectedRows[i].newsId);
		}
		ids = ids.join(",");
		$.messager.confirm("系统提示", "您确认要删掉这<font color=red>"
				+ selectedRows.length + "</font>条数据吗？", function(r) {
			if (r) {
				$.post("news/delete", {
					ids : ids
				}, function(result) {
					if (result.code == 200) {
						$.messager.alert("系统提示", "您已成功删除<font color=red>"
								+ selectedRows.length + "</font>条数据！");
						$("#news_dg").datagrid("reload");
					} else {
						$.messager.alert('系统提示', result.data.message);
					}
				});
			}
		});
	}

	function searchNews() {
		$('#news_dg').datagrid('load', {
			newsId : $('#search_news_id').val(),
			title : $('#search_title').val(),
			startCreated:$('#search_start_created').datebox("getValue"),
			endCreated:$('#search_end_created').datebox("getValue")
		});
	}

	function openNewsAdd() {
		$(".tree-title:contains('新增新闻')").parent().click();
	}

	function openNewsEdit() {
		var ids = $("#news_dg").datagrid("getSelections");
    	if(ids.length == 0){
    		$.messager.alert('提示','必须选择一个商品才能编辑!');
    		return ;
    	}
    	if(ids.length > 1){
    		$.messager.alert('提示','只能选择一个商品!');
    		return ;
    	}
		
		
		createWindow({
			url : "BackstageNewsedit",
			onLoad : function(){
				var data = $("#news_dg").datagrid("getSelections")[0];
				$("#newsEditForm").form("load",data);
				
				// 实现图片
				if(data.image){
					$("#newsEditForm [name=image]").after("<a href='"+data.image+"' target='_blank'><img src='"+data.image+"' width='80' height='50'/></a>");	
				}
				// 加载新闻描述
    			$.getJSON('getNews/'+data.newsId,function(_data){
    				newsEditEditor.html(_data.data.news.content);
    			});
			}
		});   
	}
	
	/**
     * 创建一个窗口，关闭窗口后销毁该窗口对象。<br/>
     * 
     * 默认：<br/>
     * width : 80% <br/>
     * height : 80% <br/>
     * title : (空字符串) <br/>
     * 
     * 参数：<br/>
     * width : <br/>
     * height : <br/>
     * title : <br/>
     * url : 必填参数 <br/>
     * onLoad : function 加载完窗口内容后执行<br/>
     * 
     * 
     */
    function createWindow(params){
    	$("<div>").css({padding:"5px"}).window({
    		width : params.width?params.width:"80%",
    		height : params.height?params.height:"80%",
    		modal:true,
    		title : params.title?params.title:" ",
    		href : params.url,
		    onClose : function(){
		    	$(this).window("destroy");
		    },
		    onLoad : function(){
		    	if(params.onLoad){
		    		params.onLoad.call(this);
		    	}
		    }
    	}).window("open");
    }
	
</script>