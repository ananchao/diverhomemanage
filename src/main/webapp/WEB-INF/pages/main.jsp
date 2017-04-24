<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
    <%@ include file="./common/include.jsp"%>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/static/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">
	<link href="${ctx}/static/css/myStyle.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <span class="label label-info pull-right"><i class="fa fa-file-text-o"></i></span>
                        <h5>总订单数</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins font-bold" id="orderTotal"></h1>
                        <div class="stat-percent font-bold text-info">占比：<span id="validatedOrderRate"></span>%</i>
                        </div>
                        <div class="font-bold text-info"><small>已验证订单数：<span id="validatedOrderTotal"></span></small></div>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                    	<span class="label label-info pull-right"><i class="fa fa-group"></i></span>
                        <h5>会员总数</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins font-bold" id="memberTotal"></h1>
                        <div class="stat-percent font-bold text-info">占比：<span id="vipRate"></span>%</i>
                        </div>
                        <div class="font-bold text-info"><small>VIP会员：<span id="vipMemberTotal"></span></small></div>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <span class="label label-info pull-right"><i class="fa fa-male"></i> <i class="fa fa-female"></i></span>
                        <h5>性别比例</h5>
                    </div>
                    <div class="ibox-content">
	                    <div class="row">
	                        <div class="col-md-6">
	                            <h1 class="no-margins font-bold"><span id="manTotal"></span></h1>
	                            <div class="font-bold text-info"><small>男：</small><span id="manRate"></span>%
	                            </div>
	                        </div>
	                        <div class="col-md-6">
	                            <h1 class="no-margins font-bold"><span id="womanTotal"></span></h1>
	                            <div class="font-bold text-info"><small>女：</small><span id="womanRate"></span>%
	                            </div>
	                        </div>
	                    </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <span class="label label-info pull-right"><i class="fa fa-pencil-square-o"></i></span>
                        <h5>报名方式</h5>
                    </div>
                    <div class="ibox-content">
	                    <div class="row">
	                        <div class="col-md-6">
	                            <h1 class="no-margins font-bold"><span id="siteRegistrationTotal"></span></h1>
	                            <div class="font-bold text-info"><small>现场报名：</small><span id="siteRegistrationRate"></span>%
	                            </div>
	                        </div>
	                        <div class="col-md-6">
	                            <h1 class="no-margins font-bold"><span id="wechatTotal"></span></h1>
	                            <div class="font-bold text-info"><small>微信：</small><span id="wechatRate"></span>%
	                            </div>
	                        </div>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-8">
            	<div class="row">
            		<div class="col-sm-12">
            			<div class="ibox float-e-margins">
		                    <div class="ibox-title">
		                    	<span class="label label-info pull-right"><i class="fa fa-line-chart"></i></span>
		                        <h5>近30天订单</h5>
		                    </div>
		                    <div class="ibox-content">
		                        <div class="row">
		                            <div class="col-sm-12">
										<!-- 订单柱状图 -->
		                                <div id="orderbar" class="echarts"></div>
		                            </div>
		                        </div>
		                    </div>
                		</div>
            		</div>
            		<div class="col-sm-12">
            			<div class="row">
            				<div class="col-sm-6">
            					<div class="ibox float-e-margins">
				                    <div class="ibox-title">
				                        <span class="label label-info pull-right"><i class="fa fa-calendar"></i></span>
				                        <h5>平均年龄</h5>
				                    </div>
				                    <div class="ibox-content">
					                    <div class="row">
					                        <div class="col-md-12">
					                            <h1 class="no-margins font-bold"><span id="avgAge"></span></h1>
					                            <div class="row">
					                            	<div class="col-md-6">
					                            		<div class="font-bold text-info"><small>男：<span id="manAvgAge"></span></small>
					                            		</div>
					                            	</div>
					                            	<div class="col-md-6">
					                            		<div class="font-bold text-info"><small>女：<span id="womanAvgAge"></span></small>
					                            </div>
					                            	</div>
					                            </div>
					                        </div>
					                    </div>
				                    </div>
				                </div>
            				</div>
            				<div class="col-sm-6">
            					<div class="ibox float-e-margins">
				                    <div class="ibox-title">
				                        <span class="label label-info pull-right"><i class="fa fa-star-o"></i></span>
				                        <h5>潜水资质</h5>
				                    </div>
				                    <div class="ibox-content">
					                    <div class="row">
					                        <div class="col-md-4">
					                            <h1 class="no-margins font-bold"><span id="scubaDiverTotal"></span></h1>
					                            <div class="font-bold text-info"><small>水肺潜水员</small><span id="scubaDiverRate"></span>%
					                            </div>
					                        </div>
					                        <div class="col-md-4">
					                            <h1 class="no-margins font-bold"><span id="freeDiverTotal"></span></h1>
					                            <div class="font-bold text-info"><small>自由潜水员</small><span id="freeDiverRate"></span>%
					                            </div>
					                        </div>
					                        <div class="col-md-4">
					                            <h1 class="no-margins font-bold"><span id="notDiverTotal"></span></h1>
					                            <div class="font-bold text-info"><small>非潜水员</small><span id="notDiverRate"></span>%
					                            </div>
					                        </div>
					                    </div>
				                    </div>
				                </div>
            				</div>
            			</div>
            		</div>
            	</div>
            </div>
            <div class="col-sm-4">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>畅销产品TOP10</h5>
	                    <div class="ibox-tools">
	                        <a class="collapse-link">
	                            <i class="fa fa-chevron-up"></i>
	                        </a>
	                    </div>
	                </div>
	                <div class="ibox-content">
	                    <table class="table table-hover no-margins">
	                        <thead>
	                            <tr>
	                                <th>产品名称</th>
	                                <th>产品类型</th>
	                                <th>订单数</th>
	                            </tr>
	                        </thead>
	                        <tbody id="tb_product">
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	        </div>
        </div>


        <div class="row">
	        <div class="col-sm-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                    <h5>会员所在地区分布</h5>
	                    <div class="ibox-tools">
	                        <a class="collapse-link">
	                            <i class="fa fa-chevron-up"></i>
	                        </a>
	                        <a class="close-link">
	                            <i class="fa fa-times"></i>
	                        </a>
	                    </div>
	                </div>
	                <div class="ibox-content">
	                    <div class="row">
	                        <div class="col-sm-6">
	                            <table class="table table-hover margin bottom">
	                                <thead>
	                                    <tr>
	                                        <th class="text-center">区名</th>
	                                        <th class="text-center">人数</th>
	                                    </tr>
	                                </thead>
	                                <tbody id="tb_area">
	                                    
	                                </tbody>
	                            </table>
	                        </div>
	                        <div class="col-sm-6">
	                            <div id="map" style="height:350px;"></div>
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
    <script src="${ctx}/static/js/plugins/echarts/echarts.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=amL48j6KzqlNkxwU5y63Wupv"></script>
    <script type="text/javascript" src="${ctx}/static/js/bmap.js"></script>
    <!-- 自定义js -->
    <script src="${ctx}/static/js/content.js"></script>
    <script>
        $(document).ready(function () {
        	// 订单柱状图
        	var orderbar = echarts.init(document.getElementById('orderbar'));
        	// 南京地图
        	var map = echarts.init(document.getElementById('map'));
        	$(window).resize(orderbar.resize);
        	// 获取统计数据
        	$.post("${ctx}/manage/getMainData").then(function(res){
            	if(res.status==1){ // 成功
            		var data = res.data;
            		$('#vipMemberTotal').text(data.vipMemberTotal);
            		$('#memberTotal').text(data.memberTotal);
            		$('#vipRate').text(data.vipRate);
            		$('#orderTotal').text(data.orderTotal);
            		$('#validatedOrderTotal').text(data.validatedOrderTotal);
            		$('#validatedOrderRate').text(data.validatedOrderRate);
            		$('#manTotal').text(data.manTotal);
            		$('#womanTotal').text(data.womanTotal);
            		$('#manRate').text(data.manRate);
            		$('#womanRate').text(data.womanRate);
            		$('#siteRegistrationTotal').text(data.siteRegistrationTotal);
            		$('#wechatTotal').text(data.wechatTotal);
            		$('#siteRegistrationRate').text(data.siteRegistrationRate);
            		$('#wechatRate').text(data.wechatRate);
            		$('#scubaDiverTotal').text(data.scubaDiverTotal);
            		$('#scubaDiverRate').text(data.scubaDiverRate);
            		$('#freeDiverTotal').text(data.freeDiverTotal);
            		$('#freeDiverRate').text(data.freeDiverRate);
            		$('#notDiverTotal').text(data.notDiverTotal);
            		$('#notDiverRate').text(data.notDiverRate);
            		$('#manAvgAge').text(data.manAvgAge);
            		$('#womanAvgAge').text(data.womanAvgAge);
            		$('#avgAge').text(data.avgAge);
            		
            		// 畅销产品Top10
            		$(data.bestSellProductList).each(function(index, item){
            			var type = item.type=='0'?'课程':'商品';
            			$('<tr onclick="productDetail(\''+item.id+'\')"><td>'+item.title+'</td><td>'+type+'</td><td>'+item.quantity+'</td></tr>').appendTo('#tb_product');
            		})
            		
            		// 初始化订单柱状图数据
            		orderbar.setOption({
            			color: ['#3398DB'],
	                    tooltip: {
	                        trigger: 'axis',
	                        axisPointer: {
	                            type: 'shadow'
	                        },
	                        formatter: ' {b}</br>{a0}:{c0}'
	                    },
	                    legend: {
	                        data: ['订单数']
	                    },
	                    grid:{
	                        x:40,
	                        x2:40,
	                        y2:24
	                    },
	                    yAxis: {
	                        type: 'value',
	                        boundaryGap: [0, 0.01],
	                        name: '数量',
	                        splitLine: {
	                            show: false
	                        }
	                    },
	                    xAxis: {
	                        type: 'category',
	                        name: '时间',
	                        data: data.orderBarList.dateList
	                    },
	                    series: [
	                        {
	                            name: '订单数',
	                            type: 'bar',
	                            data: data.orderBarList.countList,
	                            barWidth: '60%'
	                        }
	                    ]
	                })
	                
	                // 会员所在地区分布排行
	                // 初始化地图数据
	                var mapData = [];
	                $(data.memberAreaList).each(function(index, item){
	                	$('<tr><td class="text-center">'+item.area_name+'</td><td class="text-center">'+item.count+'</td></tr>').appendTo('#tb_area');
	                	mapData.push({
	                		name: item.area_name,
	                		value: [item.lng,item.lat],
	                		symbolSize: 10+item.count/10,
	                		itemStyle: {"normal": {"color": "#F58158"}}
	                	});
	                })
	                
	                $.get('${ctx}/static/js/nanjing.json', function (nanjingJson) {
	                    echarts.registerMap('nanjing', nanjingJson);
                        var option = {
                            geo: {
                                map: 'nanjing',
                                zoom: 2.5,
                                top: '30%',
                                label: {
                                    emphasis: {
                                        show: false
                                    }
                                },
                                roam: true,
                                itemStyle: {
                                    normal: {
                                        areaColor: '#93CDF1',
                                        borderColor: '#53A2E6'
                                    },
                                    emphasis: {
                                        areaColor: '#E9D244'
                                    }
                                }
                            },
                            series: [{
                                name: '行政区',
                                type: 'effectScatter',
                                coordinateSystem: 'geo',
                                zlevel: 2,
                                rippleEffect: {
                                    brushType: 'stroke'
                                },
                                hoverAnimation: true,
                                label: {
                                    emphasis: {
                                        show: true,
                                        position: 'right',
                                        formatter: '{b}'
                                    }
                                },
                                symbolSize: 2,
                                showEffectOn: 'render',
                                itemStyle: {
                                    normal: {
                                        color: '#46bee9'
                                    }
                                },
                                data: mapData
                            }]
                        };
                        map.setOption(option);
                    });
            	}else{
            		// 失败
            		layer.alert(res.message);
            	}
            },function(){
            	layer.alert('网络出现异常！');
            });
        	
        });
        
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
