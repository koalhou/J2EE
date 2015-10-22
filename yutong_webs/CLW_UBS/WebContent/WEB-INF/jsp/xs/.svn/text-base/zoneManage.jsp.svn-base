<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title><s:property value="getText('xs.menu')"/>&nbsp;|&nbsp;<s:property value="getText('xs.bm.menu')"/>&nbsp;|&nbsp;<s:property value="getText('zonemanage.menu')"/></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>

</head>
<body>
<script type="text/javascript">
function searchZoneXsInfo(){
	var fm=$('clwForm');
	fm.action="<s:url value='/xs/zonemanage.shtml' />";
	Wit.commitAll(fm);
}
function gotoaddZoneXsInfo(){
	var fm=$('clwForm');
	fm.action="<s:url value='/xs/zonemanage_goto_add.shtml' />";
	Wit.commitAll(fm);
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
				<s:form id="clwForm" action="zonemanage" method="post" onsubmit="return false;">
				<s:if test="#session.perUrlList.contains('111_0_6_1_1_1')">
					<tr>
	                  	<td class="reportOnline2">
		                    <table width="100%" border="0" cellspacing="8" cellpadding="0">
		                      <tr>
								<td>
								<table border="0" cellspacing="4" cellpadding="0" >
		                          <tr>
		                            <td>
		                            <s:if test="zone_level==\"1\""><s:property value="getText('zonemanage.info.leve1')"/>：</s:if>
									<s:elseif test="zone_level==\"2\""><s:property value="getText('zonemanage.info.leve2')"/>：</s:elseif>
									<s:elseif test="zone_level==\"3\""><s:property value="getText('zonemanage.info.leve3')"/>：</s:elseif>
									<s:else><s:property value="getText('zonemanage.info.leve')"/>：</s:else>
		                              <input type="text" name="zone_name" id="zone_name" value="<s:property value="zone_name" />" class="input120" maxlength="30"/>
		                              	<s:hidden id="zone_level" name="zone_level"></s:hidden>
		                              	<s:hidden id="zone_parent_id" name="zone_parent_id"></s:hidden>
		                            </td>
		                            <td><a href="#" onclick="searchZoneXsInfo()" class="sbutton"><s:property value="getText('btn.query')" /></a></td>
		                          </tr>
		                        </table>
		                        </td>
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
												<td width="30%" class="titleStyleZi">
												<s:if test="zone_level==\"1\""><s:property value="getText('zonemanage.info.list.leve1')"/></s:if>
												<s:elseif test="zone_level==\"2\""><s:property value="zone_parent_name"/><s:property value="getText('zonemanage.info.list.leve2')"/></s:elseif>
												<s:elseif test="zone_level==\"3\""><s:property value="zone_parent_name"/><s:property value="getText('zonemanage.info.list.leve3')"/></s:elseif>
												<s:else><s:property value="getText('zonemanage.info.list.leve')"/></s:else>
												</td>
												<td width="69%" align="right">
												<s:if test="#session.perUrlList.contains('111_0_6_1_1_2')">
												<div class="buhuanhangbut">
				                                  <a href="#" onclick="gotoaddZoneXsInfo()" class="btnAdd" title="<s:property value="getText('msg.add')" />">
				                                  
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
														<th scope="col" width="20%"><s:property value="getText('zonemanage.info.zone_name')"/></th>
														<th scope="col" width="20%"><s:property value="getText('zonemanage.info.zone_code')"/></th>
														<th scope="col" width="20%"><s:property value="getText('zonemanage.info.zone_level')"/></th>
														<th scope="col" width="20%"><s:property value="getText('zonemanage.info.operate')"/></th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="pageList" status="rowstatus">
														
														<tr>
															<td class="tdClass"><s:property value="#rowstatus.index+1" /></td>
															<td class="tdClass"><a href="<s:url value='/xs/zonemanage_goto_info.shtml' />?zone_id=<s:property value='zone_id'/>&zone_parent_id=<s:property value='zone_parent_id'/>&zone_level=<s:property value='zone_level'/>"><s:property value="zone_name" /></a></td>
															<td class="tdClass"><s:property value="zone_code" /></td>
															<td class="tdClass">
															<s:if test="zone_level==\"1\"">
															<s:property value="getText('zonemanage.info.zone_level1')"/>
															</s:if>
															<s:elseif test="zone_level==\"2\"">
															<s:property value="getText('zonemanage.info.zone_level2')"/>
															</s:elseif>
															<s:else>
															<s:property value="getText('zonemanage.info.zone_level3')"/>
															</s:else>
															</td>
															<td class="tdClass">
															<s:if test="zone_level==\"1\"">
															<a href="<s:url value='/xs/zonemanage.shtml' />?zone_parent_id=<s:property value='zone_id'/>&zone_level=2"><s:property value="getText('zonemanage.info.gotoedit')"/><s:property value="zone_name" /><s:property value="getText('zonemanage.info.gotoedit1')"/></a>
															</s:if>
															<s:elseif test="zone_level==\"2\"">
															<a href="<s:url value='/xs/zonemanage.shtml' />?zone_parent_id=<s:property value='zone_id'/>&zone_level=3"><s:property value="getText('zonemanage.info.gotoedit')"/><s:property value="zone_name" /><s:property value="getText('zonemanage.info.gotoedit2')"/></a>
															</s:elseif>
															<s:else>
															<s:property value="getText('zonemanage.info.operate.noneed')"/>
															</s:else>
															
															
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