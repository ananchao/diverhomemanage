<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>新增${typeName}</title>
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
                        <h5>发布新公告</h5>
                        <div class="ibox-tools">
                            <a class="close-link" href="${ctx}/manage/modulepage?page=notice/notice">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="addForm" method="post" action="${ctx}/manage/addNotice">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">标题：</label>
                                <div class="col-sm-7">
                                    <input id="title" name="title" maxlength="40" type="text" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-7">
                                    <textarea rows="5" maxlength="300" id="content" name="content" class="form-control" required></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12" style="text-align: center;">
                                    <button class="btn btn-primary" onclick="addNotice()" type="button">发 布</button>&nbsp;&nbsp;
                                    <a class="btn btn-white" href="${ctx}/manage/modulepage?page=notice/notice" type="button">返 回</a>
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
        	
        	validator=$("#addForm").validate({
			    rules: {
			    	title: "required",
			    	content: "required"
			    }
		 	})
        	
        });
        
        function addNotice() {
        	// 触发校验
        	if($('#addForm').valid()){
        		// 提交前询问
            	layer.confirm('确定发布新公告吗？', {
            		  btn: ['确定','取消'] //按钮
            		}, function(){
            			$("#addForm").submit();
            		}, function(){
            	});
        	}else{
        		validator.focusInvalid();
        	}
        	
        }
    </script>
</body>

</html>
