<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="../WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/hy_login.css" />" />
<script src="../scripts/swf/swfobject_modified.js" type="text/javascript"></script>
</head>
<body onLoad="initLoginEvent();loadCookie();" class="login_body">
<div class="login_c">
         <s:form id="login_form" action="login" method="post">
    	<table cellpadding="0" cellspacing="0" class="login_table">
        	<tr>
            	<td class="login_txt">用&nbsp;户&nbsp;名：</td>
                <td><s:textfield id="username" name="username" cssStyle=" height: 20px; width: 138px;" maxlength="18"/></td>
             	<td><span id="userdesc"></span></td>
            </tr>
            <tr>
            	<td class="login_txt">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                <td><s:password id="password" name="password" cssStyle=" height: 20px; width: 138px;" maxlength="18"/></td>
                <td><span id="passdesc"></span></td>
            </tr>
            <tr>
            	<td class="login_txt">机构编码：</td>
                <td><s:textfield id="encode" name="encode" cssStyle=" height: 20px; width: 138px;" maxlength="12"/></td>
                <td><span id="endesc"></span></td>
            </tr>
            <tr>
            	<td class="login_txt">验&nbsp;证&nbsp;码：</td>
                <td>
              <table border="0" cellpadding="0" cellspacing="0">
              <tr>
              <td>
              <s:textfield id="validateCode" name="validateCode" cssStyle=" height: 20px; width: 58px;" maxlength="4"/></td>
              <td>
              <img
				src="<s:url value='/validatecode.shtml'/>" alt="看不清，请单击图片" name="validatecodeimg" vspace="5" align="absmiddle"
				id="validatecodeimg" onClick="changeCode()" /></td>
				</tr></table></td>
			  <td><span id="validesc"></span></td>
            </tr>
            <tr>
            	<td class="login_txt">&nbsp;</td>
                <td>
                <table><tr>
                <td><input id="saveLoginInfo" name="saveLoginInfo" type="checkbox"></td>
                <td>&nbsp;</td>
                <td>记住用户名和机构编码</td>                
                </tr></table>
                </td>               
				<td></td>	
            </tr>
            <tr>
            	<td class="login_txt"></td>
                <td colspan="2"><a id="loginbtn" href="#" onClick="submitForm()" class="login_btn"><img src="../newimages/login_btn.gif" /></a></td>
            </tr>
        </table>
        <div style="width: 180px; height: 18px; position: absolute; left: 235px; top: 220px; line-height: 18px; color: #07328b;">建议使用IE8，1440*900分辨率</div>
        <div id="miaoshu" class="miaoshu" style="display:none;position:absolute;left:495px;top:80px;"></div>
        </s:form>
    </div>
    <div class="login_footer">
    <!--  
    <p>建议使用IE8浏览器，分辨率1440*900，以达到最佳体验效果  <a href="http://get.adobe.com/flashplayer/download/msie/?installer=Flash_Player_11_for_Internet_Explorer&os=XP&browser_type=MSIE&browser_dist=OEM&dualoffer=true&chromedefault=true&a=Google_Toolbar_7.4&a=Google_Chrome_22.0_IE_Browser">下载地图Flash插件</a></p>-->
    <p>客服电话：400-659-6666 豫ICP备10203497号-3 版权所有&copy;宇通集团2010-2015</p>
    </div>
    <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/jquery-1.4.2.min.js'/>"></script>
    <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/jquery-cookie.js'/>"></script>
    <script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
    <script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
    <script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
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
			jQuery('#miaoshu').css('display','none');
			trimAllObjs();
			var inishowelm=document.getElementById('miaoshu');
			inishowelm.innerHTML='';
			var f0 = checkUserName();
			var f1 = checkPassword();
			var f2 = checkValidateInput();
			var f3=  checkEncode();
			if (f0&&f1&&f2&&f3) {
				//保存用户登陆信息
				 saveLogin();
				
				 //分辨率
				 var resolution=getScree();
				
				 //操作系统
				 var operatesys=detectOS();
				 //浏览器
				 var browser=getBrowser();
				 //FLASH 版本
				 var flashver=getFlashVer();

				 //alert(resolution);
				 //alert(operatesys);
				 //alert(browser);
				 //alert(flashver);
				
				 jQuery.ajax({
						type: 'POST', 
						url: './login.shtml',
						async:false,
						data: {
					         username:jQuery("#username").val(),
					         password:jQuery("#password").val(),
					         encode:jQuery("#encode").val(),
					         validateCode:jQuery("#validateCode").val(),
					         resolution:resolution,
					         operatesys:operatesys,
					         browser:browser,
					         flashver:flashver   
						},
						success: function(message) {
							if(message=="success"){
								window.location="<s:url value='/gps/gpsAction.shtml' />";
							}else{
								jQuery("#miaoshu").css('display','');
								jQuery("#miaoshu").html(message);
								changeCode();
								jQuery("#password").val("");
								jQuery("#validateCode").val("");
							}
						}
					});
				
				return true;
			} else {
				return false;
			}
		}
		
		function setFormEvent() {
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
			jQuery.cookie("name", escape(name), { expires: 7 });
			jQuery.cookie("password", escape(pwd), { expires: 7 });
			jQuery.cookie("entid", escape(entid), { expires: 7 });

		}
		//获取cookie信息
		function getCookie(){
			var name = jQuery.cookie('name');
			var password = jQuery.cookie('password');
			var entid = jQuery.cookie('entid');

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
					document.getElementById("username").value = cookiename;
					//document.getElementById("password").value = cookiepwd;
					document.getElementById("encode").value = cookieentid;
				}
				
			}
		}	

        //获取屏幕分辨率
		function getScree(){
			var height=window.screen.height;    
			var width=window.screen.width; 
			return width+"X"+height;
		}

		//获取操作系统
		function detectOS() {   
           var sUserAgent = navigator.userAgent;             
           var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");   
           var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh") || (navigator.platform == "MacIntel");   
           if (isMac) {
        	   return "Mac OS";   
           } 
           var isUnix = (navigator.platform == "X11") && !isWin && !isMac;   
           if (isUnix) {
        	   return "Unix";     
           } 
           var isLinux = ((navigator.platform).indexOf("Linux") > -1);   
           if (isLinux) {
        	   return "Linux"; 
           }  
           if (isWin) {   
            var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;   
            if (isWin2K) return "Win2000";   
            var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 || sUserAgent.indexOf("Windows XP") > -1;   
            if (isWinXP) return "Winows XP";   
            var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1 || sUserAgent.indexOf("Windows 2003") > -1;   
            if (isWin2003) return "Winows 2003";   
            var isWinVista= sUserAgent.indexOf("Windows NT 6.0") > -1 || sUserAgent.indexOf("Windows Vista") > -1;   
            if (isWinVista) return "Winows Vista";   
            var isWin7 = sUserAgent.indexOf("Windows NT 6.1") > -1 || sUserAgent.indexOf("Windows 7") > -1;   
            if (isWin7) return "Winows7"; 
            var isWin8= sUserAgent.indexOf("Windows NT 6.2") > -1 || sUserAgent.indexOf("Windows 8") > -1;
            if (isWin8) return "Winows8";   
          }   
    return "unknown";   
}  

		//获取浏览器
		function getBrowser(){
			var mybrowser = appInfo(); 
			//如果是ie则需要大版本号
			if(mybrowser.msie){
				return "IE"+mybrowser.version;
			}else{
			//如果是其他浏览器，只需要浏览器名称
			  return mybrowser.appname;
			}	
		}

		function appInfo(){
			var browser = { msie: false, firefox: false, opera: false, safari: false, 
					chrome: false, netscape: false, appname:'unknown', version: 0};
			userAgent = window.navigator.userAgent.toLowerCase(); 
			if ( /(msie|firefox|opera|chrome|netscape)\D+(\d+)/.test( userAgent ) ){ 
							browser[RegExp.$1] = true; 
							browser.appname = RegExp.$1; 
							browser.version = RegExp.$2; 
		    } else if ( /version\D+(\d[\d.]*).*safari/.test( userAgent ) ){ // safari 
							browser.safari = true; 
							browser.appname = 'safari'; 
							browser.version = RegExp.$2; 
			} 
			return browser;
		}

		function getFlashVer(){
			var isIE  = (navigator.appVersion.indexOf("MSIE") != -1) ? true : false;
			var version="unknown";
			if(isIE){
				version=getieFlashVer();
			}else{
				version=getSwfVer();
			}
			return version;
		}

		function getieFlashVer(){
			    var version;
		        var axo;
		        var e;
		        try {
		          axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7");
		          version = axo.GetVariable("$version");	
		        } catch (e) {
		        	  version ="";
		        }
		        if (!version)
		        {
		                try {
		                        //set for 6.X players only
		                        axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.6");
		                        // default to the first public version
		                        version = "WIN 6,0,21,0";
		                        // throws if AllowScripAccess does not exist (introduced in 6.0r47)                
		                        axo.AllowScriptAccess = "always";
		                        // safe to call for 6.0r47 or greater
		                        version = axo.GetVariable("$version");
		                } catch (e) {
		                	 
		                }
		        }
		        if (!version)
		        {
		                try {
		                        // version will be set for 4.X or 5.X player
		                        axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.3");
		                        version = axo.GetVariable("$version");
		                } catch (e) {
		                	 
		                }
		        }

		        if (!version)
		        {
		                try {
		                        // version will be set for 3.X player
		                        axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.3");
		                        version = "WIN 3,0,18,0";
		                } catch (e) {
		                	 
		                }
		        }

		        if (!version)
		        {
		                try {
		                        // version will be set for 2.X player
		                        axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
		                        version = "WIN 2,0,0,11";
		                } catch (e) {
		                        version ="unknown";
		                }
		        }

		        //flashVersion=parseInt(VSwf.split(" ")[1].split(",")[0]);
		        var str ="unknown";
				if(version.indexOf('WIN')!=-1){
					version=version.substring(4);
					var verArr = version.toString().split(',');
					str=verArr[0]+"."+verArr[1];
				}
		        return str;
		}

		function getSwfVer(){
	        // NS/Opera version >= 3 check for Flash plugin in plugin array
	        var flashVer ="unknown";
	        if (navigator.plugins != null && navigator.plugins.length > 0) {
	                if (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]) {
	                        var swVer2 = navigator.plugins["Shockwave Flash 2.0"] ? " 2.0" : "";
	                        var flashDescription = navigator.plugins["Shockwave Flash" + swVer2].description;
							//alert(flashDescription);
	                        var descArray = flashDescription.split(" ");
	                        var tempArrayMajor = descArray[2].split(".");                        
	                        var versionMajor = tempArrayMajor[0];
	                        var versionMinor = tempArrayMajor[1];
	                        //var versionRevision = descArray[3];
	                        //if (versionRevision == "") {
	                              //  versionRevision = descArray[4];
	                       // }
	                        //if (versionRevision[0] == "d") {
	                               // versionRevision = versionRevision.substring(1);
	                        //} else if (versionRevision[0] == "r") {
	                               // versionRevision = versionRevision.substring(1);
	                                //if (versionRevision.indexOf("d") > 0) {
	                                      //  versionRevision = versionRevision.substring(0, versionRevision.indexOf("d"));
	                               // }
	                        //}
	                        var flashVer = versionMajor + "." + versionMinor ;
	                }
	        }
	        // MSN/WebTV 2.6 supports Flash 4
	        else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.6") != -1) flashVer = 4;
	        // WebTV 2.5 supports Flash 3
	        else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.5") != -1) flashVer = 3;
	        // older WebTV supports Flash 2
	        else if (navigator.userAgent.toLowerCase().indexOf("webtv") != -1) flashVer = 2;
	        return flashVer;
	}
</script>
      
<object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="0" height="0">
  <param name="movie" value="scripts/swf/ver.swf">
  <param name="quality" value="high">
  <param name="wmode" value="transparent">
  <param name="swfversion" value="11.5.502.146">
  <param name="expressinstall" value="scripts/swf/expressInstall.swf">
  <!--[if !IE]>-->
  <object type="application/x-shockwave-flash" data="scripts/swf/ver.swf" width="0" height="0">
    <!--<![endif]-->
    <param name="quality" value="high">
    <param name="wmode" value="transparent">
    <param name="swfversion" value="11.5.502.146">
    <param name="expressinstall" value="scripts/swf/expressInstall.swf">
    <div> 
      <p style="text-align: center;"><a href="http://www.adobe.com/go/getflashplayer"><img src="newimages/btn_get_flash_player.gif" alt="Get Adobe Flash player" /></a></p>
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