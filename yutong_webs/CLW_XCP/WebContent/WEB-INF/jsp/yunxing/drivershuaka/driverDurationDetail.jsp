<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body style="width:780px; min-width:700px;">
<s:form id="detail_form" action="" method="post">
	<s:hidden id="detailObj.driverId" name="detailObj.driverId"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td  valign="top">
			<table width="100%" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td class="reportOnline2">
					<table width="100%" border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td>
							<table border="0" cellspacing="4" cellpadding="0">
								<tr>
									<td>姓名：
										${detailObj.driverName}
									</td>
									<td>
										&nbsp;&nbsp;
									</td>
									<td>卡号：
										${detailObj.driverCardId}
									</td>
									<td>
										&nbsp;&nbsp;
									</td>
									<td id="time">时间段：
									</td>
									<td>
										&nbsp;&nbsp;
									</td>
									<td>驾驶时长：
										${detailObj.driverDuration}
									</td>
									<td>
										&nbsp;&nbsp;
									</td>
									<td>里程：
										${detailObj.driverMileage}
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>		
					</td>
				</tr>
				<tr>
				  <td valign="top">
				   <table width="100%" border="0" cellspacing="0" cellpadding="0">
				     <tr>
				        <td class="reportOnline">
				        <table width="100%" border="0" cellspacing="0" cellpadding="0">
				          <tr>
	                         <td class="titleStyle">
	                         <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                                    <tr>
	                                      <td width="30%" class="titleStyleZi">驾驶详情</td>
	                                   <td width="69%"align="right"></td>
	                                      <td width="1%">&nbsp;</td>
	                                    </tr>
	                          </table>
	                          </td>
	                      </tr>
	                      
	                      <tr>
					        <td class="reportInline">
						        <div>
									<table id="gala" width="100%" cellspacing="0"></table>
						        </div>
					        </td>
					     </tr>
				        </table>
				        </td>
				     </tr>
				     
				   </table>
				  </td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">
jQuery(function() {
	var time = window.parent.document.getElementById("start_time").value + '至' + window.parent.document.getElementById("end_time").value;
	$("#time").html("时间段："+time);
	 var gala = jQuery('#gala');
	 gala.flexigrid({
	  url: '<s:url value="/drivershuaka/getDriverDurationDetailList.shtml" />', 
	  dataType: 'json',    //json格式
	  height: 360,
	  width:880,
	  colModel : [ 
	        {display: '车牌号', name : 'VEHICLE_LN', width : 100, sortable : true, align: 'center', escape:true},
	        {display: '点火时间',name : 'T.ON_DATE', width : 150, sortable : true, align: 'center',escape:true},
	        {display: '熄火时间', name : 'T.OFF_DATE', width : 150, sortable : true, align: 'center', escape:true},
	        {display: '驾驶时长', name : 'DRIVERDURATION', width : 150, sortable : false, align: 'center', escape:true},
	        {display: '里程', name : 'DRIVERMILEAGE', width : 100, sortable : false, align: 'center', escape:true}
	        ],
	        sortname: 'T.ON_DATE',
	    	sortorder: 'asc',
	    	sortable: true,
			striped :true,
			usepager :false, 
			resizable: false,
	    	useRp: true,
	    	rp: 10000,
			rpOptions : [10,20,50,100 ],// 可选择设定的每页结果数
	    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
			newp: 1,
		    params: [{ name: 'driverId', value : document.getElementById("detailObj.driverId").value},
			 		{ name: 'searchTimeType', value : window.parent.document.getElementById("searchTimeType").value},
			 		{ name: 'month', value: window.parent.document.getElementById("month").value},
			 		{ name: 'start_time', value: window.parent.document.getElementById("start_time").value},
			 		{ name: 'end_time', value: window.parent.document.getElementById("end_time").value}]		
	     });
	});
</script>
</body>
</html>

