<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link rel="stylesheet" type="text/css" href="${CONTEXT_PATH}/resources/admin/login/blue/css/login.css">
<script src="${CONTEXT_PATH}/resources/admin/jquery-easyui/jquery-1.8.0.min.js" type="text/javascript"></script>
<script language="javascript">
	$(function(){
	 	if (window != top) {
			top.location.href = location.href;
		}
		
	  	$('#captcha_img').click(function(){
             $('#captcha_img').attr("src", "${CONTEXT_PATH}/admin/captcha?seed="+Math.random());
      	});
          
    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		
		$(window).resize(function(){  
    		$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    	});  
	});  
</script> 
</head>
<body style="background-color:#1c77ac; background-image:url(''); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  

	<div class="logintop">    
    	<span>欢迎登录后台管理系统</span>    
    </div>
    
    <div class="loginbody">
    	<span class="systemlogo"></span> 
    	<div class="loginbox">
		    <form id="loginform" class="form-vertical"  action="${CONTEXT_PATH}/admin/validateLogin" method="post">
			    <ul>
			    	<li>用户名：<input id="username" name="username" type="text" class="input" value="admin" maxlength="20"/></li>
			    	<li>密&nbsp;&nbsp;&nbsp;码：<input id="password" name="password" type="password" class="input" value="12345678" maxlength="20"/></li>
					<li>验证码：<input id="captcha_key" name="captcha" type="text" style="width:170px;" class="input" maxlength="4"/>
						<img src="${CONTEXT_PATH}/admin/captcha" id="captcha_img" style=" height:39px;cursor:pointer; vertical-align:middle" title="点击更换图片"/>
					</li>
			    	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    		<input id="loginBtn" type="submit" class="loginbtn" value="登录"/>
			    	</li>
			    </ul>
		    </form>
	    	<#if shiroLoginFailure?exists>
				<#assign error=shiroLoginFailure/>
			</#if>
	        <#if error?exists>
	        <div id=ValidationSummary1 style="color:red; margin-left:310px; padding-bottom:20px;">
	            <strong>提示：</strong>
	            <#if error?contains("AuthenticationException")>
	            	请检查您输入的账户、密码、验证码是否正确.
					<#elseif error?contains("UnknownAccountException")>
					用户不存在.
					<#elseif error?contains("LockedAccountException")>
					账号被锁定.
					<#elseif error?contains("DisabledAccountException")>
					 未经审核的账户不允许登录.
					<#elseif error?contains("IncorrectCaptchaException")>
					  验证码错误.
					<#else>
					登录失败，请重试.
	            </#if>
	        </div>
	        </#if>
    	</div>
    </div>
    <div class="loginbm"></div>
</body>
</html>
	<script type="text/javascript">
		function checkLogin(){
	    	if($("#username").val()==''){
	    		$("#username").focus();
	    	}else if($("#password").val()==''){
	    		$("#password").focus();
	    	}else if($("#captcha_key").val()==''){
	    		$("#captcha_key").focus();
	    	}else{
	    		$("#loginform").submit();
	    	}		
		}
		$(function(){
			$("#username").focus();
			
			$(document).keydown(function(event){
				if(event.keyCode == 13){
					checkLogin();
			    	event.preventDefault();
				}
			}); 
			
			$('#loginBtn').click(function(event){
				checkLogin();
	    		event.preventDefault();		
			}); 						
        });
	</script>