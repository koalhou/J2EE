<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title>终端管理&nbsp;|&nbsp;终端参数设置</title>
<style type="text/css">
.searchBox3{ background: #f5f5f5; border: 1px solid #c3c3c3; float: left; line-height: 32px; margin: 5px;  width:230px; height:35px;}
.searchPlan {
	float: left;
	width: 260px;
}
.mapsMask{ background: #fff; position: relative; top: 20px; display: block; z-index: 1000; height: 20px; width: 150px;}
a.btnbuleMap{ background: url(../images/btn_blue.gif) no-repeat left top; color: #fff; display: block; height: 28px; line-height: 28px; text-align: center; 
width: 76px; }
.searchPlanMap{ background: #eee url(../images/tree_tabBg.gif) repeat-x left top;float: left; height: 35px; padding-top: 3px; width: 100%;}
.fanwei{ width:270px; height:0px; overflow:auto;}
.contentParams{
	color: #121212;
	font-size: 14px;
	font-weight: bold;
	line-height: 20px;
	padding-left: 8px;
}
</style>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="terminalParams_validate.jsp"%>
  <%@include file="zTree_func.jsp"%>
  <s:form id="terminalparams_form" action="setTerminalParams" method="post" onsubmit="return false;">
    <s:hidden id="enterpriseId" name="enterpriseId"/>
    <s:hidden id="carIdList" name="carIdList"/>
    <s:hidden id="currentpage" name="currentpage"/>
    <s:hidden id="currentpageSize" name="currentpageSize"/>
    <s:hidden id="currentsortname" name="currentsortname"/>
    <s:hidden id="currentsortorder" name="currentsortorder"/>
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
	    <td id='searchPlanId' width="270" valign="top" class="treeline">
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
								<s:textfield id="enterpriseName" name="enterpriseName" cssClass="input120"/>
				              </td>
				              <td align="center">
				                <a href="#" class="btnbuleMap" onclick="searchTree()">企业查询</a>
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
				    
				    <tr>
				      <td>
						<div class="searchPlanMap">
						    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						      <tr>
						        <td width="130" align="right">
								  <s:textfield id="vehicleLnQuery" name="vehicleLnQuery" cssClass="input120"/>
						        </td>
						        <td align="center">
						          <a href="#" class="btnbuleMap" onclick="searchVehicleList()">车牌查询</a>
						        </td>
						        <td align="center" style="width:30px">
						        </td>
						      </tr>
						    </table>
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
          <div class="searchPlanMap">
          	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="15%" class="contentParams">
		        	终端参数设置
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
		  <div style="overflow:auto;position:relative" id="rightDiv">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          		  <tr>
          		    <td>
          		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                <tr>
		                  <td height="36" valign="top" background="../images/tree_tabBg.gif">
		                    <div class="toolbar">
				              <div class="contentTil">通信参数
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
				          <td width="15%" align="right">终端心跳发送时间间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="keepAliveTime" name="xcTerminalParamsSet.keepAliveTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">TCP消息应答超时时间(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="tcpOverTime" name="xcTerminalParamsSet.tcpOverTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">TCP消息重传次数：</td>
				          <td width="18%" align="left">
				            <s:textfield id="tcpRetransTime" name="xcTerminalParamsSet.tcpRetransTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">UDP消息应答超时时间(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="udpOverTime" name="xcTerminalParamsSet.udpOverTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">UDP消息重传次数：</td>
				          <td width="18%" align="left">
				            <s:textfield id="udpRetransTime" name="xcTerminalParamsSet.udpRetransTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">主服务器APN：</td>
				          <td width="18%" align="left">
				            <s:textfield id="mainApn" name="xcTerminalParamsSet.mainApn" maxlength="32" cssStyle="ime-mode:disabled" />
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">主服务器拨号用户名：</td>
				          <td width="18%" align="left">
				            <s:textfield id="mainUser" name="xcTerminalParamsSet.mainUser" maxlength="32" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">主服务器拨号密码：</td>
				          <td width="18%" align="left">
				            <s:textfield id="mainPass" name="xcTerminalParamsSet.mainPass" maxlength="32" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">主服务器地址：</td>
				          <td width="18%" align="left">
				            <s:textfield id="mainIp" name="xcTerminalParamsSet.mainIp" maxlength="32" cssStyle="ime-mode:disabled"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">备份服务器APN：</td>
				          <td width="18%" align="left">
				            <s:textfield id="standbyApn" name="xcTerminalParamsSet.standbyApn" maxlength="32" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">备份服务器拨号用户名：</td>
				          <td width="18%" align="left">
				            <s:textfield id="standbyUser" name="xcTerminalParamsSet.standbyUser" maxlength="32" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">备份服务器拨号密码：</td>
				          <td width="18%" align="left">
				            <s:textfield id="standbyPass" name="xcTerminalParamsSet.standbyPass" maxlength="32" cssStyle="ime-mode:disabled"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">备份服务器地址：</td>
				          <td width="18%" align="left">
				            <s:textfield id="standbyIp" name="xcTerminalParamsSet.standbyIp" maxlength="32" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">服务器TCP端口：</td>
				          <td width="18%" align="left">
				            <s:textfield id="tcpPort" name="xcTerminalParamsSet.tcpPort" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">服务器UDP端口：</td>
				          <td width="18%" align="left">
				            <s:textfield id="udpPort" name="xcTerminalParamsSet.udpPort" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
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
				              <div class="contentTil">位置汇报参数
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
				          <td width="15%" align="right">位置汇报策略：</td>
				          <td width="18%" align="left">
				            <s:select id="positionUpType" 
                                      name="xcTerminalParamsSet.positionUpType" 
                                      list="#{0:'定时汇报',1:'定距汇报',2:'定时和定距汇报'}" 
                                      headerKey="" 
                                      headerValue="%{getText('please.select')}"
                                      cssStyle="width:131px">
				            </s:select>
				          </td>
				          <td width="15%" align="right">位置汇报方案：</td>
				          <td width="18%" align="left">
				            <s:select id="positionUpSchema" 
                                      name="xcTerminalParamsSet.positionUpSchema" 
                                      list="#{0:'ACC状态',1:'登录和ACC状态'}" 
                                      headerKey="" 
                                      headerValue="%{getText('please.select')}"
                                      cssStyle="width:131px">
				            </s:select>
				          </td>
				          <td width="15%" align="right">驾驶员未登录汇报时间间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="driverOverDateTime" name="xcTerminalParamsSet.driverOverDateTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">休眠时汇报时间间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="sleepDateTime" name="xcTerminalParamsSet.sleepDateTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">紧急报警时汇报时间间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="sosTime" name="xcTerminalParamsSet.sosTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">缺省汇报时间间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="defaultDateTime" name="xcTerminalParamsSet.defaultDateTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">缺省汇报距离间隔(m)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="defaultSpaceTime" name="xcTerminalParamsSet.defaultSpaceTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">驾驶员未登录汇报距离间隔(m)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="driverOverSpaceTime" name="xcTerminalParamsSet.driverOverSpaceTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">休眠时汇报距离间隔(m)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="sleepSpaceTime" name="xcTerminalParamsSet.sleepSpaceTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">紧急报警时汇报距离间隔(m)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="sosSpaceTime" name="xcTerminalParamsSet.sosSpaceTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">拐点补传角度：</td>
				          <td width="18%" align="left">
				            <s:textfield id="makeUpAngle" name="xcTerminalParamsSet.makeUpAngle" maxlength="3" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right"></td>
				          <td width="19%" align="left">
				          </td>
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
				              <div class="contentTil">告警参数
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
				        <tr>
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
				        <tr>
				          <td width="15%" align="right" valign="top">
				            <s:checkbox id="alarmShootSave" 
				                        name="xcTerminalParamsSet.alarmShootSave" 
				                        fieldValue="true" 
				                        onclick="changeAlarmShootSaveStatus();"
				                        title="选中开启设置报警存储标志"/>报警存储标志：</td>
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
				        </tr>
				        <tr>
				          <td width="15%" align="right">最高速度(km/h)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="topSpeed" name="xcTerminalParamsSet.topSpeed" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">超速持续时间(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="overspeedTime" name="xcTerminalParamsSet.overspeedTime" maxlength="9" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">监听电话号码：</td>
				          <td width="18%" align="left">
				            <s:textfield id="listenPhone" name="xcTerminalParamsSet.listenPhone" maxlength="32" cssStyle="ime-mode:disabled"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">超速报警预警差值(1/10Km/h)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="overspeedAlarmDifference" name="xcTerminalParamsSet.overspeedAlarmDifference" maxlength="5" cssStyle="ime-mode:disabled"/>
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
				          <td width="15%" align="right">特征系数：</td>
				          <td width="19%" align="left">
				            <s:textfield id="characteristicOefficient" name="xcTerminalParamsSet.characteristicOefficient" maxlength="5" cssStyle="ime-mode:disabled"/>
				            <s:checkbox id="setcharacteristicOefficient" 
				            			name="setcharacteristicOefficient" 
				                        fieldValue="true" 
				                        title="批量设置"/>批量设置
				          </td>
				          <td width="15%" align="right">车轮每转脉冲数：</td>
				          <td width="19%" align="left">
				            <s:textfield id="wheelPulseCount" name="xcTerminalParamsSet.wheelPulseCount" maxlength="5" cssStyle="ime-mode:disabled"/>
				            <s:checkbox id="setwheelPulseCount" 
				            			name="setwheelPulseCount" 
				                        fieldValue="true" 
				                        title="批量设置"/>批量设置
				          </td>
				          <td width="12%" align="right">油箱容量(L)：</td>
				          <td width="19%" align="left">
				            <s:textfield id="fuelCapacity" name="xcTerminalParamsSet.fuelCapacity" maxlength="5" cssStyle="ime-mode:disabled"/>
				            <s:checkbox id="setfuelCapacity" 
				            			name="setfuelCapacity" 
				                        fieldValue="true" 
				                        title="批量设置"/>批量设置
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">车辆里程表读数(1/10km)：</td>
				          <td width="19%" align="left">
				            <s:textfield id="odometer" name="xcTerminalParamsSet.odometer" maxlength="9" cssStyle="ime-mode:disabled"/>
				            <s:checkbox id="setodometer" 
				            			name="setodometer" 
				                        fieldValue="true" 
				                        title="批量设置"/>批量设置
				          </td>
				          <td width="15%" align="right">车牌号：</td>
				          <td width="19%" align="left">
				            <s:textfield id="vehicleLn" name="xcTerminalParamsSet.vehicleLn" maxlength="10"/>
				            <s:checkbox id="setvehicleLn" 
				            			name="setvehicleLn" 
				                        fieldValue="true" 
				                        title="批量设置"/>批量设置
				          </td>
				          <td width="15%" align="right">核载人数下发：</td>
				          <td width="18%" align="left">
				            <s:textfield id="standardCnt" name="xcTerminalParamsSet.standardCnt" maxlength="3" cssStyle="ime-mode:disabled"/>
				            <s:checkbox id="setstandardCnt" 
				            			name="setstandardCnt" 
				                        fieldValue="true" 
				                        title="批量设置"/>批量设置				            
				          </td>				          
				        </tr>
				        <tr>
				          <td width="15%" align="right">速度来源设置：</td>
				          <td width="19%" align="left">
				            <s:select id="speedSourceSetting" 
                                      name="xcTerminalParamsSet.speedSourceSetting" 
                                      list="#{0:'VSS',1:'GPS',2:'VSS AND GPS'}" 
                                      headerKey="" 
                                      headerValue="%{getText('please.select')}"
                                      cssStyle="width:131px">
				            </s:select>
				          </td>
				          <td width="15%" align="right">调度信息语音输出通道：</td>
				          <td width="19%" align="left">
				            <s:select id="voiceOutputControlType0" 
                                      name="xcTerminalParamsSet.voiceOutputControlType0" 
                                      list="#{0:'终端设备喇叭',1:'车载音响'}" 
                                      headerKey="" 
                                      headerValue="%{getText('please.select')}"
                                      cssStyle="width:131px">
				            </s:select>
				          </td>
				          <td width="12%" align="right">超速提醒语音输出通道：</td>
				          <td width="19%" align="left">
				            <s:select id="voiceOutputControlType1" 
                                      name="xcTerminalParamsSet.voiceOutputControlType1" 
                                      list="#{0:'终端设备喇叭',1:'车载音响'}" 
                                      headerKey="" 
                                      headerValue="%{getText('please.select')}"
                                      cssStyle="width:131px">
				            </s:select>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">疲劳驾驶提醒语音输出通道：</td>
				          <td width="19%" align="left">
				            <s:select id="voiceOutputControlType2" 
                                      name="xcTerminalParamsSet.voiceOutputControlType2" 
                                      list="#{0:'终端设备喇叭',1:'车载音响'}" 
                                      headerKey="" 
                                      headerValue="%{getText('please.select')}"
                                      cssStyle="width:131px">
				            </s:select>
				          </td>
				          <td width="15%" align="right">其他告警提醒语音输出通道：</td>
				          <td width="19%" align="left">
				            <s:select id="voiceOutputControlType3" 
                                      name="xcTerminalParamsSet.voiceOutputControlType3" 
                                      list="#{0:'终端设备喇叭',1:'车载音响'}" 
                                      headerKey="" 
                                      headerValue="%{getText('please.select')}"
                                      cssStyle="width:131px">
				            </s:select>
				          </td>
				          <td width="12%" align="right">自动报站语音输出通道：</td>
				          <td width="19%" align="left">
				            <s:select id="voiceOutputControlType4" 
                                      name="xcTerminalParamsSet.voiceOutputControlType4" 
                                      list="#{0:'终端设备喇叭',1:'车载音响'}" 
                                      headerKey="" 
                                      headerValue="%{getText('please.select')}"
                                      cssStyle="width:131px">
				            </s:select>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">学生上下车提醒语音输出通道：</td>
				          <td width="19%" align="left">
				            <s:select id="voiceOutputControlType5" 
                                      name="xcTerminalParamsSet.voiceOutputControlType5" 
                                      list="#{0:'终端设备喇叭',1:'车载音响'}" 
                                      headerKey="" 
                                      headerValue="%{getText('please.select')}"
                                      cssStyle="width:131px">
				            </s:select>
				          </td>
				          <td width="12%" align="right">车牌颜色：</td>
				          <td width="19%" align="left">
				            <s:select id="vehicleLnColor" 
                                      name="xcTerminalParamsSet.vehicleLnColor" 
                                      list="#{0:'未定',1:'蓝色',2:'黄色',3:'黑色',4:'白色',9:'其他'}" 
                                      headerKey="" 
                                      headerValue="%{getText('please.select')}"
                                      cssStyle="width:131px">
				            </s:select>
				          </td>				          
				          <td width="12%" align="right"></td>
				          <td width="19%" align="left">
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right" valign="top">
				            <s:checkbox id="terminalOuterDeviceFlag" 
				                        name="xcTerminalParamsSet.terminalOuterDeviceFlag" 
				                        fieldValue="true" 
				                        onclick="changeTerminalOuterDeviceStatus();"
				                        title="选中开启设置终端外设安装配置"/>终端外设安装配置：</td>
				          <td width="18%" align="left" colspan="5">
				            <table border="0" cellspacing="5px" cellpadding="0px">
				          	  <tr>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit1" fieldValue="true" title="选中表示已装，否则未装"/>已装总线仪表</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit2" fieldValue="true" title="选中表示已装，否则未装"/>已装空调</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit3" fieldValue="true" title="选中表示已装，否则未装"/>已装中门</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit4" fieldValue="true" title="选中表示已装，否则未装"/>已装后门</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit5" fieldValue="true" title="选中表示已装，否则未装"/>已装加热器</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit6" fieldValue="true" title="选中表示已装，否则未装"/>已装电磁离合风扇</td>
				          	    </tr>
				          	  <tr>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit7" fieldValue="true" title="选中表示已装，否则未装"/>已装发动机CAN</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit8" fieldValue="true" title="选中表示已装，否则未装"/>已装摄像头1</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit9" fieldValue="true" title="选中表示已装，否则未装"/>已装摄像头2</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit10" fieldValue="true" title="选中表示已装，否则未装"/>已装摄像头3</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit11" fieldValue="true" title="选中表示已装，否则未装"/>已装摄像头4</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit12" fieldValue="true" title="选中表示已装，否则未装"/>已装摄像头5</td>
				          	  </tr>
				          	  <tr>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit13" fieldValue="true" title="选中表示已装，否则未装"/>已装摄像头6</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit14" fieldValue="true" title="选中表示已装，否则未装"/>已装摄像头7</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit15" fieldValue="true" title="选中表示已装，否则未装"/>已装摄像头8</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit30" fieldValue="true" title="选中表示已装，否则未装"/>已装DVR</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit31" fieldValue="true" title="选中表示已装，否则未装"/>已装刷卡器</td>
				          	    <td valign="top"><s:checkbox id="terminalOuterDeviceBit" name="xcTerminalParamsSet.terminalOuterDevice.bit32" fieldValue="true" title="选中表示已装，否则未装"/>已装司机助手</td>
				          	  </tr>
				          	</table>
				          </td>
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
				              <div class="contentTil">拍照控制参数
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
				                        title="选中开启定距拍照控制"/>定距拍照控制：</td>
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
				  
				  <tr>
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
          		  <tr>
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
          		</table>
          </div>
          <div>
          	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="70%" height="40px">
		        </td>
		        <td>
		          <s:if test="#session.perUrlList.contains('111_0_5_4_1')">
		          <a href="#" onclick="queryParams();" class="buttontwo">查询</a>
		          </s:if>
		        </td>
		        <td>
		          <a href="#" onclick="refreshParams();" class="buttontwo">刷新</a>
		        </td>
		        <td>
		          <s:if test="#session.perUrlList.contains('111_0_5_4_2')">
		          <a href="#" onclick="submitForm();" class="buttontwo">设置</a>
		          </s:if>
		        </td>
		      </tr>
            </table>
          </div>
	    </td>
      </tr>
    </table>
  </s:form>
  <%@include file="../common/copyright.jsp"%>
</body>
</html>