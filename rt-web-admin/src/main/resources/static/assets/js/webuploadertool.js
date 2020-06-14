(function () {
    var settings = {
        serverurl: "",
        imageExtensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/gif,image/jpg,image/jpeg,image/bmp,image/png',
        // 缩略图大小
        ratio: window.devicePixelRatio || 1,
        thumbnailWidth: 160 * this.ratio,
        thumbnailHeight: 120 * this.ratio,
        fileSizeLimit: 10 * 1024 * 1024,
        fileNumLimit: 5,
        fileSingleSizeLimit: 10 * 1024 * 1024

    };

    var $WebUpload = function (_uploader,options) {
        if (options) {
            $.extend(settings, options);
        }
        this. swfurl=BASE_URL + '/js/Uploader.swf',
        this.fileCount= 0
        this.custsuccess = null;
        this.custerror = null;
        this._uploader = _uploader
        this.WebUploader;
    };
    $WebUpload.prototype = {
        /**
         * 初始化webUploader
         */
        init: function () {
            var uploader = this.create();
            this.bindEvent(uploader);
            return uploader;
        },

        /**
         * 创建webuploader对象
         */
        create: function () {
            var webUploader = WebUploader.create({
                pick: $('._picker', $(this._uploader))[0],
                //dnd: $('._queueList', $(this._uploader))[0],
                // 这里如果要一个页面多个实例，有bug
                // https://github.com/fex-team/webuploader/issues/81#issuecomment-228499631
                accept: {
                    title: 'Images',
                    extensions: settings.imageExtensions,
                    mimeTypes: settings.mimeTypes
                },
                // swf文件路径
                swf: this.swfurl,
                disableGlobalDnd: true,
                duplicate: true,//不允许上传同一个文件
                auto: true,//自动上传
                server: settings.serverurl,
                fileNumLimit: settings.fileNumLimit,
                fileSingleSizeLimit: settings.fileSingleSizeLimit * 1024 * 1024    // 3 M
            });
            return webUploader;
        },

        /**
         * 绑定事件
         */
        bindEvent: function (bindedObj) {
            var me = this;
            bindedObj.on('fileQueued', function (file) {
                layer.load(2)
                me.fileCount++;
            });
            // 文件上传过程中创建进度条实时显示。
            bindedObj.on('uploadProgress', function (file, percentage) {
                console.log(percentage);
            });
            // 文件上传成功，给item添加成功class, 用样式标记上传成功。
            bindedObj.on('uploadSuccess', function (file, response) {
                if ('function' == typeof me.custsuccess) {
                    me.custsuccess(file, response);
                }
                console.log(file);
                console.log(response);
            });

            // 文件上传失败，显示上传出错。
            bindedObj.on('uploadError', function (file, reason) {
               console.log('上传失败,' + reason);
            });

            // 其他错误
            bindedObj.on('error', function (type) {
                console.log("webuploadertool error type:" + type);
                var message = "";
                if ("Q_EXCEED_NUM_LIMIT" == type) {
                    message = "最多只允许上传" + settings.fileNumLimit + "张图片";
                } else if ("F_EXCEED_SIZE" == type) {
                    message = "单张只允许上传" + settings.fileSingleSizeLimit + "M以内的图片";
                } else if ("Q_TYPE_DENIED" == type) {
                    message = "只允许上传类型为" + settings.imageExtensions + "的图片";
                }
                if ('function' == typeof me.custerror) {
                    me.custerror(message);
                }
                console.log(message);
            });

            // 完成上传完了，成功或者失败
            bindedObj.on('uploadComplete', function (file) {
                layer.closeAll("loading");
            });
        }
    };
    window.$WebUpload = $WebUpload;
}());
