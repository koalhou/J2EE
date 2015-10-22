<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('menu.cl')" />&nbsp;|&nbsp;<s:property value="getText('tmmanage.terminal')" />
</title>
<%@include file="../common/meta.jsp" %>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="terminalEdit_validate.jsp"%>
  <s:form id="terminaledit_form" action="updateTerminal" method="post">
    <s:hidden id="page" name="page" />
    <s:hidden id="pageSize" name="pageSize" />
    <s:hidden id="delChannelId" name="delChannelId"/>
    <s:hidden id="terminalId" name="terminalId"/>
    <s:hidden id="terminalOldCode" name="terminalOldCode"/>
    
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
            <s:property value="getText('terminal.info.name')" />
            (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('oem.name')" />：
              </td>
              <td width="30%" class="fsBlack" align="left">
                <s:select id="oemId" name="terminalInfo.oemId" list="oemInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getTypeInfo()" cssStyle="width:131px;">
                </s:select>
              </td>
              <td>&nbsp;</td>
              <td width="14%" align="right">
                <s:property value="getText('device.name')" />：
              </td>
              <td width="30%" align="left">
                <s:select id="typeId" name="terminalInfo.typeId" list="typeInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="getProtocalInfo()" cssStyle="width:131px;">
                </s:select>
              </td>
              <td>&nbsp;</td>
            </tr>
		    <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('protocal.name')" />：
              </td>
              <td width="30%" align="left">
                <s:select id="protocalId" name="terminalInfo.protocalId" list="protocalInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="onProtocalChange()" cssStyle="width:131px;">
				</s:select>
              </td>
              <td>&nbsp;</td>
              <td width="14%" height="28" align="right">
                <s:property value="getText('common.list.terminal')" />：
              </td>
              <td width="30%" align="left">
                <s:textfield id="terminalCode" name="terminalInfo.terminalCode" maxlength="32" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="28" align="right">
                <s:property value="getText('terminal.video.id')" />：
              </td>
              <td align="left">
                <s:textfield id="videoId" name="terminalInfo.videoId" maxlength="32" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
              <td width="14%" height="28" align="right">视频厂家：
              </td>
              <td width="30%" align="left">
                <s:select id="videoFactory" name="terminalInfo.videoFactory" list="#{'HI':'海康','DA':'大华'}" headerKey="" headerValue="%{getText('please.select')}" onchange="onVideoFactoryChange()" cssStyle="width:131px;">
				</s:select>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">流媒体地址：
              </td>
              <td width="30%" align="left">
                <s:textfield id="streamServerIp" name="terminalInfo.streamServerIp" maxlength="30" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
              <td width="14%" height="28" align="right">视频地址：
              </td>
              <td width="30%" align="left">
                <s:textfield id="videoServerIp" name="terminalInfo.videoServerIp" maxlength="30" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">服务器用户名：
              </td>
              <td width="30%" align="left">
                <s:textfield id="videoUser" name="terminalInfo.videoUser" maxlength="30" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
              <td width="14%" height="28" align="right">服务器密码：
              </td>
              <td width="30%" align="left">
                <s:textfield id="videoPassword" name="terminalInfo.videoPassword" maxlength="50" cssStyle="ime-mode:disabled"/>
              </td>
              <td>&nbsp;</td>
            </tr>
          </table>
        </td>
      </tr>

      <tr>
        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="80%" class="titleStyleZi">
                <s:property value="getText('terminal.channel.info')" />
                (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
              </td>
              <td align="right"><div class="buhuanhangbut"><a href="#" onclick="addChannel()" class="btnAdd" title="<s:property value="getText('msg.add')" />"></a></div></td>
              <td width="1%">&nbsp;</td>
            </tr>
          </table>
        </td>
      </tr>
      
      <tr>
        <td>
          <s:label id="channelWarn" cssClass="error"/>
        </td>
      </tr>
      <s:iterator value="channelList" status="stat">
      <tr>
        <td>
          <table>
            <tr>
              <td>
                <s:hidden id="channelId" name="channelList[%{#stat.index}].channelId" value="%{channelList[#stat.index].channelId}" />  
                &nbsp;&nbsp;<s:property value="getText('channel.name')" />：
                <s:textfield id="channelName" name="channelList[%{#stat.index}].channelName" value="%{channelList[#stat.index].channelName}" cssClass="tf" maxlength="15"/>  
                <span class="noticeMsg">*</span>
                &nbsp;<s:property value="getText('channel.number')" />：
                <s:textfield id="channelNumber" name="channelList[%{#stat.index}].channelNumber" value="%{channelList[#stat.index].channelNumber}" cssClass="tf" maxlength="2" cssStyle="ime-mode:disabled"/>
                <span class="noticeMsg">*</span>
              </td>
              <td>
                <a href="#" class="sbutton" onclick="deleteChannel('<s:property value="%{channelList[#stat.index].channelId}" />')">
                  <s:property value="getText('btn.delete')" />
                </a>  
              </td>
            </tr>
          </table>
        </td>
      </tr>
      </s:iterator> 
      
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('terminalmanage.shtml')">
            <s:property value="getText('btn.back')" />
          </a>
          
          <s:if test="#session.perUrlList.contains('111_0_3_4_4')">
          <s:url id="delTerminal" action="deleteTerminal">
            <s:param name="terminalId">${terminalId}</s:param>
            <s:param name="terminalOldCode">${terminalOldCode}</s:param>
            <s:param name="page">${page}</s:param>
            <s:param name="pageSize">${pageSize}</s:param>
          </s:url>
          <a href="#" class="buttontwo" onclick="delTerminal('${delTerminal}');">
            <s:property value="getText('btn.delete')" />
          </a>
          </s:if>
          
          <s:if test="#session.perUrlList.contains('111_0_3_4_3')">
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
