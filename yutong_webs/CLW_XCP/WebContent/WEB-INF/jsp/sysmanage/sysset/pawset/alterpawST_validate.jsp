<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">

	
	function oldPass() {
		var oldPass = $('oldPass');
		if (!Mat.checkRequired(oldPass))
			return false;
		if (!Mat
				.checkLength(
						oldPass,
						64,
						'<s:text name="password.old_pass"/>' + '<s:text name="val.feild.overlength"/>' + '64'))
			return false;
		return true;
	}

	function newPass1() {
		var newPass1 = $('newPass1');
		if (!Mat.checkRequired(newPass1))
			return false;
		if (!Mat
				.checkLength(
						newPass1,
						64,
						'<s:text name="password.new_pass1"/>' + '<s:text name="val.feild.overlength"/>' + '64'))
			return false;
			
		  var _newPass1 = newPass1.value.match(/(\s)+/g);	
		  if(_newPass1==null){
		  }else{
		       Wit.showErr(newPass1, '<s:text name="password.contain.blank"/>',getErrStyle(newPass1));
               return false;  
		  }
          return true;		
	}
	
	function newPass2() {
		var newPass2 = $('newPass2');
		if (!Mat.checkRequired(newPass2))
			return false;
		if (!Mat
				.checkLength(
						newPass2,
						64,
						'<s:text name="password.new_pass2"/>' + '<s:text name="val.feild.overlength"/>' + '64'))
			return false;
			
	     var _newPass2 = newPass2.value.match(/(\s)+/g);	
		  if(_newPass2==null){
		  }else{
		       Wit.showErr(newPass2, '<s:text name="password.contain.blank"/>',getErrStyle(newPass2));
               return false;  
		  }
		return true;
	}

	/** 初始化样式 **/
	function setFormMessage() {
     Mat.setDefault($('oldPass'),'<span class="noticeMsg">*</span>');
     Mat.setDefault($('newPass1'),'<span class="noticeMsg">*</span>');
     Mat.setDefault($('newPass2'),'<span class="noticeMsg">*</span>');
	}

	/** 加载事件 **/
	function setFormEvent() {

		
		$('oldPass').onkeyup = oldPass;
		$('oldPass').onblur=oldPass;

        $('newPass1').onkeyup = newPass1;
		$('newPass1').onblur=newPass1;

        $('newPass2').onkeyup = newPass2;
		$('newPass2').onblur=newPass2;

	}
	
	function submitalterForm(){
		
		var f2 = oldPass();
		var f1=  newPass1();
		var f3= newPass2();
		if (f1&f2&f3) {
			var form = document.getElementById('alter_pawform');
			//form.submit();
			 Wit.commitAll(form);
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