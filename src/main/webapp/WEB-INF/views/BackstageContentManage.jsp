<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="js/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="js/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="js/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="js/kindeditor/plugins/code/prettify.js"></script>
<div id="tb">
	<div>
		<a href="javascript:openContentAddDialog()" class="easyui-linkbutton"
			iconCls="icon-add" plain="true">添加</a> <a
			href="javascript:openContentModifyDialog()" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true">修改</a> <a
			href="javascript:deleteContent()" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true">删除</a>
	</div>
</div>

<div id="content_dlg" class="easyui-dialog"
	style="width: 700px; height: 500px; padding: 10px 20px" closed="true"
	buttons="#dlg-buttons">
	<form id="content_form" method="post">
		<input type="hidden" name="id" id="id"/>
		<table cellspacing="5px;">
			<tr>
				<td>标题：</td>
				<td  colspan="3">
				<input type="text" name="title" id="title"
					class="easyui-validatebox" required="true" style="width:300px;"/>
				</td>
			</tr>
			
			<tr>
				<td>图片:</td>
				<td><input type="button" id="uploadContentImage" value="选择图片" /></td>
				<td colspan="2">
					<a><img src="" height="100"></a>
					<input type="hidden"name="image" id="image" />
				</td>
			</tr>
			<tr>
				<td>内容类型：</td>
				<td><select class="easyui-combobox" id="contenttype" name="contenttype"
					editable="false" panelHeight="auto" style="width: 155px">
						<option value="">请选择...</option>
						<option value="1">新闻</option>
				</select></td>
				<td>内容id：</td>
				<td><input type="text" name="targetid" id="targetid"
					class="easyui-validatebox" required="true" /></td>
			</tr>
		</table>
	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveContent()" class="easyui-linkbutton"
		iconCls="icon-ok">保存</a> <a href="javascript:closeContentDialog()"
		class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>	
	
	
<table id="content_dg" title="首页轮播图信息" class="easyui-datagrid" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'content/list',method:'get',pageSize:10">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',width:100">ID</th>
			<th data-options="field:'title',width:200">标题</th>
			<th data-options="field:'image',width:200">图片</th>
			<th data-options="field:'created',width:200">创建日期</th>
			<th data-options="field:'contenttype',width:100">内容类型</th>
			<th data-options="field:'targetid',width:100">内容Id</th>
		</tr>
	</thead>
</table>



<script type="text/javascript">
	var url;

	function deleteContent() {
		var selectedRows = $("#content_dg").datagrid('getSelections');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var ids = [];
		for (var i = 0; i < selectedRows.length; i++) {
			ids.push(selectedRows[i].id);
		}
		ids = ids.join(",");
		$.messager.confirm("系统提示", "您确认要删掉这<font color=red>"
				+ selectedRows.length + "</font>条数据吗？", function(r) {
			if (r) {
				$.post("content/delete", {
					ids : ids
				}, function(result) {
					if (result.code == 200) {
						$.messager.alert("系统提示", "您已成功删除<font color=red>"
								+ selectedRows.length + "</font>条数据！");
						$("#content_dg").datagrid("reload");
					} else {
						$.messager.alert('系统提示', result.data.message);
					}
				});
			}
		});
	}

	function openContentAddDialog() {
		$("#content_dlg").dialog("open").dialog("setTitle", "添加学生信息");
		resetValue();
		url = "content/add";
		kindEditorReady()
	}

	function saveContent() {
		$("#content_form").form("submit", {
			url : url,
			onSubmit : function() {
				if ($('#contenttype').combobox("getValue") == "") {
					$.messager.alert("系统提示", "请选择内容类型");
					return false;
				}
				return $(this).form("validate");
			},
			success : function(result) {
				if (result.code==500) {
					$.messager.alert("系统提示", result.data.message);
					return;
				} else {
					$.messager.alert("系统提示", "保存成功");
					resetValue();
					$("#content_dlg").dialog("close");
					$("#content_dg").datagrid("reload");
				}
			}
		});
	}

	function resetValue() {
		$("#id").val("");
		$("#title").val("");
		$("#contenttype").combobox("setValue", "");
		$("#image").val("");
		$("#targetid").val("");
		$("#content_form img").attr("src","");
	}

	function closeContentDialog() {
		resetValue();
		$("#content_dlg").dialog("close");
	}

	function openContentModifyDialog() {
		var selectedRows = $("#content_dg").datagrid('getSelections');
		if (selectedRows.length != 1 ) {
			$.messager.alert("系统提示", "必须选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#content_dlg").dialog("open").dialog("setTitle", "编辑轮播图信息");
		$("#content_form").form("load", row);
		$("#content_form img").attr("src",selectedRows[0].image);
		url = "content/edit";
		kindEditorReady();
	}

	
	//编辑器参数
	var kindEditorParams = {
		//指定上传文件参数名称
		filePostName : "file",
		//指定上传文件请求的url。
		uploadJson : 'uploadImage',
		//上传类型，分别为image、flash、media、file
		dir : "image"
	};
	function kindEditorReady(){
		 /**
	     * 初始化单图片上传组件 <br/>
	     * 选择器为：uploadContentImage <br/>
	     * 上传完成后会设置input内容以及在input后面追加<img> 
	     */
		$("#uploadContentImage").click(function(){
			var _self = $(this);
			KindEditor.editor(kindEditorParams).loadPlugin('image', function() {
				this.plugin.imageDialog({
					showRemote : false,
					clickFn : function(url, title, width, height, border, align) {
						_self.parent().parent().find('img').attr("src",url);
						var input = $('#image');
						input.val(url);
						this.hideDialog();
					}
				});
			});
		});
	}
	
	
</script>