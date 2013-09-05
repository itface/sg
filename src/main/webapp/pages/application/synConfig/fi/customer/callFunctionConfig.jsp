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
<style>
</style>
</head>
<body>
<div class="toolbar">
  <div class="prompt_message">本功能根据数据的关键字,从SAP中读取相应的数据，可以测试系统的连通性，也可以同步指定的SAP数据到Garden</div>
  <div class="toolbar_left"> 
    <a href="#" class="btn" id='testCallFun' onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">测试执行</a>  </div>
  
</div>
<div style="padding:5px 10px; clear:both">
  <div id="mainTabPanel" class="easyui-tabs"></div>
</div>
<!-- div class="outter">
	<form:form id='paramInfoForm'>
		<div class="fold">参数设置</div>
	</form:form>
	<form:form id='runFunForm'>
		<div class="fold">调用执行</div>
		<div><input type='button' value='测试执行' id='testCallFun'/></div>
		<div id="mainTabPanel" class="easyui-tabs"></div>
	</form:form>
</div-->
</body>
<script>
$(function(){
	init();
	function init(){
		addTab();
		initElEvent();
		initAjaxForm();
		dynamicGrid();
	}
	function dynamicGrid(){
		var kna1GridOptions=${kna1GridOptions};
		var knb1GridOptions=${knb1GridOptions};
		var knvvGridOptions=${knvvGridOptions};
		if(kna1GridOptions!=null){
			$.extend(kna1GridOptions,{datatype:'local',contextPath:"${ctx}",width:1000});
			new commonGrid(kna1GridOptions);
		}
		if(knb1GridOptions!=null){
			$.extend(knb1GridOptions,{datatype:'local',contextPath:"${ctx}",width:1000});
			new commonGrid(knb1GridOptions);
		}
		if(knvvGridOptions!=null){
			$.extend(knvvGridOptions,{datatype:'local',contextPath:"${ctx}",width:1000});
			new commonGrid(knvvGridOptions);
		}
	}
	/**
	*把form初始化为ajaxform，用于ajax提交
	*/
	function initAjaxForm(){
		$('#paramInfoForm').ajaxForm();
	}
	/**
	*给页面元素绑定事件
	*/
	function initElEvent(){
		//设置折叠层的点击事件
		$('.fold').bind('click',function(e){
			var display = $(e.target).next().css('display');
			var newDisplay = display=='none'?'block':'none';
			$(e.target).next().css('display',newDisplay);
		});
		$('#testCallFun').bind('click',function(e){
			testCallFunction();
		});
	}
	/**
	*测试运行
	*/
	function testCallFunction(){
		$.ajax({
			url:'${basePath}/testCallFun',
			type: "GET",
			dataType:"json",
			success:function(json){
					try{
						if(json!=null){
							jQuery("#kna1Grid").clearGridData(true);
							jQuery("#kna1Grid")[0].addJSONData(json['kna1']);
							jQuery("#knb1Grid").clearGridData(true);
							jQuery("#knb1Grid")[0].addJSONData(json['knb1']);
							jQuery("#knvvGrid").clearGridData(true);
							jQuery("#knvvGrid")[0].addJSONData(json['knvv']);
						}
						alert('测试执行成功');
					}catch(e){
						alert('装载数据异常');
					}
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				alert('调用失败');
			}
		});
	}
	function addTab(){
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