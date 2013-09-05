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
	<div id="fullTreeDiv" style="float:left;">
		<ul id="fullTree"></ul>
	</div>
	<div id='contentDiv' style='float:left;border:1px #ccc solid;overflow:auto;'>
		<iframe style='height:100%;width:100%;border:0px' id='contentIframe'>
		</iframe>
	</div>
	<div style="bottom:0px;right:0px;position:absolute">
		<button id='updateMenu'>确定</button>
		<button id='cancel'>取消</button>
	</div>
</body>
<script>
var fullTree;
$(function(){
	init();
	var checkedArr = new Array();
	var getInitDataInterval;
	function init(){
		initEvent();
		createTree();
		initDiv();
		getInitDataInterval=setInterval(getInitData,100);
	}
	function closeWin(){
		var api = frameElement.api;
		var  W = api.opener; 
		var dia = W.$.dialog.data('dialog');
		dia.close();
	}
	function reloadTree(){
		var api = frameElement.api;
		var  W = api.opener; 
		var tree = W.$.dialog.data('tree');
		if(tree){
			tree.getRootNode().reload()
		}
		var menu = W.$.dialog.data('menu');
		if(menu){
			menu.getRootNode().reload()
		}
	}
	function initEvent(){
		$('#updateMenu').bind('click',function(e){
			alert(getUpdateResult());
			updateMenu();
			reloadTree();
			closeWin();
		});
		$('#cancel').bind('click',function(e){
			closeWin();
		});
	}
	function initDiv(){
		var height = getPageHeight($)-50;
		var width = getPageWidth($);
		$('#fullTreeDiv').css('height',450);
		$('#fullTreeDiv').css('width',300);
		$('#contentDiv').css('height',450);
		$('#contentDiv').css('width',450);
	}
	function createTree(){
		fullTree=$('#fullTreeDiv').tree({  
		     url: '${ctx}/system/menu/notSystemMenuCheckedTree/${roleId}',
		     method: 'GET',
		     cascadeCheck:true,
		     checkbox:true,
		     multiple:true,
		     onClick:function(node){
		     	var uri = node.attributes;
		     	if(uri!=null&&uri!=''){
		     		$('#contentIframe').attr('src','${ctx}'+uri+'/memo');
		     	}
		    }
		});
		
		
	}
	function updateMenu(){
	/*
		var height = getPageHeight($)-50;
		var width = getPageWidth($);
		$('#fullTreeDiv').css('height',height);
		$('#fullTreeDiv').css('width',width);
	*/
		var roleId=${roleId};
		//取选中还半选中的节点
    	var checkedNodes = $('#fullTreeDiv').tree('getChecked');
    	//自定义的取半选中的节点
    	var indeterminateNodes = $('#fullTreeDiv').tree('getCheckedExt');
    	//var arr = $('#tempResourcesIds').combotree('getValues');
    	var arr = new Array();
    	arr = arr.concat(checkedNodes,indeterminateNodes);
    	var s = '';
    	if(arr!=null&&arr.length>0){
    		for(var i=0;i<arr.length;i++){
    			s+=arr[i].id+',';
    		}
    		s=s.substring(0,s.lastIndexOf(','));
    	}
    	var deleteNodes = getDeleteNode();
    	//更新菜单与角色关系
    	$.ajax({
    		url:'${ctx}/system/role/updateRoleResource',
    		method:'post',
    		async:false,
    		cache:false,
    		data:{roleId:roleId,resourceIds:s,btypes:deleteNodes}
    	});
    	//把删除的菜单取消激活
    	$.ajax({
    		url:'${ctx}/application/baseinfo/updateByBusinesstypes',
    		method:'post',
    		async:false,
    		cache:false,
    		data:{status:'INACTIVE',btypes:deleteNodes}
    	});
	}
	function getDeleteNode(){
		//取选中还半选中的节点
    	var checkedNodes = $('#fullTreeDiv').tree('getChecked');
    	//自定义的取半选中的节点
    	var indeterminateNodes = $('#fullTreeDiv').tree('getCheckedExt');
    	//var arr = $('#tempResourcesIds').combotree('getValues');
    	var arr = new Array();
    	var s='';
    	arr = arr.concat(checkedNodes,indeterminateNodes);
    	if(arr!=null&&arr.length>0){
    		if(checkedArr!=null&&checkedArr.length>0){
	    		for(var j=0;j<checkedArr.length;j++){
	    			var node1 = checkedArr[j];
	    			var eq = false;
	    			for(var i=0;i<arr.length;i++){
	    				var node2 = arr[i];
	    				if(node1.id==node2.id){
	    					eq = true;
	    					break;
	    				}
	    			}
	    			if(!eq){
		    			var uri = node1.attributes;
		    			if(uri!=null&&uri!=''){
		    				var businessType = uri.substring(uri.lastIndexOf('/')+1);
		    				s+=businessType.toLowerCase()+',';
		    			}
	    			}
	    		}
    		}
    	}else{
    		if(checkedArr!=null&&checkedArr.length>0){
    			for(var i=0;i<checkedArr.length;i++){
    				var node = checkedArr[i];
    				var uri = node.attributes;
	    			if(uri!=null&&uri!=''){
	    				var businessType = uri.substring(uri.lastIndexOf('/')+1);
	    				s+=businessType.toLowerCase()+',';
	    			}
    			}
    		}
    	}
    	if(s!=null&&s!=''){
    		s=s.substring(0,s.lastIndexOf(','));
    	}
    	return s;
	}
	function getUpdateResult(){
		//取选中还半选中的节点
    	var checkedNodes = $('#fullTreeDiv').tree('getChecked');
    	//自定义的取半选中的节点
    	var indeterminateNodes = $('#fullTreeDiv').tree('getCheckedExt');
    	//var arr = $('#tempResourcesIds').combotree('getValues');
    	var arr = new Array();
    	var s='';
    	arr = arr.concat(checkedNodes,indeterminateNodes);
    	if(arr!=null&&arr.length>0){
    		if(checkedArr!=null&&checkedArr.length>0){
    			var add = '新增数据项：';
    			var del ='删除数据项：';
	    		for(var i=0;i<arr.length;i++){
	    			var node1 = arr[i];
	    			var eq = false;
	    			for(var j=0;j<checkedArr.length;j++){
	    				var node2 = checkedArr[j];
	    				if(node1.id==node2.id){
	    					eq = true;
	    					break;
	    				}
	    			}
	    			add+=node1.text+' ';
	    		}
	    		for(var j=0;j<checkedArr.length;j++){
	    			var node1 = checkedArr[j];
	    			var eq = false;
	    			for(var i=0;i<arr.length;i++){
	    				var node2 = arr[i];
	    				if(node1.id==node2.id){
	    					eq = true;
	    					break;
	    				}
	    			}
	    			if(!eq){
	    				del+=node1.text+' ';
	    			}
	    		}
	    		s+=add+'\n\n'+del;
    		}else{
    			s+='新增数据项：';
    			for(var i=0;i<arr.length;i++){
	    			var node = arr[i];
    				s+=node.text+" ";
	    		}
    		}
    	}else{
    		if(checkedArr!=null&&checkedArr.length>0){
    			s+='删除数据项：';
    			for(var i=0;i<checkedArr.length;i++){
    				var node = checkedArr[i];
    				s+=node.text+" ";
    			}
    		}
    	}
    	return s;
	}
	function getInitData(){
		if(fullTree&&fullTree.tree('getRoot')){
			clearInterval(getInitDataInterval);
			//取选中还半选中的节点
	    	var checkedNodes = fullTree.tree('getChecked');
	    	//自定义的取半选中的节点
	    	var indeterminateNodes =fullTree.tree('getCheckedExt');
	    	checkedArr = checkedArr.concat(checkedNodes,indeterminateNodes);
		}
	}
});

		
</script>
</html>