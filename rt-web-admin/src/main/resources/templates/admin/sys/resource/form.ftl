<div class="form-group">
    <label class="col-sm-3 control-label">ParentResource：</label>
    <div class="col-sm-8">
        <input id="parentId" name="parentId" value="${(resource.parentId)!}">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">ResourceName：</label>
    <div class="col-sm-8">
        <input id="name" name="name" class="form-control" type="text" value="${resource.name}" required>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">UUID：</label>
    <div class="col-sm-8">
        <input id="sourceKey" name="sourceKey" class="form-control" type="text" value="${resource.sourceKey}" required>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">ResourceType：</label>
    <div class="col-sm-8">
        <select name="type" class="form-control" >
            <option value="1" [#if resource.type == 1]selected="selected"[/#if]>Menu</option>
            <option value="2" [#if resource.type == 2]selected="selected"[/#if]>Button</option>
        </select>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">ResourceUrl：</label>
    <div class="col-sm-8">
        <input id="sourceUrl" name="sourceUrl" class="form-control" value="${resource.sourceUrl}">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">Level：</label>
    <div class="col-sm-8">
        <input id="level" name="level" class="form-control" value="${resource.level}" placeholder="Catalog：1，Menu：2，Button：3" required>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">Sort：</label>
    <div class="col-sm-8">
        <input id="sort" name="sort" class="form-control" value="${resource.sort}" required>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">Icon：</label>
    <div class="col-sm-8">
        <input id="icon" name="icon" class="form-control" value="${resource.icon}">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">Status：</label>
    <div class="col-sm-8">
        <select name="isHide" class="form-control">
            <option value="0" [#if resource.locked == 0]selected="selected"[/#if]>Show</option>
            <option value="1" [#if resource.locked == 1]selected="selected"[/#if]>Hide</option>
        </select>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">Description：</label>
    <div class="col-sm-8">
        <input id="description" name="description" class="form-control" value="${resource.description}">
    </div>
</div>
<script>
    var roleTree = eval('${(roleTree)!}');
    var json = convert(roleTree);
    console.log(json)
    $(function(){
        $('#parentId').combotree({
            width:300,
            data: json
        });
    })
</script>