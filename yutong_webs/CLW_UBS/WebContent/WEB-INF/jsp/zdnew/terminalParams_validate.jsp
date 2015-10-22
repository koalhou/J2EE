<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script>
	/**
	 * 验证终端心跳发送时间间隔
	 */
	function checkKeepAliveTime() {
	  var elm = $('keepAliveTime');
	  if(Wit.checkErr(elm,/^(\d)*$/)){
	    Mat.showSucc(elm);
	    return true;
	  } else {
	    Wit.showErr(elm, "请输入数字");
	    return false;
	  }
	}
	/**
	   * 验证TCP消息应答超时时间
	   */
	function checkTcpOverTime() {
	    var elm = $('tcpOverTime');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	      Mat.showSucc(elm);
	      return true;
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	}

	  /**
	   * 验证TCP消息重传次数
	   */
	function checkTcpRetransTime() {
	    var elm = $('tcpRetransTime');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	      Mat.showSucc(elm);
	      return true;
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	  }

	  /**
	   * 验证UDP消息应答超时时间
	   */
	  function checkUdpOverTime() {
	    var elm = $('udpOverTime');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	      Mat.showSucc(elm);
	      return true;
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	  }

	  /**
	   * 验证UDP消息重传次数
	   */
	  function checkUdpRetransTime() {
	    var elm = $('udpRetransTime');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	      Mat.showSucc(elm);
	      return true;
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	  }
	  
	  /**
	   * 验证休眠时汇报时间间隔
	   */
	  function checkSleepDateTime() {
	    var elm = $('sleepDateTime');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	      Mat.showSucc(elm);
	      return true;
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	  }
	  
	  /**
	   * 验证紧急报警时汇报时间间隔
	   */
	  function checkSosTime() {
	    var elm = $('sosTime');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	      Mat.showSucc(elm);
	      return true;
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	  }

	  /**
	   * 验证缺省时间汇报间隔
	   */
	  function checkDefaultDateTime() {
	    var elm = $('defaultDateTime');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	      Mat.showSucc(elm);
	      return true;
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	  }

	  /**
	   * 验证缺省距离汇报间隔
	   */
	  function checkDefaultSpaceTime() {
	    var elm = $('defaultSpaceTime');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	      Mat.showSucc(elm);
	      return true;
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	  }

	  /**
	   * 验证驾驶员未登录汇报距离间隔
	   */
	  function checkDriverOverSpaceTime() {
	    var elm = $('driverOverSpaceTime');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	      Mat.showSucc(elm);
	      return true;
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	  }

	  /**
	   * 验证休眠时汇报距离间隔
	   */
	  function checkSleepSpaceTime() {
	    var elm = $('sleepSpaceTime');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	      Mat.showSucc(elm);
	      return true;
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	  }

	  /**
	   * 验证紧急报警时汇报距离间隔
	   */
	   function checkSosSpaceTime() {
	     var elm = $('sosSpaceTime');
	     if(Wit.checkErr(elm,/^(\d)*$/)){
	       Mat.showSucc(elm);
	       return true;
	     } else {
	       Wit.showErr(elm, "请输入数字");
	       return false;
	     }
	   }

	   /**
	    * 验证拐点补传角度
	    */
	   function checkMakeUpAngle() {
	     var elm = $('makeUpAngle');
	     if(parseInt(elm.value) > 180 ) {
	    	 Wit.showErr(elm, "请输入小于180数字");
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
	    * 验证服务器TCP端口
	    */
	   function checkTcpPort() {
	     var elm = $('tcpPort');
	     if(Wit.checkErr(elm,/^(\d)*$/)){
	       Mat.showSucc(elm);
	       return true;
	     } else {
	       Wit.showErr(elm, "请输入数字");
	       return false;
	     }
	   }

	   /**
	    * 验证服务器UDP端口
	    */
	   function checkUdpPort() {
	     var elm = $('udpPort');
	     if(Wit.checkErr(elm,/^(\d)*$/)){
	       Mat.showSucc(elm);
	       return true;
	     } else {
	       Wit.showErr(elm, "请输入数字");
	       return false;
	     }
	   }

	   /**
	    * 验证驾驶员未登录汇报时间间隔
	    */
	   function checkDriverOverDateTime() {
	     var elm = $('driverOverDateTime');
	     if(Wit.checkErr(elm,/^(\d)*$/)){
	       Mat.showSucc(elm);
	       return true;
	     } else {
	       Wit.showErr(elm, "请输入数字");
	       return false;
	     }
	   }

	   /**
	    * 验证最高速度
	    */
	   function checkTopSpeed() {
	     var elm = $('topSpeed');
	     if(Wit.checkErr(elm,/^(\d)*$/)){
	       Mat.showSucc(elm);
	       return true;
	     } else {
	       Wit.showErr(elm, "请输入数字");
	       return false;
	     }
	   }

	   /**
	    * 验证超速持续时间
	    */
	   function checkOverspeedTime() {
	     var elm = $('overspeedTime');
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
	     if(parseInt(elm.value) > 65535 ) {
	    	 Wit.showErr(elm, "请输入小于65535数字");
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
	    * 特征系数
	    */
	   function checkCharacteristicOefficient() {
	     var elm = $('characteristicOefficient');
	     if(parseInt(elm.value) > 65535 ) {
	    	 Wit.showErr(elm, "请输入小于65535数字");
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
	    * 车轮每转脉冲数
	    */
	   function checkWheelPulseCount() {
	     var elm = $('wheelPulseCount');
	     if(parseInt(elm.value) > 65535 ) {
	    	 Wit.showErr(elm, "请输入小于65535数字");
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
	    * 邮箱容量
	    */
	   function checkFuelCapacity() {
	     var elm = $('fuelCapacity');
	     if(parseInt(elm.value) > 65535 ) {
	    	 Wit.showErr(elm, "请输入小于65535数字");
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
	    * 车辆里程表读数
	    */
	   function checkOdometer() {
	     var elm = $('odometer');
	     if(Wit.checkErr(elm,/^(\d)*$/)){
	       Mat.showSucc(elm);
	       return true;
	     } else {
	       Wit.showErr(elm, "请输入数字");
	       return false;
	     }
	   }

	   /**
	    * 定时时间
	    */
	   function checkRegularTime() {
	     var elm = $('regularTime');
	     if(parseInt(elm.value) > 65535 ) {
	    	 Wit.showErr(elm, "请输入小于65535数字");
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
	    * 定距距离
	    */
	   function checkFixDistance() {
	     var elm = $('fixDistance');
	     if(parseInt(elm.value) > 65535 ) {
	    	 Wit.showErr(elm, "请输入小于65535数字");
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

	   // 语音控制
	   function selectVoiceOutputCtrlType0() {
		   var elm0 = $('voiceOutputControlType0');
		   var elm1 = $('voiceOutputControlType1');
		   var elm2 = $('voiceOutputControlType2');
		   var elm3 = $('voiceOutputControlType3');
		   var elm4 = $('voiceOutputControlType4');
		   var elm5 = $('voiceOutputControlType5');
		   
		   if(elm0.value != "") {
			   Mat.showSucc(elm0);
		   } else {
			   // 未设置值时
			   if(elm1.value != "" || elm2.value != "" ||
				  elm3.value != "" || elm4.value != "" ||
				  elm5.value != "" ) {
				   Wit.showErr(elm0, "请填写");
			   } else {
				   Mat.showSucc(elm0);
			   }
		   }
	   }
	   function selectVoiceOutputCtrlType1() {
		   var elm0 = $('voiceOutputControlType0');
		   var elm1 = $('voiceOutputControlType1');
		   var elm2 = $('voiceOutputControlType2');
		   var elm3 = $('voiceOutputControlType3');
		   var elm4 = $('voiceOutputControlType4');
		   var elm5 = $('voiceOutputControlType5');
		   
		   if(elm1.value != "") {
			   Mat.showSucc(elm1);
		   } else {
			   // 未设置值时
			   if(elm0.value != "" || elm2.value != "" ||
				  elm3.value != "" || elm4.value != "" ||
				  elm5.value != "" ) {
				   Wit.showErr(elm1, "请填写");
			   } else {
				   Mat.showSucc(elm1);
			   }
		   }
	   }
	   function selectVoiceOutputCtrlType2() {
		   var elm0 = $('voiceOutputControlType0');
		   var elm1 = $('voiceOutputControlType1');
		   var elm2 = $('voiceOutputControlType2');
		   var elm3 = $('voiceOutputControlType3');
		   var elm4 = $('voiceOutputControlType4');
		   var elm5 = $('voiceOutputControlType5');
		   
		   if(elm2.value != "") {
			   Mat.showSucc(elm2);
		   } else {
			   // 未设置值时
			   if(elm0.value != "" || elm1.value != "" ||
				  elm3.value != "" || elm4.value != "" ||
				  elm5.value != "" ) {
				   Wit.showErr(elm2, "请填写");
			   } else {
				   Mat.showSucc(elm2);
			   }
		   }
	   }
	   function selectVoiceOutputCtrlType3() {
		   var elm0 = $('voiceOutputControlType0');
		   var elm1 = $('voiceOutputControlType1');
		   var elm2 = $('voiceOutputControlType2');
		   var elm3 = $('voiceOutputControlType3');
		   var elm4 = $('voiceOutputControlType4');
		   var elm5 = $('voiceOutputControlType5');
		   
		   if(elm3.value != "") {
			   Mat.showSucc(elm3);
		   } else {
			   // 未设置值时
			   if(elm0.value != "" || elm1.value != "" ||
				  elm2.value != "" || elm4.value != "" ||
				  elm5.value != "" ) {
				   Wit.showErr(elm3, "请填写");
			   } else {
				   Mat.showSucc(elm3);
			   }
		   }
	   }
	   function selectVoiceOutputCtrlType4() {
		   var elm0 = $('voiceOutputControlType0');
		   var elm1 = $('voiceOutputControlType1');
		   var elm2 = $('voiceOutputControlType2');
		   var elm3 = $('voiceOutputControlType3');
		   var elm4 = $('voiceOutputControlType4');
		   var elm5 = $('voiceOutputControlType5');
		   
		   if(elm4.value != "") {
			   Mat.showSucc(elm4);
		   } else {
			   // 未设置值时
			   if(elm0.value != "" || elm1.value != "" ||
				  elm2.value != "" || elm3.value != "" ||
				  elm5.value != "" ) {
				   Wit.showErr(elm4, "请填写");
			   } else {
				   Mat.showSucc(elm4);
			   }
		   }
	   }
	   function selectVoiceOutputCtrlType5() {
		   var elm0 = $('voiceOutputControlType0');
		   var elm1 = $('voiceOutputControlType1');
		   var elm2 = $('voiceOutputControlType2');
		   var elm3 = $('voiceOutputControlType3');
		   var elm4 = $('voiceOutputControlType4');
		   var elm5 = $('voiceOutputControlType5');
		   
		   if(elm5.value != "") {
			   Mat.showSucc(elm5);
		   } else {
			   // 未设置值时
			   if(elm0.value != "" || elm1.value != "" ||
				  elm2.value != "" || elm3.value != "" ||
				  elm4.value != "" ) {
				   Wit.showErr(elm5, "请填写");
			   } else {
				   Mat.showSucc(elm5);
			   }
		   }
	   }
	function setTerminalParamsEvent() {
	  Mat.setDefault($('keepAliveTime'),'');
	  Mat.setDefault($('tcpOverTime'),'');
	  Mat.setDefault($('tcpRetransTime'),'');
	  Mat.setDefault($('udpOverTime'),'');
	  Mat.setDefault($('udpRetransTime'),'');
	  Mat.setDefault($('mainApn'),'');
	  Mat.setDefault($('mainUser'),'');
	  Mat.setDefault($('mainPass'),'');
	  Mat.setDefault($('mainIp'),'');
	  Mat.setDefault($('standbyApn'),'');
	  Mat.setDefault($('standbyUser'),'');
	  Mat.setDefault($('standbyPass'),'');
	  Mat.setDefault($('standbyIp'),'');
	  Mat.setDefault($('tcpPort'),'');
	  Mat.setDefault($('udpPort'),'');
	  Mat.setDefault($('positionUpType'),'');
	  Mat.setDefault($('positionUpSchema'),'');
	  Mat.setDefault($('driverOverDateTime'),'');
	  Mat.setDefault($('sleepDateTime'),'');
	  Mat.setDefault($('sosTime'),'');
	  Mat.setDefault($('defaultDateTime'),'');
	  Mat.setDefault($('defaultSpaceTime'),'');
	  Mat.setDefault($('driverOverSpaceTime'),'');
	  Mat.setDefault($('sleepSpaceTime'),'');
	  Mat.setDefault($('sosSpaceTime'),'');
	  Mat.setDefault($('makeUpAngle'),'');
	  Mat.setDefault($('topSpeed'),'');
	  Mat.setDefault($('overspeedTime'),'');
	  Mat.setDefault($('listenPhone'),'');
	  Mat.setDefault($('overspeedAlarmDifference'),'');
	  Mat.setDefault($('characteristicOefficient'),'');
	  Mat.setDefault($('wheelPulseCount'),'');
	  Mat.setDefault($('fuelCapacity'),'');
	  Mat.setDefault($('odometer'),'');
	  //Mat.setDefault($('vehicleLnQuery'),'');
	  Mat.setDefault($('regularTime'),'');
	  Mat.setDefault($('fixDistance'),'');
	  Mat.setDefault($('vehicleLn'),'');
	  Mat.setDefault($('vehicleLnColor'),'');
	  Mat.setDefault($('speedSourceSetting'),'');
	  Mat.setDefault($('voiceOutputControlType0'),'');
	  Mat.setDefault($('voiceOutputControlType1'),'');
	  Mat.setDefault($('voiceOutputControlType2'),'');
	  Mat.setDefault($('voiceOutputControlType3'),'');
	  Mat.setDefault($('voiceOutputControlType4'),'');
	  Mat.setDefault($('voiceOutputControlType5'),'');
	  Mat.setDefault($('autoSwitchTrip'),'');
	}

	function setTerminalParamsMessage() {
      $('keepAliveTime').onkeyup = checkKeepAliveTime;
      $('keepAliveTime').onblur = checkKeepAliveTime;
      $('tcpOverTime').onkeyup = checkTcpOverTime;
      $('tcpOverTime').onblur = checkTcpOverTime;
      $('tcpRetransTime').onkeyup = checkTcpRetransTime;
      $('tcpRetransTime').onblur = checkTcpRetransTime;
      $('udpOverTime').onkeyup = checkUdpOverTime;
      $('udpOverTime').onblur = checkUdpOverTime;
      $('udpRetransTime').onkeyup = checkUdpRetransTime;
      $('udpRetransTime').onblur = checkUdpRetransTime;
      $('sleepDateTime').onkeyup = checkSleepDateTime;
      $('sleepDateTime').onblur = checkSleepDateTime;
      $('sosTime').onkeyup = checkSosTime;
      $('sosTime').onblur = checkSosTime;
      $('defaultDateTime').onkeyup = checkDefaultDateTime;
      $('defaultDateTime').onblur = checkDefaultDateTime;
      $('defaultSpaceTime').onkeyup = checkDefaultSpaceTime;
      $('defaultSpaceTime').onblur = checkDefaultSpaceTime;
      $('driverOverSpaceTime').onkeyup = checkDriverOverSpaceTime;
      $('driverOverSpaceTime').onblur = checkDriverOverSpaceTime;
      $('sleepSpaceTime').onkeyup = checkSleepSpaceTime;
      $('sleepSpaceTime').onblur = checkSleepSpaceTime;
      $('sosSpaceTime').onkeyup = checkSosSpaceTime;
      $('sosSpaceTime').onblur = checkSosSpaceTime;
      $('makeUpAngle').onkeyup = checkMakeUpAngle;
      $('makeUpAngle').onblur = checkMakeUpAngle;
      $('tcpPort').onkeyup = checkTcpPort;
      $('tcpPort').onblur = checkTcpPort;
      $('udpPort').onkeyup = checkUdpPort;
      $('udpPort').onblur = checkUdpPort;
      $('driverOverDateTime').onkeyup = checkDriverOverDateTime;
      $('driverOverDateTime').onblur = checkDriverOverDateTime;
      $('topSpeed').onkeyup = checkTopSpeed;
      $('topSpeed').onblur = checkTopSpeed;
      $('overspeedTime').onkeyup = checkOverspeedTime;
      $('overspeedTime').onblur = checkOverspeedTime;
      $('overspeedAlarmDifference').onkeyup = checkOverspeedAlarmDifference;
      $('overspeedAlarmDifference').onblur = checkOverspeedAlarmDifference;
      $('characteristicOefficient').onkeyup = checkCharacteristicOefficient;
      $('characteristicOefficient').onblur = checkCharacteristicOefficient;
      $('wheelPulseCount').onkeyup = checkWheelPulseCount;
      $('wheelPulseCount').onblur = checkWheelPulseCount;
      $('fuelCapacity').onkeyup = checkFuelCapacity;
      $('fuelCapacity').onblur = checkFuelCapacity;
      $('odometer').onkeyup = checkOdometer;
      $('odometer').onblur = checkOdometer;
      $('regularTime').onkeyup = checkRegularTime;
      $('regularTime').onblur = checkRegularTime;
      $('fixDistance').onkeyup = checkFixDistance;
      $('fixDistance').onblur = checkFixDistance;
      $('voiceOutputControlType0').onkeyup = selectVoiceOutputCtrlType0;
      $('voiceOutputControlType0').onblur = selectVoiceOutputCtrlType0;
      $('voiceOutputControlType1').onkeyup = selectVoiceOutputCtrlType1;
      $('voiceOutputControlType1').onblur = selectVoiceOutputCtrlType1;
      $('voiceOutputControlType2').onkeyup = selectVoiceOutputCtrlType2;
      $('voiceOutputControlType2').onblur = selectVoiceOutputCtrlType2;
      $('voiceOutputControlType3').onkeyup = selectVoiceOutputCtrlType3;
      $('voiceOutputControlType3').onblur = selectVoiceOutputCtrlType3;
      $('voiceOutputControlType4').onkeyup = selectVoiceOutputCtrlType4;
      $('voiceOutputControlType4').onblur = selectVoiceOutputCtrlType4;
      $('voiceOutputControlType5').onkeyup = selectVoiceOutputCtrlType5;
      $('voiceOutputControlType5').onblur = selectVoiceOutputCtrlType5;

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

	// 终端外设安装配置
	function changeTerminalOuterDeviceStatus() {
		terminalOuterDeviceObj = document.getElementById ("terminalOuterDeviceFlag");
		terminalOuterDeviceSelectObj = document.getElementsByName("terminalOuterDeviceBit");
		
		if(terminalOuterDeviceObj.checked==true) {
			for(var i=0;i<terminalOuterDeviceSelectObj.length;i++){
				terminalOuterDeviceSelectObj[i].disabled = false;
			}
		} else {
			for(var i=0;i<terminalOuterDeviceSelectObj.length;i++){
				terminalOuterDeviceSelectObj[i].disabled = true;
			}
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
		changeTerminalOuterDeviceStatus();
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
			     inputObjs[i].name != "vehicleLnQuery" && // 车牌查询
			     inputObjs[i].id != "regularTime" && // 定时
			     inputObjs[i].id != "fixDistance" && // 定距
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
		terminalOuterDeviceObj = document.getElementById ("terminalOuterDeviceFlag");
		if(alarmShieldObj.checked==true || 
		   alarmShootObj.checked==true || 
		   alarmShootSaveObj.checked==true ||
		   carDoorObj.checked==true ||
		   regularCameraObj.checked==true ||
		   fixDistanceObj.checked==true || 
		   terminalOuterDeviceObj.checked==true) {
			   ret = true;
		}

		return ret;
	}
	
	/** 清空页面 **/
	function clearPage() {
		inputObjs=document.body.getElementsByTagName("INPUT");
		selectObjs=document.body.getElementsByTagName("select");
		
		for(var i=0;i<inputObjs.length;i++){
			  if(inputObjs[i].type=='text' && 
			     inputObjs[i].value.length > 0 && 
			     inputObjs[i].name != "enterpriseName" && 
			     inputObjs[i].name != "vehicleLnQuery" &&
			     inputObjs[i].size != 4) {
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
		changeTerminalOuterDeviceStatus();
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
		} else {
			alert("请选择一台车辆！");
			return false;
		}
	    
	}

	// 验证定时时间是否填写(有复选时，必须填写)
	function checkRegularCameraControl() {
		var hasChecked = false;
		var elm = $('regularTime');
		var regularCameraControlChoice = document.getElementsByName("regularCameraControl");
		for(var i = 0; i < regularCameraControlChoice.length; i++ ) {
			if(regularCameraControlChoice[i].checked==true) {
				hasChecked = true;
				break;
			}
		}

		if(hasChecked) {
			if(Wit.checkErr(elm,/^(\d)+$/)) {
				Mat.showSucc(elm);
			    return true;
			} else {
				Wit.showErr(elm, "请填写");
			    return false;
			}
		} else {
			return checkRegularTime();
		}
	}

	// 验证定距距离是否填写(有复选时，必须填写)
	function checkFixDistanceCameraControl() {
		var hasChecked = false;
		var elm = $('fixDistance');
		var fixDistanceCameraControlChoice = document.getElementsByName("fixDistanceCameraControlBit");
		for(var i = 0; i < fixDistanceCameraControlChoice.length; i++ ) {
			if(fixDistanceCameraControlChoice[i].checked==true) {
				hasChecked = true;
				break;
			}
		}

		if(hasChecked) {
			if(Wit.checkErr(elm,/^(\d)+$/)) {
				Mat.showSucc(elm);
			    return true;
			} else {
				Wit.showErr(elm, "请填写");
			    return false;
			}
		} else {
			return checkFixDistance();
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

	// 语音输出控制
	function checkVoiceOutputControlType() {
		var elm0 = $('voiceOutputControlType0');
		var elm1 = $('voiceOutputControlType1');
		var elm2 = $('voiceOutputControlType2');
		var elm3 = $('voiceOutputControlType3');
		var elm4 = $('voiceOutputControlType4');
		var elm5 = $('voiceOutputControlType5');
		
		if(elm0.value != "" || elm1.value != "" || 
		   elm2.value != "" || elm3.value != "" || 
		   elm4.value != "" || elm5.value != "") {
			// 语音控制中有项目被选中时
			if(elm0.value != "" && elm1.value != "" && 
			   elm2.value != "" && elm3.value != "" && 
			   elm4.value != "" && elm5.value != "") {
				return true;
			} else {
				if(elm0.value == "") {
					Wit.showErr(elm0, "请填写");
				}
				if(elm1.value == "") {
					Wit.showErr(elm1, "请填写");
				}
				if(elm2.value == "") {
					Wit.showErr(elm2, "请填写");
				}
				if(elm3.value == "") {
					Wit.showErr(elm3, "请填写");
				}
				if(elm4.value == "") {
					Wit.showErr(elm4, "请填写");
				}
				if(elm5.value == "") {
					Wit.showErr(elm5, "请填写");
				}
				return false;
			}
			
			
		} else {
		   // 都未选中时
		   return true;
		}
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
		
	    var f1 = checkKeepAliveTime();
	    var f2 = checkTcpOverTime();
	    var f3 = checkTcpRetransTime();
	    var f4 = checkUdpOverTime();
	    var f5 = checkUdpRetransTime();
	    var f8 = checkSleepDateTime();
	    var f9 = checkSosTime();
	    var f10 = checkDefaultDateTime();
	    var f11 = checkDefaultSpaceTime();
	    var f12 = checkDriverOverSpaceTime();
	    var f13 = checkSleepSpaceTime();
	    var f14 = checkSosSpaceTime();
	    var f15 = checkMakeUpAngle();
	    var f16 = checkTcpPort();
	    var f17 = checkUdpPort();
	    var f18 = checkDriverOverDateTime();
	    var f19 = checkTopSpeed();
	    var f20 = checkOverspeedTime();
	    var f21 = checkOverspeedAlarmDifference();
	    var f22 = checkCharacteristicOefficient();
	    var f23 = checkWheelPulseCount();
	    var f24 = checkFuelCapacity();
		var f25 = checkOdometer();
		//var f26 = checkRegularTime();
		//var f27 = checkFixDistance();
		var f28 = checkRegularCameraControl();
		var f29 = checkFixDistanceCameraControl();
		var f30 = checkVoiceOutputControlType();
		var characteristicOefficientValue="";
		var wheelPulseCountValue="";
		var fuelCapacityValue="";
		var odometerValue="";
		var vehicleLnValue="";
		
		if (f1 && f2 && f3 && f4 && f5 && f8 && f9 && f10 && 
			f11 && f12 && f13 && f14 && f15 && f16 && f17 && f18 && f19 && f20 && 
			f21 && f22 && f23 && f24 && f25 && f28 && f29 && f30) {
			if(confirm("<s:property value="getText('params.set.confirm')" />")) {
			  document.getElementById("currentpage").value = jQuery('#carList').flex_current_page();
			  document.getElementById("currentsortname").value = jQuery('#carList').flex_sortname();
			  document.getElementById("currentsortorder").value = jQuery('#carList').flex_sortorder();
			/*
			 * modfiy start by yg 判断是否是多选车辆,来处理特征系数
			 */
			  if(document.getElementById("carIdList").value.split(',').length > 1){
				  if(!document.getElementById("setcharacteristicOefficient").checked){
				  	  characteristicOefficientValue=document.getElementById("characteristicOefficient").value;
				  	  document.getElementById("characteristicOefficient").value="";
				  }
				  if(!document.getElementById("setwheelPulseCount").checked){
					  wheelPulseCountValue=document.getElementById("wheelPulseCount").value;
				  	  document.getElementById("wheelPulseCount").value="";
				  }
				  if(!document.getElementById("setfuelCapacity").checked){
					  fuelCapacityValue=document.getElementById("fuelCapacity").value;
				  	  document.getElementById("fuelCapacity").value="";
				  }
				  if(!document.getElementById("setodometer").checked){
					  odometerValue=document.getElementById("odometer").value;
				  	  document.getElementById("odometer").value="";
				  }
				  if(!document.getElementById("setvehicleLn").checked){
					  vehicleLnValue=document.getElementById("vehicleLn").value;
				  	  document.getElementById("vehicleLn").value="";
				  }
			  }
			  Wit.commitAll($('terminalparams_form'));
			  if(document.getElementById("carIdList").value.split(',').length > 1){
				  if(!document.getElementById("setcharacteristicOefficient").checked){
					  if(characteristicOefficientValue!=""){
						  document.getElementById("characteristicOefficient").value=characteristicOefficientValue;
					  }
				  }
				  if(!document.getElementById("setwheelPulseCount").checked){
					  if(wheelPulseCountValue!=""){
						  document.getElementById("wheelPulseCount").value=wheelPulseCountValue;
					  }
				  }
				  if(!document.getElementById("setfuelCapacity").checked){
					  if(fuelCapacityValue!=""){
						  document.getElementById("fuelCapacity").value=fuelCapacityValue;
					  }
				  }
				  if(!document.getElementById("setodometer").checked){
					  if(odometerValue!=""){
						  document.getElementById("odometer").value=odometerValue;
					  }
				  }
				  if(!document.getElementById("setvehicleLn").checked){
					  if(vehicleLnValue!=""){
						  document.getElementById("vehicleLn").value=vehicleLnValue;
					  }
				  }
			  }
			/*
			 * modfiy end by yg 判断是否是多选车辆,来处理特征系数
			 */
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
	
	function reWriteCheckBox(tdDiv,pid){
		if(get_cell_text(pid, 2) == "1" ) {
			// 是否为已选车
			tdDiv.innerHTML = '<input name="carChoice" value="' + pid + '"  type="checkbox" checked="checked"/>';
		} else {
			tdDiv.innerHTML = '<input name="carChoice" value="' + pid + '"  type="checkbox" />';
		}
	}
	
	jQuery(function() {
		var gala = jQuery('#carList');
		gala.flexigrid({
			url: '<s:url value="/zdnf/getVehicleListByEnterpriseId.shtml" />',
			params: [{name: 'enterpriseId', value: jQuery('#enterpriseId').val() },
			         {name: 'carIdList', value: jQuery('#carIdList').val() },
			         {name: 'vehicleLnQuery', value: jQuery('#vehicleLnQuery').val()}],
			dataType: 'json',
			height: 240,
			width:264,
			colModel : [
	                    {display: '<input id="carChoiceAll" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', 
		                 name : '', 
		                 width : 30, 
		                 align: 'center', 
		                 process:reWriteCheckBox},
			    		{display: '车牌号', name : 'VEHICLE_LN', width : 175, sortable : true, align: 'center'},
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
	});	

	/** 查询车辆信息 **/
	function searchVehicleList() {
		jQuery('#carList').flexOptions({
			newp: 1,
			params: [{name: 'enterpriseId', value: jQuery('#enterpriseId').val() },
			         {name: 'vehicleLnQuery', value: jQuery('#vehicleLnQuery').val()}]
		});
		jQuery('#carList').flexReload();
	}
	
	//页面自适应
	(function (jQuery) {
		 jQuery(window).load(function (){
			 changeWidthHeight();
			 jQuery('#carList').fixHeight();
		});			
		 jQuery(window).resize(function(){
			 changeWidthHeight();
		});
	})(jQuery);
	//获取页面宽度
	function get_page_width() {
		 var width = 0;
		 if(jQuery.browser.msie){ 
		  width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
		 } else { 
		  width = self.innerWidth; 
		 } 
		 return width;
	}
	
	//获取页面高度
	function get_page_height() {
		 var height = 0;
		 if (jQuery.browser.msie) {
		     height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
		 } else {
		     height = self.innerHeight;
		 }
		 return height;
	}
	
	//计算控件宽高
	function changeWidthHeight(){
		 var h = get_page_height();
		 var w = get_page_width();
		 var leftInfoDiv = document.getElementById("leftInfoDiv");
		 if(screenFlag == false && h>520) {
			 jQuery("#leftInfoDiv").height(h-165);
			 jQuery(".bDiv").height(h-165-315-40);
			 jQuery("#rightDiv").height(h-245);
		 } else if(screenFlag == true && h>490) {
			 jQuery("#leftTabtd").height(h-165);
			 jQuery('.bDiv').height(h-165-315-40);
			 jQuery("#rightDiv").height(h-245);
		 } else {
			 if(h>245) {
				 jQuery("#leftTabtd").height(h-165);
				 jQuery('.bDiv').height(0);
				 jQuery("#rightDiv").height(h-245);
			 } else {
				 jQuery("#leftTabtd").height(h);
				 jQuery('.bDiv').height(0);
				 jQuery("#rightDiv").height(h);
			 }
		 }
	}
	
	var screenFlag = false;
	function LeftScreen(){
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
		screenFlag = false;
		var left = document.getElementById("leftInfoDiv");
		var Lefttab = document.getElementById("leftTabtd");
		var searchPlanId = document.getElementById("searchPlanId");
		left.style.display="";
		Lefttab.style.display="none";
		searchPlanId.style.display="";
		changeWidthHeight();			
	}  

	window.addEvent('domready', setTerminalParamsEvent);
	window.addEvent('domready', setTerminalParamsMessage);
</script>

