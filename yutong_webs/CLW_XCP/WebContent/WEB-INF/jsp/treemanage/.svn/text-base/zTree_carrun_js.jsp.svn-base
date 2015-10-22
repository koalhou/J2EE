<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>
<script>
	var curAsyncCount = 0;

	function getAsyncUrl() {
		if(jQuery("#filterFlag").attr("checked")==true) {
			// 过滤
			return jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, "")!="" ? 
		    	    '<s:url value="/carrun_history/searchPlannedTreeNodesWithoutOnline.shtml" />?name='+encodeURIComponent(encodeURIComponent(jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, ""))) : 
		    	    '<s:url value="/carrun_history/getPlannedTreeNodes.shtml" />';
		} else {
			return jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, "")!="" ? 
		    	    '<s:url value="/carrun_history/searchTreeNodesWithoutOnline.shtml" />?name='+encodeURIComponent(encodeURIComponent(jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, ""))) : 
				    '<s:url value="/carrun_history/getTreeNodesWithoutOnline.shtml" />';
		}
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
			beforeAsync: beforeAsync,
			onAsyncSuccess:checkFirstCar
		}
	};

	function beforeAsync() {
		curAsyncCount++;
	}

	function onClick(event, treeId, treeNode) {
		var url = window.location.href;
		if(url.indexOf("ridealarm/ready.shtml") > 0){
			// 异常乘车统计
			if("pIcon" == treeNode.iconSkin) {
				mytreeonClick('0',treeNode);
				//alert("组织click，id=" + treeNode.id);
			} else {
				mytreeonClick('1',treeNode);
				//alert("车辆click，id=" + treeNode.id);
			}
		} else {
			if("pIcon" == treeNode.iconSkin) {
				mytreeonClick('0',treeNode.id);
				//alert("组织click，id=" + treeNode.id);
			} else {
				mytreeonClick('1',treeNode.id);
				//alert("车辆click，id=" + treeNode.id);
			}
		}
	}

	//1、车辆运行日志-默认选中第一辆车
	function checkFirstCar() {
		var url = window.location.href;
		if(url.indexOf("carrun/ready") > 0){
		    var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		    // 获取树上所有节点
		    var nodes = treeObj.transformToArray(treeObj.getNodes());
		    for(var i = 0, len = nodes.length; i < len; i++) {
		         if("pIcon" != nodes[i].iconSkin) {
			         treeObj.selectNode(nodes[i],false);
			         mytreeonClick('1',nodes[i].id);
			         break;
			      }
		      }
		}

		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var treeNodes = newTreeObj.transformToArray(newTreeObj.getNodes());
		if(treeNodes.length == 0) {
			jQuery("#treeDemo").html("<div style='text-align:center'>查询不到内容</div>");
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
	
	jQuery(document).ready( function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});
	
</script>

