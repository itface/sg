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
<script src="<c:url value='/script/jquery.json-2.4.js'/>" type="text/javascript"></script>
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
  <div class="prompt_message">本功能根据数据的关键字,从SAP中读取相应的数据，可以测试系统的连通性，也可以同步指定的SAP数据到Garden</div>
  <div class="toolbar_left"> 
  	<input type="text" class="input1" id='kunnr' value="客户编号" onclick="javascript:if(this.value=='客户编号') this.value='';return false;">
  	<a href="#" class="btn" id='testCallFun' onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">测试执行</a>  
    <a href="#" class="btn" id='manualSyn' onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">从SAP同步到Garden</a>  
   </div>
</div>
	<!-- div>
		客户编号:<input type='text' id='kunnr'/>
	</div>
	<input type='button' value='执行同步' id='manualSyn'/-->
</form>
</body>
<script>
$(function(){
	init();
	function init(){
		initElEvent();
	}
	function initElEvent(){
		$(".input1").focus(function(){
			  $(this).attr('class','input1_onfocus');
			  if($(this).val() ==this.defaultValue){  
                  $(this).val("");           
			  } 
		}).blur(function(){
			  $(this).attr('class','input1');
			 if ($(this).val() == '') {
                $(this).val(this.defaultValue);
             }
		});
		$('#manualSyn').bind('click',function(e){
			var kunnr = $('#kunnr').val();
			if(kunnr==''||kunnr=='客户编号'){
				alert('请输入要同步的客户编号');
				return false;
			}
			$.ajax({
				url:'${ctx}/application/fi/customer/manualSyn/syn',
				data:{kunnr:kunnr},
				success:function(response){
					alert('同步成功');
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					
				}
			});
			
		});
	}
});
</script>
</html>