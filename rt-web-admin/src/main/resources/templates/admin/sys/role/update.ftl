[#include "/commons/include.form_rec.ftl"]
<link href="${ctx!}/assets/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<script src="${ctx!}/assets/plugins/ztree/js/jquery.ztree.all-3.5.js"></script>
<div class="container">
<form class="form-horizontal m-t" id="form" method="post" action="${ctx!}/admin/sys/role/update">
    <input type="hidden" id="id" name="id" value="${role.id}">
        <div class="row">
            <div class="box-body">
                 [#include "/admin/sys/role/form.ftl"/]
                <div class="row no-print footer">
                    <div class="col-xs-12" style="text-align: right">
                        <button type="button" class="btn btn-primary" onclick="T.save($form)" style="margin-right: 5px;">ok</button>
                        <button type="button" class="btn btn-default" onclick="T.close()" >cancel</button>
                    </div>
                </div>
            </div>
        </div>
</form>
</div>
<script>
    var $form = $("#form");
    $(function(){
        T.valid($form);
    })
</script>
