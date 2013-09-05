(function(W){
	var commonGrid = function(options){
		var config = $.extend({},options);
		this.config = config;
		this._init();
		this._create();
	};
	$.extend(commonGrid.prototype,{
		_init : function(){
			this.baseUrl=this.config.baseUrl||'';
			this.contextPath = this.config.contextPath||'';
			if(this.contextPath!=null&&this.contextPath!=''&&this.baseUrl!=''){
				this.baseUrl = this.contextPath+this.baseUrl;
			}
			this.multiselectWidth=this.config.multiselectWidth||20,
			this.datatype = this.config.datatype;
			this.width = this.config.width||900;
			this.autoWidth = this.config.autoWidth||false;
			this.height = this.config.height||400;
			this.caption=this.config.caption;
			this.columnConfigLength=this.config.columnConfigLength;
			this.id=this.config.id;
			this.columnNames=this.config.columnNames;
			this.columnModel=this.config.columnModel;
			this.add=this.config.add||false,
			this.del=this.config.del||false,
			this.update=this.config.update||false,
			this.refresh=this.config.refresh||false,
			this.editable=this.config.editable||false;
			this.searchable=this.config.searchable||false;
			this.multiselect = this.config.multiselect||false,
			this.rownum = this.config.rownum||100,
			this.pager = this.config.pager||'';
			this.editable = this.config.editable||false;
			this.eventModel = this.config.eventModel||{};
			this.updater={
				editData:{_method:'put'},
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
			this._create = $.proxy(this._create,this);
		},
		_getLength : function(s){
			var c = s.match(/[^\x00-\xff]/ig); 
     		return s.length + (c == null ? 0 : c.length);
		},
		_validateLength : function(value,colname){
			var columnConfigLength = this.config;
			if(columnConfigLength!=null&&this._getLength(value) > columnConfigLength[colname]){
				return [false,colname+"���Ȳ��ܳ���"+col[colname],""];
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
							searchForm+="<tr><td>"+columnNames[i]+"��:</td><td><input type='text' class='searchField' name='"+model['name']+"' id='"+model['name']+"_from' optype='ge' onclick='WdatePicker({dateFmt:\"yyyy-MM-dd\",readOnly:true})'/></td></tr>";
							searchForm+="<tr><td>"+columnNames[i]+"��:</td><td><input type='text' class='searchField' name='"+model['name']+"' id='"+model['name']+"_to' optype='le' onclick='WdatePicker({dateFmt:\"yyyy-MM-dd\",readOnly:true})'/></td></tr>";
						}else{
							searchForm+="<tr><td>"+columnNames[i]+":</td><td><input type='text' class='searchField' name='"+model['name']+"' id='"+model['name']+"' optype='eq'/></td></tr>";
						}
					}
				}
				if(flag){
					searchForm+="<tr><td><input type='hidden' id='gridId' value='"+this.id+"'/></td><td align='right'><input type='button' value='��ѯ' id='custon_search'/></td></tr>";
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
				//(8)��filters����������postDataѡ��  
    			$.extend(postData, {filters: filtersStr});
    			//��Ϊ��ʼ��ʱ���Զ��������ݣ�datatype=local��ֻ�в�ѯʱ��ȥ��̨��ѯ������
    			jQuery("#"+gridId).jqGrid('setGridParam',{datatype:'json',search:true}).trigger("reloadGrid");
			}
		},
		_create : function(){
				var editable = this.editable;
				var updater = this.updater;
				var gridId = this.id;
				jQuery("#"+this.id).jqGrid($.extend({
					url:this.baseUrl,
				   	ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },
				   	datatype:this.datatype,
				   	//datatype:"local",//Ϊ��ʹgrid��ʼ��ʱ����ʾ���ݣ�datatype��Ϊlocal,��ѯ���Ϊjson����̨ȡ��
				    //datatype: 'json',
				    mtype: 'GET',
				    jsonReader:{
				    	repeatitems:false
				    },
					autowidth: this.autoWidth,
				    width:this.width,
				    height:this.height,
				    rownumWidth:35,
				   	colNames:this.columnNames,
				   	colModel:this.columnModel,
				   	shrinkToFit:false,//����width��������
				   	forceFit:true,//�����п�ʱ������width�ܳ���
				   	pginput:false,
				   	pager:this.pager,
				   	toppager: true,
				    viewrecords:true,
				    rownumbers:true,
				    rownumWidth:40,
				    multiselectWidth:this.multiselectWidth,
				    loadtext:'loading...',
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
				    rowNum:this.rownum,
				    caption:this.caption,
					ondblClickRow:function(rowid,iCol,cellcontent,e){
						//editDataΪ��ӵĲ�������Ϊ���ò�����������put����̨
				    	if(editable==true){
				    		$('#'+gridId).jqGrid('editGridRow',rowid,updater);
				    	}else{
				    		//$('#'+gridId).jqGrid('viewGridRow',rowid,{width:400,viewPagerButtons:false});
				    	}
				    }
			},this.eventModel));
			jQuery("#"+this.id).jqGrid('navGrid','',{edit:false,cloneToTop:true},{},this.adder,this.deleter,{multipleSearch:true, multipleGroup:false, showQuery: false});
			var topPagerDiv = $("#"+this.id+"_toppager")[0];
			$("#edit_"+this.id+"_top", topPagerDiv).remove();
			$("#"+this.id+"_toppager_center", topPagerDiv).remove();
			$(".ui-paging-info", topPagerDiv).remove();
			$("#search_"+this.id+"_top", topPagerDiv).remove();
			$('.ui-jqgrid-titlebar-close.HeaderButton').remove();
			if(!this.editable){
				$("#refresh_"+this.id+"_top", topPagerDiv).remove();
				$("#add_"+this.id+"_top", topPagerDiv).remove();
				$("#del_"+this.id+"_top", topPagerDiv).remove();
			}else{
				if(!this.refresh){
					$("#refresh_"+this.id+"_top", topPagerDiv).remove();
				}
				if(!this.add){
					$("#add_"+this.id+"_top", topPagerDiv).remove();
				}
				if(!this.del){
					$("#del_"+this.id+"_top", topPagerDiv).remove();
				}
			}
			//*************��ʼ����ѯ����*****************
			/*
			var html = this._initSearchForm();
			jQuery("#gbox_"+this.id).before(html);
			if($('#custon_search')){
				$('#custon_search').bind('click',this._custom_search);
			}*/
		}
	});
	W.commonGrid=commonGrid;
})(window)