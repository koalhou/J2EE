<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript'
	src='../dwr/interface/VehicleManageDWR.js'></script>
<script type="text/javascript">

function clickEnterEvent(obj) {
    var orgid=document.getElementById('organization_id');
    orgid.value=obj.id;
}

function openorganizidtree(){
art.dialog.open("<s:url value='/handmobile/handmobiletreeAction.shtml' />?rad="+Math.random(),
	{width :260,
	height:300,
	id: 'treeOID',
	title: ' ',
	skin: 'aero',
	limit: true,
	lock: true,
	drag: true,
	fixed: false,
		//follow: document.getElementById('organizationName'),
		yesFn: function(iframeWin, topWin){
        	//对话框iframeWin中对象
        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
        	//当前页面中对象
            var topOrgName =  window.document.getElementById('organizationName');
            var topOrgID = window.document.getElementById('organization_id');
            //赋值
        	if (topOrgName) topOrgName.value = orgNameValue;
        	if (topOrgID) topOrgID.value = orgIDValue;
    	}
	});
}


	function checkUser_name() {
		var user_name = $('user_name');
		if (!Mat
				.checkLength(
						user_name,
						10,
					    '使用者姓名'+'<s:text name="val.feild.overlength"/>' + '10'))
			return false;
		return true;
	}

	/**验证手机号码**/
	 function checkUser_Contact(){
			var phone=$('user_contact');
			if(phone.value!=''){
				if(!Mat.checkTelePhone(phone)){
					Wit.showErr(phone, "<s:property value="getText('enterprise.js.info.telSucc')"/>");
				    return false;
				}
			}
			return true;
		}

	/** 取消填加操作 **/
	function goBack(url) {
	    Wit.goBack(url);
	}
	
	
	
	function setFormMessage() {
		
	}
	function checkOrganizationName() {
		var map = $('organizationName');
		if(!Mat.checkRequired(map)) return false;
		return true;
	}

	function alterForm() {
		    trimAllObjs();
		    
		    var f0=checkOrganizationName();
		    var f1=checkUser_name();
		    var f2=checkUser_Contact();
		    if(f0&&f1&&f2)
			{
		    	
		    		 Wit.commitAll($('alter_form')); 
			}else{
				return false;
			}		
	}

	
	
	function setFormEvent() {
		//$('hdmf').onkeyup = checkHdmf;
		//$('hdmf').onblur = checkHdmf;
		//$('hdnm').onkeyup = checkHdnm;
		//$('hdnm').onblur = checkHdnm;
		<s:if test="#session.perUrlList.contains('111_3_3_4_4') && 3!=#session.adminProfile.userType ">
		$('alterbutton').onclick = alterForm;	
		</s:if>
	}

	//页面自适应
	(function (jQuery) {
	 jQuery(window).resize(function(){
	 
	  testWidthHeight();
	  
	 });
	 jQuery(window).load(function (){
	  
	  testWidthHeight();
	  jQuery(window).resize();
	  
	 });
	 
	})(jQuery);
	
	//获取页面高度
	function get_page_height() {
	 var height = 0;
	 
	 if (jQuery.browser.msie) {
	     height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
	 } else {
	     height = self.innerHeight;
	 }
	 return height;
	}
	//获取页面宽度
	function get_page_width() {
	 var width = 0;
	 if(jQuery.browser.msie){ 
	  width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
	 } else { 
	  width = self.innerWidth; 
	 } 
	 return width;
	}
	 
	//计算控件宽高
	function testWidthHeight(){
	 
		var h = get_page_height();
		var test=document.getElementById("handmobiletable");
		if(h>165){
			test.style.height = h-165;
		}else{
		}
	}
	
	//window.addEvent('domready', setFormMessage);
	//window.addEvent('domready', setFormEvent);
		
//-->
</script>

