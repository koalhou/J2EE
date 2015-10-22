<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../../common/meta.jsp"%>
<title>系统设置&nbsp;|&nbsp;客户经理指标设置</title>
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
		tdDiv.innerHTML = '<span style="cursor:pointer;color:#0099FF" title="设置计划量" onclick="edittask(\''+pid+'\')">___</span>';
	}else{
		tdDiv.innerHTML = '<span style="cursor:pointer;color:#0099FF" title="设置计划量" onclick="edittask(\''+pid+'\')">'+tdDiv.innerHTML+'</span>';
	}

}
function edittask(pid){
	var ss=pid.split('|');
	art.dialog.open("preEditTask.shtml?id="+ss[0]+"&queryDate="+ss[1], {width: 338, height: 180,id:'editAMPlan',lock: true,fixed:true,title:"设置计划量"});
}
function cancelArtWinClosed (winID){
		art.dialog.get(winID).close();
		queryTask();
	}

jQuery(function() {
	var month=moment().subtract("months",1).format("YYYY-MM");	
	jQuery("#queryDate").attr("value",month);	
		/**
		*/
	var roleList = jQuery('#tasklist');
	roleList.flexigrid({
		params: [{name: 'queryDate', value: jQuery('#queryDate').val() }],
		url: '<s:url value="/ub/market/json/listTask.shtml" />',
		dataType: 'json',
		height: '375',
		width: '1000',
		colModel : [
		    		{display: '序号', name : 'nu', width : '20',  align: 'left'},
		    		{display: '市场经理', name : 'name', width : '200',  align: 'left'},
		    		{display: '所属大区', name : 'area', width : '200',  align: 'left'},
		    		{display: '计划完成', name : 'plan', width : '150',  align: 'left', process: editTask}
		    		],
		    	sortable: false,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	title: '市场经理计划完成量',
		    	rp:20,	
				rpOptions : [10,20,50,100],
		    	showTableToggleBtn: true 
	});
});

function queryTask() {
	jQuery('#tasklist').flexOptions({
		newp: 1,
		params: [{name: 'queryDate', value: jQuery('#queryDate').val() }]
	});
	jQuery('#tasklist').flexReload();
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
	jQuery(".flexigrid").width(w);
	jQuery(".bDiv").height(h-325); 
	
}
//页面自适应
/**
 * 
 */
 
(function (jQuery) {
    jQuery(window).resize(function(){
    changeWidthHeight();
    jQuery('#tasklist').fixHeight();
});
jQuery(window).load(function (){
	changeWidthHeight();
	jQuery('#tasklist').fixHeight();
});
})(jQuery);
</script>

						<div style="margin: 10px">
						 查询月份： <s:textfield
											id="queryDate" name="queryDate" 
											onfocus="WdatePicker({maxDate:'%y-%M',skin:'whyGreen',dateFmt:'yyyy-MM',onpicked:queryTask})"
											cssClass="Wdate" ></s:textfield>
						</div>
						
							<div  style="clear: both">				
						  	<table id="tasklist" width="100%"  cellspacing="0">
                          	</table>
                          	</div>
						


<%@include file="../../common/copyright.jsp"%>
</body>
</html>