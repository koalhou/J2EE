<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/PasswordConfirmDWR.js'></script>
<script type="text/javascript">
	function fileFormate() {
		//Wit.goBack("<s:url value='/picturefileupload/fileUploadInit.shtml' />");
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

	/** 验证司乘卡号唯一性 **/
	function checkStewardCardUnique(){
	    var obj = $('steward_card');
		    
		DWREngine.setAsync(false);
		var ret = false; 
		PasswordConfirmDWR.checkStewardCard(obj.value, callBackFun);
		    
		function callBackFun(data)
		{
		  ret = data;
		}
		    
		DWREngine.setAsync(true);
		   
		if(ret) {
		  Wit.showErr(obj, "司乘卡号已存在！");
		  return false;
		} else {
		  return true;
		}
	}
	
	/** 企业组织选择 **/
	function openorganizidtree(){
		art.dialog.open("<s:url value='/usm/usermanagetreeAction.shtml' />?rad="+Math.random(),
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
					
				yesFn: function(iframeWin, topWin){
			        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
			        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
			        	//当前页面中对象
			            var topOrgName =  window.document.getElementById('organizationName');
			            var topOrgID = window.document.getElementById('organizationID');
			            //赋值
			        	if (topOrgName) topOrgName.value = orgNameValue;
			        	if (topOrgID) topOrgID.value = orgIDValue;
			    	}
				});
	}

	/** 所属企业必填项 **/
	function checkOrganizationName() {
		var obj = $('organizationName');
		if (!Mat.checkRequired(obj))
			return false;
		return true;
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
				document.getElementById('steward_card').value = cardId;
			}
			obj.CloseComm();
		} else {
			alert("设备未连接上");
		}
	  }catch(e) {
		alert("未知异常");	
	  }
	}
	
	/** 姓名必填项 **/
	function checkStewardName() {
		var obj = $('steward_name');
		if (!Mat.checkRequired(obj))
			return false;
		if (!Mat.checkLength(obj, 16, '<s:text name="common.js.info.lenles"/>' + '16'))
			return false;
		return true;
	}

	/** 身份证号必填项 **/
	function checkStewardLicense() {
		var obj = $('steward_ID_Card');
		if (!Mat.checkRequired(obj))
			return false;
		if (!Mat.checkLength(obj, 32, '<s:text name="common.js.info.lenles"/>' + '32'))
			return false;
		if(!Mat.checkText(obj,'<s:text name="driverinfo.license.msg" />'))
			return false;
		return true;
	}

	/** 卡号必填项 **/
	function checkStewardCard() {
		var obj = $('steward_card');
		if(Wit.checkErr(obj, /^[0-9-a-z-A-Z]+$/)) {
			Mat.showSucc(obj);
			return true;
		} else {
			Wit.showErr(obj,"卡号为数字与字母组合");
			return false;
		}
	}

	/** 联系电话必填项 **/
	function checkStewardTel() {
		var cell_number = $('cell_number');
		if (!Mat.checkRequired(cell_number))
			return false;
		return true;
	}
	
	/**住址超长判断**/
	function checkStewardAddress(){
		var obj = $('steward_address');
		if (!Mat.checkLength(obj, 40, '<s:text name="common.js.info.lenles"/>' + '40'))
			return false;
		return true;
	}

	/** 初始化样式 **/
	function setFormMessage() {
		Mat.setDefault($('steward_name'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('steward_card'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('steward_ID_Card'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('cell_number'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('organizationName'),'<span class="noticeMsg">*</span>');
	}

	/** 加载事件 **/
	function setFormEvent() {
		$('steward_name').onkeyup = checkStewardName;
		$('steward_name').onblur = checkStewardName;
		$('steward_card').onkeyup = checkStewardCard;
		$('steward_card').onblur=checkStewardCard;
		$('steward_address').onkeyup = checkStewardAddress;
		$('steward_address').onblur=checkStewardAddress;
		$('steward_ID_Card').onkeyup = checkStewardLicense;
		$('steward_ID_Card').onblur=checkStewardLicense;
		$('cell_number').onkeyup = checkStewardTel;
		$('cell_number').onblur=checkStewardTel;
		$('organizationName').onkeyup = checkOrganizationName;
		$('organizationName').onblur = checkOrganizationName;
	}

	function submitForm() {
		trimAllObjs();
		var f0 = checkStewardName();
		var f1 = checkStewardLicense();
		var f2 = checkStewardCard();
		var f3 = checkStewardTel();
		var f5 = checkStewardAddress();
		var f6 = checkStewardCardUnique();
		var f7 = checkOrganizationName();
		if (f0 && f1 && f2 && f3 && f5 && f6 && f7) {
			Wit.commitAll($('add_stewardform'));
		} else {
			return false;
		}
	}

	function submitalterForm(){
		trimAllObjs();
		var f0 = checkStewardName();
		var f1 = checkStewardLicense();
		var f2 = checkStewardCard();
		var f3 = checkStewardTel();
		var f5 = checkStewardAddress();
		var f6 = checkOrganizationName();
		if (f0 && f1 && f2 && f3 && f5 && f6) {
			var oldStewardCard = $('old_steward_card');
			var newStewardCard = $('steward_card');
			  
			if(oldStewardCard.value != newStewardCard.value) {
		      var f6 = checkStewardCardUnique();
		      if(f6) {
			    Wit.commitAll($('alter_stewardform'));
		      }
			} else {
			  Wit.commitAll($('alter_stewardform'));
			}
		} else {
			return false;
		}
	}

	function goBack(url) {
		Wit.goBack(url);
	}

	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);
</script>