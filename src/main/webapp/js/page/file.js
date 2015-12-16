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
			{field:'filename',title:'文件名',width:120,sortable:true,align:'center'},
			{field:'filepath',title:'路径',width:120,sortable:true,align:'center'},
			{field:'subject',title:'标题',width:120,sortable:true,align:'center'},
			{field:'flag',title:'审核',width:60,sortable:true,align:'center',
				formatter:function(val,rec){
					if(rec.flag==0){
				    	val = "通过";  
				    }else if(rec.flag==1){
				    	val = "封禁";  
				    }
					return val ;
				}},
				{field:'status',title:'级别',width:90,sortable:true,align:'center',
					formatter:function(val,rec){
						if(rec.status==0){
					    	val = "个人";  
					    }else if(rec.status==1){
					    	val = "公共";  
					    }
						return val ;
					}},
			{field:'summary',title:'描述',width:120,sortable:true,align:'center'},
			{field:'uploadtime',title:'上传时间',width:120,sortable:true,align:'center',
					formatter:function(value,row,index){
						if(value != undefined){
							var date = new Date(value);  
	                        return date.format("yyyy-MM-dd hh:mm:ss");  
						}
                     } 
				}
			]];
			
/**
 * 菜单栏
 * @type 
 */
var tbar=[/*{ 
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
		 },'-',*/{ 
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
	queryInit('/Where_cms/file/list',tcolumn,tbar,render);
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
	var queryUrl='/Where_cms/file/getbyid?id='+rows[0].id;
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
				$.post("/Where_cms/file/delete",{"id":ids},function(data){
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
