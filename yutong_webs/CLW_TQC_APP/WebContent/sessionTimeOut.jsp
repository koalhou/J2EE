<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
</head>
<BODY text="#000000" vLink="#5493b4" link="#003fb7">
<script type="text/javascript">
	window.top.location = "<s:url value='/login.jsp' />";
	
</script>
</body>

<%-- <table class="tableclass" cellspacing="1" border="0" width="100%" align="center">
   <tr   height="30">
          <td   class="tdclass" nowrap="nowrap"><font color="#FF0000"><b>错误提示</b></font></td>
  </tr>
</table>
    <br>
    <table width="70%" border="0" align="center" cellpadding="2" cellspacing="1" class="bgDarker">
      <tr   height="25">
        <td  class="tdclass" disabled="false">·错误原因：页面超时</td>
      </tr>
      <tr   height="25">
        <td    class="tdclass" disabled="false"> ·可能情况或解决办法：</td>
      </tr>
       <tr   height="25">
        <td   class="tdclass" disabled="false">
		　　1、长时间没有进行操作，出于安全考虑，需要您重新登录系统。<br>
         　 2、没有登录系统，请返回首页登录。</td>
      </tr>
</table>--%>
</html>