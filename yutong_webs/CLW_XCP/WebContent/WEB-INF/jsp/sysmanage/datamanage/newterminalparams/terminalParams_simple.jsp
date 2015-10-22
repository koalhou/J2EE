<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
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
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">  
	  <s:form id="terminalparams_form" action="simpleSetTerminalParams" method="post" onsubmit="return false;">
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
									<s:textfield id="vehicleLn" name="vehicleLn" cssClass="input120" maxlength="7" onkeypress="if(event.keyCode==13){searchVehicleList();}"/>
					              </td>
					              <td align="center">
					                <a href="#" class="btnbuleMap" onclick="searchVehicleList()">车牌查询</a>
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
			          <a href="<s:url value='/zdnew/init.shtml' />" class="buttontwo">完整参数</a>
			        </td>
			        <td>
			          <a href="#" onclick="clearPage();" class="buttontwo">清空页面</a>
			        </td>
			      </tr>
	            </table>
			  </div>
			  <div style="overflow:auto;clear:both;" id="rightDiv">
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
	          		    <td>
	          		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                <tr>
			                  <td height="36" valign="top" background="../images/tree_tabBg.gif">
			                    <div class="toolbar">
					              <div class="contentTil" style="width:50%">告警参数
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
					            <s:textfield id="topSpeed" name="xcTerminalParamsSet.topSpeed" maxlength="9" cssStyle="ime-mode:disabled"/>(20-180) 
					          </td>
					          <td width="15%" align="right">超速持续时间(s)：</td>
					          <td width="18%" align="left">
					            <s:textfield id="overspeedTime" name="xcTerminalParamsSet.overspeedTime" maxlength="9" cssStyle="ime-mode:disabled"/>(1-600) 
					          </td>
					          <td width="15%" align="right">监听电话号码：</td>
					          <td width="18%" align="left">
					            <s:textfield id="listenPhone" name="xcTerminalParamsSet.listenPhone" maxlength="32" cssStyle="ime-mode:disabled"/>
					          </td>
					        </tr>
					        <tr>
					          <td width="15%" align="right">超速报警预警差值(Km/h)：</td>
					          <td width="18%" align="left">
					            <s:textfield id="overspeedAlarmDifference" name="xcTerminalParamsSet.overspeedAlarmDifference" maxlength="5" cssStyle="ime-mode:disabled"/> (5-20)
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
					          	    <td valign="top" colspan="5">定距距离(km)：<s:textfield id="fixDistance" name="xcTerminalParamsSet.fixDistance" maxlength="5" cssStyle="ime-mode:disabled"/>(>=5)</td>
					          	  </tr>
					          	</table>
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
			          <a href="#" onclick="queryParams();" class="buttontwo">查询</a>
			        </td>
			        <td>
			          <a href="#" onclick="refreshParams();" class="buttontwo">刷新</a>
			        </td>
			        <td>
			          <s:if test="#session.perUrlList.contains('111_3_5_5_1')">
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
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<%@include file="zTree_func.jsp"%>
<%@include file="terminalParams_simple_validate.jsp"%>
</body>
</html>