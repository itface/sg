(function($){
	function constructGrid(container,options){
		$(container).jqGrid($.extend({
					url:(options.contextPath?options.contextPath+options.baseUrl:options.baseUrl),
				   	ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },
				   	datatype:options.datatype,//为了使grid初始化时不显示数据，datatype设为local,查询设改为json到后台取数
				    mtype: 'GET',
				    jsonReader:{
				    	repeatitems:false
				    },
				    multiselectWidth:options.multiselectWidth,
					autowidth: options.autoWidth,
				    width:options.width,
				    height:options.height,
				   	colNames:options.columnNames,
				   	colModel:options.columnModel,
				   	shrinkToFit:options.shrinkToFit,//超出width出滚动条
				   	forceFit:options.forceFit,//调整列宽时，调整width总长度
				   	toppager: options.toppager,
				    viewrecords:options.viewrecords,
				    rownumbers:options.rownumbers,
				    rownumWidth:options.rownumWidth,
				    loadtext:options.loadtext,
				    loadui:options.loadui,
				    multiselect: options.multiselect,
				    pager : options.pager,
				    editurl:options.editurl,
					pginput:options.pginput,
				    rowNum:(options.pager==""?10000:options.rowNum),
				    rowList:options.rowList,
				    caption:options.caption,
				    sortable:options.sortable,
					loadonce:options.loadonce,
					ondblClickRow:options.ondblClickRow
			},options.eventModels));
	}
	function initGridStyle(options){
		//setGridTbarStyle(options);
		setGridPageStyle(options);
		removeCloseGridButton();
		setGridOverflowX(options);
	}
	function setGridTbarStyle(options){
		jQuery("#"+options.id).jqGrid('navGrid','',
			{edit:false,cloneToTop:true},
			{},
			options.adder,
			options.deleter,
			{multipleSearch:true, multipleGroup:false, showQuery: false}
		);
	}
	function setGridPageStyle(options){
		/*
		$("#"+options.id+"_toppager_left").empty();
		if(options.pager!=null&&options.pager!=''){
			//设置分页信息
			$("#"+options.id+"_toppager_left").css('width',10);
			$("#"+options.id+"_toppager_right .ui-paging-info").css("text-align","left");
		}else{
			$("#"+options.id+"_toppager_center").empty();
			$("#"+options.id+"_toppager_right").empty();
		}*/
		if(options.pager!=null&&options.pager!=''){
		}else{
			$("#"+options.id+"_toppager_center").empty();
			$("#"+options.id+"_toppager_right").empty();
		}
	}
	function removeCloseGridButton(){
		$('.ui-jqgrid-titlebar-close.HeaderButton').remove();
	}
	function setGridOverflowX(options){
		//设置滚动条
		/*
		$('.ui-jqgrid-titlebar-close.HeaderButton').remove();
		$('#gbox_'+options.id).css('overflow','auto');
		//标题行
		$('#gbox_'+options.id+' .ui-jqgrid-titlebar').css('width',options.width);
		//分页工具行，因为标题行的padding左右之和为17，所以要添加17
		$('#'+options.id+'_toppager').css('width',options.width+17);
		//子表head行，因为标题行的padding左右之和为17，所以要添加17
		$('#gbox_'+options.id+' .ui-jqgrid-hdiv').css('width',options.width+17);
		//子表内容行，因为标题行的padding左右之和为17，所以要添加17
		$('#gbox_'+options.id+' .ui-jqgrid-bdiv').css('width',options.width+17);
		*/
		$('#gbox_'+options.id+' .jqgfirstrow').css('height',1);
		$('#gbox_'+options.id+' .jqgfirstrow td[role="gridcell"]').css('border',0);
	}
	$.fn.extendJqgrid=function(options, param){
		var container = this;
		//如果options是字符串，则说明是调用方法
		if (typeof options == 'string') {
			return $.fn.extendJqgrid.methods[options](container, param);
		}
		options=$.extend({},$.fn.extendJqgrid.defaults,options || {});
		return $.fn.extendJqgrid.methods['init'](container, options);
	}
	/**
	*公共方法
	*/
	$.fn.extendJqgrid.methods={
		init:function(container,options){
			constructGrid(container,options);
			initGridStyle(options);
		},
		test:function(container,param){
			alert(param);
		}
	};
	/**
	*初始化参数
	*/
	$.fn.extendJqgrid.defaults = {
		id:"",
		datatype:"local",
		editable:false,
		contextPath:"",
		baseUrl: "",
		autoWidth:false,
		rownumWidth:30,
		sortable:true,
		loadonce:false,
		width:900,
		height: 400,
		page: 1,
		rowNum: 100,
		multiselectWidth:20,
		rowList:[50,100,200],
		columnNames:[],
		columnModel:[],
		multiselect:false,
		shrinkToFit:false,//超出width出滚动条
		forceFit:true,//调整列宽时，调整width总长度
		toppager: true,
		viewrecords:true,
		rownumbers:true,
		pager: "",
		editurl:"",
		loadtext:'loading...',
		loadui:'block',
		pginput:true,
		caption:"",
		eventModels:{},
		adder:{},
		updater:{},
		deleter:{},
		ondblClickRow:function(rowid,iCol,cellcontent,e){
			//editData为添加的参数，是为了让参数能正常的put到后台
			if(this.editable){
				$('#'+this.id).jqGrid('editGridRow',rowid,this.updater);
			}else{
				//dataheight表示内容的高度，它的高度=总高度-标题西栏高度（33）-底部关闭按钮栏(62)-中间内容padding距离(上下各10)-底部边线border（1）
				$('#'+this.id).jqGrid('viewGridRow',rowid,{
					width:600,
					height:400,
					dataheight:(400-33-62-20-1)
				});
			}
		}
	};
})($);