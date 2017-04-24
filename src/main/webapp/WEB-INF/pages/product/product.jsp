<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>产品管理</title>
    <%@ include file="../common/include.jsp"%>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.min.css" rel="stylesheet">
	<link href="${ctx}/static/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">
    <link href="${ctx}/static/css/myStyle.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="file-manager">
                            <h5>显示：</h5>
                            <a href="javascript:void(0);" class="file-control active">所有</a>
                            <a href="javascript:void(0);" class="file-control" type="0">课程</a>
                            <a href="javascript:void(0);" class="file-control" type="1">商品</a>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
							  <div class="col-sm-6"><a href="${ctx}/manage/modulepage?page=product/productAdd&type=0&typeName=课程" class="btn btn-primary btn-block">创建新课程</a></div>
							  <div class="col-sm-6"><a href="${ctx}/manage/modulepage?page=product/productAdd&type=1&typeName=商品" class="btn btn-primary btn-block">创建新商品</a></div>
							</div>
							<div class="hr-line-dashed"></div>
							<h5 class="tag-title">状态</h5>
							<ul class="tag-list clearfix" id="state" style="padding: 0">
                                <li><a href="javascript:void(0);" state="1">已上架</a>
                                </li>
                                <li><a href="javascript:void(0);" state="0">未上架</a>
                                </li>
                                <li><a href="javascript:void(0);" state="2">已删除</a>
                                </li>
                            </ul>
                            
                            <div class="hr-line-dashed"></div>
                            <h5 class="tag-title">标签</h5>
                            <ul class="tag-list" id="label" style="padding: 0">
                            </ul>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-9 animated fadeInRight">
                <div class="row">
                    <div class="col-sm-12" id="productList">
                    	<!-- jQuery循环添加file-box -->
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
        	// 进入页面时获取所有产品，包括已上架，未上架和已删除
            getProductList({}, true, function(){
            	$('.file-box').each(function () {
                    animationHover(this, 'pulse');
                });
            })
        	
        	$('.file-control').click(function(){
        		// 设置选中时的样式
        		$('.file-control').removeClass('active');
        		$(this).addClass('active');
        		// 获取选项对应的产品（所有，课程，商品）
        		getProductList({
        			type: $(".file-control.active").attr('type'), 
            		state: $("#state a.my-btn-primary").attr('state')},
            		true,
        			function(){
            			// 设置动画效果
                		$('.file-box').each(function () {
                        	animationHover(this, 'pulse');
                    	});
                	}
            	);
        		return false;
        	})
        	
        	// 状态选项
        	$('#state a').click(function(){
        		var flag = $(this).hasClass('my-btn-primary');
        		$('#state a').removeClass('my-btn-primary');
        		if(flag)
        			$(this).removeClass('my-btn-primary');
        		else
        			$(this).addClass('my-btn-primary');
        		// 获取选项对应的产品（所有，课程，商品）
        		getProductList({
        			type: $(".file-control.active").attr('type'), 
        			state: $("#state a.my-btn-primary").attr('state'),
        			label: $('#label a.my-btn-primary').attr('label')},
        			true,
        			function(){
        				// 设置动画效果
	                	$('.file-box').each(function () {
	                        animationHover(this, 'pulse');
	                    });
	            	},
	            	function(){
	            		
	            	}
        		);
	        	return false;
        	})
        	
        });
        
        function getProductList(data,setLabel,cb){
        	$.post("${ctx}/manage/getProductList", data).then(function(res){
            	if(res.status==1){ // 成功
            		// 清空产品列表
            		$('#productList').empty();
            		var labelArray=[];
            		$(res.data).each(function(index, item){
            			// 将产品添加到页面
            			var img = item.img_url?'<img alt="image" class="img-responsive" src="'
            					+item.img_url+'">':'<div class="icon"><i class="fa fa-file"></i></div>';
            			var state = item.state=='1'?'已上架':item.state=='0'?'未上架':'已删除';
            			$('<div class="file-box"><div class="file"><a href="javascript:void(0);" onclick="productDetail(\''
            					+item.id+'\')"><span class="corner"></span><div class="image">'
            					+img+'</div><div class="file-name">'
            					+item.title+'<a href="${ctx}/manage/modulepage?page=product/productEdit&id='
            					+item.id+'" style="float:right;">编辑</a><br/><small>状态：'
            					+state+'</small><br/><small>创建时间：'
            					+item.insert_time+'</small></div></a></div></div>').appendTo('#productList');
            			if(setLabel){
            				labelArray.push(item.label);
            			}
	            	})
	            	if(setLabel){
            			// 清空产品列表
            			$('#label').empty();
            			// 标签去重
            			var uniqueArray = labelArray.unique();
            			for(var i=0;i<uniqueArray.length;i++){
            				// 显示和状态切换的时候要动态设置标签
            				$('<li><a href="javascript:void(0);" label="'+uniqueArray[i]+'">'+uniqueArray[i]+'</a></li>').appendTo('#label');
            			}
            			// 给标签添加click监听
                    	addLabelListener();
            		}
	            	// 如果有回调函数则执行回调函数
	            	cb && cb()
            	}else{
            		// 失败
            		layer.alert(res.message);
            	}
            },function(){
            	layer.alert('网络出现异常！');
            });
        }
        
        // 数组去重
        Array.prototype.unique = function(){
       	 	var res = [];
       	 	var json = {};
       	 	for(var i = 0; i < this.length; i++){
       	  		if(!json[this[i]]){
       	   			res.push(this[i]);
       	   			json[this[i]] = 1;
       	  		}
       	 	}
       	 return res;
       }
        
        function addLabelListener(){
        	// 标签选项
        	$('#label a').click(function(){
        		var flag = $(this).hasClass('my-btn-primary');
        		$('#label a').removeClass('my-btn-primary');
        		if(flag)
        			$(this).removeClass('my-btn-primary');
        		else
        			$(this).addClass('my-btn-primary');
        		// 获取选项对应的产品（所有，课程，商品）
        		getProductList({
        			type: $(".file-control.active").attr('type'), 
        			state: $("#state a.my-btn-primary").attr('state'),
        			label: $('#label a.my-btn-primary').attr('label')},
        			false,
        			function(){
        				// 设置动画效果
	                	$('.file-box').each(function () {
	                        animationHover(this, 'pulse');
	                    });
	            	}
        		);
	        	return false;
        	})
        }
        
        // 查看产品详情
        function productDetail(id){
        	//弹出产品详情iframe层
        	layer.open({
        	  type: 2,
        	  title: '产品详情',
        	  shadeClose: true,
        	  shade: 0.8,
        	  area: ['600px', '90%'],
        	  content: '${ctx}/manage/modulepage?page=product/productDetail&id='+id //iframe的url
        	}); 
        }
    </script>
</body>

</html>
