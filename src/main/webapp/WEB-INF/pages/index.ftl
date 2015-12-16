<#include "/common/common.ftl" />
<!DOCTYPE html>
<html>
	<@head.head basePath="${basePath}"></@head.head>
	<style>
		a{
			text-decoration: none;
		}
	</style>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:50px;text-align: right;padding-top: 30px;">
		欢迎${Account}登录,<a href="${basePath}/logout">安全登出</a>
	</div>
	<div data-options="region:'south',split:true" style="height:30px;text-align: center;">
		<@footer.footer></@footer.footer>
	</div>
	<div data-options="region:'east',split:true" title="日历" style="width:180px;">
		<div class="easyui-calendar" style="width:auto;height:auto;"></div>
	</div>
	<div data-options="region:'west',split:true" title="菜单" style="width:180px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<ul id="tt" class="easyui-tree" data-options="url:null,method:'get',animate:true,lines:true"></ul>
		</div>
	</div>
	<div data-options="region:'center',title:'功能区'">
		<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div title="欢迎使用" style="padding:10px;">
				<p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
				<ul>
					<li>easyui is a collection of user-interface plugin based on jQuery.</li>
					<li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
					<li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
					<li>complete framework for HTML5 web page.</li>
					<li>easyui save your time and scales while developing your products.</li>
					<li>easyui is very easy but powerful.</li>
				</ul>
			</div>
		</div>
	</div>
	<!--加载内容页面结束-->
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
</body>
<@script.script  basePath="${basePath}"></@script.script>
<script type="text/javascript">
	$(function(){
		initLeftMenu();
		tabClose();
		tabCloseEven();
	})
	
	function initLeftMenu(){
		$('#tt').tree({
				url: '${basePath}/getMenu?user=${Account}',//访问该url，获得一个以json格式返回的数据,该URL可为后台方法的访问地址，但是返回数据格式跟tree_data1.json一样
				onClick:function(node){
					$(this).tree('toggle', node.target);
					if(node.url != undefined){
						addTab(node.text,node.url);
					}
				}
			});
	}
	function addTab(subtitle,url){
		if(!$('#tabs').tabs('exists',subtitle)){
			$('#tabs').tabs('add',{
				title:subtitle,
				content:createFrame(url),
				closable:true,
				width:$('#mainPanle').width()-10,
				height:$('#mainPanle').height()-26
			});
		}else{
			$('#tabs').tabs('select',subtitle);
		}
		tabClose();
	}

	function createFrame(url)
	{
		var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		return s;
	}
	
	function tabClose()
	{
		/*双击关闭TAB选项卡*/
		$(".tabs-inner").dblclick(function(){
			var subtitle = $(this).children("span").text();
			$('#tabs').tabs('close',subtitle);
		})
	
		$(".tabs-inner").bind('contextmenu',function(e){
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
			
			var subtitle =$(this).children("span").text();
			$('#mm').data("currtab",subtitle);
			
			return false;
		});
	}
	
	//绑定右键菜单事件
function tabCloseEven()
{
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});	
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t!=currtab_title)
				$('#tabs').tabs('close',t);
		});	
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}
</script>
</html>
