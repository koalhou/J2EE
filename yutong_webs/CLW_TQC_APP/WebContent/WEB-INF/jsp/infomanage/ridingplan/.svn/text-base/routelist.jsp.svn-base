<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseroute_css.jsp"%>
</head>
<body style="width:260px; min-width:260px;">
<s:form id="routeveh_form" action="choiceExportLn" method="post">
</s:form>
<table  width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<div class="treeTab">
				<s:if test="#session.perUrlList.contains('111_3_5_3_5')">
					<a href="javascript:void(0);" id="treeupid" onclick="tabSwitch('treeupid')" class="tabfocus">早班线路</a>
					<s:if test="#session.perUrlList.contains('111_3_5_3_6')">
						<a  class="tab" onfocus="this.blur()" id="treedownid" onclick="tabSwitch('treedownid')">晚班线路</a>
					</s:if>
					<s:if test="#session.perUrlList.contains('111_3_5_3_7')">
						<a  class="tab" onfocus="this.blur()" id="treetqcid" onclick="tabSwitch('treetqcid')">厂内通勤</a>
					</s:if>
				</s:if>
				<s:else>
					<s:if test="#session.perUrlList.contains('111_3_5_3_6')">
						<a  href="javascript:void(0);" id="treedownid" onclick="tabSwitch('treedownid')" class="tabfocus">晚班线路</a>
						<s:if test="#session.perUrlList.contains('111_3_5_3_7')">
							<a  class="tab" onfocus="this.blur()" id="treetqcid" onclick="tabSwitch('treetqcid')">厂内通勤</a>
						</s:if>
					</s:if>
					<s:else>
						<s:if test="#session.perUrlList.contains('111_3_5_3_7')">
							<a  href="javascript:void(0);" id="treetqcid" onclick="tabSwitch('treetqcid')" class="tabfocus">厂内通勤</a>
						</s:if>
					</s:else>
				</s:else>
			  </div>
			  <div class="newsearchPlan">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr>
					<td width="130" align="right"><input id="search_route_name" type="text" class="input120" maxlength="14" onkeypress="if(event.keyCode==13){searchRoute();}"/></td>
					<td align="center"><a onclick="javascript:searchRoute()" class="btnbule">查询</a></td>
				  </tr>
				</table>
			  </div>
		</td>
	</tr>
	<tr>
		<td  valign="top">
			<div id="treeDemoDiv" class="treeBox" style="height:380px;width:255px;">
		    	<ul id="treeDemo" class="ztree"></ul>      		
		    </div> 
		</td>
	</tr>
</table>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterprisecar_route.jsp"%>
<script type="text/javascript">
function tabSwitch(id){
	if(iftruereturn==0) {
		return false;
	}else{
		iftruereturn = 0;
	}
	
	if(id=="treeupid"){
		jQuery("#treeupid").removeClass();
		jQuery("#treedownid").removeClass();
		jQuery("#treetqcid").removeClass();
		jQuery("#treeupid").addClass('tabfocus');
		jQuery("#treedownid").addClass('tab');
		jQuery("#treetqcid").addClass('tab');
		//下两行加载树
		routeclass=0;
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}else if(id=="treedownid"){
		jQuery("#treeupid").removeClass();
		jQuery("#treedownid").removeClass();
		jQuery("#treetqcid").removeClass();
		jQuery("#treeupid").addClass('tab');
		jQuery("#treedownid").addClass('tabfocus');
		jQuery("#treetqcid").addClass('tab');
		//下两行加载树
		routeclass=1;
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}else if(id=="treetqcid"){
		jQuery("#treeupid").removeClass();
		jQuery("#treedownid").removeClass();
		jQuery("#treetqcid").removeClass();
		jQuery("#treeupid").addClass('tab');
		jQuery("#treedownid").addClass('tab');
		jQuery("#treetqcid").addClass('tabfocus');
		//下两行加载树
		routeclass=2;
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}
}

function searchRoute() {
	routename = jQuery("#search_route_name").val();
	searchTree();
}
	function onClickRoute(str1,str2){
  		window.parent.document.getElementById("choicerouteid").value=str1;
  		window.parent.document.getElementById("choicerouteclass").value=str2;
  		window.parent.reloadaddafterr();
  		art.dialog.close();
  	}
	function closewindow(){
		window.returnValue=carvin;
		window.close();
	}
	jQuery(function(){
		//routename = '<s:property value="exe_date"/>,'+'<s:property value="vehicle_vin"/>';
		searchTree();
	});
</script>
</body>
</html>

