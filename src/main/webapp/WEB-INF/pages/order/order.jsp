<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>订单管理</title>
    <%@ include file="../common/include.jsp"%>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/static/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">
	<link href="${ctx}/static/css/myStyle.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                    	<div id="toolbar" class="myToolbar">
							<div class="form-inline">
								<div class="form-group">
									<label for="state">订单状态</label>
									<select class="form-control" name="state" id="state">
                                        <option value="">全部</option>
                                        <option value="0">待验证</option>
                                        <option value="1">已验证</option>
                                        <option value="2">已删除</option>
                                    </select>
								</div>
								<div class="form-group">
									<label for="verification_code">验证码</label>
									<input type="text" class="form-control" id="verification_code" placeholder="输入验证码">
								</div>
								<div class="form-group">
									<label for="member_name">会员姓名</label>
									<input type="text" class="form-control" id="member_name" placeholder="输入会员姓名">
								</div>
								<div class="form-group">
									<label for="member_phone">手机号</label>
									<input type="text" class="form-control" id="member_phone" placeholder="输入手机号">
								</div>
								<button id="search" type="button" class="btn btn-primary btn-sm">查 询</button>
								<button id="reset" type="button" class="btn btn-primary btn-sm">重 置</button>
							</div>
						</div>
					    <table id="table" data-unique-id="id" data-show-export="true" data-classes="table table-no-bordered" data-mobile-responsive="true"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/plugins/layer/layer.min.js"></script>
    <!-- Bootstrap table -->
    <script src="${ctx}/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctx}/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctx}/static/js/plugins/bootstrap-table/bootstrap-table-export.min.js"></script>
    <script src="${ctx}/static/js/plugins/bootstrap-table/tableExport.min.js"></script>
    <script src="${ctx}/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <!-- 自定义js -->
    <script src="${ctx}/static/js/content.js"></script>
    <script>
    	var $table = $('#table');
        $(document).ready(function () {
        	$table.bootstrapTable({
                url: '${ctx}/manage/getOrderList',
                pagination: true,
                toolbar: '#toolbar',
                striped:true,
                sidePagination:'server',
                pageList: [ 10, 50, 'ALL' ],
                pageSize:10,
                method: 'post',
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                queryParams: queryParams,
                columns: [{
                	align: 'center',
                    valign: 'middle',
				    formatter: function (value, row, index) {  
				        return index+1;  
				    }  
				}, {
                    field: 'order_no',
                    title: '订单号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'memberShip.name',
                    title: '姓名',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'memberShip.phone',
                    title: '手机号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'product.title',
                    title: '产品名称',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'product.type',
                    title: '产品类别',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value){
                    	if(value=="0") 
                    		return "课程"; 
                    	else 
                    		return "商品";
                    }
                }, {
                    field: 'insert_time',
                    title: '下单时间',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'validate_time',
                    title: '验证时间',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'verification_code',
                    title: '验证码',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'state',
                    title: '订单状态',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value){
                    	if(value=="0") 
                    		return "<span class='label label-warning'>待验证</span>"; 
                    	else if(value=='1')
                    		return "<span class='label label-primary'>已验证</span>"; 
                    	else
                    		return "<span class='label label-danger'>已删除</span>"; 
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(id){
                    	var state=$table.bootstrapTable('getRowByUniqueId', id).state;
                    	if(state=='0')
                    		return '<div class="btn-group"><button data-toggle="dropdown" class="btn btn-success btn-xs dropdown-toggle">操作 <span class="caret"></span></button><ul class="dropdown-menu"><li><a href="javascript:void(0);" onclick="getOrderInfo(\''+id+'\')">查看详情</a></li><li><a href="javascript:void(0);" onclick="updateOrderState(\''+id+'\',\'1\')">验证订单</a></li><li><a href="javascript:void(0);" onclick="updateOrderState(\''+id+'\',\'2\')">删除订单</a></li></ul></div>';
                    	else if(state=='1')
                    		return '<div class="btn-group"><button data-toggle="dropdown" class="btn btn-success btn-xs dropdown-toggle">操作 <span class="caret"></span></button><ul class="dropdown-menu"><li><a href="javascript:void(0);" onclick="getOrderInfo(\''+id+'\')">查看详情</a></li></ul></div>';
                    	return;
                    }
                }]
            });
        	
        	$table.on('load-success.bs.table', function (e, data) {
                $("[aria-label='export type']").addClass('btn-white');
                $(".page-size").parent().addClass('btn-white');
            })
        	
        	$("#search").click(function(){
        		$table.bootstrapTable('refresh');
        	})
        	
        	$('#reset').click(function(){
        		$('#state').val('');
        		$('#verification_code').val('');
        		$('#member_name').val('');
        		$('#member_phone').val('');
        	})
        });
        
     	// 自定义传递给服务端的字段
		function queryParams(params) {
			var pageSize=params.limit;
			var pageNumber=params.offset/pageSize+1;
			return {
				startLimit: (pageNumber-1)*pageSize,
				pageSize: pageSize,
				state: $('#state').val(),
				verification_code: $('#verification_code').val(),
				member_name: $('#member_name').val(),
				member_phone: $('#member_phone').val()
			};
		}
     	
     	// 更新订单状态
     	function updateOrderState(id,state){
     		var desc;
     		if(state=='1')
     			desc='验证';
     		else
     			desc='删除';
     		// 提交前询问
        	layer.confirm('确定'+desc+'订单吗？', {
        		  btn: ['确定','取消'] //按钮
        		}, function(){
        			$.post("${ctx}/manage/updateOrderState", {id:id,state:state}).then(function(res){
                    	if(res.status==1){ // 成功
                    		layer.alert(desc+"成功！");
                    		// 刷新列表
                    		$table.bootstrapTable('refresh');
                    	}else{
                    		// 失败
                    		layer.alert(res.message);
                    	}
                    },function(){
                    	layer.alert('网络出现异常！');
                    });
        		}, function(){
        	});
     		return false;
     	}
     	
     	// 查看订单详情
     	function getOrderInfo(id){
     		//弹出订单详情iframe层
        	layer.open({
        	  type: 2,
        	  title: '订单详情',
        	  shadeClose: true,
        	  shade: 0.8,
        	  area: ['600px', '90%'],
        	  content: '${ctx}/manage/modulepage?page=order/orderDetail&id='+id //iframe的url
        	}); 
     		return false;
     	}
    </script>
</body>
</html>
