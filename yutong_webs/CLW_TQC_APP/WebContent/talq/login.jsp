<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="../WEB-INF/jsp/common/taglibs.jsp"%>
<title>欢迎使用泰安旅汽通勤车管理系统</title>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/taian/login.css" />" />
<script src="../scripts/swf/swfobject_modified.js" type="text/javascript"></script>
</head>
<body onLoad="initLoginEvent();loadCookie();" class="login_body">
<div class="login_logo"></div>
<div class="login_c" style="top:100px;">        
        <s:form id="login_form" action="loginTa" method="post">
        <div style="display:none;">
	          <s:textfield id="encode" value="0000850260" name="encode" cssStyle="height:20px;width:138px;" maxlength="12"/>
	          <span id="endesc"></span>
        </div>       
    	<table cellpadding="0" cellspacing="0" class="login_table">
        	<tr>
            	<td class="login_txt"><!--用&nbsp;户&nbsp;名：--></td>
                <td style="width:200px;"><s:textfield id="username" name="username" cssStyle="border:0;background:url(../newimages/taian/inputbg.jpg) no-repeat;height:20px;width:138px;" maxlength="18" /></td>
             	<td style="width:45px;"><span id="userdesc"></span></td>
            </tr>
            <tr>
            	<td class="login_txt"><!--密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：--></td>
                <td><s:password id="password" name="password"  cssStyle="border:0;background:url(../newimages/taian/inputbg.jpg) no-repeat;height:20px;width:138px;" maxlength="18"/></td>
                <td><span id="passdesc"></span></td>
            </tr>           
            <tr>
            	<td style="height: 40px;" class="login_txt"><!-- 验&nbsp;证&nbsp;码：--></td>
                <td style="height: 40px;">
	              <table border="0" cellpadding="0" cellspacing="0">
		              <tr>
		                <td style="height: 40px;">
		                   <s:textfield id="validateCode" name="validateCode"  cssStyle="border:0;background:url(../newimages/taian/inputbg.jpg) no-repeat;width:120px;height:20px;" maxlength="4"/>
		                </td>
		                <td style="height: 40px;">
		                    <img src="<s:url value='/validatecode.shtml'/>" alt="看不清，请单击图片" name="validatecodeimg" vspace="0" align="absmiddle"	id="validatecodeimg" onClick="changeCode()" />
		                </td>
					  </tr>
				  </table>
			    </td>
			  <td style="height: 40px;"><span id="validesc"></span></td>
            </tr>
            <tr>            	
                <td colspan="3">
                   <div style="text-align:left;width:100%;padding-left:20px;">
                     <input id="saveLoginInfo" name="saveLoginInfo" type="checkbox" /> 记住用户名</td> 
                  </div>
                </td>          
            </tr>
            <tr>            	
                <td colspan="3" style="padding-left:18px;">
                   <a id="loginbtn" href="#" onclick="submitForm()" class="tabtnLink_sure"></a>
                </td>
            </tr>
        </table>
        <!-- <div style="width: 180px; height: 18px; position: absolute; left: 490px; top: 295px; line-height: 18px; color: #07328b;">建议使用IE8，1440*900分辨率</div> -->
        <div id="miaoshu" class="miaoshu" style="display:none;position:absolute;left:600px;top:260px;"><s:actionerror theme="mat" /><s:fielderror theme="mat" /></div>
        </s:form>
    </div>
    <div class="login_footer">
    <!--  
    <p>建议使用IE8浏览器，分辨率1440*900，以达到最佳体验效果  <a href="http://get.adobe.com/flashplayer/download/msie/?installer=Flash_Player_11_for_Internet_Explorer&os=XP&browser_type=MSIE&browser_dist=OEM&dualoffer=true&chromedefault=true&a=Google_Toolbar_7.4&a=Google_Chrome_22.0_IE_Browser">下载地图Flash插件</a></p>-->
    <p>客服电话：400-659-6666&nbsp;&nbsp;&nbsp;豫ICP备10203497号-3&nbsp;&nbsp;&nbsp;版权所有©宇通集团2013-2014</p>
    </div>
    <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/jquery-cookie.js'/>"></script>
    <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
    <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
    <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
    <script type="text/javascript"> 
		var count = 0;
		 if (top != window)   
		      top.location.href = window.location.href;
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

		function initEncode(){
			Mat.setDefault($('encode'),'');
		}

		function checkUserName() {
			var username = $('username');
			var inishowelm=document.getElementById('userdesc');
			inishowelm.innerHTML='';
			if(Mat.checkNameNull(username.value)==""){
				var showelm=document.getElementById('userdesc');
			    //Wit.showErr(username, "<s:property value="getText('msg.check.required')" />");
			    showelm.className='input_required';
		        showelm.innerHTML="<s:property value="getText('msg.check.required')"/>";
				return false;
			}
			return true;
		}

		function checkPassword() {
			var password = $('password');
			var inishowelm=document.getElementById('passdesc');
			inishowelm.innerHTML='';
			if(Mat.checkNameNull(password.value)==""){
			    //Wit.showErr(password, "<s:property value="getText('msg.check.required')" />");
			    var showelm=document.getElementById('passdesc');
			    showelm.className='input_required';
		        showelm.innerHTML="<s:property value="getText('msg.check.required')"/>";
				return false;
			}
			return true;
		}

		function checkValidateInput() {
			var validateCode = $('validateCode');
			var inishowelm=document.getElementById('validesc');
			inishowelm.innerHTML='';
			if(Mat.checkNameNull(validateCode.value)==""){
			   // Wit.showErr(validateCode, "<s:property value="getText('msg.check.required')" />");
			    var showelm=document.getElementById('validesc');
			    showelm.className='input_required';
		        showelm.innerHTML="<s:property value="getText('msg.check.required')"/>";
				return false;
			}
			return true;
		}

		function checkEncode() {
			var encode = $('encode');
			var inishowelm=document.getElementById('endesc');
			inishowelm.innerHTML='';
			if(Mat.checkNameNull(encode.value)==""){
			   // Wit.showErr(encode, "<s:property value="getText('msg.check.required')" />");
			    var showelm=document.getElementById('endesc');
			    showelm.className='input_required';
		        showelm.innerHTML="<s:property value="getText('msg.check.required')"/>";
				return false;
			}
			return true;
		}
		
		function submitForm() {
			//alert("test1");
			jQuery('#miaoshu').css('display','none');
			trimAllObjs();
			var inishowelm=document.getElementById('miaoshu');
			inishowelm.innerHTML='';
			var f0 = checkUserName();
			var f1 = checkPassword();
			var f2 = checkValidateInput();
			//var f3=  checkEncode();
			if (f0&&f1&&f2) {//&&f3
				//保存用户登陆信息
				
				saveLogin();
				//Wit.commitAll($('login_form'));
				$('login_form').submit();
				return true;
			} else {
				return false;
			}
		}
		
		function setFormEvent() {
			//$('username').onkeyup = initUserName;
			//$('password').onkeyup = initPassword;
			//$('validateCode').onkeyup = initValidateCode;
			//$('encode').onkeyup=checkEncode;
			//$('btnSubmit').onclick = submitForm;
		}

		function setFormMessage() {
			initUserName();
			initPassword();
			initValidateCode();
			initEncode();
			$('username').focus();
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
				//隐藏错误提示信息
				var eText = jQuery("#miaoshu").html();
				if(eText != ''){jQuery("#miaoshu").css('display','');}		
			}

		window.addEvent('domready', setFormEvent);
		window.addEvent('domready', setFormMessage);

        // 设置cookie信息
		function setCookie(name,pwd,entid)
		{
			var valuestr = name+"_"+pwd+"_"+entid;
			//alert(valuestr);
			//var exp = new Date();    //new Date("December 31, 9998");
		    //exp.setTime(exp.getTime() + 360*24*60*60*1000);

			//document.cookie="c_nameclw_ytp=" +escape(valuestr)+";path=/;domain=localhost"+";expires="+exp.toGMTString();
			//alert(document.cookie);
			//$.cookie("name", valuestr, { expires: 128 });
			jQuery.cookie("name", escape(name), { expires: 7 });
			jQuery.cookie("password", escape(pwd), { expires: 7 });
			jQuery.cookie("entid", escape(entid), { expires: 7 });

		}
		//获取cookie信息
		function getCookie(){
			/*if (document.cookie.length>0){
				  c_start=document.cookie.indexOf(c_name + "=");
				  if (c_start!=-1){ 
					    c_start=c_start + c_name.length+1 ;
					    c_end=document.cookie.indexOf(";",c_start);
					    if (c_end==-1) c_end=document.cookie.length;
					    return unescape(document.cookie.substring(c_start,c_end));
				    } 
			  }

			  var strCookie = document.cookie;
			  var arrCookie = strCookie.split(";");

			  for(var i = 0; i < arrCookie.length; i++){
				  arr = arrCookie[i].split("=");
				  //alert(arr);
				  if(arr[0]==c_name)return arr[1];
			  }
			return "";*/
			var name = jQuery.cookie('name');
			var password = jQuery.cookie('password');
			var entid = '850260';//jQuery.cookie('entid');

			var all = name+"_"+password+"_"+entid;
			//alert(all);
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
			var entid = document.getElementById("encode").value;
			var save = document.getElementById("saveLoginInfo");
			if(save.checked ==true){
				//alert("保存cookie");
				setCookie(name,pwd,entid);
				
			}
		}
		//加载cookie
		function loadCookie(){
			//alert("加载cookie");
			var cookievalue = getCookie();
			
			if(cookievalue!=null && cookievalue!="" && cookievalue!="undefined" && cookievalue!="null"){
				var arr = cookievalue.split("_");
				var cookiename = arr[0];
				var cookiepwd = arr[1];
				var cookieentid = arr[2];
				//alert("加载cookie");
				if(cookiename!=null && cookiename!="" && cookiename!="undefined" && cookiename!="null"){
					//alert(cookiename);
					document.getElementById("username").value = cookiename;
					//document.getElementById("password").value = cookiepwd;
					document.getElementById("encode").value = cookieentid;
				}else{
					$('username').focus();
					//document.getElementById("username").foucs();
				}
				
			}
			//$.cookie("name", document.login_form.username.value, { expires: 7 });
			//$.cookie("password", document.login_form.password.value, { expires: 7 });
			//$.cookie("entid", document.login_form.encode.value, { expires: 7 });
			
		}
      </script>
      
<object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="0" height="0">
  <param name="movie" value="../scripts/swf/ver.swf">
  <param name="quality" value="high">
  <param name="wmode" value="transparent">
  <param name="swfversion" value="11.5.502.146">
  <param name="expressinstall" value="../scripts/swf/expressInstall.swf">
  <!--[if !IE]>-->
  <object type="application/x-shockwave-flash" data="../scripts/swf/ver.swf" width="0" height="0">
    <!--<![endif]-->
    <param name="quality" value="high">
    <param name="wmode" value="transparent">
    <param name="swfversion" value="11.5.502.146">
    <param name="expressinstall" value="../scripts/swf/expressInstall.swf">
    <div> 
      <p style="text-align: center;"><a href="http://www.adobe.com/go/getflashplayer"><img src="../newimages/btn_get_flash_player.gif" alt="Get Adobe Flash player" /></a></p>
    </div>
    <!--[if !IE]>-->
  </object>
  <!--<![endif]-->
</object>
 
<script type="text/javascript">
	swfobject.registerObject("FlashID");
</script>
</body>
</html>