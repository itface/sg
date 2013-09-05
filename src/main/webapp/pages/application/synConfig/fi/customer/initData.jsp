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
	<!-- input type='button' value='初始化' id='initData'/-->
</form>
</body>
<script>
$(function(){
		$('#initData').bind('click',function(e){
			if(!confirm("初始化将会清空表里数据，是否确定执行")){
				return false;
			}
			var validateColumnFlag = false;
			var exception = false;
			$.ajax({
				url:'${ctx}/application/fi/customer/initData/removeAll',
				async:false,
				success:function(status){
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					
					exception=true;
					alert('清空表数据异常');
				}
			});
			//return false;
			if(exception){
				return false;
			}
			$.ajax({
				url:'${ctx}/application/fi/customer/initData/validateColumn',
				async:false,
				success:function(status){
					validateColumnFlag=status;
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					
					exception=true;
					alert('校验表结构异常');
				}
			});
			if(exception){
				return false;
			}else if(validateColumnFlag==false||validateColumnFlag=='false'){
				if(!confirm("表结构已经变化，如要继续执行初始化，系统将会修复表结构，是否继续")){
					
					return false;
				}else{
					$.ajax({
						url:'${ctx}/application/fi/customer/initData/repairTable',
						async:false,
						success:function(status){
							
						},
						error:function(XMLHttpRequest,textStatus,errorThrown){
							
							exception=true;
							alert('修复表结构异常异常');
						}
					});
					if(exception){
						return false;
					}
				}
			}
		 	var dialog = $.dialog({
		 		id:'dia',
			    lock: true,
			    min:false,
			    max:false,
			    cancel:false,
			    background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
			    opacity: 0.5,       /* 透明度 */
			    content: 'url:${ctx}/pages/application/synConfig/fi/customer/progress.jsp',
			    title:''
			    /*
			    //icon: 'error.gif',
			    init:function(){
			    	
			    }
			    ok: function () {
			        //$.dialog({content: '再来一个锁屏', lock: true, parent:this});
			        return false;
			    },
			    
			    //cancel: true
			    */
			});
			$.dialog.data('dialog',dialog);//：跨框架数据共享写入接口
   			
			/*
			if(!confirm("初始化将会清空表里数据，是否确定执行")){
				return false;
			}
			$('#initData').attr("disabled",true);
			var validateColumnFlag = false;
			var exception = false;
			$.ajax({
				url:'${ctx}/application/fi/customer/initData/removeAll',
				async:false,
				success:function(status){
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					initPage();
					exception=true;
					alert('清空表数据异常');
				}
			});
			if(exception){
				return false;
			}
			$('#p').progressbar();
			$.ajax({
				url:'${ctx}/application/fi/customer/initData/validateColumn',
				async:false,
				success:function(status){
					validateColumnFlag=status;
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					initPage();
					exception=true;
					alert('校验表结构异常');
				}
			});
			if(exception){
				return false;
			}else if(validateColumnFlag==false||validateColumnFlag=='false'){
				if(!confirm("表结构已经变化，如要继续执行初始化，系统将会修复表结构，是否继续")){
					initPage();
					return false;
				}else{
					$.ajax({
						url:'${ctx}/application/fi/customer/initData/repairTable',
						async:false,
						success:function(status){
							
						},
						error:function(XMLHttpRequest,textStatus,errorThrown){
							initPage();
							exception=true;
							alert('修复表结构异常异常');
						}
					});
					if(exception){
						return false;
					}
					$.ajax({
						url:'${ctx}/application/fi/customer/initData/doInit',
						type: "GET",
						success:function(status){
							
						},
						error:function(XMLHttpRequest,textStatus,errorThrown){
							initPage();
							alert('执行初始化异常');
						}
					});
					interval=setInterval(progress, 1500);
					setTimeout(getTotalNum,5000);
				}
			}else{
				$.ajax({
					url:'${ctx}/application/fi/customer/initData/doInit',
					type: "GET",
					success:function(status){
						
					},
					error:function(XMLHttpRequest,textStatus,errorThrown){
						initPage();
						alert('执行初始化异常');
					}
				});
				interval=setInterval(progress, 1500);
				setTimeout(getTotalNum,5000);
			}
			*/
		});
		/*
		function initPage(){
			$('#initData').attr("disabled",false);
			$('#p').hide();
			$('#pText').hide();
			if(interval){
				clearInterval(interval);
			}
		}
		*/
		
});
</script>
</html>