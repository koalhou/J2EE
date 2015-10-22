<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body style="width:450px; min-width:450px;">
<s:form id="tripmodule_form" action="/carrunmodule/tripmodule_form" method="post">
<table  width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td  valign="top">
		<table width="100%" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td class="reportOnline2">
				<table width="100%" border="0" cellspacing="8" cellpadding="0">
					<tr>
						<td>
						<table border="0" cellspacing="4" style="height:180px;" cellpadding="0" align="center">
							<tr>
								<td>名称：</td>
								<td><s:textfield id="name" maxlength="16" name="clwXcTripHistory.name" cssClass="input120"/></td>
							</tr>
							<tr>
								<td>线路：</td>
								<td><s:textfield id="routename" maxlength="16" name="clwXcTripHistory.routename" cssClass="input120" readonly="true"/>
								<s:hidden id="routeid" name="clwXcTripHistory.routeId"/>
								</td>
							</tr>
							<tr>
								<td>描述：</td>
								<td><s:textarea id="moduleDesc" name="clwXcTripHistory.moduleDesc" cssStyle="height:80px;"/></td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<s:actionmessage cssStyle="color:green"/>
							  		<s:actionerror cssStyle="color:red"/>
								</td>
							</tr>
							<tr>
								<td></td>
								<td align="right">
									<a href="#" onclick="addTripRoute()" class="sbutton">添加</a>
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
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">
  function addTripRoute() {
	  var param = {"clwXcTripHistory.name":jQuery("#name").val(),
			  "clwXcTripHistory.routeId":jQuery("#routeid").val(),
			  "clwXcTripHistory.moduleDesc":jQuery("#moduleDesc").val()};
	  
	  jQuery.post("../carrunmodule/tripmodule_form2.shtml",param,function(data){
		  alert(data);
		  art.dialog.close();
	  },"text");
	  //var form = document.getElementById("tripmodule_form");
	  //form.submit();
  }

</script>
</body>
</html>

