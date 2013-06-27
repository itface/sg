<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./inc/header.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.jqgrid.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.ui.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_extend.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/commons.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
</head>
<body>
<table id="list"></table>
</body>
<script>
$(function(){
	var userBasePath = '${ctx}/system/scheduling/${jobGroup}/data';
	function initExecuteTime(){
		var executeFrequency = $('#executeFrequency').val();
		if(executeFrequency=='STARTUP'){
			$('#executeTimeHour').val(0);
			$('#executeTimeMinute').val(0);
			$('#tr_executeTimeHour').hide();
			$('#tr_executeTimeMinute').hide();
			$('#repeatInterval').val(0);
			$('#tr_repeatInterval').hide();
		}else if(executeFrequency=='EVERYDAY'){
			$('#tr_executeTimeHour').show();
			$('#tr_executeTimeMinute').show();
			$('#repeatInterval').val(0);
			$('#tr_repeatInterval').hide();
			//$("#list").jqGrid('hideCol','repeatInterval');
		}else if(executeFrequency=='REPEAT'){
			$('#executeTimeHour').val(0);
			$('#executeTimeMinute').val(0);
			$('#tr_executeTimeHour').hide();
			$('#tr_executeTimeMinute').hide();
			$('#tr_repeatInterval').show();
		}
	}
	function validateLength(value,colname) {
		var col ={
			'任务名称':100,
			'任务描述':100,
			'任务执行类':500
		};
		if(getLen(value) <= col[colname]) {
			return [true,"",""];
		} else {
			return [false,colname+"长度不能超过"+col[colname],""];
		}
	}
	function validateNumberMin(value,colname){
		//如果执行频率是重复执行，则校验输入的值必须是不能小于1的整数
		var executeFrequency = $('#executeFrequency').val();
		if(value < 1&&executeFrequency=='REPEAT') {
			return [false,colname+"必须是大于等于"+col[colname]+"的整数",""];
		} else {
			return [true,"",""];
		}
	}
	jQuery("#list").jqGrid({
		url:userBasePath,
	   	ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },
	    datatype: 'json',
	    mtype: 'GET',
	    jsonReader:{
	    	repeatitems:false
	    },
		autowidth: false,
	    width:1200,
	    height:600,
	   	colNames:['ID','用户','任务名称','任务描述','jobGroup','任务执行类','开始日期','结束日期','执行频率','执行小时','执行分钟','重复频率'],
	   	colModel:[
	   		{name:'id',index:'id',hidden:true, width:1,key:true},
	   		{name:'owner',index:'owner',hidden:true,editable:true,editoptions:{dataInit:function(el){$(el).val('${owner}');}}},
	   		{name:'jobName',index:'jobName',width:100,editable:true,editrules:{required:true,custom:true,custom_func:validateLength},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
	   		{name:'jobMemo',index:'jobMemo', width:100,editable:true,editrules:{required:true,custom:true,custom_func:validateLength},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
	   		{name:'jobGroup',index:'jobGroup',hidden:true, width:1,editable:true},
	   		{name:'jobClass',index:'jobClass', width:150, align:"right",editable:true,editrules:{required:true,custom:true,custom_func:validateLength},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
	   		{name:'jobBeginDate',index:'jobBeginDate', width:100, align:"right",editable:true,
		   		editoptions:{size:12,
					dataInit:function(el){
						$(el).bind('focus',function(){WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'jobEndDate\')}',});});
					}
					/*
					defaultValue: function(){
						return year+"-"+month + "-"+day;			
					}
					*/
				}
			},	
	   		{name:'jobEndDate',index:'jobEndDate', width:100, align:"right",editable:true,
	   			editoptions:{size:12,
					dataInit:function(el){
						//$(el).WdatePicker({dateFormat:'yy-mm-dd'});
						//WdatePicker({el:'jobEndDate'});
						$(el).bind('focus',function(){WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'jobBeginDate\')}',});});
					}
				}
	   		},	
	   		{name:'executeFrequency',index:'jobFrequency', width:100, align:"right",editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" },edittype:'select',
	   			editoptions: {value:"EVERYDAY:每天;STARTUP:启动平台;REPEAT:循环执行",
	   				dataInit:function(el){
	   					$(el).bind('change',
	   						function(e){
	   							initExecuteTime();
	   						}
	   					);
	   				}
	   			}
	   		},
	   		{name:'executeTimeHour',index:'executeTimeHour', width:100, align:"right",editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" },edittype:'select',
	   			editoptions: {value:"0:0;1:1;2:2;3:3;4:4;5:5;6:6;7:7;8:8;9:9;10:10;11:11;12:12;13:13;14:14;15:15;16:16;17:17;18:18;19:19;20:20;21:21;22:22;23:23"}
	   		},
	   		{name:'executeTimeMinute',index:'executeTimeMinute', width:100, align:"right",editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" },edittype:'select',
	   			editoptions: {value:"0:0;5:5;10:10;15:15;20:20;25:25;30:30;35:35;40:40;45:45;50:50;55:55"}
	   		},
	   		{name:'repeatInterval',index:'repeatInterval',width:50,editable:true,editrules:{required:true,integer:true,custom:true,custom_func:validateNumberMin},formoptions:{ elmprefix:"",elmsuffix:"  分钟执行一次<font color=red>*</font>" },editoptions:{size:12}}
	   	],
	   	pginput:false,
	   	toppager: true,
	    viewrecords:false,
	    rownumbers:true,
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    //pager : "#tools",
	    editurl:'',
	    caption:'计划任务',
		multiselect: false,
		ondblClickRow:function(rowid,iCol,cellcontent,e){
	    	//editData为添加的参数，是为了让参数能正常的put到后台
	    	$('#list').jqGrid('editGridRow',rowid,{editData:{_method:'put'},top:200,left:400,width:400,reloadAfterSubmit:true,modal:true,recreateForm:true,mtype: "POST", url: userBasePath,viewPagerButtons:false,afterShowForm:function(){initExecuteTime();}});
	    }
	});
	jQuery("#list").jqGrid('navGrid','',{edit:false,cloneToTop:true},{},{mtype: "POST",top:200,left:400,width:400,recreateForm:true,closeAfterAdd:true,reloadAfterSubmit:true,clearAfterAdd:true,modal:true,url:userBasePath,viewPagerButtons:false,afterShowForm:function(){initExecuteTime();}},{url:userBasePath,reloadAfterSubmit:true,jqModal:false});
	var topPagerDiv = $("#list_toppager")[0];
	$("#edit_list_top", topPagerDiv).remove();
	$("#list_toppager_center", topPagerDiv).remove();
	$(".ui-paging-info", topPagerDiv).remove();
});
</script>
</html>