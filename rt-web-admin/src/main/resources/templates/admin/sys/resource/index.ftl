<!DOCTYPE html>
<html>
<head>
    <title>管理员列表</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    [#include "/commons/include_style.ftl"]
    <style>
        .btn-outline {
             border: 1px solid #ddd;
            background: transparent;
             color: #444;
        }
    </style>
<body>
<!-- Main content -->
<section class="content" >

    <div class="row">
        <div class="col-xs-12">
            <div class="box" >
                <div class="box-header with-border">
                    <form class="form-inline" id="form">

                    </form>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div id="toolbar" class="btn-group">
                          [@shiro.hasPermission name="system:resource:add"]
                              <button class="btn btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;Add
                              </button>
                          [/@shiro.hasPermission]
                    </div>
                    <table class="table" id="table">
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
</section>
</body>
[#include "/commons/include_js.ftl"]
<link rel="stylesheet" href="${ctx!}/assets/plugins/bootstrap-treetable/bootstrap-treetable.css" type="text/css" />
<script type="text/javascript" src="${ctx!}/assets/plugins/bootstrap-treetable/bootstrap-treetable.js"></script>
<!-- Page-Level Scripts -->
<script>
    var $table = $('#table');
    $(function () {
        //1.初始化Table
        var oTable = new TableInit();
        oTable.init();
    });
    var TableInit = function () {
        var oTableInit = new Object();
        var table;
        //初始化Table
        oTableInit.init = function () {
            $table = $('#table').bootstrapTreeTable({
                // toolbar: "#demo-toolbar",    //顶部工具条
                expandColumn : 1,            // 在哪一列上面显示展开按钮
                // height:500,
                url: '${ctx!}/admin/sys/resource/list',
                columns: [
                    {
                        field: 'ck',
                        checkbox: true
                    },
                    {
                        field: 'name',
                        title: 'name'
                    },
                    {
                        field: 'description',
                        title: 'description'
                    },
                    {
                        field: 'sort',
                        title: 'sort',
                        width: '200',
                        align:"left"
                    },
                    {
                        field: 'type',
                        title: 'type',
                        sortable: false,
                        width: '200',
                        align:"left",
                        formatter: function(value, row, index){
                            if (row.type === 0) {
                                return 'Directory'
                            }
                            if (row.type === 1) {
                                return 'Menu'
                            }
                            if (row.type === 2) {
                                return 'Button'
                            }
                            return '-'
                        }
                    },
                    {
                        field: 'sourceUrl',
                        title: 'sourceUrl'
                    },
                    {
                        field: 'sourceKey',
                        title: 'sourceKey'
                    },
                    {
                        field: 'operation',
                        title: 'operation',
                        width: '200',
                        align: "center",
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-xs" href="#" mce_href="#" title="edit" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i>edit</a> ';
                            var d = '<a class="btn btn-danger btn-xs" href="#" mce_href="#" title="delete" onclick="del(\''+ row.id+ '\')"><i class="fa fa-remove"></i>delete</a> ';
                            return e + d;
                        }
                    }
                ],
                onAll: function(data) {
                    console.log("onAll");
                    return false;
                },
                onLoadSuccess: function(data) {
                    console.log("onLoadSuccess");
                    return false;
                },
                onLoadError: function(status) {
                    console.log("onLoadError");
                    return false;
                },
                onClickCell: function(field, value, row, $element) {
                    console.log("onClickCell",row);
                    return false;
                },
                onDblClickCell: function(field, value, row, $element) {
                    console.log("onDblClickCell",row);
                    return false;
                },
                onClickRow: function(row, $element) {
                    console.log("onClickRow",row);
                    return false;
                },
                onDblClickRow: function(row, $element) {
                    console.log("onDblClickRow",row);
                    return false;
                }
            });
        }
        return oTableInit;
    }


    function refresh(){
        $table.bootstrapTreeTable('refresh');
    }

    function edit(id) {
        layer.open({
            type: 2,
            title: 'Edit Resource',
            shadeClose: false, //点击遮罩关闭层
            area: ['800px', '600px'],
            content: '${ctx}/admin/sys/resource/update?id='+id
        });
    }
    function add() {
        layer.open({
            type: 2,
            title: 'Add Resource',
            shadeClose: false, //点击遮罩关闭层
            area: ['800px', ($(this).height()-40)+'px'],
            content: '${ctx}/admin/sys/resource/add'
        });
    }
    function del(id) {
        layer.confirm('please confirm', {btn: ['ok','cancel'] }, function(index){
            layer.close(index);
            $.ajax({
                url: "${ctx!}/admin/sys/resource/delete",
                data:{"id":id},
                type: "Post",
                success: function (data) {
                    if (data.code==0) {
                        refresh();
                        layer.alert('delete successfully');
                    }else{
                        layer.alert(data.msg);
                    }
                }
            });
        });
    }
</script>
</html>

