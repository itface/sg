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
  <div class="prompt_message">本功能用来检查SAP和Garden数据的一致性，有差异的数据可以从SAP同步到Garden。如果数据量大，运行时间可能较长</div>
  <div class="toolbar_left"> 
    <a href="javascript:void(0);" class="btn" onMouseDown="this.className='btn_mousedown'" id='compareData' onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">开始校对</a> 
    <a href="javascript:void(0);" class="btn" onMouseDown="this.className='btn_mousedown'" id='sapToLocal' onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'" style="display:none">从SAP同步到Garden</a>  
     </div>
   <div class="check_text" style="padding:0px"></div>
</div>
<div style="padding:5px 10px; clear:both">
  <table id="compareDataGrid">
  </table>
</div>
	<!-- input type='button' value='校对执行' id='compareData'/>
	<input type='button' value='SAP同步到本系统' id='sapToLocal'/>
	<table id='compareDataGrid'></table-->
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
			$.extend(compareDataGridOptions,{
				datatype:'local',
				loadonce:true,
				contextPath:"${ctx}",
				height:$(window).height()-180,
				autoWidth:true,
				caption:'对比结果',
				loadui:'',
				id:"compareDataGrid", 
				eventModels:{
					afterInsertRow: function(rowid,rowdata,rowelem){
						for(v in rowdata){
							if(v.indexOf('_s')==(v.length-'_s'.length)){
								var _g = v.substring(0,v.indexOf('_s'))+"_g";
								if(rowdata[v]!=rowdata[_g]){
									$('#compareDataGrid').setCell (rowid,v,rowdata[v],{background:"red"});
								}
							}else if(v.indexOf('_g')==(v.length-'_g'.length)){
								var _s = v.substring(0,v.indexOf('_g'))+"_s";
								if(rowdata[v]!=rowdata[_s]){
									$('#compareDataGrid').setCell (rowid,v,rowdata[v],{background:"red"});
								}
							}
						}
					},
					loadComplete:function(json){
						if(json.differenceNum!=undefined&&json.differenceNum!=null){
							$(window).blockUI('remove');
							if(json.differenceNum>0){
								$('#sapToLocal').show();
							}else{
								$('#sapToLocal').hide();
							}
							showCompareResult(json.totalSapNum,json.totalGardenNum,json.onlySapNum,json.onlyGardenNum,json.differenceNum,json.sameNum);
							alert("数据校对完成");
						}
					}
				}
			});
		}
		//new customGrid(compareDataGridOptions);
		$("#compareDataGrid").extendJqgrid(compareDataGridOptions);
	}
	function showCompareResult(totalSapNum,totalGardenNum,onlySapNum,onlyGardenNum,differenceNum,sameNum){
		var s = "";
		if(parseInt(differenceNum)>0){
			 s = "数据校对有差异。SAP有主数据"+totalSapNum+"条，Garden有数据"+totalGardenNum+"条。SAP有Garden无的数据有"+onlySapNum+"条，Garden有SAP无的数据有"+onlyGardenNum+"条。<br>完全相同的有"+sameNum+"条，有数据项差异的有"+differenceNum+"条。";
		}else{
			 s = "数据校对无差异。SAP有主数据"+totalSapNum+"条，Garden有数据"+totalGardenNum+"条。完全相同的有"+sameNum+"条。";
		}
		$(".check_text").empty();
		$(".check_text").append(s);
	}
	function initElEvent(){
		$('#compareData').bind('click',function(e){
			$(window).blockUI();
			jQuery("#compareDataGrid").jqGrid('setGridParam',{datatype:'json',url:'${ctx}/application/fi/company/compareData/compare',search:false}).trigger("reloadGrid");
			/*
			$(window).blockUI();
			$.ajax({
				url:'${ctx}/application/fi/company/compareData/compare',
				type: "GET",
				dataType:"json",
				success:function(json){
						try{
							if(json!=null){
								var data = json.dataList;
								//jQuery("#compareDataGrid").jqGrid('setGridParam',{datatype:'json',data:data,search:false}).trigger("reloadGrid");
								jQuery("#compareDataGrid").clearGridData(true);
								jQuery("#compareDataGrid")[0].addJSONData(data);
								if(json.differenceNum>0){
									$('#sapToLocal').show();
								}else{
									$('#sapToLocal').hide();
								}
								showCompareResult(json.totalSapNum,json.totalGardenNum,json.onlySapNum,json.onlyGardenNum,json.differenceNum,json.sameNum);
							}
							alert("对比成功");
							$(window).blockUI('remove');
						}catch(e){
							alert('装载数据异常');
							$(window).blockUI('remove');
						}
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					alert('校对失败');
					$(window).blockUI('remove');
				}
			});*/
		});
		$('#sapToLocal').bind('click',function(e){
			$(window).blockUI();
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
		    }else{
		    	alert("请选择要同步的记录");
		    	$(window).blockUI('remove');
		    	return false;
		    }
			$.ajax({
				type:"POST",
				//contentType:'application/json;charset=UTF-8',
				data:{list:s},
				//dataType:'json',
				url:'${ctx}/application/fi/company/compareData/sapSynToLocal',
				success:function(msg){
					alert('同步成功');
					rowData = jQuery('#compareDataGrid').jqGrid('getGridParam','selarrrow');
					var rowids = [];
					if(rowData.length) {
		        		for(var i=0;i<rowData.length;i++){
		        			rowids.push(rowData[i]);
		           		}
			           	for(var i in rowids){
		           			var ret = jQuery("#compareDataGrid").jqGrid('delRowData',rowids[i]);
		           		}
		           	}
					$(window).blockUI('remove');
					//$('#compareData').click();
					//$(window).blockUI('remove');
				},
				error:function(){
					alert('同步异常');
					$(window).blockUI('remove');
				}
				
			});
		});
	}
});
</script>
</html>