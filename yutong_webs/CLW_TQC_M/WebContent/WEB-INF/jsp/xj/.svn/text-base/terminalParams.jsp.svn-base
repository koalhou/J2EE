<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title><s:property value="getText('system.monitor.menu')" />&nbsp;|&nbsp;<s:property value="getText('menu.xj.param')" /></title>
<style type="text/css">
  .reportOnline5{ 
    background: #fff; 
    border-bottom: 1px solid #c3c3c3; 
    border-left: 1px solid #c3c3c3; 
    border-right: 1px solid #c3c3c3;
  }
</style>
</head>
<body>
  <%@include file="../common/menu.jsp"%>
  <%@include file="terminalParams_validate.jsp"%>
  <s:form id="terminalParams_form" action="setCurrentTerminalParams" method="post">
  <s:hidden id="terminalId" name="terminalId"/>
  <s:hidden id="vehicleVin" name="xcTerminalAttributeInfo.vehicleVin"/>
  <s:hidden id="simCardNumber" name="xcTerminalAttributeInfo.simCardNumber"/>
  <s:hidden id="currentPage" name="currentPage"/>
                           
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr valign="top">
      <td class="reportOnline5">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>
              <div class="reportTab">
                <ul>
                  <li>
                    <a href="#" id="terminalParamsBrowse" style="cursor:pointer" onclick="changeChoose(this.id)" class="current2">终端参数查询</a>
                  </li>
                  <li>
                    <a href="#" id="terminalParamsSet" style="cursor:pointer" onclick="changeChoose(this.id)" >终端参数设置</a>
                  </li>
                </ul>
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <div id="message">
                <s:actionerror theme="mat" />
                <s:fielderror theme="mat"/>
                <s:actionmessage theme="mat"/>
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td height="36" valign="top" background="../images/tree_tabBg.gif">
		            <div class="toolbar">
		              <div class="contentTil">终端详细信息
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
		          <td width="15%" align="right">车辆VIN：</td>
		          <td width="18%" align="left">
		            <s:property value="xcTerminalAttributeInfo.vehicleVin" />
		          </td>
		          <td width="15%" align="right">终端号：</td>
		          <td width="18%" align="left">
		            <s:property value="xcTerminalAttributeInfo.terminalId" />
		          </td>
		          <td width="15%" align="right">电子ICCID：</td>
		          <td width="18%" align="left">
		            <s:property value="xcTerminalAttributeInfo.simCardNumber" />
		          </td>
		        </tr>
		        <tr>
		          <td width="15%" align="right">终端记录仪版本号：</td>
		          <td width="18%" align="left">
		            <s:property value="xcTerminalAttributeInfo.terminalRecordVersion" />
		          </td>
		          <td width="15%" align="right">终端显示器版本号：</td>
		          <td width="18%" align="left">
		            <s:property value="xcTerminalAttributeInfo.terminalScreenVersion" />
		          </td>
		          <td width="15%" align="right">终端DVR版本号：</td>
		          <td width="18%" align="left">
		            <s:property value="xcTerminalAttributeInfo.terminalDVRVersion" />
		          </td>
		        </tr>
		        <tr>
		          <td width="15%" align="right">终端射频版本号：</td>
		          <td width="18%" align="left">
		            <s:property value="xcTerminalAttributeInfo.terminalRFVersion" />
		          </td>
		          <td width="15%" align="right">终端其他版本号：</td>
		          <td width="18%" align="left">
		            <s:property value="xcTerminalAttributeInfo.terminalOtherVersion" />
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
          	  <div id="queryTerminalParams">
          	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
				          <td width="15%" align="right">终端心跳发送时间间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.keepAliveTime" />
				          </td>
				          <td width="15%" align="right">TCP消息应答超时时间(s)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.tcpOverTime" />
				          </td>
				          <td width="15%" align="right">TCP消息重传次数：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.tcpRetransTime" />
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">UDP消息应答超时时间(s)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.udpOverTime" />
				          </td>
				          <td width="15%" align="right">UDP消息重传次数(s)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.udpRetransTime" />
				          </td>
				          <td width="15%" align="right">位置汇报策略：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.positionUpType" />
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">位置汇报方案：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.positionUpSchema" />
				          </td>
				          <td width="15%" align="right">休眠时汇报时间间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.sleepDateTime" />
				          </td>
				          <td width="15%" align="right">紧急报警时汇报时间间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.sosTime" />
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">缺省时间汇报间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.defaultDateTime" />
				          </td>
				          <td width="15%" align="right">缺省距离汇报间隔(m)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.defaultSpaceTime" />
				          </td>
				          <td width="15%" align="right">驾驶员未登录汇报距离间隔(m)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.driverOverSpaceTime" />
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">休眠时汇报距离间隔(m)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.sleepSpaceTime" />
				          </td>
				          <td width="15%" align="right">紧急报警时汇报距离间隔(m)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.sosSpaceTime" />
				          </td>
				          <td width="15%" align="right">拐点补传角度：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.makeUpAngle" />
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
				              <div class="contentTil">中心平台参数
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
				          <td width="15%" align="right">主服务器APN：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.mainApn" />
				          </td>
				          <td width="15%" align="right">主服务器无线通讯拨号用户名：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.mainUser" />
				          </td>
				          <td width="15%" align="right">主服务器无线通讯拨号密码：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.mainPass" />
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">备份服务器APN：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.standbyApn" />
				          </td>
				          <td width="15%" align="right">备份服务器无线通讯拨号用户名：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.standbyUser" />
				          </td>
				          <td width="15%" align="right">备份服务器无线通讯拨号密码：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.standbyPass" />
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">主服务器地址：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.mainIp" />
				          </td>
				          <td width="15%" align="right">服务器TCP端口：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.tcpPort" />
				          </td>
				          <td width="15%" align="right">服务器UDP端口：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.udpPort" />
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">监控平台电话号码：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.monitorPhone" />
				          </td>
				          <td width="15%" align="right">复位电话号码：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.resetPhone" />
				          </td>
				          <td width="15%" align="right">恢复出厂设置电话号码：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.resetFactory" />
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">监管平台SMS电话号码：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.monitorSmsPhone" />
				          </td>
				          <td width="15%" align="right">接收终端SMS文本报警号码：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.terminalSmsPhone" />
				          </td>
				          <td width="15%" align="right">终端电话接听策略：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.terminalPhoneTactic" />
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">每次最长通话时间(s)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.callTimePer" />
				          </td>
				          <td width="15%" align="right">当月最长通话时间(s)：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.callTimeMonth" />
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
				          <td width="15%" align="right">车辆里程表读数 ：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.odometer" />
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
				              <div class="contentTil">拍照参数
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
				          <td width="15%" align="right">图像/视频质量 ：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.mediaQuality" />
				          </td>
				          <td width="15%" align="right">亮度：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.luminance" />
				          </td>
				          <td width="15%" align="right">对比度：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.contrast" />
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">饱和度 ：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.saturation" />
				          </td>
				          <td width="15%" align="right">色度：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.chroma" />
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
				          <td width="15%" align="right">报警屏蔽字 ：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.alarmShield" />
				          </td>
				          <td width="15%" align="right">报警发送文本SMS开关：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.alarmSmsSwitch" />
				          </td>
				          <td width="15%" align="right">报警拍摄开关：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.alarmShootSwitch" />
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">报警拍摄存储标志 ：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.alarmShootSaveFlag" />
				          </td>
				          <td width="15%" align="right">关键标志：</td>
				          <td width="18%" align="left">
				            <s:property value="xcTerminalParamsInfo.keyFlag" />
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
		                  <td width="80%" height="40px">
		                  </td>
		                  <td>
		                    <s:url id="getCurrentTerminalParams" action="getCurrentTerminalParams">
                              <s:param name="terminalId">${terminalId}</s:param>
                              <s:param name="vehicleVin">${xcTerminalAttributeInfo.vehicleVin}</s:param>
                              <s:param name="simCardNumber">${xcTerminalAttributeInfo.simCardNumber}</s:param>
                            </s:url>
		                    <a href="#" onclick="queryTerminalParams('${getCurrentTerminalParams}')" class="sbutton">获取</a>
		                  </td>
		                  <td>
		                    <s:url id="queryCurrentTerminalParamsById" action="queryCurrentTerminalParamsById">
                              <s:param name="terminalId">${terminalId}</s:param>
                            </s:url>
		                    <a href="#" onclick="goBack('${queryCurrentTerminalParamsById}')" class="sbutton">刷新</a>
		                  </td>
		                  <td>
		                    <a href="#" onclick="goBack('queryCurrentTerminal.shtml')" class="sbutton">取消</a>
		                  </td>
		                </tr>
                      </table>
          		    </td>
          		  </tr>
          		</table>
          	  </div>
          	  
          	  <%-- 终端参数设置DIV --%>
          	  <div id="setTerminalParams">
          	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
				          <td width="15%" align="right">终端心跳发送时间间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="keepAliveTime" name="xcTerminalParamsSet.keepAliveTime"/>
				          </td>
				          <td width="15%" align="right">TCP消息应答超时时间(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="tcpOverTime" name="xcTerminalParamsSet.tcpOverTime"/>
				          </td>
				          <td width="15%" align="right">TCP消息重传次数：</td>
				          <td width="18%" align="left">
				            <s:textfield id="tcpRetransTime" name="xcTerminalParamsSet.tcpRetransTime"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">UDP消息应答超时时间(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="udpOverTime" name="xcTerminalParamsSet.udpOverTime"/>
				          </td>
				          <td width="15%" align="right">UDP消息重传次数(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="udpRetransTime" name="xcTerminalParamsSet.udpRetransTime"/>
				          </td>
				          <td width="15%" align="right">位置汇报策略：</td>
				          <td width="18%" align="left">
				            <s:textfield id="positionUpType" name="xcTerminalParamsSet.positionUpType"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">位置汇报方案：</td>
				          <td width="18%" align="left">
				            <s:textfield id="positionUpSchema" name="xcTerminalParamsSet.positionUpSchema"/>
				          </td>
				          <td width="15%" align="right">休眠时汇报时间间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="sleepDateTime" name="xcTerminalParamsSet.sleepDateTime"/>
				          </td>
				          <td width="15%" align="right">紧急报警时汇报时间间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="sosTime" name="xcTerminalParamsSet.sosTime"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">缺省时间汇报间隔(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="defaultDateTime" name="xcTerminalParamsSet.defaultDateTime"/>
				          </td>
				          <td width="15%" align="right">缺省距离汇报间隔(m)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="defaultSpaceTime" name="xcTerminalParamsSet.defaultSpaceTime"/>
				          </td>
				          <td width="15%" align="right">驾驶员未登录汇报距离间隔(m)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="driverOverSpaceTime" name="xcTerminalParamsSet.driverOverSpaceTime"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">休眠时汇报距离间隔(m)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="sleepSpaceTime" name="xcTerminalParamsSet.sleepSpaceTime"/>
				          </td>
				          <td width="15%" align="right">紧急报警时汇报距离间隔(m)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="sosSpaceTime" name="xcTerminalParamsSet.sosSpaceTime"/>
				          </td>
				          <td width="15%" align="right">拐点补传角度：</td>
				          <td width="18%" align="left">
				            <s:textfield id="makeUpAngle" name="xcTerminalParamsSet.makeUpAngle"/>
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
				              <div class="contentTil">中心平台参数
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
				          <td width="15%" align="right">主服务器APN：</td>
				          <td width="18%" align="left">
				            <s:textfield id="mainApn" name="xcTerminalParamsSet.mainApn" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">主服务器无线通讯拨号用户名：</td>
				          <td width="18%" align="left">
				            <s:textfield id="mainUser" name="xcTerminalParamsSet.mainUser" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">主服务器无线通讯拨号密码：</td>
				          <td width="18%" align="left">
				            <s:textfield id="mainPass" name="xcTerminalParamsSet.mainPass" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">备份服务器APN：</td>
				          <td width="18%" align="left">
				            <s:textfield id="standbyApn" name="xcTerminalParamsSet.standbyApn" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">备份服务器无线通讯拨号用户名：</td>
				          <td width="18%" align="left">
				            <s:textfield id="standbyUser" name="xcTerminalParamsSet.standbyUser" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">备份服务器无线通讯拨号密码：</td>
				          <td width="18%" align="left">
				            <s:textfield id="standbyPass" name="xcTerminalParamsSet.standbyPass" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">主服务器地址：</td>
				          <td width="18%" align="left">
				            <s:textfield id="mainIp" name="xcTerminalParamsSet.mainIp" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">服务器TCP端口：</td>
				          <td width="18%" align="left">
				            <s:textfield id="tcpPort" name="xcTerminalParamsSet.tcpPort"/>
				          </td>
				          <td width="15%" align="right">服务器UDP端口：</td>
				          <td width="18%" align="left">
				            <s:textfield id="udpPort" name="xcTerminalParamsSet.udpPort"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">监控平台电话号码：</td>
				          <td width="18%" align="left">
				            <s:textfield id="monitorPhone" name="xcTerminalParamsSet.monitorPhone" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">复位电话号码：</td>
				          <td width="18%" align="left">
				            <s:textfield id="resetPhone" name="xcTerminalParamsSet.resetPhone" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">恢复出厂设置电话号码：</td>
				          <td width="18%" align="left">
				            <s:textfield id="resetFactory" name="xcTerminalParamsSet.resetFactory" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">监管平台SMS电话号码：</td>
				          <td width="18%" align="left">
				            <s:textfield id="monitorSmsPhone" name="xcTerminalParamsSet.monitorSmsPhone" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">接收终端SMS文本报警号码：</td>
				          <td width="18%" align="left">
				            <s:textfield id="terminalSmsPhone" name="xcTerminalParamsSet.terminalSmsPhone" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">终端电话接听策略：</td>
				          <td width="18%" align="left">
				            <s:textfield id="terminalPhoneTactic" name="xcTerminalParamsSet.terminalPhoneTactic"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">每次最长通话时间(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="callTimePer" name="xcTerminalParamsSet.callTimePer"/>
				          </td>
				          <td width="15%" align="right">当月最长通话时间(s)：</td>
				          <td width="18%" align="left">
				            <s:textfield id="callTimeMonth" name="xcTerminalParamsSet.callTimeMonth"/>
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
				          <td width="15%" align="right">车辆里程表读数 ：</td>
				          <td width="18%" align="left">
				            <s:textfield id="odometer" name="xcTerminalParamsSet.odometer" maxlength="80" cssStyle="ime-mode:disabled"/>
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
				              <div class="contentTil">拍照参数
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
				          <td width="15%" align="right">图像/视频质量 ：</td>
				          <td width="18%" align="left">
				            <s:textfield id="mediaQuality" name="xcTerminalParamsSet.mediaQuality"/>
				          </td>
				          <td width="15%" align="right">亮度：</td>
				          <td width="18%" align="left">
				            <s:textfield id="luminance" name="xcTerminalParamsSet.luminance"/>
				          </td>
				          <td width="15%" align="right">对比度：</td>
				          <td width="18%" align="left">
				            <s:textfield id="contrast" name="xcTerminalParamsSet.contrast"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">饱和度 ：</td>
				          <td width="18%" align="left">
				            <s:textfield id="saturation" name="xcTerminalParamsSet.saturation"/>
				          </td>
				          <td width="15%" align="right">色度：</td>
				          <td width="18%" align="left">
				            <s:textfield id="chroma" name="xcTerminalParamsSet.chroma"/>
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
				          <td width="15%" align="right">报警屏蔽字 ：</td>
				          <td width="18%" align="left">
				            <s:textfield id="alarmShield" name="xcTerminalParamsSet.alarmShield" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">报警发送文本SMS开关：</td>
				          <td width="18%" align="left">
				            <s:textfield id="alarmSmsSwitch" name="xcTerminalParamsSet.alarmSmsSwitch" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">报警拍摄开关：</td>
				          <td width="18%" align="left">
				            <s:textfield id="alarmShootSwitch" name="xcTerminalParamsSet.alarmShootSwitch" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">报警拍摄存储标志 ：</td>
				          <td width="18%" align="left">
				            <s:textfield id="alarmShootSaveFlag" name="xcTerminalParamsSet.alarmShootSaveFlag" maxlength="80" cssStyle="ime-mode:disabled"/>
				          </td>
				          <td width="15%" align="right">关键标志：</td>
				          <td width="18%" align="left">
				            <s:textfield id="keyFlag" name="xcTerminalParamsSet.keyFlag" maxlength="80" cssStyle="ime-mode:disabled"/>
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
		                  <td width="85%" height="40px">
		                  </td>
		                  <td>
		                    <a href="#" onclick="submitForm();" class="sbutton">设置</a>
		                  </td>
		                  <td>
		                    <a href="#" onclick="goBack('queryCurrentTerminal.shtml')" class="sbutton">取消</a>
		                  </td>
		                </tr>
                      </table>
          		    </td>
          		  </tr>
          		</table>
          	  </div>
          	</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </s:form>
  
  <%@include file="../common/copyright.jsp"%>
</body>
</html>