<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/BaseInfoManageDwr.js'></script>
<script type="text/javascript">
	/** 品牌类型变化 **/
	function onBrandChange() {
	  var obj = $('parentId');
	  if(obj !=null && obj.value != "") {
	    Mat.showSucc(obj);
	  }
	}
	
	/** 消息类型必选 **/
    function checkBrand() {
	  var obj = $('parentId');
	    
	  if(obj!=null && obj.value == "") {
	    Wit.showErr(obj, "<s:property value="getText('please.select')" />");
	    return false;
	  } else {
	    return true;
	  }
	}
	  
	/** 名称必填项 **/
	function checkCodeName() {
	  var elm = $('codeName');
	  if(Mat.checkRequired(elm)){
	    Mat.showSucc(elm);
	    return true;
	  }else{
	    Wit.showErr(elm, "<s:property value="getText('msg.check.required')" />");
	    return false;
	  }
	}

	/** 消息编码必填 **/
	function checkCodeId() {
	  var codeIdObj = $('codeId');
	  if(Mat.checkRequired(codeIdObj)) {
	   	Mat.showSucc(codeIdObj);
	    return true;
	  } else {
	    Wit.showErr(codeIdObj, "<s:property value="getText('msg.check.required')" />");
        return false;
      }
	} 
	  
    /** 检查CodeId唯一性 **/
    function checkCodeIdUnique(){
	  var codeObj = $('codeId');
	        
	  DWREngine.setAsync(false);
	  var ret = false; 
	  BaseInfoManageDwr.checkCodeIdUnique(codeObj.value, 'ENGINETYPE', callBackFun);
		    
	  function callBackFun(data)
	  {
		  ret = data;
	  }
		    
	  DWREngine.setAsync(true);
		   
	  if(ret) {
	    Wit.showErr(codeObj, "<s:property value="getText('codeid.exist')" />");
	    return false;
	  } else {
		return true;
	  }
	}
	  
	/** 初始化样式 **/
	function setFormMessage() {
     Mat.setDefault($('parentId'),'<span class="noticeMsg">*</span>');
     Mat.setDefault($('codeId'),'<span class="noticeMsg">*</span>');
     Mat.setDefault($('codeName'),'<span class="noticeMsg">*</span>');
	}

	/** 加载事件 **/
	function setFormEvent() {
		$('codeId').onkeyup = checkCodeId;
	    $('codeId').onblur = checkCodeId;
		$('codeName').onkeyup = checkCodeName;
	    $('codeName').onblur = checkCodeName;
	}
	
	function createEnterprise(){
		trimAllObjs();
		var f1 = checkCodeName();
	    var f2 = checkCodeId();
	    var f3 = checkCodeIdUnique();
	    var f4 = checkBrand();
		if(f1 && f2 && f3 && f4) {
			jQuery.ajax({
				type: 'POST', 
				url: "<s:url value='/popup/createEngineType.shtml' />",	
				data: {
					parentId:$('parentId').value,
					codeId:$('codeId').value,
					codeName:$('codeName').value
				},
				success: function(data) {
					if(data=="success"){
						var obj = $('successLbl');
						obj.innerHTML = "发动机型号创建成功！";
						parent.window.submit();
						art.dialog.close();
					}
					if(data == "error") {
						var obj = $('errorLbl');
						obj.innerHTML = "发动机型号创建失败！";
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