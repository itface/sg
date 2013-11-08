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
<script src="<c:url value='/script/extendJqgrid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/blockUI/blockUI.js'/>" type="text/javascript"></script>
<style>
.fm-button-icon-left{
	display:none;
}
</style>
</head>
<body onload="setTimeout('removeMask()',200)">
<form>
	<div class="transparent_class blockUI_class" style="position: absolute; z-index: 1000; background-color: rgb(220, 226, 241);top: 0px; left: 0px; padding: 0px; margin: 0px; width: 100%; height: 100%;"></div>
	<div class="blockUI_progress" style="position: absolute; z-index: 1001; margin: 0px; padding: 0px; top: 290.5px; left: 540px;"></div>
	<div class="toolbar">
	  <div class="toolbar_left"> 
	  	  <a href="javascript:void(0);" class="btn queryLogBtn" id="queryLogOfMonthRange" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">一月内异常日志</a> 
	  	  <a href="javascript:void(0);" class="btn queryLogBtn" id="queryLogOfWeekRange" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">一周内日志</a> 
	  </div>
	  <div class="toolbar_right">
	      <input type="text" class="input4 input4_onblur_haveValue" id="bdate"   onclick="WdatePicker()" value="${bdate}">
	      <span style='float:left;margin-right:5px;line-height:25px' id='dataSpan'>-</span>
	      <input type="text" class="input4 input4_onblur_haveValue" id="edate" onclick="WdatePicker()" value="${edate}">
	    <input type="text"  class="input4" id="companyCode" value="公司代码">
	    <span style="float:left;padding:5px"><input type="checkbox" id="errorLog">&nbsp;仅异常日志</span>
	    <a href="javascript:void(0);" class="btn queryLogBtn" id="queryLogOfCommon" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">检索日志</a> 
	    <a href="javascript:void(0);" class="btn" id="expertExcel" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">导出日志</a> 
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
function removeMask(){
	$('.blockUI_class').remove();
	$('.blockUI_progress').remove();
}
$(function(){
	var type='${type}';
	var queryCondition;
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
				height:$(window).height()-110,//
				multiselect:false,
				caption:'运行日志',
				pager:"#monitorGridTbar",
				eventModels:{
					loadComplete: function(){
						$("#monitorGrid [aria-describedby='monitorGrid_optflag']").each(function(i,v){
							var value = $(v).attr('title')=='S'?'操作成功':'操作失败';
							$(v).attr('title',value);
							$(v).html(value);
						});
						var dataLength =$('#monitorGrid').jqGrid('getRowData').length;
						if(queryCondition&&dataLength<1){
							alert('没有符合条件的数据');
						}
					}
				}
			});
		}
		//new customGrid(monitorGridOptions);
		$("#monitorGrid").extendJqgrid(monitorGridOptions);
	}
	function initElEvent(){
		$(".input4").focus(function(){
			  $(this).attr('class','input4_onfocus');
			  if($(this).val() ==this.defaultValue){  
                  $(this).val("");           
			  } 
		}).blur(function(){
			$(this).attr('class','input4_onblur_haveValue');
			 if ($(this).val() == '') {
                $(this).val(this.defaultValue);
                if($(this).attr('id')=='companyCode'){
                	$(this).attr('class','input4');
                }
             }
		});
		$('#expertExcel').bind('click',function(e){
			var dataLength =$('#monitorGrid').jqGrid('getRowData').length;
			if(queryCondition&&dataLength>0){
				open('${ctx}/application/fi/company/runtimeMonitor/exportLog?bdate='+queryCondition.bdate+'&edate='+queryCondition.edate+'&optflag='+queryCondition.optflag+'&companyCode='+queryCondition.companyCode,'_self');
			}else{
				alert('没有可导出的数据，请重新检索。');
			}
		});
		$(".queryLogBtn").bind('click',function(e){
			//$(window).blockUI();
			var bdate = '';
			var edate = '';
			var companyCode = '';
			var errorLog = '';
			var id = $(e.target).attr('id');
			var url = '${ctx}/application/fi/company/runtimeMonitor/findLog';
			jQuery('#monitorGrid').jqGrid('setGridParam',{url:url,page:1});
			var postdata=jQuery('#monitorGrid').jqGrid('getGridParam','postData');
			if(id=='queryLogOfCommon'){
				bdate = $("#bdate").val();
				edate = $("#edate").val();
				companyCode = ($("#companyCode").val()=='公司代码'?'':$("#companyCode").val());
				errorLog = $("#errorLog").attr('checked')=='checked'?'E':'';
				var b = bdate.replace('-','').replace('-','');
				var e = edate.replace('-','').replace('-','');
				var title = (errorLog=='E'?'异常':'完整')+"运行日志（"+b+"-"+e+"）";
				jQuery("#kna1Grid").extendJqgrid('resetCaption',{caption:title,id:'monitorGrid'});
			}else if(id=='queryLogOfMonthRange'){
				bdate = $("#bdateOfMonthRange").val();
				edate = $("#edateOfMonthRange").val();
				companyCode = '';
				errorLog = 'E';
				var b = bdate.replace('-','').replace('-','');
				var e = edate.replace('-','').replace('-','');
				var title = "一个月内异常运行日志（"+b+"-"+e+"）";
				jQuery("#kna1Grid").extendJqgrid('resetCaption',{caption:title,id:'monitorGrid'});
			}else if(id=='queryLogOfWeekRange'){
				bdate = $("#bdateOfWeekRange").val();
				edate = $("#edateOfWeekRange").val();
				companyCode = '';
				var b = bdate.replace('-','').replace('-','');
				var e = edate.replace('-','').replace('-','');
				var title = "一周内完整运行日志（"+b+"-"+e+"）";
				jQuery("#kna1Grid").extendJqgrid('resetCaption',{caption:title,id:'monitorGrid'});
			}
			queryCondition={bdate:bdate,edate:edate,companyCode:companyCode,optflag:errorLog};
			$.extend(postdata, {bdate:bdate,edate:edate,companyCode:companyCode,optflag:errorLog});
			jQuery("#monitorGrid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
			//$(window).blockUI('remove');
			/*
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
			*/
		});
	}
});
</script>
</html>