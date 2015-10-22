<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择车辆</title>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/JQuery zTree v3.1/css/zTreeStyle/zTreeStyle.css'/>" />
<script type="text/javascript" src="../scripts/flexigrid/jquery-1.5.2.min.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>
<style type="text/css">
ul.ztree {position:relative;margin-top: 0px;border: 0px solid #617775;float:left; display:block; width:100%; padding:0px;}
.ztree li button.pIcon_ico_close{margin-right:4px; background: url(../images/ico_folderClosed.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.pIcon_ico_open{margin-right:4px; background: url(../images/ico_folderOpened.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.pIcon_ico_docu{margin-right:4px; background: url(../images/ico_sRoute.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.online_ico_docu{font-family: 微软雅黑;color: #ffffff;font-size: 12px;text-align: center;width: 31px;height: 15px;line-height: 14px;margin-top: 2px;font-weight: bold;margin-right:4px; background: url(../images/xiaocheImage/online.png) no-repeat center ; vertical-align:top; }/**vertical-align:middle*/
.ztree li button.offline_ico_docu{font-family: 微软雅黑;color: #ffffff;font-size: 12px;text-align: center;width: 31px;height: 15px;line-height: 14px;margin-top: 2px;font-weight: bold;margin-right:4px; background: url(../images/xiaocheImage/offline.png) no-repeat center ; vertical-align:top; }/**vertical-align:middle*/
/* .sidebar { */
/* 	width: 260px; */
/* 	height: 572px; */
/* } */
/* .treeTab { */
/* 	line-height: 32px; */
/* 	width: 260px; */
/* 	background : url(../images/xiaocheImage/title_bg.png) repeat-x left top; */
/* 	height: 33px; */
/* 	padding-top: 5px; */
/* } */
/* A {cursor: pointer;color: #0078c8;text-decoration : none;} */
/* .treeTab { */
/* 	line-height: 32px; */
/* 	width: 260px; */
/* 	background : url(../images/xiaocheImage/title_bg.png) repeat-x left top; */
/* 	float : left; */
/* 	height: 33px; */
/* 	padding-top: 5px; */
/* } */
/* .treeTab A.tabfocus { */
/* 	text-align : center; */
/* 	line-height: 32px; */
/* 	width: 68px; */
/* 	display : block; */
/* 	background : url(../images/xiaocheImage/tree_tab.gif) no-repeat left center; */
/* 	float: left; */
/* 	color : #222; */
/* 	margin-left: 4px; */
/* 	text-decoration : none; */
/* } */
/* A.hideLeft { */
/* 	margin : 4px 6px 0px 0px; */
/* 	width: 18px; */
/* 	display: block; */
/* 	background : url(../images/xiaocheImage/ico_hideLeft.gif) no-repeat left top; */
/* 	float : right; */
/* 	height : 18px; */
/* } */
/* A.showLeft { */
/* 	margin : 8px 2px 0px; */
/* 	width : 18px; */
/* 	dispay : block; */
/* 	background : url(../images/xiaocheImage/ico_showLeft.gif) no-repeat left top; */
/* 	float : right; */
/* 	height : 18px; */
/* } */
/* A.btnbule { */
/* 	text-align : center; */
/* 	line-height : 28px; */
/* 	width : 76px; */
/* 	display : block; */
/* 	background : url(../images/xiaocheImage/btn_blue.gif) no-repeat left top; */
/* 	height : 28px; */
/* 	color : #fff; */
/* 	font-weight : blod; */
/* } */
.treeBox {
 	width: 260px; 
 	height: 300px; 
/* 	overflow: auto; */
	background : #fff;
}
/* .newsearchPlan { */
/* 	border-bottom : #b8b8b8 1px solid; */
/* 	line-height: 35px; */
/* 	width: 100%; */
/* 	background: url(../images/xiaocheImage/search_bg.png) #eee repeat-x left top; */
/* 	float : left; */
/* 	height : 40px; */
/* 	padding-top : 4px; */
/* } */
#treeDemo {
	background : #fff;
}
</style>
</head>
<body>
<input type="hidden" id="sichen_vehicle" name="sichen_vehicle" value="<s:property value='vehicle_ln'/>"/>
<input type="hidden" id="sichen_vin" name="sichen_vin" value="<s:property value='vehicle_vin'/>"/>
<%-- <input name="ln" id="ln" value="<s:property value='vehicle_ln'/>" type="hidden"> --%>
<div id="lefttree" class="treeBox" ><!-- style="width: 260px;height: 260px;" -->
	<ul id="treeDemo" class="ztree"></ul>
</div>	
<script>
	var curAsyncCount = 0;

	function getAsyncUrl() {
	    return '../treemanage/getTreeNodesWithOilState.shtml';
	};

	var setting = {
		treeId:'orgTree',
		async: {
			enable: true,
			url:getAsyncUrl
		},
		check: {
			enable: true,
			nocheck :false,
			chkboxType : { "Y" : "ps", "N" : "ps" }
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		}
		,
		callback: {
			onClick: onClick,
			onCheck: onCheck,
			beforeAsync: beforeAsync,
			onAsyncSuccess:onAsyncSuccess
		},
		view: {
			addDiyDom: addDiyDom
		}
	};

	function addDiyDom(treeId, treeNode) {
		var aObj = jQuery("#" + treeNode.tId + "_a");
		var editStr ="";
		jQuery(aObj).find("button").eq(0).html(treeNode.vehicleNo);
	}
	
	function beforeAsync() {
		curAsyncCount++;
	}

	function onClick(event, treeId, treeNode) {
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		if(treeNode.checked) {
			// 当前是复选选择状态
			treeObj.checkNode(treeNode,false,true,true);
		} else {
			// 当前是非选择状态
			treeObj.checkNode(treeNode,true,true,true);
		}
	}
	
	// 复选框选中触发事件
	function onCheck(event, treeId, treeNode) {
		var carList = new Array();
		var carNameList = new Array();
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);

		for(var i = 0, len = nodes.length; i < len; i++) {
			if("pIcon" != nodes[i].iconSkin) {
				carList.push(nodes[i].id);
				carNameList.push(nodes[i].vehicleNo+"("+nodes[i].name+")");
			} 
		}
		document.getElementById("sichen_vin").value = carList;
		document.getElementById("sichen_vehicle").value = carNameList;	 
		 	 
	}

	function check() {
		if (curAsyncCount > 0) {
			return false;
		}
		return true;
	}

	
	jQuery(document).ready( function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
		
	});

	function onAsyncSuccess() {
		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var treeNodes = newTreeObj.transformToArray(newTreeObj.getNodes());

		if(treeNodes.length == 0) {
			jQuery("#treeDemo").html("<div style='text-align:center'>查询不到内容</div>");
		}
		curAsyncCount = 0;
		var ln=$('#sichen_vin').val();
		if($.trim(ln).length>0){
			var arr=ln.split(',');
			for(var i=0;i<arr.length;i++){
				var node=newTreeObj.getNodesByParam("id", arr[i], null);
				if(node.length>0){
					newTreeObj.checkNode(node[0], true, true);
				}
			}
		}
	}

	
</script>


</body>
</html>