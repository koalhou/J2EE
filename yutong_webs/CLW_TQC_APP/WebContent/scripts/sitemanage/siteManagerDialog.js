function formatNumber(num,pattern){
  var strarr = num?num.toString().split('.'):['0'];
  var fmtarr = pattern?pattern.split('.'):[''];
  var retstr='';

  // 整数部分
  var str = strarr[0];
  var fmt = fmtarr[0];
  var i = str.length-1; 
  var comma = false;
  for(var f=fmt.length-1;f>=0;f--){
    switch(fmt.substr(f,1)){
      case '#':
        if(i>=0 ) retstr = str.substr(i--,1) + retstr;
        break;
      case '0':
        if(i>=0) retstr = str.substr(i--,1) + retstr;
        else retstr = '0' + retstr;
        break;
      case ',':
        comma = true;
        retstr=','+retstr;
        break;
    }
  }
  if(i>=0){
    if(comma){
      var l = str.length;
      for(;i>=0;i--){
        retstr = str.substr(i,1) + retstr;
        if(i>0 && ((l-i)%3)==0) retstr = ',' + retstr;
      }
    }
    else retstr = str.substr(0,i+1) + retstr;
  }

  retstr = retstr+'.';
  // 处理小数部分
  str=strarr.length>1?strarr[1]:'';
  fmt=fmtarr.length>1?fmtarr[1]:'';
  i=0;
  for(var f=0;f<fmt.length;f++){
    switch(fmt.substr(f,1)){
      case '#':
        if(i<str.length) retstr+=str.substr(i++,1);
        break;
      case '0':
        if(i<str.length) retstr+= str.substr(i++,1);
        else retstr+='0';
        break;
    }
  }
  return retstr.replace(/^,+/,'').replace(/\.$/,'');
}
var sitManagerDialog = function() {
	this.weeksOptions = this.getDefaultWeek();
	this.monthsOptions = this.getDefaultMonth();
};

sitManagerDialog.prototype = {
	
	addOilTimer: function(){
		var agree =$('#setOilTimers').attr("checked")?1:0;
		var addOilRate =$('#addOilCount').val();
		var setStartTimers =$('#setStartTimers').val();
		var setEndTimers =$('#setEndTimers').val();
		if(parseInt(setEndTimers)<parseInt(setStartTimers)){
			alert("加油开始时间不能大于结束时间");
			return;
		}
		jQuery.ajax({
			type: 'POST', 
			url: '../sitGrid/addOilTimerConfig.shtml',	
			dataType: 'json', 
			data: 
				{addOilRate:addOilRate,
				startTimeQuantum:setStartTimers,
				endTimeQuantum:setEndTimers,
				oilRateCheck:agree
				}
			,
			success: function(data) {
				if(data.resultStr=="success"){
					alert("加油时段提醒设置成功!");
					//$("#amWeeks").val(setStartTimers + " -- " + setEndTimers);
					//parent.sitManagerObj.getSiteConfig(); //初始化父页面
					//parent.sitManagerObj.cancelArtWinClosed("addOilTimerWin");
				}
				if(data.resultStr=="error"){
					alert("加油时段提醒设置失败!");
					//parent.sitManagerObj.cancelArtWinClosed("addOilTimerWin");
				}
			}
		});
	},
	
	addLowerVal: function(){
		var oilValueCheck=$('#oilValueCheck').attr("checked")?1:0;
		var lowOilValue = $("#lowerOilValue").val();
		var oilPrice = $("#oilPrice").val();
		
		
		
		
		if($.trim(oilPrice).length==0){
			alert("请设置油价!");
			return false;
		}
		if($.trim(oilPrice)>99.99){
			alert("油价设置不合理，请重新设置小于100数值!");
			return false;
		}
		
		
		if(!(new RegExp(/^\d+\.?\d{0,2}$/).test(oilPrice))){
			alert("油价为数值，格式如：8.05");
			return false;
		}
		jQuery.ajax({
			type: 'POST', 
			url: '../sitGrid/updateLowerOil.shtml',	
			dataType: 'json', 
			data: 
				{lowOilValue:lowOilValue,
				oilValueCheck:oilValueCheck,
				oilPrice:oilPrice}
			,
			success: function(data) {
				if(data.resultStr=="success"){
					alert("低油量提醒设置成功!");
					//parent.sitManagerObj.getSiteConfig(); //初始化父页面
					//parent.sitManagerObj.cancelArtWinClosed("addLowerValWin");
				}
				if(data.resultStr=="error"){
					alert("低油量提醒设置失败!");
					//parent.sitManagerObj.cancelArtWinClosed("addLowerValWin");
				}
			}
		});
	},

	getWeekOrMonth: function(obj){
		jQuery("#setStartTimers").length = 0;
		jQuery("#setEndTimers").length = 0;
		var tmpStr = obj;
		if(tmpStr == "1"){
			//jQuery("#setStartTimers").html(this.monthsOptions);
			//jQuery("#setEndTimers").html(this.monthsOptions);
		} else if(tmpStr == "2" || tmpStr.length == 0){
			//jQuery("#setStartTimers").html(this.weeksOptions);
			//jQuery("#setEndTimers").html(this.weeksOptions);
		}
	},
	getDefaultWeek: function(){
		var weekVal = "<option value='1' selected=\"selected\">周一</option>";
		weekVal = weekVal + "<option value='2'>周二</option>";
		weekVal = weekVal + "<option value='3'>周三</option>";
		weekVal = weekVal + "<option value='4'>周四</option>";
		weekVal = weekVal + "<option value='5'>周五</option>";
		weekVal = weekVal + "<option value='6'>周六</option>";
		weekVal = weekVal + "<option value='7'>周日</option>";
		return weekVal;
	},
	getDefaultMonth: function(){
		var monthVal = "";
		var isChecked = "selected=\"selected\"";
		for(var i = 1; i <= 31; i++){
			if(i != 1){
				isChecked = "";
			}
			monthVal = monthVal + "<option value='"+i+"' "+isChecked+">"+(i+"号")+"</option>";
		}
		return monthVal;
	}
};




var sitManagerDialogObj = null;
jQuery(document).ready(function(){
	sitManagerDialogObj = new sitManagerDialog();
	jQuery.ajax({
		type: 'POST', 
		url: '../sitGrid/selectOilTimerConfig.shtml',	
		dataType: 'json', 
		data: 
			{}
		,
		success: function(data) {
			if(data.length==1){
				$("#addOilCount").val(data[0].addOilRate);
				//sitManagerDialogObj.getWeekOrMonth(data[0].addOilRate);
				$("#setStartTimers").val(data[0].startTimeQuantum);
				$("#setEndTimers").val(data[0].endTimeQuantum);
				if($.trim(data[0].oilPrice).length>0){
					
					$("#oilPrice").val(formatNumber(data[0].oilPrice,'#.00'));
				}
				
				
				if(data[0].oilRateCheck=='1'){
					$("#setOilTimers").attr("checked","checked");
				}
				$("#lowerOilValue").val(data[0].lowOilValue);
				if(data[0].oilValueCheck=='1'){
					$("#oilValueCheck").attr("checked","checked");
				}
			}
		}
	});
	
});

