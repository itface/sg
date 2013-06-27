<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../../../inc/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/icon.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.jqgrid.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.ui.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.json-2.4.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_extend.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/commons.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_common.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.form/jquery.form.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/easyui-lang-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/jquery.easyui.min.js'/>" type="text/javascript"></script>
</head>
<body>
<form>
	<div>
		客户编号:<input type='text' id='kunnr'/>
		公司代码:<input type='text' id='bukrs'/>
		<br/>
		销售组织:<input type='text' id='vkorg'/>
		分销渠道:<input type='text' id='vtweg'/>
		<br/>
		&nbsp;&nbsp;产品组:<input type='text' id='spart'/>
	</div>
	<input type='button' value='校对执行' id='compareData'/>
	<input type='button' value='SAP同步到本系统' id='sapToLocal'/>
	<div id='comparedResult'>
	</div>
	<div id="mainTabPanel" class="easyui-tabs" style="height:500px">
		<div title="客户一般数据" style="height:400px">  
	        <div style='width:100%;overflow:auto;height:100%'>
	        	<table id='kna1Table' class='comparedTable' style="border:1px solid #ccc;border-collapse:collapse;width:${kna1TableWidth}px">
	        		<thead>
	        			${kna1TableHeader}
	        		</thead>
	        		<tbody id='kna1Tbody'>
	        		</tbody>
	        	</table>
	        </div>
	    </div> 
	    <div title="客户公司代码数据" style="height:400px">  
	         <div style='width:100%;overflow:auto;height:100%'>
	        	<table id='knb1Table' class='comparedTable' style="border:1px solid #ccc;border-collapse:collapse;width:${knb1TableWidth}px">
	        		<thead>
	        			${knb1TableHeader}
	        		</thead>
	        		<tbody id='knb1Tbody'>
	        		</tbody>
	        	</table>
	        </div>
	    </div> 
	    <div title="客户销售数据" style="height:400px">  
	        <div style='width:100%;overflow:auto;height:100%'>
	        	<table id='knvvTable' class='comparedTable' style="border:1px solid #ccc;border-collapse:collapse;width:${knvvTableWidth}px">
	        		<thead>
	        			${knvvTableHeader}
	        		</thead>
	        		<tbody id='knvvTbody'>
	        		</tbody>
	        	</table>
	        </div>
	    </div>  
	</div>
</form>
</body>
<script>
$(function(){
	var kna1Grid;
	var knb1Grid;
	var knvvGrid;
	init();
	function init(){
		initElEvent();
	}
	function loadCompareData(){
		$.ajax({
				url:'${ctx}/application/fi/customer/compareData/loadCompareData/kna1',
				success:function(html){
					$('#kna1Tbody').empty();
					$('#kna1Tbody').append(html);
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					
				}
		});
		$.ajax({
				url:'${ctx}/application/fi/customer/compareData/loadCompareData/knb1',
				success:function(html){
					$('#knb1Tbody').empty();
					$('#knb1Tbody').append(html);		
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					
				}
		});
		$.ajax({
				url:'${ctx}/application/fi/customer/compareData/loadCompareData/knvv',
				success:function(html){
					$('#knvvTbody').empty();
					$('#knvvTbody').append(html);		
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					
				}
		});
	}
	function getComparedResult(){
		$.ajax({
			url:'${ctx}/application/fi/customer/compareData/getComparedResult',
			success:function(html){
				$('#comparedResult').empty();	
				$('#comparedResult').append(html);	
			}
		});
	}
	function initElEvent(){
		$('.gridCheckboxAll').bind('click',function(e){
			var checked = $(e.target).attr('checked');
			var table = $(e.target).parents('.comparedTable');
			if(checked=='checked'){
				$('.gridCheckbox',table).attr('checked',true);
			}else{
				$('.gridCheckbox',table).attr('checked',false);
			}
		});
		$('#compareData').bind('click',function(e){
			$.ajax({
				url:'${ctx}/application/fi/customer/compareData/compare',
				type: "GET",
				data:{kunnr:$('#kunnr').val(),bukrs:$('#bukrs').val(),vkorg:$('#vkorg').val(),vtweg:$('#vtweg').val(),spart:$('#spart').val()},
				success:function(json){
							alert("对比成功");
							getComparedResult();
							loadCompareData();
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					alert('校对失败');
				}
			});
		});
		$('#sapToLocal').bind('click',function(e){
			var kna1Flag = false;
			var knb1Flag = false;
			var knvvFlag = false;
			var kna1s='{"list":[';
			$('.kna1Tr').each(function(i,v){
				var c = $('.gridCheckbox',v).attr('checked');
				if(c=='checked'){
					kna1Flag=true;
					kna1s+="{"
					$('input:hidden',v).each(function(index,value){
						var name = $(value).attr('name');
						var value = $(value).val();
						kna1s+='"'+name+'":'+'"'+value+'",';
					});
					kna1s=kna1s.substring(0,kna1s.lastIndexOf(','));
					kna1s+="},"
				}
			});
			if(kna1Flag){
				kna1s=kna1s.substring(0,kna1s.lastIndexOf(','));
				kna1s+="]}";
			}else{
				kna1s='';
			}
			var knb1s='{"list":[';
			$('.knb1Tr').each(function(i,v){
				var c = $('.gridCheckbox',v).attr('checked');
				if(c=='checked'){
					knb1Flag=true;
					knb1s+="{"
					$('input:hidden',v).each(function(index,value){
						var name = $(value).attr('name');
						var value = $(value).val();
						knb1s+='"'+name+'":'+'"'+value+'",';
					});
					knb1s=knb1s.substring(0,knb1s.lastIndexOf(','));
					knb1s+="},"
				}
			});
			if(knb1Flag){
				knb1s=knb1s.substring(0,knb1s.lastIndexOf(','));
				knb1s+="]}";
			}else{
				knb1s='';
			}
			var knvvs='{"list":[';
			$('.knvvTr').each(function(i,v){
				var c = $('.gridCheckbox',v).attr('checked');
				if(c=='checked'){
					knvvFlag=true;
					knvvs+="{"
					$('input:hidden',v).each(function(index,value){
						var name = $(value).attr('name');
						var value = $(value).val();
						knvvs+='"'+name+'":'+'"'+value+'",';
					});
					knvvs=knvvs.substring(0,knvvs.lastIndexOf(','));
					knvvs+="},"
				}
			});
			if(knvvFlag){
				knvvs=knvvs.substring(0,knvvs.lastIndexOf(','));
				knvvs+="]}";
			}else{
				knvvs='';
			}
			if(!kna1Flag&&!knb1Flag&&!knvvFlag){
				alert('请选择要同步的记录');
				return;
			}
			$.ajax({
				type:"POST",
				//contentType: "application/json;charset=utf-8",
				//data:$.toJSON(JSON.parse(s)),
				//dataType:'json',
				data:{kna1Compareds:kna1s,knb1Compareds:knb1s,knvvCompareds:knvvs},
				url:'${ctx}/application/fi/customer/compareData/sapSynToLocal',
				success:function(msg){
					loadCompareData();
					alert('同步成功');
				},
				error:function(){
					alert('同步异常');
				}
			});
			//alert(s);
			//alert(JSON.parse(s));
			/*
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
				
			});*/
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