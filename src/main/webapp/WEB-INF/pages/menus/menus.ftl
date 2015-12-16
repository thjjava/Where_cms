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
		<table  id="datalist"></table>
		<!-- 新增和修改 -->
		<div id="addWindow" class="easyui-window" title="菜单增加" closed="true" collapsible="false" minimizable="false" maximizable="false"
			iconCls="icon-add" style="width: 380px; height: 340px; display:none;" resizable="false">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false"style="padding: 10px; background: #fff; border: 1px solid #ccc;">
					<form action="${basePath}/menus/save" method="post" id="saveForm">
						<table width="100%" border="0" style="font-size: 13">
							<tr style="display:none">
								<td align="right">ID：<br></td>
								<td><input type="text" name="id" id="id" style="width:180"><br></td>
							</tr>
							<tr>
								<td align="right">父菜单：<br></td>
								<td>
									<select name="pid" id="pid" style="width: 130px;">
										<option value="0">请选择</option>
										<#if mList?? && 0 lt mList?size>
											<#list mList as menu>
												<option value="${menu.id}">${menu.name} </option>
											</#list>
										</#if>
									</select>
								<br></td>
							</tr>
							<tr>
								<td align="right">名称：<br></td>
								<td><input type="text" name="name" id="name" class="easyui-validatebox" required="true" validType="length[1,25]" invalidMessage="请输入名称..."  style="width:180"><br></td>
							</tr>
							<tr>
								<td align="right">地址：<br></td>
								<td><input type="text" name="url" id="url" class="easyui-validatebox" invalidMessage="请输入地址..."  style="width:180"><br></td>
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
		
		<!-- 查询 -->
		<div id="queryWindow" class="easyui-window" title="输入查询条件" closed="true" collapsible="false" minimizable="false" maximizable="false"
			iconCls="icon-search" style="width: 550px; height: 250px; display:none;" resizable="false">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
					<form method="post" id="queryForm">
						<table style="font-size: 13">
							<tr height="30px;">
								<td align="right">名称：</td>
								<td><input type="text" id="query_account" name="account"  style="width:180"/></td>
							</tr>
							<tr>
								<td align="right">开户时间：</td>
								<td>
									<input type="text" size="22" readonly="readonly"  id="addTimeStart"  name="addTimeStart" onfocus="WdatePicker({startDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d',alwaysUseStartDate:true})" class="Wdate" style="width:180"/>到
									<input type="text" size="22" readonly="readonly"  id="addTimeEnd"  name="addTimeEnd" onfocus="WdatePicker({startDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d',alwaysUseStartDate:true})" class="Wdate" style="width:180"/>
								</td>
							</tr>
							<tr>
								<td colspan="2"><div name="info" id="info"></div></td>
							</tr>
						</table>
					</form>
				</div>
				<div region="south" border="false" style="text-align: center; height: 30px; line-height: 30px;">
					<a class="easyui-linkbutton" href="javascript:resetForm('queryForm');">重置</a>
					<a class="easyui-linkbutton" href="javascript:query();">查询</a>
					<a class="easyui-linkbutton" href="javascript:closeDiv('queryWindow');">关闭</a>
				</div>
			</div>
		</div>
	</body>
	<@script.script  basePath="${basePath}">
		<script type="text/javascript" src="${basePath}/js/page/menus.js"></script>
	</@script.script>
</html>