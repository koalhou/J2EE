<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('menu.xs')"/>&nbsp;|&nbsp;<s:property value="getText('menu.xs.vehiclebaseinfo')"/>&nbsp;|&nbsp;<s:property value="getText('tmmanage.oem')" />
</title>
<%@include file="../common/meta.jsp" %>
</head>
<body onpaste="return false;">
  <%@include file="../common/menu.jsp"%>
  <%@include file="oemEdit_validate.jsp"%>
  <s:form id="oemEdit_form" action="updateOem" method="post">
  
    <s:hidden id="page" name="page" />
    <s:hidden id="pageSize" name="pageSize" />
    <s:hidden id="oemId" name="oemId" />
    <s:hidden id="oemCodeOld" name="oemCodeOld" />
    
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
            <s:property value="getText('oem.info.name')" />
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('oem.code')" />：
              </td>
              <td width="30%" align="left">
                <s:textfield id="oemCode" name="oemInfo.oemCode" maxlength="10" cssStyle="ime-mode:disabled" />
              </td>
              <td>&nbsp;</td>
              <td width="20%"  align="right">
                <s:property value="getText('oem.name')" />：
              </td>
              <td width="30%" class="fsBlack" align="left">
                <s:textfield id="fullName" name="oemInfo.fullName" maxlength="16"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" align="right">
                <s:property value="getText('oem.shortname')" />：
              </td>
              <td width="30%" align="left">
                <s:textfield id="shortName" name="oemInfo.shortName" maxlength="10"/>
              </td>
              <td>&nbsp;</td>
              <td width="14%"  align="right">
                <s:property value="getText('oem.servicetel')" />：
              </td>
              <td width="30%" class="fsBlack" align="left">
                <s:textfield id="serviceTel" name="oemInfo.serviceTel" maxlength="20" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('common.country')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:select id="countryId" name="oemInfo.countryId" list="countryInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getProvinceInfo()">
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td width="14%" align="right">
                <s:property value="getText('common.province')" />：
              </td>
              <td width="20%" align="left">
                <s:select id="provinceId" name="oemInfo.provinceId" list="provinceInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getCityInfo()">
				</s:select>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('common.city')" />：
              </td>
              <td width="20%" align="left">
                <s:select id="cityId" name="oemInfo.cityId" list="cityInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="onCityChange()">
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td align="right" width="14%">
                <s:property value="getText('oem.type')" />：
              </td>
              <td width="20%" align="left">
                <s:select id="oemType" name="oemInfo.oemType" list="entiTypeMap">
                </s:select>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('oem.concate.address')" />：
              </td>
              <td width="20%" align="left">
                  <s:textfield id="concatAddress" name="oemInfo.concatAddress" maxlength="16"/>
              </td>
              <td>&nbsp;</td>
              <td align="right" width="14%">
                <s:property value="getText('oem.website')" />：
              </td>
              <td width="20%" align="left">
                <s:textfield id="webAddress" name="oemInfo.webAddress" maxlength="30" cssStyle="ime-mode:disabled">
				</s:textfield>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('oem.concate.person')" />：
              </td>
              <td align="left">
                <s:textfield id="concatPerson" name="oemInfo.concatPerson" maxlength="6"/>
              </td>
              <td>&nbsp;</td>
              <td align="right">
                <s:property value="getText('oem.concate.cell')" />：
              </td>
              <td align="left">
                <s:textfield id="cellPhone" name="oemInfo.cellPhone" maxlength="18" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('oem.concate.email')" />：
              </td>
              <td align="left">
                <s:textfield id="email" name="oemInfo.email" maxlength="20" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
              <td align="right">
                <s:property value="getText('oem.concate.tel')" />：
              </td>
              <td align="left">
                <s:textfield id="tel" name="oemInfo.tel" maxlength="20" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('oem.fax')" />：
              </td>
              <td align="left">
                <s:textfield id="fax" name="oemInfo.fax" maxlength="20" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
              <td align="right">
                <s:property value="getText('oem.postcode')" />：
              </td>
              <td align="left">
                <s:textfield id="postcode" name="oemInfo.postCode" maxlength="20" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('oem.desc')" />：
              </td>
              <td colspan="5" align="left">
                <s:textarea id="oemDesc" name="oemInfo.oemDesc" cols="60" rows="6"/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('oemmanage.shtml')">
            <s:property value="getText('btn.back')" />
          </a>
          
          <s:if test="#session.perUrlList.contains('111_0_3_1_4')">
            <s:url id="delOem" action="deleteOem">
              <s:param name="oemId">${oemId}</s:param>
              <s:param name="page">${page}</s:param>
              <s:param name="pageSize">${pageSize}</s:param>
            </s:url>
            <a href="#" class="buttontwo" onclick="delOem('${delOem}');">
              <s:property value="getText('btn.delete')" />
            </a>
          </s:if>
          
          <s:if test="#session.perUrlList.contains('111_0_3_1_3')">
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
