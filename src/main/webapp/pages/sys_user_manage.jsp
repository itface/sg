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
</head>
<body>
<table id="list"></table>
<div id="tools"></div>
</body>
<script>
$(function(){
var userBasePath = '${ctx}/system/user/data';
function validateLength(value,colname) {
	var col ={
		'帐号':100,
		'姓名':100
	};
	if(getLen(value) <= col[colname]) {
		return [true,"",""];
	} else {
		return [false,colname+"长度不能超过"+col[colname],""];
	}
}
jQuery("#list").jqGrid({
	url:userBasePath,
   	ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },
    datatype: 'json',
    mtype: 'GET',
    jsonReader:{
    	repeatitems:false
    },
    hidegrid:false,
	autowidth: false,
	width:1000,
    height:400,
   	colNames:['ID','帐号','密码','再次输入密码','姓名','使用状态','使用状态','创建时间','最后登录时间','登录次数','角色','用户类型','用户类型','sap权限2','授权范围','说明'],
   	colModel:[
   		{name:'id',index:'id',hidden:true, width:1,key:true,editable:true},
   		{name:'username',index:'username', width:150,editable:true,editrules:{required:true,custom:true,custom_func:validateLength}},
   		
   		{name:'repassword',index:'repassword',hidden:true,edittype:'password', width:150,editable:true,editrules:{required:true,edithidden:true}},
   		{name:'password',index:'password',hidden:true,edittype:'password', width:150,editable:true,editrules:{required:true,edithidden:true}},
   		{name:'name',index:'name', width:150, align:"right",editable:true,editrules:{required:true,custom:true,custom_func:validateLength}},
   		
   		{name:'userstatus',index:'userstatus', width:150, align:"right",editable:true,edittype:'select',editoptions:{value:"1:激活;0:非激活"},editrules:{required:true},hidden: true,editrules:{edithidden:true}},
   		{name:'status',index:'status', width:150, align:"right",editable:false},
   		
   		{name:'usercreatetime',index:'usercreatetime', width:150,editable:true,hidden: true},
   		{name:'lastlogintime',index:'lastlogintime', width:150, align:"right",editable:true,hidden:true},
   		{name:'logincount',index:'logincount', width:150,editable:true,hidden: true},
   		{name:'roleIds',index:'roleIds',editable:true,hidden:true},
   		{name:'usertype',index:'usertype', width:150, align:"right",editable:true,edittype:'select',hidden:true,editrules:{required:true,edithidden:true},
			editoptions:{
  				dataUrl:"${ctx}/system/role/getRoleSelect"
  			}
   		},
   		{name:'type',index:'type', width:150, align:"right",editable:false},
   		{name:'sapDataCollections',index:'sapDataCollections', width:150, align:"right",editable:true,hidden:true},
   		{name:'tempSapDataCollections',index:'tempSapDataCollections', width:150, align:"right",editable:true,editrules:{required:true}},
   		{name:'userdescription',index:'userdescription',edittype:'textarea', width:150, align:"right",editable:true}
   	],
   	pginput:false,
   	toppager: true,
    viewrecords:false,
    rownumbers:true,
    shrinkToFit:false,//超出width出滚动条
	forceFit:true,//调整列宽时，调整width总长度
   	sortname: 'id',
    viewrecords: true,
    sortorder: "desc",
    //pager : "#tools",
    editurl:'',
    caption:'用户管理',
	multiselect: false,
	ondblClickRow:function(rowid,iCol,cellcontent,e){
    	//editData为添加的参数，是为了让参数能正常的put到后台
    	$('#list').jqGrid('editGridRow',rowid,{editData:{_method:'put'},beforeSubmit:beforeSubmitEvent,top:200,left:400,width:450,closeAfterEdit:true,reloadAfterSubmit:true,modal:true,recreateForm:true,mtype: "POST", url: userBasePath,afterShowForm:function(){createSapClientIdsPanel();$('#username').attr('disabled',true);$('#tr_repassword').hide();$('#tr_password').hide();},viewPagerButtons:false,onClose:function(){$('#tempRoleIds').combotree('hidePanel');}});
    }
});
jQuery("#list").jqGrid('navGrid','',{edit:false,cloneToTop:true},{},{mtype: "POST",beforeSubmit:beforeSubmitEvent,recreateForm:true,width:450,closeAfterAdd:true,reloadAfterSubmit:true,clearAfterAdd:true,modal:true,url:userBasePath,afterShowForm:function(){createSapClientIdsPanel();},viewPagerButtons:false,onClose:function(){$('#tempRoleIds').combotree('hidePanel');$('#tempSapDataCollections').combotree('hidePanel');}},{url:userBasePath,reloadAfterSubmit:true,jqModal:false},{});
var topPagerDiv = $("#list_toppager")[0];
$("#edit_list_top", topPagerDiv).remove();
//$("#del_list_top", topPagerDiv).remove();
//$("#search_list_top", topPagerDiv).remove();
//$("#refresh_list_top", topPagerDiv).remove();
$("#list_toppager_center", topPagerDiv).remove();
$(".ui-paging-info", topPagerDiv).remove();



$('#gbox_list .ui-jqgrid-titlebar').remove();
var s = "<td width='130px'><div class='extendGridTitle' style='font-weight: bold;  color: #515151;  font-size: 12px;  font-family: 宋体;padding: 5px .2em .2em 15px;'>";
	s+="	<span class='ui-jqgrid-title'><img src='${ctx}/images/grid.png' height='13px'>&nbsp;&nbsp;<span>用户管理</span></span>";
	s+="</div></td>";
$('#add_list_top').before(s);
$('#add_list_top').before($('.ui-state-disabled'));

//**************************************************************************grid end***************************************************
});
</script>
<script>
function beforeSubmitEvent(postdata,formid){
	if(postdata.sapDataCollections.indexOf(',')>=0&&$('#roleIds option:selected').text()=='系统接入用户'){
		return [false,'系统接入员只能选择一个SAP源'];
	}
	if(postdata.repassword!=postdata.password){
		return [false,'两次输入的密码不一致'];
	}
	var usertype = $('#usertype').val();
	var tree = $('#tempSapDataCollections').combotree('tree');
	var nodes = tree.tree('getChecked');
	var roleids = "";
	if(usertype!=null&&usertype!=''){
		roleids+=usertype+",";
	}
	$(nodes).each(function(i,v){
		roleids+=$(v).attr('attributes')+",";
	});
	if(roleids != ""){
		roleids=roleids.substring(0,roleids.lastIndexOf(','));
	}
	$('#roleIds').val(roleids);
	postdata.roleIds=roleids;
	return [true,''];
}
function createSapClientIdsPanel(){
	$('#tempSapDataCollections').combotree({  
	    url: '${ctx}/system/dataCollection/sapDataCollection/checkedTree',  
	    method: 'GET',
	    cascadeCheck:true,
	    checkbox:true,
	    multiple:true,
	    editable:false,
	    hasDownArrow:true,
	    separator:',',
	    onShowPanel:function(){
	    	//初始化combotree
	    	var sapDataCollections = document.getElementById('sapDataCollections').value;
	    	if(sapDataCollections!=null&&sapDataCollections!=''){
	    		var arr = sapDataCollections.split(',');
	    		$('#tempSapDataCollections').combotree('setValues',arr);
	    	}
	    	
	    },
	    onCheck:function(node,checked){
	    	var s = "";
	    	var arr = $('#tempSapDataCollections').combotree('getValues');
	    	if(arr!=null&&arr.length>0){
	    		for(var i=0;i<arr.length;i++){
	    			s+=arr[i]+',';
	    		}
	    		s=s.substring(0,s.lastIndexOf(','));
	    	}
	    	document.getElementById('sapDataCollections').value=s;
	    }  
	});
	//初始化时，打开combotree,为了触发onShowPanel事件，初始化combotree
	$('#tempSapDataCollections').combotree('showPanel');
	$('#tempSapDataCollections').combotree('hidePanel');
}
</script>
</html>