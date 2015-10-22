<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" href="<s:url value='/styles/nyroModal.css' />" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/jquery-easyui-1.2.2/themes/default/menu.css'/>"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/jquery-easyui-1.2.2/themes/default/menubutton.css'/>"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/jquery-easyui-1.2.2/themes/icon.css'/>"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/flexigrid/flexigrid.css'/>" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<style type="text/css">
.searchBox3{ background: #f5f5f5; border: 1px solid #c3c3c3; float: left; line-height: 32px; margin: 5px;  width:230px; height:35px;}
.searchPlan {
	float: left;
	width: 250px;
}
body{min-width:1000px;}
.menu_iframe{ position:absolute; visibility:inherit; top:0; left:0; width:auto; z-index:-1; filter: Alpha(Opacity=0); border-style:none;}
</style>
</head>
<body>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/easyloader.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/plugins/jquery.linkbutton.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/plugins/jquery.menubutton.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/plugins/jquery.menu.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript">
(function($){
	// 预缓存皮肤，数组第一个为默认皮肤
	$.dialog.defaults.skin = ['aero'];
})(art);
function aboutbrwose(){
	art.dialog.open("<s:url value='/about/about.shtml' />",{
		title:"关于 校车智能管理系统",
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
</script>


<div id="wrapper">
  <div id="header">
    <div class="headBg">
      <div class="logo">
      <s:if test="''==#session.adminProfile.img_path || null==#session.adminProfile.img_path">
      <img src="../images/logo.png"/>
      </s:if>
      <s:else>
       <img src="..<s:property
					value="#session.adminProfile.img_path" />"  style="filter:Alpha(opacity=100,finishOpacity=0,style=3)"/>
      </s:else>
      </div>
      <div class="userInfo"><strong><s:property value="#session.adminProfile.userName" /></strong>
        <p class="userLink"><a href="<s:url value='/logout.shtml' />">注销</a> | <a href="<s:url value='/usm/modifyPersonalinitAction.shtml' />">设置</a> | <a href="javascript:aboutbrwose();">帮助</a></p>
      </div>
      <div class="navigation">
        <ul>
          <li class="snav1">
          <a href="<s:url value='/gps/gpsAction.shtml' />">位置监控</a>
          </li>
          <li class="snav2">
          <a href="<s:url value='/gps/ready.shtml' />">乘车记录</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>

<div style="display:none" >
     <div id="mm1" style="width: 140px;"><iframe class="menu_iframe"></iframe>
			<div style="background: url(../images/menu_ico/1-1.gif) no-repeat left 2px;"><a href="<s:url value='/carrun/ready.shtml'/>"><s:text name="menu2.clyxrz" /></a></div>
			<div style="background: url(../images/menu_ico/4-7.gif) no-repeat left 2px;"><a href="<s:url value='/ridepro/ready.shtml'/>"><s:text name="menu2.szltj" /></a></div>
			<div style="background: url(../images/menu_ico/1-3.gif) no-repeat left 2px;"><a href="<s:url value='/carrun/ready.shtml'/>"><s:text name="menu2.yccctj" /></a></div>
			<s:if test="#session.perUrlList.contains('111_3_2_4')">
			<div style="background: url(../images/menu_ico/1-4.gif) no-repeat left 2px;"><a href="<s:url value='/humanbaddrv/baddrive.shtml'/>"><s:text name="menu2.weigui" /></a></div>
			</s:if>			
			<div style="background: url(../images/menu_ico/1-2.gif) no-repeat left 2px;"><a href="<s:url value='/stride/ready.shtml'/>"><s:text name="menu2.xsccjl" /></a></div>
	 </div>
	 <div id="mm2" style="width:120px;"><iframe class="menu_iframe"></iframe>
	       <s:if test="#session.perUrlList.contains('111_3_3_1')">
			<div style="background: url(../images/menu_ico/2-1.gif) no-repeat left 2px;"><a href="javascript:studentPasswordConfirm();"><s:text name="menu2.xcxx" /></a></div>
		   </s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_2')">
			<div style="background: url(../images/menu_ico/2-2.gif) no-repeat left 2px;"><a href="<s:url value='/driver/drivermanage.shtml' />"><s:text name="menu2.jsyxx" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_3')">
			<div style="background: url(../images/menu_ico/2-3.gif) no-repeat left 2px;"><a href="<s:url value='/steward/stewardmanage.shtml'/>"><s:text name="menu2.scxx" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_4')">
			<div style="background: url(../images/menu_ico/2-4.gif) no-repeat left 2px;"><a href="<s:url value='/vehicle/vehiclemanage.shtml' />"><s:text name="menu2.clxx" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_5')">
			<div style="background: url(../images/menu_ico/2-5.gif) no-repeat left 2px;"><a href="<s:url value='/station/stationmanage.shtml'/>"><s:text name="menu2.zdpz" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_6')">
			<div style="background: url(../images/menu_ico/2-6.gif) no-repeat left 2px;"><a href="<s:url value='/route/routemanage.shtml' />"><s:text name="menu2.xlsz" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_7')">
			<div style="background: url(../images/menu_ico/2-7.gif) no-repeat left 2px;"><a href="<s:url value='/infomanage/ridingplan.shtml'/>"><s:text name="menu2.ccgh" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_1')">
			<div style="background: url(../images/menu_ico/2-1.gif) no-repeat left 2px;"><a href="javascript:messagePasswordConfirm();">短信提醒</a></div>
		   </s:if>
			<s:if test="#session.perUrlList.contains('111_3_3_8')">
			<div style="background: url(../images/menu_ico/2-8.gif) no-repeat left 2px;"><a href="<s:url value='/holiday/holidaymanage.shtml'/>"><s:text name="menu2.qxjgl" /></a></div>
			</s:if>
	 </div>
	 <div id="mm3" style="width:120px;"><iframe class="menu_iframe"></iframe>
	 		<s:if test="#session.perUrlList.contains('111_3_4_1')">
			<div style="background: url(../images/menu_ico/3-2.gif) no-repeat left 2px;"><a href="<s:url value='/vehiclestatus/vehiclestatus.shtml'/>"><s:text name="menu2.ckjk" /></a></div>
			</s:if>
	        <s:if test="#session.perUrlList.contains('111_3_4_2')">
			<div style="background: url(../images/menu_ico/3-1.gif) no-repeat left 2px;"><a href="<s:url value='/oilused/oilused.shtml'/>"><s:text name="menu3.youhaobaogao" /></a></div>
			</s:if>
			<s:if test="#session.perUrlList.contains('111_3_4_3')">
			<div style="background: url(../images/menu_ico/3-3.gif) no-repeat left 2px;"><a href="<s:url value='/baddriv/baddriving.shtml'/>"><s:text name="menu2.bljsjl" /></a></div>
			</s:if>
	  </div>
	<div id="mm4" style="width:140px;"><iframe class="menu_iframe"></iframe>
	    	<s:if test="#session.perUrlList.contains('111_3_5_1')">
		    <div style="background: url(../images/menu_ico/4-1.gif) no-repeat left 2px;">
		    	<a href="<s:url value='/rm/roleAction.shtml' />"><s:text name="menu3.jsgl" /></a>
		    </div>
		    </s:if>
		    <s:if test="#session.perUrlList.contains('111_3_5_2')">
		    <div style="background: url(../images/menu_ico/4-2.gif) no-repeat left 2px;">
		    	<a href= "<s:url value='/usm/usermanageAction.shtml' />"><s:text name="menu3.yhgl" /></a>
		    </div>
		    </s:if>
		    <s:if test="#session.perUrlList.contains('111_3_5_3')">
	        <div style="background: url(../images/menu_ico/4-3.gif) no-repeat left 2px;"><a href="<s:url value='/enti/entimanage.shtml' />"><s:text name="menu2.zzjgsz" /></a></div>
	        </s:if>        
		    <s:if test="#session.perUrlList.contains('111_3_5_4')">
		   <div style="background: url(../images/menu_ico/4-4.gif) no-repeat left 2px;">
		    	<a href="<s:url value='/logoset/logoset.shtml' />"><s:text name="menu3.qylogo" /></a>
		    </div>
		    </s:if>
	   	
	        <s:if test="#session.perUrlList.contains('111_3_5_5')">
	        <div style="background: url(../images/menu_ico/4-5.gif) no-repeat left 2px;"><a href="<s:url value='/terparams/vehiclemanage.shtml' />"><s:text name="menu2.ywcssz" /></a></div>
	        </s:if>
	        <s:if test="#session.perUrlList.contains('111_3_5_6')">
	       <div style="background: url(../images/menu_ico/4-6.gif) no-repeat left 2px;"><a href="<s:url value='/ock/ocktimeset.shtml' />"><s:text name="menu2.khyhydsz" /></a></div>
	        </s:if>
	        <s:if test="#session.perUrlList.contains('111_3_5_7')">
	        <div style="background: url(../images/menu_ico/4-7.gif) no-repeat left 2px;"><a href="<s:url value='/oilset/oilSet.shtml' />"><s:text name="menu2.khyhsz" /></a></div>
	        </s:if>
	   </div> 
</div>
<%@include file="xingchejilu_validate.jsp"%>
<s:form id="export_form" action="" method="post">
<div>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" class="subnavBg">
	<tr>
		<td align="left" class="subnavLeft"> <s:property value="getText('main.current')" />：
		<s:text name="menu1.yunxing" />&nbsp;-&gt;&nbsp;<a href="<s:url value='/gps/ready.shtml'/>"><s:text name="menu2.xsccjl" /></a></td>
		<td>&nbsp;</td>
	</tr>
</table>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="36" valign="top" background="../images/tree_tabBg.gif">
				<div class="toolbar">
				<div class="contentTil"><s:text name="menu2.xsccjl" /></div>
				</div>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="5" cellpadding="0">
		<tr>
           <td class="reportOnline2" height="40">
				  <table width="100%" border="0" cellspacing="8" cellpadding="0">
                    <tr>
                      <td><table border="0" cellspacing="8" cellpadding="0">
                        <tr>
                          <td>乘车类型
                               <select id="st_ride_flag" name="st_ride_flag">
                                <option value="22">全部</option>
                               	<option value="00">上学上车</option>
                               	<option value="01">上学下车</option>
                               	<option value="10">放学上车</option>
                               	<option value="11">放学下车</option>
                               </select>
                          </td>
                          <td id="vln">
                          </td>                          
                          <td>时间:
                          <input id="begTime" name="begTime"  value="${queryObj.begTime}" size="24"
									class="Wdate" type="text" readonly="readonly" 
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,maxDate:'#F{$dp.$D(\'endTime\')}',isShowClear:false})"/></td>
						  <td>至
                          <input id="endTime" name="endTime"  value="${queryObj.endTime}" size="24"
									class="Wdate" type="text" readonly="readonly" 
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',autoPickDate:true,minDate:'#F{$dp.$D(\'begTime\')}',isShowClear:false})"/></td>			
                          <td><a href="javascript:void(0);" onclick="javascript:searchAlarm();" class="sbutton">查询</a></td>
                        </tr>
                      </table></td>
                    </tr>
                  </table>
             </td>
         </tr>
          <tr>
				<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
							<tr>
								<td class="titleStyle">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class="titleStyleZi"><s:text name="menu2.xsccjl" /></td>
										<td  style="float: right;">
											<div class="buhuanhangbut">
<!--			                                  <a href="javascript:void(0);" class="btnDaochu" onclick="javascript:exportData();" title="<s:text name="button.daochu" />"></a>-->
				                            </div>										
										</td>											
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td  class="reportInline" >
									<span id="parentSpan"><table id="gala" ></table></span>
								</td>
							</tr>
						</table>
				</td>          
          </tr>
		</table>		        
		</td>
      </tr>
    </table>
   
<div id="footer">客服电话：<strong>400-659-6666</strong>&nbsp;&nbsp;&nbsp;&nbsp;豫ICP备10203497号-3&nbsp;&nbsp;&nbsp;&nbsp;版权所有<span style="font-family:Arial, Helvetica, sans-serif">&copy;</span> 宇通集团 2010-2015 </div>
</div>
<div id="BgDiv"></div>
<s:hidden id="exportObj.stu_code" name="exportObj.stu_code"/>
<s:hidden id="exportObj.vehicle_ln" name="exportObj.vehicle_ln"/>
<s:hidden id="exportObj.begTime" name="exportObj.begTime"/>
<s:hidden id="exportObj.endTime" name="exportObj.endTime"/>

<input type="hidden" id="optionUserid" name="optionUserid" value="<s:property value='#session.adminProfile.userID'/>" />
</s:form>
</body>

</html>