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
<script src="<c:url value='/script/jqgrid_custom.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.form/jquery.form.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/easyui-lang-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/jquery.easyui.min.js'/>" type="text/javascript"></script>
<!-- script src="<c:url value='/script/lhgdialog/lhgdialog.min.js?skin=default'/>" type="text/javascript"></script-->
<script src="<c:url value='/script/lhgdialog/lhgdialog.js?skin=default'/>" type="text/javascript"></script>
</head>
<body>
<form>
<div class="toolbar">
  <div class="prompt_message">慎重操作！本功能用来将SAP中的数据初始化到Garden系统中，初始化将清空已有的数据。</div>
  <div class="toolbar_left"> 
      <a href="#" class="btn" id='initData' onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">开始初始化</a> 
     <a href="#" class="btn" onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">导出数据</a>  
     </div>
</div>
<div style="padding:5px 10px; clear:both">
  <table id="dataGrid">
  </table>
</div>
</form>
</body>
<script>
$(function(){
		init();
		function init(){
			initEvent();
			dynamicGrid();
		}
		function dynamicGrid(){
			var dataGridOptions=${dataGridOptions};
			if(dataGridOptions!=null){
				$.extend(dataGridOptions,{contextPath:"${ctx}",height:$(window).height()-220,autoWidth:true,id:"dataGrid",caption:'公司代码'});
			}
			new customGrid(dataGridOptions);
		}
		//实例化XMLHttpRequest对象
		function createXMLHttpRequest(){
			var xmlHttp;
			if(window.XMLHttpRequest){
				xmlHttp = new XMLHttpRequest();	
			}else if(window.ActiveXObject){
				try{
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                }catch (e){
                    try{
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    catch (e){
                        alert("您的浏览器不支持AJAX！");
                        return false;
                    }
                }
			}
			return xmlHttp;
		}
		function initEvent(){
			$('#initData').bind('click',function(e){
				var xhr = new createXMLHttpRequest();
				xhr.open("GET","${ctx}/application/fi/company/initData/doInit");
				var count=0;
				xhr.onreadystatechange = function(){
                	if(xhr.readyState >2){ 
                		//ie                
		                 //var tmpText = xhr.responseText.substring(oldSize); 
		                alert(count);
	                  	count++;
	                 	
	                }
	                if(xhr.readyState == 4){ 
	                    if(xhr.status==200){
	                    	alert("执行完毕");
	                    }else{
	                    	alert("初始化异常");
	                    }
	                }
	            }
	            xhr.send(null);  
	            /*
				$.ajax({
					url:'${ctx}/application/fi/company/initData/doInit',
					type:'GET',
					xhr:function(){
						var xhr = jQuery.ajaxSettings.xhr();
						xhr.onreadystatechange=function(){
							 if(xhr.readyState > 2){ 
							 	var tmpText = xhr.responseText;
							 	alert(tmpText);
							 }
							 if(xhr.readyState == 4){ 
							 	alert('执行完毕');
							 }
						};
						return xhr;
					},
					success:function(msg){
						alert('success');
					},
					error:function(){
						alert('error');
					}
				});
				*/
			});
		}
		
		
});
</script>
</html>