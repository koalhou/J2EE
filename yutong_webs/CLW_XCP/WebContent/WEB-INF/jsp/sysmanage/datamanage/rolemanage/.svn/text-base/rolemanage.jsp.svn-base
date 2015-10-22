<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>

<link rel="stylesheet" href="<s:url value='/scripts/mktree.css' />" type="text/css" media="all" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body>


<div id="wrapper">						
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>					
	<div id="content">
		<div>
		<s:form id="roleform" method="post">
		</s:form>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
					     <tr>
						<td height="36" valign="top" background="../images/tree_tabBg.gif">
						<div class="toolbar">
						<div class="contentTil"><s:text name="menu3.jsgl" /></div>
						
						</div>
						</td>
					   </tr>
				       </table>
					    <table width="100%" border="0" cellspacing="0" cellpadding="0">
							
							<tr>
								 <td valign="top" class="reportInline3">
								     <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
										<tr>
										<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
										  <tr>
										    <td class="titleStyle">
										      <table width="100%" border="0" cellspacing="0" cellpadding="0">
										       <tr>
										        <td width="30%" class="titleStyleZi"><s:text name="roleinfo.info" /></td>
												<td width="69%" align="right">
														<s:if test="#session.perUrlList.contains('111_3_5_1_1')">
														<div class="buhuanhangbut"><a
													href="<s:url value='addRoleBefore.shtml' />"
													class="btnAdd" title="<s:text name="button.create" />"></a></div>
													    </s:if>
												</td>
												<td width="1%">&nbsp;</td>
										       </tr>
										      </table>
										    </td>
										  </tr>
										</table>
										</td>				
									</tr>
									<tr>
									  <td class="reportInline">
									  
									  	<!-- message -->
						 <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                                    <tr>
		                                          <td align="center"><s:actionmessage cssStyle="font-size: 12px;
		color: #009900"
			 /><s:actionerror cssStyle="font-size: 12px;
		color: #CC0000"  /></td>
		                                        </tr>
		                                      </table>
		                                      
									
												<div id="">
													<table id="roleTbl" width="100%"  cellspacing="0">
									
													</table>
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
		</div>
	</div>					
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>					
</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>

<script type="text/javascript">
function detialbrwose(roleId){
	var form = document.getElementById('roleform');
	form.action = 'updateRoleBefore.shtml?role_id=' + roleId;
	form.submit();
}
function reWriteLink(tdDiv,pid,row){
	return  '<a href="javascript:detialbrwose(\'' + pid + '\')">' + tdDiv +'</a>';
	}
//<td class="tdClass"><a href="<s:url value='updateRoleBefore.shtml' />?role_id=<s:property value='role_id'/>"><s:property value="role_name" /></a></td>
jQuery(function() {
	var gala = jQuery('#roleTbl');
	//alert(gala);
	gala.flexigrid({
		url: '<s:url value="/rolepkg/roleList.shtml" />',
		dataType: 'json',
		height: 375,
		width:2000,
		colModel : [
		    		{display: '<s:text name="roleinfo.name" />', name : 'role_name', width : calcColumn(0.46,500), sortable : true, align: 'center', process: reWriteLink,escape: true},
		    		{display: '<s:text name="roleinfo.remark" />', name : 'remark', width :  calcColumn(0.5,500), sortable : true, align: 'center',escape: true}
		    	   ],
		    		    		
		    	sortname: 'role_name',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 10,
		    	
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		    	//getQuery :getQuery
	});
});


//页面自适应
$(function(){
 jQuery(window).resize(function(){
  testWidthHeight();
 });
 testWidthHeight(); 
});
//获取页面高度
function get_page_height() {
	/*
 var height = 0;
 
 if (jQuery.browser.msie) {
     height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
 } else {
     height = self.innerHeight;
 }
//return height;
 */
 var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
 return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();

}
//获取页面宽度
function get_page_width() {
	/*
 var width = 0;
 if(jQuery.browser.msie){ 
  width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
 } else { 
  width = self.innerWidth; 
 } 
 return width;*/
 var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
 return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();

}
function autoWidthHeight(){
	var h = get_page_height();
	 var w = get_page_width();
	 jQuery(".flexigrid").width(w-25);
	 jQuery(".bDiv").height(h-270);
	
}
 
//计算控件宽高
function testWidthHeight(){
 
 
 autoWidthHeight();
 jQuery(window).mk_autoresize({
     height_include: '#content',
     height_exclude: ['#header', '#footer'],
      height_offset: 0,
       width_include: ['#header', '#content', '#footer'],
       width_offset: 0
 });
 autoWidthHeight();

}
</script>
</body>
</html>
