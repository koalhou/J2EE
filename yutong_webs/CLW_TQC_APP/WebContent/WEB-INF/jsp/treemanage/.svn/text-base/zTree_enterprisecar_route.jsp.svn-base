<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<style type="text/css">
/* 当线路查询时，查询到的子企业下午线路用此背景 */
.ztree li button.pIcon_ico_docu {
	margin-right: 4px;
	background: url(../images/ico_folderOpened.gif) no-repeat scroll 0 0 transparent;
	vertical-align: top;
	*vertical-align: middle
}
</style>
<script>
// 线路名称查询项
var routename = "";
var curAsyncCount = 0;
var routeclass = 0;
//判断数据是否成功返回
var iftruereturn = 0;

function getAsyncUrl() {
    //return '<s:url value="/treemanage/tqc_car_getRouteData.shtml" />?routeinfo='+routename+"&routeclass="+routeclass;
	var url = "";
	if("0" == routeclass) {
		url = routename.replace(/(^\s*)|(\s*$)/g, "") != "" ? '<s:url value="/treemanage/tqcsearchRouteTree.shtml" />?name='
			+ encodeURIComponent(encodeURIComponent(routename.replace(/(^\s*)|(\s*$)/g, "")))+"&route_class=0":'<s:url value="/treemanage/tqcgetOrganizationRouteData.shtml" />'+"?route_class=0";
	} else if("1" == routeclass) {
	 	url = routename.replace(/(^\s*)|(\s*$)/g, "") != "" ? '<s:url value="/treemanage/tqcsearchRouteTree.shtml" />?name='
			+ encodeURIComponent(encodeURIComponent(routename.replace(/(^\s*)|(\s*$)/g, "")))+"&route_class=1":'<s:url value="/treemanage/tqcgetOrganizationRouteData.shtml" />'+"?route_class=1";
	} else if("2" == routeclass) {
		 url = routename.replace(/(^\s*)|(\s*$)/g, "") != "" ? '<s:url value="/treemanage/tqcsearchRouteTree.shtml" />?name='
			+ encodeURIComponent(encodeURIComponent(routename.replace(/(^\s*)|(\s*$)/g, "")))+"&route_class=2":'<s:url value="/treemanage/tqcgetOrganizationRouteData.shtml" />'+"?route_class=2";
	}
	return url;
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
		} else if("pLine" == treeNode.iconSkin) {
			//alert("线路click，id=" + treeNode.id);
			//alert("线路click，name=" + treeNode.name);
			onClickRoute(treeNode.id,treeNode.route_class);
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
	
	/* jQuery(document).ready(function(){
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}); */
	
	
	jQuery(document).ready(function() {
		<s:if test="#session.perUrlList.contains('111_3_5_3_5')">
		routeclass = 0;
		</s:if>
		<s:elseif test="#session.perUrlList.contains('111_3_5_3_6')">
		routeclass = 1;
		</s:elseif>
		<s:elseif test="#session.perUrlList.contains('111_3_5_3_7')">
		routeclass = 2;
		</s:elseif>
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});
</script>

