<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('usermanage.menu')" />&nbsp;|&nbsp;<s:property value="getText('usermanage.name')" />
</title>
<%@include file="../common/meta.jsp" %>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="userInfo_validate.jsp"%>
  <s:form id="userinfo_form" action="addexecute" method="post">
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
            <s:property value="getText('user.login.info')" />
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="left">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('login.username')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:textfield id="loginName" name="userDetail.loginName" maxlength="15" cssStyle="ime-mode:disabled"/>
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('login.password')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:password id="password" name="userDetail.password" cssStyle="width:127px" maxlength="30"/>
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('user.confirm.password')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:password id="confirmPassword" name="userDetail.confirmPassword" cssStyle="width:127px" maxlength="30"/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>" align="left">
          <span class="msgTitle">&nbsp;&nbsp;
            <s:property value="getText('user.info.name')" />
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('user.type')" />：
              </td>
              <td width="30%" class="fsBlack"  align="left">
                <s:select id="userType" name="userDetail.userType" list="subSysMap" onchange="changeUserType();">
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td width="14%" align="right">
                <s:property value="getText('common.list.enterprise')" />：
              </td>
              <td width="30%"  align="left">
                <!-- 
                <s:select id="entiprise" name="userDetail.entipriseId" list="entiList" listKey="enterpriseId" listValue="enterpriseName" >
				</s:select>
				-->
				<s:hidden id="entipriseId" name="userDetail.entipriseId"/>
                <s:textfield id="entipriseName" 
                             name="userDetail.entipriseName"
                             onclick="openEnterpriseBrowse()" 
                             readonly="true"/>
				
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('user.sex')" />：
              </td>
              <td width="20%"  align="left">
                <s:select id="userSex" name="userDetail.userSex" list="sexSysMap" >
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td width="14%" height="28" align="right">
                <s:property value="getText('user.jobtype')" />：
              </td>
              <td width="20%"  align="left">
                <s:select id="jobType" name="userDetail.jobType" list="jobTypeMap" headerKey="" headerValue="%{getText('please.select')}">
                </s:select>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('common.country')" />：
              </td>
              <td width="20%"  align="left">
                <s:select id="countryId" name="userDetail.countryId" list="countryInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getProvinceInfo()">
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td align="right" width="14%">
                <s:property value="getText('common.province')" />：
              </td>
              <td width="20%"  align="left">
                <s:select id="provinceId" name="userDetail.provinceId" list="provinceInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getCityInfo()">
				</s:select>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('common.city')" />：
              </td>
              <td width="20%"  align="left">
                <s:select id="cityId" name="userDetail.cityId" list="cityInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="onCityChange()">
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td width="14%" height="28" align="right">
                <s:property value="getText('user.real.name')" />：
              </td>
              <td width="20%"  align="left">
                <s:textfield id="userName" name="userDetail.userName" maxlength="20"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td align="right" width="14%">
                <s:property value="getText('user.empno')" />：
              </td>
              <td width="20%"  align="left">
				<s:textfield id="empno" name="userDetail.empno" maxlength="10">
				</s:textfield>
              </td>
              <td>&nbsp;</td>
              <td width="14%" height="28" align="right">
                <s:property value="getText('user.duty')" />：
              </td>
              <td width="20%"  align="left">
                <s:textfield id="userDuty" name="userDetail.userDuty" maxlength="20"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td align="right" width="14%">
                <s:property value="getText('user.birth')" />：
              </td>
              <td width="20%"  align="left">
                <s:textfield id="userBirth" name="userDetail.userBirth" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate">
				</s:textfield>
              </td>
              <td>&nbsp;</td>
              <td width="14%" height="28" align="right">
                <s:property value="getText('user.fax')" />：
              </td>
              <td width="20%"  align="left">
                  <s:textfield id="fax" name="userDetail.fax" maxlength="20"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('user.cell')" />：
              </td>
              <td  align="left">
                <s:textfield id="userCell" name="userDetail.userCell" maxlength="20"/>
              </td>
              <td>&nbsp;</td>
              <td align="right">
                <s:property value="getText('user.tel')" />：
              </td>
              <td  align="left">
                <s:textfield id="userTel" name="userDetail.userTel" maxlength="20"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('user.email')" />：
              </td>
              <td  align="left">
                <s:textfield id="userEmail" name="userDetail.userEmail" maxlength="50" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
              <td align="right">
                <s:property value="getText('user.id.card')" />：
              </td>
              <td  align="left">
                <s:textfield id="userIdCard" name="userDetail.userIdCard" maxlength="50" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('user.remarks')" />：
              </td>
              <td align="left" colspan="5"  >
                <s:textarea id="remarks" name="userDetail.remarks" cols="60" rows="6"/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('usermanage.shtml')">
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
