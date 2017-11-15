var ddshop = {

    registerMenuEvent: function () {
        var _this=this;
        //约定大于配置：jquery对象前面加上$,如果是DOM对象不需要加$
        var $tree = $('#menu .easyui-tree');
        //将当前树打印到控制台
        //console.log($tree);
        $tree.tree({
            onClick: function (node) {
                var href = node.attributes.href;//item-add
                var text = node.text;
                _this.addtabs(text,href);

//                debugger;


            }
        });

    },

    addtabs:function(text,href){

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
    },

    closetabs:function(text){
        $('#tab').tabs('close',text);
    }




};


var itemList = {

    registerMenuEvent: function () {

        $("#table").datagrid({
            toolbar: '#toolbar',
            /*toolbar:[{
                iconCls: 'icon-add',
                text: '新增',
                handler: function () {
                    console.log('add');
                }
            },{
                iconCls: 'icon-remove',
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
                iconCls: 'icon-edit',
                text: '编辑',
                handler: function () {
                    console.log('update');
                }
            },{
                iconCls: 'icon-up',
                text: '上架',
                handler: function () {


                    var selectRows=$("#table").datagrid("getSelections");
                    if(selectRows.length==0){
                        $.messager.alert("提示","未选中任何列","warning");
                        return;
                    }
                    $.messager.confirm("确认","确认要上架吗",function(r){
                        if(r){
                            var ids=[];
                            for (var i=0;i<selectRows.length;i++){
                                ids.push(selectRows[i].id);
                            }
                            $.post(
                                //url:请求后台的哪个地址来进行处理，相当于url,String类型
                                'items/batch/up',
                                //data:从前台提交哪些数据给后台处理，相当于data，Object类型
                                {'ids[]':ids},
                                //callback:后台处理成功的回调函数，相当于success，function类型
                                function(data){
                                    $("#table").datagrid("reload");
                                },
                                //dataType:返回的数据类型，如：json，String类型
                                'json'
                            )
                            // $.ajax({
                            //     url:"items/batch/down",
                            //     data:{'ids[]':ids},
                            //     type:"post",
                            //     dataType:"json",
                            //     success:function (data) {
                            //         if(data>=0){
                            //             $("#table").datagrid("reload");
                            //         }
                            //     }
                            // });
                        }
                    })


                }



            },{
                iconCls: 'icon-down',
                text: '下架',
                handler: function () {

                    var selectRows=$("#table").datagrid("getSelections");
                    if(selectRows.length==0){
                        $.messager.alert("提示","未选中任何列","warning");
                        return;
                    }
                    $.messager.confirm("确认","确认要下架吗",function(r){
                        if(r){
                            var ids=[];
                            for (var i=0;i<selectRows.length;i++){
                                ids.push(selectRows[i].id);
                            }
                            $.post(
                                //url:请求后台的哪个地址来进行处理，相当于url,String类型
                                'items/batch/down',
                                //data:从前台提交哪些数据给后台处理，相当于data，Object类型
                                {'ids[]':ids},
                                //callback:后台处理成功的回调函数，相当于success，function类型
                                function(data){
                                    $("#table").datagrid("reload");
                                },
                                //dataType:返回的数据类型，如：json，String类型
                                'json'
                            )
                            // $.ajax({
                            //     url:"items/batch/down",
                            //     data:{'ids[]':ids},
                            //     type:"post",
                            //     dataType:"json",
                            //     success:function (data) {
                            //         if(data>=0){
                            //             $("#table").datagrid("reload");
                            //         }
                            //     }
                            // });
                        }
                    })


                }
            }],*/
            url: "items",
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: '商品ID', sortable: true},
                {field: 'title', title: '商品名称', width: "200", sortable: true},
                {field: 'catName', title: '商品分类'},
                {field: 'statusName', title: '商品状态'},
                {field: 'sellPoint', title: '卖点', width: "200"},
                {
                    field: 'price', title: '商品价格', formatter: function (value, row, index) {

                    return '￥' + (value / 100).toFixed(2).replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');

                }
                },
                {
                    field: 'created', title: '创建时间', formatter: function (value, row, index) {
                    return moment(value).format("LL");
                }
                },
                {
                    field: 'updated', title: '更新时间', formatter: function (value, row, index) {
                    return moment(value).format("LL");
                }
                }
            ]],
            pagination: true,
            striped: true,
            multiSort: true,
            pageSize: 20,
            rownumbers: true,
            fit: true,
            loadMsg: '数据正在努力加载，请稍后...'
        })


    }


};

/**
 * 删除(修改商品状态为3)
 */

function remove() {

    var selectRows = $('#table').datagrid('getSelections');
    if (selectRows.length == 0) {
        $.messager.alert('提示', '未选中记录', 'warning');
        return;
    }
    $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
        if (r) {
            //获取用户选中的记录
            var ids = [];
            for (var i = 0; i < selectRows.length; i++) {
                ids.push(selectRows[i].id);
            }
            //异步提交给后台
            $.ajax({
                url: "items/batch",
                type: "post",
                data: {"ids[]": ids},
                success: function (data) {
                    if (data != null) {
                        $('#table').datagrid('reload');
                    }

                },
                dataType: "json"
            });

        }
    });

};

/**
 * 上架
 */

function up() {

    var selectRows = $("#table").datagrid("getSelections");
    if (selectRows.length == 0) {
        $.messager.alert("提示", "未选中任何列", "warning");
        return;
    }
    $.messager.confirm("确认", "确认要上架吗", function (r) {
        if (r) {
            var ids = [];
            for (var i = 0; i < selectRows.length; i++) {
                ids.push(selectRows[i].id);
            }
            $.post(
                //url:请求后台的哪个地址来进行处理，相当于url,String类型
                'items/batch/up',
                //data:从前台提交哪些数据给后台处理，相当于data，Object类型
                {'ids[]': ids},
                //callback:后台处理成功的回调函数，相当于success，function类型
                function (data) {
                    $("#table").datagrid("reload");
                },
                //dataType:返回的数据类型，如：json，String类型
                'json'
            )
            // $.ajax({
            //     url:"items/batch/down",
            //     data:{'ids[]':ids},
            //     type:"post",
            //     dataType:"json",
            //     success:function (data) {
            //         if(data>=0){
            //             $("#table").datagrid("reload");
            //         }
            //     }
            // });
        }
    })

};

/**
 * 下架
 */
function down() {

    var selectRows = $("#table").datagrid("getSelections");
    if (selectRows.length == 0) {
        $.messager.alert("提示", "未选中任何列", "warning");
        return;
    }
    $.messager.confirm("确认", "确认要下架吗", function (r) {
        if (r) {
            var ids = [];
            for (var i = 0; i < selectRows.length; i++) {
                ids.push(selectRows[i].id);
            }
            $.post(
                //url:请求后台的哪个地址来进行处理，相当于url,String类型
                'items/batch/down',
                //data:从前台提交哪些数据给后台处理，相当于data，Object类型
                {'ids[]': ids},
                //callback:后台处理成功的回调函数，相当于success，function类型
                function (data) {
                    $("#table").datagrid("reload");
                },
                //dataType:返回的数据类型，如：json，String类型
                'json'
            )
            // $.ajax({
            //     url:"items/batch/down",
            //     data:{'ids[]':ids},
            //     type:"post",
            //     dataType:"json",
            //     success:function (data) {
            //         if(data>=0){
            //             $("#table").datagrid("reload");
            //         }
            //     }
            // });
        }
    });
};

/**
 * 查询
 */
function searchForm() {

    $("#table").datagrid('load',{
        title:$("#title").textbox('getValue'),
        status:$("#status").combobox('getValue')
    })

}

/**
 * 添加
 */
function add(){
    ddshop.addtabs("新增商品","item-add");
}


/**
 *
 */





