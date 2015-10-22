<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title><s:property value="getText('menu.xs')"/>&nbsp;|&nbsp;<s:property value="getText('menu.xs.vehiclebaseinfo')"/>&nbsp;|&nbsp;<s:property value="getText('business.manage')" /></title>
<%@include file="../common/meta.jsp" %>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="businessInfo_validate.jsp"%>
  <s:form id="businessinfo_form" action="addBusiness" method="post">
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
            <s:property value="getText('business.info')" />
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
              <td width="30%" class="fsBlack" align="left">
                <s:textfield id="fullName" name="businessInfo.fullName" maxlength="16"/>
              </td>
              <td>&nbsp;</td>
              <td width="14%" align="right">
                <s:property value="getText('business.shortname')" />：
              </td>
              <td width="30%" align="left">
                <s:textfield id="shortName" name="businessInfo.shortName" maxlength="10"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('common.country')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:select id="countryId" name="businessInfo.countryId" list="countryInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getProvinceInfo()">
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td width="14%" align="right">
                <s:property value="getText('common.province')" />：
              </td>
              <td width="20%" align="left">
                <s:select id="provinceId" name="businessInfo.provinceId" list="provinceInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getCityInfo()">
				</s:select>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('common.city')" />：
              </td>
              <td width="20%" align="left">
                <s:select id="cityId" name="businessInfo.cityId" list="cityInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="onCityChange()">
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td align="right" width="14%">
                <s:property value="getText('business.type')" />：
              </td>
              <td width="20%" align="left">
                <s:select id="businessType" name="businessInfo.businessType" list="bizTypeMap">
                </s:select>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('business.concate.address')" />：
              </td>
              <td width="20%" align="left">
                <s:textfield id="address" name="businessInfo.address" maxlength="16"/>
              </td>
              <td>&nbsp;</td>
              <td align="right" width="14%">
                <s:property value="getText('business.website')" />：
              </td>
              <td width="20%" align="left">
                <s:textfield id="webSite" name="businessInfo.webSite" maxlength="48" cssStyle="ime-mode:disabled">
				</s:textfield>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('business.concate.person')" />：
              </td>
              <td align="left">
                <s:textfield id="concatePerson" name="businessInfo.concatePerson" maxlength="10"/>
              </td>
              <td>&nbsp;</td>
              <td align="right">
                <s:property value="getText('business.concate.cell')" />：
              </td>
              <td align="left">
                <s:textfield id="cellPhone" name="businessInfo.cellPhone" maxlength="15" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('business.concate.email')"/>：
              </td>
              <td align="left">
                <s:textfield id="email" name="businessInfo.email" maxlength="60" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
              <td align="right">
                <s:property value="getText('business.concate.tel')" />：
              </td>
              <td align="left">
                <s:textfield id="tel" name="businessInfo.tel" maxlength="15" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('business.desc')" />：
              </td>
              <td colspan="5" align="left">
                <s:textarea id="businessDesc" name="businessInfo.businessDesc" cols="60" rows="6"/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('businessmanage.shtml')">
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
