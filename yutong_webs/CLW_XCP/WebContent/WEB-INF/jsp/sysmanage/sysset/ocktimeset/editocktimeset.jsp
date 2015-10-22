<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<div id="main" style="overflow: auto;">
  <s:form id="alter_ocktimesetform" action="TimeSet_edit" method="post">
  <s:hidden name="page" />
  <s:hidden name="pageSize" />
  <s:hidden name="ocktimeset.check_time_id" />
  <s:hidden id="enid" name="ocktimeset.enterprise_id" />
  <s:hidden id="oldocktimecode" name="oldcode"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
   		 <tr>
  			<td valign="top">
	  			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top" background="../images/tree_tabBg.gif">
					<div class="toolbar">
					<div class="contentTil"><s:text name="menu3.khydsz" /></div>
					
					</div>
					</td>
				</tr>
				</table>  
 		 <!-- message -->
               <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                  <td align="center"><s:actionmessage cssStyle="font-size: 12px;color: #009900"
	              /><s:actionerror cssStyle="font-size: 12px;color: #CC0000"  />
	             </td>
                 </tr>
               </table>

		    <table class="msgBox2" width="750" border="0" align="center" cellpadding="0" cellspacing="0">
		      <tr>
		        <td height="32" background="<s:url value='/images/msg_title_bg.gif'/>">
		          <span class="msgTitle">&nbsp;&nbsp;<s:text name="ocktime.update" /></span>
		        </td>
		      </tr>
		      <tr>
		        <td align="center">
				  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		              <td width="14%" align="right">考核月度:</td>
		              <td width="36%" align="left">
		                <s:textfield id="check_time_code" name="ocktimeset.check_time_code" onfocus="WdatePicker({dateFmt:'yyyy-MM',autoPickDate:true,isShowToday:false})" cssClass="Wdate">
						</s:textfield><span class="noticeMsg">*</span>
		              </td>
		              <td width="14%" height="28" align="right">月度描述:</td>
		              <td width="36%" align="left">
		                <s:textfield id="check_time_desc" name="ocktimeset.check_time_desc" maxlength="125"/>
		              </td>
		            </tr>
		             <tr>
		             <td height="28" align="right">开始时间:</td>
		              <td align="left">
		              
		                                    <input readonly="readonly" id="start_time" name="ocktimeset.start_time"  value="${ocktimeset.start_time}"
											class="Wdate" type="text" 
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end_time\')}',minDate:'#F{$dp.$D(\'end_time\',{d:-40})}'})"/><span class="noticeMsg">*</span>
					                            
					</td>
		              <td height="28" align="right">结束时间:</td>
		              <td align="left">
		              
		               <input readonly="readonly" id="end_time" name="ocktimeset.end_time" value="${ocktimeset.end_time}"
											class="Wdate" type="text" 
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'start_time\')}',maxDate:'#F{$dp.$D(\'start_time\',{d:40})}',minDate:'#F{$dp.$D(\'start_time\')}'})"/><span class="noticeMsg">*</span>
					</td>
		            </tr>
		          </table>
		        </td>
		      </tr>
		      <tr>
		        <td class="btnBar"> 
		            <a href="#" onclick="goBack('ocktimeset.shtml')" class="buttontwo">取消</a>
		            <s:if test="#session.perUrlList.contains('111_3_5_6_3')">
		            <s:url id="delete" action="TimeSet_delete">
						<s:param name="ocktimeset.check_time_id" value="ocktimeset.check_time_id" />
						<s:param name="page" value="page" />
						<s:param name="pageSize" value="pageSize" />		
					</s:url>
					<a href="#" class="buttontwo" onclick="Wit.delConfirm('${delete}', '<s:text name="common.delete.confirm" />')">
					<s:text name="button.delete" /></a>
					</s:if>
					<s:if test="#session.perUrlList.contains('111_3_5_6_2')">
		           <a href="#" class="buttontwo" onclick="submitalterForm()"><s:text name="button.edit" /></a>
		           </s:if>
		        </td>
		      </tr>
		    </table>
    </td>
    </tr>
 </table>
   </s:form>
</div>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/Ocktimeset.js'></script>
<%@include file="ocktimeset_validate.jsp"%>
<%@include file="editocktimeset_js.jsp"%>
</body>
</html>