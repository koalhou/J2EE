<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
</head>

<body>
<div id="main">
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	
	<div id="content">
	 <s:form id="modifyPersonalform" action="/usm/modifyPersonalAction" method="post">
	    <table id="tablehight" width="100%" border="0" cellspacing="0" cellpadding="0">
	    	<tr>
	        <td valign="top">
				  <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
	 				 <tr>
	            		<td height="38" class="changtiao2"><span class="contentTil"><s:text name="menu3.xgmm" /></span></td>
	         		 </tr>
				  </table>
	        </td>
	        <td>
	        </td>
	      </tr>
	     <tr valign="top" >
		  <td valign="top" >
		       <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                <td align="center"><s:actionmessage cssStyle="font-size: 12px;color: #009900"
		              /><s:actionerror cssStyle="font-size: 12px;color: #CC0000"  />
		            </td>
	                </tr>
	            </table>
	          <table class="msgBox2" width="100%" border="0" cellspacing="0" cellpadding="0">
	                    <tr>
				            <td height="25"><span class="msgTitle">&nbsp;&nbsp;修改个人信息</span></td>
				        </tr>
				   
	                      <tr>
	                        <td height="220">
	                          <table width="100%" border="0" cellspacing="5" cellpadding="0">
	                            <tr>
	                              <td width="14%" height="28" align="right">用户名：</td>
	                              <td width="36%" class="fsBlack">
	                              <s:textfield id="loginName" name="userinfo.loginName" readonly="true"/>
	                              <s:hidden id="userID" name="userinfo.userID"/>
	                              </td>
	                             
	                              <td width="14%" align="right">姓名：</td>
	                              <td width="36%" class="fsBlack">
	                              
	                              <s:textfield id="userName" name="userinfo.userName" maxlength="16"/> <span class="noticeMsg">*</span></td>
	                              
	                            </tr>
	
	                            <tr>
	                               
	                              <td width="14%" height="28" align="right">出生日期：</td>
	                              <td width="36%" class="fsBlack">
	                              <s:textfield id="birth" name="userinfo.userBirth" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate"></s:textfield></td>
	                              
	                             
	                              <td width="14%" align="right"> 用户密码：</td>
	                              <td width="36%" >
	                              <a href="#" onClick="modifyPass();return false;">修改密码</a> 
	                              </td>
	                            </tr>
	                            <tr>
	                            <td width="14%" height="28" align="right">性别：</td>
	                              <td width="36%" class="fsBlack">
	                              <s:select name="userinfo.sex" cssStyle="width:120px;" list="sexSysMap" > </s:select></td>
	                              
	                              <td width="14%" height="28" align="right">身份证：</td>
	                              <td width="36%" class="fsBlack">
	                              <s:textfield id="idCard" name="userinfo.idCard" maxlength="32"/></td>          
	                            </tr>
	                            <tr>
	                              <td  width="14%" height="28" align="right">国家：</td>
	                              <td width="36%" class="fsBlack">
	                              <s:select cssStyle="width:120px;" id="countyID" name="userinfo.countyID" list="countryInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_province()"></s:select><span class="noticeMsg">*</span>
	                              </td>
	                              <td  width="14%" height="28" align="right">省：</td>
	                              <td width="36%" class="fsBlack">
	                              <s:select cssStyle="width:120px;" id="provinceID" name="userinfo.provinceID" list="provinceInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange="show_enterprise_city()"></s:select><span class="noticeMsg">*</span>
	                              </td>    
	                            </tr>
	                            <tr>
	                            <td  width="14%" height="28" align="right">市：</td>
	                             <td width="36%" class="fsBlack">
	                              <s:select cssStyle="width:120px;" id="cityID" name="userinfo.cityID" list="cityInfosMap" headerKey="" headerValue="%{getText('please.select')}" onchange=""></s:select><span class="noticeMsg">*</span></td>
	                              <td width="14%" height="28" align="right">移动电话：</td>
	                              <td width="36%" class="fsBlack">
	                              <s:textfield id="moblie" name="userinfo.moblie" maxlength="32"/></td>
	                            </tr>
	                            <tr>
	                             <td width="14%" height="28" align="right">固定电话：</td>
	                              <td width="36%" class="fsBlack"><s:textfield id="tel" name="userinfo.tel" maxlength="16"/></td>
	                              <td width="14%" height="28" align="right">电子邮件：</td>
	                              <td width="36%" class="fsBlack"><s:textfield id="email" name="userinfo.email" maxlength="52"/></td>
	                            </tr>  
	                            <tr>
		                           <s:if test="userinfo.student_flag==0">
		                             <s:if test="userinfo.user_type!=5">
		           			          <td width="14%" align="right"> 模块密码：</td>
	                                  <td width="36%" >
	                                  <a href="#" onClick="modifyStudent_Pwd();return false;">修改密码</a> 
	                                  </td>
	                                </s:if>
	                               </s:if>
	                            </tr>      
	                          </table>
	                        </td>
	                      </tr>
	                       <tr>
	           			 <td class="btnBar">
	           			 <a href="<s:url value='/gps/gpsAction.shtml' />" class="button">取消</a> 
	           			 <a href="#" onclick="submitForm()" class="button">修改</a>
	           			 </td>
	           			</tr>
	                    </table>   
	        </td>
	      </tr>
	    </table>
    </s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/updateValidflagDwr.js'></script>
<script type="text/javascript"> 
	function postFrom(){
		document.updateuserform.submit();
	}
	function postValidflag(flag){
			var userid = document.getElementById("userID").value;
			updateValidflagDwr.updatValidflag(userid,flag,postValidflag_CallBack);

			function postValidflag_CallBack(data){
				var i  = data;
				if(i > 0){
					alert("设置成功!");
					if(flag == 2){
						document.getElementById("jinyong").style.display = "none";
						document.getElementById("qiyong").style.display = "";
					}else{
						document.getElementById("jinyong").style.display = "";
						document.getElementById("qiyong").style.display = "none";
						}
					
					
				}
				else{
					alert("设置失败!");
				}
			}
	}


	//展示省/直辖市选择框
	function show_enterprise_province(){
		//alert("show_enterprise_province");
		var oemObj = document.getElementById('countyID');
		//alert(oemObj.value);
		if(oemObj.value != "") {
		    ZoneDwr.showZoneXsInfo(oemObj.value,callBackFun_show_enterprise_province);
	    }else{
	    	DWRUtil.removeAllOptions(document.getElementById('provinceID'));  
	    	DWRUtil.addOptions(document.getElementById('provinceID'),{'':'<s:property value="getText('please.select')" />'});  
	    	DWRUtil.removeAllOptions(document.getElementById('cityID'));  
	    	DWRUtil.addOptions(document.getElementById('cityID'),{'':'<s:property value="getText('please.select')" />'});  
	    }
	}
	function callBackFun_show_enterprise_province(data) {
		var tempObj = document.getElementById('provinceID');  
		DWRUtil.removeAllOptions(tempObj);  
		DWRUtil.addOptions(tempObj,{'':'<s:property value="getText('please.select')" />'});  
		DWRUtil.addOptions(tempObj,data);  

		var tempObj3 = document.getElementById('cityID');  
		DWRUtil.removeAllOptions(tempObj3);  
		DWRUtil.addOptions(tempObj3,{'':'<s:property value="getText('please.select')" />'});    
	}
	//展示市/县选择框
	function show_enterprise_city(){
		var oemObj = document.getElementById('provinceID');
		if(oemObj.value != "") {
		    ZoneDwr.showZoneXsInfo(oemObj.value,callBackFun_show_enterprise_city);
	    }else{
	    	DWRUtil.removeAllOptions(document.getElementById('cityID'));  
	    	DWRUtil.addOptions(document.getElementById('cityID'),{'':'<s:property value="getText('please.select')" />'});  
	    }
	}
	function callBackFun_show_enterprise_city(data) {
		var tempObj = document.getElementById('cityID');  
		DWRUtil.removeAllOptions(tempObj);  
		DWRUtil.addOptions(tempObj,{'':'<s:property value="getText('please.select')" />'});  
		DWRUtil.addOptions(tempObj,data);  
	}

	(function(){
	    var d = art.dialog.defaults;
	    
	    // 按需加载要用到的皮肤，数组第一个为默认皮肤
	    // 如果只使用默认皮肤，可以不填写skin
	    d.skin = ['aero'];
	    
	    // 支持拖动
	    d.drag = true;
	    
	    // 超过此面积大小的对话框使用替身拖动
	    d.showTemp = 100000;
	})();

jQuery(function() {
	testWidthHeight();
});
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
//计算控件宽高
function testWidthHeight(){
	 var h = get_page_height()-175;
	 var w = get_page_width();
	 jQuery('#tablehight').height(h);
}
function openorganizidtree(){

	    var userid = document.getElementById("userID").value
	    /*
		var obj = window.showModalDialog("<s:url value='/usm/userupdatetreeAction.shtml' />?userID="+userid,"","dialogWidth=300px;dialogHeight=500px;scroll:yes;dialogLeft:200px");
		if(obj != null){
			var info = obj.split(",");
			document.getElementById("organizationName").value = info[1];
			document.getElementById("organizationID").value = info[0];
			
		}
		else{
			
		}*/

		art.dialog.open("<s:url value='/usm/userupdatetreeAction.shtml' />?userID="+userid+"&rad="+Math.random(),
				//userupdatetreeAction.shtml?userID=" + userid,
				{
				width :260,
				height:300,
				id: 'treeOID',
				title: ' ',
				skin: 'aero',
				limit: true,
				lock: true,
				drag: true,
				fixed: false,
					
					follow: document.getElementById('organizationName'),
					yesFn: function(iframeWin, topWin){
			        	//对话框iframeWin中对象
			        	
			        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
			        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
			        	//当前页面中对象
			            var topOrgName =  window.document.getElementById('organizationName');
			            var topOrgID = window.document.getElementById('organizationID');
			            //赋值
			        	if (topOrgName) topOrgName.value = orgNameValue;
			        	if (topOrgID) topOrgID.value = orgIDValue;
			    	}
			    /*noFn:function(iframeWin, topWin){

				  }*/
				});

	}

	function openrolewindow(){


		/*var arr =  window.showModalDialog("<s:url value='/usm/useraddRoleinitAction.shtml' />","","dialogWidth=480px;dialogHeight=300px;scroll:yes;dialogLeft:200px");
		//alert("wwwww:"+arr);
		var roleid ="";
		var rolename = "";

		if(arr != null){ 
			
			for(var i = 0; i < arr.length; i++){
				var s = arr[i].split(",");
				if(i == arr.length-1){
					roleid += s[0];
					rolename += s[1];
					}
				else{
					roleid += s[0]+",";
					rolename += s[1] + ",";
					}
				
			}
			document.getElementById("roleId").value = roleid;
			
			document.getElementById("roleName").value = rolename;
		}*/


		art.dialog.open("<s:url value='/usm/useraddRoleinitAction.shtml' />?rad="+Math.random(),
				//userupdatetreeAction.shtml?userID=" + userid,
				{
				width :480,
				height:300,
				id: 'treeRID',
			    
				title: ' ',
				skin: 'aero',
				limit: true,
				lock: true,
				drag: true,
				fixed: false,
					follow: document.getElementById('roleName'),
					yesFn: function(iframeWin, topWin){
			        	//对话框iframeWin中对象
			        	iframeWin.closewindow();
			        	var orgNameValue = iframeWin.document.getElementById('roleName').value;
			        	var orgIDValue = iframeWin.document.getElementById('roleId').value;
			        	
			        	//当前页面中对象
			            var topRorlName =  window.document.getElementById('roleName');
			            var topRorlID = window.document.getElementById('roleId');
			            //赋值
			        	if (topRorlName) topRorlName.value = orgNameValue;
			        	if (topRorlID) topRorlID.value = orgIDValue;
			    	}
			    /*noFn:function(iframeWin, topWin){

				  }*/
				});

	}
</script>
<%@include file="modifyPersonal_validate.jsp"%>
</body>
</html>
