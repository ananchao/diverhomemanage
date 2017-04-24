<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>登录</title>
    <%@ include file="./common/include.jsp"%>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/static/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">
    <link href="${ctx}/static/css/login.css" rel="stylesheet">
    <link href="${ctx}/static/css/myStyle.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>潜水者之家管理系统</h1>
                    </div>
                    <div class="m-b"></div>
                    <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 员工管理</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 会员管理</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 订单管理</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 产品管理</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 发布公告</li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-5">
                <form method="post" action="${ctx}/manage/login_login" id="loginForm">
                    <h4 class="no-margins">登录：</h4>
                    <input type="text" class="form-control uname m-b" name="username" placeholder="用户名" required/>
                    <input type="password" class="form-control pword m-b" name="password" placeholder="密码" required/>
                    <c:if test="${loginError != null}">
					   <div class="login-error">${loginError}</div>
					</c:if>
                    <a href="" style="display:block;">忘记密码了？</a>
                    <button class="btn btn-success btn-block">登录</button>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2017 All Rights Reserved. DriverHome
            </div>
        </div>
    </div>
    
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/static/js/plugins/validate/messages_zh.min.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		 $("#loginForm").validate({
    			    rules: {
    			      	username: "required",
    			      	password: "required",
    			    },
    			    messages: {
    			      	username: "请输入用户名",
    			    	password: "请输入密码"
    			    }
    		 })
		});
	</script>
</body>

</html>
