<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
	<style type="text/css">
		.lineStyle{ background: url(../images/wline.gif) repeat-x top left; float: left; height: 0px; padding-top: 10px; width: 100%;color: #2a2a2a;}
		.mypreview {
		 max-width:120px;
		 max-height:140px;
		 width:expression_r_r(document.body.clientWidth > 120? "120px": "auto" ); 
		 height:expression_r_r(document.body.scrollHeight > 400 ? "140px" : "auto" );
		}
	</style>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
	    <div class="popBox">
			  <div id="popArea" class="mk-sidelayer mk-sidelayer-small" style="overflow:hidden;display: none;">
				  <div class="mk-sidelayer-toolbar">        
				        <span class="mk-sidelayer-bar-btn show"></span>
				        <h1 class="mk-sidelayer-bar-title"></h1>
				        <div class="mk-sidelayer-tools" style="overflow:hidden;">
				        </div>
				  </div>
				  <div class="mk-sidelayer-content">
				  </div>
			  </div>
			</div>
		<s:form id="addForm" action="addTripPatch" method="post">
			<s:hidden id="oldPatchId" name="tripPatch.patch_id"></s:hidden>
			<table id="noticeTable" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top">
						<div class="toolbar">
							<div class="contentTil">派车补录</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="center">
									<s:actionerror theme="mat" />
			      					<s:fielderror theme="mat"/>
			      					<s:actionmessage theme="mat"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<div id="divheightsize" style="width:100%;overflow-y:auto;">
						<table class="msgBox2" border="0" align="center" cellpadding="0" cellspacing="0" style="width: 700px;">
							<tr>
								<td height="32">
									<span class="msgTitle"><s:text	name="新增临时派车补录" /></span>
								</td>
							</tr>
							<tr>
								<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr> 
										<td align="right" height="32">
											<span class="noticeMsg">*</span>
											<s:text	name="班车号" />：
										</td>
										<td align="left" class="fsBlack">
											<s:textfield id="vehicle_code" name="tripPatch.vehicle_code"  maxlength="40" readonly="true" onclick="choiceCarln();" onblur="onblurs1();">
											</s:textfield>
											<s:hidden id="vehicle_vin" name="tripPatch.vehicle_vin"></s:hidden>
											<span id="vehicle_codeDesc" class="noticeMsg"></span>
										</td>
									</tr>
									<tr>
										<td align="right" height="32">
											<s:text	name="车牌号" />：
										</td>
										<td align="left" class="fsBlack">
											<s:hidden id="vehicle_ln" name="tripPatch.vehicle_ln"></s:hidden>
											<span id='vehicle_ln_span'></span>
										</td>
									</tr>
									<tr>
										<td align="right" height="32">
										<span class="noticeMsg">*</span>
											<s:text	name="驾驶员" />：
										</td>
										<td align="left" class="fsBlack">
											<s:textfield id="driver_name" name="tripPatch.driver_name"  maxlength="40" readonly="true" onclick="choiceDriver();" onblur="onblurs2();">
											</s:textfield>
											<s:hidden id="driver_id" name="tripPatch.driver_id"></s:hidden>
											<span id="driver_nameDesc" class="noticeMsg"></span>
										</td>
									</tr>
									<tr>
										<td align="right" height="32">
											<s:text	name="用车类型" />：
										</td>
										<td align="left" class="fsBlack">
											<select id="use_type"
												name="tripPatch.type" onchange="changeShow();">
													<option value="1">通勤</option>
													<option value="2">公差</option>
											</select>
										</td>
									</tr>
									<tr>
										<td align="right" height="32">
										<span class="noticeMsg">*</span>
											<s:text	name="行驶线路" />：
										</td>
										<td align="left" class="fsBlack">
										  <div id="route_nameDiv" style="float: left">
										  <s:textfield id="route_name" name="tripPatch.route_name"  maxlength="20" readonly="true" onclick="choiceRoute();" onblur="onblurs4();">
										  </s:textfield>
										  <s:hidden id="route_id" name="tripPatch.route_id"></s:hidden>
										  </div>
										  
										   <div id="extra_trip_nameDiv" style="display:none;float: left">
										   <s:textfield id="extra_trip_name" name="tripPatch.extra_trip_name"  maxlength="20" onblur="onblurs4();">
											</s:textfield>
										   </div>
											<span id="route_nameDesc" class="noticeMsg" style="float: left"></span>
										</td>
									</tr>
									<tr>
										<td align="right" height="32">
										<span class="noticeMsg">*</span>
											<s:text	name="开始时间" />：
										</td>
										<td align="left" class="fsBlack">
											<input readonly="readonly"
											id="beginTime" name="tripPatch.start_time" value="${tripPatch.start_time}" size="25"
											class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',
													isShowToday:false,
													maxDate:'#F{$dp.$D(\'endTime\')}',
													isShowClear:false})" onblur="onblurs5()"/>
										  <span id="timeDesc" class="noticeMsg"></span>
										</td>
									</tr>
									<tr>
										<td align="right" height="32">
										<span class="noticeMsg">*</span>
											<s:text	name="结束时间" />：
										</td>
										<td align="left" class="fsBlack">
											 <input readonly="readonly" id="endTime" name="tripPatch.end_time"  size="25"
													value="${tripPatch.end_time}" class="Wdate" type="text"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',
															isShowToday:false,
															minDate:'#F{$dp.$D(\'beginTime\')}',
															maxDate:'#F{$dp.$D(\'yesterday\')}',
															isShowClear:false})" onblur="onblurs5()"/>
											  <s:hidden id="yesterday" name="tripPatch.yesterday"/>
											<a href='javascript:void(0)' onclick="showMap();">查看轨迹</a>
										</td>
									</tr>
									<tr>
										<td align="right" height="32">
										<span class="noticeMsg">*</span>
											<s:text	name="里程" />：
										</td>
										<td align="left" class="fsBlack">
											<s:textfield id="mileage" name="tripPatch.mileage"  maxlength="6" value="0" onblur="onblurs3();" onkeypress="if ((event.keyCode<48 || event.keyCode>57) && event.keyCode!=46) event.returnValue=false;">
											</s:textfield> km
											<span id="mileageDesc" class="noticeMsg"></span>
										</td>
									</tr>
									<tr>
										<td align="right" height="32">
											<s:text	name="乘车人数" />：
										</td>
										<td align="left" class="fsBlack" >
											<s:textfield id="count" name="tripPatch.count"  maxlength="3" value="0" onkeypress="if (event.keyCode<48 || event.keyCode>57) event.returnValue=false;" >
											</s:textfield>
											<span id="countDesc" class="noticeMsg" ></span>
										</td>
									</tr>
									
								</table>
								</td>
							</tr>
							<tr>
								<td class="btnBar" align="center">
									<a href="#" class="buttontwo" onclick="submitForm()" style="display:inline-block;float: none;"><s:text name="保存" /></a>
									<a href="#" class="buttontwo" onclick="goBack('tripPatchManage.shtml');" style="display:inline-block;float: none;"><s:text name="button.cancle"/></a> 
								</td>
							</tr>
						</table>
						</div>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.form-3.45.0.js'/>"></script>
<script type="text/javascript">
/**
 * 1、用车类型为“通勤”时，“行驶线路”为选择框；类型为“公差”时，“行驶线路”为输入框；
   2、行驶线路不允许为空；长度限制——20
 */
function changeShow(){
	
	var use_type=jQuery("#use_type").val();
	if(use_type=='1'){
		jQuery("#extra_trip_nameDiv").hide();
		jQuery("#route_nameDiv").show();
	}else if(use_type=='2'){
		jQuery('#route_name').val('');
		jQuery("#extra_trip_nameDiv").show();
		jQuery("#route_nameDiv").hide();
	}
}
/**
 * 选择车辆
 */
 function choiceCarln() {
		art.dialog.open("<s:url value='/tripPatch/chooseVehicle.shtml' />",{
			title:"车辆信息",
			lock:true,
			width:260,
			height:435
		});
}
/**
 * 选择驾驶员
 */
function choiceDriver(){
	 art.dialog.open("<s:url value='/tripPatch/chooseDriver.shtml' />",{
			title:"驾驶员信息",
			lock:true,
			width:460,
			height:435
		});
}
/**
 * 选择线路
 */
function choiceRoute() {
	art.dialog.open("<s:url value='/tripPatch/chooseRoute.shtml' />",{
		title:"线路查询",
		lock:true,
		width:260,
		height:435
	});
} 
function goBack(url){
	window.location.href="<s:url value='/tripPatch/"+url+"' />";
}
//根据车辆vin和时间段判断发车行程里里是否存在发车记录
function isExistTrip(){
	var isExist=false;
	jQuery.ajax({
		type:'post',
		url:'<s:url value="/tripPatch/isExistTrip.shtml" />',
		data:{'vin':jQuery('#vehicle_vin').val(),'start_time':jQuery('#beginTime').val(),'end_time':jQuery('#endTime').val()},
		async:false,
		success:function(rtn_code){
			if(rtn_code=='success'){
				isExist=true;
			}
		}
	});
	return isExist;	
}

function onblurs1(){
	if (jQuery('#vehicle_code').val().length<1){
		 jQuery('#vehicle_codeDesc').text("班车号不能为空!");
		return false;
	 }else{
		 jQuery('#vehicle_codeDesc').text("");
	 }
	
}
function onblurs2(){
	//驾驶员
	if (jQuery('#driver_name').val().length<1){
		jQuery('#driver_nameDesc').text("驾驶员不能为空!");
		return false;
	}else{
		jQuery('#driver_nameDesc').text("");
		
	}
	
}

function onblurs3(){
	var mileage=jQuery('#mileage').val();
	if (mileage.length<1){
		jQuery('#mileageDesc').text("里程不能为空!");
		return false;
	}
	//小数点后保留一位
	var idx=mileage.indexOf('.');
	if(idx>0){
		var suffix=mileage.substr(idx+1);
		if(suffix.length>1){
			jQuery('#mileageDesc').text("小数点只能保留一位!");
			return false;
		}
	}
	//里程
	if(mileage>9999.9){
		jQuery('#mileageDesc').text("里程不能大于9999.9");
		return false;
	}else{
		jQuery('#mileageDesc').text("");
	}
	
}
function onblurs4(){
	//行驶线路
	if (jQuery('#route_name').val().length<1 && jQuery('#extra_trip_name').val().length<1){
		jQuery('#route_nameDesc').text("行驶线路不能为空!");
		return false;
	}else{
		jQuery('#route_nameDesc').text("");
	}
	
}
function onblurs5(){
	//班车号
	 if (jQuery('#vehicle_code').val().length>0){
		//是否存在发车时间冲突,(判断录入的时间是否合法)
		if(isExistTrip()){
			jQuery('#timeDesc').text("开始或结束时间,存在发车记录冲突!");
			return false;
		}else{
			jQuery('#timeDesc').text("");
		}
	 }
}


/**
 * 提交
 */
 function submitForm(){
	 //去除空格
	 //trimAllObjs();
	 jQuery('input').each(function(){
			var value=jQuery.trim(jQuery(this).val());
			jQuery(this).val(value);
		});
	//班车号
	 if (jQuery('#vehicle_code').val().length<1){
		 jQuery('#vehicle_codeDesc').text("班车号不能为空!");
		return false;
	 }
	//驾驶员
	if (jQuery('#driver_name').val().length<1){
		jQuery('#driver_nameDesc').text("驾驶员不能为空!");
		return false;
	}
	//行驶线路
	if (jQuery('#route_name').val().length<1 && jQuery('#extra_trip_name').val().length<1){
		jQuery('#route_nameDesc').text("行驶线路不能为空!");
		return false;
	}
	//乘车人数
	var count=jQuery('#count').val();
	if(isNaN(count)){
		jQuery('#countDesc').text("只能为正整数!");
		return false;
	}
	//里程
	var mileage=jQuery('#mileage').val();
	if (mileage.length<1){
		jQuery('#mileageDesc').text("里程不能为空!");
		return false;
	}else{
		if(isNaN(mileage)){
			jQuery('#mileageDesc').text("里程只能为数字!");
			return false;
		}else{
			if(mileage>9999.9){
				jQuery('#mileageDesc').text("里程不能大于9999.9");
				return false;
			}
		}
		//小数点后保留一位
		var idx=mileage.indexOf('.');
		if(idx>0){
			var suffix=mileage.substr(idx+1);
			if(suffix.length>1){
				jQuery('#mileageDesc').text("小数点只能保留一位!");
				return false;
			}
		}
	}
	//是否存在发车时间冲突,(判断录入的时间是否合法)
	if(isExistTrip()){
		jQuery('#timeDesc').text("开始或结束时间,存在发车记录冲突!");
		return false;
	}
	//提交
	if(jQuery('#oldPatchId').val()!=""){
// 		var form=document.getElementById("addParamSetForm");
// 		form.action="updateParamSet.shtml";
// 		form.method="post";
// 		Wit.commitAll(form);
	}else{
		Wit.commitAll(jQuery('#addForm'));
	}
 }
/**
 * 显示地图
 */
function showMap(){
	var vehicle_code=jQuery('#vehicle_code').val();
	var vehicle_ln=jQuery('#vehicle_ln').val();
	var vehicle_vin=jQuery('#vehicle_vin').val();
	var beginTime=jQuery('#beginTime').val();
	var endTime=jQuery('#endTime').val();
	if(vehicle_code==""){
		alert('请选择车辆');
		return false;
	}
	var vehicle_vin=jQuery('#vehicle_vin').val();
	var iframeObj=document.getElementById('iframeshowArea');
	if(iframeObj!=null){
		iframeObj.src="";
	}
	//显示地图泡泡页
	var title=vehicle_code+','+vehicle_ln;
	jQuery('#popArea').mk_sidelayer('set_title',encodeHtml(title));
	jQuery('#popArea').mk_sidelayer('reload',encodeURI('<s:url value="/tripPatch/loadIframe.shtml" />?vin='+vehicle_vin+'&start_time='+beginTime+'&end_time='+endTime));
}
/**
 * 初始化地图弹出框信息
 */
 function initpop() {
	    jQuery('#popArea').mk_sidelayer({
	      'height': '610px',
	      'width': '600px',
	      //'url': '<s:url value="../checking/loadIframe.shtml" />',
	      'is_show': false,
	      'can_close': true,
	      close_callback: function() {
	    	  var srcPage=document.getElementById('iframeshowArea');
	    	  if(srcPage!=null){
	    		  srcPage.src='';
	    	  }
	    	  var is_show=jQuery('#popArea').mk_sidelayer('is_show');
			  if(is_show){
				jQuery('#popArea').mk_sidelayer('close');
			  }
	      },
	      hide_callback: function() {
	    	  var srcPage=document.getElementById('iframeshowArea');
	    	  if(srcPage!=null){
	    		  srcPage.src='';
	    	  }
	      }
	    });
	}
	function popAutoWH(){
		var popwidth = "500px";
		var popheight = "450px";
		var popIframeH = "396px";
		if(jQuery(window).height() >= 900){
			var popwidth = "655px";
			var popheight = "610px";
			var popIframeH = "550px";	
		} 
		jQuery('#popArea').mk_sidelayer('set_width', popwidth);
		jQuery('#popArea').mk_sidelayer('set_height', popheight);
		if(jQuery('#popArea').mk_sidelayer("is_show") ==true){
			jQuery("#popArea").width(popwidth);
			jQuery("#popArea").height(popheight);
			jQuery("#iframeshowArea").width(popwidth);
			jQuery("#iframeshowArea").height(popIframeH);
		}
	}
	//页面自适应
	(function (jQuery) {
		jQuery(window).resize(function(){
			testWidthHeight();
			//地图弹出框自适应
			popAutoWH();
		});
		jQuery(window).load(function (){
			testWidthHeight();
		});
		//初始化地图
		initpop();
		//地图弹出框自适应
		popAutoWH();
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
	 
	//计算控件宽高
	function testWidthHeight(){
		var h = get_page_height();
		var test=document.getElementById("noticeTable");
		if(h>165){
			test.style.height = h-165;
		}
		jQuery(window).mk_autoresize({
		       height_include: '#content',
		       height_exclude: ['#header', '#footer'],
		       height_offset: 0,
		       width_include: ['#header', '#content', '#footer'],
		       width_offset: 0
		    });
		h = get_page_height();
		if(h>165){
			test.style.height = h-165;
		}
	}
</script>
</body>
</html>
