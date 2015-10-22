<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<title><s:property value="getText('qx.menu')"/>&nbsp;|&nbsp;<s:property value="getText('enterprise.menu')"/></title>
</head>
<body>
<%@include file="../common/menu.jsp"%>
<%@include file="entiManage_edit_validate.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
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
				<div class="contentTil"><s:property value="getText('enterprise.info')"/></div>
				</td>
			</tr>
		</table>
		<s:form id="clwForm" name="clwForm"
			action="entimanage_goto_edit" method="post">
			<input type="hidden" id="ChooseEnterID_tree" name="ChooseEnterID_tree" value="<%=session.getValue("ChooseEnterID_tree") %>">
			<input type="hidden" id="PARENT_ID" name ="PARENT_ID" value="<s:property value="enterpriseDataInfo.parent_id" />">
			<input type="hidden" id="ENTERPRISE_ID" name ="ENTERPRISE_ID" value="<s:property value="enterpriseDataInfo.enterprise_id" />">
			<s:hidden id="enterprise_code_serch" name="enterprise_code_serch"></s:hidden>
			<s:hidden id="full_name_serch" name="full_name_serch"></s:hidden>
			<s:hidden id="enterprise_country_serch" name="enterprise_country_serch"></s:hidden>
			<s:hidden id="enterprise_province_serch" name="enterprise_province_serch"></s:hidden>
			<s:hidden id="enterprise_city_serch" name="enterprise_city_serch"></s:hidden>
			<input type="hidden" id="short_name_delshow"  value="<s:property value="enterpriseDataInfo.short_name" />">
			
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
				cellpadding="0" cellspacing="0" id="edit_info">
				<tr>
					<td height="32" background="../images/msg_title_bg.gif"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('enterprise.info')"/>(<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)</span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" >
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_CODE')"/>：</td>
							<td width="20%" class="fsBlack" align="left"> <label>
							<s:property value="enterpriseDataInfo.enterprise_code" />
							</label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.SHORT_NAME')"/>：</td>
							<td align="left"><label> <input type="text" id="SHORT_NAME"
								name="SHORT_NAME"  maxlength="10" value="<s:property value="enterpriseDataInfo.short_name" />">
								</label></td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.FULL_NAME')"/>：</td>
							<td width="20%" align="left"><label> <input type="text"
								id="FULL_NAME" name="FULL_NAME"  maxlength="60" value="<s:property value="enterpriseDataInfo.full_name" />"> </label></td>
							<td>&nbsp;</td>
							<td align="right">
								<s:property value="getText('enterprise.info.NETADDRESS')"/>：
							</td>
							<td align="left"><label>
							<input type="text" id="NETADDRESS"
								name="NETADDRESS"  maxlength="512" value="<s:property value="enterpriseDataInfo.netaddress" />">
							</label></td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_COUNTRY')"/>：</td>
							<td align="left"><label>
							<s:select id="ENTERPRISE_COUNTRY" name="enterpriseDataInfo.enterprise_country" list="countryInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_province()">
							</s:select>
							</label></td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.ENTERPRISE_PROVINCE')"/>：</td>
							<td align="left"><label> 
							<s:select id="ENTERPRISE_PROVINCE" name="enterpriseDataInfo.enterprise_province" list="provinceInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_city()">
							</s:select>
							</label></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_CITY')"/>：</td>
							<td align="left"><label> 
							<s:select id="ENTERPRISE_CITY" name="enterpriseDataInfo.enterprise_city" list="cityInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
							</s:select>
							</label></td>
							<td>&nbsp;</td>
							<td></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('enterprise.info.MODEL')"/>：</td>
							<td align="left">
							<label> 
								<s:select id="ENTERPRISE_MODEL" name="enterpriseDataInfo.enterprise_model" list="enterprise_model_map" headerKey="" headerValue="%{getText('please.select')}"  onchange="">
								</s:select>
							</label>
							</td>
							<td>&nbsp;</td>
							<td align="right">
								<s:property value="getText('enterprise.info.getway')"/>：
							</td>
							<td align="left">
								<label> 
									<s:select id="ENTERPRISE_GETWAY" name="enterpriseDataInfo.enterprise_getway" list="enterprise_getway_map" headerKey="" headerValue="%{getText('please.select')}"  onchange="">
									</s:select>
								</label>
							</td>							
							<td>&nbsp;</td>
						</tr>						
						<tr>
							<td height="28" align="right"><s:property value="getText('enterprise.info.ADDRESS')"/>：</td>
							<td colspan="4" align="left"><label> <input type="text"
								id="ADDRESS" name="ADDRESS" size="60" maxlength="60" value="<s:property value="enterpriseDataInfo.address" />"> </label></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.EMAIL')"/>：</td>
							<td width="20%" class="fsBlack" align="left"> <label>
							<input type="text" id="EMAIL"  maxlength="30" size="30" name="EMAIL" value="<s:property value="enterpriseDataInfo.email" />"> </label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.POSTCODE')"/>：</td>
							<td align="left"><label> <input type="text" id="POSTCODE"
								name="POSTCODE"  maxlength="10" value="<s:property value="enterpriseDataInfo.postcode" />"> </label></td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_TYPE_CFG')"/>：</td>
							<td width="20%" class="fsBlack" align="left"> <label>
							<s:select id="ENTERPRISE_TYPE_CFG" name="enterpriseDataInfo.enterprise_type_cfg" list="enterprise_type_cfgMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
								</s:select>
							</label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.ENTERPRISE_LEVE_CFG')"/>：</td>
							<td align="left"><label>
							<s:select id="ENTERPRISE_LEVE_CFG" name="enterpriseDataInfo.enterprise_leve_cfg" list="enterprise_leve_cfgMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
								</s:select>
							</label></td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_KIND_CFG')"/>：</td>
							<td width="20%" class="fsBlack" align="left"> <label>
							<s:select id="ENTERPRISE_KIND_CFG" name="enterpriseDataInfo.enterprise_kind_cfg" list="enterprise_kind_cfgMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
								</s:select>
							</label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.MONEY_KIND_CFG')"/>：</td>
							<td align="left"><label>
							<s:select id="MONEY_KIND_CFG" name="enterpriseDataInfo.money_kind_cfg" list="money_kind_cfgMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
								</s:select>
							</label></td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.LANGUAGE_CFG')"/>：</td>
							<td width="20%" class="fsBlack" align="left"> <label>
							<s:select id="LANGUAGE_CFG" name="enterpriseDataInfo.language_cfg" list="language_cfgMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
								</s:select>
							</label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.ISUSED')"/>：</td>
							<td align="left"><label>
							<select name="ISUSED" id="ISUSED">
                              	<option value="0" <s:if test="0==enterpriseDataInfo.isused">
										selected="selected"
								</s:if>
								><s:property value="getText('enterprise.info.ISUSED_0')"/></option>
                              	<option value="1" <s:if test="1==enterpriseDataInfo.isused">
										selected="selected"
								</s:if>
								><s:property value="getText('enterprise.info.ISUSED_1')"/></option>
		                    </select>
							</label></td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.CONTACT_P')"/>：</td>
							<td width="20%" class="fsBlack" align="left"> <label>
							<input type="text" id="CONTACT_P" size="30" maxlength="30" name="CONTACT_P" value="<s:property value="enterpriseDataInfo.contact_p" />"> </label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.CONTACT_TEL')"/>：</td>
							<td align="left"><label> <input type="text" id="CONTACT_TEL"
								name="CONTACT_TEL" size="16"  maxlength="16" value="<s:property value="enterpriseDataInfo.contact_tel" />"> </label></td>
						</tr>
						<tr>
							<td width="17%" height="28" align="right"><s:property value="getText('enterprise.info.MSG_NUM')"/>：</td>
							<td width="20%" class="fsBlack" align="left"> <label>
							<input type="text" maxlength="5" size="5" id="MSG_NUM" name="MSG_NUM" value="<s:property value="enterpriseDataInfo.msg_num" />"> </label> </td>
							<td>&nbsp;</td>
							<td align="right"><s:property value="getText('enterprise.info.FAX')"/>：</td>
							<td align="left"><label> <input type="text" id="FAX"
								name="FAX" maxlength="30" value="<s:property value="enterpriseDataInfo.fax" />"></label></td>
						</tr>
						<tr>
							<td height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_DESC')"/>：</td>
							<td colspan="5" align="left"><label> <textarea
								id="ENTERPRISE_DESC" name="ENTERPRISE_DESC" cols="60" rows="6"><s:property value="enterpriseDataInfo.enterprise_desc" /></textarea>
								<div id="ENTERPRISE_DESC_show" class="error"></div>
							</label></td>
						</tr>
					</table>
					
		
					
					</td>
				</tr>
				<tr>
					<td class="btnBar">
					<a href="#" class="buttontwo" onclick="history.go(-1);"><s:property value="getText('btn.back')"/></a>
					<s:if test="#session.perUrlList.contains('111_0_1_1_4')">
						<a class="buttontwo" href="#" onClick="do_del()" id="current"><s:property
							value="getText('btn.delete')" /></a>
					</s:if>
					<s:if test="#session.perUrlList.contains('111_0_1_1_3')">
						<a class="buttontwo" href="#" onClick="submitPostFrom()" id="current"><s:property
							value="getText('btn.update')" /></a>
					</s:if>
					</td>
				</tr>
			</table>
			<table class="msgBox2" width="650" border="0" align="center"
			cellpadding="0" cellspacing="0" id="show_info">
			<tr>
				<td height="32" background="../images/msg_title_bg.gif"><span
					class="msgTitle">&nbsp;&nbsp;<s:property
					value="getText('enterprise.info')" /></span></td>
			</tr>
			<tr>
				<td align="center">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_CODE')" />：</td>
						<td width="25%" align="left"><label id="show_info_ENTERPRISE_CODE"> </label></td>
						<td width="3%">&nbsp;</td>
						<td width="15%" align="right"><s:property
							value="getText('enterprise.info.SHORT_NAME')" />：</td>
				      	<td width="37%" align="left"><label id="show_info_SHORT_NAME"> </label></td>
					</tr>
					<tr>
					  <td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.FULL_NAME')" />：</td>
						<td width="25%" align="left"><label id="show_info_FULL_NAME"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.NETADDRESS')" />：</td>
					  	<td align="left"><label id="show_info_NETADDRESS"> </label></td>
					</tr>
					<tr>
						<td height="28" align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_COUNTRY')" />：</td>
					  	<td align="left"><label id="show_info_ENTERPRISE_COUNTRY"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_PROVINCE')" />：</td>
					  	<td align="left"><label id="show_info_ENTERPRISE_PROVINCE"> </label></td>
						
					</tr>
					<tr>
						<td height="28" align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_CITY')" />：</td>
					  	<td align="left"><label id="show_info_ENTERPRISE_CITY"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_TYPE')" />：</td>
					  	<td align="left"><label id="show_info_ENTERPRISE_TYPE"> </label></td>
						
					</tr>
					<tr>
						<td height="28" align="right"><s:property
							value="getText('enterprise.info.MODEL')" />：</td>
					  	<td align="left"><label id="show_info_MODEL"> </label></td>
						<td>&nbsp;</td>
						<td align="right">
							<s:property value="getText('enterprise.info.getway')"/>：
						</td>
						<td align="left"><label id="show_info_GETWAY"> </label></td>						
					</tr>				
					<tr>
						<td height="28" align="right"><s:property
							value="getText('enterprise.info.ADDRESS')" />：</td>
					  	<td colspan="4" align="left"><label id="show_info_ADDRESS"> </label></td>
						
					</tr>
					<tr>
					  	<td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.EMAIL')" />：</td>
					  	<td width="25%" align="left"><label id="show_info_EMAIL"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.POSTCODE')" />：</td>
					  	<td align="left"><label id="show_info_POSTCODE"> </label></td>
					</tr>
					<tr>
					  	<td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_TYPE_CFG')" />：</td>
					  	<td width="25%" align="left"><label id="show_info_ENTERPRISE_TYPE_CFG"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_LEVE_CFG')" />：</td>
					  <td align="left"><label id="show_info_ENTERPRISE_LEVE_CFG"> </label></td>
					</tr>
					<tr>
					  	<td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_KIND_CFG')" />：</td>
					  	<td width="25%" align="left"><label id="show_info_ENTERPRISE_KIND_CFG"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.MONEY_KIND_CFG')" />：</td>
					  	<td align="left"><label id="show_info_MONEY_KIND_CFG"> </label></td>
					</tr>
					<tr>
					  	<td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.LANGUAGE_CFG')" />：</td>
					  	<td width="25%" align="left"><label id="show_info_LANGUAGE_CFG"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.ISUSED')" />：</td>
					  	<td align="left"><label id="show_info_ISUSED"> </label></td>
					</tr>
					<tr>
					  	<td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.CONTACT_P')" />：</td>
					  	<td width="25%" align="left"><label id="show_info_CONTACT_P"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.CONTACT_TEL')" />：</td>
					  	<td align="left"><label id="show_info_CONTACT_TEL"> </label></td>
					</tr>
					<tr>
					  	<td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.MSG_NUM')" />：</td>
					  	<td width="25%" align="left"><label id="show_info_MSG_NUM"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.FAX')" />：</td>
					  <td align="left"><label id="show_info_FAX"> </label></td>
					</tr>
					<tr>
					  	<td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.CARSNUMS')" />：</td>
					  	<td width="25%" align="left"><label id="show_info_CARSNUMS"></label></td>
						<td>&nbsp;</td>
						<td align="right"></td>
						<td align="left"></td>
					</tr>
					<tr>
						<td height="28" align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_DESC')" />：</td>
					  	<td colspan="5" align="left">
							<label style="width:500px" id="show_info_ENTERPRISE_DESC"> </label>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="btnBar"><a href="#" class="buttontwo" onclick="history.go(-1);"><s:property value="getText('btn.back')"/></a></td>
					
			</tr>
		</table>
		</s:form></td>
	</tr>
</table>

<%@include file="../common/copyright.jsp"%>

</body>
</html>