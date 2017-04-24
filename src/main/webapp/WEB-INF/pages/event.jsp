<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="renderer" content="webkit">

    <title>活动报名</title>
	<%@ include file="./common/include.jsp"%>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico"> 
    <link rel="stylesheet" href="${ctx}/static/css/frozen.css">
    <style>
        .demo-desc {
            padding: 15px 10px 10px 15px;
            font-size: 16px;
        }
        .ui-tips {
            padding: 0px 15px 15px 15px !important;
        }
    </style>
</head>
<body>
    <div class="ui-slider" id="slider1" style="height: 70px;">
        <ul class="ui-slider-content" style="width: 300%">
            <li><span style="background-image:url(${ctx}/static/img/event_1.jpg)"></span></li>
            <li><span style="background-image:url(${ctx}/static/img/event_2.jpg)"></span></li>
            <li><span style="background-image:url(${ctx}/static/img/event_3.jpg)"></span></li>
        </ul>
    </div>
    <div class="ui-form ui-border-t">
        <div class="ui-form-item ui-border-b">
            <label>
                姓名
            </label>
            <input type="text" id="name" placeholder="请输入您的真实姓名">
        </div>
        <div class="ui-form-item ui-border-b">
            <label>
                年龄
            </label>
            <input type="number" id="age" placeholder="请输入您的年龄">
        </div>
        <div class="ui-form-item ui-border-b">
            <label>
                邮箱
            </label>
            <input type="text" id="email" placeholder="请输入有效邮箱">
        </div>
        <div class="ui-form-item ui-border-b">
            <label>
                微信号
            </label>
            <input type="text" id="wechat" placeholder="请输入您的微信号">
        </div>
        <div class="ui-form-item ui-form-item-l ui-border-b">
            <label class="ui-border-r">
                中国 +86
            </label>
            <input type="number" id="phone" placeholder="请输入手机号码">
        </div>
        <p class="demo-desc">性别</p>
        <div class="ui-form-item ui-form-item-radio ui-border-b">
            <label class="ui-radio" for="radio">
                <input type="radio" checked name="sex" value="男">
            </label>
            <p>男</p>
        </div>
        <div class="ui-form-item ui-form-item-radio ui-border-b">
            <label class="ui-radio" for="radio">
                <input type="radio" name="sex" value="女">
            </label>
            <p>女</p>
        </div>
        <p class="demo-desc">潜水资质</p>
        <div class="ui-form-item ui-form-item-radio ui-border-b">
            <label class="ui-radio" for="radio">
                <input type="radio" checked name="qualification" value="自由潜水员">
            </label>
            <p>自由潜水员</p>
        </div>
        <div class="ui-form-item ui-form-item-radio ui-border-b">
            <label class="ui-radio" for="radio">
                <input type="radio" name="qualification" value="水肺潜水员">
            </label>
            <p>水肺潜水员</p>
        </div>
        <div class="ui-form-item ui-form-item-radio ui-border-b">
            <label class="ui-radio" for="radio">
                <input type="radio" name="qualification" value="非潜水员">
            </label>
            <p>非潜水员</p>
        </div>
        <div class="ui-btn-wrap">
            <button class="ui-btn-lg ui-btn-primary" id="signBtn">
                立即报名
            </button>
        </div>
        <div class="ui-tips ui-tips-warn">
            <i></i><span style="font-size: 14px !important;">本次活动最终解释权归bestdive所有</span>
        </div>
    </div>

    <div class="ui-dialog">
        <div class="ui-dialog-cnt">
            <div class="ui-dialog-bd">
                <div>
                    <div style="text-align: center;" id="message"></div></div>
            </div>
            <div class="ui-dialog-ft ui-btn-group">
                <button type="button" data-role="button"  class="select" id="dialogButton">关闭</button>
            </div>
        </div>
    </div>

    <script src="${ctx}/static/js/zepto.min.js"></script>
    <script src="${ctx}/static/js/frozen.js"></script>
    <script type="text/javascript">
        (function (){
            var slider = new fz.Scroll('.ui-slider', {
                role: 'slider',
                indicator: true,
                autoplay: true,
                interval: 3000
            });

            slider.on('beforeScrollStart', function(from, to) {
            });

            slider.on('scrollEnd', function(cruPage) {
            });

            var el;
            $('#signBtn').tap(function(){
                if(!$('#name').val()){
                    el=$.tips({
                        content:'请输入您的真实姓名',
                        stayTime:2000,
                        type:"warn"
                    })
                    return;
                }
                if(!$('#age').val()){
                    el=$.tips({
                        content:'请输入您的年龄',
                        stayTime:2000,
                        type:"warn"
                    })
                    return;
                }
                if(!$('#email').val()){
                    el=$.tips({
                        content:'请输入有效邮箱',
                        stayTime:2000,
                        type:"warn"
                    })
                    return;
                }
                if(!$('#wechat').val()){
                    el=$.tips({
                        content:'请输入您的微信号',
                        stayTime:2000,
                        type:"warn"
                    })
                    return;
                }
                if(!$('#phone').val()){
                    el=$.tips({
                        content:'请输入手机号码',
                        stayTime:2000,
                        type:"warn"
                    })
                    return;
                }
                
                var sex;
                $('[name="sex"]').forEach(function(item){
                	if($(item).prop("checked")){
                		sex = $(item).val();
                	}
                	
                })
                var qualification;
                $('[name="qualification"]').forEach(function(item){
                	if($(item).prop("checked")){
                		qualification = $(item).val();
                	}
                })
                var dia=$.dialog({
                    content:'确定报名吗？',
                    button:["确定","取消"]
                });

                var loading;
                dia.on("dialog:action",function(e){
                    if(!e.index){
                    	loading = $.loading({content:'报名中...'});
                    	var data = {
           			  		name: $('#name').val(),
           			  		age: $('#age').val(),
           			  		email: $('#email').val(),
           			  		wechat: $('#wechat').val(),
           			  		phone: $('#phone').val(),
           			  		sex: sex,
           			  		qualification: qualification
           			  	}
                    	$.ajax({
            				type: 'POST',
            			  	dataType: 'json',
            			  	data: data,
            			  	url: '${ctx}/rest/signup',
            			  	success: function(data){
            			  		loading.hide();
            			  		if(data.status==1){
            			  			$('#message').text('恭喜您报名成功！待工作人员确认您的信息后将及时与您联系！');
            			  		}else{
            			  			$('#message').text('啊哦！网络不给力，报名失败，请再次提交！');
            			  		}
            			  		var dia2=$(".ui-dialog").dialog("show");
            			  		if(data.status==1){
            			  			dia2.on("dialog:action",function(e){
            			  				window.location.href=window.location.href+"?id="+10000*Math.random();
                                    });
            			  		}
            			  	},
            			  	error: function(xhr, type){
            			  		loading.hide();
            			  		$('#message').text('啊哦！网络不给力，报名失败，请再次提交！');
            			  		var dia2=$(".ui-dialog").dialog("show");
            				},
            				complete:function(xhr, status){
            					loading && loading.hide();
            				}
            			});
                    }
                });
            })
        })();
    </script>
</body>
</html>
