<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
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
		if (!Mat.checkLength(
						userName,
						32,
						'姓名' + '的长度不能超过' + '32'))
			return false;
		if(!Mat.checkTrimRequired(userName))
			return false;
		return true;
	}

	/**国家**/
	function checkCountyID() {
		var countyID = $('countyID');
		if (!Mat.checkRequired(countyID))
			return false;
		return true;
	}
	/**省**/
	function checkProvinceID() {
		var provinceID = $('provinceID');
		if (!Mat.checkRequired(provinceID))
			return false;
		return true;
	}
	/**市**/
	function checkCityID() {
		var cityID = $('cityID');
		if (!Mat.checkRequired(cityID))
			return false;
		return true;
	}
	function checkEMAIL(){
		var elm=$('email');
		if(elm.value!=''){
			if(!Mat.checkEmail(elm,'请正确填写!')){
			    return false;
			}
		} else {
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
	
	/** 初始化样式 **/
	function setFormMessage() {

	}

	/** 加载事件 **/
	function setFormEvent() {

		//$('loginName').onkeyup = checkLoginName;
		//$('loginName').onblur = checkLoginName;
		//$('loginPwd').onkeyup = checkPassWord;
		//$('loginPwd').onblur = checkPassWord;
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
		$('email').onkeyup = checkEMAIL;
		$('email').onblur = checkEMAIL;
		//$('driver_license').onkeyup = checkDriverLicense;
		//$('driver_license').onblur=checkDriverLicense;
		//身份证
		$('idCard').onkeyup = checkIdCard;
		$('idCard').onblur = checkIdCard;
		//手机
		$('moblie').onkeyup=checktelNo;
		$('moblie').onblur=checktelNo;
		//固定电话
		$('tel').onkeyup=checkTel;
		$('tel').onblur=checkTel;
		
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
		//var f0 = checkLoginName();
		//var f1 = checkPassWord();
		//var f2 = checkOrganizationName();
		//var f3 = checkRoleName();
		var f4 = checkUserName();
		var f5 = checkCountyID();
		var f6 = checkProvinceID();
		var f7 = checkCityID();
		var f8 = checkEMAIL();
		
		var f9 = true;
		var f10 = true;
// 		if(userType.value == 5){			  
// 			f9 = checkstudentID();
// 		}
		//身份证
		var f11 = checkIdCard();
		//手机
		var f12 = checktelNo();
		//固定电话
		var f13 = checkTel();
		if (f4&f5&f6&f7&&f8&&f9&&f10&&f11&&f12&&f13) {
			//var form = document.getElementById('updateuserform');
			//form.submit();
			Wit.commitAll($('modifyPersonalform'));
		} else {
			return false;
		}
	}

	function submitalterForm(){
		//var f0 = checkLoginName();
		//var f1=  checkDriverLicense();
		//var f3= checkDriverAddress();
		if (f0&f1&f3) {
			var form = document.getElementById('modifyPersonalform');
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
	
	
	function modifyPass() {	
				art.dialog.open("<s:url value='/pawset/alterpaw.shtml' />",{
					title:"修改密码",
					lock:true,
					width:400,
					height:200
				});
			
	} 
	function modifyStudent_Pwd() {	
		art.dialog.open("<s:url value='/pawset/alterpawST.shtml' />",{
			title:"修改模块密码",
			lock:true,
			width:400,
			height:200
		});
	
} 
	
</script>