<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<title><s:property value="getText('xs.menu')"/>&nbsp;|&nbsp;<s:property value="getText('ajtnotice.info')"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 中文注释 -->
<link rel="stylesheet" href="<s:url value='/styles/nyroModal.css' />"
	type="text/css" media="screen" />
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
</head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="../common/menu.jsp"%>
<script type="text/javascript">
	function reWriteLink(tdDiv,pid){
		var deal_flag = get_cell_text(pid, 5);
		if(deal_flag ==1){
			tdDiv.innerHTML = '<a href="viewEnterpriseNotice.shtml?ajtNotice.notice_id=' + pid + '">' + tdDiv.innerHTML +'</a>';
		     
		}else{
			tdDiv.innerHTML = '<a href="editEnterpriseNotice.shtml?ajtNotice.notice_id=' + pid + '">' + tdDiv.innerHTML +'</a>';
		}
		
	}
	
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
	function reWritetype(tdDiv,pid){
		var deal_flag = get_cell_text(pid, 2);
		if(deal_flag ==0 ){
		    tdDiv.innerHTML = '<s:text name="select.type.ajt0" />';
		}
		if(deal_flag ==1 ){
		 tdDiv.innerHTML = '<s:text name="select.type.ajt1" />';
		}
		if(deal_flag ==2 ){
		 tdDiv.innerHTML = '<s:text name="select.type.ajt2" />';
		}
	      
	}
	function reWriteStatus(tdDiv,pid){

	   var deal_flag = get_cell_text(pid, 5);
	   if(deal_flag ==0 ){
	      tdDiv.innerHTML = '<s:text name="notice.nopublish" />';
	   } 
	   if(deal_flag ==1 ){
	      tdDiv.innerHTML = '<s:text name="notice.publish" />';
	   }
	   if(deal_flag ==2 ){
		      tdDiv.innerHTML = '<s:text name="notice.deletepublisth" />';
	   }
	}
	jQuery(function() {
		var gala = jQuery('#gala');
		gala.flexigrid({
			url: '<s:url value="/xsp/noticePkg.shtml" />',
			dataType: 'json',
			height: '370',
			width:'2000',
			colModel : [
						{display: '序号', name : 'rowNumber', width : 30, sortable : false, align: 'left'},
						{display: '主题', name : 'notice_theme', width : 180, sortable : true, align: 'left',process:reWriteLink},
						{display: '公告类型', name : 'notice_type', width : 100, sortable : true, align: 'left',process:reWritetype},
						{display: '创建者', name : 'user_name', width : 80, sortable : true, align: 'left'},
						{display: '发布时间', name : 'publish_time', width : 120, sortable : true, align: 'left'},
						{display: '状态', name : 'notice_status', width : 100, sortable : true, align: 'left',process:reWriteStatus}
						],
			    	sortname: 'publish_time',
			    	sortorder: 'desc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
			    	useRp: true,
			    	rp: 10,
					rpOptions : [10,20,50,100 ],// 可选择设定的每页结果数
			    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		});
	});

	//页面自适应
	(function (jQuery) {
	 jQuery(window).resize(function(){
	 
	  testWidthHeight();
	  
	 });
	 jQuery(window).load(function (){
	  
	  testWidthHeight();
	  
	 });
	 
	})(jQuery);
	//获取页面高度
	function get_page_height() {
		var height = 0;
		
		if (jQuery.browser.msie) {
		    height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
		} else {
		    height = self.innerHeight;
		}
		return height;
	}
	//获取页面宽度
	function get_page_width() {
		var width = 0;
		if(jQuery.browser.msie){ 
		 width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
		} else { 
		 width = self.innerWidth; 
		} 
		return width;
	}
	 
	//计算控件宽高
	function testWidthHeight(){
		var h = get_page_height();
		var w = get_page_width();
		jQuery(".flexigrid").width(w-25);
		jQuery(".bDiv").height(h-350); 
	}

	function exportEnterPriseNotice() {
		var form = document.getElementById('noticeform');
		form.submit();
	}
</script>
<body>
<%@include file="notice_validate.jsp"%>
<s:hidden id="message" name="message"></s:hidden>
<s:form id="noticeform" action="exportNotice" method="post" onsubmit="return false;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="36" valign="top" background="../images/tree_tabBg.gif">
				<div class="toolbar">
				<div class="contentTil"><s:text name="ajtnotice.info" /></div>

				</div>
				</td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="5" cellpadding="0">
			<s:if test="#session.perUrlList.contains('111_0_6_6_1')">
			<tr>
				<td class="reportOnline2">
				<table border="0" cellspacing="8" cellpadding="0">
					<tr>
						<td>主题:</td>
						<td><s:textfield id="notice_theme" name="ajtNotice.notice_theme" cssClass="input80"
							maxlength="30" /></td>
						<td>创建时间:</td>
						<td align="left"><input readonly="readonly" id="publish_time"
							name="ajtNotice.publish_time" class="Wdate" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" />

						</td>
						<td>公告类型:</td>
						<td align="left"><select name="ajtNotice.notice_type"
							id="notice_type">
							<option value=""><s:property
								value="getText('select.all')" /></option>
							<option value="0"><s:property
								value="getText('select.type.ajt0')" /></option>
							<option value="1"><s:property
								value="getText('select.type.ajt1')" /></option>
							<option value="2"><s:property
								value="getText('select.type.ajt2')" /></option>
						</select></td>

						<td><a href="#" onClick="searchList()" class="sbutton">查询</a>
						</td>
					</tr>
				</table>
			  </td>
			</tr>
			</s:if>

			<tr>
				<td valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="reportOnline">
					<tr>
						<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="titleStyle">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="30%" class="titleStyleZi"><s:text name="ajtnotice.info" /></td>
										<td width="69%" align="right">
										<s:if test="#session.perUrlList.contains('111_0_6_6_2')">
										<div class="buhuanhangbut"><a
											href="<s:url value='/xs/addEnterpriseNotice.shtml' />"
											class="btnAdd" title="<s:text name="btn.insert" />"></a></div>
										</s:if>
										
										<s:if test="#session.perUrlList.contains('111_0_6_6_7')">
										<div class="buhuanhangbut">
			                                  <a href="#" class="btnDaochu" onclick="exportEnterPriseNotice();return false;" title="<s:text name="msg.export" />">
			                                  </a>
			                                </div>
			                            </s:if>    
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td class="reportInline">
						<%-- 提示信息 --%>
						<div id="Below_new" style="text-align:center"><s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/></div>
						<table id="gala" width="100%" cellspacing="0">

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
</s:form>
<%@include file="/WEB-INF/jsp/common/copyright.jsp"%>
</body>
</html>
