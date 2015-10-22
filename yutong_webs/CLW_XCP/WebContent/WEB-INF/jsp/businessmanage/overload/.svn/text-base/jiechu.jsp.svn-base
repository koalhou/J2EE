<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
<style type="text/css">
.tiplayer {
	position:absolute;
	bottom:1px;
	width:260px;
	line-height:15px;
	z-index:1;
	background:#FFFFE1;
	border:1px solid #ccc;
	color:#147800;
	padding-top:3px;
	padding-left:10px;
	padding-bottom:3px;
}
.tiplayer span{ font-weight:bold;}
</style>
</head>
<body>
   <s:hidden id="vin" name="overload.vehicle_vin"/>
   <s:hidden id="flag" name="overload.photo_flag"/>  
   <s:hidden id="photoid" name="overload.photo_id"/> 
   <s:hidden id="phototime" name="overload.photo_time"/>
<div style="width: 380px;height: 170px;"> 
<div style="padding-left: 50px;color: red;margin-bottom: 10px;margin-top: 10px;"><img src="../images/overload_ico.png" align="absmiddle"></img>&nbsp;&nbsp;
<span style="margin-left: -5px;"><s:if test="0==#request.overload.photo_flag">确定要解除标记吗？</s:if><s:else>确定要标记超载吗？</s:else></span></div>
<div style="float:left;margin-top: 30px;">备注：</div>
<div style="float: left;margin-bottom: 10px;"><textarea id="beizu" cols="38" rows="5"></textarea></div>
<div style="float: right;padding-right: 20px;"><a href="#" onclick="quxiao()" class="sbutton">取消</a></div>
<div style="float: right;"><a href="#" onclick="save()" class="sbutton">确定</a></div>
</div>
<div id="Layer1"  class="tiplayer" style="display: none"><img id="tishitu" align="absmiddle" src="../newimages/sidelayerimages/ico_alarm.png" width="16" height="15" /> 
    <span>提示信息</span>——<s:label id="inforeault" name="inforeault" ></s:label>
</div>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript">
function quxiao(){
	art.dialog.close();
}

function save(){
	var desc =trim(jQuery("#beizu").val());
	if(desc.length>100){
		jQuery("#tishitu").attr('src','../newimages/sidelayerimages/ico_alarm.png');
		tishiShow("输入长度请小于100个字符！");
		tishiHide();
		return;
	}
	 jQuery.ajax({
			type: 'POST', 
			url: '<s:url value="/overloadflexgrid/updatephoto.shtml"/>',
			async:false,
			data: {
		         "overload.photo_id":jQuery("#photoid").val(),
		         "overload.vehicle_vin":jQuery("#vin").val(),
		         "overload.photo_flag":jQuery("#flag").val(),
		         "overload.photo_time":jQuery("#phototime").val(),
		         "overload.photo_desc":desc
			},
			success: function(data) {
			 if(data.message=="0"){
				 jQuery("#tishitu").attr('src','../newimages/sidelayerimages/right.gif');
				 tishiShow("解除成功！");
				 tishiHide();
				 window.setTimeout("quxiao()",500);
				 //art.dialog.parent.qinli();
				 art.dialog.parent.searchList();
			 }
			 if(data.message=="1"){
				 jQuery("#tishitu").attr('src','../newimages/sidelayerimages/right.gif');
				 tishiShow("标记成功！");
				 tishiHide();
				 window.setTimeout("quxiao()",500);
				 //art.dialog.parent.qinli();
				 art.dialog.parent.searchList();
			 }
			 if(data.message=="2"){
				 jQuery("#tishitu").attr('src','../newimages/sidelayerimages/ico_alarm.png');
				 tishiShow("处理失败！");
				 tishiHide();
				 window.setTimeout("quxiao()",500);
				 //art.dialog.parent.qinli();
				 art.dialog.parent.searchList();
			 }
			}
		});
	
	//window.setTimeout("quxiao()",500);
}


function tishiShow(info){
	hideshowresultDiv(0);
	document.getElementById("inforeault").innerHTML=info;
}

function hideshowresultDiv(flag){
	//alert(111);
	if(flag==1){
		jQuery('#Layer1').css('display','none');
	}
	else if(flag==0){
		jQuery('#Layer1').css('display','block');
	}	
}

function tishiHide(){
	window.setTimeout("hideshowresultDiv(1)",1000);
}

function trim(v){
	return v.replace(/(^[\s　]*)|([\s　]*$)/g, ""); 
}
</script>
</body>
</html>
