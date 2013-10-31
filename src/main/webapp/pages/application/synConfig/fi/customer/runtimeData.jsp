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
<script src="<c:url value='/script/jquery.form/jquery.form.js'/>" type="text/javascript"></script>
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
  <div class="prompt_message">本功能用来浏览Garden系统中的数据。直接点击&ldquo;查询&rdquo;查询全部数据，也可以根据关键字查询</div>
  <div class="toolbar_left"> 
    <input type='text' id='kunnr' class='input3' value="客户编号"/>
    <a href="#" class="btn" id="queryBtn" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">查询</a> 
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
$(function(){
	init();
	function init(){
		initEvent();
		dynamicGrid();
	}
	function initEvent(){
		$('#expertExcel').bind('click',function(e){
			var kunnr = $('#kunnr').val()=="客户编号"?"":$('#kunnr').val();
			open('${ctx}/application/fi/customer/runtimeData/exportExcel?kunnr='+kunnr,'_self');
		});
		$(".input3").focus(function(){
			  $(this).attr('class','input3_onfocus');
			  if($(this).val() ==this.defaultValue){  
                  $(this).val("");           
			  } 
		}).blur(function(){
			  $(this).attr('class','input3');
			 if ($(this).val() == '') {
                $(this).val(this.defaultValue);
             }
		});
		$('#queryBtn').bind('click',function(e){
			var kunnr = $('#kunnr').val()=="客户编号"?"":$('#kunnr').val();
			var baseurl = '${ctx}/application/fi/customer/runtimeData/';
			jQuery('#kna1Grid').jqGrid('setGridParam',{url:baseurl+'kna1',page:1});
			var postdata=jQuery('#kna1Grid').jqGrid('getGridParam','postData');
			$.extend(postdata,{kunnr:kunnr});
			jQuery("#kna1Grid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
			
			jQuery('#knb1Grid').jqGrid('setGridParam',{url:baseurl+'knb1',page:1});
			postdata=jQuery('#knb1Grid').jqGrid('getGridParam','postData');
			$.extend(postdata,{kunnr:kunnr});
			jQuery("#knb1Grid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
			
			
			jQuery('#knvvGrid').jqGrid('setGridParam',{url:baseurl+'knvv',page:1});
			postdata=jQuery('#knvvGrid').jqGrid('getGridParam','postData');
			$.extend(postdata,{kunnr:kunnr});
			jQuery("#knvvGrid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
		});
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
});
</script>
</html>