<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<title><s:property value="getText('qx.menu')" />&nbsp;|&nbsp;<s:property
	value="getText('enterprise.menu')" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
</head>
<body>
<script type="text/javascript">
	function isIE(){ //ie? 
		   if (window.navigator.userAgent.toLowerCase().indexOf("msie")>=1) 
		    return true; 
		   else 
		    return false; 
		} 
/*
 * 新增一个公共参数，用以确定当前车企业被分配辆个数
 */
 	var carsnumber=0;

	
	//显示具体企业信息
	function clickEnterEvent(obj) {
		var elm=document.getElementById('ChooseEnterID_edit_show');
		elm.innerHTML="";
		
		EntiDwr.getEnterpriseDataInfoToShow(obj.id ,{
			callback : function(data) {
			if(isIE()){
				document.getElementById('ENTERPRISE_CODE').innerText=data.enterprise_code;
				document.getElementById('FULL_NAME').innerText=data.full_name;
				document.getElementById('SHORT_NAME').innerText=data.short_name;
				document.getElementById('ENTERPRISE_COUNTRY').innerText=data.enterprise_country;
				document.getElementById('ENTERPRISE_PROVINCE').innerText=data.enterprise_province;
				document.getElementById('ENTERPRISE_CITY').innerText=data.enterprise_city;
				if('0'==data.enterprise_type){
					document.getElementById('ENTERPRISE_TYPE').innerText='<s:property value="getText('enterprise.type0')" />';
				}else{
					document.getElementById('ENTERPRISE_TYPE').innerText='<s:property value="getText('enterprise.type1')" />';
				}
				document.getElementById('ADDRESS').innerText=data.address;
				document.getElementById('EMAIL').innerText=data.email;
				document.getElementById('POSTCODE').innerText=data.postcode;
				document.getElementById('CONTACT_P').innerText=data.contact_p;
				document.getElementById('CONTACT_TEL').innerText=data.contact_tel;
				document.getElementById('MSG_NUM').innerText=data.msg_num;
				document.getElementById('ENTERPRISE_DESC').innerText=data.enterprise_desc;
				document.getElementById('NETADDRESS').innerText=data.netaddress;
				document.getElementById('ENTERPRISE_TYPE_CFG').innerText=data.enterprise_type_cfg;
				document.getElementById('ENTERPRISE_LEVE_CFG').innerText=data.enterprise_leve_cfg;
				document.getElementById('ENTERPRISE_KIND_CFG').innerText=data.enterprise_kind_cfg;
				document.getElementById('MONEY_KIND_CFG').innerText=data.money_kind_cfg;
				document.getElementById('LANGUAGE_CFG').innerText=data.language_cfg;
				if('0'==data.isused){
					document.getElementById('ISUSED').innerText='<s:property value="getText('enterprise.info.ISUSED_0')" />';
				}else{
					document.getElementById('ISUSED').innerText='<s:property value="getText('enterprise.info.ISUSED_1')" />';
				}
				document.getElementById('FAX').innerText=data.fax;
			}else{
				document.getElementById('ENTERPRISE_CODE').textContent=data.enterprise_code;
				document.getElementById('FULL_NAME').textContent=data.full_name;
				document.getElementById('SHORT_NAME').textContent=data.short_name;
				document.getElementById('ENTERPRISE_COUNTRY').textContent=data.enterprise_country;
				document.getElementById('ENTERPRISE_PROVINCE').textContent=data.enterprise_province;
				document.getElementById('ENTERPRISE_CITY').textContent=data.enterprise_city;
				if('0'==data.enterprise_type){
					document.getElementById('ENTERPRISE_TYPE').textContent='<s:property value="getText('enterprise.type0')" />';
				}else{
					document.getElementById('ENTERPRISE_TYPE').textContent='<s:property value="getText('enterprise.type1')" />';
				}
				document.getElementById('ADDRESS').textContent=data.address;
				document.getElementById('EMAIL').textContent=data.email;
				document.getElementById('POSTCODE').textContent=data.postcode;
				document.getElementById('CONTACT_P').textContent=data.contact_p;
				document.getElementById('CONTACT_TEL').textContent=data.contact_tel;
				document.getElementById('MSG_NUM').textContent=data.msg_num;
				document.getElementById('ENTERPRISE_DESC').textContent=data.enterprise_desc;
				document.getElementById('NETADDRESS').textContent=data.netaddress;
				document.getElementById('ENTERPRISE_TYPE_CFG').textContent=data.enterprise_type_cfg;
				document.getElementById('ENTERPRISE_LEVE_CFG').textContent=data.enterprise_leve_cfg;
				document.getElementById('ENTERPRISE_KIND_CFG').textContent=data.enterprise_kind_cfg;
				document.getElementById('MONEY_KIND_CFG').textContent=data.money_kind_cfg;
				document.getElementById('LANGUAGE_CFG').textContent=data.language_cfg;
				document.getElementById('ISUSED').textContent=data.isused;
				if('0'==data.isused){
					document.getElementById('ISUSED').textContent='<s:property value="getText('enterprise.info.ISUSED_0')" />';
				}else{
					document.getElementById('ISUSED').textContent='<s:property value="getText('enterprise.info.ISUSED_1')" />';
				}
				document.getElementById('FAX').textContent=data.fax;
				}
			},
			errorHandler : function(msg, ex) {
				alert(msg);
			}
			});
			if(Number(obj.attributes.getNamedItem('left_num').value)+1==Number(obj.attributes.getNamedItem('right_num').value)){
				EntiDwr.getEnterpriseCarsNumsToShow(obj.id ,{
					callback : function(data) {
					if(isIE()){
						document.getElementById('CARSNUMS').innerText=data;
						carsnumber=Number(data);
					}else{
						document.getElementById('CARSNUMS').textContent=data;
						carsnumber=Number(data);
						}
					},
					errorHandler : function(msg, ex) {
						alert(msg);
					}
					});
			}else{
				if(isIE()){
					document.getElementById('CARSNUMS').innerText="0";
					carsnumber=0;
				}else{
					document.getElementById('CARSNUMS').textContent="0";
					carsnumber=0;
				}
			}
	}
	//跳转到新增页面
	function goto_add(){
		var elm=document.getElementById('ChooseEnterID_edit_show');
		var ent =document.getElementById(onfocusEnterID);
		if(isIE()){
			if(onfocusEnterID==null){
				elm.innerText="<s:property value="getText('enterprise.js.info.needInsertID')"/>";
				return;
			}
			if(onfocusEnterID=='222'){
				elm.innerText="<s:property value="getText('enterprise.js.info.222NotInsert')"/>";
				return;
			}
			if(carsnumber!=0){
				elm.innerText="<s:property value="getText('enterprise.js.info.HaveCars2')"/>";
				return;
			}
			
		}else{
			if(onfocusEnterID==null){
				elm.textContent="<s:property value="getText('enterprise.js.info.needInsertID')"/>";
				return;
			}
			if(onfocusEnterID=='222'){
				elm.textContent="<s:property value="getText('enterprise.js.info.222NotInsert')"/>";
				return;
			}
			if(carsnumber!=0){
				elm.textContent="<s:property value="getText('enterprise.js.info.HaveCars2')"/>";
				return;
			}
		}
		//树结构形状传递
		setEnterTreeMode();
		document.getElementById('ChooseEnterID_edit').value=onfocusEnterID;
		document.getElementById('clwForm').action='<s:url value='entimanage_goto_add.shtml' />';
		document.getElementById('clwForm').submit();
	}
	function goto_edit(){
		var elm=document.getElementById('ChooseEnterID_edit_show');
		if(isIE()){
			if(onfocusEnterID==null){
				elm.innerText="<s:property value="getText('enterprise.js.info.needUpdateID')"/>";
				return;
			}
			if(onfocusEnterID=='111'){
				elm.innerText="<s:property value="getText('enterprise.js.info.111NotUpdate')"/>";
				return;
			}
			if(onfocusEnterID=='222'){
				elm.innerText="<s:property value="getText('enterprise.js.info.222NotUpdate')"/>";
				return;
			}
		}else{
			if(onfocusEnterID==null){
				elm.textContent="<s:property value="getText('enterprise.js.info.needUpdateID')"/>";
				return;
			}
			if(onfocusEnterID=='111'){
				elm.textContent="<s:property value="getText('enterprise.js.info.111NotUpdate')"/>";
				return;
			}
			if(onfocusEnterID=='222'){
				elm.textContent="<s:property value="getText('enterprise.js.info.222NotUpdate')"/>";
				return;
			}
		}
		document.getElementById('ChooseEnterID_edit').value=onfocusEnterID;
		elm.innerHTML="";

		//树结构形状传递
		setEnterTreeMode();
		document.getElementById('clwForm').action='<s:url value='entimanage_goto_edit.shtml' />';
		document.getElementById('clwForm').submit();
	}
	function do_del(){
		var elm=document.getElementById('ChooseEnterID_edit_show');
		if(isIE()){
			if(onfocusEnterID==null){
				elm.innerText="<s:property value="getText('enterprise.js.info.needDelID')"/>";
				return;
			}
			if(onfocusEnterID=='111'){
				elm.innerText="<s:property value="getText('enterprise.js.info.111NotDel')"/>";
				return;
			}
			if(onfocusEnterID=='222'){
				elm.innerText="<s:property value="getText('enterprise.js.info.222NotDel')"/>";
				return;
			}
			var ent =document.getElementById(onfocusEnterID);
			if(Number(ent.attributes.getNamedItem('left_num').value)+1!=Number(ent.attributes.getNamedItem('right_num').value)){
				elm.innerText="<s:property value="getText('enterprise.js.info.OnlyDelChildNode')"/>";
				return;
			}
			if(ent.attributes.getNamedItem('concate_cr_flag').value=='1'){
				elm.innerText="<s:property value="getText('enterprise.js.info.HaveCars')"/>";
				return;
			}
			if(!confirm('<s:property value="getText('enterprise.js.info.sureDel')"/>'+ent.attributes.getNamedItem('short_name').value+'?')){
				return;
			}
		}else{
			if(onfocusEnterID==null){
				elm.textContent="<s:property value="getText('enterprise.js.info.needDelID')"/>";
				return;
			}
			if(onfocusEnterID=='111'){
				elm.textContent="<s:property value="getText('enterprise.js.info.111NotDel')"/>";
				return;
			}
			if(onfocusEnterID=='222'){
				elm.textContent="<s:property value="getText('enterprise.js.info.222NotDel')"/>";
				return;
			}
			var ent =document.getElementById(onfocusEnterID);
			if(Number(ent.attributes.getNamedItem('left_num').value)+1!=Number(ent.attributes.getNamedItem('right_num').value)){
				elm.textContent="<s:property value="getText('enterprise.js.info.OnlyDelChildNode')"/>";
				return;
			}
			if(ent.attributes.getNamedItem('concate_cr_flag').value=='1'){
				elm.textContent="<s:property value="getText('enterprise.js.info.HaveCars')"/>";
				return;
			}
			if(!confirm('<s:property value="getText('enterprise.js.info.sureDel')"/>'+ent.attributes.getNamedItem('short_name').value+'?')){
				return;
			}
		}
		elm.innerHTML="";
		document.getElementById('ChooseEnterID_edit').value=onfocusEnterID;
		//确认窗口
		document.getElementById('clwForm').action='<s:url value='entimanage_do_del.shtml' />';
		document.getElementById('clwForm').submit();

		
	}
</script>
<%@include file="../common/menu.jsp"%>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/EntiDwr.js'></script>

<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>


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
			<tr id="treeTr1" height="592px">
				<td valign="top"><div style="width: 220px; overflow: auto; height:592px;"><%@include file="../treeSpan.jsp"%></div></td>
			</tr>
		</table>
		
		
		</td>
		<td valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="36" valign="top" background="../images/tree_tabBg.gif">
				<div class="toolbar">
				<div class="contentTil"><s:property
					value="getText('enterprise.info')" /></div>
				<div class="tool">
				<ul>
					<s:if test="#session.perUrlList.contains('111_0_1_1_4')">
						<li><a href="#" onClick="do_del()" id="current"><s:property
							value="getText('btn.delete')" /></a></li>
					</s:if>
					<s:if test="#session.perUrlList.contains('111_0_1_1_3')">
						<li><a href="#" onClick="goto_edit()" id="current"><s:property
							value="getText('btn.update')" /></a></li>
					</s:if>
					<s:if test="#session.perUrlList.contains('111_0_1_1_2')">
						<li><a href="#" onClick="goto_add()" id="current"><s:property
							value="getText('btn.insert')" /></a></li>
					</s:if>
				</ul>
				</div>
				</div>
				</td>
			</tr>
		</table>
		<s:form id="clwForm" name="clwForm" action="entimanage_goto_add.shtml"
			method="post">
			<input type="hidden" id="ChooseEnterID_tree"
				name="ChooseEnterID_tree"
				value="<%=session.getValue("ChooseEnterID_tree")%>">
			<input type="hidden" id="ChooseEnterID_edit"
				name="ChooseEnterID_edit">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right" bgcolor="#F6F6F6">
					<div id="message"><s:actionerror theme="mat" /> <s:fielderror
						theme="mat" /> <s:actionmessage theme="mat" /></div>
					</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#F6F6F6"><span
						id="ChooseEnterID_edit_show" class="error"></span></td>
				</tr>
			</table>
		</s:form>
		<table class="msgBox2" width="650" border="0" align="center"
			cellpadding="0" cellspacing="0">
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
						<td width="25%"><label id="ENTERPRISE_CODE"> </label></td>
						<td width="3%">&nbsp;</td>
						<td width="15%" align="right"><s:property
							value="getText('enterprise.info.SHORT_NAME')" />：</td>
				      <td width="37%" align="left"><label id="SHORT_NAME"> </label></td>
					</tr>
					<tr>
					  <td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.FULL_NAME')" />：</td>
						<td width="25%"><label id="FULL_NAME"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.NETADDRESS')" />：</td>
					  <td align="left"><label id="NETADDRESS"> </label></td>
					</tr>
					<tr>
						<td height="28" align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_COUNTRY')" />：</td>
					  <td align="left"><label id="ENTERPRISE_COUNTRY"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_PROVINCE')" />：</td>
					  <td align="left"><label id="ENTERPRISE_PROVINCE"> </label></td>
						
					</tr>
					<tr>
						<td height="28" align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_CITY')" />：</td>
					  <td align="left"><label id="ENTERPRISE_CITY"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_TYPE')" />：</td>
					  <td align="left"><label id="ENTERPRISE_TYPE"> </label></td>
						
					</tr>
					<tr>
						<td height="28" align="right"><s:property
							value="getText('enterprise.info.ADDRESS')" />：</td>
					  <td colspan="4" align="left"><label id="ADDRESS"> </label></td>
						
					</tr>
					<tr>
					  <td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.EMAIL')" />：</td>
					  <td width="25%" align="left"><label id="EMAIL"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.POSTCODE')" />：</td>
					  <td align="left"><label id="POSTCODE"> </label></td>
					</tr>
					<tr>
					  <td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_TYPE_CFG')" />：</td>
					  <td width="25%" align="left"><label id="ENTERPRISE_TYPE_CFG"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_LEVE_CFG')" />：</td>
					  <td align="left"><label id="ENTERPRISE_LEVE_CFG"> </label></td>
					</tr>
					<tr>
					  <td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_KIND_CFG')" />：</td>
					  <td width="25%" align="left"><label id="ENTERPRISE_KIND_CFG"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.MONEY_KIND_CFG')" />：</td>
					  <td align="left"><label id="MONEY_KIND_CFG"> </label></td>
					</tr>
					<tr>
					  <td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.LANGUAGE_CFG')" />：</td>
					  <td width="25%" align="left"><label id="LANGUAGE_CFG"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.ISUSED')" />：</td>
					  <td align="left"><label id="ISUSED"> </label></td>
					</tr>
					<tr>
					  <td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.CONTACT_P')" />：</td>
					  <td width="25%" align="left"><label id="CONTACT_P"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.CONTACT_TEL')" />：</td>
					  <td align="left"><label id="CONTACT_TEL"> </label></td>
					</tr>
					<tr>
					  <td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.MSG_NUM')" />：</td>
					  <td width="25%" align="left"><label id="MSG_NUM"> </label></td>
						<td>&nbsp;</td>
						<td align="right"><s:property
							value="getText('enterprise.info.FAX')" />：</td>
					  <td align="left"><label id="FAX"> </label></td>
					</tr>
					<tr>
					  <td width="20%" height="28" align="right"><s:property
							value="getText('enterprise.info.CARSNUMS')" />：</td>
					  <td width="25%" align="left"><label id="CARSNUMS"></label></td>
						<td>&nbsp;</td>
						<td align="right"></td>
						<td align="left"></td>
					</tr>
					<tr>
						<td height="28" align="right"><s:property
							value="getText('enterprise.info.ENTERPRISE_DESC')" />：</td>
					  	<td colspan="5" align="left">
							<label style="width:500px" id="ENTERPRISE_DESC"> </label>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="btnBar"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<%@include file="../common/copyright.jsp"%>

</body>
</html>
