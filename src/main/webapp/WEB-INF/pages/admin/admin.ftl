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
								<td align="right">类型：<br></td>
								<td>
									<select name="level" id="level" style="width: 130px;">
										<option value="-1">请选择</option>
										<option value="0">超级管理员 </option>
										<option value="1">普通管理员 </option>
									</select>
								<br></td>
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
		
		<!-- 查询 -->
		<div id="queryWindow" class="easyui-window" title="输入查询条件" closed="true" collapsible="false" minimizable="false" maximizable="false"
			iconCls="icon-search" style="width: 550px; height: 250px; display:none;" resizable="false">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
					<form method="post" id="queryForm">
						<table style="font-size: 13">
							<tr height="30px;">
								<td align="right">帐号：</td>
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
		<script type="text/javascript" src="${basePath}/js/page/admin.js"></script>
	</@script.script>
	<script type="text/javascript">
		 //自定义验证
		  $.extend($.fn.validatebox.defaults.rules, {
			  phoneRex: {
			    validator: function(value){
			    var rex=/^1[3-8]+\d{9}$/;
			    //var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
			    //区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
			    //电话号码：7-8位数字： \d{7,8
			    //分机号：一般都是3位数字： \d{3,}
			     //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/		 
			    var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
			    if(rex.test(value)||rex2.test(value))
			    {
			      // alert('t'+value);
			      return true;
			    }else
			    {
			     //alert('false '+value);
			       return false;
			    }
			      
			    },
			    message: '请输入正确电话或手机格式'
			  }
		});
	</script>
</html>