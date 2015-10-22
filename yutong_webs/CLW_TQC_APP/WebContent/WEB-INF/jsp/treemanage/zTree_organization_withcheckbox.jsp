<%@page language="java" contentType="text/html;charset=utf-8"%>

<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>

<script>
	var curAsyncCount = 0;
	//树查询状态，只有数据返回成功之后方可进行第二次查询
	var checkoneclick = 0;
	
	function getAsyncUrl() {
		var url = '<s:url value="/treemanage/getOrganizationTreeData.shtml" />';
	    return url;
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
			chkboxType : { "Y" : "s", "N" : "ps" }
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
			onCheck: onCheck,
			beforeAsync: beforeAsync,
			onAsyncSuccess:refreshTreeStatus
		},view: {
			addDiyDom: addDiyDom
		}
	};
	function addDiyDom(treeId, treeNode) {
		/*var aObj = jQuery("#" + treeNode.tId + "_a");
		if("pIcon" != treeNode.iconSkin) {
			if(treeNode.vehicleType!=null&&treeNode.vehicleType=='0') {
				if(treeNode.vehicleCode == null) {
					if(jQuery(aObj).find(".offline_ico_docu").length>0)
						jQuery(aObj).find(".offline_ico_docu").html("未知");
					if(jQuery(aObj).find(".online_ico_docu").length>0)
						jQuery(aObj).find(".online_ico_docu").html("未知");
					//editStr = "<span id='diyBtn_" +treeNode.id+"' name='"+ treeNode.id + "' class='button' style=''>(空)</span>";
				}else {
					if(jQuery(aObj).find(".offline_ico_docu").length>0)
						jQuery(aObj).find(".offline_ico_docu").html(treeNode.vehicleCode);
					if(jQuery(aObj).find(".online_ico_docu").length>0)
						jQuery(aObj).find(".online_ico_docu").html(treeNode.vehicleCode);
					//editStr = "<span id='diyBtn_" +treeNode.id+"' name='"+ treeNode.id + "' class='button' style=''>("+treeNode.vehicleCode+")</span>";
				}
			} else if(treeNode.vehicleType!=null&&treeNode.vehicleType=='1') {
				if(jQuery(aObj).find(".offline_ico_docu").length>0)
					jQuery(aObj).find(".offline_ico_docu").html("外租");
				if(jQuery(aObj).find(".online_ico_docu").length>0)
					jQuery(aObj).find(".online_ico_docu").html("外租");
			}
		}*/
	}
		

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
	}
	
	// 复选框选中触发事件
	function onCheck(event, treeId, treeNode) {
		var carList = new Array();
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getChangeCheckedNodes();
		if(treeNode.checked) {
			for(var i = 0, len = nodes.length; i < len; i++) {
				nodes[i].checkedOld = true;
				if("pIcon" != nodes[i].iconSkin) {
					carList.push(nodes[i].id);
				}
			}
			
		} else {
			for(var i = 0, len = nodes.length; i < len; i++) {
				nodes[i].checkedOld = false;
				if("pIcon" != nodes[i].iconSkin) {
					carList.push(nodes[i].id);
				}
			}
		}
		// 车辆信息列表
		//alert(carList+treeNode.checked);
		//decodeSelectV(carList,treeNode.checked);
		
		//car(carList,treeNode.checked);
	}

	// 节点展开列表
	var openNodesId;
	// 节点关闭列表
	var closeNodesId;
	// 节点选中状态
	var checkNodesId;

	// 定时更新状态保持
	function refreshTreeStatus() {
		
		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		if($('update').value == "update"){
			var organizationArr = $('organization_id').value.split(',');
			var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
			for(var i = 0;i<organizationArr.length;i++){
				var node =newTreeObj.getNodeByParam("id",organizationArr[i]);
				newTreeObj.checkNode(node,true); 
			}
		}
		
		/*var refreshNodes = newTreeObj.transformToArray(newTreeObj.getNodes());
		if (null != openNodesId) {
			if(jQuery("#vehicleLn").val() == "") {
				for ( var j = 0, len = openNodesId.length; j < len; j++) {
					for ( var k = 0, lenNodes = refreshNodes.length; k < lenNodes; k++) {
						if (refreshNodes[k].id == openNodesId[j]) {
							newTreeObj.expandNode(refreshNodes[k], true, false, false,false);
						}
					}
				}
			} else {
				for ( var m = 0, closeLen = closeNodesId.length; m < closeLen; m++) {
					for ( var n = 0, lenNodes1 = refreshNodes.length; n < lenNodes1; n++) {
						if (refreshNodes[n].id == closeNodesId[m]) {
							newTreeObj.expandNode(refreshNodes[n], false, false, false,false);
						}
					}
				}
			}
			
		}

		if(null != checkNodesId) {
			for( var m =0, lenChecked = checkNodesId.length; m < lenChecked; m++) {
				for ( var n = 0, lenNodesNew = refreshNodes.length; n < lenNodesNew; n++) {
					if (refreshNodes[n].id == checkNodesId[m]) {
						newTreeObj.checkNode(refreshNodes[n],true,false,false);
						refreshNodes[n].checkedOld = true;
					}
				}
			}
		}

		if(refreshNodes.length == 0) {
			jQuery("#treeDemo").html("<div style='text-align:center'>查询不到内容</div>");
		}
		
		curAsyncCount = 0;
		checkoneclick = 1;*/
	}

	function check() {
		if (curAsyncCount > 0) {
			return false;
		}
		return true;
	}
		
	// 刷新树
	function refreshTree() {
		if (!check()) {
			return;
		}
		
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var localNodes = treeObj.transformToArray(treeObj.getNodes());
		openNodesId = new Array();
		checkNodesId = new Array();
		closeNodesId = new Array();
		
		for ( var i = 0, l = localNodes.length; i < l; i++) {
			if (localNodes[i].open) {
				openNodesId.push(localNodes[i].id);
			} else {
				if("pIcon" == localNodes[i].iconSkin) {
					closeNodesId.push(localNodes[i].id);
				}
			}
			
			if (localNodes[i].checked) {
				checkNodesId.push(localNodes[i].id);
			} 
		}

		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}

	// 查询树
	function searchTree() {
		if(checkoneclick==0) {
			return false;
		}else{
			checkoneclick = 0;
		}
		if (!check()) {
			return;
		}
		
		// 初始化状态
		openNodesId = new Array();
		checkNodesId = new Array();
		closeNodesId = new Array();
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}

	// 获取当前选中的车辆
	function getCheckedCars() {
		var carList = new Array();
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);

		for(var i = 0, len = nodes.length; i < len; i++) {
			if("pIcon" != nodes[i].iconSkin) {
				carList.push(nodes[i].id);
			}
		}
		// 车辆信息列表
		//alert(carList);
		return carList;
	}
	
	jQuery(document).ready( function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});
</script>

