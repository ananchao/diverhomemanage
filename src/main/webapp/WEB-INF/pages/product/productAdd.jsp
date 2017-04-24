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
    <link href="${ctx}/static/css/plugins/cropper/cropper.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/ionRangeSlider/ion.rangeSlider.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/ionRangeSlider/ion.rangeSlider.skinFlat.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>创建新${typeName}</h5>
                        <div class="ibox-tools">
                            <a class="close-link" href="${ctx}/manage/modulepage?page=product/product">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="addForm" method="post" action="${ctx}/manage/addProduct">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">标题：</label>
                                <div class="col-sm-7">
                                    <input id="title" name="title" maxlength="40" type="text" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">品牌：</label>
                                <div class="col-sm-7">
                                    <input id="brand" name="brand" maxlength="40" type="text" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">原价：</label>
                                <div class="col-sm-7">
                                	<div class="input-group">
                                		<span class="input-group-addon">&yen;</span>
                                		<input id="original_price" name="original_price" type="text" class="form-control" required>
                                	</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">会员价：</label>
                                <div class="col-sm-7">
                                	<div class="input-group">
                                		<span class="input-group-addon">&yen;</span>
                                		<input id="member_price" name="member_price" type="text" class="form-control" required>
                                	</div>
                                </div>
                            </div>
                            <c:if test="${type == 1}">
							   <div class="form-group">
	                                <label class="col-sm-3 control-label">数量：</label>
	                                 <div class="col-sm-7">
	                                	<input type="text" id="quantity" name="quantity" value="1" />
	                                </div>
	                            </div>
							</c:if>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">描述：</label>
                                <div class="col-sm-7">
                                    <textarea rows="5" maxlength="300" id="description" name="description" class="form-control" required></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类别：</label>
                                <div class="col-sm-7">
                                	<!-- 表单提交的时候disabled的字段不会传递到后台 -->
                                    <input id="type_disabled" name="type_disabled" type="text" value="${typeName}" disabled="" class="form-control">
                                    <!-- 隐藏input存放产品类别 -->
                        			<input id="type" name="type" type="text" value="${type}" class="hidden">
                        			<input id="typeName" name="typeName" type="text" value="${typeName}" class="hidden">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">标签：</label>
                                <div class="col-sm-7">
                                    <input id="label" name="label" maxlength="40" type="text" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">状态：</label>
                                <div class="col-sm-7">
                                	<select class="form-control m-b" name="state" id="state">
                                        <option value="1">上架</option>
                                        <option value="0">下架</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                               <div class="col-md-8">
                               		<div class="btn-group" style="margin-bottom: 2px;">
	                                    <label title="上传图片" for="inputImage" class="btn btn-primary">
	                                    	<!-- accept不要使用image/* 在chrome下会卡 -->
	                                        <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="file" id="inputImage" class="hide"> 上传新图片
	                                    </label>
	                                </div>
	                                <div class="image-crop">
	                                    <img src="">
	                                </div>
	                            </div>
	                            <div class="col-md-4">
	                                <h4 style="line-height: 21px;">图片预览：</h4>
	                                <div class="img-preview" style="height:200px;width:200px;"></div>
	                                <!-- 隐藏input存放图片base64编码数据 -->
                        			<input id="img_url" name="img_url" type="text" value="" class="hidden">
                        			<input id="thumbnail_img_url" name="thumbnail_img_url" type="text" value="" class="hidden">
	                            </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12" style="text-align: center;">
                                    <button class="btn btn-primary" onclick="addProduct()" type="button">创 建</button>&nbsp;&nbsp;
                                    <a class="btn btn-white" href="${ctx}/manage/modulepage?page=product/product" type="button">返 回</a>
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
    <script src="${ctx}/static/js/plugins/cropper/cropper.min.js"></script>
    <script src="${ctx}/static/js/plugins/ionRangeSlider/ion.rangeSlider.min.js"></script>
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
			    	brand: "required",
			    	original_price: {
			    		required: true,
			    		number:true
			    	},
			    	member_price: {
			    		required: true,
			    		number:true
			    	},
			    	description: "required",
			    	label: "required",
			    }
		 	})
        	
        	var $inputImage = $("#inputImage");
        	var $image = $(".image-crop > img")
            $($image).cropper({
                aspectRatio: 1,
                preview: ".img-preview",
                done: function (data) {
                    // 输出结果
                }
            });
            if (window.FileReader) {
                $inputImage.change(function () {
                    var fileReader = new FileReader(),
                        files = this.files,
                        file;

                    if (!files.length) {
                        return;
                    }

                    file = files[0];

                    if (/^image\/\w+$/.test(file.type)) {
                        fileReader.readAsDataURL(file);
                        fileReader.onload = function () {
                            $inputImage.val("");
                            $image.cropper("reset", true).cropper("replace", this.result);
                        };
                    } else {
                    	layer.alert("请选择图片文件");
                    }
                });
            } else {
                $inputImage.addClass("hide");
            }
            
        	$("#quantity").ionRangeSlider({
                min: 0,
                max: 100,
                type: 'single',
                postfix: " 件",
                maxPostfix: "+",
                prettify: false,
                hasGrid: true,
                grid: true
            });
        	var quantity = $("#quantity").data("ionRangeSlider");
        });
        
        function addProduct() {
        	// 触发校验
        	if($('#addForm').valid()){
        		// 提交前询问
            	layer.confirm('确定创建新${typeName}吗？', {
            		  btn: ['确定','取消'] //按钮
            		}, function(){
            			var $image = $(".image-crop > img")
            			// 原尺寸，用于在管理系统展示
                    	var imgData = $image.cropper("getDataURL");
            			// 缩略图尺寸，用于在微信端展示
            			var thumbnailImgData = $image.cropper("getDataURL", {height:120,width:120});
                    	if(imgData){
                    		$('#img_url').val(imgData);
                    	}
                    	if(thumbnailImgData){
                    		$('#thumbnail_img_url').val(thumbnailImgData);
                    	}
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
