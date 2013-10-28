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
  <div class="prompt_message">本功能用来检查SAP和Garden数据的一致性，有差异的数据可以从SAP同步到Garden。如果数据量大，运行时间可能较长</div>
  <div class="toolbar_left"> 
  			
    <a href="javascript:void(0);" class="btn" onMouseDown="this.className='btn_mousedown'" id='compareData' onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">开始校对</a> 
    <a href="javascript:void(0);" class="btn" onMouseDown="this.className='btn_mousedown'" id='sapToLocal' onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'" style="display:none">从SAP同步到Garden</a>  
     </div>
   <div class="check_text" style="padding:0px"></div>
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
<input type='hidden' id='kunnr' class='input3' value=""/>
<input type='hidden' id='bukrs' class='input3' value=""/>
<input type='hidden' id='vkorg' class='input3' value=""/>
<input type='hidden' id='vtweg' class='input3' value=""/>
<input type='hidden' id='spart' class='input3' value=''/>
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
		var kna1GridOptions=${kna1GridOptions};
		var knb1GridOptions=${knb1GridOptions};
		var knvvGridOptions=${knvvGridOptions};
		if(kna1GridOptions!=null){
			$.extend(kna1GridOptions,{
				contextPath:"${ctx}",
				height:$(window).height()-220,
				autoWidth:true,
				caption:'客户一般数据(KNA1)',
				//loadui:'',
				id:"kna1Grid", 
				pager:"#kna1Tbar",
				rowNum: 50,
				eventModels:{
					afterInsertRow: function(rowid,rowdata,rowelem){
						for(v in rowdata){
							if(v.indexOf('_old')==(v.length-'_old'.length)){
								var _g = v.substring(0,v.indexOf('_old'));
								if(rowdata[v]!=rowdata[_g]){
									$('#kna1Grid').setCell (rowid,v,rowdata[v],{background:"red"});
									$('#kna1Grid').setCell (rowid,_g,rowdata[_g],{background:"red"});
								}
							}
						}
					}
				}
			});
		}
		if(knb1GridOptions!=null){
			$.extend(knb1GridOptions,{
				contextPath:"${ctx}",
				height:$(window).height()-220,
				autoWidth:true,
				caption:'客户公司代码数据(KNB1)',
				//loadui:'',
				pager:"#knb1Tbar",
				id:"knb1Grid", 
				rowNum: 50,
				eventModels:{
					afterInsertRow: function(rowid,rowdata,rowelem){
						for(v in rowdata){
							if(v.indexOf('_old')==(v.length-'_old'.length)){
								var _g = v.substring(0,v.indexOf('_old'));
								if(rowdata[v]!=rowdata[_g]){
									$('#knb1Grid').setCell (rowid,v,rowdata[v],{background:"red"});
									$('#knb1Grid').setCell (rowid,_g,rowdata[_g],{background:"red"});
								}
							}
						}
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
				height:$(window).height()-220,
				pager:"#knvvTbar",
				caption:'客户销售数据(KNVV)',
				//loadui:'',
				rowNum: 50,
				id:"knvvGrid", 
				eventModels:{
					afterInsertRow: function(rowid,rowdata,rowelem){
						for(v in rowdata){
							if(v.indexOf('_old')==(v.length-'_old'.length)){
								var _g = v.substring(0,v.indexOf('_old'));
								if(rowdata[v]!=rowdata[_g]){
									$('#knvvGrid').setCell (rowid,v,rowdata[v],{background:"red"});
									$('#knvvGrid').setCell (rowid,_g,rowdata[_g],{background:"red"});
								}
							}
						}
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
	function showCompareResult(json){
		var s = "";
		if(json!=null){
			var totalSapNum= json.totalSapNum;
			var totalSapKna1Num=	json.totalSapKna1Num;
			var totalSapKnb1Num=	json.totalSapKnb1Num;
			var totalSapKnvvNum=	json.totalSapKnvvNum;
			var totalGardenNum=		json.totalGardenNum;
			var totalGardenKna1Num= json.totalGardenKna1Num;
			var totalGardenKnb1Num= json.totalGardenKnb1Num;
			var totalGardenKnvvNum= json.totalGardenKnvvNum;
			var onlySapNum=json.onlySapNum;
			var onlySapKna1Num=json.onlySapKna1Num;
			var onlySapKnb1Num=json.onlySapKnb1Num;
			var onlySapKnvvNum=json.onlySapKnvvNum;
			var onlyGardenNum=json.onlyGardenNum;
			var onlyGardenKna1Num=json.onlyGardenKna1Num;
			var onlyGardenKnb1Num=json.onlyGardenKnb1Num;
			var onlyGardenKnvvNum=json.onlyGardenKnvvNum;
			var differenceNum=json.differenceNum;
			var differenceKna1Num=json.differenceKna1Num;
			var differenceKnb1Num=json.differenceKnb1Num;
			var differenceKnvvNum=json.differenceKnvvNum;
			var sameNum=json.sameNum;
			var sameKna1Num=json.sameKna1Num;
			var sameKnb1Num=json.sameKnb1Num;
			var sameKnvvNum=json.sameKnvvNum;
		}
		if(parseInt(differenceNum)>0){
			 s = "数据校对有差异。SAP有主数据"+totalSapNum+"条，kna1："+totalSapKna1Num+"条、knb1："+totalSapKnb1Num+"条、knvv："+totalSapKnvvNum+"条。Garden有主数据"+totalGardenNum+"条，kna1："+totalGardenKna1Num+"条、knb1："+totalGardenKnb1Num+"条、knvv："+totalGardenKnvvNum+"条。";
			 if(parseInt(differenceKna1Num)>0){
			 	s+="kna1有"+differenceKna1Num+"条有差异。";
			 }else{
			 	s+="kna1无差异。";
			 }
			  if(parseInt(differenceKnb1Num)>0){
			 	s+="knb1有"+differenceKnb1Num+"条有差异。";
			 }else{
			 	s+="knb1无差异。";
			 }
			  if(parseInt(differenceKnvvNum)>0){
			 	s+="knvv有"+differenceKnvvNum+"条有差异。";
			 }else{
			 	s+="knvv无差异。";
			 }
			 $("#sapToLocal").show();
		}else{
			 s = "数据校对无差异。SAP有主数据"+totalSapNum+"条:kna1"+totalSapKna1Num+"条、knb1"+totalSapKnb1Num+"条、knvv"+totalSapKnvvNum+"条。Garden有主数据"+totalGardenNum+"条:kna1"+totalGardenKna1Num+"条、knb1"+totalGardenKnb1Num+"条、knvv"+totalGardenKnvvNum+"条。";
		}
		$(".check_text").empty();
		$(".check_text").append(s);
		$(window).blockUI('remove');
		alert("数据校对完成");
	}
	function loadGridData(){
		var url = '${ctx}/application/fi/customer/compareData/loadCompareData/kna1comare';
		jQuery('#kna1Grid').jqGrid('setGridParam',{url:url,page:1});
		var postdata=jQuery('#kna1Grid').jqGrid('getGridParam','postData');
		$.extend(postdata, {kunnr:$('#kunnr').val(),bukrs:$('#bukrs').val(),vkorg:$('#vkorg').val(),vtweg:$('#vtweg').val(),spart:$('#spart').val()});
		jQuery("#kna1Grid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
		
		url = '${ctx}/application/fi/customer/compareData/loadCompareData/knb1comare';
		jQuery('#knb1Grid').jqGrid('setGridParam',{url:url,page:1});
		postdata=jQuery('#knb1Grid').jqGrid('getGridParam','postData');
		$.extend(postdata, {kunnr:$('#kunnr').val(),bukrs:$('#bukrs').val(),vkorg:$('#vkorg').val(),vtweg:$('#vtweg').val(),spart:$('#spart').val()});
		jQuery("#knb1Grid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
		
		url = '${ctx}/application/fi/customer/compareData/loadCompareData/knvvcomare';
		jQuery('#knvvGrid').jqGrid('setGridParam',{url:url,page:1});
		var postdata=jQuery('#knvvGrid').jqGrid('getGridParam','postData');
		$.extend(postdata, {kunnr:$('#kunnr').val(),bukrs:$('#bukrs').val(),vkorg:$('#vkorg').val(),vtweg:$('#vtweg').val(),spart:$('#spart').val()});
		jQuery("#knvvGrid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
		$(window).blockUI('remove');
	}
	function initElEvent(){
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
		$('#compareData').bind('click',function(e){
			$(window).blockUI();
			var kunnr=$('#kunnr').val()=="客户编号"?"":$('#kunnr').val();
			var bukrs=$('#bukrs').val()=="公司代码"?"":$('#bukrs').val();
			var vkorg=$('#vkorg').val()=="销售组织"?"":$('#vkorg').val();
			var vtweg=$('#vtweg').val()=="分销渠道"?"":$('#vtweg').val();
			var spart=$('#spart').val()=="产口组"?"":$('#spart').val();
			$.ajax({
				url:'${ctx}/application/fi/customer/compareData/compare',
				type: "POST",
				data:{kunnr:kunnr,bukrs:bukrs,vkorg:vkorg,vtweg:vtweg,spart:spart},
				success:function(json){
					loadGridData();
					showCompareResult(json);
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					alert('校对失败');
					$(window).blockUI('remove');
				}
			});
		});
		$('#sapToLocal').bind('click',function(e){
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
				type:"POST",
				data:{kna1Compareds:kna1s,knb1Compareds:knb1s,knvvCompareds:knvvs},
				//dataType:'json',
				url:'${ctx}/application/fi/customer/compareData/sapSynToLocal',
				success:function(msg){
					alert('同步成功');
					rowData = jQuery('#compareDataGrid').jqGrid('getGridParam','selarrrow');
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