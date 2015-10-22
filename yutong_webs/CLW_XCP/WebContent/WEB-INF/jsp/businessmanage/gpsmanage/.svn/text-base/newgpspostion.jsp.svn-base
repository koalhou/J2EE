<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html >
<head>

<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterprise_css.jsp"%>								
<title><s:text name="common.title" /></title>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/gpspostion.css" />" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>						
<style>
.expandA{
	width:16px;
	height:50px;
	background:url(<s:url value="/newimages/week_arrow_left.png" />) no-repeat center;
	top:93px;
	right:500px;
	position:absolute;
	z-index:500;
}
</style>
<!-- 中文注释 -->
</head>

<body onload="mapInit('iCenter');" onunload="sethtmlclearInterval()">
<div id="wrapper">						
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>					
	<div id="content">					


<input type="hidden"  id="enterprise_id" name="enterprise_id" value="<s:property value='#session.adminProfile.entiID'/>" />
<input type="hidden"  id="organizationid" name="organizationid" value="<s:property value='#session.adminProfile.organizationID'/>" />
<input type="hidden"  id="Userorganizationid" name="Userorganizationid" value="<s:property value='#session.adminProfile.organizationID'/>" />
<input type="hidden" id="optionUserid" name="optionUserid" value="<s:property value='#session.adminProfile.userID'/>" />
<input type="hidden"  id="UserType" name="UserType" value="<s:property value='#session.adminProfile.userType'/>" />
<input type="hidden" id="vehicleLn" name="vehicleLn"  />
<div class="popBox">
<div id="expandD" style="display:none;">
	<a id="expandA" class="expandA" onclick="javascript:void(0);expand();"></a>
</div>
  <div id="popArea" class="mk-sidelayer mk-sidelayer-small" style="overflow:hidden;display: none;">
	  <div class="mk-sidelayer-toolbar">        
	        <span class="mk-sidelayer-bar-btn show"></span>
	        <h1 class="mk-sidelayer-bar-title"></h1>
	        <div class="mk-sidelayer-tools" style="overflow:hidden;">
	         <!--  <ul>
	              <li id="aa5"><a href="#" onclick="sidelayerButtonControl('aa5')"><img src="../newimages/qipaoimages/btn_tool1.png"/></a></li>
	              <li id="aa4"><a href="#" onclick="sidelayerButtonControl('aa4')"><img src="../newimages/qipaoimages/btn_tool2.png"/></a></li>
	              <li id="aa3"><a href="#" onclick="sidelayerButtonControl('aa3')"><img src="../newimages/qipaoimages/btn_tool3.png"/></a></li>
	              <li id="aa7"><a href="#" onclick="sidelayerButtonControl('aa7')"><img src="../newimages/qipaoimages/btn_tool7.png"/></a></li>
	              <li id="aa1"><a href="#" onclick="sidelayerButtonControl('aa1')"><img src="../newimages/qipaoimages/btn_tool5.png"/></a></li>
	              <li id="aa2"><a href="#" onclick="sidelayerButtonControl('aa25')"><img src="../newimages/qipaoimages/btn_tool6.png"/></a></li>
	              <li id="aa6"><a href="#" onclick="sidelayerButtonControl('aa6')"><img src="../newimages/qipaoimages/ico_warning.png"/></a></li>
	          </ul>-->
	        </div>
	  </div>
	  <div class="mk-sidelayer-content" >
<!--	  <iframe id="iframeshowArea" src="" height="484px" width="100%"></iframe>-->
	  </div>
  </div>
</div>
<!--批量消息开始-->
            <div class="float-opt" style="height:220px;display: none;" id="sendMsglist">
            	<h3>批量发送消息<a  class="close" onclick="closecallsendMsgList()"></a></h3>
                 <iframe id="sendMsgListIframe" name="sendMsgListIframe" src='' 
                 height="191px" width="100%" frameborder="0" scrolling="no"></iframe>
            </div> 
              <!--批量消息结束-->
              <!--批量快照开始-->
              <div class="float-opt" style="height:101px;display: none;" id="sendphotolist">
              <h3>批量拍照<a  class="close" onclick="closecallsendPhotoList()"></a></h3>
               	<iframe id="sendPhotoListIframe" name="sendPhotoListIframe" src='' 
                 height="81px" width="100%" frameborder="0" scrolling="no"></iframe>
              </div>
              <!--批量快照结束-->

 
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="top" class="leftline" id="leftdiv">
          <div id="content_leftside">
          <div class="treeTab">
            <a  class="tabfocus" onfocus="this.blur()" id="enttabid" onclick="tabSwitch('enttabid')">组织</a>
            <s:if test="3==#session.adminProfile.en_mould">
            <a  class="tab" onfocus="this.blur()"  id="treetabid" onclick="tabSwitch('treetabid')">线路</a>
            </s:if>
            <a  class="hideLeft" onfocus="this.blur()" id="hideleftbutton" onclick="HideandShowControl()"></a>
          </div>
          
	          <div class="newsearchPlan">
	            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	              <tr>
	                <td width="130px" align="right"><input maxlength="60" id="vehicleLntext" name="vehicleLntext" type="text" class="input120" onkeypress="if(event.keyCode==13){jQuery('#vehicleLn').val(jQuery('#vehicleLntext').val());mapObj.removeAllOverlays();searchTree();openTip_Close();}"/></td>
	                <td align="center"><a  class="newbtnbule" onfocus="this.blur()" onclick="jQuery('#vehicleLn').val(jQuery('#vehicleLntext').val());mapObj.removeAllOverlays();searchTree();openTip_Close();">查询</a></td>
	              </tr>
	            </table>
	          </div>
	          <div id="twobutton" class="refreshAndreset2">
		          <a  class="btnRefresh" onfocus="this.blur()" onclick="treebtnRefresh()">刷新</a>
		          <a  class="btnReset" onfocus="this.blur()" onclick="treebtnReset()">重置</a>
	          </div>
	          <div id="treeDemoDiv" class="treeBox" >
	      		<ul id="treeDemo" class="ztree" style="float:left"></ul>
	          </div>
          <div class="msg-snapshot">
          	<ul>
          		<s:if test="#session.perUrlList.contains('111_3_1_9_1')">
            	<li><a id="sendMsgListID" name="sendMsgListID" class="msg_btn"  onclick="callsendMsgList();">消息</a></li>
            	</s:if>
            	<s:if test="#session.perUrlList.contains('111_3_1_19_1')">
              	<li><a id="sendPhotoListID" name="sendPhotoListID" class="snapshot_btn"  onclick="callsendPhotoList();">拍照</a></li>
              	</s:if>
            </ul>
           
          </div>
          </div>
        </td>
        <td valign="top" class="sleftline" id="middeldiv" style="display: none;">
	        <div id="content_middelside">
		        <div>
	            	<a  class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
	          	</div>
	        </div>
        </td>
        <td valign="top" id="rightdiv">
        <div id="content_rightside">
          <div class="titleBar" id="titleBarMap">
            <div class="title">车辆监控</div>
            <div class="workLink">
              <s:if test="#session.perUrlList.contains('111_3_1_3_1')">
              <a href="<s:url value='/message/initmessage.shtml' />" class="btnLink">历史消息</a>
              </s:if>
              <s:if test="#session.perUrlList.contains('111_3_1_2_1')">
              <a href="<s:url value='/photomonitor/initphoto.shtml' />" class="btnLink" onfocus="this.blur()" >照片管理</a>
              </s:if>
               <s:if test="#session.perUrlList.contains('111_3_1_20')">
              <a href="<s:url value='/overload/newoverloadready.shtml'/>" class="btnLink" >超载记录</a>
              </s:if>
              <s:if test="#session.perUrlList.contains('111_3_1_18_1')">
              <a href="<s:url value='/sendcmdhis/newsendcmdhis.shtml'/>" class="btnLink" onfocus="this.blur()" >操作记录</a>
              </s:if>
            </div>
          </div>
          <!-- 地图区域  -->
          <div id="mapdiv" style="overflow: hidden;">
          	<div class="noPlayerBox">
          	<object id="FlashID" class="noPlayer" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="0" height="0">
			  <param name="movie" value="../scripts/swf/ver.swf">
			  <param name="quality" value="high">
			  <param name="wmode" value="transparent">
			  <param name="swfversion" value="11.5.502.146">
			  <param name="expressinstall" value="../scripts/swf/expressInstall.swf">
			  <!--[if !IE]>-->
			  <object type="application/x-shockwave-flash" data="../scripts/swf/ver.swf" width="0" height="0">
			    <!--<![endif]-->
			    <param name="quality" value="high">
			    <param name="wmode" value="transparent">
			    <param name="swfversion" value="11.5.502.146">
			    <param name="expressinstall" value="../scripts/swf/expressInstall.swf">
			    <div> 
			      <p style="text-align: center;"><a href="http://www.adobe.com/go/getflashplayer"><img src="../newimages/btn_get_flash_player.gif" alt="Get Adobe Flash player" /></a></p>
			    </div>
			    <!--[if !IE]>-->
			  </object>
			  <!--<![endif]-->
			</object>
          	</div>
          	<div id="iCenter" style="width: 100%; height: 300px;"></div>
          	
          	<table id="mapbartab"  width="300" border="0" cellspacing="0" cellpadding="0" style="width:300px;  position:absolute; z-index:1001; right:0; top:130px;">
                    <tr id="maptoolbar" style="display:none">
                      <td height="20" align="center" background="../images/fudong_bg.png" style=" border-left:1px solid #4573BE;cursor: pointer;">
                      <table width="285" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td align="left"><a href="javascript:void(0)" title="隐藏" onClick="maptoolbarIsshow('up');"><img id="goup" src="../images/up_arrow.png"  border="0"></a></td>
                          <s:if test="#session.perUrlList.contains('111_3_1_13_1')">
                          <td align="center"><a href="javascript:void(0)" title="测距" onClick="mousePolyline();"><img id="drawLine" src="../images/fudong_test.gif"  border="0"></a></td>
                          </s:if>
                          <s:if test="#session.perUrlList.contains('111_3_1_14_1')">
                          <td align="center"><a href="javascript:void(0)" title="实时交通" onclick="addTileLayer_TRAFFIC()"><img id="TILELAYERTRAFFIC" src="../images/fudong_trans.gif"  border="0"></a></td>
                          </s:if>
                          <s:if test="#session.perUrlList.contains('111_3_1_15_1')">
                          <td align="center"><a href="javascript:void(0)" title="放大" onclick="mousezoomin()"><img id="smallToBig" src="../images/fudong_big.gif"  border="0"></a></td>
                          </s:if>
                          <s:if test="#session.perUrlList.contains('111_3_1_16_1')">
                          <td align="center"><a href="javascript:void(0)" title="缩小" onclick="mousezooout()"><img id="bigToSmall" src="../images/fudong_small.gif"  border="0"></a></td>
                          </s:if>
                          <td align="center"><a href="javascript:void(0)" title="聚合" onClick="CLUSTER_OPEN()"><img id="CLUSTER_OPEN_ID" src="../newimages/sidelayerimages/fudong_together2.gif"  border="0"></a></td>
                          
<!--                          <s:if test="#session.perUrlList.contains('111_3_1_17_1')">-->
<!--                          </s:if>-->

                        </tr>
                      </table>
                      </td>
                    </tr>
                   <!--  <tr id="fudong_up" >
                      <td align="center" valign="top"><a href="#" ><img src="../images/fudong_up.png" width="60" height="8" onclick="maptoolbarIsshow('up')" border="0"></a></td>
                    </tr>-->
                    <tr id="fudong_down" style="display:block;" sytle="position:absolute;z-index:1001;">
                      <td align="center" valign="top"><a  ><img src="../images/fudong_down.png" width="60" height="8" onclick="maptoolbarIsshow('down')" border="0"></a></td>
                    </tr>
               </table>
  
          
          <!-- 新增监控页面的车辆过滤  by chenwf -->
            <div style="width:100px;  position:absolute; z-index:1002; right:500px; top:130px;">
                   <div style="background-color: #E2F5FF;width: 100px;height: 20px;text-align: center;border: 1px;border-color: #98B9E6;border-style: solid;cursor: pointer;" onclick="slideToggle();">
                                                  筛选车辆<img id="down_upImg" src="../newimages/down.png"/>
                   </div>
                   <div id="childClickDiv" style="display: none">
                   <div style="background-color: #FFF;width: 100px;height: 25px;text-align: center;border:1px;border-color: #98B9E6;border-style: solid;">
                    <img id ="image1" src="../newimages/checked.png" style="display:none;float:left;margin-left:17px;margin-right: 5px;"/><a href="javascript:void(0)" onclick="clickSel(1);">全部</a>
                   </div>
                   <div style="background-color: #FFF;width: 100px;height: 25px;text-align: center;border: 1px;border-color: #98B9E6;border-style: solid;">
                   	 <img id ="image2" src="../newimages/checked.png" style="display:none;float:left;margin-left: 17px;margin-right: 5px;"/><a href="javascript:void(0)" onclick="clickSel(2);">在线</a>
                   </div>
                   <div style="background-color: #FFF;width: 100px;height: 25px;text-align: center;border: 1px;border-color: #98B9E6;border-style: solid;">
                   <img id ="image3" src="../newimages/checked.png" style="display:none;float:left;margin-left: 17px;margin-right: 5px;"/><a href="javascript:void(0)" onclick="clickSel(3);">离线</a>
                   	</div>
                   <div style="background-color: #FFF;width: 100px;height: 25px;text-align: center;border: 1px;border-color: #98B9E6;border-style: solid;">
                   <img id ="image4" src="../newimages/checked.png" style="display:none;float:left;margin-left: 17px;margin-right: 5px;"/><a href="javascript:void(0)" onclick="clickSel(4);">行驶</a>
                   	</div>
                   <div style="background-color: #FFF;width: 100px;height: 25px;text-align: center;border: 1px;border-color: #98B9E6;border-style: solid;">
                   <img id ="image5" src="../newimages/checked.png" style="display:none;float:left;margin-left: 17px;margin-right: 5px;"/><a href="javascript:void(0)" onclick="clickSel(5);">停驶</a>
                   </div>
                   <div style="background-color: #FFF;width: 100px;height: 25px;text-align: center;border: 1px;border-color: #98B9E6;border-style: solid;">
                   <img  id ="image6" src="../newimages/checked.png" style="display:none;float:left;margin-left: 17px;"/><a href="javascript:void(0)" onclick="clickSel(6);">有告警</a>
                   </div>
                   </div>
               </div>
          </div>
           
           
           
          
          
          <!-- 告警数据区域 -->
          <div class="bottom-tab-bar" id="alarmDateArea" style="<s:if test="#session.perUrlList.contains('111_3_1_12_1')"></s:if><s:else>display: none</s:else>">
          	<a id="bottom-tab-upordown" class="bottom-tab-up" onclick="alarmAreaShowControl(1)"></a>
              <ul>
                <li id="litab1" class="select"><a id="tab1"   onclick="changeChoose(this.id)">紧急告警(<span id="tab1count" style="font-weight: bold;color: red;"></span>)</a></li>
                <li id="litab2"><a id="tab2"  onclick="changeChoose(this.id)">超速告警(<span id="tab2count" style="font-weight: bold;color: red;"></span>)</a></li>
                <li id="litab3"><a id="tab3"  onclick="changeChoose(this.id)">车辆故障(<span id="tab3count" style="font-weight: bold;color: red;"></span>)</a></li>
                <s:if test="3==#session.adminProfile.en_mould">	
                <li id="litab4"><a id="tab4"  onclick="changeChoose(this.id)">异常乘车(<span id="tab4count" style="font-weight: bold;color: red;"></span>)</a></li>
                </s:if>
<!--                 <li id="litab5"><a id="tab5"  onclick="changeChoose(this.id)">新能源系统故障(<span id="tab5count" style="font-weight: bold;color: red;"></span>)</a></li> -->
              </ul>
              <s:if test="#session.perUrlList.contains('111_3_1_12_1')">
		       <a class="bottom-tab-more" onclick="newmorealarmlist();" href="javascript:void(0)">更多>></a>
			  </s:if>
              <a class="bottom-tab-more" style="width: 80px; border-left-width: 0px;" onclick="pop_alarm_setting();"  href="javascript:void(0)">告警显示设置</a>
              <div id="tipmsg" class="tip-msg" style="display: none">
              	<span class="tip-warn"></span>
                <span class="tip-info">请注意！<br />车辆发生紧急告警</span>
                <bgsound id="embedid" src="" autostart="true" hidden="true">
          	  </div>
          	  <div id="alarmDateListssArea" style="width:100%;height: 118px;display: none;margin: 22px 0 0 0;" >
          	  		<table id="sostab" width="100%"   cellspacing="0"></table>
          	  		<table id="chaosutab" width="100%"   cellspacing="0"></table>
          	  	    <table id="alermtab" width="100%"  cellspacing="0"></table>
          	        <table id="gala2" width="100%"  cellspacing="0"></table>
<!--           	        <table id="newenergy" width="100%"  cellspacing="0"></table> -->
          	  </div>
          </div>
          </div>
        </td>   
      </tr>
    </table>
</div>					
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>					
</div>						
    

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%> 
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterprise_js.jsp"%> 
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/sidelayerMapUitl.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
<%@ include file="/WEB-INF/jsp/common/key2.jsp"%>
<%@ include file="newmapabcOption.jsp"%>

<script type="text/javascript">
//var popwidth = "500px";
//var popheight = "540px";
var id=1;//默认全部选中
function initpop() { //alert(www);
    jQuery('#popArea').mk_sidelayer({
       //'top': '129px',
      'height': '540px',
      'width': '500px',
      //'url': '<s:url value="/carrun/ready.shtml" />',
      'is_show': false,
      'can_close': true,
      close_callback: function() {
    	  jQuery("#expandD").hide();
			expandFlag = false;
			
	    	var iframeobj = document.getElementById("iframeshowArea");
	    	if(iframeobj != null){
	    		iframeobj.src ="";
	    	}
      },
      hide_callback: function() {
    	  jQuery("#expandD").hide();
			
	    	var iframeobj = document.getElementById("iframeshowArea");
	    	if(iframeobj != null){
		    	var test=iframeobj.src;
		    	if(test.indexOf("Video")==-1){
		    		iframeobj.src ="";
		    	}
		    	if(test.indexOf("Video")!=-1)
		    	{
					if(test.indexOf("RePlayVideo")==-1){
						parent.frames["iframeshowArea"].window.allstop();	
					}
		    	} 	
	    		
	    	}
    	}
      
     /*reload_callback: function() { alert(123);
    	     var iframeobj = document.getElementById("iframeshowArea");
		 	if(iframeobj != null){
				iframeobj.src ="";
			}
	}*/
      
    });
}


/*
var fileNames;//文件名，数组
var currentImgIdx = 0;//当前显示的文件索引
var maxImgIdx = 0;//最后一张图片索引
debugger;
//session为空，则调用action查询数据，并设置session的值
<s:if test="#session.isHomeShow == null">
jQuery.ajax({
	type: 'POST', 
	url: '<s:url value='/newIconDisplay/getHomeShow.shtml' />',
	async : false,
	data: {userID:'<s:property value="#session.adminProfile.userID" />'},
	success: function(data) {
		debugger;
		if(data.fileNames) {
			var height = jQuery(window).height();
			if(height > 738) {
				height = 738;
			}
			fileNames = data.fileNames;
			maxImgIdx = data.fileNames.length - 1;
			jQuery.blockUI({
				overlayCSS:{cursor:'default'},
				isShowLayr3:false//参数isShowLayr3为false，显示轮播图片处理
			});
			$('blockImg').src = "../opt/m2mfile/ftp/version/"+data.fileNames[0];
			jQuery("#imgIndex").html("1/" + data.fileNames.length);
		}
	}
});
</s:if>
//session==1，显示首页轮播图片
<s:if test="#session.isHomeShow == 1">
	fileNames = new Array();
	<s:iterator value="#session.fileNames" id="fileName">
		fileNames.push('<s:property value="fileName"/>');
	</s:iterator>
	maxImgIdx = fileNames.length - 1;
	jQuery.blockUI({
		overlayCSS:{cursor:'default'},
		isShowLayr3:false
	});
	$('blockImg').src = "../opt/m2mfile/ftp/version/"+fileNames[0];
	jQuery("#imgIndex").html("1/" + fileNames.length);
</s:if>

function unblockUI() {
	jQuery.unblockUI();
	jQuery("#closeBlockImg").css("display","none");
	jQuery("#showPriviousImg").css("display","none");
	jQuery("#blockImg").css("display","none");
	jQuery("#showNextImg").css("display","none");
	jQuery("#imgIndex").css("display","none");
	//关闭后，设置session的值，刷新页面不再显示轮播图片
	<%session.setAttribute("isHomeShow","0");%>
}
function showPreviousImg() {
	if(currentImgIdx > 0) {
		currentImgIdx = currentImgIdx - 1;
		$('blockImg').src = "../opt/m2mfile/ftp/version/" + fileNames[currentImgIdx];
	} else if(currentImgIdx == 0) {
		currentImgIdx = maxImgIdx;
		$('blockImg').src = "../opt/m2mfile/ftp/version/" + fileNames[currentImgIdx];
	}
	jQuery("#imgIndex").html((currentImgIdx + 1) + "/" + (maxImgIdx + 1));
}
function showNextImg() {
	if(currentImgIdx < maxImgIdx) {
		currentImgIdx = currentImgIdx + 1;
		$('blockImg').src = "../opt/m2mfile/ftp/version/" + fileNames[currentImgIdx];
	} else if(currentImgIdx == maxImgIdx) {
		currentImgIdx = 0;
		$('blockImg').src = "../opt/m2mfile/ftp/version/" + fileNames[currentImgIdx];
	}
	jQuery("#imgIndex").html((currentImgIdx + 1) + "/" + (maxImgIdx + 1));
}
*/


// 增加车辆帅选的js代码

jQuery('#childClickDiv div').mouseover(function(){
	//取消所有选中样式
	jQuery('#childClickDiv div').css('background-color','#FFF');
	//jQuery('#childClickDiv div img').css('display','none');
	//jQuery('#childClickDiv div a').css('float','none');
	//添加选中样式
	jQuery(this).css('background-color','#E2F5FF');
	//jQuery(this).children('img').css('display','block');
	//jQuery(this).children('a').css('float','left');
});
// jQuery('#childClickDiv div').mouseout(function(){
// 	jQuery(this).css('background-color','#FFF');
// 	jQuery('#childClickDiv div img').css('display','none');
// 	jQuery(this).children('a').css('float','none');
// });
//筛选车辆，显示或隐藏--add by chenwf
var slideup=false;
function slideToggle(){
	//var image='"'+'image'+id+'"';
	getImage(id);
	jQuery("#childClickDiv").slideToggle('fast',function(){
		if(slideup){
			jQuery("#down_upImg").attr('src','../newimages/down.png');
			slideup=false;
		}else{
			//jQuery('#image').css('display','block');
			jQuery("#down_upImg").attr('src','../newimages/up.png');
			slideup=true;
		}
		
	});
}

//点击筛选事件--add by chenwf
function clickSel(idx){
	id=idx;
	//设置选中样式
	getImage(id);
	jQuery('#vehicleLn').val('');
	mapObj.removeAllOverlays();
	var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
	var strsql ="" ;
	var nodesCheck = treeObj.getCheckedNodes();
	for(var i = 0, len = nodesCheck.length; i < len; i++) {
		if("pIcon" != nodesCheck[i].iconSkin) {
			strsql += "'"+nodesCheck[i].id+"'"+",";
		}
	}
	if(strsql!=""){
		strsql = strsql.substr(0,strsql.length-1);
	}else{
		return;
	}
	//treeObj.checkAllNodes(false);//全部取消选中
	//initpop();//init 
	//searchTree();//init 树
	//设置选中样式
	switch(idx)
	{
	case 1:
		//treeObj.checkAllNodes(true);//全部选中
		GPSDwr.getVehcileBySel('all',strsql,jQuery("#organizationid").val(),quickSelectVehicle);
		break;
	case 2:
		GPSDwr.getVehcileBySel('online',strsql,jQuery("#organizationid").val(),quickSelectVehicle);
		break;
	case 3:
		GPSDwr.getVehcileBySel('offline',strsql,jQuery("#organizationid").val(),quickSelectVehicle);
		break;
	case 4:
		GPSDwr.getVehcileBySel('run',strsql,jQuery("#organizationid").val(),quickSelectVehicle);
		break;
	case 5:
		GPSDwr.getVehcileBySel('stop',strsql,jQuery("#organizationid").val(),quickSelectVehicle);
		break;
	case 6:
		GPSDwr.getVehcileBySel('alarm',strsql,jQuery("#organizationid").val(),quickSelectVehicle);
		break;
	}
	jQuery("#childClickDiv").slideUp('fast',function(){
		
	});
}





function pop_alarm_setting(){

    art.dialog.open('<s:url value="/alarmSetting/init.shtml" />',{
    	id:'alarmShowSettingDialog',
		title:"告警显示设置",
		lock:true,
		width:1000,
		height:450,
		yesFn: function(iframeWin, topWin){
			iframeWin.save();
        	return true;
    	}
	});
}
</script>

</body>
</html>