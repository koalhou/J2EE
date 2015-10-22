<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/EntiDwr.js'></script>
<script type="text/javascript">
	/** 获取所属省信息 **/
	function getProvinceInfo() {
	  var countryObj = $('countryId');
	  
	  ZoneDwr.showZoneXsInfo(countryObj.value, callBackFun);
	
	  if(countryObj.value != "") {
	    Mat.showSucc(countryObj);
	  }
	  
	  function callBackFun(data) {
	    var provinceObj = $('provinceId');  
	    DWRUtil.removeAllOptions(provinceObj);  
	    DWRUtil.addOptions(provinceObj,{'':'<s:property value="getText('please.select')" />'});  
	    if(countryObj.value != "") {
	  	  DWRUtil.addOptions(provinceObj,data);
	    }
	  
	    var cityObj = $('cityId');
	    DWRUtil.removeAllOptions(cityObj);  
	    DWRUtil.addOptions(cityObj,{'':'<s:property value="getText('please.select')" />'});  
	  }
	}
	
	/** 获取所属市信息 **/
	function getCityInfo() {
	  var provinceObj = $('provinceId');
	  ZoneDwr.showZoneXsInfo(provinceObj.value, callBackFun);
	  
	  if(provinceObj.value != "") {
	    Mat.showSucc(provinceObj);
	  }
	  
	  function callBackFun(data) {
	    var cityObj = $('cityId');  
	    DWRUtil.removeAllOptions(cityObj);  
	    DWRUtil.addOptions(cityObj,{'':'<s:property value="getText('please.select')" />'});  
	    if(provinceObj.value != "") {
	  	  DWRUtil.addOptions(cityObj,data);
	    }
	      
	  }
	}
	
	/** 所属市变化 **/
	function onCityChange() {
		var cityObj = $('cityId');
		if(cityObj.value != "") {
		  Mat.showSucc(cityObj);
		}
	}
	
	/** 所属国家必选 **/
	function checkCountry() {
	  var countryObj = $('countryId');
	  if(countryObj.value == "") {
	    Wit.showErr(countryObj, "<s:property value="getText('please.select')" />");
	    return false;
	  } else {
	    return true;
	  }
	}
		  
	/** 所属省必选 **/
	function checkProvince() {
	  var provinceObj = $('provinceId');
	  if(provinceObj.value == "") {
	    Wit.showErr(provinceObj, "<s:property value="getText('please.select')" />");
	    return false;
	  } else {
	    return true;
	  }
	}
	
	/** 所属市必选 **/
	function checkCity() {
	  var cityObj = $('cityId');
	  if(cityObj.value == "") {
	    Wit.showErr(cityObj, "<s:property value="getText('please.select')" />");
	    return false;
	  } else {
	    return true;
	  }
	}

	function checkEnterpriseCode(){
		var elm=$('enterpriseCode');
		if(Wit.checkErr(elm,/^[0-9a-zA-Z]+$/)){
	        Mat.showSucc(elm);
		    return true;
	    } else {
	    	Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.enterprise_code')" />");
	    	return false;
		}
	}

	function checkEnterpriseShortName(){
		var elm=$('enterpriseShortName');
		if(!Mat.checkRequired(elm)){
		    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
		    return false;
		}else if(!Mat.checkLength(elm,8)){
			Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.lenles8')"/>");
		    return false;
		}else{
			Mat.showSucc(elm);
			return true;
		}
	}

	function checkEnterpriseFullName(){
		var elm=$('enterpriseName');
		if(!Mat.checkRequired(elm)){
		    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
		    return false;
		}else if(!Mat.checkLength(elm,60)){
			Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.lenles60')"/>");
		    return false;
		}else{
			Mat.showSucc(elm);
			return true;
		}
		
	}

	function checkEnterpriseMessageNum(){
		var elm=$('enterpriseMessageNum');
		if(!Mat.checkRequired(elm)){
		    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
		    return false;
		}else if(!Mat.checkDigital(elm)){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}else if(!Mat.checkLength(elm,5)){
			Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.lenles5')"/>");
		    return false;
		}else{
			Mat.showSucc(elm);
			return true;
		}
	}

	//ENTERPRISE_CODE唯一验证
	function checkCode(){
		var elm = $('enterpriseCode');
		var v_temp=elm.value;
		if(v_temp.length > 0) {
		  for(var i=v_temp.length;i<10;i++){
			v_temp="0"+v_temp;
		  }
		}
		elm.value=v_temp;
		DWREngine.setAsync(false);
	    var ret = true; 
	    EntiDwr.checkCODE(elm.value, callBackFun);
	    function callBackFun(data)
	    {
	        ret = data;
	    }
	    DWREngine.setAsync(true);
	    if(ret) {
	    	return true;
	  	} else {
	  		Wit.showErr(elm, "<s:property value="getText('enterprise.js.info.enterprise_code.only')" />");
	      	return false;
	  	}
	}
	
	/** 初始化样式 **/
	function setFormMessage() {
     Mat.setDefault($('enterpriseCode'),'<span class="noticeMsg">*</span>');
     Mat.setDefault($('enterpriseName'),'<span class="noticeMsg">*</span>');
     Mat.setDefault($('enterpriseShortName'),'<span class="noticeMsg">*</span>');
     Mat.setDefault($('enterpriseMessageNum'),'<span class="noticeMsg">*</span>');
     Mat.setDefault($('countryId'),'<span class="noticeMsg">*</span>');
     Mat.setDefault($('provinceId'),'<span class="noticeMsg">*</span>');
     Mat.setDefault($('cityId'),'<span class="noticeMsg">*</span>');
	}

	/** 加载事件 **/
	function setFormEvent() {
		$('countryId').onkeyup = checkCountry;
		$('countryId').onkeydown = checkCountry;
		$('provinceId').onkeyup = checkProvince;
		$('provinceId').onkeydown = checkProvince;
		$('cityId').onkeyup = checkCity;
		$('cityId').onkeydown = checkCity;
	}
	
	function createEnterprise(){
		trimAllObjs();
		var f1 = checkEnterpriseCode();
		var f2 = checkEnterpriseShortName();
		var f3 = checkEnterpriseFullName();
		var f4 = checkCountry();
		var f5 = checkProvince();
		var f6 = checkCity();
		var f7 = checkEnterpriseMessageNum();
		var f8 = checkCode();
		if(f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8) {
			jQuery.ajax({
				type: 'POST', 
				url: "<s:url value='/popup/createEnterpriseView.shtml' />",	
				data: {
				    enterpriseCode:$('enterpriseCode').value,
				    enterpriseName:$('enterpriseName').value,
				    enterpriseShortName:$('enterpriseShortName').value,
				    enterpriseMessageNum:$('enterpriseMessageNum').value,
				    countryId:$('countryId').value,
				    provinceId:$('provinceId').value,
				    cityId:$('cityId').value
				},
				success: function(data) {
					if(data=="success"){
						var obj = $('successLbl');
						obj.innerHTML = "企业创建成功！";
						parent.window.submit();
						art.dialog.close();
					}
					if(data == "error") {
						var obj = $('errorLbl');
						obj.innerHTML = "企业创建失败！";
						return false;
					}
				}  
			});
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