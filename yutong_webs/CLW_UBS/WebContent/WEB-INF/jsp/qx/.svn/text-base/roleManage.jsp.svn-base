<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title><s:property value="getText('qx.menu')"/>&nbsp;|&nbsp;<s:property value="getText('rolemanage.menu')"/></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>

</head>
<body>
<%@include file="../common/menu.jsp"%>
<script type="text/javascript">
function searchRoleInfo(){
	//document.getElementById('clwForm').submit();
	Wit.commitAll($('clwForm'));
}

function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}
//转换链接
function reWriteLink(tdDiv,pid){
		tdDiv.innerHTML = '<a href="../qx/rolemanage_goto_info.shtml?role_id='+ pid +'">' + tdDiv.innerHTML +'</a>';
}

function reName(tdDiv,pid){
	if(get_cell_text(pid, 3)=="0"){
		tdDiv.innerHTML ='<s:text name="apply_id_0" />';
	}
	if(get_cell_text(pid, 3)=="1"){
		tdDiv.innerHTML ='<s:text name="apply_id_1" />';
	}
}

jQuery(function() {
	var roleList = jQuery('#roleMangeList');
	roleList.flexigrid({
		url: '<s:url value="/qxf/roleMangeList.shtml" />',
		dataType: 'json',
		height: '375',
		width: '2000',
		colModel : [
		    		{display: '<s:text name="list.number" />', name : 'rowNumber', width : 30, sortable : false, align: 'left'},
		    		{display: '<s:text name="rolemanage.role_name" />', name : 'role_name', width : 150, sortable : true, align: 'left', process: reWriteLink},
		    		{display: '<s:text name="rolemanage.full_name" />', name : 'full_name', width : 200, sortable : true, align: 'left'},
		    		{display: '<s:text name="rolemanage.apply_id" />', name : 'apply_id', width : 80, sortable : true, align: 'left',process: reName}
		    		],
		    	sortname: 'apply_id',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp:10,	
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	});
});

function searchList() {
	jQuery('#empDiv').css("display","none");
	jQuery('#roleMangeList').flexOptions({
		newp: 1,
		params: [{name: 'role_name', value: jQuery('#ROLE_NAME').val() }, 
		         {name: 'full_name', value: jQuery('#FULL_NAME').val()},
		 		 {name: 'apply_id', value: jQuery('#APPLY_ID').val()}]
	});
	jQuery('#roleMangeList').flexReload();
}

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
function changeWidthHeight(){
	var h = get_page_height();
	var w = get_page_width();
	jQuery(".flexigrid").width(w-25);
	jQuery(".bDiv").height(h-350); 
}
//页面自适应
(function (jQuery) {
    jQuery(window).resize(function(){
    changeWidthHeight();
    jQuery('#roleMangeList').fixHeight();
});
jQuery(window).load(function (){
	changeWidthHeight();
	jQuery('#roleMangeList').fixHeight();
});
})(jQuery);
</script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
				<s:form id="clwForm" action="rolemanage" method="post">
				<s:if test="#session.perUrlList.contains('111_4_8_2_1')">
					<tr>
	                  	<td class="reportOnline2">
		                    <table width="100%" border="0" cellspacing="8" cellpadding="0">
		                      <tr>
		                        
								<td><table border="0" cellspacing="4" cellpadding="0" >
		                          <tr>
		                            <td><s:property value="getText('rolemanage.role_name')" />：
		                              <input type="text" name="ROLE_NAME" id="ROLE_NAME" value="<s:property value="role_name" />" class="input120" maxlength="30"/>&nbsp;&nbsp;
		                              	<s:property value="getText('rolemanage.full_name')" />：
		                              <input type="text" name="FULL_NAME" id="FULL_NAME" value="<s:property value="full_name" />" class="input120" maxlength="60"/>&nbsp;&nbsp;
		                              	 <s:property value="getText('rolemanage.apply_id')" />：
		                              <select name="APPLY_ID" id="APPLY_ID">
		                              	<option value="" ><s:property value="getText('select.all')"/></option>
		                              	<option value="0" ><s:property value="getText('apply_id_0')"/></option>
		                              	<option value="1" ><s:property value="getText('apply_id_1')"/></option>
		                              </select>
		                            </td>
		                            <td><a href="#" onclick="searchList()" class="sbutton"><s:property value="getText('btn.query')" /></a></td>
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
												<td width="30%" class="titleStyleZi"><s:property value="getText('rolemanage.info.list')"/></td>
												<td width="69%" align="right">
												<s:if test="#session.perUrlList.contains('111_4_8_2_2')">
												<div class="buhuanhangbut">
				                                  <a href="<s:url value='/qx/rolemanage_goto_add.shtml'/>" class="btnAdd" title="<s:property value="getText('msg.add')" />">
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
										 <div>
				                              <div id="message">
				                                  <div id="empDiv">
				                                    <s:actionerror theme="mat" /><s:fielderror theme="mat"/><s:actionmessage theme="mat"/>
				                                  </div>
				                              </div>
				                              <table id="roleMangeList" width="100%"  cellspacing="0">
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
				</td>
			</tr>

		</table>

		<%@include file="../common/copyright.jsp"%>
		</td>
	</tr>
</table>
</body>
</html>