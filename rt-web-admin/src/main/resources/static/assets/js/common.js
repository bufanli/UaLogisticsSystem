
//工具集合Tools
window.T = {};
// 获取请求参数
// 使用示例
// location.href = http://localhost/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

var validate_locale="zh_CN";
var i18n = "../../plugins/datatables/i18n/Chinese.json";
var saveForm = function($form){
    $form.submit();
};
var closedForm = function(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
};

var validInit = function($form){
    $form.bootstrapValidator().on('success.form.bv', function(e) {
        layer.load(2);
        $form.ajaxSubmit({
            dataType : 'json',
            success : function(data) {
                layer.closeAll('loading');
                if (data.code==0) {
                    layer.alert('操作成功！', function () {
                        parent.refresh();
                        T.close();
                    });
                }else{
                    layer.alert(data.msg);
                }
            }
        });
    });
}
T.save = saveForm;
T.close = closedForm;
T.valid = validInit;

//判断是否为空
function isBlank(value) {
    return !value || !/\S/.test(value)
}

function getUUID() {
	var s = [];
	var hexDigits = "0123456789abcdef";
	for (var i = 0; i < 36; i++) {
		s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
	}
	s[14] = "4"; 
	s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); 
														
	s[8] = s[13] = s[18] = s[23] = "-";

	var uuid = s.join("");
	return uuid;
}

/**
 * 菜单转换
 * @param rows
 * @returns {Array}
 */
function convert(rows) {
    var nodes = [];
    //取到第一级菜单
    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        if (!exists(rows, row.parentId)) {
            nodes.push({
                id: row.id,
                icon:row.icon,
                text: row.name,
                type:row.type,
                sort:row.sort,
                sourceKey:row.sourceKey,
                url: row.sourceUrl,
                targetType: "iframe-tab"
            });
        }
    }

    var toDo = [];
    for (var i = 0; i < nodes.length; i++) {
        toDo.push(nodes[i]);
    }
    while (toDo.length) {
        var node = toDo.shift();    // the parent node
        //取得子菜单
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            if (row.parentId == node.id) {
                var child = {id: row.id,targetType: "iframe-tab", text: row.name,type:row.type,sort:row.sort,icon:row.icon,sourceKey:row.sourceKey, url: row.sourceUrl};
                if (node.children) {
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    // console.log(nodes)
    return nodes;
}



function exists(rows, parentId) {
    for (var i = 0; i < rows.length; i++) {
        if (rows[i].id == parentId) {
            return true;
        }
    }
    return false;
}
