<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../../../inc/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link href="${ctx}/css/sapgarden/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/sapgarden/garden.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.jqgrid.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.ui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/script/blockUI/blockUI.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_extend.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/commons.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_custom.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.form/jquery.form.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/blockUI/blockUI.js'/>" type="text/javascript"></script>
</head>
<body>
<form>
	<div class="toolbar">
	  <div class="toolbar_left"> 
	  	  <a href="javascript:void(0);" class="btn queryLogBtn" id="queryLogOfMonthRange" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">一月内的异常日志</a> 
	  	  <a href="javascript:void(0);" class="btn queryLogBtn" id="queryLogOfWeekRange" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">一周内日志</a> 
	  </div>
	  <div class="toolbar_right">
	      <input type="text" class="input1" id="bdate"   onclick="WdatePicker()" value="${bdate}">
	      <span style='float:left;margin-right:5px' id='dataSpan'>-</span>
	      <input type="text" class="input1" id="edate" onclick="WdatePicker()" value="${edate}">
	    <input type="text"  class="input1" id="companyCode" value="公司代码">
	    <span style="float:left;padding:5px"><input type="checkbox" id="errorLog">&nbsp;仅异常日志</span>
	    <a href="javascript:void(0);" class="btn queryLogBtn" id="queryLogOfCommon" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">检索日志</a> 
	    <a href="javascript:void(0);" class="btn" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">导出日志</a> 
	    <a href="javascript:void(0);" style="display:none" class="btn" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">运行统计</a> </div>
	</div>
	<div style="padding:10px;">
		<table id='monitorGrid'></table>
	</div>
	<input type="hidden" id="bdateOfMonthRange" value="${bdateOfMonthRange}">
	<input type="hidden" id="edateOfMonthRange" value="${edateOfMonthRange}">
	<input type="hidden" id="bdateOfWeekRange" value="${bdateOfWeekRange}">
	<input type="hidden" id="edateOfWeekRange" value="${edateOfWeekRange}">
</form>
</body>
<script>
$(function(){
	var type='${type}';
	init();
	function init(){
		dynamicGrid();
		initElEvent();
	}
	function dynamicGrid(){
		var monitorGridOptions=${monitorGridOptions};
		if(monitorGridOptions!=null){
			$.extend(monitorGridOptions,{
				contextPath:"${ctx}",
				autoWidth:true,
				id:"monitorGrid",
				height:$(window).height()-200,
				multiselect:false
			});
		}
		new customGrid(monitorGridOptions);

	}
	function initElEvent(){
		$(".input1").focus(function(){
			  $(this).attr('class','input1_onfocus');
			  if($(this).val() ==this.defaultValue){  
                  $(this).val("");           
			  } 
		}).blur(function(){
			  $(this).attr('class','input1');
			 if ($(this).val() == '') {
                $(this).val(this.defaultValue);
             }
		});
		$(".queryLogBtn").bind('click',function(e){
			$(window).blockUI();
			var bdate = '';
			var edate = '';
			var companyCode = '';
			var errorLog = '';
			var id = $(e.target).attr('id');
			if(id=='queryLogOfCommon'){
				bdate = $("#bdate").val();
				edate = $("#edate").val();
				companyCode = ($("#companyCode").val()=='公司代码'?'':$("#companyCode").val());
				errorLog = $("#errorLog").attr('checked')=='checked'?'E':'';
				jQuery("#monitorGrid").jqGrid('setCaption',"检索日志");
			}else if(id=='queryLogOfMonthRange'){
				bdate = $("#bdateOfMonthRange").val();
				edate = $("#edateOfMonthRange").val();
				companyCode = '';
				errorLog = 'E';
				jQuery("#monitorGrid").jqGrid('setCaption',"一个月内的异常日志");
			}else if(id=='queryLogOfWeekRange'){
				bdate = $("#bdateOfWeekRange").val();
				edate = $("#edateOfWeekRange").val();
				companyCode = '';
				jQuery("#monitorGrid").jqGrid('setCaption',"一周内日志");
			}
			$.ajax({
				data:{bdate:bdate,edate:edate,companyCode:companyCode,optflag:errorLog},
				url:'${ctx}/application/fi/company/runtimeMonitor/findLog',
				success:function(json){
					try{
						if(json!=null){
							jQuery("#monitorGrid").clearGridData(true);
							jQuery("#monitorGrid")[0].addJSONData(json);
						}
						$(window).blockUI('remove');
					}catch(e){
						$(window).blockUI('remove');
						alert('装载数据异常');
					}
				},
				error:function(){
					alert('查询异常');
					$(window).blockUI('remove');
				}
			});
		});
	}
});
</script>
</html>