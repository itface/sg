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
.outter{
	position:absolute;
	margin:0px;
	top:0px;
	left:0px;
	width:100%;
	height:100%;
}
.tbar{
	width:100%;
	height:25px;
}
.tbar_button{
	text-align:right;
	padding-right:30px;
	padding-top:5px;
}
.fold{
	height:20px;
	text-align:left;
	padding-left:15px;
	cursor:pointer;
	background:#DFEFFC;
	margin-top:2px;
	margin-bottom:2px;
}
.contentBlock{
	height:200px;
	overflow:hidden;
}
.paramInfoDiv{
	padding-left:200px;
}
.runFunTableDiv{
	width:80%;
	padding-top:20px;
	padding-left:50px;
}
.runFunTable{
	width:100%;
}
.runFunTable tr{
	height:30px;
	border:1px solid #ccc;
}
.dataTable{
	width:100%;
	border-collapse:collapse;
}
.dataTable td{
	height:30px;
	border:1px solid #ccc;
	text-align:center;
	
}
</style>
</head>
<body>
<div class="outter">
	<form:form id='paramInfoForm'>
		<div class="fold">参数设置</div>
	</form:form>
	<form:form id='runFunForm'>
		<div class="fold">调用执行</div>
		<div><input type='button' value='测试执行' id='testCallFun'/></div>
		<div id="mainTabPanel" class="easyui-tabs"></div>
	</form:form>
</div>
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
			$.extend(kna1GridOptions,{datatype:'local',contextPath:"${ctx}"});
			new commonGrid(kna1GridOptions);
		}
		if(knb1GridOptions!=null){
			$.extend(knb1GridOptions,{datatype:'local',contextPath:"${ctx}"});
			new commonGrid(knb1GridOptions);
		}
		if(knvvGridOptions!=null){
			$.extend(knvvGridOptions,{datatype:'local',contextPath:"${ctx}"});
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