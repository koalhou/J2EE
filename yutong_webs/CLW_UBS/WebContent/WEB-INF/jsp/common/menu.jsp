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
<script>
function ubMenuClick(url)
{
	location=url;	
}
</script>
<div class="wrapper">
<div id="header">
  <div id="headBg">
  <table cellpadding=0 cellspacing=0 style="border:0px;width:100%;">
  	<tr>
	  <td style="padding:5px;width:305px;"><div class="logo"><img src="<s:url value='/images/ub/logo.png'/>" /></div></td>
	  	<td style="padding:5px;">
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
		        	
		        		&nbsp;|&nbsp;
				        <a href="<s:url value='/qx/modifypassword.shtml' />">
				          <s:property value="getText('main.modify')" />
				        </a> 
		        	
		        	</span>
			</div>
    	</td>
	  <td style="padding:5px;">
		  <div id="nav" class="navigation">
	    <ul>
	      	<!-- zyong add -->
	      	
	      	  <li class="navubindex">
	            <a href="#" class="easyui-menubutton" onclick="ubMenuClick('<s:url value='/main.shtml'/>');">
	             	 首页
	            </a>
	          </li>
	      	
	        	<s:if test="#session.perUrlList.contains('111_4_2')">
	      	  <li class="navubent">
	            <a href="#" class="easyui-menubutton" onclick="ubMenuClick('<s:url value='/ub/custom/init.shtml'/>');">
	              企业客户活跃度
	            </a>
	          </li>
	      	</s:if>
	       	<s:if test="#session.perUrlList.contains('111_4_3')">
	      	  <li class="navubsrv">
	            <a href="#" class="easyui-menubutton" onclick="ubMenuClick('<s:url value='/ub/srv/overall.shtml'/>');">
	             服务活跃度
	            </a>
	          </li>
	      	</s:if>
	       	<s:if test="#session.perUrlList.contains('111_4_4')">
	      	  <li class="navubpassivity">
	            <a href="#" class="easyui-menubutton" onclick="ubMenuClick('<s:url value='/ub/passivity/overall.shtml'/>');">
	              被动活跃度
	            </a>
	          </li>
	      	</s:if>
	       	<s:if test="#session.perUrlList.contains('111_4_5')">
	      	  <li class="navubsystem">
	            <a href="#" class="easyui-menubutton" onclick="ubMenuClick('<s:url value='/ub/system/ubsolution.shtml'/>');">
	              系统环境
	            </a>
	          </li>
	      	</s:if>
	       	<s:if test="#session.perUrlList.contains('111_4_6')">
	      	  <li class="navublogin">
	            <a href="#" class="easyui-menubutton" onclick="ubMenuClick('<s:url value='/ub/login/logincountstat.shtml'/>');">
	              登陆次数统计
	            </a>
	          </li>
	      	</s:if>
	       	<s:if test="#session.perUrlList.contains('111_4_7')">
	      	  <li class="navubmarket">
	            <a href="#" class="easyui-menubutton" onclick="ubMenuClick('<s:url value='/ub/market/linesInit.shtml'/>');">
	              市场看板
	            </a>
	          </li>
	      	</s:if>
	       	<s:if test="#session.perUrlList.contains('111_4_8')">
	      	  <li class="navubsetting">
	            <a href="#" class="easyui-menubutton"  menu="#mm6">
	              系统设置
	            </a>
	          </li>
	      	</s:if>
	     	
	      </ul>
	    
	    <!-- 
	      <ul>
	      
	      	<s:if test="#session.perUrlList.contains('111_0_3')">
	      	  <li class="navub">
	            <a href="#" class="easyui-menubutton" onclick="ubMenuClick('<s:url value='/ub/custom/init.shtml'/>');" menu="#mm_ub">
	              <s:property value="getText('menu.ub')" />
	            </a>
	          </li>
	      	</s:if>
	      	
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
	       -->
	    </div>
	  
	  </td>
	</tr>
  </table>
    
    
    
    
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
      </div>
<!-- zyong add  -->
	  <div id="mm_ub" style="width:120px;"><iframe class="menu_iframe"></iframe>
        <s:if test="#session.perUrlList.contains('111_0_5_4')">
        <div style="background: url(<s:url value='/images/menu_ico/1-1.gif'/>) no-repeat left 2px;">
            <a href="<s:url value='/ub/custom/init.shtml' />">
              <s:property value="getText('menu.ub.customliveness')" />
            </a>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_5_5')">
        <div style="background: url(<s:url value='/images/menu_ico/1-2.gif'/>) no-repeat left 2px;">
          <span>
           <a href="<s:url value='/ub/srv/overall.shtml' />">
              <s:property value="getText('menu.ub.serviceliveness')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_3_5')">
        <div style="background: url(<s:url value='/images/menu_ico/1-3.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/ub/passivity/overall.shtml' />">
              <s:property value="getText('menu.ub.passiveliveness')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_5_6')">
        <div style="background: url(<s:url value='/images/menu_ico/1-4.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/ub/system/ubsolution.shtml' />">
              <s:property value="getText('menu.ub.enviroment')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_5_7')">
        <div style="background: url(<s:url value='/images/menu_ico/1-5.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/ub/login/logincountstat.shtml' />">
              <s:property value="getText('menu.ub.logincnt')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_0_5_7')">
        <div style="background: url(<s:url value='/images/menu_ico/1-5.gif'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/ub/holiday/holidaymanage.shtml' />">
              <s:property value="getText('menu.ub.holiday')" />
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

    <s:if test="#session.perUrlList.contains('111_4_8_3')"><!-- 111_0_6 -->
      <div id="mm6" style="width:140px;"><iframe class="menu_iframe"></iframe>
       <s:if test="#session.perUrlList.contains('111_4_8_1')">
        <div style="background: url(<s:url value='/images/ub/usericon.png'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/qx/usermanage.shtml' />">
              <s:property value="getText('menu.qx.user')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_4_8_2')">
        <div style="background: url(<s:url value='/images/ub/roleicon.png'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/qx/rolemanage.shtml' />">
              <s:property value="getText('menu.qx.role')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_4_8_3')">
        <div style="background: url(<s:url value='/images/ub/holidayicon.png'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/ub/holiday/holidaymanage.shtml' />">
              <s:property value="getText('menu.ub.holiday')" />
            </a>
          </span>
        </div>
        </s:if>
        <s:if test="#session.perUrlList.contains('111_4_8_4')">
        <div style="background: url(<s:url value='/images/ub/accounticon.png'/>) no-repeat left 2px;">
          <span>
            <a href="<s:url value='/ub/market/task.shtml' />">
              客户经理指标
            </a>
          </span>
        </div>
        </s:if>
        
        <!-- 
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
        </s:if> -->
      </div>
    </s:if>
    </div>
  </div>		
</div>
</div>
<div style="clear: both"></div>
<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="subnavBg">
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

