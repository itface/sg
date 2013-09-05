<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../../inc/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link href="${ctx}/css/sapgarden/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/sapgarden/garden.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/icon.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/easyui-lang-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/jquery.easyui.min.js'/>" type="text/javascript"></script>
</head>
<body>
<div id="mainTabPanel" class="easyui-tabs">
</div>
</body>
<script type="text/javascript">
var iframeHeight = $(window).height();
var iframeWidth = $(window).width();
var tabPanelWidth = iframeWidth;
var tabPanelHeight = iframeHeight;
var tabPanelTopHeight = 34;
var tabFrameWidth = iframeWidth;
var tabFrameHeight = iframeHeight-tabPanelTopHeight;
$(function(){
	initTabs();
	$('#mainTabPanel').tabs({
		border:false,
		width:tabPanelWidth,
		height:tabPanelHeight,
		onSelect:function(title,index){
			var obj = {
				'运行设置':'${basePath}/runtimeConfig',
				//'测试执行':'${basePath}/callFun',
				//'运行日志':'${basePath}/log',
				'数据预览':'${basePath}/runtimeData',
				'数据校对':'${basePath}/compareData',
				'运行监控':'${basePath}/runtimeMonitor',
				'初始化':'${basePath}/initData',
				'手工同步':'${basePath}/manualSyn',
				'函数说明':'${basePath}/memo'
			};
			var tab = $('#mainTabPanel').tabs('getSelected');
			var src = $(tab).find('iframe').attr('src');
			if(src==null||src==''){
				$(tab).find('iframe').attr('src',obj[title]);
			}
		}
	});
	function initTabs(){
		addTab('运行监控',true);
		addTab('运行设置',false);
		addTab('手工同步',false);
		//addTab('测试执行',false);
		addTab('数据校对',false);
		addTab('数据预览',false);
		//addTab('运行日志',false);
		addTab('初始化',false);
		addTab('函数说明',false);
	}
	function addTab(title, selected){
		if ($('#mainTabPanel').tabs('exists', title)){
			$('#mainTabPanel').tabs('select', title);
		} else {
			//var clientWidth = document.body.clientWidth;
			//var clientHeight = document.body.offsetHeight;
			var windowHeight = $(window).height()-38;//减去顶部的距离和padding的距离
			var content = '<iframe scrolling="auto" frameborder="0"  src="" style="width:'+(tabFrameWidth)+'px;height:'+(tabFrameHeight)+'px"></iframe>';
			$('#mainTabPanel').tabs('add',{
				title:title,
				content:content,
				closable:false,
				selected:selected
			});
		}
	}
});
</script>
</html>