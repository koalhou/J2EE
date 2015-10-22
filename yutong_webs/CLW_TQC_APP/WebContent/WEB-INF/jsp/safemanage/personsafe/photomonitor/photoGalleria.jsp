<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title>图片详情</title>
<!-- 照片列表布局样式  -->
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/galleria.css'/>" />
<!--  
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/pop.css'/>"> -->
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/photo.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
</head>
<body>
  	<div class="popArea3_mid">
  	  <div class="popArea3_mid_left">
       <!-- 相册列表呈现 -->
        <div id="galleria">
        <img src="..<s:property value="photoView.photo_file" />" phId="<s:property value="photoView.photo_id"/>" onerror="this.src='../newimages/backgroup.gif'"/>
        
        <!-- 遍历显示本页的其他图片  -->
        <s:iterator value="photoListView" status="rowstatus">
            <img src="..<s:property value="photo_file" />" phId="<s:property value="photo_id" />" onerror="this.src='../newimages/backgroup.gif'"/>
         </s:iterator>
        </div>
       </div>
  <div class="popArea3_mid_right" style="position:relative">
  	<div id="PhotoSaveMsg" class="PhotoSaveMsg" style="display: none"><img src="../newimages/sidelayerimages/right.gif" width="16" height="15"/> 
	   	<span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
	</div>
  	<div style=" height:10px;"></div>
   <!-- 相册文字信息 --> 
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
        <!-- 照片ID 隐藏显示 -->
          <td><s:hidden id="photo_id" name="photoView.photo_id" /></td>
        </tr>
        <!-- 车牌号（*） -->
        <tr>
            <th width="26%" height="28" align="left">班车号</th>
            <td width="10%" align="left">：</td>
            <td id="vehicle_code"  align="left"><s:property value="photoView.vehicle_code" /></td>
        </tr>
        <tr>
            <th width="26%" height="28" align="left">车牌号</th>
            <td width="10%" align="left">：</td>
            <td id="vehicle_ln"  align="left"><s:property value="photoView.vehicle_ln" /></td>
        </tr>
        <tr>
          <th width="26%" height="28" align="left">摄像头</th>
          <td width="10%" align="left">：</td>
          <td id="chanle_code"  align="left">
          <s:if test="photoView.chanle_code==1">
          <span class="position2">整车</span>
          </s:if>
          <s:if test="photoView.chanle_code==2">
          <span class="position2">路况</span>
          </s:if>
          <s:if test="photoView.chanle_code==3">
          <span class="position2">车门</span>
          </s:if>
          <s:if test="photoView.chanle_code==4">
          <span class="position2">驾驶员</span>
          </s:if>
          </td>
        </tr>
        <tr>
          <th height="28" align="left">拍照时间</th>
          <td align="left">：</td>
          <td id="photo_time" align="left" class="time"><s:property value="photoView.photo_time" /></td>
        </tr>
        <!-- 触发类型（*） -->
        <tr>
            <th height="28" align="left">触发类型</th>
            <td align="left">：</td>
            <td id="photo_event" width="66%" align="left"><s:property value="photoView.photo_event" /></td>
         </tr>
         <tr>
            <th height="28" align="left">图片备注</th>
            <td align="left">：</td>
            <td align="left">
            <!-- 0-不超载 1-超载 --> 
            <div style="float:left; padding:2px 5px 0 0;">
          	<s:if test="photoView.photo_falg==1">
          	<s:checkbox id="photo_falg" name="photo_falg" value="true" disabled="true" ></s:checkbox>
		  	</s:if>
		  	<%--<s:else>
		    <s:checkbox id="photo_falg" name="photo_falg"></s:checkbox>
		  	</s:else>--%>
		  	</div>
		  	<%--<div style="float:left;">
            <label>标记为超载</label>
            </div>--%>
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
Galleria.run('#galleria');

//该方法的触发方式已经添加到  galleria-1.2.7.js中
// “上一页” “下一页 ”  “点击缩略图” 时触发该方法
function getPhotoInfo(dataObj){
	//alert(dataObj.phId);
//document.getElementById("phId").innerHTML = dataObj.phId;
//通过ajax方式 根据photo_id查询照片信息 更新右边的文本信息
	$.ajax({
        type: "POST",
        dataType: "json",
        url: '<s:url value="/photoflexgrid/getPhotoInfo.shtml"/>',
        data: {phId:dataObj.phId},          
        success: function(data) {
        	
        	var photoInfo = data.photoInfo;
        	
        	//alert(photoInfo.photo_falg+"|"+photoInfo.photo_id+"|"+photoInfo.photo_time+"|"+photoInfo.chanle_code+"|");
        	
        	//更新照片ID
        	document.getElementById("photo_id").value=photoInfo.photo_id;
            //vehicle_ln
            document.getElementById("vehicle_code").innerText=photoInfo.vehicle_code;
            document.getElementById("vehicle_ln").innerText=photoInfo.vehicle_ln;
            //photo_event
            document.getElementById("photo_event").innerText=photoInfo.photo_event;
        	document.getElementById("photo_time").innerText=photoInfo.photo_time;
        	//document.getElementById("chanle_code").innerText=photoInfo.chanle_code;
        	//通道号文本替换
        	var chanleCodeText = "";
        	if(photoInfo.chanle_code == 1){
        		chanleCodeText = '整车';
        	}else if(photoInfo.chanle_code == 2){
        		chanleCodeText = '路况';   		
        	}else if(photoInfo.chanle_code == 3){
        		chanleCodeText = '车门';
        	}else if(photoInfo.chanle_code == 4){
        		chanleCodeText = '驾驶员';
        	}else{
        		chanleCodeText = '未知';	
        	}
        	
        	document.getElementById("chanle_code").innerText=chanleCodeText;
        	document.getElementById("photo_desc").value=photoInfo.photo_desc;
        	
        	//document.getElementById("phTime").innerHTML = dataObj.phTime;
        	//超载设置  0-不超载(可以操作 )  1-超载(选中不可操作)
        	if(photoInfo.photo_falg == 1){
        		document.getElementById("photo_falg").disabled = true;
        		document.getElementById("photo_falg").checked = true;    		
        	}else{     		
        		document.getElementById("photo_falg").disabled = false;
        		document.getElementById("photo_falg").checked = false;      		
        	}
        	
        }
    });
}

function updatePhoto(){

	//获取照片ID 备注
	var photo_id = document.getElementById("photo_id").value;
	var photo_desc = document.getElementById("photo_desc").value;
	photo_desc = $.trim(photo_desc);
	
	//备注内容长度限制
	if(photo_desc.length >100){
		tishiShow("备注不能超过100字！");
		return;
	}
	
	//不超载
	var photo_falg = "0";
	/*var photo_falg_obj = document.getElementById("photo_falg");	
	if(photo_falg_obj.checked){
		photo_falg = "1"; //超载
	}*/
	
	//alert(photo_falg);
	
	//更新数据库信息 根据更新后的结果 更新右边的文本信息
	$.ajax({
        type: "POST",
        dataType: "json",
        url: '<s:url value="/photoflexgrid/updatePhotoInfo.shtml"/>',
        data: {photo_id:photo_id,photo_falg:photo_falg,photo_desc:photo_desc},          
        success: function(data) {
			
        	tishiShow("照片信息更新成功！");
        	var photoInfo = data.photoInfo;
        	
        	//alert(photoInfo.photo_falg+"|"+photoInfo.photo_id+"|"+photoInfo.photo_time+"|"+photoInfo.chanle_code+"|");
        	
        	//更新照片ID
        	document.getElementById("photo_id").value=photoInfo.photo_id;
        	document.getElementById("photo_time").innerText=photoInfo.photo_time;
        	//修改替换文本
        	//document.getElementById("chanle_code").innerText=photoInfo.chanle_code;
        	//通道号文本替换
        	var chanleCodeText = "";
        	if(photoInfo.chanle_code == 1){
        		chanleCodeText = '整车';
        	}else if(photoInfo.chanle_code == 2){
        		chanleCodeText = '路况';   		
        	}else if(photoInfo.chanle_code == 3){
        		chanleCodeText = '车门';
        	}else if(photoInfo.chanle_code == 4){
        		chanleCodeText = '驾驶员';
        	}else{
        		chanleCodeText = '未知';	
        	}
        	document.getElementById("chanle_code").innerText=chanleCodeText;       	
        	document.getElementById("photo_desc").value=photoInfo.photo_desc;
        	
        	//document.getElementById("phTime").innerHTML = dataObj.phTime;
        	//超载设置  0-不超载(可以操作 )  1-超载(选中不可操作)
        	if(photoInfo.photo_falg == 1){
        		document.getElementById("photo_falg").disabled = true;
        		document.getElementById("photo_falg").checked = true;    		
        	}else{     		
        		document.getElementById("photo_falg").disabled = false;
        		document.getElementById("photo_falg").checked = false;      		
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
</script>
</body>
</html>