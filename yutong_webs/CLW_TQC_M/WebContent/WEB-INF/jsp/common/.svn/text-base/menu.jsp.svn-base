<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/jquery-easyui-1.2.2/themes/default/menu.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/jquery-easyui-1.2.2/themes/default/menubutton.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/nyroModal.css' />"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global_manage.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">

<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/flexigrid/flexigrid.css'/>" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>

<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/easyloader.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/plugins/jquery.linkbutton.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/plugins/jquery.menubutton.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/plugins/jquery.menu.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jQuery/jquery.nyroModal-1.6.2.pack.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>

<style type="text/css">
.menu_iframe{ position:absolute; visibility:inherit; top:0; left:0; width:auto; z-index:-1; filter: Alpha(Opacity=0); border-style:none;}
</style>
<div class="wrapper">
<div id="header">
  <div id="headBg">
  
    <div class="logo"><!-- <img src="<s:url value='/images/logo_manage.png'/>" />--></div>
    <div class="userInfo">
    	<strong>
    		<s:property value="getText('main.welcome')" />
        	<s:property value="#session.adminProfile.userName" /> 
        </strong>
        <br />
        <span class="userLink">
        	<a href="<s:url value='/logout.shtml' />">
            <s:property value="getText('main.logout')" />
            </a>
        	<s:if test="#session.perUrlList.contains('111_0_6_4')">
        		&nbsp;|&nbsp;
		        <a href="<s:url value='/qx/modifypassword.shtml' />">
		          <s:property value="getText('main.modify')" />
		        </a> 
        	</s:if>
        	</span>
    </div>
    <div id="nav" class="navigation">
      <ul>
        <s:if test="#session.perUrlList.contains('111_0_3')">
      	  <li class="nav3">
            <a href="javascript:void(0)" class="easyui-menubutton" menu="#mm3">
              <s:property value="getText('menu.zd')" />
            </a>
          </li>
      	</s:if>
      	
        <s:if test="#session.perUrlList.contains('111_0_1')">
          <li class="nav1">
            <a href="javascript:void(0)" class="easyui-menubutton" menu="#mm1">
              <s:property value="getText('menu.qx')" />
            </a>
          </li>
        </s:if>
        
        <s:if test="#session.perUrlList.contains('111_0_2')">
          <li class="nav2">
            <a href="javascript:void(0)" class="easyui-menubutton" menu="#mm2">
              <s:property value="getText('menu.cl')" />
            </a>
          </li>
        </s:if>
        
        <s:if test="#session.perUrlList.contains('111_0_5')">
          <li class="nav5">
            <a href="javascript:void(0)" class="easyui-menubutton" menu="#mm5">
              <s:property value="getText('menu.xj')" />
            </a>
          </li>
        </s:if>
        
        <s:if test="#session.perUrlList.contains('111_0_6')">
          <li class="nav6">
            <a href="javascript:void(0)" class="easyui-menubutton" menu="#mm6">
              <s:property value="getText('menu.xs')" />
            </a>
          </li>
        </s:if>
      </ul>
    </div>
    
    <div style="display:none" >
    <s:if test="#session.perUrlList.contains('111_0_1')">
      <div id="mm1" style="width:100px;"><iframe class="menu_iframe"></iframe>
        <s:if test="#session.perUrlList.contains('111_0_1_1')">
        <div style="background: url(<s:url value='/images/menu_ico/2-1.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/qx/entimanage.shtml' />">
              <s:property value="getText('menu.qx.enterprise')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_1_3')">
        <div style="background: url(<s:url value='/images/menu_ico/2-2.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/qx/usermanage.shtml' />">
              <s:property value="getText('menu.qx.user')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_1_4')">
        <div style="background: url(<s:url value='/images/menu_ico/2-3.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/qx/rolemanage.shtml' />">
              <s:property value="getText('menu.qx.role')" />
            </a>
          </span>
        </div>
        </s:if>
      </div>
    </s:if>

    <s:if test="#session.perUrlList.contains('111_0_2')">
      <div id="mm2" style="width:120px;"><iframe class="menu_iframe"></iframe>
        <s:if test="#session.perUrlList.contains('111_0_4_2')"> 
        <div style="background: url(<s:url value='/images/menu_ico/3-1.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/cs/simmanage.shtml' />">
              <s:property value="getText('menu.cs.sim')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_3_4')">
        <div style="background: url(<s:url value='/images/menu_ico/3-2.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/zd/terminalmanage.shtml' />">
              <s:property value="getText('menu.zd.terminal')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_2_2')">  
        <div style="background: url(<s:url value='/images/menu_ico/3-3.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/cl/carbase.shtml' />">
              <s:property value="getText('menu.cl.vehicle')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_2_1')">
        <div style="background: url(<s:url value='/images/menu_ico/3-4.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/cl/vehicleregister.shtml' />">
              <s:property value="getText('menu.cl.vehicleregister')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_2_4')">
        <div style="background: url(<s:url value='/images/menu_ico/3-5.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/cl/handheldDevice.shtml' />">
              <s:property value="getText('menu.cl.handheld')" />
            </a>
          </span>
        </div>
        </s:if>
      </div>
    </s:if>

      <div id="mm3" style="width:120px;"><iframe class="menu_iframe"></iframe>
        <s:if test="#session.perUrlList.contains('111_0_5_4')">
        <div style="background: url(<s:url value='/images/menu_ico/1-1.gif'/>) no-repeat left 2px;">
            <a href="<s:url value='/zdnew/init.shtml' />">
              <s:property value="getText('menu.zdnew.terminalparams')" />
            </a>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_5_5')">
        <div style="background: url(<s:url value='/images/menu_ico/1-2.gif'/>) no-repeat left 2px;">
          <span>
           <a href="<s:url value='/zdnew/update.shtml' />">
              <s:property value="getText('menu.zdnew.terminalremoteupdate')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_3_5')">
        <div style="background: url(<s:url value='/images/menu_ico/1-3.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/zdnew/version_init.shtml' />">
              <s:property value="getText('menu.zdnew.terminalversions')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_5_6')">
        <div style="background: url(<s:url value='/images/menu_ico/1-4.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/zdnew/device_init.shtml' />">
              <s:property value="getText('menu.zdnew.terminaldevicestatus')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_5_7')">
        <div style="background: url(<s:url value='/images/menu_ico/1-5.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/zdnew/speedmonitoring_init.shtml' />">
              <s:property value="getText('menu.zdnew.speedadjust')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_5_8')">
        <div style="background: url(<s:url value='/images/menu_ico/5-1.png'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/zdnew/vehicleupdown_init.shtml' />">
              <s:property value="getText('menu.zdnew.vehicleupdown')" />
            </a>
          </span>
        </div>
        </s:if>
	    <s:if test="#session.perUrlList.contains('111_0_3_6')">
	        <div style="background: url(<s:url value='/images/menu_ico/5-2-2.gif'/>) no-repeat left 2px;">
	          <span>
	            <a href="<s:url value='/zdnew/ftlyinit.shtml' />">
	              <s:property value="getText('menu.zdnew.ftlyparams')" />
	            </a>
	          </span>
	        </div>
        </s:if>
      </div>

    <s:if test="#session.perUrlList.contains('111_0_5')">    
      <div id="mm5" style="width:120px;"><iframe class="menu_iframe"></iframe>
        <s:if test="#session.perUrlList.contains('111_0_5_1')">
        <div style="background: url(<s:url value='/images/menu_ico/4-1.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/xj/singleterminalmonitor.shtml' />">
              <s:property value="getText('menu.xj.monitor')" />
            </a>
          </span>
        </div>
        </s:if>
        <%-- 
        <s:if test="#session.perUrlList.contains('111_0_5_4')">
        <div>
          <span>
            <a href="<s:url value='/xj/queryCurrentTerminal.shtml' />">
              <s:property value="getText('menu.xj.param')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_5_5')">
        <div>
          <span>
            <a href="<s:url value='/xj/terminalRomoteUpdate.shtml' />">
              <s:property value="getText('menu.xj.monitorupdate')" />
            </a>
          </span>
        </div>
        </s:if>
        --%>
        <s:if test="#session.perUrlList.contains('111_0_5_2')">
        <div style="background: url(<s:url value='/images/menu_ico/4-2.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/xj/statistic.shtml' />">
              <s:property value="getText('menu.xj.visit')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_5_3')">
        <div style="background: url(<s:url value='/images/menu_ico/4-3.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/xj/statistic_log.shtml' />">
              <s:property value="getText('menu.xj.log')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_4_3')">
        <div style="background: url(<s:url value='/images/menu_ico/4-4.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/cs/simfluxmanage.shtml' />">
              <s:property value="getText('menu.cs.simflux')" />
            </a>
          </span>
        </div>
        </s:if>
      </div>
    </s:if>

    <s:if test="#session.perUrlList.contains('111_0_6')">
      <div id="mm6" style="width:140px;"><iframe class="menu_iframe"></iframe>
        <s:if test="#session.perUrlList.contains('111_0_6_1')">
        <div style="background: url(<s:url value='/images/menu_ico/5-1.gif'/>) no-repeat left 2px;">
          <span>
            <s:property value="getText('menu.xs.code')" />
          </span>
          <div style="width:120px;">
            <s:if test="#session.perUrlList.contains('111_0_6_1_1')">
              <div style="background: url(<s:url value='/images/menu_ico/5-1-1.gif'/>) no-repeat left 2px;">
                <a href="<s:url value='/xs/zonemanage.shtml' />">
                  <s:property value="getText('menu.xs.zone')" />
                </a>
              </div>
            </s:if>
            <s:if test="#session.perUrlList.contains('111_0_6_1_2')">
              <div style="background: url(<s:url value='/images/menu_ico/5-1-2.gif'/>) no-repeat left 2px;">
                <a href="<s:url value='/xs/baseinfomanage.shtml' />">
                  <s:property value="getText('menu.xs.baseinfo')" />
                </a>
              </div>
            </s:if>
          </div>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_6_5')">
        <div style="background: url(<s:url value='/images/menu_ico/5-2.gif'/>) no-repeat left 2px;">
          <span>
            <s:property value="getText('menu.xs.vehiclebaseinfo')" />
          </span>
          <div style="width:120px;">
            <s:if test="#session.perUrlList.contains('111_0_4_1')"> 
              <div style="background: url(<s:url value='/images/menu_ico/5-2-1.gif'/>) no-repeat left 2px;">
                <a href="<s:url value='/cs/businessmanage.shtml' />">
                  <s:property value="getText('menu.cs.business')" />
                </a>
              </div>
            </s:if>
            <s:if test="#session.perUrlList.contains('111_0_3_1')">
              <div style="background: url(<s:url value='/images/menu_ico/5-2-2.gif'/>) no-repeat left 2px;">
                <a href="<s:url value='/zd/oemmanage.shtml' />">
                  <s:property value="getText('menu.zd.oem')" />
                </a>
              </div>
            </s:if>
            <s:if test="#session.perUrlList.contains('111_0_3_2')">
              <div style="background: url(<s:url value='/images/menu_ico/5-2-3.gif'/>) no-repeat left 2px;">
                <a href="<s:url value='/zd/devicemanage.shtml' />">
                  <s:property value="getText('menu.zd.device')" />
                </a>
              </div>
            </s:if>
            <s:if test="#session.perUrlList.contains('111_0_3_3')">
              <div style="background: url(<s:url value='/images/menu_ico/5-2-4.gif'/>) no-repeat left 2px;">
                <a href="<s:url value='/zd/protocalmanage.shtml' />">
                  <s:property value="getText('menu.zd.protocal')" />
                </a>
              </div>
            </s:if>
            <s:if test="#session.perUrlList.contains('111_0_2_3')">  
              <div style="background: url(<s:url value='/images/menu_ico/5-2-5.gif'/>) no-repeat left 2px;">
                <a href="<s:url value='/cl/illdrive.shtml' />">
                  <s:property value="getText('menu.cl.baddrive')" />
                </a> 
              </div>
            </s:if>
          </div>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_6_2')">   
        <div style="background: url(<s:url value='/images/menu_ico/5-3.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/xs/poimanage.shtml' />">
              <s:property value="getText('menu.xs.poi')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_6_3')">
        <div style="background: url(<s:url value='/images/menu_ico/5-4.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/xs/versionmanage.shtml' />">
              <s:property value="getText('menu.xs.version')" />
            </a>
          </span>
        </div>
        </s:if>
        <div style="background: url(<s:url value='/images/menu_ico/cljk-lsxx.png'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/feedback/readyPage.shtml' />">
              问题反馈
            </a>
          </span>
        </div>
        <div style="background: url(<s:url value='/images/menu_ico/szzx-jysz.png'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/newmodel/apkfileUploadInit.shtml' />">
              安芯客户端版本发布
            </a>
          </span>
        </div>
        <div style="background: url(<s:url value='/images/menu_ico/szzx-jsgl.png'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/paramset/paramsetManage.shtml' />">
              参数设置
            </a>
          </span>
        </div>
        
        <div style="background: url(<s:url value='/images/menu_ico/5-5.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/loginstatistic/loginListAction.shtml' />">
             登陆次数统计
            </a>
          </span>
        </div>
        
        <div style="background: url(<s:url value='/images/menu_ico/5-5.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/paramset/themesetManage.shtml' />">
             用户界面设置
            </a>
          </span>
        </div>
      </div>
    </s:if>
    </div>
  </div>		
</div>
</div>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="subnavBg">
    <tr>
      
      <td align="left" class="subnavLeft">
        <s:property value="getText('main.current')" />
        <a href="<s:url value='/main.shtml' />">
          <s:property value="getText('main.name')" />
        </a>
        <s:property value="#session.location" />
      </td>
      <td>&nbsp;</td>
      <td align="right" style="padding-right:5px;">
      </td>
    </tr>
</table>
