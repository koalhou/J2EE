<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>

<title><s:property value="getText('menu.xs')"/>&nbsp;|&nbsp;<s:property value="getText('menu.xs.vehiclebaseinfo')"/>&nbsp;|&nbsp;<s:property value="getText('illdrive.menu')"/></title>
</head>
<body>
<%@include file="../common/menu.jsp"%>
<%@include file="illDrive_info_validate.jsp"%>

<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<s:form id="clwForm" name="clwForm"
			action="illdrive_do_edit" method="post">
			<s:hidden id="page" name="page" />
			<s:hidden id="pageSize" name="pageSize" />
			<s:hidden id="def_id" name="harmdefDataInfo.def_id" ></s:hidden>
			<input type="hidden" id="temp_def_id" name="def_id" value="<s:property value='harmdefDataInfo.def_id'/>"/>
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
					<td height="32"  class="msgtr"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('illdrive.info2')"/>(<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)</span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.def_name')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="def_name" name="harmdefDataInfo.def_name" maxlength="20"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.def_name" />
							</s:else>
							</label> </td>
							<td></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="32"  class="msgtr"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('illdrive.info.overspeed')"/></span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.overspeed_val')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="overspd_val" name="harmdefDataInfo.overspd_val" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.overspd_val" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.overspeed_time')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="overspd_time" name="harmdefDataInfo.overspd_time" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.overspd_time" />
							</s:else>
							</label> </td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="32" class="msgtr"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('illdrive.info.tire')"/></span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.overspeed_time_h')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="tired_time" name="harmdefDataInfo.tired_time" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.tired_time" />
							</s:else>
							</label> </td>
							<td></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="32" class="msgtr"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('illdrive.info.gear2')"/></span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.overspeed_val')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear2_spd" name="harmdefDataInfo.gear2_spd" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear2_spd" />
							</s:else>
							</label> </td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="32" class="msgtr"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('illdrive.info.egear')"/></span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.overspeed_val')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="egear_spd" name="harmdefDataInfo.egear_spd" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.egear_spd" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.egear_ratio')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="egear_ratio" name="harmdefDataInfo.egear_ratio" maxlength="25"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.egear_ratio" />
							</s:else>
							</label> </td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="32" class="msgtr"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('illdrive.info.eoil')"/></span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.overspeed_val')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="eoil_spd" name="harmdefDataInfo.eoil_spd" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.eoil_spd" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.rpm')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="eoil_rpm" name="harmdefDataInfo.eoil_rpm" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.eoil_rpm" />
							</s:else>
							</label> </td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="32" class="msgtr"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('illdrive.info.lidl_speed')"/></span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.overspeed_val')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="lidl_spd" name="harmdefDataInfo.lidl_spd" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.lidl_spd" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.rpm')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="lidl_rpm" name="harmdefDataInfo.lidl_rpm" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.lidl_rpm" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.overspeed_time')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="lidl_time" name="harmdefDataInfo.lidl_time" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.lidl_time" />
							</s:else>
							</label> </td>
							<td></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="32" class="msgtr"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('illdrive.info.idlair_speed')"/></span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.overspeed_val')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="idlair_spd" name="harmdefDataInfo.idlair_spd" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.idlair_spd" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.rpm')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="idlair_rpm" name="harmdefDataInfo.idlair_rpm" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.idlair_rpm" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.overspeed_time')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="idlair_time" name="harmdefDataInfo.idlair_time" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.idlair_time" />
							</s:else>
							</label> </td>
							<td></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="32" class="msgtr"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('illdrive.info.qgear')"/></span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.rpm')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="qgear_rpm" name="harmdefDataInfo.qgear_rpm" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.qgear_rpm" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="30%" height="28" align="right"><s:property value="getText('illdrive.info.egear_ratio')"/>：</td>
							<td width="60%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="qgear_ratio" name="harmdefDataInfo.qgear_ratio" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.qgear_ratio" />
							</s:else>
							</label> </td>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td height="32" class="msgtr"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('illdrive.info.greenarea')"/></span></td>
				</tr>
				
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
						    <td width="10%" height="28" align="center"></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.rpm')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> 
							  <label>
							    <s:textfield id="greenarea_rpm_l" name="harmdefDataInfo.greenarea_rpm_l" maxlength="50"/>
							  </label> 
							</td>
							<td align="left">—</td>
							<td width="30%" class="fsBlack" align="left"> 
							  <label>
							    <s:textfield id="greenarea_rpm_u" name="harmdefDataInfo.greenarea_rpm_u" maxlength="50"/>
							  </label>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td height="32" class="msgtr"><span
						class="msgTitle">&nbsp;&nbsp;<s:property value="getText('illdrive.info.errorgear')"/></span></td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="10%" height="28" align="center" class="dangStyle">0<s:property value="getText('illdrive.info.errorgear.gear')"/></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.speed')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear0_spd_l" name="harmdefDataInfo.gear0_spd_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear0_spd_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear0_spd_u" name="harmdefDataInfo.gear0_spd_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear0_spd_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center"></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.rpm')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear0_rpm_l" name="harmdefDataInfo.gear0_rpm_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear0_rpm_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear0_rpm_u" name="harmdefDataInfo.gear0_rpm_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear0_rpm_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center" class="dangStyle">1<s:property value="getText('illdrive.info.errorgear.gear')"/></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.speed')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear1_spd_l" name="harmdefDataInfo.gear1_spd_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear1_spd_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear1_spd_u" name="harmdefDataInfo.gear1_spd_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear1_spd_u" />
							</s:else>
							</label> </td>
						</tr>
						
						
						<tr>
							<td width="10%" height="28" align="center"></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.rpm')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear1_rpm_l" name="harmdefDataInfo.gear1_rpm_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear1_rpm_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear1_rpm_u" name="harmdefDataInfo.gear1_rpm_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear1_rpm_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center" class="dangStyle">2<s:property value="getText('illdrive.info.errorgear.gear')"/></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.speed')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear2_spd_l" name="harmdefDataInfo.gear2_spd_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear2_spd_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear2_spd_u" name="harmdefDataInfo.gear2_spd_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear2_spd_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center"></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.rpm')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear2_rpm_l" name="harmdefDataInfo.gear2_rpm_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear2_rpm_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear2_rpm_u" name="harmdefDataInfo.gear2_rpm_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear2_rpm_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center" class="dangStyle">3<s:property value="getText('illdrive.info.errorgear.gear')"/></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.speed')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear3_spd_l" name="harmdefDataInfo.gear3_spd_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear3_spd_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear3_spd_u" name="harmdefDataInfo.gear3_spd_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear3_spd_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center"></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.rpm')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear3_rpm_l" name="harmdefDataInfo.gear3_rpm_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear3_rpm_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear3_rpm_u" name="harmdefDataInfo.gear3_rpm_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear3_rpm_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center" class="dangStyle">4<s:property value="getText('illdrive.info.errorgear.gear')"/></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.speed')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear4_spd_l" name="harmdefDataInfo.gear4_spd_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear4_spd_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear4_spd_u" name="harmdefDataInfo.gear4_spd_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear4_spd_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center"></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.rpm')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear4_rpm_l" name="harmdefDataInfo.gear4_rpm_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear4_rpm_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear4_rpm_u" name="harmdefDataInfo.gear4_rpm_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear4_rpm_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center" class="dangStyle">5<s:property value="getText('illdrive.info.errorgear.gear')"/></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.speed')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear5_spd_l" name="harmdefDataInfo.gear5_spd_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear5_spd_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear5_spd_u" name="harmdefDataInfo.gear5_spd_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear5_spd_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center"></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.rpm')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear5_rpm_l" name="harmdefDataInfo.gear5_rpm_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear5_rpm_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear5_rpm_u" name="harmdefDataInfo.gear5_rpm_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear5_rpm_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center" class="dangStyle">6<s:property value="getText('illdrive.info.errorgear.gear')"/></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.speed')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear6_spd_l" name="harmdefDataInfo.gear6_spd_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear6_spd_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear6_spd_u" name="harmdefDataInfo.gear6_spd_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear6_spd_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center"></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.rpm')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear6_rpm_l" name="harmdefDataInfo.gear6_rpm_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear6_rpm_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear6_rpm_u" name="harmdefDataInfo.gear6_rpm_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear6_rpm_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center" class="dangStyle">7<s:property value="getText('illdrive.info.errorgear.gear')"/></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.speed')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear7_spd_l" name="harmdefDataInfo.gear7_spd_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear7_spd_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear7_spd_u" name="harmdefDataInfo.gear7_spd_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear7_spd_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center"></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.rpm')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear7_rpm_l" name="harmdefDataInfo.gear7_rpm_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear7_rpm_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear7_rpm_u" name="harmdefDataInfo.gear7_rpm_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear7_rpm_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center" class="dangStyle">8<s:property value="getText('illdrive.info.errorgear.gear')"/></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.speed')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear8_spd_l" name="harmdefDataInfo.gear8_spd_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear8_spd_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear8_spd_u" name="harmdefDataInfo.gear8_spd_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear8_spd_u" />
							</s:else>
							</label> </td>
						</tr>
						<tr>
							<td width="10%" height="28" align="center"></td>
							<td width="20%" height="28" align="right"><s:property value="getText('illdrive.info.errorgear.rpm')"/>：</td>
							<td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear8_rpm_l" name="harmdefDataInfo.gear8_rpm_l" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear8_rpm_l" />
							</s:else>
							</label> </td><td align="left">—</td><td width="30%" class="fsBlack" align="left"> <label>
							<s:if test="harmdefDataInfo.apply_id==\"0\"">
							<s:textfield id="gear8_rpm_u" name="harmdefDataInfo.gear8_rpm_u" maxlength="50"/>
							</s:if>
							<s:else>
							<s:property value="harmdefDataInfo.gear8_rpm_u" />
							</s:else>
							</label> </td>
						</tr>
					</table>
					</td>
				</tr>
				
				
				
				
				
				<tr>
					<td class="btnBar"><a href="<s:url value='/cl/illdrive.shtml' />" class="buttontwo"><s:property value="getText('btn.back')"/></a>
					<s:if test="harmdefDataInfo.apply_id==\"0\"">
					<s:if test="#session.perUrlList.contains('111_0_2_3_4')">
					<a href="#" onclick="submitPostDelFrom();" class="buttontwo" ><s:property value="getText('btn.delete')"/></a>
					</s:if>
					<s:if test="#session.perUrlList.contains('111_0_2_3_3')">
					<a href="#" class="buttontwo" onclick="submitPostFrom();"><s:property value="getText('btn.update')"/></a>
					</s:if>
					</s:if>
					</td>
				</tr>
			</table>
		</s:form></td>
	</tr>
</table>

<%@include file="../common/copyright.jsp"%>

</body>
</html>