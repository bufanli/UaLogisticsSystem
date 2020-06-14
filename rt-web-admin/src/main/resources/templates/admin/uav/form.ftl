<div class="form-group">
    <label class="col-sm-3 control-label"><span style="color: red">*</span>无人机名称:</label>
    <div class="col-sm-8">
        <input id="uavName" name="uavName" class="form-control" type="text" [#if detail?exists]disabled="disabled"[/#if] placeholder="please input the uavName">
    </div>
</div>
<br>
<br>
<div class="form-group">
    <label class="col-sm-3 control-label"><span style="color: red">*</span>无人机编码:</label>
    <div class="col-sm-8">
        <input id="uavCode" name="uavCode" class="form-control"  [#if detail?exists]disabled="disabled"[/#if] placeholder="please input the uavCode">
    </div>
</div>
<br>
<br>
<div class="form-group">
    <label class="col-sm-3 control-label"><span style="color: red">*</span>无人机型号:</label>
    <div class="col-sm-8">
        <input id="uavType" name="uavType" class="form-control" [#if detail?exists]disabled="disabled"[/#if] placeholder="please input the uavType">
    </div>
</div>
<br>
<br>
<div class="form-group">
    <label class="col-sm-3 control-label"><span style="color: red">*</span>无人机负载重量(Kg):</label>
    <div class="col-sm-8">
        <input id="uavWeight" name="uavWeight" class="form-control"  [#if detail?exists]disabled="disabled"[/#if] placeholder="please input the uavWeight">
    </div>
</div>