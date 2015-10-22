<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:property value="员工登陆次数统计" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
</head>

<body>
<div id="wrapper">
<%@include file="/WEB-INF/jsp/common/menu.jsp"%>
<div id="content"><s:form id="notice_form" action="loginDetailAction"
	method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top" background="../images/tree_tabBg.gif">
					<div class="toolbar">
					<div class="contentTil"><s:text name="员工登陆次数统计" /></div>
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td valign="top">
			<table width="99.5%" border="0" cellspacing="0" cellpadding="0" class="reportOnline" align="center">
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="titleStyle">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="30%" class="titleStyleZi"><s:text name="登陆信息" /></td>
									<td align="right"><a href="<s:url value='/loginstatistic/loginListAction.shtml' />" class="sbutton">返回</a>
									</td>
									<td width="1%">&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
				<span style="padding: 20px"></span>
					<td class="reportInline" align="left">
					<div id="chartDiv" style="height:250px;width:1200px;border-left: 1px solid #cccccc; background:#ffffff;">
					</div>
					<div id="tabAreaId">
					<div id="Below_new" align="center"><s:actionmessage
						cssStyle="color:green" /><s:actionerror cssStyle="color:red" /></div>
					<s:hidden id="begindate" name="begindate"/>
					<s:hidden id="enddate" name="enddate"/>
					<s:hidden id="empcode" name="empcode"/>
					<table id="datatbl" width="100%" cellspacing="0">
					</table>
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form></div>
<%@include file="/WEB-INF/jsp/common/copyright.jsp"%>
</div>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type='text/javascript' src='../scripts/fusioncharts/FusionCharts.js'></script>
<script type="text/javascript">
	jQuery(function() {
		var params=[{name:'empcode',value:jQuery('#empcode').val()},
		            {name:'begindate',value:jQuery('#begindate').val()},
		            {name:'enddate',value:jQuery('#enddate').val()}];
		var gala = jQuery('#datatbl');
		gala.flexigrid({
			url : '<s:url value="/loginstatistic/loginDetailList.shtml" />',
			params:params,
			dataType : 'json',
			height : 400,
			width : 2000,
			colModel : [ {
				display : '员工号',
				name : 'empcode',
				width : get_page_width()*0.05,
				sortable : true,
				align : 'center',
				escape : true
			}, {
				display : '姓名',
				name : 'empname',
				width : get_page_width()*0.3,
				sortable : true,
				align : 'center',
				escape : true
			}, {
				display : '登陆时间',
				name : 'logintime',
				width : get_page_width()*0.1,
				sortable : true,
				align : 'center',
				escape : true
			}, {
				display : '登陆类型',
				name : 'operate',
				width : get_page_width()*0.15,
				sortable : true,
				align : 'center',
				process : reShowLink,
				escape : true
			} ],

			sortname : 'logintime',
			sortorder : 'desc',
			sortable : true,
			striped : true,
			usepager : true,
			resizable : false,
			useRp : true,
			rp : 20,
			rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
			showTableToggleBtn : true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
			onSuccess : function() {
			}
		});
	});
	//重置列表显示
	function reShowLink(tdDiv,pid){
		if(tdDiv.innerHTML=='1'){
			tdDiv.innerHTML="自动登录";
		}else{
			tdDiv.innerHTML="输入密码登录";
		}
	}
	//图表显示
	function initChart() {
		jQuery.ajax({
			type: 'POST', 
			url: '<s:url value="/loginstatistic/getLoginDetailCharts.shtml" />',	
			data: {
				'statistic.empCode':jQuery('#empcode').val(),
				'statistic.beginDate':jQuery('#begindate').val(),
				'statistic.endDate':jQuery('#enddate').val()
				},
			success: function(data) {
				var chart = new FusionCharts("../scripts/fusioncharts/Column2D.swf", "ChartId", "1200", "250", "0", "0");
				var s = getChartXml(data);
				chart.setDataXML(s);		   
				chart.render("chartDiv");
			}  
		});
	}
	//获取时间分布图XML
	function getChartXml(data) {
		var s = "<chart palette='2' caption='登陆时间分布' bgColor='#FFFFFF' bordercolor='#FFFFFF' xAxisName='时段' rotateYAxisName='0' yAxisName='次数' showValues='0' decimals='0' formatNumberScale='0'>" +
					"<set label='0:00-2:00' value='" + data.zero2two +"' />" + 
					"<set label='2:00-4:00' value='" + data.two2four + "' />" +
					"<set label='4:00-6:00' value='" + data.four2six + "' />" +
					"<set label='6:00-8:00' value='" + data.six2eight + "' />" +
					"<set label='8:00-10:00' value='" + data.eight2ten + "' />" +
					"<set label='10:00-12:00' value='" + data.ten2tweleve + "' />" +
					"<set label='12:00-14:00' value='" + data.tweleve2fourteen + "' />" +
					"<set label='14:00-16:00' value='" + data.fourteen2sixteen + "' />" +
					"<set label='16:00-18:00' value='" + data.sixteen2eighteen + "' />" +
					"<set label='18:00-20:00' value='" + data.eighteen2twenty + "' />" +
					"<set label='20:00-22:00' value='" + data.twenty2twentytwo + "' />" +
					"<set label='22:00-24:00' value='" + data.twentytwo2twentyfour + "' />" +
					"<styles>"+
					"<definition>"+
					"<style name='MyCaptionFontStyle' type='font' face='Verdana' size='12' bold='1' />"+
					"</definition>"+
					"<application>"+
						"<apply toObject='caption' styles='MyCaptionFontStyle' />"+
					"</application>"+
					"</styles>" +
				"</chart>";
		return s;
	}
	//获取页面高度
    function get_page_height() {
		var height = 0;
		
		if (jQuery.browser.msie) {
		    height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
		} else {
		    height = self.innerHeight;
		}
		return height;
    }
    //获取页面宽度
    function get_page_width() {
		var width = 0;
		if(jQuery.browser.msie){ 
			width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
		} else { 
			width = self.innerWidth; 
		} 
		return width;
    }
    //计算控件宽高
    function changeWidthHeight(){
		var h = get_page_height();
		var w = get_page_width();
		jQuery(".flexigrid").width(w-20);
		jQuery(".bDiv").height(h-550); 
    }
    //页面自适应
    (function (jQuery) {
    jQuery(window).load(function (){
    	changeWidthHeight();
    	jQuery('#datatbl').fixHeight();
    });
    //柱状图显示
    initChart();
    })(jQuery);
	
	
</script>
</body>
</html>


