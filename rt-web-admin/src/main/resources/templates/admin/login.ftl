<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台  | 登录</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/assets/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${ctx}/assets/css/main.css">
    <style>
        .logo-container {
            width: 100%;
            margin: 0 auto;
            /*position: relative;*/
            text-align: center;
            padding-top: 120px;

        }
        .login-page,.register-box {
            background:url('${ctx}/assets/img/admin.jpg');
            background-size:100% 100%;
        }

        .login-box-body, .register-box-body {
            border-top: 0 none;
            color: #666;
            padding: 20px;
        }



        .copy_layout {
            text-align:right;
            margin-right: 12%;
        }
        .copy_layout p{
            color:#fff0e7;
        }

    </style>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${ctx}/assets/js/html5shiv.min.js"></script>
    <script src="${ctx}/assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition login-page">
<div class="logo-container">
</div>

<div class="login-box" style="margin: 3% auto;">
    <div class="login-logo">
        <!--<p style="margin-top:40px; "><img style="width:220px;"  src="${CONTEXT_PATH}/resources/site/img/logo.png"></p>-->
        <a href="###"><b style="color:#fff">无人机物流管理系统i</b></a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">

        <p class="login-box-msg">Backend Management System</p>
        <form id="frm" class="form-vertical"  action="${ctx!}/admin/login" method="post">

            <div class="form-group has-feedback">
                <input name="username" type="text" class="form-control" placeholder="用户名" value="">
            </div>
            <div class="form-group has-feedback">
                <input name="password" type="password" class="form-control" placeholder="密码" value="">
            </div>
            <div class="form-group has-feedback">
                <div class="row">
                    <div class="col-xs-8" >
                        <input id="captcha_key" name="captcha" class="form-control" placeholder="验证码" style="width:200px;">
                    </div>
                    <div class="col-xs-4" >
                        <img src="${ctx}/captcha.jpg" id="captcha" style=" height:33px;width:100px;cursor:pointer; vertical-align:middle"   title="点击更换图片"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <!-- /.col -->
                <div class="col-xs-12">
                    <button  class="btn btn-primary btn-block btn-flat">Login</button>
                </div>
                <!-- /.col -->
            </div>
        </form>
        <div>&nbsp;</div>
        [#if errors?exists]
                <div style=" color: #dd4d1b;">
                    <strong>提示：</strong>${errors!}
                </div>
        [/#if]
        <div>&nbsp;</div>
    </div>
    <!-- /.login-box-body -->
</div>

<script src="${ctx}/assets/js/jquery.min.js"></script>
<script src="${ctx}/assets/js/bootstrap.min.js"></script>
<script>
    $(function () {
        if (window != top) {
            top.location.href = location.href;
        }

        $('#captcha').click(function(){
            $('#captcha').attr("src","${ctx}/captcha.jpg?seed="+Math.random());
        });
    });
</script>
</body>
</html>