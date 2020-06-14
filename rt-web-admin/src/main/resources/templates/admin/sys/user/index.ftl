<!DOCTYPE html>
<html>
<head>
    <title>Admin List</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    [#include "/commons/include_style.ftl"]
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
                          [@shiro.hasPermission name="system:user:add"]
                            <button class="btn btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;Add
                            </button>
                          [/@shiro.hasPermission]
                    </div>
                    <table id="table"  class="table table-bordered">
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
</section>
</body>
 [#include "/commons/include_js.ftl"]
<!-- Page-Level Scripts -->

<script>
    var $table = $('#table');
    $(function () {
        //1.初始化Table
        var oTable = new TableInit();
        oTable.init();

        //2.初始化Button的点击事件
        var oButtonInit = new ButtonInit();
        oButtonInit.init();

    });


    var TableInit = function () {
        var oTableInit = new Object();
        var table;
        //初始化Table
        oTableInit.init = function () {
            $table.bootstrapTable({
                url: '${ctx}/admin/sys/user/list',         //请求后台的URL（*）
                method: 'post',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                // striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: false,                     //是否启用排序
                // contentType : "application/x-www-form-urlencoded",
                sortOrder: "asc",                   //排序方式
                queryParams: oTableInit.queryParams,//传递参数（*）
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber:1,                       //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                showColumns: true,                  //是否显示所有的列
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                // clickToSelect: true,                //是否启用点击选中行
                // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                // search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                // showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
                // strictSearch: true,
                // cardView: false,                    //是否显示详细视图
                // detailView: false,                   //是否显示父子表
                columns: [{
                    checkbox: true
                }, {
                    field: 'username',
                    title: 'username'
                }, {
                    field: 'nickname',
                    title: 'nickname'
                }, {
                    field : 'sex',
                    title : 'sex',
                    width:70,
                    align:"left",
                    formatter:function (value, row, index) {
                        if(row.sex==0){
                            return "female";
                        }else if(row.sex==1){
                            return "male";
                        } else {
                            return "";
                        }
                    }
                }, {
                    field : 'locked',
                    title : 'status',
                    width:70,
                    align:"left",
                    formatter:function (value, row, index) {
                        if(row.locked == 0){
                            return 'normal';
                        }else if(row.locked == 1){
                            return 'locked';
                        }
                    }
                }, {
                    field: 'createTime',
                    title: 'create time',
                    formatter : function(value, row, index) {
                        return dateFormat(value) ;
                    }
                },
                    {
                        field: 'operation',
                        title: 'operation',
                        width: 150,
                        align: "center",
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-xs" href="#" mce_href="#" title="edit" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i>edit</a> ';
                            var d = '<a class="btn btn-danger btn-xs" href="#" mce_href="#" title="delete" onclick="del(\''+ row.id+ '\')"><i class="fa fa-remove"></i>delete</a> ';
                            return e + d;
                        }
                    }]
            });
        };

        //得到查询的参数
        oTableInit.queryParams = function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                // limit: params.limit,   //页面大小
                // offset: params.offset,  //页码
                pageNumber : this.pageNumber,
                pageSize : this.pageSize,
                condition:{
                    name: $("#txt_search_departmentname").val(),
                    statu: $("#txt_search_statu").val()
                }
            };
            return temp;
        };
        return oTableInit;
    };


    var ButtonInit = function () {
        var oInit = new Object();
        var postdata = {};
        oInit.init = function () {
            //初始化页面上面的按钮事件
        };
        return oInit;
    };


    function refresh(){
        $table.bootstrapTable('refresh');
    }

    function edit(id) {
        layer.open({
            type: 2,
            title: '修改用户',
            shadeClose: false, //点击遮罩关闭层
            area: ['800px', ($(this).height()-60)+'px'],
            content: '${ctx}/admin/sys/user/update?id='+id
        });
    }
    function add() {
        layer.open({
            type: 2,
            title: '添加用户',
            shadeClose: false, //点击遮罩关闭层
            area: ['800px', ($(this).height()-60)+'px'],
            content: '${ctx}/admin/sys/user/add'
        });
    }
    function grant(id) {
        layer.open({
            type: 2,
            title: '用户授权',
            shadeClose: false, //点击遮罩关闭层
            area: ['800px', '400px'],
            content: '${ctx!}/admin/user/grant/' + id
        });
    }
    function del(id) {
        layer.confirm('please confirm', {btn: ['ok','cancel'] }, function(index){
            layer.close(index);
            $.ajax({
                url: "${ctx!}/admin/sys/user/delete",
                data:{"id":id},
                type: "Post",
                success: function (data) {
                    if (data.code==0) {
                        refresh();
                        layer.alert('Delete Successfully');
                    }else{
                        layer.alert(data.msg);
                    }
                }
            });
        });
    }

</script>
</html>