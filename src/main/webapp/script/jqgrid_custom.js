(function(W){
	var customGrid = function(options){
		var config = $.extend({},options);
		this.config = config;
		this._init();
		this._create();
	};
	$.extend(customGrid.prototype,{
		_init : function(){
			this.baseUrl=this.config.baseUrl||'';
			this.contextPath = this.config.contextPath;
			if(this.contextPath!=null&&this.contextPath!=''&&this.baseUrl!=''){
				this.baseUrl = this.contextPath+this.baseUrl;
			}
			this.width = this.config.width||900;
			this.height = this.config.height||400;
			this.caption=this.config.caption;
			this.columnConfigLength=this.config.columnConfigLength;
			this.id=this.config.id;
			this.columnNames=this.config.columnNames;
			this.columnModel=this.config.columnModel;
			this.editable=this.config.editable;
			this.searchable=this.config.searchable||false;
			this.multiselect = this.config.multiselect||false,
			this.updater={
				editData:{_method:'put'},
				top:200,
				left:400,
				width:400,
				reloadAfterSubmit:true,
				modal:true,
				recreateForm:true,
				mtype: "POST",
				url: this.baseUrl,
				viewPagerButtons:false
			};
			this.adder={
				mtype: "POST",
				top:200,
				left:400,
				width:400,
				recreateForm:true,
				closeAfterAdd:true,
				reloadAfterSubmit:true,
				clearAfterAdd:true,
				modal:true,
				url:this.baseUrl,
				viewPagerButtons:false
			};
			this.deleter={
				url:this.baseUrl,
				reloadAfterSubmit:true,
				jqModal:false
			};
			$.extend(this.adder,this.config.adder||{});
			$.extend(this.updater,this.config.updater||{});
			$.extend(this.deleter,this.config.deleter||{});
		},
		_getLength : function(s){
			var c = s.match(/[^\x00-\xff]/ig); 
     		return s.length + (c == null ? 0 : c.length);
		},
		_validateLength : function(value,colname){
			var columnConfigLength = this.config;
			if(columnConfigLength!=null&&this._getLength(value) > columnConfigLength[colname]){
				return [false,colname+"长度不能超过"+col[colname],""];
			}else{
				return [true,"",""];
			}
		},
		_initSearchForm : function(){
			var searchForm='<div><table style="100%">';
			if(this.columnModel){
				var columnModel=this.columnModel;
				var columnNames=this.columnNames;
				var flag = false;
				for(var i=0;i<columnModel.length;i++){
					var model = columnModel[i];
					if(model['search']==true||model['search']=='true'){
						flag=true;
						var edittype = model['edittype'];
						if('Date'==edittype||'date'==edittype||'DATE'==edittype){
							searchForm+="<tr><td>"+columnNames[i]+"从:</td><td><input type='text' class='searchField' name='"+model['name']+"' id='"+model['name']+"_from' optype='ge' onclick='WdatePicker({dateFmt:\"yyyy-MM-dd\",readOnly:true})'/></td></tr>";
							searchForm+="<tr><td>"+columnNames[i]+"到:</td><td><input type='text' class='searchField' name='"+model['name']+"' id='"+model['name']+"_to' optype='le' onclick='WdatePicker({dateFmt:\"yyyy-MM-dd\",readOnly:true})'/></td></tr>";
						}else{
							searchForm+="<tr><td>"+columnNames[i]+":</td><td><input type='text' class='searchField' name='"+model['name']+"' id='"+model['name']+"' optype='eq'/></td></tr>";
						}
					}
				}
				if(flag){
					searchForm+="<tr><td><input type='hidden' id='gridId' value='"+this.id+"'/></td><td align='right'><input type='button' value='查询' id='custon_search'/></td></tr>";
				}
			}
			searchForm+='</table></div>';
			return searchForm;
		},
		_custom_search : function(){
			if($('.searchField')){
				var arr = $('.searchField');
				var gridId=$('#gridId').val();
				var rules = ""; 
				for(var i=0;i<arr.length;i++){
					var id = $(arr[i]).attr('name');
					var optype = $(arr[i]).attr('optype');
					var value = $(arr[i]).val();
					 rules += ',{"field":"' + id + '","op":"'+optype+'","data":"' + value + '"}';
				}
				rules = rules.substring(1);
				var filtersStr = '{"groupOp":"AND","rules":[' + rules + ']}';
				var postData = $("#"+gridId).jqGrid("getGridParam", "postData");
				//(8)将filters参数串加入postData选项  
    			$.extend(postData, {filters: filtersStr});
    			//因为初始化时不自动加载数据，datatype=local，只有查询时会去后台查询出数据
    			jQuery("#"+gridId).jqGrid('setGridParam',{datatype:'json',search:true}).trigger("reloadGrid");
			}
		},
		_create : function(){
				jQuery("#"+this.id).jqGrid({
					url:this.baseUrl,
				   	ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },
				   	datatype:"local",//为了使grid初始化时不显示数据，datatype设为local,查询设改为json到后台取数
				    //datatype: 'json',
				    mtype: 'GET',
				    jsonReader:{
				    	repeatitems:false
				    },
					autowidth: true,
				    //width:this.width,
				    height:this.height,
				   	colNames:this.columnNames,
				   	colModel:this.columnModel,
				   	shrinkToFit:false,//超出width出滚动条
				   	forceFit:true,//调整列宽时，调整width总长度
				   	pginput:false,
				   	toppager: true,
				    viewrecords:true,
				    rownumbers:true,
				    rownumWidth:40,
				    loadtext:'正在加载...',
				    loadui:'block',
				    sortable:true,
				    multiselect: this.multiselect,
				    loadError : function(xhr,st,err) {
				    	//alert(xhr.status);
				    	//jQuery("#rsperror").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText);
				    },
				    search:{groupOps: [ {op: "AND", text: "all"}]},
				   	//sortname: 'opttime',
				    //sortorder: "asc",
				    //pager : "#tools",
				    editurl:'',
				    rowNum:'100000',//不分页
				    caption:this.caption,
					ondblClickRow:function(rowid,iCol,cellcontent,e){
				    	//editData为添加的参数，是为了让参数能正常的put到后台
				    	if(this.editable){
				    		$('#'+this.id).jqGrid('editGridRow',rowid,this.updater);
				    	}else{
				    		$('#'+this.id).jqGrid('viewGridRow',rowid,{left:40,top:10,width:600});
				    	}
				    }
			});
			jQuery("#"+this.id).jqGrid('navGrid','',{edit:false,cloneToTop:true},{},this.adder,this.deleter,{multipleSearch:true, multipleGroup:false, showQuery: false});
			var topPagerDiv = $("#"+this.id+"_toppager")[0];
			$("#edit_"+this.id+"_top", topPagerDiv).remove();
			$("#"+this.id+"_toppager_center", topPagerDiv).remove();
			$(".ui-paging-info", topPagerDiv).remove();
			$("#refresh_"+this.id+"_top", topPagerDiv).remove();
			$("#search_"+this.id+"_top", topPagerDiv).remove();
			$("#add_"+this.id+"_top", topPagerDiv).remove();
			$("#del_"+this.id+"_top", topPagerDiv).remove();
			//*************初始化查询条件*****************
			var html = this._initSearchForm();
			jQuery("#gbox_"+this.id).before(html);
			if($('#custon_search')){
				$('#custon_search').bind('click',this._custom_search);
			}
			//jQuery("#hideshow").jqGrid('hideCol',["amount","tax"]);
		}
	});
	W.customGrid=customGrid;
})(window)