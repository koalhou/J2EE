<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
  function changeChoose(id){
	if(id=="terminalParamsBrowse"){
		document.getElementById("terminalParamsBrowse").className="current2";
		document.getElementById("terminalParamsSet").className="";
		document.getElementById("queryTerminalParams").style.display="block";
		document.getElementById("setTerminalParams").style.display="none";
	} else {
		document.getElementById("terminalParamsSet").className="current2";
		document.getElementById("terminalParamsBrowse").className="";
		document.getElementById("queryTerminalParams").style.display="none";
		document.getElementById("setTerminalParams").style.display="block";
	}
  }

  /** 获取终端参数操作 **/
  function queryTerminalParams(url) {
    if(confirm("<s:property value="getText('params.query.confirm')" />")) {
      Wit.goBack(url);
    }
  }
  
  function setFormEvent() {
	  if(document.getElementById("currentPage").value == "") {
		  changeChoose("terminalParamsBrowse");
	  } else {
		  changeChoose("terminalParamsSet");
	  }
	  
	  Mat.setDefault($('keepAliveTime'),'');
	  Mat.setDefault($('tcpOverTime'),'');
	  Mat.setDefault($('tcpRetransTime'),'');
	  Mat.setDefault($('udpOverTime'),'');
	  Mat.setDefault($('udpRetransTime'),'');
	  Mat.setDefault($('positionUpType'),'');
	  Mat.setDefault($('positionUpSchema'),'');
	  Mat.setDefault($('sleepDateTime'),'');
	  Mat.setDefault($('sosTime'),'');
	  Mat.setDefault($('defaultDateTime'),'');
	  Mat.setDefault($('defaultSpaceTime'),'');
	  Mat.setDefault($('driverOverSpaceTime'),'');
	  Mat.setDefault($('sleepSpaceTime'),'');
	  Mat.setDefault($('sosSpaceTime'),'');
	  Mat.setDefault($('makeUpAngle'),'');
	  Mat.setDefault($('mainApn'),'');
	  Mat.setDefault($('mainUser'),'');
	  Mat.setDefault($('mainPass'),'');
	  Mat.setDefault($('standbyApn'),'');
	  Mat.setDefault($('standbyUser'),'');
	  Mat.setDefault($('standbyPass'),'');
	  Mat.setDefault($('mainIp'),'');
	  Mat.setDefault($('tcpPort'),'');
	  Mat.setDefault($('udpPort'),'');
	  Mat.setDefault($('monitorPhone'),'');
	  Mat.setDefault($('resetPhone'),'');
	  Mat.setDefault($('resetFactory'),'');
	  Mat.setDefault($('monitorSmsPhone'),'');
	  Mat.setDefault($('terminalSmsPhone'),'');
	  Mat.setDefault($('terminalPhoneTactic'),'');
	  Mat.setDefault($('callTimePer'),'');
	  Mat.setDefault($('callTimeMonth'),'');
	  Mat.setDefault($('odometer'),'');
	  Mat.setDefault($('mediaQuality'),'');
	  Mat.setDefault($('luminance'),'');
	  Mat.setDefault($('contrast'),'');
	  Mat.setDefault($('saturation'),'');
	  Mat.setDefault($('chroma'),'');
	  Mat.setDefault($('alarmShield'),'');
	  Mat.setDefault($('alarmSmsSwitch'),'');
	  Mat.setDefault($('alarmShootSwitch'),'');
	  Mat.setDefault($('alarmShootSaveFlag'),'');
	  Mat.setDefault($('keyFlag'),'');
  }

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
   * 验证位置汇报策略
   */
  function checkPositionUpType() {
    var elm = $('positionUpType');
    if(Wit.checkErr(elm,/^[0-2]?$/)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "请输入0-2之间数字");
      return false;
    }
  }

  /**
   * 验证位置汇报方案
   */
  function checkPositionUpSchema() {
    var elm = $('positionUpSchema');
    if(Wit.checkErr(elm,/^[0-1]?$/)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "请输入0-1之间数字");
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
    if(Wit.checkErr(elm,/^(\d)*$/)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "请输入0-180之间数字");
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
   * 验证终端电话接听策略
   */
  function checkTerminalPhoneTactic() {
    var elm = $('terminalPhoneTactic');
    if(Wit.checkErr(elm,/^[0-1]?$/)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "请输入0-1之间数字");
      return false;
    }
  }

  /**
   * 验证每次最长通话时间
   */
  function checkCallTimePer() {
    var elm = $('callTimePer');
    if(Wit.checkErr(elm,/^(\d)*$/)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "请输入数字");
      return false;
    }
  }  

  /**
   * 验证当月最长通话时间
   */
  function checkCallTimeMonth() {
    var elm = $('callTimeMonth');
    if(Wit.checkErr(elm,/^(\d)*$/)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "请输入数字");
      return false;
    }
  }

  /**
   * 验证图像/视频质量
   */
  function checkMediaQuality() {
    var elm = $('mediaQuality');
    if(Wit.checkErr(elm,/^(\d)*$/)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "请输入数字");
      return false;
    }
  }

  /**
   * 验证亮度
   */
  function checkLuminance() {
    var elm = $('luminance');
    if(Wit.checkErr(elm,/^(\d)*$/)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "请输入数字");
      return false;
    }
  }

  /**
   * 验证对比度
   */
  function checkContrast() {
    var elm = $('contrast');
    if(Wit.checkErr(elm,/^(\d)*$/)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "请输入数字");
      return false;
    }
  }

  /**
   * 验证饱和度
   */
  function checkSaturation() {
    var elm = $('saturation');
    if(Wit.checkErr(elm,/^(\d)*$/)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "请输入数字");
      return false;
    }
  }

  /**
   * 验证色度
   */
  function checkChroma() {
    var elm = $('chroma');
    if(Wit.checkErr(elm,/^(\d)*$/)){
      Mat.showSucc(elm);
      return true;
    } else {
      Wit.showErr(elm, "请输入数字");
      return false;
    }
  }
  
  function setFormMessage() {
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
    $('positionUpType').onkeyup = checkPositionUpType;
    $('positionUpType').onblur = checkPositionUpType;
    $('positionUpSchema').onkeyup = checkPositionUpSchema;
    $('positionUpSchema').onblur = checkPositionUpSchema;
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
    $('terminalPhoneTactic').onkeyup = checkTerminalPhoneTactic;
    $('terminalPhoneTactic').onblur = checkTerminalPhoneTactic;
    $('callTimePer').onkeyup = checkCallTimePer;
    $('callTimePer').onblur = checkCallTimePer;
    $('callTimeMonth').onkeyup = checkCallTimeMonth;
    $('callTimeMonth').onblur = checkCallTimeMonth;
    $('mediaQuality').onkeyup = checkMediaQuality;
    $('mediaQuality').onblur = checkMediaQuality;
    $('luminance').onkeyup = checkLuminance;
    $('luminance').onblur = checkLuminance;
    $('contrast').onkeyup = checkContrast;
    $('contrast').onblur = checkContrast;
    $('saturation').onkeyup = checkSaturation;
    $('saturation').onblur = checkSaturation;
    $('chroma').onkeyup = checkChroma;
    $('chroma').onblur = checkChroma;
  }

  /** 判断是否有录入项目 **/
  function checkInputText() {
    inputObjs=document.body.getElementsByTagName("INPUT");
	for(var i=0;i<inputObjs.length;i++){
	  if(inputObjs[i].type=='text' && inputObjs[i].value.length > 0 ) {
        return true;
	  }
	}
	return false;
  }
  
  /** 提交form **/
  function submitForm() {
	trimAllObjs();

    var f1 = checkKeepAliveTime();
    var f2 = checkTcpOverTime();
    var f3 = checkTcpRetransTime();
    var f4 = checkUdpOverTime();
    var f5 = checkUdpRetransTime();
    var f6 = checkPositionUpType();

    var f7 = checkPositionUpSchema();
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
    var f18 = checkTerminalPhoneTactic();

    var f19 = checkCallTimePer();
    var f20 = checkCallTimeMonth();
    var f21 = checkMediaQuality();
    var f22 = checkLuminance();
    var f23 = checkContrast();
    var f24 = checkSaturation();
    var f25 = checkChroma();

    var f26 = checkInputText();
    if(!f26) {
      alert("未设置任何参数，请输入！");
      return false;
    }
    
	if (f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8 && f9 && f10 && 
		f11 && f12 && f13 && f14 && f15 && f16 && f17 && f18 && f19 && f20 && 
		f21 && f22 && f23 && f24 && f25) {
		if(confirm("<s:property value="getText('params.set.confirm')" />")) {
		  Wit.commitAll($('terminalParams_form'));
		} else {
		  return false;
		}
	} else  {
	  return false;
	}
  }
  
  /** 返回 **/
  function goBack(url) {
    Wit.goBack(url);
  }
  
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>