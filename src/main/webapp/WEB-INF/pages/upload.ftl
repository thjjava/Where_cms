<#include "/common/common.ftl" />
<!DOCTYPE html>
<html>
	<@head.head basePath = "${basePath}"></@head.head>
	<style type="text/css">
		input,textarea{
			border:1px solid #ccc;
		}
	</style>
	<body>
		<div>
			<form name="upForm" action="${basePath}/file/upload" enctype="multipart/form-data" method="post""> 
				<input type="file" id="file" name="file" />
				<input type="submit" value="上传"/>
			</form>
		</div>
		<!-- 新增和修改 -->
		<div id="addWindow" class="easyui-window" title="管理员增加" closed="true" collapsible="false" minimizable="false" maximizable="false"
			iconCls="icon-add" style="width: 380px; height: 340px; display:none;" resizable="false">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false"style="padding: 10px; background: #fff; border: 1px solid #ccc;">
					<form action="${basePath}/admin/save" method="post" id="saveForm">
						<table width="100%" border="0" style="font-size: 13">
							<tr style="display:none">
								<td align="right">ID：<br></td>
								<td><input type="text" name="id" id="id" style="width:180"><br></td>
							</tr>
							<tr>
								<td align="right">帐号：<br></td>
								<td><input type="text" name="account" id="account" class="easyui-validatebox" required="true" validType="length[1,25]" invalidMessage="请输入管理员帐号..."  style="width:180"><br></td>
							</tr>
							<tr>
								<td align="right">密码：<br></td>
								<td><input type="password" name="password" id="password" class="easyui-validatebox" required="true" validType="length[1,25]" invalidMessage="请输入帐号密码..."  style="width:180"><br></td>
							</tr>
							<tr>
								<td align="right">确认密码：<br></td>
								<td>
									<input type="password" name="repassword" class="easyui-validatebox" required="true" missingMessage="两次密码输入一致..."  style="width:180">
								<br></td>
							</tr>
							<tr>
								<td align="right">性别：<br></td>
								<td>
								<input type="radio" name="sex" id="sex" value="0" checked="checked">男 
								<input type="radio" name="sex" id="sex" value="1">女<br></td>
							</tr>
							<tr>
								<td align="right">电话：<br></td>
								<td><input type="text" name="telephone" id="telephone" class="easyui-validatebox"  data-options="validType:'phoneRex',required:true" invalidMessage="请输入正确电话或手机格式" style="width:180"><br></td>
							</tr>
							<tr>
								<td align="right">邮件地址：<br></td>
								<td><input type="text" name="email" id="email" class="easyui-validatebox"	invalidMessage="请输入正确的邮箱地址..." data-options="required:true,validType:'email'" style="width:180"><br></td>
							</tr>
							<tr>
								<td align="right">城市：<br></td>
								<td><input type="text" name="city" id="city" class="easyui-validatebox" validType="length[1,50]" invalidMessage="请输入部门..."  style="width:180"><br></td>
							</tr>
							<tr>
								<td align="right">备注：<br></td>
								<td><input type="text" name="summary" id="summary" class="easyui-validatebox" validType="length[1,50]" invalidMessage="请输入部门..."  style="width:180"><br></td>
							</tr>
						</table>
					</form>
				</div>
				<div region="south" border="false" style="text-align: center; height: 30px; line-height: 30px;">
					<a class="easyui-linkbutton" href="javascript:void(0)" onclick="resetForm('saveForm');">重置</a>
					<a class="easyui-linkbutton" href="javascript:void(0)" onclick="submitForm()">提交</a>
					<a class="easyui-linkbutton" href="javascript:void(0)" onclick="closeDiv('addWindow');">取消</a>
				</div>
			</div>
		</div>
		
	</body>
	<@script.script  basePath="${basePath}"></@script.script>
</html>