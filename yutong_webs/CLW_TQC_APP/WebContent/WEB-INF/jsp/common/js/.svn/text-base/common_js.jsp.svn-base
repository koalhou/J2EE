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
//方法来自menu_focus.js
var data = JSON.parse(jQuery("#navphotos_value").html());
function update_current_nav_css() {
	var href = location.href;
	if (urls && urls.length > 0) {
		jQuery.each(urls, function(idx) {
			if (this.url_reg.test(href)) {
				if(this.id!==null&&this.id.length<=4) {
					jQuery(this.id).attr("style","background: url(showPhoto.shtml?id=<s:property value='#session.themeProfile.userId' />&type=nav"+this.id.substring(this.id.length-1,this.id.length)+"focus) no-repeat center top;");
				}
			}
		});
	}
	
	
}

jQuery(document).ready(function(){
	jQuery.each(jQuery(".navigation ul li"),function(i){
		var midid = jQuery(this).children("a").attr("id");
		//jQuery("#"+midid).attr("style",data[midid]);
		jQuery("#"+midid).attr("style","background: url(showPhoto.shtml?id=<s:property value='#session.themeProfile.userId' />&type="+midid+") no-repeat center top;");
	});
	//update_current_nav_css();
	jQuery("#username10").hover(function(){
	    	jQuery("#backusername10").show();								  
		},function(){
			jQuery("#backusername10").hide();	
		})
});

jQuery(function() {    
  update_current_nav_css();
});

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

jQuery(function() {
	jQuery('#mi1, #mi2, #mi3, #mi4, #mi5, #mi6, #mi7,#mi11, #mi12, #mi13, #mi14, #mi15').mk_menubar();
	jQuery(window).mk_autoresize({
       height_include: '#content',
       height_exclude: ['#header', '#footer'],
       height_offset: 0,
       min_height: 600,
       min_width: 800,
       width_include: ['#header', '#content', '#footer'],
       width_offset: 0
    });

	resizeBgDiv(800, 600);
	jQuery(window).wresize(function(){
		resizeBgDiv();
	});
});

(function($){
	// 预缓存皮肤，数组第一个为默认皮肤
	$.dialog.defaults.skin = ['aero'];
})(art);
//yutong 
function aboutbrwose(){
	art.dialog.open("<s:url value='/about/about.shtml' />",{
		title:"关于通勤车管理系统",
		lock:true,
		width:486,
		height:290
	});
}
//taian
function aboutbrwoseTaian(){
	art.dialog.open("<s:url value='/about/aboutTa.shtml' />",{
		title:"关于泰安通勤车管理系统",
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

function routechartmenu(){
	window.location="<s:url value='/routechart/ready.shtml' />";
}

function gpsmenu(){
	window.location="<s:url value='/gps/gpsAction.shtml' />";
}
//公告管理 2013-11-07
function noticemenu(){
	window.location="<s:url value='/notice/noticeManage.shtml' />";
}

function menuurlto(url){
	if(typeof closebackdatainfo == "function") {
		var dd = closebackdatainfo();
		if(dd!=null&&dd)
			window.location=url;
	} else {
		window.location=url;
	}
}

function openxintexing(){
	window.open("../ytstatic/index.html");
}
</script>
