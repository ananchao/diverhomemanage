<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>发布公告</title>
    <%@ include file="../common/include.jsp"%>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/static/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">
	<link href="${ctx}/static/css/myStyle.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="row">
        <div class="col-sm-12">
            <div class="wrapper wrapper-content animated fadeInRight" id="notices">
                <div class="ibox-content m-b-sm border-bottom">
	                <a href="${ctx}/manage/modulepage?page=notice/noticeAdd" class="btn btn-primary btn-sm">
	                <i class="fa fa-plus"></i> <span class="bold">发布公告</span>
	                </a>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/plugins/layer/layer.min.js"></script>
    <!-- 自定义js -->
    <script src="${ctx}/static/js/content.js"></script>
    <script>
	    var pageSize=10;	// 一页显示10条公告
		var pageNumber=1;
        $(document).ready(function () {
        	// 获取公告列表
        	getNoticeListPage();
        	
        	$(window).scroll(function (e) {
                // DOM文档高度
                var domHeight = $(document).height();
                // 浏览器可视区域高度
                var windowHeight = $(this).height();
                // 浏览器垂直滚动条滚动距离
                var scrollHeight = $(this).scrollTop();
                // 当scrollHeight+windowHeight大于等于domHeight的时候,表示滚动到底部
                if (scrollHeight + windowHeight >= domHeight) {
                	// 获取公告列表
                	getNoticeListPage();
                }
            });
        });
        
     	// 获取公告列表
        function getNoticeListPage() {
        	$.post("${ctx}/manage/getNoticeListPage", {
        		startLimit:(pageNumber-1)*pageSize,
        		pageSize:pageSize}
        	).then(function(res){
            	if(res.status==1){ // 成功
            		$(res.data).each(function(index, item){
            			$('<div class="faq-item"><div class="row"><div class="col-md-12"><a href="javascript:void(0);" class="faq-question">'
            					+item.title+'</a><strong>'
            					+item.content+'</strong> <div><small><i class="fa fa-clock-o"></i> '
            					+item.insert_time+'</small></div></div></div></div>').appendTo('#notices');
            		})
            		// 页数加1
            		pageNumber+=1;
            	}else{
            		// 失败
            		layer.alert(res.message);
            	}
            },function(){
            	layer.alert('网络出现异常！');
            });
        }
    </script>
</body>
</html>
