<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>UavLogisticsSystem-Backend</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${ctx}/assets/lte/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${ctx}/assets/lte/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${ctx}/assets/lte/css/skins/_all-skins.css">

    <!--http://aimodu.org:7777/admin/index_iframe.html?q=audio&search=#-->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${ctx}/assets/js/html5shiv.min.js"></script>
    <script src="${ctx}/assets/js/respond.min.js"></script>
    <![endif]-->
    <style>
        .wrapper {
            height: 100%;
            position: relative;
            overflow-x: hidden;
            overflow-y: hidden;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini " style="min-width:1024px;overflow-x:auto;">
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="###" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini">Uav</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>无人机物流管理系统i</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <div class="navbar-custom-menu">

                <ul class="nav navbar-nav">
                    <!-- Messages: style can be found in dropdown.less-->
                    <!-- Notifications: style can be found in dropdown.less -->
                    <!-- Tasks: style can be found in dropdown.less -->
                    <li class="dropdown notifications-menu" >
                        <div style="padding:15px 0 15px 0;">
                            <span style="color:#fff;font-size:14px;">${(shiroUser.org.org_name)!}</span>
                        </div>
                    </li>

                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="${ctx}/assets/lte/img/hg_avatar.jpg" class="user-image" alt="User Image">
                            <span class="hidden-xs">${(user.username)!}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="${ctx}/assets/lte/img/hg_avatar.jpg" class="img-circle" alt="User Image">

                                <p>
                                ${(user.username)!}
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <!-- Menu Footer-->
                            <li class="user-footer" style="text-align: center">
                                <!--<div class="pull-left">
                                    <a href="javascript:void();" onclick="updatePwd();"  class="btn btn-default btn-flat">修改密码</a>
                                </div>-->
                                <div  >
                                    <a href="javascript:logout();"  class="btn btn-default btn-flat">Log Out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <!-- search form -->
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">Navigation Menu</li>

            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper" id="content-wrapper" style="min-height: 421px;">
        <!--bootstrap tab风格 多标签页-->
        <div class="content-tabs">
            <button class="roll-nav roll-left tabLeft" onclick="scrollTabLeft()">
                <i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs menuTabs tab-ui-menu" id="tab-menu">
                <div class="page-tabs-content" style="margin-left: 0px;">

                </div>
            </nav>
            <button class="roll-nav roll-right tabRight" onclick="scrollTabRight()">
                <i class="fa fa-forward" style="margin-left: 3px;"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown tabClose" data-toggle="dropdown">
                    Tab<i class="fa fa-caret-down" style="padding-left: 3px;"></i>
                </button>
                <ul class="dropdown-menu dropdown-menu-right" style="min-width: 128px;">
                    <li><a class="tabReload" href="javascript:refreshTab();">refreshTab</a></li>
                    <li><a class="tabCloseCurrent" href="javascript:closeCurrentTab();">closeCurrentTab</a></li>
                    <li><a class="tabCloseAll" href="javascript:closeOtherTabs(true);">closeAllTabs</a></li>
                    <li><a class="tabCloseOther" href="javascript:closeOtherTabs();">closeOtherTabs</a></li>
                </ul>
            </div>
            <button class="roll-nav roll-right fullscreen" onclick="App.handleFullScreen()"><i
                    class="fa fa-arrows-alt"></i></button>
        </div>
        <div class="content-iframe " style="background-color: #ffffff; min-height: 300px">
            <div class="tab-content " id="tab-content">

            </div>
        </div>
    </div>
    <!-- /.content-wrapper -->

    <footer class="main-footer">
     <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.0
        </div>
       <strong><a href="http://almsaeedstudio.com"></a></strong> All rights
        reserved.
    </footer>

    <!-- Control Sidebar -->
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<script src="${ctx}/assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${ctx}/assets/js/bootstrap.min.js"></script>
<!-- Slimscroll -->
<script src="${ctx}/assets/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- AdminLTE App -->
<script src="${ctx}/assets/lte/js/app.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${ctx}/assets/lte/js/demo.js"></script>
<!--tabs-->
<script src="${ctx}/assets/lte/js/app_iframe.js"></script>
<script src='${ctx}/assets/plugins/layer/layer.js'></script>
<script src="${ctx}/assets/plugins/jQuery/plugins/jquery.form.js"></script>
<script src="${ctx}/assets/plugins/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script src="${ctx}/assets/js/common.js"></script>
<!--<script src="../dist/js/jquery.blockui.min.js"></script>
<script src="../dist/js/appx.js"></script>
<script src="../dist/js/bootstrap-tab.js"></script>
<script src="../dist/js/sidebarMenu.js"></script>-->

<script type="text/javascript">

    $(function () {
//        console.log(window.location);

        App.setbasePath("${ctx}");
        App.setGlobalImgPath("/assets/lte/img/");

        addTabs({
            id: '10008',
            title: 'index',
            close: false,
            url: '/admin/welcome',
            urlType: "relative"
        });

        App.fixIframeCotent();
        // 动态创建菜单后，可以重新计算 SlimScroll
        // $.AdminLTE.layout.fixSidebar();

        /*if ($.AdminLTE.options.sidebarSlimScroll) {
            if (typeof $.fn.slimScroll != 'undefined') {
                //Destroy if it exists
                var $sidebar = $(".sidebar");
                $sidebar.slimScroll({destroy: true}).height("auto");
                //Add slimscroll
                $sidebar.slimscroll({
                    height: ($(window).height() - $(".main-header").height()) + "px",
                    color: "rgba(0,0,0,0.2)",
                    size: "3px"
                });
            }
        }*/
        initMenu();
    });

    function initMenu(){
        $.post("${ctx}/admin/common/nav", function(data) {
            var menusJson = convert(data.menuList);
            console.log(menusJson);
            $('.sidebar-menu').sidebarMenu({data: menusJson});
        }, "JSON").error(function() {
            alert("提示", "获取菜单出错,请重新登陆!");
        });
    }


    function updatePwd(){
        layer.open({
            type: 2,
            title: "Change Password",
            shadeClose: false, //点击遮罩关闭层
            area : ["600px" ,"400px"],
            content: "${ctx}/admin/user/toUpdatePwd"
            ,btn: ['Update', 'Cancel']
            ,yes: function(index, layero){
                var body = layer.getChildFrame('body', index);
                var f = body.find("#upd_form");
                f.bootstrapValidator('validate');
                if (f.data('bootstrapValidator').isValid()) {
                    $.ajax({
                        url:'${ctx}/admin/user/updatePwd',
                        method:"Post",
                        data:f.serialize(),
                        success:function(msg){
                            if(msg.code == 0){
                                layer.alert("Change Password Successfully",{icon:1});
                                layer.close(index);
                            }else{
                                layer.alert(msg.msg,{icon:2});
                            }
                            layer.close(index);
                        }
                    });
                }
            }

        });

    }


    function updatePwd() {
        layer.open({
            type: 2,
            title: "Change Password",
            shadeClose: false, //点击遮罩关闭层
            area: ["600px", "400px"],
            content: "${ctx}/mch/basic/user/toUpdatePwd?id= ${(shiroUser.id)!}"
            , btn: ['Update', 'Cancel']
            , yes: function (index, layero) {
                var body = layer.getChildFrame('body', index);
                var f = body.find("#upd_form");
                f.bootstrapValidator('validate');
                if (f.data('bootstrapValidator').isValid()) {
                    $.ajax({
                        url: '${ctx}/mch/basic/user/updatePwd',
                        method: "Post",
                        data: f.serialize(),
                        success: function (msg) {
                            if (msg.code == 0) {
                                layer.alert("Change Password Successfully", {icon: 1});
                                layer.close(index);
                            } else {
                                layer.alert(msg.msg, {icon: 2});
                            }
                        }
                    });
                }
            }

        });
    }

    function logout() {
        window.open("${ctx}/admin/logout", "_top");
    }
</script>

</body>
</html>