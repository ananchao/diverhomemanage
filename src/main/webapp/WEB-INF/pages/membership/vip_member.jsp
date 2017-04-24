<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>VIP会员</title>
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
									<label for="name">姓名</label> <input type="text"
										class="form-control" id="name" placeholder="输入要查询的姓名">
								</div>
								<div class="form-group">
									<label for="phone">手机号</label> <input type="text"
										class="form-control" id="phone" placeholder="输入要查询的手机号">
								</div>
								<button id="search" type="button" class="btn btn-primary btn-sm">查 询</button>
								<button id="reset" type="button" class="btn btn-primary btn-sm">重 置</button>
								<a href="${ctx}/manage/modulepage?page=membership/vip_memberAdd" type="button" class="btn btn-success btn-sm">
									<i class="fa fa-group"></i>
									新建VIP会员
								</a>
							</div>
						</div>
					    <table id="table" data-show-export="true" data-classes="table table-no-bordered" data-mobile-responsive="true"></table>
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
                url: '${ctx}/manage/getVipMemberList',
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
                    field: 'name',
                    title: '姓名',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'age',
                    title: '年龄',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'phone',
                    title: '手机号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'email',
                    title: '邮箱',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'sex',
                    title: '性别',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value){
                    	if(value=="0") 
                    		return "男"; 
                    	else 
                    		return "女";
                    }
                }, {
                    field: 'qualification',
                    title: '潜水资质',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value){
                    	if(value=="0") 
                    		return "自由潜水员"; 
                    	else if(value=="1") 
                    		return "水肺潜水员";
                    	else
                    		return "非潜水员";
                    }
                }, {
                    field: 'area_name',
                    title: '所在区域',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'join_way',
                    title: '报名方式',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value){
                    	if(value=="0") 
                    		return "现场报名"; 
                    	else
                    		return "微信";
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
        		$('#name').val('');
        		$('#phone').val('');
        	})
        });
        
     	// 自定义传递给服务端的字段
		function queryParams(params) {
			var pageSize=params.limit;
			var pageNumber=params.offset/pageSize+1;
			return {
				startLimit: (pageNumber-1)*pageSize,
				pageSize: pageSize,
				name: $('#name').val(),
				phone: $('#phone').val(),
				membership_level: '1'
			};
		}
    </script>
</body>
</html>
