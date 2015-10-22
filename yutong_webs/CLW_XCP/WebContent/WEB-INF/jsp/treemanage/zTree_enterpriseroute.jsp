<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script>
// 线路名称查询项
var routename = "";
var curAsyncCount = 0;

function getAsyncUrl() {
    return routename.replace(/(^\s*)|(\s*$)/g, "")!="" ? '<s:url value="/treemanage/searchRouteTreeByRouteName.shtml" />?name='+encodeURIComponent(encodeURIComponent(routename.replace(/(^\s*)|(\s*$)/g, ""))) : 
    	    	           '<s:url value="/treemanage/getOrganizationRouteData.shtml" />';
};

	var setting = {
		treeId:'orgTree',
		async: {
			enable: true,
			url:getAsyncUrl
		},data: {
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
			onAsyncSuccess:checkFirstRoute
		}
	};

	function beforeAsync() {
		curAsyncCount++;
	}

	
	function onClick(event, treeId, treeNode) {
		if("pIcon" == treeNode.iconSkin) {
			//alert("组织click，id=" + treeNode.id);
		} else {
			//alert("线路click，id=" + treeNode.id);
			//alert("线路click，name=" + treeNode.name);
			onClickRoute(treeNode.id);
		}
	}			
	//1、线路监控-默认选中第一条线路
	function checkFirstRoute() {
		var url = window.location.href;
		if(url.indexOf("routechart/ready") > 0){
		    var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		    // 获取树上所有节点
		    var nodes = treeObj.transformToArray(treeObj.getNodes());
		    for(var i = 0, len = nodes.length; i < len; i++) {
		         if("pLine" == nodes[i].iconSkin) {
			         treeObj.selectNode(nodes[i],false);
			         onClickRoute(nodes[i].id);
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
	
	jQuery(document).ready(function(){
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});
</script>

