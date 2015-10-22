<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">

var rangeMap = new Map();
// rangeMap.put('photoSetTable',new photoSetObj("","","","","photoSetTable"));
var pointMap = new Map();
// pointMap.put('photoSetPointTable',new photoSetObj("","","","","photoSetPointTable"));

var rangeParam = "";
var pointParam = "";
var rangeTableCount = 1;
var pointTableCount = 1;
var cachData = null;

function searchSignPhotoSet(vin){
	jQuery.ajax({
 		  type: 'POST',  
 		  url: '<s:url value="/photograph/findSignVehicleSet.shtml" />',	
 		  data: {vehicleVin: jQuery("#vehicleVin").val()},	
 		  success: function(data) {
 			if(data.listCount > 0){
 				cachData = data.list;
 				var len = data.list.length;
 				for(var i=0;i<len;i++){
 					var objs = data.list[i];
 					var setId = objs.setId;
 					var weeks = objs.week;
 					var startTime = objs.beginTime;
 					var endTime = objs.endTime;
 					var interval = objs.interval;
 					if(objs.type=='1'){
 						//var tableLen = jQuery("table[id^=photoSetTable]").length;
 						if(rangeTableCount == 1){
 							setBox(weeks,'photoSetTable','0',setId);
 							setInput(startTime,endTime,interval,'photoSetTable');
 							var tObj = rangeMap.get("photoSetTable");
 	 						tObj.setId = tObj.setId+","+setId;
 	 						rangeMap.removeByKey("photoSetTable");
 	 						rangeMap.put("photoSetTable",tObj);
 							rangeTableCount++;
 						} else {
 							addRangeTable("2","");
 							setBox(weeks,'photoSetTable'+rangeTableCount,'0',setId);
 							setInput(startTime,endTime,interval,"photoSetTable"+rangeTableCount);
 							var tObj = rangeMap.get("photoSetTable"+rangeTableCount);
 	 						tObj.setId = tObj.setId+","+setId;
 	 						rangeMap.removeByKey("photoSetTable"+rangeTableCount);
 	 						rangeMap.put("photoSetTable"+rangeTableCount,tObj);
 						}
 						
 						//rangeTableCount++;
 					} else {
 						//var tableLen = jQuery("table[id^=photoSetPointTable]").length;
 						if(pointTableCount == 1){
 							setBox(weeks,'photoSetPointTable','1',setId);
 							setInput(startTime,endTime,interval,'photoSetPointTable');
 							var tObj = pointMap.get("photoSetPointTable");
 	 						tObj.setId = tObj.setId+","+setId;
 	 						pointMap.removeByKey("photoSetPointTable");
 	 						pointMap.put("photoSetPointTable",tObj);
 							pointTableCount++;
 						} else {
 							addPointTable("2","");
 							setBox(weeks,'photoSetPointTable'+pointTableCount,'1',setId);
 							setInput(startTime,endTime,interval,'photoSetPointTable'+pointTableCount);
 							var tObj = pointMap.get("photoSetPointTable"+pointTableCount);
 	 						tObj.setId = tObj.setId+","+setId;
 	 						pointMap.removeByKey("photoSetPointTable"+pointTableCount);
 	 						pointMap.put("photoSetPointTable"+pointTableCount,tObj);
 						}
 						
 						//pointTableCount++;
 					}
 				}
 			} else {
 				//alert("没有内容！");
 			}
 			if(isEditSet){
 				jQuery("input").attr("disabled","disabled");
 			}
	      },
		  error:function (){
	    	//jQuery.unblockUI();  
	      }
  });
}

function deleteSet(){
	if(confirm("您确定要删除全部定时拍照设置吗？")) {
		if(deleteCheck()){
			jQuery.ajax({
				  type: 'POST',  
				  url: '<s:url value="/photograph/deletePhotoSet.shtml" />',	
				  data: {vehicleVin: jQuery("#vehicleVin").val()},	
				  success: function(data) {
					if(data.message == "success"){
						top.searchFresh();
						top.tishiWin("车辆删除定时拍照设置成功！");
						cancelArt();
					} else if(data.message == "error") {
						top.searchFresh();
						top.tishiWin("车辆删除定时拍照设置失败！");
						cancelArt();
					}
			      },
				  error:function (){
			    	//jQuery.unblockUI();  
			      }
			});
		}
	}
}

function deleteCheck(){
	var keys = rangeMap.keys();
	for(var k = 0, vLen = keys.length;k < vLen;k++ ){
		var rObjs = rangeMap.get(keys[k]);
		var boxArr = rObjs.weeks.split(",");
		var eachStartTime = coverMinites(rObjs.bakStartTime);
		var eachEndTime = coverMinites(rObjs.bakEndTime);
		
		for(var j = 0,boxLen = boxArr.length; j < boxLen; j++){
			var boxVal = boxArr[j];
			var date = new Date();
			var curWeek = date.getDay();
			var curHours = coverMinites(date.getHours()+":"+date.getMinutes());
			if(curWeek == boxVal && jQuery("#"+keys[k]).find("input[atr="+curWeek+"]").attr("ids")!=null && (eachStartTime <= curHours && eachEndTime >= curHours)){
				var boxWeek = numToWeek(curWeek);
				jQuery("#"+keys[k]).find("td[attr='message']").html("<span style='color:red;'>*请检查，当天星期"+boxWeek+",有设置时间范围内包含当前时间,不可删除！</span>");
				jQuery("#"+keys[k]).find("td[attr='message']").css("display","block");
				//tishiShow("请检查，当天星期"+boxWeek+",有设置时间范围内包含当前时间,不可删除！");
				return false;
			}else{
				jQuery("#"+keys[k]).find("td[attr='message']").html("");
				jQuery("#"+keys[k]).find("td[attr='message']").css("display","none");
			}
		}
	}
	return true;
}

function setBox(weeks,tableId,type,setId){
	var arrs = weeks.split(",");
	var i = 0;
	var len = arrs.length;
	$.each(arrs, function(key, val) {
		jQuery("input[atr="+val+"]",jQuery("#"+tableId)).attr("checked","checked");
		jQuery("input[atr="+val+"]",jQuery("#"+tableId)).attr("ids",setId);
		checkBoxChange(tableId,type);
	}); 
}

function setInput(startTime,endTime,interval,tableId){
	jQuery("input[type=text]",jQuery("#"+tableId)).each(function(i){
		if(i == 0){
			jQuery(this).val(startTime);
		} else if(i == 1){
			jQuery(this).val(endTime);
		} else {
			jQuery(this).val(interval);
		}
		jQuery(this).change();
	});
}

/* 定时拍照设置 */
function addRangeTable(flag,cssStyle){
	var tableHtml = "";
	rangeTableCount = rangeTableCount+1;
	var tabLen = jQuery("table[id^=photoSetTable]").length;
	if(tabLen >= 5){
		tishiShow("最多设置5个时间段拍照范围！");
		//rangeTableCount = 5;
		return ;
	}
	var len = rangeTableCount;//jQuery("#range").find("table[id^=photoSetTable]").length;
	tableHtml = "<table id=\"photoSetTable"+len+"\"  width=\"525px\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">"
				+"<tr style=\"height: 25px;\">"
				+"<td style=\"color: black;\" colspan=\"3\">"
				+"<ul class=\"weeksli\">"
				+"<li><input type=\"checkbox\" atr='1' name=\"week1\" onclick=\"checkBoxChange('photoSetTable"+len+"','0',this)\" "+cssStyle+"/>周一</li>"
				+"<li><input type=\"checkbox\" atr='2' name=\"week2\" onclick=\"checkBoxChange('photoSetTable"+len+"','0',this)\" "+cssStyle+"/>周二</li>"
				+"<li><input type=\"checkbox\" atr='3' name=\"week3\" onclick=\"checkBoxChange('photoSetTable"+len+"','0',this)\" "+cssStyle+"/>周三</li>"
				+"<li><input type=\"checkbox\" atr='4' name=\"week4\" onclick=\"checkBoxChange('photoSetTable"+len+"','0',this)\" "+cssStyle+"/>周四</li>"
				+"<li><input type=\"checkbox\" atr='5' name=\"week5\" onclick=\"checkBoxChange('photoSetTable"+len+"','0',this)\" "+cssStyle+"/>周五</li>"
				+"<li><input type=\"checkbox\" atr='6' name=\"week6\" onclick=\"checkBoxChange('photoSetTable"+len+"','0',this)\" "+cssStyle+"/>周六</li>"
				+"<li><input type=\"checkbox\" atr='7' name=\"week7\" onclick=\"checkBoxChange('photoSetTable"+len+"','0',this)\" "+cssStyle+"/>周日</li>"
				+"</ul>"
				+"</td>"
				+"</tr>"
				+"<tr>"
				+"<td style=\"padding-left: 60px;width: 290px;\">"
				+"拍照时间范围：<input type=\"text\" name=\"startTime\" value=\"\" style=\"width: 70px;\" readonly=\"readonly\" onfocus=\"WdatePicker({dateFmt:'HH:mm'})\" onchange=\"inputChange('range','photoSetTable"+len+"',this,'0')\" "+cssStyle+"/>"
				+"&nbsp;------&nbsp;"
				+"<input type=\"text\" name=\"endTime\" value=\"\" style=\"width: 70px;\" readonly=\"readonly\" onfocus=\"WdatePicker({dateFmt:'HH:mm'})\" onchange=\"inputChange('range','photoSetTable"+len+"',this,'1')\" "+cssStyle+" />"
				+"</td>"
				+"<td>"
				+"拍照间隔：<input type=\"text\" name=\"interval\" value=\"\" style=\"width: 30px;\" onchange=\"inputInterval('photoSetTable"+len+"',this)\" "+cssStyle+"/>"
				+"</td>"
				+"<td style=\"width: 40px;\">";
				if(flag == '1' || flag == '2' && !isEditSet){
	tableHtml = tableHtml +"<a href=\"javascript:void(0);\" onclick=\"deleteSetTable(this.parentNode.parentNode.parentNode.parentNode)\" style=\"margin-left: 10px;\">删除</a>";
				}
	tableHtml = tableHtml +"</td>"
				+"</tr>"
				+"<tr><td attr='message' valign=\"middle\" colspan=\"3\" style='padding-left: 70px;'></td></tr>"
				+"<tr name=\"hrs\" valign=\"middle\"><td valign=\"middle\" colspan=\"3\">&nbsp;<hr style=\"height: 2px solid gray;\"/></td></tr>"
				+"</table>";
	jQuery("#range").append(tableHtml);
	var obj = new photoSetObj("","","","","photoSetTable"+len);
	rangeMap.put("photoSetTable"+len,obj);
	//rangeTableCount++;
}

function addPointTable(flag,cssStyle){
	var tableHtml = "";
		//pointTableCount = pointTableCount+1;
		var tabLen = jQuery("table[id^=photoSetPointTable]").length;
		if(tabLen >= 5){
			tishiShow("最多设置5个定点拍照！");
			//pointTableCount = 5;
			return ;
		}
	var len = pointTableCount;//jQuery("#point").find("table[id^=photoSetPointTable]").length;
	
	tableHtml = "<table id=\"photoSetPointTable"+len+"\"  width=\"525px\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">"
				+"<tr style=\"height: 25px;\">"
				+"<td style=\"color: black;\" colspan=\"3\">"
				+"<ul class=\"weeksli\">"
				+"<li><input type=\"checkbox\" atr='1' name=\"week1\" onclick=\"checkBoxChange('photoSetPointTable"+len+"','1',this)\" "+cssStyle+"/>周一</li>"
				+"<li><input type=\"checkbox\" atr='2' name=\"week2\" onclick=\"checkBoxChange('photoSetPointTable"+len+"','1',this)\" "+cssStyle+"/>周二</li>"
				+"<li><input type=\"checkbox\" atr='3' name=\"week3\" onclick=\"checkBoxChange('photoSetPointTable"+len+"','1',this)\" "+cssStyle+"/>周三</li>"
				+"<li><input type=\"checkbox\" atr='4' name=\"week4\" onclick=\"checkBoxChange('photoSetPointTable"+len+"','1',this)\" "+cssStyle+"/>周四</li>"
				+"<li><input type=\"checkbox\" atr='5' name=\"week5\" onclick=\"checkBoxChange('photoSetPointTable"+len+"','1',this)\" "+cssStyle+"/>周五</li>"
				+"<li><input type=\"checkbox\" atr='6' name=\"week6\" onclick=\"checkBoxChange('photoSetPointTable"+len+"','1',this)\" "+cssStyle+"/>周六</li>"
				+"<li><input type=\"checkbox\" atr='7' name=\"week7\" onclick=\"checkBoxChange('photoSetPointTable"+len+"','1',this)\" "+cssStyle+"/>周日</li>"
				+"</ul>"
				+"</td>"
				+"</tr>"
				+"<tr>"
				+"<td style=\"padding-left: 60px;width: 290px;\">"
				+"拍照时间：<input type=\"text\" name=\"startTime\" value=\"\" style=\"width: 70px;\" readonly=\"readonly\" onfocus=\"WdatePicker({dateFmt:'HH:mm'})\" onchange=\"inputChange('point','photoSetPointTable"+len+"',this,'0')\" "+cssStyle+"/>"
// 				+"------"
// 				+"<input type=\"input\" name=\"endTime\" value=\"\" style=\"width: 70px;\"  "+cssStyle+"/>"
				+"</td>"
				+"<td>&nbsp;"
// 				+"拍照间隔<input type=\"input\" name=\"interval\" value=\"\" style=\"width: 30px;\"  "+cssStyle+"/>"
				+"</td>"
				+"<td style=\"width: 40px;\">";
				if(flag == '1' || flag == '2' && !isEditSet){
	tableHtml = tableHtml +"<a href=\"javascript:void(0);\" onclick=\"deleteSetTable(this.parentNode.parentNode.parentNode.parentNode)\" style=\"margin-left: 10px;\">删除</a>";
				}
	tableHtml = tableHtml +"</td>"
				+"</tr>"
				+"<tr><td attr='message' valign=\"middle\" colspan=\"3\" style='padding-left: 70px;'></td></tr>"
				+"<tr name=\"hrs\" valign=\"middle\"><td valign=\"middle\" colspan=\"3\">&nbsp;<hr style=\"height: 2px solid gray;\"/></td></tr>"
				+"</table>";
	jQuery("#point").append(tableHtml);
	var obj = new photoSetObj("","","","","photoSetPointTable"+len);
	pointMap.put("photoSetPointTable"+len,obj);
	//pointTableCount++;
}

/* 周，复选框事件函数  */
function checkBoxChange(tableId,type,tarObj){
// 	var objs = new photoSetObj();
	var objs = null;
	if(type == "0"){
		//var objs = null;
		if(rangeMap.containsKey(tableId) == false){
			objs = new photoSetObj();
		} else {
			objs = rangeMap.get(tableId);
			if(jQuery(tarObj).attr("ids") != null){
				var date = new Date();
				var curWeek = date.getDay();
				var curHours = Number(coverMinites(date.getHours()+":"+date.getMinutes()),10);
				var eachStartTime = Number(coverMinites(objs.bakStartTime),10);
				var eachEndTime = Number(coverMinites(objs.bakEndTime),10);
				//var timeValue = Number(coverMinites(obj.value),10);
				
				if(objs.weeks.indexOf(curWeek) > -1 && !jQuery("#"+tableId).find("input[atr="+curWeek+"]").attr("checked")
						&& (eachStartTime <= curHours && eachEndTime >= curHours)){
					jQuery("#"+tableId).find("input[atr="+curWeek+"]").attr("checked","checked");
					var boxWeek = numToWeek(curWeek);
					jQuery("#"+tableId).find("td[attr='message']").html("<span style='color:red;'>*请检查，当天星期"+boxWeek+",当前时间在该设置范围内,暂不可修改！</span>");
					jQuery("#"+tableId).find("td[attr='message']").css("display","block");
					//tishiShow("请检查，当天星期"+boxWeek+",当前时间在该设置范围内,暂不可修改！");
					return ;
				}else{
					jQuery("#"+tableId).find("td[attr='message']").html("");
					jQuery("#"+tableId).find("td[attr='message']").css("display","none");
				}
			}
		}
		var atrs = "";
		jQuery("#"+tableId).find("input:checked").each(function(i){
			if(i == 0){
				atrs = atrs + jQuery(this).attr("atr");
			}else{
				atrs = atrs + "," + jQuery(this).attr("atr");
			}
		});
		objs.weeks = atrs;
		rangeMap.removeByKey(tableId);
		rangeMap.put(tableId,objs);
	} else {
		//var objs = null;
		if(pointMap.containsKey(tableId) == false){
			objs = new photoSetObj();
		} else {
			objs = pointMap.get(tableId);
		}
		var arrs = "";
		jQuery("#"+tableId).find("input:checked").each(function(i){
			if(i == 0){
				arrs = arrs + jQuery(this).attr("atr");
			}else{
				arrs = arrs + "," + jQuery(this).attr("atr");
			}
		});
		objs.weeks = arrs;
		pointMap.removeByKey(tableId);
		pointMap.put(tableId,objs);
	}
}
/* 时间间隔，内容改变事件函数 */
function inputInterval(tableId,obj){
	var objs = null;
	if(rangeMap.containsKey(tableId) == false){
		objs = new photoSetObj();
	} else {
		objs = rangeMap.get(tableId);
	}
	objs.interval = obj.value;
	//验证格式与内容
	coverInterval(obj);
	
	rangeMap.removeByKey(tableId);
	rangeMap.put(tableId,objs);
}
/* 时间范围选择，内容改变事件函数 */
function inputChange(tableType,tableId,obj,inputIdx){
		if(tableType == "range"){
			var objs = null;
			if(rangeMap.containsKey(tableId) == false){
				objs = new photoSetObj();
			} else {
				objs = rangeMap.get(tableId);
			}
			
			var eachStartTime = Number(coverMinites(objs.startTime),10);
			var eachEndTime = Number(coverMinites(objs.endTime),10);
			var bakStartTime = Number(coverMinites(objs.bakStartTime),10);
			var bakEndTime = Number(coverMinites(objs.bakEndTime),10);
			var timeValue = Number(coverMinites(obj.value),10);
			
			
			var date = new Date();
			var curWeek = date.getDay();
			var curHours = Number(coverMinites(date.getHours()+":"+date.getMinutes()),10);
			var boxWeek = numToWeek(curWeek);
			
			if(inputIdx == "0"){
				if(objs.setId != null){
					if(objs.backStartTime == objs.startTime){
						if(eachStartTime != 0 && eachStartTime <= curHours && timeValue <= curHours ){//&& eachStartTime==curHours
							jQuery("#"+tableId).find("td[attr='message']").css("display","none");
							jQuery("#"+tableId).find("td[attr='message']").html("");
						} else if((bakStartTime != 0 && bakStartTime < curHours && bakEndTime > curHours)&& timeValue > curHours && eachStartTime!=curHours && objs.weeks.indexOf(curWeek)>-1){
							jQuery("#"+tableId).find("td[attr='message']").html("<span style='color:red;'>*请检查，当天星期"+boxWeek+",开始时间应小于当前时间！</span>");
							jQuery("#"+tableId).find("td[attr='message']").css("display","block");
							//tishiShow("请检查，当天星期"+boxWeek+",开始时间应小于当前时间！");
						} 
					} else {
						if(bakStartTime != 0 && bakStartTime <= curHours && timeValue <= curHours ){//&& bakStartTime==curHours
							jQuery("#"+tableId).find("td[attr='message']").css("display","none");
							jQuery("#"+tableId).find("td[attr='message']").html("");
						} else if((bakStartTime != 0 && bakStartTime < curHours && bakEndTime > curHours) && timeValue > curHours && bakStartTime!=curHours &&  objs.weeks.indexOf(curWeek) > -1){
							jQuery("#"+tableId).find("td[attr='message']").html("<span style='color:red;'>*请检查，当天星期"+boxWeek+",开始时间应小于当前时间！</span>");
							jQuery("#"+tableId).find("td[attr='message']").css("display","block");
	// 						tishiShow("请检查，当天星期"+boxWeek+",开始时间应小于当前时间！");
						} 
					}
				}
				if(objs.bakStartTime == null || objs.bakStartTime == ""){
					objs.bakStartTime = obj.value;
				}
				objs.startTime = obj.value;
			} else {
				if(objs.setId != null){
					if(objs.bakEndTime == objs.endTime){
						if((eachEndTime != 0 && eachEndTime >= curHours && timeValue >= curHours) && (timeValue-curHours) >= 5){// && eachEndTime==curHours
							jQuery("#"+tableId).find("td[attr='message']").css("display","none");
							jQuery("#"+tableId).find("td[attr='message']").html("");//&& timeValue <= curHours
						} else if((eachEndTime != 0 && eachEndTime >= curHours ) && (timeValue -curHours) < 5 && eachEndTime!=curHours && objs.weeks.indexOf(curWeek) > -1){
							jQuery("#"+tableId).find("td[attr='message']").html("<span style='color:red;'>*请检查，当天星期"+boxWeek+",结束时间应大于当前时间，且间隔>=5分钟！</span>");
							jQuery("#"+tableId).find("td[attr='message']").css("display","block");
	// 						tishiShow("请检查，当天星期"+boxWeek+",结束时间应大于当前时间，且间隔>=5分钟！");
						}
					} else {
						if((bakEndTime != 0 && bakEndTime >= curHours && eachEndTime >= curHours) && (timeValue -curHours) >= 5){// && bakEndTime==curHours
							jQuery("#"+tableId).find("td[attr='message']").css("display","none");
							jQuery("#"+tableId).find("td[attr='message']").html("");//&& eachEndTime <= curHours
						} else if((bakEndTime != 0 && bakEndTime > curHours && bakStartTime < curHours ) && (timeValue -curHours)<5 && bakEndTime!=curHours && objs.weeks.indexOf(curWeek) > -1){
							jQuery("#"+tableId).find("td[attr='message']").html("<span style='color:red;'>*请检查，当天星期"+boxWeek+",结束时间应大于当前时间，且间隔>=5分钟！</span>");
							jQuery("#"+tableId).find("td[attr='message']").css("display","block");
	// 						tishiShow("请检查，当天星期"+boxWeek+",结束时间应大于当前时间，且间隔>=5分钟！");
						}
					}
				}
				if(objs.bakEndTime == null || objs.bakEndTime == ""){
					objs.bakEndTime = obj.value;
				}
				objs.endTime = obj.value;
			}
			if(objs.weeks.indexOf(curWeek) > -1 && !jQuery("#"+tableId).find("input[atr="+curWeek+"]").attr("checked")){
				jQuery("#"+tableId).find("input[atr="+curWeek+"]").attr("checked","checked");
				tishiShow("请检查，当天星期"+boxWeek+",当前时间在该设置范围内,暂不可修改！");
				return ;
			}
			
			rangeMap.removeByKey(tableId);
			rangeMap.put(tableId,objs);
			if(objs.startTime != undefined || objs.endTime != undefined){
				return;
			}
			if(objs.startTime.length != 0 && objs.endTime.length != 0){
				if(coverMinites(objs.startTime)<coverMinites(objs.endTime)){
					jQuery(obj.parentNode).children().removeClass("maxRedBorder");
					jQuery("#"+tableId).find("td[attr='message']").css("display","none");
					jQuery("#"+tableId).find("td[attr='message']").html("");
				} else {
					jQuery(obj.parentNode).children().addClass("maxRedBorder");
					jQuery("#"+tableId).find("td[attr='message']").html("<span style='color:red;'>*请检查，结束时间应大于开始时间！</span>");
					jQuery("#"+tableId).find("td[attr='message']").css("display","block");
// 					tishiShow("<span style='color:red;'>*请检查，结束时间应大于开始时间！</span>");
					//return;
				}
			}
			
		} else {
			var objs = null;
			if(pointMap.containsKey(tableId) == false){
				objs = new photoSetObj();
			} else {
				objs = pointMap.get(tableId);
			}
			objs.startTime = obj.value;
			objs.endTime = obj.value;
			pointMap.removeByKey(tableId);
			pointMap.put(tableId,objs);
		}
}

function deleteSetTable(objId){
	var rObjs = rangeMap.get(objId.id);
	if(rObjs && rObjs.setId != null){
		if(confirm("您确定要删除该定时拍照设置？")) {
			var weekT = rObjs.weeks;
			var ids = rObjs.setId;
			var date = new Date();
			var curWeek = date.getDay();
			var curHours = Number(coverMinites(date.getHours()+":"+date.getMinutes()),10);
			
			var eachStartTime = Number(coverMinites(rObjs.bakStartTime==null?rObjs.startTime:rObjs.bakStartTime),10);
			var eachEndTime = Number(coverMinites(rObjs.bakEndTime==null?rObjs.endTime:rObjs.bakEndTime),10);
			
			if(weekT.indexOf(curWeek) > -1 && jQuery("#"+objId.id).find("input[atr="+curWeek+"]").attr("ids")!=null && (eachStartTime <= curHours && eachEndTime >= curHours)){
				var boxWeek = numToWeek(curWeek);
				jQuery("#"+objId.id).find("td[attr='message']").html("<span style='color:red;'>*请检查，当天星期"+boxWeek+",当前时间在该设置范围内，不可删除！</span>");
				jQuery("#"+objId.id).find("td[attr='message']").css("display","block");
// 				tishiShow("请检查，当天星期"+boxWeek+",当前时间在该设置范围内，不可删除！");
				return false;
			} else {
				jQuery("#"+objId.id).find("td[attr='message']").html("");
				jQuery("#"+objId.id).find("td[attr='message']").css("display","none");
			}
		
			jQuery.ajax({
				  type: 'POST',  
				  url: '<s:url value="/photograph/deleteSetIds.shtml" />',	
				  data: {setIds: ids},	
				  success: function(data) {
					if(data.message == "success"){
						top.searchFresh();
						top.tishiWin("车辆删除定时拍照设置范围成功！");
						//cancelArt();
					} else if(data.message == "error") {
						top.searchFresh();
						top.tishiWin("车辆删除定时拍照设置范围失败！");
						//cancelArt();
					}
			      },
				  error:function (){
			    	//jQuery.unblockUI();  
			      }
			});
		}
		
	}
		jQuery(objId).remove();
		if(objId.id.indexOf("photoSetTable") > -1){
			rangeMap.removeByKey(objId.id);
			//rangeTableCount--;
		} else {
			pointMap.removeByKey(objId.id);
			//pointTableCount--;
		}
}

/* 关闭窗口 */
function cancelArt(){
	top.cancelArt();
}

/* 执行确定操作 */
function addPhotoSet(){
	//重新收集数据(开始、结束时间，时间间隔)
	jQuery("table[id^=photoSetTable]").each(function(){
		var id = jQuery(this).attr("id");
		var objs = rangeMap.get(id);
		if(objs){
			jQuery(this).find("input").each(function(){
				if(jQuery(this).attr("name")=="startTime"){
					objs.startTime=jQuery(this).val();
				}else if(jQuery(this).attr("name")=="endTime"){
					objs.endTime=jQuery(this).val();
				}else if(jQuery(this).attr("name")=="interval"){
					objs.interval=jQuery(this).val();
				}
			});
			rangeMap.removeByKey(id);
			rangeMap.put(id,objs);
		}
	});
	rangeParam = "";
	var rangeBoo = rangeChecked();
	var pointBoo = pointChecked();
	if(!rangeBoo){
		//tishiShow("请检查，时间范围设置有误！");
		return ;
	}
	if(!pointBoo){
		//tishiShow("请检查，定点时间设置有误！");
		return ;
	}
	var objs = rangeMap.values();
	for(var i = 0,len = objs.length; i < len;i++){
		rangeParam = rangeParam + objs[i].toString()+ "!";
	}
	pointParam = "";
	var pointObjs = pointMap.values();
	for(var i = 0,len = pointObjs.length; i < len; i++){
		if(len != (i+1)){
			pointParam = pointParam + pointObjs[i].toString() + "!";
		} else {
			pointParam = pointParam + pointObjs[i].toString();
		}
	}
	jQuery.blockUI();
		jQuery.ajax({
	 		  type: 'POST',  
	 		  url: '<s:url value="/photograph/addPhotoSet.shtml" />',	
	 		  data: {rangeObjStr : rangeParam,pointObjStr : pointParam, vehicleVin: jQuery("#vehicleVin").val()},	
	 		 success: function(data) {
	 			 if(data.message == "success"){
	 				top.searchFresh();
		 			jQuery.unblockUI();
		 			top.tishiWin("定时拍照设置成功！");
		 			//cancelArt();
		 			document.location.reload();
	 			 } else {
	 				jQuery.unblockUI();
	 				top.tishiWin("定时拍照设置失败！");
	 				//cancelArt();
	 			 }
		      },
			  error:function (){
		    	jQuery.unblockUI();  
		      }
	  	});
	
}

function pointChecked(){
	var boo = true;
// 	jQuery("#point").find("table[id=photoSetPointTable]").each(function(i){
		var keys = pointMap.keys();
		for(var i = 0,len = keys.length; i<len;i++){
			var objs = pointMap.get(keys[i]);
			var boxArr = objs.weeks.split(",");
			if(objs.startTime == undefined || objs.startTime.length == 0){
				jQuery("#"+keys[i]).find("input[id$=Time]").addClass("crossRedBorder");
				jQuery("#"+keys[i]).find("td[attr='message']").html("<span style='color:red;'>*请检查，您有未设置时间的空项！</span>");
				jQuery("#"+keys[i]).find("td[attr='message']").css("display","block");
// 				tishiShow("请检查，您有未设置时间的空项！");
				return false;
			} else {
				jQuery("#"+keys[i]).find("input[id$=Time]").removeClass("crossRedBorder");
				jQuery("#"+keys[i]).find("td[attr='message']").html("");
				jQuery("#"+keys[i]).find("td[attr='message']").css("display","none");
			}
// 			if(!boo){
// 				break;
// 			}
			for(var j = 0,boxLen = boxArr.length; j < boxLen; j++){
				var boxVal = boxArr[j];
				for(var k = 0, tlen = keys.length; k < tlen ;k++){
					var tObj = pointMap.get(keys[k]);
					weekVals = tObj.weeks;
					if(keys[k] != keys[i] && weekVals.indexOf(boxVal) != -1){
						if(coverMinites(objs.startTime) != coverMinites(tObj.startTime)){
							jQuery("#"+keys[i]).find("input[id$=Time]").removeClass("crossRedBorder");
							jQuery("#"+keys[i]).find("td[attr='message']").css("display","none");
							jQuery("#"+keys[i]).find("td[attr='message']").html("");
						} else {
							jQuery("#"+keys[i]).find("input[id$=Time]").removeClass("crossRedBorder");
							jQuery("#"+keys[i]).find("td[attr='message']").html("<span style='color:red;'>*请检查，定点拍照时间设置有重复！</span>");
							jQuery("#"+keys[i]).find("td[attr='message']").css("display","block");
// 							tishiShow("请检查，定点拍照时间设置有重复！");
							boo = false;
							return boo;
						}
					}
					
				}
			}
		}
// 	});
	return boo;
}

function rangeChecked(){
	var boo = true;
// 	jQuery("#range").find("table[id^=photoSetTable]").each(function(i){
	var keys = rangeMap.keys();
	for(var k = 0, vLen = keys.length;k < vLen;k++ ){
		var rObjs = rangeMap.get(keys[k]);
		var boxArr = rObjs.weeks.split(",");
		if(rObjs.weeks == undefined || rObjs.weeks.length == 0){
			jQuery("#"+keys[k]).find("td[attr='message']").html("<span style='color:red;'>*请检查，您有未设置的周范围！</span>");
			jQuery("#"+keys[k]).find("td[attr='message']").css("display","block");
// 			tishiShow("请检查，您有未设置的周范围!");
			return false;
		}else{
			jQuery("#"+keys[k]).find("td[attr='message']").html("");
			jQuery("#"+keys[k]).find("td[attr='message']").css("display","none");
		}
		if(rObjs.startTime == undefined || rObjs.endTime == undefined ||
				rObjs.startTime.length == 0 || rObjs.endTime.length == 0){
			jQuery("#"+keys[k]).find("td[attr='message']").html("<span style='color:red;'>*请检查，您有未设置的时间范围！</span>");
			jQuery("#"+keys[k]).find("td[attr='message']").css("display","block");
// 			tishiShow("请检查，您有未设置的时间范围!");
			return false;
		}else{
			jQuery("#"+keys[k]).find("td[attr='message']").html("</span>");
			jQuery("#"+keys[k]).find("td[attr='message']").css("display","none");
		}
		if(rObjs.interval == undefined || trimSpace(rObjs.interval).length == 0){
			jQuery("#"+keys[k]).find("td[attr='message']").html("<span style='color:red;'>*请检查，您有未设置的拍照间隔！</span>");
			jQuery("#"+keys[k]).find("td[attr='message']").css("display","block");
// 			tishiShow("请检查，您有未设置的拍照间隔！");
			return false;
		}else{
			jQuery("#"+keys[k]).find("td[attr='message']").html("");
			jQuery("#"+keys[k]).find("td[attr='message']").css("display","none");
		}
		if(rObjs.interval < 5){
			jQuery("#"+keys[k]).find("td[attr='message']").html("<span style='color:red;'>*请检查，您有设置的拍照间隔格式<5分钟！</span>");
			jQuery("#"+keys[k]).find("td[attr='message']").css("display","block");
// 			tishiShow("请检查，您有设置的拍照间隔格式<5分钟！");
			return false;
		}else{
			jQuery("#"+keys[k]).find("td[attr='message']").html("");
			jQuery("#"+keys[k]).find("td[attr='message']").css("display","none");
		}
		if(rObjs.interval.indexOf(".") > -1){
			jQuery("#"+keys[k]).find("td[attr='message']").html("<span style='color:red;'>*请检查，您有设置的拍照间隔格式为小数！</span>");
			jQuery("#"+keys[k]).find("td[attr='message']").css("display","block");
// 			tishiShow("请检查，您有设置的拍照间隔格式为小数！");
			return false;
		}else{
			jQuery("#"+keys[k]).find("td[attr='message']").html("");
			jQuery("#"+keys[k]).find("td[attr='message']").css("display","none");
		}
		var eachStartTime = coverMinites(rObjs.startTime);
		var eachEndTime = coverMinites(rObjs.endTime);
		var bakStartTime= coverMinites(rObjs.bakStartTime);
		var bakEndTime = coverMinites(rObjs.bakEndTime);
		
		if(eachStartTime != 0 && eachEndTime != 0){
			if(coverMinites(rObjs.startTime)<coverMinites(rObjs.endTime)){
				jQuery("input[name$=Time]",jQuery("#"+keys[k])).removeClass("maxRedBorder");
				jQuery("#"+keys[k]).find("td[attr='message']").css("display","none");
				jQuery("#"+keys[k]).find("td[attr='message']").html("");
			} else {
				jQuery("input[name$=Time]",jQuery("#"+keys[k])).addClass("maxRedBorder");
				jQuery("#"+keys[k]).find("td[attr='message']").html("<span style='color:red;'>*请检查，结束时间不能小于开始时间！</span>");
				jQuery("#"+keys[k]).find("td[attr='message']").css("display","block");
// 				tishiShow("请检查，结束时间不能小于开始时间！");
				boo = false;
				return boo;
			}
		}
		
		for(var j = 0,boxLen = boxArr.length; j < boxLen; j++){
			var boxVal = boxArr[j];
			var date = new Date();
			var curWeek = date.getDay();
			var curHours = coverMinites(date.getHours()+":"+date.getMinutes());
			var boxWeek = numToWeek(curWeek);
			if(rObjs.setId == null){
// 				if(curWeek == boxVal && (bakStartTime <= curHours && eachEndTime >= curHours) && (eachEndTime - curHours) < 5){
// 					var boxWeek = numToWeek(curWeek);
// 					tishiShow("请检查，当天星期"+boxWeek+",结束时间应大于当前时间5分钟！");
// 					return false;
// 				}
			} else {
				if((bakStartTime != 0 && bakStartTime < curHours && bakEndTime > curHours) && eachStartTime>curHours && bakStartTime!=curHours && boxArr.join(",").indexOf(String(curWeek)) > -1){
					jQuery("#"+keys[k]).find("td[attr='message']").html("<span style='color:red;'>*请检查，当天星期"+boxWeek+",开始时间应小于当前时间！</span>");
					jQuery("#"+keys[k]).find("td[attr='message']").css("display","block");
// 					tishiShow("请检查，当天星期"+boxWeek+",开始时间应小于当前时间！");
					return false;
				}else{
					jQuery("#"+keys[k]).find("td[attr='message']").css("display","none");
					jQuery("#"+keys[k]).find("td[attr='message']").html("");
				}
				if((bakEndTime != 0 && bakEndTime > curHours && bakStartTime < curHours)  && boxArr.join(",").indexOf(String(curWeek)) > -1
						&& (eachEndTime-curHours) < 5 && bakEndTime!=curHours){
					// 
					jQuery("#"+keys[k]).find("td[attr='message']").html("<span style='color:red;'>*请检查，当天星期"+boxWeek+",结束时间应大于当前时间，且间隔>=5分钟！</span>");
					jQuery("#"+keys[k]).find("td[attr='message']").css("display","block");
// 					tishiShow("请检查，当天星期"+boxWeek+",结束时间应大于当前时间，且间隔>=5分钟！");
					return false;
				}else{
					jQuery("#"+keys[k]).find("td[attr='message']").css("display","none");
					jQuery("#"+keys[k]).find("td[attr='message']").html("");
				}
			}
				//var keys = rangeMap.containsKey();
				for(var i = 0,len = keys.length; i < len; i++){
					var tmpBool = true;
					var objs = rangeMap.get(keys[i]);
					weekVals = objs.weeks;
					if(keys[i] != keys[k] && weekVals.indexOf(boxVal) != -1){
						var startTime = coverMinites(objs.startTime);
						var endTime = coverMinites(objs.endTime);
						if(eachStartTime >= startTime && eachStartTime <= endTime){
							jQuery("input[name$=Time]",jQuery("#"+keys[k])).addClass("crossRedBorder");
							jQuery("#"+keys[k]).find("td[attr='message']").html("<span style='color:red;'>*请检查，设置的时间范围有冲突！</span>");
							jQuery("#"+keys[k]).find("td[attr='message']").css("display","block");
// 							tishiShow("请检查，设置的时间范围有冲突！");
							tmpBool = false;
							boo = false;
							return boo;
						} else {
							jQuery("input[name$=Time]",jQuery("#"+keys[k])).removeClass("crossRedBorder");
							jQuery("#"+keys[k]).find("td[attr='message']").css("display","none");
							jQuery("#"+keys[k]).find("td[attr='message']").html("");
						}
						if(!tmpBool){
							continue;
						}
						if(eachEndTime >= startTime && eachEndTime <= endTime){
							jQuery("input[name$=Time]",jQuery("#"+keys[k])).addClass("crossRedBorder");
							jQuery("#"+keys[k]).find("td[attr='message']").html("<span style='color:red;'>*请检查，设置的时间范围有冲突！</span>");
							jQuery("#"+keys[k]).find("td[attr='message']").css("display","block");
// 							tishiShow("请检查，设置的时间范围有冲突！");
							boo = false;
							return boo;
						} else {
							jQuery("input[name$=Time]",jQuery("#"+keys[k])).removeClass("crossRedBorder");
							jQuery("#"+keys[k]).find("td[attr='message']").css("display","none");
							jQuery("#"+keys[k]).find("td[attr='message']").html("");
						}
						
					}
				}
				
		}
// 		tishiShow("时间范围设置不正确！");
// 	});
	}
	return boo;
}


function photoSetObj(data1,data2,data3,data4,data5,data6){
	this.setId = data6;
	this.tableName = data5;
	this.weeks = data1;
	this.startTime = data2;
	this.endTime = data3;
	this.bakStartTime = data2;
	this.bakEndTime = data3;
	this.interval = data4;
	this.toString = function(){
		return this.weeks+"#"+this.startTime+"#"+this.endTime+"#"+this.interval;
	};
}



/* 验证时间格式24小时制    例: 08:29  */
function checkedTime(times){
	var isReturn = false;
	if(times.indexOf(":") != -1){
		var arrs = times.split(":");
		if(arrs[0].length == 2 && Number(arrs[0]) < 24 && Number(arrs[0]) >= 0){
			isReturn = true;
		} else if(arrs[1].length == 2 && Number(arrs[1]) < 24 && Number(arrs[1]) >= 0){
			isReturn = true;
		} 
	}
	return isReturn;
}
/* 判断开始时间不能大于结束时间 */
function maxTime(val1,val2){
	var ars1 = val1.split(":");
	var ars2 = val2.split(":");
	if(val1 == val2){
		return false;
	} 
	if(Number(ars1[0],10) < Number(ars2[0],10)){
		return true;
	} else {
		return false;
	}
	if(Number(ars1[1],10) > Number(ars2[1],10)){
		return false;
	} else {
		return true;
	}
	return true;
}
function trimSpace(val){
	return val.replace(/\s/g,'');
}
/* 把时间转变为分钟     为判断时间范围 */
function coverMinites(val){
	if(val == undefined){
		return 0;
	}
	var arrs = val.split(":");
	return Number(arrs[0]*60,10)+Number(arrs[1],10);
}

/* 时间间隔验证 */
function coverInterval(obj){
	var parObj = obj.parentNode.parentNode.parentNode.parentNode;
	if(Number(obj.value,10) && obj.value.indexOf(".") == -1){
		jQuery(obj).removeClass("intervalFormatRedBorder");
		jQuery(parObj).find("td[attr='message']").css("display","none");
		jQuery(parObj).find("td[attr='message']").html("");
	} else {
		jQuery(obj.value).addClass("intervalFormatRedBorder");
		jQuery(parObj).find("td[attr='message']").html("<span style='color:red;'>*时间间隔格式不正确!</span>");
		jQuery(parObj).find("td[attr='message']").css("display","block");
// 		tishiShow("时间间隔格式不正确!");
		return ;
	}
	if(Number(obj.value,10) <5 ){
		jQuery(obj).addClass("intervalFormatRedBorder");
		jQuery(parObj).find("td[attr='message']").html("<span style='color:red;'>*时间间隔不能小于5分钟！</span>");
		jQuery(parObj).find("td[attr='message']").css("display","block");
// 		tishiShow("时间间隔不能小于5分钟！");
		return ;
	} else {
		jQuery(obj).removeClass("intervalFormatRedBorder");
		jQuery(parObj).find("td[attr='message']").css("display","none");
		jQuery(parObj).find("td[attr='message']").html("");
	}
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
	window.setTimeout("hideshowresultDiv(1)",2000);
}

function initRange(){	
	var tableHtml = "<table id=\"photoSetTable\"  width=\"525px\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">"
	+"<tr style=\"height: 25px;\">"
	+"<td style=\"color: black;\" colspan=\"3\">"
	+"<ul class=\"weeksli\">"
	+"<li><input type=\"checkbox\" atr='1' name=\"week1\" onclick=\"checkBoxChange('photoSetTable','0',this)\" />周一</li>"
	+"<li><input type=\"checkbox\" atr='2' name=\"week2\" onclick=\"checkBoxChange('photoSetTable','0',this)\" />周二</li>"
	+"<li><input type=\"checkbox\" atr='3' name=\"week3\" onclick=\"checkBoxChange('photoSetTable','0',this)\" />周三</li>"
	+"<li><input type=\"checkbox\" atr='4' name=\"week4\" onclick=\"checkBoxChange('photoSetTable','0',this)\" />周四</li>"
	+"<li><input type=\"checkbox\" atr='5' name=\"week5\" onclick=\"checkBoxChange('photoSetTable','0',this)\" />周五</li>"
	+"<li><input type=\"checkbox\" atr='6' name=\"week6\" onclick=\"checkBoxChange('photoSetTable','0',this)\" />周六</li>"
	+"<li><input type=\"checkbox\" atr='7' name=\"week7\" onclick=\"checkBoxChange('photoSetTable','0',this)\" />周日</li>"
	+"</ul>"
	+"</td>"
	+"</tr>"
	+"<tr>"
	+"<td style=\"padding-left: 60px;width: 290px;\">"
	+"拍照时间范围：<input type=\"text\" name=\"startTime\" value=\"\" style=\"width: 70px;\" readonly=\"readonly\" onfocus=\"WdatePicker({dateFmt:'HH:mm'})\" onchange=\"inputChange('range','photoSetTable',this,'0')\" />"
	+"&nbsp;------&nbsp;"
	+"<input type=\"text\" name=\"endTime\" value=\"\" style=\"width: 70px;\" readonly=\"readonly\" onfocus=\"WdatePicker({dateFmt:'HH:mm'})\" onchange=\"inputChange('range','photoSetTable',this,'1')\" />"
	+"</td>"
	+"<td>"
	+"拍照间隔：<input type=\"text\" name=\"interval\" value=\"\" style=\"width: 30px;\" onchange=\"inputInterval('photoSetTable',this)\" />"
	+"</td>"
	+"<td style=\"width: 40px;\">&nbsp;";
	if(!isEditSet){
		tableHtml = tableHtml +"<a href=\"javascript:void(0);\" onclick=\"deleteSetTable(this.parentNode.parentNode.parentNode.parentNode)\" style=\"margin-left: 10px;\">删除</a>";
	}
	tableHtml = tableHtml +"</td>"
	+"</tr>"
	+"<tr><td attr='message' valign=\"middle\" colspan=\"3\" style='padding-left: 70px;'></td></tr>"
	+"<tr name=\"hrs\" valign=\"middle\"><td valign=\"middle\" colspan=\"3\">&nbsp;<hr style=\"height: 2px solid gray;\"/></td></tr>"
	+"</table>";
	jQuery("#range").html(tableHtml);
}
function initPoint(){
	var tableHtml = "<table id=\"photoSetPointTable\"  width=\"525px\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">"
	+"<tr style=\"height: 25px;\">"
	+"<td style=\"color: black;\" colspan=\"3\">"
	+"<ul class=\"weeksli\">"
	+"<li><input type=\"checkbox\" atr='1' name=\"week1\" onclick=\"checkBoxChange('photoSetPointTable','1',this)\" />周一</li>"
	+"<li><input type=\"checkbox\" atr='2' name=\"week2\" onclick=\"checkBoxChange('photoSetPointTable','1',this)\" />周二</li>"
	+"<li><input type=\"checkbox\" atr='3' name=\"week3\" onclick=\"checkBoxChange('photoSetPointTable','1',this)\" />周三</li>"
	+"<li><input type=\"checkbox\" atr='4' name=\"week4\" onclick=\"checkBoxChange('photoSetPointTable','1',this)\" />周四</li>"
	+"<li><input type=\"checkbox\" atr='5' name=\"week5\" onclick=\"checkBoxChange('photoSetPointTable','1',this)\" />周五</li>"
	+"<li><input type=\"checkbox\" atr='6' name=\"week6\" onclick=\"checkBoxChange('photoSetPointTable','1',this)\" />周六</li>"
	+"<li><input type=\"checkbox\" atr='7' name=\"week7\" onclick=\"checkBoxChange('photoSetPointTable','1',this)\" />周日</li>"
	+"</ul>"
	+"</td>"
	+"</tr>"
	+"<tr>"
	+"<td style=\"padding-left: 60px;width: 290px;\">"
	+"拍照时间：<input type=\"text\" name=\"startTime\" value=\"\" style=\"width: 70px;\" readonly=\"readonly\" onfocus=\"WdatePicker({dateFmt:'HH:mm'})\" onchange=\"inputChange('point','photoSetPointTable',this,'0')\" />"
//		+"------"
//		+"<input type=\"input\" name=\"endTime\" value=\"\" style=\"width: 70px;\"  "+cssStyle+"/>"
	+"</td>"
	+"<td>&nbsp;"
//		+"拍照间隔<input type=\"input\" name=\"interval\" value=\"\" style=\"width: 30px;\"  "+cssStyle+"/>"
	+"</td>"
	+"<td style=\"width: 40px;\">&nbsp;";
	if(!isEditSet){
		tableHtml = tableHtml +"<a href=\"javascript:void(0);\" onclick=\"deleteSetTable(this.parentNode.parentNode.parentNode.parentNode)\" style=\"margin-left: 10px;\">删除</a>";
	}
tableHtml = tableHtml +"</td>"
	+"</tr>"
	+"<tr><td attr='message' valign=\"middle\" colspan=\"3\" style='padding-left: 70px;'></td></tr>"
	+"<tr name=\"hrs\" valign=\"middle\"><td valign=\"middle\" colspan=\"3\">&nbsp;<hr style=\"height: 2px solid gray;\"/></td></tr>"
	+"</table>";
jQuery("#point").html(tableHtml);
}

function isEdit(){
	if(jQuery("#isOperator").val() == "2"){
		isEditSet = true;
		jQuery("#addPoint").css("display","none");
		jQuery("#addRange").css("display","none");
	} else {
		isEditSet = false;
		jQuery("#addPoint").css("display","");
		jQuery("#addRange").css("display","");
	}
}

function numToWeek(val){
	if(val == "1"){
		return "一";
	} else if(val == "2"){
		return "二";
	} else if(val == "3"){
		return "三";
	} else if(val == "4"){
		return "四";
	} else if(val == "5"){
		return "五";
	} else if(val == "6"){
		return "六";
	} else if(val == "7"){
		return "日";
	}
}
var isEditSet = true;
jQuery(function() {
	isEdit();
	initRange();
	initPoint();
	if(isEditSet){
		//jQuery("#cancelButton").removeAttr("onclick");
		jQuery("#ysButton").css("display","none");
		jQuery("#delButton").css("display","none");
	} 
	if(jQuery("#isOperator").val() != "3"){
		searchSignPhotoSet();
	}
});
</script>
