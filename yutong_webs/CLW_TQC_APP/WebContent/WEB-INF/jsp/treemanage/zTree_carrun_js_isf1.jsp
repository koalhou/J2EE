<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>
<script>
	var curAsyncCount = 0;
	var treecarlistin = null;

	function getAsyncUrl() {
		return jQuery("#search_route_name").val().replace(/(^\s*)|(\s*$)/g, "")!="" ? 
	    	    '<s:url value="/carrun_history/searchTreeNodesWithoutOnline.shtml" />?name='+encodeURIComponent(encodeURIComponent(jQuery("#search_route_name").val().replace(/(^\s*)|(\s*$)/g, ""))) : 
			    '<s:url value="/carrun_history/getTreeNodesWithoutOnline.shtml" />';
	};
	
	var setting = {
		treeId:'orgTree',
		async: {
			enable: true,
			url:getAsyncUrl
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		},
		callback: {
			onClick: onClick,
			beforeAsync: beforeAsync
			,onAsyncSuccess:checkFirstCar
		},
		view: {
			addDiyDom: addDiyDom
		}
	};
	function addDiyDom(treeId, treeNode) {
		var aObj = jQuery("#" + treeNode.tId + "_ico");
		if("pIcon" != treeNode.iconSkin) {
			jQuery(aObj).html(treeNode.vehicle_code);
		}
	}
	function beforeAsync() {
		curAsyncCount++;
	}

	function onClick(event, treeId, treeNode) {		
		if("pIcon" != treeNode.iconSkin) {			
			mytreeonClick(treeNode.id,treeNode.name,treeNode.vehicle_code,treeNode);
		}
	}

	//1、车辆运行日志-默认选中第一辆车
	function checkFirstCar() {
		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var treeNodes = newTreeObj.transformToArray(newTreeObj.getNodes());
		if(treeNodes.length == 0) {
			jQuery("#treeDemo").html("<div style='text-align:center'>查询不到内容</div>");
		}
		for(var i = 0, len = treeNodes.length; i < len; i++) {
	    	if("pIcon" != treeNodes[i].iconSkin) {
				jQuery("#" + treeNodes[i].tId + "_ico").html(treeNodes[i].vehicle_code);
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
	
</script>

