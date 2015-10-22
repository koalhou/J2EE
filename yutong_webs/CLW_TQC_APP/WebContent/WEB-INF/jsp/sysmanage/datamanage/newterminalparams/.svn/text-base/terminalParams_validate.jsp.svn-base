<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script>
	   /**
	    * 验证最高速度
	    */
	   function checkTopSpeed() {
	     var elm = $('topSpeed');
	     if(elm.value.length>0) {
		     if(!Wit.checkErr(elm,/^(\d)*$/) || elm.value<40 || elm.value>180){
		    	 Wit.showErr(elm, "请输入40-180的数字");
		    	 return false;
		     } else {
		    	 Mat.showSucc(elm);
			     return true;
		     }
	     } else {
	    	 return true;
	     }
	   }

	   /**
	    * 验证超速持续时间
	    */
	   function checkOverspeedTime() {
	     var elm = $('overspeedTime');
	     if(elm.value.length>0) {
	     if(!Wit.checkErr(elm,/^(\d)*$/) || elm.value<1 || elm.value>60){
	    	 Wit.showErr(elm, "请输入1-60的数字");
	    	 return false;
	     } else {
	    	 Mat.showSucc(elm);
		     return true;
	     }
	     }else {
	    	 return true;
	     }
	   }
	   /**
	    * 验证核载人数
	    */
	   function checkstandardCnt() {
	     var elm = $('standardCnt');
	     if(parseInt(elm.value) > 999 ) {
	    	 Wit.showErr(elm, "请输入小于1000数字");
		     return false;
	     }
	     
	     if(Wit.checkErr(elm,/^(\d)*$/)){
	       Mat.showSucc(elm);
	       return true;
	     } else {
	       Wit.showErr(elm, "请输入数字");
	       return false;
	     }
	   }
	   /**
	    * 超速报警预警差值
	    */
	   function checkOverspeedAlarmDifference() {
			var elm = $('overspeedAlarmDifference');
		  if(elm.value.length>0) {
		     if(!Wit.checkErr(elm,/^(\d)*$/) || elm.value<1 || elm.value>40){
		    	 Wit.showErr(elm, "请输入1-40的数字");
		    	 return false;
		     } else {
		    	 Mat.showSucc(elm);
			     return true;
		     }
		   }else {
		    	 return true;
		     }
	   }

	   /**
	    * 定时时间
	    */
	   function checkRegularTime() {
	     var elm = $('regularTime');
	     if(Wit.checkErr(elm,/^(\d)*$/)){
	    	 if(elm.value<5) {
	    		 Wit.showErr(elm, "最小值5min");
	  	       return false;
	    	 }
	       Mat.showSucc(elm);
	       return true;
	     } else {
	       Wit.showErr(elm, "请输入数字");
	       return false;
	     }
	   }

	   /**
	    * 定距距离
	    */
	   function checkFixDistance() {
	     var elm = $('fixDistance');
	     if(Wit.checkErr(elm,/^(\d)*$/)){
	    	 if(elm.value<5) {
	    		 Wit.showErr(elm, "最小值5km");
	  	       return false;
	    	 }
	       Mat.showSucc(elm);
	       return true;
	     } else {
	       Wit.showErr(elm, "请输入数字");
	       return false;
	     }
	   }

	function setTerminalParamsEvent() {
	  Mat.setDefault($('topSpeed'),'');
	  Mat.setDefault($('overspeedTime'),'');
	  Mat.setDefault($('listenPhone'),'');
	  Mat.setDefault($('overspeedAlarmDifference'),'');
	  Mat.setDefault($('regularTime'),'');
	  Mat.setDefault($('fixDistance'),'');
	  Mat.setDefault($('autoSwitchTrip'),'');
	}

	function setTerminalParamsMessage() {
      $('topSpeed').onkeyup = checkTopSpeed;
      $('topSpeed').onblur = checkTopSpeed;
      $('overspeedTime').onkeyup = checkOverspeedTime;
      $('overspeedTime').onblur = checkOverspeedTime;
      $('overspeedAlarmDifference').onkeyup = checkOverspeedAlarmDifference;
      $('overspeedAlarmDifference').onblur = checkOverspeedAlarmDifference;
      $('regularTime').onkeyup = checkRegularTime;
      $('regularTime').onblur = checkRegularTime;
      $('fixDistance').onkeyup = checkFixDistance;
      $('fixDistance').onblur = checkFixDistance;
      
      $('standardCnt').onkeyup = checkstandardCnt;
      $('standardCnt').onblur = checkstandardCnt;

      initCheckBoxStatus();
	}

	// 修改报警屏蔽字状态
	function changAlarmShieldStatus() {
		alarmShieldObj = document.getElementById ("alarmShieldFlag");
		alarmShieldSelectObj = document.getElementsByName("alarmShieldBit");
		if(alarmShieldObj.checked==true) {
			for(var i=0;i<alarmShieldSelectObj.length;i++){
				alarmShieldSelectObj[i].disabled = false;
			}
		} else {
			for(var i=0;i<alarmShieldSelectObj.length;i++){
				alarmShieldSelectObj[i].disabled = true;
			}
		}
	}

	// 修改报警拍摄开关状态 
	function changeAlarmShootStatus() {
		alarmShootObj = document.getElementById ("alarmShootFlag");
		alarmShootSelectObj = document.getElementsByName("alarmShootSwitchBit");
		if(alarmShootObj.checked==true) {
			for(var i=0;i<alarmShootSelectObj.length;i++){
				alarmShootSelectObj[i].disabled = false;
			}
		} else {
			for(var i=0;i<alarmShootSelectObj.length;i++){
				alarmShootSelectObj[i].disabled = true;
			}
		}
	}

	// 修改报警存储标志
	function changeAlarmShootSaveStatus() {
		alarmShootSaveObj = document.getElementById ("alarmShootSave");
		alarmShootSaveSelectObj = document.getElementsByName("alarmShootSaveFlagBit");
		if(alarmShootSaveObj.checked==true) {
			for(var i=0;i<alarmShootSaveSelectObj.length;i++){
				alarmShootSaveSelectObj[i].disabled = false;
			}
		} else {
			for(var i=0;i<alarmShootSaveSelectObj.length;i++){
				alarmShootSaveSelectObj[i].disabled = true;
			}
		}
	}

	// 修改门开关拍照控制
	function changeCarDoorStatus() {
		carDoorObj = document.getElementById ("carDoorFlag");
		carDoorSelectObj = document.getElementsByName("carDoorControlBit");
		if(carDoorObj.checked==true) {
			for(var i=0;i<carDoorSelectObj.length;i++){
				carDoorSelectObj[i].disabled = false;
			}
		} else {
			for(var i=0;i<carDoorSelectObj.length;i++){
				carDoorSelectObj[i].disabled = true;
			}
		}
	}

	// 修改定时拍照控制
	function changeregularCameraStatus() {
		regularCameraObj = document.getElementById ("regularCameraFlag");
		regularCameraSelectObj = document.getElementsByName("regularCameraControl");
		regularTimeObj = document.getElementById ("regularTime");
		
		if(regularCameraObj.checked==true) {
			for(var i=0;i<regularCameraSelectObj.length;i++){
				regularCameraSelectObj[i].disabled = false;
			}
			regularTimeObj.disabled = false;
		} else {
			for(var i=0;i<regularCameraSelectObj.length;i++){
				regularCameraSelectObj[i].disabled = true;
			}
			regularTimeObj.disabled = true;
		}
	}

	// 定距拍照控制
	function changeFixDistanceStatus() {
		fixDistanceObj = document.getElementById ("fixDistanceFlag");
		fixDistanceSelectObj = document.getElementsByName("fixDistanceCameraControlBit");
		distanceObj = document.getElementById ("fixDistance");
		
		if(fixDistanceObj.checked==true) {
			for(var i=0;i<fixDistanceSelectObj.length;i++){
				fixDistanceSelectObj[i].disabled = false;
			}
			distanceObj.disabled = false;
		} else {
			for(var i=0;i<fixDistanceSelectObj.length;i++){
				fixDistanceSelectObj[i].disabled = true;
			}
			distanceObj.disabled = true;
		}
	}

	// 初始化复选框状态
	function initCheckBoxStatus() {
		changAlarmShieldStatus();
		changeAlarmShootStatus();
		changeAlarmShootSaveStatus();
		changeCarDoorStatus();
		changeregularCameraStatus();
		changeFixDistanceStatus();
	}
	
	// 判断参数是否有输入项目
	function checkParamsInput() {
		var ret = false;
		// 判断页面上的下拉类信息是否选择
		selectObjs=document.body.getElementsByTagName("select");
		for(var i=0;i<selectObjs.length;i++){
			  if(selectObjs[i].value.length > 0) {
				  ret = true;
				  break;
			  }
		}

		// 判断文本类信息是否录入
		inputObjs=document.body.getElementsByTagName("INPUT");
		for(var i=0;i<inputObjs.length;i++){
			  if(inputObjs[i].type=='text' && 
			     inputObjs[i].value.length > 0 && 
			     inputObjs[i].name != "enterpriseName" && // 企业查询
			     inputObjs[i].name != "vehicleLn" && // 车牌查询
			     inputObjs[i].id != "regularTime" && // 定时
			     inputObjs[i].id != "fixDistance" && // 定距
			     inputObjs[i].id != "topSpeed" && // 最高速度
			     inputObjs[i].id != "overspeedTime" && // 超速持续时间
			     inputObjs[i].id != "overspeedAlarmDifference" && // 超速报警预警差值
			     inputObjs[i].size != 4) { // 表格翻页栏中text
				  ret = true;
				  break;
			  }
		}

		// 判断复选类告警是否有选择
		alarmShieldObj = document.getElementById ("alarmShieldFlag");
		alarmShootObj = document.getElementById ("alarmShootFlag");
		alarmShootSaveObj = document.getElementById ("alarmShootSave");
		carDoorObj = document.getElementById ("carDoorFlag");
		regularCameraObj = document.getElementById ("regularCameraFlag");
		fixDistanceObj = document.getElementById ("fixDistanceFlag");
		if(alarmShieldObj.checked==true || 
		   alarmShootObj.checked==true || 
		   alarmShootSaveObj.checked==true ||
		   carDoorObj.checked==true ||
		   regularCameraObj.checked==true ||
		   fixDistanceObj.checked==true) {
			   ret = true;
		}
		
		if(formatSpecialChar(document.getElementById ("topSpeed").value)!='') {
			return true;
		} else if(formatSpecialChar(document.getElementById ("overspeedTime").value)!='') {
			return true;
		} else if(formatSpecialChar(document.getElementById ("overspeedAlarmDifference").value)!='') {
			return true;
		}

		return ret;
	}
	
	/** 清空页面 **/
	function clearPage() {
		inputObjs=document.body.getElementsByTagName("INPUT");
		selectObjs=document.body.getElementsByTagName("select");
		
		for(var i=0;i<inputObjs.length;i++){
			  if(inputObjs[i].type=='text' && inputObjs[i].value.length > 0 && inputObjs[i].name != "vehicleLn" && inputObjs[i].size != 4) {
				  inputObjs[i].value = "";
			  }
		}
		
		for(var i=0;i<inputObjs.length;i++){
			  if(inputObjs[i].checked==true && inputObjs[i].name != "carChoice" ) {
				  inputObjs[i].checked = false;
			  }
		}
		
		for(var i=0;i<selectObjs.length;i++){
			  if(selectObjs[i].value.length > 0) {
				  selectObjs[i].value = "";
			  }
		}
		
		// 初始化复选框类信息
		changAlarmShieldStatus();
		changeAlarmShootStatus();
		changeAlarmShootSaveStatus();
		changeCarDoorStatus();
		changeregularCameraStatus();
		changeFixDistanceStatus();
	}
	
	function vehicleIsChecked() {
		var carsChoice = document.getElementsByName("carChoice");
	    var carIdList = "";
	    for(var i=0; i<carsChoice.length; i++) {
	        if(carsChoice[i].checked==true) {
	            if(carIdList=="") {
	            	//carIdList = "'" + carsChoice[i].value + "'";
	            	carIdList =carsChoice[i].value;
	            } else {
	            	//carIdList=carIdList + "," + "'" + carsChoice[i].value  + "'";
	            	carIdList=carIdList + "," + carsChoice[i].value;
	            }
	        }
	    }
	
	    if(carIdList==""){
			alert("请选择车辆进行参数设置！");
			return false;
		} else {
			document.getElementById("carIdList").value = carIdList;
			return true;
		}
	}

	/** 判断是否单选车辆 **/
	function vehicleIsSingleChecked() {
		var carsChoice = document.getElementsByName("carChoice");		
		if(carsChoice == null){
			return false;
		}
		var carIdList = "";
		var carCnt = 0;
		for(var i=0; i<carsChoice.length; i++) {
	        if(carsChoice[i].checked==true) {
		        carCnt = carCnt + 1;
		        if(carCnt > 1) {
					break;
		        }
	            if(carIdList=="") {
	            	//carIdList = "'" + carsChoice[i].value + "'";
	            	carIdList =carsChoice[i].value;
	            } else {
	            	//carIdList=carIdList + "," + "'" + carsChoice[i].value  + "'";
	            	carIdList=carIdList + "," + carsChoice[i].value;
	            }
	        }
	    }
		
		if( carCnt == 1) {
			document.getElementById("carIdList").value = carIdList;
			return true;
		}else{
			alert("请选择一台车辆！");
			return false;
		}
	    
	}

	// 验证定时时间是否填写(有复选时，必须填写)
	function checkRegularCameraControl() {
		var hasChecked = false;
		var elm = $('regularTime');
		if(document.getElementsByName("regularCameraFlag")[0].checked == true) {
			var regularCameraControlChoice = document.getElementsByName("regularCameraControl");
			for(var i = 0; i < regularCameraControlChoice.length; i++ ) {
				if(regularCameraControlChoice[i].checked==true) {
					hasChecked = true;
					break;
				}
			}
	
			if(hasChecked) {
				return true;
			} else {
				return false;//return checkRegularTime();
			}
		} else {
			return true;
		}
	}

	// 验证定距距离是否填写(有复选时，必须填写)
	function checkFixDistanceCameraControl() {
		var hasChecked = false;
		var elm = $('fixDistance');
		if(document.getElementsByName("fixDistanceFlag")[0].checked == true) {
			var fixDistanceCameraControlChoice = document.getElementsByName("fixDistanceCameraControlBit");
			for(var i = 0; i < fixDistanceCameraControlChoice.length; i++ ) {
				if(fixDistanceCameraControlChoice[i].checked==true) {
					hasChecked = true;
					break;
				}
			}
	
			if(hasChecked) {
				return true;
			} else {
				return false;//return checkFixDistance();
			}
		} else {
			return true;
		}
	}
	
	/** 查询参数 **/
	function queryParams() {
		if(!vehicleIsSingleChecked()) {
			return false;
		}

		document.getElementById("currentpage").value = jQuery('#carList').flex_current_page();
		document.getElementById("currentsortname").value = jQuery('#carList').flex_sortname();
		document.getElementById("currentsortorder").value = jQuery('#carList').flex_sortorder();
		$('terminalparams_form').action="<s:url value='/zdnew/queryTerminalParams.shtml' />";
		Wit.commitAll($('terminalparams_form'));
	}

	
	function setLaterData() {
		var laterTime = jQuery("#laterTimes1").val()+"!"+jQuery("#laterTimes2").val();
		var laterTimeEnt = jQuery("#laterTimes1Ent").val()+"!"+jQuery("#laterTimes2Ent").val();
		jQuery.ajax({
			type : 'POST',
			url : "../zdnf/setParameterLater.shtml",
			dataType : "json",
			data : {
				"laterTime": laterTime,
				"laterTimeEnt": laterTimeEnt
			},
			success : function(data) {
				if(data.returnStr == "sccess"){
					submitForm();
				} else {
					alert("迟到告警参数设置失败！");
					submitForm();
				}
			}
		});
	}
	
	// 刷新参数(迟到时间)
	function freshLater() {
		jQuery.ajax({
			type : 'POST',
			url: "../treemanage/findEnterSub.shtml",
			dataType : "json",
// 			data : {
// 				"laterTimes1" : $("#laterTimes1").val(),
// 				"laterTimes2":$('#monitorType').val()
// 			},
			success : function(data) {
				if(data.length > 0){
					for(var i = 0;i < data.length; i++){
						jQuery("#laterTimes"+(i+1)+"Ent").val(data[i].id);
						jQuery("#laterTimes"+(i+1)).val(data[i].laterTimes);
						jQuery("#entIds"+(i+1)).html(data[i].name+"：");
					}
				}
			}
		});
	}
	// 刷新参数信息
	function refreshParams() {
		if(!vehicleIsSingleChecked()) {
			return false;
		}
		
		document.getElementById("currentpage").value = jQuery('#carList').flex_current_page();
		document.getElementById("currentsortname").value = jQuery('#carList').flex_sortname();
		document.getElementById("currentsortorder").value = jQuery('#carList').flex_sortorder();
		$('terminalparams_form').action="<s:url value='/zdnew/refreshTerminalParams.shtml' />";
		Wit.commitAll($('terminalparams_form'));
	}

	// 参数设置  
	function submitForm() {
		trimAllObjs();
		
		if(!checkParamsInput()) {
			alert("请设置至少一个参数");
			return false;
		}
		if(!vehicleIsChecked()) {
			return false;
		}
	    var f19 = checkTopSpeed();
	    var f20 = checkOverspeedTime();
	    var f21 = checkOverspeedAlarmDifference();
		var f28 = checkRegularCameraControl();
		var f29 = checkFixDistanceCameraControl();
		
		var f30 = checkstandardCnt();
		
		if(!f28) {
			alert("请选择定时拍照通道！");
			return false;
		} else {
			if(document.getElementsByName("regularCameraFlag")[0].checked == true) {
				if(!checkRegularTime()) {
				    return false;
				}
			}
		}
		
		if(!f29) {
			alert("请选择定距拍照通道！");
			return false;
		} else {
			if(document.getElementsByName("fixDistanceFlag")[0].checked == true) {
				if(!checkFixDistance()) {
				    return false;
				}
			}
		}
		
		if (f19 && f20 && f21 && f28 && f29 &&f30) {
			if(confirm("确定要设置终端参数信息么？")) {
			  document.getElementById("currentpage").value = jQuery('#carList').flex_current_page();
			  document.getElementById("currentsortname").value = jQuery('#carList').flex_sortname();
			  document.getElementById("currentsortorder").value = jQuery('#carList').flex_sortorder();
			  Wit.commitAll($('terminalparams_form'));
			} else {
			  return false;
			}
		} else  {
		  return false;
		}
	  }

	/** 全选 **/
	function setOrCancelSelect(obj) {
		 if(jQuery(obj).attr("checked")){
			jQuery("input[name='carChoice']").each(function(){
			jQuery("input[name='carChoice']").attr("checked","true");
			});
		 }else{
			 jQuery("input[name='carChoice']").each(function(){
				 jQuery("input[name='carChoice']").removeAttr("checked"); 
			});
		}
	}

	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
	
	function reWriteCheckBox(tdDiv,pid,row){
		//get_cell_text(pid, 2) == "1"
		if(row.cell[3] == "1" ) {
			// 是否为已选车
			//tdDiv.innerHTML = '<input name="carChoice" value="' + pid + '"  type="checkbox" checked="checked"/>';
			return '<input name="carChoice" value="' + pid + '"  type="checkbox" checked="checked"/>';
		} else {
			//tdDiv.innerHTML = '<input name="carChoice" value="' + pid + '"  type="checkbox" />';
			return '<input name="carChoice" value="' + pid + '"  type="checkbox" />';
		}
	}
	
	jQuery(function() {
		var gala = jQuery('#carList');
		gala.flexigrid({
			url: '<s:url value="/zdnf/getVehicleListByEnterpriseId.shtml" />',
			params: [{name: 'enterpriseId', value: jQuery('#enterpriseId').val() },
			         {name: 'carIdList', value: jQuery('#carIdList').val() },
			         {name: 'vehicleLn', value: jQuery('#vehicleLn').val()}],
			dataType: 'json',
			height: 240,
			width:248,
			colModel : [
	                    {display: '<input id="carChoiceAll" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', 
		                 name : '', 
		                 width : 30, 
		                 align: 'center', 
		                 process:reWriteCheckBox},
		                 {display: '班车号', name : 'VEHICLE_CODE', width : 50, sortable : true, escape:true,align: 'center'},
			    		{display: '车牌号', name : 'VEHICLE_LN', width : 100, sortable : true, escape:true,align: 'center'},
			    		{display: '', name : '', width : 50, sortable : false, align: 'left', hide:true, toggle:false}
				    	   ],
				    newp: jQuery('#currentpage').val() != "" ? jQuery('#currentpage').val() : 1,
					sortname: jQuery('#currentsortname').val() != "" ? jQuery('#currentsortname').val() : 'VEHICLE_LN',
					sortorder: jQuery('#currentsortorder').val() != "" ? jQuery('#currentsortorder').val() : 'asc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
			    	useRp: false,
			    	rp: 20,
					rpOptions : [10,20,50,100],// 可选择设定的每页结果数
			    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		});

		jQuery(window).load(function (){
			 changeWidthHeight();
			 jQuery('#carList').fixHeight();
		});			
		jQuery(window).resize(function(){
			 changeWidthHeight();
		});
		var isQuery = jQuery("#isQueryLater").val();
// 			freshLater();
// 		if(isQuery == "1"){
// 		} else if(isQuery == "3" || isQuery == "2"){
// 			setLaterData();
// 		}
	});	

	/** 查询车辆信息 **/
	function searchVehicleList() {
		jQuery('#carList').flexOptions({
			newp: 1,
			params: [{name: 'enterpriseId', value: jQuery('#enterpriseId').val() },
					 {name: 'vehicleLn', value: formatSpecialChar(jQuery('#vehicleLn').val())}]
		});
		jQuery('#carList').flexReload();
	}
	
	//获取页面宽度
	function get_page_width() {
		var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
		  return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
	}
	
	//获取页面高度
	function get_page_height() {
		var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
		  return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
	}
	
	//计算控件宽高
	function changeWidthHeight(){
		 var h = get_page_height();
		 var w = get_page_width();
		 var leftInfoDiv = document.getElementById("leftInfoDiv");
		 if(screenFlag == false && h>490) {
			 jQuery("#leftInfoDiv").height(h-107);
			 jQuery(".bDiv").height(h-130-315);
			 jQuery("#rightDiv").height(h-187);
		 } else if(screenFlag == true && h>490) {
			 jQuery("#leftTabtd").height(h-107);
			 jQuery('.bDiv').height(h-130-315);
			 jQuery("#rightDiv").height(h-187);
		 } else {
			 if(h>245) {
				 jQuery("#leftTabtd").height(h-107);
				 jQuery('.bDiv').height(0);
				 jQuery("#rightDiv").height(h-187);
			 } else {
				 jQuery("#leftTabtd").height(h);
				 jQuery('.bDiv').height(0);
				 jQuery("#rightDiv").height(h);
			 }
		 }

		 jQuery(window).mk_autoresize({
		       height_include: '#content',
		       height_exclude: ['#header', '#footer'],
		       height_offset: 0,
		       width_include: ['#header', '#content', '#footer'],
		       width_offset: 0
		    });

		 h = get_page_height();
		 w = get_page_width();
		 var leftInfoDiv = document.getElementById("leftInfoDiv");
		 if(screenFlag == false && h>490) {
			 jQuery("#leftInfoDiv").height(h-107);
			 jQuery(".bDiv").height(h-130-315);
			 jQuery("#rightDiv").height(h-187);
		 } else if(screenFlag == true && h>490) {
			 jQuery("#leftTabtd").height(h-107);
			 jQuery('.bDiv').height(h-130-315);
			 jQuery("#rightDiv").height(h-187);
		 } else {
			 if(h>245) {
				 jQuery("#leftTabtd").height(h-107);
				 jQuery('.bDiv').height(0);
				 jQuery("#rightDiv").height(h-187);
			 } else {
				 jQuery("#leftTabtd").height(h);
				 jQuery('.bDiv').height(0);
				 jQuery("#rightDiv").height(h);
			 }
		 }
	}
	
	var screenFlag = false;
	function LeftScreen(){
		debugger;
		screenFlag = true;
		var left = document.getElementById("leftInfoDiv");
		var Lefttab = document.getElementById("leftTab");
		left.style.display="none";
		Lefttab.style.display="";
		
		var searchPlanId = document.getElementById("searchPlanId");
		searchPlanId.style.display="none";
		
		var leftTabtd= document.getElementById("leftTabtd");
	    leftTabtd.style.display="";	
	    changeWidthHeight();		
	}
	function midScreen(){
		debugger;
		screenFlag = false;
		var left = document.getElementById("leftInfoDiv");
		var Lefttab = document.getElementById("leftTabtd");
		var searchPlanId = document.getElementById("searchPlanId");
		left.style.display="";
		Lefttab.style.display="none";
		searchPlanId.style.display="";
		changeWidthHeight();			
	}  
	jQuery(function() {
// 		if(jQuery("#isPaizhao").val() == "1"){
// 			jQuery("#paichaoBody").css("display","block");
// 			jQuery("#paichao").css("display","block");			
// 		} else {
// 			jQuery("#paichaoBody").css("display","none");
// 			jQuery("#paichao").css("display","none");
// 		}
	});
	window.addEvent('domready', setTerminalParamsEvent);
	window.addEvent('domready', setTerminalParamsMessage);
</script>

