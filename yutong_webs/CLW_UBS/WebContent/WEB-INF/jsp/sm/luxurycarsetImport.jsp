<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('service.management.maintenance.set')" />&nbsp;|&nbsp;<s:property value="getText('service.management.luxury.car.set')" />
</title>
<%@include file="../common/meta.jsp" %>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="luxurycarsetImport_validate.jsp"%>
  <s:form id="luxurycarsetImport_form" action="luxuryCarSetDoImport" method="post" enctype="multipart/form-data">
    <s:hidden id="page" name="page" />
    <s:hidden id="pageSize" name="pageSize" />
    
    <table height="628px" width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
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
          <span class="msgTitle">&nbsp;&nbsp;
            	导入<s:property value="getText('service.management.luxury.car.set')" />
          </span>
        </td>
      </tr>
      <tr>
        <td align="left">
        	<s:token/>
          &nbsp;&nbsp;<s:property value="getText('file.choice.filepath')" />：
          <s:file label="choicefile" 
                        theme="simple" 
                        name="file" 
                        size="60"
                        onkeydown="return false;"
                        cssStyle="height: 17px;line-height: 17px;font-size: 12px;border: #006699 1px solid;padding-left: 2px;padding-top: 1px;"/>
          <a href="<s:url value='/template/luxurycarsetInformation.xls' />"><s:property value="getText('file.template.download')" /></a>
        </td>
      </tr>
      
      <tr>
        <td align="left" height="40">
          <b>
            <span class="noticeMsg">&nbsp;&nbsp;<s:property value="getText('import.notice.info')" /></span>
          </b>
        </td>
      </tr>
      
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('luxuryCarSetInit.shtml')">
            <s:property value="getText('btn.back')" />
          </a>
          <a href="#" class="buttontwo" onclick="submitForm()">
            <s:property value="getText('btn.sure')" />
          </a>
        </td>
      </tr>
    </table>
    
    </td>
    </tr>
    </table>
  </s:form>
  <%@include file="../common/copyright.jsp"%>
</body>
</html>
