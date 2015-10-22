<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<%@include file="zTree_func_css.jsp"%>
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
	<style type="text/css">
	.searchBox3{ background: #f5f5f5; border: 1px solid #c3c3c3; float: left; line-height: 32px; margin: 5px;  width:230px; height:35px;}
	.searchPlan {
		float: left;
		width: 260px;
	}
	.mapsMask{ background: #fff; position: relative; top: 20px; display: block; z-index: 1000; height: 20px; width: 150px;}
	a.btnbuleMap{ background: url(../images/qipaoimages/btn_blue.gif) no-repeat left top; color: #fff; display: block; height: 28px; line-height: 28px; text-align: center; 
	width: 76px; }
	.searchPlanMap{ background: #eee url(../images/tree_tabBg.gif) repeat-x left top;float: left; height: 35px; padding-top: 3px; width: 100%;}
	.fanwei{ width:260px; height:0px; overflow:auto;}
	.contentParams{
		color: #121212;
		font-size: 14px;
		font-weight: bold;
		line-height: 20px;
		padding-left: 8px;
	}
	</style>
	<script>
		
	</script>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
<%-- 	<s:textfield id="isPaizhao" name="isPaizhao"></s:textfield> --%>
	<s:hidden id="isPaizhao" name="isPaizhao"></s:hidden>
	<div id="content">  
	  <s:form id="terminalparams_form" action="setTerminalParams" method="post" onsubmit="return false;">
		<s:hidden id="isQueryLater" name="isQueryLater" />
	    <s:hidden id="enterpriseId" name="enterpriseId"/>
	    <s:hidden id="carIdList" name="carIdList"/>
	    <s:hidden id="currentpage" name="currentpage"/>
	    <s:hidden id="currentpageSize" name="currentpageSize"/>
	    <s:hidden id="currentsortname" name="currentsortname"/>
	    <s:hidden id="currentsortorder" name="currentsortorder"/>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
		    <td id='searchPlanId' width="260" valign="top" class="treeline">
		      <div id="leftInfoDiv"  class="searchPlan">
		        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td align="center" valign="top">
				      <table border="0" align="left" cellpadding="0"	cellspacing="0">
					    <tr>
					      <td>
							<div class="searchPlanMap">
					          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					            <tr>
					              <td width="130" align="right">
									<s:textfield id="vehicleLn" name="vehicleLn" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchVehicleList();}"/>
					              </td>
					              <td align="center">
					                <a href="#" class="btnbuleMap" onclick="searchVehicleList()">车辆查询</a>
					              </td>
					              <td align="center">
					                <div class="searchHide">
					                  <a href="#" onClick="LeftScreen() ;return false;" class="btnHide"></a>
					                </div>
					              </td>
					            </tr>
					          </table>
					        </div>
						  </td>
					    </tr>
					    <tr>
					      <td align="center" valign="top">
					      	<div>
	                          <div>
	                            <ul id="treeDemo" class="ztree"></ul>
	                          </div>
	                        </div>
				            <div class="fanwei">
						    </div>
						  </td>
					    </tr>
				      </table>
	                </td>
	              </tr>
	              <tr>
		            <td class="reportInline" align="left">
					  <table id="carList" width="100%"  cellspacing="0" style="Text-align:center"></table>
		            </td>
	              </tr>
	            </table>
	          </div>
	        </td>
	        
	        <td id="leftTabtd" valign="top" bgcolor="#E9E9E9" width="24" style="display:none">
		     <table id="leftTab" width="100%" height="30" border="0" cellpadding="0" cellspacing="0" style="display:none">
	           <tr>
	             <td>
			     <div class="searchHide2"><a href="#" class="btnTreeVisible" id="Image2" onClick="midScreen() ;return false;"></a></div>
			     </td>
	           </tr>
	        </table>
	        </td>
	      
	        <td valign="top" >
	          <div class="toolbar">
	          	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td width="15%" class="contentParams">
			        	业务参数设置
			        </td>
			        <td width="75%">
			        	<div id="message">
			                <s:actionerror theme="mat" />
			                <s:fielderror theme="mat"/>
			                <s:actionmessage theme="mat"/>
	              		</div>
			        </td>
			        <td>
			          <a href="#" onclick="clearPage();" class="buttontwo">清空页面</a>
			        </td>
			      </tr>
	            </table>
			  </div>
			  <div style="overflow:auto;clear:both;" id="rightDiv">
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
					
					  <tr style="display:none;">
	          		    <td>
	          		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                <tr>
			                  <td height="36" valign="top" background="../images/tree_tabBg.gif">
			                    <div class="toolbar">
					              <div class="contentTil" style="width:50%">告警拍照参数
					              </div>
					            </div>
			                  </td>
			                </tr>
	                      </table>
	          		    </td>
	          		  </tr>
	          		  
	          		  
	          		  <tr style="display:none;">
	          		    <td>
	          		      <table width="100%" border="0" cellspacing="5px" cellpadding="0px">
	          		        <tr style="display:none;">
					          <td width="15%" align="right" valign="top">
					            <s:checkbox id="alarmShieldFlag" 
					                        name="xcTerminalParamsSet.alarmShieldFlag" 
					                        fieldValue="true" 
					                        onclick="changAlarmShieldStatus();"
					                        title="选中开启设置报警屏蔽字"/>报警屏蔽字：
					          </td>
					          <td width="18%" align="left" colspan="5">
					          	<table border="0" cellspacing="5px" cellpadding="0px">
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit1" fieldValue="true" title="选中表示该报警开启"/>开启紧急报警告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit2" fieldValue="true" title="选中表示该报警开启"/>开启超速报警告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit3" fieldValue="true" title="选中表示该报警开启"/>开启疲劳驾驶告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit4" fieldValue="true" title="选中表示该报警开启"/>开启预警告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit5" fieldValue="true" title="选中表示该报警开启"/>开启GNSS模块故障告警</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit6" fieldValue="true" title="选中表示该报警开启"/>开启GNSS天线未接/剪断告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit7" fieldValue="true" title="选中表示该报警开启"/>开启GNSS天线短路告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit8" fieldValue="true" title="选中表示该报警开启"/>开启终端主电源欠压告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit9" fieldValue="true" title="选中表示该报警开启"/>开启终端主电源掉电告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit10" fieldValue="true" title="选中表示该报警开启"/>开启终端LCD/显示器故障告警</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit11" fieldValue="true" title="选中表示该报警开启"/>开启TTS模块故障告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit12" fieldValue="true" title="选中表示该报警开启"/>开启摄像头故障告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit19" fieldValue="true" title="选中表示该报警开启"/>开启当天累计驾驶超时告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit20" fieldValue="true" title="选中表示该报警开启"/>开启超时停车告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit21" fieldValue="true" title="选中表示该报警开启"/>开启进出区域告警</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit22" fieldValue="true" title="选中表示该报警开启"/>开启进出路线告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit23" fieldValue="true" title="选中表示该报警开启"/>开启路线行驶时间不足/过长告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit24" fieldValue="true" title="选中表示该报警开启"/>开启路线偏离报警告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit25" fieldValue="true" title="选中表示该报警开启"/>开启车辆VSS故障告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit26" fieldValue="true" title="选中表示该报警开启"/>开启车辆油量故障告警</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit27" fieldValue="true" title="选中表示该报警开启"/>开启车辆被盗告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit28" fieldValue="true" title="选中表示该报警开启"/>开启车辆非法点火告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit29" fieldValue="true" title="选中表示该报警开启"/>开启车辆非法移位告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit30" fieldValue="true" title="选中表示该报警开启"/>开启碰撞侧翻告警</td>
					          	    <td valign="top"><s:checkbox id="alarmShieldBit" name="xcTerminalParamsSet.alarmShield.bit31" fieldValue="true" title="选中表示该报警开启"/>开启非法开门报警告警</td>
					          	  </tr>
					          	</table>
					          </td>
					        </tr>
					        <tr style="display:block;">
					          <td width="15%" align="right" valign="top">
					            <s:checkbox id="alarmShootFlag" 
					                        name="xcTerminalParamsSet.alarmShootFlag" 
					                        fieldValue="true" 
					                        onclick="changeAlarmShootStatus();"
					                        title="选中开启设置报警拍摄开关"/>报警拍摄开关：
					          </td>
					          <td width="18%" align="left" colspan="5">
					            <table border="0" cellspacing="5px" cellpadding="0px">
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit1" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启紧急报警告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit2" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启超速报警告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit3" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启疲劳驾驶告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit4" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启预警告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit5" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启GNSS模块故障告警触发拍照</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit6" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启GNSS天线未接/剪断告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit7" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启GNSS天线短路告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit8" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启终端主电源欠压告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit9" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启终端主电源掉电告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit10" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启终端LCD/显示器故障告警触发拍照</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit11" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启TTS模块故障告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit12" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启摄像头故障告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit19" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启当天累计驾驶超时告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit20" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启超时停车告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit21" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启进出区域告警触发拍照</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit22" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启进出路线告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit23" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启路线行驶时间不足/过长告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit24" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启路线偏离报警告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit25" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启车辆VSS故障告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit26" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启车辆油量故障告警触发拍照</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit27" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启车辆被盗告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit28" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启车辆非法点火告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit29" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启车辆非法移位告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit30" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启碰撞侧翻告警触发拍照</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSwitchBit" name="xcTerminalParamsSet.alarmShootSwitch.bit31" fieldValue="true" title="选中表示报警时摄像头拍摄"/>开启非法开门报警告警触发拍照</td>
					          	  </tr>
					          	</table>
					          </td>
					        </tr>
					        <tr style="display:none;">
					          <td width="15%" align="right" valign="top">
					            <s:checkbox id="alarmShootSave" 
					                        name="xcTerminalParamsSet.alarmShootSave" 
					                        fieldValue="true" 
					                        onclick="changeAlarmShootSaveStatus();"
					                        title="选中开启设置报警存储标志"/>报警存储标志：
					          </td>
					          <td width="18%" align="left" colspan="5">
					            <table border="0" cellspacing="5px" cellpadding="0px">
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit1" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储紧急报警告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit2" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储超速报警告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit3" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储疲劳驾驶告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit4" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储预警告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit5" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储GNSS模块故障告警触发拍摄的照片</td>
					          	    </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit6" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储GNSS天线未接/剪断告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit7" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储GNSS天线短路告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit8" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储终端主电源欠压告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit9" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储终端主电源掉电告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit10" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储终端LCD/显示器故障告警触发拍摄的照片</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit11" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储TTS模块故障告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit12" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储摄像头故障告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit19" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储当天累计驾驶超时告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit20" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储超时停车告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit21" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储进出区域告警触发拍摄的照片</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit22" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储进出路线告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit23" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储路线行驶时间不足/过长告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit24" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储路线偏离报警告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit25" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储车辆VSS故障告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit26" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储车辆油量故障告警触发拍摄的照片</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit27" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储车辆被盗告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit28" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储车辆非法点火告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit29" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储车辆非法移位告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit30" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储碰撞侧翻告警触发拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="alarmShootSaveFlagBit" name="xcTerminalParamsSet.alarmShootSaveFlag.bit31" fieldValue="true" title="选中表示报警时拍的照片进行存储，否则实时上传"/>存储非法开门报警告警触发拍摄的照片</td>
					          	  </tr>
					          	</table>
					          </td>
					        </tr>`
					        <tr>
					        	<td colspan="6"></td>
					        </tr>
					        <tr style="display:block;">
					        	<td width="15%" align="right" id="entIds1"></td>
					        	<td width="18%" align="left">
					        	 <input type="hidden" id="laterTimes1Ent" name="laterTimes1Ent"/>
<!-- 					        	 <select id="laterTimes1" name="laterTimes1"> -->
<!-- 					        	 	<option value="8:00">8:00</option> -->
<!-- 					        	 	<option value="9:00">9:00</option> -->
<!-- 					        	 	<option value="10:00">10:00</option> -->
<!-- 					        	 	<option value="11:00">11:00</option> -->
<!-- 					        	 	<option value="12:00">12:00</option> -->
<!-- 					        	 </select> -->
					        	 <input readonly="readonly" 
						 			id="laterTimes1" 
						 			name="laterTimes1"  
							       	class="Wdate" 
							       	type="text" 
							       	size="20"  
							       	onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="display:none;"/>
					        	</td>
					        	<td width="15%" align="right" id="entIds2"></td>
					        	<td width="18%" align="left">
					        		<input type="hidden" id="laterTimes2Ent" name="laterTimes2Ent"/>
<!-- 					        		<select id="laterTimes2" name="laterTimes2"> -->
<!-- 					        	 	<option value="8:00">8:00</option> -->
<!-- 					        	 	<option value="9:00">9:00</option> -->
<!-- 					        	 	<option value="10:00">10:00</option> -->
<!-- 					        	 	<option value="11:00">11:00</option> -->
<!-- 					        	 	<option value="12:00">12:00</option> -->
<!-- 					        	 </select> -->
									<input readonly="readonly" 
							 			id="laterTimes2" 
							 			name="laterTimes2"  
								       	class="Wdate" 
								       	type="text" 
								       	size="20"  
								       	onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="display:none;"/>
					        	</td>
					        	<td></td>
					        	<td></td>
					        </tr>
					      </table>
					    </td>
					  </tr>

					  <tr>
	          		    <td>
	          		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                <tr>
			                  <td height="36" valign="top" background="../images/tree_tabBg.gif">
			                    <div class="toolbar">
					              <div class="contentTil">超速告警参数
					              </div>
					            </div>
			                  </td>
			                </tr>
	                      </table>
	          		    </td>
	          		  </tr>
	          		  <tr>
	          		  	<td>
	          		  		<table width="100%" border="0" cellspacing="5px" cellpadding="0px">
	          		  			<tr>
						          <td width="15%" align="right">最高速度(km/h)：</td>
						          <td width="18%" align="left">
						            <s:textfield id="topSpeed" name="xcTerminalParamsSet.topSpeed" maxlength="9" cssStyle="ime-mode:disabled"/>
						          </td>
						          <td width="15%" align="right">超速持续时间(s)：</td>
						          <td width="18%" align="left">
						            <s:textfield id="overspeedTime" name="xcTerminalParamsSet.overspeedTime" maxlength="9" cssStyle="ime-mode:disabled"/>
						          </td>
						           <td width="15%" align="right">超速报警预警差值(Km/h)：</td>
						          <td width="18%" align="left">
						            <s:textfield id="overspeedAlarmDifference" name="xcTerminalParamsSet.overspeedAlarmDifference" maxlength="5" cssStyle="ime-mode:disabled"/>
						          </td>
						        
						        </tr>
						        <tr style="display:none;">
						          <td width="15%" align="right">监听电话号码：</td>
						          <td width="18%" align="left">
						            <s:textfield id="listenPhone" name="xcTerminalParamsSet.listenPhone" maxlength="32" cssStyle="ime-mode:disabled"/>
						          </td>						         
						          <td width="15%" align="right"></td>
						          <td width="18%" align="left">
						          </td>
						          <td width="15%" align="right"></td>
						          <td width="18%" align="left">
						          </td>
						        </tr>
	          		  		</table>
	          		  	</td>
	          		  </tr>
	          		  <s:if test="#session.perUrlList.contains('111_3_5_11_3')">
					  <tr id="paichao">
	          		    <td>
	          		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                <tr>
			                  <td height="36" valign="top" background="../images/tree_tabBg.gif">
			                    <div class="toolbar">
					              <div class="contentTil">拍照控制参数
					              </div>
					            </div>
			                  </td>
			                </tr>
	                      </table>
	          		    </td>
	          		  </tr>
	          		  
	          		  <tr id="paichaoBody">
	          		    <td>
	          		      <table width="100%" border="0" cellspacing="5px" cellpadding="0px">
					        <tr>
					          <td width="15%" align="right" valign="top">
					            <s:checkbox id="carDoorFlag" 
					                        name="xcTerminalParamsSet.carDoorFlag" 
					                        fieldValue="true" 
					                        onclick="changeCarDoorStatus();"
					                        title="选中开启设置门开关拍照控制"/>门开关拍照控制：
					          </td>
					          <td width="85%" align="left">
					            <table border="0" cellspacing="5px" cellpadding="0px">
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit1" fieldValue="true" title="选中表示开启状态"/>开启门1开主动拍照</td>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit2" fieldValue="true" title="选中表示开启状态"/>开启门1关主动拍照</td>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit3" fieldValue="true" title="选中表示开启状态"/>开启门2开主动拍照</td>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit4" fieldValue="true" title="选中表示开启状态"/>开启门2关主动拍照</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit9" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传门1开拍照拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit10" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传门1关拍照拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit11" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传门2开拍照拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit12" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传门2关拍照拍摄的照片</td>
					          	  </tr>
					          	</table>
					          </td>
					        </tr>
					        <tr>
					          <td width="15%" align="right" valign="top">
					            <s:checkbox id="regularCameraFlag" 
					                        name="xcTerminalParamsSet.regularCameraFlag" 
					                        fieldValue="true" 
					                        onclick="changeregularCameraStatus();"
					                        title="选中开启定时拍照控制"/>定时拍照控制：
					          </td>
					          <td width="85%" align="left">
					            <table border="0" cellspacing="5px" cellpadding="0px">
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit1" fieldValue="true" title="选中表示开启状态"/>开启定时拍照（通道1）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit2" fieldValue="true" title="选中表示开启状态"/>开启定时拍照（通道2）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit3" fieldValue="true" title="选中表示开启状态"/>开启定时拍照（通道3）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit4" fieldValue="true" title="选中表示开启状态"/>开启定时拍照（通道4）</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit5" fieldValue="true" title="选中表示开启状态"/>开启定时拍照（通道5）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit9" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定时拍照拍摄的照片（通道1）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit10" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定时拍照拍摄的照片（通道2）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit11" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定时拍照拍摄的照片（通道3）</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit12" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定时拍照拍摄的照片（通道4）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit13" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定时拍照拍摄的照片（通道5）</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top" colspan="5">定时时间(min)：<s:textfield id="regularTime" name="xcTerminalParamsSet.regularTime" maxlength="5" cssStyle="ime-mode:disabled"/></td>
					          	  </tr>
					          	</table>
					          </td>
					        </tr>
					        <tr>
					          <td width="15%" align="right" valign="top">
					            <s:checkbox id="fixDistanceFlag" 
					                        name="xcTerminalParamsSet.fixDistanceFlag" 
					                        fieldValue="true" 
					                        onclick="changeFixDistanceStatus();"
					                        title="选中开启定距拍照控制"/>定距拍照控制：
					          </td>
					          <td width="85%" align="left">
					            <table border="0" cellspacing="5px" cellpadding="0px">
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit1" fieldValue="true" title="选中表示开启状态"/>开启定距拍照（通道1）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit2" fieldValue="true" title="选中表示开启状态"/>开启定距拍照（通道2）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit3" fieldValue="true" title="选中表示开启状态"/>开启定距拍照（通道3）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit4" fieldValue="true" title="选中表示开启状态"/>开启定距拍照（通道4）</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit5" fieldValue="true" title="选中表示开启状态"/>开启定距拍照（通道5）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit9" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定距拍照拍摄的照片（通道1）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit10" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定距拍照拍摄的照片（通道2）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit11" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定距拍照拍摄的照片（通道3）</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit12" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定距拍照拍摄的照片（通道4）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit13" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定距拍照拍摄的照片（通道5）</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top" colspan="5">定距距离(km)：<s:textfield id="fixDistance" name="xcTerminalParamsSet.fixDistance" maxlength="5" cssStyle="ime-mode:disabled"/></td>
					          	  </tr>
					          	</table>
					          </td>
					        </tr>
					      </table>
					    </td>
					  </tr>
					  </s:if>
					  <s:else>
					  <tr style="display:none;"  id="paichao">
	          		    <td>
	          		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                <tr>
			                  <td height="36" valign="top" background="../images/tree_tabBg.gif">
			                    <div class="toolbar">
					              <div class="contentTil">拍照控制参数
					              </div>
					            </div>
			                  </td>
			                </tr>
	                      </table>
	          		    </td>
	          		  </tr>
					   <tr style="display:none;" id="paichaoBody">
	          		    <td>
	          		      <table width="100%" border="0" cellspacing="5px" cellpadding="0px">
					        <tr>
					          <td width="15%" align="right" valign="top">
					            <s:checkbox id="carDoorFlag" 
					                        name="xcTerminalParamsSet.carDoorFlag" 
					                        fieldValue="true" 
					                        onclick="changeCarDoorStatus();"
					                        title="选中开启设置门开关拍照控制"/>门开关拍照控制：
					          </td>
					          <td width="85%" align="left">
					            <table border="0" cellspacing="5px" cellpadding="0px">
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit1" fieldValue="true" title="选中表示开启状态"/>开启门1开主动拍照</td>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit2" fieldValue="true" title="选中表示开启状态"/>开启门1关主动拍照</td>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit3" fieldValue="true" title="选中表示开启状态"/>开启门2开主动拍照</td>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit4" fieldValue="true" title="选中表示开启状态"/>开启门2关主动拍照</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit9" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传门1开拍照拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit10" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传门1关拍照拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit11" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传门2开拍照拍摄的照片</td>
					          	    <td valign="top"><s:checkbox id="carDoorControlBit" name="xcTerminalParamsSet.carDoorControl.bit12" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传门2关拍照拍摄的照片</td>
					          	  </tr>
					          	</table>
					          </td>
					        </tr>
					        <tr>
					          <td width="15%" align="right" valign="top">
					            <s:checkbox id="regularCameraFlag" 
					                        name="xcTerminalParamsSet.regularCameraFlag" 
					                        fieldValue="true" 
					                        onclick="changeregularCameraStatus();"
					                        title="选中开启定时拍照控制"/>定时拍照控制：
					          </td>
					          <td width="85%" align="left">
					            <table border="0" cellspacing="5px" cellpadding="0px">
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit1" fieldValue="true" title="选中表示开启状态"/>开启定时拍照（通道1）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit2" fieldValue="true" title="选中表示开启状态"/>开启定时拍照（通道2）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit3" fieldValue="true" title="选中表示开启状态"/>开启定时拍照（通道3）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit4" fieldValue="true" title="选中表示开启状态"/>开启定时拍照（通道4）</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit5" fieldValue="true" title="选中表示开启状态"/>开启定时拍照（通道5）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit9" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定时拍照拍摄的照片（通道1）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit10" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定时拍照拍摄的照片（通道2）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit11" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定时拍照拍摄的照片（通道3）</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit12" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定时拍照拍摄的照片（通道4）</td>
					          	    <td valign="top"><s:checkbox id="regularCameraControl" name="xcTerminalParamsSet.regularCameraControl.bit13" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定时拍照拍摄的照片（通道5）</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top" colspan="5">定时时间(s)：<s:textfield id="regularTime" name="xcTerminalParamsSet.regularTime" maxlength="5" cssStyle="ime-mode:disabled"/></td>
					          	  </tr>
					          	</table>
					          </td>
					        </tr>
					        <tr>
					          <td width="15%" align="right" valign="top">
					            <s:checkbox id="fixDistanceFlag" 
					                        name="xcTerminalParamsSet.fixDistanceFlag" 
					                        fieldValue="true" 
					                        onclick="changeFixDistanceStatus();"
					                        title="选中开启定距拍照控制"/>定距拍照控制：
					          </td>
					          <td width="85%" align="left">
					            <table border="0" cellspacing="5px" cellpadding="0px">
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit1" fieldValue="true" title="选中表示开启状态"/>开启定距拍照（通道1）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit2" fieldValue="true" title="选中表示开启状态"/>开启定距拍照（通道2）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit3" fieldValue="true" title="选中表示开启状态"/>开启定距拍照（通道3）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit4" fieldValue="true" title="选中表示开启状态"/>开启定距拍照（通道4）</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit5" fieldValue="true" title="选中表示开启状态"/>开启定距拍照（通道5）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit9" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定距拍照拍摄的照片（通道1）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit10" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定距拍照拍摄的照片（通道2）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit11" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定距拍照拍摄的照片（通道3）</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit12" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定距拍照拍摄的照片（通道4）</td>
					          	    <td valign="top"><s:checkbox id="fixDistanceCameraControlBit" name="xcTerminalParamsSet.fixDistanceCameraControl.bit13" fieldValue="true" title="选中表示上传，未选中表示存储"/>上传定距拍照拍摄的照片（通道5）</td>
					          	  </tr>
					          	  <tr>
					          	    <td valign="top" colspan="5">定距距离(m)：<s:textfield id="fixDistance" name="xcTerminalParamsSet.fixDistance" maxlength="5" cssStyle="ime-mode:disabled"/></td>
					          	  </tr>
					          	</table>
					          </td>
					        </tr>
					      </table>
					    </td>
					  </tr>
					  </s:else>
					  <tr style="display:none;">
	          		    <td>
	          		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                <tr>
			                  <td height="36" valign="top" background="../images/tree_tabBg.gif">
			                    <div class="toolbar">
					              <div class="contentTil">其他参数
					              </div>
					            </div>
			                  </td>
			                </tr>
	                      </table>
	          		    </td>
	          		  </tr>
	          		  <tr style="display:none;">
	          		    <td>
	          		      <table width="100%" border="0" cellspacing="5px" cellpadding="0px">
					        <tr>
					          <td width="15%" align="right" valign="top">学生刷卡自动切换行程控制：
					          </td>
					          <td width="85%" align="left">
					            <s:select id="autoSwitchTrip" 
	                                      name="xcTerminalParamsSet.autoSwitchTrip" 
	                                      list="#{0:'开启',1:'关闭'}" 
	                                      headerKey="" 
	                                      headerValue="%{getText('please.select')}"
	                                      cssStyle="width:131px">
					            </s:select>
					          </td>
					        </tr>
					      </table>
					    </td>
					  </tr>
					  
					  <s:if test="#session.perUrlList.contains('111_3_5_11_1')">
					  <tr>
          		    <td>
          		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                <tr>
		                  <td height="36" valign="top" background="../images/tree_tabBg.gif">
		                    <div class="toolbar">
				              <div class="contentTil">车辆属性参数
				              </div>
				            </div>
		                  </td>
		                </tr>
                      </table>
          		    </td>
          		  </tr>
          		  <tr>
          		    <td>
          		      <table width="100%" border="0" cellspacing="5px" cellpadding="0px">
				        <tr>
				          <td width="15%" align="right">核载人数下发：</td>
				          <td width="18%" align="left">
				            <s:textfield id="standardCnt" name="xcTerminalParamsSet.standardCnt" maxlength="3" cssStyle="ime-mode:disabled"/>
				            <%-- <s:checkbox id="setstandardCnt"
				            			name="setstandardCnt"
				                        fieldValue="true" 
				                        title="批量设置"/>批量设置 --%>
				          </td>
				          <td width="15%" align="right"></td>
				          <td width="19%" align="left">
				          </td>
				          <td width="15%" align="right"></td>
				          <td width="19%" align="left">
				          </td>			          
				        </tr>
				      </table>
				    </td>
				  </tr>
				  </s:if>
	          		</table>
	          </div>
	          <div>
	          	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td width="70%" height="40px">
			        </td>
			        <td>
			          <a href="javascript:void(0);" onclick="queryParams();" class="buttontwo">查询</a>
			        </td>
			        <td>
			          <a href="javascript:void(0);" onclick="refreshParams();" class="buttontwo">刷新</a>
			        </td>
			        <td>
<%-- 			          <s:if test="#session.perUrlList.contains('111_3_5_11_2')"> --%>
			          <a href="#" onclick="submitForm();" class="buttontwo">设置</a>
<!-- 			          <a href="javascript:void(0);" onclick="setLaterData();" class="buttontwo">设置</a> -->
<%-- 			          </s:if> --%>
			        </td>
			      </tr>
	            </table>
	          </div>
		    </td>
	      </tr>
	    </table>
	  </s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="zTree_func.jsp"%>
<%@include file="terminalParams_validate.jsp"%>
</body>
</html>