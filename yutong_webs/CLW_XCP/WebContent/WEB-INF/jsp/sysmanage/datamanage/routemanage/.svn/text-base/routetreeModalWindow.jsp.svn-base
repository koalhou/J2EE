<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title>组织选择</title>
	<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
	<script type="text/javascript">
		//点击节点赋值父页面
		function clickEnterEvent(obj) {
			//每次点击赋值隐藏域以便传递
		    document.getElementById('organizationID').value = obj.id;
		    document.getElementById('organizationName').value = obj.attributes.getNamedItem('short_name').value; 
		}
	</script>
</head>
<body style="background-color: #eeeeee;">
	<%-- 隐藏值 --%>
	<input type="hidden" id="organizationID"/>
	<input type="hidden" id="organizationName"/>
	<input type="hidden" id="ChooseEnterID_tree" name="ChooseEnterID_tree" value="<%=session.getValue("ChooseEnterID_tree") %>">
	<%-- 树区域 --%>
	<div style="margin:5px;">
	<table border="0" cellpadding="0" cellspacing="8" >
		<tr id="treeTr1">
			<td valign="top" nowrap="nowrap"><%@include
				file="/WEB-INF/jsp/treeSpan.jsp"%></td>
		</tr>
	</table>
	</div>				
</body>
</html>