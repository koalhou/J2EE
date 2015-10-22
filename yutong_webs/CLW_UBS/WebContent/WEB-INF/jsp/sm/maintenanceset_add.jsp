<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<title><s:property value="getText('service.management.title')"/>&nbsp;|&nbsp;<s:property value="getText('service.management.maintenance.set')"/></title>
</head>
<body>
<%@include file="../common/menu.jsp"%>
<%@include file="maintenanceset_add_validate.jsp"%>

<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<s:form id="maintenanceForm" name="maintenanceForm"
			action="maintenanceDoAdd" method="post">
			<s:hidden id="page" name="page" />
			<s:hidden id="pageSize" name="pageSize" />
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
					<td height="32" class="msgtr" align="left">
					  <span	class="msgTitle">
					    &nbsp;&nbsp;<s:property value="getText('service.management.maintenance.item')"/>
					  </span>
					</td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="20%" height="28" align="left">&nbsp;&nbsp;车辆VIN号</td>
			              	<td align="left" width="20%">
			              		<s:hidden id="vehicleId"/>
                				<s:textfield id="vehicleVin" 
                             		name="maintenance.vehicle_vin" 
                             		onclick="openVehicleBrowse()" 
                             		title="选择输入框"
                             		readonly="true"/>			              	
							</td>
			              	<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="msgtr" width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.service.item')"/></td>
			              	<td class="msgtr" align="left" width="20%"><span class="msgTitle"><s:property value="getText('service.management.maintenance.newcar')"/></span></td>
			              	<td class="msgtr">&nbsp;</td>
			            </tr>
						<tr>
							<td width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.condition')"/>：</td>
			              	<td width="30%" class="fsBlack" align="left">
			                	<s:textfield id="conditionNewCar" name="maintenance.conditionNewCar" maxlength="10"/><s:property value="getText('service.management.maintenance.day')"/>
			              	</td>
			              	<td align="left">
								购车日--今天&lt;=10天
							</td>
			            </tr>
						<tr>
							<td width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.remind.condition')"/>：</td>
			              	<td width="30%" class="fsBlack" align="left">
			                	<s:textfield id="conditionRemindNewCar" name="maintenance.conditionRemindNewCar" maxlength="10"/><s:property value="getText('service.management.maintenance.day')"/>
			              	</td>
			              	<td align="left">
								购车日开始提醒10天
							</td>
			            </tr>
			            <tr height="5">
			            	<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
			            </tr>
						<tr>
							<td class="msgtr" width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.service.item')"/></td>
			              	<td class="msgtr" align="left" width="20%"><span class="msgTitle"><s:property value="getText('service.management.maintenance.go')"/></span></td>
			              	<td class="msgtr">&nbsp;</td>
			            </tr>
						<tr>
							<td width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.condition')"/>：</td>
			              	<td width="30%" class="fsBlack" align="left">
			                	<s:textfield id="goCondition" name="maintenance.conditionGo" maxlength="10"/><s:property value="getText('service.management.maintenance.km')"/>
			              	</td>
			              	<td align="left">
								&lt;=2500公里
							</td>
			            </tr>
						<tr>
							<td width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.remind.condition')"/>：</td>
			              	<td width="30%" class="fsBlack" align="left">
			                	<s:textfield id="goRemind" name="maintenance.conditionRemindGo" maxlength="10"/><s:property value="getText('service.management.maintenance.km')"/>
			              	</td>
			              	<td align="left">
								&gt;1000公里开始提醒
							</td>
			            </tr>
			            <tr height="5">
			            	<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
			            </tr>
						<tr>
							<td class="msgtr" width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.service.item')"/></td>
			              	<td class="msgtr" align="left" width="20%"><span class="msgTitle"><s:property value="getText('service.management.maintenance.compulsory')"/></span></td>
			              	<td class="msgtr">&nbsp;</td>
			            </tr>
						<tr>
							<td width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.condition')"/>：</td>
			              	<td width="30%" class="fsBlack" align="left">
			                	<s:textfield id="conditionCompulsory" name="maintenance.conditionCompulsory" maxlength="10"/><s:property value="getText('service.management.maintenance.km')"/>
			              	</td>
			              	<td align="left">
								2500公里后每5000公里提醒一次(全生命周期提醒)
							</td>
			            </tr>
						<tr>
							<td width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.remind.condition')"/>：</td>
			              	<td width="30%" class="fsBlack" align="left">
			                	<s:textfield id="conditionRemindCompulsory" name="maintenance.conditionRemindCompulsory" maxlength="10"/><s:property value="getText('service.management.maintenance.km')"/>
			              	</td>
			              	<td align="left">
								2500公里后每4500公里提醒一次
							</td>
			            </tr>
			            <tr height="5">
			            	<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
			            </tr>
						<tr>
							<td class="msgtr" width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.service.item')"/></td>
			              	<td class="msgtr" align="left" width="20%"><span class="msgTitle"><s:property value="getText('service.management.maintenance.luxurycar')"/></span></td>
			              	<td class="msgtr">&nbsp;</td>
			            </tr>
						<tr>
							<td width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.condition')"/>：</td>
			              	<td width="30%" class="fsBlack" align="left">
			                	<s:textfield id="conditionCompulsoryLuxury" name="maintenance.conditionCompulsoryLuxury" maxlength="10"/><s:property value="getText('service.management.maintenance.km')"/>
			              	</td>
			              	<td align="left">
								每15000公里强保1次*4(含走和保养)
							</td>
			            </tr>
						<tr>
							<td width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.remind.condition')"/>：</td>
			              	<td width="30%" class="fsBlack" align="left">
			                	<s:textfield id="conditionRemindCompulsoryLuxury" name="maintenance.conditionRemindCompulsoryLuxury" maxlength="10"/><s:property value="getText('service.management.maintenance.km')"/>
			              	</td>
			              	<td align="left">
								&gt;13000公里开始提醒
							</td>
			            </tr>
						<tr>
							<td width="20%" height="28" align="left">&nbsp;&nbsp;<s:property value="getText('service.management.maintenance.luxury.condition')"/>：</td>
			              	<td width="30%" class="fsBlack" align="left">
			                	<s:textfield id="condition_luxury" name="maintenance.condition_luxury" maxlength="10"/><s:property value="getText('service.management.maintenance.times')"/>
			              	</td>
			              	<td align="left">
								高档车按照销售合同要求
							</td>
			            </tr>			            
					</table>
					</td>
				</tr>
				
				<tr>
					<td class="btnBar"><a href="<s:url value='/sm/maintenance.shtml' />" class="buttontwo"><s:property value="getText('btn.back')"/></a> 
					<a href="#" class="buttontwo" onclick="submitPostFrom();"><s:property value="getText('btn.sure')"/></a></td>
				</tr>
			</table>
		</s:form></td>
	</tr>
</table>

<%@include file="../common/copyright.jsp"%>

</body>
</html>