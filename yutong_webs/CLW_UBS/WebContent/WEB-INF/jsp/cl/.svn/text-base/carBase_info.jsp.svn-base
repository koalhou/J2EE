<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<title><s:property value="getText('menu.cl')"/>&nbsp;|&nbsp;<s:property value="getText('menu.cl.vehicle')"/></title>
</head>
<body onload="show_vehicle_type_info('<s:property value='carBaseInfo.vehicle_type_id'/>');show_engine_type_info('<s:property value='carBaseInfo.engine_type'/>');">
<%@include file="../common/menu.jsp"%>
<%@include file="carBase_info_validate.jsp"%>

<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<s:form id="clwForm" name="clwForm"
			action="carbase_do_edit" method="post">
			<s:hidden id="vehicle_id" name="carBaseInfo.vehicle_id" ></s:hidden>
			<s:hidden id="page" name="page" />
			<s:hidden id="pageSize" name="pageSize" />
			<s:hidden id="oldVehicleVin" name="oldVehicleVin" />
			<input type="hidden" id="temp_vehicle_id" name="vehicle_id" value="<s:property value='carBaseInfo.vehicle_id'/>"/>
			<input type="hidden" id="vehicle_vin_old" value="<s:property value='carBaseInfo.vehicle_vin'/>"/>
			<input type="hidden" id="vehicle_ln_old" value="<s:property value='carBaseInfo.vehicle_ln'/>"/>
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
					    &nbsp;&nbsp;<s:property value="getText('carbase.info')"/>
					    (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)  
					  </span>
					</td>
				</tr>
				<tr>
					<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.vehicle_number')"/>：</td>
			              <td width="25%" class="fsBlack" align="left">
			                <s:textfield id="vehicle_number" name="carBaseInfo.vehicle_number" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			              <td align="right" width="20%"><s:property value="getText('carbase.info.vehicle_vin')"/>：</td>
			              <td width="25%" align="left">
			                 <s:textfield id="vehicle_vin" name="carBaseInfo.vehicle_vin" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			            </tr>
			            <tr>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.car_type_brand')"/>：</td>
			              <td width="25%" class="fsBlack" align="left">
							<s:select cssStyle="width:130px;" id="car_type_brand" name="carBaseInfo.car_type_brand" list="carTypeBrandMap" headerKey="" headerValue="%{getText('please.select')}" onchange="clearInfo()">
							</s:select>
			              </td>
			              <td>&nbsp;</td>
			              <td width="20%" align="right"><s:property value="getText('carbase.info.vehicle_color')"/>：</td>
			              <td width="25%" align="left">
			                <s:textfield id="vehicle_color" name="carBaseInfo.vehicle_color" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			            </tr>
			            <tr>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.vehicle_type_id')"/>：</td>
			              <td width="25%" class="fsBlack" align="left">
							<s:textfield id="vehicle_type_id_info" maxlength="30" onclick="show_vehicle_type_list()" readonly="true"/>
							<s:hidden id="vehicle_type_id" name="carBaseInfo.vehicle_type_id"></s:hidden>
			              </td>
			              <td>&nbsp;</td>
			              <td width="20%" align="right"><s:property value="getText('carbase.info.vehicle_type_info')"/>：</td>
			              <td width="25%" align="left">
			                <input type="text" disabled="disabled" id="vehicle_type_info">
			              </td>
			              <td>&nbsp;</td>
			            </tr>
			            <tr>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.business_type')"/>：</td>
			              <td width="25%" class="fsBlack" align="left">
			                <s:select cssStyle="width:130px;" id="business_type" name="carBaseInfo.business_type" list="businessTypeMap" headerKey="" headerValue="%{getText('please.select')}" >
							</s:select>
			              </td>
			              <td>&nbsp;</td>
			              <td width="20%" align="right"><s:property value="getText('carbase.info.limite_number')"/>：</td>
			              <td width="25%" align="left">
			                <s:textfield id="limite_number" name="carBaseInfo.limite_number" maxlength="10"/>
			              </td>
			              <td>&nbsp;</td>
			            </tr>
			            <tr>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.dead_load')"/>：</td>
			              <td width="25%" class="fsBlack" align="left">
			                <s:textfield id="dead_load" name="carBaseInfo.dead_load" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			              <td width="20%" align="right"><s:property value="getText('carbase.info.tyre_r')"/>：</td>
			              <td width="25%" align="left">
			                <s:textfield id="tyre_r" name="carBaseInfo.tyre_r" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			            </tr>
			            <tr>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.rear_axle_rate')"/>：</td>
			              <td width="25%" class="fsBlack" align="left">
			                <s:textfield id="rear_axle_rate" name="carBaseInfo.rear_axle_rate" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			              <td width="20%" align="right"><s:property value="getText('carbase.info.cr_config_id')"/>：</td>
			              <td width="25%" align="left">
			                <s:select cssStyle="width:130px;" id="cr_config_id" name="carBaseInfo.cr_config_id" list="illDriveMap" headerKey="" headerValue="%{getText('please.select')}" >
							</s:select>
			              </td>
			              <td>&nbsp;</td>
			            </tr>
			            <tr>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.vehicle_ln')"/>：</td>
			              <td width="25%" align="left">
			                  <s:textfield id="vehicle_ln" name="carBaseInfo.vehicle_ln" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			              <td width="20%" align="right"><s:property value="getText('carbase.info.car_number')"/>：</td>
			              <td width="25%" align="left">
			                <s:textfield id="car_number" name="carBaseInfo.car_number" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			            </tr>
			            <tr>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.oilconfig')"/>：</td>
			              <td width="25%" align="left">
			                  <select name="carBaseInfo.oil_config" id="oil_config">
			                    <option value="0" <s:if test="0==carBaseInfo.oil_config">
										selected="selected"
									</s:if>>是
								</option>
			                    <option value="1" <s:if test="1==carBaseInfo.oil_config">
										selected="selected"
									</s:if>>否</option>
			                </select>
			              </td>
			              <td>&nbsp;</td>
			              <td>&nbsp;</td>
			              <td>&nbsp;</td>
			              <td>&nbsp;</td>
			            </tr>
			            <tr>
							<td height="32" class="msgtr" colspan="6" align="left"><span
								class="msgTitle">&nbsp;&nbsp;<s:property value="getText('carbase.info.engine')"/>
								(<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)
								</span></td>
						</tr>
						<tr>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.engine_number')"/>：</td>
			              <td width="25%" class="fsBlack" align="left">
			                <s:textfield id="engine_number" name="carBaseInfo.engine_number" maxlength="60"/>
			              </td>
			              <td>&nbsp;</td>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.standard_rotate')"/>：</td>
			              <td width="25%" align="left">
			                  <s:textfield id="standard_rotate" name="carBaseInfo.standard_rotate" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			            </tr>
						<tr>
			              <td width="20%" height="28" align="right" ><s:property value="getText('carbase.info.brand')"/>：</td>
			              <td width="25%" align="left">
			                 <s:select cssStyle="width:130px;" id="brand" name="carBaseInfo.brand" list="carEngineBrandMap" headerKey="" headerValue="%{getText('please.select')}" onchange="clearInfo2()">
							</s:select>
			              </td>
			              <td>&nbsp;</td>
			              <td width="20%" align="right"><s:property value="getText('carbase.info.engine_type')"/>：</td>
			              <td width="25%" align="left">
			                <s:textfield id="engine_type_info" maxlength="30" onclick="show_engine_type_list()" readonly="true"/>
							<s:hidden id="engine_type" name="carBaseInfo.engine_type"></s:hidden>
			              </td>
			              <td>&nbsp;</td>
			            </tr>
			            <tr>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.standard_oil')"/>：</td>
			              <td width="25%" align="left">
			                  <s:textfield id="standard_oil" name="carBaseInfo.standard_oil" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			              <td width="20%"  align="right"><s:property value="getText('carbase.info.out_number')"/>：</td>
			              <td width="25%" align="left">
			                  <s:textfield id="out_number" name="carBaseInfo.out_number" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
						</tr>
			            <tr>
							<td height="32" class="msgtr" colspan="6" align="left">
							  <span class="msgTitle">&nbsp;&nbsp;<s:property value="getText('carbase.info.other')"/>
							  (<span class="noticeMsg">*</span><s:property value="getText('input.required')"/>)  
							  </span></td>
						</tr>
						<tr>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.sell_order_num')"/>：</td>
			              <td width="25%" class="fsBlack" align="left">
			                <s:textfield id="sell_order_num" name="carBaseInfo.sell_order_num" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			              <td width="20%" align="right"><s:property value="getText('carbase.info.made_order_num')"/>：</td>
			              <td width="25%" align="left">
							<s:textfield id="made_order_num" name="carBaseInfo.made_order_num" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			            </tr>
			            <tr>
			              <td width="20%" height="28" align="right"><s:property value="getText('carbase.info.chassis_order_num')"/>：</td>
			              <td width="25%" class="fsBlack" align="left">
			                <s:textfield id="chassis_order_num" name="carBaseInfo.chassis_order_num" maxlength="30"/>
			              </td>
			              <td>&nbsp;</td>
			              <td width="20%" align="right"><s:property value="getText('carbase.info.sell_date')"/>：</td>
			              <td width="25%" align="left">
							 <s:textfield id="sell_date" name="carBaseInfo.sell_date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true})" cssClass="Wdate"/>
			              </td>
			              <td>&nbsp;</td>
			            </tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td class="btnBar">
					<a href="<s:url value='/cl/carbase.shtml' />" class="buttontwo"><s:property value="getText('btn.back')"/></a> 
					<s:if test="#session.perUrlList.contains('111_0_2_2_4')">
					<a href="#"  onclick="submitPostDelFrom();" class="buttontwo" ><s:property value="getText('btn.delete')"/></a>
					</s:if>
					<s:if test="#session.perUrlList.contains('111_0_2_2_3')">
					<a href="#" class="buttontwo" onclick="submitPostFrom();"><s:property value="getText('btn.update')"/></a>
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