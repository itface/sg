<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./inc/header.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/icon.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.jqgrid.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.ui.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_extend.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/commons.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/easyui-lang-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jquery.easyui/jquery.easyui.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/easyui_extend.js'/>" type="text/javascript"></script>
</head>
<body>
<table id="list"></table>
<div id="tools"></div>
</body>
<script type="text/javascript">
$(function(){
var basePath = '${ctx}/system/role/data';
function validateLength(value,colname) {
	var col ={
		'角色名称':100
	};
	if(getLen(value) <= col[colname]) {
		return [true,"",""];
	} else {
		return [false,colname+"长度不能超过"+col[colname],""];
	}
}
function my_input(value, options) {
	return $("<input type='text' size='10' value='"+value+"' role='textbox' readonly='true' class='FormElement ui-widget-content ui-corner-all'/><input type='button'  value='选择' onclick='createRolePanel()'/>");
}
function my_value(value) {
	return value.val();
}
$('#list').jqGrid({
    url: basePath,
    ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },
    datatype: 'json',
    mtype: 'GET',
    jsonReader:{
    	repeatitems:false
    },
    treeGrid: false,
    ExpandColClick:false,
    treeGridModel: 'adjacency',
    ExpandColumn: 'resourceName',
    colNames: ['ID', '角色名称','别称', '资源', '资源'],
    colModel: [
    { name: 'id', index: 'id', hidden: true, width: 1,sorttype: 'int', key: true,editable:true },
    { name: 'roleName', index: 'roleName',sorttype: 'string',width: 300,editable:true,editrules:{required:true,custom:true,custom_func:validateLength},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" } },
    { name: 'alias', index: 'alias',width: 300,editable:true},
   
    { name: 'resourcesIds', index: 'resourcesIds', width: 100,editable:true, hidden: true},
    { name: 'tempResourcesIds', index: 'tempResourcesIds', width: 100,editable:true}
    ],
    gridview: true,
    //sortname: 'ID',
    //sortorder: "desc",
    pager: "false", 
    pginput:false,
    autowidth: false,
    width:600,
    height:600,
    //pager : "#tools",
    toppager: true,
    viewrecords:false,
    editurl:'',
    caption:'角色管理',
    rownumbers:true,
    //height: 'auto'
    ondblClickRow:function(rowid,iCol,cellcontent,e){
    	//editData为添加的参数，是为了让参数能正常的put到后台
    	$('#list').jqGrid('editGridRow',rowid,{editData:{_method:'put'},top:200,left:400,reloadAfterSubmit:false,closeAfterEdit:true,modal:true,recreateForm:true,mtype: "POST", url: basePath,afterShowForm:function(){createRolePanel();},viewPagerButtons:false,onClose:function(){$('#tempResourcesIds').combotree('hidePanel');}});
    }
});
//后三个参数，分别是edit、add、delete
jQuery("#list").jqGrid('navGrid',"",{edit:false,cloneToTop:true},{},{mtype: "POST",recreateForm:true,closeAfterAdd:true,reloadAfterSubmit:false,clearAfterAdd:true,modal:true,url:basePath,afterShowForm:function(){createRolePanel();},viewPagerButtons:false,onClose:function(){$('#tempResourcesIds').combotree('hidePanel');}},{url:basePath,reloadAfterSubmit:true,jqModal:false});
var topPagerDiv = $("#list_toppager")[0];
$("#edit_list_top", topPagerDiv).remove();
//$("#del_list_top", topPagerDiv).remove();
//$("#search_list_top", topPagerDiv).remove();
//$("#refresh_list_top", topPagerDiv).remove();
$("#list_toppager_center", topPagerDiv).remove();
$(".ui-paging-info", topPagerDiv).remove();
});
</script>
<script>
function createRolePanel(){
	$('#tempResourcesIds').combotree({  
	    url: '${ctx}/system/menu/checkedTreeNode',  
	    method: 'GET',
	    cascadeCheck:true,
	    checkbox:true,
	    multiple:true,
	    editable:false,
	    onlyLeafCheck:false,
	    hasDownArrow:true,
	    separator:',',
	    onShowPanel:function(){
	    	
	    	
	    },
	    onLoadSuccess:function(){
	    	//初始化combotree时，树还没加载节点，不能判断父子节点，所以必须放在树加载完后触发,如果某一节点有子节点，则不设置该节点的选中状态，因为如果子节点选中了，它也会被选中，如果所有子节点都没有被选中，它也不会被选中
	    	var initResourcesIds = document.getElementById('resourcesIds').value;
	    	if(initResourcesIds!=null&&initResourcesIds!=''){
	    		var resourcesIdsArr = initResourcesIds.split(',');
	    		var tree = $('#tempResourcesIds').combotree('tree');
	    		var arr = new Array();
	    		if(resourcesIdsArr!=null&&resourcesIdsArr.length>0){
	    			for(var i=0;i<resourcesIdsArr.length;i++){
	    				var node = tree.tree('find',resourcesIdsArr[i]);
		    			if(node!=null){
		    				var childrens = tree.tree('getChildren',node.target);
		    				if(childrens==null||childrens.length<1){
		    					arr.push(resourcesIdsArr[i]);
		    				}
		    			}
	    			}
		    		$('#tempResourcesIds').combotree('setValues',arr);
	    		}
	    	}
	    },
	    onCheck:function(node,checked){
	    	var s = "";
	    	var tree = $('#tempResourcesIds').combotree('tree');
	    	//取选中还半选中的节点
	    	var checkedNodes = tree.tree('getChecked');
	    	//自定义的取半选中的节点
	    	var indeterminateNodes = tree.tree('getCheckedExt');
	    	//var arr = $('#tempResourcesIds').combotree('getValues');
	    	var arr = new Array();
	    	arr = arr.concat(checkedNodes,indeterminateNodes);
	    	if(arr!=null&&arr.length>0){
	    		for(var i=0;i<arr.length;i++){
	    			s+=arr[i].id+',';
	    		}
	    		s=s.substring(0,s.lastIndexOf(','));
	    	}
	    	document.getElementById('resourcesIds').value=s;
	    	/*
	    	var tree = $('#tempResourcesIds').combotree('tree');
	    	var arr2 = tree.tree('getCheckedExt');
	    	if(arr2!=null&&arr2.length>0){
	    		for(var i=0;i<arr2.length;i++){
	    			s+=arr2[i].id+',';
	    		}
	    		//$('#tempResourcesIds').combotree('setValues',arr);
	    	}
	    	if(s!=""){
	    		s=s.substring(0,s.lastIndexOf(','));
	    	}
	    	document.getElementById('resourcesIds').value=s;
	    	*/
	    }  
	});
	//初始化时，打开combotree,为了触发onShowPanel事件，初始化combotree
	$('#tempResourcesIds').combotree('showPanel');
	$('#tempResourcesIds').combotree('hidePanel');
}
</script>
</html>