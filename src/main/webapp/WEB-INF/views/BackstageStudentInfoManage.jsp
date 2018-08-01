<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="tb">
	<div>
		<a href="javascript:openStudentAddDialog()" class="easyui-linkbutton"
			iconCls="icon-add" plain="true">添加</a> <a
			href="javascript:openStudentModifyDialog()" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true">修改</a> <a
			href="javascript:deleteStudent()" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>
		&nbsp;学号：&nbsp;<input type="text" name="id" id="search_id" size="10" class="easyui-numberbox"/>
		&nbsp;姓名：&nbsp;<input type="text" name="name" id="search_name" size="10" />
		&nbsp;政治面貌：&nbsp;<select class="easyui-combobox" id="search_politicalstatus"
			name="politicalstatus" editable="false" panelHeight="auto">
			<option value="">请选择...</option>
			<option value="党员">党员</option>
			<option value="预备党员">预备党员</option>
			<option value="团员">团员</option>
			<option value="群众">群众</option>
		</select> <a href="javascript:searchStudent()" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>

<div id="dlg" class="easyui-dialog"
	style="width: 570px; height: 350px; padding: 10px 20px" closed="true"
	buttons="#dlg-buttons">
	<form id="fm" method="post">
		<table cellspacing="5px;">
			<tr>
				<td>学号：</td>
				<td><input type="text" name="id" id="id"
					class="easyui-validatebox" required="true" class="easyui-numberbox"/></td>
				
				<td>姓名：</td>
				<td><input type="text" name="name" id="name"
					class="easyui-validatebox" required="true" /></td>
			</tr>
			<tr>
				<td>政治面貌：</td>
				<td><select class="easyui-combobox" id="politicalstatus" name="politicalstatus"
					editable="false" panelHeight="auto" style="width: 155px">
						<option value="">请选择...</option>
						<option value="党员">党员</option>
						<option value="预备党员">预备党员</option>
						<option value="团员">团员</option>
						<option value="群众">群众</option>
				</select></td>
			</tr>
			<tr>
				<td>Email：</td>
				<td><input type="text" name="email" id="email"
					class="easyui-validatebox" validType="email" /></td>
				<td>密码：</td>
				<td><input type="text" name="password" id="password"
					class="easyui-validatebox"/></td>
			</tr>
		</table>
	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveStudent()" class="easyui-linkbutton"
		iconCls="icon-ok">保存</a> <a href="javascript:closeStudentDialog()"
		class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>	
	
	
<table id="dg" title="学生信息" class="easyui-datagrid" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'student/list',method:'get',pageSize:10">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',width:150">学号</th>
			<th data-options="field:'name',width:100">姓名</th>
			<th data-options="field:'password',width:150">密码</th>
			<th data-options="field:'politicalstatus',width:100">政治面貌</th>
			<th data-options="field:'email',width:200">Email</th>
		</tr>
	</thead>
</table>



<script type="text/javascript">
	var url;

	function deleteStudent() {
		var selectedRows = $("#dg").datagrid('getSelections');
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
				$.post("student/delete", {
					ids : ids
				}, function(result) {
					if (result.code == 200) {
						$.messager.alert("系统提示", "您已成功删除<font color=red>"
								+ selectedRows.length + "</font>条数据！");
						$("#dg").datagrid("reload");
					} else {
						$.messager.alert('系统提示', result.data.message);
					}
				});
			}
		});
	}

	function searchStudent() {
		$('#dg').datagrid('load', {
			id : $('#search_id').val(),
			name : $('#search_name').val(),
			politicalstatus : $('#search_politicalstatus').combobox("getValue")
		});
	}

	function openStudentAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加学生信息");
		url = "student/add";
	}

	function saveStudent() {
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				if ($('#politicalstatus').combobox("getValue") == "") {
					$.messager.alert("系统提示", "请选择政治面貌");
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
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}

	function resetValue() {
		$("#id").val("");
		$("#name").val("");
		$("#politicalstatus").combobox("setValue", "");
		$("#email").val("");
		$("#password").val("");
	}

	function closeStudentDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}

	function openStudentModifyDialog() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1 ) {
			$.messager.alert("系统提示", "必须选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑学生信息");
		$("#fm").form("load", row);
		url = "student/edit";
	}
</script>