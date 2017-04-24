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
                    <div>
                        <div class="ibox-content profile-content">
                        	<div class="product-detail-item">
                        		<h4 class="h-inline">订单号：</h4>
                            	<p id="order_no" class="product-detail-intent"></p>
                        	</div>
                        	<div class="product-detail-item">
	                            <h4 class="h-inline">状态：</h4>
	                            <p id="order_state" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">验证码：</h4>
	                            <p id="verification_code" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">订单价格：</h4>
	                            <p id="order_price" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">下单时间：</h4>
	                            <p id="order_insert_time" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">订单验证时间：</h4>
	                            <p id="validate_time" class="product-detail-intent"></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div class="wrapper">
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
	                            <p id="product_state" class="product-detail-intent"></p>
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
    
    <div class="wrapper">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div>
                        <div class="ibox-content profile-content">
                        	<div class="product-detail-item">
                        		<h4 class="h-inline">姓名：</h4>
                            	<p id="name" class="product-detail-intent"></p>
                        	</div>
                        	<div class="product-detail-item">
	                            <h4 class="h-inline">年龄：</h4>
	                            <p id="age" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">手机号：</h4>
	                            <p id="phone" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">邮箱：</h4>
	                            <p id="email" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">性别：</h4>
	                            <p id="sex" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">潜水资质：</h4>
	                            <p id="qualification" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">地区：</h4>
	                            <p id="area_name" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">报名方式：</h4>
	                            <p id="join_way" class="product-detail-intent"></p>
                            </div>
                            <div class="product-detail-item">
	                            <h4 class="h-inline">创建时间：</h4>
	                            <p id="member_insert_time" class="product-detail-intent"></p>
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
        	// 根据id获取订单详情
        	$.post("${ctx}/manage/getOrderInfo", {id:'${id}'}).then(function(res){
            	if(res.status==1){ // 成功
            		var item = res.data;
            		$('#order_no').html(item.order_no);
            		var order_state = item.state=='0'?'待验证':item.state=='1'?'已验证':'已删除';
            		$('#order_state').html(order_state);
            		$('#verification_code').html(item.verification_code);
            		$('#order_price').html(item.order_price);
            		$('#order_insert_time').html(item.insert_time);
            		$('#validate_time').html(item.validate_time);
            		
            	
           			$('#title').html(item.product.title);
           			$('#brand').html(item.product.brand);
           			$('#img_url').attr('src', item.product.img_url);
           			$('#original_price').html(item.product.original_price);
           			$('#member_price').html(item.product.member_price);
           			$('#description').html(item.product.description);
           			var type = item.product.type=='0'?'课程':'商品';
           			$('#type').html(type);
           			if(item.product.type=='0'){
           				// 课程没有数量选项
           				$('#quantity_div').hide();
           			}else{
           				$('#quantity').html(item.product.quantity);
           			}
           			$('#label').html(item.product.label);
           			var product_state = item.product.state=='1'?'已上架':item.product.state=='0'?'未上架':'已删除';
           			$('#product_state').html(product_state);
           			$('#insert_time').html(item.product.insert_time);
           			
           			$('#name').html(item.memberShip.name);
            		$('#age').html(item.memberShip.age);
            		var qualification = item.memberShip.qualification=='0'?'自由潜水员':item.memberShip.qualification=='1'?'水肺潜水员':'非潜水员';
            		$('#qualification').html(qualification);
            		$('#phone').html(item.memberShip.phone);
            		$('#email').html(item.memberShip.email);
            		var sex = item.memberShip.sex=='0'?'男':'女'
            		$('#sex').html(sex);
            		$('#area_name').html(item.memberShip.area_name);
            		var join_way = item.memberShip.join_way=='0'?'现场报名':'微信'
            		$('#join_way').html(join_way);
            		$('#member_insert_time').html(item.memberShip.insert_time);
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
