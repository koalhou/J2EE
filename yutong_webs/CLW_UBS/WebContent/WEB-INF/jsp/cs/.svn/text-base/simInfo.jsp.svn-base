<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title><s:property value="getText('menu.cl')" />&nbsp;|&nbsp;<s:property value="getText('sim.manage')" /></title>
<%@include file="../common/meta.jsp" %>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="simInfo_validate.jsp"%>
  <s:form id="siminfo_form" action="addSim" method="post">
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
            <s:property value="getText('sim.manage.info')" />
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('business.name')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:select id="businessId" 
                          name="simInfo.businessId" 
                          list="businessList" 
                          listKey="businessId" 
                          listValue="businessName" 
                          headerKey="" 
                          headerValue="%{getText('please.select')}"
                          onchange="onBusinessChange()">
				</s:select>
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('sim.card.apntype')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:select id="apnTypeId" 
                          name="simInfo.apnType" 
                          list="apnTypeList" 
                          listKey="codeId" 
                          listValue="codeName" 
                          headerKey="" 
                          headerValue="%{getText('please.select')}">
				</s:select>
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('cell.number')" />：
              </td>
              <td width="20%" align="left">
                <s:textfield id="cellPhone" name="simInfo.cellPhone" maxlength="11" cssStyle="ime-mode:disabled"/>
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('sim.card.number')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:textfield id="simCardNumber" name="simInfo.simCardNumber" maxlength="60" cssStyle="ime-mode:disabled"/>
              </td>
            </tr>
            <%-- 
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('sim.card.iccid')" />：
              </td>
              <td width="20%">
                  <s:textfield id="iccidElectron" name="simInfo.iccidElectron"/>
              </td>
            </tr>
            --%>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('sim.card.printiccid')" />：
              </td>
              <td align="left">
                <s:textfield id="iccidPrint" name="simInfo.iccidPrint" maxlength="60" cssStyle="ime-mode:disabled"/>
              </td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('sim.card.startuse')" />：
              </td>
              <td align="left">
                <s:textfield id="startUseTime" name="simInfo.startUseTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate">
				</s:textfield>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('simmanage.shtml')">
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
