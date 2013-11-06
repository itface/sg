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
	   <div style='float:right'><a href="#" style="margin-right:0px;" class="btn"  onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'" onclick="zwyd();">变更运行状态</a></div> 
   </div>
</div>
<div id="mainPanel" class="easyui-tabs" style="padding:5px 10px; clear:both">
	<div title="客户一般数据(KNA1)">
		<table id="pna1Grid"></table>
	</div>
	<div title="客户公司代码数据(KNB1)">
		<table id="pnb1Grid"></table>
	</div>
	<div title="客户销售数据(KNVV)">
		<table id="pnvvGrid"></table>
	</div>
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
					content: 'url:${ctx}/application/commonScheduling/realtime/customer'
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
		createKna1Grid();
		createKnb1Grid();
		createKnvvGrid();
		initElEvent();
		initTab();
		initGridTitle('pna1Grid');
		initGridTitle('pnb1Grid');
		initGridTitle('pnvvGrid');
	}
	function initGridTitle(gridId){
			$('#gbox_'+gridId+' .ui-jqgrid-titlebar').remove();
	}
	function initTab(){
		$('#mainPanel .tabs-header').css('border-top','1px solid #c5c5c5');
		$('#mainPanel .tabs-header').css('border-right','1px solid #c5c5c5');
		$('#mainPanel .tabs').css('padding-left',0);
	}
	/**
	*给页面元素绑定事件
	*/
	function initElEvent(){
		
	}
	function createKna1Grid(){
		var basePath = '${ctx}/application/common/columnInfo/kna1';
		var gridId = "pna1Grid";
		var checked = '';
		var config ={
			id:gridId,
			caption:'客户一般数据',
			rownum:900,
			height:$(window).height()-155,
			width:900,
			autoWidth:true,
			multiselectWidth:60,
			pager:'',
			editable:true,
			datatype:'json',
			multiselect:true,
			add:false,
			del:false,
			refresh:false,
			editable:false,
			eventModels:{
			    gridComplete:function(){
			    	var rowData= jQuery("#"+gridId).jqGrid('getRowData');
			    	//var numberOfRecords  = jQuery("#list").jqGrid('getGridParam',"records");
					for(var i=0;i<rowData.length;i++){
						var rowId = rowData[i].id;
						var status = rowData[i].status;
						var ifkey = rowData[i].ifkey;
						if(status=='ACTIVATE'){
							//jQuery('#list').jqGrid('setSelection',rowId);
							$('#jqg_'+gridId+'_'+rowId).attr('checked','true');
						}
						if(ifkey==1){
							$('#jqg_'+gridId+'_'+rowId).attr('disabled','true');
						}
					}
			    },
			    beforeSelectRow:function(rowid,e){
			    	if($(e.target).attr('type')=='checkbox'){
			    		var c = $('#jqg_'+gridId+'_'+rowid).attr('checked');
				    	if(confirm('更改当前字段的同步状态？')){
				    		checked = c;
				    		checked = $('#jqg_'+gridId+'_'+rowid).attr('checked');
				    		return true;
				    	}else{
				    		$('#jqg_'+gridId+'_'+rowid).attr('checked',c?false:true);
				    		checked = $('#jqg_'+gridId+'_'+rowid).attr('checked');
				    		return false;
				    	}
			    	}
			    	checked = $('#jqg_'+gridId+'_'+rowid).attr('checked');
			    	return true;
			    },
			    onSelectRow:function(rowid,status,e){
			    	if($(e.target).attr('type')=='checkbox'){
			    		if(checked==true||checked=='checked'){
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',true);
			    		}else{
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',false);
			    		}
				    	var rowData= jQuery("#"+gridId).jqGrid('getRowData',rowid);
				    	var ifkey = rowData.ifkey;
				    	var rowId = rowData.id;
				    	if(ifkey==1){
							$('#jqg_'+gridId+'_'+rowId).attr('checked','checked');
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
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',true);
			    		}else{
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',false);
			    		}
			    	}
			    }
			},
			//columnNames:['ID','源字段','源字段名称','目标字段','目标字段名称','状态','类型','ifKey','是否是查询字段','查询类型'],
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
		   	sortable:true,
			baseUrl:basePath
		};
		//new commonGrid(config);
		$("#"+gridId).extendJqgrid(config);
		$("#"+gridId+"_toppager").hide();
		$("#jqgh_"+gridId+"_cb").append("<div><span>同步状态</span></div>");
		$('#cb_'+gridId).hide();
	}
	function createKnb1Grid(){
		var basePath = '${ctx}/application/common/columnInfo/knb1';
		var gridId = "pnb1Grid";
		var checked = '';
		var config ={
			id:gridId,
			caption:'客户公司代码数据',
			rownum:10000,
			height:$(window).height()-155,
			width:900,
			autoWidth:true,
			multiselectWidth:60,
			pager:'',
			editable:true,
			datatype:'json',
			multiselect:true,
			add:false,
			del:false,
			refresh:true,
			eventModels:{
			    gridComplete:function(){
			    	var rowData= jQuery("#"+gridId).jqGrid('getRowData');
			    	//var numberOfRecords  = jQuery("#list").jqGrid('getGridParam',"records");
					for(var i=0;i<rowData.length;i++){
						var rowId = rowData[i].id;
						var status = rowData[i].status;
						var ifkey = rowData[i].ifkey;
						if(status=='ACTIVATE'){
							//jQuery('#list').jqGrid('setSelection',rowId);
							$('#jqg_'+gridId+'_'+rowId).attr('checked','true');
						}
						if(ifkey==1){
							$('#jqg_'+gridId+'_'+rowId).attr('disabled','true');
						}
					}
			    },
			     beforeSelectRow:function(rowid,e){
			    	if($(e.target).attr('type')=='checkbox'){
			    		var c = $('#jqg_'+gridId+'_'+rowid).attr('checked');
				    	if(confirm('更改当前字段的同步状态？')){
				    		checked = c;
				    		checked = $('#jqg_'+gridId+'_'+rowid).attr('checked');
				    		return true;
				    	}else{
				    		$('#jqg_'+gridId+'_'+rowid).attr('checked',c?false:true);
				    		checked = $('#jqg_'+gridId+'_'+rowid).attr('checked');
				    		return false;
				    	}
			    	}
			    	checked = $('#jqg_'+gridId+'_'+rowid).attr('checked');
			    	return true;
			    },
			    onSelectRow:function(rowid,status,e){
			    	if($(e.target).attr('type')=='checkbox'){
			    		if(checked==true||checked=='checked'){
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',true);
			    		}else{
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',false);
			    		}
				    	var rowData= jQuery("#"+gridId).jqGrid('getRowData',rowid);
				    	var ifkey = rowData.ifkey;
				    	var rowId = rowData.id;
				    	if(ifkey==1){
							$('#jqg_'+gridId+'_'+rowId).attr('checked','checked');
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
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',true);
			    		}else{
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',false);
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
		   	sortable:true,
			baseUrl:basePath
		};
		//new commonGrid(config);
		$("#"+gridId).extendJqgrid(config);
		$("#"+gridId+"_toppager").hide();
		$("#jqgh_"+gridId+"_cb").append("<div><span>同步状态</span></div>");
		$('#cb_'+gridId).hide();
	}
	function createKnvvGrid(){
		var basePath = '${ctx}/application/common/columnInfo/knvv';
		var gridId = "pnvvGrid";
		var checked = '';
		var config ={
			id:gridId,
			caption:'客户销售数据',
			rownum:10000,
			height:$(window).height()-155,
			width:900,
			autoWidth:true,
			multiselectWidth:60,
			pager:'',
			editable:true,
			datatype:'json',
			multiselect:true,
			add:false,
			del:false,
			refresh:true,
			eventModels:{
			    gridComplete:function(){
			    	/*
			    	var rowData= jQuery("#"+gridId).jqGrid('getRowData');
			    	//var numberOfRecords  = jQuery("#list").jqGrid('getGridParam',"records");
					for(var i=0;i<rowData.length;i++){
						var rowId = rowData[i].id;
						var status = rowData[i].status;
						var ifkey = rowData[i].ifkey;
						if(status=='ACTIVATE'){
							//jQuery('#list').jqGrid('setSelection',rowId);
							$('#jqg_'+gridId+'_'+rowId).attr('checked','true');
						}
						if(ifkey==1){
							$('#jqg_'+gridId+'_'+rowId).attr('disabled','true');
						}
					}
					$('#cb_'+gridId).hide();
					*/
					var rowData= jQuery("#"+gridId).jqGrid('getRowData');
			    	//var numberOfRecords  = jQuery("#list").jqGrid('getGridParam',"records");
					for(var i=0;i<rowData.length;i++){
						var rowId = rowData[i].id;
						var status = rowData[i].status;
						var ifkey = rowData[i].ifkey;
						if(status=='ACTIVATE'){
							//jQuery('#list').jqGrid('setSelection',rowId);
							$('#jqg_'+gridId+'_'+rowId).attr('checked','true');
						}
						if(ifkey==1){
							$('#jqg_'+gridId+'_'+rowId).attr('disabled','true');
						}
					}
			    },
			     beforeSelectRow:function(rowid,e){
			    	if($(e.target).attr('type')=='checkbox'){
			    		var c = $('#jqg_'+gridId+'_'+rowid).attr('checked');
				    	if(confirm('更改当前字段的同步状态？')){
				    		checked = c;
				    		checked = $('#jqg_'+gridId+'_'+rowid).attr('checked');
				    		return true;
				    	}else{
				    		$('#jqg_'+gridId+'_'+rowid).attr('checked',c?false:true);
				    		checked = $('#jqg_'+gridId+'_'+rowid).attr('checked');
				    		return false;
				    	}
			    	}
			    	checked = $('#jqg_'+gridId+'_'+rowid).attr('checked');
			    	return true;
			    },
			    onSelectRow:function(rowid,status,e){
				    if($(e.target).attr('type')=='checkbox'){
			    		if(checked==true||checked=='checked'){
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',true);
			    		}else{
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',false);
			    		}
				    	var rowData= jQuery("#"+gridId).jqGrid('getRowData',rowid);
				    	var ifkey = rowData.ifkey;
				    	var rowId = rowData.id;
				    	if(ifkey==1){
							$('#jqg_'+gridId+'_'+rowId).attr('checked','checked');
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
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',true);
			    		}else{
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',false);
			    		}
			    	}
			    	//,,,,,,,,,,
			    	/*
			    	if($(e.target).attr('type')=='checkbox'){
			    		if(checked==true||checked=='checked'){
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',true);
			    		}else{
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',false);
			    		}
				    	var rowData= jQuery("#"+gridId).jqGrid('getRowData',rowid);
				    	var ifkey = rowData.ifkey;
				    	var rowId = rowData.id;
				    	if(ifkey==1){
							$('#jqg_'+gridId+'_'+rowId).attr('checked','checked');
						}else{
							$.ajax({
								url:'${ctx}/application/common/columnInfo/updateStatus',
								data:{id:rowid,status:status?'ACTIVATE':'INACTIVE'},
								success:function(){
									alert('设置成功');
								}
							});
						}
			    	}else{
			    		if(checked==true||checked=='checked'){
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',true);
			    		}else{
			    			$('#jqg_'+gridId+'_'+rowid).attr('checked',false);
			    		}
			    	}
			    	*/
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
		    sortable:true,
			baseUrl:basePath
		};
		//new commonGrid(config);
		$("#"+gridId).extendJqgrid(config);
		$("#"+gridId+"_toppager").hide();
		$("#jqgh_"+gridId+"_cb").append("<div><span>同步状态</span></div>");
		$('#cb_'+gridId).hide();
	}
});
</script>
</html>