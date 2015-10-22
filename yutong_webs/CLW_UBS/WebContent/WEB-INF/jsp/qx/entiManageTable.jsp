<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<title><s:property value="getText('qx.menu')"/>&nbsp;|&nbsp;<s:property value="getText('enterprise.menu')"/></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
</head>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<body>
<script type="text/javascript">
function searchStatisticInfo(){
	//document.getElementById('clwForm').submit();
	 $('clwForm').action="<s:url value='/qx/entimanage.shtml' />";
	Wit.commitAll($('clwForm'));
}
function goto_add(){
	document.getElementById('clwForm').action='<s:url value='entimanage_goto_add.shtml' />';
	document.getElementById('clwForm').submit();
}
function goto_edit(obj){
	document.getElementById('ChooseEnterID_edit').value=obj;
	//树结构形状传递
	document.getElementById('clwForm').action='<s:url value='entimanage_goto_edit.shtml' />';
	document.getElementById('clwForm').submit();
}
//展示省/直辖市选择框
function show_enterprise_province(){
	var oemObj = $('ENTERPRISE_COUNTRY');
	if(oemObj.value != "") {
	    ZoneDwr.showZoneXsInfo(oemObj.value,callBackFun_show_enterprise_province);
    }else{
    	DWRUtil.removeAllOptions($('ENTERPRISE_PROVINCE'));  
    	DWRUtil.addOptions($('ENTERPRISE_PROVINCE'),{'':'<s:property value="getText('please.select')" />'});  
    	DWRUtil.removeAllOptions($('ENTERPRISE_CITY'));  
    	DWRUtil.addOptions($('ENTERPRISE_CITY'),{'':'<s:property value="getText('please.select')" />'});  
    }
}
function callBackFun_show_enterprise_province(data) {
	var tempObj = $('ENTERPRISE_PROVINCE');  
	DWRUtil.removeAllOptions(tempObj);  
	DWRUtil.addOptions(tempObj,{'':'<s:property value="getText('please.select')" />'});  
	DWRUtil.addOptions(tempObj,data);  

	var tempObj3 = $('ENTERPRISE_CITY');  
	DWRUtil.removeAllOptions(tempObj3);  
	DWRUtil.addOptions(tempObj3,{'':'<s:property value="getText('please.select')" />'});    
}
//展示市/县选择框
function show_enterprise_city(){
	var oemObj = $('ENTERPRISE_PROVINCE');
	if(oemObj.value != "") {
	    ZoneDwr.showZoneXsInfo(oemObj.value,callBackFun_show_enterprise_city);
    }else{
    	DWRUtil.removeAllOptions($('ENTERPRISE_CITY'));  
    	DWRUtil.addOptions($('ENTERPRISE_CITY'),{'':'<s:property value="getText('please.select')" />'});  
    }
}
function callBackFun_show_enterprise_city(data) {
	var tempObj = $('ENTERPRISE_CITY');  
	DWRUtil.removeAllOptions(tempObj);  
	DWRUtil.addOptions(tempObj,{'':'<s:property value="getText('please.select')" />'});  
	DWRUtil.addOptions(tempObj,data);  
}
</script>
<%@include file="../common/menu.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<table height="628" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
				<s:form id="clwForm" action="entimanage" method="post">
				<s:hidden id="ChooseEnterID_edit" name="ChooseEnterID_edit"></s:hidden>
				<s:if test="#session.perUrlList.contains('111_0_1_1_1')">
					<tr>
	                  	<td class="reportOnline2">
		                    <table width="100%" border="0" cellspacing="8" cellpadding="0">
		                      <tr>
		                        
								<td><table border="0" cellspacing="4" cellpadding="0" >
		                          
		                            <td><s:property value="getText('operationsInfo.info.enterprise_code')"/>：
		                            <s:textfield id="enterprise_code" name="enterprise_code_serch" cssClass="input120" />
		                           		&nbsp;&nbsp;
		                           		<s:property value="getText('operationsInfo.info.full_name')"/>：
		                            <s:textfield id="full_name" name="full_name_serch" cssClass="input120" />
		                            	&nbsp;&nbsp;
		                            </td>
		                            </table></td>
				                   </tr> 
				                   <tr>
				                   <td><table border="0" cellspacing="4" cellpadding="0" >  
				                   <tr>     
									<td height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_COUNTRY')"/>：</td>
									<td><label>
									<s:select id="ENTERPRISE_COUNTRY" name="enterprise_country_serch" list="countryInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_province()">
									</s:select>
									</label></td>
									<td>&nbsp;</td>
									<td align="right"><s:property value="getText('enterprise.info.ENTERPRISE_PROVINCE')"/>：</td>
									<td><label> 
									<s:select id="ENTERPRISE_PROVINCE" name="enterprise_province_serch" list="provinceInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_city()">
									</s:select>
									</label></td>
									<td>&nbsp;</td>
							
									<td height="28" align="right"><s:property value="getText('enterprise.info.ENTERPRISE_CITY')"/>：</td>
									<td><label> 
									<s:select id="ENTERPRISE_CITY" name="enterprise_city_serch" list="cityInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="">
									</s:select>
									</label></td>
		                            <td>&nbsp;&nbsp;</td>
		                            <td><a href="#" onclick="searchStatisticInfo()" class="sbutton"><s:property value="getText('btn.query')" /></a></td>
		                          </tr>
		                        </table></td>
		                       
		                      </tr>
		                    </table>                  
	                    </td>
                	</tr>
                	</s:if>
                	</s:form>
					<tr>
						<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="reportOnline">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class="titleStyle">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="30%" class="titleStyleZi"><s:property value="getText('enterprise.info')"/></td>
												<td width="69%" align="right">
												 <s:if test="#session.perUrlList.contains('111_0_1_1_2')">
					                                <div class="buhuanhangbut">
					                                  <a href="#" onclick="goto_add()" class="btnAdd" title="<s:property value="getText('btn.insert')" />">
					                                  </a>
					                                </div>
                                				 </s:if>
												</td>
												<td width="1%">&nbsp;</td>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td class="reportInline">
										<div id="message">
				                                    <s:actionerror theme="mat" />
				                                    <s:fielderror theme="mat"/>
				                                    <s:actionmessage theme="mat"/>
				                                  </div>
										<div id="list">
											<table cellspacing="0" width="100%" class="reportInline2">
												<thead>
													<tr>
														<th scope="col" width="5%"><s:property value="getText('list.number')"/></th>
														<th scope="col" width="10%"><s:property value="getText('enterprise.info.ENTERPRISE_CODE')"/></th>
														<th scope="col" width="15%"><s:property value="getText('enterprise.info.FULL_NAME')"/></th>
														<th scope="col" width="10%"><s:property value="getText('enterprise.info.SHORT_NAME')"/></th>
														<th scope="col" width="8%"><s:property value="getText('enterprise.info.ENTERPRISE_COUNTRY')"/></th>
														<th scope="col" width="8%"><s:property value="getText('enterprise.info.ENTERPRISE_PROVINCE')"/></th>
														<th scope="col" width="8%"><s:property value="getText('enterprise.info.ENTERPRISE_CITY')"/></th>
														<th scope="col" width="10%"><s:property value="getText('enterprise.info.PARENT')"/></th>
														<th scope="col" width="8%"><s:property value="getText('common.creater')"/></th>
														<th scope="col" width="10%"><s:property value="getText('common.createtime')"/></th>
														<th scope="col" width="10%"><s:property value="getText('enterprise.info.ISUSED')"/></th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="pageList" status="rowstatus">
														<tr>
															<td class="tdClass"><s:property value="#rowstatus.index+1" /></td>
															<td class="tdClass">
															<s:url id="showDetail" action="enterReportInfo">
													          <s:param name="enterprise_code" value="enterprise_code" />
													          <s:param name="page">${page}</s:param>
													          <s:param name="pageSize">${pageSize}</s:param>
												            </s:url>
												            <a href="#" onclick="goto_edit('<s:property value="enterprise_id" />')">
												              <s:property value="enterprise_code" />
												            </a>
															</td>
															<td class="tdClass"><s:property value="full_name" /></td>
															<td class="tdClass"><s:property value="short_name" /></td>
															<td class="tdClass"><s:property value="enterprise_country" /></td>
															<td class="tdClass"><s:property value="enterprise_province" /></td>
															<td class="tdClass"><s:property value="enterprise_city" /></td>
															<td class="tdClass"><s:property value="parent_id" /></td>
															<td class="tdClass"><s:property value="creater" /></td>
															<td class="tdClass"><s:property value="create_time" /></td>
															<td class="tdClass">
															<s:if test="isused==0">
															<s:property value="getText('enterprise.info.ISUSED_0')"/>
															</s:if>
															<s:elseif test="isused==1">
															<s:property value="getText('enterprise.info.ISUSED_1')"/>
															</s:elseif>
															</td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
											<div id="pageCtrl" align="right">
				                              <s:property value="pageBar" escape="false" />
				                            </div>
										</div>

										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>

						</td>
					</tr>
				</table>
				</td>
			</tr>

		</table>

		<%@include file="../common/copyright.jsp"%>
		</td>
	</tr>
</table>

</body>
</html>