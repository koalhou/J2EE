<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<div id="main" style="overflow: auto;">
		  <s:form id="add_oilsetform" action="oilSet_add" method="post">
		  <s:hidden id="oilset.organization_id" name="oilset.organization_id"></s:hidden>
		  <hidden id="entiID" name="entiID" value="<s:property value='#session.adminProfile.entiID'/>" />
		  <s:hidden name="page" />
		  <s:hidden name="pageSize" />
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		   		 <tr>
		 		 <td valign="top">
			  			<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="36" valign="top" background="../images/tree_tabBg.gif">
							<div class="toolbar">
							<div class="contentTil"><s:text name="menu3.khyhsz" /></div>
							</div>
							</td>
						</tr>
						</table> 
		               <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                  <tr>
		                  <td align="center"><s:actionmessage cssStyle="font-size: 12px;color: #009900"
			              /><s:actionerror cssStyle="font-size: 12px;color: #CC0000"  />
			             </td>
		                 </tr>
		               </table>
		    <table class="msgBox2" width="650" border="0" align="center" cellpadding="0" cellspacing="0">
		      <tr>
		        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>">
		          <span class="msgTitle">&nbsp;&nbsp;<s:text name="oil.create" /></span>
		        </td>
		      </tr>
		      <tr>
		        <td align="center">
				  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td width="30%" height="28" align="right">考核月度：</td>
		              <td width="70%" align="left">
		              <input readonly="readonly" type="text" name="oilset.check_time_code" value="" id="check_time_code" style="width:120px" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM',autoPickDate:true,isShowToday:false})"/>
		              <span class="noticeMsg">*</span>
		              </td>
		            </tr>
		            <tr>
		              <td width="30%" height="28" align="right">车辆品牌：</td>
		              <td width="70%" align="left">
		                <s:select  id="oilset.carBrand" name="oilset.carBrand" cssStyle="width:122px" list="carPinPaiList" headerKey="" headerValue="%{getText('please.select')}"  onchange="carBrandType()" ></s:select>
		                <span class="noticeMsg">*</span>
		              </td>
		            </tr>
		            <tr>
		              <td width="30%" height="28" align="right">车型：</td>
		              <td width="70%" align="left">
		                <s:select id="oilset.carType" name="oilset.carType" cssStyle="width:122px" list="carTypeList" headerKey="" headerValue="%{getText('please.select')}"  ></s:select>
		                 <span class="noticeMsg">*</span>              
		              </td>
		           </tr>
		           <tr>
		              <td width="30%" height="28" align="right">考核油耗：</td>
		              <td width="70%" align="left">
		                <s:textfield id="check_value" name="oilset.check_value" cssStyle="width:120px" maxLength="10" />&nbsp;(单位：升/L)
		              </td>
		           </tr>
		          </table>
		        </td>
		      </tr>
		      <tr>
		        <td class="btnBar"> 
		          <a href="#" onclick="goBack('oilSet.shtml')" class="buttontwo">取消</a>
		          <a href="#" class="buttontwo" onclick="submitForm()">确定</a>
		        </td>
		      </tr>
		    </table>
		    </td></tr>               				 
		 </table>
		   </s:form>
		   </div>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="oilset_validate.jsp"%>
<%@include file="addoilset_js.jsp"%>
</body>

</html>