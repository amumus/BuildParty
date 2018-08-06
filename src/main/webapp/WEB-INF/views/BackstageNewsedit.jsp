<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="js/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="js/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="js/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="js/kindeditor/plugins/code/prettify.js"></script>
<form id="newsEditForm" name="example" method="post">
	 <input type="hidden" name="newsId" />
	<table>
		<tr>
			<td>标题：</td>
			<td><input class="easyui-textbox" type="text" name="title"
				data-options="required:true" style="width: 280px;"></input></td>
		</tr>
		<tr>
			<td>阅读量:</td>
			<td><input class="easyui-textbox" type="text" name="reading"
				 style="width: 280px;"></input></td>
		</tr>
		<tr>
			<td>图片:</td>
			<td><a href="javascript:void(0)"
				class="easyui-linkbutton onePicUpload">上传图片</a> <input type="hidden"
				name="image" /></td>
		</tr>
		<tr>
			<td>新闻内容:</td>
			<td><textarea name="content" cols="100" rows="8"
					style="width: 700px; height: 200px; visibility: hidden;"></textarea>

			</td>
		</tr>
	</table>
	<input type="button" name="button" value="提交内容" id="submitNewsEditForm" />
</form>
<script>
	var newsEditEditor;
	//编辑器参数
	var kingEditorParams = {
		//指定上传文件参数名称
		filePostName : "file",
		//指定上传文件请求的url。
		uploadJson : 'uploadImage',
		//上传类型，分别为image、flash、media、file
		dir : "image"
	};
	//页面初始化完毕后执行此方法
	$(function() {
		//创建富文本编辑器
		newsEditEditor = KindEditor.create("#newsEditForm [name=content]",
				kingEditorParams);
		/**
		 * 初始化单图片上传组件 <br/>
		 * 选择器为：.onePicUpload <br/>
		 * 上传完成后会设置input内容以及在input后面追加<img> 
		 */
		$(".onePicUpload").click(function() {
			var _self = $(this);
			KindEditor.editor(kingEditorParams).loadPlugin('image',function() {
				this.plugin.imageDialog({
					showRemote : false,
					clickFn : function(url, title,
							width,height,border,align) {
						var input = _self.siblings("input");
						input.parent().find("img").remove();
						input.val(url);
						input.after("<a href='"+url+"' target='_blank'><img src='"+url+"' width='80' height='50'/></a>");
						this.hideDialog();
					}
				});
			});
		});

		$("#submitNewsEditForm").click(
			function() {
				console.log('点击了submitNewsEditForm')
				if (!$('#newsEditForm').form('validate')) {
					$.messager.alert('提示', '表单还未填写完成!');
					return;
				}
				newsEditEditor.sync();

				$.post("news/edit", $("#newsEditForm").serialize(),
						function(data) {
							console.log('收到返回')
							if (data.code == 200) {
								$.messager.alert('提示', '修改成功!');
								$("#news_dg").datagrid("reload");
							}
						});
			})

	});
</script>
