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
<script src="<c:url value='/script/lhgdialog/lhgdialog.min.js?skin=default'/>" type="text/javascript"></script>
<style>

.biangeng_botton_main {
	height:40px;
	width:100%;
	padding-top:15px;
	margin-right:8px;
	position:absolute;
	bottom:2px;
	text-align:right;
	background:url(${ctx}/images/alert_toobar_bg.png);

}
a{ font-size:12px; text-decoration:none}
</style>
</head>
<body>
<div>
<div>
	<div id='pText' style='height:30px;padding-top:10px;padding-left:10px'></div>
	<div id="p" style="width:400px;"></div>
</div>
<!-- div style='right:0px;bottom:5px;height:20px;position:absolute'>
<input type='button' value='取消' id='cancel' onclick="cancelInit()"/>
<input type='button' value='确定' id='ok' style='display:none' onclick='closeWin()'/>
</div-->
<div class="biangeng_botton_main">
  <a href="javascript:void(0);" class="btn4 cancelBtn" id='cancel' onclick="cancelInit()" onMouseOver="this.className='btn_hover2'" onMouseOut="this.className='btn4'" style="float:right">取消</a> 
  <a href="javascript:void(0);" class="btn4 saveBtn" id='ok' onclick='okBtnEvent()' onMouseOver="this.className='btn_hover2'" onMouseOut="this.className='btn4'" style="float:right;display:none">确定</a> 
</div>
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
		url:'${ctx}/application/fi/company/initData/cancelInit',
		async:false
	});
	//掩饰一秒执行，因为如果不延时，直接关闭窗口，执行初始化的ajax还未执行完，会跳进error方法
	setTimeout(function(){alert('取消成功');closeWin();},1000); 
}

function okBtnEvent(){
	var api = frameElement.api;
	var  W = api.opener; 
	var queryData = W.$.dialog.data('queryData');
	queryData();
	closeWin();
}
$(document).ready(
	function(){
		$('#p').progressbar(); 
		$.ajax({
			url:'${ctx}/application/fi/company/initData/validateTable',
			async:false,
			success:function(status){
				$('#p').progressbar('setValue',10);
				if(status==false||status=='false'){
					if(!confirm("表结构已经变化，如要继续执行初始化，系统将会修复表结构，是否继续")){
						closeWin();		
					}else{
						$.ajax({
							url:'${ctx}/application/fi/company/initData/repairTable',
							async:false,
							success:function(){
								$('#p').progressbar('setValue',20);
							},
							error:function(XMLHttpRequest,textStatus,errorThrown){
								alert('修复表结构异常异常');
								closeWin();
							}
						});
					}
				}
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				$('#p').progressbar('setValue',10);
				alert('校验表结构异常');
				closeWin();
			}
		});
		$.ajax({
			url:'${ctx}/application/fi/company/initData/doInit',
			type: "GET",
			cache:false,
			success:function(){
				if(interval){
					clearInterval(interval);
				}
				if(!cancelFlag){
					getPercentage();
				}
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				alert('执行初始化异常');
				closeWin();
			}
		});
		interval=setInterval(getPercentage, 100);
		function getPercentage(){
			$.ajax({
				url:'${ctx}/application/fi/company/initData/getProgressModel',
				async:false,
				cache:false,
				datatype:'json',
				success:function(json){
					if(json!=null){
						var percent=json.percentage;
						if(json.over==false||json.over=='false'){
							$('#p').progressbar('setValue',percent);  
						}else{
							$('#p').progressbar('setValue',100);  
							setTimeout(function(){$('#p').remove();},200);
							$('#pText').text("初始化完毕，耗时"+((json.endtime-json.starttime)/1000)+"秒,"+"本次初始化共"+json.totalNum+"条记录");
							$('#cancel').remove();
							$('#ok').show();
							clearInterval(interval);
						}
					}
				}
			});
		}
	}
);
</script>
</html>