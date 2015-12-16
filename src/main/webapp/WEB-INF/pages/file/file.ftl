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
								<td align="right">上传时间：</td>
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
		<script type="text/javascript" src="${basePath}/js/page/file.js"></script>
	</@script.script>
</html>