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
<link rel="stylesheet" type="text/css" href="<c:url value='/script/blockUI/blockUI.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_extend.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/extendJqgrid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/easyui-lang-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/jquery.easyui.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/blockUI/blockUI.js'/>" type="text/javascript"></script>
<style>
.fm-button-icon-left{
	display:none;
}
</style>
</head>
<body>
<form>
	<div class="toolbar">
	  <div class="toolbar_left"> 
	  	  <a href="javascript:void(0);" class="btn queryLogBtn" id="queryLogOfMonthRange" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">一月内异常日志</a> 
	  	  <a href="javascript:void(0);" class="btn queryLogBtn" id="queryLogOfWeekRange" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">一周内日志</a> 
	  </div>
	  <div class="toolbar_right">
	      <input type="text" class="input1" id="bdate"   onclick="WdatePicker()" value="${bdate}">
	      <span style='float:left;margin-right:5px;line-height:25px' id='dataSpan'>-</span>
	      <input type="text" class="input1" id="edate" onclick="WdatePicker()" value="${edate}">
	    <!-- input type="text"  class="input1" id="companyCode" value="公司代码"-->
	    <span style="float:left;padding:5px"><input type="checkbox" id="errorLog">&nbsp;仅异常日志</span>
	    <a href="javascript:void(0);" class="btn queryLogBtn" id="queryLogOfCommon" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">检索日志</a> 
	    <a href="javascript:void(0);" class="btn" id="expertExcel" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">导出日志</a> 
	    <a href="javascript:void(0);" style="display:none" class="btn" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">运行统计</a> </div>
	</div>

	<div  class="easyui-tabs" style="padding:5px 10px; clear:both" id="mainPanel">
	    <div title="客户一般数据(KNA1)">
			<table id='kna1Grid'></table>
		</div>
		<div title="客户公司代码数据(KNB1)">
			<table id='knb1Grid'></table>
		</div>
		<div title="客户销售数据(KNVV)">
			<table id='knvvGrid'></table>
		</div>
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
	var queryCondition;
	init();
	function init(){
		dynamicGrid();
		initElEvent();
	}
	function dynamicGrid(){
		var kna1GridOptions=${kna1GridOptions};
		var knb1GridOptions=${knb1GridOptions};
		var knvvGridOptions=${knvvGridOptions};
		if(kna1GridOptions!=null){
			$.extend(kna1GridOptions,{
				contextPath:"${ctx}",
				autoWidth:true,
				id:"kna1Grid",
				height:$(window).height()-180,//
				multiselect:false,
				caption:'运行监控',
				pager:"#monitorGridTbar",
				eventModels:{
					loadComplete: function(){
						$("#kna1Grid [aria-describedby='kna1Grid_optflag']").each(function(i,v){
							var value = $(v).attr('title')=='S'?'操作成功':'操作失败';
							$(v).attr('title',value);
							$(v).html(value);
						});
					}
				}
			});
		}
		if(knb1GridOptions!=null){
			$.extend(knb1GridOptions,{
				contextPath:"${ctx}",
				autoWidth:true,
				id:"knb1Grid",
				height:$(window).height()-180,//
				multiselect:false,
				caption:'运行监控',
				pager:"#monitorGridTbar",
				eventModels:{
					loadComplete: function(){
						$("#knb1Grid [aria-describedby='knb1Grid_optflag']").each(function(i,v){
							var value = $(v).attr('title')=='S'?'操作成功':'操作失败';
							$(v).attr('title',value);
							$(v).html(value);
						});
					},
					gridComplete:function(){
						var toppagerWidth = $('#mainPanel .panel-body').css('width');
						if(toppagerWidth.indexOf('px')>0){
							toppagerWidth=toppagerWidth.substring(0,toppagerWidth.lastIndexOf('px'));
							toppagerWidth=toppagerWidth-2;
						}
						$('#knb1Grid_toppager').css('width',toppagerWidth);
						$('#gbox_knb1Grid').css('width',toppagerWidth);
						$('#gview_knb1Grid').css('width',(toppagerWidth));
						$('#gview_knb1Grid .ui-jqgrid-bdiv').css('width',toppagerWidth);
						$('#pg_knb1Grid_toppager').css('width',toppagerWidth);
						$('#gview_knb1Grid .ui-jqgrid-hdiv').css('width',toppagerWidth);
					}
				}
			});
		}
		if(knvvGridOptions!=null){
			$.extend(knvvGridOptions,{
				contextPath:"${ctx}",
				autoWidth:true,
				id:"knvvGrid",
				height:$(window).height()-180,//
				multiselect:false,
				caption:'运行监控',
				pager:"#monitorGridTbar",
				eventModels:{
					loadComplete: function(){
						$("#knvvGrid [aria-describedby='knvvGrid_optflag']").each(function(i,v){
							var value = $(v).attr('title')=='S'?'操作成功':'操作失败';
							$(v).attr('title',value);
							$(v).html(value);
						});
					},
					gridComplete:function(){
						var toppagerWidth = $('#mainPanel .panel-body').css('width');
						if(toppagerWidth.indexOf('px')>0){
							toppagerWidth=toppagerWidth.substring(0,toppagerWidth.lastIndexOf('px'));
							toppagerWidth=toppagerWidth-2;
						}
						$('#knvvGrid_toppager').css('width',toppagerWidth);
						$('#gbox_knvvGrid').css('width',toppagerWidth);
						$('#gview_knvvGrid').css('width',(toppagerWidth));
						$('#gview_knvvGrid .ui-jqgrid-bdiv').css('width',toppagerWidth);
						$('#pg_knvvGrid_toppager').css('width',toppagerWidth);
						$('#gview_knvvGrid .ui-jqgrid-hdiv').css('width',toppagerWidth);
					}
				}
			});
		}
		$("#kna1Grid").extendJqgrid(kna1GridOptions);
		$("#knb1Grid").extendJqgrid(knb1GridOptions);
		$("#knvvGrid").extendJqgrid(knvvGridOptions);
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
		$('#expertExcel').bind('click',function(e){
			var kna1dataLength =$('#kna1Grid').jqGrid('getRowData').length;
			var knb1dataLength =$('#knb1Grid').jqGrid('getRowData').length;
			var knvvdataLength =$('#knvvGrid').jqGrid('getRowData').length;
			if(queryCondition&&(kna1dataLength>0||knb1dataLength>0||knvvdataLength>0)){
				open('${ctx}/application/fi/customer/runtimeMonitor/exportLog?bdate='+queryCondition.bdate+'&edate='+queryCondition.edate+'&optflag='+queryCondition.optflag+'&companyCode='+queryCondition.companyCode,'_self');
			}else{
				alert('没有可导出的数据，请重新检索。');
			}
		});
		$(".queryLogBtn").bind('click',function(e){
			var bdate = '';
			var edate = '';
			var errorLog = '';
			var id = $(e.target).attr('id');
			if(id=='queryLogOfCommon'){
				bdate = $("#bdate").val();
				edate = $("#edate").val();
				errorLog = $("#errorLog").attr('checked')=='checked'?'E':'';
				jQuery("#kna1Grid").jqGrid('setCaption',"检索日志");
				jQuery("#knb1Grid").jqGrid('setCaption',"检索日志");
				jQuery("#knvvGrid").jqGrid('setCaption',"检索日志");
			}else if(id=='queryLogOfMonthRange'){
				bdate = $("#bdateOfMonthRange").val();
				edate = $("#edateOfMonthRange").val();
				errorLog = 'E';
				jQuery("#kna1Grid").jqGrid('setCaption',"一个月内异常日志");
				jQuery("#knb1Grid").jqGrid('setCaption',"一个月内异常日志");
				jQuery("#knvvGrid").jqGrid('setCaption',"一个月内异常日志");
			}else if(id=='queryLogOfWeekRange'){
				bdate = $("#bdateOfWeekRange").val();
				edate = $("#edateOfWeekRange").val();
				jQuery("#kna1Grid").jqGrid('setCaption',"一周内日志");
				jQuery("#knb1Grid").jqGrid('setCaption',"一周内日志");
				jQuery("#knvvGrid").jqGrid('setCaption',"一周内日志");
			}
			queryCondition={bdate:bdate,edate:edate,optflag:errorLog};
			var url = '${ctx}/application/fi/customer/runtimeMonitor/findLog/kna1';
			jQuery('#kna1Grid').jqGrid('setGridParam',{url:url,page:1});
			var postdata=jQuery('#kna1Grid').jqGrid('getGridParam','postData');
			$.extend(postdata, {bdate:bdate,edate:edate,optflag:errorLog});
			jQuery("#kna1Grid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
			
			url = '${ctx}/application/fi/customer/runtimeMonitor/findLog/knb1';
			jQuery('#knb1Grid').jqGrid('setGridParam',{url:url,page:1});
			postdata=jQuery('#knb1Grid').jqGrid('getGridParam','postData');
			$.extend(postdata, {bdate:bdate,edate:edate,optflag:errorLog});
			jQuery("#knb1Grid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
			
			url = '${ctx}/application/fi/customer/runtimeMonitor/findLog/knvv';
			jQuery('#knvvGrid').jqGrid('setGridParam',{url:url,page:1});
			var postdata=jQuery('#knvvGrid').jqGrid('getGridParam','postData');
			$.extend(postdata, {bdate:bdate,edate:edate,optflag:errorLog});
			jQuery("#knvvGrid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
		});
	}
});
</script>
</html>