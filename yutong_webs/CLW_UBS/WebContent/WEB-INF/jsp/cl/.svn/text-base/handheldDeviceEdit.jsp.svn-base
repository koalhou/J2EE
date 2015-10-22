<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<title>
  <s:property value="getText('menu.cl')" />&nbsp;|&nbsp;<s:property value="getText('menu.cl.handheld')" />
</title>
<%@include file="../common/meta.jsp" %>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="handheldDeviceEdit_validate.jsp"%>
  <s:form id="handhelddeviceedit_form" action="updateHandheldDevice" method="post">
    <s:hidden id="page" name="page" />
    <s:hidden id="pageSize" name="pageSize" />
    <s:hidden id="terminalId" name="handheldDeviceInfo.terminalId" />
    <s:hidden id="vehicleId" name="handheldDeviceInfo.vehicleId" />
    <s:hidden id="simId" name="handheldDeviceInfo.simId" />
    
    <table id="handheldTable" width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
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
          <span class="msgTitle">
            &nbsp;&nbsp;手持设备注册信息(<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
          </span>
        </td>
      </tr>
      <tr>
        <td align="center">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <tr>
              <td width="14%" height="28" align="right">
                <s:property value="getText('common.list.enterprise')" />：
              </td>
              <td width="20%" class="fsBlack" align="left">
                <s:hidden id="entipriseId" name="handheldDeviceInfo.entipriseId"/>
                <s:textfield id="entipriseName" 
                             name="handheldDeviceInfo.entipriseName"
                             onclick="openEnterpriseBrowse()" 
                             readonly="true"/>
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">手机IMEI号：</td>
              <td width="20%" class="fsBlack" align="left">
                <s:property value="handheldDeviceInfo.cellPhoneImei" />
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">设备编号：</td>
              <td width="20%" class="fsBlack" align="left">
                <s:property value="handheldDeviceInfo.handheldDeviceNo" />
              </td>
            </tr>
            <tr>
              <td width="14%" height="28" align="right">手机号：</td>
              <td width="20%" class="fsBlack" align="left">
                <s:property value="handheldDeviceInfo.cellPhone" />
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="btnBar"> 
          <a href="#" class="buttontwo" onclick="goBack('handheldDevice.shtml')">
            <s:property value="getText('btn.back')" />
          </a>
          
          <s:if test="#session.perUrlList.contains('111_0_2_4_4')">
            <s:url id="delHandheldDevice" action="delHandheldDevice">
              <s:param name="terminalId">${handheldDeviceInfo.terminalId}</s:param>
              <s:param name="vehicleId">${handheldDeviceInfo.vehicleId}</s:param>
              <s:param name="simId">${handheldDeviceInfo.simId}</s:param>
              <s:param name="page">${page}</s:param>
              <s:param name="pageSize">${pageSize}</s:param>
            </s:url>
            <a href="#" class="buttontwo" onclick="delHandheldDevice('${delHandheldDevice}');">
              <s:property value="getText('btn.delete')" />
            </a>
          </s:if>
          
          <s:if test="#session.perUrlList.contains('111_0_2_4_3')">
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
<script type="text/javascript">
	//页面自适应
	(function (jQuery) {
	 jQuery(window).resize(function(){
	 
	  testWidthHeight();
	  
	 });
	 jQuery(window).load(function (){
	  
	  testWidthHeight();
	  
	 });
	 
	})(jQuery);
	
	//获取页面高度
	function get_page_height() {
	 var height = 0;
	 
	 if (jQuery.browser.msie) {
	     height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
	 } else {
	     height = self.innerHeight;
	 }
	 return height;
	}
	//获取页面宽度
	function get_page_width() {
	 var width = 0;
	 if(jQuery.browser.msie){ 
	  width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
	 } else { 
	  width = self.innerWidth; 
	 } 
	 return width;
	}
	 
	//计算控件宽高
	function testWidthHeight(){
		var h = get_page_height();
		var test=document.getElementById("handheldTable");
		if(h>165){
			test.style.height = h-165;
		}else{
		}
	}
</script>
</html>
