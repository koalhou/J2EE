<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>

    
<script type="text/javascript"> 
var mereg=/^\d+$/;
//照片url列表
var photolist ;
//新照片url列表
var photolistNew;
var intOneinit = 0;
//照片索引
var photoindex = 0;
//选择的索引 0-2
var controlindex = 0;
// 修改表示
var updateid = false;
//提示信息隐藏
var ral_resultShowflash= 3000;

function iframefoucus(){
	document.getElementById("focus_txt").focus();
	document.getElementById("focusDiv").style.display = "none";
}

function init(){
	
	var vin = document.getElementById("vin").value;	
	
	
	GPSDwr.initSendPhotoInfo(vin,initSendPhotoInfo_callback);
	
	function initSendPhotoInfo_callback(array){
		//alert(array.length);
		if(array != null && array.length>0){
			photolistNew = array;	
			
			photolist = photolistNew;
			//alert(photoindex +","+ controlindex);
			if(photolist.length == photolistNew.length){
				
				photoOrtherInfo(photoindex+controlindex);
				pointOfIntersection(controlindex);
			}
			else{
			
				photoindex = 0;
				controlindex = 0;
				updateid = false;
				photoOrtherInfo(photoindex+controlindex);
				pointOfIntersection(controlindex);
			}

			if(photolist.length > 3){
				document.getElementById("PHOTO0").src = ".."+ photolist[photoindex].PHOTO_FILE;
				document.getElementById("PHOTO1").src = ".."+ photolist[photoindex+1].PHOTO_FILE;
				document.getElementById("PHOTO2").src = ".."+ photolist[photoindex+2].PHOTO_FILE;
				showContrlImage();
			}
			else{	
				showContrlImage();
				for(var i = 0; i < photolist.length; i++){
					//alert(photolist[i].PHOTO_FILE);
					var id = "PHOTO"+(i);
					//alert(id);
					var photo = document.getElementById(id);
					//alert(photo);
					photo.src = ".."+ photolist[i].PHOTO_FILE;
				}
			}
		}
			intOneinit = window.setTimeout("init()",6000);
	}
}
function photoOnline(){
	var vin = document.getElementById("vin").value;	
	GPSDwr.returnTipInfo(vin,returnTipInfo_callback);
}
function returnTipInfo_callback(date){
	
	var TerminalViBean = date[0];
	var vin = TerminalViBean.VEHICLE_VIN;
	
	if(TerminalViBean.color =="b" && TerminalViBean.STAT_INFO  == 1){

	}
	else{
		//tishiShow("车辆已熄火，请稍后再尝试车辆拍照！");
			hideshowresultDiv(0);
			document.getElementById("inforeault").innerHTML="车辆已熄火，请稍后再尝试车辆拍照！";
			window.setTimeout("hideshowresultDiv(1)",5000);
		
		
		//alert("车辆已熄火，请稍后再尝试车辆拍照！");
	}
	
	
}

function photoOrtherInfo(i){
	//alert(i);
	//controlindex = i;
	if(photolist != null && photolist.length >0){
		if(photolist[i] != null ){
			document.getElementById("PHOTO_ID").value = photolist[i].PHOTO_ID;
			document.getElementById("PHOTO").src =".."+ photolist[i].PHOTO_FILE;

			if(photolist[i].PHOTO_TIME != null){
				document.getElementById("PHOTO_TIME").innerHTML = photolist[i].PHOTO_TIME;		
			}
			else{
				document.getElementById("PHOTO_TIME").innerHTML = "";
			}

			if(photolist[i].PHOTO_EVENT != null){
				var tdDiv =document.getElementById("PHOTO_EVENT");
				var photo_event = photolist[i].PHOTO_EVENT;
			   if(photo_event =='0' ){
				      tdDiv.innerHTML = '下发指令拍照';
			   }
			   else if(photo_event=='1'){
				   tdDiv.innerHTML = '终端定时拍照';
			   }
			   else if(photo_event=='2'){
				  tdDiv.innerHTML = 'SOS拍照';
			   }
			   else if(photo_event=='3'){
				   tdDiv.innerHTML = '碰撞侧翻拍照';  
			   }
			   else if(photo_event=='4'){
				   tdDiv.innerHTML = '开门拍照';  
			   }
			   else if(photo_event=='5'){
				   tdDiv.innerHTML = '关门拍照';  
			   }
			   else if(photo_event=='6'){
				   tdDiv.innerHTML = '行驶开关门拍照';  
			   }
			}
			else{
				document.getElementById("PHOTO_EVENT").innerHTML = "";
			}
			//alert(updateid);
			if(!updateid){//非编辑状态
				if(photolist[i].PHOTO_DESC != null){
					document.getElementById("remark").value = photolist[i].PHOTO_DESC;
					//jQuery('#remark').val('');
					jQuery('#remark').css('color','');
				}
				else{
					document.getElementById("remark").value ="填写备注";
					jQuery('#remark').css('color','#999999');
					jQuery('#remark').blur();
				}
				if(photolist[i].PHOTO_FALG == "1"){
					document.getElementById("PHOTO_FALG").value = photolist[i].PHOTO_FALG;
					document.getElementById("PHOTO_FALG").checked = true;
				}
				else{
					document.getElementById("PHOTO_FALG").value = "0";
					document.getElementById("PHOTO_FALG").checked = false;
				}	
			}	
		}
	}
}

function savePhotoInfo(){
	var PHOTO_ID = document.getElementById("PHOTO_ID").value;
	var PHOTO_FALG = document.getElementById("PHOTO_FALG").value;
	//alert(document.getElementById("remark").value);
	
	var userid = document.getElementById('optionUserid').value;

	if(PHOTO_ID == null || PHOTO_ID == ""){
		tishiShow("无照片数据，不能保存！");
		//alert("备注内容不能超过60个字！");
		return;
	}

	if(jQuery('#remark').css('color') == '#999999'){
		 document.getElementById("remark").value = "";
	}
	var remark = document.getElementById("remark").value;
	if(remark.length >100){
		tishiShow("备注不能超过100字！");
		//alert("备注内容不能超过60个字！");
		return;
	}
	if(document.getElementById("PHOTO_FALG").checked){
		PHOTO_FALG = 1;
	}
	else{
		PHOTO_FALG = 0;
	}
	GPSDwr.SavePhotoInfo(PHOTO_ID,PHOTO_FALG,remark,userid,SavePhotoInfo_callback);

	function SavePhotoInfo_callback(date){
		if(date){
			tishiShow("保存成功！");updateid=false;
			//alert("保存成功!");
			init();
		}else{
			tishiShow("保存失败！");
			//alert("保存失败!");
		}
	}
}


//上箭头按钮
function upControl(){
	if(photolist == null || photolist.length < 0){
		tishiShow("无照片！");
		//hideshowresultDiv(0);
		//document.getElementById("inforeault").innerHTML="无照片！";
		//window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);
		return;
	}
	if(photoindex+2 < photolist.length-1){
		photoindex = photoindex+1;
		for(var i = 0; i < 3; i++){
			var id = "PHOTO"+(i);
			var photo = document.getElementById(id);
			photo.src = ".."+ photolist[photoindex+i].PHOTO_FILE;
		}
		showContrlImage();
		photoOrtherInfo(photoindex+controlindex);
		pointOfIntersection(controlindex);
	}
}

// 下箭头按钮
function downContrl(){
	if(photoindex > 0){
		photoindex = photoindex-1;
		for(var i = 0; i < 3; i++){
			var id = "PHOTO"+(i);
			var photo = document.getElementById(id);
			photo.src = ".."+ photolist[photoindex+i].PHOTO_FILE;
		}
		showContrlImage();
		photoOrtherInfo(photoindex+controlindex);
		pointOfIntersection(controlindex);
	}
}

function showContrlImage(){
	if(photoindex == 0){
		//down不可用
		document.getElementById("downImage").src = "../newimages/sidelayerimages/btn_arrowBottom.png";
		document.getElementById("upImage").src = "../newimages/sidelayerimages/btn_arrowTop_Disable.png";
	}
	if(photoindex+2 == photolist.length-1){
		//up不可用
		document.getElementById("upImage").src = "../newimages/sidelayerimages/btn_arrowTop.png";
		document.getElementById("downImage").src = "../newimages/sidelayerimages/btn_arrowBottom_Disable.png";
	}
	if(photoindex > 0 && photoindex < photolist.length-3){
		document.getElementById("upImage").src = "../newimages/sidelayerimages/btn_arrowTop.png";
		document.getElementById("downImage").src = "../newimages/sidelayerimages/btn_arrowBottom.png";
	}
	
}

//交点样式控制
function pointOfIntersection(id){
	controlindex = id;
	if(controlindex==0){
		document.getElementById("PHOTO0").style.border  ="2px solid #fc0";
		document.getElementById("PHOTO1").style.border  ="0px solid #fc0";
		document.getElementById("PHOTO2").style.border  ="0px solid #fc0";
	}else if(controlindex == 1){
		document.getElementById("PHOTO0").style.border  ="0px solid #fc0";
		document.getElementById("PHOTO1").style.border  ="2px solid #fc0";
		document.getElementById("PHOTO2").style.border  ="0px solid #fc0";//alert(123);
	}else if(controlindex == 2){
		document.getElementById("PHOTO0").style.border  ="0px solid #fc0";
		document.getElementById("PHOTO1").style.border  ="0px solid #fc0";
		document.getElementById("PHOTO2").style.border  ="2px solid #fc0";
	}
	
}



//拍照
function postPhoto(){

	var vin = document.getElementById("vin").value;
	var userid = document.getElementById('optionUserid').value;
	var routeway2 = document.all.routeway2;
	var wayNo = "";

	if(routeway2.length > 1){
		for(var j =0; j < routeway2.length; j++){
			if(routeway2[j].checked){
				wayNo += routeway2[j].value+",";
			}
		}
	}
	else{
		if(routeway2.checked){
			wayNo += routeway2.value+",";
		}
	}

	if(wayNo != ""){
		GPSDwr.postSendPhoto(vin,wayNo,1,5,userid,126,65,65,126,postPhoto_CallBack);
	}
	else{
		tishiShow("请选择拍照通道！");
		//hideshowresultDiv(0);
		//document.getElementById("inforeault").innerHTML="请选择拍照通道！";
		//window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);
		
	}

	function postPhoto_CallBack(data){

		//var str = data;
		var result = "";
		var sccess = "";
		var error  = "";
		var wait = "";

		if(data == "8000"){
			tishiShow("终端不在线 ，请稍后再试！");
			//hideshowresultDiv(0);
			//document.getElementById("inforeault").innerHTML="终端不在线 ，请稍后再试！";
		}
		else if(data == "8001"){
			tishiShow("车辆已熄火，请稍后再尝试车辆拍照！");
			//hideshowresultDiv(0);
			//document.getElementById("inforeault").innerHTML="车辆已熄火，请稍后再尝试车辆拍照！";
		}else{
			var strs = data.split(",");
			if(strs.length >0){

				for(var j =0 ; j < strs.length; j++){

					if(strs[j] != null && strs[j] != "" && strs[j] != "undefined"){

						var s = strs[j].split("-");
						//alert(s[1]);
						if(s[1]==0){
							sccess += waynorecode(s[0])+",";
						}
						else if(s[1]==611){
							wait += waynorecode(s[0]) +",";
						}
						else {
							error += waynorecode(s[0])+",";
								
						}
					}
					
				}
				if(sccess != ""){
					result += sccess+"拍照指令已下发！";
				}

				if(error != ""){
					result += error+"拍照指令下发失败！";
				}

				if(wait != ""){
					result += wait+"拍照延迟请等待！";
				}
				tishiShow(result);
			}
			/*if(sccess != ""){
				result += sccess;
			}

			if(error != ""){
				result +=error;
			}

			if(wait != null){
				result +=wait;
			}
			tishiShow(result);*/
			
		}
		
	}
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

function openhistoryphoto() {
	var vehln=document.getElementById("vehln").value;

	document.getElementById("lookphoto").href = "<s:url value='/photomonitor/photomanage.shtml'/>?vehicle_ln="+vehln;
	
	
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

function cleartext(){
	//var txt=jQuery('#opeerate_desc').html();
	if(jQuery('#remark').css('color') == '#999999'){
		jQuery('#remark').val('');
		jQuery('#remark').css('color','');
	}
}

function onerror_event(obj){
	obj.src = "../newimages/backgroup.gif";
}

jQuery( function() {
	//alert(123);
	
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
	
	if(jQuery(window.parent.iframeshowArea).width() > 500){
		//页面最外层div大小
		jQuery('#bgdiv').width(popMaxWidth);
		jQuery('#bgdiv').height(popMaxHeiht);
		//照片区域大小
		jQuery('#photoAreaDiv').height("476px");

		jQuery('#photo_leftAreaDiv').width("533px");
		jQuery('#photo_leftAreaDiv').height("476px");

		jQuery('#photo_rightAreaDiv').height("476px");

		jQuery('#PHOTO').width("476px");
		jQuery('#PHOTO').height("406px");
		
		jQuery('#PHOTO0').height("133px");
		jQuery('#PHOTO1').height("133px");
		jQuery('#PHOTO2').height("133px");

		jQuery('#remark').width("590px");
	}
	else{
		
		jQuery('#bgdiv').width(popWidth);
		jQuery('#bgdiv').height(popHeight);
	
		jQuery('#photoAreaDiv').height("300px");

		jQuery('#photo_leftAreaDiv').width("378px");
		jQuery('#photo_leftAreaDiv').height("300px");

		jQuery('#photo_rightAreaDiv').height("300px");

		jQuery('#PHOTO').width("320px");
		jQuery('#PHOTO').height("230px");
		
		jQuery('#PHOTO0').height("75px");
		jQuery('#PHOTO1').height("75px");
		jQuery('#PHOTO2').height("75px");

		jQuery('#remark').width("470px");
	}

	
	
}
</script>
