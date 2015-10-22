<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:property value="getText('common.title')参数设置" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body>
<div id="wrapper"><%@include file="/WEB-INF/jsp/common/header.jsp"%>

<div id="content"><s:form id="paramSet_form" action="paramsetList"
	method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top" background="../images/tree_tabBg.gif">
					<div class="toolbar">
					<div class="contentTil"><s:text name="参数设置管理" /></div>
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
		   <td>
			<table width="100%" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td class="reportOnline2">
					<table width="100%" border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td>
							<table border="0" cellspacing="4" cellpadding="0">
								<tr>
									<td>
									<s:text name="参数名称" />：
									<s:textfield id="paramName" name="paramName"/>
									</td>
									<td><a href="javascript:void(0);" onClick="searchList()" class="sbutton"><s:text name="common.chaxun" /></a></td>
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
		<tr>
			<td valign="top">
			<table width="99.5%" border="0" cellspacing="0" cellpadding="0" class="reportOnline" align="center">
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="titleStyle">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="30%" class="titleStyleZi"><s:text name="参数信息列表" /></td>
									<td align="right">
									<div class="buhuanhangbut"><a href="<s:url value='/paramset/preAddParamSet.shtml' />" class="btnAdd" title="<s:text name="button.create" />"></a></div>
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
				<span style="padding: 20px"></span>
					<td class="reportInline" align="left">
					<div id="tabAreaId">
					<div id="Below_new" align="center"><s:actionmessage
						cssStyle="color:green" /><s:actionerror cssStyle="color:red" /></div>
					<table id="dataListTab" width="100%" cellspacing="0">
					</table>
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form></div>
<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript">
	jQuery(function() {
		var tabl = jQuery('#dataListTab');
		tabl.flexigrid({
			url : '<s:url value="/paramset/paramsetList.shtml" />',
			dataType : 'json',
			height : 375,
			width : 2000,
			colModel : [ {
				display : '序号',
				name : 'no',
				width : get_page_width()*0.05,
				sortable : true,
				align : 'center',
				escape : true
			}, {
				display : '参数名称',
				name : 'paramName',
				width : get_page_width()*0.2,
				sortable : true,
				align : 'center',
				process : showDetail,
				escape : true
			}, {
				display : '参数值',
				name : 'paramValue',
				width : get_page_width()*0.3,
				sortable : true,
				align : 'center',
				escape : true
			}, {
				display : '参数说明',
				name : 'paramRemark',
				width : get_page_width()*0.3,
				sortable : true,
				align : 'center',
				escape : true
			}, {
				display : '操作',
				name : 'operate',
				width : get_page_width()*0.1,
				sortable : true,
				align : 'center',
				process : reShowLink,
				escape : true
			} ],

			sortname : 'no',
			sortorder : 'desc',
			sortable : true,
			striped : true,
			usepager : true,
			resizable : false,
			useRp : true,
			rp : 20,
			rpOptions : [ 10, 20, 50, 100 ],// 可选择设定的每页结果数
			showTableToggleBtn : true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
			onSuccess : function() {
			}
		});
	});
	//点击标题
	function showDetail(tdDiv, pid) {
		return '<a href="preAddParamSet.shtml?paramName='+ pid + '">' + tdDiv + '</a>';
	}
	//删除
	function delParamSet(pid){
		if(!confirm("确定要删除"+pid+"吗?")){
			return false;
		}
		jQuery.ajax({
			url:"<s:url value='/paramset/delParamSetByName.shtml'/>?paramName="+pid,
		    type:"post",
		    async:false,
			success:function(){
				//重新加载列表
				alert("删除成功!");
				searchList();
			},
			error:function(){
				alert("删除失败!");
			}
		});
	}
	//显示操作
	function reShowLink(tdDiv, pid) {
		var showStr ="<span style='padding: 10px'><a href='#' onclick=\"delParamSet('"+pid+"');\">删除</a></span>&nbsp;&nbsp;";
		return showStr;
	}
	
	//查询
	function searchList() {
		jQuery('#dataListTab').flexOptions({
			params: [{name: 'paramName', value: formatSpecialChar(jQuery('#paramName').val())}
			]
		});
		jQuery('#dataListTab').flexReload();
	}
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
	 
	 var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
	 return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
	}
	//获取页面宽度
	function get_page_width() {
	
	 var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
	 return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
	}

	function firstrisize(){
		 var h = get_page_height();
		 var w = get_page_width();
		 if(jQuery(window).width()>1310) {
			 jQuery("#tabAreaId").width(jQuery("#header").width()-30);
			 jQuery(".flexigrid").width("100%");
		 }else {
			 jQuery("#tabAreaId").width(jQuery("#header").width()-30);
			 jQuery(".flexigrid").width(jQuery("#tabAreaId").width()-10);
		} 
		jQuery(".bDiv").height(h-325);
	}

	//计算控件宽高
	function testWidthHeight(){
		  firstrisize();
		  
	}
	
</script>
</body>
</html>


