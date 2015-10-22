<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" />
</title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterprise_css.jsp"%>
<style type="text/css">
.ztree li span.button.icon01 {
	margin: 0;
	background: url(../images/lvdeng.gif) no-repeat scroll 0 0 transparent;
	vertical-align: middle;
	*vertical-align: middle;
	display: inline-block;
	width: 16px;
	height: 16px;
	margin-left: -6px;
	cursor: pointer;
}
</style>
</head>
<body style="width: 260px; min-width: 260px;">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td id='leftdiv' valign="top" class="leftline">
				<div id="content_leftside">
					<div class="newsearchPlan" style="height: 40px;">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="10px"></td>
								<td width="130" align="right"><input id="search_route_name"
									type="text" class="input120" maxlength="14"
									onkeypress="if(event.keyCode==13){searchRoute();}" />
								</td>
								<td align="center"><a onclick="javascript:searchRoute()"
									class="btnbule">查询</a>
								</td>
							</tr>
						</table>
					</div>
					<div id="treeDemoDiv" class="treeBox_box_isf">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div></td>
		</tr>
		<tr>
			<td align="center"><a href="javascript:void(0);" class="sbutton"
				onclick="checkfalse();">取消</a></td>
		</tr>
	</table>
	<script type="text/javascript" language="JavaScript1.2"
		src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
	<script type="text/javascript" language="JavaScript1.2"
		src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
	<script type="text/javascript" language="JavaScript1.2"
		src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>
	<script>
		var curAsyncCount = 0;
		var treecarlistin = null;

		function getAsyncUrl() {
			return url = jQuery("#search_route_name").val().replace(/(^\s*)|(\s*$)/g, "")!="" ? 
		    	    '<s:url value="/tripPatchList/searchTreeNodes.shtml" />?name='+ encodeURIComponent(encodeURIComponent(jQuery("#search_route_name").val().replace(/(^\s*)|(\s*$)/g, ""))) : 
				    	'<s:url value="/tripPatchList/getTreeNodes.shtml" />';
		};
// 		function getAsyncUrl() {
// 			return '<s:url value="/tripPatchList/getVehicleTree.shtml" />?name='
// 					+ encodeURIComponent(encodeURIComponent(jQuery("#search_route_name").val().replace(/(^\s*)|(\s*$)/g, "")));
// 		};

		var setting = {
			treeId : 'treeDemo',
			async : {
				enable : true,
				url : getAsyncUrl
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : 0
				}
			},
			callback : {
				onClick : onClick,
				beforeAsync : beforeAsync,
				onAsyncSuccess : checkFirstCar
			},
			view : {
				addDiyDom : addDiyDom
			}
		};
		function addDiyDom(treeId, treeNode) {
			var aObj = jQuery("#" + treeNode.tId + "_ico");
			if ("pIcon" != treeNode.iconSkin) {
				jQuery(aObj).html(treeNode.vehicle_code);
			}
		}
		function beforeAsync() {
			curAsyncCount++;
		}

		function onClick(event, treeId, treeNode) {
			if ("pIcon" != treeNode.iconSkin) {
				mytreeonClick(treeNode.id, treeNode.name,treeNode.vehicle_code,treeNode.limite_number);
			}
		}

		//1、默认选中第一辆车
		function checkFirstCar() {
			var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
			var treeNodes = newTreeObj.transformToArray(newTreeObj.getNodes());
			if (treeNodes.length == 0) {
				jQuery("#treeDemo").html(
						"<div style='text-align:center'>查询不到内容</div>");
			}
			for ( var i = 0, len = treeNodes.length; i < len; i++) {
				if ("pIcon" != treeNodes[i].iconSkin) {
					jQuery("#" + treeNodes[i].tId + "_ico").html(
							treeNodes[i].vehicle_code);
				}
			}
			curAsyncCount = 0;
		}

		function check() {
			if (curAsyncCount > 0) {
				return false;
			}
			return true;
		}

		// 查询树
		function searchTree() {
			if (!check()) {
				return;
			}
			jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
		}

		function fliterCars() {
			if (!check()) {
				return;
			}
			jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
		}

		jQuery(document).ready(function() {
			jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
		});
		
		function mytreeonClick(id, name,code,count) {
			window.parent.document.getElementById("vehicle_code").value = code;
			window.parent.document.getElementById("vehicle_ln").value = name;
 			window.parent.document.getElementById("vehicle_ln_span").innerHTML = name+'(核载：'+count+'人)';
 			window.parent.document.getElementById("vehicle_vin").value = id;
 			if(code.length>0){
 		    	window.parent.document.getElementById("vehicle_codeDesc").innerHTML='';
 		    }
 			//得到关联的驾驶员
 			var driver_name="";
 			var driver_id="";
 			jQuery.ajax({
 				type:'post',
 				url:'<s:url value="/tripPatch/getDriverByVin.shtml" />',
 				data:{'vin':id},
 				async:false,
 				success:function(rtn_code){
 					if(rtn_code != ''){
 						driver_id=rtn_code.split(',')[0];
 						driver_name=rtn_code.split(',')[1];
 					}
 				}
 			});
 			window.parent.document.getElementById("driver_id").value = driver_id;
 			window.parent.document.getElementById("driver_name").value = driver_name;
 			if(driver_name.length>0){
 		    	window.parent.document.getElementById("driver_nameDesc").innerHTML='';
 		    }
			art.dialog.close();
		}
		function checkfalse() {
			window.parent.document.getElementById("vehicle_code").value = '';
 			window.parent.document.getElementById("vehicle_ln").value = '';
 			window.parent.document.getElementById("vehicle_vin").value = '';
 			window.parent.document.getElementById("driver_id").value = '';
 			window.parent.document.getElementById("driver_name").value = '';
			//window.parent.searchList();
			art.dialog.close();
		}
		function searchRoute() {
			searchTree();
		}
	</script>
</body>
</html>

