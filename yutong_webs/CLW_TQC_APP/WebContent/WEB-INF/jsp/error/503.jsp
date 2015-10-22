<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<title>HTTP 503错误</title>
<link rel="stylesheet" type="text/css" href="/CLW_XCP/styles/global.css" />
<link rel="stylesheet" type="text/css" href="/CLW_XCP/styles/list.css" />
<style type="text/css">
.welcome {padding:5px;}
.welcome p{ line-height:22px; text-indent:10px;}
.STYLE2 {
	font-size: 36px;
	line-height:65px;
	font-family: Georgia, "Times New Roman", Times, serif;
}
.welcome li {
	text-indent: 24px;
	list-style-type: disc;
	line-height: 22px;
}
.STYLE3 {
	font-size: 36px;
	font-weight: bold;
}
.STYLE4 {
	font-size: 18px;
	font-weight: bold;
}
.STYLE5 {font-size: 14px}
.answer{ text-align:left;}
.answer div{ width:400px; margin:0 auto;}
</style>
</head>
<body>
  <div class="navbar">
    <div id="companyLogo" style="background: url(/CLW_XCP/images/company_logo.jpg) no-repeat left center;">
    </div>
  </div>
  
  <table height="628px" class="welcome" width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
    <tr valign="top">
      <td width="50%" align="center">
        <table width="900" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="80">
              <div  class="STYLE3">HTTP 503-服务处于“关闭维修”状态</div>
            </td>
          </tr>
          <tr>
            <td height="40">
              <span class="STYLE4">
                <img src="/CLW_XCP/images/stop.jpg" width="50" height="50" align="middle">无法显示该页面 
              </span>
            </td>
          </tr>
          <tr>
            <td height="60" class="answer">
              <div class="STYLE5">最可能的原因是：</div>
              <div>
              <ul>
                <li>该网站正在进行维护</li>
                <li>该网站负载超限</li>
              </ul>
              </div>
              <div class="STYLE5">您可以尝试以下操作：</div>
              <div>
              <ul>
                <li>请稍后再访问</li>
                <li>请与网站的管理员联系</li>
              </ul>
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <p class="STYLE2">&nbsp;</p>
            </td>
          </tr>
          <tr>
            <td align="center">
              <img src="/CLW_XCP/images/503.gif" width="667" height="215">
            </td>
          </tr>
          <tr>
            <td align="center">&nbsp;</td>
          </tr>
        </table>          
      </td>
    </tr>
  </table>
  
  <!--  
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="footer">
   <tr>
    <td width="130" align="center" class="logo_gray"></td>
    <td align="right" style="color:#555;">版权所有 <span style="font-family:Arial, Helvetica, sans-serif">&copy;</span> 宇通集团 2010-2015 </td>
	<td width="16">&nbsp;</td>
   </tr>
  </table>-->
</body>
</html>
