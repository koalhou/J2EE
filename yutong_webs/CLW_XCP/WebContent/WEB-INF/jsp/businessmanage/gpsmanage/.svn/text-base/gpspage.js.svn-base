window.setInterval("searchAlarm()",30000);

	var checkValue="";
	function clickEnterEvent(obj) {
		checkValue=obj.id;
		search(1);
		$("pageBarOld").style.display="none";
		$("pageDiv").style.display="";
	}
	
	var pageSize="";
	//分页获取查询到的记录 
	function search(pageNo) {
		if(!$('pageSizeEle')){
			pageSize="10";
		}else{
			pageSize = $('pageSizeEle').value;
		}
		GPSDwr.getVehcileList(checkValue, pageNo, pageSize, {
			
			callback : function(data) {
			DWRUtil.removeAllRows("GPSVehicle"); //移除页面表格中的记录           
				addTableRows(data.data); //把返回的数据添加到表格中，   
				showPageBar(data, pageNo);//调用显示页面导航的函数        
				for(var i=0; i<$('pageSizeEle').options.length; i++){
					 if($('pageSizeEle').options[i].value == pageSize){
						 $('pageSizeEle').options[i].selected = true;
					 }
				}
				for(var i=0; i<$('jumpPage').options.length; i++){
					 if($('jumpPage').options[i].value == pageNo){
						 $('jumpPage').options[i].selected = true;
					 }
				}
			},
			errorHandler : function(msg, ex) {
				alert(msg);
			}
			});
	}
	
	function addTableRows(data){
		DWRUtil.addRows('GPSVehicle', data, [ function(data) {

		    var tab=document.createElement("<input name='carChoice' type='checkbox'  value="+data.vehicle_vin+" checked/>");
		   			return tab;
			}, function(data) {
			return data.vehicle_ln;
		},function(data) {
			return data.vehicle_vin;
		}]);
	}
	
	var enterprise="";
	function searchAlarm() {
	
		if(enterprise==""&&document.getElementById("enterprise_id")!=null){
			enterprise=document.getElementById("enterprise_id").value;
		}
		
		GPSDwr.alarmList(enterprise,{
			callback : function(data) {
			DWRUtil.removeAllRows("tablebody"); //移除页面表格中的记录           
			addAlarmTableRows(data.data);
			},
			errorHandler : function(msg, ex) {
				alert(msg);
			}
			});
	}

	function addAlarmTableRows(data){
		DWRUtil.addRows('tablebody', data, [ function(data) {
			
			return data.vehicle_ln;
		},function(data) {
			
			return data.alarm_type_name;
		},function(data) {
			
			return data.alarm_type_comments;
		},function(data) {
			
			return data.alarm_time;
		},function(data) {
			
			return data.deal_flag;
		}]);
	}

	//显示页码导航
	function showPageBar(data, pageNo) {
		var pageCount = Math.ceil(data.recordCount / pageSize);
		//总页数     
		var pageStr = "共 " + data.recordCount + " 条记录     每页显示";

		pageStr+="<select name='pageSizeEle' onchange='javascript:search(1)'><option value='10'>"+10+"</option><option value='20'>"+20+"</option><option value='30'>"+30+"</option><option value='40'>"+40+"</option><option value='50'>"+50+"</option></select>";
		pageStr+="条  当前第 " + pageNo + "/"
				+ pageCount + " 页 ";
		if (pageNo > 1) {
			pageStr += "<a href='javascript:search(1)'>首页</a> <a href='javascript:search("
					+ (pageNo - 1) + ")'>上一页</a> ";
		}else{
			pageStr+="首页 &nbsp;上一页 ";
		}
		if (pageNo < pageCount) {
			pageStr += "<a href='javascript:search(" + (pageNo + 1)
					+ ")'>下一页</a> <a href='javascript:search(" + pageCount
					+ ")'>尾页</a> ";
		}else{
			pageStr+="下一页&nbsp;尾页 ";
		}
		pageStr += "&nbsp;跳转到";
		pageStr+="<select name='jumpPage' onchange='javascript:search(this.value)'>";
			for(var i=1;i<=pageCount;i++){
				pageStr+="<option value="+i+">"+i+"</option>";
			}
			pageStr+="</select>";
		$('pageDiv').innerHTML = pageStr;

	}

	/**
	 * 车辆全选控制
	 */
	function setOrCancelSelect(obj) {
		var carChoiceObjs = document.getElementsByName("carChoice");
		
		if(obj.checked) {
		    for(var i=0; i<carChoiceObjs.length; i++) {
		    	if(carChoiceObjs[i].type=="checkbox") {
		    		carChoiceObjs[i].checked=true; 
		    	} 
		    }
		} else {
			for(var i=0; i<carChoiceObjs.length; i++) {
				if(carChoiceObjs[i].type=="checkbox") {
					carChoiceObjs[i].checked=false; 
		    	} 
		    }
		}
	}