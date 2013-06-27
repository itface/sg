Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '${ctx}/script/extjs3.4/resources/images/default/tree/s.gif';
    var myTree = new Ext.tree.TreePanel({
        renderTo:'front_index_center_left',
        rootVisible:false,
        width:"100%",
		height:"100%",
		border:false,
        autoScroll:true,
		animate :false,
        tbar:new Ext.Toolbar(),
        root:new Ext.tree.AsyncTreeNode({
                children: [{
                    id: 'level',
                    text: '用户类型',
                    children: [{
                        id: 'allLevel',
                        text: '全部',
                        leaf: true
                    }, {
                        id: 'noSupport',
                        text: '无支持',
                        leaf: false,
						children:[
							{
								id: 'allLevel2',
								text: '无支持2',
								leaf: false,
								children:[
									{
										id: 'allLevel3',
										text: '无支持3',
										leaf: false,
										children:[
											{
												id: 'allLevel4',
												text: '无支持4',
												leaf: false,
												children:[
													{
														id: 'allLevel5',
														text: '无支持5',
														leaf: false,
														children:[
															{
																id: 'allLevel6',
																text: '无支持6',
																leaf: false,
																children:[
																	{
																		id: 'allLevel7',
																		text: '无支持77777777777777777777777777',
																		leaf: false,
																		children:[
																			{
																				id: 'allLevel8',
																				text: '无支持8',
																				leaf: false,
																				children:[
																					{
																						id: 'allLevel9',
																						text: '无支持77777777777777777777777777',
																						leaf: true
																						
																					}
																				]
																			}
																		]
																	}
																]
															}
														]
													}
												]
											}
										]
									}
								]
							}
						]
                    }, {
                        id: 'month',
                        text: '包月',
                        leaf: true
                    }, {
                        id: 'year',
                        text: '包年',
                        leaf: true
                    }, {
                        id: 'always',
                        text: '终身',
                        leaf: true
                    }]
                }, {
                    id: 'outOfDate',
                    text: '是否过期',
                    children: [{
                        id: 'allOutOfDate',
                        text: '全部',
                        leaf: true
                    }, {
                        id: 'notOutOfDate',
                        text: '未过期',
                        leaf: true
                    }, {
                        id: 'alreadyOutOfDate',
                        text: '已过期',
                        leaf: true
                    }]
                },{
                    id: 'report3',
                    text: '统计图表3',
                    children: [{
                        id: 'levelReport3',
                        text: '按用户类型3',
                        leaf: true
                    }, {
                        id: 'outOfDateReport3',
                        text: '按是否过期3',
                        leaf: true
                    }]
                },{
                    id: 'report4',
                    text: '统计图表4',
                    children: [{
                        id: 'levelReport4',
                        text: '按用户类型4',
                        leaf: true
                    }, {
                        id: 'outOfDateReport4',
                        text: '按是否过期4',
                        leaf: true
                    }]
                },{
                    id: 'report5',
                    text: '统计图表5',
                    children: [{
                        id: 'levelReport5',
                        text: '按用户类型5',
                        leaf: true
                    }, {
                        id: 'outOfDateReport5',
                        text: '按是否过期5',
                        leaf: true
                    }]
                },{
                    id: 'report6',
                    text: '统计图表6',
                    children: [{
                        id: 'levelReport6',
                        text: '按用户类型6',
                        leaf: true
                    }, {
                        id: 'outOfDateReport6',
                        text: '按是否过期6',
                        leaf: true
                    }]
                },{
                    id: 'report7',
                    text: '统计图表7',
                    children: [{
                        id: 'levelReport7',
                        text: '按用户类型7',
                        leaf: true
                    }, {
                        id: 'outOfDateReport7',
                        text: '按是否过期7',
                        leaf: true
                    }]
                }, {
                    id: 'report',
                    text: '统计图表',
                    children: [{
                        id: 'levelReport',
                        text: '按用户类型',
                        leaf: true
                    }, {
                        id: 'outOfDateReport',
                        text: '按是否过期',
                        leaf: true
                    }]
                }]
            })
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
    myTree.expandAll();
    var tbar = myTree.getTopToolbar();
    tbar.add(filterFiled);
    tbar.doLayout();
    //Ext.getDom('<input id="test" name="test"/>')
});
