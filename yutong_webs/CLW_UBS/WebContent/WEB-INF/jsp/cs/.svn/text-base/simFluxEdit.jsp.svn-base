<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title><s:property value="getText('menu.xj')" />&nbsp;|&nbsp;<s:property value="getText('sim.flux.manage')" /></title>
<%@include file="../common/meta.jsp" %>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="simFluxEdit_validate.jsp"%>
  <s:form id="simfluxedit_form" action="updateSimFlux" method="post">
    <s:hidden id="page" name="page" />
    <s:hidden id="pageSize" name="pageSize" />
    <s:hidden id="simId" name="simId" />
    <s:hidden id="simOldNumber" name="simOldNumber" />
        
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
            <s:property value="getText('simflux.info')" />
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
                          name="simFluxInfo.businessId" 
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
                <s:property value="getText('sim.card.number')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:textfield id="simCardNumber" name="simFluxInfo.simCardNumber" maxlength="60" cssStyle="ime-mode:disabled"/>
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('cell.number')" />：
              </td>
              <td width="20%" align="left">
                <s:textfield id="cellPhone" name="simFluxInfo.cellPhone" maxlength="15" cssStyle="ime-mode:disabled"/>
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('simflux.flux')" />：
              </td>
              <td width="20%" align="left">
                  <s:textfield id="fluxValue" name="simFluxInfo.fluxValue" maxlength="18" cssStyle="ime-mode:disabled"/>
              </td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('simflux.calltime')" />（h）：
              </td>
              <td align="left">
                <s:textfield id="callTime" name="simFluxInfo.callTime" maxlength="8" cssStyle="ime-mode:disabled"/>
              </td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('simflux.closetime')" />：
              </td>
              <td align="left">
                <s:textfield id="closeTime" name="simFluxInfo.closeTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate">
				</s:textfield>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('simfluxmanage.shtml')">
            <s:property value="getText('btn.back')" />
          </a>
          
          <s:if test="#session.perUrlList.contains('111_0_4_3_4')">
          <s:url id="delSimFlux" action="deleteSimFlux">
            <s:param name="simId">${simId}</s:param>
            <s:param name="simOldNumber">${simOldNumber}</s:param>
            <s:param name="page">${page}</s:param>
            <s:param name="pageSize">${pageSize}</s:param>
          </s:url>
          <a href="#" class="buttontwo" onclick="delSimFlux('${delSimFlux}');">
            <s:property value="getText('btn.delete')" />
          </a>
          </s:if>
          
          <s:if test="#session.perUrlList.contains('111_0_4_3_3')">
            <a href="#" class="buttontwo" onclick="submitForm()">
              <s:property value="getText('btn.update')" />
            </a>
          </s:if>
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
