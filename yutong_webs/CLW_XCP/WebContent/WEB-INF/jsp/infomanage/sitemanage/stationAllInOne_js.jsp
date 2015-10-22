<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
//页面自适应
jQuery(function() {
	if (jQuery.browser.msie && jQuery.browser.version < 7.0) {
		document.getElementById("mapbartab").style.width='600px';
	}else{
		document.getElementById("mapbartab").style.width='590px';
		jQuery("#start_time").attr("size",'24');
		jQuery("#end_time").attr("size",'24');
	}
	mapInit();
	loadUpTabDate();
	fixPNG(document.getElementById("up_img"));
	fixPNG(document.getElementById("left_img"));
	fixPNG(document.getElementById("right_img"));
	fixPNG(document.getElementById("down_img"));
	
	jQuery(window).mk_autoresize({
		min_width: 1256
	});
	auto_size();
	jQuery(document).wresize(function() {
		resizeBgDiv(1256,750);
	});
});

function auto_size(){
	//计算中区高度
	jQuery('#content').mk_autoresize( {
		height_include : [ '#content_rightside', '#content_leftside' ],
		height_offset : 0,
		width_exclude: ['#content_leftside'],
		width_include : [ '#content_rightside'],
		width_offset : 1
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize( {
		height_exclude : '#leftInfoDiv',
		height_include : '#lefttree',
		height_offset : 0

	});

	//计算树区域高度
	jQuery('#lefttree').mk_autoresize( {
		 height_include: '.bDiv',
		 height_offset: 140 // 该值各页面根据自己的页面布局调整
	});

	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize( {
		height_include : '#iCenter',
		height_offset : 0,
		width_include : '#iCenter',
		width_offset : 0
	});	
}

//修复 IE 下 PNG 图片不能透明显示的问题
function fixPNG(myImage) {
	var arVersion = navigator.appVersion.split("MSIE");
	var version = parseFloat(arVersion[1]);
	if ((version >= 5.5) && (version < 7) && (document.body.filters)){
	     var imgID = (myImage.id) ? "id='" + myImage.id + "' " : "";
	     var imgClass = (myImage.className) ? "class='" + myImage.className + "' " : "";
	     var imgTitle = (myImage.title) ? "title='" + myImage.title   + "' " : "title='" + myImage.alt + "' ";
	     var imgStyle = "display:inline-block;" + myImage.style.cssText;
	     var strNewHTML = "<span " + imgID + imgClass + imgTitle
	   + " style=\"" + "width:" + myImage.width
	   + "px; height:" + myImage.height
	   + "px;" + imgStyle + ";"
	   + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
	   + "(src=\'" + myImage.src + "\', sizingMethod='scale');\"></span>";
	     myImage.outerHTML = strNewHTML;
	}
}

//计算控件宽高
function testWidthHeight(){
	var Sys = {}; 
    var ua = navigator.userAgent.toLowerCase(); 
    
    var s; 
    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 
    (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : 
    (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : 
    (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : 
    (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0; 
    
	var h = get_page_height();
	var w = get_page_width();

	//var leftInfoDivh = document.getElementById("leftInfoDiv").offsetHeight;;
	var iCenterh = document.getElementById("iCenter").offsetHeight;;
	var listSearch=document.getElementById("listSearch");
	var tmp = 0;
	var infoH = 0;
	var listH = 0;
	
	if(Sys.ie) {
		if(Sys.ie.match(/[7-8]{1}(\.[\w]*)/)) {
			infoH = 40;
			tmp = 163;
			listH = 82;
		} else if(Sys.ie.match(/[6]{1}(\.[\w]*)/)) {
			infoH = 40;
			tmp = 158;
			listH = 82;
		}
	 } else {
		 infoH = 40;
		 tmp = 131;
		 listH = 82;
	 } 

	if(Sys.ie) {
		if(h>(tmp + listH)){
			document.getElementById("iCenter").style.height=h - tmp+35 + "px";
			document.getElementById("iCenter").style.width=w-318;
		} else {
			document.getElementById("iCenter").style.width=w-318;
			document.getElementById("iCenter").style.height=h+ "px";
		}
	} else {
		if(h>(tmp + listH)){
			document.getElementById("iCenter").style.height =h - tmp+25 + "px";
			document.getElementById("iCenter").style.width=w-288;
		} else {
			document.getElementById("iCenter").style.height = h + "px";
			document.getElementById("iCenter").style.width=w-288;
		}
	}
}


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

function accAdd(arg1, arg2) {
    var r1, r2, m, c;
    try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }
    try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }
    c = Math.abs(r1 - r2);
    m = Math.pow(10, Math.max(r1, r2))
    if (c > 0) {
        var cm = Math.pow(10, c);
        if (r1 > r2) {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", "")) * cm;
        }
        else {
            arg1 = Number(arg1.toString().replace(".", "")) * cm;
            arg2 = Number(arg2.toString().replace(".", ""));
        }
    }
    else {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", ""));
    }
    return (arg1 + arg2) / m
}

function accSub(arg1,arg2){
	var r1,r2,m,n;
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	m=Math.pow(10,Math.max(r1,r2));
	//动态控制精度长度
	n=(r1>=r2)?r1:r2;
	return ((arg1*m-arg2*m)/m).toFixed(n);
}

//地图上方工具条缩放
function maptoolbarIsshow(option){
	var map = document.getElementById("maptoolbar");
	var down = document.getElementById("fudong_down");

	if(option=="up"){
		map.style.display = "none";
		down.style.display = "";
	}
	else if(option=="down"){
		map.style.display = "";
		down.style.display = "none";
	}
}

function openorganizidtree(){
	art.dialog.open("<s:url value='/route/routemanagetree.shtml' />?rad="+Math.random(),{
		title:"选择部门",
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
        	//对话框iframeWin中对象
        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
        	//当前页面中对象
            jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#organizationName").val(orgNameValue);
			jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(3).children("td").eq(1).children("#orgidtag").val(orgIDValue);
    	}
	  
	});
}
</script>