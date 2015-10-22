<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/PasswordConfirmDWR.js'></script>
<script type="text/javascript">
	function checkPassword() {
		var oldPass = $('inputPassword');
		
		if (!Mat.checkRequired(oldPass))
			return false;
		if (!Mat.checkLength(oldPass, 64, null))
			return false;
		return true;
	}

	/** 清空提示信息 **/
	function clearMessage() {
		var obj = $('successLbl');
		var obj1 = $('errorLbl');
		
		obj.innerHTML = "";
		obj1.innerHTML = "";
	}
	
	/** 初始化样式 **/
	function setFormMessage() {
     Mat.setDefault($('inputPassword'),'<span class="noticeMsg">*</span>');
	}

	/** 加载事件 **/
	function setFormEvent() {
		$('inputPassword').onkeyup = checkPassword;
		$('inputPassword').onkeydown = clearMessage;
		$('inputPassword').focus();
		initLoginEvent();
	}
	
	function checkModulePassword(){
		trimAllObjs();
		var moduleName = $('moduleName').value;
		var f1 = checkPassword();
		if(f1) {
			var obj = $('inputPassword');
		    
			DWREngine.setAsync(false);
			var ret = false; 
			PasswordConfirmDWR.checkPassword(obj.value, callBackFun);
			    
			function callBackFun(data)
			{
			  ret = data;
			}
			    
			DWREngine.setAsync(true);
			
			if (ret) {
				var obj = $('successLbl');
				obj.innerHTML = "验证成功！";
				if(moduleName == "student_module"){
				  window.parent.location.href="<s:url value='/student/studentmanage.shtml' />";
				}
				if(moduleName == "message_module"){
					<s:if test="#session.adminProfile.en_gateway == \"2\"">
					// xd
					window.parent.location.href="<s:url value='/smsreminder/init.shtml' />";
					</s:if>
					<s:else>
					// emay
					window.parent.location.href="<s:url value='/sms/smsmanage.shtml' />";
					</s:else>
				}
				art.dialog.close();
				window.parent.location.reload();
			} else {
				var obj = $('errorLbl');
				obj.innerHTML = "验证失败，请重新输入！";
				return false;
			}
		} else {
			return false;
		}
	}
	
	function goBack(url) {
		Wit.goBack(url);
	}

	function fireFoxHandler(evt){ 
		if(evt.keyCode==13){ 
			checkModulePassword();
		} 
	} 

	function ieHandler(evt){ 
		if(evt.keyCode==13){ 
			checkModulePassword();
		} 
	} 

	/** 回车触发 **/
	function initLoginEvent() {
		if(document.addEventListener){
			// Firefox 
			document.addEventListener("keypress",fireFoxHandler, true); 
		}else{ 
			// ie
			document.attachEvent("onkeypress",ieHandler); 
		} 
	}
	
	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);

</script>