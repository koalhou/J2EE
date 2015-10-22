<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title><s:property value="getText('transfer.manage.menu')" />&nbsp;|&nbsp;<s:property value="getText('sim.manage')" /></title>
<%@include file="../common/meta.jsp" %>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="baseInfoImport_validate.jsp"%>
  <s:form id="baseinfoimport_form" action="importBaseInfo" method="post" enctype="multipart/form-data">
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
            <s:property value="getText('base.info.import')" />
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
              <td class="fsBlack" align="left">
                <s:select id="parentId" 
                          name="baseInfo.parentId" 
                          list="brandMap"  
                          headerKey="" 
                          headerValue="%{getText('please.select')}" 
                          onchange="onBrandChange()">
                </s:select>
              </td>
            </tr>
            </s:if>
            
		    <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('base.info.type')" />：
              </td>
              <td class="fsBlack" align="left">
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
            
            <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('file.choice.filepath')" />：
              </td>
              <td class="fsBlack" align="left">
                <s:file label="choicefile" 
                        theme="simple" 
                        name="file" 
                        size="60"
                        onkeydown="return false;"
                        cssStyle="height: 17px;line-height: 17px;font-size: 12px;border: #006699 1px solid;padding-left: 2px;padding-top: 1px;"/>
                <a href="<s:url value='/template/BaseInformation.xls' />"><s:property value="getText('file.template.download')" /></a>
              </td>
            </tr>
            <tr>
              <td height="40" align="right">
                <b>
                  <span class="noticeMsg"><s:property value="getText('import.notice')" />：</span>
                </b>
              </td>
              <td height="40" align="left">
                <b>
                  <span class="noticeMsg"><s:property value="getText('import.tip.info')" /></span>
                </b>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <s:url id="cancelImport" action="baseinfomanage">
            <s:param name="codeType"><s:property value="baseInfo.codeType" /></s:param>
            <s:param name="page">${page}</s:param>
            <s:param name="pageSize">${pageSize}</s:param>
          </s:url>
          
          <a href="#" class="buttontwo" onclick="goBack('${cancelImport}')">
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
