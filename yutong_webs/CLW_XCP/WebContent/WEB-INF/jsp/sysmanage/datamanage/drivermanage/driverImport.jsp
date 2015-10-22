<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
	  <s:form id="driverImport_form" action="importDriver" method="post" enctype="multipart/form-data">
	    <s:hidden id="page" name="page" />
	    <s:hidden id="pageSize" name="pageSize" />
	    
	    <table id="driverTable" width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
	    <tr>
			<td height="36" valign="top">
				<div class="toolbar">
					<div class="contentTil">驾驶员信息</div>
				</div>
			</td>
		</tr>
	    <tr>
	    <td align="center" valign="top" >
	    <div id="message">
	      <s:actionerror theme="mat" />
	      <s:fielderror theme="mat"/>
	      <s:actionmessage theme="mat"/>
	    </div>
	    <table class="msgBox2" width="650" border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>" align="left">
	          <span class="msgTitle">信息导入
	          </span>
	        </td>
	      </tr>
	      <tr>
	        <td align="center">
			  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	            <tr>
	              <td width="14%" height="28" align="right">
	                <s:property value="getText('file.choice.filepath')" />：
	              </td>
	              <td class="fsBlack" align="left">
	                <s:file label="choicefile" 
	                        theme="simple" 
	                        name="file" 
	                        size="60"
	                        onkeydown="return false;"
	                        cssStyle="height: 17px;line-height: 17px;font-size: 12px;border: #E0E0E0 1px solid;padding-left: 2px;padding-top: 1px;"/>
	                <a href="<s:url value='/uploadlogo/DriverInformation.xls' />"><s:property value="getText('file.template.download')" /></a>
	              </td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	      <tr>
	        <td class="btnBar"> 
	          <a href="#" class="buttontwo" onclick="goBack('drivermanage.shtml')">
	            <s:property value="getText('button.cancle')" />
	          </a>
	          <a href="#" class="buttontwo" onclick="submitForm()">
	            <s:property value="getText('button.queding')" />
	          </a>
	        </td>
	      </tr>
	    </table>
	    </td>
	    </tr>
	    </table>
	    </s:form>
    </div>
    <%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="driverImport_validate.jsp"%>
</body>
</html>
