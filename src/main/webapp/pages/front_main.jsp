<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pages/inc/header.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${ctx}/css/sapgarden/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/sapgarden/garden.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/script/extjs3.4/resources/css/ext-all.css" />
<script type="text/javascript" src="${ctx}/script/extjs3.4/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/script/extjs3.4/ext-all.js"></script>
<script type="text/javascript" src="${ctx}/script/treeFilter/TreeFilter.js"></script>
<script type="text/javascript" src="${ctx}/script/treeFilter/PinyinFilter.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery-1.7.2.min.js"></script>
<style>

</style>
<script type="text/javascript">

var topHeight = 70;
var treeDivWidth = 200;



/*
$(document).ready(function(){
	var h = $(window).height()-topHeight;
	var w = $(window).width()-treeDivWidth;
	$(".garden_iframe").css("height",h);
	$(".garden_iframe").css("width",w+'px');
});
$(window).resize(function(){
	var h = $(window).height()-topHeight;
	var w = $(window).width()-treeDivWidth;
	$(".garden_iframe").css("height",h);
	$(".garden_iframe").css("width",w+'px');
});
*/
</script>
</head>
<!--body>
<a href="${ctx}/common/console">进入管理员界面</a>
<a href="${ctx}/j_spring_security_switch_user?j_username=admin">切换成管理员进入管理员界面</a>
<a href="${ctx}/common/logout">注销</a>
<div id='front_index_outter' class='outter'>
	<div id="front_index_top" class='top'>
		<div id="front_index_exit" class='exit'>
			<a href="${ctx}/common/logout">退出</a>
		</div>
	</div>
	<div id="front_index_center" class='center'>
		<div id="front_index_center_left" class='center_left'>
		</div>
		<div id="front_index_center_line" class='center_line'></div>
		<div id="front_index_center_main" class='center_main'>
			<iframe id="iframe_main" src="" width="100%" height="100%" border=0></iframe>
		</div>
	</div>
	<div id="front_index_bottom" class='footer'></div>
</div>
</body-->
<body class="min_width" onload="setIframeSize()">
	<div class="header">
	  <div class="header_logo"><img src="${ctx}/images/header_logo.png" width="224" height="70" /></div>
	  <div class="header_text">
	    <h1>你好</h1>
	    <a href="${ctx}/common/logout">退出</a></div>
	</div>
	<div class="main">
	  <div class="main_tree">
	    <div id="mytree"></div>
	  </div>
	  <div class="main_box">
	    <iframe class="garden_iframe" frameborder="0" src="" ></iframe>
	  </div>
	</div>
</body>
<script>
var myTree;
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '${ctx}/script/extjs3.4/resources/images/default/tree/s.gif';
	 var myTreeLoader = new Ext.tree.TreeLoader({
        	dataUrl:'${ctx}/system/menu/node',
        	requestMethod:'GET',
        	baseParams:{format:'json'}
    });
	//ie报参数无效错误,指点高度
    myTree = new Ext.tree.TreePanel({
        renderTo:'mytree',
        rootVisible:false,
        width:200,
		height:$(window).height()-topHeight,
		border:false,
		maskDisabled :true,
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
                  		$(".garden_iframe").attr("src",'${ctx}'+node.attributes.url); 
				  }
				  return true;
    });
    var filterFiled = new Ext.form.TextField({
        width:188,
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
});
function setIframeSize(){
	var iframeHeight = $(window).height()-topHeight;
	var iframeWidth = $(window).width()-treeDivWidth;
	$(".garden_iframe").css("height",iframeHeight);
	$(".garden_iframe").css("width",iframeWidth);
}
</script>
</html>