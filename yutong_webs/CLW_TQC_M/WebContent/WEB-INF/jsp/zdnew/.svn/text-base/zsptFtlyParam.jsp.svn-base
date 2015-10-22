<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../common/meta.jsp" %>
<title>终端管理&nbsp;|&nbsp;油箱监控参数设置</title>
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
  <%@include file="zsptFtlyParam_validate.jsp"%>
  <%@include file="../zdnew/zTree_func.jsp"%>
  <s:form id="ftlyparams_form" action="setFtlyParams" method="post" onsubmit="return false;">
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
		        	油箱远程标定参数设置
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
          		      <table width="100%" border="0" cellspacing="5px" cellpadding="0px">
				        <tr>
				          <td width="15%" align="right">油量标定：</td>
				          <td width="15%" align="left">
				            <s:textfield id="oilDemarcate" name="ftlyParamInfo.oilDemarcate" maxlength="9" cssStyle="ime-mode:disabled"/>(0-1000)
				          </td>
				          <td width="15%" align="right">AD落差：</td>
				          <td width="15%" align="left">
				            <s:textfield id="adGap" name="ftlyParamInfo.adGap" maxlength="9" cssStyle="ime-mode:disabled"/>(0-4095)
				          </td>
				          <td width="20%" align="right"></td>
				          <td width="20%" align="right"></td>
				        </tr>
				        <tr>
				          <td width="15%" align="right">加油门限(L)：</td>
				          <td width="15%" align="left">
				            <s:textfield id="addOilLimit" name="ftlyParamInfo.addOilLimit" maxlength="9" cssStyle="ime-mode:disabled"/>(0-50)
				          </td>
				          <td width="15%" align="right">偷油门限(L)：</td>
				          <td width="15%" align="left">
				            <s:textfield id="stealOilLimit" name="ftlyParamInfo.stealOilLimit" maxlength="9" cssStyle="ime-mode:disabled"/>(0-50)
				          </td>
				          <td width="20%" align="right"></td>
				          <td width="20%" align="right"></td>
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