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
<body onload="setTimeout('removeMask()',200)">
<form>
<div class="transparent_class blockUI_class" style="position: absolute; z-index: 1000; background-color: rgb(220, 226, 241);top: 0px; left: 0px; padding: 0px; margin: 0px; width: 100%; height: 100%;"></div>
<div class="blockUI_progress" style="position: absolute; z-index: 1001; margin: 0px; padding: 0px; top: 290.5px; left: 540px;"></div>
<div class="toolbar">
  <div class="prompt_message">本功能根据数据的关键字,从SAP中读取相应的数据，可以测试系统的连通性，也可以同步指定的SAP数据到Garden</div>
  <div class="toolbar_left"> 
  	<input type='text' id='kunnr' class='input3' value="客户编号"/>
  	<a href="javascript:void(0);" class="btn" id='testCall' onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">查询SAP数据</a>  
    <a href="javascript:void(0);" class="btn" id='manualSyn' onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'" style="display:none">从SAP同步到Garden</a>  
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
var queryflag = false;
function removeMask(){
	$('.blockUI_class').remove();
	$('.blockUI_progress').remove();
}
$(function(){
	init();
	function init(){
		initElEvent();
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
		$('#'+gridId+'_toppager').remove();
	}
	function dynamicGrid(){
		var kna1GridOptions=${kna1GridOptions};
		var knb1GridOptions=${knb1GridOptions};
		var knvvGridOptions=${knvvGridOptions};
		$.extend(kna1GridOptions,{
				contextPath:"${ctx}",
				autoWidth:true,
				id:"kna1Grid",
				multiselect:true,
				loadui:'',
				sortable:false,
				loadonce:true,
				height:$(window).height()-150
			}
		);
		$.extend(knb1GridOptions,{
				contextPath:"${ctx}",
				autoWidth:true,
				id:"knb1Grid",
				multiselect:true,
				loadui:'',
				sortable:false,
				loadonce:true,
				height:$(window).height()-150,
				eventModels:{
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
			}
		);
		$.extend(knvvGridOptions,{
				contextPath:"${ctx}",
				autoWidth:true,
				id:"knvvGrid",
				multiselect:true,
				loadui:'',
				sortable:false,
				loadonce:true,
				height:$(window).height()-150,
				eventModels:{
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
			}
		);
		//new customGrid(resultGridOptions);
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
		$('#testCall').bind('click',function(e){
			if($('#kunnr').val()==null||$('#kunnr').val()==''||$('#kunnr').val()=='客户编号'){
				alert('客户编号不能为空。');
				return false;
			}
			$(window).blockUI();
			$.ajax({
				url:'${ctx}/application/fi/customer/manualSyn/getSapData',
				type: "GET",
				data:{kunnr:$('#kunnr').val()},
				dataType:"json",
				success:function(json){
						try{
							if(json!=null){
								jQuery("#kna1Grid").clearGridData(true);
								jQuery("#knb1Grid").clearGridData(true);
								jQuery("#knvvGrid").clearGridData(true);
								jQuery("#kna1Grid")[0].addJSONData(json.kna1);
								jQuery("#knb1Grid")[0].addJSONData(json.knb1);
								jQuery("#knvvGrid")[0].addJSONData(json.knvv);
							}
							alert('查询成功');
							$('#manualSyn').show();
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
			});
		});
		$('#manualSyn').bind('click',function(e){
			$(window).blockUI();
			var kna1rowData = jQuery('#kna1Grid').jqGrid('getGridParam','selarrrow');
			var knb1rowData = jQuery('#knb1Grid').jqGrid('getGridParam','selarrrow');
			var knvvrowData = jQuery('#knvvGrid').jqGrid('getGridParam','selarrrow');
			var kna1s  = '';
			var knb1s  = '';
			var knvvs  = '';
		    if(kna1rowData.length||knb1rowData.length||knvvrowData.length) {
		    	if(kna1rowData.length){
		    		kna1s  = '{"list":[';
		    		for(var i=0;i<kna1rowData.length;i++){
			           var ret = jQuery("#kna1Grid").jqGrid('getRowData',kna1rowData[i]);
			           kna1s+="{";
			           for(var name in ret){
			           		kna1s+=name+":'"+ret[name]+"',";
			           }
			           kna1s=kna1s.substring(0,kna1s.lastIndexOf(','))+"},";
			        }
			        kna1s=kna1s.substring(0,kna1s.lastIndexOf(','));
			        kna1s+="]}";
		    	}
		        if(knb1rowData.length){
			        knb1s  = '{"list":[';
			        for(var i=0;i<knb1rowData.length;i++){
			           var ret = jQuery("#knb1Grid").jqGrid('getRowData',knb1rowData[i]);
			           knb1s+="{";
			           for(var name in ret){
			           		knb1s+=name+":'"+ret[name]+"',";
			           }
			           knb1s=knb1s.substring(0,knb1s.lastIndexOf(','))+"},";
			        }
			        knb1s=knb1s.substring(0,knb1s.lastIndexOf(','));
			        knb1s+="]}";
			    }
			    if(knvvrowData.length){
			        knvvs  = '{"list":[';
			        for(var i=0;i<knvvrowData.length;i++){
			           var ret = jQuery("#knvvGrid").jqGrid('getRowData',knvvrowData[i]);
			           knvvs+="{";
			           for(var name in ret){
			           		knvvs+=name+":'"+ret[name]+"',";
			           }
			           knvvs=knvvs.substring(0,knvvs.lastIndexOf(','))+"},";
			        }
			        knvvs=knvvs.substring(0,knvvs.lastIndexOf(','));
			        knvvs+="]}";
			   }
		    }else{
		    	alert("请选择要同步的记录");
		    	$(window).blockUI('remove');
		    	return false;
		    }
			$.ajax({
				url:'${ctx}/application/fi/customer/manualSyn/syn',
				data:{kna1s:kna1s,knb1s:knb1s,knvvs:knvvs},
				type:'POST',
				success:function(){
					try{
						kna1rowData = jQuery('#kna1Grid').jqGrid('getGridParam','selarrrow');
						knb1rowData = jQuery('#knb1Grid').jqGrid('getGridParam','selarrrow');
						knvvrowData = jQuery('#knvvGrid').jqGrid('getGridParam','selarrrow');
						var rowids = [];
						if(kna1rowData.length) {
			        		for(var i=0;i<kna1rowData.length;i++){
			        			rowids.push(kna1rowData[i]);
			           		}
				           	for(var i in rowids){
			           			var ret = jQuery("#kna1Grid").jqGrid('delRowData',rowids[i]);
			           		}
			           	}
			           	rowids = [];
						if(knb1rowData.length) {
			        		for(var i=0;i<knb1rowData.length;i++){
			        			rowids.push(knb1rowData[i]);
			           		}
				           	for(var i in rowids){
			           			var ret = jQuery("#knb1Grid").jqGrid('delRowData',rowids[i]);
			           		}
			           	}
			           	rowids = [];
						if(knvvrowData.length) {
			        		for(var i=0;i<knvvrowData.length;i++){
			        			rowids.push(knvvrowData[i]);
			           		}
				           	for(var i in rowids){
			           			var ret = jQuery("#knvvGrid").jqGrid('delRowData',rowids[i]);
			           		}
			           	}
						alert('选择的数据从SAP同步到Garden同步成功。');
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