(function($){
	$.SimpleGrid = SimpleGrid;
	function SimpleGrid(options){
		var config = $.extend({},options);
		this.config = config;
		this._init();
	}
	$.extend(SimpleGrid.prototype,{
		_init : function(){
			this.id = this.config.id;
			this.localData = this.config.localData;
			this.loadUrl = this.config.loadUrl||'';
			this.editUrl = this.config.editUrl||'';
			this.gridEditable = this.config.gridEditable||false;
			this.autoHeight = this.config.autoHeight==null?false:this.config.autoHeight;
			this.height  = this.config.height||400;
			this.autoWidth = this.config.autoWidth==null?false:this.config.autoWidth;
			this.width=this.config.width||800;
			this.title=this.config.title||'';
			this.columnModel = this.config.columnModel||{};
			this._create = $.proxy(this._create,this);
			this._addRow = $.proxy(this._addRow,this);
			this._delRow = $.proxy(this._delRow,this);
			this._checkAll = $.proxy(this._checkAll,this);
			this._logInitGridData = $.proxy(this._logInitGridData,this);
			this._getUpdatedData = $.proxy(this._getUpdatedData,this);
			this._loadLocalData = $.proxy(this._loadLocalData,this);
			this._loadUrlData = $.proxy(this._loadUrlData,this);
			this._create();
			//this._loadLocalData();
			this._logInitGridData();
			//this._loadUrlData();
		},
		_loadUrlData : function(url){
			//var url = this.editUrl;
			var loadLocalData = this._loadLocalData;
			$.ajax({
				url:url,
				dataType:"json",
				success:function(json){
					if(json!=null){
						var arr = json.list;
						loadLocalData(arr);
					}
				},
				error:function(){
				}
			});
		},
		_loadLocalData : function(data){
			var addRow = this._addRow;
			if(data!=null){
				$(data).each(function(i,v){
					addRow(v);
				});
			}
		},
		_checkAll : function(){
			var checkedAll = $('.gridCheckboxAll',$("#"+this.id)).attr('checked');
			$('.gridCheckbox',$("#"+this.id)).each(function(i,el){
				$(el).attr('checked',checkedAll=='checked'?true:false);
			});
		},
		_logInitGridData : function(){
			$("tr[class='gridTr']",$("#"+this.id)).each(function(i,v){
				$(".gridCell",v).each(function(j,el){
					var value = $(el).val();
					$(el).attr('_value',value);
				});
			});
		},
		_getUpdatedData : function(){
			var dataArray=new Array();
			$("tr[class='gridTr']",$("#"+this.id)).each(function(i,v){
				var obj = {};
				var updatedFlag = false;
				$(".gridCell",v).each(function(j,el){
					var name = $(el).attr('name');
					var value = $(el).val();
					var _value = $(el).attr('_value');
					obj[name]=value;
					if(value!=_value){
						updatedFlag = true;
					}
				});
				if(updatedFlag){
					dataArray.push(obj);
				}
			});
			var dataObj = {dataList:dataArray};
			alert(JSON.stringify(dataObj));
			return dataObj;
		},
		_create : function(){
			var myheight = '';
			var mywidth='';
			var tableWidth=50;
			if(!this.autoHeight&&this.height>0){
				myheight = "height:"+this.height+"px;";
			}else{
				myheight = "height:100%;";
			}
			if(!this.autoWidth&&this.width>0){
				mywidth = "width:"+this.width+"px;";
			}else{
				mywidth = "width:100%;";
			}
			$(this.columnModel).each(function(i,v){
				if(!v.hidden){
					var colWidth = 100;
					if(v.width>0){
						colWidth = v.width;
					}
					tableWidth+=colWidth;
				}
			});
			var s = "<div id='gridDiv"+this.id+"' class='gridDiv' style='"+mywidth+myheight+"'>";
			s 	 += 	"<table id='gridTable"+this.id+"' class='gridTable' >";
			s 	 += 		"<tr id='gridTableTitle"+this.id+"' class='gridTableTitle'>";
			s 	 += 			"<td>";
			s 	 +=					this.title;
			s 	 += 			"</td>";
			s 	 += 		"</tr>";
			s 	 += 		"<tr id='gridTableHeader"+this.id+"' class='gridTableHeader'>";
			s 	 += 			"<td>";
			s 	 += 				"<table id='gridTableHeaderTable"+this.id+"' class='gridTableHeaderTable'>";
			s 	 += 					"<tr>";
			s 	 += 						"<td>";
			if(this.gridEditable){
				s    += 						"<a href='javascript:void(0)' class='gridAdd' id='gridAdd"+this.id+"'>新增</a>&nbsp;&nbsp;";
				s    += 						"<a href='###' class='gridDel' id='gridDel"+this.id+"'>删除</a>&nbsp;&nbsp;";
				s    += 						"<a href='###' class='gridSave' id='gridSave"+this.id+"'>保存</a>";
			}else{
				s    += 						"&nbsp;";
			}
			s    += 						"</td>";
			s 	 += 					"</tr>";
			s 	 += 				"</table>";
			s 	 += "			</td>";
			s 	 += 		"</tr>";
			s 	 += 		"<tr id='gridTableBody"+this.id+"'>";
			s 	 += 			"<td>";
			s 	 += 				"<div class='gridTableBodyDiv' style='"+myheight+mywidth+"'>";
			s 	 += 					"<table id='gridTableBodyTable"+this.id+"' class='gridTableBodyTable' width='"+tableWidth+"px'>";
			s 	 += 						"<tr style='background:#DFEFFC'>";
			s 	 += 							"<td  width='10px'>";
			s 	 += 								"<input type='checkbox' class='gridCheckboxAll'/>";
			s 	 += 							"</td>";
			$(this.columnModel).each(function(i,v){
				if(!v.hidden){
					var colWidth = "100px";
					if(v.width>0){
						colWidth = v.width+"px";
					}
					s 	 += 					"<td style=\"width:"+colWidth+"\">"+v.name;
					//s 	 += 						"<div>"+v.name+"</div>";
					s 	 += 					"</td>";	
				}
			});
			s 	 += 							"<td>&nbsp;</td>";
			s 	 += 						"</tr>";
			s 	 += 					"</table>";
			s 	 += 				"</div>";
			s 	 += 			"</td>";
			s 	 += 		"</tr>";
			s 	 += 	"</table>";
			s    += "</div>";
			$("#"+this.id).append(s);
			$(".gridAdd",$("#"+this.id)).bind('click',this._addRow);
			$(".gridDel",$("#"+this.id)).bind('click',this._delRow);
			$(".gridSave",$("#"+this.id)).bind('click',this._getUpdatedData);
			$(".gridCheckboxAll",$("#"+this.id)).bind('click',this._checkAll);
		},
		_addRow : function(data){
			var gridEditable = this.gridEditable;
			var tr="<tr class='gridTr'>";
		   	tr+=		"<td width='10px'>";
			tr+=			"<input type='checkbox' class='gridCheckbox' name='gridCheckbox'/>";
			tr+=		"</td>";
			$(this.columnModel).each(function(i,v){
				var value=data[v.id]||'';
				if(!v.hidden){
					var colWidth = "100%;";
					if(v.width>0){
						colWidth = v.width+"px;";
					}
					tr+= "<td width='"+colWidth+"'>";
					tr+= 	"<div class='gridCellDiv' style='width:"+colWidth+"'>";
					
					if(gridEditable&&v.editable){
						tr+= 	"<input type='text' class='gridCell' name='"+v.id+"' value="+value+">";
					}else{
						tr+= 	value;
						tr+= 	"<input type='hidden' class='gridCell' name='"+v.id+"' value="+value+">";
					}
					tr+= 	"</div>";
					tr+= "</td>";	
				}else{
					tr+= "<input type='hidden' class='gridCell' name='"+v.id+"' value="+value+">";
				}
			});
			tr+=	"<td style='border:0px;'>&nbsp;</td>";
		   	tr+="</tr>";
		   	$("#gridTableBodyTable"+this.id).append(tr);
		},
		_delRow : function(){
			var dataArray=new Array();
			var columnModel = this.columnModel;
			$("#gridTableBodyTable"+this.id+" tr[class='gridTr']").each(function(i,v){
				if($(".gridCheckbox",v).attr("checked")=='checked'){
					var obj = {};
					$(".gridCell",v).each(function(j,el){
						var col = columnModel[j];
						var name = col.name;
						var value = $(el).val();
						obj[name]=value;
					});
					dataArray.push(obj);
					$(v).remove();
				}
			});
			var dataObj = {dataList:dataArray};
			return dataObj;
		}
	});
})($);