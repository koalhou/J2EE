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
		<s:form id="addForm" action="addRuntime" method="post">
			<s:hidden id="oldTimeId" name="runtime.time_id"></s:hidden>
			<table id="noticeTable" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="36" valign="top">
						<div class="toolbar">
							<div class="contentTil">车辆运行时间</div>
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
						<table class="msgBox2" border="0" align="center" cellpadding="0" cellspacing="0"  style="width: 700px;">
							<tr>
								<td height="32">
									<span class="msgTitle">新增运行时间</span>
								</td>
							</tr>
							<tr>
								<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr> 
										<td align="right" height="32">
											<span class="noticeMsg">*</span>
											<s:text	name="所属组织" />：
										</td>
										<td align="left" class="fsBlack">
											<s:textfield id="org_name" name="runtime.org_name"  maxlength="40" readonly="true" onclick="choiceOrg();" onblur="onblus1();">
											</s:textfield>
											<s:hidden id="organization_id" name="runtime.organization_id"></s:hidden>
											<span id="org_nameDesc" class="noticeMsg"></span>
										</td>
									</tr>
									<tr>
										<td align="right" height="32">
										    <span class="noticeMsg">*</span>
											<s:text	name="授权运行时间" />：
										</td>
										<td align="left" class="fsBlack">
											<input readonly="readonly"
											id="start_time1" name="start_time1" value="00:00"
											class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'HH:mm',
													autoPickDate:true,
													isShowToday:false,
													maxDate:'#F{$dp.$D(\'end_time1\')}',
													isShowClear:false})"
											onblur="onblus();"/>
										    至
											<input readonly="readonly"
											id="end_time1" name="end_time1" value="23:59"
											class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'HH:mm',
													autoPickDate:true,
													isShowToday:false,
													minDate:'#F{$dp.$D(\'start_time1\')}',
													isShowClear:false})"
											onblur="onblus();"/>
										    <s:hidden id="start_time" name="runtime.start_time"></s:hidden>
										    <s:hidden id="end_time" name="runtime.end_time"></s:hidden>
											<a href="javascript:void(0)" onclick="addTimes();">
											<img src="<s:url value='../newimages/add2v.png'/>" align="bottom"/>
											</a>
											 <span id="timeDesc" class="noticeMsg"></span>
											
										</td>
									</tr>
									<tr id="rowTime2"  style="display:block">
										<td align="right" height="32">
											&nbsp;
										</td>
										<td align="left" class="fsBlack">
											<input readonly="readonly"
											id="start_time2" name="start_time2" value="00:00"
											class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'HH:mm',
													autoPickDate:true,
													isShowToday:false,
													maxDate:'#F{$dp.$D(\'end_time2\')}',
													isShowClear:false})"
											onblur="onblus();"/>
										    至
											<input readonly="readonly"
											id="end_time2" name="end_time2" value="23:59"
											class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'HH:mm',
													autoPickDate:true,
													isShowToday:false,
													minDate:'#F{$dp.$D(\'start_time2\')}',
													isShowClear:false})" 
											onblur="onblus();"/>
											<a href="javascript:void(0)" onclick="delTimes(2);">
											<img src="<s:url value='../newimages/del2v.png'/>" align="bottom"/>
											</a>
										</td>
									</tr>
									<tr id="rowTime3" style="display:none">
										<td align="right" height="32">
											&nbsp;
										</td>
										<td align="left" class="fsBlack">
											<input readonly="readonly"
											id="start_time3" name="start_time3" value="00:00"
											class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'HH:mm',
													autoPickDate:true,
													isShowToday:false,
													maxDate:'#F{$dp.$D(\'end_time3\')}',
													isShowClear:false})"
											onblur="onblus();"/>
										    至
											<input readonly="readonly"
											id="end_time3" name="end_time3" value="23:59"
											class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'HH:mm',
													autoPickDate:true,
													isShowToday:false,
													minDate:'#F{$dp.$D(\'start_time3\')}',
													isShowClear:false})"
											onblur="onblus();"/>
										<a href="javascript:void(0)" onclick="delTimes(3);">
										<img src="<s:url value='../newimages/del2v.png'/>" align="bottom"/>
										</a>
										</td>
									</tr>
									<tr id="rowTime4" style="display:none">
										<td align="right" height="32">
											&nbsp;
										</td>
										<td align="left" class="fsBlack">
											<input readonly="readonly"
											id="start_time4" name="start_time4" value="00:00"
											class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'HH:mm',
													autoPickDate:true,
													isShowToday:false,
													maxDate:'#F{$dp.$D(\'end_time4\')}',
													isShowClear:false})" 
											onblur="onblus();"/>
										    至
											<input readonly="readonly"
											id="end_time4" name="end_time4" value="23:59"
											class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'HH:mm',
													autoPickDate:true,
													isShowToday:false,
													minDate:'#F{$dp.$D(\'start_time4\')}',
													isShowClear:false})"
											onblur="onblus();"/>
										<a href="javascript:void(0)" onclick="delTimes(4);">
										<img src="<s:url value='../newimages/del2v.png'/>" align="bottom"/>
										</a>
										</td>
									</tr>
									<tr >
										<td align="right" height="32">
											<span class="noticeMsg">*</span>
											<s:text	name="参数生效周期" />：
										</td>
										<td align="left" class="fsBlack">
										  <input type="checkbox" id="mon" value="mon"/> 周一&nbsp;&nbsp;
										    <input type="checkbox" id="tu" value="tu"/> 周二&nbsp;&nbsp;
										    <input type="checkbox" id="wed" value="wed"/> 周三&nbsp;&nbsp;
										    <input type="checkbox" id="thu" value="thu"/> 周四&nbsp;&nbsp;
										    <input type="checkbox" id="fri" value="fri"/> 周五&nbsp;&nbsp;
										    <input type="checkbox" id="sat" value="sat"/> 周六&nbsp;&nbsp;
										    <input type="checkbox" id="sun" value="sun"/> 周日
										    <s:hidden id="effect_often" name="runtime.effect_often"></s:hidden>
											<span id="effect_oftenDesc" class="noticeMsg"></span>
										</td>
									</tr>
									<tr>
										<td align="right" height="32">
											<span class="noticeMsg">*</span>
											<s:text	name="状态" />：
										</td>
										<td align="left" class="fsBlack">
										   <input type="radio" id="status" name="runtime.status" value="1" checked="checked"/>启用
										   &nbsp;&nbsp;
										   <input type="radio" id="status" name="runtime.status" value="0"/>禁用
										</td>
									</tr>
									<tr>
										<td align="right" height="32">
											<s:text	name="备注" />：
										</td>
										<td align="left" class="fsBlack" >
										   <s:textarea id="remark" name="runtime.remark" cols="40" rows="3"  onkeydown="MaxTextArea(this)" onkeyup="MaxTextArea(this)" onkeypress="MaxTextArea(this)"></s:textarea>
										   <div style="color: #D2D2D2">不超过<font size="3">50</font>个字</div>
										   <span id="remarkDesc" class="noticeMsg"></span>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td class="btnBar">
									<a href="#" class="buttontwo" onclick="goBack('runtimeManage.shtml');"><s:text name="button.cancle" /></a> 
									<a href="#" class="buttontwo" onclick="submitForm()"><span id="span_btn">保存</span></a>
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
function addTimes(){
	if(jQuery('#rowTime2').css('display')=='none'){
		jQuery('#rowTime2').css('display','block');
	}else if(jQuery('#rowTime3').css('display')=='none'){
		jQuery('#rowTime3').css('display','block');
	}else if(jQuery('#rowTime4').css('display')=='none'){
		jQuery('#rowTime4').css('display','block');
	}
}
function delTimes(idx){
	if(idx==2){
		jQuery('#rowTime2').val('');
		jQuery('#rowTime2').css('display','none');
	}else if(idx==3){
		jQuery('#rowTime3').css('display','none');
		jQuery('#rowTime3').val('');
	}if(idx==4){
		jQuery('#rowTime4').css('display','none');
		jQuery('#rowTime4').val('');
	}
}
/**
 * 选择组织
 */
function choiceOrg() {
	if(jQuery('#oldTimeId').val()!=""){
		return false;
	}
	art.dialog.open("<s:url value='/runtimeSet/orgTreePage.shtml' />",{
		title:"组织查询",
		lock:true,
		width:260,
		height:435
	});
} 
function goBack(url){
	window.location.href="<s:url value='/runtimeSet/"+url+"' />";
}
function onblus1(){
	if (jQuery('#org_name').val().length<1){
		 jQuery('#org_nameDesc').text("组织不能为空!");
		 return false;
	 }else{
		 jQuery('#org_nameDesc').text("");
	 }
}
 function onblus(){
	//运行时间
		var time_arr=new Array();
		var start_time_str="";
		var end_time_str="";
		time_arr.push(jQuery('#start_time1').val()+'|'+jQuery('#end_time1').val());
		if(jQuery('#rowTime2').css('display')=='block'){
			time_arr.push(jQuery('#start_time2').val()+'|'+jQuery('#end_time2').val());
		}
		if(jQuery('#rowTime3').css('display')=='block'){
			time_arr.push(jQuery('#start_time3').val()+'|'+jQuery('#end_time3').val());
		}
		if(jQuery('#rowTime4').css('display')=='block'){
			time_arr.push(jQuery('#start_time4').val()+'|'+jQuery('#end_time4').val());
		}
		//对时间排序
		time_arr.sort();
		//时间交叉验证
		for(var i=0;i<time_arr.length;i++){
			if(i==time_arr.length-1){
				break;
			}
			var time1=time_arr[i];
			var btime=time1.split('|')[0];
			var etime=time1.split('|')[1];
			var time2=time_arr[i+1];
			for(var k=0;k<2;k++){
				if(btime <= time2.split('|')[k] && time2.split('|')[k] <= etime){
					jQuery('#timeDesc').text("授权运行时间不能有交叉!");
					 return false;
				}
			}
			
		}
		jQuery('#timeDesc').text("");
 }
  
  function orgOnblus(){
    //生效周期
	var offen="";
	jQuery(":checkbox").each(function(idx){
		if(this.checked==true){
			offen +='1';
		}else{
			offen +='0';
		}
	});
	if(offen=='0000000'){
		jQuery('#effect_oftenDesc').text("请选择参数生效周期!");
		return false;
	}else{
		jQuery('#effect_oftenDesc').text("");
	}
  }

/**
 * 提交
 */
 var old_org_id="";
 function submitForm(){
	 //去除空格
	 //trimAllObjs();
	 jQuery('input').each(function(){
			var value=jQuery.trim(jQuery(this).val());
			jQuery(this).val(value);
	 });
	//组织
	 if (jQuery('#org_name').val().length<1){
		 jQuery('#org_nameDesc').text("组织不能为空!");
		 return false;
	 }
	//根据组织id判断是否存在
	var isExist=false;
	if(jQuery('#oldTimeId').val()!="" && old_org_id==jQuery('#organization_id').val()){
		isExist=false;
	}else{
		jQuery.ajax({
			type:'post',
			url:'<s:url value="/runtimeSet/getRuntimeByOrgId.shtml" />',
			data:{id:jQuery('#organization_id').val()},
			async:false,
			success:function(rtn_code){
				if(rtn_code=='success'){
					isExist=true;
				}
			}
		});
	}
	if(isExist){
		 jQuery('#org_nameDesc').text("此组织已存在,请另选.");
		 return false;
	}
	//运行时间
	var time_arr=new Array();
	
	var start_time_str="";
	var end_time_str="";
	if(jQuery('#end_time1').val() <= jQuery('#start_time1').val()){
		alert("开始时间必须小于结束时间!");
		return false ;
	}else{
		time_arr.push(jQuery('#start_time1').val()+'|'+jQuery('#end_time1').val());
	}
	if(jQuery('#rowTime2').css('display')=='block'){
		if(jQuery('#end_time2').val() <= jQuery('#start_time2').val()){
			alert("开始时间必须小于结束时间!");
			return false;
		}else{
			time_arr.push(jQuery('#start_time2').val()+'|'+jQuery('#end_time2').val());
			}
		
	}
	if(jQuery('#rowTime3').css('display')=='block'){
		if(jQuery('#end_time3').val() <= jQuery('#start_time3').val()){
			alert("开始时间必须小于结束时间!");
			return false;
		}else{
			time_arr.push(jQuery('#start_time3').val()+'|'+jQuery('#end_time3').val());
		}			
	}
	if(jQuery('#rowTime4').css('display')=='block'){
		if(jQuery('#end_time4').val() <= jQuery('#start_time4').val()){
			alert("开始时间必须小于结束时间!");
			return false;
		}else{
			time_arr.push(jQuery('#start_time4').val()+'|'+jQuery('#end_time4').val());
		}			
	}
	//对时间排序
	time_arr.sort();
	//时间交叉验证
	for(var i=0;i<time_arr.length;i++){
		if(i==time_arr.length-1){
			break;
		}
		var time1=time_arr[i];
		var btime=time1.split('|')[0];
		var etime=time1.split('|')[1];
		var time2=time_arr[i+1];
		for(var k=0;k<2;k++){
			if(btime <= time2.split('|')[k] && time2.split('|')[k] <= etime){
				jQuery('#timeDesc').text("授权运行时间不能有交叉!");
				 return false;
			}
		}
		
	}
	//把排序后的字符串，开始和结束时间分别组装
	for(var i=0;i<time_arr.length;i++){
		var t=time_arr[i];
		if(i==time_arr.length-1){
			start_time_str += t.split('|')[0];
			end_time_str += t.split('|')[1];
		}else{
			start_time_str += t.split('|')[0]+"/";
			end_time_str += t.split('|')[1]+"/";
		}
	}
	//生效周期
	var offen="";
	jQuery(":checkbox").each(function(idx){
		if(this.checked==true){
			offen +='1';
		}else{
			offen +='0';
		}
	});
	if(offen=='0000000'){
		jQuery('#effect_oftenDesc').text("请选择参数生效周期!");
		return false;
	}
	jQuery("#start_time").val(start_time_str);
	jQuery("#end_time").val(end_time_str);
	jQuery("#effect_often").val(offen);
	//备注
	if(jQuery('#remark').val().length>50){
		jQuery('#remarkDesc').text("备注不能超过50个字!");
		return false;
	}
	//提交
	if(jQuery('#oldTimeId').val()!=""){
		var form=document.getElementById("addForm");
		form.action="updateRuntime.shtml";
		form.method="post";
		Wit.commitAll(form);
	}else{
		Wit.commitAll(jQuery('#addForm'));
	}
 }
	function popAutoWH(){
		var popwidth = "600px";
		var popheight = "610px";
		var popMaxwidth = "755px";
		var popMaxheight = "716px";
		jQuery('#popArea').mk_sidelayer('set_width', popwidth);
		jQuery('#popArea').mk_sidelayer('set_height', popheight);
		if(jQuery('#popArea').mk_sidelayer("is_show") ==true){
			jQuery("#popArea").width(popwidth);
			jQuery("#popArea").height(popheight);
		}
	}
	//进入修改页面,授权运行时间默认设置
	function defaultTimeSet(start_time,end_time){
		if(start_time=='' || end_time==''){
			return;
		}
		var stimeArr=start_time.split('/');
		var etimeArr=end_time.split('/');
		for(var i=0;i<stimeArr.length;i++){
			if(i==0){
				jQuery('#start_time1').val(stimeArr[0]);
				jQuery('#end_time1').val(etimeArr[0]);
			}else if(i==1){
				jQuery('#start_time2').val(stimeArr[1]);
				jQuery('#end_time2').val(etimeArr[1]);
				jQuery('#rowTime2').css('display','block');
			}else if(i==2){
				jQuery('#start_time3').val(stimeArr[2]);
				jQuery('#end_time3').val(etimeArr[2]);
				jQuery('#rowTime3').css('display','block');
			}else if(i==3){
				jQuery('#start_time4').val(stimeArr[3]);
				jQuery('#end_time4').val(etimeArr[3]);
				jQuery('#rowTime4').css('display','block');
			}
		}
	}
	//页面自适应
	(function (jQuery) {
		jQuery(window).resize(function(){
			testWidthHeight();
		});
		jQuery(window).load(function (){
			testWidthHeight();
		});
		//修改页面初始化
		if(jQuery('#oldTimeId').val()!=""){
			old_org_id=jQuery('#organization_id').val();
			//确定按钮变为修改
			jQuery("#span_btn").text('修改');
			//标题为修改
			jQuery('.msgTitle').text("修改运行时间");
			//授权时间第二组默认隐藏
			jQuery('#rowTime2').css('display','none');
			//状态选中
			var status='<s:property value="%{runtime.status}"/>';
			if(status==0){
				jQuery("input:radio['id'='status']")[1].checked=true;
			}else{
				jQuery("input:radio['id'='status']")[0].checked=true;
			}
			//授权运行时间默认设置
			var start_time='<s:property value="%{runtime.start_time}"/>';
			var end_time='<s:property value="%{runtime.end_time}"/>';
			defaultTimeSet(start_time,end_time);
			//参数生效周期选中
			var effectTime='<s:property value="%{runtime.effect_often}"/>';
			if(effectTime!=''){
				jQuery(":checkbox").each(function(idx,e){
					var str=effectTime.charAt(idx);
					if(str=='1'){
						this.checked=true;
					}
				});
			}
		}
		//为授权运行时间添加点击事件
		jQuery(":checkbox").click(function(){
			orgOnblus();
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
	function MaxTextArea(content){
	    maxlimit=50;
	    if (content.value.length > maxlimit)
	    	content.value = content.value.substring(0, maxlimit);
	      
	   }
</script>
</body>
</html>
