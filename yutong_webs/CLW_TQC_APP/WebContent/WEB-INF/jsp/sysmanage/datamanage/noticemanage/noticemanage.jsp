<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:property value="getText('common.title')" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body>
<div id="wrapper"><%@include file="/WEB-INF/jsp/common/header.jsp"%>

<div id="content"><s:form id="notice_form" action="noticeManageList"
	method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top" background="../images/tree_tabBg.gif">
					<div class="toolbar">
					<div class="contentTil"><s:text name="公告信息" /></div>
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
									<td><s:text name="发布时间" />：
									 <input id="gonggaoDate"
										name="gonggaoDate"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowToday:false,autoPickDate:true})"
										class="Wdate" /> 
									</td>
									<td><s:text name="状态" />：
									<s:select id="gonggaoState" name="gonggaoState" list="stateMap" headerKey="" headerValue="--全部--" cssStyle="width:90px"/>
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
									<td width="30%" class="titleStyleZi"><s:text name="公告信息" /></td>
									<td align="right">
									<div class="buhuanhangbut"><a href="<s:url value='/notice/preAddNotice.shtml' />" class="btnAdd" title="<s:text name="button.create" />"></a></div>
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
					<table id="noticetbl" width="100%" cellspacing="0">
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
		var gala = jQuery('#noticetbl');
		gala.flexigrid({
			url : '<s:url value="/notice/noticeManageList.shtml" />',
			dataType : 'json',
			height : 375,
			width : 2000,
			colModel : [ {
				display : '序号',
				name : 'GONGGAO_ID',
				width : get_page_width()*0.05,
				sortable : true,
				align : 'center',
				escape : true
			}, {
				display : '标题',
				name : 'GONGGAO_TITLE',
				width : get_page_width()*0.3,
				sortable : true,
				align : 'center',
				escape : true
			}, {
				display : '发布人',
				name : 'GONGGAO_AUTHOR',
				width : get_page_width()*0.1,
				sortable : true,
				align : 'center',
				escape : true
			}, {
				display : '公告时间',
				name : 'GONGGAO_DATE',
				width : get_page_width()*0.1,
				sortable : true,
				align : 'center',
				escape : true
			}, {
				display : '当前状态',
				name : 'SEND_FLAG',
				width : get_page_width()*0.05,
				sortable : true,
				align : 'center',
				process : showState,
				escape : true
			}, {
				display : '操作',
				name : 'operate',
				width : get_page_width()*0.15,
				sortable : true,
				align : 'center',
				process : reShowLink,
				escape : true
			} ],

			sortname : 'gonggao_Date',
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
	//显示状态文字描述
	function showState(tdDiv, pid){
		var state="";
		if(tdDiv=="1"){
			state="已推送";
		}else{
			state="未推送";
		}
		return state;
	}
	//点击标题
	function reWriteLink(tdDiv, pid) {
		return '<a href="searchNoticeById.shtml?noticeId='+ pid + '">' + tdDiv + '</a>';
	}
	//查看详情
	function noticeDetail(id) {
		window.location.href="<s:url value='/notice/searchNoticeById.shtml'/>?noticeId="+id;
	}
	//置顶
	function upFirst(id){
		//window.location.href="<s:url value='/notice/upFirstNotice.shtml'/>?noticeId="+id;
		jQuery.ajax({
			url:"<s:url value='/notice/upFirstNotice.shtml'/>?noticeId="+id,
		    type:"post",
			async:false,
			success:function(){
				//重新加载列表
				jQuery('#gonggaoDate').val("");
				jQuery('#gonggaoState').val("");
				searchList();
			},
			error:function(){
				alert("置顶失败!");
			}
		});
		
	}
	//显示操作
	function reShowLink(tdDiv, pid) {
		var showStr="";
		//showStr +="<span style='padding: 10px'><a href='#' onclick=\"upFirst('"+pid+"');\">置顶</a></span>&nbsp;&nbsp;";
		showStr +="<span style='padding: 10px'><a href='#' onclick=\"noticeDetail('"+pid+"');\">查看详情</a></span>";
		return showStr;
	}
	
	//查询
	function searchList() {
		jQuery('#noticetbl').flexOptions({
			newp: 1,
			params: [{name: 'gonggaoDate', value: formatSpecialChar(jQuery('#gonggaoDate').val())},
			{name: 'gonggaoState', value: formatSpecialChar(jQuery('#gonggaoState').val())}
			]
		});
		jQuery('#noticetbl').flexReload();
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


