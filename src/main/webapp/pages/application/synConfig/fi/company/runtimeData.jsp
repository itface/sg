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
    <input type="text" class="input1" id="companyCode" value="公司代码">
    <a href="#" class="btn" id="queryBtn" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">查询</a> 
    <a href="#" class="btn" id="expertExcel" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'" style="display:none">导出数据</a>  
  </div>
  
</div>
<div style="padding:5px 10px; clear:both">
  <table id="dataGrid">
  </table>
</div>
</form>
</body>
<script>
$(function(){
	var type='${type}';
	init();
	function init(){
		dynamicGrid();
		initEvent();
	}
	function dynamicGrid(){
		var dataGridOptions=${dataGridOptions};
		if(dataGridOptions!=null){
			$.extend(dataGridOptions,{
				contextPath:"${ctx}",
				height:$(window).height()-180,
				autoWidth:true,
				id:"dataGrid",
				caption:'公司代码',
				pager:"#tbar",
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
		}
		//new customGrid(dataGridOptions);
		$("#dataGrid").extendJqgrid(dataGridOptions);
	}
	function initEvent(){
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
			var gsdm = $("#companyCode").val();
			if(gsdm=='公司代码'){
				gsdm='';
			}
			open('${ctx}/application/fi/company/runtimeData/exportExcel?companyCode='+gsdm,'_self');
		});
		$('#queryBtn').bind('click',function(e){
			var gsdm = $("#companyCode").val();
			if(gsdm=='公司代码'){
				gsdm='';
			}
			var url = '${ctx}/application/fi/company/runtimeData/findJqgridData';
			jQuery('#dataGrid').jqGrid('setGridParam',{url:url,page:1});
			var postdata=jQuery('#dataGrid').jqGrid('getGridParam','postData');
			$.extend(postdata, {companyCode:gsdm});
			jQuery("#dataGrid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
			/*
			$.ajax({
				url:'${ctx}/application/fi/company/runtimeData/findJqgridData',
				type: "GET",
				dataType:"json",
				data:{companyCode:gsdm},
				success:function(json){
						try{
							if(json!=null){
								jQuery("#dataGrid").clearGridData(true);
								jQuery("#dataGrid")[0].addJSONData(json);
							}
							$(window).blockUI('remove');
						}catch(e){
							$(window).blockUI('remove');
							alert('装载数据异常');
						}
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					alert('查询失败');
					$(window).blockUI('remove');
				}
			});*/
		});
	}

});
</script>
</html>