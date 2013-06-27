<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../../inc/header.jsp"%>
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
</head>
<body>
<form>
	<table id='list'></table>
</form>
</body>
<script>
$(function(){
	init();
	function init(){
		createGrid();
	}
	function createGrid(){
		var basePath = '${ctx}/system/sapclient/data';
		function addTestSapConnectionBut(){
			/*
			var s = '<a href="javascript:void(0)" id="testSapConnection" class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-state-hover">测试连接</a>';
			$('#sData').before(s);
			$('#testSapConnection').bind('click',function(e){
				
			});
			*/
		}
		jQuery("#list").jqGrid({
			url:basePath,
		   	ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },
		    datatype: 'json',
		    mtype: 'GET',
		    jsonReader:{
		    	repeatitems:false
		    },
			autowidth:true,
		    width:780,
		    height:300,
		   	colNames:['ID','名称','客户端','用户名','密码','服务器IP','实例编号','语言'],
		   	colModel:[
		   		{name:'id',index:'id',hidden:true, width:1,key:true},
		   		{name:'alias',index:'alias',width:50,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'sapclient',index:'sapclient',width:50,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'userid',index:'userid', width:50,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'password',index:'password',hidden:true,width:50,editable:true,edittype:'password',editrules:{required:true,edithidden:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'ashost',index:'ashost',hidden:false,editable:true,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'sysnr',index:'sysnr',editable:true,width:50,editrules:{required:true},formoptions:{ elmprefix:"",elmsuffix:"  <font color=red>*</font>" }},
		   		{name:'langu',index:'langu',editable:true,edittype:'select',width:50,editoptions:{value:"zh:中文;en:英文"}}
		   	],
		   	pginput:false,
		   	toppager: true,
		    viewrecords:false,
		    rownumbers:true,
		   	sortname: 'id',
		    viewrecords: true,
		    sortorder: "desc",
		    //pager : "#tools",
		    editurl:'',
		    caption:'sap客户端设置',
			multiselect: false,
			ondblClickRow:function(rowid,iCol,cellcontent,e){
		    	//editData为添加的参数，是为了让参数能正常的put到后台
		    	$('#list').jqGrid('editGridRow',rowid,{editData:{_method:'put'},top:50,left:400,width:400,reloadAfterSubmit:true,closeAfterEdit:false,modal:true,recreateForm:true,mtype: "POST", url: basePath,viewPagerButtons:false,afterComplete:function(response, postdata, formid){alert("更新成功");},errorTextFormat:function(response){return "更新失败";},afterShowForm:addTestSapConnectionBut});
		    }
		});
		jQuery("#list").jqGrid('navGrid','',{edit:false,cloneToTop:true},{},{mtype: "POST",top:50,left:400,width:400,recreateForm:true,closeAfterAdd:true,reloadAfterSubmit:true,clearAfterAdd:true,modal:true,url:basePath,viewPagerButtons:false,afterComplete:function(response, postdata, formid){alert("新增成功");},errorTextFormat:function(response){return "新增失败";}},{url:basePath,reloadAfterSubmit:true,jqModal:false});
		var topPagerDiv = $("#list_toppager")[0];
		$("#edit_list_top", topPagerDiv).remove();
		$("#list_toppager_center", topPagerDiv).remove();
		$(".ui-paging-info", topPagerDiv).remove();
	}
});
</script>
</html>