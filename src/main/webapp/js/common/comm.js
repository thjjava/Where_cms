/**
 * 数据列表
 * @param {} requestUrl
 * @param {} tcolumn
 * @param {} tbar
 * @param {} id
 */
function queryInit(requestUrl,tcolumn,tbar,id){
	$("#"+id).datagrid({
		title:"数据列表",
		iconCls:'icon-search',
		pageSize: 10,
		pageList: [5,10,15,20,25,30],
		nowrap: false,
		striped: true,
		collapsible:true,
		url:requestUrl,
		loadMsg:'数据加载中......',   
		sortOrder: 'desc',
		remoteSort: false,
		fitColumns:true,
		singleSelect:true,
		idField:'id',  
		frozenColumns:[[{field:'id',checkbox:true}]],
		columns:tcolumn,
		pagination:true,
		rownumbers:true,
		toolbar:tbar,
		onClickRow: setButton
	});
	$("#"+id).datagrid('getPager').pagination({
	    beforePageText: '第',//页数文本框前显示的汉字
	    afterPageText: '页    共 {pages} 页',
	    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		onBeforeRefresh:function(pageNumber, pageSize){
		    $(this).pagination('loading');
		    $(this).pagination('loaded');
		}
	});
}
function setButton(){}
//判断值是否为“-”
function checknull(val){
	if(val=='-'){
	   val="";
    }
	return val;
}
		
//打开 strs div id 如：#divid1
function openDiv(id){
	setWindow(id);
	$('#'+id).css('display','block');
	$('#'+id).window('open');
}

//关闭 strs div id 如：#divid1
function closeDiv(id){
	$('#'+id).window('close');
}

//设置窗体
function setWindow(id){
	var height=$('#'+id).height();
	var width=$('#'+id).width();
	$('#'+id).window({
	 top:($(document).height()-height) * 0.5,   
     left:($(document).width()-width) * 0.5,
     modal:true,
     draggable:true,
     shadow:true
	});
}
           
//参数加密
function escapeParam(value){
   return escape(value);
}

//重置  id 表单id
function resetForm(id) {
	$('#'+id).each(function(){
		this.reset();
	});
} 


/**
 * 格式化性别
 * @param {} val
 * @param {} rec
 * @return {}
 */
function formatSex(val,rec){  
    if(rec.sex==0){
    	val = "男";  
    }else if(rec.sex==1){
    	val = "女";  
    }
	return val ;
}

Date.prototype.format = function(fmt){ 
	//author: meizz    
	var o = {   
		"M+" : this.getMonth()+1,                 //月份    
		"d+" : this.getDate(),                    //日    
		"h+" : this.getHours(),                   //小时    
		"m+" : this.getMinutes(),                 //分    
		"s+" : this.getSeconds(),                 //秒    
		"q+" : Math.floor((this.getMonth()+3)/3), //季度    
		"S"  : this.getMilliseconds()             //毫秒    
	};   
	if(/(y+)/.test(fmt))   
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		for(var k in o)   
		if(new RegExp("("+ k +")").test(fmt))   
		fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		return fmt;   
}
