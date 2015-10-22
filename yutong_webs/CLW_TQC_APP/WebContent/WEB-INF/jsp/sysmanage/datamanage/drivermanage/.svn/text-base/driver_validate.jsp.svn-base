<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/PasswordConfirmDWR.js'></script>
<script type="text/javascript">
	function fileFormate() {
		art.dialog.open("<s:url value='/picturefileupload/fileUploadInit.shtml' />",{
			title:"添加照片",
			lock:true,
			width:900,
			height:530
		});
	}
	
	function photoReload() {
		document.getElementById("photoPreview").style.display="block";
		document.getElementById("noPhoto").style.display="none";
		var preview =  document.getElementById("preview");
		preview.src="<s:url value='/picturefileupload/showImage.shtml'/>"+ "?dataId="  + document.getElementById("picId").value + "&rand=" + Math.random();
	}
	
	function clearPhoto() {
		if(confirm("确定要删除该相片信息么？")) {
			document.getElementById("photoPreview").style.display="none";
			document.getElementById("noPhoto").style.display="block";
			document.getElementById("picId").value = "";
		}
	}
	/** 获取卡号 **/
	function getCardId() {
	  try {
		var obj = document.getElementById('GenerateCardId');
		var connValue = obj.OpenComm();
		if(0 == connValue) {
			var cardId = obj.QueryCard();
			if(0 == cardId) {
		    	alert("未找到卡");
			} else {
				document.getElementById('driver_card').value = cardId;
			}
			obj.CloseComm();
		} else {
			alert("设备未连接上");
		}
	  }catch(e) {
		alert("未知异常");	
	  }
	}
	
	/** 驾驶员姓名必填项 **/
	function checkDriverName() {
		var driver_name = $('driver_name');
		if (!Mat.checkRequired(driver_name))
			return false;
		if (!Mat.checkLength(driver_name, 16, '<s:text name="common.js.info.lenles"/>' + '16'))
			return false;
		return true;
	}

	/** 驾驶证号必填项 **/
	function checkDriverLicense() {
		var driver_license = $('driver_license');
		
		
		if(Wit.checkErr(driver_license, /^[0-9-a-z-A-Z]+$/)) {
			Mat.showSucc(driver_license);
			return true;
		} else {
			Wit.showErr(driver_license,"驾驶证号为数字与字母组合");
			return false;
		}
		
		
		if (!Mat.checkRequired(driver_license))
			return false;
		if (!Mat.checkLength(driver_license, 32, '<s:text name="common.js.info.lenles"/>' + '32'))
			return false;
		return true;
		
	}

	/** 驾驶员卡号必填项 **/
	function checkDriverCard() {
		var obj = $('driver_card');

		if(Wit.checkErr(obj, /^[0-9-a-z-A-Z]+$/)) {
			Mat.showSucc(obj);
			return true;
		} else {
			Wit.showErr(obj,"卡号为数字与字母组合");
			return false;
		}
	}

	/** 驾驶员联系电话必填项 **/
	function checkDriverTel() {
		var cell_number = $('cell_number');
		/*if (!Mat.checkRequired(cell_number))
			return false;
		if (!Mat.checkLength(cell_number, 11, '<s:text name="common.js.info.lenles"/>' + '11'))
			return false;
		if(Wit.checkErr(cell_number, /^[0-9]+$/)) {
			Mat.showSucc(cell_number);
			return true;
		} else {
			Wit.showErr(cell_number,"电话号码只能为数字");
			return false;
		}*/
		
		if (!Mat.checkRequired(cell_number))
			return false;
		if(cell_number.value!=''){
			if(!Mat.checkTelePhone(cell_number)){
				Wit.showErr(cell_number, "<s:property value="getText('enterprise.js.info.telSucc')"/>");
			    return false;
			}
		}
		return true;
	}

	/** 驾驶员所属企业必填项 **/
	function checkOrganizationName() {
		var obj = $('organizationName');
		if (!Mat.checkRequired(obj))
			return false;
		else{
			Mat.showSucc(obj);
			return true;
		}
		
	}
	
	/**住址超长判断**/
	function checkDriverAddress(){
		var driver_address = $('driver_address');
		if (!Mat.checkLength(driver_address, 40, '<s:text name="common.js.info.lenles"/>' + '40'))
			return false;
		return true;
	}

	/** 初始化样式 **/
	function setFormMessage() {
		Mat.setDefault($('driver_name'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('driver_license'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('driver_card'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('cell_number'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('organizationName'),'<span class="noticeMsg">*</span>');
	}

	/** 加载事件 **/
	function setFormEvent() {
		$('driver_name').onkeyup = checkDriverName;
		$('driver_name').onblur = checkDriverName;
		$('driver_license').onkeyup = checkDriverLicense;
		$('driver_license').onblur=checkDriverLicense;
		$('driver_address').onkeyup = checkDriverAddress;
		$('driver_address').onblur=checkDriverAddress;
		$('driver_card').onkeyup = checkDriverCard;
		$('driver_card').onblur=checkDriverCard;
		$('cell_number').onkeyup = checkDriverTel;
		$('cell_number').onblur=checkDriverTel;
		$('organizationName').onkeyup = checkOrganizationName;
		$('organizationName').onblur = checkOrganizationName;
	}
	/** 验证驾驶员证号唯一性 **/
	function checkDriverLicenseUnique(){
	    var obj = $('driver_license');
		DWREngine.setAsync(false);
		var ret = false; 
		PasswordConfirmDWR.checkDriverLicense(obj.value, callBackFun);
		    
		function callBackFun(data)
		{
		  ret = data;
		}
		    
		DWREngine.setAsync(true);
		   
		if(ret) {
		  Wit.showErr(obj, "驾驶证号已存在！");
		  return false;
		} else {
		  return true;
		}
	}
	
	/** 验证驾驶员证号唯一性 **/
	function checkDriverLicenseUnique1(){
	    var obj = $('driver_license');
	    var obj1= $('old_driver_license');
		
	  	//var oldCard = $('old_driver_card');
		//var newCard = $('driver_card');
		//var oldLicense= $('old_driver_license');
		//var newLicense = $('driver_license');
		DWREngine.setAsync(false);
		var ret = false; 
		if(obj.value == obj1.value){
			PasswordConfirmDWR.checkDriverLicense1(obj.value, callBackFun);
		}
		else{
			PasswordConfirmDWR.checkDriverLicense(obj.value, callBackFun);
		}
		    
		function callBackFun(data)
		{
		  ret = data;
		}
		    
		DWREngine.setAsync(true);
		   
		if(ret) {
		  Wit.showErr(obj, "驾驶证号已存在！");
		  return false;
		} else {
		  return true;
		}
	}

	/** 验证驾驶员卡号唯一性 **/
	function checkDriverCardUnique(){
	    var obj = $('driver_card');
		DWREngine.setAsync(false);
		var ret = false;
		PasswordConfirmDWR.checkDriverCard(obj.value, callBackFun);

		    
		function callBackFun(data)
		{
		  ret = data;
		}
		    
		DWREngine.setAsync(true);
		   
		if(ret) {
		  Wit.showErr(obj, "卡号已存在！");
		  return false;
		} else {
		  return true;
		}
	}

	/** 验证驾驶员卡号唯一性 **/
	function checkDriverCardUnique1(){
	    var obj = $('driver_card');
	    var obj1 = $('old_driver_card');
 
	    //var oldCard = $('old_driver_card');
		//var newCard = $('driver_card');
		//var oldLicense= $('old_driver_license');
		//var newLicense = $('driver_license');
		DWREngine.setAsync(false);
		var ret = false;
		if(obj.value == obj1.value){
			PasswordConfirmDWR.checkDriverCard1(obj.value, callBackFun);
		}
		else{
			PasswordConfirmDWR.checkDriverCard(obj.value, callBackFun);
		}
		    
		function callBackFun(data)
		{
		  ret = data;
		}
		    
		DWREngine.setAsync(true);
		   
		if(ret) {
		  Wit.showErr(obj, "卡号已存在！");
		  return false;
		} else {
		  return true;
		}
	}

	/** 企业组织选择 **/
	function openorganizidtree(){
		art.dialog.open("<s:url value='/usm/usermanagetreeAction.shtml' />?rad="+Math.random(),
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
				yesFn: function(iframeWin, topWin){
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
				});
		
	}
	
	function submitForm() {
		trimAllObjs();
		var f0 = checkDriverName();
		var f1 = checkDriverLicense();
		var f2 = checkDriverCard();
		var f3 = checkDriverTel();
		var f5 = checkDriverAddress();
		var f6 = checkDriverCardUnique();
		var f7 = checkOrganizationName();
		var f8 = checkDriverLicenseUnique();
		if (f0 && f1 && f2 && f3 && f5 && f6 && f7 && f8) {
			Wit.commitAll($('add_driverform'));
		} else {
			return false;
		}
	}

	function submitalterForm(){
		trimAllObjs();
		var f0 = checkDriverName();
		var f1 = checkDriverLicense();
		var f2 = checkDriverCard();
		var f3 = checkDriverTel();
		var f5 = checkDriverAddress();
		var f6 = checkOrganizationName();
		var f8 = checkDriverCardUnique1();
		var f7 = checkDriverLicenseUnique1();
		if (f0 && f1 && f2 && f3 && f5 && f6&&f7&&f8) {
			//var oldCard = $('old_driver_card');
			//var newCard = $('driver_card');
			//var oldLicense= $('old_driver_license');
			//var newLicense = $('driver_license');
			//var f8 = checkDriverCardUnique();
			//var f7 = checkDriverLicenseUnique();
			//var f7 = checkStudentCodeUnique();
			//var f8 = checkStudentCardUnique();
			
				Wit.commitAll($('alter_driverform'));
			}
		else{
			return false;
		}
		
		
	}
			

	function goBack(url) {
		Wit.goBack(url);
	}

	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);
</script>