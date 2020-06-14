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
                              <button class="btn btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加
                              </button>
                          [/@shiro.hasPermission]
                    </div>
                    <table class="table table-bordered" id="table">
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
</section>
</body>
[#include "/commons/include_js.ftl"]
<link rel="stylesheet" type="text/css" href="${ctx!}/assets/js/easyui/themes/metro/easyui.css">
<script type="text/javascript" src="${ctx!}/assets/js/easyui/jquery.easyui.min.js"></script>
<!-- Page-Level Scripts -->
<script>
    var $dg;
    $(function() {
        $dg = $("#table");
        $dg.treegrid({
            url : '${ctx}/admin/sys/resource/list',
            width : 'auto',
            height : $(this).height()-80,
            method : "GET",
            rownumbers:true,
            fitColumns: true,
            border:false,
            cascadeCheck:true,
            idField: 'id',
            treeField: 'text',
            parentField : 'parentId',
            loadFilter: function (data, parent) {
                var opt = $(this).data().treegrid.options;
                return convert(data);
            },
            columns : [ [
                {field : 'text',title : '地区名称',width : parseInt($(this).width()*0.2)},
                {field : 'sourceKey',title : '汇率名称',width : 100
                },
                {field : 'parentId',title : '汇率',width : parseInt($(this).width()*0.2)
                },
                {
                    field: 'operation',
                    title: '操作',
                    width: 200,
                    align: "center",
                    formatter: function (value, row, index) {
                        var e = '<a class="btn btn-primary btn-xs" href="#" mce_href="#" title="编辑" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i>编辑</a> ';
                        var d = '<a class="btn btn-danger btn-xs" href="#" mce_href="#" title="删除" onclick="del(\''+ row.id+ '\')"><i class="fa fa-remove"></i>删除</a> ';
                        return e + d;
                    }
                }
            ] ]
        });
    });

</script>
</html>

