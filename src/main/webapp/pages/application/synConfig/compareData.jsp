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
	<input type='button' value='校对执行' id='compareData'/>
	<input type='button' value='SAP同步到本系统' id='sapToLocal'/>
	<table id='compareDataGrid'></table>
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
		var compareDataGridOptions=${compareDataGridOptions};
		if(compareDataGridOptions!=null){
			$.extend(compareDataGridOptions,{contextPath:"${ctx}"});
		}
		new customGrid(compareDataGridOptions);
	}
	function initElEvent(){
		$('#compareData').bind('click',function(e){
			$.ajax({
				url:'${ctx}/application/synConfig/${type}/compareData/compare',
				type: "GET",
				dataType:"json",
				success:function(json){
						try{
							if(json!=null){
								jQuery("#compareDataGrid").clearGridData(true);
								jQuery("#compareDataGrid")[0].addJSONData(json);
							}
							alert("对比成功");
						}catch(e){
							alert('装载数据异常');
						}
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					alert('校对失败');
				}
			});
		});
		$('#sapToLocal').bind('click',function(e){
			var rowData = jQuery('#compareDataGrid').jqGrid('getGridParam','selarrrow');
			var s = '';
		    if(rowData.length) {
		        for(var i=0;i<rowData.length;i++){
		           var ret = jQuery("#compareDataGrid").jqGrid('getRowData',rowData[i]);
		           s+="{";
		           for(var name in ret){
		           		s+=name+":'"+ret[name]+"',";
		           }
		           s=s.substring(0,s.lastIndexOf(','))+"},";
		        }
		        s=s.substring(0,s.lastIndexOf(','));
		        s="{data:["+s+"]}"
		    }
			$.ajax({
				type:"POST",
				//contentType:'application/json;charset=UTF-8',
				data:{list:s},
				//dataType:'json',
				url:'${ctx}/application/synConfig/${type}/compareData/sapSynToLocal',
				success:function(msg){
					alert('同步成功');
					$('#compareData').click();
				},
				error:function(){
					alert('同步异常');
				}
				
			});
			/*
			var rowData = jQuery('#compareDataGrid').jqGrid('getGridParam','selarrrow');
		    if(rowData.length) 
		    {
		        for(var i=0;i<rowData.length;i++)
		        {
		           var name= jQuery('#compareDataGrid').jqGrid('getCell',rowData[i],'comp_code_s');
		           var ret = jQuery("#compareDataGrid").jqGrid('getRowData',rowData[i]);
		           var array = jQuery("#compareDataGrid").jqGrid('getRowData');
		           alert(name+":"+ret.comp_code_s);
		        }
		    }
		    */
		});
	}
});
</script>
</html>