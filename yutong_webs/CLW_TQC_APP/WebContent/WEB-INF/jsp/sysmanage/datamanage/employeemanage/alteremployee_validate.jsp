<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/employee.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type="text/javascript">


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
	
	function checkStudentName() {
		var obj = $('student_name');
		if (!Mat.checkRequired(obj)){
			return false;
		}
		if (!Mat.checkLength(obj, 16, '<s:text name="common.js.info.lenles"/>' + '16')){
			return false;
		}
		var parten = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
		  	if(parten.exec(obj.value)){
			
		  	} else {
				Wit.showErr(obj, "只能输入汉字、字母、数字或其组合！");
				return false;
		  	}
		return true;
	}
	/** 学号必填项 **/
	function checkStudentCode() {
		var obj = $('student_code');
		if (!Mat.checkRequired(obj))
			return false;
		return true;
	}

	/** 卡号必填项 **/
	function checkStudentCard() {
		var obj = $('student_card');
		if (!Mat.checkRequired(obj))
			return false;
		if(Wit.checkErr(obj, /^[0-9-a-z-A-Z]+$/)) {
			Mat.showSucc(obj);
			return true;
		} else {
			Wit.showErr(obj,"卡号为数字与字母组合，长度应不超过14位");
			return false;
		}
	}
	function checkStudentCodeUnique(){
	    var obj = $('student_code');
	    var enid=$('organizationID');   
		DWREngine.setAsync(false);
		var ret = false;
		var obj1 = $('oldStudentCode');
		
		//var oldStudentCard = $('oldStudentCard');
		//var newStudentCard = $('student_card');
		//var oldStudentCode= $('oldStudentCode');
		//var newStudentCode = $('student_code');
		var oganid = $('organizationID');
		var oganid1 = $('og_id');
		if(oganid.value != oganid1.value){
			employee.checkStudentCode(obj.value, oganid.value,callBackFun);
		}
		else{
			if(obj.value == obj1.value){
				employee.checkStudentCode1(obj.value, oganid.value,callBackFun);
			}
			else{
				employee.checkStudentCode(obj.value, oganid.value,callBackFun);
			}
		}
		    
		function callBackFun(data)
		{
		  ret = data;
		}    
		DWREngine.setAsync(true);
		if(ret) {
		  Wit.showErr(obj, "员工号已存在！");
		  return false;
		} else {
		  return true;
		}
	  }
	function checkStudentCardUnique(){
	    var obj = $('student_card');
	    var enid=$('enid');      
		DWREngine.setAsync(false);
		var ret = false;
		var obj1 = $('oldStudentCard');
			//var oldStudentCard = $('oldStudentCard');
		//var newStudentCard = $('student_card');
		//var oldStudentCode= $('oldStudentCode');
		//var newStudentCode = $('student_code');
		
		
		if(obj.value == obj1.value){
			employee.checkStudentCard1(obj.value, enid.value,callBackFun);
		}
		else{
			employee.checkStudentCard(obj.value, enid.value,callBackFun);
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
	/** 组织必填项 **/
	function checkOrganization() {
		var obj = $('organizationName');
		if (!Mat.checkRequired(obj)){
			return false;
		}
		else {
			Mat.showSucc(obj);
			return true;
		}
	}
	/** 初始化样式 **/
	function setFormMessage() {
		Mat.setDefault($('student_name'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('student_card'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('student_code'),'<span class="noticeMsg">*</span>');
		Mat.setDefault($('organizationName'),'<span class="noticeMsg">*</span>');
		//Mat.setDefault($('student_school'),'<span class="noticeMsg">*</span>');
	}

	/** 加载事件 **/
	function setFormEvent() {
		$('student_name').onkeyup = checkStudentName;
		$('student_name').onblur = checkStudentName;
		//$('student_card').onkeyup = checkStudentCard;//添加采集功能之后，点开鼠标不要提示
		//$('student_card').onblur=checkStudentCard;
		$('student_code').onkeyup = checkStudentCode;
		$('student_code').onblur=checkStudentCode;
		$('organizationName').onkeyup = checkStudentCode;
		$('organizationName').onblur=checkStudentCode;
		$('remarks1').onkeyup = checkRemarks;
		$('remarks1').onblur=checkRemarks;
		//$('student_school').onkeyup = checkSchool;
		//$('student_school').onblur=checkSchool;
	
	}
	
	var maxLength = 40; 
	function checkRemarks(){
		var obj = $('remarks1');
		var otextleft = $('textleft');
		//var otextleft = document.getElementById("textleft"); 
		//alert(otextleft.innerHTML);
		var str = obj.value;     
		otextleft.innerHTML = maxLength - str.length;
		if(otextleft.innerHTML<0){
			Wit.showErr(obj, "已超出40个字！");
			return false;
		}
		else {
			Mat.showSucc(obj);
			return true;
		}
	}
	function submitAlterForm() {
		trimAllObjs();
		var f0 = checkStudentName();
		var f1 = checkStudentCode();
		var f2 = checkStudentCard();
		var f3 = checkOrganization();
		var f7 = checkStudentCodeUnique();
		var f8 = checkStudentCardUnique()
		var f9 = checkRemarks();
		//var f8 = checkStudentCardUnique();
		//var f3 = checkTeacherName();
		//var f4 = checkTeacherTel();
		//var f5 = checkStudentAddress();
		//var f6 = checkRelativeName();
		//var f7 = checkRelativeTel();
		//var f8 = checkSchool();
        //var f9 = checkClass();

        //var f11 = checkProvince();
		//var f12 = checkCity();
		//var f13 = checkDistrict();
		if (f0 && f1 && f2 && f3 &&f7 &&f8 && f9 ) {
		//if (f0 && f1 && f2 && f3 && f5 && f6 && f7 && f8 && f9 && f11 && f12 && f13) {
			
			var obj = $('remarks1');
			$('remarks').value=obj.value;
			Wit.commitAll($('alter_studentform'));
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
			        	checkOrganization();
			    	}
				});
	}
	function goBack(url) {
		Wit.goBack(url);
	}
	
	//var maxLength = 40; 
	function check(){
		var obj = $('remarks');
		jQuery("#remarks1").html(obj.value);
	}

	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);
	window.addEvent('domready', check);
	window.addEvent('domready', checkRemarks);
	

	
	//页面自适应
	(function (jQuery) {
	 jQuery(window).resize(function(){
	 
	  testWidthHeight();
	  
	 });
	 jQuery(window).load(function (){
	  
	  testWidthHeight();
	  
	 });
	 
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
		//var test=document.getElementById("studentTable");
		if(h>200){
			jQuery("#studentDiv").height(h-162);
		}
	}
</script>