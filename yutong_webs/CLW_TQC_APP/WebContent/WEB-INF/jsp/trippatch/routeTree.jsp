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
		<td  valign="top">
			<div class="newsearchPlan">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
	              	<td width="10px"></td>
	                <td width="100" align="right"><input id="search_route_name" type="text" class="input120" maxlength="14" onkeypress="if(event.keyCode==13){searchRoute();}"/></td>
	                <td align="center"><a onclick="javascript:searchRoute()" class="btnbule">查询</a></td>
					</tr>
	            </table>
	      	</div>
			<div id="treeDemoDiv" class="treeBox" style="height:365px;">
		    	<ul id="treeDemo" class="ztree"></ul>      		
		    </div> 
		</td>
	</tr>
	<tr>
		<td align="center">
			<a href="javascript:void(0);" class="sbutton" onclick="checkfalse();">取消</a>
		</td>
	</tr>
</table>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterprisecar_routenores.jsp"%>
<script type="text/javascript">
///左侧树，查询线路
	function searchRoute() {
		routename = jQuery("#search_route_name").val();
		searchTree();
	}
	function onClickRoute(str1,str2){
  		window.parent.document.getElementById("route_id").value=str1;
  		window.parent.document.getElementById("route_name").value=str2;
  		//window.parent.searchList();
  		if(str2.length>0){
		    window.parent.document.getElementById("route_nameDesc").innerHTML='';
		}
  		art.dialog.close();
  	}
	function checkfalse(){
  		window.parent.document.getElementById("route_id").value='';
  		window.parent.document.getElementById("route_name").value='';
  		art.dialog.close();
  	}
	function closewindow(){
		window.returnValue=carvin;
		window.close();
	}
	jQuery(function(){
		searchTree();
	});
</script>
</body>
</html>

