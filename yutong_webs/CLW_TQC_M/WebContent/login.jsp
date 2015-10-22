<%@include file="WEB-INF/jsp/common/taglibs.jsp" %>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
  <%@include file="WEB-INF/jsp/common/meta.jsp" %>
  <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/jquery-1.4.2.min.js'/>"></script>
  <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/jquery-cookie.js'/>"></script>
  <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
  <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
  <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
  
<title><s:property value="getText('login.title')" /></title>
<style type="text/css">
body{
	background: url(images/login_bg.png) repeat-x left top;
	margin: 0;
	color: #222; 
	font: 12px/18px Tahoma, Arial, Verdana,'Microsoft Yahei', 微软雅黑, 宋体;
	margin: 0;
}
img{ border: 0;}
.wrapper{
	background: url(images/login_bg2.png) no-repeat center top;
	margin: 0 auto;
	min-height: 480px;
	padding-top: 100px;
	width: 1000px;
}
.main{
	background: url(images/l_logoManag.png) no-repeat left 10px;
	margin: 0 140px;
	padding-top: 100px;
	height: 380px;
	
}
.loginBox{
	background: url(images/loginBox_b.png) no-repeat left top;
	float: left;
	height: 350px;
	padding-left: 400px;
	width: 320px;
}
.title{
	background: url(images/line.png) no-repeat center bottom;
	color: #111;
	font-size: 16px;
	font-weight: bold;
	line-height: 40px;
	text-align: left;
}
.btnLogin{
	background: url(images/btn_login.png) no-repeat center top;
	color: #222;
	display: block;
	font-weight: bold;
	height: 34px;
	line-height: 34px;
	text-decoration: none;
	text-align: center;
	width: 102px;
}

.tf{
/*height: 17px;
line-height: 17px;*/
font-size: 12px;
border: #006699 1px solid;
padding-left: 2px;
padding-top: 1px;
}

.ta{
font-size: 12px;
border: #006699 1px solid;
padding-left: 2px;
padding-top: 1px;
}

.errorInput{
border: 1px solid #CC0000;
}

.noticeMsg{
font-size: 12px;
color: #CC0000;
padding: 2px;
}

.error{
font-size: 12px;
color: #CC0000;
}

.success{
font-size: 12px;
color: #009900;
}

div#messagebar{
width: 100%;
height: 20px;
/*line-height: 90px;*/
position: relative;
margin-right: auto;
margin-left: auto;
text-align: left;
/*color: #666;*/
overflow: hidden;
}

div#message, .message{
clear: both;
text-align: center;
list-style-type: square;
overflow: hidden;
}

.input_required{ color: #c80000; font-size:12px; line-height: 18px; padding: 0;}
.footer{ float: left; text-align: center; width: 100%;}
</style>
  
  <script type="text/javascript"> 
    var count = 0;
		
    // 验证码更新
    function changeCode(){
	  var checkimg =  document.getElementById("validatecodeimg");
      checkimg.src="<s:url value='/validatecode.shtml'/>"+ "?count=" + (++count);
    }

	function initUserName(){
		Mat.setDefault($('username'),'');
	}

	function initPassword(){
		Mat.setDefault($('password'),'');
	}

	function initValidateCode(){
		Mat.setDefault($('validateCode'),'');
	}

	function checkUserName() {
		var username = $('username');
		if(Mat.checkNameNull(username.value)==""){
		    Wit.showErr(username, "<s:property value="getText('msg.check.required')" />");
			return false;
		}
		return true;
	}

	function checkPassword() {
		var password = $('password');
		if(Mat.checkNameNull(password.value)==""){
		    Wit.showErr(password, "<s:property value="getText('msg.check.required')" />");
			return false;
		}
		return true;
	}

	function checkValidateInput() {
		var validateCode = $('validateCode');
		if(Mat.checkNameNull(validateCode.value)==""){
		    //Wit.showErr(validateCode, "");
		    validateCode.className = "tf errorInput";
			var showelm=document.getElementById('validesc');
		    showelm.className='input_required';
	        showelm.innerHTML="<s:property value="getText('msg.check.required')"/>";
			return false;
		}
		return true;
	}
		
	function submitForm() {
		var f0 = checkUserName();
		var f1 = checkPassword();
		var f2 = checkValidateInput();
		if (f0&&f1&&f2) {
			saveLogin();
			$('login_form').submit();
			return true;
		} else {
			return false;
		}
	}
		
	function setFormEvent() {
		$('username').onkeyup = initUserName;
		$('password').onkeyup = initPassword;
		$('validateCode').onkeyup = initValidateCode;
		//$('btnSubmit').onclick = submitForm;
	}

	function setFormMessage() {
		initUserName();
		initPassword();
		initValidateCode();
		$('username').focus();
	}

	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);

	function fireFoxHandler(evt){ 
		if(evt.keyCode==13){ 
			submitForm();
		} 
	} 

	function ieHandler(evt){ 
		if(evt.keyCode==13){ 
			submitForm();
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
		document.getElementById("validateCode").value = "";
	}


	 // 设置cookie信息
	function setCookie(name,pwd)
	{
		var valuestr = name+"_"+pwd;
		jQuery.cookie("namem", escape(name), { expires: 7 });
		//jQuery.cookie("passwordm", escape(pwd), { expires: 7 });

	}
	//获取cookie信息
	function getCookie(){
		var name = jQuery.cookie('namem');
		var password = jQuery.cookie('passwordm');

		var all = name+"_"+password;
		return all;
		
	}

	function deleteCookie(name){
		var date = new Date();
		date.setTime(date.getTime()-10000);
		document.cookie = name+"=;expire="+date.toGMTString()+";path=/;MaxAge=0";
    }
	// 保存cookie
	function saveLogin(){
		var name = document.getElementById("username").value;
		var pwd = document.getElementById("password").value;
		var save = document.getElementById("saveLoginInfo");
		if(save.checked ==true){
			setCookie(name,pwd);
			
		}
	}
	//加载cookie
	function loadCookie(){
		var cookievalue = getCookie();
		
		if(cookievalue!=null && cookievalue!="" && cookievalue!="undefined" && cookievalue!="null"){
			var arr = cookievalue.split("_");
			var cookiename = arr[0];
			var cookiepwd = arr[1];
			if(cookiename!=null && cookiename!="" && cookiename!="undefined" && cookiename!="null"){
				document.getElementById("username").value = cookiename;
				//document.getElementById("password").value = cookiepwd;
			}
			
		}
	}
	</script>
</head>
  <body onLoad="initLoginEvent();loadCookie();">
  <s:form id="login_form" action="login" method="post">
  
  <div class="wrapper">
  <div class="main">
    <div class="loginBox">
      <table width="300" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="0" height="75" valign="bottom" class="title">用户登录</td>
        </tr>
        <tr>
          <td><table width="300" border="0" cellpadding="0" cellspacing="0" class="enter">
            <tr>
              <td width="50" height="35"><s:property value="getText('login.username')" />：</td>
              <td width="180"><s:textfield id="username" name="username"/></td>
            </tr>
            <tr>
              <td height="35"><s:property value="getText('login.password')" />：</td>
              <td><s:password id="password" name="password" cssStyle="width:127px"/></td>
            </tr>          
            <tr>
              <td height="35"><s:property value="getText('login.validatecode')" />：</td>
              <td>
                <s:textfield id="validateCode" name="validateCode" size="7"/>
                <img src="<s:url value='/validatecode.shtml'/>" 
                     align="top" 
                     id="validatecodeimg" 
                     onclick= "changeCode()" 
                     alt="<s:property value="getText('login.validatecode.refresh')" />"/>
                <font id="validesc"></font>
              </td>
            </tr>
          
            <tr>
              <td height="35">&nbsp;</td>
              <td><input id="saveLoginInfo" name="saveLoginInfo" type="checkbox">
              <span class="STYLE1">记住用户名</span>
              </td>
              </tr>
          </table></td>
        </tr>
        <tr>
          <td>
            <table width="300" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="180">
                  <div id="message2">
                    <s:actionerror theme="mat" />
                    <s:fielderror theme="mat" />
                  </div>
                </td>
                <td><a href="#" class="btnLogin" onclick="submitForm();">登录</a></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </div>
    <div class="footer">客服电话：<strong>400-659-6666</strong>&nbsp;&nbsp;&nbsp;&nbsp;豫ICP备05018144号&nbsp;&nbsp;&nbsp;&nbsp;版权所有 © 宇通集团 2010-2015 </div>
  </div>
</div>
  </s:form>  
  </body>
</html>

