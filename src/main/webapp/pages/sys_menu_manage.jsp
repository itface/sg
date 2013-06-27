<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./inc/header.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.jqgrid.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.ui.css'/>">
<script src="<c:url value='/script/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.locale-cn.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/jquery.jqGrid.src.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid/grid.treegrid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/jqgrid_extend.js'/>" type="text/javascript"></script>
<script src="<c:url value='/script/commons.js'/>" type="text/javascript"></script>
</head>
<body>
<table id="list">
</table>
<div id="paddtree"></div>
</body>
<script>
$(function () {
var basePath = '${ctx}/system/resource/data';
var selectedRowId=0;
function updateSelectOptions(){
	var indexNum = 0;
	var parentId = 0;
	var id = document.getElementById('id').value;
	if(id==null||id==0){
		//parentId = jQuery("#list").getRowData(selectedRowId).parentId;
		parentId=selectedRowId;
	}else{
		parentId = document.getElementById('parentId').value;
	}
	var select = document.getElementById('resourceIndex');
	var selectedValue = select.options[select.selectedIndex].value;
	if(parentId==null||parentId==''){
		parentId=0;
	}
	$.ajax({
		url:'${ctx}/system/resource/resourceIndex/'+parentId,
		type:'GET',
		async:false,
		success:function(num){
			indexNum=num;
		}
	});
	select.options.length=0;
	for(var i=1;i<=parseInt(indexNum);i++){
		select.add(new Option(i,i)); 
	}
	//如果是新增，则默认选中最大值
	if(id==null||id==0){
		var maxNum = parseInt(indexNum)+1;
		select.add(new Option(maxNum,maxNum)); 
		select.options[maxNum-1].selected=true;
	}else{
		for(var i=0;i<select.options.length;i++){
			if(select.options[i].value==selectedValue){
				select.options[i].selected=true;
				break;
			}
		}
	}
}
function validateLength(value,colname) {
	var col ={
		'名称':100,
		'URL':100
	};
	if(getLen(value) <= col[colname]) {
		return [true,"",""];
	} else {
		return [false,colname+"长度不能超过"+col[colname],""];
	}
}
function my_input(value, options) {
	return $("<select><option value='"+value+"'>"+value+"</select>");
	//return $("<input type='text' size='10' value='"+value+"' readonly='true'/>");
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
    treeGrid: true,
    ExpandColClick:false,
    treeGridModel: 'adjacency',
    ExpandColumn: 'resourceName',
    colNames: ['ID','名称','别称', 'URL','index','parent','是否隐藏','系统菜单'],
    colModel: [
    { name: 'id', index: 'id', hidden: true, width: 1,sorttype: 'int', key: true,editable:true },
   	{ name: 'resourceName', index: 'resourceName',sorttype: 'string', width: 300,editable:true,editrules:{required:true,custom:true,custom_func:validateLength},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
    { name: 'alias', index: 'alias',width: 100,editable:true,hidden: true,editrules:{edithidden:true}},
    { name: 'url', index: 'url', width: 300,sorttype: 'string',editable:true,editrules:{custom:true,custom_func:validateLength}},
    { name: 'resourceIndex', index: 'resourceIndex',hidden: true, width: 100,editable:true,edittype:'custom',editoptions:{custom_element:my_input,custom_value:my_value},editrules:{required:true,edithidden:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
    { name: 'parentId', index: 'parentId',sorttype: 'int',hidden: true, width: 100,editable:true },
    { name: 'isshow', index: 'isshow',width: 100,editable:true,edittype:'select',editoptions:{value:"0:是;1:否"},hidden: true,editrules:{edithidden:true}},
    { name:'issystem',index:'issystem',editable:true,edittype:'select',hidden: true,width:50,editrules:{edithidden:true},editoptions:{value:"0:否;1:是"}}
    ],
    gridview: true,
    //sortname: 'ID',
    //sortorder: "desc",
    pager: "false", 
    autowidth: true,
    //width:600,
    height:600,
    editurl:'',
    toppager: true,
    caption:'导航菜单',
    //height: 'auto'
    ondblClickRow:function(rowid,iCol,cellcontent,e){
    	//editData为添加的参数，是为了让参数能正常的put到后台,添加afterComplete:function(response,postdata,formid){alert(response.responseText);}，根据返回信息给出提示
    	$('#list').jqGrid('editGridRow',rowid,{editData:{_method:'put'},top:200,left:400,reloadAfterSubmit:true,closeAfterEdit:true,modal:true,recreateForm:true,mtype: "POST", url: basePath,viewPagerButtons:false,afterShowForm:function(){updateSelectOptions();}});
    },
    onCellSelect:function(rowid,iCol,cellcontent,e){
    	selectedRowId=rowid;
    }
});
//后三个参数，分别是edit、add、delete
jQuery("#list").jqGrid('navGrid',"",{edit:false,cloneToTop:true},{},{mtype: "POST",recreateForm:true,closeAfterAdd:true,reloadAfterSubmit:true,modal:true,clearAfterAdd:true,url:basePath,afterShowForm:function(){updateSelectOptions();}},{url:basePath,jqModal:false});
var topPagerDiv = $("#list_toppager")[0];
$("#edit_list_top", topPagerDiv).remove();
//$("#del_list_top", topPagerDiv).remove();
//$("#search_list_top", topPagerDiv).remove();
//$("#refresh_list_top", topPagerDiv).remove();
$("#list_toppager_center", topPagerDiv).remove();
$(".ui-paging-info", topPagerDiv).remove();
});

</script>
</html>