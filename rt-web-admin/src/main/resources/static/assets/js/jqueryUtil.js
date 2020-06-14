(function ($)
{
	//全局系统对象
    window['jqueryUtil'] = {};
	//获取随机时间
	 jqueryUtil.getRandTime=function(){
		 	var nowDate=new Date();
		 	var str="";
			var hour=nowDate.getHours();//HH
			str+=((hour<10)?"0":"")+hour;
			var min=nowDate.getMinutes();//MM
			str+=((min<10)?"0":"")+min;
			var sec=nowDate.getSeconds(); //SS
			str+=((sec<10)?"0":"")+sec;
			return Number(str);
	 };
        /*
    	 * 格式化字符串
    	 */
        $.formatString = function(str) {
        	for ( var i = 0; i < arguments.length - 1; i++) {
        		str = str.replace("{" + i + "}", arguments[i + 1]);
        	}
        	return str;
        };

	var dialog;
        jqueryUtil.dialog=function(title,url,width,method) {
			var _width = '800px';
			if(width!=null) _width=width+"px";
			layer.open({
				type: 2,
				title: title,
				shadeClose: true, //点击遮罩关闭层
				area : [_width , '600px'],
				content: url
				,btn: ['确定']
				,yes: function(index, layero){
					if (method != undefined) eval(method);
					layer.closeAll('iframe');
				}


			});
		};
		var $grid;

	/**
	 * 添加弹窗
	 * @param title 标题
	 * @param url 打开地址
	 * @param width 宽度
	 * @param formId 需要提交的formid
	 * @param grid 需要回调刷新的grid
	 * @param callback 需要回调的方法（在回调不是grid时使用）
	 */
		jqueryUtil.addRow = function(title,url,width,height,formId,grid,callback){
			var _width = '800px';
			var _height = '600px';
			if(width!=null) _width=width+"px";
			if(height!=null) _height=height+"px";
			$grid = grid;
			layer.open({
				type: 2,
				title: title,
				shadeClose: true, //点击遮罩关闭层
				area : [_width , _height],
				content: url
				,btn: ['保存', '取消']
				,yes: function(index, layero){
					var body = layer.getChildFrame('body', index);
					var f;
					if (formId) {
						f = body.find("#" + formId);
					} else {
						f = body.find("#form");
					}
					f.bootstrapValidator('validate');
					if (f.data('bootstrapValidator').isValid()) {
						if (callback != null) {
							jqueryUtil.form(f, callback);
						} else {
							jqueryUtil.form(f);
						}
					}
				}

			});
		}

	jqueryUtil.confirmRows = function(url,grid,method){
		layer.confirm('确定要这么做吗?', {icon: 3, title:'提示'}, function(index){
			$.ajax({
				url: url,
				type: "Get",
				success: function (data) {
					if (data.code==0) {
						if(grid!=null)
							grid.ajax.reload();
						if (method != undefined){
							eval(method);
						}
						layer.alert('操作成功');


					}else if(data.code==401){
						window.location.reload();
						return;
					}else{
						layer.alert(data.msg);
					}
				}
			});

			layer.close(index);
		});

	}

	jqueryUtil.openRow = function(title,url,width, formId,grid,callback){
		var _width = '800px';
		if(width!=null) _width=width+"px";
		$grid = grid;
		layer.open({
			type: 2,
			title: title,
			shadeClose: true, //点击遮罩关闭层
			area : [_width , '600px'],
			content: url
			,btn: ['取消']
			,cancel: function(index, layero){

			}

		});
	}

	/**
	 * 修改弹窗
	 * @param title 标题
	 * @param url 打开地址
	 * @param width 宽度
	 * @param formId 需要提交的formid
	 * @param grid 需要回调刷新的grid
	 * @param callback 需要回调的方法（在回调不是grid时使用）
	 */
	jqueryUtil.updateRow = function(title,url,width,height, formId,callback){
		var _width = '800px';
		var _height = '600px';
		if(width!=null) _width=width+"px";
		if(height!=null) _height=height+"px";

		layer.open({
			type: 2,
			title: title,
			shadeClose: true, //点击遮罩关闭层
			area : [_width ,_height],
			content: url
			,btn: ['修改', '取消']
			,yes: function(index, layero){
				var body = layer.getChildFrame('body', index);

				var f;
				if (formId) {
					f = body.find("#" + formId);
				} else {
					f = body.find("#form");
				}
				f.bootstrapValidator('validate');
				if (f.data('bootstrapValidator').isValid()) {
					if (callback != null) {
						jqueryUtil.form(f, callback);
					} else {
						jqueryUtil.form(f);
					}
				}
			}

		});

	}
		//删除
		jqueryUtil.delRows = function(url,grid,method){
			layer.confirm('确定要删除吗?', {icon: 3, title:'提示'}, function(index){
					$.ajax({
						url: url,
						type: "Get",
						success: function (data) {
							if (data.code==0) {
								if(grid!=null)
									grid.ajax.reload();
								if (method != undefined){
									eval(method);
								}
								layer.alert('删除成功');


							}else if(data.code==401){
								window.location.reload();
								return;
							}else{
								layer.alert(data.msg);
							}
						}
					});

				layer.close(index);
			});

		}
		jqueryUtil.form=function($form,method){
            layer.load(2);
			$form.ajaxSubmit({
				dataType : 'json',
				success : function(data) {
                    layer.closeAll('loading');
					if (data.code==0) {
						layer.closeAll('iframe');
						layer.alert('操作成功！');
						if (method != undefined){
							eval(method);
						}
						if($grid){
							$grid.ajax.reload();
						}
					}else if(data.code==401){
						window.location.reload();
					}else{
						layer.alert(data.msg);
					}
				}
			});
		}
})(jQuery);


$(document).ajaxComplete(function(event, xhr, settings) {
    layer.closeAll('loading');
	if (xhr.getResponseHeader('sessionstatus') == 'timeout') {
		layer.confirm('登录超时，请重新登录?', {icon: 3, title:'提示'}, function(index){
			window.location.reload();
		});
	} else if(403 == xhr.status) {
		window.location.reload();
	}
});

//iframe自适应
$(window).on('resize', function() {
    var $content = $('.content');
    $content.height($(this).height() - 94);
    $content.find('iframe').each(function() {
        $(this).height($content.height());
    });
}).resize();