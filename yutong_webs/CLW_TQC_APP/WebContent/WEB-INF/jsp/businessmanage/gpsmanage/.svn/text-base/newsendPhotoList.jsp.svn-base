<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 中文注释 -->
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/gpspostion.css" />" />

<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>


<title>批量抓拍</title>

<style type="text/css">
.radio_c{ width:22px; margin-top:2px; float:left; text-align: right;}
.radio_txt{ width:35px; float:left;}
</style> 

<script type="text/javascript">  

var mereg=/^\d+$/;

var recodeInfo;
var index=0;
//提示信息隐藏
var ral_resultShowflash= 2000;


function postPhoto(){
	recodeInfo = null;
	index = 0;


	var vinlist = window.parent.getCheckedCars();
	//alert(vinlist.length);
	var vin = "";
	if(vinlist.length > 0){
		for(var i=0; i<vinlist.length; i++) {
	    	
    		vin += vinlist[i]+",";	
    	}
	}
	else{
		tishiShow("请选择车辆！");
		return ;
	}
	
	
	
	//var vin = document.getElementById("vin").value;
	//var routeway = document.getElementById("routeway").value;
	var userid = document.getElementById('optionUserid').value;
	//alert(Pixel+","+Brightness+","+Chroma+","+Contrast+","+Saturation+","+Resolution);
	var routeway2 = document.getElementsByName("channel");
	//alert(routeway2.length);

	var wayNo = "";

	if(routeway2.length > 1){
		for(var j =0; j < routeway2.length; j++){

			if(routeway2[j].type=="checkbox"){
				if(routeway2[j].checked){
					//alert(routeway2[j].value);
					wayNo += routeway2[j].value+",";
					
				}
			}
		}
	}
	else{
		if(routeway2.checked){
			//alert(routeway2[j].value);
			wayNo += routeway2.value+",";
			
		}
	}
	
	if(wayNo != ""){
		GPSDwr.postSendPhotoList(vin,wayNo,1,5,userid,126,65,65,126,postPhoto_CallBack);
	}
	else{
		tishiShow("请选择拍照通道！");
	//alert("请选择拍照通道!");

	}
	
	//alert(" vin:"+vin+"\n routeway:"+routeway+"\n userid:"+userid);
		function postPhoto_CallBack(data){
			//alert(data);
			//var str = data;
			var result = "";
			var sccess = "";

			var error  = "";

			var wait = "";

			

			recodeInfo =data.split(",");
			forRecodeInfo();
		}
}

function forRecodeInfo(){
	if(recodeInfo != null){
		if(index < recodeInfo.length){
			if(recodeInfo[index]!=null && recodeInfo[index] != "" && recodeInfo[index]!= "undefined"){
				
				var vinfo = recodeInfo[index].split("-");
				
				if(vinfo[2]==0){
					tishiShow("车辆"+vinfo[0]+",通道"+waynorecode(vinfo[1])+"成功！");
					//document.getElementById("inforeault").innerHTML="车辆"+vinfo[0]+",通道"+vinfo[1]+"成功!";
				}
				else if(vinfo[2]== "8000"){
					//alert(vinfo[2]);
					tishiShow("车辆"+vinfo[0]+",通道"+waynorecode(vinfo[1])+"不在线！");
					//document.getElementById("inforeault").innerHTML="车辆"+vinfo[0]+",通道"+vinfo[1]+"失败!";
				}
				else if(vinfo[2]== "8001"){
					tishiShow("车辆"+vinfo[0]+",通道"+waynorecode(vinfo[1])+"已熄火！");
				}
				else{
					tishiShow("车辆"+vinfo[0]+",通道"+waynorecode(vinfo[1])+"失败！");
				}
			}
			index++;
			 window.setTimeout("forRecodeInfo()",250);
		}
		else if(index == recodeInfo.length){
			window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);
			//document.getElementById("inforeault").innerHTML="";
			window.parent.sendphotolist.style.display = "none";
			
		}
	}
}

function hideshowresultDiv(flag){
	
	if(flag==1){
		document.getElementById("Layer1").style.display = "none";
		
	}
	else if(flag==0){
		document.getElementById("Layer1").style.display = "block";
	}
}


function tishiShow(info){
	
	hideshowresultDiv(0);
	document.getElementById("inforeault").innerHTML=info;
	tishiHide();
	
}
function tishiHide(){
	window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);
}

function waynorecode(no){
	if(no == 4){
		return "驾驶员";
	}else if(no == 1){
		return "整车";
	}else if(no == 3){
		return "车门";
	}else if(no == 2){
		return "路况";
	}
}
</script> 
  
</head>   
<body >   
<input type="hidden" id="optionUserid" name="optionUserid" value="<s:property value='#session.adminProfile.userID'/>"/>
<!--<input type="hidden" id="vin" name="vin" value="<s:property value='vin'/>"/>-->


	  <div class="car-send-meg">
	  <table width="230px" border="0" cellpadding="0" cellspacing="0">
	  	<tr>
	      	  <td width="24%" >
	      	  	<div class="radio_c"><input name="channel" type="checkbox" value="1" checked="checked"/></div>
	      	  	<div class="radio_txt">整车</div>
	      	  </td>
	          <td width="24%" >
	          	<div class="radio_c"><input name="channel" type="checkbox" value="2" checked="checked"/></div>
	          	<div class="radio_txt">路况</div>
	          </td>
	          <td width="24%" >
	          	<div class="radio_c"><input name="channel" type="checkbox" value="3"/></div>
	          	<div class="radio_txt">车门</div>
	          </td>
	          <td>
	          	<div class="radio_c"><input name="channel" type="checkbox" value="4"  /></div>
	          	<div class="radio_txt">司机</div></td>
	      </tr>
	  </table>
	  <a href="#" class="btnbule" onfocus="this.blur()" onclick="postPhoto()">拍照</a>
	  </div>
	<div id="Layer1" class="LayerPhotoList" style="display: none;"><img src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
	   	<span><s:label id="inforeault" name="inforeault" ></s:label></span>
	</div>
</body>
</html>