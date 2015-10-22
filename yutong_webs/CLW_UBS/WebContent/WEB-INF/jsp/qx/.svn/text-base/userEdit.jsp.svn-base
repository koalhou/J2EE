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
  <%@include file="userEdit_validate.jsp"%>
  <s:form id="useredit_form" action="userUpdate" method="post">
  <s:hidden id="page" name="page" />
  <s:hidden id="pageSize" name="pageSize" />
  <s:hidden id="userId" name="userId" />
  <s:hidden id="oldUsetType" name="oldUsetType" />
  
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
                <s:textfield id="loginName" name="userDetail.loginName" disabled="true"/>
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
              <td width="30%" class="fsBlack" align="left">
                <s:select id="userType" name="userDetail.userType" list="subSysMap" onchange="changeUserType();">
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td width="14%" align="right">
                <s:property value="getText('common.list.enterprise')" />：
              </td>
              <td width="30%" align="left">
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
              <td width="20%" align="left">
                <s:select id="userSex" name="userDetail.userSex" list="sexSysMap" >
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td width="14%" height="28" align="right">
                <s:property value="getText('user.jobtype')" />：
              </td>
              <td width="20%" align="left">
                <s:select id="jobType" name="userDetail.jobType" list="jobTypeMap" headerKey="" headerValue="%{getText('please.select')}">
                </s:select>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('common.country')" />：
              </td>
              <td width="20%" align="left">
                <s:select id="countryId" name="userDetail.countryId" list="countryInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getProvinceInfo()">
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td align="right" width="14%">
                <s:property value="getText('common.province')" />：
              </td>
              <td width="20%" align="left">
                <s:select id="provinceId" name="userDetail.provinceId" list="provinceInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getCityInfo()">
				</s:select>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('common.city')" />：
              </td>
              <td width="20%" align="left">
                <s:select id="cityId" name="userDetail.cityId" list="cityInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="onCityChange()">
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td width="14%" height="28" align="right">
                <s:property value="getText('user.real.name')" />：
              </td>
              <td width="20%" align="left">
                <s:textfield id="userName" name="userDetail.userName" maxlength="20"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td align="right" width="14%">
                <s:property value="getText('user.empno')" />：
              </td>
              <td width="20%" align="left">
				<s:textfield id="empno" name="userDetail.empno" maxlength="10">
				</s:textfield>
              </td>
              <td>&nbsp;</td>
              <td width="14%" height="28" align="right">
                <s:property value="getText('user.duty')" />：
              </td>
              <td width="20%" align="left">
                <s:textfield id="userDuty" name="userDetail.userDuty" maxlength="20"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td align="right" width="14%">
                <s:property value="getText('user.birth')" />：
              </td>
              <td width="20%" align="left">
                <s:textfield id="userBirth" name="userDetail.userBirth" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate">
				</s:textfield>
              </td>
              <td>&nbsp;</td>
              <td width="14%" height="28" align="right">
                <s:property value="getText('user.fax')" />：
              </td>
              <td width="20%" align="left">
                  <s:textfield id="fax" name="userDetail.fax" maxlength="20"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('user.cell')" />：
              </td>
              <td align="left">
                <s:textfield id="userCell" name="userDetail.userCell" maxlength="20"/>
              </td>
              <td>&nbsp;</td>
              <td align="right">
                <s:property value="getText('user.tel')" />：
              </td>
              <td align="left">
                <s:textfield id="userTel" name="userDetail.userTel" maxlength="20"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('user.email')" />：
              </td>
              <td align="left">
                <s:textfield id="userEmail" name="userDetail.userEmail" maxlength="50" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
              <td align="right">
                <s:property value="getText('user.id.card')" />：
              </td>
              <td align="left">
                <s:textfield id="userIdCard" name="userDetail.userIdCard" maxlength="50" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('user.remarks')" />：
              </td>
              <td align="left" colspan="5">
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
          
          <!--
          <s:if test="#session.perUrlList.contains('111_0_1_3_5')">
            <s:url id="resetPwd" action="resetPwd">
              <s:param name="userId">${userId}</s:param>
              <s:param name="page">${page}</s:param>
              <s:param name="pageSize">${pageSize}</s:param>
            </s:url>
            <a href="#" class="buttontwo" onclick="resetPwd('${resetPwd}');">
              <s:property value="getText('user.reset.password')" />
            </a>
          </s:if>
          -->
          <s:if test="\"2\"==userDetail.validFlag">
            <s:if test="#session.perUrlList.contains('111_4_8_1_3')">
            <s:url id="startUseUser" action="userStartUse">
              <s:param name="userId">${userId}</s:param>
              <s:param name="page">${page}</s:param>
              <s:param name="pageSize">${pageSize}</s:param>
            </s:url>
            <a href="#" class="buttontwo" onclick="startUseUser('${startUseUser}');">
              <s:property value="getText('user.normal')" />
            </a>
            </s:if>
          </s:if>
          <s:else>
            <s:if test="#session.perUrlList.contains('111_4_8_1_7')">
            <s:url id="userForbid" action="userForbid">
              <s:param name="userId">${userId}</s:param>
              <s:param name="page">${page}</s:param>
              <s:param name="pageSize">${pageSize}</s:param>
            </s:url>
            <a href="#" class="buttontwo" onclick="forbidUser('${userForbid}');">
              <s:property value="getText('user.forbid')" />
            </a>
            </s:if>
          </s:else>
          
          <s:if test="#session.perUrlList.contains('111_4_8_1_4')">
            <s:url id="delUser" action="userDelete">
              <s:param name="userId">${userId}</s:param>
              <s:param name="page">${page}</s:param>
              <s:param name="pageSize">${pageSize}</s:param>
            </s:url>
            <a href="#" class="buttontwo" onclick="delUser('${delUser}');">
              <s:property value="getText('btn.delete')" />
            </a>
          </s:if>
          
          <s:if test="#session.perUrlList.contains('111_4_8_1_3')">
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
