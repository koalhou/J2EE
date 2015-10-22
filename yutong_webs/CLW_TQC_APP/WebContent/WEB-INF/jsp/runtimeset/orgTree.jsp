<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<%@include file="dialog_org_tree_css.jsp"%>
</head>
<body style="width:260px; min-width:260px;">
<table  width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td  valign="top">
			<div id="treeDemoDiv" class="treeBox" style="height:400px;">
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
<%@include file="dialog_org_tree_js.jsp"%>
<script type="text/javascript">
	function onclicktree(str1,str2){
  		window.parent.document.getElementById("organization_id").value=str1;
  		window.parent.document.getElementById("org_name").value=str2;
  		//window.parent.searchList();
  		if(str2.length>0){
		    window.parent.document.getElementById("org_nameDesc").innerHTML='';
		}
  		art.dialog.close();
  	}
	function checkfalse(){
  		window.parent.document.getElementById("organization_id").value='';
  		//window.parent.document.getElementById("org_name").value='';
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

