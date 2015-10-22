<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<style type="text/css">
.btn-blue {
	border: 0;
	background: url(../newimages/sidelayerimages/btn_blue.gif) no-repeat;
	width: 76px;
	height: 28px;
	line-height: 28px;
	font-size: 12px;
	font-weight: bold;
	font-family: "微软雅黑";
	color: #FFF;
	text-align: center;
	cursor: pointer;
}
</style>
</head>
<body style="width: 700px; min-width: 700px;">
	<s:hidden id="route_id" name="route_id"></s:hidden>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td valign="top">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="reportOnline">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td valign="top">
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0" class="reportOnline">
														<tr>
															<td class="titleStyle">
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr>
																		<td width="30%" class="titleStyleZi">站点信息</td>
																		<td width="69%" align="right"></td>
																		<td width="1%">&nbsp;</td>

																	</tr>
																</table>
															</td>
														</tr>

														<tr>
															<td class="reportInline">
																<div>
																	<table id="gala" width="100%" cellspacing="0"></table>
																</div>
															</td>
														</tr>
														<tr>
															<td align="center"><input type="button"
																class="btn-blue" onclick="settosite()" value="设置" />
															</td>
														</tr>
													</table>
												</td>
											</tr>

										</table>
									</td>
								</tr>

							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div id="stationInfoDiv"
		style="width: 300px;display: none;">
		<table id="dataTable" width="300px" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td align="left" height="30px;">设置内容：</td>
			</tr>
			<tr>
				<td align="left" class="fsBlack"><s:textarea id="remark"
						name="remark" cols="40" rows="6" onkeyup="gbcount(this)"></s:textarea>
				</td>

			</tr>
			<tr>
				<td style="margin-left: 5px;" height="30px">
				<span id="textleft" style="color: green">最多可输入<font size="3">30</font>个字</span>
				<a href="#" class="buttonMap"
					onclick="updatePoint()">确定</a> <a href="#" class="buttonMap"
					onclick="cancelPoint()">取消</a>	
				</td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>

<script type="text/javascript">
	var siteids = "";
	jQuery(function() {
	 var gala = jQuery('#gala');
	 var url= "../routeGrid/sitegridList.shtml?route_id="+$('#route_id').val();	 
	 gala.flexigrid({
	  url: '<s:url value="'+url+'" />', 
	  dataType: 'json',    //json格式
	  height: 310,
	  width:720,
	  colModel : [ 
            {display: '',name : '', width : 20, sortable : false, align: 'center',hide:true,toggle:false}, 
            {display: '站点名称', name : '', width : 120, sortable : false, align: 'center'},
	        {display: '站点描述', name : '', width : 140, sortable : true, align: 'center', escape:true},    
	        {display: '站点顺序',name : '', width : 60, sortable : true, align: 'center', escape:true}, 
	        {display: '进站距离', name : '', width : 60, sortable : true, align: 'center', escape:true, process: reWriteinLink},
	        {display: '出站距离', name : '', width : 60, sortable : false, align: 'center', escape:true, process: reWriteoutLink},
	        {display: '上一站点', name : '', width : 50, sortable : false, align: 'center', escape:true, process: reWriteperLink},
	        {display: '播报信息', name : '', width : 135, sortable : false, align: 'center', escape:true, process: showSitereport}
	        ],
	       sortname: 'rs_order',
	       sortorder: 'asc',  //升序OR降序
	       sortable: true,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :true,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: true,    // 是否可以动态设置每页显示的结果数
	       rp: 20,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showTableToggleBtn: true   // 是否允许显示隐藏列
	     }); 
	});
	
	function showSitereport(tdDiv,pid,row) {
		//var str = replaceTextarea1(tdDiv);
		return '<a href="#" onclick="setsitereport(\'' + pid + '\',\''+tdDiv+'\')">'+tdDiv+'</a>';
	}
	var site_id = '';
	function setsitereport(pid,tdDiv) {
		site_id = pid;
		if(tdDiv == '--'){
			$('#remark').val('');
		}else{
			$('#remark').val(tdDiv);
		}
		//window.addEvent('domready', setFormEvent);
			
// 		art.dialog.open("<s:url value='site_reportset_form.shtml' />?route_id="+ $('#route_id').val() + "&site_id="+ pid,{
// 			title:"设置播报内容",
// 			lock:true,
// 			width:350,
// 			height:200
// 		});
		 art.dialog({
				id:'stationID',
				title:'设置播报内容',
				lock:true,
				width:450,
				height:200,
				fixed:true,
			    content:jQuery("#stationInfoDiv").html(),
			    closeFn:function(){
// 			    	alert('d');
			    },
			    open:function(){
			    }
			 	});  
		 
		 gbcount(); 
	}
	
	function reWriteinLink(tdDiv,pid,row) {
		siteids += row.cell[0]+",";
		return '<input value='+tdDiv+' style="height:12px;width:45px;line-height:12px;" />';
	}
	function reWriteoutLink(tdDiv,pid,row) {
		return '<input value='+tdDiv+' style="height:12px;width:45px;line-height:12px;" />';
	}
	function reWriteperLink(tdDiv,pid,row) {
		if(tdDiv == '0')
			return '<input type="checkbox" checked="checked" />';
		else
			return '<input type="checkbox" />';
	}
	
	function settosite() {
		var sites = siteids.split(",");
		//数据参数格式	站点#线路#进站#出站#上一站点#!#
		var param = "";
		for(var s=0;s<sites.length-1;s++) {
			//循环判断数据是否正确 数据只能是数字
			if( isEmpty(get_cell_text(sites[s],4),"进站距离") &&
			isEmpty(get_cell_text(sites[s],5),"出站距离") &&
			isNum(get_cell_text(sites[s],4),"进站距离") &&
			isNum(get_cell_text(sites[s],5),"出站距离") ) {
				if(s!=sites.length-2)
					param += sites[s]+"#"+$('#route_id').val()+"#"+get_cell_text(sites[s],4)+"#"+get_cell_text(sites[s],5)+"#"+get_cell_textcheckbox(sites[s],6)+"#!#";
				else
					param += sites[s]+"#"+$('#route_id').val()+"#"+get_cell_text(sites[s],4)+"#"+get_cell_text(sites[s],5)+"#"+get_cell_textcheckbox(sites[s],6);
			} else {
				return false;
			}
		}
		var data = {"data":param};
		jQuery.post("../route/routesiteset.shtml",data,function(da){
			if(da == 'success') {
				alert("设置成功！");
				art.dialog.close();
			} else {
				alert("设置失败！");
				art.dialog.close();
			}
		});
	}
	function get_cell_text(pid, cell_idx){
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).find("input").val();
	}
	function get_cell_textcheckbox(pid, cell_idx){
		if(jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).find("input").attr("checked") == true)
			return 0;
		else
			return 1;
	}
	
		  
	//判断是否全部为空
	function isEmpty(obj,info) {
        var cur = obj;
        if (cur == "") {  
            alert(info + "不能为空！");
            return false;
        }
	    return true;
	}
	  
	//判断是否全是整数
	function isNum(obj,info) {
        var cur = obj;
        if (!/^\d+$/.test(cur)) {
            alert(info + "必须是大于30小于180整数！");
            return false;
        }
        if(cur < 30 || cur > 180) {
        	alert(info + "必须是大于30小于180整数！");
            return false;
        }
	    return true;
	}
	//取消
	function cancelPoint(){
		//如果存在弹出对话框就关闭
	    if(art.dialog.get('stationID')!=undefined){
			art.dialog.get('stationID').close();
		} 
	}
	//确定修改
	function updatePoint(){
		//保存到数据库
		var remark=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(0).children("#remark").val();
		remark=jQuery.trim(remark);//去除前后空格
		remark=replaceTextarea2(remark);//去除换行符
		if(remark.length>30){
			return false;
		}
		jQuery.post("../routeGrid/updateSiteReport.shtml", {
			'route_id' : $('#route_id').val(),
			'site_id' : site_id,
			'report_content' : remark
		}, function(data) {
			searchList();//重新刷新
		});
		//更新覆盖物
		//updateMarkerPoint(gpointId,false);
		art.dialog.get('stationID').close();
		
	}
	
	//查询公车私用异常查询
	function searchList() {
		jQuery('#gala').flexOptions({
			newp : 1
		});
		jQuery('#gala').flexReload();
	}
	
	
	function gbcount()
	{
	  var max = 30;
	  //max = total.value;
	  var remark=jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(1).children("td").eq(0).children("#remark").val();
	  var rlen=replaceTextarea2(jQuery.trim(remark)).length;
	  if (rlen > max) {
		  jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(0).children("#textleft").css('color','red');
		  jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(0).children("#textleft").html('输入不能超过<font size="3">30</font>个字');
	  }else {
		  var con = max-rlen;
		  jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(0).children("#textleft").css('color','green');
		  jQuery("#stationIDcontent").children("#dataTable").children("tbody").children("tr").eq(2).children("td").eq(0).children("#textleft").html('还可以输入<font size="3">'+con+'</font>个字');
	  }
	}
	//将回车换成<br>
	function replaceTextarea1(str){
		var reg=new RegExp("plm","g"); 
		str = str.replace(reg,"*"); 
		//str = str.replace(reg1,"＜p＞"); 

		return str; 
		}


		function replaceTextarea2(str){
		//var reg=new RegExp("br","g"); 
		//var reg1=new RegExp("＜p＞","g"); 

		//str = str.replace(reg,"\r\n"); 
		//str = str.replace(reg1," "); 
		str=str.replace(/\r/ig, "").replace(/\n/ig, ""); 


		return str; 
		}

</script>
</html>

