<!DOCTYPE html>
<html>
<head>
    <title>Parking Lot Management</title>
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
                        <label>无人机名称:</label>&nbsp;&nbsp;
                        <input style="width:180px" type="text" name="uavName" id="uavName" placeholder="uavName" class="input-small form-control">&nbsp;&nbsp;
                        <label>无人机编码:</label>&nbsp;&nbsp;
                        <input style="width:180px" type="text" name="uavCode" id="uavCode" placeholder="uavCode" class="input-small form-control">&nbsp;&nbsp;
                        <label>无人机型号:</label>&nbsp;&nbsp;
                        <input style="width:180px" type="text" name="uavType" id="uavType" placeholder="uavType" class="input-small form-control">&nbsp;&nbsp;
                        <label>状态:</label>&nbsp;&nbsp;
                        <select id="status" name="status" class="form-control">
                            <option value="">所有</option>
                            <option value="1">占用</option>
                            <option value="0">空闲</option>
                        </select>
                        &nbsp;&nbsp;
                        <button type="button" onclick="refresh();" class="btn btn-primary ">search</button>
                    </form>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div id="toolbar" class="btn-group">
                        <button class="btn btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加无人机
                        </button>
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

    var ButtonInit = function () {
        var oInit = new Object();
        var postdata = {};
        oInit.init = function () {
            //初始化页面上面的按钮事件
        };
        return oInit;
    };

    var TableInit = function () {
        var oTableInit = new Object();
        var table;
        //初始化Table
        oTableInit.init = function () {
            $table.bootstrapTable({
                url: '${ctx}/admin/uav/list',         //请求后台的URL（*）
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
                columns: [
                    {
                        field: 'uavName',
                        title: '无人机名称'
                    },
                    {
                        field: 'uavCode',
                        title: '无人机编码'
                    },
                    {
                        field: 'uavType',
                        title: '无人机型号'
                    },
                    {
                        field: 'uavWeight',
                        title: '无人机负载重量(kg)'
                    },
                    {
                        field: 'uavPower',
                        title: '无人机电量'
                    },
                    {
                        field: 'status',
                        title: '状态',
                        formatter : function (value, row, index) {
                            if (value == 0) {
                                return '<span class="badge btn-success">空闲</span>';
                            } else if (value == 1) {
                                return '<span class="badge btn-pinterest">占用</span>';
                            }
                        }
                    },
                    {
                        field: 'createTime',
                        title: '创建时间',
                        formatter : function(value, row, index) {
                            return dateFormat(value);
                        }
                    },
                    {
                        field: 'content',
                        title: '操作',
                        formatter : function(value, row, index) {
                            var btn1 = "<button onclick='enableUav(\""+ row.id +"\");' class='btn  btn-xs btn-default '>&nbsp;</i>上架</button>&nbsp;&nbsp;";
                            var btn2 = "<button onclick='disableUav(\""+ row.id +"\");' class='btn  btn-xs btn-default '>&nbsp;</i>下架</button>&nbsp;&nbsp;";
                            if (row.isValid == 0) {
                                return btn1;
                            } else if (row.isValid == 1) {
                                return btn2;
                            }
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
                    uavName: $("#uavName").val(),
                    uavCode: $("#uavCode").val(),
                    uavType: $("#uavType").val(),
                    status: $("#status").val()
                }
            };
            return temp;
        };
        return oTableInit;
    };

    /**
     * 添加无人机
     */
    function add() {
        layer.open({
            type: 2,
            title: '添加无人机',
            shadeClose: false, //点击遮罩关闭层
            area: [$(this).width() + 'px', $(this).height() + 'px'],
            content: '${ctx}/admin/uav/add'
        });
    }

    /**
     * 刷新
     */
    function refresh(){
        $table.bootstrapTable('refresh');
    }

    /**
     * 无人机上架
     * @param id
     */
    function enableUav(id) {
        $.ajax({
            type: "get",
            url: "${ctx}/admin/uav/enableUav/" + id,
            dataType: 'json',
            success: function (data) {
                if (data.code == 0) {
                    layer.alert("Enable Successfully");
                }
                refresh();
            }
        });
    }

    /**
     * 无人机下架
     * @param id
     */
    function disableUav(id) {
        $.ajax({
            type: "get",
            url: "${ctx}/admin/uav/disableUav/" + id,
            dataType: 'json',
            success: function (data) {
                if (data.code == 0) {
                    layer.alert("Disable Successfully");
                }
                refresh();
            }
        });
    }

</script>
</html>