<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>新建VIP会员</title>
    <%@ include file="../common/include.jsp"%>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>新建VIP会员</h5>
                        <div class="ibox-tools">
                            <a class="close-link" href="${ctx}/manage/modulepage?page=membership/vip_member">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="vipForm" method="post" action="${ctx}/manage/addVipMember">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓名：</label>
                                <div class="col-sm-7">
                                    <input id="name" name="name" maxlength="10" type="text" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">年龄：</label>
                                <div class="col-sm-7">
                                    <input id="age" name="age" maxlength="2" type="text" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手机号：</label>
                                <div class="col-sm-7">
                                	<input id="phone" name="phone" type="text" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">邮箱：</label>
                                <div class="col-sm-7">
                                	<input id="email" name="email" type="text" class="form-control" required>
                                </div>
                            </div>
						   <div class="form-group">
                                <label class="col-sm-3 control-label">性别：</label>
                                 <div class="col-sm-7">
                                	<select class="form-control m-b" name="sex" id="sex">
                                       <option value="0">男</option>
                                       <option value="1">女</option>
                                   </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">潜水资质：</label>
                                <div class="col-sm-7">
                                    <select class="form-control m-b" name="qualification" id="qualification">
                                       <option value="0">自由潜水员</option>
                                       <option value="1">水肺潜水员</option>
                                       <option value="2">非潜水员</option>
                                   </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">所在区：</label>
                                <div class="col-sm-7">
                                	<select class="form-control m-b" name="area_code" id="area_code">
                                       <option value="01">鼓楼区</option>
                                       <option value="02">玄武区</option>
                                       <option value="03">建邺区</option>
                                       <option value="04">秦淮区</option>
                                       <option value="05">栖霞区</option>
                                       <option value="06">江宁区</option>
                                       <option value="07">雨花台区</option>
                                       <option value="08">浦口区</option>
                                       <option value="09">六合区</option>
                                       <option value="10">溧水区</option>
                                       <option value="10">高淳区</option>
                                   	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12" style="text-align: center;">
                                    <button class="btn btn-primary" onclick="addVipMember()" type="button">保 存</button>&nbsp;&nbsp;
                                    <a class="btn btn-white" href="${ctx}/manage/modulepage?page=membership/vip_member" type="button">返 回</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <!-- 自定义js -->
    <script src="${ctx}/static/js/content.js"></script>
    <!-- jQuery Validation plugin javascript-->
    <script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/static/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${ctx}/static/js/plugins/layer/layer.min.js"></script>
	<script>
		var validator;
        $(document).ready(function () {
        	// 判断请求参数中是否包含addMessage，有就提示用户
        	if('${addMessage}'){
        		layer.alert('${addMessage}');
        	}
        	
        	validator=$("#vipForm").validate({
			    rules: {
			    	name: "required",
			    	age: {
			    		required: true,
			    		digits:true
			    	},
			    	phone: {
			    		required: true,
			    		digits:true
			    	},
			    	email: "required"
			    }
		 	})
        });
        
        function addVipMember() {
        	// 触发校验
        	if($('#vipForm').valid()){
        		// 提交前询问
            	layer.confirm('确定保存吗？', {
            		  btn: ['确定','取消'] //按钮
            		}, function(){
            			$("#vipForm").submit();
            		}, function(){
            	});
        	}else{
        		validator.focusInvalid();
        	}
        	
        }
    </script>
</body>

</html>
