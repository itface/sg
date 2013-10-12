<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../../../inc/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link href="${ctx}/css/sapgarden/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/sapgarden/garden.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/icon.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.jqgrid.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.ui.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/extendJqgrid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/easyui-lang-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/jquery.easyui.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/lhgdialog/lhgdialog.js?skin=default'/>" type="text/javascript"></script>
<style>
.toolbar_text {
	font-family:微软雅黑;
	font-size:12px;
	line-height:30px;
	float:left;
	padding:0px 5px;
	font-weight:bold;
}
.fm-button-icon-left{
	display:none;
}
</style>
</head>
<body>
<div class="toolbar">
  <div class="prompt_message">关键功能：本功能设置当前数据和SAP同步的方式，以及同步的内容。根据业务需要非关键字段可以不同步，但建议全部同步。</div>
  <div class="toolbar_left" style="float:none">
  		<div style="font-family:微软雅黑;font-size:12px;line-height:30px;float:left;padding:0px 5px;font-weight:bold;">当前运行状态：${jobMemo}</div>
	   <div style='float:right'><a href="#" style="margin-right:0px" class="btn"  onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'" onclick="zwyd();">变更运行状态</a></div> 
   </div>
</div>
<div style="padding:5px 10px; clear:both">
	<table id="list"></table>
</div>
</body>
<script>
function zwyd() {
	var dialog=$.dialog({
					title:'变更运行状态',
					width: 600,
			    	height: '240px',
			    	lock: true,
					resize:false,
			          max: false,
			          min: false,
					content: 'url:${ctx}/application/commonScheduling/time/company'
				});
	$.dialog.data('dialog',dialog);
	$.dialog.data('reloadPage',reloadPage);
}
function reloadPage(){
	location.reload(true);
}
$(function(){
	var type='${type}';
	init();
	function init(){
		createDbReflectGrid();
	}
	function createDbReflectGrid(){
		//var basePath = '${ctx}/application/synConfig/${type}/runtimeConfig/columnInfo';
		var basePath = '${ctx}/application/common/columnInfo/company';
		var checked = false;
		var config ={
			id:'list',
			caption:'同步字段定义-公司代码（COMPANY）',
			rownum:100,
			height:300,
			width:900,
			autoWidth:true,
			editable:true,
			datatype:'json',
			multiselect:true,
			multiselectWidth:60,
			add:false,
			del:false,
			refresh:false,
			editable:false,
			eventModels:{
			    gridComplete:function(){
			    	var rowData= jQuery("#list").jqGrid('getRowData');
			    	//var numberOfRecords  = jQuery("#list").jqGrid('getGridParam',"records");
					for(var i=0;i<rowData.length;i++){
						var rowId = rowData[i].id;
						var status = rowData[i].status;
						var ifkey = rowData[i].ifkey;
						if(status=='ACTIVATE'){
							//jQuery('#list').jqGrid('setSelection',rowId);
							$('#jqg_list_'+rowId).attr('checked','true');
						}
						if(ifkey==1){
							$('#jqg_list_'+rowId).attr('disabled','true');
						}
					}
					$('#cb_list').hide();
			    },
			    beforeSelectRow:function(rowid,e){
			    	if($(e.target).attr('type')=='checkbox'){
			    		var c = $('#jqg_list_'+rowid).attr('checked');
				    	if(confirm('是否要更改同步状态')){
				    		checked = c;
				    		checked = $('#jqg_list_'+rowid).attr('checked');
				    		return true;
				    	}else{
				    		$('#jqg_list_'+rowid).attr('checked',c?false:true);
				    		checked = $('#jqg_list_'+rowid).attr('checked');
				    		return false;
				    	}
			    	}
			    	checked = $('#jqg_list_'+rowid).attr('checked');
			    	return true;
			    	/*
			    	var c = $('#jqg_list_'+rowid).attr('checked');
			    	if(confirm('是否要更改同步状态')){
			    		checked = c;
			    		return true;
			    	}else{
			    		$('#jqg_list_'+rowid).attr('checked',c?false:true);
			    		return false;
			    	}*/
			    },
			    onSelectRow:function(rowid,status,e){
			    	//alert($(e.target).attr('type')+":"+checked+":"+status);
			    	if($(e.target).attr('type')=='checkbox'){
			    		if(checked==true||checked=='checked'){
			    			$('#jqg_list_'+rowid).attr('checked',true);
			    		}else{
			    			$('#jqg_list_'+rowid).attr('checked',false);
			    		}
				    	var rowData= jQuery("#list").jqGrid('getRowData',rowid);
				    	var ifkey = rowData.ifkey;
				    	var rowId = rowData.id;
				    	if(ifkey==1){
							$('#jqg_list_'+rowId).attr('checked','checked');
						}else{
							$.ajax({
								url:'${ctx}/application/common/columnInfo/updateStatus',
								data:{id:rowid,status:(checked=='checked'?'ACTIVATE':'INACTIVE')},
								success:function(){
									alert('设置成功');
								}
							});
						}
			    	}else{
			    		if(checked==true||checked=='checked'){
			    			$('#jqg_list_'+rowid).attr('checked',true);
			    		}else{
			    			$('#jqg_list_'+rowid).attr('checked',false);
			    		}
			    	}
			    }
			},
			columnNames:['ID','SAP字段','SAP字段名称','Garden字段','Garden字段名称','状态','类型','ifKey','是否是查询字段','查询类型'],
			columnModel:[
		   		{name:'id',index:'id',hidden:true, width:1,key:true},
		   		{name:'sourceColumn',index:'sourceColumn',width:200,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'sourceColumnName',index:'sourceColumnName',width:200,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'targetColumn',index:'targetColumn', width:200,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'targetColumnName',index:'targetColumnName',width:200,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'status',index:'status',editable:true,edittype:'select',hidden:true,width:100,editoptions:{value:"ACTIVATE:激活;INACTIVE:非激活"}},
		   		{name:'businesstype',index:'businesstype',hidden:true,editable:true},
		   		{name:'ifkey',index:'ifkey',hidden:true,editable:true,edittype:'select',width:100,editrules:{edithidden:true},editoptions:{value:"0:非必须同步;1:必须同步"}},
		   		{name:'searchable',index:'searchable',hidden:true,editable:true,edittype:'select',editrules:{edithidden:true},width:100,editoptions:{value:"0:否;1:是"}},
		   		{name:'searchtype',index:'searchtype',hidden:true,editable:true,edittype:'select',editrules:{edithidden:true},width:100,editoptions:{value:"text:文本;date:日期"}}
		   	],
		   	//loadonce:true,
		   	sortable:true,
			baseUrl:basePath
		};
		//new commonGrid(config);
		$("#list").extendJqgrid(config);
		$("#list_toppager").hide();
		$("#jqgh_list_cb").append("<div><span>同步状态</span></div>");
	}
});
</script>
</html>