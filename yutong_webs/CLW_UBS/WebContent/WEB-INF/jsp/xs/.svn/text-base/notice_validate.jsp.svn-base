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
	
	/** 初始化样式 **/
	function setFormMessage() {

	}

	/** 加载事件 **/
	function setFormEvent() {
	}

	function checkNotice_theme() {
		var notice_theme = $('notice_theme');
		if (!Mat.checkRequired(notice_theme))
			return false;
		if (!Mat
				.checkLength(
						notice_theme,
						100,
						'公告主题' + '<s:text name="val.feild.overlength"/>' + '100'))
			return false;
		return true;
	}

	/**点击查询按钮**/
	function searchList() {
		document.getElementById('Below_new').style.display='none';
	    jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'notice_theme', value: jQuery('#notice_theme').val() }, 
		 		{ name: 'notice_type', value: jQuery('#notice_type').val()},
		 		{ name: 'publish_time', value: jQuery('#publish_time').val()}]
		});
		jQuery('#gala').flexReload();
	}

	function submitForm() {
		var f1=  checkNotice_theme();
		if (f1) {

			Wit.commitAll($('addnoticeform'));
		} else {
			return false;
		}
	}
	function summitPurse(){
		var f1=  checkNotice_theme();
		if (f1) {
			var form = document.getElementById('addnoticeform');
			form.action="insertEnterpriseNoticeUn.shtml";
			Wit.commitAll(form);
		} else {
			return false;
		}
		
	}

	function deleteAccessory(){
		var form = document.getElementById('addnoticeform');
		form.action="cancelEnterpriseNotice.shtml";
		Wit.commitAll(form);
	}

	function deleteEditAccessory(){
		var form = document.getElementById('editnoticeform');
		form.action="cancelEditEnterpriseNotice.shtml";
		Wit.commitAll(form);
	}
	
	function submitEditForm() {
		var f1=  checkNotice_theme();
		if (f1) {
			Wit.commitAll($('editnoticeform'));
		} else {
			return false;
		}
	}
	function editTempNotice(){
		var f1=  checkNotice_theme();
		if (f1) {
			var form = document.getElementById('editnoticeform');
			form.action="saveEditEnterpriseNoticeUn.shtml";
			Wit.commitAll(form);
		} else {
			return false;
		}
		
	}

	function editDeleteNotice(){
		var form = document.getElementById('editnoticeform');
		form.action="deleteEditEnterpriseNotice.shtml";
		Wit.commitAll(form);
	}
	
	function editCancelAccessory(){
		var form = document.getElementById('editnoticeform');
		form.action="cancelEditEnterpriseNotice.shtml";
		Wit.commitAll(form);
	}

	function dropViewEnterpriseNotice(){
		Wit.commitAll($('viewnoticeform'));
		
	}

	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);
</script>