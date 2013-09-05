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
<script src="<c:url value='/script/commons.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_common.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.form/jquery.form.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/easyui-lang-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/jquery.easyui.min.js'/>" type="text/javascript"></script>
</head>
<body>
<form>
<div class="toolbar">
  <div class="prompt_message">本功能用来浏览Garden系统中的数据。直接点击&ldquo;查询&rdquo;查询全部数据，也可以根据关键字查询</div>
  <!-- div class="toolbar_left"> 
  		<input type="text" class="input1"  value="请输入数据关键字">
    	<a href="#" class="btn" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">查询</a> 
        <a href="#" class="btn" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">导出数据</a> 
   </div-->
</div>
<br>
<div id="mainTabPanel" class="easyui-tabs">
</div>
	<!-- table id='kna1Grid'></table-->
</form>
</body>
<script>
$(function(){
	var type='${type}';
	init();
	function init(){
		addTab();
		dynamicGrid();
	}
	function dynamicGrid(){
		var kna1GridOptions=${kna1GridOptions};
		var knb1GridOptions=${knb1GridOptions};
		var knvvGridOptions=${knvvGridOptions};
		var width = getPageWidth($)-25;
		if(kna1GridOptions!=null){
			$.extend(kna1GridOptions,{datatype:'local',contextPath:"${ctx}",width:width});
			new commonGrid(kna1GridOptions);
		}
		if(knb1GridOptions!=null){
			$.extend(knb1GridOptions,{datatype:'local',contextPath:"${ctx}",width:width});
			new commonGrid(knb1GridOptions);
		}
		if(knvvGridOptions!=null){
			$.extend(knvvGridOptions,{datatype:'local',contextPath:"${ctx}",width:width});
			new commonGrid(knvvGridOptions);
		}
	}
	function addTab(){
		var width = getPageWidth($)-20;
		$('#mainTabPanel').css('width',width);
		$('#mainTabPanel').tabs('add',{
				title:'客户一般数据',
				content:"<table id='kna1Grid'></table><div id='kna1Pager'></div> ",
				closable:false,
				selected:true
		});
		$('#mainTabPanel').tabs('add',{
			title:'客户公司代码数据',
			content:"<table id='knb1Grid'></table><div id='knb1Pager'></div> ",
			closable:false,
			selected:false
		});
		$('#mainTabPanel').tabs('add',{
			title:'客户销售数据',
			content:"<table id='knvvGrid'></table><div id='knvvPager'></div> ",
			closable:false,
			selected:false
		});
	}
});
</script>
</html>