var ddshop = {

    registerMenuEvent: function () {

        //约定大于配置：jquery对象前面加上$,如果是DOM对象不需要加$
        var $tree = $('#menu .easyui-tree');
        //将当前树打印到控制台
        //console.log($tree);
        $tree.tree({
            onClick: function (node) {
                var href = node.attributes.href;//item-add
                var text = node.text;

//                debugger;
                if ($('#tab').tabs('exists', text)) {
                    //跳转到指定的选项卡页面
                    $('#tab').tabs('select', text);

                }
                else {
                    $('#tab').tabs('add', {

                        title: text,
                        href: href,
                        closable: true

                    });

                }

            }
        });

    }

};


var itemList = {

    registerMenuEvent: function () {

        $("#table").datagrid({
            toolbar:[{

                text: '新增',
                handler: function () {
                    console.log('add');
                }
            },{

                text: '删除',
                handler: function () {

                    var selectRows = $('#table').datagrid('getSelections');
                    if(selectRows.length == 0){
                        $.messager.alert('提示','未选中记录','warning');
                        return;
                    }
                    $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
                        if (r){
                            //获取用户选中的记录
                            var ids = [];
                            for(var i=0;i< selectRows.length;i++){
                                ids.push(selectRows[i].id);
                            }
                            //异步提交给后台
                            $.ajax({
                                url:"items/batch",
                                type:"post",
                                data:{"ids[]":ids},
                                success:function(data){
                                    if(data!=null){
                                        $('#table').datagrid('reload');
                                    }

                                },
                                dataType:"json"
                            });

                        }
                    });

                }
            },{

                text: '编辑',
                handler: function () {
                    console.log('update');
                }
            },{
                iconCls: 'icon-up',
                text: '上架',
                handler: function () {
                    console.log('up');
                }
            },{
                iconCls: 'icon-down',
                text: '下架',
                handler: function () {
                    console.log('down');
                }
            }],
            url: "items",
            columns: [[
                {field:'ck',checkbox: true},
                {field: 'id', title: '商品ID'},
                {field: 'title', title: '商品名称'},
                {field: 'catName', title: '商品分类'},
                {field: 'statusName', title: '商品状态'},
                {field: 'sellPoint', title: '卖点'}
            ]],
            pagination: true,
            striped: true,
            pageSize:20,
            rownumbers:true,
            fit:true,
            loadMsg: '数据正在努力加载，请稍后...'
        })


    }


};







