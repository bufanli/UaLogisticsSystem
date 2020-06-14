<div class="form-group">
    <label class="col-sm-3 control-label">Username：</label>
    <div class="col-sm-8">
        <input id="type" name="type" class="form-control" type="hidden" value="0" >
        <input id="username" name="username" class="form-control" type="text" value="${user.username}" [#if user?exists] readonly="readonly"[/#if] required>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">Nickname：</label>
    <div class="col-sm-8">
        <input id="nickname" name="nickname" class="form-control" type="text" value="${user.nickname}">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">Password：</label>
    <div class="col-sm-8">
        <input id="password" name="password" class="form-control" value="" [#if !user??] required[/#if]>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">Gender：</label>
    <div class="col-sm-8">
        <input type="radio" name="sex" value="1" name="iCheck" [#if user.sex == 1]checked[/#if]>&nbsp;Male&nbsp;
        <input type="radio" name="sex" value="0" name="iCheck" [#if user.sex == 0]checked[/#if]> &nbsp;Female
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">Telephone：</label>
    <div class="col-sm-8">
        <input id="telephone" name="telephone" class="form-control" value="${user.telephone}" required>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">E-mail：</label>
    <div class="col-sm-8">
        <input id="email" name="email" class="form-control" value="${user.email}" required>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">Status：</label>
    <div class="col-sm-8">
        <input type="radio" name="locked" value="0" name="iCheck" [#if user.locked == 0]checked[/#if]> &nbsp;Nomal
        <input type="radio" name="locked" value="1" name="iCheck" [#if user.locked == 1]checked[/#if]>&nbsp;Locked&nbsp;
    </div>
</div>

<div class="form-group">
    <label class="col-sm-3 control-label">Role：</label>
    <div class="col-sm-8">
        <select class="selectpicker form-control" id="role" editable = "false"  data-bv-notempty  name="roleIds"  labelPosition="top" style="width:150px;">
            <option value="">Please select the role</option>
           [#if roles??]
               [#list roles as role]
                   [#assign  flag=false/]
                   [#if selected??]
                       [#list selected as s]
                           [#if s.id?? && s.id==role.id][#assign  flag=true/][/#if]
                       [/#list]
                   [/#if]
                <option value="${(role.id)!}"  [#if flag]selected="selected"[/#if]>${(role.name)!}</option>
               [/#list]
           [/#if]
        </select>
    </div>
</div>