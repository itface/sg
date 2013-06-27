<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../../inc/header.jsp"%>
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
<script src="<c:url value='/script/jqgrid_custom.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.form/jquery.form.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/My97DatePicker4.8/WdatePicker.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/easyui-lang-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/jquery.easyui.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/lhgdialog/lhgdialog.js?self=true'/>" type="text/javascript"></script>
<script src="<c:url value='/script/easyui_extend.js'/>" type="text/javascript"></script>
</head>
<body>
	<div id='tbar'>
		<button id='add'>编数据项</button>
	</div>
	<div id='treeDiv' style='border:1px #ccc solid;overflow:auto;padding-top:0px;padding-left:0px;float:left;'>
		<ul id="tree"></ul>
	</div>
	<div id='contentDiv' style='float:left;border:1px #ccc solid;overflow:auto;padding-top:0px;padding-left:0px;'>
		<iframe style='height:100%;width:100%;border:0px' id='contentIframe'>
		</iframe>
	</div>
</body>
<script>
$(function(){
	init();
	var tree;
	function init(){
		initEvent();
		
		createTree();
		initTreeDiv();
	}
	function initEvent(){
		$('#add').bind('click',function(e){
			var dialog = $.dialog({
		 		id:'dia2',
			    lock: true,
			    min:false,
			    max:false,
			    cancel:false,
			    background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
			    opacity: 0.5,       /* 透明度 */
			    width:800,
			    height:500,
			    content: 'url:${ctx}/system/dataCollectionMenu/fullMenu/${roleId}',
			    title:''
			    /*
			    button:[
			    	{
			    		name:'增加数据项',
			    		callback:function(){
			    			
			    		}
			    	}
			    ]
			    //icon: 'error.gif',
			    init:function(){
			    	
			    }
			    ok: function () {
			        //$.dialog({content: '再来一个锁屏', lock: true, parent:this});
			        return false;
			    },
			    */
			});
			$.dialog.data('tree',tree);
			$.dialog.data('menu',parent.myTree);
			$.dialog.data('dialog',dialog);
		});
	}
	function initTreeDiv(){
		var height = getPageHeight($)-50;
		//var width = 300;
		$('#treeDiv').css('height',height);
		$('#treeDiv').css('width',300);
		$('#contentDiv').css('height',height);
		$('#contentDiv').css('width',600);
	}
	function createTree(){
		var sapDataCollectionId=${roleId};
		tree=$('#tree').tree({  
		     url: '${ctx}/system/menu/getMenuCheckedTreeByRoleId/${roleId}',
		     method: 'GET',
		     cascadeCheck:false,
		     checkbox:false,
		     multiple:false,
		     onClick:function(node){
		     	var uri = node.attributes;
		     	if(uri!=null&&uri!=''){
		     		$('#contentIframe').attr('src','${ctx}'+uri+'/memo');
		     	}
		     }
		});
	}
	
});
</script>
</html>