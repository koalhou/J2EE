<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script>
	// 线路名称查询项
	var routename = "";
	var curAsyncCount = 0;
	var treeType = "";
	//判断是否加载完成
	var iftruereturn = 0;

	function getAsyncUrl() {
		var url = "";
		var timestamp = Date.parse(new Date());
		if("routeup" == treeType) {
			url = routename.replace(/(^\s*)|(\s*$)/g, "") != "" ? '<s:url value="/treemanage/tqcsearchRouteTree.shtml" />?name='
				+ encodeURIComponent(encodeURIComponent(routename.replace(/(^\s*)|(\s*$)/g, "")))+"&route_class=0"+"&t="+timestamp:'<s:url value="/treemanage/tqcgetOrganizationRouteData.shtml" />'+"?route_class=0"+"&t="+timestamp;
		} else if("routedown" == treeType) {
		 	url = routename.replace(/(^\s*)|(\s*$)/g, "") != "" ? '<s:url value="/treemanage/tqcsearchRouteTree.shtml" />?name='
				+ encodeURIComponent(encodeURIComponent(routename.replace(/(^\s*)|(\s*$)/g, "")))+"&route_class=1"+"&t="+timestamp:'<s:url value="/treemanage/tqcgetOrganizationRouteData.shtml" />'+"?route_class=1"+"&t="+timestamp;
		} else if("routetqc" == treeType) {
			 url = routename.replace(/(^\s*)|(\s*$)/g, "") != "" ? '<s:url value="/treemanage/tqcsearchRouteTree.shtml" />?name='
				+ encodeURIComponent(encodeURIComponent(routename.replace(/(^\s*)|(\s*$)/g, "")))+"&route_class=2"+"&t="+timestamp:'<s:url value="/treemanage/tqcgetOrganizationRouteData.shtml" />'+"?route_class=2"+"&t="+timestamp;
		}
		return url;
	};

	var setting = {
		treeId : 'orgTree',
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
			onAsyncSuccess : checkFirstRoute
		}
	};

	function beforeAsync() {
		curAsyncCount++;
	}

	function onClick(event, treeId, treeNode) {
		if ("pIcon" == treeNode.iconSkin) {
		} else {
			//alert("线路click，id=" + treeNode.id);
			//alert("线路click，name=" + treeNode.name);
			onClickRoute(treeNode.id,treeNode.name);
		}
	}
	//1、线路监控-默认选中第一条线路
	function checkFirstRoute(){
		var url = window.location.href;
		if (url.indexOf("readyrc") >= 0) {
			var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
			// 获取树上所有节点
			var nodes = treeObj.transformToArray(treeObj.getNodes());
			for ( var i = 0, len = nodes.length; i < len; i++) {
				if ("pLine" == nodes[i].iconSkin) {
					treeObj.selectNode(nodes[i], false);
					onClickRoute(nodes[i].id,nodes[i].name);
					break;
				}
			}
		}

		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var treeNodes = newTreeObj.transformToArray(newTreeObj.getNodes());
		if (treeNodes.length == 0) {
			jQuery("#treeDemo").html("<div style='text-align:center'>查询不到内容</div>");
		}
		curAsyncCount = 0;
		iftruereturn = 1;
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

	jQuery(document).ready(function() {
		<s:if test="#session.perUrlList.contains('111_3_5_3_5')">
			treeType = "routeup";
		</s:if>
			<s:elseif test="#session.perUrlList.contains('111_3_5_3_6')">
		treeType = "routedown";
			</s:elseif>
		<s:elseif test="#session.perUrlList.contains('111_3_5_3_7')">
			treeType = "routetqc";
		</s:elseif>
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});
</script>

