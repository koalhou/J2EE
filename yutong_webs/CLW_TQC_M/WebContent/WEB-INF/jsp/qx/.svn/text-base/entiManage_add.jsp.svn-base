<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<title><s:property value="getText('qx.menu')"/>&nbsp;|&nbsp;<s:property value="getText('enterprise.menu')"/></title>
</head>
<body onload="show_enterprise_country();newload();">
<script type="text/javascript">
function newload(){
	onfocusEnterID='';
	var code = document.getElementById('ENTERPRISE_CODE');
	var msgnum=  document.getElementById('MSG_NUM');
	var model = document.getElementById('ENTERPRISE_MODEL');
	var netadd=$('NETADDRESS');
	var filestr=$('file');
	code.value = '';
	code.disabled = true;
	msgnum.value = '';
	msgnum.disabled = true;
	filestr.disabled = true;
	netadd.value='';
	netadd.disabled = true;
	model.disabled = true;
	document.getElementById('ENTERPRISE_GETWAY').disabled = true;
}
</script>
<%@include file="../common/menu.jsp"%>
<%@include file="entiManage_add_validate.jsp"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="220" valign="top" class="treeline">
		<table height="628" width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td height="36" background="../images/tree_tabBg.gif"
					class="titleStyleZi"><s:property
					value="getText('enterprise.info2')" /></td>
			</tr>
			<!--<tr id="treeTr1" height="592px">
				<td valign="top"><div style="width: 220px; overflow: auto; height:592px;"><%@include file="../treeSpan.jsp"%></div></td>
			</tr>-->
			<tr id="treeTr1" height="592px">
				<td valign="top" nowrap="nowrap"><div style="width: 220px; overflow: auto; height:592px;"><table border="0" cellpadding="0"	cellspacing="8" >
											<tr id="treeTr1">
												<td valign="top" nowrap="nowrap"><%@include file="../treeSpan.jsp"%></td></tr></table></div></td>
			</tr>
		</table>
		</td>
		<td valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="36" valign="top" background="../images/tree_tabBg.gif">
				<div class="toolbar">
				<div class="contentTil"><s:property value="getText('enterprise.info')"/></div>
				</td>
			</tr>
		</table>
		<s:form id="clwForm" name="clwForm"
			action="entimanage_do_add" method="post" enctype="multipart/form-data">
			<input type="hidden" id="ChooseEnterID_tree" name="ChooseEnterID_tree" value="<%=session.getValue("ChooseEnterID_tree") %>">
			<s:hidden id="enterprise_code_serch" name="enterprise_code_serch"></s:hidden>
			<s:hidden id="full_name_serch" name="full_name_serch"></s:hidden>
			<s:hidden id="enterprise_country_serch" name="enterprise_country_serch"></s:hidden>
			<s:hidden id="enterprise_province_serch" name="enterprise_province_serch"></s:hidden>
			<s:hidden id="enterprise_city_serch" name="enterprise_city_serch"></s:hidden>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <td align="right" bgcolor="#F6F6F6">
                   <div id="message">
                     <s:actionerror theme="mat" />
                     <s:fielderror theme="mat"/>
                     <s:actionmessage theme="mat"/>
                   </div>
                 </td>
               </tr>
             </table>
			<table class="msgBox2" width="650" border="0" align="center"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="32" background="../images/msg_title_bg.gif"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('enterprise.info')"/>(<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)</span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="28" align="right"><s:property value="getText('enterprise.info.PARENT_NAME')"/>：</td>
							<td colspan="4" align="left"><label id="PARENT_NAME"><s:property value="enterpriseDataInfo.short_name"/></label><input type="hidden" id="PARENT_ID" name ="PARENT_ID" value="<s:property value="enterpriseDataInfo.enterprise_id"/>"></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_CODE')"/>：</td>
							<td width="20%" class="fsBlack" align="left"> <label>
							<input type="text" id="ENTERPRISE_CODE" name="ENTERPRISE_CODE"  maxlength="10">
							</label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.SHORT_NAME')"/>：</td>
							<td align="left"><label> <input type="text" id="SHORT_NAME"
								name="SHORT_NAME"  maxlength="10"> </label></td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.FULL_NAME')"/>：</td>
							<td width="20%" align="left"><label> <input type="text"
								id="FULL_NAME" name="FULL_NAME" maxlength="60">
							</label></td>
							<td>&nbsp;</td>
							<td align="right">
								<s:property value="getText('enterprise.info.NETADDRESS')"/>：
							</td>
							<td align="left">
								<label>
									<input type="text" id="NETADDRESS" name="NETADDRESS"  maxlength="512">
								</label>
							</td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_COUNTRY')"/>：</td>
							<td align="left"><label> 
							
							<s:select id="ENTERPRISE_COUNTRY" name="ENTERPRISE_COUNTRY" list="countryInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_province()">
							</s:select>
							
							</label></td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.ENTERPRISE_PROVINCE')"/>：</td>
							<td align="left"><label> 
							<s:select id="ENTERPRISE_PROVINCE" name="ENTERPRISE_PROVINCE" list="provinceInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_city()">
							</s:select>
							</label></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_CITY')"/>：</td>
							<td align="left"><label> 
							<s:select id="ENTERPRISE_CITY" name="ENTERPRISE_CITY" list="cityInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
							</s:select>
							</label></td>
							<td>&nbsp;</td>
							<td align="right">

								<s:property value="getText('enterprise.info.LOGO')"/>：

							</td>
							<td align="left"><label> 
								<s:file name="file" 
								        id="file" 
								        onkeydown="return false;"
                                        cssStyle="height: 17px;line-height: 17px;font-size: 12px;border: #006699 1px solid;padding-left: 2px;padding-top: 1px;">
                                </s:file>
							</label></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('enterprise.info.MODEL')"/>：</td>
							<td align="left">
							<label> 
								<s:select id="ENTERPRISE_MODEL" name="ENTERPRISE_MODEL" list="enterprise_model_map" headerKey="" headerValue="%{getText('please.select')}"  onchange="">
								</s:select>
							</label>
							</td>
							<td>&nbsp;</td>
							<td align="right">
								<s:property value="getText('enterprise.info.getway')"/>：
							</td>
							<td align="left">
								<label> 
									<s:select id="ENTERPRISE_GETWAY" name="ENTERPRISE_GETWAY" list="enterprise_getway_map" headerKey="" headerValue="%{getText('please.select')}"  onchange="">
									</s:select>
								</label>													
							</td>							
							<td>&nbsp;</td>
						</tr>						
						<tr>
							<td height="28" align="right"><s:property value="getText('enterprise.info.ADDRESS')"/>：</td>
							<td colspan="4" align="left"><label> <input type="text"
								id="ADDRESS" name="ADDRESS" size="60" maxlength="60"> </label></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.EMAIL')"/>：</td>
							<td width="20%" class="fsBlack" align="left"> <label>
							<input type="text" id="EMAIL" name="EMAIL"  maxlength="30"> </label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.POSTCODE')"/>：</td>
							<td align="left"><label> <input type="text" id="POSTCODE"
								name="POSTCODE" size="10"  maxlength="10"> </label></td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_TYPE_CFG')"/>：</td>
							<td width="20%" class="fsBlack" align="left">
							<label> 
								<s:select id="ENTERPRISE_TYPE_CFG" name="ENTERPRISE_TYPE_CFG" list="enterprise_type_cfgMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
								</s:select>
							</label>
							</td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.ENTERPRISE_LEVE_CFG')"/>：</td>
							<td align="left">
							<label> 
								<s:select id="ENTERPRISE_LEVE_CFG" name="ENTERPRISE_LEVE_CFG" list="enterprise_leve_cfgMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
								</s:select>
							</label>
							</td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_KIND_CFG')"/>：</td>
							<td width="20%" class="fsBlack" align="left">
							<label> 
								<s:select id="ENTERPRISE_KIND_CFG" name="ENTERPRISE_KIND_CFG" list="enterprise_kind_cfgMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
								</s:select>
							</label>
							</td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.MONEY_KIND_CFG')"/>：</td>
							<td align="left">
							<label> 
								<s:select id="MONEY_KIND_CFG" name="MONEY_KIND_CFG" list="money_kind_cfgMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
								</s:select>
							</label>
							</td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.LANGUAGE_CFG')"/>：</td>
							<td width="20%" class="fsBlack" align="left">
							<label> 
								<s:select id="LANGUAGE_CFG" name="LANGUAGE_CFG" list="language_cfgMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
								</s:select>
							</label>
							</td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.ISUSED')"/>：</td>
							<td align="left">
							<label> 
								<select name="ISUSED" id="ISUSED">
			                              	<option value="0" selected="selected"><s:property value="getText('enterprise.info.ISUSED_0')"/></option>
			                              	<option value="1"><s:property value="getText('enterprise.info.ISUSED_1')"/></option>
		                        </select>
							</label>
							</td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.CONTACT_P')"/>：</td>
							<td width="20%" class="fsBlack" align="left"> <label>
							<input type="text" id="CONTACT_P" name="CONTACT_P" size="30" maxlength="30"> </label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.CONTACT_TEL')"/>：</td>
							<td align="left"><label> <input type="text" id="CONTACT_TEL"
								name="CONTACT_TEL" maxlength="16" size="16"> </label></td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.MSG_NUM')"/>：</td>
							<td width="20%" class="fsBlack" align="left"> <label>
							<input type="text" maxlength="5" size="5" id="MSG_NUM" name="MSG_NUM"> </label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.FAX')"/>：</td>
							<td align="left"><label> <input type="text" id="FAX"
								name="FAX" maxlength="30" > </label></td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_DESC')"/>：</td>
							<td colspan="5" align="left"><label> <textarea
								id="ENTERPRISE_DESC" name="ENTERPRISE_DESC"  cols="60" rows="6" ></textarea>
								<div id="ENTERPRISE_DESC_show" class="error"></div>
							</label></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class="btnBar">
					<a href="#" class="buttontwo" onclick="history.go(-1);"><s:property value="getText('btn.back')"/></a>
					<a href="#" class="buttontwo" onclick="submitPostFrom();"><s:property value="getText('btn.sure')"/></a></td>
				</tr>
			</table>
		</s:form></td>
	</tr>
</table>

<%@include file="../common/copyright.jsp"%>

</body>
</html>