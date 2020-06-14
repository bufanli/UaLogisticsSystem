
<table style="width: 100%;height: 500px;font-size: 14px" class="table">
    <tr>
        <td>
            <h2 class="page-header" style="font-size: 14px;">
                <i class="fa fa-globe"></i> RoleInfo
            </h2>
        </td>
        <td>
            <h2 class="page-header" style="font-size: 14px;">
                <i class="fa fa-globe"></i> Authority
            </h2>
        </td>
    </tr>
    <tr>
        <td style="width: 450px;vertical-align: top">

            <div class="form-group">
                <label class="col-sm-3 control-label">Rolekey：</label>
                <div class="col-sm-8">
                    <input id="roleKey" name="roleKey" class="form-control" type="text" value="${role.roleKey}" [#if role?exists] readonly="readonly"[/#if]  required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">RoleName：</label>
                <div class="col-sm-8">
                    <input id="name" name="name" class="form-control" type="text" value="${role.name}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Status：</label>
                <div class="col-sm-8">
                    <select name="status" class="form-control">
                        <option value="0" [#if role.status == 0]selected="selected"[/#if]>normal</option>
                        <option value="1" [#if role.status == 1]selected="selected"[/#if]>Locked</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Description：</label>
                <div class="col-sm-8">
                    <input id="description" name="description" class="form-control" value="${role.description}">
                </div>
            </div>
        </td>
        <td>
            <div style="overflow-y:scroll;height: 500px">
                <div class="col-xs-12">

                    <ul id="dg_auth" class="ztree"></ul>
                </div>
                <div id="hiddenList">

                </div>
            </div>
        </td>
    </tr>
</table>
<script type="text/javascript">

    var roleTree = eval('${(roleTree)!}');

    var authSelected = eval('${(selected)!}');
    var setting = {
        check: {
            enable: true,
            chkboxType: { "Y": "ps", "N": "ps" }
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "parentId",
                rootPId: 0,

            }
        },
        callback: {
            onCheck: zTreeOnCheck
        }
    };
    var treeObj;
    $(function() {
        treeObj = $.fn.zTree.init($("#dg_auth"), setting, roleTree);
        treeObj.expandAll(true);
        for(var i in authSelected){
            var checkedNodes = treeObj.getNodesByParam("id", authSelected[i].id, null);
            if(checkedNodes != null){
                for(j in checkedNodes){
                    if(checkedNodes[j].check_Child_State == -1){
                        treeObj.checkNode(checkedNodes[j], true, true);
                    }
                }
            }
        }
        zTreeOnCheck();
    });

    function  zTreeOnCheck() {
        var moduleIds = "";
        var nodes   = treeObj.getCheckedNodes(true);
        $("#hiddenList").html('');
        $(nodes).each(function (i, item) {
            $("#hiddenList").append('<input name="resourceIds" type="hidden" value="'+item.id+'"/>');
        });
        return true;
    }

</script>