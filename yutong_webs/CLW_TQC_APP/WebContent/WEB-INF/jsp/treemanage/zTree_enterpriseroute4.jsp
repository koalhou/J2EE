<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>
<style type="text/css">
.ztree li button.pIcon_ico_docu {
	margin-right: 4px;
	background: url(../images/ico_folderOpened.gif) no-repeat 0px 0px;
	vertical-align: top;
	*vertical-align: middle
}
</style>
<script>
	// 线路名称查询项
	var routename = "";
	var curAsyncCount = 0;
	var treeType = "";
	//判断数据是否成功返回
	var iftruereturn = 0;

	function getAsyncUrl() {
		var url = "";
		if("routeup" == treeType) {
			url = routename.replace(/(^\s*)|(\s*$)/g, "") != "" ? '<s:url value="/treemanage/tqcsearchRouteTree.shtml" />?name='
				+ encodeURIComponent(encodeURIComponent(routename.replace(/(^\s*)|(\s*$)/g, "")))+"&route_class=0":'<s:url value="/treemanage/tqcgetOrganizationRouteData.shtml" />'+"?route_class=0";
		} else if("routedown" == treeType) {
		 	url = routename.replace(/(^\s*)|(\s*$)/g, "") != "" ? '<s:url value="/treemanage/tqcsearchRouteTree.shtml" />?name='
				+ encodeURIComponent(encodeURIComponent(routename.replace(/(^\s*)|(\s*$)/g, "")))+"&route_class=1":'<s:url value="/treemanage/tqcgetOrganizationRouteData.shtml" />'+"?route_class=1";
		} else if("routetqc" == treeType) {
			 url = routename.replace(/(^\s*)|(\s*$)/g, "") != "" ? '<s:url value="/treemanage/tqcsearchRouteTree.shtml" />?name='
				+ encodeURIComponent(encodeURIComponent(routename.replace(/(^\s*)|(\s*$)/g, "")))+"&route_class=2":'<s:url value="/treemanage/tqcgetOrganizationRouteData.shtml" />'+"?route_class=2";
		}
		return url;
	};

	var setting = {
		treeId : 'orgTree',
		async : {
			enable : true,
			url : getAsyncUrl
		},
		check: {
			enable: false,
			nocheck :false,
			chkboxType : { "Y" : "ps", "N" : "ps" }
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
			onCheck: onCheck,
			beforeAsync : beforeAsync,
			onAsyncSuccess : checkFirstRoute
		}
	};

	function beforeAsync() {
		curAsyncCount++;
	}

	function onClick(event, treeId, treeNode) {
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		if(treeNode.checked) {
			// 当前是复选选择状态
			treeObj.checkNode(treeNode,false,true,true);
			treeNode.checkedOld = false;
		} else {
			// 当前是非选择状态
			treeObj.checkNode(treeNode,true,true,true);
			treeNode.checkedOld = true;
		}
		onClickRoute_click(treeNode.id);
	}
	//1、线路监控-默认选中第一条线路
	function checkFirstRoute() {
		var url = window.location.href;
		var content = document.getElementById("content").className;
		if (content.indexOf("routemonitorDivArea") >= 0) {
			var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
			// 获取树上所有节点
			var nodes = treeObj.transformToArray(treeObj.getNodes());
			for ( var i = 0, len = nodes.length; i < len; i++) {
				if ("pLine" == nodes[i].iconSkin) {
					treeObj.selectNode(nodes[i], true);
					//treeObj.checkNode(nodes[i],true,true,true);
					onClickRoute_click(nodes[i].id);
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

	// 复选框选中触发事件
	function onCheck(event, treeId, treeNode) {
		var carList = new Array();
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.transformToArray(treeObj.getNodes());
		
		for(var i = 0, len = nodes.length; i < len; i++) {
			if("pIcon" != nodes[i].iconSkin && nodes[i].checked) {
				carList.push(nodes[i].id);
			}
		}
		onClickRoute_check(carList,'');
		/* if ("pIcon" == treeNode.iconSkin) {
			onClickRoute("ent"+treeNode.id);
		} else {
			//alert("线路click，id=" + treeNode.id);
			//alert("线路click，name=" + treeNode.name);
			onClickRoute(treeNode.id);
		} */
	}
	jQuery(document).ready(function() {
		treeType = "routeup";
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});
</script>

