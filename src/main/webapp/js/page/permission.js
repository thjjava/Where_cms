$(function(){
		initLeftMenu();
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