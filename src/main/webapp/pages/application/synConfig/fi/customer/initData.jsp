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
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/easyui-lang-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/jquery.easyui.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/lhgdialog/lhgdialog.js?skin=default'/>" type="text/javascript"></script>
<script src="<c:url value='/script/blockUI/blockUI.js'/>" type="text/javascript"></script>
</head>
<body onload="setTimeout('removeMask()',200)">
<form>
<div class="transparent_class blockUI_class" style="position: absolute; z-index: 1000; background-color: rgb(220, 226, 241);top: 0px; left: 0px; padding: 0px; margin: 0px; width: 100%; height: 100%;"></div>
<div class="blockUI_progress" style="position: absolute; z-index: 1001; margin: 0px; padding: 0px; top: 290.5px; left: 540px;"></div>
<div class="toolbar">
  <div class="prompt_message">慎重操作！本功能用来将SAP中的数据初始化到Garden系统中。初始化将校验数据结构，然后清空Garden中已有的数据，最后从SAP导入数据。数据清空后无法恢复。</div>
  <div class="toolbar_left"> 
  		<a href="#" class="btn" id='initData' onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">开始初始化</a> 
     <a href="#" class="btn" id="expertExcel" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'" style="display:none">导出数据</a>  
     </div>
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
</form>
</body>
<script>
function removeMask(){
	$('.blockUI_class').remove();
	$('.blockUI_progress').remove();
}
$(function(){
		init();
		function init(){
			initEvent();
			dynamicGrid();
			initTab();
			initGridTitle('kna1Grid');
			initGridTitle('knb1Grid');
			initGridTitle('knvvGrid');
		}
		function initTab(){
			$('#mainPanel .tabs-header').css('border-top','1px solid #c5c5c5');
			$('#mainPanel .tabs-header').css('border-right','1px solid #c5c5c5');
			$('#mainPanel .tabs').css('padding-left',0);
		}
		function initGridTitle(gridId){
			$('#gbox_'+gridId+' .ui-jqgrid-titlebar').remove();
		}
		function dynamicGrid(){
			var kna1GridOptions=${kna1GridOptions};
			var knb1GridOptions=${knb1GridOptions};
			var knvvGridOptions=${knvvGridOptions};
			if(kna1GridOptions!=null){
				$.extend(kna1GridOptions,{
					contextPath:"${ctx}",
					height:$(window).height()-215,
					autoWidth:true,
					pager:"#tbar",
					id:"kna1Grid",
					caption:'客户一般数据(KNA1)',
					eventModels:{
						loadComplete:function(json){
							if(json!=null&&json!=''&&json.rows.length>0){
								$('#expertExcel').show();
							}else{
								$('#expertExcel').hide();
							}
						}
					}
				});
				$("#kna1Grid").extendJqgrid(kna1GridOptions);
			}
			if(knb1GridOptions!=null){
				$.extend(knb1GridOptions,{
					contextPath:"${ctx}",
					height:$(window).height()-215,
					autoWidth:true,
					id:"knb1Grid",
					pager:"#tbar1",
					caption:'客户公司代码数据(KNB1)',
					eventModels:{
						loadComplete:function(json){
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
				$("#knb1Grid").extendJqgrid(knb1GridOptions);
			}
			if(knvvGridOptions!=null){
				$.extend(knvvGridOptions,{
					contextPath:"${ctx}",
					height:$(window).height()-215,
					autoWidth:true,
					id:"knvvGrid",
					pager:"#tbar2",
					caption:'客户销售数据(KNVV)',
					eventModels:{
						loadComplete:function(json){
							
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
				$("#knvvGrid").extendJqgrid(knvvGridOptions);
			}
		}
		function queryData(){
			
			var baseurl = '${ctx}/application/fi/customer/runtimeData/';
			jQuery('#kna1Grid').jqGrid('setGridParam',{url:baseurl+'kna1',page:1});
			var postdata=jQuery('#kna1Grid').jqGrid('getGridParam','postData');
			jQuery("#kna1Grid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
			
			jQuery('#knb1Grid').jqGrid('setGridParam',{url:baseurl+'knb1',page:1});
			var postdata=jQuery('#knb1Grid').jqGrid('getGridParam','postData');
			jQuery("#knb1Grid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
			
			jQuery('#knvvGrid').jqGrid('setGridParam',{url:baseurl+'knvv',page:1});
			var postdata=jQuery('#knvvGrid').jqGrid('getGridParam','postData');
			jQuery("#knvvGrid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
		}
		function initEvent(){
			$('#initData').bind('click',function(e){
				if(!confirm("初始化将会清空Garden中现有的【客户】数据，确定要执行吗？")){
					return false;
				}
				var dialog = $.dialog({
			 		id:'dia',
				    lock: true,
				    width: 500,
			    	height: 230,
				    min:false,
				    max:false,
				    cancel:false,
				    background: '#FFF',
				    opacity: 0.5,
				    content: 'url:${ctx}/pages/application/synConfig/fi/customer/progress.jsp',
				    title:''
				});
				$.dialog.data('dialog',dialog);//：跨框架数据共享写入接口
				$.dialog.data('queryData',queryData);
				$.dialog.data('client',"${client}");
			});
			
			$('#expertExcel').bind('click',function(e){
				open('${ctx}/application/fi/customer/runtimeData/exportExcel','_self');
			});
		}
		
		
});
</script>
</html>