<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
<title>行为统计&nbsp;|&nbsp;市场看板</title>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/common/moment.min.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/common/function_common.js' />"></script>
<style type="text/css">
.charts {
	margin: 10px 30px;
}
</style>

</head>
<body>
<%@include file="../../common/menu.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/fusioncharts/FusionCharts.js' />"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/indicator/GlareTpl.js' />"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/grouptype/GroupType.js' />"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/poshytip/jquery.poshytip.min.js' />"></script>
<script type="text/javascript"
	src="<s:url value='/scripts/common/HelpTip.js' />"></script>
<script type="text/javascript">
	var gtype = null;
	var indicators = [];

	///////////////////
	function setChartLoading(chartdiv) {
		var tmp_loading = jQuery("#tmp_chart_loading").html();
		var chardiv = jQuery("." + chartdiv);
		chardiv.html(tmp_loading);
	}
	function setChartNoData(chartdiv) {
		var tmp_nodata = jQuery("#tmp_chart_nodata").html();
		var chardiv = jQuery("." + chartdiv);
		chardiv.html(tmp_nodata);
	}
	function setChartNoDataByID(chartdiv) {
		var tmp_nodata = jQuery("#tmp_chart_nodata").html();
		var chardiv = jQuery("#" + chartdiv);
		chardiv.html(tmp_nodata);
	}
	function searchChartData() {
		indicators = [];
		jQuery(':checkbox').each(function() {
			if (jQuery(this).attr("checked") == true) {
				indicators.push(jQuery(this).val());
			}
		});
		if (indicators.length == 0) {
			alert('请选择指标');
			setChartNoData("charts");
			return false;
		}
		setChartLoading("charts");

		var url = '<s:url value="/ub/market/json/lines.shtml" />'
				+ '?queryDate=' + jQuery('#queryDate').val();
		jQuery.ajax({
			url : url,
			cache : false,
			dataType : "json",
			async : true,
			success : function(data) {
				loadChart(data.zblist, 'zb_chart');
				loadChart(data.hjlist, 'hj_chart');
				loadChart(data.mzlist, 'mz_chart');
				loadChart(data.hnlist, 'hn_chart');
				loadChart(data.sdlist, 'sd_chart');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				loger(textStatus);
				setChartNoData("charts");
			}
		});

	}
	function loadChart(data, divID) {
		if (data == null || data.length <= 0) {
			loger("loadChart:data is null");
			setChartNoDataByID(divID);
			return;
		}

		indicators = [];
		jQuery(':checkbox').each(function() {
			if (jQuery(this).attr("checked") == true) {
				indicators.push(jQuery(this).val());
			}
		});
		if (indicators.length > 1) {
			var strbarms = [];
			var categoryarray = [], taskArray = [], ventarray = [];
			strbarms.push("<chart caption='" + jQuery('#' + divID).attr('chn')
					+ "' xAxisName='' yAxisName='' >");
			categoryarray.push(" <categories>");

			taskArray.push("<dataset seriesName='任务完成率'>");
			ventarray.push("<dataset seriesName='企业活跃度'>");

			for ( var i = 0; i < data.length; i++) {
				var item = data[i];

				var s = "   <category  label='"+item.month+"' />";
				categoryarray.push(s);

				taskArray.push("<set value='" + item.completedPercent
						+ "'  tooltext='" + item.month + "   任务完成率:"
						+ item.completedPercent + "%25' />");
				ventarray.push("<set value='" + item.acPercent
						+ "'  tooltext='" + item.month + "   企业活跃度:"
						+ item.acPercent + "%25' />");

			}
			categoryarray.push("</categories>");
			taskArray.push("</dataset>");
			ventarray.push("</dataset>");

			strbarms.push(categoryarray.join(""));

			var check = jQuery.inArray("taskPercent", indicators);
			if (check != -1)
				strbarms.push(taskArray.join(""));

			check = jQuery.inArray("acPercent", indicators);
			if (check != -1)
				strbarms.push(ventarray.join(""));

			var tail = "<styles>"
					+ "<definition>"
					+ "<style name='MyCaptionFontStyle' type='font' face='Verdana' size='16' bold='1' />"
					+ "<style name='myToolTipFont' type='font' font='Arial' size='12' color='FF5904'/>"
					+ "</definition>"
					+ "<application>"
					+ "<apply toObject='caption' styles='MyCaptionFontStyle' />"
					+ "<apply toObject='ToolTip' styles='myToolTipFont' />"
					+ "</application>" + "</styles>" + "</chart>";
			strbarms.push(tail);
			var xml = strbarms.join("");
			var myChartbarms = new FusionCharts(
					"<s:url value='/scripts/fusioncharts/MSLine.swf' />",
					"myChartId4", "591", "197", "0");

			myChartbarms.setDataXML(xml);
			myChartbarms.render(divID);

		} else if (indicators.length = 1) {
			loger("选择一个指标");

			var sbar = [];
			var categoryarray = [], taskArray = [], acArray = [];
			sbar.push("<chart caption='" + jQuery('#' + divID).attr('chn')
					+ "'  xAxisName=''  yAxisName='' >");

			var indct = indicators[0];

			for ( var i = 0; i < data.length; i++) {
				var item = data[i];
				taskArray.push("<set label='" + item.month + "' value='"
						+ item.completedPercent + "' tooltext='" + item.month
						+ "   任务完成率：" + item.completedPercent + "%25' />");
				acArray.push("<set label='" + item.month + "' value='"
						+ item.acPercent + "' tooltext='" + item.month
						+ "   企业活跃度：" + item.acPercent + "%25' />");

			}
			switch (indct) {
			case "taskPercent":
				sbar.push(taskArray.join(""));
				break;
			case "acPercent":
			default:
				sbar.push(acArray.join(""));
				break;

			}
			var tail = "<styles>"
					+ "<definition>"
					+ "<style name='MyCaptionFontStyle' type='font' face='Verdana' size='16' bold='1' />"
					+ "<style name='myToolTipFont' type='font' font='Arial' size='12' color='FF5904'/>"
					+ "</definition>"
					+ "<application>"
					+ "<apply toObject='caption' styles='MyCaptionFontStyle' />"
					+ "<apply toObject='ToolTip' styles='myToolTipFont' />"
					+ "</application>" + "</styles>" + "</chart>";
			var charXml = sbar.join("") + tail;
			var myChartbarms = new FusionCharts(
					"<s:url value='/scripts/fusioncharts/Line.swf' />",
					"myChartId4", "591", "197", "0");
			myChartbarms.setDataXML(charXml);
			myChartbarms.render(divID);

		} else {
			loger("未选择指标");
			alert("未选择指标");
		}
	}

	jQuery(function() {
		var d = new Date();
		jQuery("#queryDate").attr("value", d.getFullYear());
		searchChartData();
		jQuery(':checkbox').click(function() {
			searchChartData();
		});

	});

	//获取页面高度
	function get_page_height() {
		var height = 0;

		if (jQuery.browser.msie) {
			height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight
					: document.body.clientHeight;
		} else {
			height = jQuery(document).height();
		}
		return height;
	}
	//获取页面宽度
	function get_page_width() {
		var width = 0;
		if (jQuery.browser.msie) {
			width = document.compatMode == "CSS1Compat" ? document.documentElement.clientWidth
					: document.body.clientWidth;
		} else {
			width = jQuery(document).width();
		}
		return width;
	}
	//计算控件宽高
	function changeWidthHeight() {
		var h = get_page_height();
		var w = get_page_width();
		jQuery(".tabDiv").width(w - 30);

	}
	//页面自适应
	/**
	 * 
	 */

	(function(jQuery) {
		jQuery(window).resize(function() {
			changeWidthHeight();
			jQuery('.tabDiv').fixHeight();
		});
		jQuery(window).load(function() {
			changeWidthHeight();
			jQuery('.tabDiv').fixHeight();
		});
	})(jQuery);
</script>

<div id="tabDiv"
	style="margin: 10px;  min-height: 750px; background-color: white;">
<div class="ubtab">
<ul>
	<li class="ubtabselect"><a href="linesInit.shtml" >任务完成率与活跃度趋势图</a></li>
	<li><a href="rankListInit.shtml"> 查看排名</a></li>

</ul>
<div class="ubtabcontent">
<div style="margin: 5px"><!-- tab内容 -->


<div style="margin: 10px">查询年度： <s:textfield id="queryDate"
	name="queryDate"
	onfocus="WdatePicker({maxDate:'%y',skin:'whyGreen',dateFmt:'yyyy',onpicked:searchChartData})"
	cssClass="Wdate"></s:textfield></div>
<div style="margin: 10px">指标： <input type="checkbox" name="index"
	value="taskPercent" checked="checked">任务完成率 <input
	type="checkbox" name="index" value="acPercent"  checked="checked">企业活跃度</div>

<div style="text-align: center; margin: 10px auto">
<table id="charttable" style="border: 0px; margin: auto">
	<tr>
		<td>
		<div class="charts" id="zb_chart" chn="中部大区"></div>
		</td>
		<td>
		<div class="charts" id="hj_chart" chn="黑吉大区"></div>
		</td>
	</tr>
	<tr>
		<td>
		<div class="charts" id="mz_chart" chn="闽浙大区"></div>
		</td>
		<td>
		<div class="charts" id="hn_chart" chn="华南大区"></div>
		</td>
	</tr>
	<tr>
		<td>
		<div class="charts" id="sd_chart" chn="山东大区"></div>
		</td>
	</tr>
</table>
</div>


</div>
</div>
</div>




</div>


<script type="text/html" id="tmp_chart_nodata">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:197px;width:597px;color:rgb(234,118,79);line-height:197px;border:1px solid #ccc;">图表无数据！</div>
</script>
<script type="text/html" id="tmp_chart_loading">
<div style="margin:auto auto;text-align:center;vertical-align:middle;height:197px;width:591px;color:rgb(72,190,244);line-height:197px;border:1px solid #ccc;">正在加载图表数据...</div>
</script>
<%@include file="../../common/copyright.jsp"%>
</body>
</html>