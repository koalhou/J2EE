<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
                            <div class="titleBar">
				            <div class="title">
				                                         告警管理
				            </div>					     
							<div class="workLink">
							    <s:if test="#session.perUrlList.contains('111_3_1_12_6')"> 
							    <div id="piliangchuli" class="export_alarm m_r_10">
                                   <a  href="#" class="export_alarm_c1"  onclick="senddealcommand()">批量处理</a> 
                                </div>
                                </s:if>
                                  <s:if test="#session.perUrlList.contains('111_3_1_12_1')"> 
                                 <div class="export_alarm">
                                    <a href="#" class="export_alarm_c2" onclick="exportAlarm()">导出</a>
                                 </div>
                                 </s:if>
                            </div>
                         </div>
                               <div id="statusManger">
                              <s:hidden id="deal_flag"  name="deal_flag"/>
                               <s:hidden id="year" name="year"/> 
                              <input id="chulialarmtypeid" type="hidden"/>
                              <input id="chulialarmtime" type="hidden"/>
                              <input id="chulialarmid" type="hidden"/>
                              <input id="chulilon"   type="hidden"/>
                              <input id="chulilat"   type="hidden"/>
                              <input id="chulivin"  type="hidden"/>
                              <div class="car-info">
                                 <h1 id="carln">未选车</h1>
                                 <div style="float:left;width:1px;height:14px;background:#000;margin-top: 12px;"></div>
                                 <span id="messagetime" class="times" style="float:left;border-left:none;display: none"><s:property value="year"/>-00-00 00:00:00</span>
                                 <div id="chuliren" style="float:left;margin-left:10px;margin-top:2px;"><table border="0" cellspacing="0" cellpadding="0"><tr><td align="right" class="alarm_title">处理人：</td><td id="chulirenname">
                                 <!--  <input id="chulirenname" type="text"  style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:23px;text-align:center;margin-top: -2px;" readonly="readonly"/>-->
                                 无</td></tr></table> </div>
                                 <a id="chuli" href="#" class="btn-blue4 newalarm_btn_p304" onclick="chuli()" style="display: none"><s:if test="#session.perUrlList.contains('111_3_1_12_6')">处理</s:if><s:else>查看详情</s:else></a>
                               </div>         
                               <div class="car-status-alarm">
              	              <!--dyy_20120817 start-->
                                    <table border="0" cellspacing="0" cellpadding="0">
  					                    <tr>
    					                     <td class="alarm_title">告警类型：</td>
    					                     <td width="156"><input id="alarmtypename1" type="text" style="background:url(../newimages/input_bg_137.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;text-align:center;" readonly="readonly"/></td>
                                             <td class="alarm_title">地理位置：</td>
    					                     <td width="330"><input id="geo1" type="text" style="background:url(../newimages/input_bg_311.png) no-repeat left top; border:none; width:306px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;text-align:center;" readonly="readonly"/></td>
    					                     <td class="alarm_title"><div id="yijian">处理意见：</div></td>
    					                     <td rowspan="2"><div id="yijianneirong" style="width:191px;background:url(../newimages/alarm_txt_bg_h52w200.gif) no-repeat left top;border:none;height:44px;overflow-y:auto;margin-top:3px;padding: 4px;"></div></td>
  					                    </tr>
  					                     <tr>
                    	                     <td class="alarm_title">速&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度：</td>
    					                     <td><input id="speed1" type="text" style="background:url(../newimages/input_bg_137.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;text-align:center;" readonly="readonly"/></td>
                                             <td class="alarm_title">转&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;速：</td>
    					                     <td>
                        	                 <table border="0" cellspacing="0" cellpadding="0">
                            	              <tr>
                            	                <td width="125"><input id="zhuanshu" type="text" style="background:url(../newimages/input_bg_101.png) no-repeat left top; border:none; width:96px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;text-align:center;" readonly="readonly"/></td>
                                                <td class="alarm_title">里&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程：</td>
                                                <td width="120"><input id="licheng" type="text" style="background:url(../newimages/pop_read_bg_2.gif) no-repeat left top;; border:none; width:115px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;text-align:center;" readonly="readonly"/></td>
                                             </tr>
                                            </table>
                                            </td>
  					                     </tr>
				                      </table>

                <!--dyy_20120817 end-->
           	                    </div>
           	                    <div class="alarm_tab">
                                 <a id="deal0" href="#" onclick="changetab(this,1)">紧急告警</a>
                                 <a id="deal2" href="#" onclick="changetab(this,2)" class="alarm_tabs">超速告警</a>
                                 <a id="deal3" href="#" onclick="changetab(this,3)">车辆故障</a>
                                 <s:if test="3==#session.adminProfile.en_mould">	
                                 <a id="deal4" href="#" onclick="changetab(this,4)">异常乘车</a>
                                 </s:if>
                                </div>
                                  <div class="alarm_select">
                	                  <span class="alarm_sel_txt">车牌号：</span>
                                      <div class="alarm_sel_c"><s:textfield id="vehicle_ln" name="vehicle_ln"
											cssClass="input100" maxlength="7" onkeypress="chaxun()"/></div>
                                      <span class="alarm_sel_txt">类&nbsp;&nbsp;&nbsp;&nbsp;型：</span>
                                      <div class="alarm_sel_c"><s:select id="alarm_type_id" name="alarm_type_id"
											list="alarmtypemap"
											headerKey="%{alarm_type_id}"
											headerValue="%{getText('please.select')}" cssStyle="width:100px;height:20px;" onchange="mychange()"/></div>
                                      <span class="alarm_sel_txt" style="width:72px;">处理状态：</span>
                                      <div class="alarm_sel_c">
                                      <select id="cdeal_flag" name="cdeal_flag" style="width:98px;height:20px;" onchange="changedeal()">
                                       <option value="0">未处理</option>
				                       <option value="2">处理中</option>
				                       <option value="1">已处理</option>
                                      </select>
									  </div>
                                      <span class="alarm_sel_txt">时&nbsp;&nbsp;&nbsp;&nbsp;段：</span>
                                      <div class="alarm_sel_c"><input readonly="readonly"
											id="start_time" name="start_time" value="${start_time}"
											class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,isShowClear:false,maxDate:'#F{$dp.$D(\'end_time\')}',isShowClear:false,onpicked:pickedstarttime})" /></div>
                                      <span class="alarm_sel_txt_2">至</span>
                                      <div class="alarm_sel_c"><input readonly="readonly" id="end_time" name="end_time"
											value="${end_time}" class="Wdate" type="text"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,isShowClear:false,minDate:'#F{$dp.$D(\'start_time\')}',maxDate:'%y-%M-%d',isShowClear:false,onpicked:pickedendtime})" /></div>
                	                  <a href="#" onclick="searchList()" class="alarm_btn" >查询</a>
                	                  <a href="javascript:void(0)" onclick="openScroll()" class="alarm_btn" >实时告警</a>
                	                  <a href="javascript:void(0)" onclick="exportReport()" class="alarm_btn" >汇总</a>
                                  </div>
                                   
                                     <!-- 下面是列表不细作,不使用 -->
                                     
                                         <div class="alarm_t_c">
                                           <table id="gala" width="100%">
				                           </table>
				                          </div>
				               </div>
                                  <%@include file="newchaosualarmlist_js.jsp"%>