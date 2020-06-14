[#include "/commons/include.form_rec.ftl"]
<div class="container">
    <form class="form-horizontal" id="form" method="post" action="${ctx!}/admin/uav/add">
        <div class="row">
            <div class="box-body">
                [#include "/admin/uav/form.ftl"/]
                <div class="row no-print footer">
                    <div class="col-xs-12" style="text-align: center">
                        <button type="button" class="btn btn-primary" onclick="save()" style="margin-right: 5px;">确定</button>
                        <button type="button" class="btn btn-default" onclick="T.close()" >取消</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctx}/assets/plugins/My97DatePicker/WdatePicker.js" ></script>
<script>
    var $form = $("#form");
    $(function(){
        T.valid($form);
    })

    /**
     * 保存活动
     */
    function save() {
        T.save($form);
    }


    //时间插件
    $('.timepicker').datetimepicker({
        language: 'zh',
        format: 'yyyy-mm-dd',//显示格式
        todayHighlight: 1,//今天高亮
        minView: "month",//设置只显示到月份
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        autoclose: 1//选择后自动关闭
    });


    $("#form").bootstrapValidator({
        /**
         *  指定不验证的情况
         *  值可设置为以下三种类型：
         *  1、String  ':disabled, :hidden, :not(:visible)'
         *  2、Array  默认值  [':disabled', ':hidden', ':not(:visible)']
         *  3、带回调函数
         [':disabled', ':hidden', function($field, validator) {
            // $field 当前验证字段dom节点
            // validator 验证实例对象
            // 可以再次自定义不要验证的规则
            // 必须要return，return true or false;
            return !$field.is(':visible');
        }]
         */
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        /**
         * 指定验证后验证字段的提示字体图标。（默认是bootstrap风格）
         * Bootstrap 版本 >= 3.1.0
         * 也可以使用任何自定义风格，只要引入好相关的字体文件即可
         * 默认样式
         .form-horizontal .has-feedback .form-control-feedback {
            top: 0;
            right: 15px;
        }
         * 自定义该样式覆盖默认样式
         .form-horizontal .has-feedback .form-control-feedback {
            top: 0;
            right: -15px;
        }
         .form-horizontal .has-feedback .input-group .form-control-feedback {
            top: 0;
            right: -30px;
        }
         */
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        /**
         * 生效规则（三选一）
         * enabled 字段值有变化就触发验证
         * disabled,submitted 当点击提交时验证并展示错误信息
         */
        live: 'enabled',
        /**
         * 为每个字段指定通用错误提示语
         */
        message: 'This value is not valid',
        /**
         * 指定提交的按钮，例如：'.submitBtn' '#submitBtn'
         * 当表单验证不通过时，该按钮为disabled
         */
        submitButtons: 'button[type="submit"]',
        /**
         * submitHandler: function(validator, form, submitButton) {
    *   //validator: 表单验证实例对象
    *   //form  jq对象  指定表单对象
    *   //submitButton  jq对象  指定提交按钮的对象
    * }
         * 在ajax提交表单时很实用
         *   submitHandler: function(validator, form, submitButton) {
            // 实用ajax提交表单
            $.post(form.attr('action'), form.serialize(), function(result) {
                // .自定义回调逻辑
            }, 'json');
         }
         *
         */
        submitHandler: null,
        /**
         * 为每个字段设置统一触发验证方式（也可在fields中为每个字段单独定义），默认是live配置的方式，数据改变就改变
         * 也可以指定一个或多个（多个空格隔开） 'focus blur keyup'
         */
        trigger: null,
        /**
         * Number类型  为每个字段设置统一的开始验证情况，当输入字符大于等于设置的数值后才实时触发验证
         */
        threshold: null,
        /**
         * 表单域配置
         */
        fields: {
            //多个重复
            uavName: {
                //隐藏或显示 该字段的验证
                enabled: true,
                //错误提示信息
                message: 'This value is not valid',
                /**
                 * 定义错误提示位置  值为CSS选择器设置方式
                 * 例如：'#firstNameMeg' '.lastNameMeg' '[data-stripe="exp-month"]'
                 */
                container: null,
                /**
                 * 定义验证的节点，CSS选择器设置方式，可不必须是name值。
                 * 若是id，class, name属性，<fieldName>为该属性值
                 * 若是其他属性值且有中划线链接，<fieldName>转换为驼峰格式  selector: '[data-stripe="exp-month"]' =>  expMonth
                 */
                selector: null,
                /**
                 * 定义触发验证方式（也可在fields中为每个字段单独定义），默认是live配置的方式，数据改变就改变
                 * 也可以指定一个或多个（多个空格隔开） 'focus blur keyup'
                 */
                trigger: null,
                // 定义每个验证规则
                validators: {
                    notEmpty: {//非空验证：提示消息
                        message: 'The name of the parking lot can not be null'
                    }
                }
            },
            uavCode: {
                //隐藏或显示 该字段的验证
                enabled: true,
                //错误提示信息
                message: 'This value is not valid',
                /**
                 * 定义错误提示位置  值为CSS选择器设置方式
                 * 例如：'#firstNameMeg' '.lastNameMeg' '[data-stripe="exp-month"]'
                 */
                container: null,
                /**
                 * 定义验证的节点，CSS选择器设置方式，可不必须是name值。
                 * 若是id，class, name属性，<fieldName>为该属性值
                 * 若是其他属性值且有中划线链接，<fieldName>转换为驼峰格式  selector: '[data-stripe="exp-month"]' =>  expMonth
                 */
                selector: null,
                /**
                 * 定义触发验证方式（也可在fields中为每个字段单独定义），默认是live配置的方式，数据改变就改变
                 * 也可以指定一个或多个（多个空格隔开） 'focus blur keyup'
                 */
                trigger: null,
                // 定义每个验证规则
                validators: {
                    notEmpty: {//非空验证：提示消息
                        message: 'The code of the uav can not be null'
                    }
                }
            },
            uavType: {
                //隐藏或显示 该字段的验证
                enabled: true,
                //错误提示信息
                message: 'This value is not valid',
                /**
                 * 定义错误提示位置  值为CSS选择器设置方式
                 * 例如：'#firstNameMeg' '.lastNameMeg' '[data-stripe="exp-month"]'
                 */
                container: null,
                /**
                 * 定义验证的节点，CSS选择器设置方式，可不必须是name值。
                 * 若是id，class, name属性，<fieldName>为该属性值
                 * 若是其他属性值且有中划线链接，<fieldName>转换为驼峰格式  selector: '[data-stripe="exp-month"]' =>  expMonth
                 */
                selector: null,
                /**
                 * 定义触发验证方式（也可在fields中为每个字段单独定义），默认是live配置的方式，数据改变就改变
                 * 也可以指定一个或多个（多个空格隔开） 'focus blur keyup'
                 */
                trigger: null,
                // 定义每个验证规则
                validators: {
                    notEmpty: {//非空验证：提示消息
                        message: 'The type of the uav can not be null'
                    }
                }
            },
            uavWeight: {
                trigger: "blur",
                enabled: true,
                message: 'The wight is not valid',
                validators: {
                    notEmpty: {
                        message: 'The wight can not be null'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: 'The count must be the Numeric'
                    }
                }
            }
        }
    });
</script>
