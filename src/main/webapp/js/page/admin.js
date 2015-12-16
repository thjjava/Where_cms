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
			{field:'account',title:'帐号',width:120,sortable:true,align:'center'},
			{field:'telephone',title:'电话',width:120,sortable:true,align:'center'},
			{field:'email',title:'邮箱',width:120,sortable:true,align:'center'},
			{field:'city',title:'城市',width:120,sortable:true,align:'center'},
			{field:'sex',title:'性别',width:60,sortable:true,align:'center',
				formatter:function(val,rec){
					if(rec.sex==0){
				    	val = "男";  
				    }else if(rec.sex==1){
				    	val = "女";  
				    }
					return val ;
				}},
				{field:'level',title:'级别',width:90,sortable:true,align:'center',
					formatter:function(val,rec){
						if(rec.level == 0){
					    	val = "<font color='red'>超级管理员</font>";  
					    }else if(rec.level == 1){
					    	val = "普通管理员";  
					    }else{
					    	val = "普通管理员";
					    }
						return val ;
					}},
			{field:'summary',title:'备注',width:120,sortable:true,align:'center'},
			{field:'addtime',title:'开户时间',width:120,sortable:true,align:'center',
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
	queryInit('/Where_cms/admin/list',tcolumn,tbar,render);
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
	var account=$("#account").val();
	if(account==''){
		alert("请输入帐号！");
		return ;
	}
	var userName=$("#userName").val();
	if(userName==''){
		alert("请输入用户名！");
		return ;
	}
	var url="";
	var adminid=$("#id").val();
	if(adminid==''){
		url="/Where_cms/admin/save";
	}else{
		url="/Where_cms/admin/update";
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
	     		$.messager.alert('提示',"该账户已存在!");
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
	var queryUrl='/Where_cms/admin/getbyid?id='+rows[0].id;
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
				$.post("/Where_cms/admin/delete",{"id":ids},function(data){
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
				$('input[name="account"]').val(obj.account);
				$('input[name="password"]').val(obj.password);
				$('input[name="repassword"]').val(obj.password);
				$('input[name="city"]').val(obj.city);
				$('input[name="telephone"]').val(obj.telephone);
				$('input[name="email"]').val(obj.email);
				$("[value = "+obj.sex+"]:radio").attr("checked", true);
				$('select[name="level"]').val(obj.level);
				$('input[name="summary"]').val(obj.summary);
				openDiv(win);
			}else{
				$.messager.alert('提示','信息不存在！');
			}
		}
  	});
}
