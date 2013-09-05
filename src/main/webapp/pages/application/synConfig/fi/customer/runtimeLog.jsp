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
  <div class="prompt_message">本功能用来浏览Garden系统中的日志。直接点击&ldquo;查询&rdquo;查询全部数据，也可以根据关键字查询</div>
  <!-- div class="toolbar_left"> 
  		<input type="text" class="input1"  value="请输入数据关键字">
    	<a href="#" class="btn" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">查询</a> 
        <a href="#" class="btn" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">导出数据</a> 
   </div-->
</div>
<br>
<div id="mainTabPanel" class="easyui-tabs">
</div>
</form>
</body>
<script>
$(function(){
	init();
	function init(){
		addTab();
		dynamicGrid();
	}
	function dynamicGrid(){
		var kna1LogGridOptions=${kna1LogGridOptions};
		var knb1LogGridOptions=${knb1LogGridOptions};
		var knvvLogGridOptions=${knvvLogGridOptions};
		var width = getPageWidth($)-25;
		if(kna1LogGridOptions!=null){
			$.extend(kna1LogGridOptions,{datatype:'local',contextPath:"${ctx}",width:width});
			new commonGrid(kna1LogGridOptions);
		}
		if(knb1LogGridOptions!=null){
			$.extend(knb1LogGridOptions,{datatype:'local',contextPath:"${ctx}",width:width});
			new commonGrid(knb1LogGridOptions);
		}
		if(knvvLogGridOptions!=null){
			$.extend(knvvLogGridOptions,{datatype:'local',contextPath:"${ctx}",width:width});
			new commonGrid(knvvLogGridOptions);
		}
	}
	function addTab(){
		var width = getPageWidth($)-20;
		$('#mainTabPanel').css('width',width);
		$('#mainTabPanel').tabs('add',{
				title:'客户一般数据日志',
				content:"<table id='kna1LogGrid'></table><div id='kna1LogPager'></div> ",
				closable:false,
				selected:true
		});
		$('#mainTabPanel').tabs('add',{
			title:'客户公司代码数据日志',
			content:"<table id='knb1LogGrid'></table><div id='knb1LogPager'></div> ",
			closable:false,
			selected:false
		});
		$('#mainTabPanel').tabs('add',{
			title:'客户销售数据日志',
			content:"<table id='knvvLogGrid'></table><div id='knvvLogPager'></div> ",
			closable:false,
			selected:false
		});
	}

});
</script>
</html>