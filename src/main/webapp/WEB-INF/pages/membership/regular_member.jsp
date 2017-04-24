<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>普通会员</title>
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
							    <label for="phone">手机号</label>
							    <input type="text" class="form-control" id="phone" placeholder="输入要查询的手机号">
							  </div>
							  <button id="search" type="button" class="btn btn-primary btn-sm">查 询</button>
							  <button id="reset" type="button" class="btn btn-primary btn-sm">重 置</button>
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
        	$('#table').bootstrapTable({
        		url: '${ctx}/manage/getRegularMemberList',
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
				},  {
                    field: 'phone',
                    title: '手机号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'visit_count',
                    title: '微信访问次数',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'update_time',
                    title: '最近一次访问时间',
                    align: 'center',
                    valign: 'middle'
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
				phone: $('#phone').val(),
				membership_level: '0'
			};
		}
    </script>
</body>
</html>
