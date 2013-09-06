<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../../../inc/header.jsp"%>
<html>
<head>
<base href="${ctx}"/> 
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link href="${ctx}/css/sapgarden/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/sapgarden/garden.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/icon.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.jqgrid.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.ui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/script/blockUI/blockUI.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.json-2.4.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_extend.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/extendJqgrid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.form/jquery.form.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/easyui-lang-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/jquery.easyui.min.js'/>" type="text/javascript"></script>
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
  <div class="prompt_message">本功能根据数据的关键字,从SAP中读取相应的数据，可以测试系统的连通性，也可以同步指定的SAP数据到Garden</div>
  <div class="toolbar_left"> 
  	<a href="javascript:void(0);" class="btn" id='testCall' onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">测试执行</a>  
    <a href="javascript:void(0);" class="btn" id='manualSyn' onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">从SAP同步到Garden</a>  
   </div>
</div>
<div style="padding:5px 10px; clear:both;">
  <table id="resultGrid"></table>
</div>
</form>
</body>
<script>
$(function(){
	init();
	function init(){
		initElEvent();
		dynamicGrid();
	}
	function dynamicGrid(){
		var resultGridOptions=${resultGridOptions};
		$.extend(resultGridOptions,{
				contextPath:"${ctx}",
				autoWidth:true,
				id:"resultGrid",
				height:$(window).height()-200,
			}
		);
		//new customGrid(resultGridOptions);
		$("#resultGrid").extendJqgrid(resultGridOptions);
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
		$('#testCall').bind('click',function(e){
			$(window).blockUI();
			$.ajax({
				url:'${ctx}/application/fi/company/manualSyn/testCall',
				type: "GET",
				dataType:"json",
				success:function(json){
						try{
							if(json!=null){
								jQuery("#resultGrid").clearGridData(true);
								jQuery("#resultGrid")[0].addJSONData(json);
							}
							alert('测试执行成功');
							$(window).blockUI('remove');
						}catch(e){
							$(window).blockUI('remove');
							alert('装载数据异常');
						}
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					alert('调用失败');
					$(window).blockUI('remove');
				}
			});
		});
		$('#manualSyn').bind('click',function(e){
			$(window).blockUI();
			$.ajax({
				url:'${ctx}/application/fi/company/manualSyn/syn',
				success:function(json){
					try{
						if(json!=null){
							jQuery("#resultGrid").clearGridData(true);
							jQuery("#resultGrid")[0].addJSONData(json);
						}
						alert('手工同步成功');
						$(window).blockUI('remove');
					}catch(e){
						alert('手工同步异常');
						$(window).blockUI('remove');
					}
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					alert('手工同步异常');
					$(window).blockUI('remove');
				}
			});
			
		});
	}
});
</script>
</html>