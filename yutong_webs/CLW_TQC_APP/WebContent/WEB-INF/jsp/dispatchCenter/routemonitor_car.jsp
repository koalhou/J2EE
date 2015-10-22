<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script>
/*
* 刷新浮动框头
*/
var jiaodianid = "";
var sidevin = "";
var intsideflash = "";
//告警参数不用为null
var alarmid,alarmtypeid,alarmtime = null;
/**
 * 展开气泡方法
 * @param vin
 * @return
 */
 function onevmouse_click(obj,vin,info){
		jQuery('#infobarDivNO').css("top",jQuery(obj).offset().top - 200).css("left",jQuery(obj).offset().left-80);
		//if(info!=null&&info == '0') {
			car_pop_vin = vin;
			GPSDwr.returnTipInfo(vin,returnTipInfo_callback);
			if(timerID_car_pop == ""){
				openCarPopTime();
			}
		//} else {
			//alert("该车无GPS位置信息，不能查看车辆详情！");
		//}
}
function returnTipInfo_callback(date){
	var TerminalViBean = date[0];
	var vin = TerminalViBean.VEHICLE_VIN;
	//alert(vin);
	document.getElementById("infobarDivNO").style.display = "block";
	document.getElementById("infobarDivNO").innerHTML=gethtml(TerminalViBean);
	BalloonConsole(BalloonTabFlag);
}

function closeCarPOP(){
	if(typeof(document.getElementById("infobarDivNO")) != "undefined"){
		document.getElementById("infobarDivNO").style.display="none";
	}
	cancelCarPopTime();
	BalloonTabFlag=1;
}

/**
 * 构建气球框内容
 * @param TerminalViBean
 * @return
 */
function gethtml(TerminalViBean){
	//alert(TerminalViBean.DIRECTION);
	
	var httpinfo = "";//"<div  style='width:240px; height:140px;top:8px;left:6px;'>";
	    httpinfo +=	"<div class='balloon'>";
	    httpinfo +=	"<div class='balloonLeft'></div>";
	    httpinfo +=	"<div class='balloonMain'>";
	    httpinfo +=	"<div class='balloonTil'>";
	    if(BalloonTabFlag == 1){
		    httpinfo +=	"<a id='BalloonTab1' class='tabfocus' onclick='BalloonConsole(1)'>实时信息</a>";
		    httpinfo +=	"<a id='BalloonTab2' class='tab' onclick='BalloonConsole(2)'>车辆信息</a>";
	    }else{
		    httpinfo +=	"<a id='BalloonTab1' class='tab' onclick='BalloonConsole(1)'>实时信息</a>";
		    httpinfo +=	"<a id='BalloonTab2' class='tabfocus' onclick='BalloonConsole(2)'>车辆信息</a>";
		}
	    httpinfo +=	"<a href='javascript:void(0);' class='close' onclick='closeCarPOP()'></a>";
	    httpinfo +=	"</div>";
	    
	    if(BalloonTabFlag == 1){
	    	httpinfo +=	"<div id='BalloonInfo1' class='data' style='z-index:1'>";
		}
	    else{
	    	httpinfo +=	"<div id='BalloonInfo1' class='data' style='z-index:1;display:none;'>";
		}
	    httpinfo +=	"<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
	    httpinfo +=	"<tr>";
	    httpinfo +=	"<td width='20%' height='24' align='left'>乘载情况：</td>";
	    httpinfo +=	"<td width='24%' align='left'><strong>"+nullToZore(TerminalViBean.STU_NUM)+"/"+nullToZore(TerminalViBean.LIMITE_NUMBER)+"(人)</strong></td>";
	    httpinfo +=	"<td width='20%'>油箱油量：</td>";
	    //httpinfo +=	"<td width='30%'  align='right'><img src='../images/qipaoimages/"+(TerminalViBean.POWER_STATE=='1'?'dianping1.gif':'dianping0.gif')+"' width='22' height='22' title='"+(TerminalViBean.POWER_STATE=='1'?'终端主电源掉电':'终端主电源供电')+"'></td>";
	    httpinfo +=	"<td width='24%' ><strong>"+nullToZore(TerminalViBean.OIL_INSTANT)+"L</strong></td>";
	    httpinfo +=	"</tr>";
	    httpinfo +=	"<tr>";   
	    httpinfo +=	"<td height='24' align='left'>行驶速度：</td>";       
	    httpinfo +=	"<td><strong>"+nullToZore(TerminalViBean.SPEEDING)+"&nbsp;km/h</strong></td>";
	    httpinfo += "<td align='left'>点火状态：</td>";
	    httpinfo +=	"<td align='left'><strong>"+(TerminalViBean.STAT_INFO==0?'关':'开')+"</strong></td>";
	    /* httpinfo +=	"<td align='left'>引擎转速：</td>";
	    httpinfo +=	"<td align='left'><strong>"+nullToZore(TerminalViBean.ENGINE_ROTATE_SPEED)+"rpm</strong></td>";  */
	    httpinfo +=	"</tr>";
	    
	    httpinfo += "<tr>";
	    httpinfo += "<td height='24' align='left'>定位状态：</td>";
	    httpinfo += "<td align='left'><strong>"+(TerminalViBean.dingwei_stat!=0?'有效':'无效')+"</strong></td>";
	    httpinfo += "<td align='left'></td>";
	    httpinfo +=	"<td align='left'></td>";
	    //httpinfo += "<td><img src='../images/qipaoimages/"+(TerminalViBean.POWER_STATE=='1'?'dianping1.gif':'dianping0.gif')+"' width='22' height='22'></td>";
	    httpinfo += "</tr>";
	    
	    httpinfo +=	"<tr>";
	    httpinfo +=	"<td height='24' align='left'>上报时间：</td>";
	    httpinfo +=	"<td colspan='2' align='left'>"+timeoptval(TerminalViBean.TERMINAL_TIME)+"</td>"; 
	    httpinfo +=	"<td height='24' align='right'>";
		    
	    //httpinfo +=	"<img src='../images/btn_tool/"+(TerminalViBean.VEH_EXT_INFO=='1'?'camera.gif':'camera2.gif')+"' width='20' height='20' title='"+(TerminalViBean.VEH_EXT_INFO=='1'?'3G可用':'3G不可用')+"'>";        
		httpinfo +=	"<img src='../newimages/qipaoimages/"+(TerminalViBean.POWER_STATE=='1'?'dianping1.png':'dianping0.png')+"' width='24' height='20' title='"+(TerminalViBean.POWER_STATE=='1'?'终端主电源掉电':'终端主电源供电')+"'></td>";
	    httpinfo +=	"</tr>";        
	    httpinfo +=	"</table>";
	    httpinfo +=	"</div>";
	    
	    if(BalloonTabFlag == 1){
	    	httpinfo +=	"<div id='BalloonInfo2' class='data' style='display:none;z-index:2;'>";
		}else{
			httpinfo +=	"<div id='BalloonInfo2' class='data' style='z-index:2'>";
		}
	    httpinfo +=	"<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
	    httpinfo +=	"<tr>";  
	    httpinfo +=	"<td height='24'>所属单位：</td>";    
	    httpinfo +=	"<td colspan='3'><strong>"+encodeHtml(TerminalViBean.ENTER_SHORT_NAME)+"</strong></td>";     
	    httpinfo +=	"</tr>";  
	    //模式三企业车辆TAB内容  
	    httpinfo += "<tr>";
	    httpinfo += "<td height='24'>当前线路：</td>";
	    httpinfo += "<td colspan='3'><strong>"+(TerminalViBean.ROUTE_NAME==null?'':encodeHtml(TerminalViBean.ROUTE_NAME))+"</strong></td>";
	    httpinfo += "</tr>";
 
	   	httpinfo += "<tr>";
	   	httpinfo += "<td width='20%'>车辆牌号：</td>";
	   	if(TerminalViBean.VEHICLE_LN.length>7){
			var titleln=TerminalViBean.VEHICLE_LN.substr(TerminalViBean.VEHICLE_LN.length-7);
			var titleln="*"+titleln;
			httpinfo += "<td width='24%'><strong>"+encodeHtml(titleln)+"</strong></td>";
		}
	   	else{
	   		httpinfo += "<td width='24%'><strong>"+encodeHtml(TerminalViBean.VEHICLE_LN)+"</strong></td>";
		}
	   	httpinfo += "<td width='20%' height='24'>班&nbsp;车&nbsp;号：</td>";
	   	httpinfo += "<td width='24%'><strong>"+TerminalViBean.vehicle_code+"</strong></td>";
	   	httpinfo += "</tr>";

	    httpinfo +=	"<tr>";
	    httpinfo += "<td width='20%' height='24'>驾&nbsp;驶&nbsp;员：</td>";

	    if(TerminalViBean.DRIVER_NAME!=null&&TerminalViBean.DRIVER_NAME.length>0) {
	    	if(TerminalViBean.DRIVER_NAME!=null&&TerminalViBean.DRIVER_NAME.length>7){
				var lndriver=TerminalViBean.DRIVER_NAME.substr(0,6);
				var lndriver=lndriver+"*";
				httpinfo += "<td width='24%'><strong>"+encodeHtml(lndriver)+"</strong></td>";
			}else{
		   		httpinfo += "<td width='24%'><strong>"+encodeHtml((TerminalViBean.DRIVER_NAME==null?'未登录':TerminalViBean.DRIVER_NAME))+"</strong></td>";
			}
	    } else {
		    if(TerminalViBean.SETDRIVER_NAME!=null&&TerminalViBean.SETDRIVER_NAME.length>7){
				var lndriver=TerminalViBean.SETDRIVER_NAME.substr(0,6);
				var lndriver=lndriver+"*";
				httpinfo += "<td width='24%'><strong>"+encodeHtml(lndriver)+"</strong></td>";
			}
		   	else{
		   		httpinfo += "<td width='24%'><strong>"+encodeHtml((TerminalViBean.SETDRIVER_NAME==null?'未登录':TerminalViBean.SETDRIVER_NAME))+"</strong></td>";
			}
	    }
	   	//httpinfo += "<td width='24%'><strong>"+(TerminalViBean.SETDRIVER_NAME==null?'未登录':encodeHtml(TerminalViBean.SETDRIVER_NAME))+"</strong></td>";
	    httpinfo +=	"<td height='24'>手机号码：</td>";
	    httpinfo +=	"<td><strong>"+(TerminalViBean.SETDRIVER_TEL==null?'未知':TerminalViBean.SETDRIVER_TEL)+"</strong></td>"; 
	    
	    httpinfo +=	"</tr>";
	    httpinfo +=	"</table>";
	    httpinfo +=	"</div>";    
	    httpinfo +=	"<div class='toolbarqp'>";
	    <s:if test="#session.perUrlList.contains('111_3_1_4')">
	    httpinfo +="<input class='btnLink_tool5' style='float: none;' type='button' onclick=\"openFramePage('";//<a class='tool5' title='信息调度' 
	    httpinfo += TerminalViBean.vehicle_code+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','5";	    
	    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
	    			+null+","+null+","+null+")\" />"; 
	    </s:if>
	    <s:if test="#session.perUrlList.contains('111_3_1_10')"> 
	    if(TerminalViBean.color =="b" && TerminalViBean.STAT_INFO == 1){
		    httpinfo +=	"<input class='btnLink_tool4' style='float: none;' type='button' onclick=\"PhotoImage('";//<a class='tool4' title='车辆拍照' 
		    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','4";
		    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
		    +null+","+null+","+null+")\" />"; 
	    }else{
	    	httpinfo +=	"<input class='btnLink_tool4h' style='float: none;' type='button' onclick=\"PhotoImage('";//<a class='tool4h' title='车辆拍照' 
		    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','4";
		    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
		    +null+","+null+","+null+")\" />";
		}
	    </s:if>
	    
	    <s:if test="#session.perUrlList.contains('111_3_1_11')"> 
	    if(TerminalViBean.color =="b" && TerminalViBean.STAT_INFO == 1  && TerminalViBean.VEH_EXT_INFO == 1){    
		    httpinfo +=	"<input class='btnLink_tool_vedio' style='float: none;' title='视频监控' type='button' onclick=\"VideoImage('";
		    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','3";
		    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
		    +null+","+null+","+null+")\" />"; 
	    }
	    else{
	    	httpinfo +=	"<input class='btnLink_tool_vedioh' style='float: none;' title='视频监控' type='button' onclick=\"VideoImage('";
		    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','3";
		    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
		    +null+","+null+","+null+")\" />";
		}
	    </s:if>
	    <s:if test="#session.perUrlList.contains('111_3_1_12')"> 
	    
	    if(TerminalViBean.color =="b" && TerminalViBean.STAT_INFO == 1  && TerminalViBean.VEH_EXT_INFO == 1){  
		    httpinfo +=	"<input class='btnLink_tool_look'  style='float: none; title='视频回放' type='button'' onclick=\"replayVideoImage('";
		    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','7";	    
		    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
		    +null+","+null+","+null+")\" />";
	    }  
	    else{
	    	httpinfo +=	"<input class='btnLink_tool_lookh' style='float: none;' title='视频回放' type='button' onclick=\"replayVideoImage('";
		    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','7";	    
		    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
		    +null+","+null+","+null+")\" />";
		}
	    </s:if>
	    
		<s:if test="#session.perUrlList.contains('111_3_1_5')">
	    httpinfo +=	"<input class='btnLink_tool1' style='float: none;' type='button' onclick=\"openFramePage('";//<a class='tool1' title='轨迹回放' 
	    httpinfo += TerminalViBean.vehicle_code+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','1";
	    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
	    +null+","+null+","+null+")\" />";
	    </s:if>
		<s:if test="#session.perUrlList.contains('111_3_1_6')">    
	    
	    httpinfo +=	"<input class='btnLink_tool2' style='float: none;' type='button' onclick=\"openFramePage('";//<a class='tool2' title='重点监控' 
	    httpinfo += TerminalViBean.vehicle_code+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','2";
	    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
	    		 +null+","+null+","+null+")\" />";
	    </s:if>
	    <s:if test="#session.perUrlList.contains('111_3_1_7')">  
	    httpinfo +=	"<input class='btnLink_tool6' style='float: none;' type='button' onclick=\"openFramePage('";//<a class='tool6' title='告警处理' 
	    httpinfo += TerminalViBean.vehicle_code+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','6";	    
	    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
	    			+null+","+null+","+null+")\" />";
	    </s:if>
	          
	    
	    httpinfo +=	"</div>";  
	    //httpinfo +=	"<div class='arrow'></div>";    
	    httpinfo +=	"</div>";    
	    httpinfo +=	"<div class='balloonRight'></div>";    
	    httpinfo +=	"</div>";
	    
	   // testsidelayerHead(TerminalViBean.VEHICLE_LN,TerminalViBean.VEHICLE_VIN,TerminalViBean.LONGITUDE,TerminalViBean.LATITUDE,TerminalViBean.ROUTE_ID,jiaodianid,
	    //		TerminalViBean.color,TerminalViBean.VEH_EXT_INFO,TerminalViBean.STAT_INFO,null,null,null);
		  
	return httpinfo;
}
var BalloonTabFlag = 1;
function BalloonConsole(f){
	BalloonTabFlag = f;
	var BalloonTab1 = document.getElementById("BalloonTab1");
	var BalloonTab2 = document.getElementById("BalloonTab2");
	var BalloonInfo1 = document.getElementById("BalloonInfo1");
	var BalloonInfo2 = document.getElementById("BalloonInfo2");
	if(f==1){
		BalloonTab1.className="tabfocus";
		BalloonTab2.className="tab";
		//BalloonInfo1.style.visibility="visible";
		//BalloonInfo1.style.z-index="1";
		BalloonInfo1.style.display="";
		//BalloonInfo2.style.visibility="hidden";
		//BalloonInfo2.style.z-index="2";
		BalloonInfo2.style.display="none";
		
	}else if(f==2){
		BalloonTab1.className="tab";
		BalloonTab2.className="tabfocus";
		//BalloonInfo1.style.visibility="visible";
		BalloonInfo1.style.display="none";
		//BalloonInfo2.style.visibility="hidden";
		BalloonInfo2.style.display="";
	}
}
/**
 * 轨迹回放，实时监控，消息调度
 * @param ln
 * @param vin
 * @param lon
 * @param lat
 * @param routeid
 * @param optpageid
 * @param color
 * @param VEH_EXT_INFO
 * @param STAT_INFO
 * @param alarmid
 * @param alarmtypeid
 * @param alarmtime
 * @return
 */
function openFramePage(code,ln,vin,lon,lat,
		routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,
		alarmid,alarmtypeid,alarmtime){

	var iframeobj = document.getElementById("iframeshowArea");
    if(iframeobj != null){
     iframeobj.src ="";
    }
	
	testsidelayerHead(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime);

	var titleln=ln;
	if(titleln.length>7){
		titleln=titleln.substr(titleln.length-7);
		titleln="*"+titleln;
	}
	titleln=code+","+titleln;
	jQuery('#popArea').mk_sidelayer('set_title', encodeHtml(titleln));
	
	if(optpageid == 1){//轨迹回放
		//jQuery('#popArea1').mk_sidelayer('show');
	    jQuery('#popArea').mk_sidelayer('reload', 
			    '<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
			    +'&route_id='+routeid+'&optpageid='+optpageid 
			    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
		change_side_images('#aa'+optpageid);
	}
	if(optpageid == 2){//重点监控
		jQuery('#popArea').mk_sidelayer('reload', 
				'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
				+'&route_id='+routeid+'&optpageid='+optpageid
			    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
		change_side_images('#aa'+optpageid);
	}
	if(optpageid == 6){//告警处理
		/*
		jQuery('#popArea').mk_sidelayer('reload', 
				'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
				+'&route_id='+routeid+'&optpageid='+optpageid
			    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);*/
	   jQuery('#popArea').mk_sidelayer('reload', {
		    	    'url': '<s:url value="/gps/iFramechooseAction.shtml" />', 
		    	     'query_param': {'vin': vin, 'lon':lon,'lat':lat,'routeid':routeid,
			    	                  'optpageid':optpageid,'alarmid':alarmid,'alarmtypeid':alarmtypeid,
			    	                  'alarmtime':alarmtime,'sourceid':'1'}
               });
		change_side_images('#aa'+optpageid);
	}
	if(optpageid == 5){//消息调度
		jQuery('#popArea').mk_sidelayer('reload', 
				'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
				+'&route_id='+routeid+'&optpageid='+optpageid
			    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
		change_side_images('#aa'+optpageid);
	}
	if(optpageid == 4){//车辆拍照
		jQuery('#popArea').mk_sidelayer('reload', 
				'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
				+'&route_id='+routeid+'&optpageid='+optpageid
			    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
		change_side_images('#aa'+optpageid);
	}
	/* 
	if(optpageid == 3){//视频监控
		jQuery('#popArea').mk_sidelayer('reload', 
				'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
				+'&route_id='+routeid+'&optpageid='+optpageid
			    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
		change_side_images('#aa'+optpageid);
	}
	if(optpageid == 7){//视频回放
		jQuery('#popArea').mk_sidelayer('reload', 
				'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
				+'&route_id='+routeid+'&optpageid='+optpageid
			    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
		change_side_images('#aa'+optpageid);
	} */
	
	startsideflash(optpageid,vin,alarmid,alarmtypeid,alarmtime);
	//intsideflash = window.setTimeout("sidelayerrealflash()",10000);    
}

function testsidelayerHead(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime){
	var ln = encodeHtml(ln);
	var sidebuttonHtml="<ul>";
	//消息调度	
	<s:if test="#session.perUrlList.contains('111_3_1_4')">
	sidebuttonHtml +="<li id='aa5'><a href='#' title='信息调度' onclick=openFramePage('";
	sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','5";	    
	sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool1.png'/></a></li>";
	</s:if>
	//拍照
	<s:if test="#session.perUrlList.contains('111_3_1_10')"> 
	if(color =="b"   && STAT_INFO == 1){
		sidebuttonHtml+="<li id='aa4'><a href='javascript:void(0)' title='车辆拍照' onclick=PhotoImage('";
		sidebuttonHtml+= code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','4";	    
		sidebuttonHtml+="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool2.png'/></a></li>";
	}else{
		sidebuttonHtml+="<li id='aa4'><a href='javascript:void(0)' title='车辆拍照' onclick=PhotoImage('";
		sidebuttonHtml+= code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','4";	    
		sidebuttonHtml+="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool2h.png'/></a></li>";
	}
	</s:if>
	//轨迹回放
	<s:if test="#session.perUrlList.contains('111_3_1_5')"> 
	sidebuttonHtml+="<li id='aa1'><a href='#' title='轨迹回放' onclick=openFramePage('";
	sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','1";	    
	sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool5.png'/></a></li>";
	</s:if>
	//重点监控
	<s:if test="#session.perUrlList.contains('111_3_1_6')"> 
	sidebuttonHtml+="<li id='aa2'><a href='#' title='重点监控' onclick=openFramePage('";
	sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','2";
	sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool6.png'/></a></li>";
	</s:if>
	//告警处理
	<s:if test="#session.perUrlList.contains('111_3_1_7')"> 
	sidebuttonHtml+="<li id='aa6'><a href='#' title='告警处理' onclick=openFramePage('";
	sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','6";	    
	sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/ico_warning.png'/></a></li>";
	</s:if>
	sidebuttonHtml+="</ul>";
	//alert(sidebuttonHtml);
	
	jQuery('.mk-sidelayer-tools').html(sidebuttonHtml);
	
	if(jiaodianid != ""){
		//alert(jiaodianid);
		change_side_images('#aa'+jiaodianid);
	}
}
//拍照图片点击处理
function PhotoImage(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime){
	//alert(color +';'+STAT_INFO);
	//if(color =="b"   && STAT_INFO  == 1){
		openFramePage(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime);
	//}
	//else{
	//	alert("车辆已熄火，请稍后再尝试车辆拍照！");
	//}
}
// 视频点击处理
function VideoImage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime){
	//alert(color +';'+VEH_EXT_INFO);
	//if(color =="b"   && VEH_EXT_INFO == 1){
		openFramePage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime);
	//}
	//else{
	//	alert("3G视频不在线，请稍后再尝试视频监控！");
	//}
	
	
}
//视频回放点击处理
function replayVideoImage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime){
	//alert(color +';'+VEH_EXT_INFO);
	//if(color =="b"   && VEH_EXT_INFO == 1){
		openFramePage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime);
	//}
	//else{
	//	alert("3G视频不在线，请稍后再尝试视频回放！");
	//}
	
	
}
/**
 * 浮动框刷新
 * @param vjiaodianid
 * @param vin
 * @param valarmid
 * @param valarmtypeid
 * @param valarmtime
 * @return
 */
function startsideflash(vjiaodianid,vin,valarmid,valarmtypeid,valarmtime){
	jiaodianid = vjiaodianid;
	sidevin = vin;
	alarmid = valarmid;
	alarmtypeid = valarmtypeid;
	alarmtime = valarmtime;
	//intsideflash = window.setTimeout("sidelayerrealflash()",10000);
}
 
/**
 * 
 * @return
 */
function sidelayerrealflash(){
	clearTimeout(intsideflash);
	if(sidevin != null && sidevin !=""){
		if(jQuery('#popArea').mk_sidelayer('is_show')){
			GPSDwr.returnTipInfo(sidevin,returnsidelayerFlash_callback);
		}else{
			clearTimeout(intsideflash);
		}
	}
	else{
		clearTimeout(intsideflash);
	}
}
function returnsidelayerFlash_callback(data){
	var TerminalViBean = data[0];
	//alert(123);
	testsidelayerHead(TerminalViBean.vehicle_code,TerminalViBean.VEHICLE_LN,TerminalViBean.VEHICLE_VIN,TerminalViBean.LONGITUDE,TerminalViBean.LATITUDE,TerminalViBean.ROUTE_ID,jiaodianid,
		    		TerminalViBean.color,TerminalViBean.VEH_EXT_INFO,TerminalViBean.STAT_INFO,alarmid,alarmtypeid,alarmtime);
	startsideflash(jiaodianid,TerminalViBean.VEHICLE_VIN,alarmid,alarmtypeid,alarmtime);
}
/**
* 刷新浮动框按钮交点
*/
function change_side_images(target) {
	jQuery('.mk-sidelayer-tools').find('li').removeClass('selected');
	jQuery(target).addClass('selected');
}
/**
* 过滤空字符和空
*/
function nullToZore(str){
	if(str == null || str == "" ||  str == "undefined" || str == " "||str =="FFFF"){
		return 0;
	}
	else{
		return str;
	}
}
/**
* 时间格式截取
*/
function timeoptval(time){
	if(time != null && time.trim() != "" && time.trim() != "undefined"){
		return time.substring(0, time.length-2);
	}
	else{
		return "0";
	}
}

/**
* 定时刷新车辆POP-begin,add by suyingtao-20121107
*/
var car_pop_vin = "";
var timerID_car_pop = "";
//取消定时器
function cancelCarPopTime(){
	window.clearTimeout(timerID_car_pop);
	timerID_car_pop = "";
}
function openCarPopTime(){
	cancelCarPopTime();
	timerID_car_pop = setInterval("refreshCarPOP()",10000);
}
function refreshCarPOP(){
	if(!isNull(car_pop_vin)){
		GPSDwr.returnTipInfo(car_pop_vin,returnTipInfo_callback);
	}
}
jQuery(function() {
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
		    	if(test.indexOf("Video")==-1) {
		    		iframeobj.src ="";
		    	}
		    	if(test.indexOf("Video")!=-1) {
					if(test.indexOf("RePlayVideo")==-1){
						parent.frames["iframeshowArea"].window.allstop();	
					}
		    	}
	    	}
  		}
     // reload_callback: function() {alert('reload');}
      
    });
});
/**
* 定时刷新车辆POP-end
*/
</script>