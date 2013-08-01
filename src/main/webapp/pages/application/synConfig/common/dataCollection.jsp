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
	<table id='list'></table>
	<table id='externalUserGrid'></table>
</body>
<script>
$(function(){
	init();
	function init(){
		createGrid();
		createExternalUserGrid();
	}
	function createGrid(){
		var basePath = '${ctx}/system/dataCollection/sapDataCollection/data';
		jQuery("#list").jqGrid({
			url:basePath,
		   	ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },
		    datatype: 'json',
		    mtype: 'GET',
		    jsonReader:{
		    	repeatitems:false
		    },
		    shrinkToFit:false,//超出width出滚动条
			forceFit:true,//调整列宽时，调整width总长度
			//autowidth:true,
		    width:990,
		    //height:300,
		   	colNames:['ID','roleid','SAP数据集代号','测试连接','GARDEN数据库IP','GARDEN数据库实例名','GARDEN数据库schema','GARDEN数据库实例用户名','GARDEN数据库实例密码','SAP Application Server(SAP应用服务器IP)','Instance Number(SAP实例编号)','System ID(SAP系统标识)','client(SAP客户端)','SAP User(SAP用户名)','Password(SAP密码)','Garden用户','说明'],
		   	colModel:[
		   		{name:'id',index:'id',hidden:true, width:1,key:true},
		   		{name:'roleid',index:'roleid',hidden:true,editable:true},
		   		{name:'alias',index:'alias',width:250,editable:false},
		   		{name:'act',index:'act',editable:false},
		   		{name:'gardendbip',index:'gardendbip',width:250,editable:true,editrules:{required:true}},
		   		{name:'gardendbinstance',index:'gardendbinstance', width:250,editable:true,editrules:{required:true}},
		   		{name:'gardendbschema',index:'gardendbschema',width:250,editable:true,editrules:{required:true,edithidden:true}},
		   		{name:'gardendbusername',index:'gardendbusername',width:250,editable:true,editrules:{required:true}},
		   		{name:'gardendbpassword',index:'gardendbpassword',width:250,editable:true,edittype:'password',width:50,editrules:{required:true},hidden: true,editrules:{edithidden:true}},
		   		{name:'sapserverip',index:'sapserverip',width:250,editable:true,editrules:{required:true}},
		   		{name:'sapserverinstance',index:'sapserverinstance', width:250,editable:true,editrules:{required:true}},
		   		{name:'sapserversystemid',index:'sapserversystemid',width:250,editable:true,editrules:{required:true,edithidden:true}},
		   		{name:'sapserverclient',index:'sapserverclient',width:250,editable:true,editrules:{required:true}},
		   		{name:'sapserverusername',index:'sapserverusername',width:250,editable:true,editrules:{required:true}},
		   		{name:'sapserverpassword',index:'sapserverpassword',width:250,editable:true,edittype:'password',width:50,editrules:{required:true},hidden: true,editrules:{edithidden:true}},
		   		{name:'gardenusername',index:'gardenusername',width:250,editable:true,editrules:{required:true}},
		   		{name:'collectiondescription',index:'collectiondescription',width:250,editable:true}
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
		    caption:'SAP数据集定义',
			multiselect: false,
			gridComplete: function(){
				var ids = jQuery("#list").jqGrid('getDataIDs');
				for(var i=0;i < ids.length;i++){
					var cl = ids[i];
					be = "<input type='button' value='测试连接' onclick=\"javascript:(function(){$.ajax({url:'${ctx}/system/testConnection',async:false,cache:false,type:'POST',data:{id:'"+cl+"'},success:function(msg){if(msg!=null&&msg!='null'&&msg!=''){alert(msg);}else{alert('连接成功');}}});})();\"/>"; 
					jQuery("#list").jqGrid('setRowData',ids[i],{act:be});
				}	
			},
			ondblClickRow:function(rowid,iCol,cellcontent,e){
		    	//editData为添加的参数，是为了让参数能正常的put到后台
		    	$('#list').jqGrid('editGridRow',rowid,{editData:{_method:'put'},top:50,left:400,width:650,reloadAfterSubmit:true,closeAfterEdit:true,modal:true,recreateForm:true,mtype: "POST", url: basePath,viewPagerButtons:false,afterComplete:function(response, postdata, formid){if(response.responseText==null||response.responseText=="")alert("更新成功");else alert(response.responseText);},errorTextFormat:function(response){return "更新失败";}});
		    }
		});
		jQuery("#list").jqGrid('navGrid','',{edit:false,cloneToTop:true},{},{mtype: "POST",top:50,left:400,width:650,recreateForm:true,closeAfterAdd:true,reloadAfterSubmit:true,clearAfterAdd:true,modal:true,url:basePath,viewPagerButtons:false,afterComplete:function(response, postdata, formid){if(response.responseText==null||response.responseText=="")alert("新增成功");else alert(response.responseText);},errorTextFormat:function(response){return "新增失败";}},{url:basePath,reloadAfterSubmit:true,jqModal:false});
		var topPagerDiv = $("#list_toppager")[0];
		$("#edit_list_top", topPagerDiv).remove();
		$("#list_toppager_center", topPagerDiv).remove();
		//$("#del_list_top", topPagerDiv).remove();
		$(".ui-paging-info", topPagerDiv).remove();
	}
	
	function createExternalUserGrid(){
		var basePath = '${ctx}/system/dataCollection/externalUser/data';
		jQuery("#externalUserGrid").jqGrid({
			url:basePath,
		   	ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },
		    datatype: 'json',
		    mtype: 'GET',
		    jsonReader:{
		    	repeatitems:false
		    },
		    shrinkToFit:false,//超出width出滚动条
			forceFit:true,//调整列宽时，调整width总长度
			//autowidth:true,
		    width:990,
		    //height:300,
		   	colNames:['ID','接入系统名称','接入系统地址（url）','接入使用的Garden User','Garden里的SAP数据集','说明'],
		   	colModel:[
		   		{name:'id',index:'id',hidden:true, width:1,key:true},
		   		{name:'externalsystemname',index:'externalsystemname',width:250,editable:true,editrules:{required:true}},
		   		{name:'externalsystemurl',index:'externalsystemurl', width:250,editable:true,editrules:{required:true}},
		   		{name:'username',index:'username',edittype:'select',editable:true,editrules:{required:true},
		   			editoptions:{
		   				dataUrl:"${ctx}/system/user/getUserSelect",
		   				dataInit:function(el){
		   					$(el).bind('change',function(){
		   						var v = $('option:checked',el).attr('extendProperty');
		   						$('#sapdatacollection').val(v);
		   					});
		   				}
		   			}
		   		},
		   		{name:'sapdatacollection',index:'sapdatacollection',width:250,editable:true,editrules:{required:true},
		   			editoptions:{
		   				dataInit:function(el){
		   					$(el).attr('readonly',true);
		   				}
		   			}
		   		},
		   		{name:'descriptioninfo',index:'descriptioninfo',editable:true,edittype:'textarea'}
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
		    caption:'Garden接入系统定义表',
			multiselect: false,
			ondblClickRow:function(rowid,iCol,cellcontent,e){
		    	//editData为添加的参数，是为了让参数能正常的put到后台
		    	$('#externalUserGrid').jqGrid('editGridRow',rowid,{editData:{_method:'put'},top:50,left:400,width:550,reloadAfterSubmit:true,closeAfterEdit:true,modal:true,recreateForm:true,mtype: "POST", url: basePath,viewPagerButtons:false,afterComplete:function(response, postdata, formid){alert("更新成功");},errorTextFormat:function(response){return "更新失败";}});
		    }
		});
		jQuery("#externalUserGrid").jqGrid('navGrid','',{edit:false,cloneToTop:true},{},{mtype: "POST",top:50,left:400,width:550,recreateForm:true,closeAfterAdd:true,reloadAfterSubmit:true,clearAfterAdd:true,modal:true,url:basePath,viewPagerButtons:false,afterComplete:function(response, postdata, formid){alert("新增成功");},errorTextFormat:function(response){return "新增失败";}},{url:basePath,reloadAfterSubmit:true,jqModal:false});
		var topPagerDiv = $("#externalUserGrid_toppager")[0];
		$("#edit_externalUserGrid_top", topPagerDiv).remove();
		$("#externalUserGrid_toppager_center", topPagerDiv).remove();
		$(".ui-paging-info", topPagerDiv).remove();
	}
	/*
	function createGrid2(){
		 var colM = [
		        { id:"id",title: "id", width: 100},
		        { id:"externalsystemurl",title: "接入系统地址", width: 250,editable:true },
		        { id:"username",title: "接入使用的Garden User", width: 300,editable:true  },
		        { id:"sapdatacollection",title: "Garden里的SAP数据集", width: 300,editable:true  },
		        { id:"descriptioninfo",title: "说明", width: 200 }
			];
        var dataModel = {
            location: "remote",
            sorting: "local",
            //paging: "remote",
            dataType: "JSON",
            method: "GET",
            curPage: 1,
            rPP: 20,
            sortIndx: 2,
            sortDir: "up",
            rPPOptions: [1, 10, 20, 30, 40, 50, 100, 500, 1000],
            getUrl: function () {
                //var sortDir = (this.sortDir == "up") ? "asc" : "desc";
                //var sort = ['ShipCountry', 'contactName', 'orderID'];
                return { url: "${ctx}/system/dataCollection/externalUser/data", data: "cur_page=" + this.curPage + "&records_per_page=" + 
                    this.rPP};
            },
            getData: function (dataJSON) {
            	var curPage = 0;
            	var totalRecords=0
            	var realData = new Array();
            	if(dataJSON!=null){
	            	var data = dataJSON.data;
	            	curPage=dataJSON.curPage;
	            	totalRecords=dataJSON.totalRecords
	            	if(data!=null&&data!=''){
		            	$(data).each(function(i,v){
		            		var data = new Array();
		            		$(colM).each(function(index,value){
		            			var id = value['id'];
		            			data.push(v[id]);
		            		});
		            		realData.push(data);
		            	});
	            	}
            	}
                return { curPage: curPage, totalRecords:totalRecords, data: realData };                
            }
        }

        var $grid  = $("div#externalUserGrid").pqGrid({ width: 900, height: 400,
            dataModel: dataModel,
            colModel: colM,
            title:"Garden接入系统定义表",
            resizable: false,            
            columnBorders: true,
            editable:true,
            render:renderFun
        });
        function renderFun(evt, obj) {
        	var id = $(this).attr('id');
           var $toolbar = $("<div class='pq-grid-toolbar pq-grid-toolbar-crud'></div>").appendTo($(".pq-grid-top", this));
			$("<span>Add</span>").appendTo($toolbar).button({ icons: { primary: "ui-icon-circle-plus"} }).click(function (evt) {
               addRow();
           });
           $("<span>Edit</span>").appendTo($toolbar).button({ icons: { primary: "ui-icon-pencil"} }).click(function (evt) {
               editRow();
           });
           $("<span>Delete</span>").appendTo($toolbar).button({ icons: { primary: "ui-icon-circle-minus"} }).click(function () {
               deleteRow();
           });
           $toolbar.disableSelection();
           $("<div class='"+id+"_dialog'><form class='"+id+"_dialogForm'></form></div>").appendTo($(this));
           $('.'+id+'_dialog').dialog({ width: 400, modal: true,
	            autoOpen: false
	        });
       }
       function addRow() {
       		var $dialog=$(".externalUserGrid_dialog");
       		var $dialogForm=$(".externalUserGrid_dialogForm");
            var DM = $grid.pqGrid("option", "colModel");
            $(DM).each(function(i,v){
            	var id = v['id'];
            	var lable = v['title'];
            	$dialogForm.append("<tr><td>"+lable+":</td><td><input type='text' id='"+id+"' name='"+id+"'></td></tr>");
            });
            $dialog.dialog({ title: "Add Record", buttons: {
                Add: function () {
                    var row = {};
                    //save the record in DM.data.
                    var arr = $dialogForm.find("input");
                    $(arr).each(function(i,v){
                    	var val = $(v).val();
                    	var id = $(v).attr('id');
                    	row[id]=val;
                    });
                    $.ajax({
                    	url:'${ctx}/system/dataCollection/externalUser/data',
                    	data:row,
                    	type:'post'
                    });
                    $grid.pqGrid("refreshDataAndView");
                    $(this).dialog("close");
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
            }).dialog("open");
        }
	}
	*/
	
});
</script>
</html>