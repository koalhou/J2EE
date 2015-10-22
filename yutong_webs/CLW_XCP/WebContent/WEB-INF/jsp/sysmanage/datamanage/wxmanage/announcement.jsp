<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/businessmanage/alarmmanage/alarmtime_js.jsp"%>
<title><s:property value="getText('common.title')" /></title>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	
	<div id="content">
		<s:form id="announcement_form" action="announcementmanage" method="post">
		<table  width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td  valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="36" valign="top" background="../images/tree_tabBg.gif">
						<div class="toolbar">
						<div class="contentTil">公告信息</div>
						
						</div>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td class="reportOnline2">
						<table width="100%" border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td>
								<table border="0" cellspacing="4" cellpadding="0">
									<tr>
										<td>
										发布人：
										<s:textfield id="author" name="author" cssClass="input150" maxlength="15" onkeypress="if(event.keyCode==13){searchList();}"/>
										</td>
										<td>
										发布时间：
										<input readonly="readonly"
											id="start_time" name="start_time" value="${start_time}"
											class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,isShowClear:false,maxDate:'#F{$dp.$D(\'end_time\')}',isShowClear:false,onpicked:pickedstarttime})" style="width:100px;background: #fff url(../scripts/lib/My97DatePicker/skin/datePicker.gif) no-repeat right;" />至
										<input readonly="readonly" id="end_time" name="end_time"
											value="${end_time}" class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,isShowClear:false,minDate:'#F{$dp.$D(\'start_time\')}',maxDate:'%y-%M-%d',isShowClear:false,onpicked:pickedendtime})" style="width:100px;background: #fff url(../scripts/lib/My97DatePicker/skin/datePicker.gif) no-repeat right;"/>
										</td>
										<td><a href="javascript:void(0);" onClick="searchList()" class="sbutton"><s:text name="common.chaxun" /></a></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>		
						</td>
					</tr>
					<tr>
					  <td valign="top">
					   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
					     <tr>
					        <td>
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          <tr>
		                         <td class="titleStyle">
		                         <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                                    <tr>
		                                      <td width="30%" class="titleStyleZi"><s:text name="wx.announcementinfo" /></td>
		                                      
		                                     <td  align="right">
		                                      <div class="buhuanhangbut"><a href="<s:url value='/wxannouncement/addbefore.shtml' />" class="btnAdd" title="<s:text name="button.create" />"></a></div>
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
					        <td class="reportInline" align="left">
					        <div>
					        <div id="Below_new" align="center">
					         <s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/>
					        </div>
		                       <table id="announcementtbl" width="100%"  cellspacing="0">
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
		</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">
(function($){
	// 预缓存皮肤，数组第一个为默认皮肤
	$.dialog.defaults.skin = ['aero'];
  })(art);
  
function submit() {
	  var form=document.getElementById('vehicle_form');
	  form.submit();
}

function searchList() {
	document.getElementById('Below_new').style.display='none';
	jQuery('#announcementtbl').flexOptions({
		newp: 1,
		params: [{name: 'author', value: formatSpecialChar(jQuery('#author').val()) },
		 		{name: 'start_time', value: jQuery('#start_time').val()},
		 		{name: 'end_time', value: formatSpecialChar(jQuery('#end_time').val())}
		 		]
	});
	jQuery('#announcementtbl').flexReload();
}

jQuery(function() {
	var gala = jQuery('#announcementtbl');
	gala.flexigrid({
		url: '<s:url value="/wxannouncementGrid/announcementBrowse.shtml" />',
		dataType: 'json',
		height: 375,
		width:2000,
		colModel : [
		    		{display: '序号', name : 'rowNumber', width : get_page_width()*0.05, align: 'center', escape: true},
                    {display: '标题', name : 'gonggao_title', width : get_page_width()*0.1, sortable : true, align: 'center',escape: true},
		    		{display: '发布人', name : 'user_name', width : get_page_width()*0.1, sortable : true, align: 'center',escape: true},
		    		{display: '发布时间', name : 'gonggao_date', width : get_page_width()*0.15, sortable : true, align: 'center',escape: true},
		    		{display: '覆盖车辆数', name : 'gonggao_veh', width : get_page_width()*0.1, align: 'center',process:reWriteLink1,escape: true},
		    		{display: '目标发送人数', name : 'gonggao_user', width : get_page_width()*0.1, align: 'center',escape: true},
		    		{display: '', name : '', width : get_page_width()*0.1, align: 'center',process:reWriteLink,escape: true}
			    	   ],
	    	    params: [{name: 'author', value: formatSpecialChar(jQuery('#author').val()) },
	    		 		{name: 'start_time', value: jQuery('#start_time').val()},
	    		 		{name: 'end_time', value: formatSpecialChar(jQuery('#end_time').val())}
	    		 		],
		    	sortname: 'gonggao_date',
		    	sortorder: 'desc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 20,
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	});
});

function detialbrwose(id){
	var form = document.getElementById('announcement_form');
	form.action = 'showDetail.shtml?announcementInfo.gonggao_id=' + id;
	form.submit();
}
function reWriteLink(tdDiv,pid,row){
	return  '<a href="javascript:detialbrwose(\'' + pid + '\')">查看详情</a>';
}
	
function vehiclebrwose(id){
	 var obj = window.showModalDialog("<s:url value='/wxannouncement/showVehicleDetail.shtml'/>?announcementInfo.gonggao_id="+id,
             self,
             "dialogWidth=700px;dialogHeight=700px;scroll:yes");
}
function reWriteLink1(tdDiv,pid,row){
	return  '<a href="javascript:vehiclebrwose(\'' + pid + '\')">'+tdDiv+'</a>';
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
 /*
 var height = 0;
 
 if (jQuery.browser.msie) {
     height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
 } else {
     height = self.innerHeight;
 }
 return height;*/
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

function firstrisize(){
	var h = get_page_height();
	 var w = get_page_width();
	 jQuery(".flexigrid").width(w-23);
	 //jQuery("#reportTab").height(h-100);
	 jQuery(".bDiv").height(h-325);
}

//计算控件宽高
function testWidthHeight(){
	firstrisize();
	 jQuery(window).mk_autoresize({
        height_include: '#content',
        height_exclude: ['#header', '#footer'],
        height_offset: 0,
        width_include: ['#header', '#content', '#footer'],
        width_offset: 0
     });
	 firstrisize();
}

</script>
</body>
</html>


