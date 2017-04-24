<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>其他人员</title>
    <%@ include file="../common/include.jsp"%>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.css" rel="stylesheet">

    <link href="${ctx}/static/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row" id="coachlist"></div>
    </div>

    <!-- 全局js -->
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/plugins/layer/layer.min.js"></script>
    <!-- 自定义js -->
    <script src="${ctx}/static/js/content.js"></script>
    <script>
        $(document).ready(function () {
            // 获取所有其他员工信息
            $.get("${ctx}/manage/getEmployeeList", {type: '1'}).then(function(res){
            	if(res.status==1){
            		// 成功
            		$(res.data).each(function(index, item){
            			$('<div class="col-sm-4"><div class="contact-box"><div class="col-sm-4"><div class="text-center"><img alt="image" class="img-circle m-t-xs img-responsive" src="${ctx}/static/img/a3.jpg"><div class="m-t-xs font-bold">'
            					+item.position_desc+'</div></div></div><div class="col-sm-8"><h3><strong>'
            					+item.name+'</strong></h3><p><address><span style="white-space:nowrap;">邮箱</span>:'
            					+item.email+'<br>Tel:'+item.phone+'</address></div><div class="clearfix"></div></div></div>').appendTo('#coachlist')
	            	})
            	}else{
            		// 失败
            		layer.alert(res.message);
            	}
            	$('.contact-box').each(function () {
                    animationHover(this, 'pulse');
                });
            },function(){
            	layer.alert('网络出现异常！');
            });
        });
    </script>
</body>
</html>
