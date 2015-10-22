<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<title>终端管理&nbsp;|&nbsp;<s:property value="getText('menu.xj.monitorupdate')" /></title>
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
<%@include file="../common/menu.jsp"%>
<%@include file="zTree_common.jsp"%>
<%@include file="terminalRomoteUpdate_validate.jsp"%>
<s:form id="terminalRomote_form" action="terminalRomoteInsert" method="post">
<s:hidden id="enterpriseId" name="enterpriseId"/>
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
				                <input name="enterpriseName" id="enterpriseName" type="text" class="input120" />
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
			      </table>
                </td>
              </tr>
              <tr>
	            <td class="reportInline" align="left">
				  &nbsp;
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
      <td valign="top">
        <table width="100%" border="0" cellspacing="5" cellpadding="0">                
          <tr>
            <td class="reportOnline2">
              <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                  <td>
                    <table border="0" cellspacing="4" cellpadding="0" >
                      <tr>
                        <td>
                          <s:property value="getText('common.list.vehicleln')" />:
                        </td>
                        <td>
                          <input id="vehicleln" name="vehicleln" type="text" class="input120"/>&nbsp;&nbsp;
                        </td>
                        <td>
                          	主机固件版本:
                        </td>
                        <td>
                          <input id="host_firm_ver" name="host_firm_ver" class="input120" type="text"/>&nbsp;&nbsp;
                        </td>
                      
                        <td>
                          	显示屏固件版本:
                        </td>
                        <td>
                          <input id="xianshi_firm_ver" name="xianshi_firm_ver" type="text" class="input120"/>&nbsp;&nbsp;
                        </td>
                        <td>
                          	射频读卡器固件版本:
                        </td>
                        <td>
                          <input id="SHEPIN_firm_ver" name="SHEPIN_firm_ver" type="text" class="input120"/>&nbsp;&nbsp;
                        </td>
                        <td>
                        	<a href="#" class="buttontwo" onclick="searchVehicleList()"><s:property value="getText('btn.query')" /></a>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td valign="top">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td class="reportOnline">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="titleStyle">
                          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td width="30%" class="titleStyleZi">车辆信息                              
                              </td>                                    
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <tr>
                        <td class="reportInline">
                          <div id="message">
                            <div id="empDiv">
                              <s:actionerror theme="mat" />
                              <s:fielderror theme="mat"/>
                              <s:actionmessage theme="mat"/>
                            </div>
                          </div>
                          <div>
                            <table id="terminalList" width="100%" cellspacing="0">
							</table> 
                          </div>
                        </td>
                      </tr>
                      
                    </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
       	  <tr>
             <td>
             <table border="0" cellspacing="8" cellpadding="0">                       
               <tr align="right">
                 <td  width="8%">硬件版本:
                 </td>
                 <td width="17%" align="left">
                     <input id="hardver" name="hardver" type="text" maxlength="30"   />            
                 </td>
                 <td  width="8%">固件版本:
                 </td>
                 <td width="17%" align="left">
                     <input id="firmver" name="firmver" type="text" maxlength="50"   />
                 </td>
                 <td  width="8%">连接时限:
                 </td>
                 <td width="17%" align="left">
                     <input id="timePer" name="timePer" type="text" maxlength="30"  />        
                 </td>
                 <td  width="8%">URL地址:
                 </td>
                 <td width="17%" align="left">
                     <input id="url" name="url" type="text" maxlength="200"  />
                 </td>                       
               </tr>
               <tr align="right" align="left">
                 <td>拨号点名称:
                 </td>
                 <td align="left">
                     <input id="mainapn" name="mainapn"  type="text" maxlength="30"  />  
                 </td>
                 <td>拨号点用户名:
                 </td>
                 <td  align="left">
                     <input id="mainuser" name="mainuser"  type="text" maxlength="30"  />                              
                 </td>
                 <td >拨号密码:
                 </td>
                 <td align="left">
                     <input id="mainpass" name="mainpass"  type="text" maxlength="30"  />
                 </td> 
                 <td></td>                                            
               </tr>
               <tr align="right">
                 <td>IP地址:
                 </td>
                 <td align="left">
                     <input id="ip" name="ip" type="text" maxlength="30"  />                    
                 </td>
                 <td>TCP端口号:
                 </td>
                 <td align="left">
                     <input id="tcpport" name="tcpport" type="text" maxlength="30" /> 
                 </td>
                 <td >UDP端口号:
                 </td>
                 <td align="left">
                     <input id="udpport" name="udpport" type="text" maxlength="30"  />                            
                 </td> 
                 <td>
                   <s:if test="#session.perUrlList.contains('111_0_5_5_1')">
                    <a href="#" onclick="updateInfo()" class="buttontwo" >升级</a>
                    </s:if>
                    <input name = "teminalList" id="teminalList" type="hidden"/> 
                    <input name = "simList" id="simList" type="hidden"/>
                    <input name = "vinList" id="simList" type="hidden"/>
                 </td>                                                                        
               </tr>
               
             </table>
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