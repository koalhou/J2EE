<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/UserManageDwr.js'></script>

<script type="text/javascript">
  var count = 0;

  /** 打开企业选择子页面 **/
  function openEnterpriseBrowse() {
    var obj = window.showModalDialog("<s:url value='/popup/enterpriseinit.shtml' />" + "?count=" + (++count) + "&flag=1",
	                                 self,
	                                 "dialogWidth=700px;dialogHeight=700px;scroll:yes;dialogLeft:200px");
  }
  
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
  
  /** 用户名称必填项 **/
  function checkUserName() {
    var elm = $('userName');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
      return false;
	}
  }

  /** 企业必选项 **/
  function checkEnterprise() {
    var elm = $('entipriseName');
    if(Mat.checkRequired(elm)){
        Mat.showSucc(elm);
        return true;
  	}else{
        Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
        return false;
  	}
  }
  
  /** 初始化样式 **/
  function setFormMessage() {
	Mat.setDefault($('loginName'),'');
	Mat.setDefault($('userType'),'<span class="noticeMsg">*</span>');
    //Mat.setDefault($('entiprise'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('userSex'),'<span class="noticeMsg">*</span>');
    //Mat.setDefault($('userRoleID'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('empno'),'');
    Mat.setDefault($('userName'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('userBirth'),'');
    Mat.setDefault($('countryId'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('provinceId'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('cityId'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('userCell'),'');
    Mat.setDefault($('userTel'),'');
    Mat.setDefault($('userEmail'),'');
    Mat.setDefault($('userIdCard'),'');
    Mat.setDefault($('userDuty'),'');
    Mat.setDefault($('entipriseName'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('fax'),'');
    Mat.setDefault($('remarks'),'');
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('userName').onkeyup = checkUserName;
    $('userName').onblur = checkUserName;
  }

  /** 检查员工号格式 **/
  function checkEmpNo() {
    var elm = $('empno');
    if(elm.value == "") {
      Mat.showSucc(elm);
      return true;
    } else {
      if(Wit.checkErr(elm,/^[0-9a-zA-Z]*$/)){
  	    Mat.showSucc(elm);
        return true;
      } else {
        Wit.showErr(elm, "<s:property value="getText('msg.empno')" />");
  	    return false;
      }
    }
  }

  /** check描述文字长度 **/
  function checkMessageInput() {
    var elm = $('remarks');
    if(elm.value != "") {
      if(!Mat.checkLength(elm,60,'<s:text name="enterprise.js.info.lenles60" />')) {
        return false;
      } else {
        Mat.showSucc(elm);
        return true;
      }
	} else {
      return true;
	}
  }

  /** 检查用户名唯一性 **/
  function checkLoginNameUnique(){
    var loginNameObj = $('loginName');
    var userTypeObj = $('userType');
    var enterpriseObj = $('entipriseId');
    
    DWREngine.setAsync(false);
    var ret = false; 
    UserManageDwr.checkLoginNameUnique(loginNameObj.value, userTypeObj.value, enterpriseObj.value, callBackFun);
    
    DWREngine.setAsync(true);
    
    function callBackFun(data)
    {
      ret = data;
    }

    if(ret) {
      Wit.showErr(loginNameObj, "<s:property value="getText('user.exist')" />");
      return false;
    } else {
      return true;
    }
  }
  
  /** 提交form **/
  function submitForm() {
    var f1 = checkUserName();
    var f2 = checkCountry();
    var f3 = checkProvince();
    var f4 = checkCity();
    var f5 = checkEmpNo();
    var f6 = checkEnterprise();
    var f7 = checkMessageInput();
	if (f1 && f2 && f3 && f4 && f5 && f6 && f7) {
	  var nowVal = $('userType');
	  var oldVal = $('oldUsetType');
	  
	  if(nowVal.value != oldVal.value) {
        // 用户类型变化时，检查用户名唯一性  
        var f8 = checkLoginNameUnique();
        if(f8) {
          if(confirm("<s:property value="getText('confirm.modify')" />")) {
            Wit.commitAll($('useredit_form'));
          }
        } 
      } else {		    
	    if(confirm("<s:property value="getText('confirm.modify')" />")) {
	      Wit.commitAll($('useredit_form'));
        }
      }
	} else {
	  return false;
	}
  }

  /** 车主类型与宇通杯用户不需要关联企业 **/
  function changeUserType() {
    var elm = $('userType');
    var enti = $('entipriseName');
    var entiId = $('entipriseId');
    if(elm.value == "2" || elm.value == "3") {
      // 车主/宇通杯类型
      enti.disabled = true;
      enti.value = "宇通杯企业";
    } else {
      enti.disabled = false;
      enti.value = "";
      entiId.value = "";
    }
  }

  function goBack(url) {
    Wit.goBack(url);
  }

  /** 删除用户 **/
  function delUser(url) {
    if(confirm("<s:property value="getText('confirm.delete')" />")) {
      Wit.goBack(url);
    } else {
        return false;
    }
  }

  /** 启用用户 **/
  function startUseUser(url) {
    if(confirm("<s:property value="getText('user.startuse.confirm')" />")) {
      Wit.goBack(url);
    } else {
      return false;
    }
  }

  /** 禁用用户 **/
  function forbidUser(url) {
    if(confirm("<s:property value="getText('user.forbid.confirm')" />")) {
      Wit.goBack(url);
    } else {
      return false;
    }
  }  
  
  /** 重置密码 **/
  /**
  function resetPwd(url) {
    if(confirm("<s:property value="getText('user.reset.confirm')" />")) {
	  Wit.goBack(url);
    } else {
      return false;
    }
  }**/
  
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>