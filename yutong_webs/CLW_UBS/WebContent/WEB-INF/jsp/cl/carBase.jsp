<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<title><s:property value="getText('menu.cl')"/>&nbsp;|&nbsp;<s:property value="getText('menu.cl.vehicle')"/></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
</head>
<body>
<script type="text/javascript">
function searchCarBaseInfo(){
	//document.getElementById('clwForm').submit();
	$('clwForm').action="<s:url value='carbase.shtml' />";
	Wit.commitAll($('clwForm'));
}
function doExportCarBaseInfo(){
	if(confirm("<s:property value="getText('confirm.export.file')" />")) {
		$('clwForm').action="<s:url value='carbase_do_export.shtml' />";
		$('clwForm').submit();
	}
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
				<s:form id="clwForm" action="carbase" method="post">
				<s:if test="#session.perUrlList.contains('111_0_2_2_1')">
					<tr>
	                  	<td class="reportOnline2">
		                    <table width="100%" border="0" cellspacing="8" cellpadding="0">
		                      <tr>
		                        
								<td><table border="0" cellspacing="4" cellpadding="0" >
		                          <tr>
		                            <td><s:property value="getText('carbase.info.vehicle_vin')"/>：
		                            <s:textfield id="vehicle_vin" name="vehicle_vin" maxlength="30" cssClass="input120"/>&nbsp;&nbsp;
		                              	<s:property value="getText('carbase.info.vehicle_ln')"/>：
		                            <s:textfield id="vehicle_ln" name="vehicle_ln" maxlength="30" cssClass="input120"/>
		                            </td>
		                            <td><a href="#" onclick="searchCarBaseInfo()" class="sbutton"><s:property value="getText('btn.query')" /></a></td>
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
												<td width="30%" class="titleStyleZi"><s:property value="getText('carbase.info.list')"/></td>
												<td width="69%" align="right">
												<s:if test="#session.perUrlList.contains('111_0_2_2_2')">
													<div class="buhuanhangbut">
					                                  <a href="<s:url value='/cl/carbase_goto_add.shtml'/>" class="btnAdd" title="<s:property value="getText('msg.add')" />">
					                                  </a>
					                                </div>
				                                </s:if>
				                                
				                                <s:if test="#session.perUrlList.contains('111_0_2_2_6')">
				                                <div class="buhuanhangbut">
				                                  <a href="#" onclick="doExportCarBaseInfo()" class="btnDaochu" title="<s:property value="getText('msg.export')" />">
				                                  </a>
				                                </div>
				                                </s:if>
				                                
				                                <s:if test="#session.perUrlList.contains('111_0_2_2_5')">
				                                <div class="buhuanhangbut">
				                                  <a href="<s:url value='/cl/carbase_goto_import.shtml' />" class="btnInput" title="<s:property value="getText('msg.import')" />"></a>
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
														<th scope="col" width="10%"><s:property value="getText('carbase.info.vehicle_vin')"/></th>
														<th scope="col" width="10%"><s:property value="getText('carbase.info.vehicle_ln')"/></th>
														<th scope="col" width="10%"><s:property value="getText('carbase.info.engine_type')"/></th>
														<th scope="col" width="10%"><s:property value="getText('carbase.info.business_type')"/></th>
														<th scope="col" width="10%"><s:property value="getText('carbase.info.vehicle_type_id')"/></th>
														<th scope="col" width="10%"><s:property value="getText('carbase.info.vehicle_code')"/></th>
														<th scope="col" width="10%"><s:property value="getText('carbase.info.show_names')"/></th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="pageList" status="rowstatus">
														
														<tr>
															<td class="tdClass"><s:property value="#rowstatus.index+1" /></td>
															<td class="tdClass"><a href="<s:url value='/cl/carbase_goto_info.shtml' />?vehicle_id=<s:property value='vehicle_id'/>"><s:property value="vehicle_vin" /></a></td>
															<td class="tdClass"><s:property value="vehicle_ln" /></td>
															<td class="tdClass"><s:property value="engine_type" /></td>
															<td class="tdClass"><s:property value="business_type" /></td>
															<td class="tdClass"><s:property value="vehicle_type_id" /></td>
															<td class="tdClass"><s:property value="vehicle_code" /></td>
															<td class="tdClass"><s:property value="show_names" /></td>
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