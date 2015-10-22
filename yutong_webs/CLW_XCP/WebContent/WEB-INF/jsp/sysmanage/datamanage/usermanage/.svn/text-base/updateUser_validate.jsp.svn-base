<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
jQuery(function() {
	jQuery('#content').mk_autoresize({
        height_include: '#main',
        height_offset: 0 
      });
});

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
    	checkCountyID();
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
    checkProvinceID();
}
function show_city(){
  checkCityID();
}
function callBackFun_show_enterprise_city(data) {
	var tempObj = document.getElementById('cityID');  
	DWRUtil.removeAllOptions(tempObj);  
	DWRUtil.addOptions(tempObj,{'':'<s:property value="getText('please.select')" />'});  
	DWRUtil.addOptions(tempObj,data);  
}
function bodyload(){
	
	var userType = document.getElementById("userType");
	if(userType.value == 5){
		document.getElementById("roleName").onclick = "";	
		document.getElementById("student_choose").style.display='';	
		document.getElementById("organizationName").onclick = "";	
	}
	else{
		document.getElementById("organizationName").onclick = openorganizidtree;
		document.getElementById("roleName").onclick = openrolewindow;	
		document.getElementById("student_choose").style.display='none';	
	}	
}
function chezhuonchange(){
	
	var userType = document.getElementById("userType");
	if(userType.value == 5){
		document.getElementById("roleName").value = "默认家长用户角色";
		document.getElementById("roleId").value = "parents_role";	
		document.getElementById("roleName").onclick = "";	
		document.getElementById("student_choose").style.display='';	
		document.getElementById("organizationName").value = "<s:property value='#session.adminProfile.fullName'/>";
		document.getElementById("organizationID").value = "<s:property value='#session.adminProfile.entiID'/>";
		document.getElementById("organizationName").onclick = "";
		//add by fanxy
		isShowStuPwd("parents_role");
	}else{
		document.getElementById("organizationName").value = "";
		document.getElementById("organizationID").value = "";
		document.getElementById("organizationName").onclick = openorganizidtree;
		document.getElementById("roleName").value = "";
		document.getElementById("roleId").value = "";	
		document.getElementById("roleName").onclick = openrolewindow;	
		document.getElementById("student_choose").style.display='none';
		//add by fanxy
		isShowStuPwd("");
	}	
}

function choiceStudent() {
	//修改为jquery获取方式
	var url = "../usm/liststudent.shtml?stu_id="+jQuery('#stu_id').val();
	art.dialog.open("<s:url value='"+url+"' />",{
		title:"学生信息",
		lock:true,
		width:700,
		height:435
	});

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
function openorganizidtree(){

    var userid = document.getElementById("userID").value;
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
		    	    checkOrganizationName();
		    	}
		    /*noFn:function(iframeWin, topWin){

			  }*/
			});

}

/**
 * 修改用户角色
 * userupdateRoleinitAction-->触发userupdateAction中的userUpdateRoleInit方法
 */
function openrolewindow(){
	
	//var userID = document.getElementById("userID").value;
	
	art.dialog.open("<s:url value='/usm/userupdateRoleinitAction.shtml' />?rad="+Math.random()+ "&userinfo.roleId=" + document.getElementById('roleId').value,
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
		        	//增加判断 add by fanxy 如果角色具有学生管理的权限 则设置为必选项
		        	isShowStuPwd(orgIDValue);
		        	//当前页面中对象
		            var topRorlName =  window.document.getElementById('roleName');
		            var topRorlID = window.document.getElementById('roleId');
		            //赋值
		        	if (topRorlName) topRorlName.value = orgNameValue;
		        	if (topRorlID) topRorlID.value = orgIDValue;
		        	checkRoleName();
		    	}
			});

}
function choosestmg(){
	if(document.getElementById("stmg").checked){
		document.getElementById("student_pwd_title").style.display='';	
		document.getElementById("student_pwd_td").style.display='';
		document.getElementById("student_flag").value="0";
		document.getElementById("pwd_update_flag").value="0";
    }else{
    	document.getElementById("student_pwd_title").style.display='none';	
		document.getElementById("student_pwd_td").style.display='none';	
		document.getElementById("student_flag").value="1";
		document.getElementById("pwd_update_flag").value="1";
    }
}

//检查是否设置学生管理密码为必填项
function isShowStuPwd(ids){
	
	if(ids != ''){
	var roleids = ids;
	//（DWR）查询数据库检查  判断是否包含 系统功能[学生管理]
	DWREngine.setAsync(false);
	//DWR不允许赋值 只能通过回调方法的形式设置
	UserNameSameDwr.queryRoleRight(roleids,isShowStuPwd_callback);
	
	function isShowStuPwd_callback(data){
		
		//0-具有学生管理密码    1-不具有学生管理密码
		var student_flag = document.getElementById("student_flag").value;
		var flag = true;
		if(student_flag == 0){
			flag = true;
		}else{
			flag = false;
		}
			
		document.getElementById("pwd_update_flag").value="0";
		if(data){
			if(!flag){//如果原来没有必填项编辑 则显示
			//设置学生管理密码为必填项
			document.getElementById("student_pwd_title").style.display='';	
			document.getElementById("student_pwd_td").style.display='';
			document.getElementById("student_flag").value="0";
			document.getElementById("stmg").checked=true;
			document.getElementById("stmg").style.display='none';
			document.getElementById("student_reset_title").style.display='none';
			document.getElementById("student_reset_td").style.display='none';
			}else{
				document.getElementById("pwd_update_flag").value="1";
				//document.getElementById("stmg").style.display='';
			}
		}else{
			//非必填项
			document.getElementById("student_pwd").value='';
			document.getElementById("student_pwd_title").style.display='none';	
			document.getElementById("student_pwd_td").style.display='none';	
			document.getElementById("student_flag").value="1";
			document.getElementById("stmg").style.display='none';
			document.getElementById("student_reset_title").style.display='none';
			document.getElementById("student_reset_td").style.display='none';
		}
	}
	DWREngine.setAsync(true);
	}else{
		//非必填项
		document.getElementById("student_pwd").value='';
		document.getElementById("student_pwd_title").style.display='none';	
		document.getElementById("student_pwd_td").style.display='none';	
		document.getElementById("student_flag").value="1";
		document.getElementById("pwd_update_flag").value="0";
		document.getElementById("stmg").style.display='none';
		//家长用户
		if(document.getElementById("student_reset_title")){
			document.getElementById("student_reset_title").style.display='none';
		}
		if(document.getElementById("student_reset_td")){
			document.getElementById("student_reset_td").style.display='none';
		}	
	}
}

	/** 用户名校验 **/
	function checkLoginName() {
		var loginName = $('loginName');
		if (!Mat.checkRequired(loginName))
			return false;
		if (!Mat
				.checkLength(
						loginName,
						32,
						'用户名' + '的长度不能超过' + '32'))
			return false;
		return true;
	}

	/** 密码校验 **/
	function checkPassWord() {
		var loginPwd = $('loginPwd');
		if (!Mat.checkRequired(loginPwd))
			return false;
		if (!Mat
				.checkLength(
						loginPwd,
						32,
						'密码' + '的长度不能超过' + '32'))
			return false;
		return true;
	}
    /** 学生校验 **/
	function checkstudentID() {
		var stu_id = $('stu_id');
		if (!Mat.checkRequired(stu_id))
			return false;
		return true;
	}
	
	/**所属部门必填**/
	function checkOrganizationName() {
		var organizationName = $('organizationName');
		if (!Mat.checkRequired(organizationName))
			return false;
		return true;
	}

	/**角色校验**/
	function checkRoleName() {
		var roleName = $('roleName');
		if (!Mat.checkRequired(roleName))
			return false;
		return true;
	}

	/** 姓名 **/
	function checkUserName() {
		var userName = $('userName');
		if (!Mat.checkRequired(userName))
			return false;
		if (!Mat
				.checkLength(
						userName,
						32,
						'姓名' + '的长度不能超过' + '32'))
			return false;
		return true;
	}

	/**国家**/
	function checkCountyID() {
		var countyID = $('countyID');
		if (!Mat.checkRequired(countyID))
		{
			Wit.showErr(countyID, "<s:property value="getText('please.select')" />");
			return false;
		}
		return true;
	}
	/**省**/
	function checkProvinceID() {
		var provinceID = $('provinceID');
		if (!Mat.checkRequired(provinceID))
		{
			Wit.showErr(provinceID, "<s:property value="getText('please.select')" />");
			return false;
		}
			
		return true;
	}
	/**市**/
	function checkCityID() {
		var cityID = $('cityID');
		if (!Mat.checkRequired(cityID))
		{
			Wit.showErr(cityID, "<s:property value="getText('please.select')" />");
			return false;
		}
		return true;
	}
	//验证email
	function checkEMAIL(){
		var elm=$('email');
		if(elm.value!=''){
			if(!Mat.checkEmail(elm,'请正确填写')){
			    return false;
			}
		}else{
			Mat.showSucc(elm);
		}
		return true;
	}
	
	//验证手机号
	function checktelNo(){
		var elm=$('moblie');
		if(elm.value!=''){
		   if(!Mat.checkTelePhone(elm,'请正确填写')){
			  return false;
		   }
		}else{
			Mat.showSucc(elm);
		}
		return true;
	}
	
	//验证身份证 idCard
	function checkIdCard(){
		var elm=$('idCard');
		if(elm.value!=''){
		   if(!Mat.checkIDCard(elm,'请正确填写')){
			  return false;
		   }
		}else{
			Mat.showSucc(elm);
		}
		return true;
	}
	
	//验证固定电话tel
	function checkTel(){
		var elm=$('tel');
		if(elm.value!=''){
		   if(!Mat.checkTEL(elm,' 请正确填写')){
			  return false;
		   }
		}else{
			Mat.showSucc(elm);
		}
		return true;
	}
	
	
	/**管理密码**/
	function checkStudent_pwd() {
		var student_pwd = $('student_pwd');
		if (!Mat.checkRequired(student_pwd))
			return false;
		//增加密码中空格的校验
		if(!Mat.checkPWDText(student_pwd,'密码为6-15位数字和字母以及符号的组合，不能含有空格'))
			return false;
		return true;
	}
	/** 初始化样式 **/
	function setFormMessage() {

	}

	/** 加载事件 **/
	function setFormEvent() {

		$('loginName').onkeyup = checkLoginName;
		$('loginName').onblur = checkLoginName;
		//$('loginPwd').onkeyup = checkPassWord;
		//$('loginPwd').onblur = checkPassWord;
		//add by yangliang
		$('stu_name').onkeyup = checkstudentID;
		$('stu_name').onblur = checkstudentID;
		$('student_pwd').onkeyup=checkStudent_pwd;
		$('student_pwd').onblur=checkStudent_pwd;
		//add by yangliang
		//$('organizationName').onkeyup = checkOrganizationName;
		//$('organizationName').onblur = checkOrganizationName;
		//$('roleName').onkeyup = checkRoleName;
		//$('roleName').onblur = checkRoleName;
		$('userName').onkeyup = checkUserName;
		$('userName').onblur = checkUserName;
		$('countyID').onkeyup = checkCountyID;
		$('countyID').onblur = checkCountyID;
		$('provinceID').onkeyup = checkProvinceID;
		$('provinceID').onblur = checkProvinceID;
		$('cityID').onkeyup = checkCityID;
		$('cityID').onblur = checkCityID;
		//Email
		$('email').onkeyup = checkEMAIL;
		$('email').onblur = checkEMAIL;
		//身份证
		$('idCard').onkeyup = checkIdCard;
		$('idCard').onblur = checkIdCard;
		//手机
		$('moblie').onkeyup=checktelNo;
		$('moblie').onblur=checktelNo;
		//固定电话
		$('tel').onkeyup=checkTel;
		$('tel').onblur=checkTel;
		
		//修改时进去第一次就判定是否正确
		checkLoginName();
		checkOrganizationName();
		checkRoleName();
		checkUserName();
		checkCountyID();
		checkProvinceID();
		checkCityID();
		checkEMAIL();
		checkIdCard();
		checktelNo();
		checkTel();
	}

	function submitForm() {
		trimAllObjs();
		var userType = document.getElementById("userType");
		//var student_flag = document.getElementById("student_flag");
		var f0 = checkLoginName();
		//var f1 = checkPassWord();
		var f2 = checkOrganizationName();
		var f3 = checkRoleName();
		var f4 = checkUserName();
		var f5 = checkCountyID();
		var f6 = checkProvinceID();
		var f7 = checkCityID();
		//email
		var f8 = checkEMAIL();
		var f9 = true;
		var f10 = true;
		if(userType.value == 5){			  
			f9 = checkstudentID();
		}
		//身份证
		var f11 = checkIdCard();
		//手机
		var f12 = checktelNo();
		//固定电话
		var f13 = checkTel();
		
		if(document.getElementById("stmg").checked && document.getElementById("student_flag").value == 0){
				f10 = checkStudent_pwd();
		}
		if (f0&f2&f3&f4&f5&f6&f7&f8&f9&f10&f11&f12&f13) {
			//var form = document.getElementById('updateuserform');
			//form.submit();
			Wit.commitAll($('updateuserform'));
		} else {
			return false;
		}
	}

	function submitalterForm(){
		var f0 = checkLoginName();
		//var f1=  checkDriverLicense();
		//var f3= checkDriverAddress();
		if (f0&f1&f3) {
			var form = document.getElementById('updateuserform');
			form.submit();
		} else {
			return false;
		}
	}

	/** 车主类型用户不需要关联企业 **/
	function changeUserType() {

	}

	function goBack(url) {
		Wit.goBack(url);
	}

	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);
</script>