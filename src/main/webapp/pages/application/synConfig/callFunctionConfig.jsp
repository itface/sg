<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../inc/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.jqgrid.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.ui.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_extend.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/commons.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_custom.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.form/jquery.form.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
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
		<div class="contentBlock" style='height:600px;overflow:hidden'>
			<div class="runFunTableDiv">
				<table class="runFunTable">
					<tr>
						<td><input type='button' value='测试执行' id='testCallFun'/></td>
					</tr>
					<tr>
						<td>
							<table id="resultGrid"></table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form:form>
</div>
</body>
<script>
$(function(){
	var type='${type}';
	
	init();
	function init(){
		initElEvent();
		initAjaxForm();
		dynamicGrid();
	}
	function dynamicGrid(){
		var resultGridOptions=${resultGridOptions};
		/*
		var config ={
			id:'customList',
			caption:'test',
			columnNames:['ID','参数字段名','参数值','类型'],
			columnModel:[
		   		{name:'id',index:'id',hidden:true, width:1,key:true},
		   		{name:'paramname',index:'paramname',width:50,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'paramvalue',index:'paramvalue', width:50,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'businesstype',index:'businesstype',hidden:true,editable:true}
		   	],
			baseUrl:'${ctx}/application/synConfig/${type}/callFun/paramInfo'
		};
		*/
		new customGrid(resultGridOptions);
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
		//保存按钮添加点击时提交的动作
		$(':button.saveForm').bind('click',function(e){
			var formId = $(e.target.form).attr('id');
			var url = $(e.target.form).attr('action');
			var id = $('#'+formId+' #id').val();
			var methodObject = {};
			//如果id=0则表示新增
			if(id!=null&&id!=''&&id>0){
				methodObject={_method:'PUT'};
				url+='/'+id;
			}
			var options = {
					data:methodObject,
					target:'#'+formId+' #id',
					type:'POST',
					url:url,
					success:function(responseText,statusText,xhr,$form){
						if(responseText!=null&&responseText.trim()!=''){
							alert('保存失败');
						}else{
							alert('保存成功');
							//保存成功后刷新当前页面，更新id.
							location=location;
						}
					},
					error:function(){
						alert('保存失败');
					}
			};
			$("#"+formId).ajaxSubmit(options);
		});
	}
	/**
	*测试运行
	*/
	function testCallFunction(){
		$.ajax({
			url:'${ctx}/application/synConfig/${type}/callFun/testCallFun',
			type: "GET",
			dataType:"json",
			success:function(json){
					try{
						if(json!=null){
							jQuery("#resultGrid").clearGridData(true);
							jQuery("#resultGrid")[0].addJSONData(json);
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
		/*
		$.getJSON('${ctx}/application/synConfig/${type}/callFun/testCallFun',
				function(json){
					try{
						if(json!=null){
							jQuery("#resultGrid").clearGridData(true);
							jQuery("#resultGrid")[0].addJSONData(json);
						}
						alert('测试执行成功');
					}catch(e){
						alert('装载数据异常');
					}
					
				}
		);
		*/
		/*
		$.ajax({
			type:'GET',
			url:'${ctx}/application/synConfig/${type}/callFun/testCallFun',
			success:function(msg){
				alert(msg);
			}
		});
		*/
	}
});
</script>
</html>