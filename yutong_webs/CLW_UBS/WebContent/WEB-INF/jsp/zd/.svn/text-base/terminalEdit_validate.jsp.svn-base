<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/TmAttributeDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/TerminalManageDWR.js'></script>
<script type='text/javascript' src='../dwr/interface/NoticeCoreDwr.js'></script>
<script type="text/javascript">
  /** 获取终端型号列表 **/
  function getTypeInfo() {
    var oemObj = $('oemId');
    TmAttributeDwr.getTmTypeByOemId(oemObj.value, callBackFun);

    if(oemObj.value != "") {
      Mat.showSucc(oemObj);
    }
    
    function callBackFun(data) {
      var typeObj = $('typeId');  
      DWRUtil.removeAllOptions(typeObj);  
      DWRUtil.addOptions(typeObj,{'':'<s:property value="getText('please.select')" />'});  
      DWRUtil.addOptions(typeObj,data); 
    
      var protocalObj = $('protocalId');
      DWRUtil.removeAllOptions(protocalObj);  
      DWRUtil.addOptions(protocalObj,{'':'<s:property value="getText('please.select')" />'});  
    }
  }

  /** 获取终端协议列表 **/
  function getProtocalInfo() {
    var typeObj = $('typeId');
    TmAttributeDwr.getProtocalByTypeId(typeObj.value, callBackFun);
    
    if(typeObj.value != "") {
      Mat.showSucc(typeObj);
    }
    
    function callBackFun(data) {
      var protocalObj = $('protocalId');  
      DWRUtil.removeAllOptions(protocalObj);  
      DWRUtil.addOptions(protocalObj,{'':'<s:property value="getText('please.select')" />'});  
      DWRUtil.addOptions(protocalObj,data);  
    }
  }

  /** 终端协议列表变化 **/
  function onProtocalChange() {
	var protocalObj = $('protocalId');
	if(protocalObj.value != "") {
	  Mat.showSucc(protocalObj);
	}
  }

  /** 视频厂家列表变化 **/
  function onVideoFactoryChange() {
	var obj = $('videoFactory');
	var serverIp = $('videoServerIp');
	var videoUser = $('videoUser'); 
	var videoPassword = $('videoPassword'); 
	var streamServerIp = $('streamServerIp');
	
	if(obj.value != "") {
	  Mat.showSucc(obj);
	} else {
	  serverIp.value = "";
	  videoUser.value = "";
	  videoPassword.value = "";
	  streamServerIp.value = "";
	}
	
	if(obj.value == "HI") {
    	Mat.setDefault($('streamServerIp'),'<span class="noticeMsg">*</span>');

    	streamServerIp.disabled=false;
        serverIp.value = '<s:property value="getText('video.server.hi')" />';
        videoUser.value = '<s:property value="getText('video.server.hi_user')" />';
        videoPassword.value = '<s:property value="getText('video.server.hi_password')" />';
        streamServerIp.value = '<s:property value="getText('video.server.hi_stream')" />';
    }
    if(obj.value == "DA") {
    	Mat.setDefault($('streamServerIp'),'');
    	
        serverIp.value = '<s:property value="getText('video.server.da')" />';
        videoUser.value = '<s:property value="getText('video.server.da_user')" />';
        videoPassword.value = '<s:property value="getText('video.server.da_password')" />';
        streamServerIp.value = "";
        streamServerIp.disabled=true;
    }
  }
  
  /** 终端号必填项 **/
  function checkTerminalCode() {
    var elm = $('terminalCode');

    var oemObj = $('oemId');
    if(oemObj.options[oemObj.selectedIndex].text.indexOf("本安") > 0) {
		var reg = /^[a-z-A-Z-0-9]{7}$/;
		if(reg.test(elm.value)) {
			Mat.showSucc(elm);
			return true;
		} else {
			Wit.showErr(elm, "<s:property value="getText('common.nineteen')" />");
			return false;
		}
    } else {
    	var reg = /^[a-z-A-Z-0-9]+$/;
		if(reg.test(elm.value)) {
			Mat.showSucc(elm);
			return true;
		} else {
			Wit.showErr(elm, "<s:property value="getText('common.number.string')" />");
			return false;
		}
    }
    /**
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}**/
  }

  /** 视频ID必填项 **/
  function checkVideoId() {
	  var elm = $('videoId');
	  if(Mat.checkRequired(elm)){
	      Mat.showSucc(elm);
	      return true;
		}else{
	      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	      return false;
		}
  }
  
  /** 终端厂商必选 **/
  function checkOem() {
    var oemObj = $('oemId');
    if(oemObj.value == "") {
      Wit.showErr(oemObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }
  
  /** 终端型号必选 **/
  function checkType() {
    var typeObj = $('typeId');
    if(typeObj.value == "") {
      Wit.showErr(typeObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }

  /** 终端协议必选 **/
  function checkProtocal() {
    var protocalObj = $('protocalId');
    if(protocalObj.value == "") {
      Wit.showErr(protocalObj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }

  /** 视频厂家必选 **/
  function checkVideoFactory() {
    var obj = $('videoFactory');
    if(obj.value == "") {
      Wit.showErr(obj, "<s:property value="getText('please.select')" />");
      return false;
    } else {
      return true;
    }
  }

  /** 视频地址必填项 **/
  function checkVideoServerIp() {
	  var elm = $('videoServerIp');
	    var serverInfos = $('videoServerIp').value.split(":");
	    var ipRegex = /^(((2[0-4]\d)|(25[0-5]))|(1\d{2})|([1-9]\d)|[1-9])\.(((2[0-4]\d)|(25[0-5]))|(1\d{2})|([1-9]\d)|[0-9])\.(((2[0-4]\d)|(25[0-5]))|(1\d{2})|([1-9]\d)|[0-9])\.(((2[0-4]\d)|(25[0-5]))|(1\d{2})|([1-9]\d)|[1-9])$/;
	    var portRegex = /^\d+$/;
	    
	    if(serverInfos.length == 2 && portRegex.test(serverInfos[1])) {
	    	 if(ipRegex.test(serverInfos[0]) && serverInfos[1] >= 0 && serverInfos[1] <= 65535) {
	    		 Mat.showSucc(elm);
	    		 return true;
	    	 } else {
	    		 Wit.showErr(elm, "请输入合法【IP:端口】");
	    	     return false;
	    	 }
	    } else {
	    	Wit.showErr(elm, "请输入合法【IP:端口】");
	    	return false;
	    }
  }

  /** 流媒体地址格式 **/
  function checkStreamServerIp() {
	    var elm = $('streamServerIp');
	    var serverInfos = $('streamServerIp').value.split(":");
	    var ipRegex = /^(((2[0-4]\d)|(25[0-5]))|(1\d{2})|([1-9]\d)|[1-9])\.(((2[0-4]\d)|(25[0-5]))|(1\d{2})|([1-9]\d)|[0-9])\.(((2[0-4]\d)|(25[0-5]))|(1\d{2})|([1-9]\d)|[0-9])\.(((2[0-4]\d)|(25[0-5]))|(1\d{2})|([1-9]\d)|[1-9])$/;
	    var portRegex = /^\d+$/;
	    
	    if(serverInfos.length == 2 && portRegex.test(serverInfos[1])) {
	    	 if(ipRegex.test(serverInfos[0]) && serverInfos[1] >= 0 && serverInfos[1] <= 65535) {
	    		 Mat.showSucc(elm);
	    		 return true;
	    	 } else {
	    		 Wit.showErr(elm, "请输入合法【IP:端口】");
	    	     return false;
	    	 }
	    } else {
	    	Wit.showErr(elm, "请输入合法【IP:端口】");
	    	return false;
	    }
	  }

  /** 流媒体地址 **/
  function checkStreamInfo() {
	  var obj = $('videoFactory');

	    if(obj.value == "HI") {
	    	return checkStreamServerIp();
	    } else if($('streamServerIp').value != "") {
	    	return checkStreamServerIp();
	    } else {
	    	Mat.showSucc($('streamServerIp'));
			return true;
	    }
  }
  
  /** 视频服务器用户名必填项 **/
  function checkVideoUser() {
    var elm = $('videoUser');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }

  /** 视频服务器密码必填项 **/
  function checkVideoPassword() {
    var elm = $('videoPassword');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }
  
  /** 初始化样式 **/
  function setFormMessage() {
	Mat.setDefault($('oemId'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('typeId'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('protocalId'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('terminalCode'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('videoFactory'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('videoServerIp'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('videoUser'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('videoPassword'),'<span class="noticeMsg">*</span>');
	if($('videoFactory').value == "HI") {
		Mat.setDefault($('streamServerIp'),'<span class="noticeMsg">*</span>');
	} else {
		Mat.setDefault($('streamServerIp'),'');
		$('streamServerIp').disabled = true;
	}
	Mat.setDefault($('videoId'),'');
	
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('terminalCode').onkeyup = checkTerminalCode;
    $('terminalCode').onblur = checkTerminalCode;
    //$('videoId').onkeyup = checkVideoId;
    //$('videoId').onblur = checkVideoId;
    //$('videoServerIp').onkeyup = checkVideoServerIp;
    //$('videoServerIp').onblur = checkVideoServerIp;
    $('videoUser').onkeyup = checkVideoUser;
    $('videoUser').onblur = checkVideoUser;
    $('videoPassword').onkeyup = checkVideoPassword;
    $('videoPassword').onblur = checkVideoPassword;
  }

  /** 检查通道信息 **/
  function checkChannelInfo() {
    var channelNameObjs = document.getElementsByName("channelName");
    var channelNumberObjs = document.getElementsByName("channelNumber");
    var warnObj = $('channelWarn');

    if(channelNameObjs.length == 0 || channelNumberObjs.length == 0) {
		warnObj.innerHTML = "&nbsp;&nbsp;&nbsp;请选择至少一个通道信息！";
        return false;
	}
    
	for( i=0; i<channelNameObjs.length; i++) {
      if(!Mat.checkNameNull(channelNameObjs[i].value)) {
        warnObj.innerHTML = "&nbsp;&nbsp;&nbsp;<s:property value="getText('terminal.channel.require')" />";
        return false;
      }
	}

	for( i=0; i<channelNumberObjs.length; i++) {
		if(!Wit.checkErr(channelNumberObjs[i],/^\d+$/)) {
	    	warnObj.innerHTML = "&nbsp;&nbsp;&nbsp;通道号格式不正确，正确格式为非空数字类。";
	        return false;
	    }
	}
	warnObj.innerHTML = "";
	return true;
  }

  /** 检查终端号唯一性 **/
  function checkTmCodeUnique(){
    var tmCodeObj = $('terminalCode');
    
    DWREngine.setAsync(false);
    var ret = false; 
    TerminalManageDWR.checkTmCodeUnique(tmCodeObj.value, callBackFun);
    
    function callBackFun(data)
    {
      ret = data;
    }
    
    DWREngine.setAsync(true);
    
    if(ret) {
      Wit.showErr(tmCodeObj, "<s:property value="getText('terminal.code.exist')" />");
      return false;
    } else {
      return true;
    }
  }
  
  /** 提交form **/
  function submitForm() {
	  trimAllObjs();
    var f1 = checkTerminalCode();
    var f2 = checkOem();
    var f3 = checkType();
    var f4 = checkProtocal();
    var f5 = checkChannelInfo();
   // var f6 = checkVideoId();
    var f7 = checkVideoFactory();
    var f8 = checkVideoServerIp();
    var f9 = checkVideoUser();
    var f10 = checkVideoPassword();
    var f11 = checkStreamInfo();
    
	if (f1 && f2 && f3 && f4 && f5 && f7 && f8 && f9 && f10 && f11) {
	  var oldTmCode = $('terminalOldCode');
	  var newTmCode = $('terminalCode');
	  
	  if(oldTmCode.value != newTmCode.value) {
		// 终端号变化时，检查终端号唯一性  
        var f6 = checkTmCodeUnique();
        if(f6) {
          if(confirm("<s:property value="getText('confirm.modify')" />")) {
        	NoticeCoreDwr.noticeCore("0", "1");
		    Wit.commitAll($('terminaledit_form'));
          } 
        }
	  } else {
		// 终端号无变化时，不检查终端号唯一性
		if(confirm("<s:property value="getText('confirm.modify')" />")) {
		//  NoticeCoreDwr.noticeCore("0", "1");
		  Wit.commitAll($('terminaledit_form'));
        } 
	  }
	} else  {
	  return false;
	}
  }

  /** 填加通道 **/
  function addChannel() {
    var form = $('terminaledit_form');
    form.action = 'addEditChannel.shtml';
    Wit.commitAll(form);
  }

  /** 删除通道 **/
  function deleteChannel(channelId) {
    $('delChannelId').value = channelId;
    var form = $('terminaledit_form');
    form.action = 'deleteEditChannel.shtml';
    Wit.commitAll(form);
  }
  
  /** 取消修改操作 **/
  function goBack(url) {
    Wit.goBack(url);
  }

  /** 删除终端信息 **/
  function delTerminal(url) {
    if(confirm("<s:property value="getText('confirm.delete')" />")) {
      NoticeCoreDwr.noticeCore("0", "1");
      Wit.goBack(url);
    } else {
      return false;
    }
  }
  
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>