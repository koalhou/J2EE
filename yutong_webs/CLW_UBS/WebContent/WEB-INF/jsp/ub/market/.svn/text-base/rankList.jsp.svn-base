<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
<title>行为统计&nbsp;|&nbsp;市场看板</title>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
		<script type="text/javascript"
	src="<s:url value='/scripts/common/moment.min.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/common/function_common.js' />"></script>


</head>
<body>
<%@include file="../../common/menu.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
	<script type="text/javascript" 
	src="<s:url value='/scripts/indicator/GlareTpl.js' />"></script>
	<script type="text/javascript" 
	src="<s:url value='/scripts/grouptype/GroupType.js' />"></script>
<script type="text/javascript">

///////////////

function tabClick(url)
{
	window.location=url;
}
///////////////////
	
	//flexgrid
function get_cell_text(pid, cell_idx) 
{
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

//编辑
function editTask(tdDiv,pid)
{
	if(tdDiv.innerHTML=='-1'){
		tdDiv.innerHTML = '';
	}
}
function editCompleted(tdDiv,pid)
{
	if(tdDiv.innerHTML=='-1.0%'){
		tdDiv.innerHTML = '';
	}
}
jQuery(function() {
	var month=moment().subtract("months",1).format("YYYY-MM");	
	jQuery("#queryDate").attr("value",month);	
		
	var roleList = jQuery('#amlist');
	roleList.flexigrid({
		params: [{name: 'queryDate', value: jQuery('#queryDate').val() }],
		url: '<s:url value="/ub/market/json/amRank.shtml" />',
		dataType: 'json',
		height: '200',
		width: '1000',
		colModel : [
		    		{display: '排名', name : 'nu', width : '30',  align: 'left'},
		    		{display: '市场经理', name : 'name', width : '200',  align: 'left'},
		    		{display: '所属大区', name : 'area', width : '200',  align: 'left'},
		    		{display: '计划完成', name : 'plan', width : '150',  align: 'left', process: editTask},
		    		{display: '实际完成', name : 'plan', width : '150',  align: 'left'},
		    		{display: '任务完成率', name : 'plan', width : '150',  align: 'left',process:editCompleted}
		    		],
		    	sortable: false,
				striped :true,
				usepager :false, 
				resizable: false,
		    	useRp: true,
		    	title: '市场经理排名',
		    	rp:10,	
				rpOptions : [10,20,50,100],
		    	showTableToggleBtn: true 
	});
	var roleList = jQuery('#tmlist');
	roleList.flexigrid({
		params: [{name: 'queryDate', value: jQuery('#queryDate').val() }],
		url: '<s:url value="/ub/market/json/acRank.shtml" />',
		dataType: 'json',
		height: '200',
		width: '1000',
		colModel : [
		    		{display: '排名', name : 'nu', width : '30',  align: 'left'},
		    		{display: '工程师', name : 'name', width : '200',  align: 'left'},
		    		{display: '所属大区', name : 'area', width : '200',  align: 'left'},
		    		{display: '企业活跃度', name : 'plan', width : '150',  align: 'left'}
		    		],
		    	sortable: false,
				striped :true,
				usepager :false, 
				resizable: false,
		    	useRp: true,
		    	title: '工程师排名',
		    	rp:20,	
				rpOptions : [10,20,50,100],
		    	showTableToggleBtn: true 
	});
});

function queryTask() {
	jQuery('#amlist').flexOptions({
		newp: 1,
		params: [{name: 'queryDate', value: jQuery('#queryDate').val() }]
	});
	jQuery('#amlist').flexReload();
	jQuery('#tmlist').flexOptions({
		newp: 1,
		params: [{name: 'queryDate', value: jQuery('#queryDate').val() }]
	});
	jQuery('#tmlist').flexReload();
}

//获取页面高度
function get_page_height() {
	var height = 0;
	
	if (jQuery.browser.msie) {
	    height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
	} else {
	    height = jQuery(document).height();
	}
	return height;
}
//获取页面宽度
function get_page_width() {
	var width = 0;
	if(jQuery.browser.msie){ 
		width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
	} else { 
		width = jQuery(document).width(); 
	} 
	return width;
}
//计算控件宽高
function changeWidthHeight(){
	var h = get_page_height();
	var w = get_page_width();
	jQuery(".flexigrid").width(w-30);
	jQuery(".bDiv").height(h-325); 
	
}
//页面自适应
/**
 * 

(function (jQuery) {
    jQuery(window).resize(function(){
    changeWidthHeight();
    jQuery('#amlist').fixHeight();
    jQuery('#tmlist').fixHeight();
});
jQuery(window).load(function (){
	changeWidthHeight();
	jQuery('#amlist').fixHeight();
    jQuery('#tmlist').fixHeight();
});
})(jQuery);
 */
</script>
<s:form id="logincntstat_form" action="querystat" method="post">

	<table height="628px" width="100%" border="0" cellspacing="0"
		cellpadding="0">
		<tr valign="top">
			<td class="reportInline">

			<table width="100%" border="0" cellspacing="0" cellpadding="0">

				<tr>
					<td valign="top"><!-- body -->
					<div style="margin:10px;min-height:600px;background-color:white;">
						<div class="ubtab">
							<ul>
							<li><a href="linesInit.shtml" >任务完成率与活跃度趋势图</a></li>
							<li class="ubtabselect"><a href="rankListInit.shtml" "> 查看排名</a></li>
								
						</ul>
							<div class="ubtabcontent" >
								<div style="margin:5px">
								<!-- tab内容 -->
									
							<div style="margin: 10px">
						 查询月份： <s:textfield
											id="queryDate" name="queryDate" 
											onfocus="WdatePicker({maxDate:'%y-%M',skin:'whyGreen',dateFmt:'yyyy-MM',onpicked:queryTask})"
											cssClass="Wdate" ></s:textfield>
						</div>					
							<div style="margin:5px;">				
						  	<table id="amlist" width="100%"  cellspacing="0">
                          	</table>
						  	<table id="tmlist" width="100%"  cellspacing="0">
                          	</table>
                          	</div>
						
								</div>
							</div>
						</div>
						
						
						
						
						
					</div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
</s:form>

<%@include file="../../common/copyright.jsp"%>
</body>
</html>