<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:property value="getText('common.title')" /></title>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	
	<div id="content">
		<s:form id="checkOilSet_form" action="vehiclemanage" method="post">
		<table  width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td  valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="36" valign="top" background="../images/tree_tabBg.gif">
						<div class="toolbar">
						<div class="contentTil">考核油耗设置</div>											
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
										<s:text name="选择年份" />：
										<select id="checkYear" onchange="searchList()">
										<option value='2013'>2013</option>										
										<option value='2014'>2014</option>
										<option value='2015'>2015</option>
										<option value='2016'>2016</option>
										<option value='2017'>2017</option>
										<option value='2018'>2018</option>
										<option value='2019'>2019</option>
										<option value='2020'>2020</option>
										</select>
										</td>
										<td>
										<s:text name="班车号" />：
										<s:textfield id="vehicleCode" name="vehicleCode" cssClass="input150" maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
										</td>
										<td>
										<s:text name="车牌号" />：
										<s:textfield id="vehicleLn" name="vehicleLn" cssClass="input150" maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
										</td>
										<td>
										<s:text name="车辆VIN" />：
										<s:textfield id="vehicleVin" name="vehicleVin" cssClass="input150" maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
										</td>
										<td>
										<s:text name="车型" />：
										<s:textfield id="vehicleType" name="vehicleType" cssClass="input150" maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
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
											  <td width="25%" class="titleStyleZi">考核百公里油耗设置（单位：升/百公里）</td>
											  <td><a href="javascript:void(0);" onClick="checkMonth()" class="sbuttonLong">设置考核月度</a></td>
											 <td  align="right">
											  <td width="64%" align="right">
											   <%-- 添加 --%>
													<div class="buhuanhangbut"><a href="<s:url value='/checkOilSet/checkOilSetAdd.shtml' />" class="btnAdd" title="<s:text name="button.create" />"></a></div>
													<div class="buhuanhangbut">
													  <a href="#" onclick="exportCheckOilSet();" class="btnDaochu" title="<s:property value="getText('msg.export')" />">
													  </a>
													</div>
													<!--<div class="buhuanhangbut">
													  <a href="<s:url value='/checkOilSet/importCheckOilSet.shtml' />" class="btnInput" title="导入"></a>
													</div>
											   --></td>
											  
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
							<div id="indivgrid">
							<div id="Below_new" align="center" style="color:green">
					         	<s:actionmessage id="messagesuc" cssStyle="color:green"/><s:actionerror id="messageerror" cssStyle="color:red"/>
					        </div>
							   <table id="vehicletbl" width="100%"  cellspacing="0">
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

try {
	xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
 } catch (e) {
	try {
	    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} catch (e2) {
	    xmlHttp = false;
	}
 }

if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
	xmlHttp = new XMLHttpRequest();
}
xmlHttp.open("GET", "null.txt", false);
xmlHttp.setRequestHeader("Range", "bytes=-1");
xmlHttp.send(null);

severtime=new Date(xmlHttp.getResponseHeader("Date"));

jQuery('#checkYear').val(severtime.getFullYear());


//导出数据
function exportCheckOilSet() {
	if($(gala).find("td").length == 0){
		alert("没有考核百公里油耗设置!");
		return;	
	}
	if (confirm("确定要导出考核百公里油耗设置吗？")) {
		document.getElementById('checkOilSet_form').action = "<s:url value='/checkOilSet/exportCheckOilSet.shtml' />";
		document.getElementById('checkOilSet_form').submit();
	}
}
 
 
function checkMonth(){
	
	art.dialog.open("<s:url value='/checkOilSet/initChcekMonth.shtml' />",{
		title:"设置考核月度",
		lock:true,
		width:620,
		height:350
	});
	
}
 

function submit() {
	  var form=document.getElementById('vehicle_form');
	  form.submit();
}

function searchList() {
	document.getElementById('Below_new').style.display = 'none';
	//document.getElementById('messagesuc').style.dispaly='none';
	//alert(jQuery("span[class=actionmessage]").length);
	//jQuery("span[class=actionmessage]").html("11");
	//jQuery("#Below_new").html("11");
	jQuery('#vehicletbl').flexOptions({
		newp: 1,
		params: [
		{name: 'checkYear', value: jQuery('#checkYear').val()},
		{name: 'vehicle_code', value: formatSpecialChar(jQuery('#vehicleCode').val())},
		{name: 'vehicle_ln', value: formatSpecialChar(jQuery('#vehicleLn').val())},
		{name: 'vehicle_vin', value: formatSpecialChar(jQuery('#vehicleVin').val())},
		{name: 'vehicle_type',value: formatSpecialChar(jQuery('#vehicleType').val())}
		]
	});	
	jQuery('#vehicletbl').flexReload();
}


function detialbrwose(vin,vehicle,vehicleCode,checkYear){
	
	
	art.dialog.open("<s:url value='/checkOilSet/getCheckValue.shtml' />?vin="+vin+"&checkYear="+checkYear,{
		title:"车辆"+vehicle+"(班车号"+vehicleCode+")考核油耗明细--"+checkYear+"年",
		lock:true,
		width:650,
		height:450,
		closeFn: function(here){
			//alert(jQuery("#Below_new").val());
			searchList();
			//document.getElementById('Below_new').style.display='';
			var mess=jQuery("#Below_new").val();
			//jQuery("#Below_new").val()=mess;
			jQuery("#messagesuc").html(mess);
		}

	});
	
	
	
	
	
	
	//var form = document.getElementById('vehicle_form');
	//form.action = 'vehicle_edit_form.shtml?vehcileInfo.vehicle_id=' + vehicleid;
	//form.submit();
}
function reWriteLink(tdDiv,pid,row){
	//alert(pid);
	return  '<a href="javascript:detialbrwose(\'' + pid + '\',\'' + row.cell[2] + '\',\'' + row.cell[1] + '\',\'' + row.cell[5] + '\')">' + tdDiv +'</a>';
	}

function reWriteCheckBox(tdDiv,pid,row){
	//if(get_cell_text(pid, 5).length<=1){
		
		return '<input name="carChoice" value="' + row.cell[2] + '"  type="checkbox" />';
	//}
	//else{
		
	 	//tdDiv.innerHTML = '<input name="carChoice" value="' + get_cell_text(pid, 1) + '" checked type="checkbox" />';
	//}
}


	
	
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}


function setOrCancelSelect(obj) {
	
	 if(jQuery(obj).attr("checked")){
		jQuery("input[name='carChoice']").each(function(){
		jQuery("input[name='carChoice']").attr("checked","true");
		});
	 }else{
		 jQuery("input[name='carChoice']").each(function(){
			 jQuery("input[name='carChoice']").removeAttr("checked"); 
			// f_str += ","+jQuery("input[name='carChoice']").attr("value");
		});
	}
}



var gala='';
jQuery(function() {
	gala = jQuery('#vehicletbl');
	gala.flexigrid({
		url: '<s:url value="/checkOilSetlist/checkOilList.shtml" />',
		dataType: 'json',
		height: 375,
		width:jQuery(window).width()-30,
		colModel : [
					{display: '<input id="carChoiceAll" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', name : '', width : get_page_width()*0.015, process:reWriteCheckBox},
					{display: '班车号', name : 'vehicle_code', width : get_page_width()*0.03, sortable : true, align: 'center',process: reWriteLink,escape: true},
					{display: '车牌号', name : 'vehicle_ln', width : get_page_width()*0.06, sortable : true, align: 'center', process: reWriteLink,escape: true},
					{display: '车辆VIN', name : 'vehicle_vin', width : get_page_width()*0.15, sortable : true, align: 'center',escape: true},
					{display: '车型', name : 'vehicle_type', width : get_page_width()*0.15, sortable : true, align: 'center',escape: true},
					{display: '考核年份', name : 'check_time_code', width : get_page_width()*0.05, sortable : false, align: 'center',escape: true},
					{display: '1月', name : 'january', width : get_page_width()*0.03, sortable : true, align: 'center',escape: true},
					{display: '2月', name : 'february', width : get_page_width()*0.03, sortable : true, align: 'center',escape: true},
					{display: '3月', name : 'march', width : get_page_width()*0.03, sortable : true, align: 'center',escape: true},
					{display: '4月', name : 'april', width : get_page_width()*0.03, sortable : true, align: 'center',escape: true},
					{display: '5月', name : 'may', width : get_page_width()*0.03, sortable : true, align: 'center',escape: true},
					{display: '6月', name : 'june', width : get_page_width()*0.03, sortable : true, align: 'center',escape: true},
					{display: '7月', name : 'july', width : get_page_width()*0.03, sortable : true, align: 'center',escape: true},
					{display: '8月', name : 'august', width : get_page_width()*0.03, sortable : true, align: 'center',escape: true},
					{display: '9月', name : 'september', width : get_page_width()*0.03, sortable : true, align: 'center',escape: true},
					{display: '10月', name : 'october', width : get_page_width()*0.03, sortable : true, align: 'center',escape: true},
					{display: '11月', name : 'november', width : get_page_width()*0.03, sortable : true, align: 'center',escape: true},
					{display: '12月', name : 'december', width : get_page_width()*0.03, sortable : true, align: 'center',escape: true},				
					{display: '创建人', name : 'creater', width : get_page_width()*0.05, sortable : true, align: 'center',hide:true,toggle:false,escape: true},
					{display: '创建时间', name : 'create_time', width : get_page_width()*0.08, sortable : true, align: 'center',hide:true,toggle:false,escape: true},
					{display: '最后修改人', name : 'modifier', width : get_page_width()*0.05, sortable : true, align: 'center',hide:true,toggle:false,escape: true},
					{display: '最后修改时间', name : 'modify_time', width : get_page_width()*0.08, sortable : true, align: 'center',hide:true,toggle:false,escape: true}
					],
								
				sortname: 'modify_time',
				sortorder: 'desc',
				sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
				useRp: true,
				rp: 20,				
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
				showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
				onSuccess:function(){
				   jQuery("input[name='carChoiceAll']").removeAttr("checked");  
				  }
				//getQuery :getQuery
	});
});


function openorganizidtree(){
	

	art.dialog.open("<s:url value='/usm/usermanagetreeAction.shtml' />?rad="+Math.random(),
			//userupdatetreeAction.shtml?userID=" + userid,
			{
			width :260,
			height:300,
			id: 'treeOID',
			title: ' ',
			skin: 'aero',
			limit: true,
			lock: true,
			drag: true,
			fixed: false,
				
				//follow: document.getElementById('organizationName'),
				yesFn: function(iframeWin, topWin){
					//对话框iframeWin中对象
					//alert(iframeWin.test);
					var orgNameValue = iframeWin.document.getElementById('organizationName').value;
					var orgIDValue = iframeWin.document.getElementById('organizationID').value;
					//当前页面中对象
					var topOrgName =  window.document.getElementById('organizationName');
					var topOrgID = window.document.getElementById('organizationID');
					//赋值
					if (topOrgName) topOrgName.value = orgNameValue;
					if (topOrgID) topOrgID.value = orgIDValue;
				}
		  
			});

}


jQuery(function(){
	//document.getElementById('Below_new').style.display = 'none';
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
	 
	if(jQuery(window).width()>1310) {
		jQuery("#indivgrid").width(jQuery("#header").width()-30);
		jQuery(".flexigrid").width("100%");
	}else {
		jQuery("#indivgrid").width(jQuery("#header").width()-30);
		jQuery(".flexigrid").width(jQuery("#tabAreaId").width()-10);
	} 
	jQuery(".bDiv").height(h-325);
}

//计算控件宽高
function testWidthHeight(){
	firstrisize();
	 /* jQuery(window).mk_autoresize({
		height_include: '#content',
		height_exclude: ['#header', '#footer'],
		height_offset: 0,
		width_include: ['#header', '#content', '#footer'],
		width_offset: 0
	 }); */
	 firstrisize();
}
</script>
















</body>
</html>


