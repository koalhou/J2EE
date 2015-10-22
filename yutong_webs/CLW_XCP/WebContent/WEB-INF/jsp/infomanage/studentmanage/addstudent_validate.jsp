<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/PasswordConfirmDWR.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/EntiDwr.js'></script>
<script type="text/javascript">
var enCity = '';
function changeProvinceCity() {
	// 设置省市信息
	var entiID = '<s:property value='#session.adminProfile.entiID'/>';
	EntiDwr.getEnterpriseDataInfoToShow(entiID, {
        callback: function(data) {
        	var enProvince = data.enterprise_province;
        	enCity = data.enterprise_city;
        	jQuery("#studentProvince option").each(function(){
				if(jQuery(this).text()== enProvince) {
					jQuery(this).attr("selected","true");
					return false;
				}
			});

        	getCityInfo();
        },
        errorHandler: function(msg, ex) {
            alert(msg);
        }
    });
}

	/** 获取所属市信息 **/
	function getCityInfo() {
	  var provinceObj = $('studentProvince');
	  
	  ZoneDwr.getGeographyInfoByPid(provinceObj.value, callBackFun);
	
	  if(provinceObj.value != "") {
	    Mat.showSucc(provinceObj);
	  }
	  
	  function callBackFun(data) {
	    var cityObj = $('studentCity');  
	    DWRUtil.removeAllOptions(cityObj);  
	    DWRUtil.addOptions(cityObj,{'':'<s:property value="getText('please.select')" />'});  
	    if(provinceObj.value != "") {
	      if(null!=data){
			if(data.length>0){
			  DWRUtil.addOptions(cityObj,data,"zone_id","zone_name");
			}
		  }
	    }
	    var districtObj = $('studentDistrict');
	    DWRUtil.removeAllOptions(districtObj);  
	    DWRUtil.addOptions(districtObj,{'':'<s:property value="getText('please.select')" />'}); 
	    
	    if (enCity != ''){
	    	jQuery("#studentCity option").each(function(){
				if(jQuery(this).text()== enCity) {
					jQuery(this).attr("selected","true");
					return false;
				}
			});
	    	getDistrictInfo();
	    } 
	  }
	}

	/** 获取所属县信息 **/
    function getDistrictInfo() {
	    var cityObj = $('studentCity');
	    ZoneDwr.getGeographyInfoByPid(cityObj.value, callBackFun);
	    
	    if(cityObj.value != "") {
	      Mat.showSucc(cityObj);
	    }
	    
	    function callBackFun(data) {
	      var districtObj = $('studentDistrict');  
	      DWRUtil.removeAllOptions(districtObj);  
	      DWRUtil.addOptions(districtObj,{'':'<s:property value="getText('please.select')" />'});  
	      if(cityObj.value != "") {
	    	  if(null!=data){
	  			if(data.length>0){
	  				DWRUtil.addOptions(districtObj,data,"zone_id","zone_name");
	  			}
			  }
	      }
	        
	    }
	}

    /** 所属县变化 **/
    function onDistrictChange() {
  	  var districtObj = $('studentDistrict');
  	  if(districtObj.value != "") {
  	    Mat.showSucc(districtObj);
  	  }
    }

    /** 所属省必选 **/
    function checkProvince() {
      var provinceObj = $('studentProvince');
      if(provinceObj.value == "") {
        Wit.showErr(provinceObj, "<s:property value="getText('please.select')" />");
        return false;
      } else {
    	Mat.showSucc(provinceObj);
        return true;
      }
    }
  	  
    /** 所属市必选 **/
    function checkCity() {
      var cityObj = $('studentCity');
      var provinceObj = $('studentProvince');
      if(provinceObj.value == "710000" ||
         provinceObj.value == "810000" ||
         provinceObj.value == "820000") {
    	Mat.showSucc(cityObj);
        return true;
      } else {
        if(cityObj.value == "") {
          Wit.showErr(cityObj, "<s:property value="getText('please.select')" />");
          return false;
        } else {
          Mat.showSucc(cityObj);
          return true;
        }
      }
    }

    /** 所属县区必选 **/
    function checkDistrict() {
      var districtObj = $('studentDistrict');
      var provinceObj = $('studentProvince');
      if(provinceObj.value == "710000" ||
         provinceObj.value == "810000" ||
         provinceObj.value == "820000") {
        Mat.showSucc(districtObj);
        return true;
      } else {
        if(districtObj.value == "") {
          Wit.showErr(districtObj, "<s:property value="getText('please.select')" />");
          return false;
        } else {
          Mat.showSucc(districtObj);
          return true;
        }
      }
    }

	  
	function fileFormate() {
		//Wit.goBack("<s:url value='/picturefileupload/fileUploadInit.shtml' />");
		art.dialog.open("<s:url value='/picturefileupload/fileUploadInit.shtml' />",{
			title:"填加照片",
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
	
	/** 获取学生卡卡号 **/
	function getCardId() {
	  try {
		var obj = document.getElementById('GenerateCardId');
		var connValue = obj.OpenComm();
		if(0 == connValue) {
			var cardId = obj.QueryCard();
			if(0 == cardId) {
		    	alert("未找到卡");
			} else {
				document.getElementById('student_card').value = cardId;
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
	function checkStudentName() {
		var obj = $('student_name');
		if (!Mat.checkRequired(obj))
			return false;
		if (!Mat.checkLength(obj, 16, '<s:text name="common.js.info.lenles"/>' + '16'))
			return false;
		return true;
	}

	/** 学号必填项 **/
	function checkStudentCode() {
		var obj = $('student_code');
		if(Wit.checkErr(obj, /^[0-9-a-z-A-Z]+$/)) {
			Mat.showSucc(obj);
			return true;
		} else {
			Wit.showErr(obj,"学号只能为数字、字母或其组合");
			return false;
		}
	}

	/** 卡号必填项 **/
	function checkStudentCard() {
		var obj = $('student_card');

		if(Wit.checkErr(obj, /^[0-9-a-z-A-Z]+$/)) {
			Mat.showSucc(obj);
			return true;
		} else {
			Wit.showErr(obj,"学生卡号只能为数字、字母或其组合");
			return false;
		}
	}

	/** 班主任姓名必填 **/
	function checkTeacherName() {
		var obj = $('teacher_name');
		if (!Mat.checkRequired(obj))
			return false;
		return true;
	}

	function checkTeacherTel() {
		var obj = $('teacher_tel');
		if (!Mat.checkTELandCell(obj, '电话格式不正确！')) {
			return false;
		} else {
			Mat.showSucc(obj);
			return true;
		}
	}
	
	/**住址超长判断**/
	function checkStudentAddress(){
		var obj = $('student_address');
		if (!Mat.checkLength(obj, 40, '<s:text name="common.js.info.lenles"/>' + '40'))
			return false;
		return true;
	}

	function checkRelativeName() {
		var obj = $('relative_name');
		if (!Mat.checkRequired(obj))
			return false;
		return true;
	}

	function checkRelativeTel() {
		var obj = $('relative_tel');
		if (!Mat.checkTELandCell(obj, '电话格式不正确！')) {
			return false;
		} else {
			Mat.showSucc(obj);
			return true;
		}
	}

	function checkSchool() {
		var obj = $('student_school');
		if (!Mat.checkRequired(obj))
			return false;
		return true;
	}

	function checkClass() {
		var obj = $('student_class');
		if (!Mat.checkRequired(obj))
			return false;
		return true;
	}

	function checkStudentCardUnique(){
	    var obj = $('student_card');
		    
		DWREngine.setAsync(false);
		var ret = false; 
		PasswordConfirmDWR.checkStudentCode(obj.value, callBackFun);
		    
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
	
	/** 初始化样式 **/
	function setFormMessage() {
		Mat.setDefault($('student_name'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('student_card'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('student_code'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('teacher_name'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('teacher_tel'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('relative_name'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('relative_tel'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('student_school'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('student_class'),'<span class="noticeMsg">*</span>');

		Mat.setDefault($('studentProvince'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('studentCity'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('studentDistrict'),'<span class="noticeMsg">*</span>');
	}

	/** 加载事件 **/
	function setFormEvent() {
		$('student_name').onkeyup = checkStudentName;
		$('student_name').onblur = checkStudentName;
		$('student_card').onkeyup = checkStudentCard;
		$('student_card').onblur=checkStudentCard;
		$('student_code').onkeyup = checkStudentCode;
		$('student_code').onblur=checkStudentCode;
		$('student_address').onkeyup = checkStudentAddress;
		$('student_address').onblur=checkStudentAddress;
		$('teacher_name').onkeyup = checkTeacherName;
		$('teacher_name').onblur=checkTeacherName;
		$('teacher_tel').onkeyup = checkTeacherTel;
		$('teacher_tel').onblur=checkTeacherTel;
		$('relative_name').onkeyup = checkRelativeName;
		$('relative_name').onblur=checkRelativeName;
		$('relative_tel').onkeyup = checkRelativeTel;
		$('relative_tel').onblur=checkRelativeTel;
		$('student_school').onkeyup = checkSchool;
		$('student_school').onblur=checkSchool;
		$('student_class').onkeyup = checkClass;
		$('student_class').onblur=checkClass;
	}

	function submitForm() {
		trimAllObjs();
		var f0 = checkStudentName();
		var f1 = checkStudentCode();
		var f2 = checkStudentCard();
		var f3 = checkTeacherName();
		var f4 = checkTeacherTel();
		var f5 = checkStudentAddress();
		var f6 = checkRelativeName();
		var f7 = checkRelativeTel();
        var f8 = checkStudentCardUnique();
        var f9 = checkSchool();
        var f10 = checkClass();
		var f11 = checkProvince();
		var f12 = checkCity();
		var f13 = checkDistrict();
		if (f0 && f1 && f2 && f3 && f5 && f6 && f7 && f8 && f9 && f10 && f11 && f12 && f13) {
			Wit.commitAll($('add_studentform'));
		} else {
			return false;
		}
	}

	function goBack(url) {
		Wit.goBack(url);
	}

	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);

	function loadFileContent() {
		document.getElementById("photoPreview").style.display="none";
	}

	//页面自适应
	(function (jQuery) {
	 jQuery(window).resize(function(){
	 
	  testWidthHeight();
	  
	 });
	 jQuery(window).load(function (){
	  
	  testWidthHeight();
	  
	 });
	 
	 changeProvinceCity();// 联动省市信息
	})(jQuery);
	
	//获取页面高度
	function get_page_width() {
	  var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
	  return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
	}
	
	//获取页面高度
	function get_page_height() {
	  var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
	  return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
	}
	 
	//计算控件宽高
	function testWidthHeight(){
		var h = get_page_height();
		//var test=document.getElementById("studentTable");
		if(h>200){
			jQuery("#studentDiv").height(h-162);
		}

		jQuery(window).mk_autoresize({
		       height_include: '#content',
		       height_exclude: ['#header', '#footer'],
		       height_offset: 0,
		       width_include: ['#header', '#content', '#footer'],
		       width_offset: 0
		    });

		h = get_page_height();
		if(h>200){
			jQuery("#studentDiv").height(h-162);
		}
	}
</script>