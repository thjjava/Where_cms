var render="datalist";
var win="addWindow";
var wform="saveForm";
var qin="queryWindow";
var qform="queryForm";

/**
 * 数据列表
 * @type 
 */
var tcolumn=[[
			{field:'name',title:'名称',width:120,sortable:true,align:'center'},
			{field:'url',title:'地址',width:120,sortable:true,align:'center'},
			{field:'pid',title:'类型',width:120,sortable:true,align:'center',
				formatter:function(val,rec){
					if(rec.pid == 0){
						return '<font color="red">父菜单</font>';
					}else {
						return '<font color="green">子菜单</font>';
					}
				}},
			{field:'addtime',title:'添加时间',width:120,sortable:true,align:'center',
					formatter:function(value,row,index){  
                        var date = new Date(value);  
                        return date.format("yyyy-MM-dd hh:mm:ss");  
                     } 
				}
			]];
			
/**
 * 菜单栏
 * @type 
 */
var tbar=[{ 
			id:'btnadd',
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				resetForm(wform);
				openDiv(win);
			}
		 },'-',{ 
			id:'btnedit',
			text:'修改',
			iconCls:'icon-edit',
			handler:edit
		 },'-',{ 
			id:'btnremove',
			text:'删除',
			iconCls:'icon-remove',
			handler:deleteobj
		 },'-',{
			id:'btnsave',
			text:'查询',
			iconCls:'icon-search',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				resetForm(qform);
				openDiv(qin);
			}
		 }];		
		 
/**
 * 加载初始化
 */
$(function(){ 
	init();
}); 

/**
 * 刷新列表
 */
function init(){
	queryInit('/Where_cms/menus/list',tcolumn,tbar,render);
}

/**
 * 增加和修改操作
 * @param {} id
 * @param {} windowid
 * @param {} urls
 * @param {} listurl
 * @return {Boolean}
 */
function submitForm(){
if($('#saveForm').form('validate')){
	var account=$("#name").val();
	if(account==''){
		alert("请输入名称！");
		return ;
	}
	var url="";
	var adminid=$("#id").val();
	if(adminid==''){
		url="/Where_cms/menus/save";
	}else{
		url="/Where_cms/menus/update";
	}
	var params = $('#'+wform).serializeArray();  
	$('#'+wform).form('submit', {
	    url:url,
	    data:params,
	    onSubmit: function(){
	    },   
		success:function(data){
	     	if("1"==data){
	     		$.messager.alert('提示',"更新数据成功!");
		     	resetForm(wform);
				closeDiv(win);
				init();
	     	}else if ("2"==data) {
	     		$.messager.alert('提示',"该菜单已存在!");
			}else {
	     		$.messager.alert('提示',"更新数据失败!");
			}
	    }
	})
  }
}

/**
 * 查询
 * @return {Boolean}
 */
function query(){
    var account=escapeParam($("#query_account").val());
    var addTimeStart=$("#addTimeStart").val();
    var addTimeEnd=$("#addTimeEnd").val();
    if(addTimeEnd !=""){
	    if(addTimeEnd<addTimeStart){
     	   $("#info").html("起始时间要小于结束时间!").css("color","red");;
     	   return false;
	    }
    }
	$('#'+render).datagrid('reload', {"account":account,"startTime":addTimeStart,"endTime":addTimeEnd});
	$('#queryWindow').window('close');
}

/**
 * 修改
 */
function edit(){
	resetForm(wform);
	var rows = $('#'+render).datagrid('getSelections');
	if(rows.length==0 || rows.length>1){
		alert("请选择一条记录！");
		return;
	}
	var queryUrl='/Where_cms/menus/getbyid?id='+rows[0].id;
	queryObjectbyID(queryUrl);
}

/**
 * 删除
 */
function deleteobj(){
	$.messager.confirm('系统提示', '您确定要删除吗?', function(r) {
        if (r) {
            var rows = $('#'+render).datagrid('getSelections');
            var ids="";
			if(rows.length>0){
				for(var i=0;i<rows.length;i++){
					if(i==0){
						ids=rows[i].id;
					}else{
						ids+="_"+rows[i].id;
					}
				}
				$.post("/Where_cms/menus/delete",{"id":ids},function(data){
					if("1"==data){
			     		$.messager.alert('提示',"删除数据成功!");
			     		init();
			     	}else {
			     		$.messager.alert('提示',"删除数据失败!");
					}
				}) 
			}
        }
    });
}

/**
 * 获取详细信息
 * @param {} url
 * @param {} win
 */
function queryObjectbyID(url){
	$.ajax({
		type:'POST',
		url:url,
		success:function(msg){
			var obj=eval("("+msg+")");
			if(obj !=''){
				$('input[name="id"]').val(obj.id);
				$('input[name="name"]').val(obj.name);
				$('input[name="url"]').val(obj.url);
				$('select[name="pid"]').val(obj.pid);
				openDiv(win);
			}else{
				$.messager.alert('提示','信息不存在！');
			}
		}
  	});
}
