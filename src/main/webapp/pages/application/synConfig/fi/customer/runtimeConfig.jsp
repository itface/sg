<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../../../inc/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/icon.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.jqgrid.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.ui.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_extend.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_common.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/commons.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/easyui-lang-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/jquery.easyui.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.form/jquery.form.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<style>
.outter{
	position:absolute;
	margin:0px;
	padding-top:10px;
	top:0px;
	left:0px;
	width:100%;
	height:100%;
}
.tbar{
	width:100%;
	height:25px;
}
.tbar_button{
	text-align:right;
	padding-right:30px;
	padding-top:5px;
}
.fold{
	height:20px;
	text-align:left;
	padding-left:15px;
	cursor:pointer;
	background:#DFEFFC;
	margin-bottom:2px;
}
.contentBlock{
	height:200px;
	overflow:auto;
}
.schedulingTableDiv{
	width:50%;
	padding-left:300px;
	padding-top:20px;
}
.schedulingTable{
	width:100%;
	height:100%;
	border:1px solid #ccc;
}
.schedulingTable tr{
	height:40px;
}
.dbReflectTableDiv{
	width:80%;
	padding-left:200px;
	
}
.dbReflectTable{
	width:80%;
	
	border:1px solid #ccc
}
.dbReflectTable tr{
	height:40px;
}
.activateDiv{
	width:50%;
	padding-left:500px;
}
</style>
</head>
<body>
<div class="outter">
	<form:form id='basicInfoForm' action='${basePath}/basicInfo' method='post' modelAttribute='basicInfo'>
		<div class="fold">激活状态</div>
		<div class="contentBlock" style='height:100px'>
			<div class="tbar">
				<div class="tbar_button">
					<input type='button' value="保存" class='saveForm'/>
				</div>	
			</div>
			<div class='activateDiv'>
				<form:radiobutton  path='status' value='ACTIVATE'/>激活
				<form:radiobutton path='status' value='INACTIVE'/>停止
			</div>
		</div>
		<div style="display:none">
					<form:input id='businesstype' path='businesstype' value='${type}'/>
					<form:input id='sapclient' path='sapclient' value='${sapclient}'/>
					<form:input id='id' path='id'/>
		</div>
	</form:form>
	<form:form id='schedulingForm' action='${ctx}/system/scheduling/${type}/data' modelAttribute='scheduling' style='display:none'>
		<div class="fold">计划任务</div>
		<div class="contentBlock" style='height:300px'>
			<div class="tbar">
				<div class="tbar_button">
					<input type='button' value="保存" class='saveForm'/>
				</div>	
			</div>
			<div class="schedulingTableDiv">
				<table class="schedulingTable">
					<tr>
						<td align='right'>任务名称：</td>
						<td align='left'><form:input path='jobName' id='jobName'/></td>
						<td align='right'>任务描述：</td>
						<td align='left'><form:input path='jobMemo' id='jobMemo'/></td>
					</tr>
					<tr>
						<td width='15%' align='right'>开始日期：</td>
						<td width='35%' align='left'><form:input id='jobBeginDate' path='jobBeginDate'/></td>
						<td width='15%' align='right'>结束日期：</td>
						<td width='35%' align='left'><form:input id='jobEndDate' path='jobEndDate'/></td>
					</tr>
					<tr>
						<td align='right'>停止：</td>
						<td align='left' colspan='3'><form:radiobutton path='executeFrequency' value='SHUTDOWN'/></td>
					</tr>
					<tr>
						<td align='right'>执行频率：</td>
						<td align='left' colspan='3'>
							<form:radiobutton  path='executeFrequency' value='EVERYDAY'/>每天
							<form:radiobutton path='executeFrequency' value='REPEAT'/>循环执行
							<form:radiobutton path='executeFrequency' value='STARTUP'/>启动平台
						</td>
					</tr>
					<tr id='tr_executeTime'>
						<td align='right'>执行小时：</td>
						<td align='left'>
							<form:select id='executeTimeHour' path='executeTimeHour'>
								<form:option value='0' label='0'/>
								<form:option value='1' label='1'/>
								<form:option value='2' label='2'/>
								<form:option value='3' label='3'/>
								<form:option value='4' label='4'/>
								<form:option value='5' label='5'/>
								<form:option value='6' label='6'/>
								<form:option value='7' label='7'/>
								<form:option value='8' label='8'/>
								<form:option value='9' label='9'/>
								<form:option value='10' label='10'/>
								<form:option value='11' label='11'/>
								<form:option value='12' label='12'/>
								<form:option value='13' label='13'/>
								<form:option value='14' label='14'/>
								<form:option value='15' label='15'/>
								<form:option value='16' label='16'/>
								<form:option value='17' label='17'/>
								<form:option value='18' label='18'/>
								<form:option value='19' label='19'/>
								<form:option value='20' label='20'/>
								<form:option value='21' label='21'/>
								<form:option value='22' label='22'/>
								<form:option value='23' label='23'/>
							</form:select>
						</td>
						<td align='right'>执行分钟：</td>
						<td align='left'>
							<form:select id='executeTimeMinute' path='executeTimeMinute'>
								<form:option value='0' label='0'/>
								<form:option value='5' label='5'/>
								<form:option value='10' label='10'/>
								<form:option value='15' label='15'/>
								<form:option value='20' label='20'/>
								<form:option value='25' label='25'/>
								<form:option value='30' label='30'/>
								<form:option value='35' label='35'/>
								<form:option value='40' label='40'/>
								<form:option value='45' label='45'/>
								<form:option value='50' label='50'/>
								<form:option value='55' label='55'/>
							</form:select>
						</td>
					</tr>
					<tr id='tr_repeatInterval'>
						<td align='right'>重复频率：</td>
						<td align='left' colspan='3'>
							每<form:input id='repeatInterval' path='repeatInterval'/>分钟执行一次
						</td>
					</tr>
				</table>
				<div style="display:none">
					<form:input id='jobGroup' path='jobGroup'/>
					<form:input id='id' path='id'/>
					<input type='text' id='jobClass' name='jobClass' value='${jobClassName}'/>
					<input type='text' id='owner' name='owner' value='${owner}'/>
					<input type='text' id='sapclient' name='sapClient' value='${sapclient}'/>
				</div>
			</div>
		</div>
	</form:form>
	<form:form id='dbReflectForm' action='${basePath}/tableInfo' modelAttribute='dbReflect'>
		<div class="fold">字段映射</div>
		<table id="pna1Grid"></table>
		<div id="pna1Pager"></div> 
		</br>
		<table id="pnb1Grid"></table>
		<div id="pnb1Pager"></div> 
		</br>
		<table id="pnvvGrid"></table>
		<div id="pnvvPager"></div> 
		<!-- div class="contentBlock">
			<div class="tbar">
				<div class="tbar_button">
					<input type='button' value="保存" class='saveForm'/>
				</div>	
			</div>
			<div class="dbReflectTableDiv">
				<table class="dbReflectTable">
					<tr>
						<td width='20%' align='right'>表名：</td>
						<td width='80%' align='left'>${kna1TableName}<form:hidden id='reflectTable' path='reflectTable' value='${kna1TableName}'/></td>
					</tr>
					<tr>
						<td align='right'>描述：</td>
						<td align='left'><form:input id='reflectTableMemo' path='reflectTableMemo' size='80'/></td>
					</tr>
					<tr>
						<td align='right'>&nbsp;</td>
						<td align='left'><form:hidden id='sapclient' path='sapclient' value='${sapclient}'/></td>
					</tr>
					<tr>
						<td colspan='2'>
							
							<br/>
						</td>
					</tr>
					<tr>
						<td colspan='2'>
							<table id="pnb1Grid"></table>
							<div id="pnb1Pager"></div> 
							<br/>
						</td>
					</tr>
					<tr>
						<td colspan='2'>
							<table id="pnvvGrid"></table>
							<div id="pnvvPager"></div> 
							<br/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div style='display:none'>
			<form:input path='id' id='id'/>
			<form:input path='businesstype' id='businesstype' value='${type}'/>
		</div-->
	</form:form>
	</div>
</body>
<script>
$(function(){
	var type='${type}';
	init();
	function init(){
		createKna1Grid();
		createKnb1Grid();
		createKnvvGrid();
		initElEvent();
		setExecuteFrequencyStatus();
		initAjaxForm();
	}
	/**
	*把form初始化为ajaxform，用于ajax提交
	*/
	function initAjaxForm(){
		$('#schedulingForm').ajaxForm();
		$('#dbReflectForm').ajaxForm();
		$('#basicInfoForm').ajaxForm();
	}
	/**
	*给页面元素绑定事件
	*/
	function initElEvent(){
		//设置折叠层的点击事件
		$('.fold').bind('click',function(e){
			var display = $(e.target).next().css('display');
			var newDisplay = display=='none'?'block':'none';
			$(e.target).next().css('display',newDisplay);
		});
		$('input[name="executeFrequency"]').bind('click',function(){
			setExecuteFrequencyStatus();
		});
		$('#jobBeginDate').bind('click',function(){
			WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'jobEndDate\')}'});
		});
		$('#jobEndDate').bind('click',function(){
			WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'jobBeginDate\')}'});
		});
		//保存按钮添加点击时提交的动作
		$(':button.saveForm').bind('click',function(e){
			var formId = $(e.target.form).attr('id');
			var url = $(e.target.form).attr('action');
			var id = $('#'+formId+' #id').val();
			var methodObject = {};
			//如果id=0则表示新增
			if(id!=null&&id!=''&&id>0){
				methodObject={_method:'PUT'};
				url+='/'+id;
			}
			var options = {
					data:methodObject,
					target:'#'+formId+' #id',
					type:'POST',
					url:url,
					success:function(responseText,statusText,xhr,$form){
						if(responseText!=null&&responseText.trim()!=''){
							alert('保存失败');
						}else{
							alert('保存成功');
							//保存成功后刷新当前页面，更新id.
							location=location;
						}
					},
					error:function(){
						alert('保存失败');
					}
			};
			$("#"+formId).ajaxSubmit(options);
		});
	}
	/**
	*根据执行频率，设置执行小时、分钟和重复频率的执行频率
	*/
	function setExecuteFrequencyStatus(){
		var executeFrequency = $('input[name=executeFrequency]:checked').val();
		if(executeFrequency=='STARTUP'){
			$('#executeTimeHour').val(0);
			$('#executeTimeMinute').val(0);
			$('#tr_executeTime').hide();
			$('#repeatInterval').val(0);
			$('#tr_repeatInterval').hide();
		}else if(executeFrequency=='EVERYDAY'){
			$('#tr_executeTime').show();
			$('#repeatInterval').val(0);
			$('#tr_repeatInterval').hide();
		}else if(executeFrequency=='REPEAT'){
			$('#executeTimeHour').val(0);
			$('#executeTimeMinute').val(0);
			$('#tr_executeTime').hide();
			$('#tr_repeatInterval').show();
		}else{
			$('#executeTimeHour').val(0);
			$('#executeTimeMinute').val(0);
			$('#tr_executeTime').hide();
			$('#repeatInterval').val(0);
			$('#tr_repeatInterval').hide();
		}
	}
	function createKna1Grid(){
		var basePath = '${ctx}/application/common/columnInfo/KNA1';
		var gridId = "pna1Grid";
		var checked = '';
		var config ={
			id:gridId,
			caption:'客户一般数据',
			rownum:100,
			height:300,
			pager:'#pna1Pager',
			editable:true,
			datatype:'json',
			multiselect:true,
			add:false,
			del:false,
			refresh:true,
			eventModel:{
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
					$('#cb_'+gridId).hide();
			    },
			    beforeSelectRow:function(rowid,e){
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
			    }
			},
			columnNames:['ID','源字段','源字段名称','目标字段','目标字段名称','状态','类型','ifKey','是否是查询字段','查询类型'],
			columnModel:[
		   		{name:'id',index:'id',hidden:true, width:1,key:true},
		   		{name:'sourceColumn',index:'sourceColumn',width:150,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'sourceColumnName',index:'sourceColumnName',width:150,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'targetColumn',index:'targetColumn', width:150,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'targetColumnName',index:'targetColumnName',width:150,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'status',index:'status',editable:true,edittype:'select',hidden:true,width:100,editoptions:{value:"ACTIVATE:激活;INACTIVE:非激活"}},
		   		{name:'businesstype',index:'businesstype',hidden:true,editable:true},
		   		{name:'ifkey',index:'ifkey',hidden:true,editable:true,edittype:'select',width:100,editrules:{edithidden:true},editoptions:{value:"0:非必须同步;1:必须同步"}},
		   		{name:'searchable',index:'searchable',hidden:true,editable:true,edittype:'select',width:100,editrules:{edithidden:true},editoptions:{value:"0:否;1:是"}},
		   		{name:'searchtype',index:'searchtype',hidden:true,editable:true,edittype:'select',width:100,editrules:{edithidden:true},editoptions:{value:"text:文本;date:日期"}}
		   	],
			baseUrl:basePath
		};
		new commonGrid(config);
	}
	function createKnb1Grid(){
		var basePath = '${ctx}/application/common/columnInfo/KNB1';
		var gridId = "pnb1Grid";
		var checked = '';
		var config ={
			id:gridId,
			caption:'客户公司代码数据',
			rownum:100,
			height:300,
			pager:'#pnb1Pager',
			editable:true,
			datatype:'json',
			multiselect:true,
			add:false,
			del:false,
			refresh:true,
			eventModel:{
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
					$('#cb_'+gridId).hide();
			    },
			     beforeSelectRow:function(rowid,e){
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
			    }
			},
			columnNames:['ID','源字段','源字段名称','目标字段','目标字段名称','状态','类型','ifKey','是否是查询字段','查询类型'],
			columnModel:[
		   		{name:'id',index:'id',hidden:true, width:1,key:true},
		   		{name:'sourceColumn',index:'sourceColumn',width:150,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'sourceColumnName',index:'sourceColumnName',width:150,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'targetColumn',index:'targetColumn', width:150,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'targetColumnName',index:'targetColumnName',width:150,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'status',index:'status',editable:true,edittype:'select',hidden:true,width:100,editoptions:{value:"ACTIVATE:激活;INACTIVE:非激活"}},
		   		{name:'businesstype',index:'businesstype',hidden:true,editable:true},
		   		{name:'ifkey',index:'ifkey',hidden:true,editable:true,edittype:'select',width:100,editrules:{edithidden:true},editoptions:{value:"0:非必须同步;1:必须同步"}},
		   		{name:'searchable',index:'searchable',hidden:true,editable:true,edittype:'select',width:100,editrules:{edithidden:true},editoptions:{value:"0:否;1:是"}},
		   		{name:'searchtype',index:'searchtype',hidden:true,editable:true,edittype:'select',width:100,editrules:{edithidden:true},editoptions:{value:"text:文本;date:日期"}}
		   	],
			baseUrl:basePath
		};
		new commonGrid(config);
	}
	function createKnvvGrid(){
		var basePath = '${ctx}/application/common/columnInfo/KNVV';
		var gridId = "pnvvGrid";
		var checked = '';
		var config ={
			id:gridId,
			caption:'客户销售数据',
			rownum:100,
			height:300,
			pager:'#pnvvPager',
			editable:true,
			datatype:'json',
			multiselect:true,
			add:false,
			del:false,
			refresh:true,
			eventModel:{
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
					$('#cb_'+gridId).hide();
			    },
			     beforeSelectRow:function(rowid,e){
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
			    }
			},
			columnNames:['ID','源字段','源字段名称','目标字段','目标字段名称','状态','类型','ifKey','是否是查询字段','查询类型'],
			columnModel:[
		   		{name:'id',index:'id',hidden:true, width:1,key:true},
		   		{name:'sourceColumn',index:'sourceColumn',width:150,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'sourceColumnName',index:'sourceColumnName',width:150,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'targetColumn',index:'targetColumn', width:150,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'targetColumnName',index:'targetColumnName',width:150,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'status',index:'status',editable:true,edittype:'select',hidden:true,width:100,editoptions:{value:"ACTIVATE:激活;INACTIVE:非激活"}},
		   		{name:'businesstype',index:'businesstype',hidden:true,editable:true},
		   		{name:'ifkey',index:'ifkey',hidden:true,editable:true,edittype:'select',width:100,editrules:{edithidden:true},editoptions:{value:"0:非必须同步;1:必须同步"}},
		   		{name:'searchable',index:'searchable',hidden:true,editable:true,edittype:'select',width:100,editrules:{edithidden:true},editoptions:{value:"0:否;1:是"}},
		   		{name:'searchtype',index:'searchtype',hidden:true,editable:true,edittype:'select',width:100,editrules:{edithidden:true},editoptions:{value:"text:文本;date:日期"}}
		   	],
			baseUrl:basePath
		};
		new commonGrid(config);
	}
});
</script>
</html>