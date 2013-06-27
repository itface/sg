<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../inc/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.jqgrid.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.ui.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_extend.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/commons.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_custom.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.form/jquery.form.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
</head>
<body>
<form>
	<input type='button' value='处理异常记录' id='processError'/>
	<table id='monitorGrid'></table>
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
			$.extend(monitorGridOptions,{contextPath:"${ctx}"});
		}
		new customGrid(monitorGridOptions);
	}
	function initElEvent(){
		$("#processError").bind('click',function(e){
			var rowData = jQuery('#monitorGrid').jqGrid('getGridParam','selarrrow');
			var ids = '';
		    if(rowData.length) {
		        for(var i=0;i<rowData.length;i++){
		           var ret = jQuery("#monitorGrid").jqGrid('getRowData',rowData[i]);
		           ids+=ret['id']+",";
		        }
		         ids=ids.substring(0,ids.lastIndexOf(','));
		         $.ajax({
					type:"POST",
					//contentType:'application/json;charset=UTF-8',
					async:false,
					data:{ids:ids},
					//dataType:'json',
					url:'${ctx}/application/synConfig/${type}/runtimeMonitor/processError',
					success:function(msg){
						alert('同步成功');
					},
					error:function(){
						alert('同步异常');
					}
				});
				jQuery('#monitorGrid').trigger("reloadGrid");
		    }else{
		    	alert("请选择要处理的记录");
		    }
		});
	}
});
</script>
</html>