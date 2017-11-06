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
            url: "items",
            columns: [[
                {field: 'id', title: '商品ID'},
                {field: 'title', title: '商品名称'},
                {field: 'sellPoint', title: '卖点'}
            ]],
            pagination: true,
            pagePosition: 'top',
            striped: true,
            pageSize: 8,
            nowrap: false,
            loadMsg: '数据正在努力加载，请稍后...'



        })


    }


};



