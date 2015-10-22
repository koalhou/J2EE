<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('system.set.menu')" />&nbsp;|&nbsp;<s:property value="getText('poi.manage')" />
</title>
<%@include file="../common/meta.jsp" %>
<style type="text/css">
  .msgBox3{
    margin:0px auto;	
	background: #eee url(../images/msg_title_bg.gif) repeat-x top left;
	border: 1px solid #b8b8b8;
	padding: 0 8px;
	width: 260px;
  }
</style>

</head>
<body onload="mapInit();">
  <%@include file="../common/menu.jsp"%>
  <%@include file="poiInfo_validate.jsp"%>
  
  <s:form id="poiinfo_form" action="addPoiInfo" method="post">
    <s:hidden id="page" name="page" />
    <s:hidden id="pageSize" name="pageSize" />
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
	    <td width="220" valign="top" class="treeline">
	    
	      <table class="msgBox3" border="0" align="center" cellpadding="0" cellspacing="0" style="padding-right:0px;">
            <tr>
              <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="100%" class="titleStyleZi" align="left">
                      <s:property value="getText('poi.info.name')" />
                      (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
                    </td>
                    <td width="1%">&nbsp;</td>
                  </tr>
                </table>
              </td>
            </tr>
            <tr>
              <td align="center">
                <div id="leftInfoDiv" style="overflow:auto">
                <table id="poiTbl" width="99%" border="0" align="center" cellpadding="0" cellspacing="1">
                  <tr>
                    <td colspan="2">
                      <div id="message">
						<div>
						  <s:actionerror theme="mat" />
						  <s:fielderror theme="mat" />
						  <s:actionmessage theme="mat" />
						</div>
					  </div>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.code')" />：
                    </td>
                    <td align="left">
                      <s:textfield id="poiCode" name="poiInfo.poiCode" maxlength="10"/>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.shortname')" />：
                    </td>
                    <td align="left">
                      <s:textfield id="poiName" name="poiInfo.poiName" maxlength="100"/>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.type')" />：
                    </td>
                    <td align="left">
                      <s:select id="poiType" name="poiInfo.poiType" list="poiTypeMap" headerKey="" headerValue="%{getText('please.select')}" onchange="onPoiTypeChange()">
                      </s:select>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.level')" />：
                    </td>
                    <td align="left">
                      <s:select id="poiLevel" name="poiInfo.poiLevel" list="poiLevelMap" headerKey="" headerValue="%{getText('please.select')}" onchange="onPoiLevelChange()">
                      </s:select>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.service')" />：
                    </td>
                    <td align="left">
                      <s:select id="poiService" name="poiInfo.poiServiceType" list="poiServiceMap" headerKey="" headerValue="%{getText('please.select')}" onchange="onPoiLevelChange()">
                      </s:select>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('common.country')" />：
                    </td>
                    <td align="left">
                      <s:select id="countryId" name="poiInfo.countryId" list="countryInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getProvinceInfo()">
                      </s:select>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('common.province')" />：
                    </td>
                    <td align="left">
                      <s:select id="provinceId" name="poiInfo.provinceId" list="provinceInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getCityInfo()">
				      </s:select>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('common.city')" />：
                    </td>
                    <td align="left">
                      <s:select id="cityId" name="poiInfo.cityId" list="cityInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="onCityChange()">
                      </s:select>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.lon')" />：
                    </td>
                    <td align="left">
                      <s:textfield id="poiLon" name="poiInfo.poiLon" maxlength="50" cssStyle="ime-mode:disabled"/>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.lat')" />：
                    </td>
                    <td align="left">
                      <s:textfield id="poiLat" name="poiInfo.poiLat" maxlength="50" cssStyle="ime-mode:disabled"/>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.concate')" />：
                    </td>
                    <td align="left">
                      <s:textfield id="concatePerson" name="poiInfo.concatePerson" maxlength="20"/>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.tel')" />：
                    </td>
                    <td align="left">
                      <s:textfield id="concateTel" name="poiInfo.concateTel" maxlength="30" cssStyle="ime-mode:disabled"/>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.fax')" />：
                    </td>
                    <td align="left">
                      <s:textfield id="concateFax" name="poiInfo.concateFax" maxlength="30" cssStyle="ime-mode:disabled"/>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.address')" />：
                    </td>
                    <td align="left">
                      <s:textfield id="poiAddress" name="poiInfo.poiAddress" maxlength="100"/>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.postcode')" />：
                    </td>
                    <td align="left">
                      <s:textfield id="concatePos" name="poiInfo.concatePos" maxlength="20"/>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.email')" />：
                    </td>
                    <td align="left">
                      <s:textfield id="email" name="poiInfo.email" maxlength="100" cssStyle="ime-mode:disabled"/>
                    </td>
                  </tr>
                  <tr>
                    <td height="20" valign="middle" align="right">
                      <s:property value="getText('poi.website')" />：
                    </td>
                    <td align="left">
                      <s:textfield id="website" name="poiInfo.website" maxlength="100" cssStyle="ime-mode:disabled"/>
                    </td>
                  </tr>
                  
                  <tr>
                    <td height="20" valign="top" align="right">
                      <s:property value="getText('poi.remark')" />：
                    </td>
                    <td align="left">
                      <s:textarea id="remark" name="poiInfo.remark" cols="25" rows="6"/>
                    </td>
                  </tr>
                  <tr>
		              <td class="btnBar" colspan="2">
		                <a href="#" class="buttontwo" onclick="goBack('poimanage.shtml')">
		                  <s:property value="getText('btn.back')" />
		                </a>
		                <a href="#" class="buttontwo" onclick="submitForm()">
		                  <s:property value="getText('btn.sure')" />
		                </a>
		              </td>
		          </tr>
                </table>
                 </div>
              </td>
            </tr>
          </table>
       
        </td>
	    <td valign="top">
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>
                <div id="map" style="width:100%;"></div>
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
