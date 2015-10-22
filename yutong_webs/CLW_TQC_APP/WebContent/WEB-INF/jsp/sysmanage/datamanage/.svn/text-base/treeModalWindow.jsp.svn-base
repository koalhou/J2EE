<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织选择</title>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<script type="text/javascript" src="<s:url value='/scripts/jquery-easyui-1.2.2/jquery-1.4.4.min.js' />"></script>
<script type="text/javascript">


var objtree = "";

function clickEnterEvent(obj) {
    //var orgid=document.getElementById('orgid');
    //objtree = obj.id;
    //objtree = objtree+","+obj.attributes.getNamedItem('short_name').value;
    //orgid.value=obj.id;

    
    //每次点击赋值隐藏域以便传递
    document.getElementById('organizationID').value = obj.id;
    document.getElementById('organizationName').value = obj.attributes.getNamedItem('short_name').value;
}

function closewindow(){

	window.returnValue=objtree;
	window.close();
}

</script>
</head>
<body>
<input type="hidden" id="organizationID"/>
<input type="hidden" id="organizationName"/>
<div class="reportPlan">

 <div class="middletanchu">
			  <div class="msgBoxtanchu">
<!--                  <div class="msgTitle">树结构</div>-->
				 
                  <div class="msgCententtanchu">
                    <table width="100%" border="0" cellspacing="0" cellpadding="2">
                      <tr>
                      <input type="hidden" id="ChooseEnterID_tree"
				name="ChooseEnterID_tree" value="<%=session.getValue("ChooseEnterID_tree") %>">
                        <td align="left"><div style="margin:5px;">
						<table border="0" cellpadding="0" cellspacing="8" >
							<tr id="treeTr1">
								<td valign="top" nowrap="nowrap"><%@include
									file="/WEB-INF/jsp/treeSpan.jsp"%></td>
							</tr>
						</table>
						</div></td>
                      </tr>
                      <tr>
                        <td height="10" align="center" style="margin-top:10px; margin-bottom:10px;"></td>
                      </tr>
                    </table>
                  </div>
                  <input type="hidden" id="organizationName"name="organizationName" >
                  <input type="hidden" id="organizationID"name="organizationID">
				   <!-- <div class="btnBar">     			    
                     <table align="center" width="100" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					    <td align="right"><a href="#" class="sbutton" onclick="closewindow()">确定</a></td>
					  </tr>
					</table>
					
                  </div>-->
                </div>
				</div>


</div>



</body>
</html>