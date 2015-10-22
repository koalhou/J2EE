<%@page language="java" contentType="text/html;charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>

		<title>加油站点设置&nbsp;|&nbsp;设置
		</title>
		
		
		<script type="text/javascript" src="../scripts/flexigrid/jquery-1.5.2.min.js"></script>
		<script type="text/javascript" src="../scripts/sitemanage/siteManagerDialog.js?sdf789dsd"></script>
		<script>
		function sub(){
			
		}
		</script>
		<style type="text/css">
		body, td, th {
		    color: #000000;
		    font-size: 12px;
		}
		a.buttonMap, a.buttonMap:link, a.buttonMap:visited, a.buttonMap:hover, a.buttonMap:active {
		    background: url("../images/xiaocheImage/sbutton_bg.gif") repeat-x scroll left top transparent;
		    border: 1px solid #B4B4B4;
		    color: #2A2A2A;
		    display: block;
		    float: right;
		    height: 20px;
		    line-height: 20px;
		    margin: 0 2px;
		    padding: 1px 2px 0;
		    text-align: center;
		    width: 50px;
		}
		a {
		    color: #0078C8;
		    text-decoration: none;
		}
		.noticeMsg {
		    color: #CC0000;
		    font-size: 12px;
		    padding: 2px;
		}
		</style>
	</head>
	<body>
		
			
				<div class="popBox">
			<input type="hidden" id="siteConfigId" name="siteConfigId" value=""/>
			<div id="timeQuantumDiv"  style="width: 440px;height: 230px;background: #FBFBFB;padding: 5px"  >
						<div style="width: 90%;margin-top: 10px;padding: 15px;">
							<img src="../images/xiaocheImage/alert.png" style="width: 40px;height: 40px; float: left; margin-right: 20px"/>
							<span style="line-height: 22px">为了掌握驾驶员加油时间是否在指定时段内，油量监控模块支持设置“加油时段”，对于未在加油时段加油的，将会以告警的方式进行提醒！</span>
						</div>
						<table id="setAddOilTimer" width="449px" border="0" align="center" cellpadding="0" cellspacing="0" style="marign-top: 10px;background: #FBFBFB;">
							<tr valign="middle"><td valign="middle" colspan="2">&nbsp;</td>
					     	</tr>
					     	<tr height="33px">
					     		<td colspan="7" style="padding-left: 10px;"><b>指定加油时段：</b></td>
					     	</tr>
					     	<tr>
					     		<td><input type="checkbox" name="setTimer" id="setOilTimers" value="1"/>启用</td>
					     		<td>加油频率：</td>
					     		<td>
									<select name="" id="addOilCount" style="width: 50px;" onchange="sitManagerDialogObj.getWeekOrMonth(this.value);">
										<option value="2" >每周</option>
										<option value="1">每月</option>
									</select><span class="noticeMsg">*</span>
								</td>
					     		<td>加油时段：</td>
					     		<td>
					     			<select name="setStartTimers" id="setStartTimers">
		<option value='1' >周一</option>
		<option value='2'>周二</option>
		<option value='3'>周三</option>
		<option value='4'>周四</option>
		<option value='5'>周五</option>
		<option value='6'>周六</option>
		<option value='7'>周日</option>
					     			</select>
					     		至
					     			<select name="setEndTimers" id="setEndTimers">
					     						<option value='1' >周一</option>
		<option value='2'>周二</option>
		<option value='3'>周三</option>
		<option value='4'>周四</option>
		<option value='5'>周五</option>
		<option value='6'>周六</option>
		<option value='7'>周日</option>
					     			</select><span class="noticeMsg">*</span>
					     		</td>
					     	</tr>
					     	<tr valign="middle"  height="80px">
								<td  colspan="7">
									<a href="javascript:void(0)" class="buttonMap" onclick="parent.sitManagerObj.cancelArtWinClosed('addOilTimerWin')">取消</a>
					                <a href="javascript:void(0)" class="buttonMap" onclick="sitManagerDialogObj.addOilTimer()">确定</a>
								</td>
							</tr>
						</table>
					</div>
					
					
					
					
		</div>
								

	</body>
</html>
