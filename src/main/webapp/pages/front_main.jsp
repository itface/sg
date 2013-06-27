<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pages/inc/header.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${ctx}/script/extjs3.4/resources/css/ext-all.css" />
<script type="text/javascript" src="${ctx}/script/extjs3.4/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/script/extjs3.4/ext-all.js"></script>
<script type="text/javascript" src="${ctx}/script/treeFilter/TreeFilter.js"></script>
<script type="text/javascript" src="${ctx}/script/treeFilter/PinyinFilter.js"></script>

<style>
body{
  margin:0px;
}
.outter{
	
}
.top{
	position:absolute;
	width:100%;
	height:5%;
	background-color:#148498;
}
.center{
	position:absolute;
	top:5%;
	width:100%;
	height:90%;
}
.center_left{
	width:15%;
	float:left;
	height:100%;
	overflow:auto
}
.center_line{
	width:0.3%;
	height:100%;
	float:left;
	background-color:#148498;
}
.center_main{
	height:100%;
	width:84.4%;
	height:100%;
	float:left;

}
.footer{
	position:absolute;
	width:100%;
	bottom:0;
	height:5%;
	background-color:#148498;
}
.exit{
	position:absolute;
	right:0;
	padding-right:50px;
	padding-top:5px;
	font-size:18px;
	
}
</style>
</head>
<body>
<!--a href="${ctx}/common/console">进入管理员界面</a>
<a href="${ctx}/j_spring_security_switch_user?j_username=admin">切换成管理员进入管理员界面</a>
<a href="${ctx}/common/logout">注销</a-->
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
        renderTo:'front_index_center_left',
        rootVisible:false,
        width:"100%",
		height:600,
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
                  		document.getElementById("iframe_main").src='${ctx}'+node.attributes.url; 
				  }
				  return true;
    });
    var filterFiled = new Ext.form.TextField({
        width:150,
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
</script>
</html>