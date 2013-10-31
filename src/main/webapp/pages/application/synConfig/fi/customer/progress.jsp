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
<body onload="$('#p').progressbar('setValue',0);setTimeout('init()',1000);">
<div>
<div>
	<div id='pText' style='height:110px;padding-top:10px;padding-left:35px'>
		<div style='height:20px' id='ptext1'>校验表结构......<span id='ptext1ok' style='display:none'>OK</span><span id='ptext1cancel' style='display:none'>用户终止</span></div>
		<div style='height:20px' id='ptext2'>检查系统运行环境......<span id='ptext2ok' style='display:none'>OK</span><span id='ptext2cancel' style='display:none'>用户终止</span></div>
		<div style='height:20px' id='ptext3'>清空Garden中的原有客户数据......<span id='ptext3ok' style='display:none'>OK</span><span id='ptext3cancel' style='display:none'>用户终止</span></div>
		<div style='height:20px' id='ptext4'>从SAP（<script>document.write(frameElement.api.opener.$.dialog.data('client'));</script>）读取数据，初始化Garden中客户数据......<span id='ptext4ok' style='display:none'>OK</span><span id='ptext4cancel' style='display:none'>用户终止</span></div>
	</div>
	<div style="margin:0 40px;">
		<div style='height:20px' id='ptextResult'></div>
		<div id="p" style="width:400px;"  class="easyui-progressbar"></div>
	</div>
</div>
<!-- div style='right:0px;bottom:5px;height:20px;position:absolute'>
<input type='button' value='取消' id='cancel' onclick="cancelInit()"/>
<input type='button' value='确定' id='ok' style='display:none' onclick='closeWin()'/>
</div-->
<div class="biangeng_botton_main">
  <a href="javascript:void(0);" class="btn4 cancelBtn" id='cancel' onclick="cancelInit()" onMouseOver="this.className='btn_hover2'" onMouseOut="this.className='btn4'" style="float:right;">取消</a> 
  <a href="javascript:void(0);" class="btn4 saveBtn" id='ok' onclick='okBtnEvent()' onMouseOver="this.className='btn_hover2'" onMouseOut="this.className='btn4'" style="float:right;display:none">确定</a> 
</div>
</div>
</body>
<script type="text/javascript">
var cancelFlag = false;
var client = frameElement.api.opener.$.dialog.data('client');
var progressId = Math.round(Math.random()*1000000);//随机数，用于记录进度条id
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
	$('#cancel').hide();
	cancelFlag=true;
	$.ajax({
		url:'${ctx}/application/fi/customer/initData/cancelInit',
		data:{progressId:progressId},
		async:false
	});
	//掩饰一秒执行，因为如果不延时，直接关闭窗口，执行初始化的ajax还未执行完，会跳进error方法
	setTimeout(function(){alert('取消成功');closeWin();},2500); 
}

function okBtnEvent(){
	var api = frameElement.api;
	var  W = api.opener; 
	var queryData = W.$.dialog.data('queryData');
	queryData();
	closeWin();
}
function init(){
		interval=setInterval(getPercentage, 100);
		$.ajax({
			url:'${ctx}/application/fi/customer/initData/validateTable',
			async:false,
			cache:false,
			data:{progressId:progressId},
			success:function(status){
				if(status==false||status=='false'){
					if(!confirm("表结构已经变化，如要继续执行初始化，系统将会修复表结构，是否继续")){
						closeWin();	
					}else{
						$.ajax({
							url:'${ctx}/application/fi/customer/initData/repairTable',
							async:false,
							cache:false,
							data:{progressId:progressId},
							success:function(){
								setTimeout("donInit()",2000);
							},
							error:function(XMLHttpRequest,textStatus,errorThrown){
								$('#ptextResult').text('修复表结构异常异常');
								if(interval){
									clearInterval(interval);
								}
								//setTimeout(function(){closeWin();},3000); 
							}
						});
					}
				}else{
					setTimeout("donInit()",2000);
				}
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				$('#ptextResult').text('校验表结构异常');
				if(interval){
					clearInterval(interval);
				}
				//setTimeout(function(){closeWin();},3000); 
			}
		});
}
function donInit(){
	$.ajax({
		url:'${ctx}/application/fi/customer/initData/doInit',
		type: "GET",
		cache:false,
		data:{progressId:progressId},
		success:function(){
			if(interval){
				clearInterval(interval);
			}
			if(!cancelFlag){
				getPercentage();
			}
		},
		error:function(XMLHttpRequest,textStatus,errorThrown){
			$('#ptextResult').text('执行初始化异常');
			if(interval){
				clearInterval(interval);
			}
			//setTimeout(function(){closeWin();},3000); 
		}
	});
}
function getPercentage(){
	$.ajax({
		url:'${ctx}/application/fi/customer/initData/getProgressModel',
		async:false,
		cache:false,
		datatype:'json',
		data:{progressId:progressId},
		success:function(json){
			if(json!=null&&json!=''){
				var percent=json.percentage;
				var cancel = json.cancel;
				if(percent<10&&cancel==true){
					$('#ptext1ok').hide();
					$('#ptext1cancel').show();
				}else if(percent>=10&&percent<17){
					if(cancel==true||cancel=='true'){
						$('#ptext1ok').show();
						$('#ptext1cancel').hide();
						$('#ptext2ok').hide();
						$('#ptext2cancel').show();
					}else{
						$('#ptext1ok').show();
						$('#ptext1cancel').hide();
						$('#ptext2ok').hide();
						$('#ptext2cancel').hide();
					}
				}
				if(percent>=17&&percent<25){
					if(cancel==true||cancel=='true'){
						$('#ptext1ok').show();
						$('#ptext1cancel').hide();
						$('#ptext2ok').show();
						$('#ptext2cancel').hide();
						$('#ptext3ok').hide();
						$('#ptext3cancel').show();
					}else{
						$('#ptext1ok').show();
						$('#ptext1cancel').hide();
						$('#ptext2ok').show();
						$('#ptext2cancel').hide();
						$('#ptext3ok').hide();
						$('#ptext3cancel').hide();
					}
				}else if(percent>=25&&percent<99){
					if(cancel==true||cancel=='true'){
						$('#ptext1ok').show();
						$('#ptext1cancel').hide();
						$('#ptext2ok').show();
						$('#ptext2cancel').hide();
						$('#ptext3ok').show();
						$('#ptext3cancel').hide();
						$('#ptext4ok').hide();
						$('#ptext4cancel').show();
					}else{
						$('#ptext1ok').show();
						$('#ptext1cancel').hide();
						$('#ptext2ok').show();
						$('#ptext2cancel').hide();
						$('#ptext3ok').show();
						$('#ptext3cancel').hide();
						$('#ptext4ok').hide();
						$('#ptext4cancel').hide();
					}
				}else if(percent>=99){
						$('#ptext1ok').show();
						$('#ptext1cancel').hide();
						$('#ptext2ok').show();
						$('#ptext2cancel').hide();
						$('#ptext3ok').show();
						$('#ptext3cancel').hide();
						$('#ptext4ok').show();
						$('#ptext4cancel').hide();
				}
				if(json.over==false||json.over=='false'){
					$('#p').progressbar('setValue',percent); 
					//$('#pText').text(json.text);
				}else if(cancel!=true&&cancel!='true'){
					$('#p').progressbar('setValue',100);  
					setTimeout(function(){$('#p').remove();},200);
					$('#ptextResult').text("Garden的客户数据初始化完成，"+"本次初始化共"+json.totalNum+"条记录，"+"耗时"+((json.endtime-json.starttime)/1000)+"秒。");
					$('#cancel').remove();
					$('#ok').show();
					clearInterval(interval);
				}
			}
		},
		error:function(){
			$('#ptextResult').text("读取进读异常");
			if(interval){
				clearInterval(interval);
			}
		}
	});
}
</script>
</html>