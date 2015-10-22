<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title>图片详情</title>
<!-- 照片列表布局样式  -->
<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/galleria.css'/>">
<!--  
<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/pop.css'/>"> -->
<link rel="stylesheet" type="text/css"
		href="<s:url value="/stylesheets/global.css" />" />
<link rel="stylesheet" type="text/css"
		href="<s:url value="/stylesheets/photo.css" />" />
<link rel="stylesheet" type="text/css"
		href="<s:url value="/stylesheets/pop.css" />" />
</head>
<body>
  	<div class="popArea3_mid">
  	  <div class="popArea3_mid_left">
       <!-- 相册列表呈现 -->
        <div id="galleria" style='display:none;'>
        
        <!-- 遍历显示本页的其他图片  -->
        <s:iterator value="photoListView" status="rowstatus">
            <img src="..<s:property value="photo_file" />" phId="<s:property value="photo_id" />" onerror="this.src='../newimages/backgroup.gif'"/>
         </s:iterator>
        </div>
       </div>

  <div class="popArea3_mid_right" style="position:relative">
  	<div id="PhotoSaveMsg" class="PhotoSaveMsg" style="display: none"><img src="../newimages/sidelayerimages/right.gif" width="16" height="15"/> 
	   	<span>提示信息</span>—<s:label id="inforeault" name="inforeault" ></s:label>
	</div> 
  	<div style=" height:10px;"></div>
   <!-- 相册文字信息 --> 
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
        <!-- 照片ID 隐藏显示 -->
          <td><s:hidden id="photo_id" name="photoView.photo_id" /></td>
          <td><s:hidden id="current_photo_falg" name="photoView.photo_falg" /></td>
          <td><s:hidden id="vehicle_vin" name="photoView.vehicle_vin" /></td>
          <input id="right" type="hidden" value="<s:property value="#session.perUrlList.contains('111_3_1_20_3')"/>"/>
        </tr>
        <!-- 车牌号（*） -->
        <tr>
            <th width="26%" height="26" align="left">车牌号</th>
            <td width="10%" align="left">：</td>
            <td id="vehicle_ln"  align="left"><s:property value="photoView.vehicle_ln" /></td>
        </tr>
        <tr>
          <th width="26%" height="26" align="left">摄像头</th>
          <td width="10%" align="left">：</td>
          <td id="chanle_code"  align="left">
          <s:if test="photoView.chanle_code==1">
          <span>整车</span>
          </s:if>
          <s:if test="photoView.chanle_code==2">
          <span>路况</span>
          </s:if>
          <s:if test="photoView.chanle_code==3">
          <span>车门</span>
          </s:if>
          <s:if test="photoView.chanle_code==4">
          <span>司机</span>
          </s:if>
          </td>
        </tr>
        <tr>
            <th width="26%" height="26" align="left">拍照人</th>
            <td width="10%" align="left">：</td>
            <td id="operate_user_name"  align="left"><s:property value="photoView.operate_user_name" /></td>
        </tr>
        <tr>
          <th width="26%" height="26" align="left">拍照时间</th>
          <td width="10%"align="left">：</td>
          <td id="photo_time" align="left" class="time"><s:property value="photoView.photo_time" /></td>
        </tr>
        <!-- 触发类型（*） -->
        <tr>
           <th width="26%" height="26" align="left">触发类型</th>
           <td width="10%" align="left">：</td>
           <td id="photo_event" align="left"><s:property value="photoView.photo_event" /></td>
         </tr>
         <s:if test="photoView.photo_falg == '0'">
         	<tr id="tr_hr"><td colspan="3"><hr></hr></td></tr> <!-- 分隔线 -->
         	<tr id="tr_falg">
            <th width="26%" height="26" align="left">状态</th>
            <td width="10%" align="left">：</td>
            <td id="photo_falg_text" align="left"></td>
         	</tr>
         	<tr id="tr_name">
            <th width="26%" height="26" align="left">操作人</th>
            <td width="10%" align="left">：</td>
            <td id="flag_user_name" align="left"><s:property value="photoView.reli_user_name" /></td>
         	</tr>
         	<tr id="tr_time">
            <th width="26%" height="26" align="left">操作时间</th>
            <td width="10%" align="left">：</td>
            <td id="flag_time" align="left"><s:property value="photoView.reli_time" /></td>
         	</tr> 
         </s:if>
         <s:else>
         	<tr id="tr_hr"><td colspan="3"><hr></hr></td></tr> <!-- 分隔线 -->
         	<tr id="tr_falg">
            <th width="26%" height="26" align="left">状态</th>
            <td width="10%" align="left">：</td>
            <td id="photo_falg_text" align="left"></td>
         	</tr>
         	<tr id="tr_name">
            <th width="26%" height="26" align="left">操作人</th>
            <td width="10%" align="left">：</td>
            <td id="flag_user_name" align="left"><s:property value="photoView.flag_user_name" /></td>
         	</tr>
         	<tr id="tr_time">
            <th width="26%" height="26" align="left">操作时间</th>
            <td width="10%" align="left">：</td>
            <td id="flag_time" align="left"><s:property value="photoView.flag_time" /></td>
         	</tr> 
         </s:else>
         <tr>
            <th width="26%" height="26" align="left">图片备注</th>
            <td width="10%" align="left">：</td>
            <td align="left">
            <!-- ''-未标记 0-解除超载 1-超载 -->
            <div id="photo_falg_checkbox" style="float:left; padding:2px 5px 0 0;display:none;">
				<s:checkbox id="photo_falg" name="photo_falg"></s:checkbox>
		  	</div>
		  	<div id="photo_falg_tip" style="float:left;display:none;">
            <label id="tip"></label>
            </div>
            </td>
          </tr>
        </table>
        <textarea id="photo_desc" class="popArea3_textarea"><s:property value="photoView.photo_desc" /></textarea>
        <!-- 保存按钮 -->
        <a href="#" class="btnbule2" onfocus="this.blur()" onclick="updatePhoto()">保存</a>
  </div>
</div>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/galleria/jquery.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/galleria/galleria-1.2.7.js'/>"></script>
<script type="text/javascript">

// Load the classic theme
Galleria.loadTheme('<s:url value='/scripts/galleria/galleria.classic.js'/>');

// Initialize Galleria
var gallery;
var imageListSize;
Galleria.run('#galleria', {
	extend: function() {
        gallery = this;
        imageListSize = this.getDataLength();
        if (parent.showLast) {
        	this._showLast = true;
        	parent.showLast = false;
        }
    },
    showIndex: parent.showLast ? parent.showIndex : 0
});
var right = "";
//进入相册的初始化方法
jQuery(function() {
	right = $("#right").val();
	var current_photo_falg = $("#current_photo_falg").val();
	if(current_photo_falg == '1'){//(以标记为超载 )
		$("#tr_hr").css("display","block");
		//状态
		$("#tr_falg").css("display","block");
		//操作人
		$("#tr_name").css("display","block");
		//操作时间
		$("#tr_time").css("display","block");
		$("#photo_falg_text").html("超载");
		if(right == 'true'){//具有解除标记权限
			$("#photo_falg_checkbox").css("display","block");
			$("#photo_falg_tip").css("display","block");
			$("#tip").html("解除标记");
		}else{//没有解除标记权限
			$("#photo_falg_checkbox").css("display","none");
			$("#photo_falg_tip").css("display","none");
		}
		//设置文本框高度
		$("#photo_desc").css("height","80px");
	}else if(current_photo_falg == '0'){//(以标记为解除超载)
		$("#tr_hr").css("display","block");
		//状态
		$("#tr_falg").css("display","block");
		//操作人
		$("#tr_name").css("display","block");
		//操作时间
		$("#tr_time").css("display","block");
		$("#photo_falg_text").html("解除超载");
		$("#photo_falg_checkbox").css("display","block");
		$("#photo_falg_tip").css("display","block");
		$("#tip").html('标记为超载');
		//设置文本框高度
		$("#photo_desc").css("height","80px");
	}else{
		$("#tr_hr").css("display","none");
		//状态
		$("#tr_falg").css("display","none");
		//操作人
		$("#tr_name").css("display","none");
		//操作时间
		$("#tr_time").css("display","none");
		$("#photo_falg_checkbox").css("display","block");
		$("#photo_falg_tip").css("display","block");
		$("#tip").html('标记为超载');
		//设置文本框高度
		$("#photo_desc").css("height","140px");
	}
	$("#galleria").css("display","block");
});

// 下一页
function nextPage(index) {
	if (index == imageListSize -1) {
		if ((parent.begin + parent.pageSize) >=  parent.pageCount - 1) {
			tishiShow("已经是最后一张照片！");
		} else {
			jQuery('.popArea3_mid').hide();
			parent.intoGalleriaPage(null, true);
		}
		/* var tName = parent.jQuery('#Pagination .next').attr("tagName");
		if (tName == 'SPAN') {
			tishiShow("已经是最后一张照片！");
		} else {
			jQuery('.popArea3_mid').hide();
			parent.jQuery('#Pagination .next').click();
			parent.autoView = true;
		} */
		return true;
	}
	return false;
}
// 上一页
function prevPage(index) {
	if (index == 0) {
		if (parent.begin ==  0) {
			tishiShow("已经是第一张照片！");
		} else {
			jQuery('.popArea3_mid').hide();
			parent.showLast = true;
			parent.intoGalleriaPage(null, false);
		}
		/* var tName = parent.jQuery('#Pagination .prev').attr("tagName");
		if (tName == 'SPAN') {
			tishiShow("已经是第一张照片！");
		} else {
			jQuery('.popArea3_mid').hide();
			parent.jQuery('#Pagination .prev').click();
			parent.autoView = true;
			parent.showLast = true;
		} */
		return true;
	}
	return false;
}
//该方法的触发方式已经添加到  galleria-1.2.7.js中
// “上一页” “下一页 ”  “点击缩略图” 时触发该方法
function getPhotoInfo(dataObj){
//通过ajax方式 根据photo_id查询照片信息 更新右边的文本信息
	$.ajax({
        type: "POST",
        dataType: "json",
        url: '<s:url value="/photoflexgrid/getPhotoInfo.shtml"/>',
        data: {phId:dataObj.phId},          
        success: function(data) {
        	var photoView = data.photoView;
        	
        	//更新照片ID
        	$("#photo_id").val(photoView.photo_id);
        	//设置当前照片的falg
        	$("#current_photo_falg").val(photoView.photo_falg);
        	//设置车辆vin
        	$("#vehicle_vin").val(photoView.vehicle_vin);
        	//document.getElementById("photo_id").value=photoView.photo_id;
            //vehicle_ln
            $("#vehicle_ln").html(photoView.vehicle_ln);
            //document.getElementById("vehicle_ln").innerText=photoView.vehicle_ln;
            //photo_event
            //document.getElementById("photo_event").innerText=photoView.photo_event;
            $("#photo_event").html(photoView.photo_event);
        	//document.getElementById("photo_time").innerText=photoView.photo_time;
        	$("#photo_time").html(photoView.photo_time);
        	//document.getElementById("chanle_code").innerText=photoInfo.chanle_code;
        	if(photoView.photo_falg == '0'){//如果是解除超载状态
        		//更新操作人
            	$("#flag_user_name").html(photoView.reli_user_name);
            	//更新操作时间
            	$("#flag_time").html(photoView.reli_time);
        	}else{
        		//更新操作人
            	$("#flag_user_name").html(photoView.flag_user_name);
            	//更新操作时间
            	$("#flag_time").html(photoView.flag_time);
        	}
        	
        	//通道号文本替换
        	var chanleCodeText = "";
        	if(photoView.chanle_code == 1){
        		chanleCodeText = '整车';
        	}else if(photoView.chanle_code == 2){
        		chanleCodeText = '路况';   		
        	}else if(photoView.chanle_code == 3){
        		chanleCodeText = '车门';
        	}else if(photoView.chanle_code == 4){
        		chanleCodeText = '司机';
        	}else{
        		chanleCodeText = '未知';	
        	}
        	
        	//document.getElementById("chanle_code").innerText=chanleCodeText;
        	$("#chanle_code").html(chanleCodeText);
        	//document.getElementById("photo_desc").value=photoView.photo_desc;
        	$("#photo_desc").val(photoView.photo_desc);
        	//设置复选框不选中
        	$("#photo_falg").attr("checked",false);
        	
        	if(photoView.photo_falg == '1'){//(以标记为超载 )
        		$("#tr_hr").css("display","block");
        		//状态
        		$("#tr_falg").css("display","block");
        		//操作人
        		$("#tr_name").css("display","block");
        		//操作时间
        		$("#tr_time").css("display","block");
        		$("#photo_falg_text").html("超载");
        		if(right == 'true'){//具有解除标记权限
        			$("#photo_falg_checkbox").css("display","block");
        			$("#photo_falg_tip").css("display","block");
        			$("#tip").html("解除标记");
        		}else{//没有解除标记权限
        			$("#photo_falg_checkbox").css("display","none");
        			$("#photo_falg_tip").css("display","none");
        		}
        		//设置文本框高度
        		$("#photo_desc").css("height","80px");
        	}else if(photoView.photo_falg == '0'){//(以标记为解除超载)
        		$("#tr_hr").css("display","block");
        		//状态
        		$("#tr_falg").css("display","block");
        		//操作人
        		$("#tr_name").css("display","block");
        		//操作时间
        		$("#tr_time").css("display","block");
        		$("#photo_falg_text").html("解除超载");
        		$("#photo_falg_checkbox").css("display","block");
        		$("#photo_falg_tip").css("display","block");
        		$("#tip").html('标记为超载');
        		//设置文本框高度
        		$("#photo_desc").css("height","80px");
        	}else{
        		$("#tr_hr").css("display","none");
        		//状态
        		$("#tr_falg").css("display","none");
        		//操作人
        		$("#tr_name").css("display","none");
        		//操作时间
        		$("#tr_time").css("display","none");
        		$("#photo_falg_checkbox").css("display","block");
        		$("#photo_falg_tip").css("display","block");
        		$("#tip").html('标记为超载');
        		//设置文本框高度
        		$("#photo_desc").css("height","140px");
        	}
        	
        	
        }
    });
}

function updatePhoto(){

	//获取照片ID 备注
	var photo_id = $("#photo_id").val();
	var photo_desc = $("#photo_desc").val();
	var vehicle_vin = $("#vehicle_vin").val();
	//增加过滤全角和半角空格
	photo_desc = trim(photo_desc);
	
	//备注内容长度限制
	if(photo_desc.length >100){
		tishiShow("备注不能超过100字！");
		return;
	}
	var current_photo_falg = $("#current_photo_falg").val();
	//var photo_falg_obj = $("#photo_falg");
	var photo_falg = '';
	
	//根据选择设置falg
	if(current_photo_falg == '1'){//超载
		if($("#photo_falg").attr("checked") == "checked"){//选中解锁
			photo_falg = '0';
		}else{
			photo_falg = '1';
		}
	}else if(current_photo_falg == '0'){//解除状态
		if($("#photo_falg").attr("checked") == "checked"){//标记超载
			photo_falg = '1';
		}else{
			photo_falg = '0';
		}
	}else{//未标记状态 
		if($("#photo_falg").attr("checked") == "checked"){//标记超载
			photo_falg = '1'; //超载
		}else{
			photo_falg = '';//未标记状态
		}
	}
	
	
	//更新数据库信息 根据更新后的结果 更新右边的文本信息
	$.ajax({
        type: "POST",
        dataType: "json",
        url: '<s:url value="/photoflexgrid/updatePhotoInfo.shtml"/>',
        data: {photo_id:photo_id,photo_falg:photo_falg,photo_desc:photo_desc,vehicle_vin:vehicle_vin},          
        success: function(data) {
			
        	tishiShow("照片信息更新成功！");
        	//获取更新后的照片信息
        	//var photoInfo = data.photoInfo;
        	var photoView = data.photoView;
        	//设置复选框不选中
        	$("#photo_falg").attr("checked",false);
        	//var photoView = photoInfo;
        	//更新照片ID
        	$("#photo_id").val(photoView.photo_id);
        	//拍照时间
        	$("#photo_time").html(photoView.photo_time);
        	//设置车辆VIN
        	$("#vehicle_vin").val(photoView.vehicle_vin);
        	//修改替换文本
        	//通道号文本替换
        	var chanleCodeText = "";
        	if(photoView.chanle_code == 1){
        		chanleCodeText = '整车';
        	}else if(photoView.chanle_code == 2){
        		chanleCodeText = '路况';   		
        	}else if(photoView.chanle_code == 3){
        		chanleCodeText = '车门';
        	}else if(photoView.chanle_code == 4){
        		chanleCodeText = '司机';
        	}else{
        		chanleCodeText = '未知';	
        	}
        	
        	//document.getElementById("chanle_code").innerText=chanleCodeText;
        	$("#chanle_code").html(chanleCodeText);
        	//document.getElementById("photo_desc").value=photoView.photo_desc;
        	$("#photo_desc").val(photoView.photo_desc);
        	
        	$("#current_photo_falg").val(photoView.photo_falg);
        	var current_photo_falg = $("#current_photo_falg").val();
        	if(current_photo_falg == '1'){//(以标记为超载 )
        		$("#tr_hr").css("display","block");
        		//状态
        		$("#tr_falg").css("display","block");
        		//操作人
        		$("#tr_name").css("display","block");
        		//操作时间
        		$("#tr_time").css("display","block");
        		$("#photo_falg_text").html("超载");
        		if(right == 'true'){//具有解除标记权限
        			$("#photo_falg_checkbox").css("display","block");
        			$("#photo_falg_tip").css("display","block");
        			$("#tip").html("解除标记");
        		}else{//没有解除标记权限
        			$("#photo_falg_checkbox").css("display","none");
        			$("#photo_falg_tip").css("display","none");
        		}
        		//设置文本框高度
        		$("#photo_desc").css("height","80px");
        	}else if(current_photo_falg == '0'){//(以标记为解除超载)
        		$("#tr_hr").css("display","block");
        		//状态
        		$("#tr_falg").css("display","block");
        		//操作人
        		$("#tr_name").css("display","block");
        		//操作时间
        		$("#tr_time").css("display","block");
        		$("#photo_falg_text").html("解除超载");
        		$("#photo_falg_checkbox").css("display","block");
        		$("#photo_falg_tip").css("display","block");
        		$("#tip").html('标记为超载');
        		//设置文本框高度
        		$("#photo_desc").css("height","80px");
        	}else{
        		$("#tr_hr").css("display","none");
        		//状态
        		$("#tr_falg").css("display","none");
        		//操作人
        		$("#tr_name").css("display","none");
        		//操作时间
        		$("#tr_time").css("display","none");
        		$("#photo_falg_checkbox").css("display","block");
        		$("#photo_falg_tip").css("display","block");
        		$("#tip").html('标记为超载');
        		//设置文本框高度
        		$("#photo_desc").css("height","140px");
        	}
        	
        	
        	
            //操作时间
            //$("#flag_time").html(photoView.flag_time);
            if(photoView.photo_falg == '0'){//如果是解除超载状态
        		//更新操作人为解除操作人
            	//$("#flag_user_name").html(photoView.reil_user_name);
            	document.getElementById("flag_user_name").innerText=photoView.reli_user_name;
            	//更新操作时间为解除操作时间
            	$("#flag_time").html(photoView.reli_time);
        	}else{
        		//更新操作人
            	//$("#flag_user_name").html(photoView.flag_user_name);
        		//操作用户  特殊字符
        		document.getElementById("flag_user_name").innerText=photoView.flag_user_name;
            	//更新操作时间
            	$("#flag_time").html(photoView.flag_time);
        	}
            
        }
    });
	
}

//浮动框隐藏显示
function hideshowresultDiv(flag){
	if(flag==1){
		jQuery('#PhotoSaveMsg').css('display','none');
	}else if(flag==0){
		jQuery('#PhotoSaveMsg').css('display','block');
	}
}

//显示提示框
function tishiShow(info){
	
	hideshowresultDiv(0);
	document.getElementById("inforeault").innerHTML=info;
	tishiHide();
}

//2秒钟隐藏提示框
function tishiHide(){
	window.setTimeout("hideshowresultDiv(1)",2000);
}
//过滤全角和半角空格
function trim(v){
	return v.replace(/(^[\s　]*)|([\s　]*$)/g, ""); 
}
</script>
</body>
</html>