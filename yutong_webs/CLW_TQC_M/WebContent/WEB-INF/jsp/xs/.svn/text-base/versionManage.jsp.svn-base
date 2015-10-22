<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title><s:property value="getText('xs.menu')"/>&nbsp;|&nbsp;<s:property value="getText('versionmanage.menu')"/></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>

</head>
<body>
<script type="text/javascript">
function searchSysVersionInfo(){
	//document.getElementById('clwForm').submit();
	Wit.commitAll($('clwForm'));
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
				<s:form id="clwForm" action="versionmanage" method="post">
				<s:if test="#session.perUrlList.contains('111_0_6_3_1')">
					<tr>
	                  	<td class="reportOnline2">
		                    <table width="100%" border="0" cellspacing="8" cellpadding="0">
		                      <tr>
								<td><table border="0" cellspacing="4" cellpadding="0" >
		                          <tr>
		                            <td>
		                           		<s:property value="getText('versionmanage.info.apply_id')"/>：
		                            	<select name="apply_id" id="apply_id">
		                            		<option value="" <s:if test="apply_id==0">selected</s:if>><s:property value="getText('select.all')"/></option>
			                              	<option value="0" <s:if test="apply_id==\"0\"">selected</s:if>><s:property value="getText('apply_id_0')"/></option>
			                              	<option value="1" <s:if test="apply_id==\"1\"">selected</s:if>><s:property value="getText('apply_id_1')"/></option>
		                              	</select>
		                            </td>
		                            <td><a href="#" onclick="searchSysVersionInfo()" class="sbutton"><s:property value="getText('btn.query')" /></a></td>
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
												<td width="30%" class="titleStyleZi"><s:property value="getText('versionmanage.info.list')"/></td>
												<td width="69%" align="right">
													<s:if test="#session.perUrlList.contains('111_0_6_3_2')">
													<div class="buhuanhangbut">
					                                	<a href="<s:url value='/xs/versionmanage_goto_add.shtml'/>" class="btnAdd" title="<s:property value="getText('msg.add')" />">
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
														<th scope="col" width="30%"><s:property value="getText('versionmanage.info.version_name')"/></th>
														<th scope="col" width="30%"><s:property value="getText('versionmanage.info.apply_id')"/></th>
														<th scope="col" width="30%"><s:property value="getText('versionmanage.info.version_time')"/></th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="pageList" status="rowstatus">
														
														<tr>
															<td class="tdClass"><s:property value="#rowstatus.index+1" /></td>
															<td class="tdClass">
															<a href="<s:url value='versionmanage_goto_info.shtml' />?version_id=<s:property value='version_id'/>"><s:property value="version_name" /></a>
															</td>
															<td class="tdClass">
															<s:if test="apply_id==0">
															<s:property value="getText('apply_id_0')"/>
															</s:if>
															<s:elseif test="apply_id==1">
															<s:property value="getText('apply_id_1')"/>
															</s:elseif>
															<s:elseif test="apply_id==2">
															<s:property value="getText('apply_id_2')"/>
															</s:elseif>
															</td>
															<td class="tdClass"><s:property value="version_time" /></td>
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