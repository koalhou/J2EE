<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('menu.cl')" />&nbsp;|&nbsp;<s:property value="getText('vehicle.register.name')" />
</title>
<%@include file="../common/meta.jsp" %>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="vehicleRegisterImport_validate.jsp"%>
  <s:form id="vehicleregisterimport_form" action="importVehicleRegister" method="post" enctype="multipart/form-data">
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
            <s:property value="getText('vehicle.assign.info')" />
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('common.list.enterprise')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:hidden id="entipriseId" name="vehicleRegisterInfo.entipriseId"/>
                <s:textfield id="entipriseName" 
                             name="vehicleRegisterInfo.entipriseName"
                             onclick="openEnterpriseBrowse()" 
                             readonly="true"/>
              </td>
            </tr>
            <!--
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('common.list.user')" />：
              </td>
              <td width="20%" class="fsBlack">
                <s:hidden id="userId" name="vehicleRegisterInfo.userId"/>
                <s:textfield id="userName" 
                             name="vehicleRegisterInfo.userName"
                             onclick="openOwnerBrowse()" 
                             readonly="true"/>
              </td>
            </tr>
            -->
          </table>
        </td>
      </tr>
      
      <tr>
        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>" align="left">
          <span class="msgTitle">&nbsp;&nbsp;
            <s:property value="getText('vehicle.assign.business')" />
          </span>
        </td>
      </tr>
      <tr>
        <td>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <s:iterator value="bizTypeList" status="stat">
              <tr>
                <td width="14%" height="28" align="right">
                  <s:hidden id="bizId" name="bizTypeList[%{#stat.index}].bizId" value="%{bizTypeList[#stat.index].bizId}" />
                  <s:hidden id="bizName" name="bizTypeList[%{#stat.index}].bizName" value="%{bizTypeList[#stat.index].bizName}" />
                  <s:checkbox id="choiceFlag" 
                              name="bizTypeList[%{#stat.index}].choiceFlag" 
                              fieldValue="true">
                  </s:checkbox>
                </td>
                <td width="20%" class="fsBlack" align="left">
                  <s:property value="bizName" />
                </td>
              </tr>
            </s:iterator> 
          </table>
        </td>
      </tr>
      
      <tr>
        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>" align="left">
          <span class="msgTitle">&nbsp;&nbsp;
            <s:property value="getText('vehicle.register.import')" />
          </span>
        </td>
      </tr>
      <tr>
        <td align="left">
          &nbsp;&nbsp;<s:property value="getText('file.choice.filepath')" />：
          <s:file label="choicefile" 
                        theme="simple" 
                        name="file" 
                        size="60"
                        onkeydown="return false;"
                        cssStyle="height: 17px;line-height: 17px;font-size: 12px;border: #006699 1px solid;padding-left: 2px;padding-top: 1px;"/>
          <a href="<s:url value='/template/VehicleRegisterInformation.xls' />"><s:property value="getText('file.template.download')" /></a>
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
          <a href="#" class="buttontwo" onclick="goBack('vehicleregister.shtml')">
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
