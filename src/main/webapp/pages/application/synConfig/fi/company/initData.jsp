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
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_extend.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/extendJqgrid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/easyui-lang-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/jquery.easyui.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/lhgdialog/lhgdialog.js?skin=default'/>" type="text/javascript"></script>
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
<div style="padding:5px 10px; clear:both">
  <table id="dataGrid">
  </table>
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
		function queryData(){
			var url = '${ctx}/application/fi/company/runtimeData/findJqgridData';
			jQuery('#dataGrid').jqGrid('setGridParam',{url:url,page:1});
			jQuery("#dataGrid").jqGrid('setGridParam',{datatype:'json',search:false}).trigger("reloadGrid");
		}
		function initEvent(){
			$('#initData').bind('click',function(e){
				if(!confirm("初始化将会清空Garden中现有的【公司代码】数据，确定要执行吗？")){
					return false;
				}
				var dialog = $.dialog({
			 		id:'dia',
				    lock: true,
				    width: 500,
			    	height: 250,
				    min:false,
				    max:false,
				    cancel:false,
				    background: '#FFF',
				    opacity: 0.5,
				    content: 'url:${ctx}/pages/application/synConfig/fi/company/progress.jsp',
				    title:''
				});
				$.dialog.data('dialog',dialog);//：跨框架数据共享写入接口
				$.dialog.data('queryData',queryData);
				$.dialog.data('client',"${client}");
			});
			
			$('#expertExcel').bind('click',function(e){
				open('${ctx}/application/fi/company/initData/exportExcel?companyCode=','_self');
			});
		}
		
		
});
</script>
</html>