<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html >
<head>

<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>							
<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterprise_css.jsp"%>								
<title><s:text name="common.title" /></title>

<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>						
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/gpspostion.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
<style type="text/css">
	
</style>
<!-- 中文注释 -->
</head>

<body onunload="sethtmlclearInterval()">
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
  <div id="popArea" class="mk-sidelayer mk-sidelayer-small" style="overflow:hidden;display: none;">
	  <div class="mk-sidelayer-toolbar">        
	        <span class="mk-sidelayer-bar-btn show"></span>
	        <h1 class="mk-sidelayer-bar-title"></h1>
	        <div class="mk-sidelayer-tools" style="overflow:hidden;">
	        </div>
	  </div>
	  <div class="mk-sidelayer-content">
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
							<div class="treeTab" style="z-index:100;position:relative;">
								<a class="tabfocus" onfocus="this.blur()" id="enttabid"
									onclick="tabSwitch('enttabid')">组织</a> <a class="hideLeft"
									onfocus="this.blur()" id="hideleftbutton"
									onclick="HideandShowControl()"></a>
							</div>
          
	          <div class="newsearchPlan">
	            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	              <tr>
	                <td width="130px" align="right"><input maxlength="60" id="vehicleLntext" name="vehicleLntext" type="text" class="input120" onkeypress="if(event.keyCode==13){jQuery('#vehicleLn').val(jQuery('#vehicleLntext').val());mapObj.removeAllOverlays();searchTree();}"/></td>
	                <td align="center"><a  class="newbtnbule" onfocus="this.blur()" onclick="jQuery('#vehicleLn').val(jQuery('#vehicleLntext').val());mapObj.removeAllOverlays();searchTree();">查询</a></td>
	              </tr>
	            </table>
	          </div>
	          <div id="twobutton" class="refreshAndreset2">
		          <a  class="btnRefresh" onfocus="this.blur()" onclick="treebtnRefresh()">刷新</a>
		          <a  class="btnReset" onfocus="this.blur()" onclick="treebtnReset()">重置</a>
	          </div>
	          <div id="treeDemoDiv" class="treeBox" style="float:left">
	      		<ul id="treeDemo" class="ztree"></ul>
	          </div>
          <div class="msg-snapshot" >
          <s:if test="#session.perUrlList.contains('111_3_1_8')">
          	<input id="sendMsgListID" name="sendMsgListID" class="msg_btn_sure" style="float: none;" type="button" onclick="callsendMsgList();"/>
          	</s:if>
          	<s:if test="#session.perUrlList.contains('111_3_1_10')">
          	<input id="sendPhotoListID" name="sendPhotoListID" class="snapshot_btn_sure" style="float: none;" type="button" onclick="callsendPhotoList();"/>
          	</s:if>
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
            	<s:if test="#session.perUrlList.contains('111_3_1_13')">
              <a href="<s:url value='/message/initmessage.shtml' />" class="btnLink">历史消息</a>
				</s:if>
              <s:if test="#session.perUrlList.contains('111_3_4_5')">
              <a href="<s:url value='/photomonitor/initphoto.shtml' />" class="btnLink" onfocus="this.blur()" >照片管理</a>
              </s:if>
              <s:if test="#session.perUrlList.contains('111_3_1_14')">
              <a href="<s:url value='/sendcmdhis/newsendcmdhis.shtml'/>" class="btnLink" onfocus="this.blur()" >操作记录</a>
           </s:if>
            </div>
            <%-- <div class="workLink">
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
            </div> --%>
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
          	
          	<table id="mapbartab"  width="300" border="0" cellspacing="0" cellpadding="0" style="width:300px;  position:absolute; z-index:1001; right:0; top:118px;">
                    <tr id="maptoolbar" style="display:none">
                      <td height="20" align="center" background="../images/fudong_bg.png" style=" border-left:1px solid #4573BE;cursor: pointer;">
                      <table width="285" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td align="left"><a href="javascript:void(0)" title="隐藏" onClick="maptoolbarIsshow('up');"><img id="goup" src="../images/up_arrow.png"  border="0"></a></td>
                          <s:if test="#session.perUrlList.contains('111_3_1_2_1')">
                          <td align="center"><a href="javascript:void(0)" title="测距" onClick="mousePolyline();"><img id="drawLine" src="../images/fudong_test.gif"  border="0"></a></td>
                          </s:if>
                          <s:if test="#session.perUrlList.contains('111_3_1_2_2')">
                          <td align="center"><a href="javascript:void(0)" title="实时交通" onclick="addTileLayer_TRAFFIC()"><img id="TILELAYERTRAFFIC" src="../images/fudong_trans.gif"  border="0"></a></td>
                          </s:if>
                          <s:if test="#session.perUrlList.contains('111_3_1_2_3')">
                          <td align="center"><a href="javascript:void(0)" title="放大" onclick="mousezoomin()"><img id="smallToBig" src="../images/fudong_big.gif"  border="0"></a></td>
                          </s:if>
                          <s:if test="#session.perUrlList.contains('111_3_1_2_4')">
                          <td align="center"><a href="javascript:void(0)" title="缩小" onclick="mousezooout()"><img id="bigToSmall" src="../images/fudong_small.gif"  border="0"></a></td>
                          </s:if>
                          <s:if test="#session.perUrlList.contains('111_3_1_2_5')">
                          <td align="center"><a href="javascript:void(0)" title="聚合" onClick="CLUSTER_OPEN()"><img id="CLUSTER_OPEN_ID" src="../newimages/sidelayerimages/fudong_together2.gif"  border="0"></a></td>
                          </s:if>
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
               
               <div style="width:100px;  position:absolute; z-index:1002; right:500px; top:118px;">
                   <div style="background-color: #E2F5FF;width: 100px;height: 25px;text-align: center;border: 1px;border-color: #98B9E6;border-style: solid;cursor: pointer;" onclick="slideToggle();">
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
          <div class="bottom-tab-bar" id="alarmDateArea">
          	<a id="bottom-tab-upordown" class="bottom-tab-up" onclick="alarmAreaShowControl(1)"></a>
              <ul>
                <li id="litab1" class="select"><a id="tab1" onclick="changeChoose(this.id)">紧急告警(<span id="tab1count" style="font-weight: bold;color: red;"></span>)</a></li>
              <s:if test="#session.perUrlList.contains('111_3_4_4_6')">
                <li id="litab2"><a id="tab2"  onclick="changeChoose(this.id)">超速告警(<span id="tab2count" style="font-weight: bold;color: red;"></span>)</a></li>
              </s:if>
              <s:if test="#session.perUrlList.contains('111_3_4_4_7')">
                <li id="litab3"><a id="tab3"  onclick="changeChoose(this.id)">非时发车告警(<span id="tab3count" style="font-weight: bold;color: red;"></span>)</a></li>
              </s:if>
              <s:if test="#session.perUrlList.contains('111_3_4_4_8')">
                <li id="litab4"><a id="tab4"  onclick="changeChoose(this.id)">未满发车告警(<span id="tab4count" style="font-weight: bold;color: red;"></span>)</a></li>
              </s:if>
              <s:if test="#session.perUrlList.contains('111_3_4_4_9')">
                <li id="litab5"><a id="tab5"  onclick="changeChoose(this.id)">站外开门告警(<span id="tab5count" style="font-weight: bold;color: red;"></span>)</a></li>
              </s:if>
              <s:if test="#session.perUrlList.contains('111_3_4_4_10')">
                <li id="litab6"><a id="tab6"  onclick="changeChoose(this.id)">迟到告警(<span id="tab6count" style="font-weight: bold;color: red;"></span>)</a></li>
              </s:if>
              <s:if test="#session.perUrlList.contains('111_3_4_4_11')">
                <li id="litab7"><a id="tab7"  onclick="changeChoose(this.id)">油量告警(<span id="tab7count" style="font-weight: bold;color: red;"></span>)</a></li>
              </s:if>
              </ul>
              
		       <a class="bottom-tab-more" onclick="newmorealarmlist();" href="javascript:void(0)">更多>></a>
			  
              <div id="tipmsg" class="tip-msg" style="display: none">
              	<span class="tip-warn"></span>
                <span class="tip-info">请注意！<br />车辆发生紧急告警</span>
                <bgsound id="embedid" src="" autostart="true" hidden="true">
          	  </div>
          	  <div id="alarmDateListssArea" style="width:100%;height: 118px;display: none;margin: 22px 0 0 0;" >
          	  		<table id="sostab" width="100%"   cellspacing="0"></table><!-- 紧急告警 -->
          	  	    <table id="overspdtab" width="100%"  cellspacing="0"></table><!-- 超速告警 -->
          	        <table id="nottimetab" width="100%"  cellspacing="0"></table>
          	        <table id="nofulltab" width="100%"  cellspacing="0"></table>
          	        <table id="notsitetab" width="100%"  cellspacing="0"></table>
          	        <table id="latetab" width="100%"  cellspacing="0"></table>
          	        <table id="oiltab" width="100%"  cellspacing="0"></table>
          	  </div>
          </div>
          </div>
        </td>   
      </tr>
    </table>
</div>					
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>					
</div>						
    
 <!-- test="#session.adminProfile.en_code == '0000600001' or #session.adminProfile.en_code == '0000600002' or #session.adminProfile.en_code == '0000600003' or #session.adminProfile.en_code == '0000600004' or #session.adminProfile.en_code == '0000105556'" -->
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%> 
<%-- <s:if test="#session.adminProfile.en_code == '0000850260'"> --%>
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterprisedvr_js.jsp"%>
<%-- </s:if>
<s:else>
<%@include file="/WEB-INF/jsp/treemanage/zTree_enterprise_js.jsp"%>
</s:else> --%>
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
	    	var iframeobj = document.getElementById("iframeshowArea");
	    	if(iframeobj != null){
	    		iframeobj.src ="";
	    	}
      },
      hide_callback: function() {
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
      
    });
}
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
//筛选车辆，显示或隐藏
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

//点击筛选事件
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
</script>

</body>
</html>