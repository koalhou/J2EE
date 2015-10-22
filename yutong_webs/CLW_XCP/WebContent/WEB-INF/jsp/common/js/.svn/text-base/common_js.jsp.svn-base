<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery.wresize.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.parser.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.autoresize.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.linkbutton.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.menubar.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.menu.js" />"></script>
<script type="text/javascript" src="<s:url value="/mooko-ui/core/mk.sidelayer.js" />"></script>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/BlockUI/jquery.blockUI.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/menufocus/menu_focus.js' />"></script>
<script type="text/javascript">
jQuery(document).ready(function(){
	 jQuery("#username10").hover(function(){
	    	jQuery("#backusername10").show();								  
		},function(){
			jQuery("#backusername10").hide();	
		})
})

function calcColumn(persent, minwidth, offset) {
    var pagewidth = 0;
    
    var width = 0;
    if(jQuery.browser.msie){ 
       pagewidth = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
    } else { 
       pagewidth = self.innerWidth; 
    } 
    if(offset > 0) {
       width = pagewidth - offset;
    } else {
       width = pagewidth;
    }
    return width*persent < minwidth ? minwidth : width*persent;
}

function resizeBgDiv() {
	var minHeight = jQuery.data(window, 'mk-autoresize').options.min_height;
	var minWidth = jQuery.data(window, 'mk-autoresize').options.min_width;
	var width = (parseInt(jQuery(window).width())<= minWidth) ? minWidth : jQuery(window).width();
	var height = (parseInt(jQuery(window).height())<= minHeight) ? minHeight : jQuery(window).height();
	jQuery("#BgDiv").css("width", width);
	jQuery("#BgDiv").css("height", height);
}
var imgRate = 1;
var isRate = null
jQuery(function() {
	jQuery('#mi1, #mi2, #mi3, #mi4, #mi5, #mi6, #mi7,#mi8,#mi9').mk_menubar();
	jQuery(window).mk_autoresize({
       height_include: '#content',
       height_exclude: ['#header', '#footer'],
       height_offset: 0,
       min_height: 700,
       min_width: 1256,
       width_include: ['#header', '#content', '#footer'],
       width_offset: 0
    });

	resizeBgDiv(1256, 700);
	jQuery(window).wresize(function(){
		resizeBgDiv();
	});
	isRate = setTimeout("rotationImg()",5000);
	imgEvent();
});

(function($){
	// 预缓存皮肤，数组第一个为默认皮肤
	$.dialog.defaults.skin = ['aero'];
})(art);
function aboutbrwose(){
	art.dialog.open("<s:url value='/about/about.shtml' />",{
		title:"关于 安芯校车管理系统",
		lock:true,
		width:486,
		height:290
	});
}

function studentPasswordConfirm() {	
	art.dialog.open("<s:url value='/student/passwordConfirm.shtml' />?moduleName=student_module",{
		title:"输入密码",
		lock:true,
		width:400,
		height:170
	});
}

function messagePasswordConfirm() {	
	art.dialog.open("<s:url value='/student/passwordConfirm.shtml' />?moduleName=message_module",{
		title:"输入密码",
		lock:true,
		width:400,
		height:170
	});
}

function alarmmenu(){
	window.location="<s:url value='/busalarm/newmorealarm.shtml'/>?alarm_type_id='40','72'";
}

function routechartmenu(){
	window.location="<s:url value='/routechart/ready.shtml' />";
}

function gpsmenu(){
	window.location="<s:url value='/gps/gpsAction.shtml' />";
}

function openxintexing(){
	
	window.open("../ytstatic/index.html");
}
function rotationImg(){
	if(jQuery("div[class=downLink]").css("display")=="none"){
		jQuery("div[class=downLink]").css("display","block");
		jQuery("div[class=downLink1]").css("display","none");
	} else {
		jQuery("div[class=downLink]").css("display","none");
		jQuery("div[class=downLink1]").css("display","block");
	}
	if(imgRate){
		isRate = null;
		isRate = setTimeout("rotationImg()",5000);
	}
}
function imgEvent(){
	jQuery("div[class=downLink]").mouseover(function(){
		imgRate = 0;
		clearTimeout(isRate);
		isRate = null;
	}).mouseout(function(){
		imgRate = 1;
		isRate = setTimeout("rotationImg()",5000);
	});
	jQuery("div[class=downLink1]").mouseover(function(){
		imgRate = 0;
		clearTimeout(isRate);
		isRate = null;
	}).mouseout(function(){
		imgRate = 1;
		setTimeout("rotationImg()",5000);
	});
}
</script>
