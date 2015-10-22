<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>

    
<script type="text/javascript">  

function trim(v){
	return v.replace(/(^[\s　]*)|([\s　]*$)/g, ""); 
}

var flushlist= 5000;
var intlist = 0;
//提示信息隐藏
var ral_resultShowflash= 2000;
function mapInit() {
	getInfoTitle();
	loadDownMessageList();
	intlist = window.setTimeout("loadDownMessageList()",flushlist);
	jQuery('#msg').focus();
	
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
var deflault = false;

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
		
		deflault = true;
		document.getElementById("1").checked = true;
		document.getElementById("4").checked = true;
		//tishiShow("默认选择'终端显示器显示'与'语音播报'！");
		//alert("默认选择'终端显示器显示'与'语音播报'!");
		return "01100000";
	}
}

function postMsg(){

	var vin = document.getElementById("vin").value;
	var msg = jQuery('#msg').val();
	var userid = document.getElementById('optionUserid').value;

	var yusheObj = document.getElementById('yusheInfo');
	var yusheInfo = yusheObj.value;
	
	var sendmsg = "";
	
	sendmsg = msg;

	

	if(sendmsg == null || trim(sendmsg).trim() == ""){
		tishiShow("请填写或选择消息内容！");
		//alert("请填写或选择消息内容!");
		return;
	}

	if(sendmsg.length >64){
		tishiShow("输入消息长度不能大于64个字！");
		//alert("输入消息长度不能大于32个字!");
		return;
	}


	if(document.getElementById("saveinfo").checked){
		//alert(document.getElementById("saveinfo").checked);

		addInfoTitle();
	}
	else{
		var typevalue = setInfoType();
		GPSDwr.postSendMsg(vin,trim(sendmsg),userid,typevalue,postMsg_CallBack);
	}	
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
	
	if(msg == null || trim(msg).trim() == ""){
		//alert("请输入要保存的预设消息!");
		tishiShow("请输入要保存的预设消息！");
		return ;
	}

	if(confirm("你确认要保存这条信息为预设消息吗?")){
		GPSDwr.addEnterInfo(entid,userid,msg.trim(),addEnterInfo_callback);
	}
	else{
		return ;
	}
	
	

	function addEnterInfo_callback(date){
		if(date==0){
			//hideshowresultDiv(0);
			//document.getElementById("inforeault").innerHTML="信息保存成功";
			tishiShow("信息保存成功！");
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
		var vin = document.getElementById("vin").value;
		var msg = document.getElementById("msg").value;
		var userid = document.getElementById('optionUserid').value;
		var yusheObj = document.getElementById('yusheInfo');
		var yusheInfo = yusheObj.value;
		
		var sendmsg = "";
		if(msg != null && msg != "" && msg != "undefined"){
			sendmsg = msg;
		}
		if(sendmsg == null || trim(sendmsg).trim() == ""){
			//alert("请填写或选择消息内容!");
			tishiShow("请填写或选择消息内容！");
			return;
		}
		if(sendmsg.length >64){
			//alert("输入消息长度不能大于32个字!");
			tishiShow("输入消息长度不能大于64个字！");
			return;
		}
		var typevalue = setInfoType();
		GPSDwr.postSendMsg(vin,trim(sendmsg),userid,typevalue,postMsg_CallBack);
		//下发指令结束
	}
	
}
function postMsg_CallBack(data){

	if(data=="0"){

		if(deflault){
			tishiShow("指令已下发！<br>默认发送到：'终端显示器'和'语音播报'！");
			deflault = false;
		}
		else{
			tishiShow("指令已下发！");
		}
		tishiShow("指令已下发！");
		//hideshowresultDiv(0);
		//document.getElementById("inforeault").innerHTML="指令已下发!";
		
		loadDownMessageList();	
	}
	else if(data=="8000"){
		tishiShow("终端不在线,请稍后再试！");
		//hideshowresultDiv(0);
		//document.getElementById("inforeault").innerHTML="终端不在线,请稍后再试!";
	}
	else{
		tishiShow("指令下发失败！");
		//hideshowresultDiv(0);
		//document.getElementById("inforeault").innerHTML="指令下发失败!";	
	}
	//window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);
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
			reset();
		}
		else{
			tishiShow("删除 失败！");
			//hideshowresultDiv(0);
			//document.getElementById("inforeault").innerHTML="删除 失败!";
		}
		//window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);

	}
}


/**
 * 输入监控
 */
 function textareaonkeyup(key){
	 //alert(123);
	
	if(key == 0){
		document.getElementById("buttontype").innerHTML = "重置";
	}else{
		document.getElementById("buttontype").innerHTML = "删除";
	}
}


/**
 * 删除重置按钮点击
 */
function deleteAndreturn(){
	
		deletenfoTitle();
	
}

function reset(){
	getInfoTitle();
	document.getElementById("msg").value = "";
	jQuery("#infocount").html("64");
}

/**
 * 实时刷新调度信息
 */
function loadDownMessageList(){
	closeThread();
	var vin = document.getElementById("vin").value;
	GPSDwr.downManageList(vin,downManageList_callback);
}
function downManageList_callback(data){
	
	if(data != null && data.length > 0){
		var result = "";
		for(var i = 0; i < data.length; i++){
			if(data[i].deal_state == "3"){
				result += infoSendedShow(data[i].operate_time,encodeHtml(data[i].remark));
			}else{
				result += infoSendingShow(data[i].operate_time,encodeHtml(data[i].remark));
			}
		}
		document.getElementById("infoShowDiv").innerHTML = result;
	}
	intlist = window.setTimeout("loadDownMessageList()",flushlist);
}


/**
 *发送中信息
 */
function infoSendingShow(time,remark){
	var result = "";
	result += '<div class="dialog_box" style="width:100%;">';
	result += '<div class="dialog_time2">'+time+'</div>';
	result += '<table  border="0" align="center" cellpadding="0" cellspacing="0" style="width:100%;">';
	result += '<tr>';
	result += '<td><img src="../newimages/sidelayerimages/dialog1.gif" style="width:100%;" height="6" /></td>';
	result += '</tr>';
	result += '<tr>';
	result += '<td class="dialog_timebg" style="width:100%;">'+remark+'</td>';
	result += '</tr>';
	result += '<tr>';
	result += '<td><img src="../newimages/sidelayerimages/dialog2.gif" style="width:100%;" height="6" /></td>'
	result += '</tr>';
	result += '</table>';
	result += '</div>';
	return result;
}
/**
*发送完信息
*/
function infoSendedShow(time,remark){
	var result = "";
	result += '<div class="dialog_box" style="width:100%;">';
	result += '<div class="dialog_time1">'+time+'</div>';
	result += '<table  border="0" align="center" cellpadding="0" cellspacing="0" style="width:100%;">';
	result += '<tr>';
	result += '<td><img src="../newimages/sidelayerimages/dialog3.gif" style="width:100%;" height="6" /></td>';
	result += '</tr>';
	result += '<tr>';
	result += '<td class="dialog_timebg2" style="width:100%;">'+remark+'</td>';
	result += '</tr>';
	result += '<tr>';
	result += '<td><img src="../newimages/sidelayerimages/dialog4.gif" style="width:100%;" height="6" /></td>'
	result += '</tr>';
	result += '</table>';
	result += '</div>';
	return result;
}

function closeThread(){
	window.clearTimeout(intlist);
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
function tishiShow(info){
	
	hideshowresultDiv(0);
	document.getElementById("inforeault").innerHTML=info;
	tishiHide();
}
function tishiHide(){
	window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);
}


 
 jQuery( function() {
	//alert(123);
	  jQuery('#bgdiv').css('display','');
	 jQuery(window.parent.iframeshowArea).resize(function(){
		 
		 //alert("msg");
		styleControl();
		
		  
	});

	 styleControl();
	
});

 var popWidth ="500px";
 var popMaxWidth ="655px";
 var popHeight = "484px";
 var popMaxHeiht = "660px";

function styleControl(){
	

	/*jQuery(window.parent.iframeshowArea).mk_autoresize( {
		height_include : '#bgdiv',
		height_offset : 0,
		width_include: '#bgdiv',
		width_offset: 0
	});*/

	if(jQuery(window.parent.iframeshowArea).width() > 500){
		//页面最外层div大小
		jQuery('#bgdiv').width(popMaxWidth);
		jQuery('#bgdiv').height(popMaxHeiht);
		//信息区高度
		jQuery('#infoShowDiv').height("452px");
		//jQuery('#infoShowDiv').width(popMaxWidth);
		//jQuery('#showArea1').width(popMaxWidth);
		//下拉框宽度
		//jQuery('#showArea2').width(popMaxWidth);
		jQuery('#yusheInfo').width("480px");
		//消息输入区
		//jQuery('#showArea3').width(popMaxWidth);
		jQuery('#msg').width("616px");

		//jQuery('#showArea4').width(popMaxWidth);
		
	}
	else{
		
		jQuery('#bgdiv').width(popWidth);
		jQuery('#bgdiv').height(popHeight);

		jQuery('#infoShowDiv').height("275px");
		//jQuery('#showArea1').width(popWidth);

		//jQuery('#showArea2').width(popWidth);
		jQuery('#yusheInfo').width("330px");

		//jQuery('#showArea3').width(popWidth);
		jQuery('#msg').width("466px");

		//jQuery('#showArea4').width(popWidth);

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
  
  if (jQuery('#msg').val().length > max) {

	  
	  jQuery('#infocount').html(0);
	  jQuery('#msg').val(jQuery('#msg').val().substring(0,max));
	  

	  tishiShow("输入消息长度不能大于64个字！");
  }
  else {
	  var con = max-jQuery('#msg').val().length;
	  jQuery('#infocount').html(con);
  }
}


 
</script> 