<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	    <%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
		<title><s:text name="common.title" />
		</title>
		<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
		<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
		<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseonly_css.jsp"%>
		<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/alarm.css" />" />
    </head>
	<body>
	    <div id="wrapper">
	    <%@include file="/WEB-INF/jsp/common/header.jsp"%>
	    <div id="content">
		<div class="popBox">
			<div id="popArea" class="mk-sidelayer mk-sidelayer-small" style="z-index:901;overflow:hidden;display: none">
			  <div class="mk-sidelayer-toolbar">        
			        <span class="mk-sidelayer-bar-btn show"></span>
			        <h1 class="mk-sidelayer-bar-title"></h1>
			        <div class="mk-sidelayer-tools" style="overflow:hidden;">
			        </div>
			  </div>
			  <div class="mk-sidelayer-content">
			  </div>
		  </div>
        </div>
		<s:form id="alarmManage_form" action="alarmmanage" method="post" onsubmit="return false;">
		<s:hidden id="chooseorgid" name="chooseorgid" />
		<s:hidden id="newalarmtypeid" name="newalarm_type_id" />
		<s:hidden id="newveh_ln" name="newveh_ln" />
			<table  width="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td id='leftdiv'  valign="top" class="leftline">
						<div id="content_leftside">
						<div id="leftInfoDiv" class="treeTab">
							<a href="#" class="tabfocus">组织</a>
							<a href="#" onclick="HideandShowControl()" class="hideLeft"></a>
						</div>		
						 <div id="lefttree" class="treeBox">
						     <ul id="treeDemo" class="ztree"></ul>
						  </div>	
						</div>
					</td>

					<td  valign="top" class="sleftline" id="middeldiv" style="display: none;">
	                   <div id="content_middelside">
		                <div>
	            	     <a href="#" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
	          	       </div>
	                  </div>
                   </td>
					
					<td valign="top"  class="position_r" id="rightdiv">
					 <div id="content_rightside">
					     
                     </div>
					</td>
				</tr>
			</table>
			</s:form>				
</div>
   <%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
   <%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
   <%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseonly_js.jsp"%>
   <%@include file="alarmcommon_js.jsp"%>
   <%@include file="alarmtime_js.jsp"%>
   <script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
   <script type="text/javascript" language="JavaScript1.2"
			src="<s:url value='/scripts/flexigrid/flexigrid2.0.js' />"></script>
   <script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
   <%@include file="newmorealarmList_js.jsp"%>
</body>
</html>