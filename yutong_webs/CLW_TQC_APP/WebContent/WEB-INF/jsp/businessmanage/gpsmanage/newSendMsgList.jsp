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
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<title>批量信息调度</title>

<style type="text/css">
.radio_c{ width:19px; margin-top:2px; float:left;}
.radio_txt{ width:85px; float:left;}
</style> 

<script type="text/javascript">  

var recodeInfo;
var index=0;

//提示信息隐藏
var ral_resultShowflash= 2000;


function mapInit() {
	getInfoTitle();
}

function getInfoTitle(){

	var entid = document.getElementById("enterprise_id").value;
	//alert(entid);
	GPSDwr.getEnterInfoList(entid,getEnterInfoList_callback);

	function getEnterInfoList_callback(date){

		var obj = document.getElementById("yusheInfo");
		//var yusheObj = document.getElementById('yusheInfo');
		//obj.options=null;
		
		for(var i = obj.options.length; i >= 0; i--){
			obj.remove(i);
		}
		
		if(date != null){
			obj.add(new Option("","0"));
			for(var i = 0; i < date.length; i++){
				var til=textApp.clearEnter(date[i].INFO_TITLE), val=date[i].INFO_ID;
				obj.add(new Option(til,val));
				
				
			}
			//textareaonkeyup(1);
		}

	}
	
}
function postMsg(){
	index=0;
	recodeInfo=null;

	var vinlist = window.parent.getCheckedCars();
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
	//alert(vin);
	var msg = document.getElementById("msg").value;
	var userid = document.getElementById('optionUserid').value;

	var yusheObj = document.getElementById('yusheInfo');
	var yusheInfo = yusheObj.value;

	var sendmsg = "";
	if(msg != null && msg != "" && msg != "undefined"){
		sendmsg = msg;
	}
	
	if(sendmsg == null || sendmsg.trim() == ""){
		tishiShow("请填写或选择消息内容！");
		//alert("请填写或选择消息内容!");
		return;
	}

	if(sendmsg.length >64){
		tishiShow("输入消息长度不能大于64个字！");
		//alert("输入消息长度不能大于32个字!");
		return;
	}

	//var typevalue = setInfoType();
	if(document.getElementById("saveinfo").checked){
		//alert(document.getElementById("saveinfo").checked);

		addInfoTitle();
	}
	else{
		var typevalue = setInfoType();
		GPSDwr.postSendMsgS(vin,sendmsg,userid,typevalue,postMsg_CallBack);
	}	
	
	

	
}
function postMsg_CallBack(data){
	//alert(data.length); // intreal = window.setTimeout("AscUpdateLine()",settime);
	var strs = data.split(",");
	recodeInfo = strs;
	forRecodeInfo();
}
function addInfoTitle(){
	var entid = document.getElementById("enterprise_id").value;
	var userid = document.getElementById("optionUserid").value;
	var msg = document.getElementById("msg").value.trim(); 
//alert(msg);
	if(msg.length > 64){
		tishiShow("消息内容不能大于64个字！");
		//hideshowresultDiv(0);
		//document.getElementById("inforeault").innerHTML="消息内容不能大于32个字";
		return;
	}
	
	if(msg.trim() == null || msg.trim() == ""){
		tishiShow("请输入要保存的预设消息！");
		//alert("请输入要保存的预设消息!");
		return ;
	}

	if(confirm("你确认要保存这条信息为预设消息吗？")){
		GPSDwr.addEnterInfo(entid,userid,msg.trim(),addEnterInfo_callback);
	}
	else{
		return ;
	}
	
	

	function addEnterInfo_callback(date){
		if(date==0){
			tishiShow("信息保存成功！");
			//hideshowresultDiv(0);
			//document.getElementById("inforeault").innerHTML="信息保存成功";

			getInfoTitle();
		}
		else if(date == 2){
			tishiShow("信息已经存在！");
			//hideshowresultDiv(0);
			//document.getElementById("inforeault").innerHTML="信息已经存在";
		}
		else{
			tishiShow("信息保存失败！");
			//hideshowresultDiv(0);
			//document.getElementById("inforeault").innerHTML="信息保存失败";
		}
		//window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);

		//下发指令
		var vinlist = window.parent.getCheckedCars();
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
		//var vin = document.getElementById("vin").value;alert(123);
		var msg = document.getElementById("msg").value;
		var userid = document.getElementById('optionUserid').value;
		var yusheObj = document.getElementById('yusheInfo');
		var yusheInfo = yusheObj.value;
		
		var sendmsg = "";
		if(msg != null && msg != "" && msg != "undefined"){
			sendmsg = msg;
		}
		if(sendmsg == null || sendmsg.trim() == ""){
			tishiShow("请填写或选择消息内容！");
			//alert("请填写或选择消息内容!");
			return;
		}
		if(sendmsg.length >64){
			tishiShow("输入消息长度不能大于64个字！");
			//alert("输入消息长度不能大于32个字!");
			return;
		}
		var typevalue = setInfoType();
		//alert(123);
		GPSDwr.postSendMsgS(vin,sendmsg,userid,typevalue,postMsg_CallBack);
		//下发指令结束
	}
	
}


//组织消息类型码
function setInfoType(){
	//7 = 普通消息，
	//6 = 禁用，
	//5 = 提示/告警消息，
	//4= 打印消息,
	//3=紧急，
	//2=终端显示器显示，
	//1=终端TTS播读，
	//0=广告屏显示

	var setinfo = "0000";
	var setinfo0 = "0";
	var setinfo1 = "0";
	var setinfo2 = "0";
	var setinfo3 = "0";

	var sumvalue = 0;
	
	var InfoTypes = document.getElementsByName("InfoType");
	//alert(InfoTypes.length);
	for(var i = 0; i < InfoTypes.length; i++){
		if(InfoTypes[i].type=="checkbox"){
			//alert(InfoTypes[i].value);
			if(InfoTypes[i].checked){
				
				sumvalue=sumvalue+1;
				if(InfoTypes[i].value == "0"){
					setinfo0 ="1";
				}
				if(InfoTypes[i].value == "1"){
					setinfo1 ="1";
				}
				if(InfoTypes[i].value == "2"){
					setinfo2 ="1";
				}
				if(InfoTypes[i].value == "3"){
					setinfo3 ="1";
				}
			}
		}	
	}
	//alert(sumvalue);
	if(sumvalue >0){
		//alert(setinfo0+setinfo1+setinfo2+setinfo3+setinfo);
		return setinfo0+setinfo1+setinfo2+setinfo3+setinfo;
	}
	else{
		//alert("默认选择'终端显示器显示'与'语音播报'!");
		tishiShow("默认选择'终端显示器显示'与'语音播报'！");
		return "01100000";
	}

	
	
}
function onchangeOption(){
	var yusheObj = document.getElementById('yusheInfo');
	var yusheInfo = yusheObj.value;
	
	var entid = document.getElementById("enterprise_id").value;
	GPSDwr.getEnterInfoOne(yusheInfo,getEnterInfoList_callback);

	function getEnterInfoList_callback(date){

		var obj = document.getElementById("yusheInfo");

		if(date != null){
			
			document.getElementById("msg").value = date.INFO_REMARK;
			gbcount();
		}
	}
}
function forRecodeInfo(){
	if(recodeInfo != null){
		if(index < recodeInfo.length){
			if(recodeInfo[index]!=null && recodeInfo[index] != "" && recodeInfo[index]!= "undefined"){
				
				//hideshowresultDiv(0);
				var vinfo = recodeInfo[index].split("-");
				if(vinfo[1]==0){
					//alert(vinfo[0]);
					tishiShow(vinfo[0]+",成功！");
					//document.getElementById("inforeault").innerHTML=vinfo[0]+"成功!";
				}
				else if(vinfo[2]== "8000"){
					//alert(vinfo[2]);
					tishiShow(vinfo[0]+",不在线！");
					//document.getElementById("inforeault").innerHTML="车辆"+vinfo[0]+",通道"+vinfo[1]+"失败!";
				}
				else if(vinfo[2]== "8001"){
					tishiShow(vinfo[0]+",已熄火！");
				}
				else{
					tishiShow(vinfo[0]+",失败！");
				}
			}
			index++;
			 window.setTimeout("forRecodeInfo()",250);
		}
		else if(index == recodeInfo.length){
			//window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);
			
			window.parent.sendMsglist.style.display = "none";
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

function deleteAndreturn(){
	
	deletenfoTitle();

}
function deletenfoTitle(){

	var val = document.getElementById('yusheInfo').value;
	//alert(val);

	if(val < 6 && val > 0){
		tishiShow("系统级预设消息不能删除！");
		//alert("系统级预设消息不能删除!");
		
	}else if(val ==0){
		tishiShow("请选择要删除预设消息！");
		//alert("请选择要删除预设消息!");
	}
	else{
		if(confirm("你确定要删除这条预设消息吗?")){
			GPSDwr.deleteEnterInfo(val,deleteEnterInfo_callback);
		}
		else{
			return ;
		}
	}

	function deleteEnterInfo_callback(date){
		//alert(date);
		if(date > 0){
			tishiShow("删除成功！");
			//hideshowresultDiv(0);
			//document.getElementById("inforeault").innerHTML="删除成功!";
			getInfoTitle();
			document.getElementById("msg").value = "";
		}
		else{
			tishiShow("删除 失败！");
			//hideshowresultDiv(0);
			//document.getElementById("inforeault").innerHTML="删除 失败!";
		}
		//window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);

	}
}

function checktext(text)
{
    allValid = true;
    for (i = 0;  i < text.length;  i++)
    {
      if (text.charAt(i) != " ")
      {
        allValid = false;
        break;
      }
    }
	return allValid;
}

function gbcount()
{
  var max = 64;
  //max = total.value;
  if (document.getElementById('msg').value.length > max) {

	  
	  document.getElementById("infocount").innerHTML = 0;
	  document.getElementById('msg').value=document.getElementById('msg').value.substring(0,max);
	  

	  tishiShow("输入消息长度不能大于64个字！");
  }
  else {
	  var con = max-document.getElementById('msg').value.length;
	  document.getElementById('infocount').innerHTML=con;
  }
}
</script>
 
</head>   
<body onload="mapInit()">  
<input type="hidden"  id="enterprise_id" name="enterprise_id" value="<s:property value='#session.adminProfile.entiID'/>" /> 
<input type="hidden" id="optionUserid" name="optionUserid" value="<s:property value='#session.adminProfile.userID'/>"/>
<!--<input type="hidden" id="vin" name="vin" value="<s:property value='vin'/>"/>-->
	
    <div class="car-send-snap">
    <table width="100%" border="0" cellpadding="0" cellspacing="3" >
    	<tr>
        	<td>
        	<div class="radio_c"><input name="InfoType" type="checkbox" id="3"  value="3"/></div>
        	<div class="radio_txt">紧急</div>
        	</td>
          <td>
            <div class="radio_c"><input name="InfoType" type="checkbox" id="1" checked="checked" value="1"/></div>
            <div class="radio_txt">语音播报</div>
          </td>
        </tr>
        <tr>
            <!-- <td>
            <div class="radio_c"><input name="InfoType" type="checkbox" id="2"  value="0"/></div>
            <div class="radio_txt">车载屏</div>
            </td> -->
          <td>
          <div class="radio_c"><input name="InfoType" type="checkbox" id="4" checked="checked"  value="2"/></div>
            <div class="radio_txt">终端显示器</div></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="2">
            <select name="yusheInfo" id="yusheInfo" class="selected" size="1" style="float: left;width: 140px" onchange="onchangeOption()" ></select>
            <a id="deleteInfoTextBtn" class ="sbutton" style="display: inline-block;float: left;margin-top:0px;height: 17px;line-height: 17px" onclick="deleteAndreturn()" >删除预设</a>
            </td>
        </tr>
        <tr>
            <td colspan="2"><textarea id="msg" name="msg"  cols="23" rows="3" style="width: 202px" onkeyup="gbcount(this)" onkeydown="gbcount(this)" onmouseout="gbcount(this)"></textarea></td>
        </tr>
        <tr>
            <td colspan="2" height="15px"><p style="line-height: 20px;width:72px;float: left;">您还可以输入</p>
            <h3 style="width: 20px;float: left;line-height:17px;font-size: 16px"><label id = "infocount">64</label></h3>
            <p style=" float:left  ;line-height: 20px;width:30px">个字</p></td>
        </tr>
        <tr>
        	<td >
        	<div style="margin-top: 10px;">
        	<div class="radio_c"><input name="saveinfo" type="checkbox" value="" id="saveinfo" />
        	</div> <div class="radio_txt">保存为预设消息</div>
        	</div>
        	
        	</td>
        	<td >
        	<div style="margin-top: 10px;">
        	<a href="#" class="btnbule" onfocus="this.blur()" onclick="postMsg()">发送</a>
        	</div>
        	
        	</td>
        </tr>
    </table>
    
    
    </div>
	<div id="Layer1" class="LayerMsgList" style="display: none;"><img src="../newimages/sidelayerimages/right.gif" width="16" height="15" /> 
	   	<s:label id="inforeault" name="inforeault" ></s:label>
	</div>
</body>
</html>