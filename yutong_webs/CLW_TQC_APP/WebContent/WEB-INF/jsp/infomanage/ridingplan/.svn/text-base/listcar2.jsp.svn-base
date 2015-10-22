<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterprise_css.jsp"%>
	
	<style type="text/css">
		.ztree li span.button.icon01{
			margin:0; 
			background: url(../images/lvdeng.gif) no-repeat scroll 0 0 transparent; 
			vertical-align:middle; 
			*vertical-align:middle;
			display:inline-block;
			width:16px;
			height:16px;
			margin-left:-6px;
			cursor:pointer;
		}
	</style>
</head>
<body style="width:260px; min-width:260px;">
<s:form id="routeveh_form" action="report_veh" method="post">
</s:form>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td id='leftdiv'  valign="top" class="leftline">
        <div id="content_leftside">
          <div class="newsearchPlan" style="height: 40px;">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
              	<td width="10px"></td>
                <td width="130" align="right"><input id="search_route_name" type="text" class="input120" maxlength="14" onkeypress="if(event.keyCode==13){searchRoute();}"/></td>
                <td align="center"><a onclick="javascript:searchRoute()" class="btnbule">查询</a></td>
              </tr>
              <tr>
              	<td width="10px"></td>
              	<%-- <td width="130px" colspan="2" align="left">
              		<table border="0" align="left" cellpadding="0" cellspacing="0">
              			<tr>
              			  <td>
              			    <span>
              			    	<s:checkbox id="filterFlag" name="filterFlag" onclick="fliterCars();"/>
              			    </span>
              			  </td>
              			  <td valign="top">
              			    <span>&nbsp;未配置线路车辆过滤</span>
              			  </td>
              			</tr>
              		</table>
              	</td> --%>
              	<td align="center">
                </td>
              </tr>
            </table>
          </div>
          <div id="treeDemoDiv" class="treeBox_box">
	      	<ul id="treeDemo" class="ztree"></ul>
	      </div>
         </div>
        </td>
	</tr>
</table>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<%@include file="/WEB-INF/jsp/treemanage/zTree_carrun_js_route.jsp"%>
<script>
function mytreeonClick(id,name,code,drivername,driverid){
	/* if(treecarlistin!=null&&treecarlistin.length>0) {
		for(var v=0;v<treecarlistin.length;v++) {
			if(treecarlistin[v].VIN == id) {
				if(treecarlistin[v].send_condition!=null&&treecarlistin[v].send_condition==1) {
					alert("循环发车车辆不可重复添加！");
					return false;
				}
			}
		}
	} */
	if(code != '外租')
		window.parent.document.getElementById("vehicle_ln").value=name+" ("+code+")";
	else
		window.parent.document.getElementById("vehicle_ln").value=name;
	
	/* if(window.parent.document.getElementById("vehicle_code"))
		window.parent.document.getElementById("vehicle_code").innerText=code; */
	if(window.parent.document.getElementById("driver_name")) {
		if(drivername == null) {
			window.parent.document.getElementById("vehicle_vin").value="";
			window.parent.document.getElementById("driver_name").innerText="驾驶员：空";
		}else {
			window.parent.document.getElementById("vehicle_vin").value=id;
			window.parent.document.getElementById("driver_name").innerText="驾驶员："+drivername;
		}
	}
	if(window.parent.document.getElementById("driver_id"))
		window.parent.document.getElementById("driver_id").value=driverid;
	
	art.dialog.close();
}
function searchRoute() {
	searchTree();
}
</script>
</body>
</html>

