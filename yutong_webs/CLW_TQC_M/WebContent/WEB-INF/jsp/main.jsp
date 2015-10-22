<%@include file="common/taglibs.jsp" %>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="common/meta.jsp" %>
<title><s:property value="getText('main.name')" /></title>
<script type="text/javascript"> 
</script>
<style type="text/css">
.reportOnline2 p{
	font-size: 12px;
	text-indent: 25px;
	line-height: 24px;
	padding:5px 0 12px 0;
	border-bottom:1px dotted #CCCCCC;
}

.msgBoxind p{
	font-size: 12px;
	text-indent: 25px;
	line-height: 24px;
	padding:18px 0 0 0;
}

.STYLE6 {
	font-size: 10px;
	color: #43132F;
}

</style>
</head>
<body>
  <%@include file="common/menu.jsp"%>
  
  <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
	  <td valign="top">
	  
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="36" valign="top" background="../images/tree_tabBg.gif">
            <div class="toolbar">
              <div class="contentTil">
                <s:property value="getText('main.clwm.introduce')" />
              </div>
            </div>
          </td>
        </tr>
      </table>
      
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="30" align="center">&nbsp;</td>
          <td valign="top" class="msgBoxind">&nbsp;</td>
          <td align="center" valign="top">&nbsp;</td>
          <td valign="top" class="msgBoxind">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" valign="top">
            <img src="<s:url value='/images/ind_1.jpg'/>" width="129" height="186">
          </td>
          <td width="35%" valign="top" class="msgBoxind">
            <p>
              <s:property value="getText('main.user.p1')" /><br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="STYLE6">●</span>&nbsp;<s:property value="getText('main.user.p2')" /><br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="STYLE6">●</span>&nbsp;<s:property value="getText('main.user.p3')" /><br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="STYLE6">●</span>&nbsp;<s:property value="getText('main.user.p4')" /><br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="STYLE6">●</span>&nbsp;<s:property value="getText('main.user.p5')" />
            </p>
          </td>
          <td align="center" valign="top">
            <img src="<s:url value='/images/ind_3.jpg'/>" width="129" height="186">
          </td>
          <td valign="top" class="msgBoxind">
            <p>
              <s:property value="getText('main.enterprise.p1')" />
              <s:property value="getText('main.enterprise.p2')" />
              <s:property value="getText('main.enterprise.p3')" />
              <s:property value="getText('main.enterprise.p4')" />
            </p>
          </td>
        </tr>
        <tr>
          <td height="40" align="center" valign="top">&nbsp;</td>
          <td valign="top" class="msgBoxind">&nbsp;</td>
          <td align="center" valign="top">&nbsp;</td>
          <td valign="top" class="msgBoxind">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" valign="top">
            <img src="<s:url value='/images/ind_2.jpg'/>" width="129" height="186">
          </td>
          <td valign="top" class="msgBoxind">
            <p>
              <s:property value="getText('main.vehicle.p1')" />
            </p>
          </td>
          <td align="center" valign="top">
            <img src="<s:url value='/images/ind_4.jpg'/>" width="129" height="186">
          </td>
          <td valign="top" class="msgBoxind">
            <p>
              <s:property value="getText('main.terminal.p1')" />
            </p>
            <p>
              <s:property value="getText('main.terminal.p2')" />
            </p>
          </td>
        </tr>
        <tr>
          <td height="40" align="center" valign="top">&nbsp;</td>
          <td valign="top" class="msgBoxind">&nbsp;</td>
          <td align="center" valign="top">&nbsp;</td>
          <td valign="top" class="msgBoxind">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" valign="top">
            <img src="<s:url value='/images/ind_5.jpg'/>" width="161" height="186">
          </td>
          <td valign="top" class="msgBoxind">
            <p>
              <s:property value="getText('main.code.p1')" />
            </p>
          </td>
          <td align="center" valign="top">
            <img src="<s:url value='/images/ind_6.jpg'/>" width="161" height="186">
          </td>
          <td valign="top" class="msgBoxind">
            <p>
              <s:property value="getText('main.system.p1')" />
            </p>
          </td>
        </tr>
        <tr>
          <td align="center" valign="top">&nbsp;</td>
          <td valign="top" class="msgBoxind">&nbsp;</td>
          <td align="center" valign="top">&nbsp;</td>
          <td valign="top" class="msgBoxind">&nbsp;</td>
        </tr>
      </table>
      </td>
    </tr>
  </table>
  
  <%@include file="common/copyright.jsp"%>
</body>
</html>


