<%@page language="java" contentType="text/html;charset=utf-8"%>
<script>
var editOrLook = "";
function getVinSmsSet(){
	jQuery.ajax({
		  type: 'POST',  
		  url: '<s:url value="/energySMS/findVehicleSmsSign.shtml" />',	
		  data: {vins: jQuery("#vins").val()},	
		 success: function(data) {
			 if(data.message == "success" && data.smsCount != 0){
	 			var smsList = data.smsList;
	 			createUserAndPhones(smsList,data.smsCount);
			 } else {
				 
			 }
	      },
		  error:function (){
	      }
	});
}

function createUserAndPhones (objList,len){
	var upHtml = "";
	for(var i=0;i<len;i++){
		var obj = objList[i];
		var userid = obj.userId;
		var userName = obj.userName;
		var telePhone = obj.telePhone;
		var alarmTypes = obj.alarmType.split("!");
		for(var j=0,lengs=alarmTypes.length-1;j<lengs;j++){
			var tmpVal = "";
			if(j!=0){
				tmpVal = alarmTypes[j].replace(",","");
			} else {
				tmpVal = alarmTypes[j];
			}
			jQuery("input[value='"+tmpVal+"']").attr("checked","checked");
		}
		upHtml += "<tr>"+
				 "<td align=\"right\">姓名：</td>"+
				 "<td>"+
				 "<input type=\"hidden\" name=\"userId\" value=\""+userid+"\"/>"+
				 "<input type=\"text\" name=\"userName\" value=\""+userName+"\"/>"+
				 "<span style=\"color: red;\">*</span></td>"+
				 "<td align=\"right\">手机号：</td>"+
				 "<td><input type=\"text\" maxlength=\"11\" name=\"telephone\" value=\""+telePhone+"\"/><span style=\"color: red;\">*</span></td>";
				 if(editOrLook=="edit"){
				 	upHtml+="<td><a href=\"javascript:void(0)\" onclick=\"removeRow(this)\">删除</a></td>";
				 }
				 upHtml+="</tr>";
	}
	jQuery("#userMsg").html("");
	jQuery("#userMsg").append(upHtml);
	chooseAll();
	if(editOrLook=="look"){
		jQuery("#addUserBtn").css("display","none");
		jQuery("input").attr("disabled","disabled");
	}
}

function getAlarmType(){
	jQuery.ajax({
		  type: 'POST',  
		  url: '<s:url value="/energySMS/findSmsTypeList.shtml" />',	
		  data: {},	
		 success: function(data) {
			 if(data.message == "success" && data.dicCount != 0){
	 			var array = data.dicList;
	 			createAlarmType(array,data.dicCount);
			 }
	      },
		  error:function (){
	      }
	});
}
/* 放置页面内提醒类型数据 */
function createAlarmType(objList,listCount){
	var tables = jQuery("#alarmTypeTable");
	var alarmHtml = "";
	for(var i=0;i<listCount;i++){
		var obj = objList[i];
		var alarmValue = obj.alarmValue;
		var alarmType = obj.alarmType;
		var alarmInfo = obj.alarmInfo;
		alarmHtml += "<tr style=\"height: 10px;\">"+
					"<td align=\"center\"><input type=\"checkbox\" name=\"smsWarn\" value=\""+alarmValue+"\" onclick=\"chooseAll(this)\"/></td>"+
					"<td style=\"width: 60px;\">"+alarmType+"</td>"+
					"<td><div style=\"width: 100%;\"><span style=\"font-weight: bold;\">提示信息：</span><span>"+alarmInfo+"</span></div></td>"+
					"</tr>";
	}
	jQuery("#alarmTypeTable").html("");
	jQuery("#alarmTypeTable").append(alarmHtml);
}

function chooseAll(){
	var choseCount = jQuery(":checked",jQuery("#alarmTypeTable")).length;
	var boxCount = jQuery("input[type=checkbox]",jQuery("#alarmTypeTable")).length;
	if(choseCount == boxCount){
		jQuery("#allSels").attr("checked","checked");
	} else {
		jQuery("#allSels").removeAttr("checked");
	}
}

/* 添加车辆短信提醒设置 */
function addSmsSet(){
	var rex = new RegExp("^(13[0-9]{9}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}$)");
	var flag = "";
	if(jQuery(":checked").length == 0){
		flag += "1";
	}
	if(jQuery("input[name=userName]").length == 0){
		flag += "2";
	}
	if(jQuery("input[name=telephone]").length == 0){
		flag += "3";
	}
	var a = new Array();
	var b = new Array();
	var c = new Array();
	var d = new Array();
	if(flag.indexOf("1")>-1 && flag.indexOf("2")>-1 && flag.indexOf("3")>-1){
	} else {
		jQuery(":checked").each(function(i){
			if(jQuery(this).attr("name")=="smsWarn"){
				a.push(jQuery(this).val());
			}
		});
		jQuery("input[name=userName]").each(function(i){
			if(jQuery(this).val().length == 0){
				jQuery(this).addClass("inputRed");
				flag = flag.indexOf("2") > -1?flag: flag += "2";
			} else {
				jQuery(this).removeClass("inputRed");
			}
			b.push(jQuery(this).val());
		});
		jQuery("input[name=telephone]").each(function(i){
			if(jQuery(this).val().length == 0){
				jQuery(this).addClass("inputRed");
				flag = flag.indexOf("3") > -1?flag: flag += "3";
			} else if(!rex.test(jQuery(this).val())){
				jQuery(this).addClass("inputRed");
				flag = flag.indexOf("4") > -1?flag: flag += "4";
			} else {
				jQuery(this).removeClass("inputRed");
			}
			c.push(jQuery(this).val());
		});
		jQuery("input[name=userId]").each(function(){
			d.push(jQuery(this).val());
		});
		if(flag.length == 1){
			if(flag == "1"){
				tishiShow("您未设置短信提醒类型！");
				return ;
			} else if(flag == "2"){
				tishiShow("您有未设置的手机持有者名字！");
				return ;
			} else if(flag == "3"){
				tishiShow("您有未设置的手机号码！");
				return ;
			} else if(flag == "4"){
				tishiShow("您有设置的不合法的手机号码！");
				return ;
			}
		} else if(flag.length > 1){
				tishiShow("您有多项未设置！");
				return ;
		}
	}
	var smsType = a.join("!");
	var userName = b.join(",");
	var phones = c.join(",");
	var userIds = d.join(",");
	jQuery.ajax({
		  type: 'POST',  
		  url: '<s:url value="/energySMS/addSmsSet.shtml" />',	
		  data: {vins: jQuery("#vins").val(),alarmTypeList: smsType,
			  smsNames: userName,smsPhones: phones,smsUserIds: userIds},	
		 success: function(data) {
			 if(data.message == "success"){
				 alert("保存成功!");
				 top.smsSetSearch();
				 cancelArt();
	 			//var smsList = data.smsList;
	 			//createUserAndPhones(smsList,data.smsCount);
			 } else {
				 
			 }
	      },
		  error:function (){
	      }
	});
}

//打开选择用户窗口
function openWinSign(vin){
	art.dialog.open("<s:url value='/energySMS/iframeUser.shtml' />?flag=0&vins="+vin,{
		id:'smsUser',
		title:'选择用户',
		lock:true,
		width:470,
		height:460,
		fixed:true,
		initFn:function(){
			
		},
		closeFn:function(){
			//cancelArt();
		}
	}); 
}


/* 关闭dialog 窗口 */
function cancelArt(){
	top.cancelArt('smsSet');
}

function hideshowresultDiv(flag){
	if(flag==1){
		jQuery('#Layer1').css('display','none');
	}
	else if(flag==0){
		jQuery('#Layer1').css('display','block');
	}	
}

function tishiShow(info){
	tishiHide();
	hideshowresultDiv(0);
	document.getElementById("inforeault").innerHTML=info;
}
function tishiHide(){
	window.setTimeout("hideshowresultDiv(1)",1500);
}

function createMsg(msgtxt){
	var arr = msgtxt.split(",");
	var len = arr.length;
	var upHtml = "";
	for(var i=0;i<len;i++){
		var obj = arr[i].split("!");
		if(obj.length == 0 || obj == ""){
			continue;
		}
		var userid = obj[0];
		var userName = obj[1];
		var telePhone = obj[2] == "null"?"":obj[2];
		upHtml += "<tr>"+
		 "<td align=\"right\">姓名：</td>"+
		 "<td>"+
		 "<input type=\"hidden\" name=\"userId\" value=\""+userid+"\"/>"+
		 "<input type=\"text\" name=\"userName\" value=\""+userName+"\"/>"+
		 "<span style=\"color: red;\">*</span></td>"+
		 "<td align=\"right\">手机号：</td>"+
		 "<td><input type=\"text\" maxlength=\"11\" name=\"telephone\" value=\""+telePhone+"\"/><span style=\"color: red;\">*</span></td>";
		 if(editOrLook=="edit"){
		 	upHtml+="<td><a href=\"javascript:void(0)\" onclick=\"removeRow(this)\">删除</a></td>";
		 }
		 upHtml+="</tr>";
	}
	jQuery("#userMsg").append(upHtml);
	top.cancelArt('smsUser');
}

function removeRow(obj){
	jQuery(obj).parent().parent().remove();
}

jQuery(function() {
	editOrLook = top.getEidtOrLook();
	getAlarmType();
	if(jQuery("#flag").val() == "0"){
		getVinSmsSet();
	}
	jQuery("#allSels").click(function(){
		if(jQuery(this).attr("checked")){
			jQuery("input[name=smsWarn]").attr("checked","checked");
		} else {
			jQuery("input[name=smsWarn]").removeAttr("checked");
		}
	});
});

</script>