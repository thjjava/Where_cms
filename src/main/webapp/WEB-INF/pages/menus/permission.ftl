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
		<div style="height: 20px;"></div>
		<div class="easyui-layout" fit="true" style="padding-top: 20px;">
			<div region="west" border="true" style="width:200px;">
				<div class="easyui-accordion" data-options="fit:true,border:false">
					<ul id="adminTree" class="easyui-tree" data-options="url:null,method:'get',animate:true,lines:true"></ul>
				</div>
			</div>
			<div region="center" border="true">
				<div style="margin: 10px;border-bottom: 1px solid;">
					<a href="#" onclick="saveMenus()">提交</a>
				</div>
				<div class="easyui-accordion" data-options="fit:true,border:false">
					<ul id="menusTree" class="easyui-tree" data-options="url:null,method:'get',animate:true,lines:true,checkbox:true"></ul>
				</div>
			</div>
		</div>
		
	</body>
	<@script.script  basePath="${basePath}">
		<script type="text/javascript" src="${basePath}/js/page/permission.js"></script>
	</@script.script>
	<script type="text/javascript">
		$(function(){
			initLeftMenu();
		})
		
		function initLeftMenu(){
			$('#adminTree').tree({
				url: '${basePath}/menus/getAdmin',//访问该url，获得一个以json格式返回的数据,该URL可为后台方法的访问地址，但是返回数据格式跟tree_data1.json一样
				onClick:function(node){
					$(this).tree('toggle', node.target);
					if(node.url != undefined){
						getMenus(node.url);
					}
				}
			});
		}
		
		function getMenus(url){
			$('#menusTree').tree({
				url: url,
				checkbox: true,
				cascadeCheck: false
			});
		}
		
		function getChecked(){
            var arr = [];
            var checkeds = $('#menusTree').tree('getChecked', 'checked');
            for (var i = 0; i < checkeds.length; i++) {
                arr.push(checkeds[i].id);
            }
            return arr.join(',');
        }
		
		function saveMenus(){
			var arr = [],aid=0;
            var checkeds = $('#menusTree').tree('getChecked', 'checked');
            if (checkeds.length > 0) {
            	 for (var i = 0; i < checkeds.length; i++) {
                     arr.push(checkeds[i].id);
                 }
            	 aid = checkeds[0].aid;
			}else{
			    var root = $('#menusTree').tree('getRoot'); 
			    aid = root.aid;
			}
			$.post("${basePath}/menus/saveMenus",{"menus":arr.join(','),"aid":aid},function(data){
				if(data == "SUCCESS"){
					alert("保存成功");
				}else{
					alert("保存失败!");
				}
			})
		}
	</script>
</html>