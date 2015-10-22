<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('system.set.menu')" />&nbsp;|&nbsp;<s:property value="getText('xs.bm.menu')" />&nbsp;|&nbsp;<s:property value="getText('base.info.name')" />
</title>
<%@include file="../common/meta.jsp" %>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="baseInfoEdit_validate.jsp"%>
  <s:form id="baseinfoedit_form" action="updateBaseInfo" method="post">
    <s:hidden id="page" name="page" />
    <s:hidden id="pageSize" name="pageSize" />
    <s:hidden id="defId" name="defId" />
    <s:hidden id="codeIdOld" name="codeIdOld" />
    <s:hidden id="codeTypeOld" name="codeTypeOld" />
    
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
            <s:property value="getText('base.info')" />
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <s:if test="\"VEHICLETYPE\"==baseInfo.codeType || \"ENGINETYPE\"==baseInfo.codeType">
		    <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('base.info.type')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:select id="parentId" 
                          name="baseInfo.parentId" 
                          list="brandMap"  
                          headerKey="" 
                          headerValue="%{getText('please.select')}" 
                          onchange="onBrandChange()">
                </s:select>
              </td>
              <td width="1%">&nbsp;</td>
            </tr>
            </s:if>
		  
		    <s:if test="\"VEHICLETYPE\"==baseInfo.codeType || \"ENGINETYPE\"==baseInfo.codeType">
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('base.info.type')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:hidden id="codeTypeInput" name="baseInfo.codeType" />
                <s:select id="codeType" 
                          name="baseInfo.codeType" 
                          list="typeList" 
                          listKey="codeId" 
                          listValue="codeName" 
                          headerKey="" 
                          headerValue="%{getText('please.select')}" 
                          disabled="true">
                </s:select>
              </td>
              <td width="1%">&nbsp;</td>
            </tr>
            </s:if>
            <s:else>
		    <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('base.info.type')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:hidden id="codeTypeInput" name="baseInfo.codeType" />
                <s:select id="codeType" 
                          name="baseInfo.codeType" 
                          list="typeList" 
                          listKey="codeId" 
                          listValue="codeName"   
                          headerKey="" 
                          headerValue="%{getText('please.select')}" 
                          onchange="onCodeTypeChange()"
                          disabled="true">
                </s:select>
              </td>
              <td width="1%">&nbsp;</td>
            </tr>
            </s:else>
            
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('base.info.code')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:textfield id="codeId" name="baseInfo.codeId" maxlength="20"/>
              </td>
              <td width="1%">&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('base.info.codename')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:textfield id="codeName" name="baseInfo.codeName" maxlength="20"/>
              </td>
              <td width="1%">&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('base.info.level')" />：
              </td>
              <td width="20%" align="left">
                <s:textfield id="codeLevel" name="baseInfo.codeLevel" maxlength="20"/>
              </td>
              <td width="1%">&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('base.info.remark')" />：
              </td>
              <td width="20%" align="left">
                <s:textarea id="remark" name="baseInfo.remark" cols="60" rows="6"/>
              </td>
              <td width="1%">&nbsp;</td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar">
          <s:url id="cancelEdit" action="baseinfomanage">
            <s:param name="codeType">${codeTypeOld}</s:param>
            <s:param name="page">${page}</s:param>
            <s:param name="pageSize">${pageSize}</s:param>
          </s:url>
          <a href="#" class="buttontwo" onclick="goBack('${cancelEdit}')">
            <s:property value="getText('btn.back')" />
          </a>
          
          <s:if test="#session.perUrlList.contains('111_0_6_1_2_4')">
          <s:url id="delBaseInfo" action="deleteBaseInfo">
            <s:param name="defId">${defId}</s:param>
            <s:param name="codeType">${codeTypeOld}</s:param>
            <s:param name="page">${page}</s:param>
            <s:param name="pageSize">${pageSize}</s:param>
          </s:url>
          <a href="#" class="buttontwo" onclick="delBaseInfo('${delBaseInfo}');">
            <s:property value="getText('btn.delete')" />
          </a>
          </s:if>
          
          <s:if test="#session.perUrlList.contains('111_0_6_1_2_3')">
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
