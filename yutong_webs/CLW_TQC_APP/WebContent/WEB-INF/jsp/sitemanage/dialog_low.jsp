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
			<div id="setLowerValDiv"  style="width: 440px;height: 230px;background: #FBFBFB;padding: 5px"  >
						<div style="width: 90%;margin-top: 10px;padding: 15px;">
							<img src="../images/xiaocheImage/alert.png" style="width: 40px;height: 40px; float: left; margin-right: 20px"/>
							<span style="line-height: 22px">油量监控模块支持对于低油量车辆进行标记，以便识别需要加油的车辆！</span>
						</div>
						<table id="lowerValTable" width="449px" border="0" align="center" cellpadding="0" cellspacing="0" style="marign-top: 10px;background: #FBFBFB;">
							<tr valign="middle"><td valign="middle" colspan="2">&nbsp;</td>
					     	</tr>
					     	<tr height="33px">
					     		<td colspan="7" style="padding-left: 10px;"><b>低油量提醒阀值：</b></td>
					     	</tr>
					     	<tr>
					     		<td><input type="checkbox" name="oilValueCheck" id="oilValueCheck" value="1"/>启用低油量阀值提醒</td>
					     		<td>提醒阀值：</td>
					     		<td>
									<select name="" id="lowerOilValue" style="width: 80px;" >
										<option value="0.05">5%</option>
										<option value="0.1">10%</option>
										<option value="0.15">15%</option>
										<option value="0.2">20%</option>
										<option value="0.25">25%</option>
										<option value="0.3">30%</option>
										<option value="0.35">35%</option>
										<option value="0.40">40%</option>
										<option value="0.45">45%</option>
										<option value="0.50">50%</option>
									</select><span class="noticeMsg">*</span>
								</td>
					     	</tr>
					     	<tr>
					     		<td></td>
					     		<td>油价（元/升）：</td>
					     		<td >
									<input type="text" name="oilPrice" id="oilPrice" value="" maxlength="14" style="width: 80px;"/><span class="noticeMsg">*</span>
								</td>
					     	</tr>
					     	<tr valign="middle"  height="50px">
								<td  colspan="4">
									<a href="javascript:void(0)" class="buttonMap" onclick="parent.sitManagerObj.cancelArtWinClosed('addLowerValWin')">取消</a>
					                     <a href="javascript:void(0)" class="buttonMap" onclick="sitManagerDialogObj.addLowerVal()">确定</a>
								</td>
							</tr>
						</table>
					</div>
					
					
					
					
		</div>
								

	</body>
</html>
