<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body style="min-width: 300px;">
		<s:form>
			<s:hidden id="vehicle_vin" name="queryObj.vehicle_vin"/>	
			<s:hidden id="on_date" name="queryObj.on_date"/>	
			<s:hidden id="off_date" name="queryObj.off_date"/>	
			<table id="galaList" ></table>
		</s:form>
	</div>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">
jQuery(function() {
     var galaList = jQuery('#galaList');
	 galaList.flexigrid({
		  url: '<s:url value="/runoilJS/getAirDetailRecords.shtml" />', 
		  params: [{name: 'queryObj.vehicle_vin', value: jQuery('#vehicle_vin').val()},
		           {name: 'queryObj.on_date', value: jQuery('#on_date').val()},
		           {name: 'queryObj.off_date', value: jQuery('#off_date').val()}],
		  dataType: 'json',    //json格式
		  height: 235,
		  width: 585,
		  colModel : [ 
				{display: '序号', name : 'ROWNUMBER', width : 30, sortable : false, align: 'center',escape: true},
				{display: 'event_id', name : 'ID', width : 20, sortable : true, align: 'center',hide:true,toggle:false,escape: true},
				{display: '类型', name : 'EVNET_TYPE', width : 80, sortable : true, align: 'center',toggle:false,escape: true},
				{display: '开始时间', name : 'ON_DATE', width : 120, sortable : true, align: 'center',toggle:false,escape: true},
				{display: '结束时间', name : 'OFF_DATE', width : 120, sortable : true, align: 'center',escape: true},
				{display: '时长(s)', name : 'DURATION', width : 80, sortable : false, align: 'center'}
		        ],
		       sortname: "ON_DATE",
		       sortorder: 'DESC',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true  // 是否允许显示隐藏列
	     });
});
</script>
</body>
</html>