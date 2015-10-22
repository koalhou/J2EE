<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
	<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
	<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseonly_css.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style type="text/css">
	.searchPlan {
	float: left;
	width: 250px;
	}
	</style>
	</head>
	<body>
		<div id="wrapper">
		  <%@include file="/WEB-INF/jsp/common/header.jsp"%>
		 <div id="content">
		  <s:form id="send_form" action="sendcmdhis" method="post">
		  <s:hidden id="chooseorgid" name="chooseorgid" />
		  <input value="${endime}" id="endimehidden" type="hidden" />
		  <input type="hidden" name="send_type" id="send_type" value="'2001','2002','2202'"/>
			<table  width="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td id='leftdiv' valign="top" class="leftline">
					 <div id="content_leftside">
						  <div id="leftInfoDiv" class="treeTab">
							<a href="#" class="tabfocus">组织</a>
							<a href="#" onclick="HideandShowControl()" class="hideLeft"></a>
						  </div>
						  <div id="lefttree" class="treeBox">
							 <ul id="treeDemo" class="ztree"></ul>
						  </div>	
					 </div>	
					</td>
					<td valign="top" class="sleftline" id="middeldiv" style="display: none;">
					   <div id="content_middelside">
						<div>
						 <a href="#" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
			  		   </div>
					  </div>
					</td>

					<td valign="top" id="rightdiv">
					  <div id="content_rightside">
						 <div class="titleBar">
							<div class="title">操作记录</div>
						 </div>
						  <div id="statusManger">
							  <div class="car-info2">
								 <div style=" height:5px;"></div>
								 <table width="750" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<td width="15">&nbsp;</td>
									<td width="60" align="right"><strong>班车号：&nbsp;</strong></td>
									<td width="110"><s:textfield id="vehicle_code" name="vehicle_code"  maxlength="32" cssClass="input100" onkeypress="chaxun()"/></td>
									
									<td width="60" align="right"><strong>车牌号：&nbsp;</strong></td>
									<td width="110"><s:textfield id="vehicle_ln" name="vehicle_ln"  maxlength="32" cssClass="input100" onkeypress="chaxun()"/></td>
									<td width="70" align="right"><strong>指令状态：&nbsp;</strong></td>
									<td width="110"> 
									<select id="deal_state" name="deal_state" class="text100" onchange="mychange()">
											 <option value="" >全部</option>
											 <option value="0,1,2,4">已下发</option>
											 <option value="3">下发成功</option>
									</select>	
									</td>
									<td width="70"><strong>指令类型：&nbsp;</strong></td>									
									<!-- 如果是泰安的用户，就显示拍照命令		--取消使用，使用权限设定-->
									<%-- <s:if test="#session.adminProfile.en_code == '0000850260'"> --%>
									<s:if test="#session.perUrlList.contains('111_3_1_14_2')">
									
									 <td>
									  <input type="checkbox" name="checkleixing" value="'2001'" checked="ture" onclick="mychange()"/>
									  </td>
									  <td width="30">拍照</td>
									  </s:if>
							
									<%-- </s:if> --%>
									<s:if test="#session.perUrlList.contains('111_3_1_14_1')">
									<td width="10">&nbsp;</td>
									<td width="15"><input type="checkbox" name="checkleixing" value="'2002'" checked="ture" onclick="mychange()"/></td>
									<td width="30">消息</td>
									</s:if>
									<s:if test="#session.perUrlList.contains('111_3_1_14_3')">
									<td width="10">&nbsp;</td>
									<td width="15"><input type="checkbox" name="checkleixing" value="'2202'" checked="ture" onclick="mychange()"/></td>
									<td width="60">重点监控</td>
									</s:if>
								  </tr>
							  </table>
							  <table width="685" border="0" cellspacing="0" cellpadding="0">
								<tr>
								 <td width="15">&nbsp;</td>
								 <td width="60" align="right"><strong>操作人：&nbsp;</strong></td>
								 <td width="110"><s:textfield id="operate_user_name" name="operate_user_name"
												cssClass="input100" maxlength="30" onkeypress="chaxun()"/></td>
								 <td width="70" align="right"><strong>时&nbsp;&nbsp;段：&nbsp;</strong></td>
								 <td width="122">
								   <input readonly="readonly" id="begintime"
												 name="begintime" value="${begintime}" class="Wdate" type="text"
												 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'endime\')}',isShowClear:false})" />
								 </td>
								 <td width="40" align="center"><strong>至&nbsp;</strong></td>
								 <td width="125">
								 <input readonly="readonly"
												id="endime" name="endime" value="${endime}" class="Wdate"
												type="text"
											   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begintime\')}',maxDate:'#F{$dp.$D(\'endimehidden\')}',isShowClear:false})" />
								 </td>
								 <td align="left"><a href="#" class="btn-blue"  onclick="searchList()">查询</a></td>
							  </tr>
							</table>
							</div> 
								<table cellspacing="4" width="100%">
								  <tr>
									<td>
									  <table id="gala" width="100%" cellspacing="0">

									 </table>
								   </td>
								</tr>
							   </table>
							 </div>
						  </div>
					  
				   </td>
				</tr>
			</table>
			</s:form>
		</div>
		 <%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	   </div>
	   <%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	   <%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseonly_js.jsp"%>
	   <script type="text/javascript" language="JavaScript1.2"
		src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	   <script type="text/javascript" language="JavaScript1.2"
			src="<s:url value='/scripts/flexigrid/flexigrid2.0.js' />"></script>
	   <%@include file="/WEB-INF/jsp/businessmanage/sendcommandhis/newsendcmdhis_js.jsp"%>
	</body>
</html>