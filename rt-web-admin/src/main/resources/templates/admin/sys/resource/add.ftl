[#include "/commons/include.form_rec.ftl"]
<link rel="stylesheet" type="text/css" href="${ctx!}/assets/js/easyui/themes/metro/easyui.css">
<script type="text/javascript" src="${ctx!}/assets/js/easyui/jquery.easyui.min.js"></script>
<div class="container">
<form class="form-horizontal m-t" id="form" method="post" action="${ctx!}/admin/sys/resource/add">
        <div class="row">
            <div class="box-body">
                [#include "/admin/sys/resource/form.ftl"/]
                <div class="row no-print footer">
                    <div class="col-xs-12" style="text-align: right">
                        <button type="button" class="btn btn-primary" onclick="save();" style="margin-right: 5px;">确定</button>
                        <button type="button" class="btn btn-default" onclick="T.close()" >取消</button>
                    </div>
                </div>
            </div>
        </div>
</form>
</div>
<script>
    var $form = $("#form");
    function save(){
        var tree = $('#parentId').combotree('tree');
        var data = tree.tree('getSelected');
        console.log(data);
        if(data==null){
            layer.alert("请选择上级资源！");
            return;
        }
        T.save($form);
    }
    $(function(){
        T.valid($form);
    })
</script>

