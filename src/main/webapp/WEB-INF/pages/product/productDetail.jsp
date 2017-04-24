<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>产品详情</title>
    <%@ include file="../common/include.jsp"%>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">
    <link href="${ctx}/static/css/myStyle.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5 id="title"></h5>
                    </div>
                    <div>
                        <div class="ibox-content no-padding border-left-right">
                            <img class="img-responsive marginCenter" id="img_url">
                        </div>
                        <div class="ibox-content profile-content">
                        	<div class="product-detail-item">
                        		<h4 class="h-inline">品牌：</h4>
                            	<p id="brand" class="product-detail-intent"></p>
                        	</div>
                        	<div class="product-detail-item">
	                            <h4 class="h-inline">原价：</h4>
	                            <p id="original_price" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">会员价：</h4>
	                            <p id="member_price" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">描述：</h4>
	                            <p id="description" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item" id="quantity_div">
	                            <h4 class="h-inline">数量：</h4>
	                            <p id="quantity" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">类别：</h4>
	                            <p id="type" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">标签：</h4>
	                            <p id="label" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">状态：</h4>
	                            <p id="state" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">创建时间：</h4>
	                            <p id="insert_time" class="product-detail-intent"></p>
                            </div>
                        </div>
                    </div>
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
        $(document).ready(function () {
        	// 根据id获取产品详情
        	$.post("${ctx}/manage/getProductById", {id:'${id}'}).then(function(res){
            	if(res.status==1){ // 成功
            		var item = res.data;
           			$('#title').html(item.title);
           			$('#brand').html(item.brand);
           			$('#img_url').attr('src', item.img_url);
           			$('#original_price').html(item.original_price);
           			$('#member_price').html(item.member_price);
           			$('#description').html(item.description);
           			var type = item.type=='0'?'课程':'商品';
           			$('#type').html(type);
           			if(item.type=='0'){
           				// 课程没有数量选项
           				$('#quantity_div').hide();
           			}else{
           				$('#quantity').html(item.quantity);
           			}
           			$('#label').html(item.label);
           			var state = item.state=='1'?'已上架':item.state=='0'?'未上架':'已删除';
           			$('#state').html(state);
           			$('#insert_time').html(item.insert_time);
            	}else{
            		// 失败
            		layer.alert(res.message);
            	}
            },function(){
            	layer.alert('网络出现异常！');
            });
        });
    </script>
</body>

</html>
