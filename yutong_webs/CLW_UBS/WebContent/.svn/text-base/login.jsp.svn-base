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

*{
	font-family:Arial,'宋体','微软雅黑';
	font-size:12px;
	color:black;
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



/*new*/
body
	{
		background:#fff2df;
		min-width: 800px;
		min-height: 600px;
		margin:0px;
		padding:0px;
		overflow:auto;
	}
	.hvcenter{  
        width:780px;  
        height:546px;  
        background:url(<s:url value='/images/ub/loginbg.jpg'/>) no-repeat;
        position:absolute;  
        top:50%;  
        left:50%;  
        margin:-273px 0 0 -390px;  

        
    }  
    
    .innercon
    {
    	width:100%;
    	height:100%;
    	position:relative;
    }
    .label
    {
    	position:absolute;
    	font-weight: bold;
    	font-size:14px;
    }
    .labeluser
    {
		left:518px;
		top:160px;
    }
    .labelpwd
    {
    	left:518px;
    	top:220px;
    }
    .labelvalcode
    {
    	left:518px;
    	top:280px;
    }
    .inputuser
    {
    	position: absolute;
    	border:0px;
    	height:24px;
    	width:180px;
    	left:550px;
    	top:185px;
    }
    .inputpwd
    {
   		position: absolute;
    	border:0px;
    	height:24px;
    	width:180px;
    	left:550px;
    	top:243px;
    }
    #validateCode
    {
		position: absolute;
    	border:0px;
    	height:24px;
    	
    	left:515px;
    	top:303px;
    	width:90px;
    	border:1px solid #cdcdcd;
    }
    #validatecodeimg
    {
    	position: absolute;
    	left:626px;
    	top:303px;
    }
    .loginbtn
    {
    	display: block;
    	width:223px;
    	height:32px;
    	background: url(<s:url value='/images/ub/loginbtn_n.png'/>) no-repeat;
    	text-align: center;
    	line-height: 32px;
    	padding-top:2px;
    	text-decoration: none;
    	color: #804712;
    	font-weight: bold;
    	position: absolute;
    	left:515px;
    	top:370px;
    }
    .loginbtn:hover
    {
    	background: url(<s:url value='/images/ub/loginbtn_h.png'/>) no-repeat;	
    	
    }
   .lgnfoot
   {
   	width:100%;
   	height:20px;   	
   	padding-bottom:100px;
   	
   	position: absolute;
   	bottom:0;
   	text-align: center;
   	
   	font-family: arial,"宋体";
	font-size:14px;
   }
   .lgnfoot p
   {
   	color:#888;
   }
   .lgnfoot  a
   {
   	color:blue;
   }
   
   .labelusermsg
   {
   		font-size:12px;
   		color:#cc0000;
   		left:582px;
   		top:160px;
   		font-weight:normal;
   }
   .labelpwdmsg
   {
   		font-size:12px;
   		color:#cc0000;
   		left:582px;
   		top:220px;
   		font-weight:normal;
   }
   .labelvalcodemsg
   {
   		font-size:12px;
   		color:#cc0000;
   		left:582px;
   		top:280px;
   		font-weight:normal;
   }
   .remuser
   {
   		left:515px;
   		top:340px;
   		position:absolute;
   		width:150px;
   		vertical-align:middle;
   		line-height:23px;
   		
   		
   }
   .remuser input
   {
   		vertical-align:middle;
   }
   #message2
   {
   		left:515px;
   		top:415px;
   		position:absolute;
   		
   }
</style>
  
  <script type="text/javascript"> 
    var count = 0;
		
    // 验证码更新
    function changeCode(){
	  var checkimg =  document.getElementById("validatecodeimg");
      checkimg.src="<s:url value='/validatecode.shtml'/>"+ "?count=" + (++count);
    }

	function checkUserName() {
		jQuery("#vuser").hide();
		var username = $('username');
		if(Mat.checkNameNull(username.value)==""){
		    //Wit.showErr(username, "<s:property value="getText('msg.check.required')" />");
		    jQuery("#vuser").show();
			return false;
		}
		return true;
	}

	function checkPassword() {
		jQuery("#vpwd").hide();
		var password = $('password');
		if(Mat.checkNameNull(password.value)==""){
		    //Wit.showErr(password, "<s:property value="getText('msg.check.required')" />");
		    jQuery("#vpwd").show();
			return false;
		}
		return true;
	}

	function checkValidateInput() {
		jQuery("#vval").hide();
		var validateCode = $('validateCode');
		if(Mat.checkNameNull(validateCode.value)==""){
			jQuery("#vval").show();
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
  <div class="hvcenter">

		<div class="label labeluser">用户名：</div><div id="vuser" class="label labelusermsg" style="display:none;">请输入用户名</div>
		<div class="label labelpwd">密&nbsp;&nbsp;&nbsp;&nbsp;码：</div><div id="vpwd" class="label labelpwdmsg" style="display:none;">请输入密码</div>
		<div class="label labelvalcode">验证码：</div><div id="vval" class="label labelvalcodemsg" style="display:none;">请输入验证码</div>

		<s:textfield id="username" name="username" cssClass="inputuser"/>
		
		<s:password id="password" name="password" cssClass="inputpwd"/>
		
		
		<s:textfield id="validateCode" name="validateCode" size="7"/>
		<img src="<s:url value='/validatecode.shtml'/>" 
                     align="top" 
                     id="validatecodeimg" 
                     onclick= "changeCode()" 
                     alt="<s:property value="getText('login.validatecode.refresh')" />"/>
                <font id="validesc"></font>
		
	
			<label for="saveLoginInfo" class="remuser">
			<input id="saveLoginInfo" name="saveLoginInfo" type="checkbox">
			记住用户名
            </label>
        

		<a href="#" class="loginbtn" onclick="submitForm();">登录</a>
		
		<div id="message2">
                    <s:actionerror theme="mat" />
                    <s:fielderror theme="mat" />
                  </div>

	</div>
	<div class="lgnfoot">
		<p>
			客服电话： 400-659-6666
			&nbsp;&nbsp;&nbsp;&nbsp;豫ICP备05018144号&nbsp;&nbsp;&nbsp;&nbsp;版权所有 © 宇通集团 2010-2015
		</p>
		<p>
			前台地址：
			<a href="http://www.axxc.cn">http://www.axxc.cn</a>
		</p>
	</div>
  
  </s:form>  
  </body>
</html>

