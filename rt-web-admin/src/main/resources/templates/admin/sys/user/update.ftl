[#include "/commons/include.form_rec.ftl"]
<div class="container">
    <form class="form-horizontal" id="form" method="post" action="${ctx!}/admin/sys/user/update">
        <div class="row">
            <div class="box-body">
                <input type="hidden" id="id" name="id" value="${user.id}">
                [#include "/admin/sys/user/form.ftl"/]
                <div class="row no-print footer">
                    <div class="col-xs-12" style="text-align: right">
                        <button type="button" class="btn btn-primary" onclick="save()" style="margin-right: 5px;">确定</button>
                        <button type="button" class="btn btn-default" onclick="T.close()" >取消</button>
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
    function save(){
        T.save($form);
    }
    function close(){
        T.close();
    }
</script>
