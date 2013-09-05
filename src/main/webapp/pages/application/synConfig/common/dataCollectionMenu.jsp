<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../../inc/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link href="${ctx}/css/sapgarden/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/sapgarden/garden.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.easyui/themes/icon.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.jqgrid.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/jquery.ui.css'/>">
<link rel="stylesheet" type="text/css" href="${ctx}/script/extjs3.4/resources/css/ext-all.css" />
<script type="text/javascript" src="${ctx}/script/extjs3.4/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/script/extjs3.4/ext-all.js"></script>
<script type="text/javascript" src="${ctx}/script/treeFilter/TreeFilter.js"></script>
<script type="text/javascript" src="${ctx}/script/treeFilter/PinyinFilter.js"></script>
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
<body onload="setIframeSize()">
<div class="toolbar">
  <div class="prompt_message">使用说明</div>
  <div class="toolbar_left"> 
      <a href="#" class="btn" id='add' onMouseDown="this.className='btn_mousedown'" onMouseUp="this.className='btn'" onMouseOver="this.className='btn_hover'" onMouseOut="this.className='btn'">编辑数据项</a> 
   </div>
</div>
<div style="padding:5px 10px; clear:both">
     <div class="sjx_tree"> 
       	<div id="mytree"></div>
     </div>
	<div class="sjx">
        <div class="sjx_title">数据项说明</div>
        <div class="sjx_main">
        	<iframe id='contentIframe' style='border:0px'></iframe>
        </div>
    </div>
</div>  
	<!-- div id='tbar'>
		<button id='add'>编数据项</button>
	</div>
	<div id='treeDiv' style='border:1px #ccc solid;overflow:auto;padding-top:0px;padding-left:0px;float:left;'>
		<ul id="tree"></ul>
	</div>
	<div id='contentDiv' style='float:left;border:1px #ccc solid;overflow:auto;padding-top:0px;padding-left:0px;'>
		<iframe style='height:100%;width:100%;border:0px' id='contentIframe'>
		</iframe>
	</div-->
</body>
<script>
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '${ctx}/script/extjs3.4/resources/images/default/tree/s.gif';
	 var myTreeLoader = new Ext.tree.TreeLoader({
        	dataUrl:'${ctx}/system/menu/getDataCollectionExtTreeByRoleId/${roleId}',
        	requestMethod:'GET',
        	baseParams:{format:'json'}
    });
	//ie报参数无效错误,指点高度
    var myTree = new Ext.tree.TreePanel({
        renderTo:'mytree',
        rootVisible:false,
        width:215,
		height:$(window).height()-90,
		border:false,
        autoScroll:true,
		animate :false,
        tbar:new Ext.Toolbar(),
        root:{id:'0',text: 'root',loader:myTreeLoader}
    });
    /*
    tree.on('beforeload', function(node){ 
                   tree.loader.dataUrl='ajax?cmd=API_Login_GetDep&nodeId='+node.id;    //定义子节点的Loader    
    }); 
    */
    myTree.on("click",function(node,e){       	        
         	       if(!node.isLeaf()){
		                e.stopEvent();//非叶子节点则不触发
                  }else{
                  		//node.attributes.href='sg'+node.attributes.href;
                  		$("#contentIframe").attr("src",'${ctx}'+node.attributes.url+'/memo'); 
				  }
				  return true;
    });
    var filterFiled = new Ext.form.TextField({
        width:205,
        emptyText:'快速检索',
        enableKeyEvents: true,
	    listeners:{
			render: function(f){
                this.filter = new QM.ux.TreeFilter(myTree,{
					clearAction : 'expand'
				});//初始化TreeFilter 
			},
            keyup: {//添加键盘点击监听
                fn:function(t,e){
                  t.filter.filter(t.getValue());
                },
                buffer: 350
            }
		}
    });
    //myTree.expandAll();
    var tbar = myTree.getTopToolbar();
    tbar.add(filterFiled);
    tbar.doLayout();
    //Ext.getDom('<input id="test" name="test"/>')
    initEvent();
    function initEvent(){
		$('#add').bind('click',function(e){
			var dialog = $.dialog({
		 		id:'dia2',
			    lock: true,
			    min:false,
			    max:false,
			    cancel:false,
			    background: '#FFF',
			    opacity: 0.5,      
			    width:800,
			    height:500,
			    content: 'url:${ctx}/system/dataCollectionMenu/fullMenu/${roleId}',
			    title:''
			});
			$.dialog.data('tree',myTree);
			$.dialog.data('menu',parent.myTree);
			$.dialog.data('dialog',dialog);
		});
	}
});
function setIframeSize(){
	var iframeHeight = $(window).height()-124;
	var iframeWidth = $(window).width()-260;
	$("#contentIframe").css("height",iframeHeight);
	$("#contentIframe").css("width",iframeWidth);
}

/*
$(function(){
	init();
	var tree;
	function init(){
		initEvent();
		createTree();
	}
	function initEvent(){
		$('#add').bind('click',function(e){
			var dialog = $.dialog({
		 		id:'dia2',
			    lock: true,
			    min:false,
			    max:false,
			    cancel:false,
			    background: '#FFF',
			    opacity: 0.5,      
			    width:800,
			    height:500,
			    content: 'url:${ctx}/system/dataCollectionMenu/fullMenu/${roleId}',
			    title:''
			});
			$.dialog.data('tree',tree);
			$.dialog.data('menu',parent.myTree);
			$.dialog.data('dialog',dialog);
		});
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
*/
</script>
</html>