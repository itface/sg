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
<script src="<c:url value='/script/lhgdialog/lhgdialog.js'/>" type="text/javascript"></script>
</head>
<body>
<div>
	<div id='pText' style='height:30px'></div>
	<div id="p" style="width:400px;"></div>
</div>
<div style='right:0px;bottom:5px;height:20px;position:absolute'>
<input type='button' value='取消' id='cancel' onclick="cancelInit()"/>
<input type='button' value='确定' id='ok' style='display:none' onclick='closeWin()'/>
</div>
</body>
<script type="text/javascript">
var cancelFlag = false;
function closeWin(){
	var api = frameElement.api;
	var  W = api.opener; 
	var dia = W.$.dialog.data('dialog');
	dia.close();
}
function cancelInit(){
	if(!confirm('确定取消?')){
		return false;
	}
	cancelFlag=true;
	$.ajax({
		url:'${ctx}/application/fi/customer/initData/cancelInit',
		async:false
	});
	alert('取消成功');
	closeWin();
}
$(function(){
	var exception = false;
	var interval;
	var getTotalNumFlag = true;
	$('#p').progressbar(); 
	$.ajax({
		url:'${ctx}/application/fi/customer/initData/doInit',
		type: "GET",
		cache:false,
		success:function(status){
			if(interval){
				clearInterval(interval);
			}
			var totalTime = getTotalTime();
			$('#p').remove();
			var text = $('#pText').text();
			$('#pText').text("初始化完毕，耗时"+totalTime+"秒,"+text);
			$('#cancel').remove();
			$('#ok').show();
		},
		error:function(XMLHttpRequest,textStatus,errorThrown){
			exception = true;
			if(!cancelFlag){
				alert('执行初始化异常');
			}
		}
	});
	interval=setInterval(getPercentage, 1500);
	function getPercentage(){
		if(exception){
			clearInterval(interval);
			closeWin();
		}
		var percent=0;
		$.ajax({
			url:'${ctx}/application/fi/customer/initData/getProgressPercent',
			async:false,
			cache:false,
			success:function(response){
				percent=response;
				$('#p').progressbar('setValue',percent);  
				if(percent>20&&getTotalNumFlag){
					getTotalNum();
					getTotalNumFlag=false;
				}
			}
		});
	}
	function getTotalTime(){
		var totalTime=0;
		$.ajax({
			url:'${ctx}/application/fi/customer/initData/getTotalTime',
			async:false,
			cache:false,
			success:function(response){
				totalTime=response;
			}
		});
		return totalTime;
	}
	function getTotalNum(){
		var totalNum=0;
		var totalCustomer=0;
		$.ajax({
				url:'${ctx}/application/fi/customer/initData/getTotalNum',
				async:false,
				cache:false,
				success:function(response){
					//var s = response.substring(1,response.length-1);
					var s = response;
					var arr = s.split(',');
					totalNum=arr[0];
					totalCustomer=arr[1];
					//alert(s+":"+totalNum+":"+totalCustomer);
				}
		});
		$('#pText').text('本次初始化有'+totalCustomer+'个客户共'+totalNum+'条记录');
		return totalNum;
	}
});
</script>
</html>